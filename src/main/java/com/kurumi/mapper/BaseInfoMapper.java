package com.kurumi.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BaseInfoMapper {

	/**
	 * 依据医院编号获取医院
	 * @return
	 */
	List<Map<String,Object>> getHospitalByCode(@Param("hospitalCode")String hospitalCode);
	
	/**
	 * 依据医院id获取医疗科室
	 * @return
	 */
	List<Map<String,Object>> getMedicalDeptByHospitalId(@Param("hospitalId")Integer hospitalId);
	
	/**
	 * 依据编号获取医疗科室
	 * @return
	 */
	List<Map<String,Object>> getMedicalDeptByCode(@Param("code")String code);
	
	
	/**
	 * 获取医疗付费方式
	 * @return
	 */
	List<Map<String,Object>> getMedicalPaymentTypes();
	
	/**
	 * 获取性别
	 * @return
	 */
	List<Map<String,Object>> getSexs();
	
	List<Map<String, Object>> getSexByCode(@Param("code")String code);
	
	List<Map<String, Object>> getTreatmentSignByCode(@Param("code")String code);
	
	List<Map<String, Object>> getTreatmentTypeByCode(@Param("code")String code);
	
	/**
	 * 获取婚姻
	 * @return
	 */
	List<Map<String,Object>> getMarriages();
	
	/**
	 * 获取身份证件类型
	 * @return
	 */
	List<Map<String,Object>> getIdDocumentTypes();
	
	List<Map<String, Object>> getIdDocumentTypeByCode(@Param("code")String code);
	
	/**
	 * 获取国籍
	 * @return
	 */
	List<Map<String,Object>> getNationalitys();
	
	/**
	 * 获取民族
	 * @return
	 */
	List<Map<String,Object>> getNations();
	
	
	
	/**
	 * 获取职业
	 * @return
	 */
	List<Map<String,Object>> getProfessions();
	
	/**
	 * 获取亲属关系
	 * @return
	 */
	List<Map<String,Object>> getRelativeRelations();
	
	/**
	 * 获取入院方式
	 * @return
	 */
	List<Map<String,Object>> getInHospitalTypes();
	
	/**
	 * 获取入院方式依据编号
	 * @return
	 */
	List<Map<String,Object>> getInHospitalTypeByCode(@Param("code")String code);
	
	/**
	 * 获取入院状况
	 * @return
	 */
	List<Map<String,Object>> getInHospitalStates();
	
	/**
	 * 获取出院方式
	 * @return
	 */
	List<Map<String,Object>> getOutHospitalTypes();
	
	/**
	 * 获取出院方式依据编号
	 * @return
	 */
	List<Map<String,Object>> getOutHospitalTypeByCode(@Param("code")String code);
	
	/**
	 * 获取再住院目的
	 * @return
	 */
	List<Map<String,Object>> getRehospitalAims();
	
	
	/**
	 * 获取死亡原因
	 * @return
	 */
	List<Map<String,Object>> getHospitalDealthReasons();
	
	
	/**
	 * 获取疾病诊断类型
	 * @return
	 */
	List<Map<String,Object>> getDiagTypes();
	
	/**
	 * 获取治疗结果
	 * @return
	 */
	List<Map<String,Object>> getTreatmentResults();
	
	/**
	 * 模糊搜索疾病
	 * @return
	 */
	List<Map<String,Object>> getDiseaseByQueryName(@Param("queryName")String queryName);
	
	/**
	 * 模糊搜索医疗工作人员
	 * @return
	 */
	List<Map<String,Object>> getMedicalWorkerByQueryName(@Param("queryName")String queryName);

	/**
	 * 获取ABO血型
	 * @return
	 */
	List<Map<String,Object>> getBloodTypes();
	
	
	/**
	 * 获取RH血型
	 * @return
	 */
	List<Map<String,Object>> getRhBloodTypes();
	
	/**
	 * 获取诊断对比符合类型
	 * @return
	 */
	List<Map<String,Object>> getDiagAccordTypes();
	
	/**
	 * 获取切口等级
	 * @return
	 */
	List<Map<String,Object>> getIncisionLevels();
	
	/**
	 * 获取愈合类型
	 * @return
	 */
	List<Map<String,Object>> getCicatrizeTypes();
	
	/**
	 * 获取手术级别
	 * @return
	 */
	List<Map<String,Object>> getOpsLevels();
	
	/**
	 * 获取麻醉方式
	 * @return
	 */
	List<Map<String,Object>> getAnaesthesiaWays();
	
	/**
	 * 获取麻醉分级
	 * @return
	 */
	List<Map<String,Object>> getAnaesthesiaLevels();
	
	/**
	 * 模糊搜索手术操作
	 * @return
	 */
	List<Map<String,Object>> getOperateByQueryName(@Param("queryName")String queryName);

	
	/**
	 * 获取ICU类型
	 * @return
	 */
	List<Map<String,Object>> getICUTypes();
	
	/**
	 * 获取过敏药物类型
	 * @return
	 */
	List<Map<String,Object>> getAllergyDrugTypes();
	
	
	/**
	 * 获取压疮来源
	 * @return
	 */
	List<Map<String,Object>> getPressureSoreCradles();
	
	/**
	 * 获取压疮分期
	 * @return
	 */
	List<Map<String,Object>> getPressureSorePhases();
	
	/**
	 * 获取压疮部位
	 * @return
	 */
	List<Map<String,Object>> getPressureSoreParts();
	
	/**
	 * 获取病原体
	 * @return
	 */
	List<Map<String,Object>> getPathogens();
	
	/**
	 * 获取病原学检测标本
	 * @return
	 */
	List<Map<String,Object>> getPathogenyCheckSpecimens();
	
	/**
	 * 获取易感因素
	 * @return
	 */
	List<Map<String,Object>> getPredisposeFactors();
	
	/**
	 * 获取抗菌用药目的
	 * @return
	 */
	List<Map<String,Object>> getAntibacterialDrugPurposes();
	
	/**
	 *  获取抗菌药物分类
	 * @return
	 */
	List<Map<String,Object>> getAntibacterialDrugClassificatorys();
	
	/**
	 * 获取抗菌用药方案
	 * @return
	 */
	List<Map<String,Object>> getAntibacterialDrugRegimens();
	
	/**
	 * 依据编号获取示踪类型
	 * @return
	 */
	List<Map<String,Object>> getTraceTypeByCode(@Param("code")String code);
	
	
	/**
	 * 获取第一级指控标准
	 * @return
	 */
	List<Map<String,Object>> getQualityControlOfFirstLevel();
	
	/**
	 * 依据上一级编号获取指控标准
	 * @return
	 */
	List<Map<String,Object>> getQualityControlByUpOneLevelCode(@Param("upOneLevelCode")String upOneLevelCode);
	
	/**
	 * 获取标签类型
	 * @return
	 */
	List<Map<String,Object>> getMrPageTypes();
}
