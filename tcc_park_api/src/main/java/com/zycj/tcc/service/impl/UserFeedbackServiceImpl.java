/**
 * 
 */
package com.zycj.tcc.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.dao.UserFeedbackMapper;
import com.zycj.tcc.domain.criteria.UserFeedbackCriteria;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.domain.vo.UserFeedbackVo;
import com.zycj.tcc.service.UserFeedbackService;

/**
 * Title: UserFeedbackServiceImpl.java
 * Description: 城泊二期
 * Company: zycj
 * @author wangy
 * @date 2015年4月10日
 */
@Service("UserFeedbackService")
public class UserFeedbackServiceImpl implements UserFeedbackService {
	private static final Logger logger = LoggerFactory
			.getLogger(SysAdminServiceImpl.class);
	@Autowired
	private UserFeedbackMapper userFbMapper;
	@Override
	public List<UserFeedbackVo> list(SysAdminVo currAdmin,
			UserFeedbackCriteria criteria) {
		logger.info("UserFeedbackService list" + criteria);
		// 获取 总记录数
		int count = userFbMapper.count(criteria);
		criteria.setCount(count);
		if (count > 0) {
			criteria.setCurrentPage(criteria.getPage());
			return userFbMapper.list(criteria);
		}
		return null;
	}

}
