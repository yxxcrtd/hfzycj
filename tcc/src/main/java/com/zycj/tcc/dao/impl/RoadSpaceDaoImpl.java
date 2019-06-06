package com.zycj.tcc.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.zycj.tcc.dao.BaseDao;
import com.zycj.tcc.dao.RoadSpaceDao;
import com.zycj.tcc.domain.RoadSpace;
import com.zycj.tcc.mybatis.mapper.RoadSpaceMapper;
import com.zycj.tcc.vo.DcjRoadSpaceVo;
import com.zycj.tcc.vo.RoadSpaceSectionInfo;
import com.zycj.tcc.vo.RoadSpaceVo;

@Repository
public class RoadSpaceDaoImpl extends BaseDao implements RoadSpaceDao {

	@Autowired
	private RoadSpaceMapper roadSpaceMapper;
	@Override
	public List<RoadSpaceVo> selectAllRoadSpaceByPosNo(String posNo) {
		return queryList("RoadSpaceDao.selectRoadSpaceByPosNo", posNo);
	}

	@Override
	public List<RoadSpaceVo> selectAllRoadSpaceByPosNoExtend(String posNo) {
		return queryList("RoadSpaceDao.selectRoadSpaceByPosNoExtend", posNo);
	}

	@Override
	public List<RoadSpaceVo> selectEmptyRoadSpaceByPosNo(String posNo) {
		return queryList("RoadSpaceDao.selectEmptyRoadSpaceByPosNo", posNo);
	}
	@Override
	public List<RoadSpaceVo> selectEmptyRoadSpaceByPosNoExtend(String posNo) {
		return queryList("RoadSpaceDao.selectEmptyRoadSpaceByPosNoExtend", posNo);
	}

	@Override
	public List<RoadSpaceVo> selectParkedRoadSpaceByPosNo(String posNo) {
		return queryList("RoadSpaceDao.selectParkedRoadSpaceByPosNo", posNo);
	}
	@Override
	public List<RoadSpaceVo> selectParkedRoadSpaceByPosNoExtend(String posNo) {
		return queryList("RoadSpaceDao.selectParkedRoadSpaceByPosNoExtend", posNo);
	}

	@Override
	public List<RoadSpaceVo> selectWaitConfirmRoadSpaces(String posNo, int currentPage, int pageSize) {
		PageBounds pb = new PageBounds(currentPage, pageSize);
		return queryListByPage("RoadSpaceDao.selectWaitConfirmRoadSpaces", posNo,pb);
	}
	
	@Override
	public List<RoadSpaceVo> selectALLRoadSpaceByPosNoForPage(String posNo,int currentPage, int pageSize) {
		PageBounds pb = new PageBounds(currentPage, pageSize);
//		return queryListByPage("RoadSpaceDao.selectRoadSpaceByPosNo", posNo,pb);
		return queryListByPage("RoadSpaceDao.selectRoadSpaceByPosNoExtend", posNo,pb);
	}

	@Override
	public Integer selectRoadSpaceCountByPosNo(String posNo) {
		return (Integer) query("RoadSpaceDao.selectRoadSpaceCountByPosNo", posNo);
	}

	@Override
	public List<RoadSpaceVo> selectEmptyRoadSpaceByPosNoForPage(String posNo, int currentPage, int pageSize) {
		PageBounds pb = new PageBounds(currentPage, pageSize);
//		return queryListByPage("RoadSpaceDao.selectEmptyRoadSpaceByPosNo", posNo,pb);
		return queryListByPage("RoadSpaceDao.selectEmptyRoadSpaceByPosNoExtend", posNo,pb);
	}

	@Override
	public Integer selectEmptyRoadSpaceCountByPosNo(String posNo) {
		return (Integer) query("RoadSpaceDao.selectEmptyRoadSpaceCountByPosNo", posNo);
	}

	@Override
	public List<RoadSpaceVo> selectParkedRoadSpaceByPosNoForPage(String posNo, int currentPage, int pageSize) {
		PageBounds pb = new PageBounds(currentPage, pageSize);
//		return queryListByPage("RoadSpaceDao.selectParkedRoadSpaceByPosNo", posNo,pb);
		return queryListByPage("RoadSpaceDao.selectParkedRoadSpaceByPosNoExtend", posNo,pb);
	}

	@Override
	public Integer selectParkedRoadSpaceCountByPosNo(String posNo) {
		return (Integer) query("RoadSpaceDao.selectParkedRoadSpaceCountByPosNo", posNo);
	}

	@Override
	public int updateRoadSpaceBySpaceNoForCarEntry(String spaceNo, String carNo,Date parkTime) {
		Map<String,Object> val=new HashMap<String,Object>();
		val.put("spaceNo", spaceNo);
		val.put("carNo", carNo);
		val.put("parkTime", parkTime);
		return update("RoadSpaceDao.updateRoadSpaceBySpaceNoForCarEntry", val);
	}
	
