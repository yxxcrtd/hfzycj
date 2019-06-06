package com.zycj.server.netty.dto;

import io.netty.channel.Channel;

public class ChannelConnection {
	private Channel channel;
	private long connectionTime;

	public ChannelConnection() {
		super();
	}

	public ChannelConnection(Channel channel, long connectionTime) {
		super();
		this.channel = channel;
		this.connectionTime = connectionTime;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public long getConnectionTime() {
		return connectionTime;
	}

	public void setConnectionTime(long connectionTime) {
		this.connectionTime = connectionTime;
	}

}
