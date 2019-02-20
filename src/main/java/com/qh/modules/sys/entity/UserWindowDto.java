package com.qh.modules.sys.entity;

/**
 * 类的功能描述：
 * 审批范围dto
 *
 * Created by Administrator on 2018/4/26.
 */
public class UserWindowDto {

    /**
     * 主键id 可能是： 用户id， 角色id， 组织id
     */
    private String id;
    /**
     * 名称
     */
    private String name;

    /**
     * 机构
     */
    private String bapName;

    /**
     * 用户类型
     */
    private String userType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBapName() {
        return bapName;
    }

    public void setBapName(String bapName) {
        this.bapName = bapName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
