package com.qh.modules.oss.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 类SysOssEntity的功能描述:
 * 文件上传
 *
 * Created by Administrator on 2018/5/14.
 */
public class SysOssEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //
    private Long id;

    //URL地址
    private String url;
    //创建时间
    private Date createDate;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：URL地址
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * 获取：URL地址
     */
    public String getUrl() {
        return url;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    /**
     * 获取：创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }
}
