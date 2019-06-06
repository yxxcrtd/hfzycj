package com.zycj.tcc.file.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import org.apache.log4j.Logger;

import com.zycj.tcc.server.service.IServer;

public class FileServer implements IServer {
	private final static Logger log= Logger.getLogger(FileServer.class);
	private int port;
	private ServerBootstrap serverBootstrap;
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	
	private void init(){
		port=7879;
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup();
		serverBootstrap= new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup)
		.option(ChannelOption.SO_BACKLOG,100)
		.option(ChannelOption.TCP_NODELAY, true)
		.option(ChannelOption.SO_KEEPALIVE, true)
		.channel(NioServerSocketChannel.class)
		.childHandler(new ChannelInitializer<SocketChannel>(){
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("inCoder1", new StringDecoder(CharsetUtil.UTF_8));
				pipeline.addLast("fileServerHandler",new FileServerHandler());
			}
		});
	}
	@Override
	public void start() {
		init();
		log.info("正在启动服务...");
		try {
			serverBootstrap.bind(port).sync()
			//监听服务器绑定端口
			.addListener(new GenericFutureListener() {
				@Override
				public void operationComplete(Future future) throws Exception {
					log.info("服务器启动"+(future.isSuccess()?"成功":"失败")+"! 端口："+port);
				}
			})
			.channel().closeFuture().sync()
			//监听服务器关闭
			.addListener(new GenericFutureListener() {
				@Override
				public void operationComplete(Future future) throws Exception {
					log.info("服务器关闭"+(future.isSuccess()?"成功":"失败")+"!");
				}
			});
		} catch (InterruptedException e) {
			log.warn("netty服务器启动异常",e);
		}finally {
			stop();
		}
	}

	@Override
	public void stop() {
		log.info("正在关闭服务...");
		if(bossGroup!=null)
		bossGroup.shutdownGracefully();
		if(workerGroup!=null)
		workerGroup.shutdownGracefully();
	}

	@Override
	public void restart() {
		log.info("重启服务...");
		stop();
		start();
	}

}
