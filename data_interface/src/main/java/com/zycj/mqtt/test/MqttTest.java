package com.zycj.mqtt.test;

import java.util.Date;

import com.zycj.mqtt.dto.MqttMessage;
import com.zycj.mqtt.server.MqttSendData;

public class MqttTest {

	/**
	 * @description 
	 * @author fengya
	 * @date 2016-8-18 下午02:40:18
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
//		while(true){
			MqttMessage message = new MqttMessage();
			message.setBusinessID(String.valueOf(new Date().getTime()));
			message.setSendTopic("123");
			message.setData("我是谁-client - send？");
			try {
				MqttSendData.sendData("interface_server1", message);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//		}
	}
	

}
