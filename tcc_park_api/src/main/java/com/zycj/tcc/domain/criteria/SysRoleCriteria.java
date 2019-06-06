/**
 * 
 */
package com.zycj.tcc.domain.criteria;

import org.apache.commons.lang.StringUtils;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.PageModel;

/**
 * 
* Title: SysRoleCriteria.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月3日
 */
public class SysRoleCriteria extends PageModel {
	/**
	 * 是否要展示初始化时，超级管理员角色
	 */
	private Boolean isViewInitRole = Constants.IS_NOT_SHOW;
	/**
	 * 角色名
	 */
	private String roleName;
	/**
	 * 状态
	 */
	private Integer roleStatus;


	public Integer getRoleStatus() {
		return null==roleStatus?null:(roleStatus == Constants.UN_SELECT ? null : roleStatus);
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public String getRoleName() {
		return StringUtils.isBlank(roleName) ? null : roleName.trim();
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getIsViewInitRole() {
		return isViewInitRole;
	}

	public void setIsViewInitRole(Boolean isViewInitRole) {
		this.isViewInitRole = isViewInitRole;
	}

}
