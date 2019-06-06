package com.zycj.tcc.async;

import org.apache.log4j.Logger;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.dao.LogEmpLoginDao;
import com.zycj.tcc.dao.PosDao;
import com.zycj.tcc.service.PosService;

/**
 * 签到签退相关信息更新 异步操作  工作线程
 * @author 洪捃能
 */
public class LogInOutWorker implements Runnable {
	private final static Logger log = Logger.getLogger(LogInOutWorker.class);
	//签到
	public LogInOutWorker(int type,LogEmpLoginDao logEmpLoginDao,PosDao posDao,
			PosService posService,String empNo,String posNo,
			String ip,String empName){
		this.type=type;
		this.logEmpLoginDao=logEmpLoginDao;
		this.posDao=posDao;
		this.posService=posService;
		this.empNo=empNo;
		this.posNo=posNo;
		this.ip=ip;
		this.empName=empName;
	}
	//签退
	public LogInOutWorker(int type,LogEmpLoginDao logEmpLoginDao,PosDao posDao,
			String empNo,String posNo,String ip){
		this.type=type;
		this.logEmpLoginDao=logEmpLoginDao;
		this.posDao=posDao;
		this.empNo=empNo;
		this.posNo=posNo;
		this.ip=ip;
	}
	private int type;
	private LogEmpLoginDao logEmpLoginDao;
	private PosDao posDao;
	private PosService posService;
	
	private String empNo;
	private String posNo;
	private String ip;
	private String empName;
	
	@Override
	public void run() {
		try {
			switch (type) {
			case Constants.EMPLOYEE_LOGINOUT_TYPE_IN://签到
				updateLoginRelateInfo(empNo,posNo,ip,empName);
				break;
			case Constants.EMPLOYEE_LOGINOUT_TYPE_OUT://签退
				logout(empNo,posNo,ip);
				break;
			}
		} catch (Exception e) {
			log.warn("异步 签到签退异常",e);
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
	public void logout(String empNo,String posNo,String ip){
		logEmpLoginDao.addLogEmpLogin(Constants.EMPLOYEE_LOGINOUT_TYPE_OUT, empNo, posNo, ip);
		posDao.updateEmpOnlineStatusDownByPosNo(posNo);
	}
}
