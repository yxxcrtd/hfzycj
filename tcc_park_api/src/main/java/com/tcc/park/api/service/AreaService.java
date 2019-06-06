package com.tcc.park.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.park.api.common.AreaCode;
import com.tcc.park.api.common.Constants;
import com.tcc.park.api.dao.AreaDao;
import com.tcc.park.api.domain.RoadSection;
import com.tcc.park.api.mybatis.RoadSectionMapper;
import com.tcc.park.api.util.PageUtil;
import com.tcc.park.api.vo.AreaDetailListVo;
import com.tcc.park.api.vo.AreaListVo;
import com.tcc.park.api.vo.ListResult;

/**
 * @author Think
 *
 */
/**
 * @author Think
 *
 */
@Service
public class AreaService {
	
	@Autowired
	private AreaDao areaDao;
	
	public List<AreaListVo> getAreaList() {
		List<AreaListVo> list = areaDao.selectAreaList();
		for (AreaListVo listVo : list) {
//			if (listVo.getAreaId().equals(AreaCode.LUYANG.getIndex())) {
//				listVo.setAreaName(AreaCode.LUYANG.getName());
//			}else if (listVo.getAreaId().equals(AreaCode.LUYANG.getIndex())) {
//				listVo.setAreaName(AreaCode.LUYANG.getName());
//			}else if (listVo.getAreaId().equals(AreaCode.LUYANG.getIndex())) {
//				listVo.setAreaName(AreaCode.LUYANG.getName());
//			}
			for (AreaCode c : AreaCode.values()) {
				
				if (listVo.getAreaId()!=null && listVo.getAreaId().equals(String.valueOf(c.getIndex()))) {
					listVo.setAreaName(c.getName());
				}
			}
		}
		return list;
	}
	
	public ListResult<AreaDetailListVo> getAreaDetailById(String areaCode,Integer pageNo) {
		ListResult<AreaDetailListVo> listResult = new ListResult<AreaDetailListVo>();
		int pageStart = 1;
		int pageEnd = Constants.PAGE_SIZE;
		if (pageNo != null&&pageNo >0) {
			pageStart = PageUtil.getBeginIndex(Constants.PAGE_SIZE, pageNo);
		}
		Integer count = 0;
		count = areaDao.selectByAreaListCount(areaCode);
		if (count == null || count == 0) {
			listResult.setPageNum(0);
			return listResult;
		}
		List<RoadSection> sections = areaDao.selectByAreaList(areaCode,pageStart,pageEnd);
		List<AreaDetailListVo> list = new ArrayList<AreaDetailListVo>();
		if (sections!=null) {
			for (RoadSection section : sections) {
				AreaDetailListVo vo = new AreaDetailListVo();
				Integer fee = section.getFeeType();
				vo.setIsFree((fee!=null&&fee==0)?"1":"0");
				vo.setParkName(section.getSectionName());
				vo.setParkNow(String.valueOf(section.getTotal()-section.getUsed()));
				vo.setSpaceTotal(String.valueOf(section.getTotal()));
				list.add(vo);
			}
		}
		listResult.setInfo(list);
		listResult.setPageNum(PageUtil.getTotalPage(count==null?0:count, Constants.PAGE_SIZE));
		return listResult;
	}
	
}
