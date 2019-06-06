package com.tcc.park.api.controller;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.park.api.common.ResultCode;
import com.tcc.park.api.service.SmsService;
import com.tcc.park.api.util.RSACoder;
import com.tcc.park.api.util.URLUtil;
import com.tcc.park.api.vo.ObjectResult;

@Controller
public class SmsController {
	
	@Autowired
	private SmsService smsService;
	
	@RequestMapping("/api/common/code/{urlParams}")
	public @ResponseBody String getCode(@PathVariable String urlParams) {
		ObjectResult<String> ojbResult = new ObjectResult<String>();
		ojbResult.setResultCode(ResultCode.APP_PARAM_ERROR);
		Map<String, String> urlMap;
		try {
			urlMap = URLUtil.analysis(RSACoder.decryptDataByRSA64(urlParams));
			if (urlMap == null) {
				return JSONObject.fromObject(ojbResult).toString();
			}
			Integer type = MapUtils.getInteger(urlMap, "code_type",0);
			String mobile = MapUtils.getString(urlMap, "phone_no");
			if (StringUtils.isBlank(mobile) || type == 0) {
				return JSONObject.fromObject(ojbResult).toString();
			}
			ojbResult = smsService.sendCode(mobile, type);
			return JSONObject.fromObject(ojbResult).toString();
		} catch (Exception e) {
			e.printStackTrace();
			ojbResult.setResultCode(ResultCode.APP_SYSTEM_ERROR);
			return JSONObject.fromObject(ojbResult).toString();
		}
	}
	
	
	@RequestMapping("/api/common/code/check/{urlParams}")
	public @ResponseBody String checkCode(@PathVariable String urlParams) {
		ObjectResult<String> ojbResult = new ObjectResult<String>();
		ojbResult.setResultCode(ResultCode.APP_PARAM_ERROR);
		Map<String, String> urlMap;
		try {
			urlMap = URLUtil.analysis(RSACoder.decryptDataByRSA64(urlParams));
			if (urlMap == null) {
				return JSONObject.fromObject(ojbResult).toString();
			}
			Integer type = MapUtils.getInteger(urlMap, "code_type",0);
			String mobile = MapUtils.getString(urlMap, "phone_no");
			String smsCode = MapUtils.getString(urlMap, "code");
			if (StringUtils.isBlank(mobile) || type == 0) {
				return JSONObject.fromObject(ojbResult).toString();
			}
			String result = smsService.checkCode(mobile, smsCode, type);
			if (ResultCode.CODE_OK.equals(result)) {
				ojbResult.setResultCode(ResultCode.APP_SUCCESS);
			}else {
				ojbResult.setResultCode(result);
			}
			return JSONObject.fromObject(ojbResult).toString();
		} catch (Exception e) {
			e.printStackTrace();
			ojbResult.setResultCode(ResultCode.APP_SYSTEM_ERROR);
			return JSONObject.fromObject(ojbResult).toString();
		}
	}
}
