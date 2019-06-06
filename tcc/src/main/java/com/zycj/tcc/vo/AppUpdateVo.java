package com.zycj.tcc.vo;

public class AppUpdateVo {
	private String isNew;// 是否有最新版本 1表示有新版本 0表示无新版本
	private String url;// 下载地址
	private String introduce;// 新版本说明
	private String isForce;// 是否强制更新 1表示强制更新 0 表示不强制更新
	private String size;//apk 大小 

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getIsForce() {
		return isForce;
	}

	public void setIsForce(String isForce) {
		this.isForce = isForce;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
