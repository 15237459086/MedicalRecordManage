package com.kurumi.pojo.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * 转科信息
 * @author lyh
 *
 */
public class TransferDeptInfo {

	/**
	 * visitGuid
	 */
	private String visitGuid;
	
	/**
	 * 转科记录集合
	 */
	private List<TransferDeptRecord> transferDeptRecords = new ArrayList<TransferDeptRecord>();

	public String getVisitGuid() {
		return visitGuid;
	}

	public void setVisitGuid(String visitGuid) {
		this.visitGuid = visitGuid;
	}

	public List<TransferDeptRecord> getTransferDeptRecords() {
		return transferDeptRecords;
	}

	public void setTransferDeptRecords(List<TransferDeptRecord> transferDeptRecords) {
		this.transferDeptRecords = transferDeptRecords;
	}
	
	

}
