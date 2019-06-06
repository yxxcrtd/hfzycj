package com.wl.base.interceptor;

import com.wl.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 登陆拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /** Log */
    protected static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView model)
            throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute(Constants.CURRENT_OPENID);
        if (user != null) {
            return true;
        }
        //ajax请求过期session
//		if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
//			response.setHeader("sessionstatus", "timeout");  
//            response.sendError(518, "session timeout.");
//            return false;
//		}
        LOGGER.debug("session不存在,重新登陆");
        response.sendRedirect(request.getContextPath() + "/home/forShowLogin.shtml");
        return false;
    }

}
