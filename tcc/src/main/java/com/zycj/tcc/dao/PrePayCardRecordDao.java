package com.zycj.tcc.dao;

import java.util.Date;
import java.util.List;

import com.zycj.tcc.domain.PrePayCardCheckInfo;
import com.zycj.tcc.domain.PrePayCardRecord;

public interface PrePayCardRecordDao {
	/**
	 * 保存
	 */
	public void save(PrePayCardRecord prePayCardRecord);
	public int countCardDay(String cardNo, Date start, Date end);
	public List<PrePayCardCheckInfo> getNotCheckInfo(String empNo);
	public PrePayCardRecord getPrePayCardRecordByOrderId(String orderId);
	public void deleteByOrderId(String orderId);
	public void saveError(PrePayCardRecord record);
	public void check(String emp_no, String date);
	public int getEmpChargeAmount(String empNo);
}
