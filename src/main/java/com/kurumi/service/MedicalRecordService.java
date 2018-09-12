package com.kurumi.service;

import java.util.List;
import java.util.Map;

import com.kurumi.pojo.MedicalRecord;
import com.kurumi.pojo.MedicalRecordQualityControlItem;
import com.kurumi.query.MedicalRecordQuery;

public interface MedicalRecordService {

	/**
	 * 依据VisitGuid获取病案pojo对象
	 * @param visitGuid
	 * @return
	 */
	MedicalRecord selectMedicalRecordByPrimaryKey(String visitGuid);
	
	/**
	 * 获取病案信息依据visitGuid
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordByVisitGuid(String visitGuid);

	/**
	 * 依据VisitGuid修改病案对象
	 * @param record
	 * @return
	 */
    int updateMedicalRecordByPrimaryKey(MedicalRecord record);
    
    
    /**
	 * 获取病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecord(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 获取病案数量
	 * @return
	 */
	int getMedicalRecordCount(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 获取未归档病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfUnPigeonhole(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 获取未归档病案数量
	 * @return
	 */
	int getMedicalRecordCountOfUnPigeonhole(MedicalRecordQuery medicalRecordQuery);
	
	
	/**
	 * 检验病案是否唯一
	 * @param onlyId
	 * @param mrId
	 * @param visitNumber
	 * @return
	 */
	int checkMeditalRecordUniq(String onlyId,String mrId,Integer visitNumber);
	
	List<String> getVisitGuidByMrIdAndVisitNumber(String mrId,Integer visitNumber);
	
	int insertMeditalRecord(MedicalRecord medicalRecord);
	
	/**
	 * 病案归档
	 * @param visitGuid
	 * @param pigeonholeDateTime
	 * @param treatmentSignId
	 * @return
	 */
    int pigeonholeMedicalRecord(String visitGuid, String pigeonholeDateTime,String treatmentSignId);
    
    
    /**
	 * 获取已归档病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfPigeonholed(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 获取已归档病案数量
	 * @return
	 */
	int getMedicalRecordCountOfPigeonholed(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 获取迟送病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfPigeonholedBeyond(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 获取迟送病案数量
	 * @return
	 */
	int getMedicalRecordCountOfPigeonholedBeyond(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 统计病案2，3，7日归档率
	 * @param medicalRecordQuery
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordPigeonholedRate(MedicalRecordQuery medicalRecordQuery);
	
	
	/**
	 * 获质控病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfQualityControl(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 获取质控病案数量
	 * @return
	 */
	int getMedicalRecordCountOfQualityControl(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 添加质控评价项
	 * @return
	 */
	int addQualityControlItem(String visitGuid,MedicalRecordQualityControlItem qualityControlItem);
	
	/**
	 * 修改质控评价项
	 * @return
	 */
	MedicalRecordQualityControlItem updateQualityControlItem(String visitGuid,MedicalRecordQualityControlItem qualityControlItem);
	
	/**
	 * 删除质控评价项
	 * @return
	 */
	int deleteQualityControlItem(String visitGuid,Integer qualityControlItemId);
	
	
	/**
	 * 完成质控评价
	 * @return
	 */
	int finishQualityControl(String visitGuid);
	
	/**
	 * 获取病案质控项
	 * @param record
	 * @return
	 */
	List<Map<String,Object>> getQualityControlItemByVisitGuid(String visitGuid);
	
	/**
	 * 获取病案质控项
	 * @param record
	 * @return
	 */
	List<Map<String,Object>> getQualityControlItemByQualityControlId(Integer qualityControlId);
	
	/**
	 * 获取病案质控项
	 * @param id
	 * @return
	 */
	MedicalRecordQualityControlItem selectQualityControlItemByPrimaryKey(Integer id);
	
	/**
	 * 获取指控评价统计
	 * @param medicalRecordQuery
	 * @return
	 */
	List<Map<String,Object>> getQualityControlScoreRate(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 获取病案示踪
	 * @return
	 */
	List<Map<String,Object>> getMeditalRecordTraceByVisitGuid(String visitGuid);
	
	
	/** 获首页编码质控病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfHomePage(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 获取首页编码病案数量
	 * @return
	 */
	int getMedicalRecordCountOfHomePage(MedicalRecordQuery medicalRecordQuery);
	
	int importMedicalRecord(List<MedicalRecord> medicalRecords);
}
