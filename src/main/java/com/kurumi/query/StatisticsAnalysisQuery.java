package com.kurumi.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StatisticsAnalysisQuery {
	

	//开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date startDate;
	
	//结束时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date endDate;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date outHospitalStartDate;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date outHospitalEndDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	
	
	
	
	
}
