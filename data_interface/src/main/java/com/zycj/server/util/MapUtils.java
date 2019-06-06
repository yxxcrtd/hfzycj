package com.zycj.server.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {
	
	public static HashMap<String, String> getPublicMap(String tCode) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("t_code", tCode);
		hashMap.put("loginValidate","false");
		return hashMap;
	}
	
	public static String getParamesStr(HashMap<String, String> params) {
		String values = "";
		if (params != null && params.size() > 0) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				values = values + entry.getKey() + "=" + entry.getValue() + "&";
			}
			values = values.substring(0, values.length() - 1);
		}
		return values;
	}
}
