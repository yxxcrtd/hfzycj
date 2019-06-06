package com.zycj.tcc.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.dao.BaseDao;
import com.zycj.tcc.dao.EmployeeOnlineDao;
import com.zycj.tcc.domain.EmployeeOnline;
import com.zycj.tcc.mybatis.mapper.EmployeeOnlineMapper;

@Repository
public class EmployeeOnlineDaoImpl extends BaseDao implements EmployeeOnlineDao {

	@Autowired
	private EmployeeOnlineMapper employeeOnlineMapper;
	@Override
	public int updateStatusDownByEmpNoOrPosNo(String empNo, String posNo) {
		Map<String,String> val=new HashMap<String,String>();
		val.put("empNo", empNo);
		val.put("posNo", posNo);
		return update("EmployeeOnlineDao.updateStatusDownByEmpNoOrPosNo", val);
	}

	@Override
	public int updateStatusDownByEmpNo(String empNo) {
		return update("EmployeeOnlineDao.updateStatusDownByEmpNo", empNo);
	}

	@Override
	public EmployeeOnline selectEmployeeOnlineByEmpNoAndPosNo(String empNo,String posNo) {
		Map<String,String> val=new HashMap<String,String>();
		val.put("empNo", empNo);
		val.put("posNo", posNo);
		return (EmployeeOnline) query("EmployeeOnlineDao.selectEmployeeOnlineByEmpNoAndPosNo", val);
	}

	@Override
	public EmployeeOnline selectEmployeeOnlineByTokenAndRelevantNo(
			String empNo, String posNo, String token) {
		Map<String,String> val=new HashMap<String,String>();
		val.put("empNo", empNo);
		val.put("posNo", posNo);
		val.put("token", token);
		return (EmployeeOnline) query("EmployeeOnlineDao.selectEmployeeOnlineByTokenAndRelevantNo", val);
	}

	@Override
	public int addEmployeeOnlineForLogin(String empNo, String posNo, String ip,String token) {
		EmployeeOnline el=new EmployeeOnline();
		el.setEmpNo(empNo);
		el.setPosNo(posNo);
		el.setStatus(Constants.EMPLOYEE_ONLINE_STATUS_ON);
		el.setToken(token);
		el.setLoginIp(ip);
		el.setLoginTime(new Date());
		el.setActiveTime(new Date());
		return employeeOnlineMapper.insertSelective(el);
	}

	@Override
	public int updateEmployeeOnlineForLogin(Integer id, String ip, String token) {
		EmployeeOnline el=new EmployeeOnline();
		el.setId(id);
		el.setStatus(Constants.EMPLOYEE_ONLINE_STATUS_ON);
		el.setToken(token);
		el.setLoginIp(ip);
		el.setLoginTime(new Date());
		el.setActiveTime(new Date());
		el.setUpdateTime(new Date());
		return employeeOnlineMapper.updateByPrimaryKeySelective(el);
	}

	@Override
	public int updateStatusDownForLogout(String empNo, String posNo) {
		Map<String,String> val=new HashMap<String,String>();
		val.put("empNo", empNo);
		val.put("posNo", posNo);
		return update("EmployeeOnlineDao.updateStatusDownForLogout", val);
	}

	@Override
	public int updateStatusDownByAll() {
		return update("EmployeeOnlineDao.updateStatusDownByAll", null);
	}
	
	@Override
	public int updateStatusDownById(Integer id) {
		return update("EmployeeOnlineDao.updateStatusDownById", id);
	}

	@Override
	public List<EmployeeOnline> selectEmployeeOnlineByStatus(Integer status) {
		return queryList("EmployeeOnlineDao.selectEmployeeOnlineByStatus", status);
	}
}
