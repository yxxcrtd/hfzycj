/**
 * 
 */
package com.zycj.tcc.service;

import java.util.List;

import com.zycj.tcc.domain.criteria.SysAdminCriteria;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.exception.TccException;

/**
 * 平台管理员相关service
 * Title: SysPermissionService.java
 * Description: 城泊二期
 * Company: zycj
 * @author wangy
 * @date 2015年3月26日
 */
public interface SysAdminService {
	/**
	 * 判断登录信息是否有效
	 * @param loginname
	 * @param password
	 * @return null:表示无效的身份信息;
	 */
	SysAdminVo validateLoginInfo(String loginName,String password);
	/**
	 * 更新平台管理员登录信息
	 * @param admin
	 */
	void updateAdminLoginRecord(SysAdminVo admin);
	
	/**
	 * 按条件查询 平台操作员
	 * @param admin
	 * @param criteria
	 * @return
	 */
	List<SysAdminVo> list(SysAdminVo currAdmin,SysAdminCriteria criteria);
	
	/**
	 * 操作员登录名，唯一性校验
	 * @param loginName id
	 * @return fales:不通过 true:通过
	 */
	boolean validateLoginName(String loginName,Integer id);
	
	/**
	 * 新增管理员
	 * @param currAdmin
	 * @param admin
	 * @throws TccException
	 * @throws Exception
	 */
	void add(SysAdminVo currAdmin,SysAdminVo admin)throws TccException ,Exception;
	/**
	 * 根据admin id 获取操作员信息
	 * @param currAdmin
	 * @param id
	 * @return
	 */
	SysAdminVo getSysAdminById(SysAdminVo currAdmin,Integer id)throws TccException ,Exception;
	
	/**
	 * 修改管理员
	 * @param currAdmin
	 * @param admin
	 * @throws TccException
	 * @throws Exception
	 */
	void edit(SysAdminVo currAdmin,SysAdminVo admin)throws TccException ,Exception;
	/**
	 * 重置操作员密码
	 * @param currAdmin
	 * @param id
	 * @throws TccException
	 * @throws Exception
	 */
	void resetPwd(SysAdminVo currAdmin,Integer id)throws TccException ,Exception;
	/**
	 * 禁用操作员
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
	/**
	 * 更新当前操作员信息
	 * @param currAdmin
	 * @param admin
	 * @throws TccException
	 * @throws Exception
	 */
	void editCurrentAdmin(SysAdminVo currAdmin,SysAdminVo admin)throws TccException ,Exception;
	
	/**
	 * 修改 操作员密码
	 * @param currAdmin
	 * @param id
	 * @param oldPwd
	 * @param newPwd
	 * @throws TccException
	 * @throws Exception
	 */
	void modifyPwd(SysAdminVo currAdmin,Integer id ,String oldPwd,String newPwd)throws TccException ,Exception;
}
