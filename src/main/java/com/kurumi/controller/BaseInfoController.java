package com.kurumi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurumi.pojo.RespondResult;
import com.kurumi.service.BaseInfoService;
import com.kurumi.util.JsonUtil;

@Controller
@RequestMapping("/base_info")
public class BaseInfoController {

	@Autowired
	private BaseInfoService baseInfoService;
	
	@GetMapping("/ajax_quality_control_point_by_up_level_code")
	@ResponseBody
	public RespondResult ajaxQualityControlPointByUpLevelCode(String upOneLevelCode){
		RespondResult respondResult = null;
		
		try{
			List<Map<String, Object>> qualityControlPoints = baseInfoService.getQualityControlByUpOneLevelCode(upOneLevelCode);
			respondResult = new RespondResult(true, RespondResult.successCode, null, qualityControlPoints);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), null);
		}
		
		return respondResult;
	}
	
	@GetMapping("/ajax_base_datas")
	@ResponseBody
	public String ajaxHomePageData(){
		
		Map<String, List<Map<String,Object>>> datas = baseInfoService.getBaseInfoOfHomePageBasic();
		String jsonStr = JsonUtil.objectToJson(datas);
		return jsonStr;

	}
	
	@GetMapping("/ajax_diseases_by_query_name")
	@ResponseBody
	public String ajaxDiseaseByQueryName(String queryName){
		
		List<Map<String, Object>> diseases = baseInfoService.getDiseaseByQueryName(queryName);
		String jsonStr = JsonUtil.objectToJson(diseases);
		return jsonStr;

	}
	
	
	@GetMapping("/ajax_operate_by_query_name")
	@ResponseBody
	public String ajaxOperateByQueryName(String queryName){
		List<Map<String, Object>> operates = baseInfoService.getOperateByQueryName(queryName);
		String jsonStr = JsonUtil.objectToJson(operates);
		return jsonStr;
	}
	
	@GetMapping("/ajax_medical_worker_by_query_name")
	@ResponseBody
	public String ajaxMedicalWorkByQueryName(String queryName){
		List<Map<String, Object>> medicalWorks = baseInfoService.getMedicalWorkerByQueryName(queryName);
		String jsonStr = JsonUtil.objectToJson(medicalWorks);
		return jsonStr;
	}
	
	
	@GetMapping("/ajax_medical_dept")
	@ResponseBody
	public String ajaxMedicalDept(){
		
		List<Map<String,Object>> medicalDepts = baseInfoService.getMedicalDepts();
		String jsonStr = JsonUtil.objectToJson(medicalDepts);
		return jsonStr;

	}
	
	

	@GetMapping("/ajax_un_pigeonhole_base_data")
	@ResponseBody
	public String ajaxUnPigeonholeBaseData(){
		
		Map<String, List<Map<String,Object>>> baseInfo = baseInfoService.getBaseInfoOfUnPigeonhole();
		String jsonStr = JsonUtil.objectToJson(baseInfo);
		return jsonStr;

	}
	
	
	@GetMapping("/ajax_print_base_data")
	@ResponseBody
	public String ajaxPrintBaseData(){
		
		Map<String, List<Map<String,Object>>> baseInfo = baseInfoService.getBaseInfoOfPrint();
		String jsonStr = JsonUtil.objectToJson(baseInfo);
		return jsonStr;

	}
	
	@GetMapping("/ajax_borrow_base_data")
	@ResponseBody
	public String ajaxBorrowBaseData(){
		
		Map<String, List<Map<String,Object>>> baseInfo = baseInfoService.getBaseInfoOfBorrow();
		String jsonStr = JsonUtil.objectToJson(baseInfo);
		return jsonStr;

	}
}
