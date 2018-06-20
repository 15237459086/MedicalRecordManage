package com.kurumi.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StatisticsAnalysisService {

	int getMedicalRecordCount(Date outHospitalStartDate,Date outHospitalEndDate);
	
	List<Map<String,Object>> getMedicalPayTypeAnalysis(Date outHospitalStartDate,Date outHospitalEndDate);
	
	List<Map<String,Object>> getInHospitalTypeAnalysis(Date outHospitalStartDate,Date outHospitalEndDate);
}
