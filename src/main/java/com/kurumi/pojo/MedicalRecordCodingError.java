package com.kurumi.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MedicalRecordCodingError {

	private Integer id;
	
	private String visitGuid;
	
	private String codingErrorCode;
	
	private String codingErrorName;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
   	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDateTime = new Date();
	
	private String createErrorCoderCode;
	
	private String createErrorCoderName;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
   	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastDateTime = new Date();
	
	private String lastErrorCoderCode;
	
	private String lastErrorCoderName;
	
	private String errorStatusCode;
	
	private String errorStatusName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVisitGuid() {
		return visitGuid;
	}

	public void setVisitGuid(String visitGuid) {
		this.visitGuid = visitGuid;
	}

	public String getCodingErrorCode() {
		return codingErrorCode;
	}

	public void setCodingErrorCode(String codingErrorCode) {
		this.codingErrorCode = codingErrorCode;
	}

	public String getCodingErrorName() {
		return codingErrorName;
	}

	public void setCodingErrorName(String codingErrorName) {
		this.codingErrorName = codingErrorName;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateErrorCoderCode() {
		return createErrorCoderCode;
	}

	public void setCreateErrorCoderCode(String createErrorCoderCode) {
		this.createErrorCoderCode = createErrorCoderCode;
	}

	public String getCreateErrorCoderName() {
		return createErrorCoderName;
	}

	public void setCreateErrorCoderName(String createErrorCoderName) {
		this.createErrorCoderName = createErrorCoderName;
	}

	public Date getLastDateTime() {
		return lastDateTime;
	}

	public void setLastDateTime(Date lastDateTime) {
		this.lastDateTime = lastDateTime;
	}

	public String getLastErrorCoderCode() {
		return lastErrorCoderCode;
	}

	public void setLastErrorCoderCode(String lastErrorCoderCode) {
		this.lastErrorCoderCode = lastErrorCoderCode;
	}

	public String getLastErrorCoderName() {
		return lastErrorCoderName;
	}

	public void setLastErrorCoderName(String lastErrorCoderName) {
		this.lastErrorCoderName = lastErrorCoderName;
	}

	public String getErrorStatusCode() {
		return errorStatusCode;
	}

	public void setErrorStatusCode(String errorStatusCode) {
		this.errorStatusCode = errorStatusCode;
	}

	public String getErrorStatusName() {
		return errorStatusName;
	}

	public void setErrorStatusName(String errorStatusName) {
		this.errorStatusName = errorStatusName;
	}
	
	
	
	
	
}
