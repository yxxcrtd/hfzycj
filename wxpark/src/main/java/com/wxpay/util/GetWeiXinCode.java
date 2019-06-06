package com.wxpay.util;

import java.net.URLEncoder;

import com.wxpay.config.WxPayConfig;

public class GetWeiXinCode {
	public static String GetCodeRequest = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	public static String GETOPENID="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	public static String getCodeRequest(String price) {

		String result = null;

		GetCodeRequest = GetCodeRequest.replace("APPID", urlEnodeUTF8(WxPayConfig.APPID));

		GetCodeRequest = GetCodeRequest.replace("REDIRECT_URI", urlEnodeUTF8(WxPayConfig.REDIRECT_URL));

		GetCodeRequest = GetCodeRequest.replace("SCOPE", "snsapi_base");
		
		GetCodeRequest = GetCodeRequest.replace("STATE", price);
		
		result = GetCodeRequest;

		return result;

	}

	public static String urlEnodeUTF8(String str) {
		String result = str;
		try {
			result = URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}


}
