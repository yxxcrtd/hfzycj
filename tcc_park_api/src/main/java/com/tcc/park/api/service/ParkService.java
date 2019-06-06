package com.tcc.park.api.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.park.api.common.Constants;
import com.tcc.park.api.common.FeeConfig;
import com.tcc.park.api.dao.ParkLotDao;
import com.tcc.park.api.domain.RoadSection;
import com.tcc.park.api.mybatis.RoadSectionMapper;
import com.tcc.park.api.util.DateUtil;
import com.tcc.park.api.util.LoLaUtil;
import com.tcc.park.api.util.PageUtil;
import com.tcc.park.api.vo.ListResult;
import com.tcc.park.api.vo.ParkDetailVo;
import com.tcc.park.api.vo.ParkListVo;

/**
 * @author Think
 *
 */
/**
 * @author Think
 *
 */
@Service
public class ParkService {
	
	@Autowired
	private ParkLotDao parkLotDao;
	@Autowired
	private RoadSectionMapper roadSectionMapper;
	
	
	public ListResult<ParkListVo> getParkListByPointAll(Integer feeType,BigDecimal la,BigDecimal lo,Integer distance,Integer pageNo,Integer type) {
		if (type == null || type.intValue() == 1) {//地图模式
			return getParkMapByPoint(feeType, la, lo, distance);
		}else if (type.intValue() == 2) {//列表模式
			return getParkListByPoint(feeType, la, lo, distance, pageNo);
		}
		return new ListResult<ParkListVo>();
	}
	
	private ListResult<ParkListVo> getParkMapByPoint(Integer feeType,BigDecimal la,BigDecimal lo,Integer distance) {
		ListResult<ParkListVo> listResult = new ListResult<ParkListVo>();
		double[] fourPoint = LoLaUtil.getAround(la.doubleValue(), lo.doubleValue(), distance);
		List<RoadSection> srcList = parkLotDao.selectByPointFee(feeType, new BigDecimal(fourPoint[0]), new BigDecimal(fourPoint[1]), new BigDecimal(fourPoint[2]), new BigDecimal(fourPoint[3]),0,100);
		List<ParkListVo> list = new ArrayList<ParkListVo>();
		for (RoadSection roadSection : srcList) {
			ParkListVo vo = new ParkListVo();
			vo.setParkId(String.valueOf(roadSection.getId()));
			vo.setParkName(roadSection.getSectionName());
			vo.setParkNow(String.valueOf(roadSection.getTotal()-roadSection.getUsed()));
			vo.setParkTotal(String.valueOf(roadSection.getTotal()));
			vo.setLatitude(String.valueOf(roadSection.getStartLa().doubleValue()));
			vo.setLongitude(String.valueOf(roadSection.getStartLo().doubleValue()));
			Integer fee = roadSection.getFeeType();
			vo.setIsFree((fee!=null&&fee==0)?"1":"0");
			vo.setDistance(LoLaUtil.getDistatce(la.doubleValue(),roadSection.getStartLa().doubleValue(),lo.doubleValue(),roadSection.getStartLo().doubleValue()).intValue()+"");
			list.add(vo);
		}
//		Collections.sort(list);
		listResult.setInfo(list);
		return listResult;
	}
	
	private ListResult<ParkListVo> getParkListByPoint(Integer feeType,BigDecimal la,BigDecimal lo,Integer distance,Integer pageNo) {
		ListResult<ParkListVo> listResult = new ListResult<ParkListVo>();
		int pageStart = 1;
		int pageEnd = Constants.PAGE_SIZE;
		if (pageNo != null&&pageNo >0) {
			pageStart = PageUtil.getBeginIndex(Constants.PAGE_SIZE, pageNo);
		}
		double[] fourPoint = LoLaUtil.getAround(la.doubleValue(), lo.doubleValue(), distance);
		Integer count = parkLotDao.selectByPointFeeCount(feeType, new BigDecimal(fourPoint[0]), new BigDecimal(fourPoint[1]), new BigDecimal(fourPoint[2]), new BigDecimal(fourPoint[3]));
		if (count == null || count == 0) {
			listResult.setPageNum(0);
			return listResult;
		}
		int pageCount = PageUtil.getTotalPage(count==null?0:count, Constants.PAGE_SIZE);
		if (pageCount < pageNo) {
			listResult.setPageNum(pageCount);
			return listResult;
		}
		List<RoadSection> srcList = parkLotDao.selectByPointFee(feeType, new BigDecimal(fourPoint[0]), new BigDecimal(fourPoint[1]), new BigDecimal(fourPoint[2]), new BigDecimal(fourPoint[3]),null,null);
		List<ParkListVo> list = new ArrayList<ParkListVo>();
		for (RoadSection roadSection : srcList) {
			ParkListVo vo = new ParkListVo();
			vo.setParkId(String.valueOf(roadSection.getId()));
			vo.setParkName(roadSection.getSectionName());
			vo.setParkNow(String.valueOf(roadSection.getTotal()-roadSection.getUsed()));
			vo.setParkTotal(String.valueOf(roadSection.getTotal()));
			vo.setLatitude(String.valueOf(roadSection.getStartLa().doubleValue()));
			vo.setLongitude(String.valueOf(roadSection.getStartLo().doubleValue()));
			Integer fee = roadSection.getFeeType();
			vo.setIsFree((fee!=null&&fee==0)?"1":"0");
			vo.setDistance(LoLaUtil.getDistatce(la.doubleValue(),roadSection.getStartLa().doubleValue(),lo.doubleValue(),roadSection.getStartLo().doubleValue()).intValue()+"");
			list.add(vo);
		}
		Collections.sort(list);
		int listSize = list.size();
		int end = pageStart+pageEnd > listSize ? listSize : pageStart+pageEnd;
		if (pageStart > end) {
			listResult.setInfo(new ArrayList<ParkListVo>());
		}else {
			list = list.subList(pageStart, end);
			listResult.setInfo(list);
		}
		listResult.setPageNum(pageCount);
		return listResult;
	}
	
	public ParkDetailVo getParkDetailById(Integer parkId,BigDecimal la,BigDecimal lo) {
		RoadSection section = roadSectionMapper.selectByPrimaryKey(parkId);
		if (section!=null) {
			ParkDetailVo detailVo = new ParkDetailVo();
			detailVo.setParkName(section.getRoadName());
			detailVo.setParkNow(String.valueOf(section.getTotal()-section.getUsed()));
			detailVo.setParkTotal(String.valueOf(section.getTotal()));
			detailVo.setBusinessTime(DateUtil.getDateStringByFormat(section.getFeeStart(),"HH:mm")+"-"+DateUtil.getDateStringByFormat(section.getFeeEnd(),"HH:mm"));
			detailVo.setParkLocation(section.getSectionName());
			detailVo.setFeeDescription(FeeConfig.feeMap.get("fee_"+section.getFeeType()));
			detailVo.setSignDescription(FeeConfig.FEE_SEPECIAL);
			Integer fee = section.getFeeType();
			detailVo.setIsFree((fee!=null&&fee==0)?"1":"0");
			return detailVo;
		}
		return null;
	}
	
}
