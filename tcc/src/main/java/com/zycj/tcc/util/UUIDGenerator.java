package com.zycj.tcc.util;

import java.util.UUID;

public class UUIDGenerator {

	/** 
     * UUID工具类
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        return UUID.randomUUID().toString(); 
    } 
    
    public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(getUUID());
		}
	}
}
