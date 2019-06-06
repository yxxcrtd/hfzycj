package com.zycj.tcc.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.dao.PosDao;
import com.zycj.tcc.dao.RoadSectionDao;
import com.zycj.tcc.domain.Pos;
import com.zycj.tcc.domain.RoadSection;
import com.zycj.tcc.mybatis.mapper.RoadSectionMapper;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {
	private final static Logger log = Logger.getLogger(SystemServiceImpl.class); 
	@Autowired
	private PosDao posDao;
	@Autowired
	private RoadSectionDao roadSectionDao;
	@Autowired
	private RoadSectionMapper roadSectionMapper;
	
	@Override
	public Response checkPosForBind(String posNo) {
		Pos pos=posDao.selectPosByPosNo(posNo);
		if(pos==null){
			return ResponseUtil.createResponse(ResultCode.POS_NO_EXIST_ERROR);
		}
		if(pos.getSectionId()==null){
			return ResponseUtil.createResponse(ResultCode.EMPLOYEE_LOGIN_POS_NO_SECTION_ERROR);
		}
		RoadSection rs=roadSectionMapper.selectByPrimaryKey(pos.getSectionId());
		if(rs==null){
			return ResponseUtil.createResponse(ResultCode.EMPLOYEE_LOGIN_POS_NO_SECTION_ERROR);
		}
		try{
			if(StringUtils.isBlank(pos.getSectionName())){
				pos.setSectionName(rs.getSectionName());
				posDao.updateSectionNameById(pos.getId(),pos.getSectionName());
			}
		}catch(Exception e){
			log.warn("获取并更新路段名称异常",e);
			return ResponseUtil.createResponse(ResultCode.SYSTEM_CHECK_IPPORT_POS_EXCEPTION);
		}
		Map<String,String> val=new HashMap<String,String>();
		val.put("sectionName", pos.getSectionName());
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, val);
	}
	
}
