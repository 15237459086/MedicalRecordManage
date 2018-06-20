package com.kurumi.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface StatisticsAnalysisMapper {
	
	int getMedicalRecordCount(@Param("outHospitalStartDate")Date outHospitalStartDate,@Param("outHospitalEndDate")Date outHospitalEndDate);
	
	
	List<Map<String,Object>> getMedicalPayTypeAnalysis(@Param("outHospitalStartDate")Date outHospitalStartDate,@Param("outHospitalEndDate")Date outHospitalEndDate);
	
	List<Map<String,Object>> getInHospitalTypeAnalysis(@Param("outHospitalStartDate")Date outHospitalStartDate,@Param("outHospitalEndDate")Date outHospitalEndDate);
	
}
