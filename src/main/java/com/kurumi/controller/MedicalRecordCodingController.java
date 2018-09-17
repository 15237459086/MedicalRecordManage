package com.kurumi.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurumi.config.MyConfig;
import com.kurumi.pojo.MedicalRecord;
import com.kurumi.pojo.RespondResult;
import com.kurumi.pojo.coding.BasicInfo;
import com.kurumi.pojo.coding.CostInfo;
import com.kurumi.pojo.coding.CureInfo;
import com.kurumi.pojo.coding.DiseaseDiagInfo;
import com.kurumi.pojo.coding.DiseaseDiagRecord;
import com.kurumi.pojo.coding.IntensiveCareInfo;
import com.kurumi.pojo.coding.NurseInfo;
import com.kurumi.pojo.coding.OperateInfo;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.query.StatisticsAnalysisQuery;
import com.kurumi.service.BaseInfoService;
import com.kurumi.service.MedicalRecordCodingService;
import com.kurumi.service.MedicalRecordService;
import com.kurumi.util.DateUtil;
import com.kurumi.util.FileUtil;
import com.kurumi.util.JsonUtil;
import com.kurumi.util.PDFUtil;
import com.kurumi.util.StringUtil;
import com.kurumi.util.WaterMarkUtil;

@Controller
@RequestMapping("/medical_record_coding")
public class MedicalRecordCodingController {

	@Autowired
	private MyConfig myConfig;
	
	@Autowired
	private BaseInfoService baseInfoService;
	
	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@Autowired
	private MedicalRecordCodingService medicalRecordCodingService;
	
	@GetMapping("/query_home_page")
	public String queryHomePage(Model model){
		return "coding/query_home_page";
	}
	
	@GetMapping("/ajax_query_home_page")
	@ResponseBody
	public RespondResult ajaxQueryHomePage(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			int count = 0;
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordService.getMedicalRecordOfHomePage(params);
				count = medicalRecordService.getMedicalRecordCountOfHomePage(params);
			}
			
