package com.hfzycj.interceptor;

import com.hfzycj.domain.User;
import com.hfzycj.util.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Login Interceptor
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
        if(null == user) {
        	response.sendRedirect("/login");
            return false;
        }
        request.setAttribute(Constants.SESSION_USER_KEY, user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		
	}

}
