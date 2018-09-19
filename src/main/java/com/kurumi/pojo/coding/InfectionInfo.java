package com.kurumi.pojo.coding;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 医院感染信息
 * @author lyh
 *
 */
public class InfectionInfo {
	
	/**
	 * visitGuid
	 */
	private String visitGuid;
	
	/**
	 * 感染总次数
	 */
	private BigDecimal infectionTotalTimes;
	
	/**
	 * 医院感染明细集合
	 */
	private List<InfectionRecord> infectionRecords = new ArrayList<InfectionRecord>();

	public String getVisitGuid() {
		return visitGuid;
	}

	public void setVisitGuid(String visitGuid) {
		this.visitGuid = visitGuid;
	}

	public BigDecimal getInfectionTotalTimes() {
		return infectionTotalTimes;
	}

	public void setInfectionTotalTimes(BigDecimal infectionTotalTimes) {
		this.infectionTotalTimes = infectionTotalTimes;
	}

	public List<InfectionRecord> getInfectionRecords() {
		return infectionRecords;
	}

	public void setInfectionRecords(List<InfectionRecord> infectionRecords) {
		this.infectionRecords = infectionRecords;
	}
	
	
	


}
