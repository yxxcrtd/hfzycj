package com.wl.common.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 聊天系統服務器
 * @author Administrator
 *
 */
public class ChatServer {
	private static final Logger logger = Logger.getLogger(ChatServer.class.getName());  
    private ServerSocket serverSocket = null;  
    private BManager manager = new BManager();  
    public ChatServer(int port) {  
        initVar(port);  
    }  
  
    /** 
     * 初始化服务器 
     * @param port 
     */  
    private void initVar(int port) {  
        try {  
            serverSocket = new ServerSocket(port);  
            Socket socket = null;  
            while (serverSocket != null) {  
                socket = serverSocket.accept();  
                System.out.println("一个用户已经连接上了");  
                manager.add(socket);  
                Runnable runnable = new SocketThread(socket, manager);  
                if (runnable != null) {  
                    Thread thread = new Thread(runnable);  
                    thread.start();  
                }  
                manager.sendInfo();  
            }  
        } catch (Exception ex) {  
            logger.log(Level.SEVERE, "启动服务器失败!", ex);  
        }  
    }  
  
    public static void main(String[] args) {  
        ChatServer chatServer = new ChatServer(8888);  
    } 
}
