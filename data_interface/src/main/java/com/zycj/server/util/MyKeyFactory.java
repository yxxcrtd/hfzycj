package com.zycj.server.util;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class MyKeyFactory {
	private static String publicKeyJavaStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoI9jxt0uV5OmNmB8fLThPUJ4vjkjz6DtU3GRKR0tDkp+Uoqh/yPBJ/4exKh9procP1rcjNiMXz5Uuun1lS77GiD6neAe52YMh1EXZ3SeJPJjYQqs6wS82v9+xJIVlHXp1nMzZhpYMMPIXlhqnUIgSZOyNG0nenNSHU+tNZ5reNwIDAQAB";

	// private static String privateKeyJavaStr = "";

	/**
	 * 得到公钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码）
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
}
