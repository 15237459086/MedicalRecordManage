package com.kurumi.pojo;

import java.util.Date;

public class MedicalRecordImage {
    private Integer id;

    private String fileHash;

    private String visitGuid;

    private String mrPageTypeCode;

    private String createUserId;

    private String createUserName;

    private Date createDateTime = new Date();

    private String lastUserId;

    private String lastUserName;

    private Date lastDateTime = new Date();

    private Integer version = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash == null ? null : fileHash.trim();
    }

    public String getVisitGuid() {
        return visitGuid;
    }

    public void setVisitGuid(String visitGuid) {
        this.visitGuid = visitGuid == null ? null : visitGuid.trim();
    }

    public String getMrPageTypeCode() {
        return mrPageTypeCode;
    }

    public void setMrPageTypeCode(String mrPageTypeCode) {
        this.mrPageTypeCode = mrPageTypeCode == null ? null : mrPageTypeCode.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
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