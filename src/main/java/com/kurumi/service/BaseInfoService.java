package com.kurumi.service;

import java.util.List;
import java.util.Map;

public interface BaseInfoService {

	/**
	 * 依据医院编号获取医院
	 * @return
	 */
	List<Map<String,Object>> getHospitalByCode(String hospitalCode);
	
	Map<String, List<Map<String,Object>>> getBaseDataOfBasicInfo();
	
	Map<String, List<Map<String,Object>>> getBaseDataOfCureInfo();
	
	Map<String, List<Map<String,Object>>> getBaseDataOfDiagInfo();
	
	Map<String, List<Map<String,Object>>> getBaseDataOfOperateInfo();
	
	
	
	Map<String, List<Map<String,Object>>> getBaseInfoOfHomePageTransferDept();
	
	Map<String, List<Map<String,Object>>> getBaseInfoOfHomePageInfusionBlood();
	
	Map<String, List<Map<String,Object>>> getBaseInfoOfHomePageIntensiveCare();
	
	Map<String, List<Map<String,Object>>> getBaseInfoOfHomePageAllergyDrug();
	
	Map<String, List<Map<String,Object>>> getBaseInfoOfHomePagePressureSore();
	
	Map<String, List<Map<String,Object>>> getBaseInfoOfHomePageInfection();
	
	Map<String, List<Map<String,Object>>> getBaseInfoOfHomePageAntibacterialDrug();
	
	
	Map<String, List<Map<String,Object>>> getBaseInfoOfUnPigeonhole();
	
	Map<String, List<Map<String,Object>>> getBaseInfoOfPrint();
	
	Map<String, List<Map<String,Object>>> getBaseInfoOfBorrow();
	/**
	 * 模糊搜索疾病
	 * @return
	 */
	List<Map<String,Object>> getDiseaseByQueryName(String queryName);
	
	/**
	 * 模糊搜索医疗工作人员
	 * @return
	 */
	List<Map<String,Object>> getMedicalWorkerByQueryName(String queryName);
	
	Map<String, Object> getSignatureMedicalWorks(List<String> medicalWorkCodes);
	
	/**
	 * 模糊搜索手术操作
	 * @return
	 */
	List<Map<String,Object>> getOperateByQueryName(String queryName);
	
	/**
	 * 获取医疗科室
	 * @return
	 */
	List<Map<String,Object>> getMedicalDepts();
	
	
	Map<String, List<Map<String,Object>>> getBaseInfoOfQualityControl();
	
	/**
	 * 获取第一级指控标准
	 * @return
	 */
	List<Map<String,Object>> getQualityControlOfFirstLevel();
	
	/**
	 * 依据上一级编号获取指控标准
	 * @return
	 */
	List<Map<String,Object>> getQualityControlByUpOneLevelCode(String upOneLevelCode);
	
	
	/**
	 * 获取标签类型
	 * @return
	 */
	List<Map<String, Object>> getMrPageTypes();
	
}
