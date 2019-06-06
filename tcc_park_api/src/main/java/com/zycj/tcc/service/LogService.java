/**
 * 
 */
package com.zycj.tcc.service;


import java.util.List;

import com.zycj.tcc.domain.criteria.LogSystemCriteria;
import com.zycj.tcc.domain.vo.LogSystemVo;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.exception.TccException;

/**
 * 记录操作日志相关
* Title: LogService.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月9日
 */
public interface LogService {
	
	/**
	 * 新增操作日志
	 * @param admin
	 * @param criteria
	 * @return
	 */
	void add(SysAdminVo currAdmin,LogSystemVo log) throws TccException,Exception;
	
	/**
	 * 按条件查询 咨询
	 * @param LogSystem
	 * @param criteria
	 * @return
	 */
	List<LogSystemVo> list(SysAdminVo currAdmin,LogSystemCriteria criteria);
	
}
