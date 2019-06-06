package com.tcc.park.api.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcc.park.api.mybatis.PrePayCardMapper;
import com.tcc.park.api.mybatis.PrePayCardRecordMapper;
import com.tcc.park.api.user.domain.PrePayCard;
import com.tcc.park.api.user.domain.PrePayCardRecord;
import com.tcc.park.api.util.DateUtil;
import com.tcc.park.api.util.HttpUtil;
import com.tcc.park.api.util.PrePayCardHelper;
import com.tcc.park.api.util.PrePayPropertiesUtil;
import com.tcc.park.api.vo.PrePayCardFeeVo;
import com.tcc.park.api.vo.PrePayCardVo;


@Service
public class PrePayCardService {

	public static final String REMOTE_QUERY_METHOD = "allinpay.ppcs.cardinfo.get";
	
	private static final String encode = "UTF-8";
	@Autowired
	private PrePayCardMapper prePayCardMapper;
	@Autowired
	private PrePayCardRecordMapper prePayCardRecordMapper;

	/**
	 * 根据手机号码获取该手机号码下的所有卡号
	 * 
	 * @param phone
	 * @return
	 */
	public List<PrePayCardVo> getCardListByPhone(String phone) {
		List<PrePayCard> selectByPhone = prePayCardMapper.selectByPhone(phone);
		List<PrePayCardVo> cards = new ArrayList<PrePayCardVo>();
		for (PrePayCard prePayCard : selectByPhone) {
			PrePayCardVo card1 = new PrePayCardVo();
			card1.setCardCarNo(prePayCard.getCardCarNo());
			card1.setCardBalance(getAmountByCardNo(prePayCard.getCardNo()));
			card1.setCardContacts(prePayCard.getCardContacts());
			card1.setCardFreeTime(prePayCard.getCardFreeTime());
			card1.setCardNo(prePayCard.getCardNo());
			card1.setCardPhone(prePayCard.getCardPhone());
			card1.setCardScore(prePayCard.getCardScore());
			card1.setCardStatus(prePayCard.getCardStatus());
			card1.setCardType(prePayCard.getCardType());
			card1.setCreateTime(formartDate(prePayCard.getCreateTime()));
			card1.setEndTime(formartDate(prePayCard.getEndTime()));
			card1.setLastDealTime(formartDate(prePayCard.getLastDealTime()));
			card1.setUseEndTime(formartDate(prePayCard.getUseEndTime()));
			cards.add(card1);
		}
		return cards;
	}

	/**
	 * 根据卡号获得该卡的最近的消费记录
	 * 
	 * @param phone
	 * @return
	 */
	public List<PrePayCardFeeVo> getCardFeesByNo(String cardNo) {
		List<PrePayCardRecord> selectByCard = prePayCardRecordMapper.selectByCard(cardNo);
		List<PrePayCardFeeVo> result = new ArrayList<PrePayCardFeeVo>();
		for (PrePayCardRecord prePayCardRecord : selectByCard) {
			String mm= " - ";
			if(prePayCardRecord.getRecordType()==1){
				mm = " + ";
			}
			result.add(new PrePayCardFeeVo(prePayCardRecord.getTip(), mm + formartAmount(prePayCardRecord.getAmount()), formartDate(prePayCardRecord.getCreateTime())));
		}
		return result;
	}
	
	/**
	 * 格式化金额
	 * @param num
	 * @return
	 */
	public String formartAmount(Integer num) {
		if (num == null) {
			return "0.00";
		}
		NumberFormat instance = NumberFormat.getInstance();
		instance.setMaximumFractionDigits(2);
		instance.setMinimumFractionDigits(2);
		double amount = num / 100.0;
		String format = instance.format(amount);
		return format;
	}

	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	public String formartDate(Date date) {
		if (date == null) {
			return "";
		} else {
			return DateUtil.getDateStringByFormat(date, "yyyy-MM-dd HH:mm:ss");
		}
	}

	/**
	 * 远程获取卡号余额
	 * @param cardNo
	 * @return
	 */
	public String getAmountByCardNo(String cardNo) {
		Map<String, Object> pp_params = PrePayCardHelper.buildBaseParam(REMOTE_QUERY_METHOD);
		pp_params.put("card_id",cardNo);// 充值卡号
		try {
			PrePayCardHelper.sign(pp_params, encode);
			String post = HttpUtil.get(PrePayPropertiesUtil.get("base_url"), pp_params, encode);
			JSONObject parse = (JSONObject) JSON.parse(post);
			if(parse != null && (parse = (JSONObject)parse.get("ppcs_cardinfo_get_response")) !=null){
				if((parse=(JSONObject)parse.get("card_info"))!=null){
					if((parse=(JSONObject)parse.get("card_product_info_arrays"))!=null){
						JSONArray array = (JSONArray)parse.get("card_product_info");
						if(array!=null){
							for (Object info : array) {
								JSONObject i = (JSONObject) info;
								if(i.get("product_id")!=null && i.get("product_id").toString().equals("0002")){
									String balance = i.get("valid_balance").toString();
									int parseInt = Integer.parseInt(balance);
									return formartAmount(parseInt);
								}
							}	
						}
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "查询失败";
	}

}
