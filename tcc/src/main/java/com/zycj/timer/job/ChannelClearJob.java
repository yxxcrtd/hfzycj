package com.zycj.timer.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.zycj.mqtt.server.DataChannel;

public class ChannelClearJob extends QuartzJobBean{
	private static final Logger LOG = Logger.getLogger(ChannelClearJob.class);

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		LOG.info("*******************定时器清理实时推送消息中的管道缓存数据开始**************");
		DataChannel.getInstance().clear();
		LOG.info("*******************定时器清理实时推送消息中的管道缓存数据结束**************");
	}
}
