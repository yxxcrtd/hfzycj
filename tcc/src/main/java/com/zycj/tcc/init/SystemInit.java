package com.zycj.tcc.init;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.zycj.tcc.cache.FeeRegularCache;
import com.zycj.tcc.mybatis.mapper.FeeRegularMapper;
import com.zycj.tcc.scheduler.SchedulerCenter;
import com.zycj.tcc.server.service.IServer;
import com.zycj.tcc.server.util.ServiceDispatcherUtil;

@Component
public class SystemInit implements ApplicationListener<ContextRefreshedEvent>{
	private final static Logger log= Logger.getLogger(SystemInit.class);
	
	@Resource
	private IServer server;
	@Resource
	private SchedulerCenter schedulerCenter;
	
	private FeeRegularMapper feeRegularMapper;
	@Autowired
	public void setFeeRegularMapper(FeeRegularMapper feeRegularMapper) {
		this.feeRegularMapper = feeRegularMapper;
		FeeRegularCache.feeRegularMapper= feeRegularMapper;
	}

	public synchronized void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent()==null){
			ServiceDispatcherUtil.init();//消息分发器初始化
			log.info("消息分发器初始化!");
			schedulerCenter.init();//启动调度程序
			log.info("启动调度程序!");
			FeeRegularCache.init();//初始化计费规则
			log.info("初始化计费规则!");
			server.start();//启动netty服务器
		}
	}
}
