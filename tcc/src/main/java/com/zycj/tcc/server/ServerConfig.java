package com.zycj.tcc.server;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.zycj.tcc.util.PropertiesHelper;

/**
 * POS服务端配置文件工具类
 * @author 洪捃能
 *
 */
public class ServerConfig {
	private static Logger logger = Logger.getLogger(ServerConfig.class);
	private static PropertiesHelper properties = new PropertiesHelper(new Properties());
	
	public static int TCP_Server_Port;//netty服务器端口
	public static int IO_Worder_Threads;//netty处理IO操作的线程数
	public static int TCP_Channel_ReadTimeOut;//netty连接读超时的时间
	
	static{
		try {
			properties.loadPropertiesFromClassLoader("netty_Tcp_Server.properties");
			TCP_Server_Port=properties.getInt("TCP_Server_Port",7878);
			IO_Worder_Threads=properties.getInt("IO_Worder_Threads",200);
			TCP_Channel_ReadTimeOut=properties.getInt("TCP_Channel_ReadTimeOut",10);
		} catch (IOException e) {
			logger.error("加载  netty_Tcp_Server.properties 异常！",e);
		}
	}
}
