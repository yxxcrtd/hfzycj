package com.tcc.park.api.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import com.tcc.park.api.user.mybatis.WeiXinMapper;
import com.tcc.park.api.util.HttpUtil;
import com.tcc.park.api.vo.AccesstokenInfo;
import com.tcc.park.api.vo.WeiXinUserInfo;

import net.sf.json.JSONObject;

@Service
public class WeiXinService {
	private static String appid = null;
	private static String secret = null;

	static {
		try {
			Properties loadAllProperties = PropertiesLoaderUtils.loadAllProperties("weixin.properties");
			appid = loadAllProperties.getProperty("appid");
			secret = loadAllProperties.getProperty("secret");
		} catch (IOException e) {
		}
	}

	@Autowired
	private WeiXinMapper weiXinMapper;
	/**
	 * 根据微信传过来的code去微信服务器获取用户的信息
	 * @param code
	 * @return
	 */
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

	private void updateUserInfo(WeiXinUserInfo userInfo) {
		weiXinMapper.updateUserInfo(userInfo);
	}

	private WeiXinUserInfo getUserinfoByAccesstokenInfo(AccesstokenInfo acccessinfo) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("access_token", acccessinfo.getAccess_token());
			params.put("openid", acccessinfo.getOpenid());
			params.put("lang", "zh_CN");
			String userInfoJson = HttpUtil.get(getUserInfoUrl(), params, "UTF-8");

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
	 * 
	 * @param code
	 * @return
	 */
	private AccesstokenInfo getAcccessTokenByCode(String code) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("code", code);
			params.put("appid", appid);
			params.put("secret", secret);
			params.put("grant_type", "authorization_code");
			String json = HttpUtil.get(getAccessTokenUrl(), params, "UTF-8");

			JSONObject jsonObject = JSONObject.fromObject(json);
			String access_token = jsonObject.getString("access_token");
			String openid = jsonObject.getString("openid");
			AccesstokenInfo info = new AccesstokenInfo();

			info.setAccess_token(access_token);
			info.setOpenid(openid);
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param 根据code
	 *            获取访问微信服务器获得accsstoken 与oppenid的url
	 * @return
	 */
	private String getAccessTokenUrl() {
		return "https://api.weixin.qq.com/sns/oauth2/access_token";
	}

	private String getUserInfoUrl() {
		return "https://api.weixin.qq.com/sns/userinfo";
	}
}
