package com.kurumi.pojo.coding;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 医院感染记录
 * @author lyh
 *
 */
public class InfectionRecord {

	/**
	 * 感染日期
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date infectDateTime;
	
	/**
	 * 感染部位编号
	 */
	private String infectedPartCodes;
	
	/**
	 * 感染部位名称
	 */
	private String infectedPartNames; 
	
	/**
	 * 是否院内感染编号
	 */
	private String isNoSocomialCode;
	
	/**
	 * 是否院内感染名称
	 */
	private String isNoSocomialName;
	
	/**
	 * 感染科室code
	 */
	private String infectedDeptCode;
	
	/**
	 * 感染科室Name
	 */
	private String infectedDeptName;
	
	/**
	 * 感染状态
	 */
	private String infstateCode;
	
	/**
	 * 感染状态
	 */
	private String infstateName;
	
	/**
	 * 院感科处理人员Code
	 */
	private String confirmerCode;
	
	
	/**
	 * 院感科处理人员Name
	 */
	private String confirmerName;
	
	/**
	 * 院感科处理时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date confirmedDateTime;
	
	/**
	 * 感染致病菌Code
	 */
	private String bacteriaCode;
	
	/**
	 * 感染致病菌Name
	 */
	private String bacteriaName;
	
	/**
	 * 临床处理人员Code
	 */
	private String reportDoctorCode;
	
	/**
	 * 临床处理人员Name
	 */
	private String reportDoctorName;
	
	/**
	 * 临床处理时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date reportDateTime;
	
	/**
	 * 临床处理结果内容
	 */
	private String reportContent;
	
	/**
	 * 是否为手术部位Code
	 */
	private String isOperinfCode;
	
	/**
	 * 是否为手术部位Name
	 */
	private String isOperinfName;

	public Date getInfectDateTime() {
		return infectDateTime;
	}

	public void setInfectDateTime(Date infectDateTime) {
		this.infectDateTime = infectDateTime;
	}

	public String getInfectedPartCodes() {
		return infectedPartCodes;
	}

	public void setInfectedPartCodes(String infectedPartCodes) {
		this.infectedPartCodes = infectedPartCodes;
	}

	public String getInfectedPartNames() {
		return infectedPartNames;
	}

	public void setInfectedPartNames(String infectedPartNames) {
		this.infectedPartNames = infectedPartNames;
	}

	public String getIsNoSocomialCode() {
		return isNoSocomialCode;
	}

	public void setIsNoSocomialCode(String isNoSocomialCode) {
		this.isNoSocomialCode = isNoSocomialCode;
	}

	public String getIsNoSocomialName() {
		return isNoSocomialName;
	}

	public void setIsNoSocomialName(String isNoSocomialName) {
		this.isNoSocomialName = isNoSocomialName;
	}

	public String getInfectedDeptCode() {
		return infectedDeptCode;
	}

	public void setInfectedDeptCode(String infectedDeptCode) {
		this.infectedDeptCode = infectedDeptCode;
	}

	public String getInfectedDeptName() {
		return infectedDeptName;
	}

	public void setInfectedDeptName(String infectedDeptName) {
		this.infectedDeptName = infectedDeptName;
	}

	public String getInfstateCode() {
		return infstateCode;
	}

	public void setInfstateCode(String infstateCode) {
		this.infstateCode = infstateCode;
	}

	public String getInfstateName() {
		return infstateName;
	}

	public void setInfstateName(String infstateName) {
		this.infstateName = infstateName;
	}

	public String getConfirmerCode() {
		return confirmerCode;
	}

	public void setConfirmerCode(String confirmerCode) {
		this.confirmerCode = confirmerCode;
	}

	public String getConfirmerName() {
		return confirmerName;
	}

	public void setConfirmerName(String confirmerName) {
		this.confirmerName = confirmerName;
	}

	public Date getConfirmedDateTime() {
		return confirmedDateTime;
	}

	public void setConfirmedDateTime(Date confirmedDateTime) {
		this.confirmedDateTime = confirmedDateTime;
	}

	public String getBacteriaCode() {
		return bacteriaCode;
	}

	public void setBacteriaCode(String bacteriaCode) {
		this.bacteriaCode = bacteriaCode;
	}

	public String getBacteriaName() {
		return bacteriaName;
	}

	public void setBacteriaName(String bacteriaName) {
		this.bacteriaName = bacteriaName;
	}

	public String getReportDoctorCode() {
		return reportDoctorCode;
	}

	public void setReportDoctorCode(String reportDoctorCode) {
		this.reportDoctorCode = reportDoctorCode;
	}

	public String getReportDoctorName() {
		return reportDoctorName;
	}

	public void setReportDoctorName(String reportDoctorName) {
		this.reportDoctorName = reportDoctorName;
	}

	public Date getReportDateTime() {
		return reportDateTime;
	}

	public void setReportDateTime(Date reportDateTime) {
		this.reportDateTime = reportDateTime;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public String getIsOperinfCode() {
		return isOperinfCode;
	}

	public void setIsOperinfCode(String isOperinfCode) {
		this.isOperinfCode = isOperinfCode;
	}

	public String getIsOperinfName() {
		return isOperinfName;
	}

	public void setIsOperinfName(String isOperinfName) {
		this.isOperinfName = isOperinfName;
	}

	
	
}
