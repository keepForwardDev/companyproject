package com.doctortech.fhq.entity.jpa.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色资源关系表
 */
@Entity
@Table(name="sys_role_resource")
@TableName("sys_role_resource")
public class RoleResource extends Model<RoleResource> {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long roleId;

    private Long resourceId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
