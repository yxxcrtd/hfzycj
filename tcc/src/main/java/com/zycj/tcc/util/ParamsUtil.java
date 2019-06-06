package com.zycj.tcc.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.zycj.tcc.server.vo.Request;

/**
 * 参数工具类
 * @author 洪捃能
 *
 */
public class ParamsUtil {
//	private static String[] keys=new String[]{"t_code"};
	private static String key="t_code";
	
	public static Request getRequestData(String parametersStr) throws IllegalArgumentException, IllegalAccessException{
		Map<String, String> paramMap =ParamsUtil.getParameters(parametersStr);
		Request req=new Request();
		req.setT_code(paramMap.remove(key));
//		setParametersToObject(paramMap,keys,req);
		req.setParameter(paramMap);
		return req;
	}
	public static Map<String, String> getParameters(String params){
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.clear();
		if(StringUtils.isNotBlank(params)){
			params=params.substring(params.indexOf('?')+1);
			String paramaters[] = params.split("&");
			for (String param : paramaters) {
	            String values[] = param.split("=");
	            if (values.length>=2) {
	            	paramMap.put(values[0], values[1]);
				}
	        }
		}
		return paramMap;
	}
	
	public static <T> T setParametersToObject(Map<String, String> paramMap,String[] keys,T t) throws IllegalArgumentException, IllegalAccessException{
		if(keys==null||keys.length==0){
			return t;
		}
		for (int i = 0; i < keys.length; i++) {
			if(paramMap.containsKey(keys[i])){
				Field[] fds=t.getClass().getDeclaredFields();
				for (int j = 0; j < fds.length; j++) {
					Field f=fds[j];
					if(keys[i].equals(f.getName())){
						f.setAccessible(true);
						f.set(t, paramMap.get(keys[i]));
						paramMap.remove(keys[i]);
					}
				}
			}
		}
		return t;
	}
}
