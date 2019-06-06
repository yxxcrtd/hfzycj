package com.zycj.tcc.remote.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.domain.PrePayCardCheckInfo;
import com.zycj.tcc.domain.PrePayCardInfo;
import com.zycj.tcc.exception.ArrearRepeatPayException;
import com.zycj.tcc.server.annotation.ServiceClass;
import com.zycj.tcc.server.annotation.ServiceMethod;
import com.zycj.tcc.server.vo.DataResponse;
import com.zycj.tcc.server.vo.PrePayCardVo;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.service.ArrearService;
import com.zycj.tcc.service.PrePayCardRecordService;
import com.zycj.tcc.service.PrePayCardService;
import com.zycj.tcc.service.impl.PrePayCardServiceImpl;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.vo.TraceInfoVo;

/**
 * @author xiechanglei
 */
@ServiceClass
@Service
public class PrePayCardServiceInteFace {
	private final static Logger log = Logger.getLogger(PrePayCardServiceInteFace.class);

	@Autowired
	private ArrearService arrearService;

	@Autowired
	private PrePayCardService prePayCardService;

	@Autowired
	private PrePayCardRecordService prePayCardRecordService;

	/**
	 * 刷卡支付停车费 预付费卡返回的json成功是一种格式，失败又是一种格式，业务逻辑错误又是一种格式，没有办法只能用json一层一层解析 后期需要重构
	 * 
	 * @param params
	 * @return
	 */
	@ServiceMethod(type = "0061", note = "预付费消费")
	public Response deal(Map<String, String> params) {
		return prePayCardService.pay(params);
	}

	/**
	 * 实现在pos机上面充值的功能，目前仅支持现金充值
	 * 
	 * @param params
	 * @return
	 */
	@ServiceMethod(type = "0062", note = "预付费充值")
	public Response addMoney(Map<String, String> params) {

		DataResponse dataResponse = new DataResponse();
		dataResponse.setResultCode(ResultCode.SUCCESS);
		// 除合肥地区其他地区暂时不能使用
		if (!"0".equals(params.get("emp_no").substring(0, 1))) {
			dataResponse.setData(new PrePayCardVo(false, "该地区暂时无法使用"));
			return dataResponse;
		}
		String amount = params.get("chargeAmount");// 充值金额
		if (!checkAmount(amount)) {
			dataResponse.setData(new PrePayCardVo(false, "金额错误"));
		} else {
			prePayCardService.chongzhi(params, dataResponse, amount);
		}
		return dataResponse;
	}

	@ServiceMethod(type = "0063", note = "预付费 现金充值轧帐查询")
	public Response queryCheck(Map<String, String> params) {
		DataResponse dataResponse = new DataResponse();
		dataResponse.setResultCode(ResultCode.SUCCESS);
		List<PrePayCardCheckInfo> checks = prePayCardRecordService.getNotCheckInfo(params.get("emp_no"));
		dataResponse.setData(checks);
		return dataResponse;
	}

	@ServiceMethod(type = "0064", note = "预付费 现金充值轧帐")
	public Response Check(Map<String, String> params) {
		DataResponse dataResponse = new DataResponse();
		String emp_no = params.get("emp_no");
		String date = params.get("date");
		String amount = params.get("amount");
		prePayCardRecordService.check(emp_no, date, amount);
		dataResponse.setResultCode(ResultCode.SUCCESS);
		return dataResponse;
	}

	@ServiceMethod(type = "0065", note = "预付费余额查询")
	public Response getAmount(Map<String, String> params) {
		DataResponse dataResponse = new DataResponse();
		dataResponse.setResultCode(ResultCode.SUCCESS);
		String cardNo = params.get("cardNo");
		// 除合肥地区其他地区暂时不能使用
		if (!"0".equals(params.get("emp_no").substring(0, 1))) {
			PrePayCardInfo prePayCardInfo = new PrePayCardInfo(false, cardNo, 0);
			prePayCardInfo.setChargeAble(false);
			return dataResponse;
		}
		try {
			int amount = prePayCardService.getAmountByCardNo(cardNo);
			PrePayCardInfo prePayCardInfo = new PrePayCardInfo(true, cardNo, amount / 100);
			// 查询该员工的充值金额，是否有充值权限
			int charge = prePayCardRecordService.getEmpChargeAmount(params.get("emp_no"));
			if (amount + charge <= PrePayCardServiceImpl.MAX_EMP_AMOUNT) {
				prePayCardInfo.setChargeAble(true);
			}
			dataResponse.setData(prePayCardInfo);
		} catch (Exception e) {
			dataResponse.setData(new PrePayCardInfo(false, cardNo, 0));
			e.printStackTrace();
		}
		return dataResponse;
	}

	@ServiceMethod(type = "0066", note = "使用预付费卡补缴欠费接口")
	public Response payArrear(Map<String, String> params) {
		DataResponse dataResponse = new DataResponse();
		dataResponse.setResultCode(ResultCode.SUCCESS);
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		String ids = params.get("idList"); // 欠费主键ID
		String amount = params.get("amount");// 应缴
		Integer payType = 10;// 预付费卡支付
		String reqSerial = params.get("reqseries_no");
		String invoiceBatch = params.get("invoice_id");
		String invoiceNo = params.get("invoice");
		String carNo = params.get("carNo");
		String cardNo = params.get("cardNo");
		if (StringUtils.isNotBlank(ids) && "0".equals(ids)) {
			Date selectTime = null;
			try {
				selectTime = DateUtil.parse_YMDHMS(params.get("serverTime"));
			} catch (Exception e) {
				dataResponse.setData(new PrePayCardVo(false, "服务时间格式不正确！"));
				return dataResponse;
			}
			ids = arrearService.selectIdListByCarNo(carNo, selectTime);
		}

		if (StringUtils.isBlank(ids)) {
			dataResponse.setData(new PrePayCardVo(false, "补缴欠费的ids为空！"));
			return dataResponse;
		}

		BigDecimal reallAmountx = null;
		BigDecimal amountx = null;
		try {
			reallAmountx = new BigDecimal(amount);
			amountx = new BigDecimal(amount);
		} catch (Exception e) {
			dataResponse.setData(new PrePayCardVo(false, "欠费金额格式不正确！"));
			return dataResponse;
		}
		try {
			return arrearService.payArrearByPrePayCard(ids, reallAmountx, amountx, posNo, empNo, payType, reqSerial,
					invoiceBatch, invoiceNo, getTraceInfoVo(params), cardNo, carNo);

		} catch (Exception e) {
			if (e instanceof ArrearRepeatPayException) {
				dataResponse.setData(new PrePayCardVo(false, "欠费已经补缴了，重复补缴！"));
				return dataResponse;
			}
			log.warn("补缴失败，系统出现了异常！", e);
			dataResponse.setData(new PrePayCardVo(false, "补缴失败，系统出现了异常！"));
			return dataResponse;
		}
	}

	/**
	 * 获取格式化时间
	 * 
	 * @param date
	 * @return
	 */
	public String getTimeStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}

	private TraceInfoVo getTraceInfoVo(Map<String, String> params) {
		return new TraceInfoVo(params.get("trace_no"), params.get("reference_no"), params.get("card_no"));
	}

	/**
	 * 判断金额是否合法
	 * 
	 * @param amount
	 * @return
	 */
	public boolean checkAmount(String amount) {
		try {
			double parseDouble = Double.parseDouble(amount);
			if (parseDouble <= 0) {
				return false;
			}
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
