package com.tcc.park.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

public class HttpUtil {

	public static String get(String url, Map<String, Object> params, String encode) throws Exception {
		String param = getParamFromMap(params,encode);
		url = url + "?" + param;
		HttpURLConnection openConnection = null;
		InputStream inputStream = null;
		String result = "";
		try {
			URL u = new URL(url);
			openConnection = (HttpURLConnection) u.openConnection();
			inputStream = openConnection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encode));
			String readline = null;
			while ((readline = reader.readLine()) != null) {
				result += readline;
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeAll(openConnection, null, inputStream);
		}
	}

	/**
	 * 采用post方式请求url结果
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数的HashMap
	 * @param encode
	 *            返回结果的编码方式
	 * @return
	 */
	public static String post(String url, Map<String, Object> params, String encode) {
		String param ="";
		try {
			param = getParamFromMap(params,null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return post(url, param, encode);
	}

	private static String getParamFromMap(Map<String, Object> params, String encode) throws UnsupportedEncodingException {
		Set<Entry<String, Object>> entrySet = params.entrySet();
		StringBuffer sb = new StringBuffer("");
		for (Entry<String, Object> entry : entrySet) {
			if(encode!=null){
				sb.append(entry.getKey() + "=" +URLEncoder.encode(entry.getValue().toString(),encode));
			}else{
				sb.append(entry.getKey() + "=" + entry.getValue());
			}
			sb.append("&");
		}
		String param = sb.toString();
		if (param != null) {
			param = param.substring(0, param.length());
		}
		return param;
	}

	/**
	 * @Title: post
	 * @Description: 发送post请求
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param encode
	 *            结果编码
	 * @return
	 * @throws
	 *
	 * 			@author
	 *             xiechanglei
	 * @time 2015年11月18日 下午4:35:27
	 */
	public static String post(String url, String params, String encode) {
		HttpURLConnection openConnection = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		String result = "";
		try {
			URL u = new URL(url);
			openConnection = (HttpURLConnection) u.openConnection();
			openConnection.setDoOutput(true);
			openConnection.connect();
			outputStream = openConnection.getOutputStream();
			PrintWriter pWriter = new PrintWriter(outputStream);
			pWriter.println(params);
			pWriter.flush();
			inputStream = openConnection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encode));
			String readline = null;
			while ((readline = reader.readLine()) != null) {
				result += readline;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeAll(openConnection, outputStream, inputStream);
		}
		return result;
	}

	/**
	 * @Title: closeAll
	 * @Description: 关闭输入输出流
	 * @param openConnection
	 * @param outputStream
	 * @param inputStream
	 * @throws
	 *
	 * 			@author
	 *             xiechanglei
	 * @time 2015年11月18日 下午4:36:57
	 */
	private static void closeAll(HttpURLConnection openConnection, OutputStream outputStream, InputStream inputStream) {
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
			}
		}
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
		openConnection.disconnect();
	}

	public static String postHttps(String url, Map<String, Object> params, String encode) {
		String param="";
		try {
			param = getParamFromMap(params,null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(param);
		return postHttps(url, param, encode);
	}

	/**
	 * https 的方式
	 * 
	 * @param url
	 * @param param
	 * @param encode
	 * @return
	 */
	public static String postHttps(String url, String param, String encode) {
		HttpsURLConnection openConnection = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		String result = "";
		try {
			URL u = new URL(url);
			openConnection = (HttpsURLConnection) u.openConnection();
			openConnection.setDoOutput(true);
			openConnection.connect();
			outputStream = openConnection.getOutputStream();
			PrintWriter pWriter = new PrintWriter(outputStream);
			pWriter.println(param);
			pWriter.flush();
			inputStream = openConnection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encode));
			String readline = null;
			while ((readline = reader.readLine()) != null) {
				result += readline;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeAll(openConnection, outputStream, inputStream);
		}
		return result;
	}
}
