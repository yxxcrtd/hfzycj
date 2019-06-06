package com.zycj.mqtt.listener;

import org.apache.log4j.Logger;
import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import com.alibaba.fastjson.JSONObject;
import com.zycj.mqtt.dto.MqttMessage;
import com.zycj.mqtt.server.DataChannel;
import com.zycj.mqtt.server.Mqtt;
import com.zycj.mqtt.util.MqttConfig;


public class DataListener {
	private static final Logger log = Logger.getLogger(DataListener.class);
	
	private DataListener(){
		new DataReceive().start();
	}
	
	public static DataListener init(){
		return new DataListener();
	}

	class DataReceive extends Thread {
		@Override
		public void run() {
			BlockingConnection connection = this.getConnection();
			if(connection == null){
				return ;
			}
			MqttMessage receiveMsg = null;
			log.info("mqtt监听已经启动【tcc】！" + MqttConfig.getClientId());
			while (true) {
				try {
					Message message = connection.receive();
					message.ack();
					String msg = new String(message.getPayload());
					log.info("【tcc】mqtt接收到数据：" + msg);
					receiveMsg = JSONObject.parseObject(msg, MqttMessage.class);
					if(DataChannel.getInstance().isContainsKey(receiveMsg.getBusinessID())){
						DataChannel.getInstance().push(receiveMsg.getBusinessID(), receiveMsg);
					}
//					receiveMsg = new MqttMessage();
//					receiveMsg.setBusinessID(String.valueOf(new Date().getTime()));
//					receiveMsg.setCode("0001");
//					receiveMsg.setReceiveTopic("app-皖Aj0111");
//					receiveMsg.setData("我是谁？");
//					connection.publish(receiveMsg.getReceiveTopic(), JSONObject.fromObject(message).toString().getBytes(), QoS.AT_LEAST_ONCE, true);
				} catch (Exception e) {
					log.error("【tcc】MQTT数据接收出现了异常！", e);
					connection = this.getConnection();
					if(connection == null){
						return ;
					}
				}
			}
		}
		
		private BlockingConnection getConnection(){
			BlockingConnection connection = null;
			try {
				connection = Mqtt.getInstance(MqttConfig.getClientId()).getMQTT().blockingConnection();
				connection.connect();
				Topic[] topics = { new Topic(MqttConfig.getClientId(), QoS.AT_LEAST_ONCE) };
				connection.subscribe(topics);
			} catch (Exception e) {
				log.error("【tcc】连接MQTT出现了异常！", e);
			}
			return connection;
		}
	}
}
