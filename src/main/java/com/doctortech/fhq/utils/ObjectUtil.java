package com.doctortech.fhq.utils;

import java.math.BigInteger;
import java.util.Date;

public final class ObjectUtil {

    public static Long toLon(Object obj){
        if(obj instanceof Long) {
            return (Long) obj ;   
        }else if(obj instanceof BigInteger){
            return ((BigInteger)obj).longValue();
        }
        return 0l ;
    }
    
    public static String toStr(Object obj){
        if(obj instanceof String) {
            return (String) obj ;   
        }
        return "" ;
    }
    
    public static Integer toInt(Object obj){
        if(obj instanceof Integer) {
            return (Integer) obj ;   
        }
        return null ;
    }
    
    public static Date toDate(Object obj){
        if(obj instanceof Date) {
            return (Date) obj ;   
        }
        return null ;
    }
    
}
