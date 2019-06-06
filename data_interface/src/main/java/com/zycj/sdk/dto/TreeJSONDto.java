package com.zycj.sdk.dto;

public class TreeJSONDto {
	protected String id;

	protected String text;

	protected String state = "closed";

	protected boolean checked;
	/**
	 * 默认构造函数
	 */
	public TreeJSONDto() {
	}
	/**
	 * 构造函数初始化数据
	 */
	public TreeJSONDto(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	/**
	 * 构造函数初始化数据
	 */
	public TreeJSONDto(String id, String text, String state) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
	}
	/**
	 * 构造函数初始化数据
	 */
	public TreeJSONDto(String id, String text, String state, boolean checked) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
