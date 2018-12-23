package com.doctortech.fhq.utils;

public class SmsToJsonBeanUtil{
	  private String num="";
	  private String success="";
	  private String faile="";
	  private String err="";
	  private String errid="";

	  public SmsToJsonBeanUtil()
	  {
	  }

	  public SmsToJsonBeanUtil(String inString)
	  {
	    String[] strs = inString.split("&");
	    for (int i = 0; i < strs.length; i++) {
	      String[] ones = strs[i].split("=");
	      if (ones.length == 2)
	        if ("num".equals(ones[0]))
	          setNum(ones[1]);
	        else if ("success".equals(ones[0]))
	          setSuccess(ones[1]);
	        else if ("faile".equals(ones[0]))
	          setFaile(ones[1]);
	        else if ("err".equals(ones[0]))
	          setErr(ones[1]);
	        else if ("errid".equals(ones[0]))
	          setErrid(ones[1]);
	    }
	  }

	  public String getNum()
	  {
	    return this.num;
	  }

	  public void setNum(String num) {
	    this.num = num;
	  }

	  public String getSuccess() {
	    return this.success;
	  }

	  public void setSuccess(String success) {
	    this.success = success;
	  }

	  public String getFaile() {
	    return this.faile;
	  }

	  public void setFaile(String faile) {
	    this.faile = faile;
	  }

	  public String getErr() {
	    return this.err;
	  }

	  public void setErr(String err) {
	    this.err = err;
	  }

	  public String getErrid() {
	    return this.errid;
	  }

	  public void setErrid(String errid) {
	    this.errid = errid;
	  }

	@Override
	public String toString() {
		return "SmsToJsonBeanUtil [num=" + num + ", success=" + success + ", faile=" + faile + ", err=" + err
				+ ", errid=" + errid + "]";
	}
	  
	}
