package com.zycj.tcc.service;

import java.util.Map;

import com.zycj.tcc.server.vo.DataResponse;

public interface PrePayCardService {
	public void chongzhi(Map<String, String> params, DataResponse dataResponse, String amount);

	DataResponse pay(Map<String, String> params);

	int getAmountByCardNo(String cardNo) throws Exception;

	boolean deal(String cardNo, String amount, String empNo, String discount) throws Exception;

	void saveErrorChongzhi(Map<String, Object> pp_params);
	
	String buildOrderId();
}
