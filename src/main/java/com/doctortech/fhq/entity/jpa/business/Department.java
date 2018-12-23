package com.doctortech.fhq.entity.jpa.business;

import com.baomidou.mybatisplus.annotation.TableName;
import com.doctortech.framework.orm.CommonModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sys_department")
@TableName("sys_department")
public class Department extends CommonModel<Department> {
    private static final long serialVersionUID = 1L;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 全名
     */
    private String fullName;

    /**
     * 区域id
     */
    private Long areaId;

    /**
     * 部门编码
     */
    private String levelCode;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }
}
