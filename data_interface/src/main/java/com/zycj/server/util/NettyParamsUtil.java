package com.zycj.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class NettyParamsUtil {
	private static Integer nettyServerPort;

	static {
		InputStream is = null;
		try {
			is = NettyParamsUtil.class.getClassLoader().getResourceAsStream("netty.properties");
			Properties prop = new Properties();
			prop.load(is);
			nettyServerPort = Integer.parseInt(prop.getProperty("netty_server_port"));
		} catch (IOException e) {
			System.out.println("初始化加载netty参数出现了异常");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// e.printStackTrace();
				}
			}
		}
	}

	public static Integer getNettyServerPort() {
		return nettyServerPort;
	}

}
