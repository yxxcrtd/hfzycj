package com.zycj.mqtt.dto;

public class MqttMessage {
	private String sendTopic;
	private String code;
	private String businessID;
	private String data;

	public String getSendTopic() {
		return sendTopic;
	}

	public void setSendTopic(String sendTopic) {
		this.sendTopic = sendTopic;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBusinessID() {
		return businessID;
	}

	public void setBusinessID(String businessID) {
		this.businessID = businessID;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
