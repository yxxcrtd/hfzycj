package com.wl.common.utils;

import java.util.UUID;

public class UUIDUtil {
	
	private UUIDUtil(){
		
	}
	
	public static String genUUID32(){
		String suuid=UUID.randomUUID().toString();
		return suuid.replace("-", "");
	}
	
	public static String genUUID10(){
		String suuid=UUID.randomUUID().toString();
		return suuid.replace("-", "").substring(0, 10);
	}

}
