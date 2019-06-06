package com.zycj.business.park.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.zycj.server.util.NettyParamsUtil;

public class PayConfig {
	private static String wxPartnerKey;
	private static String aliPartner;
	private static String alipayVerifyUrl;
	private static String aliPublicKey;
	private static Integer payTimes;

	static {
		InputStream is = null;
		try {
			is = NettyParamsUtil.class.getClassLoader().getResourceAsStream("pay_config.properties");
			Properties prop = new Properties();
			prop.load(is);
			wxPartnerKey = prop.getProperty("wx.partnerKey");
			aliPartner = prop.getProperty("ali.partner");
			alipayVerifyUrl = prop.getProperty("ali.alipayVerifyUrl");
			aliPublicKey = prop.getProperty("ali.publicKey");
			payTimes = Integer.parseInt(prop.getProperty("pay_times"));
		} catch (IOException e) {
			System.out.println("初始化微信配置参数出现了异常");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// e.printStackTrace();
				}
			}
		}
	}

	public static Integer getPayTimes() {
		return payTimes;
	}

	public static String getAliPublicKey() {
		return aliPublicKey;
	}

	public static String getWxPartnerKey() {
		return wxPartnerKey;
	}

	public static String getAliPartner() {
		return aliPartner;
	}

	public static String getAlipayVerifyUrl() {
		return alipayVerifyUrl;
	}
	
}
