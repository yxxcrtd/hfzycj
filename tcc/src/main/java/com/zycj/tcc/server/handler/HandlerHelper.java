package com.zycj.tcc.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.server.dispatcher.Dispatcher;
import com.zycj.tcc.server.util.HandlerUtil;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Request;
/**
 * handler帮助类
 * @author 洪捃能
 *
 */
@Component
public class HandlerHelper {
	@Autowired
	private Dispatcher dispatcher;
	
	/**
	 * 根据请求处理业务并把处理结果返回给客户端
	 * @param ch socket通道
	 * @param req 请求数据包装类
	 * @throws Exception 
	 */
	public ChannelFuture doWork(Channel ch, Request req) throws Exception{
		req.getParameter().put(Request.CLIENT_IP,HandlerUtil.getClientIP(ch));
		return ch.writeAndFlush(dispatcher.doWork(req));
	}
	public ChannelFuture doWork(ChannelHandlerContext ctx, Request req) throws Exception{
		req.getParameter().put(Request.CLIENT_IP,HandlerUtil.getClientIP(ctx.channel()));
		return ctx.writeAndFlush(dispatcher.doWork(req));
	}
	/**
	 * 处理异常情况 并把异常结果返回给客户端
	 * @param ch socket通道
	 * @param cause 异常对象
	 */
	public ChannelFuture doExceptionWork(Channel ch,Throwable cause){
		ChannelFuture cf=ch.writeAndFlush(ResponseUtil.createResponse(ResultCode.SYSTEM_ERROR));
		return cf;
	}
}
