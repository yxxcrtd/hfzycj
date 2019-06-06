package com.zycj.tcc.scheduler;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

public class SchedulerHelper {
	private final static Logger log = Logger.getLogger(SchedulerHelper.class);

	public static String DEFAULT_GROUP = "TCC_GROUP";
	private static Scheduler scheduler;
	private static boolean flag = true;
	public static String Job_Carrier="JobCarrier";

	static {
		getScheduler();
	}

	public synchronized static Scheduler getScheduler() {
		if (scheduler == null && flag) {
			try {
				scheduler = new StdSchedulerFactory().getScheduler();
			} catch (SchedulerException e) {
				log.warn("初始化调度程序异常!!!", e);
				e.printStackTrace();
			}
			flag = false;
		}
		return scheduler;
	}

	public static JobDetail createJobDetail(Class<? extends Job> jobClass,String name, String group) {
		JobDetailImpl job = new JobDetailImpl();
		job.setDescription("测试用的任务");
		job.setJobClass(jobClass);
		job.setKey(new JobKey(name, group));
		job.setDurability(false);
		job.setRequestsRecovery(false);
		
//		job=JobBuilder.newJob(jobClass).withIdentity(name, group).build();
		return job;
	}

	public static CronTrigger createCronTrigger(String cronExpression,String name, String group) {
		CronTriggerImpl ct = new CronTriggerImpl();
		CronExpression cronEx=null;
		try {
			cronEx = new CronExpression(cronExpression);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ct.setCronExpression(cronEx);
		ct.setTimeZone(cronEx.getTimeZone());
		ct.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
		ct.setStartTime(new Date());
		ct.setKey(new TriggerKey(name,group)); 
        ct.setPriority(Trigger.DEFAULT_PRIORITY);
        
//		CronTriggerImpl ct = (CronTriggerImpl) TriggerBuilder.newTrigger()
//				.withIdentity(name, group)
//				.withSchedule(cronSchedule(cronExpression)).build();
		return ct;
	}

	@Deprecated
	public static boolean scheduleJob(Class<? extends Job> jobClass,
			String jobName, String cronExpression, String trigName) {
		JobDetail job = createJobDetail(jobClass, jobName, DEFAULT_GROUP);
		return scheduleJob(job,
				createCronTrigger(cronExpression, trigName, DEFAULT_GROUP));
	}

	public static boolean scheduleJob(Object targetObject, String targetMethod,String cronExpression) {
		JobCarrier jc = new JobCarrier();
		jc.setTargetObject(targetObject);
		Method[] ms=targetObject.getClass().getDeclaredMethods();
		Method om=null;
		for (Method m:ms) {
			if(targetMethod.equals(m.getName())){
				om=m;
				break;
			}
		}
		if(om==null){
			log.warn("未找到执行的方法");
			return false;
		}
		
		jc.setTargetMethod(om);
		String jobName = targetObject.getClass().getName() + "_" + targetMethod;
		JobDetail job = createJobDetail(JobHelper.class, jobName, DEFAULT_GROUP);
		job.getJobDataMap().put(SchedulerHelper.Job_Carrier, jc);
		return scheduleJob(
				job,
				createCronTrigger(cronExpression, jobName + "_CronTrigger",
						DEFAULT_GROUP));
	}

	public static boolean scheduleJob(JobDetail job, CronTrigger trigger) {
		try {
			Date ft = scheduler.scheduleJob(job, trigger);
			log.info(job.getKey() + " run at: " + ft + " ,expression: "
					+ trigger.getCronExpression());
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.warn("创建调度任务异常!!!", e);
		}
		return false;
	}

	public static void start() {
		try {
			scheduler.start();
			log.info("启动调度程序成功!!!");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.warn("启动调度程序失败!!!", e);
		}
	}

	public static void stop() {
		try {
			scheduler.shutdown(true);
			log.info("关闭调度程序成功!!!");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.warn("关闭调度程序失败!!!", e);
		}
	}

	public static void aa() {
		try {
			System.err.println("Jobs:");
			List<JobExecutionContext> jecs=scheduler.getCurrentlyExecutingJobs();
			Trigger trig=null;
			JobDetail jd=null;
			for(JobExecutionContext jec:jecs){
				trig=jec.getTrigger();
				jd=jec.getJobDetail();
				System.out.println(trig.getKey()+"<1==>"+jd.getKey()+":"+jd.getDescription()+";"+trig.getStartTime());
			}
			
			System.out.println("NumberOfJobsExecuted:"+scheduler.getMetaData().getNumberOfJobsExecuted());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		TriggerKey tk = new TriggerKey("testTrigger",SchedulerHelper.DEFAULT_GROUP);
//		JobKey jk = new JobKey("testJob", SchedulerHelper.DEFAULT_GROUP);
//		try {
//			scheduler.pauseTrigger(tk);
//			System.err.println("暂停了任务执行....");
//			CronTriggerImpl ct = (CronTriggerImpl) SchedulerHelper
//					.getScheduler().getTrigger(tk);
//			ct.setCronExpression("0/30 * * * * ?");
//			JobDetail tj = (JobDetail) scheduler.getJobDetail(jk);
//			scheduler.deleteJob(jk);
//			// scheduler.resumeTrigger(tk);
//			scheduler.scheduleJob(tj, ct);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	// public static CronTrigger createCronTrigger(String cronExpression)
	// throws ParseException {
	// CronExpression cronExp = new CronExpression(cronExpression);
	// CronTriggerImpl trig = new CronTriggerImpl();
	// trig.setCronExpression(cronExp);
	// trig.setTimeZone(cronExp.getTimeZone());
	// trig.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_SMART_POLICY);
	/***
	 * 
	 quartz启动后，第一次，立即执行问题 是由于startTime在上一次执行时间（利用crontab表达式计算出）之前引起的； 解决办法是：
	 * CronTriggerImpl trigger = new CronTriggerImpl();
	 * trigger.setMisfireInstruction
	 * (CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
	 * 将CronTrigger的misfireInstruction属性设置为CronTrigger
	 * .MISFIRE_INSTRUCTION_DO_NOTHING
	 */
	// trig.setCalendarName(null);
	// trig.setDescription(null);
	// trig.setStartTime(new Date());
	// trig.setEndTime(null);
	// TriggerKey key = new TriggerKey(null, null);//Trigger的名称和组名
	// trig.setKey(key);
	// JobKey jobKey=new JobKey(null, null);//任务的名称和组名
	// trig.setJobKey(jobKey);
	// trig.setPriority(Trigger.DEFAULT_PRIORITY);
	// JobDataMap jobDataMap = new JobDataMap();
	// if (!jobDataMap.isEmpty())
	// trig.setJobDataMap(jobDataMap);
	//
	// return trig;
	// }

}
