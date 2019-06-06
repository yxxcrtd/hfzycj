package com.tcc.park.api.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class JsonUtil {
	
	public static String getResultJson(String key ,String result) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(key, result);
		return JSONObject.fromObject(jsonMap).toString();
	}

}
