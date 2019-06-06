package com.zycj.sdk.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.zycj.framework.cache.InitCacheData;
import com.zycj.mqtt.listener.DataListener;


public class InitContextDataListener implements ServletContextListener{
	// 启动运行日志
	/** log */
	private static final Logger LOG = Logger.getLogger(InitContextDataListener.class);
	public void contextDestroyed(ServletContextEvent arg0) {
		// 停止所有定时任务
		LOG.info("****************************************************");
		LOG.info("系统停止，停止定时任务！");
//		if (TimeTaskManager.isStarted()) {
//			TimeTaskManager.getScheduler().isShutdown();
//			TimeTaskManager.getScheduler().clear();
//			LOG.info("停止定时任务成功！");
//		}
		LOG.info("****************************************************");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// 初始化缓存数据
		InitCacheData.getInstance().initData();
		// MQTT消息接收监听启动
		DataListener.init();
//		// 启动mina服务器
//		MinaServer.getInstance().startServer();
	}
}
