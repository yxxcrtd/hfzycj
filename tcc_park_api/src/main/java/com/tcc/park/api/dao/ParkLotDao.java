package com.tcc.park.api.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.tcc.park.api.domain.RoadSection;

@Repository
public class ParkLotDao extends SqlSessionDaoSupport{
	
	public List<RoadSection>  selectByPointFee(Integer feeType, BigDecimal sLa,BigDecimal lLa,BigDecimal sLo,BigDecimal lLo
			,Integer pageStart,Integer pageEnd) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sLa", sLa);
		params.put("lLa", lLa);
		params.put("sLo", sLo);
		params.put("lLo", lLo);
		params.put("feeType", feeType);
		params.put("pageStart", pageStart);
		params.put("pageEnd", pageEnd);
		return getSqlSession().selectList("ParkLotDao.selectByPointFee", params);
	}
	
	public Integer  selectByPointFeeCount(Integer feeType, BigDecimal sLa,BigDecimal lLa,BigDecimal sLo,BigDecimal lLo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sLa", sLa);
		params.put("lLa", lLa);
		params.put("sLo", sLo);
		params.put("lLo", lLo);
		params.put("feeType", feeType);
		return getSqlSession().selectOne("ParkLotDao.selectByPointFeeCount", params);
	}
	
}
