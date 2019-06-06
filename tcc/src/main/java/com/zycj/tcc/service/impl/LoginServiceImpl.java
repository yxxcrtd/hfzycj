package com.zycj.tcc.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.async.AsyncExecutor;
import com.zycj.tcc.async.LogInOutWorker;
import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.dao.EmployeeDao;
import com.zycj.tcc.dao.EmployeeOnlineDao;
import com.zycj.tcc.dao.FeeRegularDao;
import com.zycj.tcc.dao.LogEmpLoginDao;
import com.zycj.tcc.dao.PaymentRecordDao;
import com.zycj.tcc.dao.PosDao;
import com.zycj.tcc.dao.RoadSectionDao;
import com.zycj.tcc.domain.Employee;
import com.zycj.tcc.domain.EmployeeOnline;
import com.zycj.tcc.domain.EmployeeOnlineExample;
import com.zycj.tcc.domain.Pos;
import com.zycj.tcc.domain.RoadSection;
import com.zycj.tcc.mybatis.mapper.EmployeeOnlineMapper;
import com.zycj.tcc.mybatis.mapper.PosMapper;
import com.zycj.tcc.mybatis.mapper.RoadSectionMapper;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.service.CheckService;
import com.zycj.tcc.service.EmployeeOnlineService;
import com.zycj.tcc.service.LoginService;
import com.zycj.tcc.service.PosService;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.util.FeeRegularInfo;
import com.zycj.tcc.util.UUIDGenerator;
import com.zycj.tcc.vo.LogoutQueryVo;
import com.zycj.tcc.vo.SignInVo;

