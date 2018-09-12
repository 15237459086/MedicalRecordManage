package com.kurumi.pojo;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kurumi.util.DateUtil;
import com.kurumi.util.StringUtil;
/**
 * 放疗病案
 * @author lyh
 *
 */
public class MedicalRecordRadioTherapy {
    private String visitGuid;

    private String patientName;

    private String sexCode;
 
    private String sexName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    private String documentTypeCode;

    private String documentTypeName;

    private String idNumber;

    private String onlyId;

    private String mrId;

    private Integer visitNumber;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inHospitalDateTime;

    private String inDeptCode;

    private String inDeptName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date outHospitalDateTime;

    private String outDeptCode;

    private String outDeptName;

    private String treatmentSignCode;

    private String treatmentSignName;

    private Integer version;

    public String getVisitGuid() {
        return StringUtil.meaningStr(visitGuid);
    }

    public void setVisitGuid(String visitGuid) {
        this.visitGuid = StringUtil.meaningStr(visitGuid);
    }

    public String getPatientName() {
        return StringUtil.meaningStr(patientName);
    }

    public void setPatientName(String patientName) {
        this.patientName = StringUtil.meaningStr(patientName);
    }

    public String getSexCode() {
        return StringUtil.meaningStr(sexCode);
    }

    public void setSexCode(String sexCode) {
        this.sexCode = StringUtil.meaningStr(sexCode);
    }

    public String getSexName() {
        return StringUtil.meaningStr(sexName);
    }

    public void setSexName(String sexName) {
        this.sexName = StringUtil.meaningStr(sexName);
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDocumentTypeCode() {
        return StringUtil.meaningStr(documentTypeCode);
    }

    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = StringUtil.meaningStr(documentTypeCode);
    }

    public String getDocumentTypeName() {
        return StringUtil.meaningStr(documentTypeName);
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = StringUtil.meaningStr(documentTypeName);
    }

    public String getIdNumber() {
        return StringUtil.meaningStr(idNumber);
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = StringUtil.meaningStr(idNumber);
    }

    public String getOnlyId() {
        return StringUtil.meaningStr(onlyId);
    }

    public void setOnlyId(String onlyId) {
        this.onlyId = StringUtil.meaningStr(onlyId);
    }

    public String getMrId() {
        return StringUtil.meaningStr(mrId);
    }

    public void setMrId(String mrId) {
        this.mrId = StringUtil.meaningStr(mrId);
    }

    public Integer getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(Integer visitNumber) {
        this.visitNumber = visitNumber;
    }

    public Date getInHospitalDateTime() {
        return inHospitalDateTime;
    }

    public void setInHospitalDateTime(Date inHospitalDateTime) {
        this.inHospitalDateTime = inHospitalDateTime;
    }

    public String getInDeptCode() {
        return StringUtil.meaningStr(inDeptCode);
    }

    public void setInDeptCode(String inDeptCode) {
        this.inDeptCode = StringUtil.meaningStr(inDeptCode);
    }

    public String getInDeptName() {
        return StringUtil.meaningStr(inDeptName);
    }

    public void setInDeptName(String inDeptName) {
        this.inDeptName = StringUtil.meaningStr(inDeptName);
    }

    public Date getOutHospitalDateTime() {
        return outHospitalDateTime;
    }

    public void setOutHospitalDateTime(Date outHospitalDateTime) {
        this.outHospitalDateTime = outHospitalDateTime;
    }

    public String getOutDeptCode() {
        return StringUtil.meaningStr(outDeptCode);
    }

    public void setOutDeptCode(String outDeptCode) {
        this.outDeptCode = StringUtil.meaningStr(outDeptCode);
    }

    public String getOutDeptName() {
        return StringUtil.meaningStr(outDeptName);
    }

    public void setOutDeptName(String outDeptName) {
        this.outDeptName = StringUtil.meaningStr(outDeptName);
    }


    public String getTreatmentSignCode() {
        return StringUtil.meaningStr(treatmentSignCode);
    }

    public void setTreatmentSignCode(String treatmentSignCode) {
        this.treatmentSignCode = StringUtil.meaningStr(treatmentSignCode);
    }

    public String getTreatmentSignName() {
        return StringUtil.meaningStr(treatmentSignName);
    }

    public void setTreatmentSignName(String treatmentSignName) {
        this.treatmentSignName = StringUtil.meaningStr(treatmentSignName);
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public static  MedicalRecordRadioTherapy buildMedicalRecord(Map<String, Object> data){
    	MedicalRecordRadioTherapy medicalRecord = new MedicalRecordRadioTherapy();
    	String visitGuid = StringUtil.meaningStr((String)data.get("visitGuid"));
    	medicalRecord.setVisitGuid(visitGuid);
    	
    	String patientName = StringUtil.meaningStr((String)data.get("patientName"));
    	medicalRecord.setPatientName(patientName);
    	
    	String sexName = StringUtil.meaningStr((String)data.get("sexName"));
    	medicalRecord.setSexName(sexName);
    	
    	String mrId = StringUtil.meaningStr((String)data.get("mrId"));
    	medicalRecord.setMrId(mrId);
    	
    	Object inTimesObj = data.get("visitNumber");
    	if(inTimesObj!= null){
    		medicalRecord.setVisitNumber((int)inTimesObj);
    	}
    	
    	String inTimeStr = StringUtil.meaningStr((String)data.get("inHospitalDateTime"));
    	if(inTimeStr != null){
    		try {
    			Date inHospitalDateTime = DateUtil.dateParse(inTimeStr);
    			medicalRecord.setInHospitalDateTime(inHospitalDateTime);
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
    	String inDeptCode = StringUtil.meaningStr((String)data.get("inDeptCode"));
    	medicalRecord.setInDeptCode(inDeptCode);
    	
    	String inDeptName = StringUtil.meaningStr((String)data.get("inDeptName"));
    	medicalRecord.setInDeptName(inDeptName);
    	
    	String outTimeStr = StringUtil.meaningStr((String)data.get("outHospitalDateTime"));
    	if(outTimeStr != null){
    		try {
    			Date outHospitalDateTime = DateUtil.dateParse(outTimeStr);
    			medicalRecord.setOutHospitalDateTime(outHospitalDateTime);
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
    	String outDeptCode = StringUtil.meaningStr((String)data.get("outDeptCode"));
    	medicalRecord.setOutDeptCode(outDeptCode);
    	
    	String outDeptName = StringUtil.meaningStr((String)data.get("outDeptName"));
    	medicalRecord.setOutDeptName(outDeptName);
    	


    	
    	return medicalRecord;
    }
    
}