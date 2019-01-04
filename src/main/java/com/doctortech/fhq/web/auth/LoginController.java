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
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String clientLogin(String userName, String password, String kaptcha) {
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

    @ResponseBody
    @RequestMapping(value = "/vueLogin", method = RequestMethod.POST)
    public CommonRespon vueLogin(String userName, String password,String kaptcha) {
        CommonRespon responese;
        try {

            String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (!StringUtils.isEmpty(kaptcha) && !kaptcha.equalsIgnoreCase(code)) {
                throw new InvalidKaptchaException();
            }
            Subject currentUser = ShiroKit.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password.toCharArray());
            token.setRememberMe(false);
            currentUser.login(token);
            ShiroUser shiroUser = ShiroKit.getUser();
            super.getSession().setAttribute("shiroUser", shiroUser);
            super.getSession().setAttribute("userName", shiroUser.getAccount());
            ShiroKit.getSession().setAttribute("sessionFlag", true);
            CommonRespon res = success();
            res.setData(super.getSession().getId());
            return res;
        } catch (InvalidKaptchaException e) {
            responese = faild();
            responese.setMsg("验证码错误");
        } catch (CredentialsException e) {
            responese = faild();
            responese.setMsg("账号或者密码错误");
            Integer errorTimes = getSession().getAttribute("errorTimes") ==null?0: (Integer)getSession().getAttribute("errorTimes");
            if (errorTimes >= Const.LOGIN_MAX_NUM) {
                responese.setData(true);
            }
            errorTimes = errorTimes+1;
            getSession().setAttribute("errorTimes",errorTimes );
        } catch (DisabledAccountException e) {
            responese = faild();
            responese.setMsg("账号被冻结");
        } catch (AuthenticationException e) {
            responese = noLogin();
        }
        return responese;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public CommonRespon getUserInfo() {
        CommonRespon responese;
        ShiroUser currentUser = getCurrentUser();
        Map<String, Object> res = new HashMap<>();
        res.put("userName", currentUser.getName());
        res.put("avatar", currentUser.getAvatar());
        List<String> access = new ArrayList<>();
        currentUser.getMenu().forEach(m -> {
            access.add(m.getName());
        });
        res.put("resources", currentUser.getResourcesCode());
        res.put("access", access);
        res.put("userId", currentUser.getId());
        res.put("departmentName", currentUser.getDepartmentName());
        res.put("roleName", currentUser.getRoleName());
        res.put("menu", currentUser.getMenu());
        responese = success();
        responese.setData(res);
        return responese;
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public CommonRespon logOut() {
        ShiroKit.getSubject().logout();
        CommonRespon res= success();
       // return REDIRECT + "/login";
        return res;
    }

}
