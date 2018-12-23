package com.doctortech.fhq.utils;
/** 
 * Project Name:gaoqi 
 * File Name:IPEntry.java 
 * author: zgh
 * Package Name:com.highjy.gaoqi.util 
 * Date:2017年6月20日下午3:38:20 
 * Copyright (c) 2017,  All Rights Reserved. 
 * 
 */
public class IPEntry {
	public String beginIp;
	public String endIp;
	public String country;
	public String area;

	/**
	 * 构造函数
	 */

	public IPEntry() {
		beginIp = endIp = country = area = "";
	}

	public String toString() {
		return this.area + "  " + this.country + "IP范围:" + this.beginIp + "-"
				+ this.endIp;
	}
}
