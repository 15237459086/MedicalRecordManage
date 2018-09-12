package com.kurumi.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.DocumentException;
import com.kurumi.config.MyConfig;
import com.kurumi.mapper.BaseInfoMapper;
import com.kurumi.mapper.MedicalRecordImageMapper;
import com.kurumi.mapper.MedicalRecordMapper;
import com.kurumi.mapper.MedicalRecordOutPatientMapper;
import com.kurumi.mapper.MedicalRecordRadioTherapyMapper;
import com.kurumi.mapper.ScanImageMapper;
import com.kurumi.pojo.MedicalRecord;
import com.kurumi.pojo.MedicalRecordImage;
import com.kurumi.pojo.MedicalRecordOutPatient;
import com.kurumi.pojo.MedicalRecordRadioTherapy;
import com.kurumi.pojo.MedicalRecordTrace;
import com.kurumi.pojo.ScanImage;
import com.kurumi.pojo.resource.MedicalRecordResource;
import com.kurumi.service.InterfaceMedicalRecordService;
import com.kurumi.service.MedicalRecordScanService;
import com.kurumi.util.PDFUtil;
import com.kurumi.util.StringUtil;

@Service
public class InterfaceMedicalRecordServiceImpl implements InterfaceMedicalRecordService {

	private final String USER_CODE = "interfacer";
	
	private final String USER_NAME = "接口用户";
	
	@Autowired
	private MyConfig myConfig;
	
	@Autowired
	private ScanImageMapper scanImageMapper;
	
	@Autowired
	private MedicalRecordScanService medicalRecordScanService;
	
	@Autowired
	private MedicalRecordImageMapper medicalRecordImageMapper;
	
	@Autowired
	private MedicalRecordMapper medicalRecordMapper;
	
	@Autowired
	private MedicalRecordOutPatientMapper medicalRecordOutPatientMapper;
	
	@Autowired
	private MedicalRecordRadioTherapyMapper medicalRecordRadioTherapyMapper;
	
	
	
	@Autowired
	private BaseInfoMapper baseInfoMapper;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int importPaginationInfo(MedicalRecord medicalRecord,List<Map<String, Object>> scanImagesJson) throws DocumentException, IOException {
		// TODO Auto-generated method stub
		int count = 0;
		medicalRecord.setVersion(1);
		count = medicalRecordMapper.insert(medicalRecord);
		if(count < 0){
			return -1;
		}
		List<Map<String,Object>> treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_INIT);
		if(treatmentTraceTypes.size() == 0){
			return -2;
		}
		
		MedicalRecordTrace medicalRecordTrace = new MedicalRecordTrace();
		
