package com.zycj.tcc.dao;

import com.zycj.tcc.util.FeeRegularInfo;

public interface FeeRegularDao {

	/**
	 * 获取计费规则明细类
	 * @param regId 计费规则id
	 * @param carType 车类型
	 * @return 计费规则明细类
	 */
	public FeeRegularInfo selectFeeRegularInfoForCache(Integer regId, Integer carType);
}
