package com.kurumi.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MedicalRecordPrinterApply {
    private Integer id;

    private String claimerName;

    private String claimerCode;

    private String applyRemark;

    private String claimerPhone;

    private String claimerRelativeRelationCode;

    private String claimerRelativeRelationName;

    private String applyTypeCode;

    private String applyTypeName;

    private String registrantName;

    private String registrantCode;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date applyDateTime = new Date();

    private Integer version = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClaimerName() {
        return claimerName;
    }

    public void setClaimerName(String claimerName) {
        this.claimerName = claimerName == null ? null : claimerName.trim();
    }

    public String getClaimerCode() {
        return claimerCode;
    }

    public void setClaimerCode(String claimerCode) {
        this.claimerCode = claimerCode == null ? null : claimerCode.trim();
    }

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark == null ? null : applyRemark.trim();
    }

    public String getClaimerPhone() {
        return claimerPhone;
    }

    public void setClaimerPhone(String claimerPhone) {
        this.claimerPhone = claimerPhone == null ? null : claimerPhone.trim();
    }

    public String getClaimerRelativeRelationCode() {
        return claimerRelativeRelationCode;
    }

    public void setClaimerRelativeRelationCode(String claimerRelativeRelationCode) {
        this.claimerRelativeRelationCode = claimerRelativeRelationCode == null ? null : claimerRelativeRelationCode.trim();
    }

    public String getClaimerRelativeRelationName() {
        return claimerRelativeRelationName;
    }

    public void setClaimerRelativeRelationName(String claimerRelativeRelationName) {
        this.claimerRelativeRelationName = claimerRelativeRelationName == null ? null : claimerRelativeRelationName.trim();
    }

    public String getApplyTypeCode() {
        return applyTypeCode;
    }

    public void setApplyTypeCode(String applyTypeCode) {
        this.applyTypeCode = applyTypeCode == null ? null : applyTypeCode.trim();
    }

    public String getApplyTypeName() {
        return applyTypeName;
    }

    public void setApplyTypeName(String applyTypeName) {
        this.applyTypeName = applyTypeName == null ? null : applyTypeName.trim();
    }

    public String getRegistrantName() {
        return registrantName;
    }

    public void setRegistrantName(String registrantName) {
        this.registrantName = registrantName == null ? null : registrantName.trim();
    }

    public String getRegistrantCode() {
        return registrantCode;
    }

    public void setRegistrantCode(String registrantCode) {
        this.registrantCode = registrantCode == null ? null : registrantCode.trim();
    }

    public Date getApplyDateTime() {
        return applyDateTime;
    }

    public void setApplyDateTime(Date applyDateTime) {
        this.applyDateTime = applyDateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}