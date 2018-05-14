package com.kurumi.pojo.coding;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kurumi.pojo.regionalism.Regionalism;

public class BasicInfo {

	/**
	 * visitGuid
	 */
	private String visitGuid;

	/**
	 * 患者名
	 */
	private String patientName;
	
	
	/**
	 * 性别编号
	 */
	private String sexCode;
	
	/**
	 * 性别名称
	 * 
	 */
	private String sexName;
	
	/**
	 * 出生日期
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	/**
	 * 年龄（年）
	 */
	private BigDecimal age;
	
	
	/**
	 * 年龄(月)
	 */
	private String monthOfAge;
	
	/**
	 * 年龄(周)
	 */
	private String weekOfAge;
	
	/**
	 * 年龄(天)
	 */
	private String dayOfAge;
	
	/**
     * 婚姻状况编号
     */
    private String marriageCode;
    
    /**
     * 婚姻状况名称
     * 
     */
    private String marriageName;
    
    /**
     * 职业编号
     */
    private String professionCode;
    
    /**
     * 职业名称
     * 
     */
    private String professionName;
    
    
    /**
     *	出生地（户口）编号
     */
    private String birthAddressCode;
    
    /**
     * 出生地（户口）
     * 
     */
    private String birthAddress;
    
    /**
     * 出生地（户口）行政划分
     */
    private Regionalism birthRegionalism = new Regionalism();
    
    /**
     * 出生地（户口）
     * 
     */
    private String birthAddressPostCode;
    
    
    /**
     * 籍贯编号
     */
    private String nativePlaceCode;
    
    /**
     * 籍贯
     */
    private String nativePlace;
    
    /**
     * 籍贯行政划分
     */
    private Regionalism nativePlaceRegionalism = new Regionalism();
	
    
    /**
     * 电话号码
     */
    private String telePhone;
    
    /**
	 * 常住地地址编号
	 */
	private String permanentAddressCode;
	
	/**
	 * 常住地地址
	 */
	private String permanentAddress;
	
	/**
     * 常住地行政划分
     */
    private Regionalism permanentAddressRegionalism = new Regionalism();
	
	/**
	 * 常住地电话
	 */
	private String permanentAddressPhone;
	
	/**
	 * 常住地移动电话
	 */
	private String permanentAddressMobilePhone;
	
	/**
	 * 常住地邮编
	 */
	private String permanentAddressPostCode;
    
    /**
     * 民族编号
     */
    private String nationCode;
    
    /**
     * 民族名称
     * 
     */
    private String nationName;
    
    /**
     * 国籍编号
     */
    private String nationalityCode;
    
    /**
     * 国籍名称
     * 
     */
    private String nationalityName;
    
    
	/**
	 * 证件类型编号
	 * 
	 */
	private String idDocumentTypeCode;
	
	/**
	 * 证件类型名称
	 * 
	 */
	private String idDocumentTypeName;
	
	/**
	 * 患者证件号
	 */
	private String idNumber;
	
	/**
	 * 医院编号
	 */
	private String hospitalCode;
	
	/**
	 * 医院名称
	 */
	private String hospitalName;
	
	/**
	 * 病案号
	 */
	private String mrId;
	
	/**
	 * 住院号
	 */
	private String onlyId;
	
	/**
	 * 住院次数
	 */
	private BigDecimal visitNumber;
	
	/**
	 * 医疗付费方式编号
	 */
	private String medicalPayTypeCode;
	
	/**
	 * 医疗付费方式名称
	 */
	private String medicalPayTypeName;
	
	/**
	 * 医疗保险（社会保险）账号
	 */
	private String medicalInsuranceNumber;
	
	/**
	 * 健康卡号
	 */
	private String medicalHealthNumber;
	
	
	/**
	 * 工作单位名称
	 */
	private String workUnitName;
	
	/**
	 * 工作单位地址编号
	 */
	private String workUnitAddressCode;
	
	/**
	 * 工作单位地址
	 */
	private String workUnitAddress;
	
	/**
     * 工作单位行政划分
     */
    private Regionalism workUnitRegionalism = new Regionalism();
	
	/**
	 * 工作单位电话
	 */
	private String workUnitPhone;
	
	/**
	 * 工作单位邮编
	 */
	private String workUnitPostCode;
	
	
	
