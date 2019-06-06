package com.zycj.mqtt.util;

public enum MessageCode {
	PAY_FINISH_REMIND("0001"),CAR_ENTRY("0002"),CAR_REFUSE_OUT("car_refuse_out");
	private String code;

	private MessageCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
