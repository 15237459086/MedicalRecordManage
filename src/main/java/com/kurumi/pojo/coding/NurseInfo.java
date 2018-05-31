package com.kurumi.pojo.coding;

import java.math.BigDecimal;

/**
 * 护理信息
 * @author lyh
 *
 */
public class NurseInfo {

	/**
	 * visitGuid
	 */
	private String visitGuid;
	
	/**
	 * 病危天数
	 */
	private BigDecimal criticalDayNumber;
	
	/**
	 * 病重天数
	 */
	private BigDecimal sickDayNumber;
	
	/**
	 * 特级护理天数
	 */
	private BigDecimal specialNurseDayNumber;
	
	/**
	 * 一级护理天数
	 */
	private BigDecimal firstLevelNurseDayNumber;
	
	/**
	 * 二级护理天数
	 */
	private BigDecimal secondLevelNurseDayNumber;
	
	/**
	 * 三级护理天数
	 */
	private BigDecimal threeLevelNurseDayNumber;

	public String getVisitGuid() {
		return visitGuid;
	}

	public void setVisitGuid(String visitGuid) {
		this.visitGuid = visitGuid;
	}

	public BigDecimal getCriticalDayNumber() {
		return criticalDayNumber;
	}

	public void setCriticalDayNumber(BigDecimal criticalDayNumber) {
		this.criticalDayNumber = criticalDayNumber;
	}

	public BigDecimal getSickDayNumber() {
		return sickDayNumber;
	}

	public void setSickDayNumber(BigDecimal sickDayNumber) {
		this.sickDayNumber = sickDayNumber;
	}

	public BigDecimal getSpecialNurseDayNumber() {
		return specialNurseDayNumber;
	}

	public void setSpecialNurseDayNumber(BigDecimal specialNurseDayNumber) {
		this.specialNurseDayNumber = specialNurseDayNumber;
	}

	public BigDecimal getFirstLevelNurseDayNumber() {
		return firstLevelNurseDayNumber;
	}

	public void setFirstLevelNurseDayNumber(BigDecimal firstLevelNurseDayNumber) {
		this.firstLevelNurseDayNumber = firstLevelNurseDayNumber;
	}

	public BigDecimal getSecondLevelNurseDayNumber() {
		return secondLevelNurseDayNumber;
	}

	public void setSecondLevelNurseDayNumber(BigDecimal secondLevelNurseDayNumber) {
		this.secondLevelNurseDayNumber = secondLevelNurseDayNumber;
	}

	public BigDecimal getThreeLevelNurseDayNumber() {
		return threeLevelNurseDayNumber;
	}

	public void setThreeLevelNurseDayNumber(BigDecimal threeLevelNurseDayNumber) {
		this.threeLevelNurseDayNumber = threeLevelNurseDayNumber;
	}
	
	
	
	
}
