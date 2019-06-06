/**
 * 
 */
package com.zycj.tcc.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zycj.tcc.common.Result;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.exception.TccException;
import com.zycj.tcc.service.SysAdminService;

/**
 * 当前登录的操作员
* Title: CurrentAdminController.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月11日
 */
@Controller
@RequestMapping("currentAdmin")
public class CurrentAdminController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CurrentAdminController.class);
	@Autowired
	private SysAdminService saService;

	@RequestMapping("/detail.htm")
	public ModelAndView detail(HttpSession session) throws TccException, Exception {
		logger.info("CurrentAdminController detail");
		Map<String, Object> model = getModel();
		SysAdminVo currentAdmin = saService.getSysAdminById(getCurrentAdmin(session), getCurrentAdmin(session).getId());
		model.put("currentAdmin", currentAdmin);
		return new ModelAndView("currentAdmin/detail", "model", model);
	}
	@RequestMapping("/edit.htm")
	public ModelAndView edit(HttpSession session,SysAdminVo currentAdmin) throws TccException, Exception {
		logger.info("CurrentAdminController edit");
		saService.editCurrentAdmin(getCurrentAdmin(session), currentAdmin);
		session.setAttribute("admin", saService.getSysAdminById(getCurrentAdmin(session), currentAdmin.getId()));
		return new ModelAndView("redirect:detail.htm");
	}
	@RequestMapping(value="/modifyPwd.htm",method =RequestMethod.GET)
	public String toModifyPwd() {
		logger.info("CurrentAdminController toModifyPwd");
		return "currentAdmin/modifyPwd";
	}
	@RequestMapping(value="/modifyPwd.htm",method =RequestMethod.POST)
	public ModelAndView modifyPwd(HttpSession session,Integer id,String oldPwd,String newPwd) {
		logger.info("CurrentAdminController modifyPwd");
		Map<String, Object> model = null;
		Result result = null;
		try {
			saService.modifyPwd(getCurrentAdmin(session), id, oldPwd, newPwd);
		} catch (TccException e) {
			model = getModel();
			result = getResult();
			doException(model, result, e);
			model.put("oldPwd", oldPwd);
			model.put("newPwd", newPwd);
			return new ModelAndView("currentAdmin/modifyPwd","model",model);
		} catch (Exception e) {
			model = getModel();
			result = getResult();
			doException(model, result, e);
			model.put("oldPwd", oldPwd);
			model.put("newPwd", newPwd);
			return new ModelAndView("currentAdmin/modifyPwd","model",model);
		}
		return new ModelAndView("redirect:detail.htm");
	}
}
