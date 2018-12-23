package com.doctortech.fhq.entity.jpa.common;

import com.baomidou.mybatisplus.annotation.TableName;
import com.doctortech.framework.orm.CommonModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="sys_role" ,uniqueConstraints={@UniqueConstraint(columnNames={"code"})} )
@TableName("sys_role")
public class Role extends CommonModel<Role> {

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色编号，唯一，作为系统区分
     */
    private String code;

    /**
     * 角色描述
     */
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
