package com.zycj.tcc.service;

import com.zycj.tcc.server.vo.Response;


public interface EmployeeService {
	
	/**
	 * 修改密码
	 * @param empNo 员工编号
	 * @param oldPwd 旧密码
	 * @param newPwd 新密码
	 * @return
	 */
	public Response updatePassword(String empNo, String oldPwd, String newPwd);
}
