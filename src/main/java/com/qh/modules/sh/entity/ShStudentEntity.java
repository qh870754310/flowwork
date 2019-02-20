package com.qh.modules.sh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qh.modules.common.entity.BaseEntity;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-20 13:22:00
 */
public class ShStudentEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//学号
	private String studentNo;
	//姓名
	private String name;
	//姓名拼音
	private String spellname;
	//曾用名
	private String usedname;
	//性别
	private String sex;
	//出生日期
	private Date birthday;
	//证件类型
	private String certificateType;
	//身份证号码
	private String idCard;
	//一卡通号
	private String metroCard;
	//手机卡号
	private String mobileno;
	//考生号
	private String candidateNo;
	//准考证号
	private String ticketNo;
	//生源地
	private String localInstitution;
	//籍贯
	private String nativePlace;
	//入团时间
	private Date leagueTime;
	//入党时间
	private Date partyTime;
	//婚姻状态
	private String maritalStatus;
	//身高（cm）
	private Double height;
	//体重（kg）
	private Double weight;
	//民族
	private String nation;
	//政治面貌
	private String politicalStatus;
	//来源国别
	private String sourceCountry;
	//户口所在省
	private String registeredProvince;
	//户口所在市
	private String registeredCity;
	//户口所在县
	private String registeredCounty;
	//户口所在地
	private String domicilePlace;
	//家庭所属街道（镇
	private String familyStreet;
	//警署
	private String townPoliceStation;
	//本人住址所在省
	private String addressProvince;
	//本人住址所在市
	private String addressCity;
	//本人住址所在县
	private String addressCounty;
	//本人住址
	private String address;
	//家庭特殊情况
	private String specialFamilyCircumstances;
	//是否知青子女
	private Boolean educatedChildren;
	//健康状况
	private String healthCondition;
	//血型
	private String bloodType;
	//银行卡号
	private String bankCard;
	//邮递编号
	private String postalCodes;
	//港澳台侨
	private String emigrantcode;
	//户口性质
	private String householdRegistrationType;
	//特长
	private String speciality;
	//获奖情况
	private String award;
	//通讯地址
	private String postaladdress;
	//电子邮箱
	private String email;
	//通讯编码
	private String postalcode;
	//其它联系方式
	private String otherWay;
	//MSN号
	private String msn;
	//个人主页
	private String homepage;
	//QQ号
	private String qq;
	//家庭地址
	private String homeAddress;
	//家庭邮编
	private String homePostal;
	//家庭电话
	private String homePhone;
	//家庭Email
	private String familyEmail;
	//到达地点
	private String arrival;
	//预计到达时间
	private Date arrivalTime;
	//到达班次
	private String arrivalFlight;
	//是否自备车
	private Boolean selfMadeCar;
	//随行人数
	private Integer entourage;
	//是否走读
	private Boolean nonResident;
	//是否预定生活用品
	private Boolean bookLiving;

	//学籍号
	private String studentStatus;
	//学生类别
	private String studentCategory;
	//学生身份
	private String studentIdentity;
	//院系
	private String department;
	//年级
	private String grade;
	//专业
	private String profession;
	//班级
	private String stuClass;
	//开户银行1
	private String bankAccountF;
	//开户银行2
	private String bankAccountS;
	//银行账号
	private String bankAccount;
	//报考省市
	private String bkss;
	//入学前单位
	private String rxqdw;
	//入学日期
	private Date rxrq;
	//入学年级
	private String rxnj;
	//入学院系
	private String rxyx;
	//入学专业
	private String rxzy;
	//入学方式
	private String rxfs;
	//培养方式
	private String pyfs;
	//学生来源
	private String xsly;
	//校区
	private String xq;
	//就读学位
	private String jdxw;
	//就读学历
	private String jdxl;
	//学制
	private String xz;
	//预计毕业年份
	private String yjbynf;
	//实际毕业时间
	private Date sjbysj;
	//手机号2
	private String mobile;
	//注册学年
	private String zcxn;
	//注册学期
	private String zcxq;
	//在校
	private Boolean zx;
	//再籍
	private Boolean zj;
	//高考成绩
	private Double gkcj;
	//备注
	private String remark;
	//军训信息
	private String jxxx;
	//毕业类型
	private String bylx;
	//毕业证书编号
	private String byzsbh;
	//考区
	private String kq;

	@Transient
	private String sex_code;

	@Transient
	private String nation_code;

	@Transient
	private String sourceCountry_code;

	@Transient
	private String certificate_code;

	@Transient
	private String blood_code;

	@Transient
	private String political_code;

	@Transient
	private String health_code;

	@Transient
	private String marital_code;

	@Transient
	private String emigrantcode_code;

	@Transient
	private String householdRegistrationType_code;

	@Transient
	private String student_type_code;

	@Transient
	private String student_identity_code;

	@Transient
	private String department_code;

	@Transient
	private String profession_code;

	@Transient
	private String class_code;

	@Transient
	private String rxyx_code;

	@Transient
	private String rxzy_code;

	@Transient
	private String rxfs_code;

	@Transient
	private String pyfs_code;

	@Transient
	private String xq_code;

	@Transient
	private String jdxw_code;

	@Transient
	private String jdxl_code;

	@Transient
	private String zcxn_code;

	@Transient
	private String zcxq_code;

	@Transient
	private String bylx_code;

	@Transient
	private String zj_code;

	@Transient
	private String zx_code;


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
	 * 设置：学号
	 */
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	/**
	 * 获取：学号
	 */
	public String getStudentNo() {
		return studentNo;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：姓名拼音
	 */
	public void setSpellname(String spellname) {
		this.spellname = spellname;
	}
	/**
	 * 获取：姓名拼音
	 */
	public String getSpellname() {
		return spellname;
	}
	/**
	 * 设置：曾用名
	 */
	public void setUsedname(String usedname) {
		this.usedname = usedname;
	}
	/**
	 * 获取：曾用名
	 */
	public String getUsedname() {
		return usedname;
	}
	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
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
	 * 设置：身份证号码
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * 获取：身份证号码
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * 设置：一卡通号
	 */
	public void setMetroCard(String metroCard) {
		this.metroCard = metroCard;
	}
	/**
	 * 获取：一卡通号
	 */
	public String getMetroCard() {
		return metroCard;
	}
	/**
	 * 设置：手机卡号
	 */
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	/**
	 * 获取：手机卡号
	 */
	public String getMobileno() {
		return mobileno;
	}
	/**
	 * 设置：考生号
	 */
	public void setCandidateNo(String candidateNo) {
		this.candidateNo = candidateNo;
	}
	/**
	 * 获取：考生号
	 */
	public String getCandidateNo() {
		return candidateNo;
	}
	/**
	 * 设置：准考证号
	 */
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	/**
	 * 获取：准考证号
	 */
	public String getTicketNo() {
		return ticketNo;
	}
	/**
	 * 设置：生源地
	 */
	public void setLocalInstitution(String localInstitution) {
		this.localInstitution = localInstitution;
	}
	/**
	 * 获取：生源地
	 */
	public String getLocalInstitution() {
		return localInstitution;
	}
	/**
	 * 设置：籍贯
	 */
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	/**
	 * 获取：籍贯
	 */
	public String getNativePlace() {
		return nativePlace;
	}
	/**
	 * 设置：入团时间
	 */
	public void setLeagueTime(Date leagueTime) {
		this.leagueTime = leagueTime;
	}
	/**
	 * 获取：入团时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getLeagueTime() {
		return leagueTime;
	}
	/**
	 * 设置：入党时间
	 */
	public void setPartyTime(Date partyTime) {
		this.partyTime = partyTime;
	}
	/**
	 * 获取：入党时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getPartyTime() {
		return partyTime;
	}
	/**
	 * 设置：婚姻状态
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	/**
	 * 获取：婚姻状态
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * 设置：身高（cm）
	 */
	public void setHeight(Double height) {
		this.height = height;
	}
	/**
	 * 获取：身高（cm）
	 */
	public Double getHeight() {
		return height;
	}
	/**
	 * 设置：体重（kg）
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	/**
	 * 获取：体重（kg）
	 */
	public Double getWeight() {
		return weight;
	}
	/**
	 * 设置：民族
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}
	/**
	 * 获取：民族
	 */
	public String getNation() {
		return nation;
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
	 * 设置：来源国别
	 */
	public void setSourceCountry(String sourceCountry) {
		this.sourceCountry = sourceCountry;
	}
	/**
	 * 获取：来源国别
	 */
	public String getSourceCountry() {
		return sourceCountry;
	}
	/**
	 * 设置：户口所在省
	 */
	public void setRegisteredProvince(String registeredProvince) {
		this.registeredProvince = registeredProvince;
	}
	/**
	 * 获取：户口所在省
	 */
	public String getRegisteredProvince() {
		return registeredProvince;
	}
	/**
	 * 设置：户口所在市
	 */
	public void setRegisteredCity(String registeredCity) {
		this.registeredCity = registeredCity;
	}
	/**
	 * 获取：户口所在市
	 */
	public String getRegisteredCity() {
		return registeredCity;
	}
	/**
	 * 设置：户口所在县
	 */
	public void setRegisteredCounty(String registeredCounty) {
		this.registeredCounty = registeredCounty;
	}
	/**
	 * 获取：户口所在县
	 */
	public String getRegisteredCounty() {
		return registeredCounty;
	}
	/**
	 * 设置：户口所在地
	 */
	public void setDomicilePlace(String domicilePlace) {
		this.domicilePlace = domicilePlace;
	}
	/**
	 * 获取：户口所在地
	 */
	public String getDomicilePlace() {
		return domicilePlace;
	}
	/**
	 * 设置：家庭所属街道（镇
	 */
	public void setFamilyStreet(String familyStreet) {
		this.familyStreet = familyStreet;
	}
	/**
	 * 获取：家庭所属街道（镇
	 */
	public String getFamilyStreet() {
		return familyStreet;
	}
	/**
	 * 设置：警署
	 */
	public void setTownPoliceStation(String townPoliceStation) {
		this.townPoliceStation = townPoliceStation;
	}
	/**
	 * 获取：警署
	 */
	public String getTownPoliceStation() {
		return townPoliceStation;
	}
	/**
	 * 设置：本人住址所在省
	 */
	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}
	/**
	 * 获取：本人住址所在省
	 */
	public String getAddressProvince() {
		return addressProvince;
	}
	/**
	 * 设置：本人住址所在市
	 */
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	/**
	 * 获取：本人住址所在市
	 */
	public String getAddressCity() {
		return addressCity;
	}
	/**
	 * 设置：本人住址所在县
	 */
	public void setAddressCounty(String addressCounty) {
		this.addressCounty = addressCounty;
	}
	/**
	 * 获取：本人住址所在县
	 */
	public String getAddressCounty() {
		return addressCounty;
	}
	/**
	 * 设置：本人住址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：本人住址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：家庭特殊情况
	 */
	public void setSpecialFamilyCircumstances(String specialFamilyCircumstances) {
		this.specialFamilyCircumstances = specialFamilyCircumstances;
	}
	/**
	 * 获取：家庭特殊情况
	 */
	public String getSpecialFamilyCircumstances() {
		return specialFamilyCircumstances;
	}
	/**
	 * 设置：是否知青子女
	 */
	public void setEducatedChildren(Boolean educatedChildren) {
		this.educatedChildren = educatedChildren;
	}
	/**
	 * 获取：是否知青子女
	 */
	public Boolean getEducatedChildren() {
		return educatedChildren;
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
	 * 设置：血型
	 */
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	/**
	 * 获取：血型
	 */
	public String getBloodType() {
		return bloodType;
	}
	/**
	 * 设置：银行卡号
	 */
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	/**
	 * 获取：银行卡号
	 */
	public String getBankCard() {
		return bankCard;
	}
	/**
	 * 设置：邮递编号
	 */
	public void setPostalCodes(String postalCodes) {
		this.postalCodes = postalCodes;
	}
	/**
	 * 获取：邮递编号
	 */
	public String getPostalCodes() {
		return postalCodes;
	}
	/**
	 * 设置：港澳台侨
	 */
	public void setEmigrantcode(String emigrantcode) {
		this.emigrantcode = emigrantcode;
	}
	/**
	 * 获取：港澳台侨
	 */
	public String getEmigrantcode() {
		return emigrantcode;
	}
	/**
	 * 设置：户口性质
	 */
	public void setHouseholdRegistrationType(String householdRegistrationType) {
		this.householdRegistrationType = householdRegistrationType;
	}
	/**
	 * 获取：户口性质
	 */
	public String getHouseholdRegistrationType() {
		return householdRegistrationType;
	}
	/**
	 * 设置：特长
	 */
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	/**
	 * 获取：特长
	 */
	public String getSpeciality() {
		return speciality;
	}
	/**
	 * 设置：获奖情况
	 */
	public void setAward(String award) {
		this.award = award;
	}
	/**
	 * 获取：获奖情况
	 */
	public String getAward() {
		return award;
	}
	/**
	 * 设置：通讯地址
	 */
	public void setPostaladdress(String postaladdress) {
		this.postaladdress = postaladdress;
	}
	/**
	 * 获取：通讯地址
	 */
	public String getPostaladdress() {
		return postaladdress;
	}
	/**
	 * 设置：电子邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：电子邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：通讯编码
	 */
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	/**
	 * 获取：通讯编码
	 */
	public String getPostalcode() {
		return postalcode;
	}
	/**
	 * 设置：其它联系方式
	 */
	public void setOtherWay(String otherWay) {
		this.otherWay = otherWay;
	}
	/**
	 * 获取：其它联系方式
	 */
	public String getOtherWay() {
		return otherWay;
	}
	/**
	 * 设置：MSN号
	 */
	public void setMsn(String msn) {
		this.msn = msn;
	}
	/**
	 * 获取：MSN号
	 */
	public String getMsn() {
		return msn;
	}
	/**
	 * 设置：个人主页
	 */
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	/**
	 * 获取：个人主页
	 */
	public String getHomepage() {
		return homepage;
	}
	/**
	 * 设置：QQ号
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * 获取：QQ号
	 */
	public String getQq() {
		return qq;
	}
	/**
	 * 设置：家庭地址
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	/**
	 * 获取：家庭地址
	 */
	public String getHomeAddress() {
		return homeAddress;
	}
	/**
	 * 设置：家庭邮编
	 */
	public void setHomePostal(String homePostal) {
		this.homePostal = homePostal;
	}
	/**
	 * 获取：家庭邮编
	 */
	public String getHomePostal() {
		return homePostal;
	}
	/**
	 * 设置：家庭电话
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	/**
	 * 获取：家庭电话
	 */
	public String getHomePhone() {
		return homePhone;
	}
	/**
	 * 设置：家庭Email
	 */
	public void setFamilyEmail(String familyEmail) {
		this.familyEmail = familyEmail;
	}
	/**
	 * 获取：家庭Email
	 */
	public String getFamilyEmail() {
		return familyEmail;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getArrivalFlight() {
		return arrivalFlight;
	}

	public void setArrivalFlight(String arrivalFlight) {
		this.arrivalFlight = arrivalFlight;
	}

	public Integer getEntourage() {
		return entourage;
	}

	public void setEntourage(Integer entourage) {
		this.entourage = entourage;
	}

	public Boolean getSelfMadeCar() {
		return selfMadeCar;
	}

	public void setSelfMadeCar(Boolean selfMadeCar) {
		this.selfMadeCar = selfMadeCar;
	}

	public Boolean getNonResident() {
		return nonResident;
	}

	public void setNonResident(Boolean nonResident) {
		this.nonResident = nonResident;
	}

	public Boolean getBookLiving() {
		return bookLiving;
	}

	public void setBookLiving(Boolean bookLiving) {
		this.bookLiving = bookLiving;
	}

	/**
	 * 设置：学籍号
	 */
	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}
	/**
	 * 获取：学籍号
	 */
	public String getStudentStatus() {
		return studentStatus;
	}
	/**
	 * 设置：学生类别
	 */
	public void setStudentCategory(String studentCategory) {
		this.studentCategory = studentCategory;
	}
	/**
	 * 获取：学生类别
	 */
	public String getStudentCategory() {
		return studentCategory;
	}
	/**
	 * 设置：学生身份
	 */
	public void setStudentIdentity(String studentIdentity) {
		this.studentIdentity = studentIdentity;
	}
	/**
	 * 获取：学生身份
	 */
	public String getStudentIdentity() {
		return studentIdentity;
	}
	/**
	 * 设置：院系
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * 获取：院系
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * 设置：年级
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * 获取：年级
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * 设置：专业
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}
	/**
	 * 获取：专业
	 */
	public String getProfession() {
		return profession;
	}
	/**
	 * 设置：班级
	 */
	public void setStuClass(String stuClass) {
		this.stuClass = stuClass;
	}
	/**
	 * 获取：班级
	 */
	public String getStuClass() {
		return stuClass;
	}
	/**
	 * 设置：开户银行1
	 */
	public void setBankAccountF(String bankAccountF) {
		this.bankAccountF = bankAccountF;
	}
	/**
	 * 获取：开户银行1
	 */
	public String getBankAccountF() {
		return bankAccountF;
	}
	/**
	 * 设置：开户银行2
	 */
	public void setBankAccountS(String bankAccountS) {
		this.bankAccountS = bankAccountS;
	}
	/**
	 * 获取：开户银行2
	 */
	public String getBankAccountS() {
		return bankAccountS;
	}
	/**
	 * 设置：银行账号
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	/**
	 * 获取：银行账号
	 */
	public String getBankAccount() {
		return bankAccount;
	}
	/**
	 * 设置：报考省市
	 */
	public void setBkss(String bkss) {
		this.bkss = bkss;
	}
	/**
	 * 获取：报考省市
	 */
	public String getBkss() {
		return bkss;
	}
	/**
	 * 设置：入学前单位
	 */
	public void setRxqdw(String rxqdw) {
		this.rxqdw = rxqdw;
	}
	/**
	 * 获取：入学前单位
	 */
	public String getRxqdw() {
		return rxqdw;
	}
	/**
	 * 设置：入学日期
	 */
	public void setRxrq(Date rxrq) {
		this.rxrq = rxrq;
	}
	/**
	 * 获取：入学日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getRxrq() {
		return rxrq;
	}
	/**
	 * 设置：入学年级
	 */
	public void setRxnj(String rxnj) {
		this.rxnj = rxnj;
	}
	/**
	 * 获取：入学年级
	 */
	public String getRxnj() {
		return rxnj;
	}
	/**
	 * 设置：入学院系
	 */
	public void setRxyx(String rxyx) {
		this.rxyx = rxyx;
	}
	/**
	 * 获取：入学院系
	 */
	public String getRxyx() {
		return rxyx;
	}
	/**
	 * 设置：入学专业
	 */
	public void setRxzy(String rxzy) {
		this.rxzy = rxzy;
	}
	/**
	 * 获取：入学专业
	 */
	public String getRxzy() {
		return rxzy;
	}
	/**
	 * 设置：入学方式
	 */
	public void setRxfs(String rxfs) {
		this.rxfs = rxfs;
	}
	/**
	 * 获取：入学方式
	 */
	public String getRxfs() {
		return rxfs;
	}
	/**
	 * 设置：培养方式
	 */
	public void setPyfs(String pyfs) {
		this.pyfs = pyfs;
	}
	/**
	 * 获取：培养方式
	 */
	public String getPyfs() {
		return pyfs;
	}
	/**
	 * 设置：学生来源
	 */
	public void setXsly(String xsly) {
		this.xsly = xsly;
	}
	/**
	 * 获取：学生来源
	 */
	public String getXsly() {
		return xsly;
	}
	/**
	 * 设置：校区
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * 获取：校区
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * 设置：就读学位
	 */
	public void setJdxw(String jdxw) {
		this.jdxw = jdxw;
	}
	/**
	 * 获取：就读学位
	 */
	public String getJdxw() {
		return jdxw;
	}
	/**
	 * 设置：就读学历
	 */
	public void setJdxl(String jdxl) {
		this.jdxl = jdxl;
	}
	/**
	 * 获取：就读学历
	 */
	public String getJdxl() {
		return jdxl;
	}
	/**
	 * 设置：学制
	 */
	public void setXz(String xz) {
		this.xz = xz;
	}
	/**
	 * 获取：学制
	 */
	public String getXz() {
		return xz;
	}
	/**
	 * 设置：预计毕业年份
	 */
	public void setYjbynf(String yjbynf) {
		this.yjbynf = yjbynf;
	}
	/**
	 * 获取：预计毕业年份
	 */
	public String getYjbynf() {
		return yjbynf;
	}
	/**
	 * 设置：实际毕业时间
	 */
	public void setSjbysj(Date sjbysj) {
		this.sjbysj = sjbysj;
	}
	/**
	 * 获取：实际毕业时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getSjbysj() {
		return sjbysj;
	}
	/**
	 * 设置：手机号2
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号2
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：注册学年
	 */
	public void setZcxn(String zcxn) {
		this.zcxn = zcxn;
	}
	/**
	 * 获取：注册学年
	 */
	public String getZcxn() {
		return zcxn;
	}
	/**
	 * 设置：注册学期
	 */
	public void setZcxq(String zcxq) {
		this.zcxq = zcxq;
	}
	/**
	 * 获取：注册学期
	 */
	public String getZcxq() {
		return zcxq;
	}
	/**
	 * 设置：在校
	 */
	public void setZx(Boolean zx) {
		this.zx = zx;
	}

	/**
	 * 获取：在校
	 */
	public Boolean getZx() {
		return zx;
	}

	/**
	 * 设置：再籍
	 */
	public void setZj(Boolean zj) {
		this.zj = zj;
	}

	/**
	 * 获取：再籍
	 */
	public Boolean getZj() {
		return zj;
	}

	/**
	 * 设置：高考成绩
	 */
	public void setGkcj(Double gkcj) {
		this.gkcj = gkcj;
	}
	/**
	 * 获取：高考成绩
	 */
	public Double getGkcj() {
		return gkcj;
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
	/**
	 * 设置：军训信息
	 */
	public void setJxxx(String jxxx) {
		this.jxxx = jxxx;
	}
	/**
	 * 获取：军训信息
	 */
	public String getJxxx() {
		return jxxx;
	}
	/**
	 * 设置：毕业类型
	 */
	public void setBylx(String bylx) {
		this.bylx = bylx;
	}
	/**
	 * 获取：毕业类型
	 */
	public String getBylx() {
		return bylx;
	}
	/**
	 * 设置：毕业证书编号
	 */
	public void setByzsbh(String byzsbh) {
		this.byzsbh = byzsbh;
	}
	/**
	 * 获取：毕业证书编号
	 */
	public String getByzsbh() {
		return byzsbh;
	}
	/**
	 * 设置：考区
	 */
	public void setKq(String kq) {
		this.kq = kq;
	}
	/**
	 * 获取：考区
	 */
	public String getKq() {
		return kq;
	}

	public String getSex_code() {
		return sex_code;
	}

	public void setSex_code(String sex_code) {
		this.sex_code = sex_code;
	}

	public String getCertificate_code() {
		return certificate_code;
	}

	public void setCertificate_code(String certificate_code) {
		this.certificate_code = certificate_code;
	}

	public String getBlood_code() {
		return blood_code;
	}

	public void setBlood_code(String blood_code) {
		this.blood_code = blood_code;
	}

	public String getPolitical_code() {
		return political_code;
	}

	public void setPolitical_code(String political_code) {
		this.political_code = political_code;
	}

	public String getHealth_code() {
		return health_code;
	}

	public void setHealth_code(String health_code) {
		this.health_code = health_code;
	}

	public String getMarital_code() {
		return marital_code;
	}

	public void setMarital_code(String marital_code) {
		this.marital_code = marital_code;
	}

	public String getNation_code() {
		return nation_code;
	}

	public void setNation_code(String nation_code) {
		this.nation_code = nation_code;
	}

	public String getSourceCountry_code() {
		return sourceCountry_code;
	}

	public void setSourceCountry_code(String sourceCountry_code) {
		this.sourceCountry_code = sourceCountry_code;
	}

	public String getEmigrantcode_code() {
		return emigrantcode_code;
	}

	public void setEmigrantcode_code(String emigrantcode_code) {
		this.emigrantcode_code = emigrantcode_code;
	}

	public String getHouseholdRegistrationType_code() {
		return householdRegistrationType_code;
	}

	public void setHouseholdRegistrationType_code(String householdRegistrationType_code) {
		this.householdRegistrationType_code = householdRegistrationType_code;
	}

	public String getStudent_type_code() {
		return student_type_code;
	}

	public void setStudent_type_code(String student_type_code) {
		this.student_type_code = student_type_code;
	}

	public String getStudent_identity_code() {
		return student_identity_code;
	}

	public void setStudent_identity_code(String student_identity_code) {
		this.student_identity_code = student_identity_code;
	}

	public String getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}

	public String getProfession_code() {
		return profession_code;
	}

	public void setProfession_code(String profession_code) {
		this.profession_code = profession_code;
	}

	public String getClass_code() {
		return class_code;
	}

	public void setClass_code(String class_code) {
		this.class_code = class_code;
	}

	public String getRxyx_code() {
		return rxyx_code;
	}

	public void setRxyx_code(String rxyx_code) {
		this.rxyx_code = rxyx_code;
	}

	public String getRxzy_code() {
		return rxzy_code;
	}

	public void setRxzy_code(String rxzy_code) {
		this.rxzy_code = rxzy_code;
	}

	public String getRxfs_code() {
		return rxfs_code;
	}

	public void setRxfs_code(String rxfs_code) {
		this.rxfs_code = rxfs_code;
	}

	public String getPyfs_code() {
		return pyfs_code;
	}

	public void setPyfs_code(String pyfs_code) {
		this.pyfs_code = pyfs_code;
	}

	public String getXq_code() {
		return xq_code;
	}

	public void setXq_code(String xq_code) {
		this.xq_code = xq_code;
	}

	public String getJdxw_code() {
		return jdxw_code;
	}

	public void setJdxw_code(String jdxw_code) {
		this.jdxw_code = jdxw_code;
	}

	public String getJdxl_code() {
		return jdxl_code;
	}

	public void setJdxl_code(String jdxl_code) {
		this.jdxl_code = jdxl_code;
	}

	public String getZcxn_code() {
		return zcxn_code;
	}

	public void setZcxn_code(String zcxn_code) {
		this.zcxn_code = zcxn_code;
	}

	public String getZcxq_code() {
		return zcxq_code;
	}

	public void setZcxq_code(String zcxq_code) {
		this.zcxq_code = zcxq_code;
	}

	public String getBylx_code() {
		return bylx_code;
	}

	public void setBylx_code(String bylx_code) {
		this.bylx_code = bylx_code;
	}

	public String getZj_code() {
		return zj_code;
	}

	public void setZj_code(String zj_code) {
		this.zj_code = zj_code;
	}

	public String getZx_code() {
		return zx_code;
	}

	public void setZx_code(String zx_code) {
		this.zx_code = zx_code;
	}
}
