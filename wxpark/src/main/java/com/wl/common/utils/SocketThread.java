package com.wl.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

/** 
 * 单个用户Socket线程 
 */  
public class SocketThread implements Runnable {
	 private static final Logger logger = Logger.getLogger(SocketThread.class.getName());  
	    private Socket socket = null;  
	    private BManager manager = null;  
	    private BufferedReader reader = null;  
	    private PrintWriter writer = null;  
	  
	    public SocketThread(Socket socket, BManager manager) {  
	        this.socket = socket;  
	        this.manager = manager;  
	    }  
	  
	    public void run() {  
	        System.out.println("运行已个玩家线程");  
	        try {  
	            if (socket != null && manager != null) {  
	                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf8"));  
	                writer = new PrintWriter(socket.getOutputStream(), true); // true : autoFlush  
	  
	                if (reader != null && writer != null) {  
	                    String imsg;  
	                    while ((imsg = reader.readLine()) != null) {  
	                        if (imsg.equals("exit")) {  
	                            break;  
	                        }  
	                        System.out.println("imsg : " + imsg);  
	                        manager.sendToAll(imsg);  
	                    }  
	                }  
	            }  
	        } catch (Exception ex) {  
	        } finally {  
	            try {  
	                manager.remove(socket);  
	                if (reader != null) {  
	                    reader.close();  
	                }  
	                if (writer != null) {  
	                    writer.close();  
	                }  
	                if (socket != null) {  
	                    socket.close();  
	                }  
	                reader = null;  
	                writer = null;  
	                socket = null;  
	                System.out.println("客户断开了连接...");  
	            } catch (IOException ex) {  
	            }  
	        }  
	    } 
}
