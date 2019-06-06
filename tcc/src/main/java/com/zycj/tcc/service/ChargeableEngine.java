package com.zycj.tcc.service;

import java.util.Date;

import com.zycj.tcc.util.FeeRegularInfo;
import com.zycj.tcc.vo.ChargeableEngineResult;

/**
 * 计费引擎
 * @author 洪捃能
 *
 */
public interface ChargeableEngine {

	/**
	 * 执行停车费计算
	 * @param inTime 驶入时间
	 * @param outTime 使出时间
	 * @param fri 计费规则明细类
	 * @param type 1是停车卡消费、补缴计费。2是普通计费
	 * @return 停车费计算结果
	 */
	public ChargeableEngineResult execute(Date inTime, Date outTime, FeeRegularInfo fri, int type);

}
