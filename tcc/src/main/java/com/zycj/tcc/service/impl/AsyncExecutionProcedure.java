package com.zycj.tcc.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.dao.LogEmpLoginDao;
import com.zycj.tcc.dao.OrderInfoDao;
import com.zycj.tcc.dao.PosDao;
import com.zycj.tcc.domain.OrderInfo;
import com.zycj.tcc.service.OrderInfoService;
import com.zycj.tcc.service.PosService;

//@Service
@Deprecated
public class AsyncExecutionProcedure {
	private final static Logger log = Logger.getLogger(AsyncExecutionProcedure.class);
	
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private LogEmpLoginDao logEmpLoginDao;
	@Autowired
	private PosDao posDao;
	@Autowired
	private PosService posService;
	
	/**
	 * 根据车位编号 清算该停车位对应的历史订单
	 * @param spaceNo 车位编号
	 * @throws Exception
	 */
	public void clearOrderHistoryBySpaceNo(String spaceNo){
		List<OrderInfo> hisOrderList=orderInfoDao.selectOrderHistoryBySpaceNo(spaceNo);
		for(OrderInfo oi:hisOrderList){
			try {
				orderInfoService.clearOrderBySystem(oi, false);
			} catch (Exception e) {
				log.warn("清算  订单id:"+oi.getId()+" 异常", e);
			}
		}
	}
	/**
	 * 签到时 增加登录日志，更新pos终端上的员工相关信息
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @param ip pos终端ip
	 * @param loginName 员工登录名
	 * @param empName 员工姓名
	 */
	public void updateLoginRelateInfo(String empNo,String posNo, String ip,String empName){
		logEmpLoginDao.addLogEmpLogin(Constants.EMPLOYEE_LOGINOUT_TYPE_IN,empNo, posNo, ip);
		posDao.updateEmpOnlineStatusDown(empNo,posNo);
		posService.updatePosEmpByPosNo(posNo,empNo,empName,Constants.EMPLOYEE_ONLINE_STATUS_ON);
	}
	/**
	 * 签退时 更新员工在线表及pos终端上的员工相关信息
	 * @param type 签到、签退、日终签退
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @param ip
	 */
	public void logout(Integer type,String empNo,String posNo,String ip){
		logEmpLoginDao.addLogEmpLogin(type, empNo, posNo, ip);
		posDao.updateEmpOnlineStatusDownByPosNo(posNo);
	}
}
