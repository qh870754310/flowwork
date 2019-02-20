package com.qh.modules.sh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qh.modules.common.entity.BaseEntity;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-25 09:21:11
 */
public class ShFamilyMemberEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//学生基本信息id
	private String studentId;
	//亲属关系
	private String domesticRelation;
	//亲属姓名
	private String familyName;
	//政治面貌
	private String politicalStatus;
	//出生日期
	private Date birthday;
	//证件类型
	private String certificateType;
	//证件号
	private String idCard;
	//家庭成员
	private String member;
	//平均月收入
	private BigDecimal averageMonthlyEarnings;
	//健康状况
	private String healthCondition;
	//是否监护人
	private Boolean guardian;
	//文化程度
	private String educationalLevel;
	//工作单位
	private String company;
	//职业
	private String profession;
	//职务
	private String post;
	//联系地址
	private String contactAddress;
	//邮政编码
	private String postalCodes;
	//手机
	private String mobile;
	//联系电话
	private String phone;
	//婚姻状况
	private String maritalStatus;
	//备注
	private String remark;
	@Transient
	private String relation_code;
	@Transient
	private String political_code;
	@Transient
	private String certificate_code;
	@Transient
	private String marital_code;
	@Transient
	private String health_code;

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
	 * 设置：学生基本信息id
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * 获取：学生基本信息id
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * 设置：亲属关系
	 */
	public void setDomesticRelation(String domesticRelation) {
		this.domesticRelation = domesticRelation;
	}
	/**
	 * 获取：亲属关系
	 */
	public String getDomesticRelation() {
		return domesticRelation;
	}
	/**
	 * 设置：亲属姓名
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	/**
	 * 获取：亲属姓名
	 */
	public String getFamilyName() {
		return familyName;
	}
	/**
	 * 设置：政治面貌
	 */
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	/**
	 * 获取：政治面貌
	 */
	public String getPoliticalStatus() {
		return politicalStatus;
	}
	/**
	 * 设置：出生日期
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：出生日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 设置：证件类型
	 */
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	/**
	 * 获取：证件类型
	 */
	public String getCertificateType() {
		return certificateType;
	}
	/**
	 * 设置：证件号
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * 获取：证件号
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * 设置：家庭成员
	 */
	public void setMember(String member) {
		this.member = member;
	}
	/**
	 * 获取：家庭成员
	 */
	public String getMember() {
		return member;
	}
	/**
	 * 设置：平均月收入
	 */
	public void setAverageMonthlyEarnings(BigDecimal averageMonthlyEarnings) {
		this.averageMonthlyEarnings = averageMonthlyEarnings;
	}
	/**
	 * 获取：平均月收入
	 */
	public BigDecimal getAverageMonthlyEarnings() {
		return averageMonthlyEarnings;
	}
	/**
	 * 设置：健康状况
	 */
	public void setHealthCondition(String healthCondition) {
		this.healthCondition = healthCondition;
	}
	/**
	 * 获取：健康状况
	 */
	public String getHealthCondition() {
		return healthCondition;
	}
	/**
	 * 设置：是否监护人
	 */
	public void setGuardian(Boolean guardian) {
		this.guardian = guardian;
	}
	/**
	 * 获取：是否监护人
	 */
	public Boolean getGuardian() {
		return guardian;
	}
	/**
	 * 设置：文化程度
	 */
	public void setEducationalLevel(String educationalLevel) {
		this.educationalLevel = educationalLevel;
	}
	/**
	 * 获取：文化程度
	 */
	public String getEducationalLevel() {
		return educationalLevel;
	}
	/**
	 * 设置：工作单位
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * 获取：工作单位
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * 设置：职业
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}
	/**
	 * 获取：职业
	 */
	public String getProfession() {
		return profession;
	}
	/**
	 * 设置：职务
	 */
	public void setPost(String post) {
		this.post = post;
	}
	/**
	 * 获取：职务
	 */
	public String getPost() {
		return post;
	}
	/**
	 * 设置：联系地址
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	/**
	 * 获取：联系地址
	 */
	public String getContactAddress() {
		return contactAddress;
	}
	/**
	 * 设置：邮政编码
	 */
	public void setPostalCodes(String postalCodes) {
		this.postalCodes = postalCodes;
	}
	/**
	 * 获取：邮政编码
	 */
	public String getPostalCodes() {
		return postalCodes;
	}
	/**
	 * 设置：手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：婚姻状况
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	/**
	 * 获取：婚姻状况
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}

	public String getRelation_code() {
		return relation_code;
	}

	public void setRelation_code(String relation_code) {
		this.relation_code = relation_code;
	}

	public String getPolitical_code() {
		return political_code;
	}

	public void setPolitical_code(String political_code) {
		this.political_code = political_code;
	}

	public String getCertificate_code() {
		return certificate_code;
	}

	public void setCertificate_code(String certificate_code) {
		this.certificate_code = certificate_code;
	}

	public String getMarital_code() {
		return marital_code;
	}

	public void setMarital_code(String marital_code) {
		this.marital_code = marital_code;
	}

	public String getHealth_code() {
		return health_code;
	}

	public void setHealth_code(String health_code) {
		this.health_code = health_code;
	}
}
