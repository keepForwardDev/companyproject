package com.doctortech.fhq.web.auth;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doctortech.fhq.bean.MenuResources;
import com.doctortech.fhq.bean.ResourcesBean;
import com.doctortech.fhq.service.common.MenuService;
import com.doctortech.fhq.service.common.ResourcesService;
import com.doctortech.fhq.web.BaseController;
import com.doctortech.framework.bean.CommonRespon;
import com.doctortech.framework.consts.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("resource")
@Controller
public class ResourcesController extends BaseController{

    @Autowired
    private MenuService menuService;

    @Autowired
    private ResourcesService resourcesService;

    @RequestMapping(value = "/menuResources",method = RequestMethod.POST)
    @ResponseBody
    public CommonRespon getMenuResources(MenuResources rs, Page<MenuResources> page) {
        CommonRespon res= success();
        res.setData(menuService.getMenuResources(rs,page));
        return res;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public CommonRespon saveOrUpdate(@RequestBody @Valid ResourcesBean bean, BindingResult result) {
        CommonRespon res= success();
        List<ObjectError> error = result.getAllErrors();
        if (error.size() > 0) {
            res.setCode(Const.CODE_ERROR);
            res.setMsg(error.get(0).getDefaultMessage());
            return res;
        }
        try {
            resourcesService.saveOrUpdate(bean);
        } catch (DuplicateKeyException e) {
            res.setCode(Const.CODE_ERROR);
            res.setMsg("该资源编码已存在，请重新输入！");
        }
        return res;
    }


    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public CommonRespon delete(String id) {
        if (id==null) {
            return faild();
        }
        CommonRespon res= success();
        resourcesService.delete(id);
        return res;
    }
}
