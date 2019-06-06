package com.tcc.park.api.controller;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.park.api.common.ResultCode;
import com.tcc.park.api.service.AreaService;
import com.tcc.park.api.util.RSACoder;
import com.tcc.park.api.util.URLUtil;
import com.tcc.park.api.vo.AreaDetailListVo;
import com.tcc.park.api.vo.AreaListVo;
import com.tcc.park.api.vo.ListResult;

@Controller
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	private static final Logger logger = Logger.getLogger(AreaController.class);
	
	@RequestMapping("/api/area/list/{urlParams}")
	public @ResponseBody String getAreaList(@PathVariable String urlParams) {
		logger.info("areaList");
		ListResult<AreaListVo> listResult = new ListResult<AreaListVo>();
		listResult.setResultCode(ResultCode.APP_PARAM_ERROR);
		try {
			List<AreaListVo> list = areaService.getAreaList();
			listResult.setInfo(list);
			listResult.setResultCode(ResultCode.APP_SUCCESS);
			return JSONObject.fromObject(listResult).toString();
		} catch (Exception e) {
			e.printStackTrace();
			listResult.setResultCode(ResultCode.APP_SYSTEM_ERROR);
			return JSONObject.fromObject(listResult).toString();
		}
	}
	
	@RequestMapping("/api/area/detail/{urlParams}")
	public @ResponseBody String getAreaDetail(@PathVariable String urlParams) {
		ListResult<AreaDetailListVo> result = new ListResult<AreaDetailListVo>();
		result.setResultCode(ResultCode.APP_PARAM_ERROR);
		Map<String, String> urlMap;
		try {
			urlMap = URLUtil.analysis(RSACoder.decryptDataByRSA64(urlParams));
			logger.info("areaDetail="+urlMap);
			if (urlMap == null) {
				return JSONObject.fromObject(result).toString();
			}
			String areaCode = urlMap.get("area_id");
			if (StringUtils.isBlank(areaCode)) {
				return JSONObject.fromObject(result).toString();
			}
			Integer pageNo = MapUtils.getInteger(urlMap, "page_no",1);
			result = areaService.getAreaDetailById(areaCode,pageNo);
			result.setResultCode(ResultCode.APP_SUCCESS);
			return JSONObject.fromObject(result).toString();
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(ResultCode.APP_SYSTEM_ERROR);
			return JSONObject.fromObject(result).toString();
		}
	}
}
