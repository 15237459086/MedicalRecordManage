package com.kurumi.service;

import java.util.List;
import java.util.Map;

import com.kurumi.pojo.MedicalRecordPrinterApply;
import com.kurumi.pojo.MedicalRecordPrinterApplyItem;
import com.kurumi.query.MedicalRecordPrinterQuery;
import com.kurumi.query.MedicalRecordQuery;

public interface MedicalRecordPrinterService {

	/**
	 * 获取打印病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfPrinter(MedicalRecordQuery medicalRecordQuery);
	
	/**
	 * 获取打印病案数量
	 * @return
	 */
	int getMedicalRecordCountOfPrinter(MedicalRecordQuery medicalRecordQuery);
	
	
	int addMedicalRecordPrinterApply(MedicalRecordPrinterApply printerApply,MedicalRecordPrinterApplyItem printerApplyItem);

	List<Map<String,Object>> getPrinterApplyItemById(Integer printerApplyItemId);
	
	List<Map<String,Object>> getPrinterApplyItemByApplyId(Integer printerApplyId);
	
	List<Map<String,Object>> getPrinterApplyByPrinterQuery(MedicalRecordPrinterQuery record);
    
    int getPrinterApplyCountByPrinterQuery(MedicalRecordPrinterQuery record);
}
