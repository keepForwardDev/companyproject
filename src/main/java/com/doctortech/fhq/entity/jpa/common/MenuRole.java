package com.doctortech.fhq.entity.jpa.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.doctortech.framework.orm.CommonModel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色菜单关系表
 */
@Entity
@Table(name="sys_menu_role")
@TableName("sys_menu_role")
public class MenuRole extends Model<MenuRole> {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long roleId;

    private Long menuId;

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

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
