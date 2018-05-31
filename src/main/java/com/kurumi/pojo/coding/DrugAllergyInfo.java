package com.kurumi.pojo.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * 过敏药物信息
 * @author lyh
 *
 */
public class DrugAllergyInfo {
	
	/**
	 * visitGuid
	 */
	private String visitGuid;
	
	/**
	 * 过敏药物记录集合
	 */
	private List<DrugAllergyRecord> drugAllergyRecords = new ArrayList<DrugAllergyRecord>();

	public String getVisitGuid() {
		return visitGuid;
	}

	public void setVisitGuid(String visitGuid) {
		this.visitGuid = visitGuid;
	}

	public List<DrugAllergyRecord> getDrugAllergyRecords() {
		return drugAllergyRecords;
	}

	public void setDrugAllergyRecords(List<DrugAllergyRecord> drugAllergyRecords) {
		this.drugAllergyRecords = drugAllergyRecords;
	}
	
	

}
