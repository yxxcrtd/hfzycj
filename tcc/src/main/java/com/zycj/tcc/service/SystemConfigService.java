package com.zycj.tcc.service;

import com.zycj.tcc.server.vo.Response;

public interface SystemConfigService {

	/**
	 * 修改系统配置
	 * @param checkNo 系统校验码
	 * @param fieldName 字段名称
	 * @param fieldVal 字段值
	 * @return
	 */
	public Response updateSystemConfig(String checkNo, String fieldName, String fieldVal);

	/**
	 * 更新计费规则缓存
	 * @return
	 */
	public Response updateFeeRegularCache();
}
