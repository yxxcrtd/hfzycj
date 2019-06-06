package com.zycj.business.http.httpclient;

public interface HttpCallBack<T> {
	public T todo(String result)throws Exception;
}
