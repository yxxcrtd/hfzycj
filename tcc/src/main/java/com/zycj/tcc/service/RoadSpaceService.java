package com.zycj.tcc.service;

import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.util.Page;



public interface RoadSpaceService {
	
	/**
	 * 根据pos终端编号获取车位信息
	 * @param type 1：全部数据、2：空闲、3：已驶入
	 * @param posNo 终端编号
	 * @return
	 */
	public Response selectRoadSpaceByPosNo(int type, String posNo);
	/**
	 * 根据pos终端编号获取-所有- 车位信息
	 * @param posNo 终端编号
	 * @return
	 */
	public Response selectAllRoadSpaceByPosNo(String posNo);
	/**
	 * 根据pos终端编号获取-空-车位集合
	 * @param posNo 终端编号
	 * @return 
	 */
	public Response selectEmptyRoadSpaceByPosNo(String posNo);
	/**
	 * 根据pos终端编号获取-已停车-车位集合
	 * @param posNo 终端编号
	 * @return 
	 */
	public Response selectParkedRoadSpaceByPosNo(String posNo);
	/**
	 * 根据pos终端编号获取车位信息 分页
	 * @param type 1：全部数据、2：空闲、3：已驶入
	 * @param posNo 终端编号
	 * @param page 分页信息
	 * @return
	 */
	public Response selectRoadSpaceByPosNoForPage(int type, String posNo, Page page);
	/**
	 * 根据pos终端编号获取-所有-车位信息 分页
	 * @param posNo 终端编号
	 * @param page 分页信息
	 * @return
	 */
	public Response selectAllRoadSpaceByPosNoForPage(String posNo, Page page);
	/**
	 * 根据pos终端编号获取-空-车位  分页
	 * @param posNo 终端编号
	 * @return 
	 */
	public Response selectEmptyRoadSpaceByPosNoForPage(String posNo, Page page);
	/**
	 * 根据pos终端编号获取-已停车-车位  分页
	 * @param posNo 终端编号
	 * @return 
	 */
	public Response selectParkedRoadSpaceByPosNoForPage(String posNo, Page page);
	/**
	 * 获取首页车位设置的所有车位数据
	 * @param posNo
	 * @return
	 */
	public Response loadAllRoadSpaceForHomePageSet(String posNo);
	/**
	 * 设置首页已选中的车位
	 * @param posNo
	 * @param spaceNoList 选中车位集合
	 * @return
	 */
	public Response setRoadSpaceForHomePageSet(String posNo, String spaceNoList);
	/**
	 * 根据泊位号或路段主键获取所有车位数据 --督查机
	 * @param type 1：全部数据、2：空闲、3：已驶入
	 * @param spaceNo 泊位号
	 * @param roadSectionId 路段主键id
	 * @return
	 */
	public Response loadAllRoadSpaceBySpaceNoForDCJ(int type, String spaceNo, Integer roadSectionId);
	
	/**
	 * 根据路段id查询 -所有- 车位信息
	 * @param sectionId
	 * @return
	 */
	public Response selectAllRoadSpaceByRoadSectionId(Integer sectionId);
	/**
	 * 根据路段id查询 -空- 车位信息
	 * @param sectionId
	 * @return
	 */
	public Response selectEmptyRoadSpaceByRoadSectionId(Integer sectionId);
	/**
	 * 根据路段id查询 -已停车- 车位信息
	 * @param sectionId
	 * @return
	 */
	public Response selectParkedRoadSpaceByRoadSectionId(Integer sectionId);
	
	public Response loadRoadSpaceBySpaceNoPageForDCJ(int type, Integer roadSectionId, Page page);
	public Response loadRoadSpaceBySpaceNoPageForDCJ(int type, String spaceNo, Integer roadSectionId, Page page);
	
	public Response selectParkedCarByCarNo(String posNo, String carNo);

}
