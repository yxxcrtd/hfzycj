package com.wl.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

/**
 * 广播类
 * 
 * @author Administrator
 *
 */
public class BManager extends Vector<Socket> {
	/**
	 * 对所有已经连接上的socket发送消息
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public synchronized void sendToAll(String msg) throws Exception {
		Socket socket = null;
		PrintWriter writer = null;
		for (int i = 0; i < size(); i++) {
			socket = get(i);
			if (socket != null) {
				writer = new PrintWriter(socket.getOutputStream(), true);
				if (writer != null) {
					writer.println(msg);
				}
			}
		}
	}

	/**
	 * 向某个人发送消息
	 * 
	 * @param msg
	 * @param socket
	 * @throws IOException
	 */
	public synchronized void sendToOne(String msg, Socket socket) throws Exception {
		PrintWriter writer = null;
		if (socket != null && contains(socket)) {
			writer = new PrintWriter(socket.getOutputStream(), true);
			if (writer != null) {
				writer.println(msg);
			}
		}
	}

	/**
	 * 向所有人发送当前在线人数
	 * 
	 * @throws IOException
	 */
	public synchronized void sendInfo() throws Exception {
		String msg = "当前人数:" + size() + "人";
		sendToAll(msg);
	}
}
