package com.zycj.tcc.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.zycj.tcc.domain.Arrear;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.vo.ArrearInfo;
import com.zycj.tcc.vo.TraceInfoVo;

/**
 * 欠费服务接口
 * @author Administrator
 *
 */
public interface ArrearService {

	/**
	 * 获取该车牌号的所有欠费金额
	 * @param carNo 车牌号
	 * @return 欠费金额
	 */
	public Response selectAllArrearAmountByCarNo(String carNo);
	
	/**
	 * 根据车牌号获取欠费记录列表
	 * @param carNo
	 * @return
	 */
	public Response getArrearListByCarNo(String carNo, int carType, int pageNo);
	/**
	 * 根据车牌号获取欠费记录信息，
	 * 总共欠费多少笔；
	 * 总欠费金额；
	 * 各个欠费类型的欠费笔数及金额
	 * 欠费级别：拒缴>欠费>无欠费      优先显示：从高到底
	 * @param carNo 车牌号
	 * @return
	 */
	public ArrearInfo getArrearInfoByCarNo(String carNo);
	
	public List<Arrear> getArrearListByIds(List<Integer> idsList);
	
	public Response payArrear(String ids, BigDecimal reallAmount, BigDecimal amount, String posNo, String empNo, Integer payType, String reqSerial, String invoiceBatch, String invoiceNo, TraceInfoVo traceInfo, String cardNo)throws Exception;
	
	public Response payArrearByPrePayCard(String ids, BigDecimal reallAmount, BigDecimal amount, String posNo, String empNo, Integer payType, String reqSerial, String invoiceBatch, String invoiceNo, TraceInfoVo traceInfo, String cardNo, String carNo)throws Exception;
	
	public String selectIdListByCarNo(String carNo, Date selectTime);

	public Response getArrearNetPayByCarNo(String carNo, int pageNo);

	Response printNetPay(String ids, String posNo, String empNo,
                         String reqSerial, String invoiceBatch, String invoiceNo,
                         Date selectTime, String carNo) throws Exception;
}
