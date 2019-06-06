package com.tcc.park.api.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author xiechanglei
 * Md5加密 分16位和32位
 */
public class MD5Util {
	
	public static String Md516(String value){
		return Md532(value).substring(8, 24);
	}
	
	public static String  Md516(byte[] bytes){
		return Md532(bytes).substring(8,24);
	}
	 
    public static String Md532(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] e = md.digest(value.getBytes());
            return toHexString(e);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return value;
        }
    }
 
    public static String Md532(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] e = md.digest(bytes);
            return toHexString(e);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
 
    private static String toHexString(byte bytes[]) {
        StringBuilder hs = new StringBuilder();
        String stmp = "";
        for (int n = 0; n < bytes.length; n++) {
            stmp = Integer.toHexString(bytes[n] & 0xff);
            if (stmp.length() == 1)
                hs.append("0").append(stmp);
            else
                hs.append(stmp);
        }
 
        return hs.toString();
    }
}
