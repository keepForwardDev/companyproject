package com.doctortech.fhq.bean;

import com.alibaba.fastjson.JSONObject;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * 路由
 */
public class Router implements Serializable{
    /**
     * 访问地址
     */
    @NotBlank(message = "访问地址必须填写")
    private String path;

    /**
     * 菜单 code
     */
    @NotBlank(message = "菜单唯一标识必须填写")
    private String name;

    /**
     * 路由meta
     *
     */
    private JSONObject meta;

    private List<Router> children;

    /**
     *
     * 前端组件地址 例如 view/login/login.vue
     */
    @NotBlank(message = "组件地址必须填写")
    private String component;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称必须填写")
    private String title;

    private Long id;

    private Long parentId;

    private Integer sort;

    private boolean expand= true;
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Router> getChildren() {
        return children;
    }

    public void setChildren(List<Router> children) {
        this.children = children;
    }

    public JSONObject getMeta() {
        return meta;
    }

    public void setMeta(JSONObject meta) {
        this.meta = meta;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public boolean isExpand() {
        return expand;
    }
}
