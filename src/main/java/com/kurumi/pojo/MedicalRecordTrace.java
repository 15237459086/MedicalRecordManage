package com.kurumi.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kurumi.util.StringUtil;

public class MedicalRecordTrace {
	
	//初始化信息
	public static String VISIT_INIT="CSH";
	
	//病案归档
	public static final String VISIT_PIGEONHOLE = "BAGD";
	
	//病案质控
	public static final String VISIT_QUALITY = "BAZK";
	
	//病案编页
	public static final String VISIT_PAGINATION = "BABY";
	
	//病案编码
	public static String VISIT_PAGE="BABM";
	
	//病案编码
	public static String VISIT_PUBLISH="BNKFX";
	
	private Integer id;

    private String visitGuid;

    private String createUserId;

    private String createUserName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date traceDateTime = new Date();

    private String traceTypeCode;

    private String traceTypeName;

    private String remark;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDateTime = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVisitGuid() {
		return StringUtil.meaningStr(visitGuid);
	}

	public void setVisitGuid(String visitGuid) {
		this.visitGuid = visitGuid;
	}

	public String getCreateUserId() {
		return StringUtil.meaningStr(createUserId);
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return StringUtil.meaningStr(createUserName);
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getTraceDateTime() {
		return traceDateTime;
	}

	public void setTraceDateTime(Date traceDateTime) {
		this.traceDateTime = traceDateTime;
	}

	public String getTraceTypeCode() {
		return StringUtil.meaningStr(traceTypeCode);
	}

	public void setTraceTypeCode(String traceTypeCode) {
		this.traceTypeCode = traceTypeCode;
	}

	public String getTraceTypeName() {
		return StringUtil.meaningStr(traceTypeName);
	}

	public void setTraceTypeName(String traceTypeName) {
		this.traceTypeName = traceTypeName;
	}

	public String getRemark() {
		return StringUtil.meaningStr(remark);
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
    
    
    
}