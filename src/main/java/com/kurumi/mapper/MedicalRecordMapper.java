package com.kurumi.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kurumi.pojo.MedicalRecord;
import com.kurumi.pojo.MedicalRecordQualityControl;
import com.kurumi.pojo.MedicalRecordQualityControlItem;
import com.kurumi.pojo.MedicalRecordTrace;
import com.kurumi.query.MedicalRecordQuery;

public interface MedicalRecordMapper {
    
    
    int deleteByPrimaryKey(String visitGuid);

    int insert(MedicalRecord record);

    int insertSelective(MedicalRecord record);

   

    MedicalRecord selectByPrimaryKey(String visitGuid);

    int updateByPrimaryKeySelective(MedicalRecord record);

    int updateByPrimaryKey(MedicalRecord record);
    
    
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
	int checkMeditalRecordUniq(@Param("onlyId") String onlyId,@Param("mrId") String mrId,@Param("visitNumber") Integer visitNumber);
	
	
	
	/**
	 * 获取病案示踪数量
	 * @param visitGuid
	 * @param traceTypeCode
	 * @return
	 */
	int getMeditalRecordTraceCount(@Param("visitGuid") String visitGuid,@Param("traceTypeCode") String traceTypeCode);
	
	/**
	 * 获取病案示踪
	 * @return
	 */
	List<Map<String,Object>> getMeditalRecordTraceByVisitGuid(@Param("visitGuid") String visitGuid);
	
	
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
	 * 统计病案2，3，7日归档率
	 * @param medicalRecordQuery
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordPigeonholedRate(MedicalRecordQuery medicalRecordQuery);
	
	
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
	 * 获取质控病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfQualityControl(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 获取质控病案数量
	 * @return
	 */
	int getMedicalRecordCountOfQualityControl(MedicalRecordQuery medicalRecordQuery);
	
	
	
	/**
	 * 获取未催收病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfUnUrge(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 新增病案示踪
	 * @param medicalRecordTrace
	 * @return
	 */
	int insertMedicalRecordTrace(MedicalRecordTrace medicalRecordTrace);
	
	/**
	 * 获取编页病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfPagination(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 *获取编页病案数量
	 * @return
	 */
	int getMedicalRecordCountOfPagination(MedicalRecordQuery medicalRecordQuery);
	
	
	/**
	 * 获取病案信息依据visitGuid
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordByVisitGuid(String visitGuid);
	
	/**
	 * 添加病案质控
	 * @param record
	 * @return
	 */
	int insertQualityControl(MedicalRecordQualityControl record);
	
	/**
	 * 修改病案质控
	 * @param record
	 * @return
	 */
	int updateQualityControlByPrimaryKey(MedicalRecordQualityControl record);
	
	/**
	 * 获取病案质控等级
	 * @param visitGuid
	 * @return
	 */
	List<Map<String,Object>> getQualityControlGradeByVisitGuid(@Param("visitGuid") String visitGuid);
	
	/**
	 * 获取病案质控数量
	 * @param visitGuid
	 * @return
	 */
	int getQualityControlCountByVisitGuid(@Param("visitGuid") String visitGuid);
	
	/**
	 * 获取病案质控
	 * @param visitGuid
	 * @return
	 */
	MedicalRecordQualityControl selectQualityControlByPrimaryKey(@Param("visitGuid") String visitGuid);
	
	/**
	 * 添加病案质控项
	 * @param record
	 * @return
	 */
	int insertQualityControlItem(MedicalRecordQualityControlItem record);
	
	
	/**
	 * 获取病案质控项
	 * @param record
	 * @return
	 */
	List<Map<String,Object>> getQualityControlItemByQualityControlId(@Param("qualityControlId") Integer qualityControlId);
	
	/**
	 * 获取病案质控项
	 * @param record
	 * @return
	 */
	List<Map<String,Object>> getQualityControlItemByVisitGuid(@Param("visitGuid") String visitGuid);
	
	/**
	 * 获取病案质控项
	 * @param id
	 * @return
	 */
	MedicalRecordQualityControlItem selectQualityControlItemByPrimaryKey(Integer id);
	
	/**
	 * 修改病案质控项
	 * @param record
	 * @return
	 */
	int updateQualityControlItemByPrimaryKey(MedicalRecordQualityControlItem record);
	
	/**
	 * 删除病案质控项
	 * @param id
	 * @return
	 */
	int deleteQualityControlItemByPrimaryKey(Integer id);
	
	/**
	 * 获取指控评价统计
	 * @param medicalRecordQuery
	 * @return
	 */
	List<Map<String,Object>> getQualityControlScoreRate(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 获取编码病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfHomePage(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 *获取编码病案数量
	 * @return
	 */
	int getMedicalRecordCountOfHomePage(MedicalRecordQuery medicalRecordQuery);
	
	
	/**
	 * 获取扫描病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfScan(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 获取扫描病案数量
	 * @return
	 */
	int getMedicalRecordCountOfScan(MedicalRecordQuery medicalRecordQuery);
	
    
	/**
	 * 获取用于打印的病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfPrinter(MedicalRecordQuery params);

	/**
	 * 获取用于打印的病案数量
	 * @return
	 */
	int getMedicalRecordCountOfPrinter(MedicalRecordQuery params);
	
	
	
	/**
	 * 添加病案Json数据
	 * @param medicalRecordJson
	 * @return
	 */
	public int insertMedicalRecordJson(@Param("medicalRecordJson")String medicalRecordJson);
	
	
	/**
	 * 根visitGuid获取病案json数据
	 * @param visitGuid
	 * @return
	 */
	public List<String> getMedicalRecordJsonByVisitGuid(@Param("visitGuid")String visitGuid);
	
	
	/**
	 * 根据visitGuid删除病案json数据
	 * @param visitGuid
	 * @return
	 */
	int deleteMedicalRecordJsonByVisitGuid(@Param("visitGuid")String visitGuid);
	
	
	/**
	 * 获取首页导出病案
	 * @param medicalRecordQuery
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfExport(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 获取审核病案
	 * @param medicalRecordQuery
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfCheck(MedicalRecordQuery medicalRecordQuery);
	
	
	/*List<Map<String,Object>> getCollectionStatistics(MedicalRecordTraceQuery medicalRecordTraceQuery);

	*//**
	 * 获取已发行或未放行病案
	 * @return
	 *//*
	List<Map<String,Object>> getMedicalRecordShelveByTraceType(MedicalRecordTraceQuery medicalRecordTraceQuery);

	*//**
	 *获取已发行或未放行病案数量
	 * @return
	 *//*
	int getMedicalRecordShelveCountByTraceType(MedicalRecordTraceQuery medicalRecordTraceQuery);
	
	*//**
	 * 获取已发行用于借阅的病案
	 * @return
	 *//*
	List<Map<String,Object>> getMeditalRecordOfBorrow(MedRecordQuery params);

	*//**
	 * 获取已发行用于借阅的病案数量
	 * @return
	 *//*
	int getMeditalRecordCountOfBorrow(MedRecordQuery params);*/
	
	
	
}