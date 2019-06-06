package com.zycj.mqtt.server;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.QoS;

import com.zycj.mqtt.dto.MqttMessage;
import com.zycj.mqtt.util.MqttConfig;

public class MqttSendData {

	private final static Logger log= Logger.getLogger(MqttSendData.class);
	/**
	 * @description 
	 * @author fengya
	 * @date 2016-8-18 下午02:17:52
	 * @param topic 发送数据的主题，如果是向服务器端进行发送，该主题是server,否则指定对应的主题名称
	 * @param message 发送的消息内容，格式：{receiveTopic:本机的topic,code:业务编码，businessID:业务ID,data:发送的内容}
	 * @throws Exception
	 * @return void
	 */
	public static MqttMessage sendData(String topic,MqttMessage message) throws Exception{
		log.info("开始连接Mqtt:" + topic);
		String rndClient = String.valueOf(new Date().getTime());
		BlockingConnection connection = Mqtt.getInstance(rndClient).getMQTT().blockingConnection();
		connection.connect();
		message.setBusinessID(rndClient);
		connection.publish(topic, JSONObject.fromObject(message).toString().getBytes(), QoS.AT_LEAST_ONCE, true);
		log.info("数据已经发送:" + JSONObject.fromObject(message).toString());
		DataChannel.getInstance().push(rndClient, null);
		connection.disconnect();
		MqttMessage receiveMsg = null;
		int index = 0;
		while(true){
			Thread.sleep(100);
			receiveMsg = DataChannel.getInstance().get(rndClient);
			if(receiveMsg != null){
				break;
			}
			if(++index > MqttConfig.getMaxWaitTime()){
				break;
			}
		}
		DataChannel.getInstance().remove(rndClient);
		return receiveMsg;
	}
}
