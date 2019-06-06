package com.zycj.tcc.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.common.OrderPayType;
import com.zycj.tcc.common.PaymentFeeType;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.domain.Arrear;
import com.zycj.tcc.domain.CheckInfo;
import com.zycj.tcc.domain.InvoicePrint;
import com.zycj.tcc.domain.OrderInfo;
import com.zycj.tcc.mybatis.mapper.ArrearMapper;
import com.zycj.tcc.mybatis.mapper.CheckInfoMapper;
import com.zycj.tcc.mybatis.mapper.InvoicePrintMapper;
import com.zycj.tcc.mybatis.mapper.OrderInfoMapper;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.service.InvoicePrintService;
import com.zycj.tcc.util.ArrayUtil;
import com.zycj.tcc.util.DateUtil;

@Service
public class InvoicePrintServiceImpl implements InvoicePrintService {
	private final static Logger log = Logger.getLogger(InvoicePrintServiceImpl.class); 
	
	@Autowired
	private InvoicePrintMapper invoicePrintMapper;
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Autowired
	private CheckInfoMapper checkInfoMapper;
	@Autowired
	private ArrearMapper arrearMapper;

	@Override
	public Response addRePrintRecord(String empNo, String posNo,
			String invoiceBatch, String invoiceNo, BigDecimal price,
			Integer printNum, Integer type, String relateId, String checkTime,
			String reqSerial) {
		try {
			//发票类型 1:驶出类型、2：驶入类型、3：扎帐类型、4：补缴类型、5：免费类型
			if(type.intValue()!=1&&type.intValue()!=2&&type.intValue()!=3&&
					type.intValue()!=4&&type.intValue()!=5){
				return ResponseUtil.createResponse(ResultCode.INVOICE_REPRINT_TYPE_NO_MATCH_ERROR);
			}
			switch (type.intValue()) {
				case 1://驶出类型
					if(StringUtils.isBlank(relateId)){
						return ResponseUtil.createResponse(ResultCode.ORDERNO_ISBLANK_ERROR);
					}
					OrderInfo oi=orderInfoMapper.selectByPrimaryKey(Integer.parseInt(relateId));
					if(oi==null){
						return ResponseUtil.createResponse(ResultCode.ORDER_NO_EXIST_ERROR);
					}
					InvoicePrint ip1=new InvoicePrint();
					ip1.setInvoiceBatch(invoiceBatch);
					ip1.setInvoiceNo(invoiceNo);
					ip1.setInvoiceAmount(price);
					ip1.setSectionName(oi.getSectionName());
					ip1.setSpaceNo(oi.getSpaceNo());
					ip1.setInTime(oi.getInTime());
					ip1.setOutTime(oi.getOutTime());
//					ip1.setPayTime(new Date());
					ip1.setPayType(oi.getPayType());
					ip1.setChargeEmp(empNo);
					ip1.setPosNo(posNo);
					ip1.setCarNo(oi.getCarNo());
					ip1.setFeeType(PaymentFeeType.OUT_PAYMENT.getIndex());
					ip1.setOrderId(oi.getId());//orderid
					ip1.setPrintTimes(printNum);
					ip1.setReqSerial(reqSerial);
					
					
					invoicePrintMapper.insertSelective(ip1);
					
					OrderInfo order = new OrderInfo();
					order.setInvoiceBatch(invoiceBatch);
					order.setInvoiceNo(invoiceNo);
					order.setId(oi.getId());
					orderInfoMapper.updateByPrimaryKeySelective(order);
					
					break;
				case 2://驶入类型
					break;
				case 3://扎帐类型
					if(StringUtils.isBlank(checkTime)){
						return ResponseUtil.createResponse(ResultCode.CHECK_DATE_ISBLANK_ERROR);
					}
					boolean isToday=false;
					Date checkDate=null;
					try {
						checkDate=DateUtil.parse_YMD(checkTime);
						isToday=DateUtil.isToday(checkDate);
					} catch (Exception e) {
						return ResponseUtil.createResponse(ResultCode.CHECK_DATE_FORMAT_ERROR);
					}
					InvoicePrint ip2 = new InvoicePrint();
					if(!isToday){//如果不是当天，记录扎帐id
						if(StringUtils.isBlank(relateId)){
							return ResponseUtil.createResponse(ResultCode.CHECK_ID_ISBLANK_ERROR);
						}
						CheckInfo ci=checkInfoMapper.selectByPrimaryKey(Integer.parseInt(relateId));
						if(ci==null){
							return ResponseUtil.createResponse(ResultCode.CHECK_NO_EXIST_ERROR);
						}
						ip2.setCheckId(ci.getId());
					}
					ip2.setInvoiceBatch(invoiceBatch);
					ip2.setInvoiceNo(invoiceNo);
					ip2.setInvoiceAmount(price);
//					ip2.setPayTime(new Date());
					ip2.setPayType(OrderPayType.CARD_OUT.getIndex());
					ip2.setChargeEmp(empNo);
					ip2.setPosNo(posNo);
					ip2.setFeeType(PaymentFeeType.ACCOUNT_PAYMENT.getIndex());
					ip2.setReqSerial(reqSerial);
					//扎帐日期
					ip2.setCheckDate(checkDate);
					ip2.setPrintTimes(printNum);
					invoicePrintMapper.insertSelective(ip2);
					break;
				case 4://补缴类型
					//多笔欠费合并补缴，相关id传的是什么？
					List<Integer> idsList=ArrayUtil.stringToInteger(ArrayUtil.strToArray(relateId));
					for (Integer id:idsList) {
						Arrear arrear=arrearMapper.selectByPrimaryKey(id);
						if(arrear==null){
							return ResponseUtil.createResponse(ResultCode.ARREAR_NO_EXIST_ERROR);
						}
						InvoicePrint ip3 = new InvoicePrint();
						ip3.setInvoiceBatch(invoiceBatch);
						ip3.setInvoiceNo(invoiceNo);
						ip3.setInvoiceAmount(arrear.getArrearAmount());//price
//						ip3.setPayTime(new Date());
//						ip3.setPayType(????);//
						ip3.setChargeEmp(empNo);
						ip3.setPosNo(posNo);
						ip3.setFeeType(PaymentFeeType.MAKE_PAYMENT.getIndex());
						ip3.setReqSerial(reqSerial);
						
						ip3.setArrearId(arrear.getId());//欠费id
						ip3.setOrderId(arrear.getOrderId());//订单id
						ip3.setCarNo(arrear.getCarNo());
						ip3.setSectionName(arrear.getSectionName());
						ip3.setSpaceNo(arrear.getSpaceNo());
						ip3.setInTime(arrear.getInTime());
						ip3.setOutTime(arrear.getOutTime());
						
						ip3.setPrintTimes(printNum);
						invoicePrintMapper.insertSelective(ip3);
					}
					break;
				case 5://免费类型
					break;
			}
		} catch (Exception e) {
			log.warn("发票重新打印异常", e);
			return ResponseUtil.createResponse(ResultCode.INVOICE_REPRINT_FAILED);
		}
		return ResponseUtil.createResponse(ResultCode.SUCCESS);
	}
	
}
