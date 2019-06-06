package com.zycj.mqtt.listener;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import com.zycj.mqtt.dto.MqttMessage;
import com.zycj.mqtt.server.DataChannel;
import com.zycj.mqtt.server.Mqtt;
import com.zycj.mqtt.util.MqttConfig;

public class DataListener {
	private static final Logger LOG = Logger.getLogger(DataListener.class);
	
	private DataListener(){
		new DataReceive().start();
	}
	
	public static void init(){
		new DataListener();
	}

	class DataReceive extends Thread {
		@Override
		public void run() {
			BlockingConnection connection = this.getConnection();
			if(connection == null){
				return ;
			}
			JSONObject json = null;
			MqttMessage receiveMsg = null;
			LOG.info("mqtt监听已经启动【data_interface】！" + MqttConfig.getClientId());
			while (true) {
				try {
					Message message = connection.receive();
					message.ack();
					String msg = new String(message.getPayload());
					LOG.info("【data_interface】mqtt接收到数据：" + msg);
					json = JSONObject.fromObject(msg);
					receiveMsg = (MqttMessage) JSONObject.toBean(json, MqttMessage.class);
					if(DataChannel.getInstance().isContainsKey(receiveMsg.getBusinessID())){
						DataChannel.getInstance().push(receiveMsg.getBusinessID(), receiveMsg);
					}
//					receiveMsg = new MqttMessage();
//					receiveMsg.setBusinessID(String.valueOf(new Date().getTime()));
//					receiveMsg.setCode("0001");
//					receiveMsg.setReceiveTopic("app-皖Aj0111");
//					receiveMsg.setData("我是谁？");
					System.out.println(message.getTopic() + "server【data_interface】接收的数据" + msg);
//					connection.publish(receiveMsg.getReceiveTopic(), JSONObject.fromObject(message).toString().getBytes(), QoS.AT_LEAST_ONCE, true);
				} catch (Exception e) {
					LOG.error("【data_interface】MQTT数据接收出现了异常！", e);
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
				LOG.error("【data_interface】连接MQTT出现了异常！", e);
			}
			return connection;
		}
	}
}
