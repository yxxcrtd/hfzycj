package com.zycj.mqtt.listener;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.QoS;

import com.zycj.mqtt.server.Mqtt;

public class CallBackListener {

	public static void dataListener() {
		final CallbackConnection connection = Mqtt.getInstance("server").getMQTT().callbackConnection();
		// 添加连接的监听事件
		connection.listener(new Listener() {

			public void onDisconnected() {
				System.out.println("连接失败！");
			}

			public void onConnected() {
				System.out.println("连接失败！");
			}

			/**
			 * 监听接收数据
			 */
			public void onPublish(UTF8Buffer topic, Buffer payload, Runnable ack) {
				ack.run();
				System.out.println("topic" + topic.toString() + "=" + new String(payload.getData()));
			}

			public void onFailure(Throwable value) {
			}
		});
		// 添加连接事件
		connection.connect(new Callback<Void>() {
			/**
			 * 连接失败的操作
			 */
			public void onFailure(Throwable value) {
				// If we could not connect to the server.
				System.out.println("MQTTCallbackServer.CallbackConnection.connect.onFailure" + "连接失败......" + value.getMessage());
				value.printStackTrace();
			}

			/**
			 * 连接成功的操作
			 * 
			 * @param v
			 */
			public void onSuccess(Void v) {

				int count = 1;
				while (true) {
					count++;
					// 用于发布消息，目前手机段不需要向服务端发送消息
					// 主题的内容
					final String message = "hello " + count + "chinese people !";
					final String topic = "0001";
					System.out.println("MQTTCallbackServer  publish  topic=" + topic + " message :" + message);
					connection.publish(topic, message.getBytes(), QoS.AT_LEAST_ONCE, false, new Callback<Void>() {
						public void onSuccess(Void v) {
							System.out.println("数据发送成功！");
						}

						public void onFailure(Throwable value) {
							value.printStackTrace();
						}
					});
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// 连接断开
				// connection.disconnect(new Callback<Void>() {
				// public void onSuccess(Void v) {
				// // called once the connection is disconnected.
				// System.out.println("MQTTSubscribeClient.CallbackConnection.connect.disconnect.onSuccess",
				// "called once the connection is disconnected.");
				// }
				//
				// public void onFailure(Throwable value) {
				// // Disconnects never fail.
				// System.out.println("MQTTSubscribeClient.CallbackConnection.connect.disconnect.onFailure",
				// "Disconnects never fail." + value.getMessage());
				// value.printStackTrace();
				// }
				// });

			}
		});
	}

	public static void sendData() {
		final CallbackConnection connection = Mqtt.getInstance("server").getMQTT().callbackConnection();

	}
}
