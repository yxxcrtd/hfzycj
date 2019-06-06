package com.zycj.tcc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.common.SystemConfig;
import com.zycj.tcc.dao.PosSpaceDao;
import com.zycj.tcc.dao.RoadSpaceDao;
import com.zycj.tcc.domain.PosSpace;
import com.zycj.tcc.domain.RoadSpace;
import com.zycj.tcc.mybatis.mapper.PosSpaceMapper;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.service.RoadSpaceService;
import com.zycj.tcc.util.ArrayUtil;
import com.zycj.tcc.util.Page;
import com.zycj.tcc.util.PageUtil;
import com.zycj.tcc.vo.DcjRoadSpaceVo;
import com.zycj.tcc.vo.RoadSpaceHPSetVo;
import com.zycj.tcc.vo.RoadSpaceVo;

@Service
public class RoadSpaceServiceImpl implements RoadSpaceService {
	private final static Logger log = Logger.getLogger(RoadSpaceServiceImpl.class); 
	
	@Autowired
	private RoadSpaceDao roadSpaceDao;
	@Autowired
	private PosSpaceMapper posSpaceMapper;
	@Autowired
	private PosSpaceDao posSpaceDao;
	/**
	 * 过滤已选择的车位，如果已选择的车位集合为null或为0，就显示所有车位集合
	 * @param selectRsList 已选择的车位集合
	 * @param allRsList 所有车位集合(三种状态:已用与空闲、已用、空闲)
	 * @return
	 
	public List<RoadSpaceVo> toFilterRoadSpace(List<String> selectRsList,List<RoadSpaceVo> allRsList){
		List<RoadSpaceVo> jgList=null;
		if(selectRsList==null||selectRsList.size()==0){
			jgList=allRsList;
		}else{
			jgList=new ArrayList<RoadSpaceVo>();
			for(RoadSpaceVo rsv:allRsList){
				if(selectRsList.contains(rsv.getA())){
					jgList.add(rsv);
				}
			}
		}
		return jgList;
	}
	*/
	@Override
	public Response selectRoadSpaceByPosNo(int type, String posNo) {
		switch (type) {
			case 1:
				return selectAllRoadSpaceByPosNo(posNo);
			case 2:
				return selectEmptyRoadSpaceByPosNo(posNo);
			case 3:
				return selectParkedRoadSpaceByPosNo(posNo);
		}
		return ResponseUtil.createResponse(ResultCode.LOAD_ROADSPACE_TYPE_NO_MATCH_ERROR);
	}
	@Override
	public Response selectAllRoadSpaceByPosNo(String posNo) {
		//获取已经选择的车位
//		List<PosSpace> selectRsList=posSpaceDao.loadSelectRoadSpaceByPosNo(posNo);
		List<RoadSpaceVo> jgList=null;
//		if(selectRsList==null||selectRsList.size()==0){//未设置首页
//			jgList=roadSpaceDao.selectAllRoadSpaceByPosNo(posNo);
//			Set<String> rsSet=new HashSet<String>();
//			for(RoadSpaceVo rsv:jgList){
//				rsSet.add(rsv.getSpaceNo());
//			}
//			if(rsSet.size()>0){
//				for(String spaceNo:rsSet){
//					PosSpace ps=new PosSpace();
//					ps.setPosNo(posNo);
//					ps.setSpaceNo(spaceNo);
//					ps.setStatus(Constants.POS_SPACE_YES);
//					posSpaceMapper.insert(ps);
//				}
//			}
//		}else{
			jgList=roadSpaceDao.selectAllRoadSpaceByPosNoExtend(posNo);
//		}
		
		Integer totalPage=PageUtil.getTotalPage(jgList.size(), SystemConfig.POS_Parking_PageSize);//总页数
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("pageSize", totalPage);//总页数
		res.put("info", jgList);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, res);
	}

	@Override
	public Response selectEmptyRoadSpaceByPosNo(String posNo) {
		List<RoadSpaceVo> rsvList=roadSpaceDao.selectEmptyRoadSpaceByPosNoExtend(posNo);
		Integer totalPage=PageUtil.getTotalPage(rsvList.size(), SystemConfig.POS_Parking_PageSize);//总页数
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("pageSize", totalPage);//总页数
		res.put("info", rsvList);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, res);
	}

	@Override
	public Response selectParkedRoadSpaceByPosNo(String posNo) {
		List<RoadSpaceVo> rsvList=roadSpaceDao.selectParkedRoadSpaceByPosNoExtend(posNo);
		Integer totalPage=PageUtil.getTotalPage(rsvList.size(), SystemConfig.POS_Parking_PageSize);//总页数
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("pageSize", totalPage);//总页数
		res.put("info", rsvList);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, res);
	}

	@Override
	public Response selectRoadSpaceByPosNoForPage(int type, String posNo,Page page) {
		switch (type) {
		case 1:
			return selectWaitConfirmRoadSpaces(posNo,page);
		case 2:
			return selectEmptyRoadSpaceByPosNoForPage(posNo,page);
		case 3:
			return selectParkedRoadSpaceByPosNoForPage(posNo,page);
	}
	return ResponseUtil.createResponse(ResultCode.LOAD_ROADSPACE_TYPE_NO_MATCH_ERROR);
	}
	
	private Response selectWaitConfirmRoadSpaces(String posNo,Page page){
		List<RoadSpaceVo> pl=(List<RoadSpaceVo>) roadSpaceDao.selectWaitConfirmRoadSpaces(posNo, page.getCurrentPage(), page.getPageSize());
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("f", PageUtil.getTotalPage(roadSpaceDao.selectRoadSpaceCountByPosNo(posNo), SystemConfig.POS_Parking_PageSize));//pageSize总页数
		res.put("info", pl);
		res.put("sysDate", new Date().getTime());
		res.put("car_out_wait_time", SystemConfig.CAR_OUT_WAIT_TIME);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS,res);
	}
	@Override
	public Response selectAllRoadSpaceByPosNoForPage(String posNo,Page page){
//		Paginator paginator=((PageList)pl).getPaginator();
		List<RoadSpaceVo> pl=(List<RoadSpaceVo>) roadSpaceDao.selectALLRoadSpaceByPosNoForPage(posNo, page.getCurrentPage(), page.getPageSize());
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("f", PageUtil.getTotalPage(roadSpaceDao.selectRoadSpaceCountByPosNo(posNo), SystemConfig.POS_Parking_PageSize));//pageSize总页数
		res.put("info", pl);
		res.put("sysDate", new Date().getTime());
		res.put("car_out_wait_time", SystemConfig.CAR_OUT_WAIT_TIME);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS,res);
	}
	@Override
	public Response selectEmptyRoadSpaceByPosNoForPage(String posNo, Page page) {
		List<RoadSpaceVo> pl=(List<RoadSpaceVo>) roadSpaceDao.selectEmptyRoadSpaceByPosNoForPage(posNo, page.getCurrentPage(), page.getPageSize());
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("f", PageUtil.getTotalPage(roadSpaceDao.selectEmptyRoadSpaceCountByPosNo(posNo), SystemConfig.POS_Parking_PageSize));//pageSize总页数
		res.put("info", pl);
		res.put("sysDate", new Date().getTime());
		res.put("car_out_wait_time", SystemConfig.CAR_OUT_WAIT_TIME);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS,res);
	}
	@Override
	public Response selectParkedRoadSpaceByPosNoForPage(String posNo, Page page) {
		List<RoadSpaceVo> pl=(List<RoadSpaceVo>) roadSpaceDao.selectParkedRoadSpaceByPosNoForPage(posNo, page.getCurrentPage(), page.getPageSize());
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("f", PageUtil.getTotalPage(roadSpaceDao.selectParkedRoadSpaceCountByPosNo(posNo), SystemConfig.POS_Parking_PageSize));//pageSize总页数
		res.put("info", pl);
		res.put("sysDate", new Date().getTime());
		res.put("car_out_wait_time", SystemConfig.CAR_OUT_WAIT_TIME);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS,res);
	}
	@Override
	public Response loadAllRoadSpaceForHomePageSet(String posNo) {
		//获取所有车位
		List<String> allRsList=roadSpaceDao.selectRoadSpaceByPosNoForHomePageSet(posNo);
		//获取已经选择的车位
		List<String> selectSpaceNoList=posSpaceDao.loadSelectSpaceNoByPosNo(posNo);
		int spaceSize=0;
		if(selectSpaceNoList==null||selectSpaceNoList.size()==0){
			List<PosSpace> selectRsList=posSpaceDao.loadSelectRoadSpaceByPosNo(posNo);
			posSpaceCheck(selectRsList,allRsList,posNo);
			spaceSize=allRsList.size();
			selectSpaceNoList=posSpaceDao.loadSelectSpaceNoByPosNo(posNo);
		}else{
			spaceSize=selectSpaceNoList.size();
		}
		List<RoadSpaceHPSetVo> rsList=new ArrayList<RoadSpaceHPSetVo>();
		for(String spaceNo:allRsList){
			rsList.add(new RoadSpaceHPSetVo(spaceNo,selectSpaceNoList.contains(spaceNo)?1:0));
		}
		
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("spaceSize", spaceSize);//车位个数
		res.put("info", rsList);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, res);
	}
	
	@Override
	@Transactional
	public Response setRoadSpaceForHomePageSet(String posNo, String spaceNoList) {
		List<String> snList=ArrayUtil.strToArray(spaceNoList);
		if(snList==null||snList.size()==0){
			return ResponseUtil.createResponse(ResultCode.SPACENO_LIST_ISBLANK_ERROR);
		}
		//获取所有车位
		List<String> allRsList=roadSpaceDao.selectRoadSpaceByPosNoForHomePageSet(posNo);
		//获取已经选择的车位
		List<PosSpace> selectRsList=posSpaceDao.loadSelectRoadSpaceByPosNo(posNo);
		
		posSpaceCheck(selectRsList,allRsList,posNo);
		
		Set<PosSpace> xzList=new HashSet<PosSpace>();//选择的集合
		Set<PosSpace> qxList=new HashSet<PosSpace>();//取消的集合
		for(PosSpace ps:selectRsList){
			if(snList.contains(ps.getSpaceNo())){
				xzList.add(ps);
			}else{
				qxList.add(ps);
			}
		}
		log.warn("选择："+xzList.size()+"个；取消："+qxList.size());
		for (PosSpace ps:xzList) {
			if(ps.getStatus().intValue()==0){
				System.out.println("选中："+ps.getPosNo()+"-"+ps.getSpaceNo());
				PosSpace up=new PosSpace();
				up.setId(ps.getId());
				up.setStatus(Constants.POS_SPACE_YES);
				posSpaceMapper.updateByPrimaryKeySelective(up);
			}
		}
		for (PosSpace ps:qxList) {
			if(ps.getStatus().intValue()==1){
				System.out.println("取消："+ps.getPosNo()+"-"+ps.getSpaceNo());
				PosSpace up=new PosSpace();
				up.setId(ps.getId());
				up.setStatus(Constants.POS_SPACE_NO);
				posSpaceMapper.updateByPrimaryKeySelective(up);
			}
		}
		return ResponseUtil.createResponse(ResultCode.SUCCESS);
	}
	//自检
	public void posSpaceCheck(List<PosSpace> selectRsList,List<String> allRsList,String posNo){
		List<PosSpace> addList=new ArrayList<PosSpace>();//新增映射车位集合
		for (String spaceNo:allRsList) {
			if(!contains(selectRsList,spaceNo)){
				PosSpace ps=new PosSpace();
				ps.setPosNo(posNo);
				ps.setSpaceNo(spaceNo);
				ps.setStatus(Constants.POS_SPACE_YES);
				addList.add(ps);
			}
		}
		//有需要新增的映射车位
		if(addList.size()>0){
			log.warn("车位表与pos_space表映射的数据不一致,需要新增到pos_space表的个数为："+addList.size());
			log.warn("开始--新增pos_space表数据......");
			for(PosSpace ps:addList){
				log.warn("insert:"+ps.getPosNo()+"-"+ps.getSpaceNo());
				posSpaceMapper.insertSelective(ps);
			}
			log.warn("结束--新增pos_space表数据");
			selectRsList=posSpaceDao.loadSelectRoadSpaceByPosNo(posNo);
		}
		List<PosSpace> delList=new ArrayList<PosSpace>();//删除映射车位集合
		for(PosSpace ps:selectRsList){
			if(!allRsList.contains(ps.getSpaceNo())){
				delList.add(ps);
			}
		}
		//有需要删除的映射车位
		if(delList.size()>0){
			log.warn("车位表与pos_space表映射的数据不一致,需要从pos_space表删除的个数为："+delList.size());
			log.warn("开始--删除pos_space表数据......");
			for(PosSpace ps:delList){
				log.warn("delete:"+ps.getPosNo()+"-"+ps.getSpaceNo());
				posSpaceMapper.deleteByPrimaryKey(ps.getId());
				selectRsList.remove(ps);
			}
			log.warn("结束--删除pos_space表数据");
		}
	}
	//spaceNo是否存在于list中，true:存在，false:不存在
	public boolean contains(List<PosSpace> selectRsList,String spaceNo){
		for(PosSpace ps:selectRsList){
			if(ps.getSpaceNo().equals(spaceNo)){
				return true;
			}
		}
		return false;
	}
	@Override
	public Response loadAllRoadSpaceBySpaceNoForDCJ(int type, String spaceNo,Integer roadSectionId) {
		Integer sectionId=null;
		//泊车号和路段id同时存在时优先泊车号
		if(roadSectionId.intValue()!=-1){
			sectionId=roadSectionId;
		}
		if(!"-1".equals(spaceNo)){
			RoadSpace rs=roadSpaceDao.selectRoadSpaceBySpaceNo(spaceNo);
			if(rs==null){
				return ResponseUtil.createResponse(ResultCode.ROAD_SPACE_NO_EXIST_ERROR);
			}
			sectionId=rs.getSectionId();
		}
		if(sectionId==null){
			ResponseUtil.createResponse(ResultCode.ROAD_SECTION_ISBLANK_ERROR);
		}
		//1：全部数据、2：空闲、3：已驶入
		switch (type) {
			case 1:
				return selectAllRoadSpaceByRoadSectionId(sectionId);
			case 2:
				return selectEmptyRoadSpaceByRoadSectionId(sectionId);
			case 3:
				return selectParkedRoadSpaceByRoadSectionId(sectionId);
		}
		return ResponseUtil.createResponse(ResultCode.LOAD_ROADSPACE_TYPE_NO_MATCH_ERROR);
	}
	@Override
	public Response selectAllRoadSpaceByRoadSectionId(Integer sectionId) {
		List<DcjRoadSpaceVo> jgList=roadSpaceDao.selectAllRoadSpaceByRoadSectionId(sectionId);
		Integer totalPage=PageUtil.getTotalPage(jgList.size(), SystemConfig.POS_Parking_PageSize);//总页数
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("pageSize", totalPage);//总页数
		res.put("roadId", sectionId);//路段主键
		res.put("info", jgList);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, res);
	}
	@Override
	public Response selectEmptyRoadSpaceByRoadSectionId(Integer sectionId) {
		List<DcjRoadSpaceVo> rsvList=roadSpaceDao.selectEmptyRoadSpaceByRoadSectionId(sectionId);
		Integer totalPage=PageUtil.getTotalPage(rsvList.size(), SystemConfig.POS_Parking_PageSize);//总页数
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("pageSize", totalPage);//总页数
		res.put("roadId", sectionId);//路段主键
		res.put("info", rsvList);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, res);
	}
	@Override
	public Response selectParkedRoadSpaceByRoadSectionId(Integer sectionId) {
		List<DcjRoadSpaceVo> rsvList=roadSpaceDao.selectParkedRoadSpaceByRoadSectionId(sectionId);
		Integer totalPage=PageUtil.getTotalPage(rsvList.size(), SystemConfig.POS_Parking_PageSize);//总页数
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("pageSize", totalPage);//总页数
		res.put("roadId", sectionId);//路段主键
		res.put("info", rsvList);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, res);
	}
	@Override
	public Response loadRoadSpaceBySpaceNoPageForDCJ(int type,Integer roadSectionId, Page page) {
		//1：全部数据、2：空闲、3：已驶入
		switch (type) {
			case 1:
				return selectAllRoadSpacePageByRoadSectionId(roadSectionId,page);
			case 2:
				return selectEmptyRoadSpacePageByRoadSectionId(roadSectionId,page);
			case 3:
				return selectParkedRoadSpacePageByRoadSectionId(roadSectionId,page);
		}
		return ResponseUtil.createResponse(ResultCode.LOAD_ROADSPACE_TYPE_NO_MATCH_ERROR);
	}
	
	@Override
	public Response loadRoadSpaceBySpaceNoPageForDCJ(int type, String spaceNo,
			Integer roadSectionId, Page page) {
		Integer sectionId=null;
		//泊车号和路段id同时存在时优先泊车号
		if(roadSectionId.intValue()!=-1){
			sectionId=roadSectionId;
		}
		if(!"-1".equals(spaceNo)){
			RoadSpace rs=roadSpaceDao.selectRoadSpaceBySpaceNo(spaceNo);
			if(rs==null){
				return ResponseUtil.createResponse(ResultCode.ROAD_SPACE_NO_EXIST_ERROR);
			}
			sectionId=rs.getSectionId();
		}
		if(sectionId==null){
			ResponseUtil.createResponse(ResultCode.ROAD_SECTION_ISBLANK_ERROR);
		}
		//1：全部数据、2：空闲、3：已驶入
		switch (type) {
			case 1:
				return selectAllRoadSpacePageByRoadSectionId(sectionId,page);
			case 2:
				return selectEmptyRoadSpacePageByRoadSectionId(sectionId,page);
			case 3:
				return selectParkedRoadSpacePageByRoadSectionId(sectionId,page);
		}
		return ResponseUtil.createResponse(ResultCode.LOAD_ROADSPACE_TYPE_NO_MATCH_ERROR);
	}
	
	@Override
	public Response selectParkedCarByCarNo(String posNo,String carNo) {
		List<RoadSpaceVo> pl=(List<RoadSpaceVo>) roadSpaceDao.selectParkedCarByCarNo(posNo, carNo);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS,pl);
	}
	
	private Response selectAllRoadSpacePageByRoadSectionId(Integer roadSectionId, Page page) {
//		Paginator paginator=((PageList)pl).getPaginator();
		List<DcjRoadSpaceVo> pl=(List<DcjRoadSpaceVo>) roadSpaceDao.selectAllRoadSpacePageByRoadSectionId(roadSectionId, page.getCurrentPage(), page.getPageSize());
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("a", PageUtil.getTotalPage(roadSpaceDao.selectRoadSpaceCountByRoadSectionId(roadSectionId), SystemConfig.POS_Parking_PageSize));//pageSize总页数
		res.put("b",roadSectionId);
		res.put("info", pl);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS,res);
	}
	private Response selectEmptyRoadSpacePageByRoadSectionId(Integer roadSectionId, Page page) {
		List<DcjRoadSpaceVo> pl=(List<DcjRoadSpaceVo>) roadSpaceDao.selectEmptyRoadSpacePageByRoadSectionId(roadSectionId, page.getCurrentPage(), page.getPageSize());
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("a", PageUtil.getTotalPage(roadSpaceDao.selectEmptyRoadSpaceCountByRoadSectionId(roadSectionId), SystemConfig.POS_Parking_PageSize));//pageSize总页数
		res.put("b",roadSectionId);
		res.put("info", pl);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS,res);
	}
	private Response selectParkedRoadSpacePageByRoadSectionId(Integer roadSectionId, Page page) {
		List<DcjRoadSpaceVo> pl=(List<DcjRoadSpaceVo>) roadSpaceDao.selectParkedRoadSpacePageByRoadSectionId(roadSectionId, page.getCurrentPage(), page.getPageSize());
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("a", PageUtil.getTotalPage(roadSpaceDao.selectParkedRoadSpaceCountByRoadSectionId(roadSectionId), SystemConfig.POS_Parking_PageSize));//pageSize总页数
		res.put("b",roadSectionId);
		res.put("info", pl);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS,res);
	}
	
}
