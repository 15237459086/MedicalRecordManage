package com.kurumi.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MedicalRecordPrinterApplyItem {
    private Integer id;

    private Integer medicalRecordPrinterApplyId;

    private String visitGuid;

    private String onlyId;

    private String mrId;

    private Integer visitNumber;

    private String patientName;

    private String patientCode;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date outHospitalDateTime;

    private String applyPrinterTypeCode;

    private String applyPrinterTypeName;

    private Integer applyCopies;

    private String auditorName;

    private String auditorCode;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date applyDateTime = new Date();

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date approvalDateTime = new Date();

    private String approvalStatusCode;

    private String approvalStatusName;

    private Integer version = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicalRecordPrinterApplyId() {
        return medicalRecordPrinterApplyId;
    }

    public void setMedicalRecordPrinterApplyId(Integer medicalRecordPrinterApplyId) {
        this.medicalRecordPrinterApplyId = medicalRecordPrinterApplyId;
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

    public String getApplyPrinterTypeCode() {
        return applyPrinterTypeCode;
    }

    public void setApplyPrinterTypeCode(String applyPrinterTypeCode) {
        this.applyPrinterTypeCode = applyPrinterTypeCode == null ? null : applyPrinterTypeCode.trim();
    }

    public String getApplyPrinterTypeName() {
        return applyPrinterTypeName;
    }

    public void setApplyPrinterTypeName(String applyPrinterTypeName) {
        this.applyPrinterTypeName = applyPrinterTypeName == null ? null : applyPrinterTypeName.trim();
    }

    public Integer getApplyCopies() {
        return applyCopies;
    }

    public void setApplyCopies(Integer applyCopies) {
        this.applyCopies = applyCopies;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName == null ? null : auditorName.trim();
    }

    public String getAuditorCode() {
        return auditorCode;
    }

    public void setAuditorCode(String auditorCode) {
        this.auditorCode = auditorCode == null ? null : auditorCode.trim();
    }

    public Date getApplyDateTime() {
        return applyDateTime;
    }

    public void setApplyDateTime(Date applyDateTime) {
        this.applyDateTime = applyDateTime;
    }

    public Date getApprovalDateTime() {
        return approvalDateTime;
    }

    public void setApprovalDateTime(Date approvalDateTime) {
        this.approvalDateTime = approvalDateTime;
    }

    public String getApprovalStatusCode() {
        return approvalStatusCode;
    }

    public void setApprovalStatusCode(String approvalStatusCode) {
        this.approvalStatusCode = approvalStatusCode == null ? null : approvalStatusCode.trim();
    }

    public String getApprovalStatusName() {
        return approvalStatusName;
    }

    public void setApprovalStatusName(String approvalStatusName) {
        this.approvalStatusName = approvalStatusName == null ? null : approvalStatusName.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}