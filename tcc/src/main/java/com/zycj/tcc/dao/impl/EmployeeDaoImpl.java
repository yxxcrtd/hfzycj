package com.zycj.tcc.dao.impl;

import org.springframework.stereotype.Repository;

import com.zycj.tcc.dao.BaseDao;
import com.zycj.tcc.dao.EmployeeDao;
import com.zycj.tcc.domain.Employee;

@Repository
public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {

	@Override
	public Employee selectEmployeeByEmpNo(String empNo) {
		return (Employee) query("EmployeeDao.selectEmployeeByEmpNo", empNo);
	}

	@Override
	public String selectEmpNameByEmpNo(String empNo) {
		return (String) query("EmployeeDao.selectEmpNameByEmpNo", empNo);
	}

	
}
