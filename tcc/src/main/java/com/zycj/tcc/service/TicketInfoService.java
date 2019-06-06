package com.zycj.tcc.service;

import java.util.List;

import com.zycj.tcc.domain.TicketInfo;
import com.zycj.tcc.server.vo.Response;

public interface TicketInfoService {

	/**
	 * 根据月票编号查询月票信息
	 * @param ticketNo 月票编号
	 * @return
	 */
	public Response selectTicketInfoByTicketNo(String ticketNo, int type);
	
	public List<TicketInfo> getTicketInfoByCarNo(String carNo);

}
