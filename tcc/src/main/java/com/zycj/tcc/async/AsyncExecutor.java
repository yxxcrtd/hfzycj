package com.zycj.tcc.async;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.zycj.tcc.common.SystemConfig;

public class AsyncExecutor {
	
	private static ThreadPoolExecutor pool=new ThreadPoolExecutor(SystemConfig.Async_Executor_ThreadCount, 
			SystemConfig.Async_Executor_ThreadCount,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
	
	public static int getActiveCount(){
		return pool.getActiveCount();
	}
	public static int getCorePoolSize(){
		return pool.getCorePoolSize();
	}
	public static void shutdown(){
		pool.shutdown();
	}
	private static long getTaskCount() {
		return pool.getTaskCount();
	}
	public static void execute(Runnable run){
		pool.execute(run);
	}
	public static void execute(ReflectWorker worker){
		pool.execute(worker);
	}
	public static void execute(ReflectWorker[] workers){
		for (ReflectWorker worker:workers) {
			pool.execute(worker);
		}
	}
}
