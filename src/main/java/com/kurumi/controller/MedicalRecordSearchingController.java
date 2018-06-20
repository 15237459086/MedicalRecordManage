package com.kurumi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurumi.pojo.RespondResult;
import com.kurumi.query.MedicalRecordSearchingQuery;
import com.kurumi.service.MedicalRecordCodingService;

@Controller
@RequestMapping("/medical_record_searching")
public class MedicalRecordSearchingController {

	@Autowired
	private MedicalRecordCodingService medicalRecordCodingService;
	
	@GetMapping("/medical_record_searching_index")
	public String medicalRecordSearchingIndex(){
		return "medical_record_searching_index.default";
	}
	
	@GetMapping("/disease_diag_searching_page")
	public String diseaseDiagSearchingPage(){
		return "searching/disease_diag_searching_page";
	}
	
	@GetMapping("/disease_diag_searching")
	@ResponseBody
	public RespondResult diseaseDiagSearching(MedicalRecordSearchingQuery params){
		RespondResult respondResult = null;
		
		try{
			int count = 0;
			if(params.getDiseaseDiagName() != null){
				params.setDiseaseDiagName("%"+params.getDiseaseDiagName()+"%");
			}
			List<Map<String, Object>> diseaseMedicalRecords = medicalRecordCodingService.getDiseaseMedicalRecord(params);
			count = medicalRecordCodingService.getDiseaseMedicalRecordCount(params);
			params.setQueryDatas(diseaseMedicalRecords);
			params.setTotalCounts(count);
			respondResult = new RespondResult(true, RespondResult.successCode, null, params);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), null);
		}
		
		return respondResult;
	}
}
