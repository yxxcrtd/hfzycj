package com.zycj.tcc.server.vo;

import java.util.Map;

/**
 * 接口请求数据
 * @author 洪捃能
 */
public class Request{
	public static final String CLIENT_IP="client_ip"; 
	// 接口类型
	private String t_code;
	//接口参数
	private Map<String,String> parameter;
	
	public String getT_code() {
		return t_code;
	}
	public void setT_code(String t_code) {
		this.t_code = t_code;
	}
	public Map<String, String> getParameter() {
		return parameter;
	}
	public void setParameter(Map<String, String> parameter) {
		this.parameter = parameter;
	}
	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer("t_code="+t_code);
		if(parameter!=null){
			for(String key:parameter.keySet()){
				sb.append("&"+key+"="+parameter.get(key));
			}
		}
		return sb.toString();
	}
}
