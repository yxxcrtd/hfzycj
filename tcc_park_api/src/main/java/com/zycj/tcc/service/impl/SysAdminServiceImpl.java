/**
 * 
 */
package com.zycj.tcc.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.MD5;
import com.zycj.tcc.dao.SysAdminMapper;
import com.zycj.tcc.dao.SysAdminRoleMapper;
import com.zycj.tcc.domain.SysAdmin;
import com.zycj.tcc.domain.SysAdminExample;
import com.zycj.tcc.domain.SysAdminRole;
import com.zycj.tcc.domain.criteria.SysAdminCriteria;
import com.zycj.tcc.domain.vo.LogSystemVo;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.exception.TccException;
import com.zycj.tcc.service.LogService;
import com.zycj.tcc.service.SysAdminService;

/**
 * Title: SysAdminServiceImpl.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年3月26日
 */
@Service("SysAdminService")
public class SysAdminServiceImpl implements SysAdminService {
	private static final Logger logger = LoggerFactory
			.getLogger(SysAdminServiceImpl.class);
	@Autowired
	private SysAdminMapper saMapper;
	@Autowired
	private SysAdminRoleMapper sarMapper;
	@Autowired
	private LogService logService;

	@Override
	public SysAdminVo validateLoginInfo(String loginName, String password) {
		logger.info("validateLoginInfo:" + loginName + "--" + password);
		SysAdminVo vo = saMapper.getSysAdminByLoginName(loginName);
		// 登录信息不存在
		if (null == vo) {
			return null;
		} else if ((new MD5().getMD5ofStr(password)).equals(vo.getLoginPwd())) {
			// 校验密码是否正确
			// 暂不加/解密
			return vo;
		}
		return null;
	}

	@Override
	public void updateAdminLoginRecord(SysAdminVo admin) {
		// session中没有管理员信息
		Date curDate = new Date();
		// 获取上次的登陆时间，作为此次的最近一次登录时间
		Date lastLoginDate = admin.getLoginTime();
		// 获取登录次数
		int count = admin.getLoginTotal() == null ? 0 : admin.getLoginTotal();
		// 设置登录时间
		admin.setLoginTime(curDate);
		if (null != lastLoginDate) {
			admin.setLastTime(lastLoginDate);
		}
		admin.setLoginTotal(count + 1);
		admin.setUpdateTime(curDate);
		saMapper.updateByPrimaryKeySelective(admin);
	}

	@Override
	public List<SysAdminVo> list(SysAdminVo currAdmin, SysAdminCriteria criteria) {
		// 获取 总记录数
		int count = saMapper.count(criteria);
		criteria.setCount(count);
		if (count > 0) {
			criteria.setCurrentPage(criteria.getPage());
			return saMapper.list(criteria);
		}
		return null;
	}

