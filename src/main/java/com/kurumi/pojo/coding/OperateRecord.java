package com.kurumi.pojo.coding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurumi.util.StringUtil;

/**
 * 手术记录明细
 * @author lyh
 *
 */
public class OperateRecord {

	/**
	 * 手术开始日期
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date operateStartDate;
	
	/**
	 * 手术结束日期
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date operateEndDate;
	
	/**
	 * 手术原始编号
	 */
	private String operateOriginalCode;
	
	/**
	 * 手术原始描述
	 */
	private String operateOriginalDesc;
	
	/**
	 * 手术编码
	 */
	private String operateCode;
	
	/**
	 * 手术名称
	 */
	private String operateName;
	
	/**
	 * 手术版本
	 */
	private String operateVersion;
	
	/**
	 * 合并手术1
	 */
	private String mergeOperateCode1;
	
	

	/**
	 * 合并手术名称1
	 */
	private String mergeOperateName1;
	
	
	/**
	 * 合并手术2
	 */
	private String mergeOperateCode2;
	
	/**
	 * 合并手术名称2
	 */
	private String mergeOperateName2;
	
	
	/**
	 * 合并手术3
	 */
	private String mergeOperateCode3;
	
	/**
	 * 合并手术名称3
	 */
	private String mergeOperateName3;
	
	/**
	 * 手术切口等级编号
	 */
	private String incisionLevelCode;
	
	/**
	 * 手术切口等级名称
	 */
	private String incisionLevelName;
	
	/**
	 * 愈合类别编号
	 */
	private String cicatrizeTypeCode;
	
	/**
	 * 愈合类别名称
	 */
	private String cicatrizeTypeName;
	
	/**
	 * 手术级别编号
	 */
	private String opsLevelCode;
	
	/**
	 * 手术级别名称
	 */
	private String opsLevelName;
	
	
	/**
	 * 麻醉分级编号
	 */
	private String anaesthesiaLevelCode;
	
	/**
	 * 麻醉分级名称
	 */
	private String anaesthesiaLevelName;
	
	/**
	 * 麻醉方式编号
	 */
	private String anaesthesiaTypeCode;
	
	/**
	 * 麻醉方式名称
	 */
	private String anaesthesiaTypeName;
	
	/**
	 * 诊断排序
	 */
    private Integer operateSortIndex;
	
	/**
	 * 手术医师集合
	 */
	private List<MedicalWorker> operateWorkers = new ArrayList<MedicalWorker>();

	/**
	 * 数据来源编号
	 */
	private String dataSourcesCode;
	
	/**
	 * 数据来源名称
	 */
	private String dataSourcesName;
	
	/**
	 * 是否页面显示（原始诊断若页面删除时，不能真正删除，不显示）
	 */
	private String pageShow;
	
	@JsonIgnore
    public boolean isEmpty(){
    	if(this.getOperateName() == null && this.getOperateOriginalDesc() == null){
    		return true;
    	}
    	return false;
    }

	public Date getOperateStartDate() {
		return operateStartDate;
	}

	public void setOperateStartDate(Date operateStartDate) {
		this.operateStartDate = operateStartDate;
	}

	public Date getOperateEndDate() {
		return operateEndDate;
	}

	public void setOperateEndDate(Date operateEndDate) {
		this.operateEndDate = operateEndDate;
	}

	public String getOperateCode() {
		return StringUtil.meaningStr(operateCode);
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}

