package com.zycj.tcc.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.PaymentFeeType;
import com.zycj.tcc.dao.BaseDao;
import com.zycj.tcc.dao.PaymentRecordDao;
import com.zycj.tcc.domain.PaymentRecord;
import com.zycj.tcc.mybatis.mapper.PaymentRecordMapper;
import com.zycj.tcc.util.RandomUtil;
import com.zycj.tcc.vo.CheckListInfo;
import com.zycj.tcc.vo.PaymentAmountGroupInfo;
import com.zycj.tcc.vo.TraceInfoVo;

@Repository
public class PaymentRecordDaoImpl extends BaseDao implements PaymentRecordDao {
	@Autowired
	private PaymentRecordMapper paymentRecordMapper;

	@Override
	public int addPaymentRecordForCarExit(Integer orderId,BigDecimal amount,BigDecimal reallAmount,Date payTime,
			Integer payType,String chargeEmp,String empName,String carNo,String chargePos,String spaceNo,
			String sectionName,String invoiceId,String invoice,String reqseriesNo,TraceInfoVo traceInfo) {
		PaymentRecord pr=new PaymentRecord();
		pr.setSerialNo(RandomUtil.getSrialNoByDate());
		pr.setIsCheck(Constants.CHECK_STATUS_NO);
		pr.setToPay(amount);//应缴
		pr.setRealPay(reallAmount);//实缴
		pr.setPayTime(payTime);//缴费时间
		pr.setPayType(payType);//支付方式,刷卡，现金
		pr.setChargeEmp(chargeEmp);//收费员工号
		pr.setEmpName(empName);//收费员名称
		pr.setCarNo(carNo);//车牌号
		pr.setPosNo(chargePos);//收费pos
		pr.setSpaceNo(spaceNo);//车位编号
		pr.setSectionName(sectionName);//路段名称
		pr.setFeeType(PaymentFeeType.OUT_PAYMENT.getIndex());//缴费类型 驶出缴费、补缴费、扎帐缴费
		pr.setInvoiceBatch(invoiceId);//发票批次号
		pr.setInvoiceNo(invoice);//发票号
		pr.setOrderId(orderId);//订单ID
		pr.setReqSerial(reqseriesNo);
		
		pr.setTraceNo(traceInfo.getTraceNo());
		pr.setReferenceNo(traceInfo.getReferenceNo());
		pr.setCardNo(traceInfo.getCardNo());
		
		return paymentRecordMapper.insertSelective(pr);
	}
	
	@Override
	public int addPaymentRecordForPay(Integer orderId, BigDecimal reallAmount, Date payTime, Integer payType) {
		PaymentRecord pr=new PaymentRecord();
		pr.setOrderId(orderId);
		pr.setRealPay(reallAmount);
		pr.setPayTime(new Date());
		pr.setPayType(payType);
		return paymentRecordMapper.insertSelective(pr);
	}
	
	@Override
	public CheckListInfo selectCheckInfoTodayByEmpNo(String empNo, Date nowTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("empNo", empNo);
		params.put("nowTime", nowTime);
		return (CheckListInfo) query("PaymentRecordDao.selectCheckInfoTodayByEmpNo", params);
	}
	
	@Override
	public Integer updateCheckStatusByDateEmpNo(String empNo,Integer isToday,Date checkTime,Date checkDate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("empNo", empNo);
		params.put("checkTime", checkTime);
		params.put("isToday", isToday);
		params.put("checkDate", checkDate);
		return update("PaymentRecordDao.updateCheckStatusByDateEmpNo", params);
	}
	
	@Override
	public Integer updateReCheckStatusByEmpNo(String empNo, java.sql.Date checkDateSql) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("empNo", empNo);
		params.put("checkDateSql", checkDateSql);
		return update("PaymentRecordDao.updateReCheckStatusByEmpNo", params);
	}
	
	@Override
	public Integer selectCheckPaymentRecordCountBycheckTime(String empNo,Date checkTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("empNo", empNo);
		params.put("checkTime", checkTime);
		return update("PaymentRecordDao.selectCheckPaymentRecordCountBycheckTime", params);
	}

	@Override
	public BigDecimal selectNoCheckAmountTotalByEmpNoForTime(String empNo,
			Date nowTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("empNo", empNo);
		params.put("nowTime", nowTime);
		return (BigDecimal) query("PaymentRecordDao.selectNoCheckAmountTotalByEmpNoForTime", params);
	}

	@Override
	public BigDecimal selectNoCheckAmountTotalByEmpNoForDate(String empNo) {
		return (BigDecimal) query("PaymentRecordDao.selectNoCheckAmountTotalByEmpNoForDate", empNo);
	}
	
	@Override
	public List<PaymentRecord> findPaymentRecordByOrderId(Integer orderId) {
		return queryList("PaymentRecordDao.findPaymentRecordByOrderId", orderId);
	}

	@Override
	public List<PaymentAmountGroupInfo> selectPaymentAmountByDateForGroupFeeType(
			String empNo, Date queryDate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("empNo", empNo);
		params.put("queryDate", queryDate);
		return queryList("PaymentRecordDao.selectPaymentAmountByDateForGroupFeeType", params);
	}

	@Override
	public List<PaymentAmountGroupInfo> selectPaymentAmountByDateForGroupPayType(
			String empNo, Date queryDate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("empNo", empNo);
		params.put("queryDate", queryDate);
		return queryList("PaymentRecordDao.selectPaymentAmountByDateForGroupPayType", params);
	}
	
	

}