/**
 * 员工签到签退校验业务类
 * @author 洪捃能
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
	private final static Logger log = Logger.getLogger(LoginServiceImpl.class); 
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private PosDao posDao;
	@Autowired
	private PosMapper posMapper;
	@Autowired
	private PosService posService;
	@Autowired
	private EmployeeOnlineService employeeOnlineService;
	@Autowired
	private EmployeeOnlineDao employeeOnlineDao;
	@Autowired
	private EmployeeOnlineMapper employeeOnlineMapper;
	@Autowired
	private LogEmpLoginDao logEmpLoginDao;
	@Autowired
	private RoadSectionDao roadSectionDao;
	@Autowired
	private CheckService checkService;
	@Autowired
	private FeeRegularDao feeRegularDao;
	@Autowired
	private PaymentRecordDao paymentRecordDao;
	@Autowired
	private RoadSectionMapper roadSectionMapper;
	
	//0001 员工签到接口
	@Override
	public Response login(String empNo, String pwd,String posNo, String ip) {
		try {
			Employee emp=employeeDao.selectEmployeeByEmpNo(empNo);
			if(emp==null){
				return ResponseUtil.createResponse(ResultCode.EMPLOYEE_NO_EXIST_ERROR);
			}
			if(!emp.getLoginPwd().equals(pwd)){
				return ResponseUtil.createResponse(ResultCode.EMPLOYEE_PASSWORD_ERROR);
			}
			if(emp.getEmpStatus().intValue()==Constants.EMPLOYEE_STATUS_LEAVE){
				return ResponseUtil.createResponse(ResultCode.EMPLOYEE_STATUS_LEAVE_ERROR);
			}
			Pos pos=posDao.selectPosByPosNo(posNo);
			if(pos==null){
				return ResponseUtil.createResponse(ResultCode.POS_NO_EXIST_ERROR);
			}
			if(pos.getSectionId()==null){
				return ResponseUtil.createResponse(ResultCode.EMPLOYEE_LOGIN_POS_NO_SECTION_ERROR);
			}
			if(pos.getPosStatus().intValue()==Constants.POS_STATUS_BROKEN){
				return ResponseUtil.createResponse(ResultCode.POS_STATUS_BROKEN_ERROR);
			}
			RoadSection rs=roadSectionMapper.selectByPrimaryKey(pos.getSectionId());
			if(rs==null){
				return ResponseUtil.createResponse(ResultCode.EMPLOYEE_LOGIN_POS_NO_SECTION_ERROR);
			}
			//校验路段是否可用
			
			String token=empNo+"_"+UUIDGenerator.getUUID();//生成token
			if(employeeOnlineService.updateOrAddEmployeeOnlineForLogin(empNo, posNo, ip, token)){
				try{
					AsyncExecutor.execute(new LogInOutWorker(Constants.EMPLOYEE_LOGINOUT_TYPE_IN,logEmpLoginDao,posDao,posService,empNo, posNo, ip,emp.getName()));
				}catch(Exception e){
				}
				return ResponseUtil.createDataResponse(ResultCode.SUCCESS,new SignInVo(token,rs.getSectionName(),emp.getName()));
			}else{
				return ResponseUtil.createResponse(ResultCode.EMPLOYEE_LOGIN_FAILED);
			}
		} catch (Exception e) {
			log.warn("执行员工签到异常",e);
			return ResponseUtil.createResponse(ResultCode.EMPLOYEE_LOGIN_FAILED);
		}
	}
	@Override
	public boolean checkLogin(String empNo, String posNo, String token) {
		EmployeeOnline eo=employeeOnlineDao.selectEmployeeOnlineByTokenAndRelevantNo(empNo, posNo,token);
		if(eo==null || eo.getStatus().intValue()!=Constants.EMPLOYEE_ONLINE_STATUS_ON){
			return false;
		}
		/**
		try{
			//考虑异步执行
			EmployeeOnline activeEo=new EmployeeOnline();
			activeEo.setId(eo.getId());
			activeEo.setActiveTime(new Date());
			employeeOnlineMapper.updateByPrimaryKeySelective(activeEo);
		}catch(Exception e){
			log.warn("更新员工最后活动时间异常",e);
		}
		*/
		return true;
	}
	
	@Override
	public boolean checkLogin(String empNo, String posNo) {
		EmployeeOnlineExample eoe=new EmployeeOnlineExample();
		eoe.or().andEmpNoEqualTo(empNo).andPosNoEqualTo(posNo);
		List<EmployeeOnline> eoList=employeeOnlineMapper.selectByExample(eoe);
		if(eoList==null||eoList.size()==0){
			return false;
		}
		return true;
	}
	
	@Override
	public Response gotoLogout(String empNo, String posNo) {
		BigDecimal bd=paymentRecordDao.selectNoCheckAmountTotalByEmpNoForTime(empNo, new Date());
		Integer noCheckCount=checkService.getNoCheckCountByEmpNo(empNo);
		
		//0表示未扎帐 1表示已经扎帐
		int isAccount=(bd.doubleValue()>0||noCheckCount.intValue()>0)?0:1;
		
		Integer feeType=roadSectionDao.selectFeeTypeByPosNo(posNo);
		if(feeType==null||feeType==0){
			return ResponseUtil.createResponse(ResultCode.FEE_REGULAR_NO_EXIST_ERROR);
		}
		FeeRegularInfo fri=feeRegularDao.selectFeeRegularInfoForCache(feeType, Constants.CAR_TYPE_SMALL);
		if(fri==null){
			return ResponseUtil.createResponse(ResultCode.FEE_REGULAR_NO_EXIST_ERROR);
		}
		LogoutQueryVo lqv=new LogoutQueryVo();
		try {
			lqv.setTime(DateUtil.format_HMS(new Date()));
			lqv.setIsAccount(isAccount);
			lqv.setCutoffTime(DateUtil.format_HM(fri.getEndTime()));//计费结束时间
		} catch (Exception e) {
			log.warn("签退查询 异常",e);
			return ResponseUtil.createResponse(ResultCode.LOGOUT_QUERY_FAILED);
		}
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, lqv);
	}
	
	@Override
	public Response logout(String empNo,String posNo,String ip) {
		try {
			employeeOnlineDao.updateStatusDownForLogout(empNo,posNo);
		} catch (Exception e) {
			log.warn("员工签退 更新在线表异常", e);
		}
		try{
			AsyncExecutor.execute(new LogInOutWorker(Constants.EMPLOYEE_LOGINOUT_TYPE_OUT,logEmpLoginDao,posDao,empNo, posNo, ip));
		}catch(Exception e){
		}
		return ResponseUtil.createResponse(ResultCode.SUCCESS);
	}
	
	
}
