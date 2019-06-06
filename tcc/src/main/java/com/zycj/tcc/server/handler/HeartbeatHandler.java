package com.zycj.tcc.server.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import org.apache.log4j.Logger;

import com.zycj.tcc.server.util.HandlerUtil;

public class HeartbeatHandler extends ChannelDuplexHandler {
	private final static Logger log= Logger.getLogger(HeartbeatHandler.class);
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			 IdleStateEvent event = (IdleStateEvent) evt;
	            if (event.state().equals(IdleState.READER_IDLE)) {//读超时
	            	//服务器接收完远程sokect上的字节数才开始计算，业务逻辑时间也计算在内的
	            	//比如readeTimeOut设置为10秒，服务器接收完数据处理业务用了3秒，那么这3秒也是计算在这10秒内的
	            	ctx.close();
	            	log.warn("ReadTimeOut 服务器关闭了："+HandlerUtil.getClientIP_Port(ctx)+" 连接");
	            } else if (event.state().equals(IdleState.WRITER_IDLE)) {//写超时
	            	log.warn(HandlerUtil.getClientIP_Port(ctx)+" WRITER_IDLE");
	            } else if (event.state().equals(IdleState.ALL_IDLE)) {//空闲超时
	            	log.warn(HandlerUtil.getClientIP_Port(ctx)+" ALL_IDLE");
	            }
		}
	}
}
