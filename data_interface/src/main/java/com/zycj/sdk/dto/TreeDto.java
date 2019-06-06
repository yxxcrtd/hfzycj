package com.zycj.sdk.dto;

import java.util.ArrayList;
import java.util.List;
/**
 * <p> Title: 空港边检勤务综合管理系统-TreeDto.java </p>
 * <p> Description: Information manage System </p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company: www.pingtech.com.cn </p>
 * @author fengya
 * @version 1.0
 * @date  2014-4-26 下午04:01:40
 */
public class TreeDto {
	//树节点对应的ID
	private String id;
	//树节点对应的名称
	private String text;
	//节点下的孩子节点的集合
	private List<TreeDto> children = new ArrayList<TreeDto>();
	//当前节点状态
	private String state;
	//节点前对应的图标
	private String iconCls;
	//是否是叶子节点
	private boolean isLeaf;
	//是否选中
	private boolean checked;
	// 扩展信息
	private String exchangeInfo;
	//节点状态：open（展开）closed（合拢）
	public enum stateValue {
		OPEN, CLOSED
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

	public List<TreeDto> getChildren() {
		return children;
	}

	public void setChildren(List<TreeDto> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getExchangeInfo() {
		return exchangeInfo;
	}

	public void setExchangeInfo(String exchangeInfo) {
		this.exchangeInfo = exchangeInfo;
	}

}
