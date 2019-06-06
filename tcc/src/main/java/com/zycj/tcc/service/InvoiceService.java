package com.zycj.tcc.service;

import com.zycj.tcc.server.vo.Response;

public interface InvoiceService {

	/**
	 * 校验发票
	 * @param invoiceId 发票批次号
	 * @param invoiceStart 发票起始号
	 * @param invoiceEnd 发票终止号
	 * @return
	 */
	public Response checkInvoice(String invoiceId, String invoiceStart,
                                 String invoiceEnd, String empName, String empNo, String posNo, String reqSerial);

}
