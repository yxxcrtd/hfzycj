package com.zycj.server.util;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.zycj.server.netty.dto.ChannelConnection;

public class TcpConnectionPoolUtil {
	private static Logger logger = Logger.getLogger(TcpConnectionPoolUtil.class);
	private static Map<String, ChannelConnection> pool = new HashMap<String, ChannelConnection>();

	static {
		// 每10秒清理一次无效连接
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				clearDead();
			}
		}, 1000, 10000);

	}
	
	// 清理死掉的連接
	private static void clearDead() {
//		logger.info("清理死掉的线程");
		Set<Entry<String, ChannelConnection>> entrySet = pool.entrySet();
		for (Entry<String, ChannelConnection> entry : entrySet) {
			long lastTime = entry.getValue().getConnectionTime();
			if (new Date().getTime() > lastTime + NettyHttpServerConfig.getAliveTime() * 60 * 1000) {
				removeConnection(entry.getKey());
				logger.info(entry.getKey()+":" + NettyHttpServerConfig.getAliveTime() + "分钟内没有发送心跳，服务端强制踢下线");
			}
		}
	}
	
	public static void removeConnection(String id) {
		try {
			ChannelConnection conn = pool.get(id);
			if (conn != null) {
				pool.remove(id);
				conn.getChannel().close();
			}
		} catch (Exception e) {
			logger.error("移除连接出现异常", e);
//			e.printStackTrace();
		}
	}
	
	public static ChannelConnection getChannelConnection(String id) {
		return pool.get(id);
	}

	public static void pushPool(String key, ChannelConnection conn){
		pool.put(key, conn);
	}
}
