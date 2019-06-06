package com.zycj.tcc.scheduler;

import java.lang.reflect.Method;

/**
 * 任务载体
 * @author 洪捃能
 *
 */
public class JobCarrier {
	private Object targetObject;
	private Method targetMethod;
	public Object getTargetObject() {
		return targetObject;
	}
	public void setTargetObject(Object targetObject) {
		this.targetObject = targetObject;
	}
	public Method getTargetMethod() {
		return targetMethod;
	}
	public void setTargetMethod(Method targetMethod) {
		this.targetMethod = targetMethod;
	}
	
}
