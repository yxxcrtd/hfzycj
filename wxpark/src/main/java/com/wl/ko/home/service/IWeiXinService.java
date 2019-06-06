package com.wl.ko.home.service;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wl.ko.home.model.AccesstokenInfo;
import com.wl.ko.home.model.ParkVo;
import com.wl.ko.home.model.ParkVoDetail;
import com.wl.ko.home.model.WeiXinUserInfo;

/***
 * @author Administrator
 *
 */
public interface IWeiXinService {
	/***
	 * 查询出停车信息
	 * @param car_no
	 * @param pageno
	 * @param device_type
	 * @param dataStatus
	 * @return
	 * @throws Exception
	 */
	 public ParkVo getUserCurrentPark(String car_no, int pageno, String device_type, String dataStatus) throws Exception;
	 
	/**
	 * 根据code 获取accesstoken 与 openid
	 * 
	 * @param code
	 * @return
	 */
	public AccesstokenInfo getAcccessTokenByCode(String code);

	
	/**
	 * 根据微信传过来的code去微信服务器获取用户的信息
	 * 
	 * @param code
	 * @return
	 */
	public WeiXinUserInfo getUserInfoByCode(String code);
	
	
	 /****
	  * 执行查询订单详情信息
	  * @param paramObj
	  * @return
	  * @throws Exception
	  */
	 public ParkVoDetail getUserOrderDetail(Map<String, String> paramObj) throws Exception;
}
