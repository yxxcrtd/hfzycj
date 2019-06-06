package com.zycj.tcc.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.common.SystemConfig;
import com.zycj.tcc.scheduler.job.ParkClearJob;
import com.zycj.tcc.scheduler.job.ParkClearJobTomorrow;

@Service
public class SchedulerCenter {
	
	@Autowired
	private ParkClearJob parkClearJob;
	@Autowired
	private ParkClearJobTomorrow parkClearJobTomorrow;
	
	public void init(){
		SchedulerHelper.scheduleJob(parkClearJob, "clear", SystemConfig.Park_Clear_Job_CronExpression);
		SchedulerHelper.start();
		tomorrow();
	}
	
	public void tomorrow() {
		SchedulerHelper.scheduleJob(parkClearJobTomorrow, "clear", SystemConfig.Park_Clear_Job_CronExpression_Tomorrow);
		SchedulerHelper.start();
	}
	
}
