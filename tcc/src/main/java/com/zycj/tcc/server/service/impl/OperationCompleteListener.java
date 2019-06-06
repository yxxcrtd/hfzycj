package com.zycj.tcc.server.service.impl;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

import org.apache.log4j.Logger;

import com.zycj.tcc.server.util.HandlerUtil;

public class OperationCompleteListener implements ChannelFutureListener {
	private final static Logger log= Logger.getLogger(OperationCompleteListener.class);
	
	public OperationCompleteListener(ChannelHandlerContext ctx){
		this.ctx=ctx;
	}
	private ChannelHandlerContext ctx;
	@Override
	public void operationComplete(ChannelFuture future) throws Exception {
//		future.isDone();//表示任务是否执行完成，(代码是否执行完成)
//		future.isSuccess();//表示IO操作是否执行完成
		if(log.isDebugEnabled()){
			if(!future.isSuccess()){
				log.debug("代码执行:"+future.isDone()+";"+HandlerUtil.getClientIP_Port(future.channel())+" IO 失败", future.cause());
			}else{
//				log.debug("代码执行:"+future.isDone()+";IO 执行："+future.isSuccess());
			}
		}
//		ctx.close();
	}

}
