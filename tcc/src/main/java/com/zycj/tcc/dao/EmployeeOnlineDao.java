package com.zycj.tcc.dao;

import java.util.List;

import com.zycj.tcc.domain.EmployeeOnline;


public interface EmployeeOnlineDao {

	/**
	 * 根据员工编号或pos终端编号  更新状态为离线
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @return
	 */
	public int updateStatusDownByEmpNoOrPosNo(String empNo, String posNo);
	/**
	 * 根据员工编号  更新状态为离线
	 * @param empNo 员工编号
	 * @return
	 */
	public int updateStatusDownByEmpNo(String empNo);
	/**
	 * 查询员工在线状态
	 * @param empNo 员工编号
	 * @param posNo 终端编号
	 * @return
	 */
	public EmployeeOnline selectEmployeeOnlineByEmpNoAndPosNo(String empNo, String posNo);

	/**
	 * 查询员工在线状态
	 * @param empNo 员工编号
	 * @param posNo 终端编号
	 * @param token 
	 * @return
	 */
	public EmployeeOnline selectEmployeeOnlineByTokenAndRelevantNo(
            String empNo, String posNo, String token);
	/**
	 * 员工签到时 增加员工在线状态 
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @param ip 终端ip
	 * @param token 
	 * @return
	 */
	public int addEmployeeOnlineForLogin(String empNo, String posNo, String ip, String token);
	
	/**
	 * 员工签到时 更新员工在线状态  ，并且已经有历史登录记录
	 * @param id 历史登录记录id
	 * @param ip
	 * @param token
	 * @return
	 */
	public int updateEmployeeOnlineForLogin(Integer id, String ip, String token);
	/**
	 * 员工签退时 更新员工在线状态
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @return
	 */
	public int updateStatusDownForLogout(String empNo, String posNo);
	
	/**
	 * 更新所有在线员工为离线
	 * @return
	 */
	public int updateStatusDownByAll();
	
	/**
	 * 根据id更新相关记录为离线
	 * @param id
	 * @return
	 */
	public int updateStatusDownById(Integer id);
	
	/**
	 * 根据状态获取在线记录
	 * @param status 在线状态
	 * @return
	 */
	public List<EmployeeOnline> selectEmployeeOnlineByStatus(Integer status);
}
