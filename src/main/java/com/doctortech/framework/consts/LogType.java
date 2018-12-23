
package com.doctortech.framework.consts;

/**
 * 
 * 日志分类
 */
public enum LogType {
	
	Login(0);
	
	private Integer type;
	
	LogType(Integer type){
		this.type=type;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
	

}
