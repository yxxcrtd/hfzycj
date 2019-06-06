package com.zycj.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class NettyHttpServerConfig {
	private static Logger logger = Logger.getLogger(NettyHttpServerConfig.class);
	private static String netty_ip;
	private static int netty_port;
	private static int aliveTime;
	private static int waitTime;
	
	static {
		InputStream is = null;
		try {
			is = NettyHttpServerConfig.class.getClassLoader().getResourceAsStream("netty_http_server.properties");
			Properties prop = new Properties();
			prop.load(is);
			netty_ip = prop.getProperty("netty_ip");
			netty_port = Integer.parseInt(prop.getProperty("netty_port"));
			aliveTime = Integer.parseInt(prop.getProperty("alive_time"));
			waitTime = Integer.parseInt(prop.getProperty("wait_time"));
		} catch (IOException e) {
			logger.error("系统找不到对应的netty服务器的配置文件！", e);
//			e.printStackTrace();
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static String getNetty_ip() {
		return netty_ip;
	}

	public static int getNetty_port() {
		return netty_port;
	}

	public static int getAliveTime() {
		return aliveTime;
	}

	public static int getWaitTime() {
		return waitTime;
	}
	
	
}
