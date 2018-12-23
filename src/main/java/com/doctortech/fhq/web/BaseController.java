package com.doctortech.fhq.web;

import com.alibaba.druid.util.DruidWebUtils;
import com.doctortech.framework.bean.CommonRespon;
import com.doctortech.framework.bean.PageData;
import com.doctortech.framework.common.shiro.ShiroKit;
import com.doctortech.framework.common.shiro.ShiroUser;
import com.doctortech.framework.consts.Const;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class BaseController {
    protected final String REDIRECT = "redirect:";
    protected final String FORWARD = "forward:";

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public HttpServletResponse getResponse(){
        HttpServletResponse  response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    public HttpSession getSession() {
        return Objects.requireNonNull(getRequest()).getSession();
    }

    public PageData getPageData(){
        return new PageData(this.getRequest());
    }

    public ModelAndView getModelAndView(){
        return new ModelAndView();
    }

    public Long getCurrentUserId(){
        if(getCurrentUser()!=null){
            return getCurrentUser().getId();
        }
        return null;
    }

    /**
     * 当前用户
     * @return
     */
    public ShiroUser getCurrentUser(){
        return ShiroKit.getUser();
    }

    /**
     * 获取请求全路径
     * @param request
     * @return
     */
    public String getFullURL(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        if (request.getQueryString() != null) {
            url.append("?");
            url.append(request.getQueryString());
        }
        return url.toString();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    public CommonRespon noLogin(CommonRespon respon) {
        /*CommonRespon respon = new CommonRespon();*/
        respon.setCode(Const.CODE_NO_LOGIN);
        respon.setMsg(Const.CODE_NO_LOGIN_STR);
        return respon;
    }

    public String getIp() {
        return DruidWebUtils.getRemoteAddr(getRequest());
    }
}
