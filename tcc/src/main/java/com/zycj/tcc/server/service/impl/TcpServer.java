package com.zycj.tcc.server.service.impl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.zycj.tcc.server.ServerConfig;
import com.zycj.tcc.server.handler.ServerChannelInitializer;
import com.zycj.tcc.server.service.IServer;

/**
 * 基于TCP的服务端
 * @author 洪捃能
 */
@Component("tcpServer")
public class TcpServer implements IServer {
	private final static Logger log= Logger.getLogger(TcpServer.class);
	
	@Resource
	private ServerChannelInitializer serverChannelInitializer;
	
	private int port;
	private ServerBootstrap serverBootstrap;
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	
	//option() 方法用于设置监听套接字。childOption()则用于设置连接到服务器的客户端套接字。
	private void init(){
		port=ServerConfig.TCP_Server_Port;
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup(ServerConfig.IO_Worder_Threads);
		serverBootstrap= new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup)
//		.handler(new LoggingHandler(LogLevel.DEBUG))
		.option(ChannelOption.SO_BACKLOG, 2048)//服务端接收的连接数
//		.option(ChannelOption.TCP_NODELAY, true)//false,为启用nagle算法
		.childOption(ChannelOption.SO_KEEPALIVE, true)
		.channel(NioServerSocketChannel.class)
		.childHandler(serverChannelInitializer);
	}
	
	@SuppressWarnings("unchecked")
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
					System.out.println("服务器启动"+(future.isSuccess()?"成功":"失败")+"! 端口："+port);
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
			System.out.println("netty服务器启动异常");
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
