package com.kurumi.service;

import java.util.List;
import java.util.Map;

import com.kurumi.query.MedicalRecordQuery;

public interface MedicalRecordScanService {

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
}
