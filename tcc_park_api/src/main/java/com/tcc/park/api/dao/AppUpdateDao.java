package com.tcc.park.api.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.tcc.park.api.domain.AppUpdate;

@Repository
public class AppUpdateDao extends SqlSessionDaoSupport{
	
	public AppUpdate  selectUpdateByCodeType(Integer clientType,Integer versionCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientType", clientType);
		params.put("versionCode", versionCode);
		return getSqlSession().selectOne("AppUpdateDao.selectByCodeType", params);
	}
	
}
