package com.tcc.park.api.controller;

import java.math.BigDecimal;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.park.api.common.ResultCode;
import com.tcc.park.api.service.ParkService;
import com.tcc.park.api.util.RSACoder;
import com.tcc.park.api.util.URLUtil;
import com.tcc.park.api.vo.ListResult;
import com.tcc.park.api.vo.ObjectResult;
import com.tcc.park.api.vo.ParkDetailVo;
import com.tcc.park.api.vo.ParkListVo;

@Controller
public class ParkController {
	
	@Autowired
	private ParkService parkService;
	
	private static final Logger logger = Logger.getLogger(ParkController.class);
	
	@RequestMapping("/api/park/list/{urlParams}")
	public @ResponseBody String getParkList(@PathVariable String urlParams) {
		ListResult<ParkListVo> listResult = new ListResult<ParkListVo>();
		listResult.setResultCode(ResultCode.APP_PARAM_ERROR);
		Map<String, String> urlMap;
		try {
			urlMap = URLUtil.analysis(RSACoder.decryptDataByRSA64(urlParams));
			logger.info("parkList="+urlMap);
			if (urlMap == null) {
				return JSONObject.fromObject(listResult).toString();
			}
			Integer distance = MapUtils.getInteger(urlMap, "distance",1000);
			Integer feeType = MapUtils.getInteger(urlMap, "is_free",3);//1收费 2免费 3 全部
			BigDecimal la = new BigDecimal(MapUtils.getDoubleValue(urlMap, "latitude",0));
			BigDecimal lo = new BigDecimal(MapUtils.getDoubleValue(urlMap, "longitude",0));
			if (la ==null || lo ==null) {
				return JSONObject.fromObject(listResult).toString();
			}			
			Integer pageNo = MapUtils.getInteger(urlMap, "page_no",1);
			Integer type = MapUtils.getInteger(urlMap, "type",1);//1-map,2-list
			listResult = parkService.getParkListByPointAll(feeType, la, lo, distance,pageNo,type);
			listResult.setResultCode(ResultCode.APP_SUCCESS);
			return JSONObject.fromObject(listResult).toString();
		} catch (Exception e) {
			e.printStackTrace();
			listResult.setResultCode(ResultCode.APP_SYSTEM_ERROR);
			return JSONObject.fromObject(listResult).toString();
		}
	}
	
	@RequestMapping("/api/park/detail/{urlParams}")
	public @ResponseBody String getParkDetail(@PathVariable String urlParams) {
		ObjectResult<ParkDetailVo> result = new ObjectResult<ParkDetailVo>();
		result.setResultCode(ResultCode.APP_PARAM_ERROR);
		Map<String, String> urlMap;
		try {
			urlMap = URLUtil.analysis(RSACoder.decryptDataByRSA64(urlParams));
			logger.info("parkDetail="+urlMap);
			if (urlMap == null) {
				return JSONObject.fromObject(result).toString();
			}
			Integer parkId = MapUtils.getInteger(urlMap, "park_id",null);
//			BigDecimal la = new BigDecimal(MapUtils.getDoubleValue(urlMap, "la",0));
//			BigDecimal lo = new BigDecimal(MapUtils.getDoubleValue(urlMap, "lo",0));
			if (parkId==null ) {
				return JSONObject.fromObject(result).toString();
			}			
			ParkDetailVo parkDetail = parkService.getParkDetailById(parkId, null, null);
			result.setObj(parkDetail);
			result.setResultCode(ResultCode.APP_SUCCESS);
			return JSONObject.fromObject(result).toString();
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(ResultCode.APP_SYSTEM_ERROR);
			return JSONObject.fromObject(result).toString();
		}
	}
}
