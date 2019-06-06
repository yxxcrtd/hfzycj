package com.zycj.business.park.controller;

import java.util.HashMap;
import java.util.Map;

import com.zycj.server.netty.httpclient.NettyClient;
import com.zycj.server.util.MapUtils;
import com.zycj.server.util.RsaCodeUtils;

public class NettyTest {

	/**
	 * @description 
	 * @author fengya
	 * @date 2016-8-2 上午10:27:35
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		request("1","1","1");
//		byte[] tmp=new byte[buf.readableBytes()];
//		buf.readBytes(tmp);
//		String parm=new String(tmp,CharsetUtil.UTF_8);
//		parm=RSACoder.decryptDataByRSA64(parm);
	}
	
	

	private static void request(String carNo,String spaceNo,String orderNo){
		HashMap<String, String> parms = new HashMap<String, String>();
		parms.putAll(MapUtils.getPublicMap("0005"));
		parms.put("car_no", carNo);
		parms.put("space_no", spaceNo);
		parms.put("order_no", orderNo);
		String rsaData;
		try {
			rsaData = RsaCodeUtils.encryptDataByRSA64(getParamesStr(parms));
			NettyClient nc = new NettyClient("127.0.0.1", 9090);
			nc.request(rsaData);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public static String getParamesStr(HashMap<String, String> params) {
		String values = "";
		if (params != null && params.size() > 0) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				values = values + entry.getKey() + "=" + entry.getValue() + "&";
			}
			values = values.substring(0, values.length() - 1);
		}
		return values;
	}
}
