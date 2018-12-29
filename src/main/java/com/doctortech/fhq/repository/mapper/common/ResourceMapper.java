package com.doctortech.fhq.repository.mapper.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doctortech.fhq.bean.MenuResources;
import com.doctortech.fhq.entity.jpa.common.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper extends BaseMapper<Resource> {

    public List<Resource> getRoleResources(@Param("roleIds") List<Long> roleIds);

    public IPage<MenuResources> getMenuRes(Page<MenuResources> page,@Param("res") MenuResources res);
}
