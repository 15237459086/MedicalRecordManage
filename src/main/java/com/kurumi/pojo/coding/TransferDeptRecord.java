package com.kurumi.pojo.coding;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 专科记录
 * @author lyh
 *
 */
public class TransferDeptRecord {

	/**
	 * 转出科室编号
	 */
	private String outDeptCode;
	
	/**
	 * 转出科室名称
	 */
	private String outDeptName;
	
	/**
	 * 转出时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date transferOutDeptDateTime;
	
	/**
	 * 转入科室编号
	 */
	private String inDeptCode;
	
	/**
	 * 转入科室名称
	 */
	private String inDeptName;
	
	/**
	 * 转入时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date transferInDeptDateTime;
	
	/**
	 * 转科时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date transferDeptDateTime;
	
	/**
	 * 转出科室时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date transferDeptOutDateTime;
	
	/**
	 * 转入科室时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date transferDeptInDateTime;
	
	/**
	 * 转科原因
	 */
	private String transferDeptRemarks;

	public String getOutDeptCode() {
		return outDeptCode;
	}

	public void setOutDeptCode(String outDeptCode) {
		this.outDeptCode = outDeptCode;
	}

	public String getOutDeptName() {
		return outDeptName;
	}

	public void setOutDeptName(String outDeptName) {
		this.outDeptName = outDeptName;
	}

	public Date getTransferOutDeptDateTime() {
		return transferOutDeptDateTime;
	}

	public void setTransferOutDeptDateTime(Date transferOutDeptDateTime) {
		this.transferOutDeptDateTime = transferOutDeptDateTime;
	}

	public String getInDeptCode() {
		return inDeptCode;
	}

	public void setInDeptCode(String inDeptCode) {
		this.inDeptCode = inDeptCode;
	}

	public String getInDeptName() {
		return inDeptName;
	}

	public void setInDeptName(String inDeptName) {
		this.inDeptName = inDeptName;
	}

	public Date getTransferInDeptDateTime() {
		return transferInDeptDateTime;
	}

	public void setTransferInDeptDateTime(Date transferInDeptDateTime) {
		this.transferInDeptDateTime = transferInDeptDateTime;
	}

	public Date getTransferDeptDateTime() {
		return transferDeptDateTime;
	}

	public void setTransferDeptDateTime(Date transferDeptDateTime) {
		this.transferDeptDateTime = transferDeptDateTime;
	}

	public Date getTransferDeptOutDateTime() {
		return transferDeptOutDateTime;
	}

	public void setTransferDeptOutDateTime(Date transferDeptOutDateTime) {
		this.transferDeptOutDateTime = transferDeptOutDateTime;
	}

	public Date getTransferDeptInDateTime() {
		return transferDeptInDateTime;
	}

	public void setTransferDeptInDateTime(Date transferDeptInDateTime) {
		this.transferDeptInDateTime = transferDeptInDateTime;
	}

	public String getTransferDeptRemarks() {
		return transferDeptRemarks;
	}

	public void setTransferDeptRemarks(String transferDeptRemarks) {
		this.transferDeptRemarks = transferDeptRemarks;
	}
	
	
	
	
	
}
