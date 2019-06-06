package com.zycj.mqtt.test;

import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.QoS;

public class Client {

	/**
	 * @description 
	 * @author fengya
	 * @date 2016-8-19 下午02:58:10
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		try {
			final CallbackConnection callbackConnection = MqttClient.getConnection();

			// 连接
			callbackConnection.connect(new Callback<Void>() {

				// 连接失败
				public void onFailure(Throwable value) {
					System.out.println("============连接失败：" + value.getLocalizedMessage() + "============");
				}

				// 连接成功
				public void onSuccess(Void v) {

					// 发布消息
					callbackConnection.publish("server88", ("Hello ").getBytes(), QoS.AT_LEAST_ONCE, true, new Callback<Void>() {
						public void onSuccess(Void v) {
							System.out.println("===========消息发布成功============");
						}

						public void onFailure(Throwable value) {
							System.out.println("========消息发布失败=======");
							callbackConnection.disconnect(null);
						}
					});

				}
			});

			while (true) {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
