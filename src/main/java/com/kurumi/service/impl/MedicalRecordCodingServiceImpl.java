package com.kurumi.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kurumi.config.MyConfig;
import com.kurumi.mapper.BaseInfoMapper;
import com.kurumi.mapper.MedicalRecordMapper;
import com.kurumi.pojo.MedicalRecord;
import com.kurumi.pojo.MedicalRecordTrace;
import com.kurumi.pojo.coding.BasicInfo;
import com.kurumi.pojo.coding.CureInfo;
import com.kurumi.pojo.coding.DiseaseDiagInfo;
import com.kurumi.pojo.coding.NurseInfo;
import com.kurumi.pojo.coding.OperateInfo;
import com.kurumi.pojo.resource.MedicalRecordResource;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.service.MedicalRecordCodingService;
import com.kurumi.thread.PageIndexPDFThread;
import com.kurumi.util.DateUtil;
import com.kurumi.util.FileUtil;
import com.kurumi.util.JsonUtil;
import com.kurumi.util.StringUtil;

@Service
public class MedicalRecordCodingServiceImpl implements MedicalRecordCodingService {

	@Autowired
	private MyConfig myConfig;
	
	@Autowired
	private BaseInfoMapper baseInfoMapper;
	
	@Autowired
	private MedicalRecordMapper medicalRecordMapper;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int editBasicInfo(String visitGuid, BasicInfo basicInfo,Map<String, Object> jsonMap) {
		// TODO Auto-generated method stub
		List<String> medicalRecordJsons = medicalRecordMapper.getMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
		jsonMap.put("visitGuid", visitGuid);
		String jsonMapJson = JsonUtil.objectToJson(jsonMap);
		MedicalRecord medicalRecord = medicalRecordMapper.selectByPrimaryKey(visitGuid);
		medicalRecord.setPatientName(basicInfo.getPatientName());
		medicalRecord.setSexCode(basicInfo.getSexCode());
		medicalRecord.setSexName(basicInfo.getSexName());
		medicalRecord.setBirthday(basicInfo.getBirthday());
		medicalRecord.setDocumentTypeCode(basicInfo.getDocumentTypeCode());
		medicalRecord.setDocumentTypeName(basicInfo.getDocumentTypeName());
		medicalRecord.setIdNumber(basicInfo.getIdNumber());
		medicalRecord.setOnlyId(basicInfo.getOnlyId());
		medicalRecord.setMrId(basicInfo.getMrId());
		medicalRecord.setVisitNumber(basicInfo.getVisitNumber().intValue());
		medicalRecord.setInHospitalDateTime(basicInfo.getInHospitalDateTime());
		medicalRecord.setInDeptCode(basicInfo.getInDeptCode());
		medicalRecord.setInDeptName(basicInfo.getInDeptName());
		medicalRecord.setOutHospitalDateTime(basicInfo.getOutHospitalDateTime());
		medicalRecord.setOutDeptCode(basicInfo.getOutDeptCode());
		medicalRecord.setOutDeptName(basicInfo.getOutDeptName());
		medicalRecord.setOutHospitalTypeCode(basicInfo.getOutHospitalTypeCode());
		medicalRecord.setOutHospitalTypeName(basicInfo.getOutHospitalTypeName());
		medicalRecordMapper.updateByPrimaryKey(medicalRecord);
		if(medicalRecordJsons.isEmpty()){
			medicalRecordMapper.insertMedicalRecordJson(jsonMapJson);
		}else{
			medicalRecordMapper.deleteMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
			medicalRecordMapper.insertMedicalRecordJson(jsonMapJson);
		}
		return 1;
	}

	@Override
	public List<String> getMedicalRecordJsonByVisitGuid(String visitGuid) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public String codingFinish(String visitGuid) {
		// TODO Auto-generated method stub
		List<String> jsonDatas = medicalRecordMapper.getMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if(!jsonDatas.isEmpty()){
			
			jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
		}else{
			return "该病案未编码";
		}
		Map<String, Object> basicInfo = (Map<String, Object>)jsonMap.get("basicInfo");
		if(basicInfo == null){
			return "基本信息未填写";
		}
		
		Map<String, Object> cureInfo = (Map<String, Object>)jsonMap.get("cureInfo");
		if(cureInfo == null){
			return "治疗信息未填写";
		}
		
		Map<String, Object> diseaseDiagInfo = (Map<String, Object>)jsonMap.get("diseaseDiagInfo");
		if(diseaseDiagInfo == null){
			return "疾病诊断未填写";
		}
		List<Map<String,Object>> treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_CODING);
		if(treatmentTraceTypes.size() == 0){
			return "编码枚举缺失";
		}
		Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
		Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
		String userCode = (String)currentUser.get("user_code");
		String userName = (String)currentUser.get("user_name");
		if(medicalRecordMapper.getMeditalRecordTraceCount(visitGuid, MedicalRecordTrace.VISIT_CODING) == 0){
			MedicalRecordTrace medicalRecordTrace = new MedicalRecordTrace();
			medicalRecordTrace.setVisitGuid(visitGuid);
			
			medicalRecordTrace.setCreateUserId(userCode);
			medicalRecordTrace.setCreateUserName(userName);
			medicalRecordTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
			medicalRecordTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
			
			medicalRecordMapper.insertMedicalRecordTrace(medicalRecordTrace);
		}
		jsonMap.put("userCode", userCode);
		jsonMap.put("userName", userName);
		List<Map<String,Object>> controlGrades = medicalRecordMapper.getQualityControlGradeByVisitGuid(visitGuid);
		
