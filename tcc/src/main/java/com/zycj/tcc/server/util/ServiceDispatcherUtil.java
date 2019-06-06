package com.zycj.tcc.server.util;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zycj.tcc.init.SystemInit;
import com.zycj.tcc.server.annotation.ServiceClass;
import com.zycj.tcc.server.annotation.ServiceMethod;
import com.zycj.tcc.server.domain.ServiceInfo;
import com.zycj.tcc.util.SpringContextUtil;

/**
 * 服务调度工具类
 * @author 洪捃能
 *
 */
public class ServiceDispatcherUtil {
	private final static Logger log= Logger.getLogger(ServiceDispatcherUtil.class);
	
	private static Map<String, ServiceInfo> serviceMap;
	
	public static ServiceInfo getServiceInfoByType(String type){
		return serviceMap.get(type);
	}
	public static void init(){
		Map<String,Object> beans=SpringContextUtil.getBeansWithAnnotation(ServiceClass.class);
		Map<String, ServiceInfo> tmpMap = new HashMap<String, ServiceInfo>();
		for (String key:beans.keySet()) {
			Object target=beans.get(key);
			log.info("服务类："+target);
			Method[] methods = target.getClass().getDeclaredMethods();
			if (methods != null) {
				for (Method method : methods) {
					if (method.isAnnotationPresent(ServiceMethod.class)) {
						ServiceMethod sm = method.getAnnotation(ServiceMethod.class);
						ServiceInfo si=new ServiceInfo();
						si.setObj(target);
						si.setMethod(method);
						tmpMap.put(sm.type(), si);
						log.info(sm.type()+":"+sm.note()+";服务类方法："+method.getName());
					}
				}
			}
			serviceMap= Collections.unmodifiableMap(tmpMap);
		}
	}
}
