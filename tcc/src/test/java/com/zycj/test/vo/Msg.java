package com.zycj.test.vo;

public class Msg {

	private short msgType;
	private Object[] args;
	private String desc;
	public short getMsgType() {
		return msgType;
	}
	public void setMsgType(short msgType) {
		this.msgType = msgType;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
