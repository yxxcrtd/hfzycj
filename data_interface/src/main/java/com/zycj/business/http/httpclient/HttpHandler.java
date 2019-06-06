package com.zycj.business.http.httpclient;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpHandler {
	private final static Logger log= Logger.getLogger(HttpHandler.class);
	private static CloseableHttpClient httpClient = HttpConnectionManager.getInstance().getHttpClient();
	
	public static <T> T get(String url,String charset,HttpCallBack<T> cb) throws Exception{
		CloseableHttpResponse response =null;
		try {
			HttpGet httpget = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HttpConnectionManager.WAIT_TIMEOUT)
					.setConnectTimeout(HttpConnectionManager.CONNECT_TIMEOUT).build();// 设置请求和传输超时时间
			httpget.setConfig(requestConfig);
			
			response = httpClient.execute(httpget, HttpClientContext.create());
			if(cb!=null){
				return cb.todo(EntityUtils.toString(response.getEntity(),charset));
			}
			return null;
		}finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					log.warn("关闭get请求response异常", e);
				}
			}
		}
	}
	
	public static <T> T post(String url,String postData,String charset,String contentType,HttpCallBack<T> cb) throws Exception{
		if(StringUtils.isBlank(postData)){
			postData="";
		}
		if(StringUtils.isBlank(contentType)){
			contentType="application/x-www-form-urlencoded";
		}
		if(StringUtils.isBlank(charset)){
			charset="UTTF-8";
		}
		CloseableHttpResponse response =null;
		try {
			HttpPost httpPost = new HttpPost(url);
			ByteArrayEntity entity = new ByteArrayEntity(postData.getBytes(charset), ContentType.create(contentType+";charset="+charset));
			httpPost.setEntity(entity);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HttpConnectionManager.WAIT_TIMEOUT)
					.setConnectTimeout(HttpConnectionManager.CONNECT_TIMEOUT).build();// 设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			response = httpClient.execute(httpPost, HttpClientContext.create());
			if(cb!=null){
				return cb.todo(EntityUtils.toString(response.getEntity(),charset));
			}
			return null;
		}finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					log.warn("关闭post请求response异常", e);
				}
			}
		}
	}
}
