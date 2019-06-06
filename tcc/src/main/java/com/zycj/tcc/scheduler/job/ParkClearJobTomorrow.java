package com.zycj.tcc.scheduler.job;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zycj.tcc.cache.FeeRegularCache;
import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.SystemConfig;
import com.zycj.tcc.dao.CheckInfoDao;
import com.zycj.tcc.dao.EmployeeOnlineDao;
import com.zycj.tcc.dao.LogEmpLoginDao;
import com.zycj.tcc.dao.OrderInfoDao;
import com.zycj.tcc.dao.PaymentRecordDao;
import com.zycj.tcc.dao.PosDao;
import com.zycj.tcc.dao.RoadSectionDao;
import com.zycj.tcc.dao.RoadSpaceDao;
import com.zycj.tcc.domain.CheckInfo;
import com.zycj.tcc.domain.Employee;
import com.zycj.tcc.domain.EmployeeExample;
import com.zycj.tcc.domain.EmployeeOnline;
import com.zycj.tcc.domain.OrderInfo;
import com.zycj.tcc.mybatis.mapper.CheckInfoMapper;
import com.zycj.tcc.mybatis.mapper.EmployeeMapper;
import com.zycj.tcc.mybatis.mapper.FeeRegularMapper;
import com.zycj.tcc.mybatis.mapper.OrderInfoMapper;
import com.zycj.tcc.service.OrderInfoService;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.util.DecimalFormatUtil;

/**
 * 停车位清算任务类
 * @author 洪捃能
 *
 */
@Component
public class ParkClearJobTomorrow {
	private final static Logger log = Logger.getLogger(ParkClearJobTomorrow.class);
	
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Autowired
	private LogEmpLoginDao logEmpLoginDao;
	@Autowired
	private PaymentRecordDao paymentRecordDao;
	@Autowired
	private RoadSpaceDao roadSpaceDao;
	@Autowired
	private EmployeeOnlineDao employeeOnlineDao;
	@Autowired
	private FeeRegularMapper feeRegularMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private CheckInfoMapper checkInfoMapper;
	@Autowired
	private PosDao posDao;
	@Autowired
	private RoadSectionDao roadSectionDao;
	@Autowired
	private CheckInfoDao checkInfoDao;
	
