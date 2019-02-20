package com.qh.modules.sh.entity;

import com.qh.modules.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/21.
 */
public class Guidevt extends BaseEntity implements Serializable {

    private String id;

    private Integer blzt;

    private String hjbz;

    private Integer hjid;

    private String pcid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBlzt() {
        return blzt;
    }

    public void setBlzt(Integer blzt) {
        this.blzt = blzt;
    }

    public String getHjbz() {
        return hjbz;
    }

    public void setHjbz(String hjbz) {
        this.hjbz = hjbz;
    }

    public Integer getHjid() {
        return hjid;
    }

    public void setHjid(Integer hjid) {
        this.hjid = hjid;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }
}
