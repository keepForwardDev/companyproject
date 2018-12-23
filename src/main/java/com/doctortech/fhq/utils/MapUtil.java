package com.doctortech.fhq.utils;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class MapUtil {
	
	
	public static String toStr(Map<String,Object> map,String key){
		if(map!=null&&map.containsKey(key)&&map.get(key)!=null) {
			return StringUtils.trim(map.get(key).toString());
		}
		return "" ;
	}
	public static Integer toInt(Map<String,Object> map,String key){
		if(map!=null&&map.containsKey(key)&&map.get(key)!=null) {
			return Integer.valueOf(map.get(key).toString());
		}
		return null ;
	}
	public static Long toLon(Map<String,Object> map,String key){
		if(map!=null&&map.containsKey(key)&&map.get(key)!=null) {
			return Long.valueOf(map.get(key).toString());
		}
		return null ;
	}
	
	public static Date toDate(Map<String,Object> map,String key){
		if(map!=null&&map.containsKey(key)&&map.get(key)!=null) {
			Object obj=map.get(key);
			if(obj instanceof Date) {
				return (Date)obj ;
			}
		}
		return null;
	}

}
