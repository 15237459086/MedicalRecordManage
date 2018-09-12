package com.kurumi.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurumi.config.MyConfig;
import com.kurumi.pojo.MedicalRecord;
import com.kurumi.pojo.MedicalRecordOutPatient;
import com.kurumi.pojo.MedicalRecordRadioTherapy;
import com.kurumi.pojo.RespondResult;
import com.kurumi.service.InterfaceMedicalRecordService;
import com.kurumi.service.MedicalRecordService;
import com.kurumi.util.JsonUtil;
import com.kurumi.util.StringUtil;

@Controller
@RequestMapping("/interface_medical_record")
public class InterfaceMedicalRecordController {
	
	@Autowired
	private MyConfig myConfig;
	
	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@Autowired
	private InterfaceMedicalRecordService interfaceMedicalRecordService;
	
	@PostMapping("/ajax_add_pagination_info")
	@ResponseBody
	public RespondResult ajaxAddPaginationInfo(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		RespondResult respondResult = null;
		
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));  
			StringBuffer sb = new StringBuffer("");  
			String temp;  
			while ((temp = br.readLine()) != null) {  
				sb.append(temp);  
			}  
			br.close();
			String postDataJson = sb.toString();
			Map<String, Object> postData = JsonUtil.jsonToPojo(postDataJson, Map.class);
			Map<String, Object> medicalRecordJson= (Map<String, Object>)postData.get("medicalRecord");
			List<Map<String, Object>> scanImagesJson = (List<Map<String, Object>>)postData.get("scanImages");
			MedicalRecord medicalRecord = MedicalRecord.buildMedicalRecord(medicalRecordJson);
			int count = medicalRecordService.checkMeditalRecordUniq(medicalRecord.getOnlyId(), medicalRecord.getMrId(), medicalRecord.getVisitNumber());
			if(count > 0){
				List<String> visitGuids = medicalRecordService.getVisitGuidByMrIdAndVisitNumber(medicalRecord.getMrId(), medicalRecord.getVisitNumber());
				String visitGuid = visitGuids.get(0);
				String printerPath =myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(visitGuid)+ visitGuid+"\\"+"publish\\"+medicalRecord.getMrId()+"_"+medicalRecord.getVisitNumber()+"_"+myConfig.getDefaultPrintTypeCode()+".pdf";
				if(!new File(printerPath).exists()){
					return new RespondResult(true, RespondResult.lackCode, "病案号："+ medicalRecord.getMrId()+",住院次数:"+medicalRecord.getVisitNumber()+",数据存在，资源缺失", printerPath);
				}else{
					return new RespondResult(true, RespondResult.repeatCode, "资源已存在", printerPath);
				}
				
			}
			count = interfaceMedicalRecordService.importPaginationInfo(medicalRecord,scanImagesJson);
			if(count == 1){
				Thread.sleep(3000);
				String printerPath =myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(medicalRecord.getVisitGuid())+ medicalRecord.getVisitGuid()+"\\"+"publish\\"+medicalRecord.getMrId()+"_"+medicalRecord.getVisitNumber()+"_"+myConfig.getDefaultPrintTypeCode()+".pdf";
				if(!new File(printerPath).exists()){
					return new RespondResult(true, RespondResult.lackCode, "病案号："+ medicalRecord.getMrId()+",住院次数:"+medicalRecord.getVisitNumber()+",数据存在，资源缺失", printerPath);
				}else{
					respondResult = new RespondResult(true, RespondResult.successCode, null, printerPath);
				}
				
			}else if(count == -2){
				respondResult = new RespondResult(true, RespondResult.lackCode, "资源缺失", null);
			}else{
				respondResult = new RespondResult(true, RespondResult.errorCode, "错误", null);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
	
	
	@PostMapping("/ajax_add_pagination_infos")
	@ResponseBody
	public RespondResult ajaxAddPaginationInfos(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		RespondResult respondResult = null;
		
		try{
			List<String> printerPaths = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));  
			StringBuffer sb = new StringBuffer("");  
			String temp;  
			while ((temp = br.readLine()) != null) {  
				sb.append(temp);  
			}  
			br.close();
			String postDataJson = sb.toString();
			List<Map<String, Object>> postDatas = JsonUtil.jsonToPojo(postDataJson, List.class);
			for (Map<String, Object> postData : postDatas) {
				Map<String, Object> medicalRecordJson= (Map<String, Object>)postData.get("medicalRecord");
				List<Map<String, Object>> scanImagesJson = (List<Map<String, Object>>)postData.get("scanImages");
				MedicalRecord medicalRecord = MedicalRecord.buildMedicalRecord(medicalRecordJson);
				int count = medicalRecordService.checkMeditalRecordUniq(medicalRecord.getOnlyId(), medicalRecord.getMrId(), medicalRecord.getVisitNumber());
				if(count > 0){
					List<String> visitGuids = medicalRecordService.getVisitGuidByMrIdAndVisitNumber(medicalRecord.getMrId(), medicalRecord.getVisitNumber());
					String visitGuid = visitGuids.get(0);
					String printerPath =myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(visitGuid)+ visitGuid+"\\"+"publish\\"+medicalRecord.getMrId()+"_"+medicalRecord.getVisitNumber()+"_"+myConfig.getDefaultPrintTypeCode()+".pdf";
					if(!new File(printerPath).exists()){
						return new RespondResult(true, RespondResult.lackCode, "病案号："+ medicalRecord.getMrId()+",住院次数:"+medicalRecord.getVisitNumber()+",数据存在，资源缺失", printerPath);
					}else{
						printerPaths.add(printerPath);
						continue;
					}
					
				}
				count = interfaceMedicalRecordService.importPaginationInfo(medicalRecord,scanImagesJson);
				if(count == 1){
					String printerPath =myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(medicalRecord.getVisitGuid())+ medicalRecord.getVisitGuid()+"\\"+"publish\\"+medicalRecord.getMrId()+"_"+medicalRecord.getVisitNumber()+"_"+myConfig.getDefaultPrintTypeCode()+".pdf";
					if(!new File(printerPath).exists()){
						return new RespondResult(true, RespondResult.lackCode, "病案号："+ medicalRecord.getMrId()+",住院次数:"+medicalRecord.getVisitNumber()+",数据存在，资源缺失", printerPath);
					}else{
						printerPaths.add(printerPath);
						continue;
					}
					
				}else if(count == -2){
					return new RespondResult(true, RespondResult.lackCode, "资源缺失", null);
				}else{
					return new RespondResult(true, RespondResult.errorCode, "错误", null);
				}
			}
			
			respondResult = new RespondResult(true, RespondResult.successCode, null, printerPaths);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
	
	
	@PostMapping("/ajax_add_out_patient_pagination_info")
	@ResponseBody
	public RespondResult ajaxAddOutPatientPaginationInfo(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		RespondResult respondResult = null;
		
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));  
			StringBuffer sb = new StringBuffer("");  
			String temp;  
			while ((temp = br.readLine()) != null) {  
				sb.append(temp);  
			}  
			br.close();
			String postDataJson = sb.toString();
			Map<String, Object> postData = JsonUtil.jsonToPojo(postDataJson, Map.class);
			Map<String, Object> medicalRecordJson= (Map<String, Object>)postData.get("medicalRecord");
			List<Map<String, Object>> scanImagesJson = (List<Map<String, Object>>)postData.get("scanImages");
			MedicalRecordOutPatient medicalRecord = MedicalRecordOutPatient.buildMedicalRecord(medicalRecordJson);
			int count = interfaceMedicalRecordService.checkMeditalRecordUniqOfOutPatient(medicalRecord.getMrId());
			if(count > 0){
				List<String> visitGuids = interfaceMedicalRecordService.getVisitGuidByOutPatientMrId(medicalRecord.getMrId());
				String visitGuid = visitGuids.get(0);
				String printerPath =myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(visitGuid)+ visitGuid+"\\"+"publish\\"+medicalRecord.getMrId()+"_"+myConfig.getDefaultPrintTypeCode()+".pdf";
				if(!new File(printerPath).exists()){
					return new RespondResult(true, RespondResult.lackCode, "病案号："+ medicalRecord.getMrId()+",数据存在，资源缺失1", printerPath);
				}else{
					return new RespondResult(true, RespondResult.repeatCode, "资源已存在", printerPath);
				}
				
			}
			count = interfaceMedicalRecordService.importPaginationInfo(medicalRecord,scanImagesJson);
			if(count == 1){
				String printerPath =myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(medicalRecord.getVisitGuid())+ medicalRecord.getVisitGuid()+"\\"+"publish\\"+medicalRecord.getMrId()+"_"+myConfig.getDefaultPrintTypeCode()+".pdf";
				if(!new File(printerPath).exists()){
					return new RespondResult(true, RespondResult.lackCode, "病案号："+ medicalRecord.getMrId()+",数据存在，资源缺失2", printerPath);
				}else{
					respondResult = new RespondResult(true, RespondResult.successCode, null, printerPath);
				}
				
			}else if(count == -2){
				respondResult = new RespondResult(true, RespondResult.lackCode, "资源缺失", null);
			}else{
				respondResult = new RespondResult(true, RespondResult.errorCode, "错误", null);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
	
	
	@PostMapping("/ajax_add_radio_therapy_pagination_info")
	@ResponseBody
	public RespondResult ajaxAddRadioTherapyPaginationInfo(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		RespondResult respondResult = null;
		
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));  
			StringBuffer sb = new StringBuffer("");  
			String temp;  
			while ((temp = br.readLine()) != null) {  
				sb.append(temp);  
			}  
			br.close();
			String postDataJson = sb.toString();
			Map<String, Object> postData = JsonUtil.jsonToPojo(postDataJson, Map.class);
			Map<String, Object> medicalRecordJson= (Map<String, Object>)postData.get("medicalRecord");
			List<Map<String, Object>> scanImagesJson = (List<Map<String, Object>>)postData.get("scanImages");
			MedicalRecordRadioTherapy medicalRecord = MedicalRecordRadioTherapy.buildMedicalRecord(medicalRecordJson);
			int count = interfaceMedicalRecordService.checkMeditalRecordUniqOfRadioTherapy(medicalRecord.getMrId());
			if(count > 0){
				List<String> visitGuids = interfaceMedicalRecordService.getVisitGuidByRadioTherapyMrId(medicalRecord.getMrId());
				String visitGuid = visitGuids.get(0);
				String printerPath =myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(visitGuid)+ visitGuid+"\\"+"publish\\"+medicalRecord.getMrId()+"_"+myConfig.getDefaultPrintTypeCode()+".pdf";
				if(!new File(printerPath).exists()){
					return new RespondResult(true, RespondResult.lackCode, "病案号："+ medicalRecord.getMrId()+",数据存在，资源缺失1", printerPath);
				}else{
					return new RespondResult(true, RespondResult.repeatCode, "资源已存在", printerPath);
				}
				
			}
			count = interfaceMedicalRecordService.importPaginationInfo(medicalRecord,scanImagesJson);
			if(count == 1){
				String printerPath =myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(medicalRecord.getVisitGuid())+ medicalRecord.getVisitGuid()+"\\"+"publish\\"+medicalRecord.getMrId()+"_"+myConfig.getDefaultPrintTypeCode()+".pdf";
				if(!new File(printerPath).exists()){
					return new RespondResult(true, RespondResult.lackCode, "病案号："+ medicalRecord.getMrId()+",数据存在，资源缺失2", printerPath);
				}else{
					respondResult = new RespondResult(true, RespondResult.successCode, null, printerPath);
				}
				
			}else if(count == -2){
				respondResult = new RespondResult(true, RespondResult.lackCode, "资源缺失", null);
			}else{
				respondResult = new RespondResult(true, RespondResult.errorCode, "错误", null);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
}
