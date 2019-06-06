package com.wl.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

	private static final String CHARSET = "UTF-8";
//	private static final Integer CONNECTION_TIMEOUT = 3000;
//	private static final Integer SO_TIMEOUT = 3000;

	/**
	 * HTTP Get 获取内容
	 * 
	 * @param url
	 *            请求的url地址 ?之前的地址
	 * @param params
	 *            请求的参数
	 * @return 页面内容
	 */
	public static String doGet(String url, Map<String, String> params) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		String result = null;
		RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
//		HttpClient httpClient = new DefaultHttpClient();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();
		try {
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(
						params.size());
				for (Map.Entry<String, String> entry : params.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value));
					}
				}
				url += "?"+ EntityUtils.toString(new UrlEncodedFormEntity(pairs,CHARSET));
			}
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :"
						+ statusCode);
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}

	/**
	 * HTTP Post 获取内容
	 * 
	 * @param url 请求的url地址 ?之前的地址
	 * @param params 请求的参数
	 * @return 页面内容
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String doPost(String url, Map<String, String> params) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		String result = null;
		RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();
		try {
			List<NameValuePair> pairs = null;
			if (params != null && !params.isEmpty()) {
				pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, String> entry : params.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value));
					}
				}
			}
			HttpPost httpPost = new HttpPost(url);
			if (pairs != null && pairs.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
			}
			// 请求超时
			//httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,CONNECTION_TIMEOUT);
			// 读取超时
//			//httpClient.getParams().setParameter(
//					CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}
	
	/****
	 * 发送xml格式数据
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doxmlPost(String url, String params) {
		String result = null;
		FileInputStream instream = null;
		try {
			if (StringUtils.isBlank(url)) {
				return null;
			}
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			instream = new FileInputStream(new File(SystemConfigUtil.getValue("WX.LOCAL")));// 加载本地的证书进行https加密传输
			keyStore.load(instream, SystemConfigUtil.getValue("WX.MACHID").toCharArray());// 设置证书密码
			SSLContext sslcontext = SSLContexts.custom()
					.loadKeyMaterial(keyStore, SystemConfigUtil.getValue("WX.MACHID").toCharArray()).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			// 根据默认超时限制初始化requestConfig
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(30000)
					.build();
			// RequestConfig config =
			// RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
			// CloseableHttpClient httpClient =
			// HttpClients.custom().setDefaultRequestConfig(config).build();
			HttpPost post = new HttpPost(url);
			// 设置请求器的配置
			post.setConfig(requestConfig);
			post.setHeader("Content-Type", "text/xml;charset=UTF-8");
			post.addHeader("Connection", "keep-alive");
			post.addHeader("Accept", "*/*");
			post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			post.addHeader("Host", "api.mch.weixin.qq.com");
			post.addHeader("X-Requested-With", "XMLHttpRequest");
			post.addHeader("Cache-Control", "max-age=0");
			post.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
			StringEntity entity = new StringEntity(params,"UTF-8");
			post.setEntity(entity);  
			HttpResponse response = httpClient.execute(post);
			HttpEntity entitys = response.getEntity();
			result = EntityUtils.toString(entitys, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeyStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				instream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
//	public static void main(String[] args) {
//		Map<String, String> map=new HashMap<String,String>();
//		map.put("tel", "28039579868");
//		String str=HttpClientUtils.doPost("http://qxt.ko.cn:8080/ICTGWS/getCheckCode.do", map);
//		System.out.println(str);
		
		
//		Map<String, String> map=new HashMap<String,String>();
//		map.put("tel", "18039579868");
//		map.put("userCheckCode", "j513");
//		map.put("identifierStr", "498n407B");
//		map.put("signature", "ZDJZB000S99");
//		map.put("templateID", "model1");
//		map.put("contentParams", "j513");
//		
//		String str=HttpClientUtils.doPost("http://qxt.ko.cn:8080/ICTGWS/sendsms.do", map);
//		System.out.println(str);
		
//	}
}
