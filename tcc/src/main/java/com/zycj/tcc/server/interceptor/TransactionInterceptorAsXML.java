package com.zycj.tcc.server.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zycj.tcc.server.vo.Request;
import com.zycj.tcc.server.vo.DataResponse;

/**
 * 交易数据,基于XML配置的切面类
 * 不介意使用这种方法，采用aspectj注解方式
 * @author 洪捃能
 *
 */
@Deprecated
@Component("transactionInterceptorAsXML")
public class TransactionInterceptorAsXML implements MethodInterceptor, InitializingBean{

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String targetName = invocation.getThis().getClass().getName();		
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();	 
		Request req=(Request) arguments[1];
		Object result=invocation.proceed();
		DataResponse rd=(DataResponse) result;
		return result;
	}

}
