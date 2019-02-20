package com.qh.modules.sh.entity;

import com.qh.modules.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;



/**
 * 文件管理
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-03 09:58:17
 */
public class ShResourcesUploadEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//资源类型（1-入学指南，2-资助政策，3-缴费说明，4-安全信息）
	private Integer type;
	//文件说明
	private String remark;
	//存储路径
	private String path;
	//文件名
	private String name;
	//新增日期
	private Date createDate;
	//状态（1-正常，0-禁用）
	private Integer status;
	//显示顺序
	private Integer order;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：资源类型（1-入学指南，2-资助政策，3-缴费说明，4-安全信息）
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：资源类型（1-入学指南，2-资助政策，3-缴费说明，4-安全信息）
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：文件说明
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：文件说明
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：存储路径
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 获取：存储路径
	 */
	public String getPath() {
		return path;
	}
	/**
	 * 设置：文件名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：文件名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 获取：新增日期
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：新增日期
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取：显示顺序
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * 设置：显示顺序
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
}
