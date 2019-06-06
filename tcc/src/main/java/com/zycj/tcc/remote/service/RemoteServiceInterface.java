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
import com.zycj.tcc.common.SystemConfig;
import com.zycj.tcc.domain.Arrear;
import com.zycj.tcc.exception.ArrearRepeatPayException;
import com.zycj.tcc.server.annotation.ServiceClass;
import com.zycj.tcc.server.annotation.ServiceMethod;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Request;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.service.AppConfigService;
import com.zycj.tcc.service.AppSupportService;
import com.zycj.tcc.service.ArrearService;
import com.zycj.tcc.service.CheckService;
import com.zycj.tcc.service.EmployeeService;
import com.zycj.tcc.service.InvoicePrintService;
import com.zycj.tcc.service.InvoiceService;
import com.zycj.tcc.service.LoginService;
import com.zycj.tcc.service.ParkService;
import com.zycj.tcc.service.QueryService;
import com.zycj.tcc.service.RoadSpaceService;
import com.zycj.tcc.service.SystemConfigService;
import com.zycj.tcc.service.SystemService;
import com.zycj.tcc.service.TicketInfoService;
import com.zycj.tcc.service.impl.TestServiceImpl;
import com.zycj.tcc.util.ArrayUtil;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.util.PageUtil;
import com.zycj.tcc.vo.AppUpdateVo;
import com.zycj.tcc.vo.CheckListInfo;
import com.zycj.tcc.vo.TraceInfoVo;

/**
 * 远程服务接口 提供给外部使用
 * 
 * @author 洪捃能
 */
@ServiceClass
@Service
public class RemoteServiceInterface {
	private final static Logger log = Logger.getLogger(RemoteServiceInterface.class);

	@Autowired
	private TestServiceImpl testServiceImpl;
	@Autowired
	private LoginService loginService;
	@Autowired
	private RoadSpaceService roadSpaceService;
	@Autowired
	private SystemConfigService systemConfigService;
	@Autowired
	private ParkService parkService;
	@Autowired
	private ArrearService arrearService;
	@Autowired
	private CheckService checkService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AppConfigService appConfigService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private AppSupportService appSupportService;
	@Autowired
	private QueryService queryService;
	@Autowired
	private InvoicePrintService invoicePrintService;
	@Autowired
	private TicketInfoService ticketInfoService;
	@Autowired
	private InvoiceService invoiceService;

