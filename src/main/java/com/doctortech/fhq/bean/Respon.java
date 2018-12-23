package com.doctortech.fhq.bean;

import com.doctortech.framework.consts.Const;

/**
 * Created by Administrator on 2016/11/30.
 */
public class Respon {
    private int code;
    private String msg="";
    private Object data="";
    private Object resData="";
    private int type=0;
    public Respon(){
    	this.code=Const.CODE_ERROR;
        this.msg=Const.CODE_ERROR_STR;
    };
    public Respon(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public Object getResData() {
        return resData;
    }

    public void setResData(Object resData) {
        this.resData = resData;
    }
}
