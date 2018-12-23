package com.doctortech.fhq.utils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.CaseFormat;

/** 
* @author 作者:zgh 
* @version 创建时间：2018年4月25日 上午10:20:40 
* 类说明 
*/
public  class BeanUtil {
	public static Map<String,Object> wrap(List<String> fields,Object obj,Map<String,BeanUtilFace> transMap){
		return wrap(fields.toArray(new String[fields.size()]),obj, transMap);
	}
	public static Map<String,Object> wrap(String[] fields,Object obj,Map<String,BeanUtilFace> transMap){
		Map<String,Object> map=new LinkedHashMap<>();
		if(fields==null||obj==null)return map;
		Class clazz=obj.getClass();
		Method[] methods=clazz.getMethods();
		for(String str:fields){
			CaseFormat fromFormat = CaseFormat.LOWER_CAMEL;
			 CaseFormat toFormat = CaseFormat.UPPER_CAMEL;
			String tmpName="get"+fromFormat.to(toFormat, str);
			for(Method m:methods){
				if(m.getName().equals(tmpName)){
					try {
						Object res=m.invoke(obj, null);
						map.put(str, res);
						Class rClazz=m.getReturnType();
						if(rClazz.equals(String.class)){
							if(res==null)
								map.put(str, "");
						}
						else if(rClazz.equals(Integer.class)){
							if(res==null)
								map.put(str, 0);
						}
						else if(rClazz.equals(Byte.class)){
							if(res==null)
								map.put(str, "");
						}
						else if(rClazz.equals(Long.class)){
							if(res==null)
								map.put(str, 0);
						}
						else if(rClazz.equals(Double.class)){
							if(res==null)
								map.put(str, 0);
						}
						else if(rClazz.equals(Float.class)){
							if(res==null)
								map.put(str, 0);
						}
						else if(rClazz.equals(Character.class)){
							if(res==null)
								map.put(str, "");
						}
						else if(rClazz.equals(Short.class)){  
							if(res==null)
								map.put(str, 0);
						}
						else if(rClazz.equals(BigDecimal.class)){
							if(res==null)
								map.put(str, 0);
						}
						else if(rClazz.equals(BigInteger.class)){
							if(res==null)
								map.put(str, 0);
						}
						else if(rClazz.equals(Boolean.class)){ 
							if(res==null)
								map.put(str, false);
						}
						else if(rClazz.equals(Date.class)){
							if(res==null)
								map.put(str, "");
						}
						if(transMap!=null&&transMap.containsKey(str)){
							map.put(str, transMap.get(str).transform(str,obj, res,map));
						}
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		return map;
	}
	
	public  interface BeanUtilFace{
		Object transform(String field,Object bean,Object res,Map<String,Object> map);
	}
}
