package com.zycj.test.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
	private NettyClient nClient;
	private String type;
	public ClientChannelInitializer(NettyClient nClient){
		this.nClient=nClient;
	}
	public ClientChannelInitializer(NettyClient nClient,String type){
//		System.out.println("实例了...ClientChannelInitializer");
		this.nClient=nClient;
		this.type=type;
	}
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("inCoder1", new LengthFieldBasedFrameDecoder(2*1024*1024,0,4,0,4));
		pipeline.addLast("inCoder2", new StringDecoder(CharsetUtil.UTF_8));
		pipeline.addLast("clientHandler", new ClientHandler(nClient,type));
		pipeline.addLast("outCoder1", new LengthFieldPrepender(4));
		pipeline.addLast("outCoder2", new StringEncoder(CharsetUtil.UTF_8));
		
	}
	

}
