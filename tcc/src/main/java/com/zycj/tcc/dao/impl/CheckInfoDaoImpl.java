package com.zycj.tcc.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zycj.tcc.dao.BaseDao;
import com.zycj.tcc.dao.CheckInfoDao;
import com.zycj.tcc.domain.CheckInfo;

@Repository
public class CheckInfoDaoImpl extends BaseDao implements CheckInfoDao {

	@Override
	public CheckInfo selectCheckInfoByEmpAndCheckDate(String empNo, Date checkDate) {
		Map<String,Object> val=new HashMap<String,Object>();
		val.put("empNo", empNo);
		val.put("checkDate", checkDate);
		return (CheckInfo) query("CheckInfoDao.selectCheckInfoByEmpAndCheckDate", val);
	}

}
