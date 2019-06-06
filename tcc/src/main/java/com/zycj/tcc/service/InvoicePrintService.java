package com.zycj.tcc.service;

import java.math.BigDecimal;

import com.zycj.tcc.server.vo.Response;

public interface InvoicePrintService {

	/**
	 * 发票重新打印日志
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @param invoiceBatch 发票批次号
	 * @param invoiceNo 发票编号
	 * @param price 发票金额
	 * @param printNum 发票打印次数
	 * @param type 发票类型 1:驶出类型、2：驶入类型、3：扎帐类型、4：补缴类型、5：免费类型
	 * @param relateId 相关id
	 * @param checkTime 扎帐日期
	 * @param reqSerial 序列号
	 * @return
	 */
	public Response addRePrintRecord(String empNo, String posNo, String invoiceBatch,
                                     String invoiceNo, BigDecimal price, Integer printNum, Integer type,
                                     String relateId, String checkTime, String reqSerial);

}
