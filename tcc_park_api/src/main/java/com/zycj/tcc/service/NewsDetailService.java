/**
 * 
 */
package com.zycj.tcc.service;

import java.util.List;

import com.zycj.tcc.domain.criteria.NewsDetailCriteria;
import com.zycj.tcc.domain.vo.NewsDetailVo;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.exception.TccException;

/**
 * 资讯相关
* Title: NewsDetailService.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月9日
 */
public interface NewsDetailService {
	
	/**
	 * 按条件查询 咨询
	 * @param admin
	 * @param criteria
	 * @return
	 */
	List<NewsDetailVo> list(SysAdminVo currAdmin,NewsDetailCriteria criteria);
	
	
	/**
	 * 新增资讯
	 * @param currAdmin
	 * @param admin
	 * @throws TccException
	 * @throws Exception
	 */
	void add(SysAdminVo currAdmin,NewsDetailVo news)throws TccException ,Exception;
	
	/**
	 * 上线资讯
	 * @param currAdmin
	 * @param id
	 * @throws TccException
	 * @throws Exception
	 */
	void online(SysAdminVo currAdmin,Integer id)throws TccException ,Exception;
	/**
	 * 下线资讯
	 * @param currAdmin
	 * @param id
	 * @throws TccException
	 * @throws Exception
	 */
	void outline(SysAdminVo currAdmin,Integer id)throws TccException ,Exception;
	/**
	 * 删除资讯
	 * @param currAdmin
	 * @param id
	 * @throws TccException
	 * @throws Exception
	 */
	void delete(SysAdminVo currAdmin,Integer id)throws TccException ,Exception;
	
	/**
	 * 根据 id 获取生成 资讯详情静态页 的路径
	 */
	String getHtmlSrc(SysAdminVo currAdmin,Integer id)throws TccException ,Exception;
}
