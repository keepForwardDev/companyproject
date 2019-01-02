package com.doctortech.fhq.service.common;

import com.doctortech.fhq.bean.ResourcesBean;
import com.doctortech.fhq.entity.jpa.common.Resource;
import com.doctortech.fhq.repository.mapper.common.ResourceMapper;
import com.doctortech.framework.common.shiro.ShiroKit;
import com.doctortech.framework.common.shiro.ShiroUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResourcesService {
    @Autowired
    private ResourceMapper resourceMapper;

    public void saveOrUpdate(ResourcesBean bean) {
        Date now= new Date();
        ShiroUser user= ShiroKit.getUser();
        if (bean.getId()!=null) {
            Resource res= resourceMapper.selectById(bean.getId());
            if (res==null) return;
            BeanUtils.copyProperties(bean,res);
            res.setUpdateTime(now);
            res.setUpdateUserId(user.getId());
            resourceMapper.updateById(res);
        } else {
            Resource res= new Resource();
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
            } catch (Exception e) {

            }
        }
    }
}
