package com.zycj.tcc.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zycj.tcc.cache.FeeRegularCache;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.common.SystemConfig;
import com.zycj.tcc.domain.FeeRegular;
import com.zycj.tcc.service.ChargeableEngine;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.util.DecimalFormatUtil;
import com.zycj.tcc.util.FeeRegularInfo;
import com.zycj.tcc.vo.ChargeableEngineResult;

/**
 * 默认的计费引擎实现
 * 
 * @author 洪捃能
 *
 */
@Service
public class DefaultChargeableEngine implements ChargeableEngine {
	private final static Logger log = Logger.getLogger(DefaultChargeableEngine.class);

	/**
	 * [ {"carType":"1","formula":{"startTime":"07:30:00","endTime":"21:30:00",
	 * "intervalTime":"30","cost":"2.00","maxCost":"25.00","timeType":"min",
	 * "freeReg":{"freeTime":"15","firstChargeTime":"15","firstChargeTimeCost":
	 * "3.00"}}} ,
	 * {"carType":"2","formula":{"startTime":"07:30:00","endTime":"19:30:00",
	 * "intervalTime":"30","cost":"4.00","maxCost":"25.00","timeType":"min",
	 * "freeReg":{"freeTime":"15","firstChargeTime":"15","firstChargeTimeCost":
	 * "6.00"}}} ] carType:车类型：1=小车，2=大车 formula:公式 XX startTime：收费开始时间
	 * endTime：收费结束时间 intervalTime：收费间隔时间 cost：每个收费间隔时间产生的停车费 maxCost：最高停车费
	 * timeType：时间类型，相对于intervalTime、freeTime、firstChargeTime的时间单位(hr:小时，min：分钟,
	 * sec:秒) freeReg：免费规则 XX freeTime：免费时间，在这个时间段内不产生停车费
	 * firstChargeTime：过了免费时间后的第一次收费时间 firstChargeTimeCost：过了免费时间后的第一次停车费
	 */
	@Override
	public ChargeableEngineResult execute(Date inTime, Date outTime, FeeRegularInfo fri, int type) {
		try {
			if (inTime == null) {
				return new ChargeableEngineResult(ResultCode.Chargeable_Engine_InTime_IsBlank);
			}
			if (outTime == null) {
				return new ChargeableEngineResult(ResultCode.Chargeable_Engine_OutTime_IsBlank);
			}
			if (fri == null) {
				return new ChargeableEngineResult(ResultCode.Chargeable_Engine_FeeRegular_IsBlank);
			}
			if (fri.getIntervalTime() == null) {
				return new ChargeableEngineResult(ResultCode.Chargeable_Engine_IntervalTime_IsBlank);
			}
			if (fri.getFreeTime() == null) {
				return new ChargeableEngineResult(ResultCode.Chargeable_Engine_FreeTime_IsBlank);
			}
			if (fri.getFirstChargeTime() == null) {
				return new ChargeableEngineResult(ResultCode.Chargeable_Engine_FirstChargeTime_IsBlank);
			}
			// 判断驶入时间和驶出时间是不是同一天
			if (!DateUtil.format_YMD(inTime).equals(DateUtil.format_YMD(outTime))) {
				return new ChargeableEngineResult(ResultCode.Chargeable_Engine_InOutTime_NOT_TODAY);
			}
			Date start = fri.getStartTime();// 计费开始时间hms
			Date end = fri.getEndTime();// 计费结束时间hms
			if (log.isDebugEnabled()) {
				log.debug("计费开始时间：" + ymdhmsStr(fri.getStartTime()));
				log.debug("计费结束时间：" + ymdhmsStr(fri.getEndTime()));
				log.debug("车辆驶入时间：" + ymdhmsStr(inTime));
				log.debug("车辆驶出时间：" + ymdhmsStr(outTime));
			}
			Date inTime_hms = DateUtil.parse_HMS(inTime);// 处理后的驶入时间hms
			Date outTime_hms = DateUtil.parse_HMS(outTime);// 处理后的驶出时间hms
			if (log.isDebugEnabled()) {
				log.debug("处理后的驶入时间：" + ymdhmsStr(inTime_hms));
				log.debug("处理后的驶出时间：" + ymdhmsStr(outTime_hms));
			}
			int luxuryTime_sec = SystemConfig.Car_Entry_Luxury_Time * DateUtil.MIN;
			if (log.isDebugEnabled()) {
				log.debug("提前记录驶入时间的范围：" + luxuryTime_sec + "秒");
			}
			/** 判断驶入时间是否在计费时间段内 开始 */
			// 1、判断驶入时间是否比计费时间早
			int isc = (int) DateUtil.getTimeDiffBySec(start, inTime_hms);// 计算计费开始时间与驶入时间的差值
			if (isc < 0) {// 如果驶入时间比计费开始时间早，如果
							// 不在容错时间范围内就返回错误，否则：以计费开始时间当做驶入时间计费费用
				if (isc + luxuryTime_sec < 0) {
					return new ChargeableEngineResult(ResultCode.Chargeable_Engine_InTime_IsEarly);
				} else {
					inTime_hms = start;
					// log.info("驶入时间比计费时间早，并且在容错时间范围内!驶入时间为计费开始时间");
				}
			}
			// 2、判断驶入时间是否比计费结束时间晚
			int iec = (int) DateUtil.getTimeDiffBySec(end, inTime_hms);
			if (iec > 0) {
				return new ChargeableEngineResult(ResultCode.Chargeable_Engine_InTime_IsLast);
			}
			/** 判断驶入时间是否在计费时间段内 结束 */
			/** 判断驶出时间是否在计费时间段内 开始 */
			// 3、判断驶出时间是否比计费开始时间早
			int osc = (int) DateUtil.getTimeDiffBySec(start, outTime_hms);
			if (osc < 0) {
				if (osc + luxuryTime_sec < 0) {// 如果驶出时间比计费开始时间早，如果 不在容错时间范围内就返回
												// 错误,否则：收费金额为0.00
					return new ChargeableEngineResult(ResultCode.Chargeable_Engine_OutTime_IsEarly);
				} else {
					return new ChargeableEngineResult(new BigDecimal("0.00"), ResultCode.SUCCESS);
				}
			}
			// 4、判断驶出时间是否比计费结束时间晚
			int oec = (int) DateUtil.getTimeDiffBySec(end, outTime_hms);
			if (oec > 0) {// 如果驶入时间比计费开始时间早,驶出时间为计费结束时间
				outTime_hms = end;
			}
			/** 判断驶出时间是否在计费时间段内 结束 */
			if (log.isDebugEnabled()) {
				log.debug("用于计算费用的驶入时间：" + ymdhmsStr(inTime_hms));
				log.debug("用于计算费用的驶出时间：" + ymdhmsStr(outTime_hms));
			}
			// 判断驶出时间是否比驶入时间早
			int inoutc = (int) DateUtil.getTimeDiffBySec(inTime_hms, outTime_hms);// 停车时间
																					// 秒
			if (inoutc < 0) {// 如果驶出时间比驶入时间早，终止计算
				return new ChargeableEngineResult(ResultCode.Chargeable_Engine_OutTime_Before_InTime);
			}
			int unit = getSecByTimeType(fri.getTimeType());// 获取基于秒的时间单位
			int intervalTime = fri.getIntervalTime() * unit;// 基础计费时间间隔
			int freeTime = fri.getFreeTime() * unit;// 免费时间
			int firstChargeTime = fri.getFirstChargeTime() * unit;// 免费后的第一次收费时间
			int firstChargeTimeSUM = freeTime + firstChargeTime;// 第一次收费时间
			if (log.isDebugEnabled()) {
				log.debug("公式时间单位：" + unit + "秒");
				log.debug("公式基础计费时间间隔：" + intervalTime + "秒");
				log.debug("公式免费时间：" + freeTime + "秒");
				log.debug("公式免费后的第一次收费时间：" + firstChargeTime + "秒");
				log.debug("公式第一次收费时间：" + firstChargeTimeSUM + "秒");
			}
			double firstCost = fri.getFirstChargeTimeCost();// 免费后第一次收费时间金额
			double cost = fri.getCost();// 基础计费时间间隔的停车费
			double maxCost = fri.getMaxCost();// 收费最高金额
			if (log.isDebugEnabled()) {
				log.debug("公式免费后第一次收费时间金额：" + firstCost + "元");
				log.debug("公式基础计费时间间隔的停车费：" + cost + "元");
				log.debug("公式收费最高金额：" + maxCost + "元");
			}
			double tcf = 0;
			if (inoutc < firstChargeTimeSUM) {// 如果停车时间<第一次收费时间
				if (inoutc > freeTime) {// 如果停车时间在第一次收费时间段内
					tcf = firstCost;
				}
			} else {
				//type=1为刷停车卡缴费
				if (type == 1 && inoutc < (firstChargeTimeSUM + freeTime)) {
					tcf = firstCost;
				} else {
					tcf = firstCost;
					int mfhtcsj = inoutc - firstChargeTimeSUM;// 第一次收费时间后的停车时间
					int shang = mfhtcsj / intervalTime;
					int yu = mfhtcsj % intervalTime;
					if (shang > 0) {
						tcf = tcf + cost * shang;
					}
					if (yu > 0) {
						tcf = tcf + cost;
					}
				}
			}
			if (maxCost > 0 && tcf > maxCost) {
				tcf = maxCost;
			}
			BigDecimal result = new BigDecimal(DecimalFormatUtil.format_00(tcf));
			if (log.isDebugEnabled()) {
				log.debug((fri.getCarType().intValue() == 1) ? "小车"
						: "大车   停车时间：" + (inoutc / DateUtil.H) + "小时" + (inoutc % DateUtil.H / DateUtil.MIN) + "分钟"
								+ (inoutc % DateUtil.H % DateUtil.MIN) + "秒       停车费：" + result);
			}
			return new ChargeableEngineResult(result, ResultCode.SUCCESS);
		} catch (Exception e) {
			log.warn("计费引擎执行异常", e);
			return new ChargeableEngineResult(ResultCode.Chargeable_Engine_Exception);
		}
	}

