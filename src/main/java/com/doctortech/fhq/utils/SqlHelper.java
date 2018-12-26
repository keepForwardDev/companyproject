package com.doctortech.fhq.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SqlHelper {
    
    public final static String DEFAULTESCAPE="/";

    public final static String PERCENT="%";

    public final static String UNDERLINE="_";

    public final static String PREFIX="^";

    public final static String SUFFIX=" escape '/' ";

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

    /**
     * 后面跟 escape '/',使用预编译
     * @param str
     * @see #getSqlLike
     * @return
     */
    public static String getSqlLikeParams(String str){
        if (StringUtils.isBlank(str)) {
            return StringUtils.EMPTY;
        }
        String escaped=str.replace("%", DEFAULTESCAPE+PERCENT).replace("_", DEFAULTESCAPE+UNDERLINE).replace("^", DEFAULTESCAPE+PREFIX);
        return PERCENT+escaped+PERCENT ;
    }

    /**
     *
     * @param key 绑定占位参数名
     * @return
     */
    public static String getSqlLike(String key) {
        String sql= "like :"+key;
        return sql+SUFFIX;
    }

    public static String getSqlLike() {
        String sql= "like ? ";
        return sql+SUFFIX;
    }
}
