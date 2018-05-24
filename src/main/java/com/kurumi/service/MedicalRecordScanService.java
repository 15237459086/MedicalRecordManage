package com.kurumi.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

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
	
	void scanImagesUpload(String visitGuid,MultipartFile[] uploadImages)throws IllegalStateException, IOException;

	List<Map<String,Object>> getImageFilesByVisitGuid(String visitGuid);
	
	List<Map<String,Object>> getImageFilesByVisitGuidAndPrinterTypeCode(String visitGuid,String printerTypeCode);
	
	List<Map<String,Object>> getImageFileByFileHash(String fileHash);
	
	int imagePagination(String visitGuid,String fileHash,String newPageTypeCode);
	
	
	List<Map<String,Object>> getPaginationCountByVisitGuid(String visitGuid);
	
	int getUnPaginationImageCountByVisitGuid(String visitGuid);
	
	int imagePaginationFinish(String visitGuid);
}
