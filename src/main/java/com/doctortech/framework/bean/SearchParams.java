package com.doctortech.framework.bean;

import javax.servlet.http.HttpServletRequest;

public class SearchParams {

    HttpServletRequest request ;
    
    public SearchParams(HttpServletRequest request) {
       this.request=request ;
    }
    
    public Object get(String key) {
        
        return request.getParameterMap().get(key); 
        
    }
    
}
