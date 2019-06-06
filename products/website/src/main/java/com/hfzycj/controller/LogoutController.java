package com.hfzycj.controller;

import com.hfzycj.domain.User;
import com.hfzycj.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Logout
 */
@Controller
@RequestMapping("logout")
public class LogoutController extends BaseController {

	/**
	 * Logout
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		if (null != user) {
			LOGGER.info("用户：{} 注销！", user.getUser_name());
		}
		request.getSession().setAttribute(Constants.SESSION_USER_KEY, null);
		return new ModelAndView("Logout");
	}

}
