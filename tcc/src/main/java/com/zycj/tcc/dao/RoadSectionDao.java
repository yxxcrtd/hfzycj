package com.zycj.tcc.dao;

public interface RoadSectionDao {

	/**
	 * 查询路段名称
	 * @param id 主键
	 * @return 路段名称
	 */
	public String selectSectionNameByPrimaryKey(Integer id);
	/**
	 * 根据pos绑定的路段查询此路段的计费规则id
	 * @param posNo pos终端编号
	 * @return 计费规则id
	 */
	public Integer selectFeeTypeByPosNo(String posNo);
	/**
	 * 车位占用数加1
	 * @param spaceNo
	 * @return
	 */
	public int addSpace(String spaceNo);
	/**
	 * 车位占用数减1
	 * @param spaceNo
	 * @return
	 */
	public int subtractSpace(String spaceNo);
	
	/**
	 * 清空路段车位占用数
	 * @return
	 */
	public int clearRoadSectionUsed();
}
