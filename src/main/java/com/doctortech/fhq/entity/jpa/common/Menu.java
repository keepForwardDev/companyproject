package com.doctortech.fhq.entity.jpa.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.doctortech.framework.orm.CommonModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import java.util.List;

/**
 * 菜单
 */
@Entity
@Table(name ="sys_menu")
@TableName("sys_menu")
public class Menu extends CommonModel<Menu> {
    /**
     * 访问地址
     */
    private String path;

    /**
     * 菜单名称
     */
    private String title;

    private String code;
    /**
     * 父级菜单
     *
     */
    private Long parentId;


    /**
     * 路由meta
     *
     */
    private String meta;

    /**
     * 前端组件地址 例如 view/login/login.vue
     */
    private String component;


    /**
     * 排序号
     */
    private Integer sort;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
