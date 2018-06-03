package com.kurumi.service;

import java.util.List;
import java.util.Map;

import com.kurumi.query.MedicalRecordQuery;

public interface MedicalRecordDataManageService {

	List<Map<String,Object>> getMedicalRecordOfExport(MedicalRecordQuery medicalRecordQuery);
	
	
	List<Map<String,Object>> getMedicalRecordOfExcel(MedicalRecordQuery medicalRecordQuery);
}
