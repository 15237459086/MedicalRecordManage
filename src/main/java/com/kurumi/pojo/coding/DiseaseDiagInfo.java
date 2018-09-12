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
	 * 疾病诊断明细集合
	 */
	private List<DiseaseDiagRecord> diseaseDiagRecords = new ArrayList<DiseaseDiagRecord>();
	
	/**
	 * 损伤、中毒外部原因
	 */
	private List<DiseaseDiagRecord> damageAndVenenationDiagRecords = new ArrayList<DiseaseDiagRecord>();
	
	/**
	 * 病理诊断
	 */
	private List<PathologyDiseaseDiagRecord> pathologyDiagRecords = new ArrayList<PathologyDiseaseDiagRecord>();
	
	

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


	public List<DiseaseDiagRecord> getDamageAndVenenationDiagRecords() {
		return damageAndVenenationDiagRecords;
	}

	public void setDamageAndVenenationDiagRecords(List<DiseaseDiagRecord> damageAndVenenationDiagRecords) {
		this.damageAndVenenationDiagRecords = damageAndVenenationDiagRecords;
	}

	public List<PathologyDiseaseDiagRecord> getPathologyDiagRecords() {
		return pathologyDiagRecords;
	}

	public void setPathologyDiagRecords(List<PathologyDiseaseDiagRecord> pathologyDiagRecords) {
		this.pathologyDiagRecords = pathologyDiagRecords;
	}

	public List<DiseaseDiagRecord> getDiseaseDiagRecords() {
		return diseaseDiagRecords;
	}

	public void setDiseaseDiagRecords(
			List<DiseaseDiagRecord> diseaseDiagRecords) {
		this.diseaseDiagRecords = diseaseDiagRecords;
	}

	
	
	
	
}
