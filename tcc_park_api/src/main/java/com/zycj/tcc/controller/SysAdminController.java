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
import com.zycj.tcc.domain.criteria.SysAdminCriteria;
import com.zycj.tcc.domain.criteria.SysRoleCriteria;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.domain.vo.SysRoleVo;
import com.zycj.tcc.exception.TccException;
import com.zycj.tcc.service.SysAdminService;
import com.zycj.tcc.service.SysRoleService;

/**
 * 平台管理员相关功能 Title: SysAdminController.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年3月30日
 */
@Controller
@RequestMapping("admin")
public class SysAdminController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(SysAdminController.class);
	@Autowired
	private SysAdminService saService;
	@Autowired
	private SysRoleService srService;

	@RequestMapping("/list.htm")
	public ModelAndView list(HttpSession session, SysAdminCriteria criteria) {
		logger.info("sysAdmin list");
		List<SysAdminVo> list = null;
		if (null != criteria) {
			list = saService.list(getCurrentAdmin(session), criteria);
		} else {
			list = saService.list(getCurrentAdmin(session),
					new SysAdminCriteria());
		}
		Map<String, Object> model = getModel();
		model.put("list", list);
		model.put("criteria", criteria);
		return new ModelAndView("admin/list", "model", model);
	}

	@RequestMapping("/toAdd.htm")
	public ModelAndView toAdd(HttpSession session) {
		logger.info("sysAdmin toAdd");
		Map<String, Object> model = getModel();
		SysRoleCriteria roleCriteria = new SysRoleCriteria();
		roleCriteria.setRoleStatus(Constants.IS_NORMAL);
		List<SysRoleVo> allRoleList = srService.list(getCurrentAdmin(session), roleCriteria);
		model.put("allRoleList", allRoleList);
		return new ModelAndView("admin/add","model",model);
	}

	@RequestMapping("/add.htm")
	public ModelAndView add(SysAdminVo admin ,HttpSession session)  {
		logger.info("sysAdmin add:");
		Map<String, Object> model = null;
		Result result = null;
		try {
			saService.add(getCurrentAdmin(session), admin);
		} catch (TccException e) {
			SysRoleCriteria roleCriteria = new SysRoleCriteria();
			roleCriteria.setRoleStatus(Constants.IS_NORMAL);
			List<SysRoleVo> allRoleList = srService.list(getCurrentAdmin(session), roleCriteria);
			model = getModel();
			result = getResult();
			doException(model, result, e);
			model.put("admin", admin);
			model.put("allRoleList", allRoleList);
			return new ModelAndView("admin/add","model",model);
		} catch (Exception e) {
			SysRoleCriteria roleCriteria = new SysRoleCriteria();
			roleCriteria.setRoleStatus(Constants.IS_NORMAL);
			List<SysRoleVo> allRoleList = srService.list(getCurrentAdmin(session), roleCriteria);
			model = getModel();
			result = getResult();
			doException(model, result, e);
			model.put("admin", admin);
			model.put("allRoleList", allRoleList);
			return new ModelAndView("admin/add","model",model);
		}
		return new ModelAndView("redirect:list.htm");
	}

	@RequestMapping("/toEdit.htm")
	public ModelAndView toEdit(HttpSession session,Integer id) throws TccException, Exception {
		logger.info("sysAdmin toEdit");
		Map<String, Object> model = getModel();
		//获取系统所有正常状态的角色
		SysRoleCriteria roleCriteria = new SysRoleCriteria();
		roleCriteria.setRoleStatus(Constants.IS_NORMAL);
		List<SysRoleVo> allRoleList = srService.list(getCurrentAdmin(session), roleCriteria);
		//获取 操作员信息
		SysAdminVo  admin = saService.getSysAdminById(getCurrentAdmin(session), id);
		model.put("allRoleList", allRoleList);
		model.put("admin", admin);
		return new ModelAndView("admin/edit","model",model);
	}

	@RequestMapping("/edit.htm")
	public ModelAndView edit(SysAdminVo admin ,HttpSession session)  {
		logger.info("sysAdmin edit");
		Map<String, Object> model = null;
		Result result = null;
		try {
			saService.edit(getCurrentAdmin(session), admin);
		} catch (TccException e) {
			SysRoleCriteria roleCriteria = new SysRoleCriteria();
			roleCriteria.setRoleStatus(Constants.IS_NORMAL);
			List<SysRoleVo> allRoleList = srService.list(getCurrentAdmin(session), roleCriteria);
			model = getModel();
			result = getResult();
			doException(model, result, e);
			model.put("admin", admin);
			model.put("allRoleList", allRoleList);
			return new ModelAndView("admin/edit","model",model);
		} catch (Exception e) {
			SysRoleCriteria roleCriteria = new SysRoleCriteria();
			roleCriteria.setRoleStatus(Constants.IS_NORMAL);
			List<SysRoleVo> allRoleList = srService.list(getCurrentAdmin(session), roleCriteria);
			model = getModel();
			result = getResult();
			doException(model, result, e);
			model.put("admin", admin);
			model.put("allRoleList", allRoleList);
			return new ModelAndView("admin/edit","model",model);
		}
		return new ModelAndView("redirect:list.htm");
	}
	@ResponseBody
	@RequestMapping("/validateLoginName.htm")
	public boolean validateLoginName(String loginNameStr,Integer id){
		return saService.validateLoginName(loginNameStr, id);
	}
	@ResponseBody
	@RequestMapping("/resetPwd.htm")
	public Result resetPwd(HttpSession session,Integer id){
		Result result = getResult();
		try {
			saService.resetPwd(getCurrentAdmin(session), id);
			
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
	@RequestMapping("/open.htm")
	public Result open(HttpSession session,Integer id){
		Result result = getResult();
		try {
			saService.open(getCurrentAdmin(session), id);
			
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
			saService.close(getCurrentAdmin(session), id);
			
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