			params.setTotalCounts(count);
			params.setQueryDatas(medicalRecords);
			respondResult = new RespondResult(true, RespondResult.successCode, null, params);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), params);
		}
		
		return respondResult;
	}
	
	
	@GetMapping("/edit_index_info")
	public String editHomePageIndexForm(String visitGuid,Model model){
		model.addAttribute("visitGuid", visitGuid);
		List<Map<String,Object>> medicalRecords = medicalRecordService.getMedicalRecordByVisitGuid(visitGuid);
		if(medicalRecords.size() == 0){
			RespondResult respondResult = new RespondResult(false,"404","网址错误，资源不存在",null);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
		}else{
			Map<String,Object> medicalRecord = medicalRecords.get(0);
			RespondResult respondResult = new RespondResult(true,"302","",medicalRecord);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
		}
		return "coding/edit_index_info";
	}
	
	@GetMapping("/edit_basic_info_form")
	public String editBasicInfoForm(String visitGuid,Model model){
		Map<String, List<Map<String, Object>>> baseInfo = baseInfoService.getBaseDataOfBasicInfo();
		String baseInfoJson = JsonUtil.objectToJson(baseInfo);
		model.addAttribute("baseInfoJson", baseInfoJson);
		model.addAttribute("visitGuid", visitGuid);
		/*String filePath = myConfig.getJsonRecourcePath() + StringUtil.getLocalPath(visitGuid);
		String fileName = visitGuid + ".json";
		
		String jsonData = FileUtil.readFile(filePath, fileName);*/
		List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(visitGuid);
		Map<String, Object> basicInfo = null;
		if(!jsonDatas.isEmpty()){
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
			basicInfo = (Map<String, Object>)jsonMap.get("basicInfo");
			
		}
		if(basicInfo == null){
			basicInfo = new HashMap<String, Object>();
			MedicalRecord medicalRecord = medicalRecordService.selectMedicalRecordByPrimaryKey(visitGuid);
			
			String patientName = medicalRecord.getPatientName();
			basicInfo.put("patientName",patientName);
			
			String sexCode = medicalRecord.getSexCode();
			basicInfo.put("sexCode",sexCode);
			
			String sexName = medicalRecord.getSexName();
			basicInfo.put("sexName",sexName);
			
			Date birthday = medicalRecord.getBirthday();
			if(birthday != null){
				basicInfo.put("birthday", DateUtil.dateFormat(birthday));
			}
			String documentTypeCode = medicalRecord.getDocumentTypeCode();
			basicInfo.put("documentTypeCode",documentTypeCode);
			
			String documentTypeName = medicalRecord.getDocumentTypeName();
			basicInfo.put("documentTypeName",documentTypeName);
			
			String idNumber = medicalRecord.getIdNumber();
			basicInfo.put("idNumber",idNumber);
			
			
			String inDeptCode = medicalRecord.getInDeptCode();
			basicInfo.put("inDeptCode",inDeptCode);
			
			String inDeptName = medicalRecord.getInDeptName();
			basicInfo.put("inDeptName",inDeptName);
			
			Date inHospitalDateTime = medicalRecord.getInHospitalDateTime();
			if(inHospitalDateTime != null){
				basicInfo.put("inHospitalDateTime", DateUtil.dateFormat(DateUtil.DATE_TIME_FORMATE,inHospitalDateTime));
			}
			
			
			Date outHospitalDateTime = medicalRecord.getOutHospitalDateTime();
			if(outHospitalDateTime != null){
				basicInfo.put("outHospitalDateTime", DateUtil.dateFormat(DateUtil.DATE_TIME_FORMATE,outHospitalDateTime));
			}
			
			
			
			String outDeptCode = medicalRecord.getOutDeptCode();
			basicInfo.put("outDeptCode",outDeptCode);
			
			String outDeptName = medicalRecord.getOutDeptName();
			basicInfo.put("outDeptName",outDeptName);
			
			String outHospitalTypeCode = medicalRecord.getOutHospitalTypeCode();
			basicInfo.put("outHospitalTypeCode",outHospitalTypeCode);
			
			String outHospitalTypeName = medicalRecord.getOutHospitalTypeName();
			basicInfo.put("outHospitalTypeName",outHospitalTypeName);
			
			String mrId = medicalRecord.getMrId();
			basicInfo.put("mrId",mrId);
			
			String onlyId = medicalRecord.getOnlyId();
			basicInfo.put("onlyId",onlyId);
			
			Integer visitNumber = medicalRecord.getVisitNumber();
			basicInfo.put("visitNumber",visitNumber);
		}
		
		
		String basicInfoJson = JsonUtil.objectToJson(basicInfo);
		model.addAttribute("basicInfoJson", basicInfoJson);
		model.addAttribute("basicInfo", basicInfo);
		return "coding/edit_basic_info";
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/edit_basic_info")
	public String editBasicInfo(BasicInfo basicInfo,HttpServletRequest request,Model model){
		
		try {
			basicInfo.getNativePlaceRegionalism().initProvinceCityCountyCode();
			basicInfo.getBirthRegionalism().initProvinceCityCountyCode();
			basicInfo.getRegisteredAddressRegionalism().initProvinceCityCountyCode();
			basicInfo.getPermanentAddressRegionalism().initProvinceCityCountyCode();
			String filePath = myConfig.getJsonRecourcePath() + StringUtil.getLocalPath(basicInfo.getVisitGuid());
			String versionFilePath = myConfig.getJsonRecourcePath()+ StringUtil.getLocalPath(basicInfo.getVisitGuid())+"version\\";
			String fileName = basicInfo.getVisitGuid() + ".json";
			String versionFileName = basicInfo.getVisitGuid()+"-" + DateUtil.dateFormat("yyyyMMddHHmmssssss", new Date()) + ".json";
			/*String jsonData = FileUtil.readFile(filePath, fileName);*/
			List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(basicInfo.getVisitGuid());
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			if(!jsonDatas.isEmpty()){
				jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
				if(jsonMap == null){
					jsonMap = new HashMap<String, Object>();
				}
			}
			
			Subject subject=SecurityUtils.getSubject();
			Session session = subject.getSession();
			Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
			String userCode =(String)currentUser.get("user_code");
			String userName =(String)currentUser.get("user_name");
			jsonMap.put("userCode", userCode);
			jsonMap.put("userName", userName);
			jsonMap.put("basicInfo", basicInfo);
			String jsonMapJson = JsonUtil.objectToJson(jsonMap);
			FileUtil.createOrEditFile(jsonMapJson, filePath, fileName);
			FileUtil.createOrEditFile(jsonMapJson, versionFilePath, versionFileName);
			medicalRecordCodingService.editBasicInfo(basicInfo.getVisitGuid(), basicInfo, jsonMap);
			RespondResult respondResult = new RespondResult(true,"200","保存成功",null);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
			jsonMap = JsonUtil.jsonToPojo(jsonMapJson, Map.class);
			Map<String, List<Map<String, Object>>> baseInfo = baseInfoService.getBaseDataOfBasicInfo();
			String baseInfoJson = JsonUtil.objectToJson(baseInfo);
			model.addAttribute("baseInfoJson", baseInfoJson);
			model.addAttribute("visitGuid", basicInfo.getVisitGuid());
			Map<String, Object> basicInfoMap = (Map<String, Object>)jsonMap.get("basicInfo");
			String basicInfoJson = JsonUtil.objectToJson(basicInfoMap);
			model.addAttribute("basicInfoJson", basicInfoJson);
			model.addAttribute("basicInfo",  basicInfoMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RespondResult respondResult = new RespondResult(false,"500","保存失败",null);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
		}
		
		return "coding/edit_basic_info";
	}
	
	
	@GetMapping("/edit_cure_info_form")
	public String editCureInfoForm(String visitGuid,Model model){
		Map<String, List<Map<String, Object>>> baseInfo = baseInfoService.getBaseDataOfCureInfo();
		String baseInfoJson = JsonUtil.objectToJson(baseInfo);
		model.addAttribute("baseInfoJson", baseInfoJson);
		model.addAttribute("visitGuid", visitGuid);
		/*String filePath = myConfig.getJsonRecourcePath() + StringUtil.getLocalPath(visitGuid);
		String fileName = visitGuid + ".json";
		String jsonData = FileUtil.readFile(filePath, fileName);*/
		List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(visitGuid);
		Map<String, Object> cureInfo = new HashMap<String, Object>();
		if(!jsonDatas.isEmpty()){
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
			cureInfo = (Map<String, Object>)jsonMap.get("cureInfo");
			if(cureInfo == null){
				cureInfo = new HashMap<String, Object>();
			}
		}
		
		
		String cureInfoJson = JsonUtil.objectToJson(cureInfo);
		model.addAttribute("cureInfo", cureInfo);
		model.addAttribute("basicInfoJson", cureInfoJson);
		model.addAttribute("cureInfoJson", cureInfoJson);
		return "coding/edit_cure_info";
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/edit_cure_info")
	public String editCureInfo(CureInfo cureInfo,HttpServletRequest request,Model model){
		
		try {
			String filePath = myConfig.getJsonRecourcePath() + StringUtil.getLocalPath(cureInfo.getVisitGuid());
			String versionFilePath = myConfig.getJsonRecourcePath()+ StringUtil.getLocalPath(cureInfo.getVisitGuid())+"version\\";
			String fileName = cureInfo.getVisitGuid() + ".json";
			String versionFileName = cureInfo.getVisitGuid()+"-" + DateUtil.dateFormat("yyyyMMddHHmmssssss", new Date()) + ".json";
			/*String jsonData = FileUtil.readFile(filePath, fileName);*/
			List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(cureInfo.getVisitGuid());
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			if(!jsonDatas.isEmpty()){
				jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
				if(jsonMap == null){
					jsonMap = new HashMap<String, Object>();
				}
			}
			
			Subject subject=SecurityUtils.getSubject();
			Session session = subject.getSession();
			Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
			String userCode =(String)currentUser.get("user_code");
			String userName =(String)currentUser.get("user_name");
			jsonMap.put("userCode", userCode);
			jsonMap.put("userName", userName);
			jsonMap.put("cureInfo", cureInfo);
			String jsonMapJson = JsonUtil.objectToJson(jsonMap);
			FileUtil.createOrEditFile(jsonMapJson, filePath, fileName);
			FileUtil.createOrEditFile(jsonMapJson, versionFilePath, versionFileName);
			medicalRecordCodingService.editCureInfo(cureInfo.getVisitGuid(), cureInfo, jsonMap);
			RespondResult respondResult = new RespondResult(true,"200","保存成功",null);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
			jsonMap = JsonUtil.jsonToPojo(jsonMapJson, Map.class);
			Map<String, List<Map<String, Object>>> baseInfo = baseInfoService.getBaseDataOfCureInfo();
			String baseInfoJson = JsonUtil.objectToJson(baseInfo);
			model.addAttribute("baseInfoJson", baseInfoJson);
			model.addAttribute("visitGuid", cureInfo.getVisitGuid());
			Map<String, Object> cureInfoMap = (Map<String, Object>)jsonMap.get("cureInfo");
			String cureInfoJson = JsonUtil.objectToJson(cureInfoMap);
			model.addAttribute("cureInfoJson", cureInfoJson);
			model.addAttribute("cureInfo",  cureInfoMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RespondResult respondResult = new RespondResult(false,"500","保存失败",null);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
		}
		
		return "coding/edit_cure_info";
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/edit_disease_diag_info_form")
	public String editDiseaseDiagInfoForm(String visitGuid,Model model){
		Map<String, List<Map<String, Object>>> baseInfo = baseInfoService.getBaseDataOfDiagInfo();
		String baseInfoJson = JsonUtil.objectToJson(baseInfo);
		model.addAttribute("baseInfoJson", baseInfoJson);
		model.addAttribute("visitGuid", visitGuid);
		/*String filePath = myConfig.getJsonRecourcePath() + StringUtil.getLocalPath(visitGuid);
		String fileName = visitGuid + ".json";
		String jsonData = FileUtil.readFile(filePath, fileName);*/
		List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(visitGuid);
		Map<String, Object> diseaseDiagInfo =  new HashMap<String, Object>();
		if(!jsonDatas.isEmpty()){
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
			diseaseDiagInfo = (Map<String, Object>)jsonMap.get("diseaseDiagInfo");
			if(diseaseDiagInfo == null){
				diseaseDiagInfo = new HashMap<String, Object>();
			}
		}
		
		model.addAttribute("diseaseDiagInfo", diseaseDiagInfo);
		String diseaseDiagInfoJson = JsonUtil.objectToJson(diseaseDiagInfo);
		model.addAttribute("diseaseDiagInfoJson", diseaseDiagInfoJson);
		return "coding/edit_disease_diag_info";
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/edit_disease_diag_info")
	public String editDiseaseDiagInfo(DiseaseDiagInfo diseaseDiagInfo,HttpServletRequest request,Model model){
		
		try {
			
			String filePath = myConfig.getJsonRecourcePath() + StringUtil.getLocalPath(diseaseDiagInfo.getVisitGuid());
			String versionFilePath = myConfig.getJsonRecourcePath()+ StringUtil.getLocalPath(diseaseDiagInfo.getVisitGuid())+"version\\";
			String fileName = diseaseDiagInfo.getVisitGuid() + ".json";
			String versionFileName = diseaseDiagInfo.getVisitGuid()+"-" + DateUtil.dateFormat("yyyyMMddHHmmssssss", new Date()) + ".json";
			
			/*String jsonData = FileUtil.readFile(filePath, fileName);*/
			List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(diseaseDiagInfo.getVisitGuid());
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			if(!jsonDatas.isEmpty()){
				jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
				if(jsonMap == null){
					jsonMap = new HashMap<String, Object>();
				}
			}
			diseaseDiagInfo.clearEmpty();
			Collections.sort(diseaseDiagInfo.getDiseaseDiagRecords(),
				new DiseaseDiagRecord.DiseaseDiagRecordComparator());
			Subject subject=SecurityUtils.getSubject();
			Session session = subject.getSession();
			Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
			String userCode =(String)currentUser.get("user_code");
			String userName =(String)currentUser.get("user_name");
			jsonMap.put("userCode", userCode);
			jsonMap.put("userName", userName);
			jsonMap.put("diseaseDiagInfo", diseaseDiagInfo);
			String jsonMapJson = JsonUtil.objectToJson(jsonMap);
			FileUtil.createOrEditFile(jsonMapJson, filePath, fileName);
			FileUtil.createOrEditFile(jsonMapJson, versionFilePath, versionFileName);
			jsonMap = JsonUtil.jsonToPojo(jsonMapJson, Map.class);
			medicalRecordCodingService.editDiseaseDiagInfo(diseaseDiagInfo.getVisitGuid(), diseaseDiagInfo, jsonMap);
			Map<String, List<Map<String, Object>>> baseInfo = baseInfoService.getBaseDataOfDiagInfo();
			String baseInfoJson = JsonUtil.objectToJson(baseInfo);
			model.addAttribute("baseInfoJson", baseInfoJson);
			model.addAttribute("visitGuid", diseaseDiagInfo.getVisitGuid());
			model.addAttribute("diseaseDiagInfo", diseaseDiagInfo);
			String diseaseDiagInfoJson = JsonUtil.objectToJson(diseaseDiagInfo);
			model.addAttribute("diseaseDiagInfoJson", diseaseDiagInfoJson);
			RespondResult respondResult = new RespondResult(true,"200","保存成功",null);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RespondResult respondResult = new RespondResult(false,"500","保存失败",null);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
		}
		
		return "coding/edit_disease_diag_info";
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/edit_operate_info_form")
	public String editOperateInfoForm(String visitGuid,Model model){
		Map<String, List<Map<String, Object>>> baseInfo = baseInfoService.getBaseDataOfOperateInfo();
		String baseInfoJson = JsonUtil.objectToJson(baseInfo);
		model.addAttribute("baseInfoJson", baseInfoJson);
		model.addAttribute("visitGuid", visitGuid);
		/*String filePath = myConfig.getJsonRecourcePath() + StringUtil.getLocalPath(visitGuid);
		String fileName = visitGuid + ".json";
		String jsonData = FileUtil.readFile(filePath, fileName);*/
		List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(visitGuid);
		
		Map<String, Object> operateInfo =new HashMap<String, Object>();
		if(!jsonDatas.isEmpty()){
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
			operateInfo = (Map<String, Object>)jsonMap.get("operateInfo");
			if(operateInfo == null){
				operateInfo = new HashMap<String, Object>();
			}
		}
		
		model.addAttribute("operateInfo", operateInfo);
		String operateInfoJson = JsonUtil.objectToJson(operateInfo);
		model.addAttribute("operateInfoJson", operateInfoJson);
		return "coding/edit_operate_info";
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/edit_operate_info")
	public String editOperateInfo(OperateInfo operateInfo,HttpServletRequest request,Model model){
		try {
			
			String filePath = myConfig.getJsonRecourcePath() + StringUtil.getLocalPath(operateInfo.getVisitGuid());
			String versionFilePath = myConfig.getJsonRecourcePath()+ StringUtil.getLocalPath(operateInfo.getVisitGuid())+"version\\";
			String fileName = operateInfo.getVisitGuid() + ".json";
			String versionFileName = operateInfo.getVisitGuid()+"-" + DateUtil.dateFormat("yyyyMMddHHmmssssss", new Date()) + ".json";
			
			/*String jsonData = FileUtil.readFile(filePath, fileName);*/
			List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(operateInfo.getVisitGuid());
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			if(!jsonDatas.isEmpty()){
				jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
				if(jsonMap == null){
					jsonMap = new HashMap<String, Object>();
				}
			}
			operateInfo.clearEmpty();
			Subject subject=SecurityUtils.getSubject();
			Session session = subject.getSession();
			Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
			String userCode =(String)currentUser.get("user_code");
			String userName =(String)currentUser.get("user_name");
			jsonMap.put("userCode", userCode);
			jsonMap.put("userName", userName);
			jsonMap.put("operateInfo", operateInfo);
			String jsonMapJson = JsonUtil.objectToJson(jsonMap);
			FileUtil.createOrEditFile(jsonMapJson, filePath, fileName);
			FileUtil.createOrEditFile(jsonMapJson, versionFilePath, versionFileName);
			medicalRecordCodingService.editOperateInfo(operateInfo.getVisitGuid(), operateInfo, jsonMap);
			jsonMap = JsonUtil.jsonToPojo(jsonMapJson, Map.class);
			Map<String, List<Map<String, Object>>> baseInfo = baseInfoService.getBaseDataOfOperateInfo();
			String baseInfoJson = JsonUtil.objectToJson(baseInfo);
			model.addAttribute("baseInfoJson", baseInfoJson);
			model.addAttribute("visitGuid", operateInfo.getVisitGuid());
			
			Map<String, Object> operateInfoMap = (Map<String, Object>)jsonMap.get("operateInfo");
			String operateInfoJson = JsonUtil.objectToJson(operateInfoMap);
			model.addAttribute("operateInfo", operateInfoMap);
			
			model.addAttribute("operateInfoJson", operateInfoJson);
			RespondResult respondResult = new RespondResult(true,"200","保存成功",null);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RespondResult respondResult = new RespondResult(false,"500","保存失败",null);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
		}
		
		return "coding/edit_operate_info";
	}
	
	@GetMapping("/edit_nurse_info_form")
	public String editNurseInfoForm(String visitGuid,Model model){
		model.addAttribute("visitGuid", visitGuid);
		List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(visitGuid);
		
		Map<String, Object> nurseInfo = new HashMap<String, Object>();
		if(!jsonDatas.isEmpty()){
			Map<String, Object> jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
			nurseInfo = (Map<String, Object>)jsonMap.get("nurseInfo");
			if(nurseInfo == null){
				nurseInfo = new HashMap<String, Object>();
			}
		}
		
		String nurseInfoJson = JsonUtil.objectToJson(nurseInfo);
		model.addAttribute("nurseInfo", nurseInfo);
		model.addAttribute("nurseInfoJson", nurseInfoJson);
		return "coding/edit_nurse_info";
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/edit_nurse_info")
	public String editNurseInfo(NurseInfo nurseInfo,HttpServletRequest request,Model model){
		try {
			
			String filePath = myConfig.getJsonRecourcePath() + StringUtil.getLocalPath(nurseInfo.getVisitGuid());
			String versionFilePath = myConfig.getJsonRecourcePath()+ StringUtil.getLocalPath(nurseInfo.getVisitGuid())+"version\\";
			String fileName = nurseInfo.getVisitGuid() + ".json";
			String versionFileName = nurseInfo.getVisitGuid()+"-" + DateUtil.dateFormat("yyyyMMddHHmmssssss", new Date()) + ".json";
			
			/*String jsonData = FileUtil.readFile(filePath, fileName);*/
			List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(nurseInfo.getVisitGuid());
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			if(!jsonDatas.isEmpty()){
				jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
				if(jsonMap == null){
					jsonMap = new HashMap<String, Object>();
				}
			}
			Subject subject=SecurityUtils.getSubject();
			Session session = subject.getSession();
			Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
			String userCode =(String)currentUser.get("user_code");
			String userName =(String)currentUser.get("user_name");
			jsonMap.put("userCode", userCode);
			jsonMap.put("userName", userName);
			jsonMap.put("nurseInfo", nurseInfo);
			String jsonMapJson = JsonUtil.objectToJson(jsonMap);
			FileUtil.createOrEditFile(jsonMapJson, filePath, fileName);
			FileUtil.createOrEditFile(jsonMapJson, versionFilePath, versionFileName);
			medicalRecordCodingService.editNurseInfo(nurseInfo.getVisitGuid(), nurseInfo, jsonMap);
			jsonMap = JsonUtil.jsonToPojo(jsonMapJson, Map.class);
			model.addAttribute("visitGuid", nurseInfo.getVisitGuid());
			
			Map<String, Object> nurseInfoMap = (Map<String, Object>)jsonMap.get("nurseInfo");
			String nurseInfoJson = JsonUtil.objectToJson(nurseInfoMap);
			model.addAttribute("nurseInfo", nurseInfoMap);
			
			model.addAttribute("nurseInfoJson",nurseInfoJson);
			RespondResult respondResult = new RespondResult(true,"200","保存成功",null);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RespondResult respondResult = new RespondResult(false,"500","保存失败",null);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
		}
		
		return "coding/edit_nurse_info";
	}
	
	@GetMapping("/edit_intensive_care_info_form")
	public String editIntensiveCareInfoForm(String visitGuid,Model model){
		Map<String, List<Map<String, Object>>> baseInfo = baseInfoService.getBaseDataOfIntensiveCareInfo();
		String baseInfoJson = JsonUtil.objectToJson(baseInfo);
		model.addAttribute("baseInfoJson", baseInfoJson);
		model.addAttribute("visitGuid", visitGuid);
		List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(visitGuid);
		
		Map<String, Object> intensiveCareInfo = new HashMap<String, Object>();
		if(!jsonDatas.isEmpty()){
			Map<String, Object> jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
			intensiveCareInfo = (Map<String, Object>)jsonMap.get("intensiveCareInfo");
			if(intensiveCareInfo == null){
				intensiveCareInfo = new HashMap<String, Object>();
			}
		}
		
		String intensiveCareInfoJson = JsonUtil.objectToJson(intensiveCareInfo);
		model.addAttribute("intensiveCareInfo", intensiveCareInfo);
		model.addAttribute("intensiveCareInfoJson", intensiveCareInfoJson);
		return "coding/edit_intensive_care_info";
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/edit_intensive_care_info")
	public String editIntensiveCareInfo(IntensiveCareInfo intensiveCareInfo,HttpServletRequest request,Model model){
		try {
			
			String filePath = myConfig.getJsonRecourcePath() + StringUtil.getLocalPath(intensiveCareInfo.getVisitGuid());
			String versionFilePath = myConfig.getJsonRecourcePath()+ StringUtil.getLocalPath(intensiveCareInfo.getVisitGuid())+"version\\";
			String fileName = intensiveCareInfo.getVisitGuid() + ".json";
			String versionFileName = intensiveCareInfo.getVisitGuid()+"-" + DateUtil.dateFormat("yyyyMMddHHmmssssss", new Date()) + ".json";
			
			/*String jsonData = FileUtil.readFile(filePath, fileName);*/
			List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(intensiveCareInfo.getVisitGuid());
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			if(!jsonDatas.isEmpty()){
				jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
 				if(jsonMap == null){
					jsonMap = new HashMap<String, Object>();
				}
			}
 			Subject subject=SecurityUtils.getSubject();
			Session session = subject.getSession();
			Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
			String userCode =(String)currentUser.get("user_code");
			String userName =(String)currentUser.get("user_name");
 			jsonMap.put("userCode", userCode);
 			jsonMap.put("userName", userName);
 			jsonMap.put("intensiveCareInfo", intensiveCareInfo);
 			String jsonMapJson = JsonUtil.objectToJson(jsonMap);
 			FileUtil.createOrEditFile(jsonMapJson, filePath, fileName);
 			FileUtil.createOrEditFile(jsonMapJson, versionFilePath, versionFileName);
  			medicalRecordCodingService.editIntensiveCareInfo(intensiveCareInfo.getVisitGuid(), intensiveCareInfo, jsonMap);
  			jsonMap = JsonUtil.jsonToPojo(jsonMapJson, Map.class);
 			model.addAttribute("visitGuid", intensiveCareInfo.getVisitGuid());
			
 			Map<String, Object> intensiveCareInfoMap = (Map<String, Object>)jsonMap.get("intensiveCareInfo");
			String intensiveCareInfoJson = JsonUtil.objectToJson(intensiveCareInfoMap);
			model.addAttribute("intensiveCareInfo", intensiveCareInfoMap);
			
			model.addAttribute("intensiveCareInfoJson",intensiveCareInfoJson);
			RespondResult respondResult = new RespondResult(true,"200","保存成功",null);
			Map<String, List<Map<String, Object>>> baseInfo = baseInfoService.getBaseDataOfIntensiveCareInfo();
			String baseInfoJson = JsonUtil.objectToJson(baseInfo);
			model.addAttribute("baseInfoJson", baseInfoJson);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RespondResult respondResult = new RespondResult(false,"500","保存失败",null);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
		}
		return "coding/edit_intensive_care_info";
	}
	
	@GetMapping("/edit_cost_info_form")
	public String editCostInfoForm(String visitGuid,Model model){
		model.addAttribute("visitGuid", visitGuid);
		List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(visitGuid);
		
		Map<String, Object> costInfo = new HashMap<String, Object>();
		if(!jsonDatas.isEmpty()){
			Map<String, Object> jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
			costInfo = (Map<String, Object>)jsonMap.get("costInfo");
			if(costInfo == null){
				costInfo = new HashMap<String, Object>();
			}
		}
		
		String costInfoJson = JsonUtil.objectToJson(costInfo);
		model.addAttribute("costInfo", costInfo);
		model.addAttribute("costInfoJson", costInfoJson);
		return "coding/edit_cost_info";
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/edit_cost_info")
	public String editCostInfo(CostInfo costInfo,HttpServletRequest request,Model model){
		try {
			
			String filePath = myConfig.getJsonRecourcePath() + StringUtil.getLocalPath(costInfo.getVisitGuid());
			String versionFilePath = myConfig.getJsonRecourcePath()+ StringUtil.getLocalPath(costInfo.getVisitGuid())+"version\\";
			String fileName = costInfo.getVisitGuid() + ".json";
			String versionFileName = costInfo.getVisitGuid()+"-" + DateUtil.dateFormat("yyyyMMddHHmmssssss", new Date()) + ".json";
			
			/*String jsonData = FileUtil.readFile(filePath, fileName);*/
			List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(costInfo.getVisitGuid());
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			if(!jsonDatas.isEmpty()){
				jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
 				if(jsonMap == null){
					jsonMap = new HashMap<String, Object>();
				}
			}
 			Subject subject=SecurityUtils.getSubject();
			Session session = subject.getSession();
			Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
			String userCode =(String)currentUser.get("user_code");
			String userName =(String)currentUser.get("user_name");
 			jsonMap.put("userCode", userCode);
 			jsonMap.put("userName", userName);
 			jsonMap.put("costInfo", costInfo);
 			String jsonMapJson = JsonUtil.objectToJson(jsonMap);
 			FileUtil.createOrEditFile(jsonMapJson, filePath, fileName);
 			FileUtil.createOrEditFile(jsonMapJson, versionFilePath, versionFileName);
  			medicalRecordCodingService.editCostInfo(costInfo.getVisitGuid(), costInfo, jsonMap);
  			jsonMap = JsonUtil.jsonToPojo(jsonMapJson, Map.class);
 			model.addAttribute("visitGuid", costInfo.getVisitGuid());
			
 			Map<String, Object> costInfoMap = (Map<String, Object>)jsonMap.get("costInfo");
			String costInfoJson = JsonUtil.objectToJson(costInfoMap);
			model.addAttribute("costInfo", costInfoMap);
			
			model.addAttribute("costInfoJson",costInfoJson);
			RespondResult respondResult = new RespondResult(true,"200","保存成功",null);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RespondResult respondResult = new RespondResult(false,"500","保存失败",null);
			model.addAttribute("respondResultJson", JsonUtil.objectToJson(respondResult));
		}
		return "coding/edit_cost_info";
	}
	
	
	@PostMapping("/ajax_coding_finish")
	@ResponseBody
	public RespondResult ajaxCodingFinish(String visitGuid){
		RespondResult respondResult = null;
		String errorMessage = null;
		try{
			
			errorMessage = medicalRecordCodingService.codingFinish(visitGuid);
			if(errorMessage == null){
				respondResult = new RespondResult(true, RespondResult.successCode, null, null);
			}else{
				respondResult = new RespondResult(true, RespondResult.errorCode, errorMessage, errorMessage);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "编码完成失败");
		}
		
		return respondResult;
	}
	
	@GetMapping("/show_home_page_index")
	public void homePagePrint(String visitGuid, Model model, HttpServletRequest request,HttpServletResponse response) {
		Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
		Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
		ByteArrayOutputStream baos = null;
		try {
			List<String> jsonDatas = medicalRecordCodingService.getMedicalRecordJsonByVisitGuid(visitGuid);
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			Map<String, Object> basicInfo = new HashMap<String, Object>();
			if(!jsonDatas.isEmpty()){
				jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
				basicInfo = (Map<String, Object>)jsonMap.get("basicInfo");
			}
			
			if(basicInfo == null){

				basicInfo = new HashMap<String, Object>();
				MedicalRecord medicalRecord = medicalRecordService.selectMedicalRecordByPrimaryKey(visitGuid);
				
				String patientName = medicalRecord.getPatientName();
				basicInfo.put("patientName",patientName);
				
				String sexCode = medicalRecord.getSexCode();
				basicInfo.put("sexCode",sexCode);
				
				String sexName = medicalRecord.getSexName();
				basicInfo.put("sexName",sexName);
				
				Date birthday = medicalRecord.getBirthday();
				if(birthday != null){
					basicInfo.put("birthday", DateUtil.dateFormat(birthday));
				}
				String documentTypeCode = medicalRecord.getDocumentTypeCode();
				basicInfo.put("documentTypeCode",documentTypeCode);
				
				String documentTypeName = medicalRecord.getDocumentTypeName();
				basicInfo.put("documentTypeName",documentTypeName);
				
				String idNumber = medicalRecord.getIdNumber();
				basicInfo.put("idNumber",idNumber);
				
				
				String inDeptCode = medicalRecord.getInDeptCode();
				basicInfo.put("inDeptCode",inDeptCode);
				
				String inDeptName = medicalRecord.getInDeptName();
				basicInfo.put("inDeptName",inDeptName);
				
				Date inHospitalDateTime = medicalRecord.getInHospitalDateTime();
				if(inHospitalDateTime != null){
					basicInfo.put("inHospitalDateTime", DateUtil.dateFormat(DateUtil.DATE_TIME_FORMATE,inHospitalDateTime));
				}
				
				Date outHospitalDateTime = medicalRecord.getOutHospitalDateTime();
				if(outHospitalDateTime != null){
					basicInfo.put("outHospitalDateTime", DateUtil.dateFormat(DateUtil.DATE_TIME_FORMATE,outHospitalDateTime));
				}
				String outDeptCode = medicalRecord.getOutDeptCode();
				basicInfo.put("outDeptCode",outDeptCode);
				
				String outDeptName = medicalRecord.getOutDeptName();
				basicInfo.put("outDeptName",outDeptName);
				
				String outHospitalTypeCode = medicalRecord.getOutHospitalTypeCode();
				basicInfo.put("outHospitalTypeCode",outHospitalTypeCode);
				
				String outHospitalTypeName = medicalRecord.getOutHospitalTypeName();
				basicInfo.put("outHospitalTypeName",outHospitalTypeName);
				
				String mrId = medicalRecord.getMrId();
				basicInfo.put("mrId",mrId);
				
				String onlyId = medicalRecord.getOnlyId();
				basicInfo.put("onlyId",onlyId);
				
				Integer visitNumber = medicalRecord.getVisitNumber();
				basicInfo.put("visitNumber",visitNumber);
			
				jsonMap.put("basicInfo", basicInfo);
			}
			
			List<String> medicalWorkerCodes = new ArrayList<String>();
			Map<String, Object> cureInfo = (Map<String, Object>) jsonMap.get("cureInfo");
			if(cureInfo != null){
				List<Map<String, Object>> medicalWorkers = (List<Map<String, Object>>)cureInfo.get("cureWorkers");
				if(medicalWorkers != null){
					for (Map<String, Object> medicalWorker : medicalWorkers) {
						String medicalWorkerCode = StringUtil.meaningStr((String)medicalWorker.get("medicalWorkerCode"));
						if(medicalWorkerCode != null){
							medicalWorkerCodes.add(medicalWorkerCode);
						}
						
					}
				}
				
			}
			Map<String, Object> signatureMedicalWorks = new HashMap<String, Object>();
			if(!medicalWorkerCodes.isEmpty()){
				signatureMedicalWorks = baseInfoService.getSignatureMedicalWorks(medicalWorkerCodes);
			}
			baos = PDFUtil.getPDFStreamByTemplate(jsonMap, myConfig.getPageIndexpPdfTemplatePath(),signatureMedicalWorks);
			ByteArrayOutputStream out = WaterMarkUtil.getOutputStreamOfWterMarkByText(baos, (String)currentUser.get("user_code"));
			/*ByteArrayOutputStream out = WaterMarkUtil.getOutputStreamOfWterMarkByIcon(baos, "D:\\publics\\medical_record\\water_icon\\305logo.jpg");*/
			response.setContentLength(out.size());
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline;FileName=out.pdf");
			
			OutputStream outStream = response.getOutputStream();  
	        outStream.write(out.toByteArray(), 0, out.size());  
	        outStream.flush(); 
	        outStream.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@GetMapping("/main_disease_diag_check_page")
	public String mainDiseaseDiagCheck(Model model){
		return "check/main_disease_diag_check_page";
	}
	
	
	@GetMapping("/ajax_main_disease_diag_check")
	@ResponseBody
	public RespondResult ajaxMainDiseaseDiagCheck(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordCodingService.mainDiseaseDiagCheck(params);
			}
			
			respondResult = new RespondResult(true, RespondResult.successCode, null, medicalRecords);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
	
	@GetMapping("/in_hospital_day_number_check_page")
	public String inHospitalDayNumberCheck(Model model){
		return "check/in_hospital_day_number_check_page";
	}
	
	@GetMapping("/in_hospital_day_number_check")
	@ResponseBody
	public RespondResult ajaxInHospitalDayNumberCheck(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordCodingService.inHospitalDayNumberCheck(params);
			}
			
			respondResult = new RespondResult(true, RespondResult.successCode, null, medicalRecords);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
	
	@GetMapping("/repeat_coding_check_page")
	public String repeatCodingCheckCheck(Model model){
		return "check/repeat_coding_check_page";
	}
	
	@GetMapping("/ajax_repeat_coding_check")
	@ResponseBody
	public RespondResult ajaxRepeatCodingCheckCheck(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordCodingService.repeatCodingCheck(params);
			}
			
			respondResult = new RespondResult(true, RespondResult.successCode, null, medicalRecords);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
	
	@GetMapping("/date_time_check_page")
	public String dateTimeCheckCheck(Model model){
		return "check/date_time_check_page";
	}
	
	
	@GetMapping("/ajax_date_time_check")
	@ResponseBody
	public RespondResult ajaxDateTimeCheckCheck(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordCodingService.dateTimeCheck(params);
			}
			
			respondResult = new RespondResult(true, RespondResult.successCode, null, medicalRecords);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
	
	@GetMapping("/medical_record_repair_page")
	public String medicalRecordRepairPage(){
		return "coding/medical_record_repair_page";
	}
	
	
	@GetMapping("/query_repair_medical_record")
	@ResponseBody
	public RespondResult queryRepairMedicalRecord(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			int count = 0;
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordCodingService.getMedicalRecordOfRepair(params);
				count = medicalRecordCodingService.getMedicalRecordCountOfRepair(params);
			}
			
			params.setTotalCounts(count);
			params.setQueryDatas(medicalRecords);
			respondResult = new RespondResult(true, RespondResult.successCode, null, params);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), params);
		}
		
		return respondResult;
	}
	
	@GetMapping("/coding_defect_page")
	public String codingDefectPage(Model model){
		return "coding/coding_defect_page";
	}
	
	
	@GetMapping("/query_coding_defect")
	@ResponseBody
	public RespondResult queryCodingDefect(StatisticsAnalysisQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecordDefects = new ArrayList<Map<String,Object>>();
			medicalRecordDefects = medicalRecordCodingService.getMedicalRecordOfDefect(params);
				
			respondResult = new RespondResult(true, RespondResult.successCode, null, medicalRecordDefects);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), e);
		}
		
		return respondResult;
	}
	
	
	@GetMapping("/query_coding_defect_detail")
	@ResponseBody
	public RespondResult queryCodingDefectDetail(StatisticsAnalysisQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> defectDetails = new ArrayList<Map<String,Object>>();
			defectDetails = medicalRecordCodingService.getMedicalRecordOfDefectDetail(params);
				
			respondResult = new RespondResult(true, RespondResult.successCode, null, defectDetails);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), e);
		}
		
		return respondResult;
	}
}
