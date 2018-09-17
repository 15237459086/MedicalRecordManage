package com.kurumi.pojo.coding;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 治疗信息
 * @author lyh
 *
 */
public class CureInfo {

	/**
	 * visitGuid
	 */
	private String visitGuid;
	
	
	/**
	 * ABO血型编号
	 */
	private String bloodTypeCode;
	
	/**
	 * ABO血型名称
	 */
	private String bloodTypeName;
	
	/**
	 * RH血型编号
	 */
	private String rhBloodTypeCode;
	
	/**
	 * RH血型名称
	 */
	private String rhBloodTypeName;
	
	/**
	 * 抢救次数
	 */
	private Integer rescueNumber;
	
	/**
	 * 抢救成功次数
	 */
	private Integer rescueSucceedNumber;
	
	/**
	 * 院内会诊数
	 */
	private Integer inConsultationNumber;
	
	/**
	 * 外院会诊数
	 */
	private Integer outConsultationNumber;
	
	/**
	 * 手术、治疗、检查、诊断为本院第一例编号
	 */
	private String isFirstCaseCode;
	
	/**
	 * 手术、治疗、检查、诊断为本院第一例名称
	 */
	private String isFirstCaseName;
	
	/**
	 * 示教病例编号
	 */
	private String isTeachingCaseCode;
	
	/**
	 * 示教病例名称
	 */
	private String isTeachingCaseName;
	
	/**
	 * 输液次数
	 */
	private Integer infusionTimes;
	
	/**
	 * 输液反应次数
	 */
	private Integer infusionReactTimes;
	
	/**
	 * HBsAg 结果编号
	 */
	private String hbsAgeResultCode;
	
	/**
	 * HBsAg 结果名称
	 */
	private String hbsAgeResultName;
	
	/**
	 * HCV-Ab 结果编号
	 */
	private String hcvAbResultCode;
	
	/**
	 * HCV-Ab 结果名称
	 */
	private String hcvAbResultName;
	
	/**
	 * HIV-Ab 结果编号
	 */
	private String hivAbResultCode;
	
	/**
	 * HIV-Ab 结果名称
	 */
	private String hivAbResultName;
	
	
	 /**
     * 出院31天内再住院计划编号
     */
    private String rehospitalAimOf31Code;
    
    /**
     *  出院31天内再住院计划名称
     */
    private String rehospitalAimOf31Name;
    
    /**
     *  出院31天内再住院计划目的描述
     */
    private String rehospitalAimOf31Description;
    
    /**
   	 * 入院前颅脑损伤昏迷时间描述
   	 */
    private String beforeInHospitalComaTimeDesc;
    
    /**
     * 入院前颅脑损伤昏迷时间(天)
     */
    private BigDecimal dayOfBeforeInHospitalComa;
    
    /**
     * 入院前颅脑损伤昏迷时间(时)
     */
    private BigDecimal hourOfBeforeInHospitalComa;
    
    /**
     * 入院前颅脑损伤昏迷时间(分)
     */
    private BigDecimal minuteOfBeforeInHospitalComa;
    
    /**
   	 * 入院后颅脑损伤昏迷时间描述
   	 */
    private String afterInHospitalComaTimeDesc;
    
    /**
   	 * 入院后颅脑损伤昏迷时间(天)
   	 */
    private BigDecimal dayOfAfterInHospitalComa;
    
    /**
   	 * 入院后颅脑损伤昏迷时间(时)
   	 */
    private BigDecimal hourOfAfterInHospitalComa;
    
    /**
   	 * 入院后颅脑损伤昏迷时间(分)
   	 */
    private BigDecimal minuteOfAfterInHospitalComa;
    
    /**
     * 恶性肿瘤入院确诊日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date malignantTumorConfirmedDateTime;
    
    /**
     * 肿瘤分级
     */
    private String tumorGrade;
    
    /**
     * 肿瘤分期T
     */
    private String tumorStagingOfT;
    
    /**
     * 肿瘤分期N
     */
    private String tumorStagingOfN;
    
    /**
     * 肿瘤分期M
     */
    private String tumorStagingOfM;
    
    /**
     * 恶性肿瘤最初治疗方式编号
     */
    private String malignantTumorFirstCureTypeCode;
    