	@Override
	public boolean validateLoginName(String loginName, Integer id) {
		SysAdminExample exp = new SysAdminExample();
		exp.or().andLoginNameEqualTo(loginName);
		List<SysAdmin> admins = saMapper.selectByExample(exp);
		if (null == id) {
			if (null != admins && admins.size() > 0) {
				return false;
			} else {
				return true;
			}
		} else {
			if ((null != admins && admins.size() > 1)
					|| (null != admins && admins.size() == 1 && !admins.get(0)
							.getId().equals(id))) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public void add(SysAdminVo currAdmin, SysAdminVo admin)
			throws TccException, Exception {
		logger.info("SysAdmin add:" + currAdmin + admin);
		// 校验数据非空
		if (null == admin) {
			throw new TccException("操作员数据不完整，操作失败");
		}
		// 基本参数校验
		validateParam(admin);
		// 登录名是否已经存在的校验
		if (!validateLoginName(admin.getLoginName(), null)) {
			throw new TccException("登录名已经存在，操作失败");
		}
		// 新增操作员
		// 设置操作员，登录密码
		Date date = new Date();
		admin.setLoginPwd(new MD5().getMD5ofStr(Constants.DEFAULT_PWD));
		admin.setLoginTotal(Constants.DEFAULT_LOGIN_TOTAL);
		admin.setCreateTime(date);
		admin.setStatus(Constants.IS_NORMAL);
		int rows = saMapper.insertWithId(admin);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 新增操作员-角色关联表数据
		SysAdminRole sar = null;
		for (int roleId : admin.getRoleIds()) {
			sar = new SysAdminRole();
			sar.setAdminId(admin.getId());
			sar.setRoleId(roleId);
			rows = sarMapper.insert(sar);
			if (Constants.ONE_RETURN_ROWS != rows) {
				throw new TccException("操作数据库出错，操作失败");
			}
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.ADD);
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("操作员名：").append(admin.getAdminName())
				.append("，登录名：").append(admin.getLoginName());
		if (StringUtils.isNotBlank(admin.getContact())) {
			valueAfterBuff.append("，手机号：").append(admin.getContact());
		}
		if (StringUtils.isNotBlank(admin.getCertificateNo())) {
			valueAfterBuff.append("，证件号码：").append(admin.getCertificateNo());
		}
		log.setValueAfter(valueAfterBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}
	}

	@Override
	public SysAdminVo getSysAdminById(SysAdminVo currAdmin, Integer id)
			throws TccException, Exception {
		logger.info("getSysAdminById" + currAdmin + id);
		return saMapper.getSysAdminById(id);
	}

	@Override
	@Transactional
	public void edit(SysAdminVo currAdmin, SysAdminVo admin)
			throws TccException, Exception {
		logger.info("SysAdmin edit:" + currAdmin + admin);
		if (null == admin || null == admin.getId()) {
			throw new TccException("操作员数据不完整，操作失败");
		}
		// 基本参数校验
		validateParam(admin);
		// 判断原数据是否存在
		SysAdminVo oldAdmin = saMapper.getSysAdminById(admin.getId());
		if (null == oldAdmin) {
			throw new TccException("操作员信息不存在，操作失败");
		}
		// 判断登录名，是否已经存在
		if (!validateLoginName(admin.getLoginName(), admin.getId())) {
			throw new TccException("登录名已经存在，操作失败");
		}
		// 判断角色是否发生变化,没有变化
		boolean roleIsNotChange = Arrays.toString(oldAdmin.getRoleIds())
				.equals(Arrays.toString(admin.getRoleIds()));

		// 发生变化时，删除原先的角色信息
		if (!roleIsNotChange) {
			sarMapper.deleteByAdminId(oldAdmin.getId());
		}
		// 更新操作员信息
		Date date = new Date();
		admin.setUpdateTime(date);
		int rows = saMapper.updateByPrimaryKeySelective(admin);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 角色发生变化时，重新插入amdin-role 数据
		if (!roleIsNotChange) {
			// 新增操作员-角色关联表数据
			SysAdminRole sar = null;
			for (int roleId : admin.getRoleIds()) {
				sar = new SysAdminRole();
				sar.setAdminId(admin.getId());
				sar.setRoleId(roleId);
				rows = sarMapper.insert(sar);
				if (Constants.ONE_RETURN_ROWS != rows) {
					throw new TccException("操作数据库出错，操作失败");
				}
			}
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.EDIT);
		StringBuffer valueBeforeBuff = new StringBuffer();
		valueBeforeBuff.append("操作员名：").append(oldAdmin.getAdminName())
				.append("，登录名：").append(oldAdmin.getLoginName());
		if (StringUtils.isNotBlank(oldAdmin.getContact())) {
			valueBeforeBuff.append("，手机号：").append(oldAdmin.getContact());
		}
		if (StringUtils.isNotBlank(oldAdmin.getCertificateNo())) {
			valueBeforeBuff.append("，证件号码：")
					.append(oldAdmin.getCertificateNo());
		}
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("操作员名：").append(admin.getAdminName())
				.append("，登录名：").append(admin.getLoginName());
		if (StringUtils.isNotBlank(admin.getContact())) {
			valueAfterBuff.append("，手机号：").append(admin.getContact());
		}
		if (StringUtils.isNotBlank(admin.getCertificateNo())) {
			valueAfterBuff.append("，证件号码：").append(admin.getCertificateNo());
		}
		log.setValueBefore(valueBeforeBuff.toString());
		log.setValueAfter(valueAfterBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}

	}

	private void validateParam(SysAdminVo admin) throws TccException, Exception {
		if (StringUtils.isBlank(admin.getLoginName())) {
			throw new TccException("登录名不能为空，操作失败");
		}
		if (StringUtils.isBlank(admin.getAdminName())) {
			throw new TccException("操作员名称不能为空，操作失败");
		}
		if (null == admin.getRoleIds() || admin.getRoleIds().length == 0) {
			throw new TccException("操作员未分配任何角色，操作失败");
		}
	}

	@Override
	@Transactional
	public void resetPwd(SysAdminVo currAdmin, Integer id) throws TccException,
			Exception {
		if (null == id) {
			throw new TccException("操作员数据不完整，操作失败");
		}
		SysAdmin admin = saMapper.selectByPrimaryKey(id);
		if (null == admin) {
			throw new TccException("操作员信息不存在，操作失败");
		}
		admin.setLoginPwd(new MD5().getMD5ofStr(Constants.DEFAULT_PWD));
		admin.setUpdateTime(new Date());
		int rows = saMapper.updateByPrimaryKeySelective(admin);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.RESET_PWD);
		StringBuffer valueBeforeBuff = new StringBuffer();
		valueBeforeBuff.append("操作员名：").append(admin.getAdminName())
				.append("，登录名：").append(admin.getLoginName()).append("，密码：***");
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("操作员名：").append(admin.getAdminName())
				.append("，登录名：").append(admin.getLoginName()).append("，密码：***");
		log.setValueBefore(valueBeforeBuff.toString());
		log.setValueAfter(valueAfterBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}

	}

	@Override
	public void close(SysAdminVo currAdmin, Integer id) throws TccException,
			Exception {
		if (null == id) {
			throw new TccException("操作员数据不完整，操作失败");
		}
		SysAdmin admin = saMapper.selectByPrimaryKey(id);
		if (null == admin) {
			throw new TccException("操作员信息不存在，操作失败");
		}
		if (Constants.IS_CLOSED == admin.getStatus()) {
			throw new TccException("操作员已经处于禁用状态，操作失败");
		}
		admin.setStatus(Constants.IS_CLOSED);
		admin.setUpdateTime(new Date());
		int rows = saMapper.updateByPrimaryKeySelective(admin);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.UPDATE_STATUS);
		StringBuffer valueBeforeBuff = new StringBuffer();
		valueBeforeBuff.append("操作员名：").append(admin.getAdminName())
				.append("，登录名：").append(admin.getLoginName()).append("，状态：启用");
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("操作员名：").append(admin.getAdminName())
				.append("，登录名：").append(admin.getLoginName()).append("，状态：禁用");
		log.setValueBefore(valueBeforeBuff.toString());
		log.setValueAfter(valueAfterBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}
	}

