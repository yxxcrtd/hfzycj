package com.zycj.tcc.server.interceptor;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.common.SystemConfig;
import com.zycj.tcc.server.util.HandlerUtil;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Request;
import com.zycj.tcc.service.LoginService;

/**
 * 拦截器 判断用户是否登陆，记录用户最后活动时间 aspectj注解方式
 * 
 * @author 洪捃能
 */
// @Aspect
@Deprecated
public class LoginInterceptor {
	private final static Logger log = Logger.getLogger(LoginInterceptor.class);
	@Autowired
	private LoginService loginService;

	@Pointcut("execution(* com.zycj.tcc.server.handler.HandlerHelper.doWork(..))")
	public void anyMethod() {
	}

	@Around("execution(* com.zycj.tcc.server.handler.HandlerHelper.doWork(..))")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		// Channel ch=(Channel) args[0];
		ChannelHandlerContext ctx = (ChannelHandlerContext) args[0];
		Request req = (Request) args[1];
		Map<String, String> parms = req.getParameter();
		if (StringUtils.isBlank(req.getT_code())) {
			return HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.INTERFACE_NO_FOUND_ERROR));
		}
		if (SystemConfig.noCheckInterface.contains(req.getT_code())) {
			return pjp.proceed();
		}
		String empNo = parms.get("emp_no");
		String posNo = parms.get("pos_no");
		String dataType = parms.get("data_type");// 1 数据即时发送 2 数据重新发送

		if (StringUtils.isBlank(empNo)) {
			return HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.EMPLOYEENO_ISBLANK_ERROR));
		}
		if (StringUtils.isBlank(posNo)) {
			return HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR));
		}
		if (Constants.Data_Send_repeat.equals(dataType)) {// 如果是重新发送
			boolean rcheckLogin = false;
			try {
				rcheckLogin = loginService.checkLogin(empNo, posNo);
			} catch (Exception e) {
				log.warn("重发接口时  校验员工是否合法 异常！", e);
				return HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.CHECK_LOGIN_ERROR));
			}
			log.info("重发接口  " + (rcheckLogin ? "成功" : "失败") + " 参数：" + req.toString());
			if (rcheckLogin) {
				return pjp.proceed();
			} else {
				return HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.NO_LOGIN_ERROR));
			}
		}
		String token = parms.get("token");
		if (StringUtils.isBlank(token)) {
			return HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.TOKEN_ISBLANK_ERROR));
		}
		boolean checkLogin = false;
		try {
			checkLogin = loginService.checkLogin(empNo, posNo, token);
		} catch (Exception e) {
			log.warn("校验员工是否登录程序异常！", e);
			return HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.CHECK_LOGIN_ERROR));
		}
		if (checkLogin) {
			return pjp.proceed();
		} else {
			String ipport = HandlerUtil.getClientIP_Port(ctx);
			log.info("非法请求<" + ipport + "> 参数：" + req.toString());
			return HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.NO_LOGIN_ERROR));
		}
	}
}
