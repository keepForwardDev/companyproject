package com.doctortech.framework.common.beetl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.doctortech.fhq.utils.SpringUtils;
import com.doctortech.framework.config.properties.CustomProperties;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.ext.web.WebRenderExt;

/** 
 * Project Name:es 
 * File Name:BeetlGlobaInfo.java 
 * author: zgh
 * Package Name:com.doctor.es.support.util 
 * Date:2016年6月13日上午10:27:39 
 * Copyright (c) 2016,  All Rights Reserved. 
 * 
 */
public class BeetlGlobaInfo implements WebRenderExt {

	@Override
	public void modify(Template template, GroupTemplate arg1, HttpServletRequest arg2, HttpServletResponse arg3) {
		// TODO Auto-generated method stub
		template.binding("openkaptcha", SpringUtils.getBean(CustomProperties.class).getOpenkaptcha());
		
	}

}
