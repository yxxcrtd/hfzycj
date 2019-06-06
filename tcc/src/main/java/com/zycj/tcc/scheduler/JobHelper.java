package com.zycj.tcc.scheduler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class JobHelper implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobCarrier jc=(JobCarrier) context.getJobDetail().getJobDataMap().get(SchedulerHelper.Job_Carrier);
		Object targetObj=jc.getTargetObject();
		Method targetMethod=jc.getTargetMethod();
		try {
			targetMethod.invoke(targetObj, null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
