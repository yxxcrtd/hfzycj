package com.zycj.tcc.dao;

import java.util.Date;
import java.util.List;

import com.zycj.tcc.domain.RoadSpace;
import com.zycj.tcc.vo.DcjRoadSpaceVo;
import com.zycj.tcc.vo.RoadSpaceSectionInfo;
import com.zycj.tcc.vo.RoadSpaceVo;

public interface RoadSpaceDao {

	/**
	 * 根据pos终端编号获取-所有-车位信息集合(空闲和已驶入车位)
	 * @param posNo 终端编号
	 * @return 车位信息集合
	 */
	public List<RoadSpaceVo> selectAllRoadSpaceByPosNo(String posNo);
	public List<RoadSpaceVo> selectAllRoadSpaceByPosNoExtend(String posNo);
	public Integer selectRoadSpaceCountByPosNo(String posNo);
	/**
	 * 根据pos终端编号获取-空-车位集合
	 * @param posNo 终端编号
	 * @return 车位信息集合
	 */
	public List<RoadSpaceVo> selectEmptyRoadSpaceByPosNo(String posNo);
	public List<RoadSpaceVo> selectEmptyRoadSpaceByPosNoExtend(String posNo);
	public Integer selectEmptyRoadSpaceCountByPosNo(String posNo);
	/**
	 * 根据pos终端编号获取-已停车-车位集合
	 * @param posNo 终端编号
	 * @return 车位信息集合
	 */
	public List<RoadSpaceVo> selectParkedRoadSpaceByPosNo(String posNo);
	public List<RoadSpaceVo> selectParkedRoadSpaceByPosNoExtend(String posNo);
	public Integer selectParkedRoadSpaceCountByPosNo(String posNo);
	/**
	 * 按分页 根据pos终端编号获取-所有-车位信息集合(空闲和已驶入车位)
	 * @param posNo 终端编号
	 * @param currentPage 当前页
	 * @param pageSize 每页个数
	 * @return
	 */
	public List<RoadSpaceVo> selectWaitConfirmRoadSpaces(String posNo, int currentPage, int pageSize);
	public List<RoadSpaceVo> selectALLRoadSpaceByPosNoForPage(String posNo, int currentPage, int pageSize);
	public List<RoadSpaceVo> selectEmptyRoadSpaceByPosNoForPage(String posNo, int currentPage, int pageSize);
	public List<RoadSpaceVo> selectParkedRoadSpaceByPosNoForPage(String posNo, int currentPage, int pageSize);
	
	/**
	 * 更新车位停车信息当车辆驶入时
	 * @param spaceNo 车位编号
	 * @param carNo 车牌号
	 * @param parkTime 驶入时间
	 * @return
	 */
	public int updateRoadSpaceBySpaceNoForCarEntry(String spaceNo, String carNo, Date parkTime);
	
	/**
	 * 更新停车位信息当车辆驶出时
	 * @param spaceNo 停车位编号
	 * @param driveTime 驶出时间
	 * @return
	 */
	public int updateRoadSpaceBySpaceNoForCarExit(String spaceNo, Date driveTime);
	
	/**
	 * 更新停车为未停车
	 * @param spaceNo 车位编号
	 * @return
	 */
	public int updateRoadSpaceNoParkBySpaceNo(String spaceNo);
	/**
	 * 根据车位编号获取所属路段收费规则id
	 * @param spaceNo 车位编号
	 * @return 收费规则id
	 */
	public Integer selectFeeRegularIdBySpaceNo(String spaceNo);
	
	/**
	 * 根据车位编号获取车位信息
	 * @param spaceNo 车位编号
	 * @return 车位信息
	 */
	public RoadSpace selectRoadSpaceBySpaceNo(String spaceNo);
	
	/**
	 * 根据车位编号获取车位信息、计费规则id、路段id
	 * @param spaceNo 车位编号
	 * @return
	 */
	public RoadSpaceSectionInfo selectRoadSpaceSectionInfoBySpaceNo(String spaceNo);
	
	/**
	 * 更新所有停车状体是停车的为空闲
	 * @return
	 */
	public int updateNoParkByAll();
	
	/**
	 * 根据pos终端编号获取 所有 车位编号 信息
	 * @param posNo
	 * @return
	 */
	public List<String> selectRoadSpaceByPosNoForHomePageSet(String posNo);
	
	/**
	 * 根据泊车位编号查询所属路段id
	 * @param spaceNo 泊车位编号
	 * @return
	 */
	public Integer selectRoadSectionIdBySpaceNo(String spaceNo);
	/**
	 * 根据路段id查询 -所有- 车位信息
	 * @param sectionId
	 * @return
	 */
	public List<DcjRoadSpaceVo> selectAllRoadSpaceByRoadSectionId(Integer sectionId);
	/**
	 * 根据路段id查询 -空- 车位信息
	 * @param sectionId
	 * @return
	 */
	public List<DcjRoadSpaceVo> selectEmptyRoadSpaceByRoadSectionId(Integer sectionId);
	/**
	 * 根据路段id查询 -已停车- 车位信息
	 * @param sectionId
	 * @return
	 */
	public List<DcjRoadSpaceVo> selectParkedRoadSpaceByRoadSectionId(Integer sectionId);
	
	/**
	 * 根据路段id查询 -所有- 车位信息
	 * @param roadSectionId
	 * @param currentPage 当前页
	 * @param pageSize 每页个数
	 * @return
	 */
	public List<DcjRoadSpaceVo> selectAllRoadSpacePageByRoadSectionId(
            Integer roadSectionId, int currentPage, int pageSize);
	public Integer selectRoadSpaceCountByRoadSectionId(Integer roadSectionId);
	/**
	 * 根据路段id查询 -空- 车位信息
	 * @param roadSectionId
	 * @param currentPage 当前页
	 * @param pageSize 每页个数
	 * @return
	 */
	public List<DcjRoadSpaceVo> selectEmptyRoadSpacePageByRoadSectionId(
            Integer roadSectionId, int currentPage, int pageSize);
	public Integer selectEmptyRoadSpaceCountByRoadSectionId(Integer roadSectionId);
	/**
	 * 根据路段id查询 -已停车- 车位信息
	 * @param roadSectionId
	 * @param currentPage 当前页
	 * @param pageSize 每页个数
	 * @return
	 */
	public List<DcjRoadSpaceVo> selectParkedRoadSpacePageByRoadSectionId(
            Integer roadSectionId, int currentPage, int pageSize);
	public Integer selectParkedRoadSpaceCountByRoadSectionId(Integer roadSectionId);
	
	public List<RoadSpaceVo> selectParkedCarByCarNo(String posNo, String carNo);
}
