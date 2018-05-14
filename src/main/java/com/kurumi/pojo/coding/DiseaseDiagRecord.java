package com.kurumi.pojo.coding;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurumi.util.StringUtil;

/**
 * 疾病诊断明细
 * @author lyh
 *
 */
public class DiseaseDiagRecord {

	/**
	 * 诊断类型编号
	 */
	private String diagTypeCode;
	
	/**
	 * 疾病诊断原始编号
	 */
	private String diseaseDiagOriginalCode;
	
	/**
	 * 疾病诊断原始描述
	 */
	private String diseaseDiagOriginalDesc;
	
	/**
	 * 诊断类型名称
	 */
	private String diagTypeName;
	
	/**
	 * 疾病诊断编号
	 */
	private String diseaseDiagCode;
	
	/**
	 * 疾病诊断名称
	 */
	private String diseaseDiagName;
	
	/**
	 * 治疗结果编号
	 */
	private String treatResultCode;
	
	/**
	 * 治疗结果名称
	 */
	private String treatResultName;
	
	/**
     *确诊日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date confirmedDateTime;
    
    /**
	 * 治疗天数
	 */
    private BigDecimal treatDayNumber;
    
    
    /**
   	 * 手术标志编号
   	 */
   	private String operateMarkCode;
   	
   	/**
   	 * 手术标志名称
   	 */
   	private String operateMarkName;
    
    /**
	 * 诊断排序
	 */
    private Integer diagSortIndex;
    
    /**
	 * 是否可删除标志 0:true;1:false;
	 */
    private Integer operatingLevel = 0;
    
    @JsonIgnore
    public boolean isEmpty(){
    	if(this.getDiseaseDiagName() == null
    			&& this.getConfirmedDateTime() == null && this.getTreatResultName() == null
    			&& this.getDiseaseDiagOriginalDesc() == null){
    		return true;
    	}
    	return false;
    }

	public String getDiagTypeCode() {
		return StringUtil.meaningStr(diagTypeCode);
	}

	public void setDiagTypeCode(String diagTypeCode) {
		this.diagTypeCode = diagTypeCode;
	}

	public String getDiagTypeName() {
		return StringUtil.meaningStr(diagTypeName);
	}

	public void setDiagTypeName(String diagTypeName) {
		this.diagTypeName = diagTypeName;
	}

	public String getDiseaseDiagCode() {
		return StringUtil.meaningStr(diseaseDiagCode);
	}

	public void setDiseaseDiagCode(String diseaseDiagCode) {
		this.diseaseDiagCode = diseaseDiagCode;
	}

	public String getDiseaseDiagName() {
		return StringUtil.meaningStr(diseaseDiagName);
	}

	public void setDiseaseDiagName(String diseaseDiagName) {
		this.diseaseDiagName = diseaseDiagName;
	}

	public String getTreatResultCode() {
		return StringUtil.meaningStr(treatResultCode);
	}

	public void setTreatResultCode(String treatResultCode) {
		this.treatResultCode = treatResultCode;
	}

	public String getTreatResultName() {
		return StringUtil.meaningStr(treatResultName);
	}

	public void setTreatResultName(String treatResultName) {
		this.treatResultName = treatResultName;
	}

	public Date getConfirmedDateTime() {
		return confirmedDateTime;
	}

	public void setConfirmedDateTime(Date confirmedDateTime) {
		this.confirmedDateTime = confirmedDateTime;
	}

	public BigDecimal getTreatDayNumber() {
		return treatDayNumber;
	}

	public void setTreatDayNumber(BigDecimal treatDayNumber) {
		this.treatDayNumber = treatDayNumber;
	}

	public Integer getDiagSortIndex() {
		return diagSortIndex;
	}

	public void setDiagSortIndex(Integer diagSortIndex) {
		this.diagSortIndex = diagSortIndex;
	}
    
	public String getDiseaseDiagOriginalDesc() {
		return diseaseDiagOriginalDesc;
	}

	public void setDiseaseDiagOriginalDesc(String diseaseDiagOriginalDesc) {
		this.diseaseDiagOriginalDesc = diseaseDiagOriginalDesc;
	}

	
	
	
	public String getOperateMarkCode() {
		return operateMarkCode;
	}

	public void setOperateMarkCode(String operateMarkCode) {
		this.operateMarkCode = operateMarkCode;
	}

	public String getOperateMarkName() {
		return operateMarkName;
	}

	public void setOperateMarkName(String operateMarkName) {
		this.operateMarkName = operateMarkName;
	}
	
	



	public Integer getOperatingLevel() {
		return operatingLevel;
	}

	public void setOperatingLevel(Integer operatingLevel) {
		this.operatingLevel = operatingLevel;
	}





	public String getDiseaseDiagOriginalCode() {
		return diseaseDiagOriginalCode;
	}

	public void setDiseaseDiagOriginalCode(String diseaseDiagOriginalCode) {
		this.diseaseDiagOriginalCode = diseaseDiagOriginalCode;
	}





	@SuppressWarnings("rawtypes")
	public static class DiseaseDiagRecordComparator implements Comparator{

		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			DiseaseDiagRecord  diseaseDiag1 = (DiseaseDiagRecord)o1;
			DiseaseDiagRecord  diseaseDiag2 = (DiseaseDiagRecord)o2;
			if(diseaseDiag1.getDiagSortIndex() != null && diseaseDiag2.getDiagSortIndex() != null){
				return diseaseDiag1.getDiagSortIndex().compareTo(diseaseDiag2.getDiagSortIndex());
			}else if(diseaseDiag1.getDiagSortIndex() == null && diseaseDiag2.getDiagSortIndex() != null){
				return 1;
			}else if(diseaseDiag1.getDiagSortIndex() != null && diseaseDiag2.getDiagSortIndex() == null){
				return -1;
			}else{
				return 0;
			}
			
		}
    	
    }




	@Override
	public String toString() {
		return "HospitalVisitDiseaseDiagRecord [diagTypeCode=" + diagTypeCode + ", diseaseDiagOriginalDesc="
				+ diseaseDiagOriginalDesc + ", diagTypeName=" + diagTypeName + ", diseaseDiagCode=" + diseaseDiagCode
				+ ", diseaseDiagName=" + diseaseDiagName + ", treatResultCode=" + treatResultCode + ", treatResultName="
				+ treatResultName + ", confirmedDateTime=" + confirmedDateTime + ", treatDayNumber=" + treatDayNumber
				+ ", operateMarkCode=" + operateMarkCode + ", operateMarkName=" + operateMarkName + ", diagSortIndex="
				+ diagSortIndex + "]";
	}
	
	
	
}
