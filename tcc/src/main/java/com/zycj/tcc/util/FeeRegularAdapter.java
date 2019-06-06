package com.zycj.tcc.util;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zycj.tcc.domain.FeeRegular;

/**
 * 计费规则工具类
 * @author 洪捃能
 *
 */
public class FeeRegularAdapter {
	private final static Logger log = Logger.getLogger(FeeRegularAdapter.class); 
	/**
	 [
	 {"carType":"1","formula":{"startTime":"07:30:00","endTime":"21:30:00","intervalTime":"30","cost":"2.00","maxCost":"25.00","timeType":"min","freeReg":{"freeTime":"15","firstChargeTime":"15","firstChargeTimeCost":"3.00"}}}
	 ,
	 {"carType":"2","formula":{"startTime":"07:30:00","endTime":"19:30:00","intervalTime":"30","cost":"4.00","maxCost":"25.00","timeType":"min","freeReg":{"freeTime":"15","firstChargeTime":"15","firstChargeTimeCost":"6.00"}}}
	 ]
	 * carType:车类型：1=小车，2=大车
	 * formula:公式 XX
	 * startTime：收费开始时间
	 * endTime：收费结束时间
	 * intervalTime：收费间隔时间
	 * cost：每个收费间隔时间产生的停车费
	 * maxCost：最高停车费
	 * timeType：时间类型，相对于intervalTime、freeTime、firstChargeTime的时间单位(hr:小时，min：分钟,sec:秒)
	 * freeReg：免费规则 XX
	 * freeTime：免费时间，在这个时间段内不产生停车费
	 * firstChargeTime：过了免费时间后的第一次收费时间
	 * firstChargeTimeCost：过了免费时间后的第一次停车费
	 * @throws ParseException 
	 */
	
	
	public static Map<String,FeeRegularInfo> format(FeeRegular fr){
		Map<String,FeeRegularInfo> friMap=new HashMap<String,FeeRegularInfo>();
		if(fr==null){
			return friMap;
		}
		String regFormula=fr.getRegFormula();
		if(StringUtils.isBlank(regFormula)){
			return friMap;
		}
//		regFormula="[{\"carType\":\"1\",\"formula\":{\"startTime\":\"07:30:00\",\"endTime\":\"21:30:00\",\"intervalTime\":\"30\",\"cost\":\"2.00\",\"maxCost\":\"25.00\",\"timeType\":\"min\",\"freeReg\":{\"freeTime\":\"15\",\"firstChargeTime\":\"15\",\"firstChargeTimeCost\":\"3.00\"}}},{\"carType\":\"2\",\"formula\":{\"startTime\":\"07:30:00\",\"endTime\":\"19:30:00\",\"intervalTime\":\"30\",\"cost\":\"4.00\",\"maxCost\":\"25.00\",\"timeType\":\"min\",\"freeReg\":{\"freeTime\":\"15\",\"firstChargeTime\":\"15\",\"firstChargeTimeCost\":\"6.00\"}}}]";
		try {
			JSONArray jarr=(JSONArray) JSONObject.parse(regFormula);
			for (int i = 0; i <jarr.size(); i++) {
				FeeRegularInfo fri=new FeeRegularInfo();
				fri.setRegid(fr.getId());
				JSONObject jo=jarr.getJSONObject(i);
				fri.setCarType(jo.getInteger("carType"));
				JSONObject formula=jo.getJSONObject("formula");
				fri.setStartTime(DateUtil.parse_HMS(formula.getString("startTime")));
				fri.setEndTime(DateUtil.parse_HMS(formula.getString("endTime")));
				fri.setIntervalTime(formula.getInteger("intervalTime"));
				fri.setCost(formula.getDouble("cost"));
				fri.setMaxCost(formula.getDouble("maxCost"));
				fri.setTimeType(formula.getString("timeType"));
				JSONObject freeReg=formula.getJSONObject("freeReg");
				fri.setFreeTime(freeReg.getInteger("freeTime"));
				fri.setFirstChargeTime(freeReg.getInteger("firstChargeTime"));
				fri.setFirstChargeTimeCost(freeReg.getDouble("firstChargeTimeCost"));
				friMap.put(fri.getRegid().intValue()+"-"+fri.getCarType().intValue(), fri);
			}
		} catch (Exception e) {
			log.warn("计费规则格式化错误！", e);
		}
		return friMap;
	}
	
}
