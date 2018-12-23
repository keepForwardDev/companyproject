package com.doctortech.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/** 
 * Project Name:gaoqi 
 * File Name:RequestMappingHandlerConfig.java 
 * author: zgh
 * Package Name:com.highjy.framework.config 
 * Date:2017年3月29日下午8:52:05 
 * Copyright (c) 2017,  All Rights Reserved. 
 * 
 */
@Configuration
public class RequestMappingHandlerConfig {
	@Bean  
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {  
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();  
        return mapping;  
    }  
	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter(){
		RequestMappingHandlerAdapter adapter=new RequestMappingHandlerAdapter();
		
		return adapter;
	}
}
