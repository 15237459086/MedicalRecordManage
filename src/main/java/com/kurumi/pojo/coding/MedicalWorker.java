package com.kurumi.pojo.coding;

/**
 * 治疗记录工作人员
 * @author lyh
 *
 */
public class MedicalWorker {

	/**
	 * 职称编号
	 */
	private String professionTitleCode;
	
	/**
	 * 职称名字
	 */
	private String professionTitleName;
	
	/**
	 * 工作人员编号
	 */
	private String medicalWorkerCode;
	
	/**
	 * 工作人员名称
	 */
	private String medicalWorkerName;
	
	/**
	 * 身份证号码
	 */
	private String idNumber;

	public String getProfessionTitleCode() {
		return professionTitleCode;
	}

	public void setProfessionTitleCode(String professionTitleCode) {
		this.professionTitleCode = professionTitleCode;
	}

	public String getProfessionTitleName() {
		return professionTitleName;
	}

	public void setProfessionTitleName(String professionTitleName) {
		this.professionTitleName = professionTitleName;
	}

	public String getMedicalWorkerCode() {
		return medicalWorkerCode;
	}

	public void setMedicalWorkerCode(String medicalWorkerCode) {
		this.medicalWorkerCode = medicalWorkerCode;
	}

	public String getMedicalWorkerName() {
		return medicalWorkerName;
	}

	public void setMedicalWorkerName(String medicalWorkerName) {
		this.medicalWorkerName = medicalWorkerName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	
	
}
