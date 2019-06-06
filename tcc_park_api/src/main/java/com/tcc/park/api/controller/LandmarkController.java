package com.tcc.park.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.park.api.common.ResultCode;
import com.tcc.park.api.common.SmsConfig;
import com.tcc.park.api.service.LandmarkService;
import com.tcc.park.api.user.domain.LandmarkType;
import com.tcc.park.api.util.CarGuideSysUtil;
import com.tcc.park.api.util.HttpUtil;
import com.tcc.park.api.util.RSACoder;
import com.tcc.park.api.util.URLUtil;
import com.tcc.park.api.vo.LandmarkVo;
import com.tcc.park.api.vo.ListResult;
import com.tcc.park.api.vo.PlaceTimesData;

import net.sf.json.JSONObject;

@Controller
public class LandmarkController {
	private static final Logger logger = Logger.getLogger(LandmarkController.class);
	@Autowired
	private LandmarkService landmarkService;
	
	@RequestMapping("/api/landmarkType/list/{urlParams}")
	public @ResponseBody String queryLandmarkType(@PathVariable String urlParams) {
		ListResult<HashMap<String,Object>> result = new ListResult<HashMap<String,Object>>();
		result.setResultCode(ResultCode.APP_PARAM_ERROR);
//		Map<String, String> urlMap;
		try {
//			urlMap = URLUtil.analysis(RSACoder.decryptDataByRSA64(urlParams));
//			logger.info("landmarkTypeList="+urlMap);
//			if (urlMap == null) {
//				return JSONObject.fromObject(result).toString();
//			}
			List<LandmarkType> landmarkTypes=landmarkService.selectAllLandmarkType();
			if (landmarkTypes!=null&&landmarkTypes.size()>0) {
				List<HashMap<String,Object>> types=new ArrayList<HashMap<String,Object>>();
				for (LandmarkType type:landmarkTypes) {
					HashMap<String,Object> v=new HashMap<String,Object>();
					v.put("id", type.getId());
					v.put("name", type.getName());
					v.put("img", SmsConfig.IMGURL+type.getImgUrl());
					types.add(v);
				}
				result.setInfo(types);
				
			}
			result.setResultCode(ResultCode.APP_SUCCESS);
			result.setPageNum(1);
			System.out.println("地标类型:"+JSONObject.fromObject(result).toString());
			return JSONObject.fromObject(result).toString();
		} catch (Exception e) {
			logger.warn("获取地标类型异常", e);
			result.setResultCode(ResultCode.APP_SYSTEM_ERROR);
			return JSONObject.fromObject(result).toString();
		}
	}
	
	@RequestMapping("/api/landmark/list/{urlParams}")
	public @ResponseBody String reg(@PathVariable String urlParams) {
		ListResult<LandmarkVo> listResult = new ListResult<LandmarkVo>();
		listResult.setResultCode(ResultCode.APP_PARAM_ERROR);
		Map<String, String> urlMap;
		try {
			urlMap = URLUtil.analysis(RSACoder.decryptDataByRSA64(urlParams));
			logger.info("landmarkList="+urlMap);
			if (urlMap == null) {
				return JSONObject.fromObject(listResult).toString();
			}
			Integer distance = MapUtils.getInteger(urlMap, "distance",1000);
			Integer typeId = MapUtils.getInteger(urlMap, "type_id",0);//0全部,对应的地标类型id
			BigDecimal la = new BigDecimal(MapUtils.getDoubleValue(urlMap, "latitude",0));
			BigDecimal lo = new BigDecimal(MapUtils.getDoubleValue(urlMap, "longitude",0));
			if (la ==null || lo ==null) {
				return JSONObject.fromObject(listResult).toString();
			}			
			Integer pageNo = MapUtils.getInteger(urlMap, "page_no",1);
			Integer type = MapUtils.getInteger(urlMap, "type",1);//1-map,2-list
			listResult = landmarkService.getLandmarkListByPointAll(typeId, la, lo, distance,pageNo,type);
			if(listResult.getInfo() != null) {
				if(typeId.equals(17)){
					// 说明是社会停车场，进行获得停车场的实时数据
					this.setParkData(listResult);
				} else if(typeId.equals(0)){
					// 说明是社会停车场，进行获得停车场的实时数据
					this.setParkData(this.getParkData(listResult));
				}
			}
			listResult.setResultCode(ResultCode.APP_SUCCESS);
			return JSONObject.fromObject(listResult).toString();
		} catch (Exception e) {
			logger.warn("获取地标列表异常", e);
			listResult.setResultCode(ResultCode.APP_SYSTEM_ERROR);
			return JSONObject.fromObject(listResult).toString();
		}
	}
	
	private ListResult<LandmarkVo> getParkData(ListResult<LandmarkVo> listResult){
		ListResult<LandmarkVo> list = new ListResult<LandmarkVo>();
		list.setInfo(new ArrayList<LandmarkVo>());
		LandmarkVo vo = null;
		Iterator<LandmarkVo> it = listResult.getInfo().iterator();
		while(it.hasNext()){
			vo = it.next();
			if(vo.getTypeId().equals(17)){
				list.getInfo().add(vo);
			}
		}
		return list;
	}
	
	/**
	 * 设置停车场的实时数据
	 * @param listResult
	 * @throws Exception 
	 */
	private void setParkData(ListResult<LandmarkVo> listResult) throws Exception{
		// 对系统中的停车场按照停车场编码进行map设置
		Map<String, LandmarkVo> landmarkMap = new HashMap<String, LandmarkVo>();
		LandmarkVo vo = null;
		Iterator<LandmarkVo> voIt = listResult.getInfo().iterator();
		while(voIt.hasNext()){
			vo = voIt.next();
			if(StringUtils.isEmpty(vo.getCode())){
				continue;
			}
			landmarkMap.put(vo.getCode(), vo);
		}
		// 获得诱导系统中的实时停车场信息
		String result = HttpUtil.get(CarGuideSysUtil.getGuideSysUrl() + "/parkdata/getParkData", new HashMap<String, Object>(), "utf-8");
		JSONObject jsonObject = JSONObject.fromObject(result);
		String code = jsonObject.getString("code");
		if("0000".equals(code)){
			JSONObject dataObject = jsonObject.getJSONObject("data");
			Iterator<String> it = dataObject.keys();
			String key = null;
			PlaceTimesData timesData = null;
			while(it.hasNext()){
				key = it.next();
				timesData = (PlaceTimesData)JSONObject.toBean(dataObject.getJSONObject(key), PlaceTimesData.class);
				if(landmarkMap.containsKey(key)){
					vo = landmarkMap.get(key);
					vo.setTotal(timesData.getTotal());
					vo.setSurplus(timesData.getSurplus());
					vo.setUse(true);
				}
			}
		}
	}
}