	/**
	 * 联系人名称
	 */
	private String linkManName;
	
	/**
	 * 联系人关系编号
	 */
	private String linkManRelativeRelationCode;
	
	/**
	 * 联系人关系名称
	 */
	private String linkManRelativeRelationName;
	
	/**
	 * 联系人电话
	 */
	private String linkManPhone;
	
	/**
	 * 联系人地址编号
	 */
	private String linkManAddressCode;
	
	/**
	 * 联系人地址
	 */
	private String linkManAddress;
	
	/**
     * 工作单位行政划分
     */
    private Regionalism linkManAddressRegionalism = new Regionalism();
	
	
	/**
	 * 联系人地址邮编
	 */
	private String linkManAddressPostCode;
	
	/**
	 * 入院方式编号
	 */
	private String inHospitalTypeCode;
	
	/**
	 * 入院方式名称
	 */
	private String inHospitalTypeName;
	
	/**
	 * 入院状况编号
	 */
	private String inHospitalStateCode;
	
	/**
	 * 入院状况名称
	 */
	private String inHospitalStateName;
	
	 /**
     * 接诊时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date clinicalReceptionDateTime;
    
    /**
     * 门诊医师编号
     */
    private String outpatientOfDoctorCode;
    
    /**
     * 门诊医师名称
     */
    private String outpatientOfDoctorName;
    
	
    /**
     * 经办人编号
     */
    private String responsiblePersonCode;
    
    /**
     * 经办人名称
     */
    private String responsiblePersonName;
    
    /**
     * 入院时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inHospitalDateTime;
    
    /**
     * 入院科室编号
     */
    private String inDeptCode;
    
    /**
     * 入院科室名称
     */
    private String inDeptName;
    
    /**
     * 出院时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date outHospitalDateTime;
    
    /**
     * 出院科室编号
     */
    private String outDeptCode;
    
    /**
     * 出院科室名称
     */
    private String outDeptName;
    
    /**
	 * 出院方式编号
	 */
	private String outHospitalTypeCode;
	
	/**
	 * 出院方式名称
	 */
	private String outHospitalTypeName;
    
    /**
     *诊断确诊时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date diagConfirmedDateTime;
    
    /**
     * 诊断确诊天数
     */
    private BigDecimal diagConfirmedDayNumber;
    
    /**
     * 住院天数
     */
    private BigDecimal inHospitalDayNumber;
    
    
    /**
     * 转入机构编号
     */
    private String shiftToUnitCode;
    
    /**
     * 转入机构名称
     * 
     */
    private String shiftToUnitName;
    
    /**
   	 * 颅脑损伤昏迷时间
   	 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date craniocerebralInjuryAndComaDateTime;
    
    /**
     *诊疗科目编号
     */
    private String diagSubjectCode;
    
    /**
     *诊疗科目名称
     */
    private String diagSubjectName;
    
    /**
     * 拟接收机构编号
     */
    private String receiveUnitCode;
    
    /**
     * 拟接收机构名称
     * 
     */
    private String receiveUnitName;
    
    /**
     * 再住院计划编号
     */
    private String rehospitalAimCode;
    
    /**
     * 再住院计划名称
     */
    private String rehospitalAimName;
    
    
    /**
     * 再住院计划间隔天数
     */
    private BigDecimal rehospitalIntervalDayNumber;
    
    /**
     * 死亡日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
   	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dealthDateTime;
    
   
    /**
     * 死亡原因编码
     */
    private String dealthReasonCode;
    
    /**
     * 死亡原因名称
     */
    private String dealthReasonName;
    
    /**
     * 是否尸检编号
     */
    private String autopsyCode;
    
    /**
     * 是否尸检名称
     */
    private String autopsyName;
	
    /**
     * 入院病区名称
     */
    private String inHospitalWardName;
    
    /**
     * 入院病房名称
     */
    private String inHospitalHouseName;
    
    /**
     * 入院床位名称
     */
    private String inHospitalBedName;
    
    
    /**
     * 出院病区名称
     */
    private String outHospitalWardName;
    
    /**
     * 出院病房名称
     */
    private String outHospitalHouseName;
    
    /**
     * 出院床位名称
     */
    private String outHospitalBedName;

	public String getVisitGuid() {
		return visitGuid;
	}

