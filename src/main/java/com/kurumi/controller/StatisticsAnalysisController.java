package com.kurumi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurumi.pojo.RespondResult;
import com.kurumi.query.StatisticsAnalysisQuery;
import com.kurumi.service.StatisticsAnalysisService;

@Controller
@RequestMapping("/statistics_analysis")
public class StatisticsAnalysisController {
	
	@Autowired
	private StatisticsAnalysisService statisticsAnalysisService;

	@GetMapping("/statistics_analysis_index")
	public String statisticsAnalysisIndex(){
		return "statistics_analysis_index.default";
	}
	
	@GetMapping("/medical_pay_type_page")
	public String medicalPayTypePage(){
		return "statistics_analysis/medical_pay_type_page";
	}
	
	
	@RequestMapping("/medical_pay_type_analysis")
	@ResponseBody
	public RespondResult medicalPayTypeAnalysis(StatisticsAnalysisQuery params){
		RespondResult respondResult = null;
		try {
			Map<String, Object> datas=new HashMap<String, Object>();
			int totalCount = statisticsAnalysisService.getMedicalRecordCount(params.getOutHospitalStartDate(), params.getOutHospitalEndDate());
			datas.put("totalCount", totalCount);
			List<Map<String,Object>> medicalPayTypeAnalysis = statisticsAnalysisService.getMedicalPayTypeAnalysis(params.getOutHospitalStartDate(), params.getOutHospitalEndDate());
			List<Map<String, Object>> pageDataList = new ArrayList<Map<String, Object>>(); 
			int sumCount = 0;
			for (Map<String, Object> medicalPayType : medicalPayTypeAnalysis) {
				Map<String, Object> pageDataMap=new HashMap<String, Object>();
				String medicalPayTypeName=(String)medicalPayType.get("medical_pay_type_name");
				if(medicalPayTypeName !=null){
					pageDataMap.put("label",medicalPayTypeName.replaceAll("\"", ""));
				}else{
					pageDataMap.put("label","");
				}
				
				Object countObj = medicalPayType.get("count");
				if(countObj !=null){
					int number = ((Long)countObj).intValue();
					pageDataMap.put("value",number);
					sumCount +=number;
				}else{
					pageDataMap.put("value",0);
				}
                
                pageDataList.add(pageDataMap);
			}
			if(totalCount > sumCount){
				Map<String, Object> pageDataMap=new HashMap<String, Object>();
				pageDataMap.put("label","未知");
				pageDataMap.put("value",totalCount - sumCount);
				pageDataList.add(pageDataMap);
			}
			datas.put("items", pageDataList);
			/*List<Integer> pageTypeIds = printService.getMrPageTypeIdsByPrintTypeId(printerTypeId);*/
			respondResult = new RespondResult(true, RespondResult.successCode, "查询成功", datas);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(),e.getMessage());
		}
		
		return respondResult;
	}
	
	@GetMapping("/in_hospital_type_page")
	public String inHospitalTypePage(){
		return "statistics_analysis/in_hospital_type_page";
	}
	
	
	@RequestMapping("/in_hospital_type_analysis")
	@ResponseBody
	public RespondResult inHospitalTypeAnalysis(StatisticsAnalysisQuery params){
		RespondResult respondResult = null;
		try {
			Map<String, Object> datas=new HashMap<String, Object>();
			int totalCount = statisticsAnalysisService.getMedicalRecordCount(params.getOutHospitalStartDate(), params.getOutHospitalEndDate());
			datas.put("totalCount", totalCount);
			List<Map<String,Object>> inHospitalTypeAnalysis = statisticsAnalysisService.getInHospitalTypeAnalysis(params.getOutHospitalStartDate(), params.getOutHospitalEndDate());
			List<Map<String, Object>> pageDataList = new ArrayList<Map<String, Object>>(); 
			int sumCount = 0;
			for (Map<String, Object> inHospitalType : inHospitalTypeAnalysis) {
				Map<String, Object> pageDataMap=new HashMap<String, Object>();
				String inHospitalTypeName=(String)inHospitalType.get("in_hospital_type_name");
				if(inHospitalTypeName !=null){
					pageDataMap.put("label",inHospitalTypeName.replaceAll("\"", ""));
				}else{
					pageDataMap.put("label","");
				}
				
				Object countObj = inHospitalType.get("count");
				if(countObj !=null){
					int number = ((Long)countObj).intValue();
					pageDataMap.put("value",number);
					sumCount +=number;
				}else{
					pageDataMap.put("value",0);
				}
                
                pageDataList.add(pageDataMap);
			}
			if(totalCount > sumCount){
				Map<String, Object> pageDataMap=new HashMap<String, Object>();
				pageDataMap.put("label","未知");
				pageDataMap.put("value",totalCount - sumCount);
				pageDataList.add(pageDataMap);
			}
			datas.put("items", pageDataList);
			/*List<Integer> pageTypeIds = printService.getMrPageTypeIdsByPrintTypeId(printerTypeId);*/
			respondResult = new RespondResult(true, RespondResult.successCode, "查询成功", datas);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(),e.getMessage());
		}
		
		return respondResult;
	}
}
