package com.kurumi.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurumi.util.StringUtil;

public class MedicalRecordPrinterQuery extends PageQuery {

	//申请人名称
	private String claimerName;

	//申请人证件号码
    private String claimerCode;

    //申请人电话
    private String claimerPhone;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applyStartDateTime ;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applyEndDateTime;
    
    
    private String onlyId;

    private String mrId;

    private Integer visitNumber;

    private String patientName;

    private String patientCode;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date outHospitalStartDateTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date outHospitalEndDateTime;
    
    //查询结果集
  	private Object queryDatas;

	public String getClaimerName() {
		return StringUtil.meaningStr(claimerName);
	}

	public void setClaimerName(String claimerName) {
		this.claimerName = StringUtil.meaningStr(claimerName);
	}

	public String getClaimerCode() {
		return StringUtil.meaningStr(claimerCode);
	}

	public void setClaimerCode(String claimerCode) {
		this.claimerCode = StringUtil.meaningStr(claimerCode);
	}

	public String getClaimerPhone() {
		return StringUtil.meaningStr(claimerPhone);
	}

	public void setClaimerPhone(String claimerPhone) {
		this.claimerPhone = StringUtil.meaningStr(claimerPhone);
	}

	public Date getApplyStartDateTime() {
		return applyStartDateTime;
	}

	public void setApplyStartDateTime(Date applyStartDateTime) {
		this.applyStartDateTime = applyStartDateTime;
	}

	public Date getApplyEndDateTime() {
		return applyEndDateTime;
	}

	public void setApplyEndDateTime(Date applyEndDateTime) {
		this.applyEndDateTime = applyEndDateTime;
	}

	public String getOnlyId() {
		return StringUtil.meaningStr(onlyId);
	}

	public void setOnlyId(String onlyId) {
		this.onlyId = StringUtil.meaningStr(onlyId);
	}

	public String getMrId() {
		return StringUtil.meaningStr(mrId);
	}

	public void setMrId(String mrId) {
		this.mrId = StringUtil.meaningStr(mrId);
	}

	public Integer getVisitNumber() {
		return visitNumber;
	}

	public void setVisitNumber(Integer visitNumber) {
		this.visitNumber = visitNumber;
	}

	public String getPatientName() {
		return StringUtil.meaningStr(patientName);
	}

	public void setPatientName(String patientName) {
		this.patientName = StringUtil.meaningStr(patientName);
	}

	public String getPatientCode() {
		return StringUtil.meaningStr(patientCode);
	}

	public void setPatientCode(String patientCode) {
		this.patientCode = StringUtil.meaningStr(patientCode);
	}

	public Date getOutHospitalStartDateTime() {
		return outHospitalStartDateTime;
	}

	public void setOutHospitalStartDateTime(Date outHospitalStartDateTime) {
		this.outHospitalStartDateTime = outHospitalStartDateTime;
	}

	public Date getOutHospitalEndDateTime() {
		return outHospitalEndDateTime;
	}

	public void setOutHospitalEndDateTime(Date outHospitalEndDateTime) {
		this.outHospitalEndDateTime = outHospitalEndDateTime;
	}

	public Object getQueryDatas() {
		return queryDatas;
	}

	public void setQueryDatas(Object queryDatas) {
		this.queryDatas = queryDatas;
	}
    
	@JsonIgnore
	public boolean IsPrinterApplyEmpty(){
		if(this.getClaimerName() == null && this.getClaimerCode() == null && this.getMrId() == null
				&& this.getClaimerPhone() == null && (this.getApplyStartDateTime()==null || this.getApplyEndDateTime() == null)){
			return true;
		}
		return false;
	}
    
}