	@Override
	public void open(SysAdminVo currAdmin, Integer id) throws TccException,
			Exception {
		if (null == id) {
			throw new TccException("操作员数据不完整，操作失败");
		}
		SysAdmin admin = saMapper.selectByPrimaryKey(id);
		if (null == admin) {
			throw new TccException("操作员信息不存在，操作失败");
		}
		if (Constants.IS_NORMAL == admin.getStatus()) {
			throw new TccException("操作员已经处于启用状态，操作失败");
		}
		admin.setStatus(Constants.IS_NORMAL);
		admin.setUpdateTime(new Date());
		int rows = saMapper.updateByPrimaryKeySelective(admin);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.UPDATE_STATUS);
		StringBuffer valueBeforeBuff = new StringBuffer();
		valueBeforeBuff.append("操作员名：").append(admin.getAdminName())
				.append("，登录名：").append(admin.getLoginName()).append("，状态：禁用");
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("操作员名：").append(admin.getAdminName())
				.append("，登录名：").append(admin.getLoginName()).append("，状态：启用");
		log.setValueBefore(valueBeforeBuff.toString());
		log.setValueAfter(valueAfterBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}

	}

	@Override
	public void editCurrentAdmin(SysAdminVo currAdmin, SysAdminVo admin)
			throws TccException, Exception {
		logger.info("SysAdminService editCurrentAdmin" + admin);
		if (null == admin || null == currAdmin.getId()) {
			throw new TccException("当前操作员数据不完整，操作失败");
		}
		admin.setUpdateTime(new Date());
		int rows = saMapper.updateByPrimaryKeySelective(admin);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.EDIT);
		StringBuffer valueBeforeBuff = new StringBuffer();
		valueBeforeBuff.append("操作员名：").append(currAdmin.getAdminName())
				.append("，登录名：").append(currAdmin.getLoginName());
		if (StringUtils.isNotBlank(currAdmin.getContact())) {
			valueBeforeBuff.append("，手机号：").append(currAdmin.getContact());
		}
		if (StringUtils.isNotBlank(currAdmin.getCertificateNo())) {
			valueBeforeBuff.append("，证件号码：").append(
					currAdmin.getCertificateNo());
		}
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("操作员名：").append(admin.getAdminName())
				.append("，登录名：").append(admin.getLoginName());
		if (StringUtils.isNotBlank(admin.getContact())) {
			valueAfterBuff.append("，手机号：").append(admin.getContact());
		}
		if (StringUtils.isNotBlank(admin.getCertificateNo())) {
			valueAfterBuff.append("，证件号码：").append(admin.getCertificateNo());
		}
		log.setValueBefore(valueBeforeBuff.toString());
		log.setValueAfter(valueAfterBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}
	}

	@Override
	public void modifyPwd(SysAdminVo currAdmin, Integer id, String oldPwd,
			String newPwd) throws TccException, Exception {
		logger.info("SysAdminService modifyPwd" + id + oldPwd + newPwd);
		if (null == id) {
			throw new TccException("当前操作员数据不完整，操作失败");
		}
		if (StringUtils.isBlank(oldPwd)) {
			throw new TccException("当前操作员原密码不能为空，操作失败");
		}
		if (StringUtils.isBlank(newPwd)) {
			throw new TccException("当前操作员新密码不能为空，操作失败");
		}
		SysAdminExample exp = new SysAdminExample();
		exp.or().andIdEqualTo(id)
				.andLoginPwdEqualTo(new MD5().getMD5ofStr(oldPwd));
		List<SysAdmin> list = saMapper.selectByExample(exp);
		if (null == list || list.size() == 0) {
			throw new TccException("当前操作员原密码不正确，操作失败");
		}
		// 更新操作员密码
		SysAdmin admin = new SysAdmin();
		admin.setId(id);
		admin.setLoginPwd(new MD5().getMD5ofStr(newPwd));
		int rows = saMapper.updateByPrimaryKeySelective(admin);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.EDIT);
		StringBuffer valueBeforeBuff = new StringBuffer();
		valueBeforeBuff.append("操作员名：").append(currAdmin.getAdminName())
				.append("，登录名：").append(currAdmin.getLoginName())
				.append("，密码：***");
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("操作员名：").append(currAdmin.getAdminName())
				.append("，登录名：").append(currAdmin.getLoginName())
				.append("，密码：***");
		log.setValueBefore(valueBeforeBuff.toString());
		log.setValueAfter(valueAfterBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}

	}

}
