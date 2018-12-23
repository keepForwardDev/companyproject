package com.doctortech.fhq.web.auth;

import com.doctortech.fhq.web.BaseController;
import com.doctortech.framework.bean.CommonRespon;
import com.doctortech.framework.consts.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/menu")
@Controller
public class MenuController extends BaseController{

    /**
     * 登陆用户菜单
     * @return
     */
    @RequestMapping(value = "/me",method = RequestMethod.GET)
    @ResponseBody
    public CommonRespon myMenu() {
        CommonRespon res= new CommonRespon();
        res.setCode(Const.CODE_SUCCESS);
        res.setMsg(Const.CODE_SUCCESS_STR);
        res.setData(getCurrentUser().getMenu());
        return res;
    }
}
