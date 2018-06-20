package com.kurumi.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kurumi.util.StringUtil;

public class MedicalRecordSearchingQuery extends PageQuery{

		//病案唯一标识
		private String onlyId;

		//患者姓名
		private String patientName;
		
		//证件类型编号
		private String idDocumentTypeCode;
		
		//证件号
		private String idNumber;
		
		//出院开始时间
		@DateTimeFormat(pattern="yyyy-MM-dd")
		@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
		private Date outHospitalStartDate;
		
		//出院结束时间
		@DateTimeFormat(pattern="yyyy-MM-dd")
		@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
		private Date outHospitalEndDate;
		
		//病案号
		private String mrId;
		
		//出院科室编号
		private String outHospitalDeptCode;
		
		//就诊类型编号
		private String treamentTypeCode;
		
		//出院方式编号
		private String outHospitalTypeCode;
		
		private String diseaseDiagName;
		
		//查询结果集
		private Object queryDatas;
		
		
		public String getOnlyId() {
			return StringUtil.meaningStr(onlyId);
		}




		public void setOnlyId(String onlyId) {
			this.onlyId = onlyId;
		}




		public String getPatientName() {
			return StringUtil.meaningStr(patientName);
		}




		public void setPatientName(String patientName) {
			this.patientName = patientName;
		}




		public String getIdDocumentTypeCode() {
			return StringUtil.meaningStr(idDocumentTypeCode);
		}




		public void setIdDocumentTypeCode(String idDocumentTypeCode) {
			this.idDocumentTypeCode = idDocumentTypeCode;
		}




		public String getIdNumber() {
			return StringUtil.meaningStr(idNumber);
		}




		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}




		public Date getOutHospitalStartDate() {
			return outHospitalStartDate;
		}




		public void setOutHospitalStartDate(Date outHospitalStartDate) {
			this.outHospitalStartDate = outHospitalStartDate;
		}




		public Date getOutHospitalEndDate() {
			return outHospitalEndDate;
		}




		public void setOutHospitalEndDate(Date outHospitalEndDate) {
			this.outHospitalEndDate = outHospitalEndDate;
		}




		public String getMrId() {
			return StringUtil.meaningStr(mrId);
		}




		public void setMrId(String mrId) {
			this.mrId = mrId;
		}




		public String getOutHospitalDeptCode() {
			return StringUtil.meaningStr(outHospitalDeptCode);
		}




		public void setOutHospitalDeptCode(String outHospitalDeptCode) {
			this.outHospitalDeptCode = outHospitalDeptCode;
		}




		public String getTreamentTypeCode() {
			return StringUtil.meaningStr(treamentTypeCode);
		}




		public void setTreamentTypeCode(String treamentTypeCode) {
			this.treamentTypeCode = treamentTypeCode;
		}




		public String getOutHospitalTypeCode() {
			return StringUtil.meaningStr(outHospitalTypeCode);
		}




		public void setOutHospitalTypeCode(String outHospitalTypeCode) {
			this.outHospitalTypeCode = outHospitalTypeCode;
		}

		
		
		public String getDiseaseDiagName() {
			return diseaseDiagName;
		}




		public void setDiseaseDiagName(String diseaseDiagName) {
			this.diseaseDiagName = diseaseDiagName;
		}




		public Object getQueryDatas() {
			return queryDatas;
		}




		public void setQueryDatas(Object queryDatas) {
			this.queryDatas = queryDatas;
		}

}