    /**
     * 恶性肿瘤最初治疗方式名称
     */
    private String malignantTumorFirstCureTypeName;
    
    /**
     * 恶性肿瘤最高诊断依据编号
     */
    private String malignantTumorHighestDiagBasisCode;
    
    /**
     * 恶性肿瘤最高诊断依据名称
     */
    private String malignantTumorHighestDiagBasisName;
    
	/**
	 * 是否随诊期限编码
	 */
	private String followUpClinicLimitCode;
	
	/**
	 * 是否随诊期限名称
	 */
	private String followUpClinicLimitName;
	
	/**
	 * 随诊期限天数
	 */
	private BigDecimal followUpClinicDayNumber;
	
	/**
	 * 随诊期限zho数
	 */
	private BigDecimal followUpClinicWeekNumber;
	
	/**
	 * 随诊期限天数
	 */
	private BigDecimal followUpClinicMonthNumber;
	
	/**
	 * 随诊期限天数
	 */
	private BigDecimal followUpClinicYearNumber;
	
	/**
	 * 随诊期限单位编号
	 */
	private String followUpClinicUnitCode;
	
	/**
	 * 随诊期限单位名称
	 */
	private String followUpClinicUnitName;
	
	
	/**
	 * 随诊期限数
	 */
	private BigDecimal followInterval;
	
	
	/**
	 * 诊断符合集合
	 */
	private List<DiagAccordRecord> diagAccordRecords = new ArrayList<DiagAccordRecord>();

	/**
	 * 治疗医师集合
	 */
	private List<MedicalWorker> cureWorkers = new ArrayList<MedicalWorker>();
	
	/**
	 * 病案质量代码
	 */
	private String medicalRecordQualityCode;
	
	/**
	 * 病案质量代码
	 */
	private String medicalRecordQualityName;
	
	/**
     * 质控日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date qualityControlDateTime;
    
    /**
     * 新生儿出生体重（g）
     */
    private BigDecimal babyBirthWeight;
    
    /**
     * 新生儿入院体重（g）
     */
    private BigDecimal babyInHospitalWeight;
    
