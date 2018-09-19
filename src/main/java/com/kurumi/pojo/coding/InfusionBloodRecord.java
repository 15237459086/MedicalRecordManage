package com.kurumi.pojo.coding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 输血记录
 * @author lyh
 *
 */
public class InfusionBloodRecord {

	/**
	 * 科室Code
	 */
	private String deptCode;
	
	/**
	 * 科室Name
	 */
	private String deptName;
	
	/**
	 * 患者血型Code
	 */
	private String patientBloodTypeCode;
	
	/**
	 * 患者血型Name
	 */
	private String patientBloodTypeName;

	/**
	 * 患者RH血型Code
	 */
	private String patientRhBloodTypeCode;
	
	/**
	 * 患者RH血型Name
	 */
	private String patientRhBloodTypeName;

	/**
	 * 输血血液类型Code
	 */
	private String infusionBloodTypeCode;
	
	/**
	 * 输血血液类型Name
	 */
	private String infusionBloodTypeName;

	/**
	 * 是否有输血反应Code
	 */
	private String isBleedingReactionCode;
	
	/**
	 * 是否有输血反应Name
	 */
	private String isBleedingReactionName;

	/**
	 * 输血日期
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date bleedingDateTime;
	
	private List<InfusionBloodElement> infusionBloodElements = new ArrayList<InfusionBloodElement>();

	/**
	 * 操作人Code
	 */
	private String operatorCode;
	
	/**
	 * 操作人Name
	 */
	private String operatorName;

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPatientBloodTypeCode() {
		return patientBloodTypeCode;
	}

	public void setPatientBloodTypeCode(String patientBloodTypeCode) {
		this.patientBloodTypeCode = patientBloodTypeCode;
	}

	public String getPatientBloodTypeName() {
		return patientBloodTypeName;
	}

	public void setPatientBloodTypeName(String patientBloodTypeName) {
		this.patientBloodTypeName = patientBloodTypeName;
	}

	public String getPatientRhBloodTypeCode() {
		return patientRhBloodTypeCode;
	}

	public void setPatientRhBloodTypeCode(String patientRhBloodTypeCode) {
		this.patientRhBloodTypeCode = patientRhBloodTypeCode;
	}

	public String getPatientRhBloodTypeName() {
		return patientRhBloodTypeName;
	}

	public void setPatientRhBloodTypeName(String patientRhBloodTypeName) {
		this.patientRhBloodTypeName = patientRhBloodTypeName;
	}

	public String getInfusionBloodTypeCode() {
		return infusionBloodTypeCode;
	}

	public void setInfusionBloodTypeCode(String infusionBloodTypeCode) {
		this.infusionBloodTypeCode = infusionBloodTypeCode;
	}

	public String getInfusionBloodTypeName() {
		return infusionBloodTypeName;
	}

	public void setInfusionBloodTypeName(String infusionBloodTypeName) {
		this.infusionBloodTypeName = infusionBloodTypeName;
	}

	public String getIsBleedingReactionCode() {
		return isBleedingReactionCode;
	}

	public void setIsBleedingReactionCode(String isBleedingReactionCode) {
		this.isBleedingReactionCode = isBleedingReactionCode;
	}

	public String getIsBleedingReactionName() {
		return isBleedingReactionName;
	}

	public void setIsBleedingReactionName(String isBleedingReactionName) {
		this.isBleedingReactionName = isBleedingReactionName;
	}

	public Date getBleedingDateTime() {
		return bleedingDateTime;
	}

	public void setBleedingDateTime(Date bleedingDateTime) {
		this.bleedingDateTime = bleedingDateTime;
	}

	public List<InfusionBloodElement> getInfusionBloodElements() {
		return infusionBloodElements;
	}

	public void setInfusionBloodElements(List<InfusionBloodElement> infusionBloodElements) {
		this.infusionBloodElements = infusionBloodElements;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	
	
	
}
