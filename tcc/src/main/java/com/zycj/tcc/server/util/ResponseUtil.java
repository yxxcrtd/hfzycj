package com.zycj.tcc.server.util;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zycj.tcc.server.vo.DataResponse;
import com.zycj.tcc.server.vo.PagingResponse;
import com.zycj.tcc.server.vo.Response;

/**
 * 响应工具类
 * @author 洪捃能
 */
public class ResponseUtil {
	public static final SerializerFeature[] sfs={
		SerializerFeature.WriteDateUseDateFormat,
		SerializerFeature.WriteMapNullValue,
		SerializerFeature.WriteNullStringAsEmpty,
		SerializerFeature.WriteNullNumberAsZero
	};
	public static Response createResponse(String code){
		Response res=new Response();
		res.setResultCode(code);
		return res;
	}
	public static DataResponse createDataResponse(String code,Object data){
		DataResponse res=new DataResponse();
		res.setResultCode(code);
		res.setData(data);
		return res;
	}
	public static PagingResponse createPagingResponse(String code,Object data,Object paging){
		PagingResponse pr=new PagingResponse();
		pr.setResultCode(code);
		pr.setData(data);
		pr.setPaging(paging);
		return pr;
	}
	
}
