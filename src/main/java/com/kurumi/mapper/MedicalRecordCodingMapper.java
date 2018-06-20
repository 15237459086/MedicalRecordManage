package com.kurumi.mapper;

import java.util.List;
import java.util.Map;

import com.kurumi.pojo.MedicalRecordCodingError;
import com.kurumi.pojo.MedicalRecordDiseaseDiag;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.query.MedicalRecordSearchingQuery;

public interface MedicalRecordCodingMapper {

	 int deleteDiseaseDiagByVisitGuid(String visitGuid);

	 int insertMedicalRecordDiseaseDiag(MedicalRecordDiseaseDiag record);
	 
	 
	 List<Map<String,Object>> getDiseaseMedicalRecord(MedicalRecordSearchingQuery params);
		
	 int getDiseaseMedicalRecordCount(MedicalRecordSearchingQuery params);
	 
	 int insertMedicalRecordCodingError(MedicalRecordCodingError record);
	 
	 List<Map<String,Object>> getMedicalRecordOfRepair(MedicalRecordQuery params);
		
	 int getMedicalRecordCountOfRepair(MedicalRecordQuery params);
	 
	 List<Map<String,Object>> getMedicalRecordCodingErrorByVisitGuid(String visitGuid);
	 
	 int updateMedicalRecordCodingError(MedicalRecordCodingError record);
}
