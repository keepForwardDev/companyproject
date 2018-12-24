package com.doctortech.fhq.service.common;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.doctortech.fhq.bean.Router;
import com.doctortech.fhq.entity.jpa.common.Menu;
import com.doctortech.fhq.repository.mapper.common.MenuMapper;
import com.doctortech.fhq.utils.SqlHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * 获取用户路由
     * @param rolesId
     * @return
     */
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
        router.setTitle(parent.getTitle());
        router.setId(parent.getId());
        router.setParentId(parent.getParentId());
        router.setSort(parent.getSort());
        List<Router> children = new ArrayList<>();
        all.forEach(m->{
            if (parent.getId().longValue()==m.getParentId().longValue()) {
                children.add(getRouter(m,all));
            }
        });
        router.setChildren(children);
        return router;
    }

    /**
     * 菜单列表
     * @return
     */
    public List<Router> getMenuList(String title) {
        List<Router> routers= new ArrayList<>();
        QueryWrapper<Menu> condition = new QueryWrapper<>();
        if (StringUtils.isNotBlank(title)) {
            condition.apply(SqlHelper.getSqlLike(),SqlHelper.getSqlLikeParams(title));
        }
        condition.orderByAsc("sort");
        List<Menu> list= menuDao.selectList(condition);
        //根节点
        List<Menu> parents= list.stream().filter(m->m.getParentId()==null).collect(Collectors.toList());
        //子节点
        List<Menu> child= list.stream().filter(m->m.getParentId()!=null).collect(Collectors.toList());
        if (parents.isEmpty()) {
            return routers;
        }
        parents.forEach(p->{
            routers.add(getRouter(p,child));
        });
        return routers;
    }
}
