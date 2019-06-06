/**
 * 
 */
package com.zycj.tcc.domain.criteria;

import org.apache.commons.lang.StringUtils;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.PageModel;

/**
 * 平台管理员，查询条件 Title: SysAdminCriteria.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年3月26日
 */
public class SysAdminCriteria extends PageModel {
	/**
	 * 是否要展示初始化时，管理员
	 */
	private Boolean isViewInitAdmin =  Constants.IS_NOT_SHOW;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 管理员名
	 */
	private String adminName;
	/**
	 * 状态
	 */
	private Integer status;

	public String getLoginName() {
		return StringUtils.isBlank(loginName) ? null : loginName.trim();
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getAdminName() {
		return StringUtils.isBlank(adminName) ? null : adminName.trim();
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Integer getStatus() {
		return null==status?null:(status == Constants.UN_SELECT ? null : status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getIsViewInitAdmin() {
		return isViewInitAdmin;
	}

	public void setIsViewInitAdmin(Boolean isViewInitAdmin) {
		this.isViewInitAdmin = isViewInitAdmin;
	}

}
