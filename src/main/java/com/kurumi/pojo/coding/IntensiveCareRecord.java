package com.kurumi.pojo.coding;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurumi.util.StringUtil;
/**
 * 重症监控明细
 * @author lyh
 *
 */
public class IntensiveCareRecord {

	/**
	 * ICU类型编号
	 */
	private String icuTypeCode;
	
	/**
	 * ICU类型名称
	 */
	private String icuTypeName;
	
	/**
	 * 进入ICU时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date inIcuDateTime;
	
	/**
	 * 退出ICU时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date outIcuDateTime;
	
	/**
	 * 是否计划再次重返ICU编号
	 */
	private String reIcuPlanCode;
	
	/**
	 * 是否计划再次重返ICU名称
	 */
	private String reIcuPlanName;
	
	/**
	 * APACHEII评分
	 */
	private BigDecimal apacheScore;
	
	/**
	 * 患者是否死亡编号
	 */
	private String icuDeathCode;
	
	/**
	 * 患者是否死亡名称
	 */
	private String icuDeathName;
	
	
	/**
	 * 计划再次入住原因
	 */
	private String reIcuComment;

	
	@JsonIgnore
    public boolean isEmpty(){
    	if(this.getIcuTypeName() == null && this.getInIcuDateTime() == null
    			&& this.getOutIcuDateTime() == null){
    		return true;
    	}
    	return false;
    }


	public String getIcuTypeCode() {
		return StringUtil.meaningStr(icuTypeCode);
	}


	public void setIcuTypeCode(String icuTypeCode) {
		this.icuTypeCode = icuTypeCode;
	}


	public String getIcuTypeName() {
		return StringUtil.meaningStr(icuTypeName);
	}


	public void setIcuTypeName(String icuTypeName) {
		this.icuTypeName = icuTypeName;
	}


	public Date getInIcuDateTime() {
		return inIcuDateTime;
	}


	public void setInIcuDateTime(Date inIcuDateTime) {
		this.inIcuDateTime = inIcuDateTime;
	}


	public Date getOutIcuDateTime() {
		return outIcuDateTime;
	}


	public void setOutIcuDateTime(Date outIcuDateTime) {
		this.outIcuDateTime = outIcuDateTime;
	}


	public String getReIcuPlanCode() {
		return StringUtil.meaningStr(reIcuPlanCode);
	}


	public void setReIcuPlanCode(String reIcuPlanCode) {
		this.reIcuPlanCode = reIcuPlanCode;
	}


	public String getReIcuPlanName() {
		return StringUtil.meaningStr(reIcuPlanName);
	}


	public void setReIcuPlanName(String reIcuPlanName) {
		this.reIcuPlanName = reIcuPlanName;
	}


	public BigDecimal getApacheScore() {
		return apacheScore;
	}


	public void setApacheScore(BigDecimal apacheScore) {
		this.apacheScore = apacheScore;
	}


	public String getIcuDeathCode() {
		return StringUtil.meaningStr(icuDeathCode);
	}


	public void setIcuDeathCode(String icuDeathCode) {
		this.icuDeathCode = icuDeathCode;
	}


	public String getIcuDeathName() {
		return StringUtil.meaningStr(icuDeathName);
	}


	public void setIcuDeathName(String icuDeathName) {
		this.icuDeathName = icuDeathName;
	}


	public String getReIcuComment() {
		return StringUtil.meaningStr(reIcuComment);
	}


	public void setReIcuComment(String reIcuComment) {
		this.reIcuComment = reIcuComment;
	}
	
}
