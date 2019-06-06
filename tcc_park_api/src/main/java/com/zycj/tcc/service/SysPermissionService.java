/**
 * 
 */
package com.zycj.tcc.service;

import java.util.List;
import java.util.Map;

import com.zycj.tcc.domain.SysPermission;
import com.zycj.tcc.domain.vo.SysPermissionVo;

/**
 * 权限相关service处理
 * Title: SysPermissionService.java
 * Description: 城泊二期
 * Company: zycj
 * @author wangy
 * @date 2015年3月26日
 */
public interface SysPermissionService {
	/**
	 * 判断登录操作员是否拥有，操作权限
	 * @param id
	 * @return false:无权限，true:有权限
	 */
	boolean hasPermission(Integer id,String servletPath);
	/**
	 * 按资源id排序，key:父资源，value:子级资源
	 * @param id
	 * @return
	 */
	Map<SysPermissionVo,List<SysPermissionVo>> getPermissionsByAdminId(Integer id);
	/**
	 * 获取所有permission list
	 */
	List<SysPermission> getAllPermission();
	
	/**
	 * key:父资源，value:子级资源
	 */
	Map<SysPermission,List<SysPermission>> getAllSortPermission();
}
