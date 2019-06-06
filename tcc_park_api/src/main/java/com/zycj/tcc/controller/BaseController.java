/**
 * 
 */
package com.zycj.tcc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;


import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.Result;
import com.zycj.tcc.domain.vo.SysAdminVo;


/**
 * Title: BaseController.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年3月25日
 */
public class BaseController {
	
	public Map<String,Object> getModel(){
		return new HashMap<String, Object>();
	}
	
	public Result getResult(){
		return new Result();
	}
	/**
	 * 获取当前登录的管理员
	 * @param sesion
	 * @return
	 */
	public SysAdminVo getCurrentAdmin(HttpSession session){
		return (SysAdminVo)session.getAttribute("admin");
	}
	/**
	 * 异常处理
	 */
	public void doException(Map<String,Object> model,Result result,Exception e){
		result.setCode(Constants.FAIL_RESULT);
		result.setExceptionClassName(e.getClass().toString());
		result.setExceptionMsg(e.getMessage());
		result.setMsg(e.getMessage());
		model.put("result", result);
	}
	/**
	 * 操作成功
	 */
	public void doSuccess(Map<String,Object> model,Result result){
		result.setCode(Constants.SUCCESS_RESULT);
		model.put("result", result);
	}
}
