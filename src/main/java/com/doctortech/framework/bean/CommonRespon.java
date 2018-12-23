package com.doctortech.framework.bean;

import com.doctortech.framework.consts.Const;

/** 
 * Project Name:ifuhua 
 * File Name:CommonRespon.java 
 * author: zgh
 * Package Name:com.doctor.es.support.util.bean 
 * Date:2017年1月5日下午4:19:47 
 * Copyright (c) 2017,  All Rights Reserved. 
 * 
 */
public class CommonRespon {
	private int code; //返回标识
	private String msg=""; //返回提示信息
	private String exception=""; //异常信息
	private Object data=new String(); //数据列表
	private String token=""; //token
	private Object reserveData=new String();//预留数据
	private String attach="";
	public CommonRespon(){
		this.code=Const.CODE_ERROR;
		this.msg=Const.CODE_ERROR_STR;
	}
	
	public CommonRespon(int code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public CommonRespon(int code,String msg,Object data,String token,Object reserveData){
		this.code=code;
		this.msg=msg;
		this.data=data;
		this.token=token;
		this.reserveData=reserveData;
	}
	
	public CommonRespon(int code,String msg,Object data,String token){
		this.code=code;
		this.msg=msg;
		this.data=data;
		this.token=token;
	}
	
	public CommonRespon(int code,String msg,Object data){
		this.code=code;
		this.msg=msg;
		this.data=data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Object getReserveData() {
		return reserveData;
	}

	public void setReserveData(Object reserveData) {
		this.reserveData = reserveData;
	}

	public String getException()
	{
		return exception;
	}

	public void setException(String exception)
	{
		this.exception = exception;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	@Override
	public String toString() {
		return "CommonRespon [code=" + code + ", msg=" + msg + ", exception=" + exception + ", data=" + data
				+ ", token=" + token + ", reserveData=" + reserveData + ", attach=" + attach + "]";
	}

	
	
	
}
