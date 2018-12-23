package com.doctortech.framework.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.DisabledAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * 异常处理
 *
 */
@ControllerAdvice
@Order(-1)
class GlobalExceptionHandler {



    private Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * 用户未登录异常
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unAuth(AuthenticationException e) {
        log.error("用户未登陆：", e);
        return "/login";
    }

    /**
     * 账号被冻结异常
     */
    @ExceptionHandler(DisabledAccountException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String accountLocked(DisabledAccountException e, Model model) {
        model.addAttribute("tips", "账号被冻结");
        return "/login";
    }


    /**
     * 账号密码错误异常
     */
    @ExceptionHandler(CredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String credentials(CredentialsException e, Model model,HttpServletRequest request) {
        model.addAttribute("tips", "账号或者密码错误");
        model.addAttribute("username", request.getParameter("username"));
        return "/login";

    }

    /**
     * 验证码错误异常
     */
    @ExceptionHandler(InvalidKaptchaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String credentials(InvalidKaptchaException e, Model model,HttpServletRequest request) {
        model.addAttribute("tips", "验证码错误");
        model.addAttribute("username", request.getParameter("username"));
        model.addAttribute("password", request.getParameter("password"));
        return "/login";
    }

}

