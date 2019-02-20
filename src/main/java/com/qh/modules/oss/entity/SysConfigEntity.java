package com.qh.modules.oss.entity;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 类SysConfigEntity的功能描述:
 * 系统参数配置信息
 *
 *
 * Created by Administrator on 2018/5/11.
 */
public class SysConfigEntity {

    private Long id;
    @NotBlank(message = "参数名不能为空")
    private String key;
    @NotBlank(message = "参数名不能为空")
    private String value;
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
