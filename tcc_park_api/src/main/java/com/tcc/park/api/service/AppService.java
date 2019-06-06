package com.tcc.park.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.park.api.dao.AppUpdateDao;
import com.tcc.park.api.domain.AppUpdate;
import com.tcc.park.api.vo.AppUpdateVo;

@Service
public class AppService {

	@Autowired
	private AppUpdateDao appUpdateDao;
	
	public AppUpdateVo getAppUpdateInfo(Integer versionCode,Integer clientType) {
		AppUpdateVo updateVo = new AppUpdateVo();
		clientType = (clientType==null?1:clientType);
		if (versionCode ==null) {
			return updateVo;
		}
		AppUpdate update = appUpdateDao.selectUpdateByCodeType(clientType, versionCode);
		if (update ==null) {
			return updateVo;
		}
		updateVo.setApkSize(update.getApkSize());
		updateVo.setApkUrl(update.getApkUrl());
		updateVo.setDescription(update.getUpdateDescription());
		updateVo.setVersionName(update.getVersionName());
		return updateVo;
	}
}
