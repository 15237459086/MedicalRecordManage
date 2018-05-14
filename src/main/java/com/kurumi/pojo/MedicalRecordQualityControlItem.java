package com.kurumi.pojo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MedicalRecordQualityControlItem {
    private Integer id;

    private Integer medicalRecordQualityControlId;

    private String firstLevelCode;

    private String firstLevelName;

    private String secondLevelCode;

    private String secondLevelName;

    private String thirdLevelCode;

    private String thirdLevelName;

    private BigDecimal deduction;

    private String remark;

    private String lastUserId;

    private String lastUserName;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
   	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastDateTime = new Date();


    private Integer version = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicalRecordQualityControlId() {
        return medicalRecordQualityControlId;
    }

    public void setMedicalRecordQualityControlId(Integer medicalRecordQualityControlId) {
        this.medicalRecordQualityControlId = medicalRecordQualityControlId;
    }

    public String getFirstLevelCode() {
        return firstLevelCode;
    }

    public void setFirstLevelCode(String firstLevelCode) {
        this.firstLevelCode = firstLevelCode == null ? null : firstLevelCode.trim();
    }

   

    public String getFirstLevelName() {
		return firstLevelName;
	}

	public void setFirstLevelName(String firstLevelName) {
		this.firstLevelName = firstLevelName;
	}

	public String getSecondLevelCode() {
        return secondLevelCode;
    }

    public void setSecondLevelCode(String secondLevelCode) {
        this.secondLevelCode = secondLevelCode == null ? null : secondLevelCode.trim();
    }

    public String getSecondLevelName() {
        return secondLevelName;
    }

    public void setSecondLevelName(String secondLevelName) {
        this.secondLevelName = secondLevelName == null ? null : secondLevelName.trim();
    }

    public String getThirdLevelCode() {
        return thirdLevelCode;
    }

    public void setThirdLevelCode(String thirdLevelCode) {
        this.thirdLevelCode = thirdLevelCode == null ? null : thirdLevelCode.trim();
    }

    public String getThirdLevelName() {
        return thirdLevelName;
    }

    public void setThirdLevelName(String thirdLevelName) {
        this.thirdLevelName = thirdLevelName == null ? null : thirdLevelName.trim();
    }

    public BigDecimal getDeduction() {
        return deduction;
    }

    public void setDeduction(BigDecimal deduction) {
        this.deduction = deduction;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLastUserId() {
        return lastUserId;
    }

    public void setLastUserId(String lastUserId) {
        this.lastUserId = lastUserId == null ? null : lastUserId.trim();
    }

    public String getLastUserName() {
        return lastUserName;
    }

    public void setLastUserName(String lastUserName) {
        this.lastUserName = lastUserName == null ? null : lastUserName.trim();
    }

    public Date getLastDateTime() {
		return lastDateTime;
	}

	public void setLastDateTime(Date lastDateTime) {
		this.lastDateTime = lastDateTime;
	}

	public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}