package com.zycj.tcc.server.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrePayCardHelper {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	
	//基本参数
	public static Map<String, Object> buildBaseParam(String method) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("app_key", PrePayPropertiesUtil.get("app_key"));
		result.put("method", method);
		result.put("timestamp", getCurrentDateStr());
		result.put("v", PrePayPropertiesUtil.get("v"));
		result.put("sign_v", PrePayPropertiesUtil.get("sign_v"));
		result.put("format", "JSON");
		return result;
	}

	private static String getCurrentDateStr() {
		return sdf.format(new Date());
	}
	
	//参数签名
	public static void sign(Map<String ,Object> parms,String encode) throws UnsupportedEncodingException{
		String secret = PrePayPropertiesUtil.get("secretKey");
		Set<String> keySet = parms.keySet();
		List<String> keyList = new ArrayList<String>();
		for (String key : keySet) {
			keyList.add(key);
		}
		Collections.sort(keyList);
		String result = secret;
		for (String key : keyList) {
			result += key;
			result += parms.get(key);
		}
		result += secret;
		String sign = MD5Util.Md532(result);
		parms.put("sign", sign.toUpperCase());
	}
	
}
