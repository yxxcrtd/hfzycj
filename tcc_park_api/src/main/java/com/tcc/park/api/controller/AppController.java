package com.tcc.park.api.controller;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.park.api.common.ResultCode;
import com.tcc.park.api.dao.AreaDao;
import com.tcc.park.api.domain.RoadSection;
import com.tcc.park.api.mybatis.RoadSectionMapper;
import com.tcc.park.api.service.AppService;
import com.tcc.park.api.user.domain.User;
import com.tcc.park.api.user.mybatis.UserMapper;
import com.tcc.park.api.util.RSACoder;
import com.tcc.park.api.util.URLUtil;
import com.tcc.park.api.vo.AppUpdateVo;
import com.tcc.park.api.vo.ObjectResult;

@Controller
public class AppController {
	
	@Autowired
	private AppService appService;

	@RequestMapping("/api/update/check/{urlParams}")
	public @ResponseBody String	checkUpdate(@PathVariable String urlParams) {
		ObjectResult<AppUpdateVo> objResult = new ObjectResult<AppUpdateVo>();
		objResult.setResultCode(ResultCode.APP_PARAM_ERROR);
		Map<String, String> urlMap;
		try {
			urlMap = URLUtil.analysis(RSACoder.decryptDataByRSA64(urlParams));
			if (urlMap == null) {
				return JSONObject.fromObject(objResult).toString();
			}
			Integer versionCode = MapUtils.getInteger(urlMap, "versionCode",null);
			Integer clientType = MapUtils.getInteger(urlMap, "clientType",null);
			if (versionCode == null || clientType == null) {
				return JSONObject.fromObject(objResult).toString();
			}
			
			AppUpdateVo updateVo = appService.getAppUpdateInfo(versionCode, clientType);
			objResult.setResultCode(ResultCode.APP_SUCCESS);
			objResult.setObj(updateVo);
			return JSONObject.fromObject(objResult).toString();
		} catch (Exception e) {
			e.printStackTrace();
			objResult.setResultCode(ResultCode.APP_SYSTEM_ERROR);
			return JSONObject.fromObject(objResult).toString();
		}
		
	}
	
	
//	@Autowired
//	private UserMapper userMapper;
//	@Autowired
//	private RoadSectionMapper roadSectionMapper;
//	@Autowired
//	private AreaDao areaDao;
//	
//	@RequestMapping("/api/test")
//	public @ResponseBody String	test() {
//		try {
//			User user = userMapper.selectByPrimaryKey(1);
//			System.err.println("user="+user.getCarNo());
//			RoadSection roadSection = roadSectionMapper.selectByPrimaryKey(1);
//			System.err.println("section="+roadSection.getSectionName());
//			int count = areaDao.selectByAreaListCount("1");
//			System.err.println("count="+count);
//			return "ok";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "a~o";
//		}
//	}
}
