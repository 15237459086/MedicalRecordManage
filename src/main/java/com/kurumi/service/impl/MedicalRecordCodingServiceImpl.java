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

import com.itextpdf.text.log.SysoCounter;
import com.kurumi.config.MyConfig;
import com.kurumi.mapper.BaseInfoMapper;
import com.kurumi.mapper.MedicalRecordCodingMapper;
import com.kurumi.mapper.MedicalRecordMapper;
import com.kurumi.pojo.MedicalRecord;
import com.kurumi.pojo.MedicalRecordCodingError;
import com.kurumi.pojo.MedicalRecordDiseaseDiag;
import com.kurumi.pojo.MedicalRecordTrace;
import com.kurumi.pojo.coding.BasicInfo;
import com.kurumi.pojo.coding.CostInfo;
import com.kurumi.pojo.coding.CureInfo;
import com.kurumi.pojo.coding.DiseaseDiagInfo;
import com.kurumi.pojo.coding.DiseaseDiagRecord;
import com.kurumi.pojo.coding.InfectionInfo;
import com.kurumi.pojo.coding.IntensiveCareInfo;
import com.kurumi.pojo.coding.NurseInfo;
import com.kurumi.pojo.coding.OperateInfo;
import com.kurumi.pojo.coding.PathologyDiseaseDiagRecord;
import com.kurumi.pojo.resource.MedicalRecordResource;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.query.MedicalRecordSearchingQuery;
import com.kurumi.query.StatisticsAnalysisQuery;
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
	
	@Autowired
	private MedicalRecordCodingMapper medicalRecordCodingMapper;
	
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
		Map<String, Object> cureInfo = (Map<String, Object>)jsonMap.get("cureInfo");
		String errorMessage = checkCodingEmpty(jsonMap);
		if(errorMessage != null){
			return errorMessage;
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
		List<MedicalRecordCodingError> codingErrors = checkCoding(jsonMap);
		if(!codingErrors.isEmpty()){
			List<Map<String,Object>> checkErrors = medicalRecordCodingMapper.getMedicalRecordCodingErrorByVisitGuid(visitGuid);
			Map<String, Object> checkErrorMap = new HashMap<String, Object>();
			for (Map<String, Object> checkError : checkErrors) {
				checkErrorMap.put((String)checkError.get("coding_error_code"), checkError);
			}
			for (MedicalRecordCodingError medicalRecordCodingError : codingErrors) {
				
				if(checkErrorMap.containsKey(medicalRecordCodingError.getCodingErrorCode())){
					medicalRecordCodingError.setVisitGuid(visitGuid);
					
					medicalRecordCodingError.setLastErrorCoderCode(userCode);
					medicalRecordCodingError.setLastErrorCoderName(userName);
					medicalRecordCodingError.setErrorStatusCode("ERROR");
					medicalRecordCodingError.setErrorStatusName("错误");
					medicalRecordCodingMapper.updateMedicalRecordCodingError(medicalRecordCodingError);
					checkErrorMap.remove(medicalRecordCodingError.getCodingErrorCode());
					
				}else{
					medicalRecordCodingError.setVisitGuid(visitGuid);
					medicalRecordCodingError.setCreateErrorCoderCode(userCode);
					medicalRecordCodingError.setCreateErrorCoderName(userName);
					medicalRecordCodingError.setLastErrorCoderCode(userCode);
					medicalRecordCodingError.setLastErrorCoderName(userName);
					medicalRecordCodingError.setErrorStatusCode("ERROR");
					medicalRecordCodingError.setErrorStatusName("错误");
					medicalRecordCodingMapper.insertMedicalRecordCodingError(medicalRecordCodingError);
				}
				
			}
			for (Object map : checkErrorMap.values()) {
				Map<String, Object> checkError = (Map<String, Object>)map;
				String codingErrorCode = (String)checkError.get("coding_error_code");
				if(!codingErrorCode.equalsIgnoreCase("FINISH")){
					MedicalRecordCodingError medicalRecordCodingError = new MedicalRecordCodingError();
					medicalRecordCodingError.setVisitGuid((String)checkError.get("visit_guid"));
					medicalRecordCodingError.setCodingErrorCode(codingErrorCode);
					medicalRecordCodingError.setLastErrorCoderCode(userCode);
					medicalRecordCodingError.setLastErrorCoderName(userName);
					medicalRecordCodingError.setErrorStatusCode("FINISH");
					medicalRecordCodingError.setErrorStatusName("完成");
					medicalRecordCodingMapper.updateMedicalRecordCodingError(medicalRecordCodingError);
				}
				
			}
		}else{
			List<Map<String,Object>> checkErrors = medicalRecordCodingMapper.getMedicalRecordCodingErrorByVisitGuid(visitGuid);
			for (Map<String, Object> checkError : checkErrors) {
				String codingErrorCode = (String)checkError.get("coding_error_code");
				if(!codingErrorCode.equalsIgnoreCase("FINISH")){
					MedicalRecordCodingError medicalRecordCodingError = new MedicalRecordCodingError();
					medicalRecordCodingError.setVisitGuid((String)checkError.get("visit_guid"));
					medicalRecordCodingError.setCodingErrorCode(codingErrorCode);
					medicalRecordCodingError.setLastErrorCoderCode(userCode);
					medicalRecordCodingError.setLastErrorCoderName(userName);
					medicalRecordCodingError.setErrorStatusCode("FINISH");
					medicalRecordCodingError.setErrorStatusName("完成");
					medicalRecordCodingMapper.updateMedicalRecordCodingError(medicalRecordCodingError);
				}
			}
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

	
	private String checkCodingEmpty(Map<String, Object> jsonMap){
		Map<String, Object> basicInfo = (Map<String, Object>)jsonMap.get("basicInfo");
		if(basicInfo == null){
			return "基本信息未填写";
		}
		
		//住院号
		String onlyId = (String)basicInfo.get("onlyId");
		if(StringUtil.meaningStr(onlyId) == null){
			return "基本信息>住院号不能为空！";
		}
		//病案号
		String mrId = (String)basicInfo.get("mrId");
		if(StringUtil.meaningStr(mrId) == null){
			return "基本信息>病案号不能为空！";
		}
		//住院次数
		Object visitNumberObj = (Object) basicInfo.get("visitNumber");
		if(visitNumberObj!=null){
			BigDecimal visitNumber = new BigDecimal(visitNumberObj.toString());
			if(visitNumber == null){
				return "基本信息>住院次数不能为空！";
			}
		}else{
			return "基本信息>住院次数不能为空！";
		}
		//医疗付费
		String medicalPayTypeCode = (String)basicInfo.get("medicalPayTypeCode");
		if(StringUtil.meaningStr(medicalPayTypeCode) == null){
			return "基本信息>医疗付费方式名称不能为请选择！";
		}
		
		//患者名
		String patientName = (String)basicInfo.get("patientName");
		if(StringUtil.meaningStr(patientName) == null){
			return "基本信息>患者名不能为空！";
		}
		//性别
		String sexCode = (String)basicInfo.get("sexCode");
		if(StringUtil.meaningStr(sexCode) == null){
			return "基本信息>性别名称不能为请选择！";
		}
		//出生日期
		Object birthdayObj = (Object)basicInfo.get("birthday");
		if(birthdayObj!=null){
			Date birthday;
			try {
				birthday = DateUtil.dateParse(birthdayObj.toString());
				if(birthday == null){
					return "基本信息>出生日期不能为空！";
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			return "基本信息>出生日期不能为空！";
		}
		
		
		//年龄（年）
		Object yearOfAgeObj = (Object) basicInfo.get("yearOfAge");
		if(yearOfAgeObj!=null){
			BigDecimal yearOfAge = new BigDecimal(yearOfAgeObj.toString());
			if(yearOfAge == null){
				return "基本信息>年龄（年）不能为空！";
			}
		}else{
			return "基本信息>年龄（年）不能为空！";
		}
		
		
		//国籍
		String nationalityName = (String)basicInfo.get("nationalityName");
		if(StringUtil.meaningStr(nationalityName) == null){
			return "基本信息>国籍名称不能为请选择！";
		}
		
		//民族
		String nationCode = (String)basicInfo.get("nationCode");
		if(StringUtil.meaningStr(nationCode) == null){
			return "基本信息>民族名称不能为请选择！";
		}

		//婚姻
		String marriageCode = (String)basicInfo.get("marriageCode");
		if(StringUtil.meaningStr(marriageCode) == null){
			return "基本信息>婚姻名称不能为请选择！";
		}

		//职业
		String professionName = (String)basicInfo.get("professionName");
		if(StringUtil.meaningStr(professionName) == null){
			return "基本信息>职业名称不能为请选择！";
		}

		//籍贯地址
		String nativePlace = (String)basicInfo.get("nativePlace");
		if(StringUtil.meaningStr(nativePlace) == null){
			return "基本信息>籍贯地址不能为空！";
		}

		//联系人名称
		String linkManName = (String)basicInfo.get("linkManName");
		if(StringUtil.meaningStr(linkManName) == null){
			return "基本信息>联系人名称不能为空！";
		}

		//联系人关系名称
		String linkManRelativeRelationName = (String)basicInfo.get("linkManRelativeRelationName");
		if(StringUtil.meaningStr(linkManRelativeRelationName) == null){
			return "基本信息>联系人关系不能为空！";
		}

		//联系人电话
		String linkManPhone = (String)basicInfo.get("linkManPhone");
		if(StringUtil.meaningStr(linkManPhone) == null){
			return "基本信息>联系人电话不能为空！";
		}

		//入院途径名称
		String inHospitalTypeCode = (String)basicInfo.get("inHospitalTypeCode");
		if(StringUtil.meaningStr(inHospitalTypeCode) == null){
			return "基本信息>入院方式名称不能为请选择！";
		}

		//入院科室名称
		String inDeptCode = (String)basicInfo.get("inDeptCode");
		if(StringUtil.meaningStr(inDeptCode) == null){
			return "基本信息>入院科室不能为请选择！";
		}

		//入院时间
		Object inHospitalDateTimeObj = (Object)basicInfo.get("inHospitalDateTime");
		if(inHospitalDateTimeObj != null){
			Date inHospitalDateTime;
			try {
				inHospitalDateTime = DateUtil.dateParse(DateUtil.DATE_TIME_FORMATE,inHospitalDateTimeObj.toString());
				if(inHospitalDateTime == null){
					return "基本信息>入院时间不能为空！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "基本信息>入院时间格式不正确！";
			}
			
		}else{
			return "基本信息>入院时间不能为空！";
		}

		//出院科室
		String outDeptCode = (String)basicInfo.get("outDeptCode");
		if(StringUtil.meaningStr(outDeptCode) == null){
			return "基本信息>出院科室不能为空！";
		}

		//出院时间
		Object outHospitalDateTimeObj = (Object)basicInfo.get("outHospitalDateTime");
		if(outHospitalDateTimeObj != null){
			Date outHospitalDateTime;
			try {
				outHospitalDateTime = DateUtil.dateParse(DateUtil.DATE_TIME_FORMATE, outHospitalDateTimeObj.toString());
				if(outHospitalDateTime == null){
					return "基本信息>出院时间不能为空！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				return "基本信息>出院时间格式不正确！";
			}
			
		}else{
			return "基本信息>出院时间不能为空！";
		}

		//住院天数
		Object inHospitalDayNumberObj = (Object)basicInfo.get("inHospitalDayNumber");
		if(inHospitalDayNumberObj!=null){
			BigDecimal inHospitalDayNumber = new BigDecimal(inHospitalDayNumberObj.toString());
			if(inHospitalDayNumber == null){
				return "基本信息>住院天数不能为空！";
			}
		}else{
			return "基本信息>住院天数不能为空！";
		}
				
		//出院方式
		String outHospitalTypeCode = (String)basicInfo.get("outHospitalTypeCode");
		if(StringUtil.meaningStr(outHospitalTypeCode) == null){
			return "基本信息>出院方式不能为空！";
		}
		
		Map<String, Object> cureInfo = (Map<String, Object>)jsonMap.get("cureInfo");
		if(cureInfo == null){
			return "治疗信息未填写";
		}
		
		//ABO血型
		String bloodTypeCode =(String)cureInfo.get("bloodTypeCode");
		if(StringUtil.meaningStr(bloodTypeCode) == null){
			return "治疗信息>ABO血型不能为空！";
		}
		
		//RH血型
		String rhBloodTypeCode =(String)cureInfo.get("rhBloodTypeCode");
		if(StringUtil.meaningStr(rhBloodTypeCode) == null){
			return "治疗信息>RH血型不能为空！";
		}
		
		Map<String, Object> diseaseDiagInfo = (Map<String, Object>)jsonMap.get("diseaseDiagInfo");
		if(diseaseDiagInfo == null){
			return "疾病诊断未填写";
		}
		Map<String, Object> mainDiagRecord = (Map<String, Object>)diseaseDiagInfo.get("mainDiagRecord");
		if(mainDiagRecord == null){
			return "主要诊断未填写";
		}
		String mainDiagCode = (String)mainDiagRecord.get("diseaseDiagCode");
		if(mainDiagCode == null){
			return "主要诊断未填写";
		}
		return null;
	}
	
	private List<MedicalRecordCodingError> checkCoding(Map<String, Object> jsonMap){
		List<MedicalRecordCodingError> codingErrors = new ArrayList<MedicalRecordCodingError>();
		Date currentDate = new Date();
		Map<String, Object> basicInfo = (Map<String, Object>)jsonMap.get("basicInfo");
		Object inHospitalDateTimeObj = (Object)basicInfo.get("inHospitalDateTime");
		Date inHospitalDateTime = null;
		try {
			inHospitalDateTime = DateUtil.dateParse(DateUtil.DATE_TIME_FORMATE,inHospitalDateTimeObj.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object outHospitalDateTimeObj = (Object)basicInfo.get("outHospitalDateTime");
		Date outHospitalDateTime = null;
		try {
			outHospitalDateTime = DateUtil.dateParse(DateUtil.DATE_TIME_FORMATE,outHospitalDateTimeObj.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(inHospitalDateTime.after(outHospitalDateTime)){
			MedicalRecordCodingError codingError = new MedicalRecordCodingError();
			codingError.setCodingErrorCode("10001");
			codingError.setCodingErrorName("出院日期小于入院日期或出院日期，入院日期大于当前日期!");
			codingErrors.add(codingError);
		}else if(inHospitalDateTime.after(currentDate) || outHospitalDateTime.after(currentDate)){
			MedicalRecordCodingError codingError = new MedicalRecordCodingError();
			codingError.setCodingErrorCode("10001");
			codingError.setCodingErrorName("出院日期小于入院日期或出院日期，入院日期大于当前日期!");
			codingErrors.add(codingError);
		}
		String outHospitalTypeCode =  (String)basicInfo.get("outHospitalTypeCode");
		String dealthReasonCode = (String)basicInfo.get("dealthReasonCode");
		Object dealthDateTimeOjb = (Object)basicInfo.get("dealthDateTime");
		String autopsyCode = (String)basicInfo.get("autopsyCode");
		if(outHospitalTypeCode.equalsIgnoreCase("5")){
			
			if(dealthReasonCode == null || dealthDateTimeOjb == null || autopsyCode == null){
				MedicalRecordCodingError codingError = new MedicalRecordCodingError();
				codingError.setCodingErrorCode("10002");
				codingError.setCodingErrorName("患者死亡出院，死亡事件，死亡原因，是否尸检未填写!");
				codingErrors.add(codingError);
			}
			
			if(dealthDateTimeOjb != null){
				Date dealthDateTime = null;
				try {
					dealthDateTime = DateUtil.dateParse(DateUtil.DATE_TIME_FORMATE,dealthDateTimeOjb.toString());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!(inHospitalDateTime.before(dealthDateTime)&&outHospitalDateTime.after(dealthDateTime))){
					MedicalRecordCodingError codingError = new MedicalRecordCodingError();
					codingError.setCodingErrorCode("10004");
					codingError.setCodingErrorName("患者死亡出院，死亡事件不再住院期间!");
					codingErrors.add(codingError);
				}
			}
		}else{
			if(dealthReasonCode != null || dealthDateTimeOjb != null || autopsyCode != null){
				MedicalRecordCodingError codingError = new MedicalRecordCodingError();
				codingError.setCodingErrorCode("10003");
				codingError.setCodingErrorName("患者非死亡出院，死亡事件，死亡原因，是否尸检不应填写!");
				codingErrors.add(codingError);
			}
		}
		return codingErrors;
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
		try{
			medicalRecordCodingMapper.deleteDiseaseDiagByVisitGuid(visitGuid);
			DiseaseDiagRecord outpatientDiagRecord = diseaseDiagInfo.getOutpatientDiagRecord();
			MedicalRecordDiseaseDiag outpatientDiseaseDiag = new MedicalRecordDiseaseDiag();
			outpatientDiseaseDiag.setVisitGuid(visitGuid);
			outpatientDiseaseDiag.setDiagTypeCode(outpatientDiagRecord.getDiagTypeCode());
			outpatientDiseaseDiag.setDiagTypeName(outpatientDiagRecord.getDiagTypeName());
			outpatientDiseaseDiag.setOriginalDiseaseDiagCode(outpatientDiagRecord.getDiseaseDiagOriginalCode());
			outpatientDiseaseDiag.setOriginalDiseaseDiagName(outpatientDiagRecord.getDiseaseDiagOriginalDesc());
			outpatientDiseaseDiag.setDiseaseDiagCode(outpatientDiagRecord.getDiseaseDiagCode());
			outpatientDiseaseDiag.setDiseaseDiagName(outpatientDiagRecord.getDiseaseDiagName());
			if(outpatientDiseaseDiag.getDiagTypeCode() != null && outpatientDiseaseDiag.getDiseaseDiagCode() != null){
				medicalRecordCodingMapper.insertMedicalRecordDiseaseDiag(outpatientDiseaseDiag);
			}
			
			
			DiseaseDiagRecord mainDiagRecord = diseaseDiagInfo.getMainDiagRecord();
			MedicalRecordDiseaseDiag mainDiseaseDiag = new MedicalRecordDiseaseDiag();
			mainDiseaseDiag.setVisitGuid(visitGuid);
			mainDiseaseDiag.setDiagTypeCode(mainDiagRecord.getDiagTypeCode());
			mainDiseaseDiag.setDiagTypeName(mainDiagRecord.getDiagTypeName());
			mainDiseaseDiag.setOriginalDiseaseDiagCode(mainDiagRecord.getDiseaseDiagOriginalCode());
			mainDiseaseDiag.setOriginalDiseaseDiagName(mainDiagRecord.getDiseaseDiagOriginalDesc());
			mainDiseaseDiag.setDiseaseDiagCode(mainDiagRecord.getDiseaseDiagCode());
			mainDiseaseDiag.setDiseaseDiagName(mainDiagRecord.getDiseaseDiagName());
			if(mainDiseaseDiag.getDiagTypeCode() != null && mainDiseaseDiag.getDiseaseDiagCode() != null){
				medicalRecordCodingMapper.insertMedicalRecordDiseaseDiag(mainDiseaseDiag);
			}
			
			List<DiseaseDiagRecord> damageAndVenenationDiagRecords = diseaseDiagInfo.getDamageAndVenenationDiagRecords();
			for (DiseaseDiagRecord damageAndVenenationDiagRecord : damageAndVenenationDiagRecords) {
				MedicalRecordDiseaseDiag damageAndVenenationDiseaseDiag = new MedicalRecordDiseaseDiag();
				damageAndVenenationDiseaseDiag.setVisitGuid(visitGuid);
				damageAndVenenationDiseaseDiag.setDiagTypeCode(damageAndVenenationDiagRecord.getDiagTypeCode());
				damageAndVenenationDiseaseDiag.setDiagTypeName(damageAndVenenationDiagRecord.getDiagTypeName());
				damageAndVenenationDiseaseDiag.setOriginalDiseaseDiagCode(damageAndVenenationDiagRecord.getDiseaseDiagOriginalCode());
				damageAndVenenationDiseaseDiag.setOriginalDiseaseDiagName(damageAndVenenationDiagRecord.getDiseaseDiagOriginalDesc());
				damageAndVenenationDiseaseDiag.setDiseaseDiagCode(damageAndVenenationDiagRecord.getDiseaseDiagCode());
				damageAndVenenationDiseaseDiag.setDiseaseDiagName(damageAndVenenationDiagRecord.getDiseaseDiagName());
				if(damageAndVenenationDiseaseDiag.getDiagTypeCode() != null && damageAndVenenationDiseaseDiag.getDiseaseDiagCode() != null){
					medicalRecordCodingMapper.insertMedicalRecordDiseaseDiag(damageAndVenenationDiseaseDiag);
				}
			}
			
			
			List<PathologyDiseaseDiagRecord> pathologyDiagRecords = diseaseDiagInfo.getPathologyDiagRecords();
			for (PathologyDiseaseDiagRecord pathologyDiagRecord : pathologyDiagRecords) {
				MedicalRecordDiseaseDiag pathologyDiseaseDiag = new MedicalRecordDiseaseDiag();
				pathologyDiseaseDiag.setVisitGuid(visitGuid);
				pathologyDiseaseDiag.setDiagTypeCode(pathologyDiagRecord.getDiagTypeCode());
				pathologyDiseaseDiag.setDiagTypeName(pathologyDiagRecord.getDiagTypeName());
				pathologyDiseaseDiag.setOriginalDiseaseDiagCode(pathologyDiagRecord.getDiseaseDiagOriginalCode());
				pathologyDiseaseDiag.setOriginalDiseaseDiagName(pathologyDiagRecord.getDiseaseDiagOriginalDesc());
				pathologyDiseaseDiag.setDiseaseDiagCode(pathologyDiagRecord.getDiseaseDiagCode());
				pathologyDiseaseDiag.setDiseaseDiagName(pathologyDiagRecord.getDiseaseDiagName());
				if(pathologyDiseaseDiag.getDiagTypeCode() != null && pathologyDiseaseDiag.getDiseaseDiagCode() != null){
					medicalRecordCodingMapper.insertMedicalRecordDiseaseDiag(pathologyDiseaseDiag);
				}
			}
			
			List<DiseaseDiagRecord> diseaseDiagRecords = diseaseDiagInfo.getDiseaseDiagRecords();
			for (DiseaseDiagRecord diseaseDiagRecord : diseaseDiagRecords) {
				MedicalRecordDiseaseDiag otherDiseaseDiag = new MedicalRecordDiseaseDiag();
				otherDiseaseDiag.setVisitGuid(visitGuid);
				otherDiseaseDiag.setDiagTypeCode(diseaseDiagRecord.getDiagTypeCode());
				otherDiseaseDiag.setDiagTypeName(diseaseDiagRecord.getDiagTypeName());
				otherDiseaseDiag.setOriginalDiseaseDiagCode(diseaseDiagRecord.getDiseaseDiagOriginalCode());
				otherDiseaseDiag.setOriginalDiseaseDiagName(diseaseDiagRecord.getDiseaseDiagOriginalDesc());
				otherDiseaseDiag.setDiseaseDiagCode(diseaseDiagRecord.getDiseaseDiagCode());
				otherDiseaseDiag.setDiseaseDiagName(diseaseDiagRecord.getDiseaseDiagName());
				if(otherDiseaseDiag.getDiagTypeCode() != null && otherDiseaseDiag.getDiseaseDiagCode() != null){
					medicalRecordCodingMapper.insertMedicalRecordDiseaseDiag(otherDiseaseDiag);
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int editCostInfo(String visitGuid, CostInfo costInfo, Map<String, Object> jsonMap) {
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
	public int editIntensiveCareInfo(String visitGuid,IntensiveCareInfo intensiveCareInfo, Map<String, Object> jsonMap) {
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
	public int editInfectionInfo(String visitGuid,InfectionInfo infectionInfo, Map<String, Object> jsonMap) {
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
	public List<Map<String, Object>> getDiseaseMedicalRecord(MedicalRecordSearchingQuery params) {
		// TODO Auto-generated method stub
		return medicalRecordCodingMapper.getDiseaseMedicalRecord(params);
	}

	@Override
	public int getDiseaseMedicalRecordCount(MedicalRecordSearchingQuery params) {
		// TODO Auto-generated method stub
		return medicalRecordCodingMapper.getDiseaseMedicalRecordCount(params);
	}

	@Override
	public List<Map<String, Object>> getMedicalRecordOfRepair(MedicalRecordQuery params) {
		// TODO Auto-generated method stub
		return medicalRecordCodingMapper.getMedicalRecordOfRepair(params);
	}

	@Override
	public int getMedicalRecordCountOfRepair(MedicalRecordQuery params) {
		// TODO Auto-generated method stub
		return medicalRecordCodingMapper.getMedicalRecordCountOfRepair(params);
	}

	@Override
	public List<Map<String, Object>> getMedicalRecordOfDefect(StatisticsAnalysisQuery params) {
		// TODO Auto-generated method stub
		return medicalRecordCodingMapper.getMedicalRecordOfDefect(params);
	}

	@Override
	public List<Map<String, Object>> getMedicalRecordOfDefectDetail(StatisticsAnalysisQuery params) {
		// TODO Auto-generated method stub
		return medicalRecordCodingMapper.getMedicalRecordOfDefectDetail(params);
	}

}
