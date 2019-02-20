package com.qh.modules.sys.entity;

import java.io.Serializable;

/**
 * 权限角色表
 *
 * Created by Administrator on 2018/5/17.
 */
public class RoleMenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //角色id
    private String roleId;
    //菜单id
    private String menuId;

    /**
     * 设置：角色id
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    /**
     * 获取：角色id
     */
    public String getRoleId() {
        return roleId;
    }
    /**
     * 设置：权限id
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    /**
     * 获取：权限id
     */
    public String getMenuId() {
        return menuId;
    }

}
