package com.qh.modules.common.entity;

import java.util.Date;

/**
 * 类的功能描述：
 * activiti公共属性，需要用到流程的业务，需要继承
 *
 * Created by Administrator on 2018/4/24.
 */
public class ActivitiBaseEntity extends BaseEntity {

    /**
     * 业务流程状态 1=草稿，2=审批中，3=结束
     */
    private String status;

    /**
     * 审批结果： 1为同意，2为不同意，3为审批中
     */
    private String actResult;

    /**
     * 流程发起时间
     */
    private Date startTime;

    /**
     * 流程实例Id
     */
    private String instanceId;

    /**
     *  流程定义id
     */
    private String defId;

    /**
     * 流程发起人
     */
    private String startUserId;

    /**
     * 业务流程单据编号
     */
    private String code;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getDefId() {
        return defId;
    }

    public void setDefId(String defId) {
        this.defId = defId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActResult() {
        return actResult;
    }

    public void setActResult(String actResult) {
        this.actResult = actResult;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }
}
