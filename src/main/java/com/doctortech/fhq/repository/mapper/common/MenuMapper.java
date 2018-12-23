package com.doctortech.fhq.repository.mapper.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doctortech.fhq.entity.jpa.common.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 查询角色对应的菜单
     * @param roleIds
     * @return
     */
    public List<Menu> selectUserMenus(@Param("roleIds") List<Long> roleIds);
}
