package com.zycj.tcc.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.zycj.tcc.domain.Arrear;
import com.zycj.tcc.vo.ArrearGroupInfo;

public interface ArrearDao {

	/**
	 * 获取该车牌号的所有欠费金额
	 * @param carNo 车牌号
	 * @return 欠费金额
	 */
	public BigDecimal selectAllArrearAmountByCarNo(String carNo);
	
	/**
	 * 以欠费类型分组查询信息
	 * @param carNo 车牌号
	 * @return
	 */
	public List<ArrearGroupInfo> selectArrearGroupByCarNo(String carNo);
	
	/**
	 * 增加欠费金额，根据车辆确认驶出或日终清算程序
	 * @param orderId 停车订单id
	 * @param carNo 车牌号
	 * @param carTye 车类型
	 * @param sectionName 路段名
	 * @param spaceNo 车位编号
	 * @param inEmp 驶入员工编号
	 * @param inTime 驶入时间
	 * @param outTime 驶出时间
	 * @param arrearAmount 欠费金额 订单中的应缴
	 * @param arrearType 欠费类型 （拒缴，欠费，日终）
	 * @param reqseriesNo 交易流水号
	 * @return
	 */
	public int addArrearForCarExit(Integer orderId, String carNo, Integer carTye, String sectionName,
                                   String spaceNo, String inEmp, Date inTime, Date outTime, BigDecimal arrearAmount,
                                   Integer arrearType, String reqseriesNo, String sectionNo);
	
	public List<Arrear> selectListByCarNo(String carNo, Integer carType, Integer pageNo, Integer pageSize);
	
	public List<Integer> selectIdListByCarNo(String carNo, Date selectTime);
	
	public List<Arrear> selectNetPayListByCarNo(String carNo, Integer pageNo, Integer pageSize);
	
	public BigDecimal selectNetPayAmountByCarNo(String carNo);

	void updatePrintBatch(List<Integer> idList, String invoiceBatch,
                          String invoiceNo);

	void updatePrintAll(Date selectTime, String invoiceBatch, String invoiceNo, String carNo);
}
