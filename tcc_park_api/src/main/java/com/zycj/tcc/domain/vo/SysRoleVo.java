/**
 * 
 */
package com.zycj.tcc.domain.vo;

import java.util.List;

import com.zycj.tcc.domain.SysPermission;
import com.zycj.tcc.domain.SysRole;

/**
 * Title: SysAdminVo.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年3月26日
 */
public class SysRoleVo extends SysRole {
	
	/**
	 * 角色拥有权限字符串形式，perId1,...perId2
	 */
	private String perIdsStr;
	/**
	 * 角色拥有资源权限list
	 */
	private List<SysPermission> permissionList;
	/**
	 * 角色拥有的permission Id数组
	 */
	private Integer[] perIds;

	public List<SysPermission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<SysPermission> permissionList) {
		this.permissionList = permissionList;
	}

	public Integer[] getPerIds() {
		if(null!=perIdsStr){
			String[] perIdsStrArr = perIdsStr.split(",");
			Integer perIdArr[] = new Integer[perIdsStrArr.length];
			for(int i=0;i<perIdsStrArr.length;i++){
				perIdArr[i]=Integer.valueOf(perIdsStrArr[i]);
			}
			return perIdArr;
		}
		return perIds;
	}

	public void setPerIds(Integer[] perIds) {
		this.perIds = perIds;
	}
	
	
}
