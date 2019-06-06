package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.PrePayCardCheckInfo;
import com.zycj.tcc.domain.PrePayCardRecord;

import java.util.Date;
import java.util.List;

public interface PrePayCardRecordMapper {
    int insertSelective(PrePayCardRecord record);
    public int countCardDay(String cardNo, Date start, Date end);
	List<PrePayCardCheckInfo> getNotCheckInfo(String empNo);
	PrePayCardRecord getPrePayCardRecordByOrderId(String orderId);
	void deleteByOrderId(String orderId);
	void saveError(PrePayCardRecord record);
	void check(String emp_no, String date);
	Integer getEmpChargeAmount(String empNo);
}