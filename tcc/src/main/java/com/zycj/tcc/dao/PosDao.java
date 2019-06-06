package com.zycj.tcc.dao;

import java.util.List;

import com.zycj.tcc.domain.Pos;

public interface PosDao {

	/**
	 * 获取POS信息
	 * @param posNo pos终端编号
	 * @return
	 */
	public Pos selectPosByPosNo(String posNo);
	
	/**
	 * 更新pos对应的路段名称
	 * @param id pos主键
	 * @param sectionName 新的路段名称
	 * @return
	 */
	public int updateSectionNameById(Integer id, String sectionName);
	
	/**
	 * 更新pos对应员工的在线状态
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @return
	 */
	public int updateEmpOnlineStatusDown(String empNo, String posNo);

	/**
	 * 日终 更新pos在线员工为离线
	 */
	public int clearPosEmpStatus();
	/**
	 * 更新pos对应员工的在线状态
	 * @param posNo pos终端编号
	 * @return
	 */
	public int updateEmpOnlineStatusDownByPosNo(String posNo);
	
	/**
	 * 根据停车场ID得到该停车场上所有的POS对象
	 * @param sectionId
	 * @return
	 */
	public List<Pos> findPos(Integer sectionId);
}
