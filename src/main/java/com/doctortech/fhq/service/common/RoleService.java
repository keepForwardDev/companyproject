package com.doctortech.fhq.service.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.doctortech.fhq.bean.RoleBean;
import com.doctortech.fhq.entity.jpa.common.Role;
import com.doctortech.fhq.repository.mapper.common.RoleMapper;
import com.doctortech.fhq.utils.SqlHelper;
import com.doctortech.framework.common.shiro.ShiroKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleDao;


    public void addOrUpdate(Long id,String name,String code,String description) {
        Date now= new Date();
        Role role = null;
        if (id==null) {
            role= new Role();
            role.setCreateTime(now);
            role.setCode(code);
            role.setDescription(description);
            role.setName(name);
            role.setCreateUserId(ShiroKit.getUser().getId());
            roleDao.insert(role);
        } else {
            role= roleDao.selectById(id);
            if (role==null) {
                return;
            }
            role.setCode(code);
            role.setDescription(description);
            role.setName(name);
            role.setUpdateTime(now);
            role.setUpdateUserId(ShiroKit.getUser().getId());
            roleDao.updateById(role);
        }
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