	public void setVisitGuid(String visitGuid) {
		this.visitGuid = visitGuid;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public BigDecimal getAge() {
		return age;
	}

	public void setAge(BigDecimal age) {
		this.age = age;
	}

	

	public String getMonthOfAge() {
		return monthOfAge;
	}

	public void setMonthOfAge(String monthOfAge) {
		this.monthOfAge = monthOfAge;
	}

	public String getWeekOfAge() {
		return weekOfAge;
	}

	public void setWeekOfAge(String weekOfAge) {
		this.weekOfAge = weekOfAge;
	}

	public String getDayOfAge() {
		return dayOfAge;
	}

	public void setDayOfAge(String dayOfAge) {
		this.dayOfAge = dayOfAge;
	}

	public String getMarriageCode() {
		return marriageCode;
	}

	public void setMarriageCode(String marriageCode) {
		this.marriageCode = marriageCode;
	}

	public String getMarriageName() {
		return marriageName;
	}

	public void setMarriageName(String marriageName) {
		this.marriageName = marriageName;
	}

	public String getProfessionCode() {
		return professionCode;
	}

	public void setProfessionCode(String professionCode) {
		this.professionCode = professionCode;
	}

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	public String getBirthAddressCode() {
		return birthAddressCode;
	}

	public void setBirthAddressCode(String birthAddressCode) {
		this.birthAddressCode = birthAddressCode;
	}

	public String getBirthAddress() {
		return birthAddress;
	}

	public void setBirthAddress(String birthAddress) {
		this.birthAddress = birthAddress;
	}

	public Regionalism getBirthRegionalism() {
		return birthRegionalism;
	}

	public void setBirthRegionalism(Regionalism birthRegionalism) {
		this.birthRegionalism = birthRegionalism;
	}

	public String getBirthAddressPostCode() {
		return birthAddressPostCode;
	}

	public void setBirthAddressPostCode(String birthAddressPostCode) {
		this.birthAddressPostCode = birthAddressPostCode;
	}

	public String getNativePlaceCode() {
		return nativePlaceCode;
	}

	public void setNativePlaceCode(String nativePlaceCode) {
		this.nativePlaceCode = nativePlaceCode;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public Regionalism getNativePlaceRegionalism() {
		return nativePlaceRegionalism;
	}

	public void setNativePlaceRegionalism(Regionalism nativePlaceRegionalism) {
		this.nativePlaceRegionalism = nativePlaceRegionalism;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getPermanentAddressCode() {
		return permanentAddressCode;
	}

	public void setPermanentAddressCode(String permanentAddressCode) {
		this.permanentAddressCode = permanentAddressCode;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public Regionalism getPermanentAddressRegionalism() {
		return permanentAddressRegionalism;
	}

	public void setPermanentAddressRegionalism(Regionalism permanentAddressRegionalism) {
		this.permanentAddressRegionalism = permanentAddressRegionalism;
	}

	public String getPermanentAddressPhone() {
		return permanentAddressPhone;
	}

	public void setPermanentAddressPhone(String permanentAddressPhone) {
		this.permanentAddressPhone = permanentAddressPhone;
	}

	public String getPermanentAddressMobilePhone() {
		return permanentAddressMobilePhone;
	}

	public void setPermanentAddressMobilePhone(String permanentAddressMobilePhone) {
		this.permanentAddressMobilePhone = permanentAddressMobilePhone;
	}

	public String getPermanentAddressPostCode() {
		return permanentAddressPostCode;
	}

	public void setPermanentAddressPostCode(String permanentAddressPostCode) {
		this.permanentAddressPostCode = permanentAddressPostCode;
	}

	public String getNationCode() {
		return nationCode;
	}

	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}

	public String getNationName() {
		return nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
	}

	public String getNationalityCode() {
		return nationalityCode;
	}

	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}

	public String getNationalityName() {
		return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

	public String getIdDocumentTypeCode() {
		return idDocumentTypeCode;
	}

	public void setIdDocumentTypeCode(String idDocumentTypeCode) {
		this.idDocumentTypeCode = idDocumentTypeCode;
	}

	public String getIdDocumentTypeName() {
		return idDocumentTypeName;
	}

	public void setIdDocumentTypeName(String idDocumentTypeName) {
		this.idDocumentTypeName = idDocumentTypeName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getMrId() {
		return mrId;
	}

	public void setMrId(String mrId) {
		this.mrId = mrId;
	}

	public String getOnlyId() {
		return onlyId;
	}

	public void setOnlyId(String onlyId) {
		this.onlyId = onlyId;
	}

	public BigDecimal getVisitNumber() {
		return visitNumber;
	}

	public void setVisitNumber(BigDecimal visitNumber) {
		this.visitNumber = visitNumber;
	}

	public String getMedicalPayTypeCode() {
		return medicalPayTypeCode;
	}

	public void setMedicalPayTypeCode(String medicalPayTypeCode) {
		this.medicalPayTypeCode = medicalPayTypeCode;
	}

	public String getMedicalPayTypeName() {
		return medicalPayTypeName;
	}

	public void setMedicalPayTypeName(String medicalPayTypeName) {
		this.medicalPayTypeName = medicalPayTypeName;
	}

	public String getMedicalInsuranceNumber() {
		return medicalInsuranceNumber;
	}

	public void setMedicalInsuranceNumber(String medicalInsuranceNumber) {
		this.medicalInsuranceNumber = medicalInsuranceNumber;
	}

	public String getMedicalHealthNumber() {
		return medicalHealthNumber;
	}

	public void setMedicalHealthNumber(String medicalHealthNumber) {
		this.medicalHealthNumber = medicalHealthNumber;
	}

	public String getWorkUnitName() {
		return workUnitName;
	}

	public void setWorkUnitName(String workUnitName) {
		this.workUnitName = workUnitName;
	}

	public String getWorkUnitAddressCode() {
		return workUnitAddressCode;
	}

	public void setWorkUnitAddressCode(String workUnitAddressCode) {
		this.workUnitAddressCode = workUnitAddressCode;
	}

	public String getWorkUnitAddress() {
		return workUnitAddress;
	}

	public void setWorkUnitAddress(String workUnitAddress) {
		this.workUnitAddress = workUnitAddress;
	}

	public Regionalism getWorkUnitRegionalism() {
		return workUnitRegionalism;
	}

	public void setWorkUnitRegionalism(Regionalism workUnitRegionalism) {
		this.workUnitRegionalism = workUnitRegionalism;
	}

	public String getWorkUnitPhone() {
		return workUnitPhone;
	}

	public void setWorkUnitPhone(String workUnitPhone) {
		this.workUnitPhone = workUnitPhone;
	}

	public String getWorkUnitPostCode() {
		return workUnitPostCode;
	}

	public void setWorkUnitPostCode(String workUnitPostCode) {
		this.workUnitPostCode = workUnitPostCode;
	}

	public String getLinkManName() {
		return linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	public String getLinkManRelativeRelationCode() {
		return linkManRelativeRelationCode;
	}

	public void setLinkManRelativeRelationCode(String linkManRelativeRelationCode) {
		this.linkManRelativeRelationCode = linkManRelativeRelationCode;
	}

	public String getLinkManRelativeRelationName() {
		return linkManRelativeRelationName;
	}

	public void setLinkManRelativeRelationName(String linkManRelativeRelationName) {
		this.linkManRelativeRelationName = linkManRelativeRelationName;
	}

	public String getLinkManPhone() {
		return linkManPhone;
	}

	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}

	public String getLinkManAddressCode() {
		return linkManAddressCode;
	}

	public void setLinkManAddressCode(String linkManAddressCode) {
		this.linkManAddressCode = linkManAddressCode;
	}

	public String getLinkManAddress() {
		return linkManAddress;
	}

	public void setLinkManAddress(String linkManAddress) {
		this.linkManAddress = linkManAddress;
	}

	public Regionalism getLinkManAddressRegionalism() {
		return linkManAddressRegionalism;
	}

	public void setLinkManAddressRegionalism(Regionalism linkManAddressRegionalism) {
		this.linkManAddressRegionalism = linkManAddressRegionalism;
	}

	public String getLinkManAddressPostCode() {
		return linkManAddressPostCode;
	}

	public void setLinkManAddressPostCode(String linkManAddressPostCode) {
		this.linkManAddressPostCode = linkManAddressPostCode;
	}

	public String getInHospitalTypeCode() {
		return inHospitalTypeCode;
	}

	public void setInHospitalTypeCode(String inHospitalTypeCode) {
		this.inHospitalTypeCode = inHospitalTypeCode;
	}

	public String getInHospitalTypeName() {
		return inHospitalTypeName;
	}

	public void setInHospitalTypeName(String inHospitalTypeName) {
		this.inHospitalTypeName = inHospitalTypeName;
	}

	public String getInHospitalStateCode() {
		return inHospitalStateCode;
	}

	public void setInHospitalStateCode(String inHospitalStateCode) {
		this.inHospitalStateCode = inHospitalStateCode;
	}

	public String getInHospitalStateName() {
		return inHospitalStateName;
	}

	public void setInHospitalStateName(String inHospitalStateName) {
		this.inHospitalStateName = inHospitalStateName;
	}

	public Date getClinicalReceptionDateTime() {
		return clinicalReceptionDateTime;
	}

	public void setClinicalReceptionDateTime(Date clinicalReceptionDateTime) {
		this.clinicalReceptionDateTime = clinicalReceptionDateTime;
	}

	public String getOutpatientOfDoctorCode() {
		return outpatientOfDoctorCode;
	}

	public void setOutpatientOfDoctorCode(String outpatientOfDoctorCode) {
		this.outpatientOfDoctorCode = outpatientOfDoctorCode;
	}

	public String getOutpatientOfDoctorName() {
		return outpatientOfDoctorName;
	}

	public void setOutpatientOfDoctorName(String outpatientOfDoctorName) {
		this.outpatientOfDoctorName = outpatientOfDoctorName;
	}

	public String getResponsiblePersonCode() {
		return responsiblePersonCode;
	}

	public void setResponsiblePersonCode(String responsiblePersonCode) {
		this.responsiblePersonCode = responsiblePersonCode;
	}

	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}

	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}

	public Date getInHospitalDateTime() {
		return inHospitalDateTime;
	}

	public void setInHospitalDateTime(Date inHospitalDateTime) {
		this.inHospitalDateTime = inHospitalDateTime;
	}

	public String getInDeptCode() {
		return inDeptCode;
	}

	public void setInDeptCode(String inDeptCode) {
		this.inDeptCode = inDeptCode;
	}

	public String getInDeptName() {
		return inDeptName;
	}

	public void setInDeptName(String inDeptName) {
		this.inDeptName = inDeptName;
	}

	public Date getOutHospitalDateTime() {
		return outHospitalDateTime;
	}

	public void setOutHospitalDateTime(Date outHospitalDateTime) {
		this.outHospitalDateTime = outHospitalDateTime;
	}

	public String getOutDeptCode() {
		return outDeptCode;
	}

	public void setOutDeptCode(String outDeptCode) {
		this.outDeptCode = outDeptCode;
	}

	public String getOutDeptName() {
		return outDeptName;
	}

	public void setOutDeptName(String outDeptName) {
		this.outDeptName = outDeptName;
	}

	public String getOutHospitalTypeCode() {
		return outHospitalTypeCode;
	}

	public void setOutHospitalTypeCode(String outHospitalTypeCode) {
		this.outHospitalTypeCode = outHospitalTypeCode;
	}

	public String getOutHospitalTypeName() {
		return outHospitalTypeName;
	}

	public void setOutHospitalTypeName(String outHospitalTypeName) {
		this.outHospitalTypeName = outHospitalTypeName;
	}

	public Date getDiagConfirmedDateTime() {
		return diagConfirmedDateTime;
	}

	public void setDiagConfirmedDateTime(Date diagConfirmedDateTime) {
		this.diagConfirmedDateTime = diagConfirmedDateTime;
	}

	public BigDecimal getDiagConfirmedDayNumber() {
		return diagConfirmedDayNumber;
	}

	public void setDiagConfirmedDayNumber(BigDecimal diagConfirmedDayNumber) {
		this.diagConfirmedDayNumber = diagConfirmedDayNumber;
	}

	public BigDecimal getInHospitalDayNumber() {
		return inHospitalDayNumber;
	}

	public void setInHospitalDayNumber(BigDecimal inHospitalDayNumber) {
		this.inHospitalDayNumber = inHospitalDayNumber;
	}

	public String getShiftToUnitCode() {
		return shiftToUnitCode;
	}

	public void setShiftToUnitCode(String shiftToUnitCode) {
		this.shiftToUnitCode = shiftToUnitCode;
	}

	public String getShiftToUnitName() {
		return shiftToUnitName;
	}

	public void setShiftToUnitName(String shiftToUnitName) {
		this.shiftToUnitName = shiftToUnitName;
	}

	public Date getCraniocerebralInjuryAndComaDateTime() {
		return craniocerebralInjuryAndComaDateTime;
	}

	public void setCraniocerebralInjuryAndComaDateTime(Date craniocerebralInjuryAndComaDateTime) {
		this.craniocerebralInjuryAndComaDateTime = craniocerebralInjuryAndComaDateTime;
	}

	public String getDiagSubjectCode() {
		return diagSubjectCode;
	}

	public void setDiagSubjectCode(String diagSubjectCode) {
		this.diagSubjectCode = diagSubjectCode;
	}

	public String getDiagSubjectName() {
		return diagSubjectName;
	}

	public void setDiagSubjectName(String diagSubjectName) {
		this.diagSubjectName = diagSubjectName;
	}

	public String getReceiveUnitCode() {
		return receiveUnitCode;
	}

	public void setReceiveUnitCode(String receiveUnitCode) {
		this.receiveUnitCode = receiveUnitCode;
	}

	public String getReceiveUnitName() {
		return receiveUnitName;
	}

	public void setReceiveUnitName(String receiveUnitName) {
		this.receiveUnitName = receiveUnitName;
	}

	public String getRehospitalAimCode() {
		return rehospitalAimCode;
	}

	public void setRehospitalAimCode(String rehospitalAimCode) {
		this.rehospitalAimCode = rehospitalAimCode;
	}

	public String getRehospitalAimName() {
		return rehospitalAimName;
	}

	public void setRehospitalAimName(String rehospitalAimName) {
		this.rehospitalAimName = rehospitalAimName;
	}

	public BigDecimal getRehospitalIntervalDayNumber() {
		return rehospitalIntervalDayNumber;
	}

	public void setRehospitalIntervalDayNumber(BigDecimal rehospitalIntervalDayNumber) {
		this.rehospitalIntervalDayNumber = rehospitalIntervalDayNumber;
	}

	public Date getDealthDateTime() {
		return dealthDateTime;
	}

	public void setDealthDateTime(Date dealthDateTime) {
		this.dealthDateTime = dealthDateTime;
	}

	public String getDealthReasonCode() {
		return dealthReasonCode;
	}

	public void setDealthReasonCode(String dealthReasonCode) {
		this.dealthReasonCode = dealthReasonCode;
	}

	public String getDealthReasonName() {
		return dealthReasonName;
	}

	public void setDealthReasonName(String dealthReasonName) {
		this.dealthReasonName = dealthReasonName;
	}

	public String getAutopsyCode() {
		return autopsyCode;
	}

	public void setAutopsyCode(String autopsyCode) {
		this.autopsyCode = autopsyCode;
	}

	public String getAutopsyName() {
		return autopsyName;
	}

	public void setAutopsyName(String autopsyName) {
		this.autopsyName = autopsyName;
	}

	public String getInHospitalWardName() {
		return inHospitalWardName;
	}

	public void setInHospitalWardName(String inHospitalWardName) {
		this.inHospitalWardName = inHospitalWardName;
	}

	public String getInHospitalHouseName() {
		return inHospitalHouseName;
	}

	public void setInHospitalHouseName(String inHospitalHouseName) {
		this.inHospitalHouseName = inHospitalHouseName;
	}

	public String getInHospitalBedName() {
		return inHospitalBedName;
	}

	public void setInHospitalBedName(String inHospitalBedName) {
		this.inHospitalBedName = inHospitalBedName;
	}

	public String getOutHospitalWardName() {
		return outHospitalWardName;
	}

	public void setOutHospitalWardName(String outHospitalWardName) {
		this.outHospitalWardName = outHospitalWardName;
	}

	public String getOutHospitalHouseName() {
		return outHospitalHouseName;
	}

	public void setOutHospitalHouseName(String outHospitalHouseName) {
		this.outHospitalHouseName = outHospitalHouseName;
	}

	public String getOutHospitalBedName() {
		return outHospitalBedName;
	}

	public void setOutHospitalBedName(String outHospitalBedName) {
		this.outHospitalBedName = outHospitalBedName;
	}
    
	
    
}
