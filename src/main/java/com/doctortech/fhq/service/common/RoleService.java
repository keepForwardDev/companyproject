package com.doctortech.fhq.service.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doctortech.fhq.bean.RoleBean;
import com.doctortech.fhq.bean.RoleResourcesBean;
import com.doctortech.fhq.entity.jpa.common.MenuRole;
import com.doctortech.fhq.entity.jpa.common.Role;
import com.doctortech.fhq.entity.jpa.common.RoleResource;
import com.doctortech.fhq.repository.mapper.common.MenuRoleMapper;
import com.doctortech.fhq.repository.mapper.common.RoleMapper;
import com.doctortech.fhq.repository.mapper.common.RoleResourceMapper;
import com.doctortech.fhq.utils.SqlHelper;
import com.doctortech.framework.common.shiro.ShiroKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleDao;

    @Autowired
    private MenuRoleMapper menuRoleDao;

    @Autowired
    private RoleResourceMapper roleResourceDao;

    public void addOrUpdate(RoleResourcesBean bean) {
        Date now= new Date();
        Role role = null;
        if (bean.getId()==null) {
            role= new Role();
            role.setCreateTime(now);
            role.setCode(bean.getCode());
            role.setDescription(bean.getDescription());
            role.setName(bean.getName());
            role.setCreateUserId(ShiroKit.getUser().getId());
            roleDao.insert(role);
        } else {
            role= roleDao.selectById(bean.getId());
            if (role==null) {
                return;
            }
            role.setCode(bean.getCode());
            role.setDescription(bean.getDescription());
            role.setName(bean.getName());
            role.setUpdateTime(now);
            role.setUpdateUserId(ShiroKit.getUser().getId());
            roleDao.updateById(role);
        }
        Map<String, Object> columnMap= new HashMap<>();
        columnMap.put("role_id", role.getId());
        //删除
        menuRoleDao.deleteByMap(columnMap);
        roleResourceDao.deleteByMap(columnMap);
        batchSaveRoleResMenu(bean.getResources(), bean.getMenu(),role.getId());
    }

    /**
     * 批量保存角色关联表
     * @param resourcesId 多个以逗号分隔
     * @param menusId 多个以逗号分隔
     * @param roleId
     */
    public void batchSaveRoleResMenu(String resourcesId, String menusId,Long roleId) {
        if (!StringUtils.isBlank(resourcesId)) {
            for (String id : resourcesId.split(",")) {
                saveRoleResources(Long.valueOf(id), roleId);
            }
        }

        if (!StringUtils.isBlank(menusId)) {
            for (String id : menusId.split(",")) {
                saveMenuRole(Long.valueOf(id), roleId);
            }
        }
    }

    /**
     * 保存角色和菜单关系
     * @param menuId
     * @param roleId
     */
    public void saveMenuRole(Long menuId, Long roleId) {
        MenuRole menuRole= new MenuRole();
        menuRole.setMenuId(menuId);
        menuRole.setRoleId(roleId);
        menuRoleDao.insert(menuRole);
    }

    /**
     * 保存角色和资源关系
     * @param resourceId
     * @param roleId
     */
    public void saveRoleResources(Long resourceId,Long roleId) {
        RoleResource roleResource = new RoleResource();
        roleResource.setResourceId(resourceId);
        roleResource.setRoleId(roleId);
        roleResourceDao.insert(roleResource);
    }

    public IPage<RoleBean> getPageList(Page<RoleBean> page,String name,String code) {
        return roleDao.getRolesList(page,SqlHelper.getSqlLikeParams(name),SqlHelper.getSqlLikeParams(code));
    }

    public void deleteRole(String ids) {
        if (StringUtils.isBlank(ids)) {
            return;
        }
        String[] idArray= ids.split(",");
        for (String id : idArray) {
            roleDao.deleteById(id);
        }
    }


}
