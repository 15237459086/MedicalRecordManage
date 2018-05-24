package com.kurumi.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MedicalRecordBorrowApplyItem {
    private Integer id;

    private Integer medicalRecordBorrowApplyId;

    private String visitGuid;

    private String onlyId;

    private String mrId;

    private Integer visitNumber;

    private String patientName;

    private String patientCode;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date outHospitalDateTime;

    private String outDeptCode;

    private String outDeptName;

    private String replyerName;

    private String replyerCode;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date applyDateTime = new Date();

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date replyDateTime;

    private String replyStatusCode;

    private String replyStatusName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date validityDateTime;

    private Integer version =1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicalRecordBorrowApplyId() {
        return medicalRecordBorrowApplyId;
    }

    public void setMedicalRecordBorrowApplyId(Integer medicalRecordBorrowApplyId) {
        this.medicalRecordBorrowApplyId = medicalRecordBorrowApplyId;
    }

    public String getVisitGuid() {
        return visitGuid;
    }

    public void setVisitGuid(String visitGuid) {
        this.visitGuid = visitGuid == null ? null : visitGuid.trim();
    }

    public String getOnlyId() {
        return onlyId;
    }

    public void setOnlyId(String onlyId) {
        this.onlyId = onlyId == null ? null : onlyId.trim();
    }

    public String getMrId() {
        return mrId;
    }

    public void setMrId(String mrId) {
        this.mrId = mrId == null ? null : mrId.trim();
    }

    public Integer getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(Integer visitNumber) {
        this.visitNumber = visitNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode == null ? null : patientCode.trim();
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
        this.outDeptCode = outDeptCode == null ? null : outDeptCode.trim();
    }

    public String getOutDeptName() {
        return outDeptName;
    }

    public void setOutDeptName(String outDeptName) {
        this.outDeptName = outDeptName == null ? null : outDeptName.trim();
    }

    

    public String getReplyerName() {
		return replyerName;
	}

	public void setReplyerName(String replyerName) {
		this.replyerName = replyerName;
	}

	

    public String getReplyerCode() {
		return replyerCode;
	}

	public void setReplyerCode(String replyerCode) {
		this.replyerCode = replyerCode;
	}

	public Date getApplyDateTime() {
        return applyDateTime;
    }

    public void setApplyDateTime(Date applyDateTime) {
        this.applyDateTime = applyDateTime;
    }

    public Date getReplyDateTime() {
        return replyDateTime;
    }

    public void setReplyDateTime(Date replyDateTime) {
        this.replyDateTime = replyDateTime;
    }

    public String getReplyStatusCode() {
        return replyStatusCode;
    }

    public void setReplyStatusCode(String replyStatusCode) {
        this.replyStatusCode = replyStatusCode == null ? null : replyStatusCode.trim();
    }

    public String getReplyStatusName() {
        return replyStatusName;
    }

    public void setReplyStatusName(String replyStatusName) {
        this.replyStatusName = replyStatusName == null ? null : replyStatusName.trim();
    }

    public Date getValidityDateTime() {
        return validityDateTime;
    }

    public void setValidityDateTime(Date validityDateTime) {
        this.validityDateTime = validityDateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}