package com.zycj.tcc.async;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/**
 * 基于反射worder
 * @author 洪捃能
 */
public class ReflectWorker implements Runnable{
	private final static Logger log = Logger.getLogger(ReflectWorker.class);
	
	public ReflectWorker(String method,Object target,Object[] params,String exceptionMsg){
		this.methodName=method;
		this.target=target;
		this.params=params;
		this.exceptionMsg=exceptionMsg;
	}
	private String methodName;
	private Object target;
	private Object[] params;
	private String exceptionMsg;
	
	public void run() {
		try {
			Method[] methods=target.getClass().getDeclaredMethods();
			Method method=null;
			for (Method m:methods) {
				if(m.getName().equals(methodName)){
					method=m;
					break;
				}
			}
			if(method==null){
				return;
			}
			method.setAccessible(true);
			method.invoke(target, params);
		} catch (Exception e) {
			if(exceptionMsg!=null){
				log.warn(exceptionMsg, e);
			}
		}
	}
}