    /**
     * 编目完成日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date finishCatalogDateTime;
	
	public String getVisitGuid() {
		return visitGuid;
	}

	public void setVisitGuid(String visitGuid) {
		this.visitGuid = visitGuid;
	}

	public Integer getRescueNumber() {
		return rescueNumber;
	}

	public void setRescueNumber(Integer rescueNumber) {
		this.rescueNumber = rescueNumber;
	}

	public Integer getRescueSucceedNumber() {
		return rescueSucceedNumber;
	}

	public void setRescueSucceedNumber(Integer rescueSucceedNumber) {
		this.rescueSucceedNumber = rescueSucceedNumber;
	}

	public Integer getInConsultationNumber() {
		return inConsultationNumber;
	}

	public void setInConsultationNumber(Integer inConsultationNumber) {
		this.inConsultationNumber = inConsultationNumber;
	}

	public Integer getOutConsultationNumber() {
		return outConsultationNumber;
	}

	public void setOutConsultationNumber(Integer outConsultationNumber) {
		this.outConsultationNumber = outConsultationNumber;
	}

	public Integer getInfusionTimes() {
		return infusionTimes;
	}

	public void setInfusionTimes(Integer infusionTimes) {
		this.infusionTimes = infusionTimes;
	}

	public Integer getInfusionReactTimes() {
		return infusionReactTimes;
	}

	public void setInfusionReactTimes(Integer infusionReactTimes) {
		this.infusionReactTimes = infusionReactTimes;
	}

	public String getFollowUpClinicLimitCode() {
		return followUpClinicLimitCode;
	}

	public void setFollowUpClinicLimitCode(String followUpClinicLimitCode) {
		this.followUpClinicLimitCode = followUpClinicLimitCode;
	}

	public String getFollowUpClinicLimitName() {
		return followUpClinicLimitName;
	}

	public void setFollowUpClinicLimitName(String followUpClinicLimitName) {
		this.followUpClinicLimitName = followUpClinicLimitName;
	}

	public BigDecimal getFollowUpClinicDayNumber() {
		return followUpClinicDayNumber;
	}

	public void setFollowUpClinicDayNumber(BigDecimal followUpClinicDayNumber) {
		this.followUpClinicDayNumber = followUpClinicDayNumber;
	}

	public List<DiagAccordRecord> getDiagAccordRecords() {
		
		return diagAccordRecords;
	}

	public void setDiagAccordRecords(
			List<DiagAccordRecord> diagAccordRecords) {
		this.diagAccordRecords = diagAccordRecords;
	}

	public String getBloodTypeCode() {
		return bloodTypeCode;
	}

	public void setBloodTypeCode(String bloodTypeCode) {
		this.bloodTypeCode = bloodTypeCode;
	}

	public String getBloodTypeName() {
		return bloodTypeName;
	}

	public void setBloodTypeName(String bloodTypeName) {
		this.bloodTypeName = bloodTypeName;
	}

	public String getRhBloodTypeCode() {
		return rhBloodTypeCode;
	}

	public void setRhBloodTypeCode(String rhBloodTypeCode) {
		this.rhBloodTypeCode = rhBloodTypeCode;
	}

	public String getRhBloodTypeName() {
		return rhBloodTypeName;
	}

	public void setRhBloodTypeName(String rhBloodTypeName) {
		this.rhBloodTypeName = rhBloodTypeName;
	}

	public List<MedicalWorker> getCureWorkers() {
		return cureWorkers;
	}

	public void setCureWorkers(List<MedicalWorker> cureWorkers) {
		this.cureWorkers = cureWorkers;
	}

	public String getMedicalRecordQualityCode() {
		return medicalRecordQualityCode;
	}

	public void setMedicalRecordQualityCode(String medicalRecordQualityCode) {
		this.medicalRecordQualityCode = medicalRecordQualityCode;
	}

	public String getMedicalRecordQualityName() {
		return medicalRecordQualityName;
	}

	public void setMedicalRecordQualityName(String medicalRecordQualityName) {
		this.medicalRecordQualityName = medicalRecordQualityName;
	}

	public Date getQualityControlDateTime() {
		return qualityControlDateTime;
	}

	public void setQualityControlDateTime(Date qualityControlDateTime) {
		this.qualityControlDateTime = qualityControlDateTime;
	}

	public String getHbsAgeResultCode() {
		return hbsAgeResultCode;
	}

	public void setHbsAgeResultCode(String hbsAgeResultCode) {
		this.hbsAgeResultCode = hbsAgeResultCode;
	}

	public String getHbsAgeResultName() {
		return hbsAgeResultName;
	}

	public void setHbsAgeResultName(String hbsAgeResultName) {
		this.hbsAgeResultName = hbsAgeResultName;
	}

	public String getHcvAbResultCode() {
		return hcvAbResultCode;
	}

	public void setHcvAbResultCode(String hcvAbResultCode) {
		this.hcvAbResultCode = hcvAbResultCode;
	}

	public String getHcvAbResultName() {
		return hcvAbResultName;
	}

	public void setHcvAbResultName(String hcvAbResultName) {
		this.hcvAbResultName = hcvAbResultName;
	}

	public String getHivAbResultCode() {
		return hivAbResultCode;
	}

	public void setHivAbResultCode(String hivAbResultCode) {
		this.hivAbResultCode = hivAbResultCode;
	}

	public String getHivAbResultName() {
		return hivAbResultName;
	}

	public void setHivAbResultName(String hivAbResultName) {
		this.hivAbResultName = hivAbResultName;
	}

	public BigDecimal getFollowInterval() {
		return followInterval;
	}

	public void setFollowInterval(BigDecimal followInterval) {
		this.followInterval = followInterval;
	}

	public String getFollowUpClinicUnitCode() {
		return followUpClinicUnitCode;
	}

	public void setFollowUpClinicUnitCode(String followUpClinicUnitCode) {
		this.followUpClinicUnitCode = followUpClinicUnitCode;
	}

	public String getFollowUpClinicUnitName() {
		return followUpClinicUnitName;
	}

	public void setFollowUpClinicUnitName(String followUpClinicUnitName) {
		this.followUpClinicUnitName = followUpClinicUnitName;
	}

	public BigDecimal getBabyBirthWeight() {
		return babyBirthWeight;
	}

	public void setBabyBirthWeight(BigDecimal babyBirthWeight) {
		this.babyBirthWeight = babyBirthWeight;
	}

	

	public BigDecimal getBabyInHospitalWeight() {
		return babyInHospitalWeight;
	}

	public void setBabyInHospitalWeight(BigDecimal babyInHospitalWeight) {
		this.babyInHospitalWeight = babyInHospitalWeight;
	}

	public Date getFinishCatalogDateTime() {
		return finishCatalogDateTime;
	}

	public void setFinishCatalogDateTime(Date finishCatalogDateTime) {
		this.finishCatalogDateTime = finishCatalogDateTime;
	}

	public String getRehospitalAimOf31Code() {
		return rehospitalAimOf31Code;
	}

	public void setRehospitalAimOf31Code(String rehospitalAimOf31Code) {
		this.rehospitalAimOf31Code = rehospitalAimOf31Code;
	}

	public String getRehospitalAimOf31Name() {
		return rehospitalAimOf31Name;
	}

	public void setRehospitalAimOf31Name(String rehospitalAimOf31Name) {
		this.rehospitalAimOf31Name = rehospitalAimOf31Name;
	}

	public String getRehospitalAimOf31Description() {
		return rehospitalAimOf31Description;
	}

	public void setRehospitalAimOf31Description(String rehospitalAimOf31Description) {
		this.rehospitalAimOf31Description = rehospitalAimOf31Description;
	}

	public Date getMalignantTumorConfirmedDateTime() {
		return malignantTumorConfirmedDateTime;
	}

	public void setMalignantTumorConfirmedDateTime(
			Date malignantTumorConfirmedDateTime) {
		this.malignantTumorConfirmedDateTime = malignantTumorConfirmedDateTime;
	}

	public String getTumorGrade() {
		return tumorGrade;
	}

	public void setTumorGrade(String tumorGrade) {
		this.tumorGrade = tumorGrade;
	}

	public String getTumorStagingOfT() {
		return tumorStagingOfT;
	}

	public void setTumorStagingOfT(String tumorStagingOfT) {
		this.tumorStagingOfT = tumorStagingOfT;
	}

	public String getTumorStagingOfN() {
		return tumorStagingOfN;
	}

	public void setTumorStagingOfN(String tumorStagingOfN) {
		this.tumorStagingOfN = tumorStagingOfN;
	}

	public String getTumorStagingOfM() {
		return tumorStagingOfM;
	}

	public void setTumorStagingOfM(String tumorStagingOfM) {
		this.tumorStagingOfM = tumorStagingOfM;
	}

	public String getMalignantTumorFirstCureTypeCode() {
		return malignantTumorFirstCureTypeCode;
	}

	public void setMalignantTumorFirstCureTypeCode(
			String malignantTumorFirstCureTypeCode) {
		this.malignantTumorFirstCureTypeCode = malignantTumorFirstCureTypeCode;
	}

	public String getMalignantTumorFirstCureTypeName() {
		return malignantTumorFirstCureTypeName;
	}

	public void setMalignantTumorFirstCureTypeName(
			String malignantTumorFirstCureTypeName) {
		this.malignantTumorFirstCureTypeName = malignantTumorFirstCureTypeName;
	}

	public String getMalignantTumorHighestDiagBasisCode() {
		return malignantTumorHighestDiagBasisCode;
	}

	public void setMalignantTumorHighestDiagBasisCode(
			String malignantTumorHighestDiagBasisCode) {
		this.malignantTumorHighestDiagBasisCode = malignantTumorHighestDiagBasisCode;
	}

	public String getMalignantTumorHighestDiagBasisName() {
		return malignantTumorHighestDiagBasisName;
	}

	public void setMalignantTumorHighestDiagBasisName(
			String malignantTumorHighestDiagBasisName) {
		this.malignantTumorHighestDiagBasisName = malignantTumorHighestDiagBasisName;
	}

	public String getBeforeInHospitalComaTimeDesc() {
		return beforeInHospitalComaTimeDesc;
	}

	public void setBeforeInHospitalComaTimeDesc(String beforeInHospitalComaTimeDesc) {
		this.beforeInHospitalComaTimeDesc = beforeInHospitalComaTimeDesc;
	}

	public BigDecimal getDayOfBeforeInHospitalComa() {
		return dayOfBeforeInHospitalComa;
	}

	public void setDayOfBeforeInHospitalComa(BigDecimal dayOfBeforeInHospitalComa) {
		this.dayOfBeforeInHospitalComa = dayOfBeforeInHospitalComa;
	}

	public BigDecimal getHourOfBeforeInHospitalComa() {
		return hourOfBeforeInHospitalComa;
	}

	public void setHourOfBeforeInHospitalComa(BigDecimal hourOfBeforeInHospitalComa) {
		this.hourOfBeforeInHospitalComa = hourOfBeforeInHospitalComa;
	}

	public BigDecimal getMinuteOfBeforeInHospitalComa() {
		return minuteOfBeforeInHospitalComa;
	}

	public void setMinuteOfBeforeInHospitalComa(BigDecimal minuteOfBeforeInHospitalComa) {
		this.minuteOfBeforeInHospitalComa = minuteOfBeforeInHospitalComa;
	}

	public String getAfterInHospitalComaTimeDesc() {
		return afterInHospitalComaTimeDesc;
	}

	public void setAfterInHospitalComaTimeDesc(String afterInHospitalComaTimeDesc) {
		this.afterInHospitalComaTimeDesc = afterInHospitalComaTimeDesc;
	}

	public BigDecimal getDayOfAfterInHospitalComa() {
		return dayOfAfterInHospitalComa;
	}

	public void setDayOfAfterInHospitalComa(BigDecimal dayOfAfterInHospitalComa) {
		this.dayOfAfterInHospitalComa = dayOfAfterInHospitalComa;
	}

	public BigDecimal getHourOfAfterInHospitalComa() {
		return hourOfAfterInHospitalComa;
	}

	public void setHourOfAfterInHospitalComa(BigDecimal hourOfAfterInHospitalComa) {
		this.hourOfAfterInHospitalComa = hourOfAfterInHospitalComa;
	}

	public BigDecimal getMinuteOfAfterInHospitalComa() {
		return minuteOfAfterInHospitalComa;
	}

	public void setMinuteOfAfterInHospitalComa(BigDecimal minuteOfAfterInHospitalComa) {
		this.minuteOfAfterInHospitalComa = minuteOfAfterInHospitalComa;
	}

	public String getIsFirstCaseCode() {
		return isFirstCaseCode;
	}

	public void setIsFirstCaseCode(String isFirstCaseCode) {
		this.isFirstCaseCode = isFirstCaseCode;
	}

	public String getIsFirstCaseName() {
		return isFirstCaseName;
	}

	public void setIsFirstCaseName(String isFirstCaseName) {
		this.isFirstCaseName = isFirstCaseName;
	}

	public String getIsTeachingCaseCode() {
		return isTeachingCaseCode;
	}

	public void setIsTeachingCaseCode(String isTeachingCaseCode) {
		this.isTeachingCaseCode = isTeachingCaseCode;
	}

	public String getIsTeachingCaseName() {
		return isTeachingCaseName;
	}

	public void setIsTeachingCaseName(String isTeachingCaseName) {
		this.isTeachingCaseName = isTeachingCaseName;
	}

	public BigDecimal getFollowUpClinicWeekNumber() {
		return followUpClinicWeekNumber;
	}

	public void setFollowUpClinicWeekNumber(BigDecimal followUpClinicWeekNumber) {
		this.followUpClinicWeekNumber = followUpClinicWeekNumber;
	}

	public BigDecimal getFollowUpClinicMonthNumber() {
		return followUpClinicMonthNumber;
	}

	public void setFollowUpClinicMonthNumber(BigDecimal followUpClinicMonthNumber) {
		this.followUpClinicMonthNumber = followUpClinicMonthNumber;
	}

	public BigDecimal getFollowUpClinicYearNumber() {
		return followUpClinicYearNumber;
	}

	public void setFollowUpClinicYearNumber(BigDecimal followUpClinicYearNumber) {
		this.followUpClinicYearNumber = followUpClinicYearNumber;
	}
	
	

}
