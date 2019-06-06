package com.zycj.tcc.dao.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zycj.tcc.common.PaymentFeeType;
import com.zycj.tcc.dao.InvoicePrintDao;
import com.zycj.tcc.domain.InvoicePrint;
import com.zycj.tcc.mybatis.mapper.InvoicePrintMapper;

@Repository
public class InvoicePrintDaoImpl implements InvoicePrintDao {

	@Autowired
	private InvoicePrintMapper invoicePrintMapper;
	@Override
	public int addInvoicePrintForCarExit(String invoiceBatch, String invoiceNo,
			BigDecimal invoiceAmount,String sectionName, String spaceNo, Date inTime, 
			Date outTime,Date payTime, Integer payType, String chargeEmp, String posNo,
			String carNo, Integer orderId, String reqSerial) {
		
		InvoicePrint ip=new InvoicePrint();
		ip.setInvoiceBatch(invoiceBatch);
		ip.setInvoiceNo(invoiceNo);
		ip.setInvoiceAmount(invoiceAmount);
		ip.setSectionName(sectionName);
		ip.setSpaceNo(spaceNo);
		ip.setInTime(inTime);
		ip.setOutTime(outTime);
		ip.setPayTime(payTime);
		ip.setPayType(payType);
		ip.setChargeEmp(chargeEmp);
		ip.setPosNo(posNo);
		ip.setCarNo(carNo);
		ip.setFeeType(PaymentFeeType.OUT_PAYMENT.getIndex());
		ip.setOrderId(orderId);
		ip.setPrintTimes(1);
		ip.setReqSerial(reqSerial);
		return invoicePrintMapper.insertSelective(ip);
	}

}
