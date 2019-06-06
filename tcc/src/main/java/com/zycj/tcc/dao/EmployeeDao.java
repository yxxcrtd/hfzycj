package com.zycj.tcc.dao;

import com.zycj.tcc.domain.Employee;

public interface EmployeeDao {

	/**
	 * 查询收费员
	 * @param empNo 员工编号
	 * @return
	 */
	public Employee selectEmployeeByEmpNo(String empNo);
	
	/**
	 * 根据员工编号查询员工姓名
	 * @param empNo 员工编号
	 * @return 员工姓名
	 */
	public String selectEmpNameByEmpNo(String empNo);
}
