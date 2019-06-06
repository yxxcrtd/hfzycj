package com.zycj.test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadTest {
//	public static HashMap<String,String> val=new HashMap<String,String>();
	public static Hashtable<String,String> val=new Hashtable<String,String>();
	
	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			System.out.println(i);
			pool.execute(new T(val,i+""));
		}
		pool.shutdown();
//		while(!pool.isTerminated()){}
		Thread.sleep(3000);
		System.out.println(val.size()+"结果为"+val.get("k"));
	}
	
	static class T implements Runnable{
		public T(Hashtable<String,String> val,String v){
			this.val=val;
			this.v=v;
		}
		private Hashtable<String,String> val;
		private String v;
		@Override
		public void run() {
			val.put("k",v);
		}
	}

}
