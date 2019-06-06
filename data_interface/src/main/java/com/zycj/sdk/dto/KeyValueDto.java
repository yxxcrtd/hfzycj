package com.zycj.sdk.dto;

public class KeyValueDto {
	private Integer id;
	private String name;

	public KeyValueDto(){
	}
	
	public KeyValueDto(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
