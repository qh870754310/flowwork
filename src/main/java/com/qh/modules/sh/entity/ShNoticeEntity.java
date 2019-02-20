package com.qh.modules.sh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qh.modules.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;



/**
 * 上海科技学院通知公告表
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-02 14:30:01
 */
public class ShNoticeEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String id;
	//标题
	private String title;
	//内容
	private String content;
	//创建日期
	private Date createdate;
	//状态（0-正常，-1-禁用）
	private Integer state;

	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/**
	 * 获取：创建日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreatedate() {
		return createdate;
	}
	/**
	 * 设置：状态（0-正常，-1-禁用）
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：状态（0-正常，-1-禁用）
	 */
	public Integer getState() {
		return state;
	}
}
