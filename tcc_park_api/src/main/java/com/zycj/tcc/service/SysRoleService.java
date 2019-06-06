/**
 * 
 */
package com.zycj.tcc.service;

import java.util.List;

import com.zycj.tcc.domain.criteria.SysRoleCriteria;
import com.zycj.tcc.domain.vo.SysRoleVo;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.exception.TccException;

/**
 * 角色相关
* Title: SysRoleService.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月3日
 */
public interface SysRoleService {
	
	/**
	 * 按条件查询 平台操作员
	 * @param admin
	 * @param criteria
	 * @return
	 */
	List<SysRoleVo> list(SysAdminVo currAdmin,SysRoleCriteria criteria);
	/**
	 * 按id 查询 role 及role拥有的权限
	 */
	
	/**
	 * 角色名，唯一性校验
	 * @param loginName id
	 * @return fales:不通过 true:通过
	 */
	boolean validateRoleName(String roleName,Integer id);
	
	/**
	 * 新增角色
	 * @param currAdmin
	 * @param role
	 * @throws TccException
	 * @throws Exception
	 */
	void add(SysAdminVo currAdmin,SysRoleVo role)throws TccException ,Exception;
	/**
	 * 根据id，查询role及其分配的权限
	 * @param currAdmin
	 * @param id
	 * @return
	 * @throws TccException
	 * @throws Exception
	 */
	SysRoleVo getSysRoleById(SysAdminVo currAdmin,Integer id)throws TccException ,Exception;
	
	/**
	 * 修改role
	 * @param currAdmin
	 * @param role
	 * @throws TccException
	 * @throws Exception
	 */
	void edit(SysAdminVo currAdmin,SysRoleVo role)throws TccException ,Exception;
	/**
	 * 禁用角色
	 * @param currAdmin
	 * @param id
	 * @throws TccException
	 * @throws Exception
	 */
	void close(SysAdminVo currAdmin,Integer id)throws TccException ,Exception;
	/**
	 * 启用操作员
	 * @param currAdmin
	 * @param id
	 * @throws TccException
	 * @throws Exception
	 */
	void open(SysAdminVo currAdmin,Integer id)throws TccException ,Exception;
}
