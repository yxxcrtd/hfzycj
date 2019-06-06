package com.hfzycj.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * 公钥和私钥的生成类
 */
public class MyKeyFactory {
	private static String publicKeyJavaStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbnZyZjStTTiQhwUQ6BSFryLCe1xJoYEWMHr/gU1lQezfJ27tVNd9aQadKv2d08YEcvZX8gg83JyE60llpimRh6SXkabV+LIzNNowNxtY/gS6+5gXfdZKspYPzL+EuSpp9SaDf7MpL4ykQq0U8+OfgKWhxUZtFjYnLJ4kCrR0DqQIDAQAB";
	
	/**
	 * 得到公钥
	 * 
	 * @param key 密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PublicKey getPublicKey() throws Exception {
		byte[] keyBytes;
		keyBytes = Base64.decode(publicKeyJavaStr);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 得到私钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	// public static PrivateKey getPrivateKey() throws Exception {
	// byte[] keyBytes;
	// keyBytes = Base64.decode(privateKeyJavaStr);
	// PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
	// KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	// PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
	// return privateKey;
	// }

	/**
	 * 得到密钥字符串（经过base64编码）
	 * 
	 * @return
	 */
	public static String getKeyString(Key key) throws Exception {
		byte[] keyBytes = key.getEncoded();
		String s = Base64.encode(keyBytes);
		return s;
	}

}
