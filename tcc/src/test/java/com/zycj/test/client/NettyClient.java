package com.zycj.test.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;

public class NettyClient {

	private String host;
	private int port;
	private EventLoopGroup group;
	private Bootstrap b;
	private Channel ch;
	
	public NettyClient(String host,int port){
		this.host=host;
		this.port=port;
		init();
	}
	public NettyClient(String host,int port,String type){
		this.host=host;
		this.port=port;
		init(type);
	}
	public void init(){
		group= new NioEventLoopGroup(1);
		b= new Bootstrap();
		b.group(group)
		.channel(NioSocketChannel.class)
		.handler(new ClientChannelInitializer(this));
		try {
			ch= b.connect(host, port).sync().channel();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void init(String type){
		group= new NioEventLoopGroup(1);
		b= new Bootstrap();
		b.group(group)
		.channel(NioSocketChannel.class)
		.handler(new ClientChannelInitializer(this,type));
		try {
			ch= b.connect(host, port).sync().channel();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public ChannelFuture sendData(String str){
		return ch.writeAndFlush(str);
//		return ch.writeAndFlush(str).addListener(new ChannelFutureListener(){
//			@Override
//			public void operationComplete(ChannelFuture future)throws Exception {
////				future.isDone();//表示任务是否执行完成，(代码是否执行完成)
////				future.isSuccess();//表示IO操作是否执行完成
////				System.out.println("代码执行:"+future.isDone()+";IO 执行："+future.isSuccess());
//				if(!future.isSuccess()){
//					future.cause().printStackTrace();
//				}
//				stop();
//			}});
	}
	
	public Future stop(){
		return group.shutdownGracefully();
	}
}
