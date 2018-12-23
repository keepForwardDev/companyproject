package com.doctortech.fhq.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/** 
 * Project Name:sea 
 * File Name:SpringUtils.java 
 * author: zgh
 * Package Name:com.highjy.sea.framework.common 
 * Date:2017年1月10日上午10:16:13 
 * Copyright (c) 2017,  All Rights Reserved. 
 * 
 */
@Component
public class SpringUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext=null;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
			if(SpringUtils.applicationContext==null){
				SpringUtils.applicationContext=arg0;
			}
	}
	// 获取applicationContext
	    public static ApplicationContext getApplicationContext() {
	        return applicationContext;
	     }
	 
	     // 通过name获取 Bean.
	     public static Object getBean(String name) {
	         return getApplicationContext().getBean(name);
	     }
	 
	     // 通过class获取Bean.
	     public static <T> T getBean(Class<T> clazz) {
	         return getApplicationContext().getBean(clazz);
	     }
	 
	     // 通过name,以及Clazz返回指定的Bean
	     public static <T> T getBean(String name, Class<T> clazz) {
	         return getApplicationContext().getBean(name, clazz);
	     }

}
