package com.zycj.mqtt.server;

import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

import com.zycj.mqtt.util.MqttConfig;

public class Mqtt {
	private static final Logger LOG = Logger.getLogger(Mqtt.class);
	private static MQTT mqtt = null;
	private static Mqtt mq = new Mqtt();
	
	private Mqtt(){
	}
	
	public static Mqtt getInstance(String clientId){
		try {
			mqtt = new MQTT();
			mqtt.setHost(MqttConfig.getIp(),MqttConfig.getPort());
			mqtt.setClientId(clientId); // 用于设置客户端会话的ID。在setCleanSession(false);被调用时，MQTT服务器利用该ID获得相应的会话。此ID应少于23个字符，默认根据本机地址、端口和时间自动生成
			mqtt.setCleanSession(true); // 若设为false，MQTT服务器将持久化客户端会话的主体订阅和ACK位置，默认为true
			mqtt.setKeepAlive(MqttConfig.getKeepAlive());// 定义客户端传来消息的最大时间间隔秒数，服务器可以据此判断与客户端的连接是否已经断开，从而避免TCP/IP超时的长时间等待
			mqtt.setUserName(MqttConfig.getUserName());// 服务器认证用户名
			mqtt.setPassword(MqttConfig.getPassword());// 服务器认证密码
			mqtt.setWillTopic(MqttConfig.getWillTopic());// 设置“遗嘱”消息的话题，若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息
			mqtt.setWillMessage(MqttConfig.getWillMessage());// 设置“遗嘱”消息的内容，默认是长度为零的消息
			mqtt.setWillQos(QoS.AT_LEAST_ONCE);// 设置“遗嘱”消息的QoS，默认为QoS.ATMOSTONCE
			mqtt.setWillRetain(false);// 若想要在发布“遗嘱”消息时拥有retain选项，则为true
			mqtt.setVersion("3.1.1");
			// 失败重连接设置说明
			mqtt.setConnectAttemptsMax(MqttConfig.getConnectAttemptsMax());// 客户端首次连接到服务器时，连接的最大重试次数，超出该次数客户端将返回错误。-1意为无重试上限，默认为-1
			mqtt.setReconnectAttemptsMax(MqttConfig.getReconnectAttemptsMax());// 客户端已经连接到服务器，但因某种原因连接断开时的最大重试次数，超出该次数客户端将返回错误。-1意为无重试上限，默认为-1
			mqtt.setReconnectDelay(MqttConfig.getReconnectDelay());// 首次重连接间隔毫秒数，默认为10ms
			mqtt.setReconnectDelayMax(MqttConfig.getReconnectDelayMax());// 重连接间隔毫秒数，默认为30000ms
			mqtt.setReconnectBackOffMultiplier(MqttConfig.getReconnectBackOffMultiplier());// 设置重连接指数回归。设置为1则停用指数回归，默认为2
			// Socket设置说明
			mqtt.setReceiveBufferSize(MqttConfig.getReceiveBufferSize());// 设置socket接收缓冲区大小，默认为65536（64k）
			mqtt.setSendBufferSize(MqttConfig.getSendBufferSize());// 设置socket发送缓冲区大小，默认为65536（64k）
			mqtt.setTrafficClass(MqttConfig.getTrafficClass());// 设置发送数据包头的流量类型或服务类型字段，默认为8，意为吞吐量最大化传输
			// 带宽限制设置说明
			mqtt.setMaxReadRate(MqttConfig.getMaxReadRate());// 设置连接的最大接收速率，单位为bytes/s。默认为0，即无限制
			mqtt.setMaxWriteRate(MqttConfig.getMaxWriteRate());// 设置连接的最大发送速率，单位为bytes/s。默认为0，即无限制
			mqtt.setDispatchQueue(ZYCJDispatchQueue.getInstance().getDispatchQueue());
		} catch (URISyntaxException e) {
			LOG.error("初始化MQTT出现了异常！",e);
		}
		return mq;
	}
	
	public synchronized MQTT getMQTT(){
		return mqtt;
	}
}
