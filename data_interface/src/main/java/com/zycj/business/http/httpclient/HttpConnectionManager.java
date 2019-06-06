package com.zycj.business.http.httpclient;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpConnectionManager {
	private static Logger log = LoggerFactory.getLogger(HttpConnectionManager.class);
	/**
	 * 最大连接数
	 */
	public final static int MAX_TOTAL_CONNECTIONS = 200;
	/**
	 * 每个路由的最大连接数
	 */
	public final static int MAX_ROUTE_CONNECTIONS = 200;
	/**
	 * 连接超时时间
	 */
	public final static int CONNECT_TIMEOUT = 5000;
	/**
	 * 获取数据的最大等待时间
	 */
	public final static int WAIT_TIMEOUT = 10000;

	private HttpConnectionManager() {
		createHttpClient();
	}

	private static final HttpConnectionManager single = new HttpConnectionManager();

	public static HttpConnectionManager getInstance() {
		return single;
	}

	private PoolingHttpClientConnectionManager cm;

	public static IdleConnectionMonitorThread idleConnectionMonitorThread;

	private volatile CloseableHttpClient httpClient = null;

	private CloseableHttpClient createHttpClient() {
		if (httpClient == null) {
			synchronized (HttpConnectionManager.class) {
				if (httpClient == null) {
					RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder
							.<ConnectionSocketFactory> create();
					registryBuilder.register("http", new PlainConnectionSocketFactory());
					// 指定信任密钥存储对象和连接套接字工厂
					try {
						KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
						SSLContext sslContext = SSLContexts.custom().useTLS()
								.loadTrustMaterial(trustStore, new TrustStrategy() {
									@Override
									public boolean isTrusted(X509Certificate[] chain, String authType)
											throws CertificateException {
										return true;
									}
								}).build();
						LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext,
								SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
						registryBuilder.register("https", sslSF);
						cm = new PoolingHttpClientConnectionManager(registryBuilder.build());
					} catch (Exception e) {
						log.warn("创建httpClient异常", e);
						cm = new PoolingHttpClientConnectionManager();
					}

					cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);
					cm.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
					idleConnectionMonitorThread = new IdleConnectionMonitorThread(cm);
					idleConnectionMonitorThread.start();
					httpClient = HttpClients.custom().setConnectionManager(cm).build();
					log.warn("create... PoolingHttpClientConnectionManager[{MaxTotal=" + MAX_TOTAL_CONNECTIONS
							+ ",DefaultMaxPerRoute=" + MAX_ROUTE_CONNECTIONS + "}]. httpClient:" + httpClient);
				}
			}
		}
		return httpClient;
	}

	public CloseableHttpClient getHttpClient() {
		return createHttpClient();
	}
}
