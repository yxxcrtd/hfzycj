package com.zycj.server.util;

import java.io.ByteArrayOutputStream;
import java.security.PublicKey;

import javax.crypto.Cipher;



public class RsaCodeUtils {
	private static final int MAX_ENCRYPT_BLOCK = 117;// RSA最大加密明文大小
	private static final int MAX_DECRYPT_BLOCK = 128;// RSA最大解密密文大小
	
	public static String encryptDataByRSA64(String data) throws Exception {
		byte[] bytes = encryptByPublic(data.getBytes(),MyKeyFactory.getPublicKey());
		String dataEn = encryptBASE64(bytes);
		dataEn = dataEn.replaceAll("\\+", "-");
		dataEn = dataEn.replaceAll("/", "_");
		dataEn = dataEn.replaceAll("=", ",");
		dataEn = dataEn.replace("\r\n", "");
		dataEn = dataEn.replace("\n", "");
		return dataEn;
	}
	
	/**
	 * 公钥加密
	 * 
	 * @param data
	 *            源数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublic(byte[] data, PublicKey publicKey)
			throws Exception {
		// 对数据加密
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}
	
	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static String encryptBASE64(byte[] key) throws Exception {
		return Base64.encode(key);
	}
}
