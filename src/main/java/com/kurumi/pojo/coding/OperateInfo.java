package com.kurumi.pojo.coding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class OperateInfo {

	/**
	 * visitGuid
	 */
	private String visitGuid;
	
	
	/**
	 * 围手术期开始日期
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date periOperatePeriodStartDate;
	
	/**
	 * 围手术期结束日期
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date periOperatePeriodEndDate;
	
	private List<OperateRecord> operateRecords = new ArrayList<OperateRecord>();

	@JsonIgnore
	public void clearEmpty(){
		for (int i = this.operateRecords.size()-1; i >=0; i--) {
			OperateRecord operateRecord = this.operateRecords.get(i);
			if(operateRecord.isEmpty()){
				this.operateRecords.remove(i);
			}
		}
	}
	
	public String getVisitGuid() {
		return visitGuid;
	}

	public void setVisitGuid(String visitGuid) {
		this.visitGuid = visitGuid;
	}

	public Date getPeriOperatePeriodStartDate() {
		return periOperatePeriodStartDate;
	}

	public void setPeriOperatePeriodStartDate(Date periOperatePeriodStartDate) {
		this.periOperatePeriodStartDate = periOperatePeriodStartDate;
	}

	public Date getPeriOperatePeriodEndDate() {
		return periOperatePeriodEndDate;
	}

	public void setPeriOperatePeriodEndDate(Date periOperatePeriodEndDate) {
		this.periOperatePeriodEndDate = periOperatePeriodEndDate;
	}

	public List<OperateRecord> getOperateRecords() {
		return operateRecords;
	}

	public void setOperateRecords(List<OperateRecord> operateRecords) {
		this.operateRecords = operateRecords;
	}
	
	
	
}
