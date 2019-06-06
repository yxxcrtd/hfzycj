package com.zycj.tcc.server.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.zycj.tcc.server.domain.TransactionData;

/**
 * 交易数据帮助类
 * @author 洪捃能
 *
 */
@Deprecated
public class TransactionDataHelper {

	public static Map<String,TransactionData> bizDataMap=new ConcurrentHashMap<String,TransactionData>();
	
	public static void put(String key,TransactionData val){
		bizDataMap.put(key, val);
	}
	public static TransactionData get(String key){
		return bizDataMap.get(key);
	}
	public static TransactionData remove(String key){
		return bizDataMap.remove(key);
	}
}