	public void clear(){
		log.info(DateUtil.format_YMDHMSx(new Date())+" 开始---->执行Tomorrow="+SystemConfig.Park_Clear_Job_CronExpression_Tomorrow);
//		clearOrder();
//		clearRoadSpace();//not today
//		clearRoadSectionUsed();//not today
		if (SystemConfig.SYSTEM_TYPE == 0) {
			clearEmployeeOnline();//not today
			clearCheck();
			clearPosEmpStatus();//not today
		}
		updateFeeRegularCache();//not today
		log.info(DateUtil.format_YMDHMSx(new Date())+" 结束---->执行Tomorrow="+SystemConfig.Park_Clear_Job_CronExpression_Tomorrow);
	}
	public int clearOrder(List<OrderInfo> oiList){
		int clearCount=0;
		for(OrderInfo oi:oiList){
			try {
				orderInfoService.clearOrderBySystem(oi,true);
				clearCount++;
			} catch (Exception e) {
			}
		}
		return clearCount;
	}
	//更新车位是停车状态的为空闲
	public void clearRoadSpace(){
		long startTime=System.currentTimeMillis();//执行开始时间
		log.warn("》》》》》》》》开始  清算停车位....");
		int count=0;
		try {
			count=roadSpaceDao.updateNoParkByAll();
		} catch (Exception e) {
			log.warn("清算  停车位为空闲 异常",e);
		}
		long endTime=System.currentTimeMillis(); //执行结束时间
		log.warn("》》》》》》》》结束  清算停车位"+count+"个....用时："+DateUtil.getSFM(endTime-startTime));
	}
	//把在线员工表更新为离线，状态为日终离线 
	public void clearEmployeeOnline(){
		long startTime=System.currentTimeMillis();//执行开始时间
		log.warn("》》》》》》》》开始  清算在线员工....");
		List<EmployeeOnline> eoList=employeeOnlineDao.selectEmployeeOnlineByStatus(Constants.EMPLOYEE_ONLINE_STATUS_ON);
		int count=0;
		for(EmployeeOnline eo:eoList){
			try {
				employeeOnlineDao.updateStatusDownById(eo.getId());
			} catch (Exception e) {
				log.warn("清算在线员工为离线 异常",e);
			}
			try {
				logEmpLoginDao.addLogEmpLogin(Constants.EMPLOYEE_LOGINOUT_TYPE_CLEAR_OUT, eo.getEmpNo(),eo.getPosNo(),null);
			} catch (Exception e) {
				log.warn("清算 添加员工签退日志  异常",e);
			}
			count++;
		}
		long endTime=System.currentTimeMillis(); //执行结束时间
		log.warn("》》》》》》》》结束  清算在线员工"+count+"个....用时："+DateUtil.getSFM(endTime-startTime));
	}
	//统计扎帐情况添加到扎帐汇总表
	public void clearCheck(){
		long startTime=System.currentTimeMillis();//执行开始时间
		log.warn("》》》》》》》》开始  统计扎帐情况，并记入扎帐汇总表....");
		EmployeeExample example=new EmployeeExample();
		example.or().andEmpStatusEqualTo(Constants.EMPLOYEE_STATUS_ON);
		List<Employee> empList=employeeMapper.selectByExample(example);
		//未扎帐日期
		Date date=DateUtils.addDays(new Date(), -1);
		for (Employee emp:empList) {
			try {
				BigDecimal amount=paymentRecordDao.selectNoCheckAmountTotalByEmpNoForDate(emp.getLoginName());
				log.warn(emp.getName()+"("+emp.getLoginName()+") 的未扎帐金额为："+DecimalFormatUtil.format_00(amount));
				CheckInfo ci=checkInfoDao.selectCheckInfoByEmpAndCheckDate(emp.getLoginName(), date);
				if(ci==null&&amount.doubleValue()>0){//如果缴费表中未扎帐的金额>0，那么记入到扎帐汇总表
					ci=new CheckInfo();
					ci.setEmpNo(emp.getLoginName());
					ci.setCheckDate(date);
					ci.setAmount(amount);
					ci.setCheckStatus(Constants.CHECK_STATUS_NO);
					checkInfoMapper.insertSelective(ci);
				}
				if(ci!=null){
					log.warn(emp.getName()+"("+emp.getLoginName()+") 重复统计未扎帐记录");
				}
			} catch (Exception e) {
				log.warn("清算  员工编号："+emp.getLoginName()+" 统计扎帐情况 异常",e);
			}
		}
		long endTime=System.currentTimeMillis(); //执行结束时间
		log.warn("》》》》》》》》结束 统计扎帐情况....用时："+DateUtil.getSFM(endTime-startTime));
	}
	//更新pos在线员工为离线
	public void clearPosEmpStatus(){
		long startTime=System.currentTimeMillis();//执行开始时间
		log.warn("》》》》》》》》开始  更新pos在线员工为离线....");
		try {
			posDao.clearPosEmpStatus();
		} catch (Exception e) {
			log.warn("清算  pos在线员工为离线  异常",e);
		}
		long endTime=System.currentTimeMillis(); //执行结束时间
		log.warn("》》》》》》》》开始  更新pos在线员工为离线....用时："+DateUtil.getSFM(endTime-startTime));
	}
	//刷新计费规则缓存
	public void updateFeeRegularCache(){
		long startTime=System.currentTimeMillis();//执行开始时间
		log.warn("》》》》》》》》开始  刷新计费规则缓存。。。。");
		try {
			FeeRegularCache.init(feeRegularMapper.selectByExample(null));//初始化计费规则
		} catch (Exception e) {
			log.warn("清算 刷新计费规则缓存 异常", e);
		}
		long endTime=System.currentTimeMillis(); //执行结束时间
		log.warn("》》》》》》》》结束  刷新计费规则缓存。。。。用时："+DateUtil.getSFM(endTime-startTime));
	}
	
	//清空路段车位占用数
	public void clearRoadSectionUsed(){
		long startTime=System.currentTimeMillis();//执行开始时间
		log.warn("》》》》》》》》开始 清空路段车位占用数。。。。");
		try {
			roadSectionDao.clearRoadSectionUsed();
		} catch (Exception e) {
			log.warn("清算  清空路段车位占用数 异常", e);
		}
		long endTime=System.currentTimeMillis(); //执行结束时间
		log.warn("》》》》》》》》结束  清空路段车位占用数。。。。用时："+DateUtil.getSFM(endTime-startTime));
	}
	
	public static void main(String[] args) throws ParseException {
		//设置驶出时间为计费结束时间
		String outTimeStr=DateUtil.format_YMD(new Date())+" 23:59:59";
		Date outTime=DateUtil.parse_YMDHMS(outTimeStr);
		System.out.println(DateUtil.format_YMDHMS(outTime));
		System.out.println(DateUtil.getLastTimeForDay());
	}
	
}
