package com.kurumi.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MedicalRecordBorrowApply {
	
	public static String BORROW_APPLY_CODE="APPLYING";
	
	public static String BORROW_APPLY_NAME="借阅申请中";
	
	public static String BORROW_REJECT_CODE="REJECT";
	
	public static String BORROW_REJECT_NAME="借阅拒批";
	
	public static String BORROW_PERMIT_CODE="PERMIT";
	
	public static String BORROW_PERMIT_NAME="借阅批准";
	
    private Integer id;

    private String borrowerName;

    private String borrowerCode;

    private String borrowRemark;

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
    
    

    public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	

    public String getBorrowerCode() {
		return borrowerCode;
	}

	public void setBorrowerCode(String borrowerCode) {
		this.borrowerCode = borrowerCode;
	}

	public String getBorrowRemark() {
        return borrowRemark;
    }

    public void setBorrowRemark(String borrowRemark) {
        this.borrowRemark = borrowRemark == null ? null : borrowRemark.trim();
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