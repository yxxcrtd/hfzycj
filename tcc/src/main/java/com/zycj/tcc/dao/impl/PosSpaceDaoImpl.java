package com.zycj.tcc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zycj.tcc.dao.BaseDao;
import com.zycj.tcc.dao.PosSpaceDao;
import com.zycj.tcc.domain.PosSpace;

@Repository
public class PosSpaceDaoImpl extends BaseDao implements PosSpaceDao {

	@Override
	public List<PosSpace> loadSelectRoadSpaceByPosNo(String posNo) {
		return queryList("PosSpaceDao.loadSelectRoadSpaceByPosNo", posNo);
	}

	@Override
	public List<String> loadSelectSpaceNoByPosNo(String posNo) {
		return queryList("PosSpaceDao.loadSelectSpaceNoByPosNo", posNo);
	}

	@Override
	public Integer loadSelectRoadSpaceCountByPosNo(String posNo) {
		return (Integer) query("PosSpaceDao.loadSelectRoadSpaceCountByPosNo", posNo);
	}

}
