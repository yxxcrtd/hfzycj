package com.tcc.park.api.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tcc.park.api.common.ResultCode;
import com.tcc.park.api.service.UserService;
import com.tcc.park.api.util.RSACoder;
import com.tcc.park.api.util.URLUtil;
import com.tcc.park.api.vo.AppResult;

public class LoginHandler implements HandlerInterceptor{
	private static final Logger logger = Logger.getLogger(LoginHandler.class);
	
	@Autowired
	private UserService userService;
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
//		logger.info("after");
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
//		logger.info("post");
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		String url = request.getRequestURI();
		if (logger.isInfoEnabled()) {
			logger.info("intercepetor:"+url);
		}
		AppResult result = new AppResult();
		result.setResultCode(ResultCode.NO_LOGIN_ERROR);
		try {
			String params = url.substring(url.lastIndexOf("/")+1);
			Map<String, String> urlMap= URLUtil.analysis(RSACoder.decryptDataByRSA64(params));
			String mobile = MapUtils.getString(urlMap, "phone_no");
			String token = MapUtils.getString(urlMap, "token");
			if (StringUtils.isBlank(token)) {
				response.getOutputStream().write(JSONObject.fromObject(result).toString().getBytes());
				return false;
			}
			if (!ResultCode.APP_SUCCESS.equals(userService.isLogin(mobile, token))) {
				response.getOutputStream().write(JSONObject.fromObject(result).toString().getBytes());
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.getOutputStream().write(JSONObject.fromObject(result).toString().getBytes());
			return false;
		}
		return true;
	}

}