	@Override
	public int updateRoadSpaceNoParkBySpaceNo(String spaceNo) {
		return update("RoadSpaceDao.updateRoadSpaceNoParkBySpaceNo", spaceNo);
	}

	@Override
	public int updateRoadSpaceBySpaceNoForCarExit(String spaceNo, Date driveTime) {
		Map<String,Object> val=new HashMap<String,Object>();
		val.put("spaceNo", spaceNo);
		val.put("driveTime", driveTime);
		return update("RoadSpaceDao.updateRoadSpaceBySpaceNoForCarExit", val);
	}

	@Override
	public Integer selectFeeRegularIdBySpaceNo(String spaceNo) {
		return (Integer) query("RoadSpaceDao.selectFeeRegularIdBySpaceNo", spaceNo);
	}

	@Override
	public RoadSpace selectRoadSpaceBySpaceNo(String spaceNo) {
		return (RoadSpace) query("RoadSpaceDao.selectRoadSpaceBySpaceNo",spaceNo);
	}

	@Override
	public RoadSpaceSectionInfo selectRoadSpaceSectionInfoBySpaceNo(String spaceNo) {
		return (RoadSpaceSectionInfo) query("RoadSpaceDao.selectRoadSpaceSectionInfoBySpaceNo",spaceNo);
	}

	@Override
	public int updateNoParkByAll() {
		return update("RoadSpaceDao.updateNoParkByAll", null);
	}

	@Override
	public List<String> selectRoadSpaceByPosNoForHomePageSet(String posNo) {
		return queryList("RoadSpaceDao.selectRoadSpaceByPosNoForHomePageSet", posNo);
	}

	@Override
	public Integer selectRoadSectionIdBySpaceNo(String spaceNo) {
		return (Integer) query("RoadSpaceDao.selectRoadSectionIdBySpaceNo", spaceNo);
	}

	@Override
	public List<DcjRoadSpaceVo> selectAllRoadSpaceByRoadSectionId(Integer sectionId) {
		return queryList("RoadSpaceDao.selectAllRoadSpaceByRoadSectionId", sectionId);
	}

	@Override
	public List<DcjRoadSpaceVo> selectEmptyRoadSpaceByRoadSectionId(Integer sectionId) {
		return queryList("RoadSpaceDao.selectEmptyRoadSpaceByRoadSectionId", sectionId);
	}

	@Override
	public List<DcjRoadSpaceVo> selectParkedRoadSpaceByRoadSectionId(Integer sectionId) {
		return queryList("RoadSpaceDao.selectParkedRoadSpaceByRoadSectionId", sectionId);
	}

	@Override
	public List<DcjRoadSpaceVo> selectAllRoadSpacePageByRoadSectionId(Integer roadSectionId, int currentPage, int pageSize) {
		PageBounds pb = new PageBounds(currentPage, pageSize);
		return queryListByPage("RoadSpaceDao.selectAllRoadSpaceByRoadSectionId", roadSectionId,pb);
	}
	
	@Override
	public Integer selectRoadSpaceCountByRoadSectionId(Integer roadSectionId) {
		return (Integer) query("RoadSpaceDao.selectRoadSpaceCountByRoadSectionId", roadSectionId);
	}

	@Override
	public List<DcjRoadSpaceVo> selectEmptyRoadSpacePageByRoadSectionId(Integer roadSectionId, int currentPage, int pageSize) {
		PageBounds pb = new PageBounds(currentPage, pageSize);
		return queryListByPage("RoadSpaceDao.selectEmptyRoadSpaceByRoadSectionId", roadSectionId,pb);
	}

	@Override
	public Integer selectEmptyRoadSpaceCountByRoadSectionId(Integer roadSectionId) {
		return (Integer) query("RoadSpaceDao.selectEmptyRoadSpaceCountByRoadSectionId", roadSectionId);
	}

	@Override
	public List<DcjRoadSpaceVo> selectParkedRoadSpacePageByRoadSectionId(Integer roadSectionId, int currentPage, int pageSize) {
		PageBounds pb = new PageBounds(currentPage, pageSize);
		return queryListByPage("RoadSpaceDao.selectParkedRoadSpaceByRoadSectionId", roadSectionId,pb);
	}

	@Override
	public Integer selectParkedRoadSpaceCountByRoadSectionId(Integer roadSectionId) {
		return (Integer) query("RoadSpaceDao.selectParkedRoadSpaceCountByRoadSectionId", roadSectionId);
	}
	
	@Override
	public List<RoadSpaceVo> selectParkedCarByCarNo(String posNo,String carNo) {
		Map<String,Object> val=new HashMap<String,Object>();
		val.put("posNo", posNo);
		val.put("carNo", carNo);
		return queryList("RoadSpaceDao.selectParkedCarByCarNo", val);
	}
	
}
