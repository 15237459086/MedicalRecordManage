package com.kurumi.pojo.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * 输血信息
 * @author lyh
 *
 */
public class InfusionBloodInfo {

	/**
	 * visitGuid
	 */
	private String visitGuid;
	
	/**
	 * 输血次数
	 */
	private Integer infusionBloodTimes;
	
	/**
	 * 输血反应次数
	 */
	private Integer infusionBloodReactTimes;
	
	/**
	 * 输血记录集合
	 */
	private List<InfusionBloodRecord> infusionBloodRecords = new ArrayList<InfusionBloodRecord>();

	public String getVisitGuid() {
		return visitGuid;
	}

	public void setVisitGuid(String visitGuid) {
		this.visitGuid = visitGuid;
	}

	public Integer getInfusionBloodTimes() {
		return infusionBloodTimes;
	}

	public void setInfusionBloodTimes(Integer infusionBloodTimes) {
		this.infusionBloodTimes = infusionBloodTimes;
	}

	public Integer getInfusionBloodReactTimes() {
		return infusionBloodReactTimes;
	}

	public void setInfusionBloodReactTimes(Integer infusionBloodReactTimes) {
		this.infusionBloodReactTimes = infusionBloodReactTimes;
	}

	public List<InfusionBloodRecord> getInfusionBloodRecords() {
		return infusionBloodRecords;
	}

	public void setInfusionBloodRecords(List<InfusionBloodRecord> infusionBloodRecords) {
		this.infusionBloodRecords = infusionBloodRecords;
	}

	
	
}
