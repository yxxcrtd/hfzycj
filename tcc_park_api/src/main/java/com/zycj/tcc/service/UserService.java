/**
 * 
 */
package com.zycj.tcc.service;

import java.util.List;

import com.zycj.tcc.domain.criteria.UserCriteria;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.domain.vo.UserVo;
import com.zycj.tcc.exception.TccException;

/**
 * 会员相关
* Title: UserService.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月10日
 */
public interface UserService {
	
	/**
	 * 按条件查询 咨询
	 * @param admin
	 * @param criteria
	 * @return
	 */
	List<UserVo> list(SysAdminVo currAdmin,UserCriteria criteria);
	
	
	
	/**
	 * 启用会员
	 * @param currAdmin
	 * @param id
	 * @throws TccException
	 * @throws Exception
	 */
	void active(SysAdminVo currAdmin,Integer id)throws TccException ,Exception;
	/**
	 * 禁用会员
	 * @param currAdmin
	 * @param id
	 * @throws TccException
	 * @throws Exception
	 */
	void close(SysAdminVo currAdmin,Integer id)throws TccException ,Exception;
}
