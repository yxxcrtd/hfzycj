package com.zycj.tcc.service;

import com.zycj.tcc.vo.AppUpdateVo;

public interface AppConfigService {

	public AppUpdateVo checkUpdate(Integer versionCode, Integer appType);
	
}
