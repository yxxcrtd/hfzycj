package com.tcc.park.api.controller.weixin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tcc.park.api.service.WeiXinService;
import com.tcc.park.api.vo.WeiXinUserInfo;

@Controller
public class WeiXinController {
	
	@Autowired
	private WeiXinService weiXinService;
	
	@RequestMapping("/weixin/")
	public ModelAndView index(String code) {
		WeiXinUserInfo userInfo = weiXinService.getUserInfoByCode(code);
		Map<String,Object> data = new HashMap<String,Object>();  
	    data.put("userInfo",userInfo);
		ModelAndView view = new ModelAndView("weixin/index", "userinfo", userInfo);
		return view;
	}
}