	public static int getSecByTimeType(String timeType) {
		if ("sec".equals(timeType)) {
			return DateUtil.SEC;
		} else if ("min".equals(timeType)) {
			return DateUtil.MIN;
		} else if ("hr".equals(timeType)) {
			return DateUtil.H;
		} else {
			return DateUtil.MIN;
		}
	}

	public static String ymdhmsStr(Date d) throws ParseException {
		return DateUtil.format_YMDHMS(d);
	}

	public static void main(String[] args) throws ParseException {
		Date inTimex = DateUtil.parse_YMDHMS("2014-09-23 10:28:55");// 驶入时间
		Date outTimex = DateUtil.parse_YMDHMS("2014-09-23 21:30:00");// 驶出时间，临时，用于计算停车费
		if (DateUtil.format_YMD(inTimex).equals(DateUtil.format_YMD(outTimex))) {
			System.out.println("同一天");
		} else {
			System.out.println("不是同一天");
		}
		Integer carType = 1;
		FeeRegular fr = new FeeRegular();
		fr.setId(1);
		fr.setRegCode("A");
		fr.setRegFormula(
				"[{\"carType\":\"1\",\"formula\":{\"startTime\":\"07:30:00\",\"endTime\":\"21:30:00\",\"intervalTime\":\"30\",\"cost\":\"2.00\",\"maxCost\":\"-1\",\"timeType\":\"min\",\"freeReg\":{\"freeTime\":\"15\",\"firstChargeTime\":\"15\",\"firstChargeTimeCost\":\"3.00\"}}},{\"carType\":\"2\",\"formula\":{\"startTime\":\"07:30:00\",\"endTime\":\"19:30:00\",\"intervalTime\":\"30\",\"cost\":\"4.00\",\"maxCost\":\"-1\",\"timeType\":\"min\",\"freeReg\":{\"freeTime\":\"15\",\"firstChargeTime\":\"15\",\"firstChargeTimeCost\":\"6.00\"}}}]");
		List<FeeRegular> feeRegList = new ArrayList<FeeRegular>();
		feeRegList.add(fr);
		FeeRegularCache.init(feeRegList);
		FeeRegularInfo fri = FeeRegularCache.get(fr.getId(), carType);
		/******* 开始计费 ******************************/
		System.out.println("计费开始时间：" + ymdhmsStr(fri.getStartTime()));
		System.out.println("计费结束时间：" + ymdhmsStr(fri.getEndTime()));
		System.out.println("车辆驶入时间：" + ymdhmsStr(inTimex));
		System.out.println("车辆使出时间：" + ymdhmsStr(outTimex));
		Date start = fri.getStartTime();// hms
		Date end = fri.getEndTime();// hms
		// 处理驶入驶出时间
		Date inTime_hms = DateUtil.parse_HMS(inTimex);
		System.out.println("处理后的驶入时间：" + ymdhmsStr(inTime_hms));
		Date outTime_hms = DateUtil.parse_HMS(outTimex);
		System.out.println("处理后的驶出时间：" + ymdhmsStr(outTime_hms));
		// 判断驶入时间是否在计费时间段内，如果不在，就已计费开始时间作为驶入时间
		int sic = (int) DateUtil.getTimeDiffBySec(start, inTime_hms);
		System.out.println("计费开始时间与驶入时间的时间差：" + sic + "秒");
		if (sic < 0) {// 如果驶入时间比计费开始时间早，终止计算
			return;
		}
		// 判断驶出时间是否在计费时间段内，如果不在，就已计费结束时间作为驶出时间
		int eoc = (int) DateUtil.getTimeDiffBySec(end, outTime_hms);
		System.out.println("计费结束时间与驶出时间的时间差：" + eoc + "秒");
		if (eoc > 0) {// 如果驶入时间比计费开始时间早,驶出时间为计费结束时间
			outTime_hms = end;
		}
		// 判断是驶出时间是否比驶入时间早
		int inoutc = (int) DateUtil.getTimeDiffBySec(inTime_hms, outTime_hms);// 停车时间
																				// 秒
		System.out.println("计费驶入时间与驶出时间的时间差：" + inoutc + "秒");
		if (inoutc < 0) {// 如果驶出时间比驶入时间早，终止计算
			return;
		}
		int unit = getSecByTimeType(fri.getTimeType());// 获取基于秒的时间单位
		int intervalTime = fri.getIntervalTime() * unit;// 基础计费时间间隔
		int freeTime = fri.getFreeTime() * unit;// 免费时间
		int firstChargeTime = fri.getFirstChargeTime() * unit;// 免费后的第一次收费时间
		int firstChargeTimeSUM = freeTime + firstChargeTime;// 第一次收费时间
		System.out.println("时间单位：" + unit + "秒;基础计费时间间隔：" + intervalTime + "秒;免费时间：" + freeTime + "秒;免费后的第一次收费时间："
				+ firstChargeTime + "秒;第一次收费时间：" + firstChargeTimeSUM + "秒");
		double firstCost = fri.getFirstChargeTimeCost();// 免费后第一次收费时间金额
		double cost = fri.getCost();// 基础计费时间间隔的停车费
		double maxCost = fri.getMaxCost();// 收费最高金额
		System.out.println("停车时间：" + (inoutc / DateUtil.H) + "小时" + (inoutc % DateUtil.H / DateUtil.MIN) + "分钟"
				+ (inoutc % DateUtil.H % DateUtil.MIN) + "秒");
		double tcf = 0;
		if (inoutc < firstChargeTimeSUM) {// 如果停车时间<第一次收费时间
			if (inoutc > freeTime) {// 如果停车时间在第一次收费时间段内
				tcf = firstCost;
			}
		} else {
			tcf = firstCost;
			int mfhtcsj = inoutc - firstChargeTimeSUM;// 第一次收费时间后的停车时间
			System.out.println("第一次收费时间后的停车时间:" + mfhtcsj + "秒");
			int shang = mfhtcsj / intervalTime;
			int yu = mfhtcsj % intervalTime;
			System.out.println("商：" + shang + ";余：" + yu);
			if (shang > 0) {
				tcf = tcf + cost * shang;
			}
			if (yu > 0) {
				tcf = tcf + cost;
			}
		}
		if (maxCost > 0 && tcf > maxCost) {
			tcf = maxCost;
		}
		BigDecimal result = new BigDecimal(DecimalFormatUtil.format_00(tcf));
		System.out.println("停车费：" + result + "元");
	}
}
