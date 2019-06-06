package com.zycj.tcc.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.zycj.tcc.domain.OrderInfo;

public interface OrderInfoDao {

	/**
	 * 查询今天该车牌号已经驶入没有驶出的停车信息
	 * @param carNo 车牌号
	 * @return 停车订单信息
	 */
	public OrderInfo selectParkingOrderByCarNoForToday(String carNo);
	
	/**
	 * 查询今天所有未驶出的停车订单信息
	 * @return
	 */
	public List<OrderInfo> selectParkingOrderByToday();
	
	/**
	 * 车辆驶入时 增加停车订单信息
	 * @param sectionName 路段名
	 * @param spaceNo 车位编号
	 * @param carNo 车牌号
	 * @param carType 车类型
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @param parkTime 停车时间
	 * @param feeType 计费规则id
	 * @param arrearStatus 欠费类型
	 * @param reqseriesNo 流水号
	 */
	public OrderInfo addOrderInfo(String sectionName, String spaceNo, String carNo, Integer carType,
                                  String empNo, String posNo, Date parkTime, Integer feeType, Integer arrearStatus, String reqseriesNo, Integer sectionId);

	/**
	 * 根据车辆确认驶出更新停车订单信息
	 * @param orderId 订单id
	 * @param payType 支付类型
	 * @param outEmpNo 驶出员工编号
	 * @param outPosNo 驶出pos终端编号
	 * @param outTime 驶出时间
	 * @param chargeEmp 收费员工编号
	 * @param chargePos 收费pos终端编号
	 * @param chargeTime 收费时间
	 * @param amount 应收金额
	 * @param reallAmount 实收金额
	 * @param invoice 发票号码
	 * @param invoiceId 发票批次号 发票代号
	 * @return
	 */
	public int updateOrderForCarExit(Integer orderId, Integer payType, String outEmpNo, String outPosNo, Date outTime,
                                     String chargeEmp, String chargePos, Date chargeTime, BigDecimal amount, BigDecimal reallAmount, String invoice, String invoiceId);
	
	/**
	 * 车辆驶出，更改订单信息
	 * @param orderId
	 * @param outEmpNo
	 * @param outPosNo
	 * @param outTime
	 * @return
	 */
	public int updateOrderForCarExit(Integer orderId, String outEmpNo, String outPosNo, Date outTime, Integer status);
	
	/**
	 * @param orderId 订单号
	 * @param orderFlow 订单流程状态
	 * @return
	 */
	public int updateOrderForPay(Integer orderId, Integer orderFlow);

	/**
	 * 系统日终清算 更新停车订单信息
	 * @param orderId 订单id
	 * @param outTime 驶出时间
	 * @param amount 应收金额
	 * @return
	 */
	public int updateOrderForSystemClear(Integer orderId, Date outTime, BigDecimal amount);
	
	/**
	 * 根据车牌号查询最近一次交易
	 * @param carNo 车牌号
	 * @return
	 */
	public OrderInfo selectRecentlyOrderByCarNo(String carNo);
	
	/**
	 * 根据泊车号查询最近一次交易
	 * @param spaceNo 车位号
	 * @return
	 */
	public OrderInfo selectRecentlyOrderBySpaceNo(String spaceNo);
	
	/**
	 * 获取该车位的历史未驶出订单信息集合
	 * @param spaceNo 车位编号
	 * @return
	 */
	public List<OrderInfo> selectOrderHistoryBySpaceNo(String spaceNo);
	
	public OrderInfo selectLastOrderNoInvoiceByCarNo(String carNo);
}
