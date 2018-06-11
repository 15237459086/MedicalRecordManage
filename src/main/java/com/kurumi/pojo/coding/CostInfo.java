package com.kurumi.pojo.coding;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 住院费用
 * @author lyh
 *
 */
public class CostInfo {
	
	/**
	 * visitGuid
	 */
	private String visitGuid;
	
	/**
	 * 住院总费用
	 */
	private BigDecimal totalCost;
	
	/**
	 * 自付金额
	 */
	private BigDecimal selfCost;
	
	/**
	 * 实付费用
	 */
	private BigDecimal actuallyCost;
	
	/**
	 * 费用记录集合
	 */
	private List<CostRecord> costRecords = new ArrayList<CostRecord>();

	public String getVisitGuid() {
		return visitGuid;
	}

	public void setVisitGuid(String visitGuid) {
		this.visitGuid = visitGuid;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BigDecimal getSelfCost() {
		return selfCost;
	}

	public void setSelfCost(BigDecimal selfCost) {
		this.selfCost = selfCost;
	}

	public BigDecimal getActuallyCost() {
		return actuallyCost;
	}

	public void setActuallyCost(BigDecimal actuallyCost) {
		this.actuallyCost = actuallyCost;
	}

	public List<CostRecord> getCostRecords() {
		return costRecords;
	}

	public void setCostRecords(List<CostRecord> costRecords) {
		this.costRecords = costRecords;
	}
	

	
	
	
}
