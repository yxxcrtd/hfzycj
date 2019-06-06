package com.zycj.server.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadMessageUtil {
	private static Map<Long,Object> threadData = new HashMap<Long,Object>();
	public static final int WAIT_TIME = NettyHttpServerConfig.getWaitTime();
	
	static {
		// 每10秒清理一次无效连接
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				clearThreadData();
			}
		}, 1000, 10000);

	}
	
	private static void clearThreadData() {
//		logger.info("清理死掉的线程");
		for (long key : threadData.keySet()) {
			if (new Date().getTime() > key + NettyHttpServerConfig.getAliveTime() * 60 * 1000) {
				remove(key);
			}
		}
	}
	
	public static void push(Long key,Object value){
		threadData.put(key, value);
	}
	
	public static Object getValue(long key){
		return threadData.get(key);
	}
	
	public static void remove(long key){
		threadData.remove(key);
	}
}
