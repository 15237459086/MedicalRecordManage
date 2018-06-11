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
	 * 手术标识代码
	 */
	private String operatMarkCode;
	
	/**
	 * 手术标识代码
	 */
	private String operatMarkName;
	
	/**
	 * 手术部位代码
	 */
	private String operateBodyPartCode;
	
	/**
	 * 手术部位代码
	 */
	private String operateBodyPartName;
	
	/**
	 * 切口感染部位代码
	 */
	private String surgicalSiteInfectionCode;
	
	/**
	 * 切口感染部位名称
	 */
	private String surgicalSiteInfectionName;
	
	/**
	 * 患者来源代码
	 */
	private String patientSourceCode;
	
	/**
	 * 患者来源名称
	 */
	private String patientSourceName;
	
	/**
	 * 术后并发症代码
	 */
	private String operatedComplicationCode;
	
	/**
	 * 术后并发症名称
	 */
	private String operatedComplicationName;
	
	/**
	 * 是否重返手术室计划代码
	 */
	private String haveReoperateRoomPlanCode;
	
	
	/**
	 * 是否重返手术室计划名称
	 */
	private String haveReoperateRoomPlanName;
	
	/**
	 * 重返手术室目的代码
	 */
	private String reoperateRoomAimCode;
	
	/**
	 * 重返手术室目的名称
	 */
	private String reoperateRoomAimName;
	
	
	/**
	 * 手术风险等级代码
	 */
	private String operateRiskLevelCode;
	
	/**
	 * 手术风险等级名称
	 */
	private String operateRiskLevelName;
	
	
	/**
	 * 麻醉非预期事件代码
	 */
	private String anaesthesiaUnintendedCode;
	
	/**
	 * 麻醉非预期事件名称
	 */
	private String anaesthesiaUnintendedName;
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

	public String getOperatMarkCode() {
		return operatMarkCode;
	}

	public void setOperatMarkCode(String operatMarkCode) {
		this.operatMarkCode = operatMarkCode;
	}

	public String getOperatMarkName() {
		return operatMarkName;
	}

	public void setOperatMarkName(String operatMarkName) {
		this.operatMarkName = operatMarkName;
	}

	public String getOperateBodyPartCode() {
		return operateBodyPartCode;
	}

	public void setOperateBodyPartCode(String operateBodyPartCode) {
		this.operateBodyPartCode = operateBodyPartCode;
	}

	public String getOperateBodyPartName() {
		return operateBodyPartName;
	}

	public void setOperateBodyPartName(String operateBodyPartName) {
		this.operateBodyPartName = operateBodyPartName;
	}

	public String getSurgicalSiteInfectionCode() {
		return surgicalSiteInfectionCode;
	}

	public void setSurgicalSiteInfectionCode(String surgicalSiteInfectionCode) {
		this.surgicalSiteInfectionCode = surgicalSiteInfectionCode;
	}

	public String getSurgicalSiteInfectionName() {
		return surgicalSiteInfectionName;
	}

	public void setSurgicalSiteInfectionName(String surgicalSiteInfectionName) {
		this.surgicalSiteInfectionName = surgicalSiteInfectionName;
	}

	public String getPatientSourceCode() {
		return patientSourceCode;
	}

	public void setPatientSourceCode(String patientSourceCode) {
		this.patientSourceCode = patientSourceCode;
	}

	public String getPatientSourceName() {
		return patientSourceName;
	}

	public void setPatientSourceName(String patientSourceName) {
		this.patientSourceName = patientSourceName;
	}

	public String getOperatedComplicationCode() {
		return operatedComplicationCode;
	}

	public void setOperatedComplicationCode(String operatedComplicationCode) {
		this.operatedComplicationCode = operatedComplicationCode;
	}

	public String getOperatedComplicationName() {
		return operatedComplicationName;
	}

	public void setOperatedComplicationName(String operatedComplicationName) {
		this.operatedComplicationName = operatedComplicationName;
	}

	public String getHaveReoperateRoomPlanCode() {
		return haveReoperateRoomPlanCode;
	}

	public void setHaveReoperateRoomPlanCode(String haveReoperateRoomPlanCode) {
		this.haveReoperateRoomPlanCode = haveReoperateRoomPlanCode;
	}

	public String getHaveReoperateRoomPlanName() {
		return haveReoperateRoomPlanName;
	}

	public void setHaveReoperateRoomPlanName(String haveReoperateRoomPlanName) {
		this.haveReoperateRoomPlanName = haveReoperateRoomPlanName;
	}

	public String getReoperateRoomAimCode() {
		return reoperateRoomAimCode;
	}

	public void setReoperateRoomAimCode(String reoperateRoomAimCode) {
		this.reoperateRoomAimCode = reoperateRoomAimCode;
	}

	public String getReoperateRoomAimName() {
		return reoperateRoomAimName;
	}

	public void setReoperateRoomAimName(String reoperateRoomAimName) {
		this.reoperateRoomAimName = reoperateRoomAimName;
	}

	public String getOperateRiskLevelCode() {
		return operateRiskLevelCode;
	}

	public void setOperateRiskLevelCode(String operateRiskLevelCode) {
		this.operateRiskLevelCode = operateRiskLevelCode;
	}

	public String getOperateRiskLevelName() {
		return operateRiskLevelName;
	}

	public void setOperateRiskLevelName(String operateRiskLevelName) {
		this.operateRiskLevelName = operateRiskLevelName;
	}

	public String getAnaesthesiaUnintendedCode() {
		return anaesthesiaUnintendedCode;
	}

	public void setAnaesthesiaUnintendedCode(String anaesthesiaUnintendedCode) {
		this.anaesthesiaUnintendedCode = anaesthesiaUnintendedCode;
	}

	public String getAnaesthesiaUnintendedName() {
		return anaesthesiaUnintendedName;
	}

	public void setAnaesthesiaUnintendedName(String anaesthesiaUnintendedName) {
		this.anaesthesiaUnintendedName = anaesthesiaUnintendedName;
	}
	
	
}
