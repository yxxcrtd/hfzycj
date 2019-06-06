package com.zycj.test.remote.service.test.batch;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;

import com.zycj.tcc.domain.OrderInfo;

public class NettyClient_Batch {

	private String host;
	private int port;
	private EventLoopGroup group;
	private Bootstrap b;
	private Channel ch;
	
	public NettyClient_Batch(String host,int port){
		this.host=host;
		this.port=port;
		init();
	}
	public NettyClient_Batch(String host,int port,String type){
		this.host=host;
		this.port=port;
		init(type);
	}
	public NettyClient_Batch(String host,int port,String type,OrderInfo oi){
		this.host=host;
		this.port=port;
		init(type,oi);
	}
	public void init(){
		group= new NioEventLoopGroup(1);
		b= new Bootstrap();
		b.group(group)
		.channel(NioSocketChannel.class)
		.handler(new ClientChannelInitializer_Batch(this));
//		try {
//			ch= b.connect(host, port).sync().channel();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	public void init(String type){
		group= new NioEventLoopGroup(1);
		b= new Bootstrap();
		b.group(group)
		.channel(NioSocketChannel.class)
		.handler(new ClientChannelInitializer_Batch(this,type));
//		try {
//			ch= b.connect(host, port).sync().channel();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	public void init(String type,OrderInfo oi){
		group= new NioEventLoopGroup(1);
		b= new Bootstrap();
		b.group(group)
		.channel(NioSocketChannel.class)
		.handler(new ClientChannelInitializer_Batch(this,type,oi));
//		try {
//			ch= b.connect(host, port).sync().channel();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	public ChannelFuture sendData(String str){
		try {
			ch= b.connect(host, port).sync().channel();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ch.writeAndFlush(str);
	}
	
	public Future stop(){
		return group.shutdownGracefully();
	}
}
