package com.kurumi.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurumi.util.StringUtil;

public class MedicalRecordBorrowQuery extends PageQuery {

	//借阅人名称
	private String borrowerName;

	//借阅人编号
    private String borrowerCode;

    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applyStartDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applyEndDate;
    
    private String onlyId;

    private String mrId;

    private Integer visitNumber;

    private String patientName;

    private String patientCode;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date outHospitalStartDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date outHospitalEndDate;
    
    private String outDeptCode;
    
    private String outDeptName;
    
    
    //查询结果集
  	private Object queryDatas;

  	
	
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

	

	

	public Date getApplyStartDate() {
		return applyStartDate;
	}

	public void setApplyStartDate(Date applyStartDate) {
		this.applyStartDate = applyStartDate;
	}

	public Date getApplyEndDate() {
		return applyEndDate;
	}

	public void setApplyEndDate(Date applyEndDate) {
		this.applyEndDate = applyEndDate;
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

	public Date getOutHospitalStartDate() {
		return outHospitalStartDate;
	}

	public void setOutHospitalStartDate(Date outHospitalStartDate) {
		this.outHospitalStartDate = outHospitalStartDate;
	}

	public Date getOutHospitalEndDate() {
		return outHospitalEndDate;
	}

	public void setOutHospitalEndDate(Date outHospitalEndDate) {
		this.outHospitalEndDate = outHospitalEndDate;
	}

	public String getOutDeptCode() {
		return StringUtil.meaningStr(outDeptCode);
	}

	public void setOutDeptCode(String outDeptCode) {
		this.outDeptCode = StringUtil.meaningStr(outDeptCode);
	}

	public String getOutDeptName() {
		return StringUtil.meaningStr(outDeptName);
	}

	public void setOutDeptName(String outDeptName) {
		this.outDeptName = StringUtil.meaningStr(outDeptName);
	}

	public Object getQueryDatas() {
		return queryDatas;
	}

	public void setQueryDatas(Object queryDatas) {
		this.queryDatas = queryDatas;
	}
    
	@JsonIgnore
	public boolean IsBorrowApplyEmpty(){
		if(this.getPatientName() == null && this.getPatientCode()== null && this.getMrId() == null
				&& this.getOnlyId() == null && this.getVisitNumber() == null&& (this.getOutHospitalStartDate()==null || this.getOutHospitalEndDate() == null)
				&& this.getOutDeptCode() == null && this.getOutDeptName() == null && (this.getApplyStartDate()==null || this.getApplyEndDate()== null)){
			return true;
		}
		return false;
	}
    
}
