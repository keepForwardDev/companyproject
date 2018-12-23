package com.doctortech.framework.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class StringUtil extends StringUtils{

    public static final String ELLIPSIS = "...";
    
    public static String left(String str,int len) {
        if (str == null) {
            return EMPTY;
        }
        if (len < 0) {
            return EMPTY;
        }
        return StringUtils.left(str, len)+ELLIPSIS;
    }

    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
