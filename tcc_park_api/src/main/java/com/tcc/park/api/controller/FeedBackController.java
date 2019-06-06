package com.tcc.park.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.park.api.common.ResultCode;
import com.tcc.park.api.service.UserService;
import com.tcc.park.api.user.domain.User;
import com.tcc.park.api.user.domain.UserFeedback;
import com.tcc.park.api.user.mybatis.UserFeedbackMapper;
import com.tcc.park.api.util.RSACoder;
import com.tcc.park.api.util.URLUtil;
import com.tcc.park.api.vo.AppResult;

@Controller
public class FeedBackController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserFeedbackMapper userFeedbackMapper;

	@RequestMapping("/api/common/feed/{urlParams}")
	public @ResponseBody String feed(@PathVariable String urlParams,HttpServletRequest request) {
		AppResult result = new AppResult();
		result.setResultCode(ResultCode.APP_PARAM_ERROR);
		Map<String, String> urlMap;
		try {
			urlMap = URLUtil.analysis(RSACoder.decryptDataByRSA64(urlParams));
			if (urlMap == null) {
				return JSONObject.fromObject(result).toString();
			}
			String mobile = MapUtils.getString(urlMap, "phone_no");
			String content = MapUtils.getString(urlMap, "context");
			Integer vCode = MapUtils.getInteger(urlMap, "version_code",0);
			String deviceType = MapUtils.getString(urlMap, "device_type");
			
			if (StringUtils.isBlank(content) ) {
				return JSONObject.fromObject(result).toString();
			}
			insert(content, mobile, request.getRemoteAddr(),vCode,deviceType);
			result.setResultCode(ResultCode.APP_SUCCESS);
			return JSONObject.fromObject(result).toString();
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(ResultCode.APP_SYSTEM_ERROR);
			return JSONObject.fromObject(result).toString();
		}
	}
	
	public void insert(String content,String mobile,String ip,Integer vCode,String deviceType) {
		UserFeedback feedback = new UserFeedback();
		if (mobile != null) {
			User user = userService.selectUserByMobile(mobile);
			if (user != null) {
				feedback.setUserId(user.getId());
			}
		}
		feedback.setContact(mobile);
		feedback.setContent(content);
		feedback.setMachineType(deviceType);
		feedback.setVersionCode(vCode +"");
		
		feedback.setIp(ip);
		userFeedbackMapper.insertSelective(feedback);
	}
}
