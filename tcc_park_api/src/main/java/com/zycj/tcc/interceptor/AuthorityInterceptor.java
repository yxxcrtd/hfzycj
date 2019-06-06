/**
 * 
 */
package com.zycj.tcc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zycj.tcc.controller.TestController;
import com.zycj.tcc.exception.NoAuthorityException;
import com.zycj.tcc.exception.NoLoginException;
import com.zycj.tcc.service.SysAdminService;
import com.zycj.tcc.service.SysPermissionService;

/**
 * 
 * Title: AuthorityInterceptor.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年3月25日
 */
public class AuthorityInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory
			.getLogger(TestController.class);
	@Autowired
	private SysPermissionService spService;
	@Autowired
	private SysAdminService saService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String servletPath = request.getServletPath().toLowerCase();
		logger.info("request servletPath:" + servletPath);
		// 登录相关请求，不用拦截
		if (servletPath.indexOf("login") > 0
				|| servletPath.indexOf("logout") > 0
				|| servletPath.indexOf("identifyingcode") > 0) {
			return true;
		}
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("admin");
		// 尚未登录，请先登录
		if (null == obj) {
			throw new NoLoginException("尚未登录，请先登录");
		}
		// 判断是否拥有访问权限
		// main相关的不需要做权限控制
		if (servletPath.indexOf("top") > 0 || servletPath.indexOf("left") > 0
				|| servletPath.indexOf("firstpage") > 0) {
			return true;
		} else if (!spService.hasPermission(1, servletPath)) {
			throw new NoAuthorityException("您没有访问权限");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("postHandle" + request.getPathInfo());
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("afterCompletion");
	}

}
