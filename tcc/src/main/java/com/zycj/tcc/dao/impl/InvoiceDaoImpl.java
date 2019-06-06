package com.zycj.tcc.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zycj.tcc.dao.BaseDao;
import com.zycj.tcc.dao.InvoiceDao;

@Repository
public class InvoiceDaoImpl extends BaseDao implements InvoiceDao {

	@Override
	public int selectCountByInvoiceRelevantNum(String invoiceId,int invoiceStart, int invoiceEnd) {
		Map<String,Object> val=new HashMap<String,Object>();
		val.put("invoiceId", invoiceId);
		val.put("invoiceStart", invoiceStart);
		val.put("invoiceEnd", invoiceEnd);
		return (Integer) query("InvoiceDao.selectCountByInvoiceRelevantNum", val);
	}
	
	
	@Override
	public int selectCountByInvoiceId(String invoiceId) {
		Map<String,Object> val=new HashMap<String,Object>();
		val.put("invoiceId", invoiceId);
		return (Integer) query("InvoiceDao.selectCountByInvoiceId", val);
	}

	
}
