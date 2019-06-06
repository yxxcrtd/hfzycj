package com.tcc.park.api.util;

import java.util.HashMap;
import java.util.Map;

public class URLUtil {

    public static Map<String, String> analysis(String url) {
    	Map<String, String> paramMap = new HashMap<String,String>();
        paramMap.clear();
        if (!"".equals(url)) {// 如果URL不是空字符串
            url = url.substring(url.indexOf('?') + 1);
            String paramaters[] = url.split("&");
            for (String param : paramaters) {
                String values[] = param.split("=");
                if (values.length>=2) {
                	paramMap.put(values[0], values[1]);
				}
            }
        }
        return paramMap;
    }

}
