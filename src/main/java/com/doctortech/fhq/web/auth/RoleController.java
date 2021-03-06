package com.doctortech.fhq.web.auth;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doctortech.fhq.bean.RoleBean;
import com.doctortech.fhq.bean.RoleResourcesBean;
import com.doctortech.fhq.service.common.RoleService;
import com.doctortech.fhq.web.BaseController;
import com.doctortech.framework.bean.CommonRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("role")
@Controller
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("save")
    @ResponseBody
    public CommonRespon addOrUpdate(RoleResourcesBean bean) {
        CommonRespon res;
        try {
            roleService.addOrUpdate(bean);
            res= success();
        } catch (DuplicateKeyException e) {
            res= faild();
            res.setMsg("该资源编码已存在，请重新输入！");
        } catch (Exception e) {
            res= faild();
        }
        return res;
    }

    @RequestMapping("list")
    @ResponseBody
    public CommonRespon getRolesList(Page<RoleBean> page, String name, String code) {
        CommonRespon res= success();
        res.setData(roleService.getPageList(page,name,code));
        return res;
    }

    @RequestMapping("delete")
    @ResponseBody()
    public CommonRespon deleteRole(String ids) {
        CommonRespon res= success();
        roleService.deleteRole(ids);
        return res;
    }
}
