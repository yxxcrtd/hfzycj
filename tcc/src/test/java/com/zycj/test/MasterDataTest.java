package com.zycj.test;

import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.server.vo.Request;
import com.zycj.tcc.server.vo.DataResponse;
import com.zycj.tcc.util.ParamsUtil;

public class MasterDataTest {

	@Test
	public void RequestData1() throws Exception{
		String parm="interfaceType=1001&systemCode=pos&securityCode=zycj&parm1=val1&parm2=val2";
		Request rd=new Request();
		Map<String, String> paramMap =ParamsUtil.getParameters(parm);
		String[] keys=new String[]{"interfaceType","systemCode","securityCode"};
		ParamsUtil.setParametersToObject(paramMap, keys, rd);
		rd.setParameter(paramMap);
		String jo=JSON.toJSONString(rd);
		System.out.println(jo);
	}
	
	@Test
	public void ResponseData1(){
		DataResponse rd=new DataResponse();
		Response md=rd;
	}
}
