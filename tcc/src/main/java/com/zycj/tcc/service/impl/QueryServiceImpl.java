package com.zycj.tcc.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.common.OrderPayType;
import com.zycj.tcc.common.OrderStatus;
import com.zycj.tcc.common.PaymentFeeType;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.dao.ArrearDao;
import com.zycj.tcc.dao.OrderInfoDao;
import com.zycj.tcc.dao.PaymentRecordDao;
import com.zycj.tcc.domain.OrderInfo;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.service.QueryService;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.util.DecimalFormatUtil;
import com.zycj.tcc.vo.PaymentAmountGroupInfo;
import com.zycj.tcc.vo.QueryOrderVo;
import com.zycj.tcc.vo.QueryPaymentRecordVo;

@Service
public class QueryServiceImpl implements QueryService {
	private final static Logger log = Logger.getLogger(QueryServiceImpl.class); 
	
	@Autowired
	private PaymentRecordDao paymentRecordDao;
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private ArrearDao arrearDao;
	
	@Override
	public Response queryCarEntryExitByCond(int type, String cond,String empNo, String posNo) {
		try {
			OrderInfo order=null;
			switch (type) {
				case 1://车牌号
					order=orderInfoDao.selectRecentlyOrderByCarNo(cond);
					break;
				case 2://泊车号
					order=orderInfoDao.selectRecentlyOrderBySpaceNo(cond);
					break;
				default:
					return ResponseUtil.createResponse(ResultCode.QUERY_TYPE_NO_MATCH_ERROR);
			}
			if(order==null){
				return ResponseUtil.createResponse(ResultCode.NO_RECENTLY_ORDER);
			}
			QueryOrderVo qov=new QueryOrderVo();
			qov.setOrderId(order.getId());
			qov.setParkName(order.getSectionName());
			qov.setCarNo(order.getCarNo());
			qov.setSpaceNo(order.getSpaceNo());
			qov.setEmpNo(order.getInEmp());
			qov.setPosNo(order.getInPos());
			qov.setInTime(DateUtil.format_YMDHMS(order.getInTime()));
			qov.setCarType(order.getCarType()+"");
			//驶入 1，驶出0
			if(OrderStatus.IN.getIndex()==order.getOrderStatus().intValue()){
				qov.setIsIn(1);
				qov.setArrearage(DecimalFormatUtil.format_00(arrearDao.selectAllArrearAmountByCarNo(order.getCarNo())));//欠费金额
			}else{
				qov.setIsIn(0);
				qov.setOutTime(DateUtil.format_YMDHMS(order.getOutTime()));
				qov.setAccount(DecimalFormatUtil.format_00(order.getFeeTotal()));//停车费
				qov.setOutType(OrderPayType.getName(order.getPayType().intValue()));//驶出方式
				qov.setEmpNoOut(order.getOutEmp());
				qov.setPosNoOut(order.getOutPos());
			}
			return ResponseUtil.createDataResponse(ResultCode.SUCCESS, qov);
		} catch (Exception e) {
			log.warn("数据查询 驶入驶出最近一笔交易 异常",e);
			return ResponseUtil.createResponse(ResultCode.QUERY_TYPE_FAILED);
		}
	}

	@Override
	public Response queryPaymentRecordByDay(String cond, String empNo, String posNo) {
		//校验日期格式
		Date queryDate=null;
		try {
			if(!DateUtil.matches(cond)){
				return ResponseUtil.createResponse(ResultCode.QUERY_DATE_NO_MATCH_ERROR);
			}
			queryDate=DateUtil.parseByFormat(cond,"yyyyMMdd");
		} catch (Exception e) {
			return ResponseUtil.createResponse(ResultCode.QUERY_DATE_NO_MATCH_ERROR);
		}
		//待优化，合并两种查询
		List<PaymentAmountGroupInfo> feeList=paymentRecordDao.selectPaymentAmountByDateForGroupFeeType(empNo, queryDate);
		List<PaymentAmountGroupInfo> payList=paymentRecordDao.selectPaymentAmountByDateForGroupPayType(empNo, queryDate);
		QueryPaymentRecordVo prv=new QueryPaymentRecordVo();
		double toalAmount=0;
		//支付类型分组
		for(PaymentAmountGroupInfo pa:payList){
			toalAmount+=pa.getAmount().doubleValue();
			if(pa.getType().intValue()==OrderPayType.CASH_OUT.getIndex()){
				prv.setCashAccount(DecimalFormatUtil.format_00(pa.getAmount()));
			}else if(pa.getType().intValue()==OrderPayType.CARD_OUT.getIndex()){
				prv.setCardAccount(DecimalFormatUtil.format_00(pa.getAmount()));
			}else if(pa.getType().intValue()==OrderPayType.KTC_OUT.getIndex()){
				prv.setKtcAccount(DecimalFormatUtil.format_00(pa.getAmount()));
			}
		}
		//缴费类型分组
		for(PaymentAmountGroupInfo pa:feeList){
			if(pa.getType().intValue()==PaymentFeeType.OUT_PAYMENT.getIndex()){
				prv.setNormalAccount(DecimalFormatUtil.format_00(pa.getAmount()));
			}else if(pa.getType().intValue()==PaymentFeeType.MAKE_PAYMENT.getIndex()){
				prv.setArrearageAccount(DecimalFormatUtil.format_00(pa.getAmount()));
			}
		}
		prv.setEmpNo(empNo);
		prv.setDate(cond);
		prv.setTotalAccount(DecimalFormatUtil.format_00(toalAmount));
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, prv);
	}

	@Override
	public Response queryInvoiceInfo2PrintByCarNo(String carNo) {
		QueryOrderVo qov = new QueryOrderVo();
		try {
			OrderInfo order = orderInfoDao.selectLastOrderNoInvoiceByCarNo(carNo);
			if (order != null) {
				qov.setOrderId(order.getId());
				qov.setParkName(order.getSectionName());
				qov.setCarNo(order.getCarNo());
				qov.setSpaceNo(order.getSpaceNo());
				qov.setEmpNo(order.getInEmp());
				qov.setPosNo(order.getInPos());
				qov.setInTime(DateUtil.format_YMDHMS(order.getInTime()));
				qov.setIsIn(0);
				qov.setOutTime(DateUtil.format_YMDHMS(order.getOutTime()));
				qov.setAccount(DecimalFormatUtil.format_00(order.getFeeTotal()));//停车费
				qov.setOutType(String.valueOf(order.getPayType().intValue()));//驶出方式
				qov.setEmpNoOut(order.getOutEmp());
				qov.setPosNoOut(order.getOutPos());
				qov.setCarType(String.valueOf(order.getCarType()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, qov);
	}
	
	
}
