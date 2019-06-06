package com.zycj.tcc.server.interceptor;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.server.domain.TransactionData;
import com.zycj.tcc.server.service.impl.ServerWriteFutureListener;
import com.zycj.tcc.server.util.HandlerUtil;
import com.zycj.tcc.server.util.TransactionDataHelper;
import com.zycj.tcc.server.vo.Request;
import com.zycj.tcc.server.vo.Response;

/**
 *  拦截器 接口交易数据记录
 * aspectj注解方式
 * @author 洪捃能
 *
 */
//@Aspect
@Deprecated
public class TransactionInterceptor {
	@Autowired
	private ServerWriteFutureListener serverWriteFutureListener;

	@Pointcut("execution(* com.zycj.tcc.server.dispatcher.DefaultDispatcher.doWork(..))"
		  + "||execution(* com.zycj.tcc.server.handler.HandlerHelper.*(..))")
	public void anyMethod() {
	}
	
	//2
	/**处理执行成功的业务逻辑交易数据  开始*/
	@Around("execution(* com.zycj.tcc.server.dispatcher.DefaultDispatcher.doWork(..))")
	public Object doWorkAsDispatcher(ProceedingJoinPoint pjp) throws Throwable {
		// 调用方法的参数
        Object[] args = pjp.getArgs();
        // 调用的方法名:pjp.getSignature().getName();
        // 获取目标对象:pjp.getTarget();
        Channel ch=(Channel) args[0];
        String key=HandlerUtil.getClientIP_Port(ch);
//        System.err.println("2");
        Object result = pjp.proceed();//触发切入点方法执行，方法的返回值
        TransactionData td=TransactionDataHelper.get(key);
        if(td==null){
        	Request req=(Request) args[1];
        	td=new TransactionData();
            td.setIpPort(key);
    		td.setRequest(req);
    		TransactionDataHelper.put(key, td);
        }
		td.setResponse((Response) result);
        return result;
	}
	//1
	@Around("execution(* com.zycj.tcc.server.handler.HandlerHelper.doWork(..))")
	public Object doWorkAsHandlerHelper(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		Channel ch=(Channel) args[0];
        Request req=(Request) args[1];
        String key=HandlerUtil.getClientIP_Port(ch);
        TransactionData td=new TransactionData();
        td.setIpPort(key);
		td.setRequest(req);
		td.setReqTime(new Date());
		TransactionDataHelper.put(key, td);
		ChannelFuture cf=(ChannelFuture) pjp.proceed();//方法的返回值
        cf.addListener(serverWriteFutureListener);
        return cf;
	}
	/**处理执行成功的业务逻辑交易数据  结束*/
	
	/**处理执行异常的业务逻辑交易数据  开始*/
	//2
	@Around("execution(* com.zycj.tcc.server.handler.HandlerHelper.doExceptionWork(..))")
	public Object doExceptionWorkAsHandlerHelper(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		Channel ch=(Channel) args[0];
		String key=HandlerUtil.getClientIP_Port(ch);
		Throwable e=(Throwable) args[1];
		Response res=new Response();
		res.setResultCode(ResultCode.SYSTEM_ERROR);
		TransactionData td=TransactionDataHelper.get(key);
	    if(td==null){
	    	td=new TransactionData();
            td.setIpPort(key);
            td.setReqTime(new Date());
            TransactionDataHelper.put(key, td);
	    }
	    td.setResponse(res);
		ChannelFuture cf=(ChannelFuture) pjp.proceed();
		cf.addListener(serverWriteFutureListener);
        return cf;
	}
	/**处理执行异常的业务逻辑交易数据  结束*/
}
