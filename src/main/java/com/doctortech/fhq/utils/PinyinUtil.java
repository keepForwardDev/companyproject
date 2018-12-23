package com.doctortech.fhq.utils;

import org.apache.commons.lang3.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinyinUtil {
    
    /**
     * 首字母
     * @param str
     * @return
     */
    public static String getFirstLetter(String str){
        if(StringUtils.isBlank(str)) return null ;
        StringBuffer sb=new StringBuffer();
        for(char c : str.toCharArray()){
            String[] temp=PinyinHelper.toHanyuPinyinStringArray(c);
            System.out.println("拼音："+temp);
            if(temp!=null && temp.length>0){
                sb.append(temp[0].charAt(0));                
            }
        }
        
        return sb.toString();
    }

}
