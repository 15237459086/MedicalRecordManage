package com.kurumi.pojo.coding;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 疾病诊断
 * @author lyh
 *
 */
public class DiseaseDiagInfo {

	/**
	 * visitGuid
	 */
	private String visitGuid;
	
	/**
	 * 门诊诊断
	 */
	private DiseaseDiagRecord outpatientDiagRecord = new DiseaseDiagRecord();
	
	/**
	 * 主要诊断
	 */
	private DiseaseDiagRecord mainDiagRecord = new DiseaseDiagRecord();
	
	/**
	 * 损失和中毒原因
	 */
	private DiseaseDiagRecord damageAndVenenationDiagRecord = new DiseaseDiagRecord();
	
	/**
	 * 疾病诊断明细集合
	 */
	private List<DiseaseDiagRecord> diseaseDiagRecords = new ArrayList<DiseaseDiagRecord>();

	@JsonIgnore
	public void clearEmpty(){
		for (int i = this.diseaseDiagRecords.size()-1; i >=0; i--) {
			DiseaseDiagRecord diseaseDiagRecord = this.diseaseDiagRecords.get(i);
			if(diseaseDiagRecord.isEmpty()){
				this.diseaseDiagRecords.remove(i);
			}
		}
	}
	
	public String getVisitGuid() {
		return visitGuid;
	}

	public void setVisitGuid(String visitGuid) {
		this.visitGuid = visitGuid;
	}

	
	
	
	public DiseaseDiagRecord getOutpatientDiagRecord() {
		return outpatientDiagRecord;
	}

	public void setOutpatientDiagRecord(DiseaseDiagRecord outpatientDiagRecord) {
		this.outpatientDiagRecord = outpatientDiagRecord;
	}

	public DiseaseDiagRecord getMainDiagRecord() {
		return mainDiagRecord;
	}

	public void setMainDiagRecord(DiseaseDiagRecord mainDiagRecord) {
		this.mainDiagRecord = mainDiagRecord;
	}

	public DiseaseDiagRecord getDamageAndVenenationDiagRecord() {
		return damageAndVenenationDiagRecord;
	}

	public void setDamageAndVenenationDiagRecord(DiseaseDiagRecord damageAndVenenationDiagRecord) {
		this.damageAndVenenationDiagRecord = damageAndVenenationDiagRecord;
	}

	public List<DiseaseDiagRecord> getDiseaseDiagRecords() {
		return diseaseDiagRecords;
	}

	public void setDiseaseDiagRecords(
			List<DiseaseDiagRecord> diseaseDiagRecords) {
		this.diseaseDiagRecords = diseaseDiagRecords;
	}

	@Override
	public String toString() {
		return "HospitalVisitDiseaseDiagInfo [visitGuid=" + visitGuid + ", diseaseDiagRecords=" + diseaseDiagRecords
				+ "]";
	}
	
	
	
	
}
