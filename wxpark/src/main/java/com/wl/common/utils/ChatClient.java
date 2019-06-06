package com.wl.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**  
 * 客户端程序 
 */  
public class ChatClient {
	
    private static final Logger logger = Logger.getLogger(ChatClient.class.getName());  
    private Socket socket = null;  
    private PrintWriter writer = null;  
    private BufferedReader reader = null;  
    
    public ChatClient(String host, int port) {  
        if (host == null || host.trim().equals("")) {  
            throw new IllegalArgumentException("地址不正确");  
        }  
        if (port < 1 || port > 9999) {  
            throw new IllegalArgumentException("端口不正确");  
        }  
        try {  
            socket = new Socket(host, port);  
        } catch (UnknownHostException ex) {  
            logger.log(Level.SEVERE, "找不到该地址", ex);  
        } catch (IOException ex) {  
            logger.log(Level.SEVERE, "创建Socket失败", ex);  
        }  
        if (socket != null) {  
            try {  
                writer = new PrintWriter(socket.getOutputStream(), true);  
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf8"));  
            } catch (IOException ex) {  
                logger.log(Level.SEVERE, "获得输入输出流失败", ex);  
            }  
        }  
    }  
    
    /** 
     * 向服务器端发送数据 
     * @param omsg 
     */  
    public void sendQuest(String omsg) {  
        if (writer != null) {  
            writer.println(omsg);  
        }  
    } 
}
