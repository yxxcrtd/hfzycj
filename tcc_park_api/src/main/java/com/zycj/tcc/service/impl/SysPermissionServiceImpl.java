/**
 * 
 */
package com.zycj.tcc.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.dao.SysPermissionMapper;
import com.zycj.tcc.domain.SysPermission;
import com.zycj.tcc.domain.SysPermissionExample;
import com.zycj.tcc.domain.vo.SysPermissionVo;
import com.zycj.tcc.service.SysPermissionService;

/**
 * Title: SysPermissionServiceImpl.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年3月26日
 */
@Service("SysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {
	private static final Logger logger = LoggerFactory
			.getLogger(SysPermissionServiceImpl.class);
	@Autowired
	private SysPermissionMapper spMapper;

	@Override
	public boolean hasPermission(Integer id, String servletPath) {
		logger.info("SysPermissionService hasPermission:" + id + servletPath);
		String permissionsStr = spMapper.getOwnPermissions(id);
		// 截取servlet path
		String path = servletPath.split("/")[1];
		logger.info("截取servlet path:" + path);
		logger.debug("permissionsStr:" + permissionsStr);
		if (null != permissionsStr) {
			for (String p : permissionsStr.split(",")) {
				if (p.toLowerCase().indexOf(path) != -1) {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	@Override
	public Map<SysPermissionVo, List<SysPermissionVo>> getPermissionsByAdminId(
			Integer id) {
		logger.info("SysPermissionService getPermissionsByAdminId:" + id);
		// 根据操作员id,获取他拥有的所有菜单
		List<SysPermissionVo> permissions = spMapper
				.getPermissionsByAdminId(id);
		if (null == permissions || permissions.size() == 0||permissions.get(0)==null ){
			return null;
		} else {
			// 获取有序的map集合
			Map<SysPermissionVo, List<SysPermissionVo>> map = new LinkedHashMap<SysPermissionVo, List<SysPermissionVo>>();
			List<SysPermissionVo> listTemp = null;
			for (SysPermissionVo p : permissions) {
				if (p.getPerType() == Constants.SUPER_MENU) {
					listTemp = new ArrayList<SysPermissionVo>();
					for (SysPermissionVo p2 : permissions) {
						if (p2.getPerType() == Constants.SUPER_MENU) {
							continue;
						} else if (p2.getParentId()==p.getId()+0) {
							//获取 以及菜单p 的 子菜单p2
							listTemp.add(p2);
						} else {
							continue;
						}
					}
					map.put(p, listTemp);
				} else {
					continue;
				}
			}
			logger.info("map:" + map);
			return map;
		}
	}

	@Override
	public List<SysPermission> getAllPermission() {
		logger.info("SysPermissionService getAllPermission");
		return spMapper.selectByExample(new SysPermissionExample());
	}

	@Override
	public Map<SysPermission, List<SysPermission>> getAllSortPermission() {
		logger.info("SysPermissionService getAllSortPermission:");
		List<SysPermission> permissions = getAllPermission();
		if (null == permissions || permissions.size() == 0||permissions.get(0)==null ){
			return null;
		} else {
			// 获取有序的map集合
			Map<SysPermission, List<SysPermission>> map = new LinkedHashMap<SysPermission, List<SysPermission>>();
			List<SysPermission> listTemp = null;
			for (SysPermission p : permissions) {
				if (p.getPerType() == Constants.SUPER_MENU) {
					listTemp = new ArrayList<SysPermission>();
					for (SysPermission p2 : permissions) {
						if (p2.getPerType() == Constants.SUPER_MENU) {
							continue;
						} else if (p2.getParentId()==p.getId()+0) {
							//获取 以及菜单p 的 子菜单p2
							listTemp.add(p2);
						} else {
							continue;
						}
					}
					map.put(p, listTemp);
				} else {
					continue;
				}
			}
			logger.info("map:" + map);
			return map;
		}
	}

}
