package com.zycj.tcc.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.ReadTimeoutException;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.server.service.impl.OperationCompleteListener;
import com.zycj.tcc.server.util.HandlerUtil;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Request;

@Component
@Sharable
public class ServerHandler extends SimpleChannelInboundHandler<Request> {
	private final static Logger log= Logger.getLogger(ServerHandler.class);
	public ServerHandler(){
//		System.out.println("实例了..ServerHandler");
	}
	@Autowired
	private HandlerHelper handlerHelper;
	//断开连接时触发
	//isActive:false;isOpen:false;isWritable:false;isRegistered:false;
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.warn("与客户端的连接断开了"+HandlerUtil.getClientIP_Port(ctx.channel()));
	}
	//异常时触发
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception {
		Channel c=ctx.channel();
		/**
		 * 如果是IO异常，如客户端的soketChannel关闭了，导致服务端数据发送失败
		 * 常见的异常：远程主机强迫关闭了一个现有的连接或Connection reset by peer
		 * Connection reset by peer原因 1：服务器的并发连接数超过了其承载量，服务器会将其中一些连接Down掉；2：客户关掉了浏览器，而服务器还在给客户端发送数据；3：浏览器端按了Stop
		 */
		if (cause instanceof IOException) {
			log.warn(HandlerUtil.getClientIP_Port(c)+" 关闭连接 IOException --"+cause.getMessage());
			ctx.close();
			return;
		}
		//netty的读超时异常
		if(cause instanceof ReadTimeoutException){
			log.warn(HandlerUtil.getClientIP_Port(c)+"关闭连接  ReadTimeoutException --"+cause.getMessage());
			ctx.close();
			return;
		}
		log.warn(HandlerUtil.getClientIP(c)+" 执行异常", cause);
//		handlerHelper.doExceptionWork(c,cause);
		//新增
		ctx.writeAndFlush(ResponseUtil.createResponse(ResultCode.SYSTEM_ERROR)).addListener(new OperationCompleteListener(ctx));
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Request req)throws Exception {
//		System.out.println("serverHander>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		Iterator<Entry<String, ChannelHandler>> hds=ctx.pipeline().iterator();
//		while(hds.hasNext()){
//			Entry<String, ChannelHandler> hd=hds.next();
//			System.out.println(hd.getKey()+"<==>"+hd.getValue());
//		}
		handlerHelper.doWork(ctx, req).addListener(new OperationCompleteListener(ctx));
	}
}

