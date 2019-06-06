package com.zycj.test.client;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

import com.zycj.tcc.server.util.CompressionUtil;

@Sharable
public class ClientHandler extends SimpleChannelInboundHandler<String> {
	
	private NettyClient nClient;
	private String type;
	public ClientHandler(NettyClient nClient,String type){
//		System.out.println("实例了...ClientHandler");
		this.nClient=nClient;
		this.type=type;
	}
	//channelRead0优先于channelReadComplete
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,String msg) throws Exception {
//		System.out.println("解压前："+msg.getBytes().length);
//		msg=CompressionUtil.unCompress(msg);
//		System.out.println("解压后："+msg.getBytes().length);
		InetSocketAddress isa=(InetSocketAddress) ctx.channel().localAddress();
		String key=isa.getPort()+":"+isa.getHostName();
		System.out.println("Server say : " + msg+"<>"+key);
//		System.out.println("客户端自己打印:"+HandlerUtil.getClientIP_Port(ctx.channel())+ctx.channel());
		nClient.stop();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		NioSocketChannel ch=(NioSocketChannel) ctx.channel();
//		System.out.println("channelReadComplete:"+ch.isInputShutdown()+":"+ch.isOutputShutdown());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		System.out.println("Client active ");
	}
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Client close ");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
		System.err.println("Client:exceptionCaught");
		cause.printStackTrace();
	}
}
