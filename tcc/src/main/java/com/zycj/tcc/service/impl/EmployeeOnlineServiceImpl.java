package com.zycj.tcc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zycj.tcc.dao.EmployeeOnlineDao;
import com.zycj.tcc.domain.EmployeeOnline;
import com.zycj.tcc.service.EmployeeOnlineService;

@Service
public class EmployeeOnlineServiceImpl implements EmployeeOnlineService {

	@Autowired
	private EmployeeOnlineDao employeeOnlineDao;
	
	@Transactional(rollbackFor={Exception.class})
	@Override
	public boolean updateOrAddEmployeeOnlineForLogin(String empNo, String posNo,String ip, String token) throws Exception {
		try {
			employeeOnlineDao.updateStatusDownByEmpNoOrPosNo(empNo, posNo);
			EmployeeOnline el=employeeOnlineDao.selectEmployeeOnlineByEmpNoAndPosNo(empNo, posNo);
			if(el==null){
				employeeOnlineDao.addEmployeeOnlineForLogin(empNo, posNo, ip, token);
			}else{
				employeeOnlineDao.updateEmployeeOnlineForLogin(el.getId(), ip, token);
			}
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
}
