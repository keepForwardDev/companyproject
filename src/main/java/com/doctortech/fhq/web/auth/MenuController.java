package com.doctortech.fhq.web.auth;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doctortech.fhq.bean.MenuResources;
import com.doctortech.fhq.bean.Router;
import com.doctortech.fhq.service.common.MenuService;
import com.doctortech.fhq.web.BaseController;
import com.doctortech.framework.bean.CommonRespon;
import com.doctortech.framework.consts.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/menu")
@Controller
public class MenuController extends BaseController{

    @Autowired
    private MenuService menuService;
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

    @RequestMapping(value = "/menuList",method = RequestMethod.GET)
    @ResponseBody
    public CommonRespon menuList(String title) {
        CommonRespon res= success();
        res.setData(menuService.getMenuList(title));
        return res;
    }

    @RequestMapping(value = "/menuTree",method = RequestMethod.GET)
    @ResponseBody
    public CommonRespon menuTree() {
        CommonRespon res= success();
        res.setData(menuService.getMenuTree());
        return res;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public CommonRespon saveMenu(@RequestBody @Valid Router r, BindingResult result) {
        CommonRespon res= new CommonRespon();
        List<ObjectError> error = result.getAllErrors();
        if (error.size() > 0) {
            res.setCode(Const.CODE_ERROR);
            res.setMsg(error.get(0).getDefaultMessage());
            return res;
        }
        menuService.saveOrUpdateMenu(r);
        return success();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public CommonRespon deleteMenu(Long id) {
        menuService.delete(id);
        return success();
    }

}
