/**
 * 
 */
package com.zycj.tcc.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.dao.UserLoginMapper;
import com.zycj.tcc.dao.UserMapper;
import com.zycj.tcc.domain.User;
import com.zycj.tcc.domain.UserLogin;
import com.zycj.tcc.domain.UserLoginExample;
import com.zycj.tcc.domain.criteria.UserCriteria;
import com.zycj.tcc.domain.vo.LogSystemVo;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.domain.vo.UserVo;
import com.zycj.tcc.exception.TccException;
import com.zycj.tcc.service.LogService;
import com.zycj.tcc.service.UserService;

/**
 * Title: UserServiceImpl.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年4月10日
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory
			.getLogger(SysAdminServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserLoginMapper uLoginMapper;
	@Autowired
	private LogService logService;

	@Override
	public List<UserVo> list(SysAdminVo currAdmin, UserCriteria criteria) {
		logger.info("user list" + criteria);
		// 获取 总记录数
		int count = userMapper.count(criteria);
		criteria.setCount(count);
		if (count > 0) {
			criteria.setCurrentPage(criteria.getPage());
			return userMapper.list(criteria);
		}
		return null;
	}

	@Override
	public void active(SysAdminVo currAdmin, Integer id) throws TccException,
			Exception {
		if (null == id) {
			throw new TccException("会员数据不完整，操作失败");
		}
		User user = userMapper.selectByPrimaryKey(id);
		if (null == user) {
			throw new TccException("会员信息不存在，操作失败");
		}
		// 判断会员状态
		if (Constants.IS_NORMAL == user.getStatus()) {
			throw new TccException("会员已经处于启用状态，操作失败");
		}
		// 设置 会员状态
		user.setStatus(Constants.IS_NORMAL);
		user.setModifyTime(new Date());
		int rows = userMapper.updateByPrimaryKeySelective(user);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.UPDATE_STATUS);
		StringBuffer valueBeforeBuff = new StringBuffer();
		valueBeforeBuff.append("姓名：").append(StringUtils.isBlank(user.getName())?"":user.getName()).append("，手机号：")
				.append(user.getMobile()).append("，状态：禁用");
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("姓名：").append(StringUtils.isBlank(user.getName())?"":user.getName()).append("，手机号：")
				.append(user.getMobile()).append("，状态：启用");
		log.setValueBefore(valueBeforeBuff.toString());
		log.setValueAfter(valueAfterBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}
	}

	@Override
	@Transactional
	public void close(SysAdminVo currAdmin, Integer id) throws TccException,
			Exception {
		if (null == id) {
			throw new TccException("会员数据不完整，操作失败");
		}
		User user = userMapper.selectByPrimaryKey(id);
		if (null == user) {
			throw new TccException("会员信息不存在，操作失败");
		}
		// 判断会员状态
		if (Constants.IS_CLOSED == user.getStatus()) {
			throw new TccException("会员已经处于禁用状态，操作失败");
		}
		// 设置 会员状态
		Date date = new Date();
		user.setStatus(Constants.IS_CLOSED);
		user.setModifyTime(date);
		int rows = userMapper.updateByPrimaryKeySelective(user);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 禁用的会员，更新 会员的登陆状态
		UserLogin userLogin = new UserLogin();
		userLogin.setActiveTime(date);
		userLogin.setModifyTime(date);
		userLogin.setIsActive(Constants.IS_CLOSED);
		UserLoginExample exp = new UserLoginExample();
		exp.or().andUserIdEqualTo(user.getId())
				.andMobileEqualTo(user.getMobile());
		uLoginMapper.updateByExampleSelective(userLogin, exp);

		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.UPDATE_STATUS);
		StringBuffer valueBeforeBuff = new StringBuffer();
		valueBeforeBuff.append("姓名：").append(StringUtils.isBlank(user.getName())?"":user.getName()).append("，手机号：")
				.append(user.getMobile()).append("，状态：启用");
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("姓名：").append(StringUtils.isBlank(user.getName())?"":user.getName()).append("，手机号：")
				.append(user.getMobile()).append("，状态：禁用");
		log.setValueBefore(valueBeforeBuff.toString());
		log.setValueAfter(valueAfterBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}
	}

}
