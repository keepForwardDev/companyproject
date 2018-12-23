package com.doctortech.fhq.repository.mapper.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doctortech.fhq.entity.jpa.common.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 获取用户角色
     * @param userId
     * @return
     */

    @Select("select * from sys_role where id in (select role_id from sys_user_role where user_id=#{userId})")
    public List<Role> getUserRoles(Long userId);
}
