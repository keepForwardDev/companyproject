package com.doctortech.framework.bean;

import java.util.List;


/** 
* @author 作者:zgh 
* @version 创建时间：2018年5月3日 上午11:04:16 
* 类说明 
*/
public class AdminVueMenuBean {
	 private long id;
	 private String name="";
	 private String path="";
	 private String remark="";
	 private int morder;
	 private long parentId;
	private List<AdminVueMenuBean> children=null;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getMorder() {
		return morder;
	}
	public void setMorder(int morder) {
		this.morder = morder;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public List<AdminVueMenuBean> getChildren() {
		return children;
	}
	public void setChildren(List<AdminVueMenuBean> children) {
		this.children = children;
	}
	
	
}
