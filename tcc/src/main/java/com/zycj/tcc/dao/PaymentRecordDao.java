package com.zycj.tcc.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.zycj.tcc.domain.PaymentRecord;
import com.zycj.tcc.vo.CheckListInfo;
import com.zycj.tcc.vo.PaymentAmountGroupInfo;
import com.zycj.tcc.vo.TraceInfoVo;

public interface PaymentRecordDao {

	public CheckListInfo selectCheckInfoTodayByEmpNo(String empNo, Date nowTime);
	
	public Integer updateReCheckStatusByEmpNo(String empNo, java.sql.Date checkDateSql);

	public Integer updateCheckStatusByDateEmpNo(String empNo, Integer isToday, Date checkTime, Date checkDate);

	public Integer selectCheckPaymentRecordCountBycheckTime(String empNo, Date checkTime);

	/**
	 * 添加缴费记录 车辆驶出时
	 * 
	 * @param orderId
	 *            停车订单id
	 * @param amount
	 *            应缴
	 * @param reallAmount
	 *            实缴
	 * @param payTime
	 *            支付时间
	 * @param payType
	 *            支付方式,刷卡，现金，免费
	 * @param chargeEmp
	 *            收费员编号
	 * @param empName
	 *            收费员名称
	 * @param carNo
	 *            车牌号
	 * @param chargePos
	 *            收费pos编号
	 * @param spaceNo
	 *            车位编号
	 * @param sectionName
	 *            路段名称
	 * @param invoiceId
	 *            发票批次号
	 * @param invoice
	 *            发票代号
	 * @param reqseriesNo
	 *            交易流水号
	 * @return
	 */
	public int addPaymentRecordForCarExit(Integer orderId, BigDecimal amount, BigDecimal reallAmount, Date payTime,
                                          Integer payType, String chargeEmp, String empName, String carNo, String chargePos, String spaceNo,
                                          String sectionName, String invoiceId, String invoice, String reqseriesNo, TraceInfoVo traceInfo);

	/**
	 * @param orderId
	 * @param reallAmount
	 * @param payTime
	 * @param payType
	 * @return
	 */
	public int addPaymentRecordForPay(Integer orderId, BigDecimal reallAmount, Date payTime, Integer payType);

	/**
	 * 查询今天没有扎帐的缴费总额
	 * 
	 * @param empNo
	 *            员工编号
	 * @param nowTime
	 *            当时时间
	 * @return
	 */
	public BigDecimal selectNoCheckAmountTotalByEmpNoForTime(String empNo, Date nowTime);

	public BigDecimal selectNoCheckAmountTotalByEmpNoForDate(String empNo);

	/**
	 * 根据订单号，获取该订单下所有已缴费的历史记录
	 * 
	 * @param orderId
	 * @return
	 */
	public List<PaymentRecord> findPaymentRecordByOrderId(Integer orderId);

	/**
	 * 按缴费类型分类查询
	 * 
	 * @param empNo
	 *            员工编号
	 * @param queryDate
	 *            查询日期
	 * @return
	 */
	public List<PaymentAmountGroupInfo> selectPaymentAmountByDateForGroupFeeType(String empNo, Date queryDate);

	/**
	 * 按支付类型分类查询
	 * 
	 * @param empNo
	 *            员工编号
	 * @param queryDate
	 *            查询日期
	 * @return
	 */
	public List<PaymentAmountGroupInfo> selectPaymentAmountByDateForGroupPayType(String empNo, Date queryDate);
}
