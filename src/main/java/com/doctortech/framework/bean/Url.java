package com.doctortech.framework.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
/**
 * 地址类
 * @author gong
 *
 * 2016年3月30日
 */
public class Url {

	private String urlString;
	
	private String queryString;
	
	private String requestUrl;
	
	
	private Map<String, String> parmetersMap=new HashMap<String,String>();
	
	public Url() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 用来取参数
	 * @param urlString
	 */
	public Url(String urlString) {
		
		this.urlString=urlString;
				
		String requestUrl=StringUtils.substringBefore(urlString, "?");
		
		setRequestUrl(requestUrl);
		
		String queryString=StringUtils.substringAfter(urlString, "?");
	
		setQueryString(queryString);
	
		
		String[]  keyAndValues=StringUtils.split(queryString, "&");

		for (String keyAndValue : keyAndValues) {
			
			if (keyAndValue.indexOf("=")!=-1) {
				
				String[] keyValue=StringUtils.split(keyAndValue, "=");
				
				parmetersMap.put(keyValue[0],keyValue.length==2?keyValue[1]:null);

			}else {
				parmetersMap.put(keyAndValue,"");
			}

		}
		
		
		
	}

	public String getUrlString() {
		return urlString;
	}

	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public Map<String, String> getParmetersMap() {
		return parmetersMap;
	}
	
	
	public Url put(String key,Object value) {
		
		parmetersMap.put(key, value==null?null:value.toString());
		
		return this;
	}
	
	
	@Override
	public String toString() {

		StringBuffer stringBuffer=new StringBuffer();
		
		int i=0;
		
		for (String key :parmetersMap.keySet()) {
			
			if (i>0) {
				
				stringBuffer.append("&");
			}
			
			try {
				
				
				String temp=StringUtils.defaultIfBlank(parmetersMap.get(key), "");
				
				stringBuffer.append(String.format("%s=%s",key,URLEncoder.encode(temp,"utf-8")));
				
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			i++;
		}
		
		if (i>0) {
		
			return urlString+"?"+stringBuffer.toString();
		}
		
		return urlString;
	}
	
	
	public  static  Url get() {
		
		return new Url();

	}
	
	
	
}
