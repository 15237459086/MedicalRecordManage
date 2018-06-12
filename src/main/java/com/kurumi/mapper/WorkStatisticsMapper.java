package com.kurumi.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface WorkStatisticsMapper {

	int getMedicalRecordCountOfShouldPigeonholed(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
	int getImageCountOfShouldPigeonholed(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
	int getImageCountOfPigeonholed(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
	List<Map<String,Object>> getGroupImageCountOfPigeonholer(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
	int getMedicalRecordCountOfCodinged(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
	List<Map<String,Object>> getGroupMedicalRecordCountOfCodinged(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
}
