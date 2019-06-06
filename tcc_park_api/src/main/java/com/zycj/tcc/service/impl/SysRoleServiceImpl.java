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
import com.zycj.tcc.dao.SysAdminRoleMapper;
import com.zycj.tcc.dao.SysPermissionMapper;
import com.zycj.tcc.dao.SysRoleMapper;
import com.zycj.tcc.dao.SysRolePermissionMapper;
import com.zycj.tcc.domain.SysAdminRole;
import com.zycj.tcc.domain.SysAdminRoleExample;
import com.zycj.tcc.domain.SysPermission;
import com.zycj.tcc.domain.SysPermissionExample;
import com.zycj.tcc.domain.SysRole;
import com.zycj.tcc.domain.SysRoleExample;
import com.zycj.tcc.domain.SysRolePermission;
import com.zycj.tcc.domain.criteria.SysRoleCriteria;
import com.zycj.tcc.domain.vo.LogSystemVo;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.domain.vo.SysRoleVo;
import com.zycj.tcc.exception.TccException;
import com.zycj.tcc.service.LogService;
import com.zycj.tcc.service.SysRoleService;

/**
 * Title: SysRoleServiceImpl.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年4月3日
 */
@Service("SysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	private static final Logger logger = LoggerFactory
			.getLogger(SysAdminServiceImpl.class);
	@Autowired
	private SysRoleMapper srMapper;
	@Autowired
	private SysPermissionMapper spMapper;
	@Autowired
	private SysRolePermissionMapper srpMapper;
	@Autowired
	private SysAdminRoleMapper sarMapper;
	@Autowired
	private LogService logService;

	@Override
	public List<SysRoleVo> list(SysAdminVo currAdmin, SysRoleCriteria criteria) {
		logger.info("SysRoleService list" + criteria);
		// 获取 总记录数
		int count = srMapper.count(criteria);
		criteria.setCount(count);
		if (count > 0) {
			criteria.setCurrentPage(criteria.getPage());
			return srMapper.list(criteria);
		}
		return null;
	}

	@Override
	public boolean validateRoleName(String roleName, Integer id) {
		logger.info("SysRoleService validateRoleName" + roleName + id);
		SysRoleExample exp = new SysRoleExample();
		exp.or().andRoleNameEqualTo(roleName);
		List<SysRole> roles = srMapper.selectByExample(exp);
		if (null == id) {
			if (null != roles && roles.size() > 0) {
				return false;
			} else {
				return true;
			}
		} else {
			if ((null != roles && roles.size() > 1)
					|| (null != roles && roles.size() == 1 && !roles.get(0)
							.getId().equals(id))) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public void add(SysAdminVo currAdmin, SysRoleVo role) throws TccException,
			Exception {
		logger.info("SysRoleService add " + role);
		if (null == role) {
			throw new TccException("角色数据不完整，操作失败");
		}
		// 基本字段菲空校验
		validateParam(role);
		// 角色名唯一性校验
		if (!validateRoleName(role.getRoleName(), null)) {
			throw new TccException("角色名已经存在，操作失败");
		}
		Date date = new Date();
		// 插入角色数据
		role.setCreateTime(date);
		role.setRoleStatus(Constants.IS_NORMAL);
		int rows = srMapper.insertWithId(role);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 所有角色，均分配父级菜单
		SysRolePermission srp = new SysRolePermission();
		SysPermissionExample spExp = new SysPermissionExample();
		spExp.or().andPerTypeEqualTo(Constants.SUPER_MENU);
		List<SysPermission> superMenuList = spMapper.selectByExample(spExp);
		for (SysPermission p : superMenuList) {
			srp.setPerId(p.getId());
			srp.setRoleId(role.getId());
			srpMapper.insert(srp);
		}
		// 子级菜单
		for (Integer pId : role.getPerIds()) {
			srp.setPerId(pId);
			srp.setRoleId(role.getId());
			srpMapper.insert(srp);
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.ADD);
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("角色名：").append(role.getRoleName())
				.append("，角色描述：").append(role.getRoleDescription());
		log.setValueAfter(valueAfterBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}

	}

	@Override
	public SysRoleVo getSysRoleById(SysAdminVo currAdmin, Integer id)
			throws TccException, Exception {
		logger.info("SysRoleService getSysRoleById" + currAdmin + id);
		return srMapper.getSysRoleById(id);
	}

	@Override
	@Transactional
	public void edit(SysAdminVo currAdmin, SysRoleVo role) throws TccException,
			Exception {
		logger.info("SysRoleService edit " + role);
		if (null == role || null == role.getId()) {
			throw new TccException("角色数据不完整，操作失败");
		}
		// 基本字段菲空校验
		validateParam(role);
		SysRoleVo oldRole = srMapper.getSysRoleById(role.getId());
		if (null == oldRole) {
			throw new TccException("角色数据不存在，操作失败");
		}
		// 角色名唯一性校验
		if (!validateRoleName(role.getRoleName(), role.getId())) {
			throw new TccException("角色名已经存在，操作失败");
		}
		Date date = new Date();
		// 更新角色表中，角色数据
		role.setUpdateTime(date);
		srMapper.updateByPrimaryKeySelective(role);
		// 无论角色分配的权限是否变化，均先删除，再重新添加
		srpMapper.deleteByRoleId(role.getId());
		// 重新分配角色权限
		// 所有角色，均分配父级菜单
		SysRolePermission srp = new SysRolePermission();
		SysPermissionExample spExp = new SysPermissionExample();
		spExp.or().andPerTypeEqualTo(Constants.SUPER_MENU);
		List<SysPermission> superMenuList = spMapper.selectByExample(spExp);
		for (SysPermission p : superMenuList) {
			srp.setPerId(p.getId());
			srp.setRoleId(role.getId());
			srpMapper.insert(srp);
		}
		// 子级菜单
		for (Integer pId : role.getPerIds()) {
			srp.setPerId(pId);
			srp.setRoleId(role.getId());
			srpMapper.insert(srp);
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.EDIT);
		StringBuffer valueBeforeBuff = new StringBuffer();
		valueBeforeBuff.append("角色名：").append(oldRole.getRoleName())
				.append("，角色描述：").append(oldRole.getRoleDescription());
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("角色名：").append(role.getRoleName())
				.append("，角色描述：").append(role.getRoleDescription());
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
			throw new TccException("角色数据不完整，操作失败");
		}
		SysRole role = srMapper.selectByPrimaryKey(id);
		if (null == role) {
			throw new TccException("角色信息不存在，操作失败");
		}
		if (Constants.IS_CLOSED == role.getRoleStatus()) {
			throw new TccException("角色已经处于禁用状态，操作失败");
		}
		// 判断角色是否允许禁用，当有操作员拥有该角色时，不允许禁用该角色
		SysAdminRoleExample exp = new SysAdminRoleExample();
		exp.or().andRoleIdEqualTo(id);
		List<SysAdminRole> sarList = sarMapper.selectByExample(exp);
		if (null != sarList && sarList.size() > 0) {
			throw new TccException("已经有操作员分配了该角色，不允许禁用，操作失败");
		}
		role.setRoleStatus(Constants.IS_CLOSED);
		role.setUpdateTime(new Date());
		int rows = srMapper.updateByPrimaryKeySelective(role);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.UPDATE_STATUS);
		StringBuffer valueBeforeBuff = new StringBuffer();
		valueBeforeBuff.append("角色名：").append(role.getRoleName())
				.append("，角色描述：").append(role.getRoleDescription())
				.append("，状态：启用");
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("角色名：").append(role.getRoleName())
				.append("，角色描述：").append(role.getRoleDescription())
				.append("，状态：启用");
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
			throw new TccException("角色数据不完整，操作失败");
		}
		SysRole role = srMapper.selectByPrimaryKey(id);
		if (null == role) {
			throw new TccException("角色信息不存在，操作失败");
		}
		if (Constants.IS_NORMAL == role.getRoleStatus()) {
			throw new TccException("角色已经处于启用状态，操作失败");
		}
		role.setRoleStatus(Constants.IS_NORMAL);
		role.setUpdateTime(new Date());
		int rows = srMapper.updateByPrimaryKeySelective(role);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.UPDATE_STATUS);
		StringBuffer valueBeforeBuff = new StringBuffer();
		valueBeforeBuff.append("角色名：").append(role.getRoleName())
				.append("，角色描述：").append(role.getRoleDescription())
				.append("，状态：禁用");
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("角色名：").append(role.getRoleName())
				.append("，角色描述：").append(role.getRoleDescription())
				.append("，状态：启用");
		log.setValueBefore(valueBeforeBuff.toString());
		log.setValueAfter(valueAfterBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}
	}

	private void validateParam(SysRoleVo role) throws TccException {
		if (StringUtils.isBlank(role.getRoleName())) {
			throw new TccException("角色名不能为空，操作失败");
		}
		if (StringUtils.isBlank(role.getRoleDescription())) {
			throw new TccException("角色描述不能为空，操作失败");
		}
		if (null == role.getPerIds() || 0 == role.getPerIds().length) {
			throw new TccException("角色描述不能为空，操作失败");
		}
	}
}
