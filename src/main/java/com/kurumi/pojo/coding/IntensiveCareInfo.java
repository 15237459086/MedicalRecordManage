package com.kurumi.pojo.coding;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 重症监控
 * @author lyh
 *
 */
public class IntensiveCareInfo {

	/**
	 * visitGuid
	 */
	private String visitGuid;
	
	/**
	 * ICU理天数
	 */
	private BigDecimal icuNurseDayNumber;
	
	/**
	 * CCU理天数
	 */
	private BigDecimal ccuNurseDayNumber;
	
	/**
	 * 重症监护明细集合
	 */
	private List<IntensiveCareRecord> intensiveCareRecords = new ArrayList<IntensiveCareRecord>();

	
	@JsonIgnore
	public void clearEmpty(){
		for (int i = this.intensiveCareRecords.size()-1; i >=0; i--) {
			IntensiveCareRecord intensiveCareRecord = this.intensiveCareRecords.get(i);
			if(intensiveCareRecord.isEmpty()){
				this.intensiveCareRecords.remove(i);
			}
		}
	}
	
	
	public String getVisitGuid() {
		return visitGuid;
	}

	public void setVisitGuid(String visitGuid) {
		this.visitGuid = visitGuid;
	}

	
	public BigDecimal getIcuNurseDayNumber() {
		return icuNurseDayNumber;
	}

	public void setIcuNurseDayNumber(BigDecimal icuNurseDayNumber) {
		this.icuNurseDayNumber = icuNurseDayNumber;
	}

	public BigDecimal getCcuNurseDayNumber() {
		return ccuNurseDayNumber;
	}

	public void setCcuNurseDayNumber(BigDecimal ccuNurseDayNumber) {
		this.ccuNurseDayNumber = ccuNurseDayNumber;
	}

	public List<IntensiveCareRecord> getIntensiveCareRecords() {
		return intensiveCareRecords;
	}

	public void setIntensiveCareRecords(
			List<IntensiveCareRecord> intensiveCareRecords) {
		this.intensiveCareRecords = intensiveCareRecords;
	}
	
}
