package com.zycj.tcc.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.OrderPayType;
import com.zycj.tcc.common.PaymentFeeType;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.dao.EmployeeDao;
import com.zycj.tcc.dao.PaymentRecordDao;
import com.zycj.tcc.domain.CheckInfo;
import com.zycj.tcc.domain.CheckInfoExample;
import com.zycj.tcc.domain.CheckRecord;
import com.zycj.tcc.domain.InvoicePrint;
import com.zycj.tcc.domain.PaymentRecord;
import com.zycj.tcc.mybatis.mapper.CheckInfoMapper;
import com.zycj.tcc.mybatis.mapper.CheckRecordMapper;
import com.zycj.tcc.mybatis.mapper.InvoicePrintMapper;
import com.zycj.tcc.service.CheckService;
import com.zycj.tcc.service.PaymentService;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.util.DecimalFormatUtil;
import com.zycj.tcc.vo.CheckListInfo;
import com.zycj.tcc.vo.TraceInfoVo;

@Service
public class CheckServiceImpl implements CheckService {
	private final static Logger log = Logger.getLogger(CheckServiceImpl.class); 
	@Autowired
	private CheckInfoMapper checkInfoMapper;
	@Autowired
	private PaymentRecordDao paymentRecordDao;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private InvoicePrintMapper invoicePrintMapper;
	@Autowired
	private CheckRecordMapper checkRecordMapper;
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public List<CheckListInfo> getCheckListByEmpNo(String empNo) {
		List<CheckListInfo> voList = new ArrayList<CheckListInfo>();
		//before
		List<CheckInfo> infoList = getCheckInfoListByEmpNo(empNo);
		for (CheckInfo checkInfo : infoList) {
			CheckListInfo vo = new CheckListInfo();
			vo.setAmount(DecimalFormatUtil.format_00(checkInfo.getAmount()));
			try {
				vo.setDate(DateUtil.format_YMD(checkInfo.getCheckDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			vo.setIsToday("0");
			vo.setRequstTime(null);
			vo.setId(String.valueOf(checkInfo.getId()));
			voList.add(vo);
		}
		//today
		CheckListInfo todayInfo = getCheckListInfoTodayByEmpNo(empNo, new Date());
		if (todayInfo != null) {
			voList.add(todayInfo);
		}
		return voList;
	}
	//获取历史未扎帐信息
	public List<CheckInfo> getCheckInfoListByEmpNo(String empNo) {
		CheckInfoExample example = new CheckInfoExample();
		example.or().andEmpNoEqualTo(empNo).andCheckStatusEqualTo(0);
		example.setOrderByClause(" check_date DESC");
		return checkInfoMapper.selectByExample(example);
	}
	//获取今天到这个时间点为止的缴费未扎帐金额
	public CheckListInfo getCheckListInfoTodayByEmpNo(String empNo,Date nowTime) {
		CheckListInfo info = paymentRecordDao.selectCheckInfoTodayByEmpNo(empNo, nowTime);
		if (info==null || info.getAmount()==null) {
			return null;
		}
		info.setIsToday("1");
		try {
			info.setRequstTime(DateUtil.format_YMDHMS(nowTime));
			info.setDate(DateUtil.format_YMD(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	@Transactional
	@Override
	public String confirmCheck(Integer checkId,String empNo,BigDecimal amount,int isToday,Date checkDate,Date requestTime,String invoiceBatch,String invoiceNo,String posNo,String reqSerial,TraceInfoVo traceInfo) {
		synchronized (this) {
			Date payTime = new Date();
			String empName=employeeDao.selectEmpNameByEmpNo(empNo);//收费员名称
			if(isToday==1){//今天扎帐 查询同一requesttime 是否有影响行数。
				int i = paymentRecordDao.updateCheckStatusByDateEmpNo(empNo, isToday, requestTime,checkDate);
				if(i==0){
					return ResultCode.CHECK_REPEAT_TODAY_FAILED;
				}
			}else if(isToday==0){//历史补扎
				CheckInfo oldci=checkInfoMapper.selectByPrimaryKey(checkId);
				if(oldci==null){
					return ResultCode.ARREAR_NO_EXIST_ERROR;
				}
				CheckInfo checkInfo = new CheckInfo();
				checkInfo.setId(checkId);
				checkInfo.setCheckStatus(Constants.CHECK_STATUS_YES);
				checkInfo.setCheckTime(new Date());
				checkInfo.setRealAmount(amount);
				checkInfo.setUpdateTime(new Date());
				checkInfoMapper.updateByPrimaryKeySelective(checkInfo);
				int i= paymentRecordDao.updateCheckStatusByDateEmpNo(empNo, isToday, requestTime,checkDate);
				if(i==0){
					return ResultCode.CHECK_REPEAT_HISTORY_FAILED;
				}else{
					//历史扎帐成功，修改manager_report_day 2016年2月2日10:12:02
					java.sql.Date checkDateSql = new java.sql.Date(checkDate.getTime());
					int num = paymentRecordDao.updateReCheckStatusByEmpNo(empNo, checkDateSql);
				}
			}
			// insert payment
			PaymentRecord paymentRecord = new PaymentRecord();
			paymentRecord.setIsCheck(Constants.CHECK_STATUS_NO);
			paymentRecord.setChargeEmp(empNo);
			paymentRecord.setEmpName(empName);
			paymentRecord.setFeeType(PaymentFeeType.ACCOUNT_PAYMENT.getIndex());
			paymentRecord.setInvoiceBatch(invoiceBatch);
			paymentRecord.setInvoiceNo(invoiceNo);
			paymentRecord.setPayTime(payTime);
			paymentRecord.setPayType(OrderPayType.CARD_OUT.getIndex());
			paymentRecord.setPosNo(posNo);
			paymentRecord.setToPay(amount);
			paymentRecord.setRealPay(amount);
			paymentRecord.setReqSerial(reqSerial);
			
			paymentRecord.setTraceNo(traceInfo.getTraceNo());
			paymentRecord.setReferenceNo(traceInfo.getReferenceNo());
			paymentRecord.setCardNo(traceInfo.getCardNo());
			
			paymentService.insertPaymentRecord(paymentRecord);
			
			//check record
			CheckRecord checkRecord = new CheckRecord();
			checkRecord.setCheckDate(checkDate);
			checkRecord.setEmpNo(empNo);
			checkRecord.setPosNo(posNo);
			checkRecord.setToPay(amount);
			checkRecord.setRealPay(amount);
			checkRecord.setReqSerial(reqSerial);
			checkRecordMapper.insertSelective(checkRecord);
			
			try {
				if (StringUtils.isNotBlank(invoiceBatch)&&StringUtils.isNotBlank(invoiceNo)) {
					InvoicePrint invoicePrint = new InvoicePrint();
					invoicePrint.setInvoiceBatch(invoiceBatch);
					invoicePrint.setInvoiceNo(invoiceNo);
					invoicePrint.setInvoiceAmount(amount);
					invoicePrint.setPayTime(payTime);
					invoicePrint.setPayType(OrderPayType.CARD_OUT.getIndex());
					invoicePrint.setChargeEmp(empNo);
					invoicePrint.setPosNo(posNo);
					invoicePrint.setFeeType(PaymentFeeType.ACCOUNT_PAYMENT.getIndex());
					invoicePrint.setReqSerial(reqSerial);
					invoicePrint.setPrintTimes(1);
					invoicePrintMapper.insertSelective(invoicePrint);
				}
			} catch (Exception e) {
				log.warn("扎帐  发票记录 异常",e);
			}
			
			return ResultCode.SUCCESS;
		}
	}
	
	
	@Override
	public Integer getNoCheckCountByEmpNo(String empNo) {
		CheckInfoExample example = new CheckInfoExample();
		example.or().andEmpNoEqualTo(empNo).andCheckStatusEqualTo(0);
		return checkInfoMapper.countByExample(example);
	}
	@Override
	public boolean checkPaymentTodayByTime(String empNo,Date checkTime){
		Integer flag=paymentRecordDao.selectCheckPaymentRecordCountBycheckTime(empNo, checkTime);
		if(flag!=null&&flag.intValue()>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean checkPaymentHistoryByDate(Integer checkId){
		CheckInfo ci=checkInfoMapper.selectByPrimaryKey(checkId);
		if(ci!=null&&ci.getCheckStatus().intValue()==Constants.CHECK_STATUS_YES){
			return true;
		}
		return false;
	}
	
}
