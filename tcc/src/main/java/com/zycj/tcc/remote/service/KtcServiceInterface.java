package com.zycj.tcc.remote.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.common.ArrearStatus;
import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.OrderPayType;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.domain.Arrear;
import com.zycj.tcc.exception.ArrearRepeatPayException;
import com.zycj.tcc.server.annotation.ServiceClass;
import com.zycj.tcc.server.annotation.ServiceMethod;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.service.ArrearService;
import com.zycj.tcc.service.OrderInfoService;
import com.zycj.tcc.service.ParkService;
import com.zycj.tcc.service.QueryService;
import com.zycj.tcc.util.ArrayUtil;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.vo.TraceInfoVo;

/**
 * 远程服务接口
 * 提供给外部使用
 * @author 洪捃能
 */
@ServiceClass
@Service
public class KtcServiceInterface {
	private final static Logger log = Logger.getLogger(KtcServiceInterface.class); 
	
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private ParkService parkService;
	@Autowired
	private ArrearService arrearService;
	@Autowired
	private QueryService queryService;
	
	
	@ServiceMethod(type="0030",note="查询订单状态")
	public Response serachOrderStatus(Map<String, String> params){
		String orderIdS=params.get("order_id");
		Integer orderId = null;
		try {
			orderId=Integer.parseInt(orderIdS);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.ORDERID_IS_NOT_NUMBER_ERROR);
		}
		try {
			return orderInfoService.checkIsKtcOut(orderId);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.createResponse(ResultCode.SYSTEM_ERROR);
		}
	}
	
	
	@ServiceMethod(type="0031",note="查询未打印发票网络支付订单")
	public Response serachOrderNoPrintByCarNo(Map<String, String> params){
		String carNo = params.get("car_no");
		try {
			return queryService.queryInvoiceInfo2PrintByCarNo(carNo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.createResponse(ResultCode.SYSTEM_ERROR);
		}
	}
	
	@ServiceMethod(type="0055",note="KTC停车金额支付成功更改订单状态")
	public Response pay(Map<String, String> params){
		String orderId=params.get("order_id");//订单id
		int orderIdx=0;
		String reallAmount=params.get("reall_amount");//实收金额
		String outTime=params.get("out_time");//驶出时间
		String outType=params.get("out_type");//驶出类型
		int outTypex=0;
		
		String dataType=params.get("data_type");//1 数据即时发送 2 数据重新发送
		
		if(StringUtils.isBlank(orderId)){
			return ResponseUtil.createResponse(ResultCode.ORDERNO_ISBLANK_ERROR);
		}
		try {
			orderIdx=Integer.parseInt(orderId);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.ORDERID_IS_NOT_NUMBER_ERROR);
		}
		if(StringUtils.isBlank(reallAmount)){
			return ResponseUtil.createResponse(ResultCode.REALL_AMOUNT_ISBLANK_ERROR);
		}
		if(StringUtils.isBlank(outTime)){
			return ResponseUtil.createResponse(ResultCode.OUTTIME_ISBLANK_ERROR);
		}
		if(StringUtils.isBlank(outType)){
			return ResponseUtil.createResponse(ResultCode.OUTTYPE_ISBLANK_ERROR);
		}
		try {
			outTypex=Integer.parseInt(outType);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.OUTTYPE_IS_NOT_NUMBER_ERROR);
		}
		//校验时间格式
		Date outTimex=null;
		try {
			outTimex=DateUtil.parse_YMDHMS(outTime);
		} catch (ParseException e) {
			log.warn("ktcorderId="+orderId+"支付停车金额时间格式异常",e);
			return ResponseUtil.createResponse(ResultCode.CAR_EXIT_OUTTIME_FORMAT_ERROR);
		}
		if(Constants.Data_Send_repeat.equals(dataType)){//如果是重新发送
			log.warn("ktc："+orderIdx);
		}
		return parkService.pay(orderIdx,reallAmount,outTimex,outTypex);
	}
	
	@ServiceMethod(type="0050",note="KTC确认驶出")
	public Response carExit(Map<String, String> params){
		String empNo=params.get("emp_no");
		String posNo=params.get("pos_no");
		String orderId=params.get("order_id");//订单id
		int orderIdx=0;
		String reallAmount=params.get("reall_amount");//实收金额
		String amount=params.get("amount");//应收金额
		String outTime=params.get("out_time");//驶出时间
		String outType=params.get("out_type");//驶出类型
		int outTypex=0;
		String invoiceId=params.get("invoice_id");//发票代号
		String invoice=params.get("invoice");//发票号码
		String reqseriesNo=params.get("reqseries_no");
		
		String dataType=params.get("data_type");//1 数据即时发送 2 数据重新发送
		
		String carNo=params.get("car_no");
		
		if(StringUtils.isBlank(orderId)){
			return ResponseUtil.createResponse(ResultCode.ORDERNO_ISBLANK_ERROR);
		}
		try {
			orderIdx=Integer.parseInt(orderId);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.ORDERID_IS_NOT_NUMBER_ERROR);
		}
		if(StringUtils.isBlank(reallAmount)){
			return ResponseUtil.createResponse(ResultCode.REALL_AMOUNT_ISBLANK_ERROR);
		}
		if(StringUtils.isBlank(amount)){
			return ResponseUtil.createResponse(ResultCode.AMOUNT_ISBLANK_ERROR);
		}
		if(StringUtils.isBlank(outTime)){
			return ResponseUtil.createResponse(ResultCode.OUTTIME_ISBLANK_ERROR);
		}
		if(StringUtils.isBlank(outType)){
			return ResponseUtil.createResponse(ResultCode.OUTTYPE_ISBLANK_ERROR);
		}
		try {
			outTypex=Integer.parseInt(outType);
//			if (outTypex != 7) {
//				outTypex =7;
//				log.error("ktcout:type="+outType);
//			}
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.OUTTYPE_IS_NOT_NUMBER_ERROR);
		}
		//校验时间格式
		Date outTimex=null;
		try {
			outTimex=DateUtil.parse_YMDHMS(outTime);
		} catch (ParseException e) {
			log.warn("ktcorderId="+orderId+"确认驶出时间格式异常",e);
			return ResponseUtil.createResponse(ResultCode.CAR_EXIT_OUTTIME_FORMAT_ERROR);
		}
		if(Constants.Data_Send_repeat.equals(dataType)){//如果是重新发送
			log.warn("ktc重发 驶出 订单："+orderIdx);
		}
		return parkService.carExit(empNo,posNo,orderIdx,reallAmount,amount,outTimex,outTypex,invoiceId,invoice,reqseriesNo,getTraceInfoVo(params),carNo);
	}
	
	
	@ServiceMethod(type="0051",note="KTC车辆欠费列表查询分页")
	public Response getArrearListByCarNo(Map<String, String> params){
		String carNo = params.get("car_no");
		int carType = Integer.parseInt(params.get("carType"));
		int pageNo = MapUtils.getIntValue(params, "page_no",1);
		if(StringUtils.isBlank(carNo)){
			return ResponseUtil.createResponse(ResultCode.CARNO_ISBLANK_ERROR);
		}
		return arrearService.getArrearListByCarNo(carNo, carType, pageNo);
	}
	
	
	@ServiceMethod(type="0052",note="KTC车辆欠费补缴接口")
	public Response payArrear(Map<String, String> params){
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		String ids = params.get("id_list");
		String reallAmount=params.get("reall_amount");//实缴
		String amount=params.get("amount");//应缴
		Integer payType = MapUtils.getInteger(params, "out_type");
		String reqSerial = params.get("reqseries_no");
		String invoiceBatch = params.get("invoice_id");
		String invoiceNo = params.get("invoice");
		
		String dataType=params.get("data_type");//1 数据即时发送 2 数据重新发送
		
		if (StringUtils.isNotBlank(ids) && "0".equals(ids)) {
			Date selectTime = null;
			String carNo = params.get("car_no");
			try {
				selectTime = DateUtil.parse_YMDHMS(params.get("server_time"));
			} catch (Exception e) {
				return ResponseUtil.createResponse(ResultCode.ARREAR_FAILED);
			}
			ids = arrearService.selectIdListByCarNo(carNo, selectTime);
		}
		
		if(StringUtils.isBlank(ids)){
			return ResponseUtil.createResponse(ResultCode.ARREAR_IDS_ISBLANK_ERROR);
		}
		BigDecimal reallAmountx=null;
		BigDecimal amountx=null;
		try {
			reallAmountx=new BigDecimal(reallAmount);
			amountx=new BigDecimal(amount);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.ARREAR_AMOUNT_NO_MATCH_ERROR);
		}
		if(reallAmountx.intValue()<=0||amountx.intValue()<=0){
			return ResponseUtil.createResponse(ResultCode.ARREAR_AMOUNT_NO_MATCH_ERROR);
		}
		if(payType==null){
			return ResponseUtil.createResponse(ResultCode.OUTTYPE_ISBLANK_ERROR);
		}
		if (OrderPayType.KTC_OUT.getIndex() == payType.intValue()) {
			empNo = "999999";
		}else if (OrderPayType.ZFB_OUT.getIndex() == payType.intValue()) {
			empNo = "888888";
		}else if (OrderPayType.WX_OUT.getIndex() == payType.intValue()) {
			empNo = "777777";
		}
		if(Constants.Data_Send_repeat.equals(dataType)){//如果是重新发送s
			log.warn("重发 欠费补缴 ids ："+ids);
			List<Integer> idsList=ArrayUtil.stringToInteger(ArrayUtil.strToArray(ids));
			if(idsList==null||idsList.size()==0){
				return ResponseUtil.createResponse(ResultCode.ARREAR_IDS_NO_MATCH_ERROR);
			}
			List<Arrear> arrearList  = arrearService.getArrearListByIds(idsList);
			if(arrearList==null||arrearList.size()==0){
				return ResponseUtil.createResponse(ResultCode.ARREAR_NO_EXIST_ERROR);
			}
			boolean isKtcPay = false;
			for (int i = 0; i < arrearList.size(); i++) {
				Arrear arrear = arrearList.get(i);
				if(arrear.getStatus().intValue()==ArrearStatus.PAID.getIndex()){
					if (OrderPayType.KTC_OUT.getIndex() == arrear.getPayType().intValue()
						|| OrderPayType.ZFB_OUT.getIndex() == arrear.getPayType().intValue()
						|| OrderPayType.WX_OUT.getIndex() == arrear.getPayType().intValue()) {
						isKtcPay = true;
						continue;
					}else {
						return ResponseUtil.createResponse(ResultCode.ARREAR_HAS_COMPLETED_ERROR);
					}
				}else {
					if (isKtcPay) {
						return ResponseUtil.createResponse(ResultCode.ARREAR_HAS_COMPLETED_ERROR);
					}
				}
			}
			if (isKtcPay) {
				return ResponseUtil.createResponse(ResultCode.ARREAR_HAS_COMPLETED_KTC);
			}
		}
		try {
			return arrearService.payArrear(ids, reallAmountx, amountx, posNo, empNo, payType, reqSerial, invoiceBatch, invoiceNo,getTraceInfoVo(params),null);
		} catch (Exception e) {
			if(e instanceof ArrearRepeatPayException){
				return ResponseUtil.createResponse(ResultCode.ARREAR_HAS_COMPLETED_ERROR);
			}
			return ResponseUtil.createResponse(ResultCode.ARREAR_FAILED);
		}
	}
	
	
	private TraceInfoVo getTraceInfoVo(Map<String, String> params){
		return new TraceInfoVo(params.get("trace_no"), params.get("reference_no"), params.get("card_no"));
	}
	
}
