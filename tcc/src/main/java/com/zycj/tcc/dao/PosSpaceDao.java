package com.zycj.tcc.dao;

import java.util.List;

import com.zycj.tcc.domain.PosSpace;

public interface PosSpaceDao {

	/**
	 * 根据pos编号获取已选择的车位
	 * @param pos编号
	 * @return
	 */
	public List<PosSpace> loadSelectRoadSpaceByPosNo(String posNo);
	
	public List<String> loadSelectSpaceNoByPosNo(String posNo);
	
	/**
	 * 获取pos编号映射的车位个数
	 * @param posNo
	 * @return
	 */
	public Integer loadSelectRoadSpaceCountByPosNo(String posNo);
}
