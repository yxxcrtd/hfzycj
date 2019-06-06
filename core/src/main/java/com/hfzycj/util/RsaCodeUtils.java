package com.hfzycj.util;

import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

/**
 * RSA加解密工具类
 * 
 * @author HuangZhong Email:mcloyal@163.com
 * @version 创建时间：2014-1-17 上午9:27:54
 */
public class RsaCodeUtils {
	private static final int MAX_ENCRYPT_BLOCK = 117;// RSA最大加密明文大小
	private static final int MAX_DECRYPT_BLOCK = 128;// RSA最大解密密文大小
	
	/**
	 * 执行加密数据的操作
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptDataByRSA64(String data) throws Exception {
		byte[] bytes = encryptByPublic(data.getBytes(), MyKeyFactory.getPublicKey());
		String dataEn = encryptBASE64(bytes);
		dataEn = dataEn.replaceAll("\\+", "-");
		dataEn = dataEn.replaceAll("/", "_");
		dataEn = dataEn.replaceAll("=", ",");
		dataEn = dataEn.replace("\r\n", "");
		dataEn = dataEn.replace("\n", "");
		return dataEn;
	}

	/** */
	/**
	 * <p>
	 * 公钥加密
	 * </p>
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
	 * 把中文转成Unicode码
	 * 
	 * @param str
	 * @return
	 */
	public static String chinaToUnicode(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int chr1 = str.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
				result += "\\u" + Integer.toHexString(chr1);
			} else {
				result += str.charAt(i);
			}
		}
		return result;
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

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return Base64.decode(key);
	}

	/**
	 * 执行解密操作
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
//	public static String decryptDataByRSA64(String data) throws Exception {
//		data = data.replaceAll("-", "\\+");
//		data = data.replaceAll("_", "/");
//		data = data.replaceAll(",", "=");
//		byte[] bytes = decryptByPrivate(decryptBASE64(data),
//				MyKeyFactory.getPrivateKey());
//		return new String(bytes);
//	}

	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivate(byte[] encryptedData,
			PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher
						.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher
						.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}
}
