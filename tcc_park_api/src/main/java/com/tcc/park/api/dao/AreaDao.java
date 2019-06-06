package com.tcc.park.api.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.tcc.park.api.domain.RoadSection;
import com.tcc.park.api.vo.AreaListVo;

@Repository
public class AreaDao extends SqlSessionDaoSupport{
	
	public List<AreaListVo>  selectAreaList() {
		return getSqlSession().selectList("AreaDao.selectAreaList");
	}
	
	public List<RoadSection>  selectByAreaList(String areaCode,Integer pageStart,Integer pageEnd) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("areaCode", areaCode);
		params.put("pageStart", pageStart);
		params.put("pageEnd", pageEnd);
		return getSqlSession().selectList("AreaDao.selectByAreaList",params);
	}
	
	public Integer selectByAreaListCount(String areaCode) {
		return getSqlSession().selectOne("AreaDao.selectByAreaListCount",areaCode);
	}
	
}
