package com.zycj.mqtt.server;

import org.fusesource.hawtdispatch.Dispatch;
import org.fusesource.hawtdispatch.DispatchQueue;

public class ZYCJDispatchQueue extends Dispatch {
	private static ZYCJDispatchQueue queue = new ZYCJDispatchQueue();
	private DispatchQueue dispatchQueue;
	
	private ZYCJDispatchQueue(){
		dispatchQueue = super.createQueue("zycj");
	}
	
	public static ZYCJDispatchQueue getInstance(){
		return queue;
	}
	
	public DispatchQueue getDispatchQueue(){
		return dispatchQueue;
	}
}
