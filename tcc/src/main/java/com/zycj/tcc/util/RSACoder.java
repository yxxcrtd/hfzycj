package com.zycj.tcc.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.io.FileUtils;

/** */
/**
 * RSA安全编码组件
 * 
 * @version 1.0
 * @since 1.0
 */
public class RSACoder extends Coder {
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	private static final String PUBLIC_KEY = "RSAPublicKey";
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	/** */
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/** */
	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;
	
	private static String PUBLICKEY_STRING ;
	
//	private static String PRIVATEKEY_STRING="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKgj2PG3S5Xk6Y2YHx8tOE9Qni+OSPPoO1TcZEpHS0OSn5SiqH/I8En/h7EqH2muhw/WtyM2IxfPlS66fWVLvsaIPqd4B7nZgyHURdndJ4k8mNhCqzrBLza/37EkhWUdenWczNmGlgww8heWGqdQiBJk7I0bSd6c1IdT601nmt43AgMBAAECgYBZmaDGTpMs8au0FNig2IwlWCSz2vLtZ1gWxMt8+bkV7VC0+Gkqj33dMRqyQmwy+RpoSleVoAfbbHdnS2H/VPn4Gp/rysMpiG7cBO//5VCU+S3yMWEEcdbUVaPYVUIcJxq9jBSZjIiNjlo9JXDPhEixicm5gsYPv7FakedLYb0XAQJBAPCHGT10BWB+DWATb+LD0wpJEI57Dl6T32RgaFlymFG61nhc5cJRpQZJjP+peT30Qokw3/uRZtCAu3Omnjf9F+ECQQCy9LN51GJvomH6x4ESwGhHOa1C7XwHZSP0X+WIwtxxveMO12nVUQZi/DKCot8piLcfaTOz6T1Czf0rdmcqoNkXAkEAmhqktDfSfhkk7qplZA989jkLz43ODhCF7yTJlo2SQ8MGSEWUu2Rd6+JR4Teuw1/tBUL4LpDhI7Nm5lkZQ9v3YQJAVT3L878fuE8f3qWDLNxrj2nAxv16M8eQbcnz+sXe3Wr2qc4gAlUa9rFsmd9c71rI53RkBVGXNagK7NX0ZFDDtwJBALOyEsq48yCKl0dQa+6DyUSwQquM21HzYVqUKAfQg97IpoE1sBYrz1lvWyiQjJXhXqqtfO27GZoK92YoVPvK64k=";
	private static String PRIVATEKEY_STRING;

	/** */
	/**
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data
	 *            加密数据
	 * @param privateKey
	 *            私钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		// 解密由base64编码的私钥
		byte[] keyBytes = decryptBASE64(privateKey);
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 取私钥匙对象
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(data);

		return encryptBASE64(signature.sign());
	}

	/** */
	/**
	 * 校验数字签名
	 * 
	 * @param data
	 *            加密数据
	 * @param publicKey
	 *            公钥
	 * @param sign
	 *            数字签名
	 * 
	 * @return 校验成功返回true 失败返回false
	 * @throws Exception
	 * 
	 */
	public static boolean verify(byte[] data, String publicKey, String sign)
			throws Exception {

		// 解密由base64编码的公钥
		byte[] keyBytes = decryptBASE64(publicKey);

		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 取公钥匙对象
		PublicKey pubKey = keyFactory.generatePublic(keySpec);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(data);

		// 验证签名是否正常
		return signature.verify(decryptBASE64(sign));
	}

	/** */
	/**
	 * 解密<br>
	 * 用私钥解密 http://www.5a520.cn http://www.feng123.com
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String key)
			throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);

		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

		// X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// Key privateKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	/** */
	/**
	 * 解密<br>
	 * 用私钥解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, String key)
			throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);

		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	/** */
	/**
	 * 加密<br>
	 * 用公钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String key)
			throws Exception {
		// 对公钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	/** */
	/**
	 * 加密<br>
	 * 用私钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String key)
			throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);

		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	/** */
	/**
	 * 取得私钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);

		return encryptBASE64(key.getEncoded());
	}

	/** */
	/**
	 * 取得公钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);

		return encryptBASE64(key.getEncoded());
	}

	/** */
	/**
	 * 初始化密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator
				.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);

		KeyPair keyPair = keyPairGen.generateKeyPair();

		// 公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

		// 私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		Map<String, Object> keyMap = new HashMap<String, Object>(2);

		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	public static byte[] encryptByPublicKey(byte[] data, byte[] key)
			throws Exception {
		// 对公钥解密
		byte[] keyBytes = key;// decryptBASE64(key);

		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}
	
    /** 
     * RSA BASE64 替换字符
     * @param data
     * @return
     * @throws Exception
     */
	public static String encryptDataByRSA64(String data) throws Exception {
		String key = getPublicKeyString();
		byte[] bytes = encryptByPublic(data.getBytes(), key);
		String dataEn = encryptBASE64(bytes);
		dataEn = dataEn.replaceAll("\\+", "-");
		dataEn = dataEn.replaceAll("/", "_");
		dataEn = dataEn.replaceAll("=", ",");
		dataEn = dataEn.replace("\r\n", "");
		dataEn = dataEn.replace("\n", "");
		return dataEn;
	}

