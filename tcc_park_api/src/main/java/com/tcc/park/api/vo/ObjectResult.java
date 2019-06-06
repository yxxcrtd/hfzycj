package com.tcc.park.api.vo;

public class ObjectResult<T> extends AppResult{

	private T obj;

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}
	
}
