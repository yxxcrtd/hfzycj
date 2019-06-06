package com.zycj.tcc.server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zycj.tcc.server.ServerConfig;
import com.zycj.tcc.server.handler.codec.MasterDataDecoder;
import com.zycj.tcc.server.handler.codec.MasterDataEncoder;

@Component("serverChannelInitializer")
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	
	@Autowired
	private ServerHandler serverHandler;
	@Autowired
	private LogInterfaceHandler logInterfaceHandler;
	@Autowired
	private CheckLoginHandler checkLoginHandler;
	
//	@Autowired
//	private MasterDataEncoder masterDataEncoder;
	

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
//		pipeline.addLast("readTimeoutHandler",new ReadTimeoutHandler(10));//默认秒为单位
		pipeline.addLast("idleStateHandler",new IdleStateHandler(ServerConfig.TCP_Channel_ReadTimeOut,0,0));//默认秒为单位
		pipeline.addLast("heartbeatHandler",new HeartbeatHandler());
//		pipeline.addLast("logHandler",new LoggingHandler(LogLevel.DEBUG));
		pipeline.addLast("inCoder1", new LengthFieldBasedFrameDecoder(1024*1024,0,4,0,4));
//		pipeline.addLast("inCoder2", new StringDecoder(CharsetUtil.UTF_8));
		pipeline.addLast("inCoder2", new MasterDataDecoder());
		pipeline.addLast("outCoder1", new MasterDataEncoder());
		pipeline.addLast("logInterfaceHandler",logInterfaceHandler);
		pipeline.addLast("checkLoginHandler",checkLoginHandler);
		pipeline.addLast("serverHandler",serverHandler);
//		pipeline.addLast("outCoder1", new LengthFieldPrepender(4));
//		pipeline.addLast("outCoder2", new StringEncoder(CharsetUtil.UTF_8));
	}
}

