package com.doctortech.fhq.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SqlHelper {
    
    
    
    public static String inEx(List<Long> ids){
        return inEx(ids.toArray(new Long[]{}));
    }
    
    public static String inEx(Long[] ids){
        StringBuffer sb=new StringBuffer(" in ");
        sb.append(" ( ");
        for (Long id : ids) {
            sb.append(id).append(",");
        }
        return StringUtils.removeEnd(sb.toString(), ",")+" ) ";
    }
    
    public static String notInEx(Long[] ids){
    	StringBuffer sb=new StringBuffer(" not in ");
    	sb.append(" ( ");
    	for (Long id : ids) {
    		sb.append(id).append(",");
    	}
    	return StringUtils.removeEnd(sb.toString(), ",")+" ) ";
    }
}
