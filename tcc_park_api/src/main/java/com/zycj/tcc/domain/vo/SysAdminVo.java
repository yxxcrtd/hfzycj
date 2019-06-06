/**
 * 
 */
package com.zycj.tcc.domain.vo;

import java.text.SimpleDateFormat;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.domain.SysAdmin;

/**
 * Title: SysAdminVo.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年3月26日
 */
public class SysAdminVo extends SysAdmin {
	/**
	 * 所分配角色的字符串形式，roleId1,...roleId2
	 */
	private String roleIdsStr;
	/**
	 * 管理拥有的角色id数组
	 */
	private Integer[] roleIds;
	/**
	 * 登录平台时，记录它的ip
	 */
	private String ip;
	public String getLastTimeVo() {
		return getLastTime() == null ? null : (new SimpleDateFormat(
				Constants.DEFAULT_TIME_FORMATE)).format(getLastTime());
	}
	
	public Integer[] getRoleIds() {
		if(null!=roleIdsStr){
			String[] roleIdsStrArr = roleIdsStr.split(",");
			Integer roleIdArr[] = new Integer[roleIdsStrArr.length];
			for(int i=0;i<roleIdsStrArr.length;i++){
				roleIdArr[i]=Integer.valueOf(roleIdsStrArr[i]);
			}
			return roleIdArr;
		}
		return roleIds;
	}
	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}
	public String getRoleIdsStr() {
		return roleIdsStr;
	}
	public void setRoleIdsStr(String roleIdsStr) {
		this.roleIdsStr = roleIdsStr;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
