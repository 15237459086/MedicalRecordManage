package com.kurumi.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kurumi.pojo.coding.BasicInfo;
import com.kurumi.pojo.coding.CostInfo;
import com.kurumi.pojo.coding.CureInfo;
import com.kurumi.pojo.coding.DiseaseDiagInfo;
import com.kurumi.pojo.coding.DrugAllergyInfo;
import com.kurumi.pojo.coding.InfectionInfo;
import com.kurumi.pojo.coding.InfusionBloodInfo;
import com.kurumi.pojo.coding.IntensiveCareInfo;
import com.kurumi.pojo.coding.NurseInfo;
import com.kurumi.pojo.coding.OperateInfo;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.query.MedicalRecordSearchingQuery;
import com.kurumi.query.StatisticsAnalysisQuery;

public interface MedicalRecordCodingService {

	int editBasicInfo(String visitGuid,BasicInfo basicInfo,Map<String, Object> jsonMap);
	
	int editCureInfo(String visitGuid,CureInfo cureInfo,Map<String, Object> jsonMap);
	
	int editDiseaseDiagInfo(String visitGuid,DiseaseDiagInfo diseaseDiagInfo,Map<String, Object> jsonMap);
	
	int editOperateInfo(String visitGuid,OperateInfo operateInfo,Map<String, Object> jsonMap);
	
	int editNurseInfo(String visitGuid,NurseInfo nurseInfo,Map<String, Object> jsonMap);
	
	int editInfusionBloodInfo(String visitGuid,InfusionBloodInfo infusionBloodInfo,Map<String, Object> jsonMap);
	
	int editDrugAllergyInfo(String visitGuid,DrugAllergyInfo drugAllergyInfo,Map<String, Object> jsonMap);
	
	int editInfectionInfo(String visitGuid,InfectionInfo infectionInfo,Map<String, Object> jsonMap);
	
	int editCostInfo(String visitGuid,CostInfo costInfo,Map<String, Object> jsonMap);
	
	int editIntensiveCareInfo(String visitGuid,IntensiveCareInfo intensiveCareInfo,Map<String, Object> jsonMap);
	
	/**
	 * 根visitGuid获取病案json数据
	 * @param visitGuid
	 * @return
	 */
	public List<String> getMedicalRecordJsonByVisitGuid(@Param("visitGuid")String visitGuid);
	
	
	String codingFinish(String visitGuid);
	
	/** 主要诊断审核
	 * @return
	 */
	List<Map<String,Object>> mainDiseaseDiagCheck(MedicalRecordQuery medicalRecordQuery);
	
	/** 住院天数审核
	 * @return
	 */
	List<Map<String,Object>> inHospitalDayNumberCheck(MedicalRecordQuery medicalRecordQuery);
	
	/** 重复编码审核
	 * @return
	 */
	List<Map<String,Object>> repeatCodingCheck(MedicalRecordQuery medicalRecordQuery);
	
	
	/** 日期审核
	 * @return
	 */
	List<Map<String,Object>> dateTimeCheck(MedicalRecordQuery medicalRecordQuery);
	
	List<Map<String,Object>> getDiseaseMedicalRecord(MedicalRecordSearchingQuery params);
	
	int getDiseaseMedicalRecordCount(MedicalRecordSearchingQuery params);
	
	List<Map<String,Object>> getMedicalRecordOfRepair(MedicalRecordQuery params);
	
	int getMedicalRecordCountOfRepair(MedicalRecordQuery params);
	
	List<Map<String,Object>> getMedicalRecordOfDefect(StatisticsAnalysisQuery params);
	
	List<Map<String,Object>> getMedicalRecordOfDefectDetail(StatisticsAnalysisQuery params);
}
