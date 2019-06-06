package com.zycj.tcc.dao;

public interface LogEmpLoginDao {

	/**
	 * 增加员工签到签退日志
	 * @param type 签到、签退、日终签退
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @param ip
	 * @return
	 */
	public int addLogEmpLogin(Integer type, String empNo, String posNo, String ip);
}
