package com.doctortech.fhq.service.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.doctortech.fhq.bean.ResourcesBean;
import com.doctortech.fhq.entity.jpa.common.MenuRole;
import com.doctortech.fhq.entity.jpa.common.Resource;
import com.doctortech.fhq.entity.jpa.common.RoleResource;
import com.doctortech.fhq.repository.mapper.common.ResourceMapper;
import com.doctortech.fhq.repository.mapper.common.RoleResourceMapper;
import com.doctortech.framework.common.shiro.ShiroKit;
import com.doctortech.framework.common.shiro.ShiroUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResourcesService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleResourceMapper roleResourceDao;

    public void saveOrUpdate(ResourcesBean bean) {
        Date now= new Date();
        ShiroUser user= ShiroKit.getUser();
        Resource res = null;
        if (bean.getId()!=null) {
            res= resourceMapper.selectById(bean.getId());
            if (res==null) return;
            BeanUtils.copyProperties(bean,res);
            res.setUpdateTime(now);
            res.setUpdateUserId(user.getId());
            resourceMapper.updateById(res);
        } else {
            res= new Resource();
            BeanUtils.copyProperties(bean,res);
            res.setCreateTime(now);
            res.setCreateUserId(user.getId());
            resourceMapper.insert(res);
        }
    }

    public void delete(String id) {
        for (String i : id.split(",")) {
            try {
                resourceMapper.deleteById(Long.valueOf(i));
                QueryWrapper<RoleResource> condition = new QueryWrapper<>();
                condition.eq("resource_id",id);
                roleResourceDao.delete(condition);
            } catch (Exception e) {

            }
        }
    }

    /**
     * 获取角色资源
     * @param id
     * @return
     */
    public List<RoleResource>  getRoleRes(Long id) {
        RoleResource res = new RoleResource();
        QueryWrapper<RoleResource> condition = new QueryWrapper<>();
        condition.eq("role_id",id);
        List<RoleResource> list = res.selectList(condition);
        return list;
    }

    /**
     * 获取角色菜单
     * @param id
     * @return
     */
    public List<MenuRole>  getMenuRes(Long id) {
        MenuRole menuRole= new MenuRole();
        QueryWrapper<MenuRole> condition = new QueryWrapper<>();
        condition.eq("role_id",id);
        List<MenuRole> list = menuRole.selectList(condition);
        return list;
    }
}
