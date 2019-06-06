package com.zycj.server.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.apache.log4j.Logger;

import com.zycj.server.netty.handler.NettyHandler;
import com.zycj.server.util.NettyParamsUtil;

public class NettyServer {
	private static Logger logger = Logger.getLogger(NettyServer.class);

	public void init() {
		new Thread() {
			public void run() {
				logger.info("开始启动tcpserver");
				EventLoopGroup bossGroup = new NioEventLoopGroup();
				EventLoopGroup workerGroup = new NioEventLoopGroup();
				try {
					ServerBootstrap b = new ServerBootstrap();
					b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new NettyHandler()).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
					ChannelFuture f = b.bind(NettyParamsUtil.getNettyServerPort()).sync();
					logger.info("成功启动tcpserver");
					f.channel().closeFuture().sync();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					workerGroup.shutdownGracefully();
					bossGroup.shutdownGracefully();
				}
			}
		}.start();
	}
}
