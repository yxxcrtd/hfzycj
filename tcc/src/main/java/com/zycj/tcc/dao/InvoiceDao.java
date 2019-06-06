package com.zycj.tcc.dao;

/**
 * @author Think
 *
 */
public interface InvoiceDao {

	/**
	 * 获取符合发票号码的个数
	 * @param invoiceId 发票批次号
	 * @param invoiceStart 发票起始号
	 * @param invoiceEnd 发票终止号
	 * @return
	 */
	public int selectCountByInvoiceRelevantNum(String invoiceId, int invoiceStart, int invoiceEnd);

	
	/**
	 * 查询发票批次号个数
	 * @param invoiceId 发票批次号
	 * @return
	 */
	public int selectCountByInvoiceId(String invoiceId);
}
