package com.qh.modules.sys.entity;

import java.io.Serializable;

/**
 * 用户角色关系表
 *
 * Created by Administrator on 2018/4/19.
 */
public class UserRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //用户Id
    private String userId;

    //角色Id
    private String roleId;

    /**
     * 设置：用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 获取：用户id
     */
    public String getUserId() {
        return userId;
    }
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
}
