package com.zycj.tcc.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.dao.EmployeeDao;
import com.zycj.tcc.dao.EmployeeOnlineDao;
import com.zycj.tcc.domain.Employee;
import com.zycj.tcc.mybatis.mapper.EmployeeMapper;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	private final static Logger log = Logger.getLogger(EmployeeServiceImpl.class); 
	
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private EmployeeOnlineDao employeeOnlineDao;

	@Override
	public Response updatePassword(String empNo, String oldPwd, String newPwd) {
		Employee emp=employeeDao.selectEmployeeByEmpNo(empNo);
		if(emp==null){
			ResponseUtil.createResponse(ResultCode.EMPLOYEE_NO_EXIST_ERROR);
		}
		if(!emp.getLoginPwd().equals(oldPwd)){
			return ResponseUtil.createResponse(ResultCode.EMPLOYEE_PASSWORD_ERROR);
		}
		Employee upemp=new Employee();
		upemp.setId(emp.getId());
		upemp.setLoginPwd(newPwd);
		upemp.setUpdateTime(new Date());
		try {
			employeeMapper.updateByPrimaryKeySelective(upemp);
			employeeOnlineDao.updateStatusDownByEmpNo(empNo);
		} catch (Exception e) {
			log.warn("更新修改密码 异常",e);
			return ResponseUtil.createResponse(ResultCode.UPDATE_PWD_FAILED);
		}
		return ResponseUtil.createResponse(ResultCode.SUCCESS);
	}
}
