/**
 * 
 */
package com.zycj.tcc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.Result;
import com.zycj.tcc.domain.SysPermission;
import com.zycj.tcc.domain.criteria.SysRoleCriteria;
import com.zycj.tcc.domain.vo.SysRoleVo;
import com.zycj.tcc.exception.TccException;
import com.zycj.tcc.service.SysAdminService;
import com.zycj.tcc.service.SysPermissionService;
import com.zycj.tcc.service.SysRoleService;

/**
 * 角色相关
* Title: SysRoleController.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月11日
 */
@Controller
@RequestMapping("role")
public class SysRoleController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(SysRoleController.class);
	@Autowired
	private SysAdminService saService;
	@Autowired
	private SysRoleService srService;
	@Autowired
	private SysPermissionService spService;

	@RequestMapping("/list.htm")
	public ModelAndView list(HttpSession session, SysRoleCriteria criteria) {
		logger.info("SysRoleController list");
		List<SysRoleVo> list = null;
		if (null != criteria) {
			list = srService.list(getCurrentAdmin(session), criteria);
		} else {
			list = srService.list(getCurrentAdmin(session),
					new SysRoleCriteria());
		}
		Map<String, Object> model = getModel();
		model.put("list", list);
		model.put("criteria", criteria);
		return new ModelAndView("role/list", "model", model);
	}

	@RequestMapping("/toAdd.htm")
	public ModelAndView toAdd() {
		logger.info("SysRoleController toAdd");
		Map<String, Object> model = getModel();
		Map<SysPermission, List<SysPermission>> allPer = spService.getAllSortPermission();
		model.put("allPer", allPer);
		return new ModelAndView("role/add","model",model);
	}

	@RequestMapping("/add.htm")
	public ModelAndView add(SysRoleVo role ,HttpSession session)  {
		logger.info("SysRoleController add:");
		Map<String, Object> model = null;
		Result result = null;
		try {
			srService.add(getCurrentAdmin(session), role);
		} catch (TccException e) {
			Map<SysPermission, List<SysPermission>> allPer = spService.getAllSortPermission();
			model = getModel();
			result = getResult();
			doException(model, result, e);
			model.put("role", role);
			model.put("allPer", allPer);
			return new ModelAndView("role/add","model",model);
		} catch (Exception e) {
			Map<SysPermission, List<SysPermission>> allPer = spService.getAllSortPermission();
			model = getModel();
			result = getResult();
			doException(model, result, e);
			model.put("role", role);
			model.put("allPer", allPer);
			return new ModelAndView("role/add","model",model);
		}
		return new ModelAndView("redirect:list.htm");
	}

	@RequestMapping("/toEdit.htm")
	public ModelAndView toEdit(HttpSession session,Integer id) throws TccException, Exception {
		logger.info("SysRoleController toEdit");
		Map<String, Object> model = getModel();
		//获取所有权限信息
		Map<SysPermission, List<SysPermission>> allPer = spService.getAllSortPermission();
		SysRoleVo role = srService.getSysRoleById(getCurrentAdmin(session), id);
		model.put("role", role);
		model.put("allPer", allPer);
		return new ModelAndView("role/edit","model",model);
	}

	@RequestMapping("/edit.htm")
	public ModelAndView edit(SysRoleVo role ,HttpSession session)  {
		logger.info("SysRoleController edit");
		Map<String, Object> model = null;
		Result result = null;
		try {
			srService.edit(getCurrentAdmin(session), role);
		} catch (TccException e) {
			Map<SysPermission, List<SysPermission>> allPer = spService.getAllSortPermission();
			model = getModel();
			result = getResult();
			doException(model, result, e);
			model.put("role", role);
			model.put("allPer", allPer);
			return new ModelAndView("role/edit","model",model);
		} catch (Exception e) {
			Map<SysPermission, List<SysPermission>> allPer = spService.getAllSortPermission();
			model = getModel();
			result = getResult();
			doException(model, result, e);
			model.put("role", role);
			model.put("allPer", allPer);
			return new ModelAndView("role/edit","model",model);
		}
		return new ModelAndView("redirect:list.htm");
	}
	@ResponseBody
	@RequestMapping("/validateRoleName.htm")
	public boolean validateRoleName(String roleNameStr,Integer id){
		logger.info("SysRoleController validateRoleName:");
		return srService.validateRoleName(roleNameStr, id);
	}
	@ResponseBody
	@RequestMapping("/open.htm")
	public Result open(HttpSession session,Integer id){
		Result result = getResult();
		try {
			srService.open(getCurrentAdmin(session), id);
			
		} catch (TccException e) {
			result.setCode(Constants.FAIL_RESULT);
			result.setExceptionClassName(e.getClass().toString());
			result.setExceptionMsg(e.getMessage());
			result.setMsg(e.getMessage());
		} catch (Exception e) {
			result.setCode(Constants.FAIL_RESULT);
			result.setExceptionClassName(e.getClass().toString());
			result.setExceptionMsg(e.getMessage());
			result.setMsg(e.getMessage());
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/close.htm")
	public Result close(HttpSession session,Integer id){
		Result result = getResult();
		try {
			srService.close(getCurrentAdmin(session), id);
			
		} catch (TccException e) {
			result.setCode(Constants.FAIL_RESULT);
			result.setExceptionClassName(e.getClass().toString());
			result.setExceptionMsg(e.getMessage());
			result.setMsg(e.getMessage());
		} catch (Exception e) {
			result.setCode(Constants.FAIL_RESULT);
			result.setExceptionClassName(e.getClass().toString());
			result.setExceptionMsg(e.getMessage());
			result.setMsg(e.getMessage());
		}
		return result;
	}
}