		cureInfo.put("finishCatalogDateTime", DateUtil.dateFormat(DateUtil.DATE_TIME_FORMATE, new Date()));
		List<Map<String,Object>> cureWorkers = (List<Map<String,Object>>)cureInfo.get("cureWorkers");
		for (Map<String, Object> cureWorker : cureWorkers) {
			String professionTitleCode = StringUtil.meaningStr((String)cureWorker.get("professionTitleCode"));
			if(professionTitleCode != null && professionTitleCode.equalsIgnoreCase("BMY")){
				cureWorker.put("medicalWorkerCode", userCode);
				cureWorker.put("medicalWorkerName", userName);
			}else if(professionTitleCode != null && professionTitleCode.equalsIgnoreCase("ZKYS")){
				if(!controlGrades.isEmpty()){
					Map<String,Object> controlGrade = controlGrades.get(0);
					String traceDateTimeFormat = (String)controlGrade.get("trace_date_time_format");
					cureInfo.put("qualityControlDateTime",traceDateTimeFormat);
										
					cureWorker.put("medicalWorkerCode", (String)controlGrade.get("create_user_id"));
					cureWorker.put("medicalWorkerName", (String)controlGrade.get("create_user_name"));
					Object scoreOjb = controlGrade.get("score");
					if(scoreOjb!= null){
						BigDecimal score = new BigDecimal(scoreOjb.toString());
						if(new BigDecimal("90").compareTo(score)<=0){
							cureInfo.put("medicalRecordQualityCode","1");
							cureInfo.put("medicalRecordQualityName","甲级");
						}else if(new BigDecimal("90").compareTo(score)>0 && new BigDecimal("75").compareTo(score)<=0){
							cureInfo.put("medicalRecordQualityCode","2");
							cureInfo.put("medicalRecordQualityName","乙级");
						}else{
							cureInfo.put("medicalRecordQualityCode","3");
							cureInfo.put("medicalRecordQualityName","丙级");
						}
					}
				}
				
			}
		}
		medicalRecordMapper.deleteMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
		String jsonMapJson = JsonUtil.objectToJson(jsonMap);
		medicalRecordMapper.insertMedicalRecordJson(jsonMapJson);
		String filePath = myConfig.getJsonRecourcePath() + StringUtil.getLocalPath(visitGuid);
		String versionFilePath = myConfig.getJsonRecourcePath()+ StringUtil.getLocalPath(visitGuid)+"version\\";
		String fileName = visitGuid + ".json";
		String versionFileName = visitGuid+"-" + DateUtil.dateFormat("yyyyMMddHHmmssssss", new Date()) + ".json";
		try {
			FileUtil.createOrEditFile(jsonMapJson, filePath, fileName);
			FileUtil.createOrEditFile(jsonMapJson, versionFilePath, versionFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MedicalRecordResource medicalRecordResource = new MedicalRecordResource();
		medicalRecordResource.setDataMap(jsonMap);
		medicalRecordResource.setPageIndexTemplatePDFPath(myConfig.getPageIndexpPdfTemplatePath());
		String newPDFPath = myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(visitGuid)+ visitGuid+"\\page_index.pdf";
		medicalRecordResource.setNewPDFPath(newPDFPath);
		PageIndexPDFThread pageIndexPDFThread = new PageIndexPDFThread(medicalRecordResource);
		pageIndexPDFThread.start();
		return null;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int editCureInfo(String visitGuid, CureInfo cureInfo, Map<String, Object> jsonMap) {
		// TODO Auto-generated method stub
		List<String> medicalRecordJsons = medicalRecordMapper.getMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
		jsonMap.put("visitGuid", visitGuid);
		String jsonMapJson = JsonUtil.objectToJson(jsonMap);
		if(medicalRecordJsons.isEmpty()){
			medicalRecordMapper.insertMedicalRecordJson(jsonMapJson);
		}else{
			medicalRecordMapper.deleteMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
			medicalRecordMapper.insertMedicalRecordJson(jsonMapJson);
		}
		return 1;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int editDiseaseDiagInfo(String visitGuid, DiseaseDiagInfo diseaseDiagInfo, Map<String, Object> jsonMap) {
		// TODO Auto-generated method stub
		List<String> medicalRecordJsons = medicalRecordMapper.getMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
		jsonMap.put("visitGuid", visitGuid);
		String jsonMapJson = JsonUtil.objectToJson(jsonMap);
		if(medicalRecordJsons.isEmpty()){
			medicalRecordMapper.insertMedicalRecordJson(jsonMapJson);
		}else{
			medicalRecordMapper.deleteMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
			medicalRecordMapper.insertMedicalRecordJson(jsonMapJson);
		}
		return 1;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int editOperateInfo(String visitGuid, OperateInfo operateInfo, Map<String, Object> jsonMap) {
		// TODO Auto-generated method stub
		List<String> medicalRecordJsons = medicalRecordMapper.getMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
		jsonMap.put("visitGuid", visitGuid);
		String jsonMapJson = JsonUtil.objectToJson(jsonMap);
		if(medicalRecordJsons.isEmpty()){
			medicalRecordMapper.insertMedicalRecordJson(jsonMapJson);
		}else{
			medicalRecordMapper.deleteMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
			medicalRecordMapper.insertMedicalRecordJson(jsonMapJson);
		}
		return 1;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int editNurseInfo(String visitGuid, NurseInfo nurseInfo, Map<String, Object> jsonMap) {
		// TODO Auto-generated method stub
		List<String> medicalRecordJsons = medicalRecordMapper.getMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
		jsonMap.put("visitGuid", visitGuid);
		String jsonMapJson = JsonUtil.objectToJson(jsonMap);
		if(medicalRecordJsons.isEmpty()){
			medicalRecordMapper.insertMedicalRecordJson(jsonMapJson);
		}else{
			medicalRecordMapper.deleteMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
			medicalRecordMapper.insertMedicalRecordJson(jsonMapJson);
		}
		return 1;
	}

	@Override
	public List<Map<String, Object>> mainDiseaseDiagCheck(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> errorMedicalRecords = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> medicalRecords = medicalRecordMapper.getMedicalRecordOfCheck(medicalRecordQuery);
		for (Map<String, Object> medicalRecord : medicalRecords) {
			String visitGuid = (String)medicalRecord.get("visit_guid");
			List<String> jsonDatas = medicalRecordMapper.getMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
			if(!jsonDatas.isEmpty()){
				Map<String, Object> jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
				Map<String, Object> diseaseDiagInfo = (Map<String, Object>)jsonMap.get("diseaseDiagInfo");
				if(diseaseDiagInfo == null){
					medicalRecord.put("check_desc", "主要诊断未填写");
					errorMedicalRecords.add(medicalRecord);
				}else{
					Map<String, Object> mainDiagRecord = (Map<String, Object>)diseaseDiagInfo.get("mainDiagRecord");
					if(mainDiagRecord == null){
						medicalRecord.put("check_desc", "主要诊断未填写");
						errorMedicalRecords.add(medicalRecord);
					}else{
						String diseaseDiagCode = (String)mainDiagRecord.get("diseaseDiagCode");
						if(diseaseDiagCode == null){
							medicalRecord.put("check_desc", "主要诊断未填写");
							errorMedicalRecords.add(medicalRecord);
						}
						continue;
					}
				}
			}else{
				medicalRecord.put("check_desc", "主要诊断未填写");
				errorMedicalRecords.add(medicalRecord);
			}
		}
		return errorMedicalRecords;
	}

	@Override
	public List<Map<String, Object>> inHospitalDayNumberCheck(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		List<Map<String, Object>> errorMedicalRecords = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> medicalRecords = medicalRecordMapper.getMedicalRecordOfCheck(medicalRecordQuery);
		for (Map<String, Object> medicalRecord : medicalRecords) {
			String visitGuid = (String)medicalRecord.get("visit_guid");
			List<String> jsonDatas = medicalRecordMapper.getMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
			if(!jsonDatas.isEmpty()){
				Map<String, Object> jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
				Map<String, Object> basicInfo = (Map<String, Object>)jsonMap.get("basicInfo");
				if(basicInfo == null){
					medicalRecord.put("check_desc", "入院日期，出院日期，住院天数未填写");
					errorMedicalRecords.add(medicalRecord);
				}else{
					StringBuilder errorMessage = new StringBuilder();
					Object inHospitalDateTime = (Object)basicInfo.get("inHospitalDateTime");
					if(inHospitalDateTime == null){
						errorMessage.append("入院日期，");
					}
					Object outHospitalDateTime = (Object)basicInfo.get("outHospitalDateTime");
					if(outHospitalDateTime == null){
						errorMessage.append("出院日期，");
					}
					Object inHospitalDayNumber = (Object)basicInfo.get("inHospitalDayNumber");
					if(inHospitalDayNumber == null){
						errorMessage.append("住院天数");
					}
					if(errorMessage.toString().length()>0){
						errorMessage.append("未填写");
						medicalRecord.put("check_desc", errorMessage.toString());
						errorMedicalRecords.add(medicalRecord);
					}else{
						try {
							Date inHospitalDate = DateUtil.dateParse(DateUtil.DATE_TIME_FORMATE, inHospitalDateTime.toString());
							Date outHospitalDate = DateUtil.dateParse(DateUtil.DATE_TIME_FORMATE, outHospitalDateTime.toString());
							int diffDay = DateUtil.getDaysBetween(outHospitalDate,inHospitalDate);
							BigDecimal inHospitalDay = new BigDecimal(inHospitalDayNumber.toString());
							if(inHospitalDay.doubleValue() > diffDay+1 || inHospitalDay.doubleValue() < diffDay-1){
								medicalRecord.put("check_desc","住院天数与入院日期和出院日期天数差不一致");
								errorMedicalRecords.add(medicalRecord);
							}
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}else{
				medicalRecord.put("check_desc", "入院日期，出院日期，住院天数未填写");
				errorMedicalRecords.add(medicalRecord);
			}
		}
		return errorMedicalRecords;
	
	}

	@Override
	public List<Map<String, Object>> repeatCodingCheck(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> errorMedicalRecords = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> medicalRecords = medicalRecordMapper.getMedicalRecordOfCheck(medicalRecordQuery);
		for (Map<String, Object> medicalRecord : medicalRecords) {
			String visitGuid = (String)medicalRecord.get("visit_guid");
			List<String> jsonDatas = medicalRecordMapper.getMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
			if(!jsonDatas.isEmpty()){
				Map<String, Object> jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
				Map<String, Object> diseaseDiagInfo = (Map<String, Object>)jsonMap.get("diseaseDiagInfo");
				if(diseaseDiagInfo != null){
					List<Map<String, Object>> diseaseDiagRecords = (List<Map<String, Object>>)diseaseDiagInfo.get("diseaseDiagRecords");
					if(diseaseDiagRecords != null){
						Set<String> diseaseDiagCodes = new HashSet<String>();
						for (Map<String, Object> diseaseDiagRecord : diseaseDiagRecords) {
							String diseaseDiagCode = (String)diseaseDiagRecord.get("diseaseDiagCode");
							if(diseaseDiagCode != null){
								if(diseaseDiagCodes.contains(diseaseDiagCode)){
									medicalRecord.put("check_desc", "诊断码"+diseaseDiagCode +"重复");
									errorMedicalRecords.add(medicalRecord);
									break;
								}else{
									diseaseDiagCodes.add(diseaseDiagCode);
								}
								
							}
						}
					}
				}
			}
		}
		return errorMedicalRecords;
	}

	@Override
	public List<Map<String, Object>> dateTimeCheck(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> errorMedicalRecords = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> medicalRecords = medicalRecordMapper.getMedicalRecordOfCheck(medicalRecordQuery);
		for (Map<String, Object> medicalRecord : medicalRecords) {
			String visitGuid = (String)medicalRecord.get("visit_guid");
			List<String> jsonDatas = medicalRecordMapper.getMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
			if(!jsonDatas.isEmpty()){
				Map<String, Object> jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
				Map<String, Object> basicInfo = (Map<String, Object>)jsonMap.get("basicInfo");
				if(basicInfo != null){
					Object birthdayOjb = (Object)basicInfo.get("birthday");
					if(birthdayOjb == null){
						continue;
					}
					
					/*String idNumber = StringUtil.meaningStr((String)basicInfo.get("idNumber"));
					if(idNumber == null){
						continue;
					}*/
					
					Object inHospitalDateTime = (Object)basicInfo.get("inHospitalDateTime");
					if(inHospitalDateTime == null){
						continue;
					}
					Object outHospitalDateTime = (Object)basicInfo.get("outHospitalDateTime");
					if(outHospitalDateTime == null){
						continue;
					}
					try {
						Date birthday = DateUtil.dateParse(birthdayOjb.toString());
						Date inHospitalDate = DateUtil.dateParse(DateUtil.DATE_TIME_FORMATE, inHospitalDateTime.toString());
						Date outHospitalDate = DateUtil.dateParse(DateUtil.DATE_TIME_FORMATE, outHospitalDateTime.toString());
						if(inHospitalDate.compareTo(outHospitalDate)>0){
							medicalRecord.put("check_desc", "入院日期大于出院日期");
							errorMedicalRecords.add(medicalRecord);
						}else if(inHospitalDate.compareTo(birthday) <=0){
							medicalRecord.put("check_desc", "出生日期大于入院日期");
							errorMedicalRecords.add(medicalRecord);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}
		}
		return errorMedicalRecords;
	}

}
