package com.zycj.tcc.server.domain;

import java.lang.reflect.Method;

/**
 * 标识业务类及业务方法
 * @author 洪捃能
 *
 */
public class ServiceInfo {

	//业务方法所在类
	private Object obj;
	//业务方法
	private Method method;
	
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	
	
}
