package com.doctortech.fhq.entity.jpa.common;

import com.baomidou.mybatisplus.annotation.TableName;
import com.doctortech.framework.orm.CommonModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 按钮资源表
 */
@Entity
@Table(name="sys_resource",uniqueConstraints={@UniqueConstraint(columnNames={"code"})})
@TableName("sys_resource")
public class Resource extends CommonModel<Resource> {

    /**
     * 资源编码， 唯一
     */
    private String code;

    /**
     * 资源链接
     */
    private String url;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 资源展示排序
     */
    private Integer sort=0;

    /**
     * 所属菜单
     */
    private Long menuId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
