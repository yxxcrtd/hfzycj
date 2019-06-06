package com.zycj.tcc.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.common.TicketType;
import com.zycj.tcc.domain.RoadSection;
import com.zycj.tcc.domain.RoadSectionExample;
import com.zycj.tcc.domain.TicketInfo;
import com.zycj.tcc.domain.TicketInfoExample;
import com.zycj.tcc.mybatis.mapper.RoadSectionMapper;
import com.zycj.tcc.mybatis.mapper.TicketInfoMapper;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.service.RoadSectionService;
import com.zycj.tcc.service.TicketInfoService;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.vo.TicketInfoVo;

@Service
public class TicketInfoServiceImpl implements TicketInfoService {
	private final static Logger log = Logger.getLogger(TicketInfoServiceImpl.class); 
	@Autowired
	private TicketInfoMapper ticketInfoMapper;
	@Autowired
	private RoadSectionMapper roadSectionMapper;
	@Autowired
	private RoadSectionService roadSectionService;
	
	@Override
	public Response selectTicketInfoByTicketNo(String ticketNo,int type) {
		try {
			List<TicketInfo> tiList=null;
			if (type == 1) {
				tiList = getTicketInfoByCarNo(ticketNo);
			}else {
				tiList = getTicketInfoByTicketNo(ticketNo);
			}
			if(tiList==null||tiList.size()==0){
				return ResponseUtil.createResponse(ResultCode.TICKETINFO_NO_EXIST_ERROR);
			}
			TicketInfo ti=tiList.get(0);
			if(ti.getStatus()==Constants.TICKET_STATUS_NO){
				return ResponseUtil.createResponse(ResultCode.TICKETINFO_OVERDUE_ERROR);
			}
			TicketInfoVo tiv=new TicketInfoVo();
			tiv.setCarNo(ti.getCarNo());
			tiv.setType(TicketType.getName(ti.getTicketType()));
			tiv.setTicketNo(ti.getTicketNo());
			if(ti.getSectionId().equals("-1")){
				tiv.setUserWay("所有");
			}else{
				tiv.setUserWay(getRoadNames(roadSectionService.getSectionListByIds(ti.getSectionId())));
			}
			try {
				tiv.setUserTime(DateUtil.format_YMD(ti.getStartDate())+"至"+DateUtil.format_YMD(ti.getEndDate()));
			} catch (ParseException e) {
			}
			tiv.setUserType(ti.getOwnerType()==Constants.TICKET_OWNER_TYPE_PERSONAL?"个人":"公司");
			tiv.setBuyer(ti.getReceiver());
			return ResponseUtil.createDataResponse(ResultCode.SUCCESS, tiv);
		} catch (Exception e) {
			log.warn("查询月票 异常",e);
			return ResponseUtil.createResponse(ResultCode.QUERY_TICKETINFO_FAILED);
		}
		
	}
	public String getRoadSectionNameById(Integer sectionId){
		RoadSection rs=roadSectionMapper.selectByPrimaryKey(sectionId);
		if(rs==null){
			return null;
		}else{
			return rs.getSectionName();
		}
	}
	public List<TicketInfo> getTicketInfoByTicketNo(String ticketNo){
		TicketInfoExample tie=new TicketInfoExample();
		tie.or().andTicketNoEqualTo(ticketNo);
		return ticketInfoMapper.selectByExample(tie);
	}
	
	public List<TicketInfo> getTicketInfoByCarNo(String carNo){
		TicketInfoExample tie=new TicketInfoExample();
		tie.or().andCarNoEqualTo(carNo);
		return ticketInfoMapper.selectByExample(tie);
	}
	
	public String getRoadNames(List<RoadSection> sections) {
		String rName ="";
		for (RoadSection roadSection : sections) {
			rName = rName +"\n\r"+ roadSection.getSectionName();
		}
//		rName = rName.replaceFirst("\n\r", "");
		return rName;
	}
	
	public static void main(String[] args) {
		String rName ="";
		List<String> sections = new ArrayList<String>();
		sections.add("1323");
		sections.add("456");
		sections.add("qwer");
		sections.add("zxv");
		for (String roadSection : sections) {
			rName = rName +"<br/>"+ roadSection;
		}
		rName = rName.replaceFirst("<br/>", "");
		System.err.println(rName);
	}

}
