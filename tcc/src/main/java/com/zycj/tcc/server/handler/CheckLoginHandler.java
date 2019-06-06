package com.zycj.tcc.server.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.common.SystemConfig;
import com.zycj.tcc.server.util.HandlerUtil;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.util.ServiceDispatcherUtil;
import com.zycj.tcc.server.vo.Request;
import com.zycj.tcc.service.LoginService;

@Component
@Sharable
public class CheckLoginHandler extends SimpleChannelInboundHandler<Request> {
	private final static Logger log = Logger.getLogger(CheckLoginHandler.class);
	@Autowired
	private LoginService loginService;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Request req) throws Exception {
		try {
			if (StringUtils.isBlank(req.getT_code())) {
				HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.INTERFACE_NO_FOUND_ERROR));
				return;
			}
			if (ServiceDispatcherUtil.getServiceInfoByType(req.getT_code()) == null) {
				HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.INTERFACE_NO_FOUND_ERROR));
				return;
			}
			if (SystemConfig.noCheckInterface.contains(req.getT_code()) || req.getT_code().indexOf("test_") != -1) {
				ctx.fireChannelRead(req);
				return;
			}
			Map<String, String> parms = req.getParameter();
			String loginValidate = parms.get("loginValidate");
			if (!"false".equals(loginValidate)) {
				String empNo = parms.get("emp_no");
				String posNo = parms.get("pos_no");
				String dataType = parms.get("data_type");// 1 数据即时发送 2 数据重新发送

				if (StringUtils.isBlank(empNo)) {
					HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.EMPLOYEENO_ISBLANK_ERROR));
					return;
				}
				if (StringUtils.isBlank(posNo)) {
					HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.POSNO_ISBLANK_ERROR));
					return;
				}
				if (Constants.Data_Send_repeat.equals(dataType)) {// 如果是重新发送
					boolean rcheckLogin = false;
					try {
						rcheckLogin = loginService.checkLogin(empNo, posNo);
					} catch (Exception e) {
						log.warn("重发接口时  校验员工是否合法 异常！", e);
						HandlerUtil.writeAndFlushEnd(ctx,
								ResponseUtil.createResponse(ResultCode.DATA_SEND_REPEAT_CHECK_ERROR));
						return;
					}
					if (log.isDebugEnabled()) {
						log.debug("重发接口  校验" + (rcheckLogin ? "成功" : "失败") + " 参数：" + req.toString());
					}
					if (rcheckLogin) {
						ctx.fireChannelRead(req);
						return;
					} else {
						HandlerUtil.writeAndFlushEnd(ctx,
								ResponseUtil.createResponse(ResultCode.DATA_SEND_REPEAT_CHECK_ERROR));
						return;
					}
				}
				String token = parms.get("token");
				if (StringUtils.isBlank(token)) {
					HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.TOKEN_ISBLANK_ERROR));
					return;
				}
				boolean checkLogin = false;
				try {
					checkLogin = loginService.checkLogin(empNo, posNo, token);
				} catch (Exception e) {
					log.warn("校验员工是否登录程序异常！", e);
					HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.CHECK_LOGIN_ERROR));
					return;
				}
				if (checkLogin) {
					ctx.fireChannelRead(req);
				} else {
					String ipport = HandlerUtil.getClientIP_Port(ctx);
					log.info("非法请求<" + ipport + "> 参数：" + req.toString());
					HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.NO_LOGIN_ERROR));
				}
			} else {
				ctx.fireChannelRead(req);
			}
		} catch (Exception e) {
			log.warn("校验员工是否登录程序异常！", e);
			HandlerUtil.writeAndFlushEnd(ctx, ResponseUtil.createResponse(ResultCode.CHECK_LOGIN_ERROR));
		}
	}

}
