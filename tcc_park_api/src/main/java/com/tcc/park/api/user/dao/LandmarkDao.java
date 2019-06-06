package com.tcc.park.api.user.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.tcc.park.api.user.domain.Landmark;

public class LandmarkDao extends SqlSessionDaoSupport{

	public List<Landmark> selectByPointTypeId(Integer typeId,
			BigDecimal sLa,BigDecimal lLa,BigDecimal sLo,BigDecimal lLo
			,Integer pageStart,Integer pageEnd) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sLa", sLa);
		params.put("lLa", lLa);
		params.put("sLo", sLo);
		params.put("lLo", lLo);
		params.put("typeId", typeId);
		params.put("pageStart", pageStart);
		params.put("pageEnd", pageEnd);
		return getSqlSession().selectList("LandmarkDao.selectByPointTypeId", params);
	}

	public Integer selectByPointTypeIdCount(Integer typeId,
			BigDecimal sLa,BigDecimal lLa,BigDecimal sLo,BigDecimal lLo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sLa", sLa);
		params.put("lLa", lLa);
		params.put("sLo", sLo);
		params.put("lLo", lLo);
		params.put("typeId", typeId);
		return getSqlSession().selectOne("LandmarkDao.selectByPointTypeIdCount", params);
	}
}
