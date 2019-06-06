package com.zycj.tcc.dao.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zycj.tcc.dao.LogEmpLoginDao;
import com.zycj.tcc.domain.LogEmpLogin;
import com.zycj.tcc.mybatis.mapper.LogEmpLoginMapper;

@Repository
public class LogEmpLoginDaoImpl implements LogEmpLoginDao {
	private final static Logger log = Logger.getLogger(LogEmpLoginDaoImpl.class); 
	
	@Autowired
	private LogEmpLoginMapper logEmpLoginMapper;
	@Override
	public int addLogEmpLogin(Integer type, String empNo, String posNo,String ip) {
		LogEmpLogin lel=new LogEmpLogin();
		lel.setType(type);
		lel.setEmpNo(empNo);
		lel.setPosNo(posNo);
		lel.setLoginIp(ip);
		lel.setOperateTime(new Date());
		lel.setCreateTime(new Date());
		try {
			return logEmpLoginMapper.insertSelective(lel);
		} catch (Exception e) {
			log.warn("增加员工签到签退日志 异常",e);
		}
		return 0;
	}
}
