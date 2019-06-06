package com.zycj.tcc.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zycj.tcc.dao.BaseDao;
import com.zycj.tcc.dao.PrePayCardRecordDao;
import com.zycj.tcc.domain.PrePayCardCheckInfo;
import com.zycj.tcc.domain.PrePayCardRecord;
import com.zycj.tcc.mybatis.mapper.PrePayCardRecordMapper;

@Repository
public class PrePayCardRecordDaoImpl extends BaseDao implements PrePayCardRecordDao {
	@Autowired
	private PrePayCardRecordMapper prePayCardRecordMapper;
	@Override
	public void save(PrePayCardRecord prePayCardRecord) {
		prePayCardRecordMapper.insertSelective(prePayCardRecord);
	}
	@Override
	public int countCardDay(String cardNo, Date start, Date end) {
		return prePayCardRecordMapper.countCardDay(cardNo, start, end);
	}
	@Override
	public List<PrePayCardCheckInfo> getNotCheckInfo(String empNo) {
		return prePayCardRecordMapper.getNotCheckInfo(empNo);
	}
	@Override
	public PrePayCardRecord getPrePayCardRecordByOrderId(String orderId) {
		return prePayCardRecordMapper.getPrePayCardRecordByOrderId(orderId);
	}
	@Override
	public void deleteByOrderId(String orderId) {
		prePayCardRecordMapper.deleteByOrderId(orderId);
	}
	@Override
	public void saveError(PrePayCardRecord record) {
		prePayCardRecordMapper.saveError(record);
	}
	@Override
	public void check(String emp_no, String date) {
		prePayCardRecordMapper.check(emp_no,date);
	}
	@Override
	public int getEmpChargeAmount(String empNo) {
		Integer empChargeAmount = prePayCardRecordMapper.getEmpChargeAmount(empNo);
		return empChargeAmount== null?0:empChargeAmount;
	}
}
