package com.zycj.tcc.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.dao.PrePayCardRecordDao;
import com.zycj.tcc.domain.PrePayCard;
import com.zycj.tcc.domain.PrePayCardCheckInfo;
import com.zycj.tcc.domain.PrePayCardRecord;
import com.zycj.tcc.domain.PrePayCardSection;
import com.zycj.tcc.mybatis.mapper.PrePayCardMapper;
import com.zycj.tcc.mybatis.mapper.PrePayCardSectionMapper;
import com.zycj.tcc.service.PrePayCardRecordService;
import com.zycj.tcc.util.DateUtil;

/**
 * 预付费卡消费充值记录保存
 *
 */
@Service
public class PrePayCardRecordServiceImpl implements PrePayCardRecordService {
	
	@Autowired
	private PrePayCardMapper prePayCardMapper;
	
	@Autowired
	private PrePayCardRecordDao prePayCardRecordDao;
	
	@Autowired
	private PrePayCardSectionMapper prePayCardSectionMapper;

	/**
	 * 保存消费或者充值记录
	 */
	@Override
	public void save(PrePayCardRecord record) {
		prePayCardRecordDao.save(record);
	}

	/**
	 * 根据卡号获取停车卡
	 */
	@Override
	public PrePayCard getPrePayCard(String cardNo) {
		return prePayCardMapper.getCardByCardNo(cardNo);
	}
	
	public PrePayCard getPrePayCard(PrePayCard prePayCard){
		return prePayCardMapper.getCardBySelDate(prePayCard);
	}

	@Override
	public boolean existSection(String sectionName, String cardNo) {
		PrePayCardSection section =  prePayCardSectionMapper.getByCardNoSectionName(cardNo, sectionName);
		return section!=null;
	}

	@Override
	public int countCardDealToday(String cardNo) {
		Date date = new Date();
		try {
			String format_YMD = DateUtil.format_YMD(date);
			Date start = DateUtil.parse_YMDHMS(format_YMD+" 00:00:00");
			Date end = DateUtil.parse_YMDHMS(format_YMD+" 23:59:59");
			return prePayCardRecordDao.countCardDay(cardNo, start, end);
		} catch (ParseException e) {
			return 0;
		}
	}
	
	@Override
	public int countCardDeal(String cardNo,Date date) {
		try {
			String format_YMD = DateUtil.format_YMD(date);
			Date start = DateUtil.parse_YMDHMS(format_YMD+" 00:00:00");
			Date end = DateUtil.parse_YMDHMS(format_YMD+" 23:59:59");
			return prePayCardRecordDao.countCardDay(cardNo, start, end);
		} catch (ParseException e) {
			return 0;
		}
	}

	@Override
	public List<PrePayCardCheckInfo> getNotCheckInfo(String empNo) {
		return prePayCardRecordDao.getNotCheckInfo(empNo);
	}

	@Override
	public PrePayCardRecord getPrePayCardRecordByOrderId(String orderId) {
		return prePayCardRecordDao.getPrePayCardRecordByOrderId(orderId);
	}

	@Override
	public void deleteByOrderId(String orderId) {
		prePayCardRecordDao.deleteByOrderId(orderId);
	}

	@Override
	public void saveError(PrePayCardRecord record) {
		prePayCardRecordDao.saveError(record);
	}

	@Override
	public void check(String emp_no, String date,String amount) {
		prePayCardRecordDao.check(emp_no,date);
	}

	@Override
	public int getEmpChargeAmount(String empNo) {
		return prePayCardRecordDao.getEmpChargeAmount(empNo);
	}
}
