package com.doctortech.fhq.utils;

import java.util.Set;

/** 
 * Project Name:fuhuaquangx 
 * File Name:RoleChecker.java 
 * author: zgh
 * Package Name:com.doctortech.fhq.utils 
 * Date:2017年10月31日上午9:58:25 
 * Copyright (c) 2017,  All Rights Reserved. 
 * 
 */
public class RoleChecker {
	public static boolean containRoles(Set<String> roles,String role){
		if(roles.contains(role))return true;
		return false;
	}
}
