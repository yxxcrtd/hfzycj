package com.zycj.tcc.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.zycj.tcc.vo.CheckListInfo;
import com.zycj.tcc.vo.TraceInfoVo;

public interface CheckService {
	public List<CheckListInfo> getCheckListByEmpNo(String empNo); 
	public String confirmCheck(Integer checkId, String empNo, BigDecimal amount, int isToday, Date checkDate, Date requestTime, String invoiceBatch, String invoiceNo, String posNo, String reqSerial, TraceInfoVo traceInfo);
	/**
	 * 查询扎帐汇总表中未扎帐的笔数
	 * @param empNo 员工编号
	 * @return
	 */
	public Integer getNoCheckCountByEmpNo(String empNo); 
	
	/**
	 * 校验时间段内是否已经有扎完帐的缴费记录了
	 * @param empNo 员工编号
	 * @param checkTime 扎帐时间点
	 * @return true:有，false:没有
	 */
	public boolean checkPaymentTodayByTime(String empNo, Date checkTime);
	
	/**
	 * 校验该历史扎帐是否已经扎帐了
	 * @param checkId
	 * @return true:是，false:否
	 */
	public boolean checkPaymentHistoryByDate(Integer checkId);
}
