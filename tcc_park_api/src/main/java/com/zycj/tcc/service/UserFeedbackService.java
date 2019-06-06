/**
 * 
 */
package com.zycj.tcc.service;

import java.util.List;

import com.zycj.tcc.domain.criteria.UserFeedbackCriteria;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.domain.vo.UserFeedbackVo;

/**
 * 会员反馈相关
* Title: UserFeedbackService.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月10日
 */
public interface UserFeedbackService {
	
	/**
	 * 按条件查询 咨询
	 * @param admin
	 * @param criteria
	 * @return
	 */
	List<UserFeedbackVo> list(SysAdminVo currAdmin,UserFeedbackCriteria criteria);
	
}