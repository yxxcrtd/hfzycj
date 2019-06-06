package com.zycj.tcc.service;

import com.zycj.tcc.server.vo.Response;

public interface SystemService {
	
	/**
	 * 校验IP地址、端口号、终端编号
	 * @param posNo pos终端编号
	 * @return
	 */
	public Response checkPosForBind(String posNo);
}