	/**
	 * 替换字符 base64 rsa
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String decryptDataByRSA64(String data) throws Exception {
		String key = getPrivateKeyString();
		data = data.replaceAll("-", "\\+");
		data = data.replaceAll("_", "/");
		data = data.replaceAll(",", "=");
		byte[] bytes = decryptByPrivate(decryptBASE64(data), key);
		return new String(bytes);
	}
	
	/** */
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
			String privateKey) throws Exception {
		byte[] keyBytes = decryptBASE64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateK);
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
	public static byte[] encryptByPublic(byte[] data, String publicKey)
			throws Exception {
		byte[] keyBytes = decryptBASE64(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
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
	
	private static String getPublicKeyString() throws Exception{
		if (PUBLICKEY_STRING == null) {
			PUBLICKEY_STRING = FileUtils
					.readFileToString(new File(RSACoder.class.getResource("/app_public_key.pem").getFile()));
		}
		return PUBLICKEY_STRING;
	}
	
	private static String getPrivateKeyString() throws Exception{
		if (PRIVATEKEY_STRING == null) {
			InputStream input = RSACoder.class.getClassLoader().getResourceAsStream("app_private_key.pem");
			byte b[] = new byte[1024];  
			input.read(b);
	        input.close();
			PRIVATEKEY_STRING = new String(b);
		}
		return PRIVATEKEY_STRING;
	}

	public static void main(String[] args) throws Exception {
		 String data = "hahah12133";
//		 String pubKey= FileUtils.readFileToString(new File("D:\\workspace\\ctz-web-site\\src\\main\\resources\\app_public_key.pem"));
//		 byte[] enBytes = encryptByPublicKey(data.getBytes(),pubKey);
//		 
//		 String priKey= FileUtils.readFileToString(new File("D:\\workspace\\ctz-web-site\\src\\main\\resources\\app_private_key.pem"));
//		 byte[] deBytes=  decryptByPrivate(enBytes,priKey);
//		 
//		 String priKey= FileUtils.readFileToString(new File("E:\\Workspace\\batcc\\src\\main\\resources\\app_private_key.pem"));
//		 System.out.println(priKey);
		 RSACoder r=new RSACoder();
		 System.out.println(RSACoder.class.getResource("/app_private_key.pem").getFile());
		 
//		 String pubKey= FileUtils.readFileToString(new File("D:\\workspace\\ctz-web-site\\src\\main\\resources\\app_public_key.pem"));
//		 byte[] enBytes = encryptByPublicKey(encryptBASE64(data.getBytes()).getBytes(),pubKey);
//		 System.err.println("enString="+new String(enBytes));
//		 String priKey= FileUtils.readFileToString(new File("D:\\workspace\\ctz-web-site\\src\\main\\resources\\app_private_key.pem"));
//		 byte[] deBytes=  decryptBASE64(new String(decryptByPrivate(enBytes,priKey)));
//		 System.err.println("deString="+new String(deBytes));
		 
//		 String enString = encryptDataByRSA64("sendType=1&codeType=2&sendMobile=18019907186&sendEmail=234234@dafd.com");
////		 String enString = encryptDataByRSA64("verifieCode=551148&codeType=1&sendMobile=15256586585");
//		 System.out.println(enString);
//		 String deString = decryptDataByRSA64("LnjvWAQI79hfwZmESQa3xcVECprJMpC6JsLkqK53gKyzzFKgaG969wpboPFH_HSivVX9hDBAF1jQSFa_Kx5SQpchHQLBpIRhzR832LUoDdjfBBwN6_l-_zDJ2eqQDaqfICDNJ7UnuBVruoSM8EB8aa6LNALFq14H-zV3zVlukw8,");
//		 System.out.println(deString);
		 
		//生成公钥，私钥
//		 String publicKey;  
//	     String privateKey;  
//		 Map<String, Object> keyMap = RSACoder.initKey();  
//        publicKey = RSACoder.getPublicKey(keyMap);  
//        privateKey = RSACoder.getPrivateKey(keyMap);  
//        System.err.println("公钥: \n\r" + publicKey);  
//        System.err.println("私钥： \n\r" + privateKey); 
      //生成公钥，私钥
	}
}
