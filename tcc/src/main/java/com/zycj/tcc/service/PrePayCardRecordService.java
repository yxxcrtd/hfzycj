package com.zycj.tcc.service;

import java.util.List;

import com.zycj.tcc.domain.PrePayCard;
import com.zycj.tcc.domain.PrePayCardCheckInfo;
import com.zycj.tcc.domain.PrePayCardRecord;

public interface PrePayCardRecordService {

	public void save(PrePayCardRecord record);
	public PrePayCard getPrePayCard(String cardNo);
	/**
	 * @param cardNo
	 * @param selDate
	 * @return 根据卡号和时间获得当前卡是否在指定的时间内有效
	 */
	public PrePayCard getPrePayCard(PrePayCard prePayCard);
	public boolean existSection(String sectionName, String cardNo);
	public int countCardDealToday(String cardNo);
	public int countCardDeal(String cardNo, java.util.Date date);
	public List<PrePayCardCheckInfo> getNotCheckInfo(String string);
	public PrePayCardRecord getPrePayCardRecordByOrderId(String orderId);
	public void deleteByOrderId(String orderId);
	public void saveError(PrePayCardRecord record);
	public void check(String emp_no, String date, String amount);
	public int getEmpChargeAmount(String string);
}
