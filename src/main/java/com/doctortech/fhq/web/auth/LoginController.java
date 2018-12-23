package com.doctortech.fhq.web.auth;

import com.doctortech.fhq.service.common.MenuService;
import com.doctortech.fhq.web.BaseController;
import com.doctortech.framework.bean.CommonRespon;
import com.doctortech.framework.common.exception.InvalidKaptchaException;
import com.doctortech.framework.common.shiro.ShiroKit;
import com.doctortech.framework.common.shiro.ShiroUser;
import com.doctortech.framework.config.properties.CustomProperties;
import com.doctortech.framework.consts.Const;
import com.google.code.kaptcha.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController extends BaseController {
    @Autowired
    private CustomProperties customProperties;

    @Autowired
    private MenuService menuService;
    /**
     * 跳转到主页
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "/index";
    }
    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
            return "/login";
        }
    }

    /**
     * 点击登录执行的动作
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String clientLogin(String userName,String password,String kaptcha) {
        //验证验证码是否正确
        if (customProperties.getOpenkaptcha()) {
            String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (StringUtils.isEmpty(kaptcha) || !kaptcha.equalsIgnoreCase(code)) {
                throw new InvalidKaptchaException();
            }
        }

        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password.toCharArray());
        token.setRememberMe(false);
        currentUser.login(token);
        ShiroUser shiroUser = ShiroKit.getUser();
        super.getSession().setAttribute("shiroUser", shiroUser);
        super.getSession().setAttribute("userName", shiroUser.getAccount());
        ShiroKit.getSession().setAttribute("sessionFlag", true);
        return REDIRECT + "/";
    }


    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        ShiroKit.getSubject().logout();
        return REDIRECT + "/login";
    }

}
