package com.zycj.tcc.server.util;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;

import com.zycj.tcc.server.service.impl.OperationCompleteListener;
import com.zycj.tcc.server.vo.Response;

/**
 * 服务端handler工具类
 * @author 洪捃能
 *
 */
public class HandlerUtil {
	private static String default_separator=":";
	
	public static String getClientIP_Port(ChannelHandlerContext ctx){
		return getClientIP_Port(ctx.channel());
	}
	public static String getClientIP_Port(Channel channel){
		return getClientIP_Port(channel,default_separator);
	}
	public static String getClientIP_Port(Channel channel,String separator){
		InetSocketAddress isa=(InetSocketAddress) channel.remoteAddress();
		return isa.getHostString()+separator+isa.getPort();
	}
	public static String getClientIP(Channel channel){
		InetSocketAddress isa=(InetSocketAddress) channel.remoteAddress();
		return isa.getHostString();
	}
	public static Object writeAndFlushEnd(ChannelHandlerContext ctx,Response res){
		return ctx.writeAndFlush(res).addListener(new OperationCompleteListener(ctx));
	}
}
