package com.zycj.tcc.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.dao.InvoiceDao;
import com.zycj.tcc.domain.InvoiceSetting;
import com.zycj.tcc.domain.InvoiceSettingExample;
import com.zycj.tcc.mybatis.mapper.InvoiceMapper;
import com.zycj.tcc.mybatis.mapper.InvoiceSettingMapper;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.service.InvoiceService;

/**
 * 发票服务类
 * @author 洪捃能
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
	private final static Logger log = Logger.getLogger(InvoiceServiceImpl.class); 
	
	@Autowired
	private InvoiceMapper invoiceMapper;
	@Autowired
	private InvoiceDao invoiceDao;
	@Autowired
	private InvoiceSettingMapper invoiceSettingMapper;
	
	@Override
	public Response checkInvoice(String invoiceId, String invoiceStart,String invoiceEnd,String empName,String empNo,String posNo,String reqSerial) {
		try {
			int is=Integer.parseInt(invoiceStart);
			int ie=Integer.parseInt(invoiceEnd);
			
			if (checkSetting(invoiceId, invoiceStart, invoiceEnd, empNo, posNo, reqSerial)) {
				return ResponseUtil.createResponse(ResultCode.INVOICE_CHECK_DUPLICATE);
			}
			
			int count=invoiceDao.selectCountByInvoiceRelevantNum(invoiceId,is,ie);
			if(count>0){
				insertSetting(invoiceId, invoiceStart, invoiceEnd, empName, empNo, posNo, reqSerial);
				return ResponseUtil.createResponse(ResultCode.SUCCESS);
			}else{
				int batchCount = invoiceDao.selectCountByInvoiceId(invoiceId);
				if (batchCount>0) {
					return ResponseUtil.createResponse(ResultCode.INVOICE_CHECK_FAILED);
				}else {
					return ResponseUtil.createResponse(ResultCode.INVOICE_BATCH_CHECK_FAILED);
				}
			}
		} catch (Exception e) {
			log.warn("发票校验异常", e);
			return ResponseUtil.createResponse(ResultCode.INVOICE_CHECK_ERROR);
		}
		
	}
	
	public Integer insertSetting(String invoiceId, String invoiceStart,String invoiceEnd,String empName,String empNo,String posNo,String reqSerial) {
		InvoiceSetting setting = new InvoiceSetting();
		setting.setEmpName(empName);
		setting.setEmpNo(empNo);
		setting.setInvoiceBatch(invoiceId);
		setting.setInvoiceEnd(invoiceEnd);
		setting.setInvoiceStart(invoiceStart);
		setting.setPosNo(posNo);
		setting.setReqSerial(reqSerial);
		setting.setSetTime(new Date());
		return invoiceSettingMapper.insertSelective(setting);
	}
	
	
	public boolean checkSetting(String invoiceId, String invoiceStart,String invoiceEnd,String empNo,String posNo,String reqSerial) {
		InvoiceSettingExample example = new InvoiceSettingExample();
		example.or().andInvoiceBatchEqualTo(invoiceId).andInvoiceStartEqualTo(invoiceStart).andInvoiceEndEqualTo(invoiceEnd)
		.andEmpNoEqualTo(empNo).andPosNoEqualTo(posNo);
		int count = invoiceSettingMapper.countByExample(example);
		return count > 0;
	}
	
	

}
