package com.kurumi.pojo.coding;

import java.util.ArrayList;
import java.util.List;
/**
 * 孕产信息
 * @author lyh
 *
 */
public class MotherhoodInfo {

	private String visitGuid;
	
	/**
	 * 孕产记录集合
	 */
	private List<MotherhoodRecord> motherhoodRecords = new ArrayList<MotherhoodRecord>();

	public String getVisitGuid() {
		return visitGuid;
	}

	public void setVisitGuid(String visitGuid) {
		this.visitGuid = visitGuid;
	}

	public List<MotherhoodRecord> getMotherhoodRecords() {
		return motherhoodRecords;
	}

	public void setMotherhoodRecords(List<MotherhoodRecord> motherhoodRecords) {
		this.motherhoodRecords = motherhoodRecords;
	}
	
	
	
}