		medicalRecordTrace.setVisitGuid(medicalRecord.getVisitGuid());
		medicalRecordTrace.setCreateUserId(USER_CODE);
		medicalRecordTrace.setCreateUserName(USER_NAME);
		medicalRecordTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
		medicalRecordTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
		count = medicalRecordMapper.insertMedicalRecordTrace(medicalRecordTrace);
		treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_PIGEONHOLE);
		if(treatmentTraceTypes.size() == 0){
			return -2;
		}
		MedicalRecordTrace pigeonholeTrace = new MedicalRecordTrace();
		
		pigeonholeTrace.setVisitGuid(medicalRecord.getVisitGuid());
		pigeonholeTrace.setCreateUserId(USER_CODE);
		pigeonholeTrace.setCreateUserName(USER_NAME);
		pigeonholeTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
		pigeonholeTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
		
		pigeonholeTrace.setTraceDateTime(new Date());
		count = medicalRecordMapper.insertMedicalRecordTrace(pigeonholeTrace);
		for (Map<String, Object> scanImageJson : scanImagesJson) {
			ScanImage scanImage = new ScanImage();
			String fileHash = (String)scanImageJson.get("fileHash");
			
			scanImage.setFileHash(fileHash);
			
			String originalFileName = (String)scanImageJson.get("originalFileName");
			
			scanImage.setOriginalFileName(originalFileName);
			
			
			String fileName = (String)scanImageJson.get("fileName");
			scanImage.setFileName(fileName);
			
			String fileType = (String)scanImageJson.get("fileType");
			scanImage.setFileType(fileType);
			
			scanImage.setCreateUserId(USER_CODE);
			scanImage.setCreateUserName(USER_NAME);
			if(scanImageMapper.selectByPrimaryKey(scanImage.getFileHash()) == null){
				scanImageMapper.insert(scanImage);
			}
			
			MedicalRecordImage medicalRecordImage = new MedicalRecordImage();
			medicalRecordImage.setFileHash(scanImage.getFileHash());
			medicalRecordImage.setVisitGuid(medicalRecord.getVisitGuid());
			String mrPageTypeCode = (String)scanImageJson.get("mrPageTypeCode");
			medicalRecordImage.setMrPageTypeCode(mrPageTypeCode);
			String mrPageTypeName = (String)scanImageJson.get("mrPageTypeName");
			medicalRecordImage.setMrPageTypeName(mrPageTypeName);
			medicalRecordImage.setCreateUserId(USER_CODE);
			medicalRecordImage.setCreateUserName(USER_NAME);
			medicalRecordImage.setLastUserId(USER_CODE);
			medicalRecordImage.setLastUserName(USER_NAME);
			if(medicalRecordImageMapper.getCountByFileHashAndVisitGuid(scanImage.getFileHash(), medicalRecord.getVisitGuid())==0){
				medicalRecordImageMapper.insert(medicalRecordImage);
			}
		}
		
		count = medicalRecordImageMapper.getUnPaginationImageCountByVisitGuid(medicalRecord.getVisitGuid());
		if(count != 0){
			return -2;
		}
		treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_PAGINATION);
		if(treatmentTraceTypes.size() == 0){
			return -2;
		}
		
		MedicalRecordTrace pigeonholeFinishTrace = new MedicalRecordTrace();
		pigeonholeFinishTrace.setVisitGuid(medicalRecord.getVisitGuid());
		pigeonholeFinishTrace.setCreateUserId(USER_CODE);
		pigeonholeFinishTrace.setCreateUserName(USER_NAME);
		pigeonholeFinishTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
		pigeonholeFinishTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
		count = medicalRecordMapper.insertMedicalRecordTrace(pigeonholeFinishTrace);
		List<String> objectiveCodes = new ArrayList<String>();
		objectiveCodes.add("2");
		objectiveCodes.add("1");
		List<Map<String,Object>> sourceFiles = medicalRecordImageMapper.getObjectiveImageFilesByVisitGuid(medicalRecord.getVisitGuid(), objectiveCodes);
		/*List<Map<String,Object>> sourceFiles = medicalRecordScanService.getImageFilesByVisitGuidAndPrinterTypeCode(medicalRecord.getVisitGuid(), myConfig.getDefaultPrintTypeCode());*/
		MedicalRecordResource medicalRecordResource = new MedicalRecordResource();
		/*medicalRecordResource.setCurrentVersion(myConfig.getCurrentVersion());*/
		medicalRecordResource.getImageRecources().addAll(sourceFiles);
		medicalRecordResource.setImageBasicPath(myConfig.getImageRecourcePath());
		String newPDFPath = myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(medicalRecord.getVisitGuid())+ medicalRecord.getVisitGuid()+"\\"+"publish\\"+medicalRecord.getMrId()+"_"+medicalRecord.getVisitNumber()+"_"+myConfig.getDefaultPrintTypeCode()+".pdf";
		medicalRecordResource.setNewPDFPath(newPDFPath);
		/*MedicalRecordPDFThread medicalRecordPDFThread = new MedicalRecordPDFThread(medicalRecordResource);
		medicalRecordPDFThread.start();*/
		PDFUtil.createPdf(medicalRecordResource.getImageRecources(),medicalRecordResource.getImageBasicPath(), medicalRecordResource.getNewPDFPath());
		return count;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int importPaginationInfo(MedicalRecordOutPatient medicalRecord, List<Map<String, Object>> scanImagesJson)
			throws DocumentException, IOException {
		// TODO Auto-generated method stub
		int count = 0;
		medicalRecord.setVersion(1);
		count = medicalRecordOutPatientMapper.insert(medicalRecord);
		if(count < 0){
			return -1;
		}
		List<Map<String,Object>> treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_INIT);
		if(treatmentTraceTypes.size() == 0){
			return -2;
		}
		
		MedicalRecordTrace medicalRecordTrace = new MedicalRecordTrace();
		
		medicalRecordTrace.setVisitGuid(medicalRecord.getVisitGuid());
		medicalRecordTrace.setCreateUserId(USER_CODE);
		medicalRecordTrace.setCreateUserName(USER_NAME);
		medicalRecordTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
		medicalRecordTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
		count = medicalRecordMapper.insertMedicalRecordTrace(medicalRecordTrace);
		treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_PIGEONHOLE);
		if(treatmentTraceTypes.size() == 0){
			return -2;
		}
		MedicalRecordTrace pigeonholeTrace = new MedicalRecordTrace();
		
		pigeonholeTrace.setVisitGuid(medicalRecord.getVisitGuid());
		pigeonholeTrace.setCreateUserId(USER_CODE);
		pigeonholeTrace.setCreateUserName(USER_NAME);
		pigeonholeTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
		pigeonholeTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
		
		pigeonholeTrace.setTraceDateTime(new Date());
		count = medicalRecordMapper.insertMedicalRecordTrace(pigeonholeTrace);
		for (Map<String, Object> scanImageJson : scanImagesJson) {
			ScanImage scanImage = new ScanImage();
			String fileHash = (String)scanImageJson.get("fileHash");
			
			scanImage.setFileHash(fileHash);
			
			String originalFileName = (String)scanImageJson.get("originalFileName");
			
			scanImage.setOriginalFileName(originalFileName);
			
			
			String fileName = (String)scanImageJson.get("fileName");
			scanImage.setFileName(fileName);
			
			String fileType = (String)scanImageJson.get("fileType");
			scanImage.setFileType(fileType);
			
			scanImage.setCreateUserId(USER_CODE);
			scanImage.setCreateUserName(USER_NAME);
			if(scanImageMapper.selectByPrimaryKey(scanImage.getFileHash()) == null){
				scanImageMapper.insert(scanImage);
			}
			
			MedicalRecordImage medicalRecordImage = new MedicalRecordImage();
			medicalRecordImage.setFileHash(scanImage.getFileHash());
			medicalRecordImage.setVisitGuid(medicalRecord.getVisitGuid());
			String mrPageTypeCode = (String)scanImageJson.get("mrPageTypeCode");
			medicalRecordImage.setMrPageTypeCode(mrPageTypeCode);
			String mrPageTypeName = (String)scanImageJson.get("mrPageTypeName");
			medicalRecordImage.setMrPageTypeName(mrPageTypeName);
			medicalRecordImage.setCreateUserId(USER_CODE);
			medicalRecordImage.setCreateUserName(USER_NAME);
			medicalRecordImage.setLastUserId(USER_CODE);
			medicalRecordImage.setLastUserName(USER_NAME);
			if(medicalRecordImageMapper.getCountByFileHashAndVisitGuid(scanImage.getFileHash(), medicalRecord.getVisitGuid())==0){
				medicalRecordImageMapper.insert(medicalRecordImage);
			}
		}
		
		count = medicalRecordImageMapper.getUnPaginationImageCountByVisitGuid(medicalRecord.getVisitGuid());
		if(count != 0){
			return -2;
		}
		treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_PAGINATION);
		if(treatmentTraceTypes.size() == 0){
			return -2;
		}
		
		MedicalRecordTrace pigeonholeFinishTrace = new MedicalRecordTrace();
		pigeonholeFinishTrace.setVisitGuid(medicalRecord.getVisitGuid());
		pigeonholeFinishTrace.setCreateUserId(USER_CODE);
		pigeonholeFinishTrace.setCreateUserName(USER_NAME);
		pigeonholeFinishTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
		pigeonholeFinishTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
		count = medicalRecordMapper.insertMedicalRecordTrace(pigeonholeFinishTrace);
		List<String> objectiveCodes = new ArrayList<String>();
		objectiveCodes.add("2");
		objectiveCodes.add("1");
		List<Map<String,Object>> sourceFiles = medicalRecordImageMapper.getObjectiveImageFilesByVisitGuid(medicalRecord.getVisitGuid(), objectiveCodes);
		/*List<Map<String,Object>> sourceFiles = medicalRecordScanService.getImageFilesByVisitGuidAndPrinterTypeCode(medicalRecord.getVisitGuid(), myConfig.getDefaultPrintTypeCode());*/
		MedicalRecordResource medicalRecordResource = new MedicalRecordResource();
		/*medicalRecordResource.setCurrentVersion(myConfig.getCurrentVersion());*/
		medicalRecordResource.getImageRecources().addAll(sourceFiles);
		medicalRecordResource.setImageBasicPath(myConfig.getImageRecourcePath());
		String newPDFPath = myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(medicalRecord.getVisitGuid())+ medicalRecord.getVisitGuid()+"\\"+"publish\\"+medicalRecord.getMrId()+"_"+myConfig.getDefaultPrintTypeCode()+".pdf";
		medicalRecordResource.setNewPDFPath(newPDFPath);
		/*MedicalRecordPDFThread medicalRecordPDFThread = new MedicalRecordPDFThread(medicalRecordResource);
		medicalRecordPDFThread.start();*/
		PDFUtil.createPdf(medicalRecordResource.getImageRecources(),medicalRecordResource.getImageBasicPath(), medicalRecordResource.getNewPDFPath());
		return count;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int importPaginationInfo(MedicalRecordRadioTherapy medicalRecord, List<Map<String, Object>> scanImagesJson)
			throws DocumentException, IOException {
		// TODO Auto-generated method stub
		
		int count = 0;
		medicalRecord.setVersion(1);
		count = medicalRecordRadioTherapyMapper.insert(medicalRecord);
		if(count < 0){
			return -1;
		}
		List<Map<String,Object>> treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_INIT);
		if(treatmentTraceTypes.size() == 0){
			return -2;
		}
		
		MedicalRecordTrace medicalRecordTrace = new MedicalRecordTrace();
		
		medicalRecordTrace.setVisitGuid(medicalRecord.getVisitGuid());
		medicalRecordTrace.setCreateUserId(USER_CODE);
		medicalRecordTrace.setCreateUserName(USER_NAME);
		medicalRecordTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
		medicalRecordTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
		count = medicalRecordMapper.insertMedicalRecordTrace(medicalRecordTrace);
		treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_PIGEONHOLE);
		if(treatmentTraceTypes.size() == 0){
			return -2;
		}
		MedicalRecordTrace pigeonholeTrace = new MedicalRecordTrace();
		
		pigeonholeTrace.setVisitGuid(medicalRecord.getVisitGuid());
		pigeonholeTrace.setCreateUserId(USER_CODE);
		pigeonholeTrace.setCreateUserName(USER_NAME);
		pigeonholeTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
		pigeonholeTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
		
		pigeonholeTrace.setTraceDateTime(new Date());
		count = medicalRecordMapper.insertMedicalRecordTrace(pigeonholeTrace);
		for (Map<String, Object> scanImageJson : scanImagesJson) {
			ScanImage scanImage = new ScanImage();
			String fileHash = (String)scanImageJson.get("fileHash");
			
			scanImage.setFileHash(fileHash);
			
			String originalFileName = (String)scanImageJson.get("originalFileName");
			
			scanImage.setOriginalFileName(originalFileName);
			
			
			String fileName = (String)scanImageJson.get("fileName");
			scanImage.setFileName(fileName);
			
			String fileType = (String)scanImageJson.get("fileType");
			scanImage.setFileType(fileType);
			
			scanImage.setCreateUserId(USER_CODE);
			scanImage.setCreateUserName(USER_NAME);
			if(scanImageMapper.selectByPrimaryKey(scanImage.getFileHash()) == null){
				scanImageMapper.insert(scanImage);
			}
			
			MedicalRecordImage medicalRecordImage = new MedicalRecordImage();
			medicalRecordImage.setFileHash(scanImage.getFileHash());
			medicalRecordImage.setVisitGuid(medicalRecord.getVisitGuid());
			String mrPageTypeCode = (String)scanImageJson.get("mrPageTypeCode");
			medicalRecordImage.setMrPageTypeCode(mrPageTypeCode);
			String mrPageTypeName = (String)scanImageJson.get("mrPageTypeName");
			medicalRecordImage.setMrPageTypeName(mrPageTypeName);
			medicalRecordImage.setCreateUserId(USER_CODE);
			medicalRecordImage.setCreateUserName(USER_NAME);
			medicalRecordImage.setLastUserId(USER_CODE);
			medicalRecordImage.setLastUserName(USER_NAME);
			if(medicalRecordImageMapper.getCountByFileHashAndVisitGuid(scanImage.getFileHash(), medicalRecord.getVisitGuid())==0){
				medicalRecordImageMapper.insert(medicalRecordImage);
			}
		}
		
		count = medicalRecordImageMapper.getUnPaginationImageCountByVisitGuid(medicalRecord.getVisitGuid());
		if(count != 0){
			return -2;
		}
		treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_PAGINATION);
		if(treatmentTraceTypes.size() == 0){
			return -2;
		}
		
		MedicalRecordTrace pigeonholeFinishTrace = new MedicalRecordTrace();
		pigeonholeFinishTrace.setVisitGuid(medicalRecord.getVisitGuid());
		pigeonholeFinishTrace.setCreateUserId(USER_CODE);
		pigeonholeFinishTrace.setCreateUserName(USER_NAME);
		pigeonholeFinishTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
		pigeonholeFinishTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
		count = medicalRecordMapper.insertMedicalRecordTrace(pigeonholeFinishTrace);
		List<String> objectiveCodes = new ArrayList<String>();
		objectiveCodes.add("2");
		objectiveCodes.add("1");
		List<Map<String,Object>> sourceFiles = medicalRecordImageMapper.getObjectiveImageFilesByVisitGuid(medicalRecord.getVisitGuid(), objectiveCodes);
		/*
		List<Map<String,Object>> sourceFiles = medicalRecordScanService.getImageFilesByVisitGuidAndPrinterTypeCode(medicalRecord.getVisitGuid(), myConfig.getDefaultPrintTypeCode());*/
		MedicalRecordResource medicalRecordResource = new MedicalRecordResource();
		/*medicalRecordResource.setCurrentVersion(myConfig.getCurrentVersion());*/
		medicalRecordResource.getImageRecources().addAll(sourceFiles);
		medicalRecordResource.setImageBasicPath(myConfig.getImageRecourcePath());
		String newPDFPath = myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(medicalRecord.getVisitGuid())+ medicalRecord.getVisitGuid()+"\\"+"publish\\"+medicalRecord.getMrId()+"_"+myConfig.getDefaultPrintTypeCode()+".pdf";
		medicalRecordResource.setNewPDFPath(newPDFPath);
		/*MedicalRecordPDFThread medicalRecordPDFThread = new MedicalRecordPDFThread(medicalRecordResource);
		medicalRecordPDFThread.start();*/
		PDFUtil.createPdf(medicalRecordResource.getImageRecources(),medicalRecordResource.getImageBasicPath(), medicalRecordResource.getNewPDFPath());
		return count;
	}

	@Override
	public List<String> getVisitGuidByRadioTherapyMrId(String mrId) {
		// TODO Auto-generated method stub
		return medicalRecordRadioTherapyMapper.getVisitGuidByMrId(mrId);
	}

	@Override
	public int checkMeditalRecordUniqOfRadioTherapy(String mrId) {
		// TODO Auto-generated method stub
		return medicalRecordRadioTherapyMapper.checkMeditalRecordUniq(mrId);
	}

	@Override
	public List<String> getVisitGuidByOutPatientMrId(String mrId) {
		// TODO Auto-generated method stub
		return medicalRecordOutPatientMapper.getVisitGuidByMrId(mrId);
	}

	@Override
	public int checkMeditalRecordUniqOfOutPatient(String mrId) {
		// TODO Auto-generated method stub
		return medicalRecordOutPatientMapper.checkMeditalRecordUniq(mrId);
	}

}
