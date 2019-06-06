package com.zycj.mqtt.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class MqttConfig {
	private static final Logger LOG = Logger.getLogger(MqttConfig.class);
	private static String ip;
	private static int port;
	private static String userName;
	private static String password;
	private static String clientId;
	private static boolean cleanSession; 
	private static short keepAlive;
	private static String willTopic;
	private static String willMessage;
	private static long connectAttemptsMax;
	private static long reconnectAttemptsMax;
	private static long reconnectDelay;
	private static long reconnectDelayMax;
	private static int reconnectBackOffMultiplier;
	private static int receiveBufferSize;
	private static int sendBufferSize;
	private static int trafficClass;
	private static int maxReadRate;
	private static int maxWriteRate;
	private static int maxWaitTime;
	
	private MqttConfig(){}
	
	static {
		InputStream is = null;
		try {
			is = MqttConfig.class.getClassLoader().getResourceAsStream("mqtt.properties");
			Properties prop = new Properties();
			prop.load(is);
			ip = prop.getProperty("pollo_ip");
			port = Integer.parseInt(prop.getProperty("pollo_port"));
			userName = prop.getProperty("pollo_user_name");
			password = prop.getProperty("pollo_password");
			clientId = new String(prop.getProperty("client_id").getBytes("ISO8859-1"),"utf-8");
			cleanSession = Boolean.parseBoolean(prop.getProperty("clean_session"));
			keepAlive = Short.parseShort(prop.getProperty("keep_alive"));
			willTopic = prop.getProperty("will_topic");
			willMessage = prop.getProperty("will_message");
			connectAttemptsMax = Long.parseLong(prop.getProperty("connect_attempts_max"));
			reconnectAttemptsMax = Long.parseLong(prop.getProperty("reconnect_attempts_max"));
			reconnectDelay = Long.parseLong(prop.getProperty("reconnect_delay"));
			reconnectDelayMax = Long.parseLong(prop.getProperty("reconnect_delay_max"));
			reconnectBackOffMultiplier = Integer.parseInt(prop.getProperty("reconnect_back_off_multiplier"));
			receiveBufferSize = Integer.parseInt(prop.getProperty("receive_buffer_size"));
			sendBufferSize = Integer.parseInt(prop.getProperty("send_buffer_size"));
			trafficClass = Integer.parseInt(prop.getProperty("traffic_class"));
			maxReadRate = Integer.parseInt(prop.getProperty("max_read_rate"));
			maxWriteRate = Integer.parseInt(prop.getProperty("max_write_rate"));
			maxWaitTime = Integer.parseInt(prop.getProperty("max_wait_time"));
		} catch (IOException e) {
			LOG.error("mqtt启动参数加载错误，请检查参数配置是否正确！", e);
		}
	}

	public static String getIp() {
		return ip;
	}

	public static int getPort() {
		return port;
	}

	public static String getUserName() {
		return userName;
	}

	public static String getPassword() {
		return password;
	}

	public static String getClientId() {
		return clientId;
	}

	public static boolean isCleanSession() {
		return cleanSession;
	}

	public static short getKeepAlive() {
		return keepAlive;
	}

	public static String getWillTopic() {
		return willTopic;
	}

	public static String getWillMessage() {
		return willMessage;
	}

	public static long getConnectAttemptsMax() {
		return connectAttemptsMax;
	}

	public static long getReconnectAttemptsMax() {
		return reconnectAttemptsMax;
	}

	public static long getReconnectDelay() {
		return reconnectDelay;
	}

	public static long getReconnectDelayMax() {
		return reconnectDelayMax;
	}

	public static int getReconnectBackOffMultiplier() {
		return reconnectBackOffMultiplier;
	}

	public static int getReceiveBufferSize() {
		return receiveBufferSize;
	}

	public static int getSendBufferSize() {
		return sendBufferSize;
	}

	public static int getTrafficClass() {
		return trafficClass;
	}

	public static int getMaxReadRate() {
		return maxReadRate;
	}

	public static int getMaxWriteRate() {
		return maxWriteRate;
	}

	public static int getMaxWaitTime() {
		return maxWaitTime;
	}
	
	
}
