package com.zycj.tcc.service;

public interface EmployeeOnlineService {

	/**
	 * 根据员工编号和终端编号 更新或新增员工在线记录
	 * @param empNo 员工编号
	 * @param posNo 终端编号
	 * @param ip 终端ip
	 * @param token 
	 * @return true:更新成功,false:更新失败
	 */
	public boolean updateOrAddEmployeeOnlineForLogin(String empNo, String posNo, String ip, String token)throws Exception;
}
