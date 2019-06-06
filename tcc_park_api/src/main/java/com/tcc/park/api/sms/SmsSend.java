package com.tcc.park.api.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tcc.park.api.common.SmsConfig;


public class SmsSend {
	
	private static Logger logger = Logger.getLogger(SmsSend.class);
	
	public static String sendSms(String mobile,String msgContent) {
		String result = "0";
		if (StringUtils.isBlank(mobile)||StringUtils.isBlank(msgContent)) {
			return result;
		}
//		cdkey	用户序列号。
//		password	用户密码
//		phone	手机号码（最多200个），多个用英文逗号(,)隔开。
//		message	短信内容（UTF-8编码）（最多500个汉字或1000个纯英文）。
//		addserial	附加号（最长10位，可置空）。
//		seqid	长整型值企业内部必须保持唯一，获取状态报告使用
//		smspriority	短信优先级1-5
		StringBuffer getData = new StringBuffer();
		getData.append("?cdkey=").append(SmsConfig.SNAME);
		getData.append("&password=").append(SmsConfig.SPWD);
		getData.append("&phone=").append(mobile);
		try {
			getData.append("&message=").append(URLEncoder.encode(msgContent, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			logger.warn("URLEncoder 短信内容异常",e1);
			return result;
		}
		getData.append("&smspriority=").append(3);
		HttpURLConnection conn =null;
		BufferedReader in =null;
		try {
			URL url = new URL(SmsConfig.URL+getData.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5*1000);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
            // 建立实际的连接  
            conn.connect();  
			// 获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				logger.info("connect failed!!url:"+SmsConfig.URL);
				return result;
			}
			// 获取响应内容体
			String line, resp = "";
			in= new BufferedReader(new InputStreamReader(conn
					.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				resp += line + "\n";
			}
			if(resp!=null){
				resp=resp.trim();
			}
			in.close();
			logger.info("[mobile="+mobile+"] SmsSendResult:"+resp);
			if(resp.contains("<error>")&&resp.contains("</error>")){
				return resp.substring(resp.indexOf("<error>")+7,resp.indexOf("</error>"));
			}else{
				return result;
			}
		} catch (Exception e) {
			logger.warn("发送短信异常",e);
			return result;
		}finally{
			try{  
                if (in != null){  
                    in.close();  
                }  
            }catch (IOException ex){  
            } 
			if (conn != null) {
				//关闭连接 即设置 http.keepAlive = false;
				conn.disconnect();
			}
		}
	}
	
//	public static String sendSms_delete(String mobile,String msgContent) {
//		String result = "0";
//		if (StringUtils.isBlank(mobile)||StringUtils.isBlank(msgContent)) {
//			return result;
//		}
//		StringBuffer postData = new StringBuffer();
//		postData.append("sname=").append(SmsConfig.SNAME);
//		postData.append("&spwd=").append(SmsConfig.SPWD);
//		postData.append("&scorpid=&sprdid=").append(SmsConfig.SPRDID);
//		postData.append("&sdst=").append(mobile);
//		postData.append("&smsg=").append(msgContent);
//		// 发送POST请求
//		try {
//			URL url = new URL(SmsConfig.URL);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Type",	"application/x-www-form-urlencoded");
//			conn.setRequestProperty("Connection", "Keep-Alive");
//			conn.setUseCaches(false);
//			conn.setDoOutput(true);
//			conn.setRequestProperty("Content-Length", ""+postData.length());
//			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
//			out.write(postData.toString());
//			out.flush();
//			out.close();
//			// 获取响应状态
//			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
//				logger.info("connect failed!!url:"+SmsConfig.URL);
//				return result;
//			}
//			// 获取响应内容体
//			String line, resp = "";
//			BufferedReader in = new BufferedReader(new InputStreamReader(conn
//					.getInputStream(), "utf-8"));
//			while ((line = in.readLine()) != null) {
//				resp += line + "\n";
//			}
//			in.close();
//			logger.info("mobile="+mobile+resp);
//			if(resp.contains("<MsgID>")&&resp.contains("</MsgID>")){
//				return resp.substring(resp.indexOf("<MsgID>")+7,resp.indexOf("</MsgID>"));
//			}else{
//				return result;
//			}
//		} catch (Exception e) {
//			return result;
//		}
//	}
	
	public static void main(String[] args) {
		sendSms("18668299908", "您本次的验证码：#code#【合肥城泊】");
	}

}
