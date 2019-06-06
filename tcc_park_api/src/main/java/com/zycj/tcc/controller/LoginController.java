/**
 * 
 */
package com.zycj.tcc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.GetIP;
import com.zycj.tcc.common.IdentifyingCode;
import com.zycj.tcc.common.Result;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.service.SysAdminService;
import com.zycj.tcc.service.SysPermissionService;

/**
 * 登录相关 Title: LoginController.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年3月25日
 */
@Controller
public class LoginController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	@Autowired
	private SysAdminService saService;
	@Autowired
	private SysPermissionService spService;

	@RequestMapping("/toLogin.htm")
	public String toLogin(HttpSession session) {
		logger.info("toLogin");
		// 判断是否已经登录，如果已经登录，直接跳转main
		// 判断是否已经登录，如果已经登录，直接跳转main
		Object obj = session.getAttribute("admin");
		if (null != obj) {
			return "main";
		}
		return "login";
	}

	@RequestMapping("/login.htm")
	public ModelAndView login(String loginName, String password,
			String validateNum, String identifyingCode, HttpSession session,HttpServletRequest request) {
		logger.info("login:" + loginName + "--" + password + "--"
				+ identifyingCode);
		// 判断是否已经登录，如果已经登录，直接跳转main
		Object obj = session.getAttribute("admin");
		if (null != obj) {
			return new ModelAndView("main");
		}
		Integer count = (Integer) session.getAttribute("loginCount");
		Map<String, Object> model = getModel();
		Result result = getResult();
		if (null == count) {
			count = 0;
		}
		//非空校验
		if(StringUtils.isBlank(loginName)){
			count++;
			result.setCode(Constants.FAIL_RESULT);
			result.setMsg("登录账号不能为空");
			model.put("result", result);
			return new ModelAndView("login", "model", model);
		}
		if(StringUtils.isBlank(password)){
			count++;
			result.setCode(Constants.FAIL_RESULT);
			result.setMsg("登录密码不能为空");
			model.put("result", result);
			return new ModelAndView("login", "model", model);
		}
		// 判断登录次数是否大于5次，如果大于5次，必须校验验证码
		logger.info("identifyingCode:" + loginName + "--"
				+ session.getAttribute("identifyingCode"));
		if (count > 4) {
			if (StringUtils.isBlank(identifyingCode)) {
				result.setCode(Constants.FAIL_RESULT);
				result.setMsg("验证码不能为空");
				model.put("result", result);
				return new ModelAndView("login", "model", model);
			} else if (!identifyingCode.equalsIgnoreCase((String) session
					.getAttribute("identifyingCode"))) {
				result.setCode(Constants.FAIL_RESULT);
				result.setMsg("验证码不正确");
				model.put("result", result);
				return new ModelAndView("login", "model", model);
			}
		}
		// 校验登录信息是否有效
		SysAdminVo admin = saService.validateLoginInfo(loginName, password);
		if (null == admin) {
			count++;
			session.setAttribute("loginCount", count);
			result.setCode(Constants.FAIL_RESULT);
			result.setMsg("登录账号或者登录密码不正确");
			model.put("result", result);
			return new ModelAndView("login", "model", model);
		} else if(Constants.IS_CLOSED==admin.getStatus()){
			count++;
			session.setAttribute("loginCount", count);
			result.setCode(Constants.FAIL_RESULT);
			result.setMsg("登录账号已经被禁用，请联系相关人员");
			model.put("result", result);
			return new ModelAndView("login", "model", model);
		}
		else {
			//记录当前操作员的ip
			admin.setIp(GetIP.getIpAddr(request));
			session.setAttribute("admin", admin);
			// 更新 操作管理员登录信息
			saService.updateAdminLoginRecord(admin);
			// 在session中添加当前操作员拥有的资源
			session.setAttribute("permissionMap",
					spService.getPermissionsByAdminId(admin.getId()));
			return new ModelAndView("main");
		}
	}

	@RequestMapping("/identifyingCode.htm")
	public void identifyingCode(HttpSession session,
			HttpServletResponse response) throws Exception {
		logger.info("identifyingCode:");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		IdentifyingCode identifyingCode = new IdentifyingCode();
		identifyingCode.createIdentifyingCode(response.getOutputStream());
		session.setAttribute("identifyingCode",
				identifyingCode.getIdentifyingCodeStr());
	}

	@RequestMapping("/logout.htm")
	public ModelAndView logout(HttpSession session) {
		logger.info("logout:");
		session.invalidate();
		return new ModelAndView("redirect:toLogin.htm");
	}
}
