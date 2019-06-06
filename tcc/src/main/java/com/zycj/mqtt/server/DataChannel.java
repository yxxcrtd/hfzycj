package com.zycj.mqtt.server;

import java.util.HashMap;
import java.util.Map;

import com.zycj.mqtt.dto.MqttMessage;

public class DataChannel {
	private Map<String,MqttMessage> dataChannels = new HashMap<String,MqttMessage>();
	
	private static DataChannel channel = new DataChannel();
	
	private DataChannel(){}
	
	public static synchronized DataChannel getInstance(){
		if(channel == null){
			channel = new DataChannel();
		}
		return channel;
	}
	
	public void push(String key,MqttMessage value){
		dataChannels.put(key, value);
	}
	
	public MqttMessage get(String key){
		if(dataChannels.containsKey(key)){
			return dataChannels.get(key);
		}
		return null;
	}
	
	public boolean isContainsKey(String key){
		return dataChannels.containsKey(key);
	}
	
	public void remove(String key){
		dataChannels.remove(key);
	}
	
	public void clear(){
		dataChannels.clear();
	}
}
