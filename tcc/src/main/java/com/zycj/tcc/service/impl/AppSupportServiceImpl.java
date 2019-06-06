package com.zycj.tcc.service.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.domain.AppError;
import com.zycj.tcc.domain.LogAppInstall;
import com.zycj.tcc.mybatis.mapper.AppErrorMapper;
import com.zycj.tcc.mybatis.mapper.LogAppInstallMapper;
import com.zycj.tcc.service.AppSupportService;

@Service
public class AppSupportServiceImpl implements AppSupportService {
	
	@Autowired
	private AppErrorMapper appErrorMapper;
	@Autowired
	private LogAppInstallMapper logAppInstallMapper;

	@Override
	public Integer appErrorRecord(Map<String, String> errors) {
		if (errors != null) {
			AppError error = new AppError();
			error.setErrorInfo(errors.get("log_info"));
			error.setPosNo(errors.get("pos_no"));
			return appErrorMapper.insertSelective(error);
		}
		return 0;
	}

	@Override
	public Integer appInstall(Map<String, String> installs) {
		if (installs == null) {
			return 0;
		}
		LogAppInstall appInstall = new LogAppInstall();
		appInstall.setCurrentCode(MapUtils.getInteger(installs, "current_code"));
		appInstall.setFormerCode(MapUtils.getInteger(installs, "former_code"));
		appInstall.setPosNo(installs.get("pos_no"));
		appInstall.setCreateTime(new Date());
		return logAppInstallMapper.insertSelective(appInstall);
	}

}
