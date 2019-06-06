package com.zycj.test.client.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;

import com.zycj.tcc.util.RSACoder;
import com.zycj.test.client.NettyClient;


public class NettyClientTest {
	
	public static String host;
	public static int port;
	public static String delimit;
	@Before
	public void init(){
		host="127.0.0.1";
		port = 7878;
		delimit="end";
	}
	public static void main(String[] args) throws InterruptedException {
		NettyClientTest nct=new NettyClientTest();
		nct.init();
		nct.test1();
	}
	
	@Test
	public void test1() throws InterruptedException{
		ExecutorService es=Executors.newCachedThreadPool();
		String str="systemCode=pos&securityCode=zycj&page=1&pageSize=5&key=val";
		for (int i = 0; i < 1; i++) {
			es.execute(new clientThread(i+"-",str));
		}
		es.shutdown();
		while(!es.isTerminated());  
	}
	
	
	class clientThread implements Runnable {
		private String name;
		private String str;
		public clientThread(String name,String str){
			this.name=name;
			this.str=str;
		}
		
		@Override
		public void run() {
			for (int i = 1; i <= 1; i++) {
				NettyClient nc=new NettyClient(host,port);
				try {
					nc.sendData(RSACoder.encryptDataByRSA64("interfaceType=1003&birthday=2014-09-04&price=0&tontent=阿斯蒂撒地方随便芬"+i+"&description=超级字段"+i+"&"+str+">>>"+name+i));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
