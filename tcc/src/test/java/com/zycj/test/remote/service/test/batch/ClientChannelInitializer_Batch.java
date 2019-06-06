package com.zycj.test.remote.service.test.batch;

import com.zycj.tcc.domain.OrderInfo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ClientChannelInitializer_Batch extends ChannelInitializer<SocketChannel> {
	private NettyClient_Batch nClient;
	private String type;
	private OrderInfo oi;
	public ClientChannelInitializer_Batch(NettyClient_Batch nClient){
		this.nClient=nClient;
	}
	public ClientChannelInitializer_Batch(NettyClient_Batch nClient,String type){
		this.nClient=nClient;
		this.type=type;
	}
	public ClientChannelInitializer_Batch(NettyClient_Batch nClient,String type,OrderInfo oi){
		this.nClient=nClient;
		this.type=type;
		this.oi=oi;
	}
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("inCoder1", new LengthFieldBasedFrameDecoder(20*1024*1024,0,4,0,4));
		pipeline.addLast("inCoder2", new StringDecoder(CharsetUtil.UTF_8));
		pipeline.addLast("clientHandler", new ClientHandler_Batch(nClient,type,oi));
		pipeline.addLast("outCoder1", new LengthFieldPrepender(4));
		pipeline.addLast("outCoder2", new StringEncoder(CharsetUtil.UTF_8));
		
	}
	

}
