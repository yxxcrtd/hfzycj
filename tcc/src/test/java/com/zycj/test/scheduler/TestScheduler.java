package com.zycj.test.scheduler;

import org.junit.Before;
import org.junit.Test;
import org.quartz.Scheduler;

import com.zycj.tcc.scheduler.SchedulerHelper;

public class TestScheduler {
	
	private Scheduler scheduler;
	
	@Before
	public void before(){
		scheduler=SchedulerHelper.getScheduler();
	}

	@Test
	public void test1(){
		
	}
}
