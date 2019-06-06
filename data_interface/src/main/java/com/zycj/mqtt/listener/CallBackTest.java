package com.zycj.mqtt.listener;

public class CallBackTest {

	/**
	 * @description 
	 * @author fengya
	 * @date 2016-8-19 上午10:31:14
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		CallBackListener.dataListener();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		CallBackListener.sendData();
		try {
			Thread.sleep(5000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
