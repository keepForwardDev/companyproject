package com.doctortech.fhq.service.common;

import com.alibaba.fastjson.JSONObject;
import com.doctortech.fhq.bean.Router;
import com.doctortech.fhq.entity.jpa.common.Menu;
import com.doctortech.fhq.repository.mapper.common.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuService {
    @Autowired
    private MenuMapper menuDao;
    /**
     * 获取用户菜单
     * @param rolesId
     * @return
     */
    public List<Menu> selectUserMenus(List<Long> rolesId) {
        if (rolesId.isEmpty()) {
            return null;
        }
        return menuDao.selectUserMenus(rolesId);
    }

    public List<Router> getUserRouters(List<Long> rolesId) {
        List<Menu> menu= selectUserMenus(rolesId);
        List<Router> routers= new ArrayList<>();
        //根节点
        List<Menu> parents= menu.stream().filter(m->m.getParentId()==null).collect(Collectors.toList());
        //子节点
        List<Menu> child= menu.stream().filter(m->m.getParentId()!=null).collect(Collectors.toList());
        if (parents.isEmpty()) {
            return routers;
        }
        parents.forEach(p->{
            routers.add(getRouter(p,child));
        });
        return routers;
    }

    private Router getRouter(Menu parent ,List<Menu> all) {
        Router router= new Router();
        router.setPath(parent.getPath());
        router.setComponent(parent.getComponent());
        router.setMeta(JSONObject.parseObject(parent.getMeta()));
        router.setName(parent.getCode());
        List<Router> children = new ArrayList<>();
        all.forEach(m->{
            if (parent.getId().longValue()==m.getParentId().longValue()) {
                children.add(getRouter(m,all));
            }
        });
        router.setChildren(children);
        return router;
    }
}
