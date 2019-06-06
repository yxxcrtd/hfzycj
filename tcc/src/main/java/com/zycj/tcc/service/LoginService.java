package com.zycj.tcc.service;

import com.zycj.tcc.server.vo.Response;

public interface LoginService {
	
	/**
	 * 员工 签到
	 * @param empNo 账号=员工编号
	 * @param loginPwd 密码
	 * @param posNo 终端编号
	 * @param ip 终端ip
	 * @return token
	 */
	public Response login(String empNo, String pwd, String posNo, String ip);
	
	/**
	 * 校验员工是否登录
	 * @param empNo 员工编号
	 * @param posNo 终端编号
	 * @param token
	 * @return true：已登录，false:未登陆
	 */
	public boolean checkLogin(String empNo, String posNo, String token);
	
	/**
	 * 接口重发时的校验
	 * @param empNo 员工编号
	 * @param posNo 终端编号
	 * @return true：正确，false:失败
	 */
	public boolean checkLogin(String empNo, String posNo);

	/**
	 * 签退查询
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @return
	 */
	public Response gotoLogout(String empNo, String posNo);

	/**
	 * 员工签退
	 * @param empNo 员工编号
	 * @param posNo 终端编号
	 * @param ip 
	 * @return
	 */
	public Response logout(String empNo, String posNo, String ip);
	
}
