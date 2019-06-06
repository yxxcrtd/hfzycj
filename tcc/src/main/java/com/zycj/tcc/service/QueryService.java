package com.zycj.tcc.service;

import com.zycj.tcc.server.vo.Response;

public interface QueryService {

	/**
	 * 查询当天的驶入驶出信息
	 * @param type 查询类型 1表示车牌号查询 2表示泊位号查询
	 * @param cond 查询条件
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @return
	 */
	public Response queryCarEntryExitByCond(int type, String cond, String empNo, String posNo);

	/**
	 * 根据日期查询收费记录
	 * @param cond 查询条件
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @return
	 */
	public Response queryPaymentRecordByDay(String cond, String empNo, String posNo);
	
	public Response queryInvoiceInfo2PrintByCarNo(String carNo);
}
