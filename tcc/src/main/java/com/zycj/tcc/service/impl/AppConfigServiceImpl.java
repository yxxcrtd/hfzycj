package com.zycj.tcc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.domain.AppUpdate;
import com.zycj.tcc.domain.AppUpdateExample;
import com.zycj.tcc.mybatis.mapper.AppUpdateMapper;
import com.zycj.tcc.service.AppConfigService;
import com.zycj.tcc.vo.AppUpdateVo;

@Service
public class AppConfigServiceImpl implements AppConfigService {

	@Autowired
	private AppUpdateMapper appUpdateMapper;
	
	@Override
	public AppUpdateVo checkUpdate(Integer versionCode,Integer appType) {
//		SELECT 
//	    *
//	    FROM app_update
//	    WHERE 
//	     version_code_min &lt;= #{versionCode}
//	    AND version_code_max >= #{versionCode}
//	    ORDER BY id DESC 
//	    LIMIT 1
		AppUpdateVo vo = new AppUpdateVo();
		vo.setIsNew("0");
		AppUpdateExample example = new AppUpdateExample();
		example.or().andVersionCodeMinLessThanOrEqualTo(versionCode).andVersionCodeMaxGreaterThanOrEqualTo(versionCode)
		.andAppTypeEqualTo(appType).andStatusEqualTo(1);
		example.setOrderByClause(" id DESC");
		List<AppUpdate> list = appUpdateMapper.selectByExample(example);
		if (list != null  && list.size()>0) {
			AppUpdate info = list.get(0);
			vo.setIntroduce(info.getUpdateDescription());
			vo.setIsForce(String.valueOf(info.getIsForce()));
			vo.setIsNew("1");
			vo.setUrl(info.getApkUrl());
			vo.setSize(info.getApkSize());
		}
		return vo;
	}

}
