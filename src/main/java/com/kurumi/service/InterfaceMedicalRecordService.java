package com.kurumi.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.DocumentException;
import com.kurumi.pojo.MedicalRecord;
import com.kurumi.pojo.MedicalRecordOutPatient;
import com.kurumi.pojo.MedicalRecordRadioTherapy;

public interface InterfaceMedicalRecordService {
	
	List<String> getVisitGuidByOutPatientMrId(String mrId);
	
	/**
	 * 检验病案是否唯一
	 * @param onlyId
	 * @param mrId
	 * @param visitNumber
	 * @return
	 */
	int checkMeditalRecordUniqOfOutPatient(String mrId);

	List<String> getVisitGuidByRadioTherapyMrId(String mrId);
	
	/**
	 * 检验病案是否唯一
	 * @param onlyId
	 * @param mrId
	 * @param visitNumber
	 * @return
	 */
	int checkMeditalRecordUniqOfRadioTherapy(String mrId);
	
	
	int importPaginationInfo(MedicalRecord medicalRecord,List<Map<String, Object>> scanImagesJson)throws DocumentException, IOException;
	
	int importPaginationInfo(MedicalRecordOutPatient medicalRecord,List<Map<String, Object>> scanImagesJson)throws DocumentException, IOException;
	
	int importPaginationInfo(MedicalRecordRadioTherapy medicalRecord,List<Map<String, Object>> scanImagesJson)throws DocumentException, IOException;
}
