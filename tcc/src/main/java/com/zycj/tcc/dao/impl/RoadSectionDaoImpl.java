package com.zycj.tcc.dao.impl;

import org.springframework.stereotype.Repository;

import com.zycj.tcc.dao.BaseDao;
import com.zycj.tcc.dao.RoadSectionDao;

@Repository
public class RoadSectionDaoImpl extends BaseDao implements RoadSectionDao {

	@Override
	public String selectSectionNameByPrimaryKey(Integer id) {
		return (String) query("RoadSectionDao.selectSectionNameByPrimaryKey", id);
	}

	@Override
	public Integer selectFeeTypeByPosNo(String posNo) {
		return (Integer) query("RoadSectionDao.selectFeeTypeByPosNo", posNo);
	}

	@Override
	public int addSpace(String spaceNo) {
		return update("RoadSectionDao.addSpace", spaceNo);
	}

	@Override
	public int subtractSpace(String spaceNo) {
		return update("RoadSectionDao.subtractSpace", spaceNo);
	}

	@Override
	public int clearRoadSectionUsed() {
		return update("RoadSectionDao.clearRoadSectionUsed",null);
	}
	
}
