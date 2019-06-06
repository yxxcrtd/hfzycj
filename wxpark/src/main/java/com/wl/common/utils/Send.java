package com.wl.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Administrator
 */
public class Send {

    public static String SMS(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }
    
    /*
	public  static void sendShortMessage(String sentcontent,String msgmobiletel){
		 
		 String msgurl = PropertyHelpers.getKey("msg.url");//短信发送通道地址
		 String username = PropertyHelpers.getKey("msg.username");
		 String userpass = PropertyHelpers.getKey("msg.userpass");
		 String id = PropertyHelpers.getKey("msg.id");
		 //构造参数 
		 String str ="sname="+username+"&spwd="+userpass+"&scorpid=&sprdid="+id+"&sdst="+msgmobiletel+"&smsg="+sentcontent;
		 Send.SMS(str,msgurl);
	}*/
}
