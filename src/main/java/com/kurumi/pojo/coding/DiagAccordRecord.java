package com.kurumi.pojo.coding;

/**
 * 诊断对比记录
 * @author lyh
 *
 */
public class DiagAccordRecord {

	
	/**
	 * 诊断对比编号
	 */
	private String diagCompareCode;
	
	/**
	 * 诊断对比名称
	 */
	private String diagCompareName;
	
	/**
	 * 诊断对比符合编号
	 */
	private String diagAccordCode;
	
	/**
	 * 诊断对比符合名称
	 */
	private String diagAccordName;

	public String getDiagCompareCode() {
		return diagCompareCode;
	}

	public void setDiagCompareCode(String diagCompareCode) {
		this.diagCompareCode = diagCompareCode;
	}

	public String getDiagCompareName() {
		return diagCompareName;
	}

	public void setDiagCompareName(String diagCompareName) {
		this.diagCompareName = diagCompareName;
	}

	public String getDiagAccordCode() {
		return diagAccordCode;
	}

	public void setDiagAccordCode(String diagAccordCode) {
		this.diagAccordCode = diagAccordCode;
	}

	public String getDiagAccordName() {
		return diagAccordName;
	}

	public void setDiagAccordName(String diagAccordName) {
		this.diagAccordName = diagAccordName;
	}
	
	
}
