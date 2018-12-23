package com.doctortech.framework.util;

import java.util.UUID;

/** 
 * Project Name:ifuhua_b 
 * File Name:UUIDUtils.java 
 * author: zgh
 * Package Name:com.doctor.es.simple.utils 
 * Date:2017年8月7日上午9:58:00 
 * Copyright (c) 2017,  All Rights Reserved. 
 * 
 */
public class UUIDUtils {

	public static String genUUid(){
		String uuid=UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
}
