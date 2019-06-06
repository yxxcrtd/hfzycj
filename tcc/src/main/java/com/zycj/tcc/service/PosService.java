package com.zycj.tcc.service;


public interface PosService {

	/**
	 * 更新pos终端对应的员工
	 * @param posNo 终端编号
	 * @param empNo 员工编号
	 * @param empName 员工姓名
	 * @param onlineStatus 在线状态
	 */
	public int updatePosEmpByPosNo(String posNo, String empNo, String empName, Integer onlineStatus);
}