	public String getOperateName() {
		return StringUtil.meaningStr(operateName);
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getMergeOperateCode1() {
		return mergeOperateCode1;
	}

	public void setMergeOperateCode1(String mergeOperateCode1) {
		this.mergeOperateCode1 = mergeOperateCode1;
	}

	public String getMergeOperateName1() {
		return mergeOperateName1;
	}

	public void setMergeOperateName1(String mergeOperateName1) {
		this.mergeOperateName1 = mergeOperateName1;
	}

	public String getMergeOperateCode2() {
		return mergeOperateCode2;
	}

	public void setMergeOperateCode2(String mergeOperateCode2) {
		this.mergeOperateCode2 = mergeOperateCode2;
	}

	public String getMergeOperateName2() {
		return mergeOperateName2;
	}

	public void setMergeOperateName2(String mergeOperateName2) {
		this.mergeOperateName2 = mergeOperateName2;
	}

	public String getMergeOperateCode3() {
		return mergeOperateCode3;
	}

	public void setMergeOperateCode3(String mergeOperateCode3) {
		this.mergeOperateCode3 = mergeOperateCode3;
	}

	public String getMergeOperateName3() {
		return mergeOperateName3;
	}

	public void setMergeOperateName3(String mergeOperateName3) {
		this.mergeOperateName3 = mergeOperateName3;
	}

	public String getIncisionLevelCode() {
		return incisionLevelCode;
	}

	public void setIncisionLevelCode(String incisionLevelCode) {
		this.incisionLevelCode = incisionLevelCode;
	}

	public String getIncisionLevelName() {
		return incisionLevelName;
	}

	public void setIncisionLevelName(String incisionLevelName) {
		this.incisionLevelName = incisionLevelName;
	}

	public String getCicatrizeTypeCode() {
		return cicatrizeTypeCode;
	}

	public void setCicatrizeTypeCode(String cicatrizeTypeCode) {
		this.cicatrizeTypeCode = cicatrizeTypeCode;
	}

	public String getCicatrizeTypeName() {
		return cicatrizeTypeName;
	}

	public void setCicatrizeTypeName(String cicatrizeTypeName) {
		this.cicatrizeTypeName = cicatrizeTypeName;
	}

	public String getOpsLevelCode() {
		return opsLevelCode;
	}

	public void setOpsLevelCode(String opsLevelCode) {
		this.opsLevelCode = opsLevelCode;
	}

	public String getOpsLevelName() {
		return opsLevelName;
	}

	public void setOpsLevelName(String opsLevelName) {
		this.opsLevelName = opsLevelName;
	}

	public String getAnaesthesiaLevelCode() {
		return anaesthesiaLevelCode;
	}

	public void setAnaesthesiaLevelCode(String anaesthesiaLevelCode) {
		this.anaesthesiaLevelCode = anaesthesiaLevelCode;
	}

	public String getAnaesthesiaLevelName() {
		return anaesthesiaLevelName;
	}

	public void setAnaesthesiaLevelName(String anaesthesiaLevelName) {
		this.anaesthesiaLevelName = anaesthesiaLevelName;
	}

	public String getAnaesthesiaTypeCode() {
		return anaesthesiaTypeCode;
	}

	public void setAnaesthesiaTypeCode(String anaesthesiaTypeCode) {
		this.anaesthesiaTypeCode = anaesthesiaTypeCode;
	}

	public String getAnaesthesiaTypeName() {
		return anaesthesiaTypeName;
	}

	public void setAnaesthesiaTypeName(String anaesthesiaTypeName) {
		this.anaesthesiaTypeName = anaesthesiaTypeName;
	}

	public List<MedicalWorker> getOperateWorkers() {
		return operateWorkers;
	}

	public void setOperateWorkers(List<MedicalWorker> operateWorkers) {
		this.operateWorkers = operateWorkers;
	}

	public String getOperateOriginalDesc() {
		return operateOriginalDesc;
	}

	public void setOperateOriginalDesc(String operateOriginalDesc) {
		this.operateOriginalDesc = operateOriginalDesc;
	}

	public Integer getOperateSortIndex() {
		return operateSortIndex;
	}

	public void setOperateSortIndex(Integer operateSortIndex) {
		this.operateSortIndex = operateSortIndex;
	}
	

	public String getOperateOriginalCode() {
		return operateOriginalCode;
	}

	public void setOperateOriginalCode(String operateOriginalCode) {
		this.operateOriginalCode = operateOriginalCode;
	}

	public String getOperateVersion() {
		return operateVersion;
	}

	public void setOperateVersion(String operateVersion) {
		this.operateVersion = operateVersion;
	}

	public String getDataSourcesCode() {
		return dataSourcesCode;
	}

	public void setDataSourcesCode(String dataSourcesCode) {
		this.dataSourcesCode = dataSourcesCode;
	}

	public String getDataSourcesName() {
		return dataSourcesName;
	}

	public void setDataSourcesName(String dataSourcesName) {
		this.dataSourcesName = dataSourcesName;
	}

	public String getPageShow() {
		return pageShow;
	}

	public void setPageShow(String pageShow) {
		this.pageShow = pageShow;
	}
	
	
}
