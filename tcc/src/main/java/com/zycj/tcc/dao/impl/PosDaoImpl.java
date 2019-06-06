package com.zycj.tcc.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zycj.tcc.dao.BaseDao;
import com.zycj.tcc.dao.PosDao;
import com.zycj.tcc.domain.Pos;

@Repository
public class PosDaoImpl extends BaseDao implements PosDao {

	@Override
	public Pos selectPosByPosNo(String posNo) {
		return (Pos) query("PosDao.selectPosByPosNo", posNo);
	}

	@Override
	public int updateSectionNameById(Integer id, String sectionName) {
		Map<String,Object> val=new HashMap<String,Object>();
		val.put("id",id);
		val.put("sectionName", sectionName);
		return update("PosDao.updateSectionNameById", val);
	}

	@Override
	public int updateEmpOnlineStatusDown(String empNo,String posNo) {
		Map<String,Object> val=new HashMap<String,Object>();
		val.put("empNo",empNo);
		val.put("posNo", posNo);
		return update("PosDao.updateEmpOnlineStatusDown",val);
	}

	@Override
	public int updateEmpOnlineStatusDownByPosNo(String posNo) {
		return update("PosDao.updateEmpOnlineStatusDownByPosNo",posNo);
	}

	@Override
	public int clearPosEmpStatus() {
		return update("PosDao.clearPosEmpStatus",null);
	}

	@Override
	public List<Pos> findPos(Integer sectionId) {
		return queryList("PosDao.findPos", sectionId);
	}
}
