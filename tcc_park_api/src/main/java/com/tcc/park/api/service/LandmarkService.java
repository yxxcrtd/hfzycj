package com.tcc.park.api.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.park.api.common.Constants;
import com.tcc.park.api.user.dao.LandmarkDao;
import com.tcc.park.api.user.domain.Landmark;
import com.tcc.park.api.user.domain.LandmarkType;
import com.tcc.park.api.user.domain.LandmarkTypeExample;
import com.tcc.park.api.user.mybatis.LandmarkTypeMapper;
import com.tcc.park.api.util.LoLaUtil;
import com.tcc.park.api.util.PageUtil;
import com.tcc.park.api.vo.LandmarkVo;
import com.tcc.park.api.vo.ListResult;

@Service
public class LandmarkService {

	@Autowired
	private LandmarkDao landmarkDao;
	@Autowired
	private LandmarkTypeMapper landmarkTypeMapper;
	
	public List<LandmarkType> selectAllLandmarkType() {
		LandmarkTypeExample example=new LandmarkTypeExample();
		example.createCriteria().andStatusEqualTo(1);
		return landmarkTypeMapper.selectByExample(example);
	}

	public ListResult<LandmarkVo> getLandmarkListByPointAll(Integer typeId,
			BigDecimal la, BigDecimal lo, Integer distance, Integer pageNo,
			Integer type) {
		if (type == null || type.intValue() == 1) {//地图模式
			return getLandmarkMapByPoint(typeId, la, lo, distance);
		}else if (type.intValue() == 2) {//列表模式
			return getLandmarkListByPoint(typeId, la, lo, distance, pageNo);
		}
		return new ListResult<LandmarkVo>();
	}
	
	private ListResult<LandmarkVo> getLandmarkMapByPoint(Integer typeId,BigDecimal la,BigDecimal lo,Integer distance) {
		ListResult<LandmarkVo> listResult = new ListResult<LandmarkVo>();
		double[] fourPoint = LoLaUtil.getAround(la.doubleValue(), lo.doubleValue(), distance);
		List<Landmark> srcList = landmarkDao.selectByPointTypeId(typeId, new BigDecimal(fourPoint[0]), new BigDecimal(fourPoint[1]), new BigDecimal(fourPoint[2]), new BigDecimal(fourPoint[3]),0,100);
		List<LandmarkVo> list = new ArrayList<LandmarkVo>();
		for (Landmark lk : srcList) {
			LandmarkVo vo = new LandmarkVo();
			vo.setId(lk.getId());
			vo.setTypeId(lk.getLandmarkTypeId());
			vo.setName(lk.getName());
			vo.setCode(lk.getCode());
			vo.setAddr(lk.getAddr());
			vo.setLatitude(String.valueOf(lk.getLat().doubleValue()));
			vo.setLongitude(String.valueOf(lk.getLo().doubleValue()));
			vo.setDistance(LoLaUtil.getDistatce(la.doubleValue(),lk.getLat().doubleValue(),lo.doubleValue(),lk.getLo().doubleValue()).intValue()+"");
			list.add(vo);
		}
//		Collections.sort(list);
		listResult.setInfo(list);
		return listResult;
	}
	
	private ListResult<LandmarkVo> getLandmarkListByPoint(Integer typeId,BigDecimal la,BigDecimal lo,Integer distance,Integer pageNo) {
		ListResult<LandmarkVo> listResult = new ListResult<LandmarkVo>();
		int pageStart = 1;
		int pageEnd = Constants.PAGE_SIZE;
		if (pageNo != null&&pageNo >0) {
			pageStart = PageUtil.getBeginIndex(Constants.PAGE_SIZE, pageNo);
		}
		double[] fourPoint = LoLaUtil.getAround(la.doubleValue(), lo.doubleValue(), distance);
		Integer count = landmarkDao.selectByPointTypeIdCount(typeId, new BigDecimal(fourPoint[0]), new BigDecimal(fourPoint[1]), new BigDecimal(fourPoint[2]), new BigDecimal(fourPoint[3]));
		if (count == null || count == 0) {
			listResult.setPageNum(0);
			return listResult;
		}
		int pageCount = PageUtil.getTotalPage(count==null?0:count, Constants.PAGE_SIZE);
		if (pageCount < pageNo) {
			listResult.setPageNum(pageCount);
			return listResult;
		}
		List<Landmark> srcList = landmarkDao.selectByPointTypeId(typeId, new BigDecimal(fourPoint[0]), new BigDecimal(fourPoint[1]), new BigDecimal(fourPoint[2]), new BigDecimal(fourPoint[3]),null,null);
		List<LandmarkVo> list = new ArrayList<LandmarkVo>();
		for (Landmark lk : srcList) {
			LandmarkVo vo = new LandmarkVo();
			vo.setId(lk.getId());
			vo.setTypeId(lk.getLandmarkTypeId());
			vo.setName(lk.getName());
			vo.setAddr(lk.getAddr());
			vo.setCode(lk.getCode());
			vo.setLatitude(String.valueOf(lk.getLat().doubleValue()));
			vo.setLongitude(String.valueOf(lk.getLo().doubleValue()));
			vo.setDistance(LoLaUtil.getDistatce(la.doubleValue(),lk.getLat().doubleValue(),lo.doubleValue(),lk.getLo().doubleValue()).intValue()+"");
			list.add(vo);
		}
		Collections.sort(list);
		int listSize = list.size();
		int end = pageStart+pageEnd > listSize ? listSize : pageStart+pageEnd;
		if (pageStart > end) {
			listResult.setInfo(new ArrayList<LandmarkVo>());
		}else {
			list = list.subList(pageStart, end);
			listResult.setInfo(list);
		}
		listResult.setPageNum(pageCount);
		return listResult;
	}
}
