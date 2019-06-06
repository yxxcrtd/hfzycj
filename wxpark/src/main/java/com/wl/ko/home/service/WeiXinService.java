package com.wl.ko.home.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wl.base.dao.IBaseDAO;
import com.wl.common.utils.HttpClientUtils;
import com.wl.common.utils.JacksonUtil;
import com.wl.common.utils.SystemConfigUtil;
import com.wl.ko.home.model.AccesstokenInfo;
import com.wl.ko.home.model.ParkVo;
import com.wl.ko.home.model.ParkVoDetail;
import com.wl.ko.home.model.WeiXinUserInfo;

import net.sf.json.JSONObject;

@Service
public class WeiXinService implements IWeiXinService {

	@Autowired
	private IBaseDAO ibaseDao ;

	/***
	 * 查询出停车信息
	 * @param car_no
	 * @param pageno
	 * @param device_type
	 * @param dataStatus
	 * @return
	 * @throws Exception
	 */

	@Override
	public ParkVo getUserCurrentPark(String car_no, int pageno, String device_type, String dataStatus) throws Exception {
		 Map<String,String> param = new HashMap<String,String>();
		 param.put("device_type", device_type);
		 param.put("version_code","33");
		 param.put("pageNo", pageno+"");
		 param.put("carNo",car_no);
		 param.put("dataStatus",dataStatus);
		 String userInfoJson = HttpClientUtils.doGet(SystemConfigUtil.getValue("PARKURL"), param);
		 //转换对象
		 ParkVo parkVo = JacksonUtil.fromJson(userInfoJson, ParkVo.class);
		 // ParkVo parkVo = JSON.parseObject(userInfoJson, ParkVo.class);   
		 return parkVo;
	}


	@Override
	public WeiXinUserInfo getUserInfoByCode(String code) {
		AccesstokenInfo acccessinfo = getAcccessTokenByCode(code);
		if (acccessinfo == null) {
			return null;
		}
		WeiXinUserInfo userInfo = getUserinfoByAccesstokenInfo(acccessinfo);
		if(userInfo!=null){
			updateUserInfo(userInfo);
		}
		return userInfo;
	}
	
	/***
	 * 更新用户信息
	 * @param userInfo
	 */
	@Deprecated
	private void updateUserInfo(WeiXinUserInfo userInfo) {
		//weiXinMapper.updateUserInfo(userInfo);
		this.ibaseDao.update("com.wl.weixin.updateUserInfo", userInfo);
	}
	
	/***
	 * 获取微信的个人资料
	 * @param acccessinfo
	 * @return
	 */
	@Deprecated
	private WeiXinUserInfo getUserinfoByAccesstokenInfo(AccesstokenInfo acccessinfo) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("access_token", acccessinfo.getAccess_token());
			params.put("openid", acccessinfo.getOpenid());
			params.put("lang", "zh_CN");
			//String userInfoJson = HttpUtil.get(getUserInfoUrl(), params, "UTF-8");
			String userInfoJson = HttpClientUtils.doGet(getUserInfoUrl(), params);

			WeiXinUserInfo info = new WeiXinUserInfo();
			JSONObject userInfoJO = JSONObject.fromObject(userInfoJson);

			String user_openid = userInfoJO.getString("openid");
			String user_nickname = userInfoJO.getString("nickname");
			String user_sex = userInfoJO.getString("sex");
			String user_province = userInfoJO.getString("province");
			String user_city = userInfoJO.getString("city");
			String user_country = userInfoJO.getString("country");
			String user_headImage = userInfoJO.getString("headImage");
			//add
			info.setOpenId(user_openid);
			info.setNickName(user_nickname);
			info.setSex(user_sex);
			info.setProvince(user_province);
			info.setCity(user_city);
			info.setCountry(user_country);
			info.setHeadImage(user_headImage);
			return info;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据code 获取accesstoken 与 openid
	 * @param code
	 * @return
	 */
	public AccesstokenInfo getAcccessTokenByCode(String code) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("code", code);
			params.put("appid", SystemConfigUtil.getValue("WX.APPID"));
			params.put("secret", SystemConfigUtil.getValue("WX.SCRECRT"));
			params.put("grant_type", "authorization_code");
			String json = HttpClientUtils.doGet(getAccessTokenUrl(), params);
			
			Map jsonObject = JacksonUtil.jsonToMap(json);
			Object errcode =jsonObject.get("errcode");
			AccesstokenInfo info =  null;
			if(errcode == null){
				//JSONObject jsonObject = JSONObject.fromObject(json);
				String access_token = jsonObject.get("access_token").toString();
				String openid = jsonObject.get("openid").toString();
				info = new AccesstokenInfo();
				info.setAccess_token(access_token);
				info.setOpenid(openid);
			}
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param 根据code
	 * 获取访问微信服务器获得accsstoken 与oppenid的url
	 * @return
	 */
	private String getAccessTokenUrl() {
		return "https://api.weixin.qq.com/sns/oauth2/access_token";
	}

	private String getUserInfoUrl() {
		return "https://api.weixin.qq.com/sns/userinfo";
	}
	
	/****
	 * 执行查询订单详情信息
	 * 
	 * @param paramObj
	 * @return
	 * @throws Exception
	 */
	public ParkVoDetail getUserOrderDetail(Map<String, String> paramObj) throws Exception{
		 String userInfoJson = HttpClientUtils.doGet(SystemConfigUtil.getValue("PARKDETAIL"), paramObj);
		 //转换对象
		  ParkVoDetail parkVo = JacksonUtil.fromJson(userInfoJson, ParkVoDetail.class);
		  // ParkVo parkVo = JSON.parseObject(userInfoJson, ParkVo.class);   
		 return parkVo;
		 //return userInfoJson;
	}
}
