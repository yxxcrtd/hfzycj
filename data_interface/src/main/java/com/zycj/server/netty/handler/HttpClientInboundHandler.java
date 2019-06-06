package com.zycj.server.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.zycj.server.netty.httpclient.NettyClient;

public class HttpClientInboundHandler extends SimpleChannelInboundHandler<String>{
	private NettyClient nettyClient;
	
	public HttpClientInboundHandler(NettyClient nettyClient) {
		super();
		this.nettyClient = nettyClient;
	}


	@Override
	protected void messageReceived(ChannelHandlerContext context, String message) throws Exception {
		this.nettyClient.setResponseResult(message);
	}
}
