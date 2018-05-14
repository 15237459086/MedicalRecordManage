package com.kurumi.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MedicalRecord {
    private String visitGuid;

    private String patientName;

    private String sexCode;

    private String sexName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    private String documentTypeCode;

    private String documentTypeName;

    private String idNumber;

    private String onlyId;

    private String mrId;

    private Integer visitNumber;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inHospitalDateTime;

    private String inDeptCode;

    private String inDeptName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date outHospitalDateTime;

    private String outDeptCode;

    private String outDeptName;

    private String outHospitalTypeCode;

    private String outHospitalTypeName;

    private String treatmentSignCode;

    private String treatmentSignName;

    private Integer version;

    public String getVisitGuid() {
        return visitGuid;
    }

    public void setVisitGuid(String visitGuid) {
        this.visitGuid = visitGuid == null ? null : visitGuid.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode == null ? null : sexCode.trim();
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName == null ? null : sexName.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDocumentTypeCode() {
        return documentTypeCode;
    }

    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode == null ? null : documentTypeCode.trim();
    }

    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName == null ? null : documentTypeName.trim();
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
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
        this.inDeptCode = inDeptCode == null ? null : inDeptCode.trim();
    }

    public String getInDeptName() {
        return inDeptName;
    }

    public void setInDeptName(String inDeptName) {
        this.inDeptName = inDeptName == null ? null : inDeptName.trim();
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

    public String getOutHospitalTypeCode() {
        return outHospitalTypeCode;
    }

    public void setOutHospitalTypeCode(String outHospitalTypeCode) {
        this.outHospitalTypeCode = outHospitalTypeCode == null ? null : outHospitalTypeCode.trim();
    }

    public String getOutHospitalTypeName() {
        return outHospitalTypeName;
    }

    public void setOutHospitalTypeName(String outHospitalTypeName) {
        this.outHospitalTypeName = outHospitalTypeName == null ? null : outHospitalTypeName.trim();
    }

    public String getTreatmentSignCode() {
        return treatmentSignCode;
    }

    public void setTreatmentSignCode(String treatmentSignCode) {
        this.treatmentSignCode = treatmentSignCode == null ? null : treatmentSignCode.trim();
    }

    public String getTreatmentSignName() {
        return treatmentSignName;
    }

    public void setTreatmentSignName(String treatmentSignName) {
        this.treatmentSignName = treatmentSignName == null ? null : treatmentSignName.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}