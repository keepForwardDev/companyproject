package com.doctortech.framework.bean;

import java.util.List;

/** 
 * Project Name:fuhuaquan 
 * File Name:AdminMenuBean.java 
 * author: zgh
 * Package Name:com.doctortech.framework.bean 
 * Date:2017年8月21日下午7:46:22 
 * Copyright (c) 2017,  All Rights Reserved. 
 * 菜单
 */
public class AdminMenuBean {
	private long id;//菜单id
	private String name="";//菜单名
	private long parentId;//父id,0为一级
	private String icon="";
	private int morder=0;
	
	
	
	
	public int getMorder() {
		return morder;
	}
	public void setMorder(int morder) {
		this.morder = morder;
	}
	private String path="";//访问路径
	private List<AdminMenuBean> items;//子菜单
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<AdminMenuBean> getItems() {
		return items;
	}
	public void setItems(List<AdminMenuBean> items) {
		this.items = items;
	}
	
	
	
}
