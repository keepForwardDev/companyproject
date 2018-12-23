package com.doctortech.fhq.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 正则表单工具类
 * @author 
 *
 */
public class RegularUtil {

	
	//检测邮箱是否正确
	public static  boolean isEmail(String email) {
		if(StringUtils.isEmpty(email)){
			return false;
		}
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	 }
	
	//检测手机是否正确
	public static  boolean isMobilePhone(String phone) {
		if(StringUtils.isEmpty(phone)){
			return false;
		}
		String str = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0-9]|17[0-9])\\d{8}$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(phone);
		return m.matches();
	 }
	

}
