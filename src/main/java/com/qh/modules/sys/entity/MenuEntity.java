package com.qh.modules.sys.entity;

import com.qh.modules.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单表
 *
 * Created by Administrator on 2018/4/19.
 */
public class MenuEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键Id
    private String id;

    //父菜单Id
    private String parentId;

    //所有父菜单id
    private String parentIds;

    //菜单名称
    private String name;

    //菜单URL
    private String url;

    //菜单图标
    private String icon;

    //排序
    private Integer sort;

    //状态（0：显示，-1：隐藏）
    private String status;

    //权限标示
    private String permission;

    //备注
    private String remark;

    //菜单类型
    private String type;

    //父菜单名称
    private String parentName;

    //子类菜单
    private List list;

    //是否展开
    private String open;

    /**
     * 获取：主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取：父菜单id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置：父菜单id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：所有父id
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * 设置：所有父id
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * 获取：菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：菜单链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：菜单链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：菜单图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置：菜单图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取：排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置：排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取：状态（0显示，1隐藏)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置：状态（0显示，1隐藏)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取：权限标识
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 设置：权限标识
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * 获取：备注
     */
    @Override
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：备注
     */
    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }
}
