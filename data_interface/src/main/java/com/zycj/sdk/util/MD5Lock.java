package com.zycj.sdk.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class MD5Lock {
	private static final Logger LOG = Logger.getLogger(MD5Lock.class);

	private MD5Lock() {
	}

	public static String lock(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			LOG.debug("初始化md5失败！", e);
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuilder hexValue = new StringBuilder();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
}
