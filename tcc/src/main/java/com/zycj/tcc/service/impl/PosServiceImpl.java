package com.zycj.tcc.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.domain.Pos;
import com.zycj.tcc.domain.PosExample;
import com.zycj.tcc.mybatis.mapper.PosMapper;
import com.zycj.tcc.service.PosService;

@Service
public class PosServiceImpl implements PosService {

	@Autowired
	private PosMapper posMapper;

	@Override
	public int updatePosEmpByPosNo(String posNo, String empNo, String empName,Integer onlineStatus) {
		Pos pos=new Pos();
		pos.setEmpNo(empNo);
		pos.setEmpName(empName);
		pos.setOnlineStatus(onlineStatus);
		pos.setUpdateTime(new Date());
		PosExample pe=new PosExample();
		pe.or().andPosNoEqualTo(posNo);
		return posMapper.updateByExampleSelective(pos, pe);
	}
	
}