	@ServiceMethod(type = "0001", note = "签到")
	public Response login(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String pwd = params.get("pwd");
		String posNo = params.get("pos_no");
		String ip = MapUtils.getString(params, Request.CLIENT_IP, null);
		if (StringUtils.isBlank(empNo)) {
			return ResponseUtil.createResponse(ResultCode.EMPLOYEENO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(pwd)) {
			return ResponseUtil.createResponse(ResultCode.PWD_ISBLANK_ERROR);
		}
		return loginService.login(empNo, pwd, posNo, ip);
	}

	@ServiceMethod(type = "0016", note = "获取POS所有车位")
	public Response loadAllRoadSpaceByPos(Map<String, String> params) {
		String posNo = params.get("pos_no");
		int type = MapUtils.getIntValue(params, "type", -1);// 1：全部数据、2：空闲、3：已驶入
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		if (type == -1) {
			return ResponseUtil.createResponse(ResultCode.LOAD_ROADSPACE_TYPE_ISBLANK_ERROR);
		}
		return roadSpaceService.selectRoadSpaceByPosNo(type, posNo);
	}

	@ServiceMethod(type = "0002", note = "按分页获取POS车位")
	public Response loadRoadSpaceByPosForPage(Map<String, String> params) {
		String posNo = params.get("pos_no");
		int type = MapUtils.getIntValue(params, "type", -1);// 1：全部数据 2：空闲 3：已驶入
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		Integer currentPage = null;
		try {
			currentPage = Integer.parseInt(params.get("page_no"));
		} catch (Exception e) {
			currentPage = 1;
		}
		if (type == -1) {
			return ResponseUtil.createResponse(ResultCode.LOAD_ROADSPACE_TYPE_ISBLANK_ERROR);
		}
		return roadSpaceService.selectRoadSpaceByPosNoForPage(type, posNo,
				PageUtil.createPage(currentPage, SystemConfig.POS_Parking_PageSize));
	}

	@ServiceMethod(type = "0003", note = "车辆驶入接口")
	public Response carEntry(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		String carNo = params.get("car_no");
		String spaceNo = params.get("space_no");
		String carType = params.get("car_type");
		String reqseriesNo = params.get("reqseries_no");
		// String flag = params.get("newVersion");
		// if (flag == null) {
		// return ResponseUtil.createResponse(ResultCode.NO_LOGIN_ERROR);
		// }
		if (StringUtils.isBlank(carNo)) {
			return ResponseUtil.createResponse(ResultCode.CARNO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(spaceNo)) {
			return ResponseUtil.createResponse(ResultCode.SPACENO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(carType)) {
			return ResponseUtil.createResponse(ResultCode.CAR_TYPE_ISBLANK_ERROR);
		}
		if (carNo.indexOf("O") != -1 || carNo.indexOf("o") != -1 || carNo.indexOf("I") != -1
				|| carNo.indexOf("i") != -1) {
			return ResponseUtil.createResponse(ResultCode.CAR_TYPE_ISBLANK_ERROR);
		}
		Integer carTypex = null;
		try {
			carTypex = Integer.parseInt(carType);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.CAR_TYPE_FORMAT_ERROR);
		}
		return parkService.carEntry(empNo, posNo, spaceNo, carNo, carTypex, reqseriesNo);
	}

	@ServiceMethod(type = "0004", note = "车辆欠费查询接口")
	public Response selectAllArrearAmountByCarNo(Map<String, String> params) {
		String carNo = params.get("car_no");
		if (StringUtils.isBlank(carNo)) {
			return ResponseUtil.createResponse(ResultCode.CARNO_ISBLANK_ERROR);
		}
		return arrearService.selectAllArrearAmountByCarNo(carNo);
	}

//	@ServiceMethod(type = "1105", note = "测试")
//	public Response selectPark(Map<String, String> params) {
//		return ResponseUtil.createResponse(ResultCode.SPACENO_ISBLANK_ERROR);
//	}
	
	@ServiceMethod(type = "0005", note = "车辆驶出费用查询")
	public Response selectParkFeeForExit(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		String carNo = params.get("car_no");
		String spaceNo = params.get("space_no");
		String orderNo = params.get("order_no");
		if (StringUtils.isBlank(carNo)) {
			return ResponseUtil.createResponse(ResultCode.CARNO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(spaceNo)) {
			return ResponseUtil.createResponse(ResultCode.SPACENO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(orderNo)) {
			return ResponseUtil.createResponse(ResultCode.ORDERNO_ISBLANK_ERROR);
		}
		Integer orderId = null;
		try {
			orderId = Integer.parseInt(orderNo);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.ORDER_ID_FORMAT_ERROR);
		}
		return parkService.selectParkFeeForExit(orderId, empNo, posNo, carNo, spaceNo, null);
	}

	@ServiceMethod(type = "1006", note = "确认驶出")
	public Response carConfirmExit(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		String orderId = params.get("order_id");// 订单id
		String status = params.get("status");// 订单id
		int orderIdx = 0;
		// String reallAmount = params.get("reall_amount");// 实收金额
		// String amount = params.get("amount");// 应收金额
		// String outTime = params.get("out_time");// 驶出时间
		// String outType = params.get("out_type");// 驶出类型
		// int outTypex = 0;
		// String invoiceId = params.get("invoice_id");// 发票代号
		// String invoice = params.get("invoice");// 发票号码
		// String reqseriesNo = params.get("reqseries_no");
		//
		// String dataType = params.get("data_type");// 1 数据即时发送 2 数据重新发送

		// String carNo = params.get("car_no");
		// String isTuiKuan = params.get("isTuiKuan");

		if (StringUtils.isBlank(orderId)) {
			return ResponseUtil.createResponse(ResultCode.ORDERNO_ISBLANK_ERROR);
		}
		try {
			orderIdx = Integer.parseInt(orderId);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.ORDERID_IS_NOT_NUMBER_ERROR);
		}
		// if (StringUtils.isBlank(reallAmount)) {
		// return
		// ResponseUtil.createResponse(ResultCode.REALL_AMOUNT_ISBLANK_ERROR);
		// }
		// if (StringUtils.isBlank(amount)) {
		// return ResponseUtil.createResponse(ResultCode.AMOUNT_ISBLANK_ERROR);
		// }

		// if (StringUtils.isBlank(outTime)) {
		// return ResponseUtil.createResponse(ResultCode.OUTTIME_ISBLANK_ERROR);
		// }
		// if (StringUtils.isBlank(outType)) {
		// return ResponseUtil.createResponse(ResultCode.OUTTYPE_ISBLANK_ERROR);
		// }
		// try {
		// outTypex = Integer.parseInt(outType);
		// } catch (Exception e) {
		// return
		// ResponseUtil.createResponse(ResultCode.OUTTYPE_IS_NOT_NUMBER_ERROR);
		// }
		// if(Double.parseDouble(amount)>0 && outTypex ==
		// OrderPayType.FREE_OUT.getIndex()){
		// return ResponseUtil.createResponse(ResultCode.AMOUNT_ISBLANK_ERROR);
		// }
		// if (StringUtils.isBlank(invoiceId)) {
		// return
		// ResponseUtil.createResponse(ResultCode.INVOICEID_ISBLANK_ERROR);
		// }
		// if (StringUtils.isBlank(invoice)) {
		// return ResponseUtil.createResponse(ResultCode.INVOICE_ISBLANK_ERROR);
		// }
		// 校验时间格式
		// Date outTimex = null;
		// try {
		// outTimex = DateUtil.parse_YMDHMS(outTime);
		// } catch (ParseException e) {
		// log.warn("orderId=" + orderId + "确认驶出时间格式异常", e);
		// return
		// ResponseUtil.createResponse(ResultCode.CAR_EXIT_OUTTIME_FORMAT_ERROR);
		// }
		// if (Constants.Data_Send_repeat.equals(dataType)) {// 如果是重新发送
		// log.warn("重发 驶出 订单：" + orderIdx);
		// }
		return parkService.carConfirmExit(empNo, posNo, orderIdx, Integer.parseInt(status));
	}

	@ServiceMethod(type = "0006", note = "缴费完成，确认驶出")
	public Response carExit(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		String orderId = params.get("order_id");// 订单id
		int orderIdx = 0;
		String reallAmount = params.get("reall_amount");// 实收金额
		String amount = params.get("amount");// 应收金额
		String outTime = params.get("out_time");// 驶出时间
		String outType = params.get("out_type");// 驶出类型
		int outTypex = 0;
		String invoiceId = params.get("invoice_id");// 发票代号
		String invoice = params.get("invoice");// 发票号码
		String reqseriesNo = params.get("reqseries_no");

		String dataType = params.get("data_type");// 1 数据即时发送 2 数据重新发送

		String carNo = params.get("car_no");

		if (StringUtils.isBlank(orderId)) {
			return ResponseUtil.createResponse(ResultCode.ORDERNO_ISBLANK_ERROR);
		}
		try {
			orderIdx = Integer.parseInt(orderId);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.ORDERID_IS_NOT_NUMBER_ERROR);
		}
		if (StringUtils.isBlank(reallAmount)) {
			return ResponseUtil.createResponse(ResultCode.REALL_AMOUNT_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(amount)) {
			return ResponseUtil.createResponse(ResultCode.AMOUNT_ISBLANK_ERROR);
		}

		if (StringUtils.isBlank(outTime)) {
			return ResponseUtil.createResponse(ResultCode.OUTTIME_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(outType)) {
			return ResponseUtil.createResponse(ResultCode.OUTTYPE_ISBLANK_ERROR);
		}
		try {
			outTypex = Integer.parseInt(outType);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.OUTTYPE_IS_NOT_NUMBER_ERROR);
		}
		if (Double.parseDouble(amount) > 0 && outTypex == OrderPayType.FREE_OUT.getIndex()) {
			return ResponseUtil.createResponse(ResultCode.AMOUNT_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(invoiceId)) {
			return ResponseUtil.createResponse(ResultCode.INVOICEID_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(invoice)) {
			return ResponseUtil.createResponse(ResultCode.INVOICE_ISBLANK_ERROR);
		}
		// 校验时间格式
		Date outTimex = null;
		try {
			outTimex = DateUtil.parse_YMDHMS(outTime);
		} catch (ParseException e) {
			log.warn("orderId=" + orderId + "确认驶出时间格式异常", e);
			return ResponseUtil.createResponse(ResultCode.CAR_EXIT_OUTTIME_FORMAT_ERROR);
		}
		if (Constants.Data_Send_repeat.equals(dataType)) {// 如果是重新发送
			log.warn("重发 驶出 订单：" + orderIdx);
		}
		return parkService.carExit(empNo, posNo, orderIdx, reallAmount, amount, outTimex, outTypex, invoiceId, invoice,
				reqseriesNo, getTraceInfoVo(params), carNo);
	}

	@ServiceMethod(type = "0007", note = "车辆欠费列表查询分页")
	public Response getArrearListByCarNo(Map<String, String> params) {
		// String empNo = params.get("emp_no");
		// String posNo = params.get("pos_no");
		String carNo = params.get("car_no");
		int pageNo = MapUtils.getIntValue(params, "page_no", 1);
		if (StringUtils.isBlank(carNo)) {
			return ResponseUtil.createResponse(ResultCode.CARNO_ISBLANK_ERROR);
		}
		return arrearService.getArrearListByCarNo(carNo, pageNo);
	}

	@ServiceMethod(type = "0008", note = "车辆欠费补缴接口")
	public Response payArrear(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		String ids = params.get("id_list");
		String reallAmount = params.get("reall_amount");// 实缴
		String amount = params.get("amount");// 应缴
		Integer payType = MapUtils.getInteger(params, "out_type");
		String reqSerial = params.get("reqseries_no");
		String invoiceBatch = params.get("invoice_id");
		String invoiceNo = params.get("invoice"); 
		String cardNo = params.get("cardNo");

		String dataType = params.get("data_type");// 1 数据即时发送 2 数据重新发送

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

		if (StringUtils.isBlank(ids)) {
			return ResponseUtil.createResponse(ResultCode.ARREAR_IDS_ISBLANK_ERROR);
		}

		BigDecimal reallAmountx = null;
		BigDecimal amountx = null;
		try {
			reallAmountx = new BigDecimal(reallAmount);
			amountx = new BigDecimal(amount);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.ARREAR_AMOUNT_NO_MATCH_ERROR);
		}
		if (reallAmountx.intValue() <= 0 || amountx.intValue() <= 0) {
			return ResponseUtil.createResponse(ResultCode.ARREAR_AMOUNT_NO_MATCH_ERROR);
		}
		if (payType == null) {
			return ResponseUtil.createResponse(ResultCode.OUTTYPE_ISBLANK_ERROR);
		}
		if (Constants.Data_Send_repeat.equals(dataType)) {// 如果是重新发送s
			log.warn("重发 欠费补缴 ids ：" + ids);
			List<Integer> idsList = ArrayUtil.stringToInteger(ArrayUtil.strToArray(ids));
			if (idsList == null || idsList.size() == 0) {
				return ResponseUtil.createResponse(ResultCode.ARREAR_IDS_NO_MATCH_ERROR);
			}
			List<Arrear> arrearList = arrearService.getArrearListByIds(idsList);
			if (arrearList == null || arrearList.size() == 0) {
				return ResponseUtil.createResponse(ResultCode.ARREAR_NO_EXIST_ERROR);
			}
			for (int i = 0; i < arrearList.size(); i++) {
				Arrear arrear = arrearList.get(i);
				if (arrear.getStatus().intValue() == ArrearStatus.PAID.getIndex()) {
					return ResponseUtil.createResponse(ResultCode.ARREAR_HAS_COMPLETED_ERROR);
				}
			}
		}
		try {
			return arrearService.payArrear(ids, reallAmountx, amountx, posNo, empNo, payType, reqSerial, invoiceBatch,
					invoiceNo, getTraceInfoVo(params),cardNo);
		} catch (Exception e) {
			if (e instanceof ArrearRepeatPayException) {
				return ResponseUtil.createResponse(ResultCode.ARREAR_HAS_COMPLETED_ERROR);
			}
			return ResponseUtil.createResponse(ResultCode.ARREAR_FAILED);
		}
	}

	@ServiceMethod(type = "0009", note = "收费员扎帐查询接口")
	public Response getCheckListByEmpNo(Map<String, String> params) {
		String empNo = params.get("emp_no");
		if (StringUtils.isBlank(empNo)) {
			return ResponseUtil.createResponse(ResultCode.EMPLOYEENO_ISBLANK_ERROR);
		}
		List<CheckListInfo> vo = checkService.getCheckListByEmpNo(empNo);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, vo);
	}

	@ServiceMethod(type = "0010", note = "提交收费员扎帐信息接口")
	public Response confirmCheck(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		Integer isToday = MapUtils.getInteger(params, "is_today", null);// 是否今天（1表示今天、0表示欠）
		String checkIdstr = params.get("check_id");// 历史未扎帐id(is_today=0的时候才有值)
		Integer checkId = null;
		String invoiceBatch = params.get("invoice_id");
		String invoiceNo = params.get("invoice");
		String reqSerial = params.get("reqseries_no");

		String dataType = params.get("data_type");// 1 数据即时发送 2 数据重新发送

		Date checkDate = null;// 扎帐日期：年月日
		try {
			checkDate = DateUtil.parse_YMD(params.get("date"));
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.CHECK_DATE_FORMAT_ERROR);
		}
		BigDecimal amount = null;// 扎帐金额
		try {
			amount = new BigDecimal(params.get("amount"));
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.CHECK_AMOUNT_FORMAT_ERROR);
		}

		if (StringUtils.isBlank(empNo)) {
			return ResponseUtil.createResponse(ResultCode.EMPLOYEENO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		if (isToday == null) {
			return ResponseUtil.createResponse(ResultCode.CHECK_ISTODAY_ISBLANK_ERROR);
		}
		Date requestTime = null;
		if (isToday.intValue() == 1) {// 今天扎帐
			try {
				requestTime = DateUtil.parse_YMDHMS(params.get("request_time"));// 当天的查看扎帐金额的时间点
			} catch (Exception e) {
				return ResponseUtil.createResponse(ResultCode.CHECK_DATE_FORMAT_ERROR);
			}
		} else if (isToday.intValue() == 0) {// 历史补扎
			if (checkIdstr == null) {
				return ResponseUtil.createResponse(ResultCode.CHECK_ID_ISBLANK_ERROR);
			}
			try {
				checkId = Integer.parseInt(checkIdstr);
			} catch (Exception e) {
				return ResponseUtil.createResponse(ResultCode.CHECK_ID_FORMAT_ERROR);
			}
		} else {
			return ResponseUtil.createResponse(ResultCode.INTERFACE_PARAM_ERROR);
		}
		if (Constants.Data_Send_repeat.equals(dataType)) {// 如果是重新发送s
			log.warn("重发  扎帐：" + (isToday.intValue() == 1 ? "今天" : "历史  id:" + checkId));
			// 判断是否已经扎帐了
			if (isToday.intValue() == 1) {// 今天扎帐
				if (checkService.checkPaymentTodayByTime(empNo, requestTime)) {
					return ResponseUtil.createResponse(ResultCode.CHECK_REPEAT_TODAY_FAILED);
				}
			} else if (isToday.intValue() == 0) {// 历史补扎
				if (checkService.checkPaymentHistoryByDate(checkId)) {
					return ResponseUtil.createResponse(ResultCode.CHECK_REPEAT_HISTORY_FAILED);
				}
			}
		}
		return ResponseUtil.createResponse(checkService.confirmCheck(checkId, empNo, amount, isToday, checkDate,
				requestTime, invoiceBatch, invoiceNo, posNo, reqSerial, getTraceInfoVo(params)));
	}

	@ServiceMethod(type = "0011", note = "员工修改登录密码")
	public Response updatePassword(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		String oldPwd = params.get("old_pwd");
		String newPwd = params.get("new_pwd");
		if (StringUtils.isBlank(empNo)) {
			return ResponseUtil.createResponse(ResultCode.EMPLOYEENO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(oldPwd)) {
			return ResponseUtil.createResponse(ResultCode.PWD_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(newPwd)) {
			return ResponseUtil.createResponse(ResultCode.NEW_PWD_ISBLANK_ERROR);
		}
		return employeeService.updatePassword(empNo, oldPwd, newPwd);
	}

	@ServiceMethod(type = "0012", note = "签退查询")
	public Response gotoLogout(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		if (StringUtils.isBlank(empNo)) {
			return ResponseUtil.createResponse(ResultCode.EMPLOYEENO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		return loginService.gotoLogout(empNo, posNo);
	}

	@ServiceMethod(type = "0013", note = "查询当天的驶入驶出信息")
	public Response queryCarEntryExitByCond(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		String key = params.get("key");// 查询条件关键字
		String queryType = params.get("type");// 查询类型 1表示车牌号查询 2表示泊位号查询
		if (StringUtils.isBlank(empNo)) {
			return ResponseUtil.createResponse(ResultCode.EMPLOYEENO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(key)) {
			return ResponseUtil.createResponse(ResultCode.QUERY_CONDITION_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(queryType)) {
			return ResponseUtil.createResponse(ResultCode.QUERY_TYPE_ISBLANK_ERROR);
		}
		int type = -1;
		try {
			type = Integer.parseInt(queryType);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.QUERY_TYPE_NO_MATCH_ERROR);
		}
		if (type != 1 && type != 2) {
			return ResponseUtil.createResponse(ResultCode.QUERY_TYPE_NO_MATCH_ERROR);
		}
		return queryService.queryCarEntryExitByCond(type, key, empNo, posNo);
	}

	@ServiceMethod(type = "0019", note = "根据日期查询收费记录")
	public Response queryPaymentRecordByDay(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		String key = params.get("key");// 查询条件关键字
		if (StringUtils.isBlank(empNo)) {
			return ResponseUtil.createResponse(ResultCode.EMPLOYEENO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(key)) {
			return ResponseUtil.createResponse(ResultCode.QUERY_CONDITION_ISBLANK_ERROR);
		}

		return queryService.queryPaymentRecordByDay(key, empNo, posNo);
	}

	@ServiceMethod(type = "0017", note = "签退")
	public Response logout(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		String ip = MapUtils.getString(params, Request.CLIENT_IP, null);
		if (StringUtils.isBlank(empNo)) {
			return ResponseUtil.createResponse(ResultCode.EMPLOYEENO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		return loginService.logout(empNo, posNo, ip);
	}

	@ServiceMethod(type = "0014", note = "版本更新接口")
	public Response appUpdate(Map<String, String> params) {
		Integer versionCode = MapUtils.getInteger(params, "version_code");
		Integer appType = MapUtils.getInteger(params, "app_type");
		if (versionCode == null || appType == null) {
			return ResponseUtil.createResponse(ResultCode.INTERFACE_PARAM_ERROR);
		}
		AppUpdateVo vo = appConfigService.checkUpdate(versionCode, appType);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, vo);
	}

	@ServiceMethod(type = "0015", note = "错误日志上传接口")
	public Response appUploadError(Map<String, String> params) {
		if (params == null) {
			return ResponseUtil.createResponse(ResultCode.INTERFACE_PARAM_ERROR);
		}
		appSupportService.appErrorRecord(params);
		return ResponseUtil.createResponse(ResultCode.SUCCESS);
	}

	@ServiceMethod(type = "0018", note = "提交安装日志信息")
	public Response appInstallLog(Map<String, String> params) {
		if (params == null) {
			return ResponseUtil.createResponse(ResultCode.INTERFACE_PARAM_ERROR);
		}
		appSupportService.appInstall(params);
		return ResponseUtil.createResponse(ResultCode.SUCCESS);
	}

	@ServiceMethod(type = "0020", note = "校验IP地址、端口号、终端编号")
	public Response checkPosForBind(Map<String, String> params) {
		String posNo = params.get("pos_no");
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		return systemService.checkPosForBind(posNo);
	}

	@ServiceMethod(type = "0021", note = "重新打印发票日志信息上传接口")
	public Response invoiceRePrint(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		String typeStr = params.get("type");// 发票类型
											// 1:驶出类型、2：驶入类型、3：扎帐类型、4：补缴类型、5：免费类型
		Integer type = null;
		String relateId = params.get("relate_id");// 对应于type的相关id
		String checkTime = params.get("check_time");// 扎帐日期 只有type=3时才有值
		String priceStr = params.get("price");
		BigDecimal price = null;
		String printNumStr = params.get("print_num");// 打印次数
		Integer printNum = null;
		String invoiceBatch = params.get("invoice_id");// 发票批次号
		String invoiceNo = params.get("invoice");
		String reqSerial = params.get("reqseries_no");
		if (StringUtils.isBlank(empNo)) {
			return ResponseUtil.createResponse(ResultCode.ORDERNO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(typeStr)) {
			return ResponseUtil.createResponse(ResultCode.INVOICE_REPRINT_TYPE_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(priceStr)) {
			return ResponseUtil.createResponse(ResultCode.INVOICE_REPRINT_PRICE_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(invoiceBatch)) {
			return ResponseUtil.createResponse(ResultCode.INVOICE_REPRINT_INVOICEBATCH_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(invoiceNo)) {
			return ResponseUtil.createResponse(ResultCode.INVOICE_REPRINT_INVOICENO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(printNumStr)) {
			return ResponseUtil.createResponse(ResultCode.INVOICE_REPRINT_PRINTNUM_ISBLANK_ERROR);
		}
		try {
			type = Integer.parseInt(typeStr);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.INVOICE_REPRINT_TYPE_NO_MATCH_ERROR);
		}
		try {
			printNum = Integer.parseInt(printNumStr);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.INVOICE_REPRINT_PRINTNUM_FORMAT_ERROR);
		}
		try {
			price = new BigDecimal(Double.parseDouble(priceStr));
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.INVOICE_REPRINT_PRICE_NO_MATCH_ERROR);
		}
		return invoicePrintService.addRePrintRecord(empNo, posNo, invoiceBatch, invoiceNo, price, printNum, type,
				relateId, checkTime, reqSerial);
	}

	@ServiceMethod(type = "0022", note = "获取设置首页车位数据")
	public Response loadAllRoadSpaceForHomePageSet(Map<String, String> params) {
		String posNo = params.get("pos_no");
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		return roadSpaceService.loadAllRoadSpaceForHomePageSet(posNo);
	}

	@ServiceMethod(type = "0023", note = "设置首页车位显示")
	public Response setRoadSpaceForHomePageSet(Map<String, String> params) {
		String posNo = params.get("pos_no");
		String spaceNoList = params.get("space_no");
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(spaceNoList)) {
			return ResponseUtil.createResponse(ResultCode.SPACENO_LIST_ISBLANK_ERROR);
		}
		return roadSpaceService.setRoadSpaceForHomePageSet(posNo, spaceNoList);
	}

	@ServiceMethod(type = "0024", note = "督查机-根据泊车位查询获取所属路段所有泊车位")
	public Response loadAllRoadSpaceBySpaceNoForDCJ(Map<String, String> params) {
		String spaceNo = params.get("space_no");
		int type = MapUtils.getIntValue(params, "type", -1);// 1：全部数据、2：空闲、3：已驶入
		Integer roadSectionId = MapUtils.getIntValue(params, "road_id", -1);
		if (StringUtils.isBlank(spaceNo)) {
			return ResponseUtil.createResponse(ResultCode.SPACENO_ISBLANK_ERROR);
		}
		if (type == -1) {
			return ResponseUtil.createResponse(ResultCode.LOAD_ROADSPACE_TYPE_ISBLANK_ERROR);
		}
		if (roadSectionId.intValue() == -1 && "-1".equals(spaceNo)) {
			return ResponseUtil.createResponse(ResultCode.INTERFACE_PARAM_ERROR);
		}
		return roadSpaceService.loadAllRoadSpaceBySpaceNoForDCJ(type, spaceNo, roadSectionId);
	}

	@ServiceMethod(type = "0025", note = "督查机-根据泊车位查询获取所属路段泊车位  按分页")
	public Response loadRoadSpaceBySpaceNoPageForDCJ(Map<String, String> params) {
		String spaceNo = params.get("space_no");
		int type = MapUtils.getIntValue(params, "type", -1);// 1：全部数据、2：空闲、3：已驶入
		Integer roadSectionId = MapUtils.getIntValue(params, "road_id", -1);
		Integer currentPage = null;
		try {
			currentPage = Integer.parseInt(params.get("page_no"));
		} catch (Exception e) {
			currentPage = 1;
		}
		if (StringUtils.isBlank(spaceNo)) {
			return ResponseUtil.createResponse(ResultCode.SPACENO_ISBLANK_ERROR);
		}
		if (type == -1) {
			return ResponseUtil.createResponse(ResultCode.LOAD_ROADSPACE_TYPE_ISBLANK_ERROR);
		}
		if (roadSectionId.intValue() == -1 && "-1".equals(spaceNo)) {
			return ResponseUtil.createResponse(ResultCode.INTERFACE_PARAM_ERROR);
		}
		return roadSpaceService.loadRoadSpaceBySpaceNoPageForDCJ(type, spaceNo, roadSectionId,
				PageUtil.createPage(currentPage, SystemConfig.POS_Parking_PageSize));
	}

	@ServiceMethod(type = "0026", note = "月票查询")
	public Response selectTicketInfoByTicketNo(Map<String, String> params) {
		String ticketNo = params.get("key_code");
		int type = MapUtils.getIntValue(params, "type", 1);// 1 车牌号，2编号。
		if (StringUtils.isBlank(ticketNo)) {
			return ResponseUtil.createResponse(ResultCode.TICKETNO_ISBLANK_ERROR);
		}
		return ticketInfoService.selectTicketInfoByTicketNo(ticketNo, type);
	}

	@ServiceMethod(type = "0027", note = "发票设置接口")
	public Response checkInvoice(Map<String, String> params) {
		String invoiceId = params.get("invoice_id");// 发票代号(批次号)
		String invoiceStart = params.get("invoice_start");// 发票号码(开始号)
		String invoiceEnd = params.get("invoice_end");// 发票号码(结束号)
		if (StringUtils.isBlank(invoiceId)) {
			return ResponseUtil.createResponse(ResultCode.TICKETNO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(invoiceStart)) {
			return ResponseUtil.createResponse(ResultCode.TICKETNO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(invoiceEnd)) {
			return ResponseUtil.createResponse(ResultCode.TICKETNO_ISBLANK_ERROR);
		}
		String empName = MapUtils.getString(params, "emp_name", "");
		String empNo = MapUtils.getString(params, "emp_no", "");
		String posNo = MapUtils.getString(params, "pos_no", "");
		String reqSerial = MapUtils.getString(params, "reqseries_no", "");
		return invoiceService.checkInvoice(invoiceId, invoiceStart, invoiceEnd, empName, empNo, posNo, reqSerial);
	}

	@ServiceMethod(type = "0028", note = "根据订单号查询订单信息") // 条形码扫描
	public Response selectParkInfoByOrderId(Map<String, String> params) {
		String posNo = params.get("pos_no");
		String orderNo = params.get("order_no");
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(orderNo)) {
			return ResponseUtil.createResponse(ResultCode.ORDERNO_ISBLANK_ERROR);
		}
		Integer orderId = null;
		try {
			orderId = Integer.parseInt(orderNo);
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.ORDER_ID_FORMAT_ERROR);
		}
		return parkService.selectParkInfoByOrderId(orderId, posNo);
	}

	@ServiceMethod(type = "0029", note = "模糊搜索车牌号驶出")
	public Response serachByCaoNo(Map<String, String> params) {
		String posNo = params.get("pos_no");
		String carNo = params.get("car_no");
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(carNo)) {
			return ResponseUtil.createResponse(ResultCode.CARNO_ISBLANK_ERROR);
		}
		return roadSpaceService.selectParkedCarByCarNo(posNo, carNo);
	}

	@ServiceMethod(type = "0032", note = "网络补缴列表查询")
	public Response getArrearNetPayByCarNo(Map<String, String> params) {
		String carNo = params.get("car_no");
		int pageNo = MapUtils.getIntValue(params, "page_no", 1);
		if (StringUtils.isBlank(carNo)) {
			return ResponseUtil.createResponse(ResultCode.CARNO_ISBLANK_ERROR);
		}
		return arrearService.getArrearNetPayByCarNo(carNo, pageNo);
	}

	@ServiceMethod(type = "0033", note = "网络补缴发票打印")
	public Response printNetPay(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		String ids = params.get("id_list");
		String reqSerial = params.get("reqseries_no");
		String invoiceBatch = params.get("invoice_id");
		String invoiceNo = params.get("invoice");

		Date selectTime = null;
		String carNo = params.get("car_no");

		if (StringUtils.isNotBlank(ids) && "0".equals(ids)) {
			try {
				selectTime = DateUtil.parse_YMDHMS(params.get("server_time"));
			} catch (Exception e) {
				return ResponseUtil.createResponse(ResultCode.ARREAR_FAILED);
			}
		}

		if (StringUtils.isBlank(ids)) {
			return ResponseUtil.createResponse(ResultCode.ARREAR_IDS_ISBLANK_ERROR);
		}
		try {
			return arrearService.printNetPay(ids, posNo, empNo, reqSerial, invoiceBatch, invoiceNo, selectTime, carNo);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof ArrearRepeatPayException) {
				return ResponseUtil.createResponse(ResultCode.ARREAR_HAS_COMPLETED_ERROR);
			}
			return ResponseUtil.createResponse(ResultCode.ARREAR_FAILED);
		}
	}

	// @ServiceMethod(type="0030",note="查询订单状态")
	// public Response serachOrderStatus(Map<String, String> params){
	// String orderIdS=params.get("order_id");
	// Integer orderId = null;
	// try {
	// orderId=Integer.parseInt(orderIdS);
	// } catch (Exception e) {
	// return
	// ResponseUtil.createResponse(ResultCode.ORDERID_IS_NOT_NUMBER_ERROR);
	// }
	// try {
	// return orderInfoService.checkIsKtcOut(orderId);
	// } catch (Exception e) {
	// e.printStackTrace();
	// return ResponseUtil.createResponse(ResultCode.SYSTEM_ERROR);
	// }
	// }

	@ServiceMethod(type = "999999", note = "修改系统配置")
	public Response updateSystemConfig(Map<String, String> params) {
		String checkNo = params.get("checkNo");
		String fieldName = params.get("fieldName");
		String fieldVal = params.get("fieldVal");
		if (StringUtils.isBlank(checkNo) || StringUtils.isBlank(fieldName) || StringUtils.isBlank(fieldVal)) {
			return ResponseUtil.createResponse(ResultCode.INTERFACE_PARAM_ERROR);
		}
		return systemConfigService.updateSystemConfig(checkNo, fieldName, fieldVal);
	}

	@ServiceMethod(type = "updateCache_FeeRegular", note = "更新计费规则缓存")
	public Response updateFeeRegularCache(Map<String, String> params) {
		return systemConfigService.updateFeeRegularCache();
	}

	//////////////////////////////////////////////////////////////////////////
	// 以下为测试接口
	//////////////////////////////////////////////////////////////////////////
	@ServiceMethod(type = "test_DateInsert", note = "test_DateInsert")
	public Response test(Map<String, String> params) {
		return testServiceImpl.test_DateInsert();
	}

	@ServiceMethod(type = "test_getRoadSpaceByPos", note = "test_getRoadSpaceByPos")
	public Response test_getRoadSpaceByPos(Map<String, String> params) {
		String posNo = params.get("pos_no");
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, testServiceImpl.test_getRoadSpaceByPos(posNo));
	}

	@ServiceMethod(type = "test_exitOrderByEmpPos", note = "test_exitOrderByEmpPos")
	public Response test_getOrderByEmpPos(Map<String, String> params) {
		String empNo = params.get("emp_no");
		String posNo = params.get("pos_no");
		if (StringUtils.isBlank(empNo)) {
			return ResponseUtil.createResponse(ResultCode.EMPLOYEENO_ISBLANK_ERROR);
		}
		if (StringUtils.isBlank(posNo)) {
			return ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR);
		}
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, testServiceImpl.test_getOrderByEmpPos(empNo, posNo));
	}

	@ServiceMethod(type = "delete_handler", note = "delete_handler")
	public Response delete_handler(Map<String, String> params) {
		return ResponseUtil.createResponse(ResultCode.SUCCESS);
	}

	private TraceInfoVo getTraceInfoVo(Map<String, String> params) {
		return new TraceInfoVo(params.get("trace_no"), params.get("reference_no"), params.get("card_no"));
	}

}
