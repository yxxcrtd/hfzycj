package com.zycj.tcc.dao;

import java.math.BigDecimal;
import java.util.Date;

public interface InvoicePrintDao {

	/**
	 * 增加发票打印记录  车辆确认是驶出时
	 * @param invoiceBatch 发票批次
	 * @param invoiceNo 发票号
	 * @param invoiceAmount 发票金额
	 * @param sectionName 路段名称
	 * @param spaceNo 车位编号
	 * @param inTime 驶入时间
	 * @param outTime 驶出时间
	 * @param payTime 支付时间
	 * @param payType 支付类型
	 * @param chargeEmp 收款员工编号
	 * @param posNo 收款pos编号
	 * @param carNo 车牌号
	 * @param orderId 停车订单id
	 * @param reqSerial 交易流水号
	 * @return
	 */
	public int addInvoicePrintForCarExit(String invoiceBatch, String invoiceNo, BigDecimal invoiceAmount,
                                         String sectionName, String spaceNo, Date inTime, Date outTime, Date payTime, Integer payType,
                                         String chargeEmp, String posNo, String carNo, Integer orderId, String reqSerial);
}
