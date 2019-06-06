package com.zycj.tcc.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.zycj.tcc.common.ArrearStatus;
import com.zycj.tcc.dao.ArrearDao;
import com.zycj.tcc.dao.BaseDao;
import com.zycj.tcc.domain.Arrear;
import com.zycj.tcc.mybatis.mapper.ArrearMapper;
import com.zycj.tcc.util.RandomUtil;
import com.zycj.tcc.vo.ArrearGroupInfo;

@Repository
public class ArrearDaoImpl extends BaseDao implements ArrearDao {

	@Autowired
	private ArrearMapper arrearMapper;
	
	@Override
	public BigDecimal selectAllArrearAmountByCarNo(String carNo) {
		return (BigDecimal) query("ArrearDao.selectAllArrearAmountByCarNo", carNo);
	}

	@Override
	public List<ArrearGroupInfo> selectArrearGroupByCarNo(String carNo) {
		return queryList("ArrearDao.selectArrearGroupByCarNo", carNo);
	}

	@Override
	public List<Arrear> selectListByCarNo(String carNo, Integer carType, Integer pageNo,
			Integer pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("carNo", carNo);
		param.put("carType",carType);
		PageBounds pb = new PageBounds(pageNo,pageSize,Order.create("out_time", "DESC"));
		return queryListByPage("ArrearDao.selectListByCarNo", param, pb);
	}

	@Override
	public int addArrearForCarExit(Integer orderId,String carNo,Integer carTye,String sectionName,
			String spaceNo,String inEmp,Date inTime,Date outTime,BigDecimal arrearAmount,
			Integer arrearType,String reqseriesNo, String sectionNo) {
		Arrear ar=new Arrear();
		ar.setSerialNo(RandomUtil.getSrialNoByDate());
		ar.setOrderId(orderId);
		ar.setCarNo(carNo);
		ar.setCarType(carTye);
		ar.setSectionName(sectionName);
		ar.setSpaceNo(spaceNo);//车位编号
		ar.setInEmp(inEmp);//驶入员工编号
		ar.setInTime(inTime);//驶入时间
		ar.setOutTime(outTime);//驶出时间
		ar.setArrearAmount(arrearAmount);//欠费金额 应缴
		ar.setArrearType(arrearType);//欠费类型 拒缴，欠费，日终
		ar.setStatus(ArrearStatus.UNPAID.getIndex());//欠费状态 未缴费
//		ar.setUpdateTime(new Date());
		ar.setReqSerial(reqseriesNo);
		ar.setOldAmount(arrearAmount);
		ar.setSectionNo(sectionNo);
		return arrearMapper.insertSelective(ar);
	}
	
	public List<Integer> selectIdListByCarNo(String carNo,Date selectTime) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("carNo", carNo);
		param.put("selectTime",selectTime);
		return queryList("ArrearDao.selectIdListByCarNo", param);
	}
	
	
	@Override
	public List<Arrear> selectNetPayListByCarNo(String carNo, Integer pageNo,
			Integer pageSize) {
		PageBounds pb = new PageBounds(pageNo,pageSize,Order.create("pay_time", "DESC"));
		return queryListByPage("ArrearDao.selectNetPayListByCarNo", carNo, pb);
	}
	
	public BigDecimal selectNetPayAmountByCarNo(String carNo) {
		return (BigDecimal) query("ArrearDao.selectNetPayAmountByCarNo", carNo);
	}
	
	@Override
	public void updatePrintBatch(List<Integer> idList,String invoiceBatch,String invoiceNo){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("invoiceBatch", invoiceBatch);
		param.put("invoiceNo",invoiceNo);
		param.put("list",idList);
		update("ArrearDao.updatePrintBatch", param);
	}
	
	@Override
	public void updatePrintAll(Date selectTime,String invoiceBatch,String invoiceNo,String carNo){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("invoiceBatch", invoiceBatch);
		param.put("invoiceNo",invoiceNo);
		param.put("selectTime",selectTime);
		param.put("carNo",carNo);
		update("ArrearDao.updatePrintAll", param);
	}
	
}
