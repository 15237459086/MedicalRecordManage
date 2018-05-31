package com.kurumi.pojo.coding;

/**
 * 过敏药物记录
 * @author lyh
 *
 */
public class DrugAllergyRecord {

	/**
	 * 过敏药物类型编号
	 */
	private String drugAllergyTypeCode;
	
	/**
	 * 过敏药物类型名称
	 */
	private String drugAllergyTypeName;
	
	/**
	 * 过敏药物名称
	 */
	private String drugAllergyName;

	public String getDrugAllergyTypeCode() {
		return drugAllergyTypeCode;
	}

	public void setDrugAllergyTypeCode(String drugAllergyTypeCode) {
		this.drugAllergyTypeCode = drugAllergyTypeCode;
	}

	public String getDrugAllergyTypeName() {
		return drugAllergyTypeName;
	}

	public void setDrugAllergyTypeName(String drugAllergyTypeName) {
		this.drugAllergyTypeName = drugAllergyTypeName;
	}

	public String getDrugAllergyName() {
		return drugAllergyName;
	}

	public void setDrugAllergyName(String drugAllergyName) {
		this.drugAllergyName = drugAllergyName;
	}
	
	
	
	
}
