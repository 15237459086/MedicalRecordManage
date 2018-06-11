package com.kurumi.pojo.coding;

import java.math.BigDecimal;

/**
 * 住院费用明细
 * @author lyh
 *
 */
public class CostRecord {

	/**
	 * 费用类型编号
	 */
	private String medicalCostTypeCode;
	
	/**
	 * 费用类型名称
	 */
	private String medicalCostTypeName;
	
	/**
	 * 费用类型金额
	 */
	private BigDecimal costMoney;

	public String getMedicalCostTypeCode() {
		return medicalCostTypeCode;
	}

	public void setMedicalCostTypeCode(String medicalCostTypeCode) {
		this.medicalCostTypeCode = medicalCostTypeCode;
	}

	public String getMedicalCostTypeName() {
		return medicalCostTypeName;
	}

	public void setMedicalCostTypeName(String medicalCostTypeName) {
		this.medicalCostTypeName = medicalCostTypeName;
	}

	public BigDecimal getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(BigDecimal costMoney) {
		this.costMoney = costMoney;
	}
	
	
}
