package com.doctortech.framework.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.doctortech.framework.bean.Url;


public class URLUtil {
    
    public static Map<String,String> getParametersMap(String url) {

        return new Url(url).getParmetersMap();
    }
    
    public static Url getUrl(String url) {
        
        return new Url(url);
        
    }

    public static void main(String[] args) {
        
        //System.out.println(getParametersMap("http://xueshu.baidu.com/usercenter/data/author?cmd=academic_paper&entity_id=龚增辉&bsToken=bedca8feded16ff3499eea2ba2385481&sc_sort=sc_time&paper_type=1"));
        
        System.out.println(isUrl("http://environment.fudan.edu.cn/userfiles/images/Christian  George.jpg"));
    }
    
    /**
     * 检查URL的格式合法性
     * @param pInput
     * @return
     */
    public static boolean isUrl(String pInput) {
    
        if(pInput == null){
            return false;
        }
        
        String regEx = "^(http|https|ftp)//://([a-zA-Z0-9//.//-]+(//:[a-zA-"
            + "Z0-9//.&%//$//-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
            + "2}|[1-9]{1}[0-9]{1}|[1-9])//.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
            + "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)//.(25[0-5]|2[0-4][0-9]|"
            + "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)//.(25[0-5]|2[0-"
            + "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
            + "-9//-]+//.)*[a-zA-Z0-9//-]+//.[a-zA-Z]{2,4})(//:[0-9]+)?(/"
            + "[^/][a-zA-Z0-9//.//,//?//'///////+&%//$//=~_//-@]*)*$";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(pInput);
        return matcher.matches();
    }
}
