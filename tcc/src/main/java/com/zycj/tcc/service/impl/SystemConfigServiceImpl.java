package com.zycj.tcc.service.impl;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zycj.tcc.cache.FeeRegularCache;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.common.SystemConfig;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.service.SystemConfigService;

@Service
public class SystemConfigServiceImpl implements SystemConfigService {
	private final static Logger log = Logger.getLogger(SystemConfigServiceImpl.class); 
	
	@Override
	public Response updateSystemConfig(String checkNo, String fieldName,String fieldVal) {
		if(!checkNo.equals(SystemConfig.Update_SystemConfig_CheckNo)){
			return ResponseUtil.createResponse(ResultCode.SYSTEM_ERROR);
		}
		Field[] fields=SystemConfig.class.getDeclaredFields();
		Field fd=null;
		for(Field f:fields){
			if(f.getName().equals(fieldName)){
				fd=f;
				break;
			}
		}
		if(fd==null){
			return ResponseUtil.createResponse(ResultCode.SYSTEM_ERROR);
		}
		try{
			fd.setAccessible(true);
			if(fd.getType()==int.class){
				fd.set(SystemConfig.class,Integer.parseInt(fieldVal));
			}else{
				fd.set(SystemConfig.class,fieldVal);
			}
			return ResponseUtil.createResponse(ResultCode.SUCCESS);
		}catch(Exception e){
			log.warn("更新系统配置异常", e);
		}
		return ResponseUtil.createResponse(ResultCode.SUCCESS);
	}

	@Override
	public Response updateFeeRegularCache() {
		try {
			FeeRegularCache.reset();
			log.warn("刷新计费规则缓存");
		} catch (Exception e) {
			log.warn("刷新计费规则缓存异常",e);
			return ResponseUtil.createResponse(ResultCode.SYSTEM_ERROR);
		}
		return ResponseUtil.createResponse(ResultCode.SUCCESS);
	}
}
