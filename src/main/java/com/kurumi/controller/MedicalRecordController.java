package com.kurumi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurumi.pojo.MedicalRecord;
import com.kurumi.pojo.MedicalRecordQualityControlItem;
import com.kurumi.pojo.RespondResult;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.service.BaseInfoService;
import com.kurumi.service.MedicalRecordService;

@Controller
@RequestMapping("/medical_record")
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@Autowired
	private BaseInfoService bseInfoService;
	
	private Logger log = LoggerFactory.getLogger(MedicalRecordController.class);
	
	@GetMapping("/pigeonholed_index")
	public String pigeonholedIndex(){
		
		
		return "pigeonholed_index.default";
	}
	
	@GetMapping("/quality_control_index")
	public String qualityControlIndex(){
		
		
		return "quality_control_index.default";
	}
	
	@GetMapping("/scan_index")
	public String scanIndex(){
		
		
		return "scan_index.default";
	}
	
	@GetMapping("/trace_index")
	public String traceIndex(){
		
		
		return "trace_index.default";
	}
	
	@GetMapping("/un_pigeonhole")
	public String unPigeonhole(){
		return "pigeonholed/un_pigeonhole";
	}
	
	@GetMapping("/pigeonholed")
	public String pigeonholed(){
		return "pigeonholed/pigeonholed";
	}
	
	@GetMapping("/pigeonholed_beyond")
	public String pigeonholedBeyond(){
		return "pigeonholed/pigeonholed_beyond";
	}
	
	@GetMapping("/pigeonhole_rate")
	public String pigeonholeRate(){
		return "pigeonholed/pigeonhole_rate";
	}
	
	
	@GetMapping("/query_trace")
	public String queryTrace(){
		return "trace/query_trace";
	}
	
	@GetMapping("/ajax_query_un_pigeonhole")
	@ResponseBody
	public RespondResult ajaxQueryUnPigeonhole(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			int count = 0;
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordService.getMedicalRecordOfUnPigeonhole(params);
				count= medicalRecordService.getMedicalRecordCountOfUnPigeonhole(params);
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
	
	
	@PostMapping("/ajax_add_medical_record")
	@ResponseBody
	public RespondResult ajaxAddMedicalRecord(MedicalRecord medicalRecord){
		RespondResult respondResult = null;
		
		try{
			int count = medicalRecordService.checkMeditalRecordUniq(medicalRecord.getOnlyId(), medicalRecord.getMrId(), medicalRecord.getVisitNumber());
			if(count > 0){
				return new RespondResult(true, RespondResult.repeatCode, null, count);
			}
			medicalRecord.setVersion(1);
			medicalRecord.setDocumentTypeCode("SFZ");
			medicalRecord.setDocumentTypeName("身份证");
			medicalRecord.setTreatmentSignCode("PT");
			medicalRecord.setTreatmentSignName("普通");
			count = medicalRecordService.insertMeditalRecord(medicalRecord);
			respondResult = new RespondResult(true, RespondResult.successCode, null, count);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
	
	
	@PostMapping("/ajax_pigeonhole_medical_record")
	@ResponseBody
	public RespondResult ajaxPigeonholeMedicalRecord(String visitGuid, String pigeonholeDateTime,String treatmentSignId){
		RespondResult respondResult = null;
		
		try{
			int count = medicalRecordService.pigeonholeMedicalRecord(visitGuid, pigeonholeDateTime, treatmentSignId);
			if(count == 1){
				respondResult = new RespondResult(true, RespondResult.successCode, null, "");
			}else{
				respondResult = new RespondResult(true, RespondResult.errorCode, null, "");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
	
	@GetMapping("/ajax_check_medical_record_uniq")
	@ResponseBody
	public RespondResult ajaxCheckMedicalRecordUniq(MedicalRecord medicalRecord){
		RespondResult respondResult = null;
		
		try{
			int count = medicalRecordService.checkMeditalRecordUniq(medicalRecord.getOnlyId(), medicalRecord.getMrId(), medicalRecord.getVisitNumber());
			respondResult = new RespondResult(true, RespondResult.successCode, null, count);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
	
	
	@GetMapping("/ajax_query_pigeonholed")
	@ResponseBody
	public RespondResult ajaxQueryPigeonholed(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			int count = 0;
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordService.getMedicalRecordOfPigeonholed(params);
				count= medicalRecordService.getMedicalRecordCountOfPigeonholed(params);
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
	
	@GetMapping("/ajax_query_pigeonholed_beyond")
	@ResponseBody
	public RespondResult ajaxQueryPigeonholedBeyond(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			int count = 0;
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordService.getMedicalRecordOfPigeonholedBeyond(params);
				count= medicalRecordService.getMedicalRecordCount(params);
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
	
	
	@GetMapping("/ajax_query_pigeonholed_rate")
	@ResponseBody
	public RespondResult ajaxQueryPigeonholedRate(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecordRates = new ArrayList<Map<String,Object>>();
			
			if(!params.queryUnEncodingEmpty()){
				medicalRecordRates = medicalRecordService.getMedicalRecordPigeonholedRate(params);
			}
			
			params.setTotalCounts(medicalRecordRates.size());
			params.setQueryDatas(medicalRecordRates);
			respondResult = new RespondResult(true, RespondResult.successCode, null, params);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), params);
		}
		
		return respondResult;
	}
	
	@GetMapping("/query_quality_control")
	public String queryQualityControl(){
		return "quality_control/query_quality_control";
	}
	
	@GetMapping("/quality_control_score")
	public String qualityControlScore(String visitGuid,Model model){
		model.addAttribute("visitGuid",visitGuid);
		return "quality_control/quality_control_score";
	}
	
	@GetMapping("/quality_control_score_rate")
	public String qualityControlScoreRate(Model model){
		return "quality_control/quality_control_score_rate";
	}
	
	@GetMapping("/ajax_query_quality_control")
	@ResponseBody
	public RespondResult ajaxQueryQualityControl(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			int count = 0;
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordService.getMedicalRecordOfQualityControl(params);
				count = medicalRecordService.getMedicalRecordCountOfQualityControl(params);
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
	
	
	

	@GetMapping("/ajax_medical_record_quality_control")
	@ResponseBody
	public RespondResult ajaxMedicalRecordQualityControl(String visitGuid){
		RespondResult respondResult = null;
		
		try{
			Map<String,Object> data = new HashMap<String, Object>();
			List<Map<String, Object>> medicalRecords = medicalRecordService.getMedicalRecordByVisitGuid(visitGuid);
			data.put("medicalRecords", medicalRecords);
			List<Map<String, Object>> qualityControlPoints = bseInfoService.getQualityControlOfFirstLevel();
			data.put("qualityControlPoints", qualityControlPoints);
			List<Map<String, Object>> qualityControlItems = medicalRecordService.getQualityControlItemByVisitGuid(visitGuid);
			data.put("qualityControlItems", qualityControlItems);
			respondResult = new RespondResult(true, RespondResult.successCode, null, data);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), null);
		}
		
		return respondResult;
	}
	
	
	@PostMapping("/ajax_add_quality_control_item")
	@ResponseBody
	public RespondResult ajaxAddQualityControlItem(String visitGuid,MedicalRecordQualityControlItem qualityControlItem){
		RespondResult respondResult = null;
		
		try{
			int count = medicalRecordService.addQualityControlItem(visitGuid, qualityControlItem);
			if(count > 0){
				respondResult = new RespondResult(true, RespondResult.successCode, "", qualityControlItem);
			}else{
				respondResult = new RespondResult(false, RespondResult.errorCode, "", qualityControlItem);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
	
	
	@PostMapping("/ajax_finish_quality_control")
	@ResponseBody
	public RespondResult ajaxFinshiQualityControl(String visitGuid){
		RespondResult respondResult = null;
		
		try{
			int count = medicalRecordService.finishQualityControl(visitGuid);
			if(count == 1){
				respondResult = new RespondResult(true, RespondResult.successCode, "", null);
			}else if(count == 2){
				respondResult = new RespondResult(true, RespondResult.repeatCode, "", null);
			}
			else{
				respondResult = new RespondResult(false, RespondResult.errorCode, "", null);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}

	@GetMapping("/ajax_medical_record_quality_control_item")
	@ResponseBody
	public RespondResult ajaxMedicalRecordQualityControlItem(Integer qualityControlItemId){
		RespondResult respondResult = null;
		
		try{
			Map<String,Object> data = new HashMap<String, Object>();
			MedicalRecordQualityControlItem qualityControlItem = medicalRecordService.selectQualityControlItemByPrimaryKey(qualityControlItemId);
			
			data.put("qualityControlItem", qualityControlItem);
			
			List<Map<String, Object>> secondQualityControlPoints = bseInfoService.getQualityControlByUpOneLevelCode(qualityControlItem.getFirstLevelCode());
			List<Map<String, Object>> thirdQualityControlPoints = bseInfoService.getQualityControlByUpOneLevelCode(qualityControlItem.getSecondLevelCode());
			data.put("secondQualityControlPoints", secondQualityControlPoints);
			data.put("thirdQualityControlPoints", thirdQualityControlPoints);
			respondResult = new RespondResult(true, RespondResult.successCode, null, data);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), null);
		}
		
		return respondResult;
	}
	
	
	@PostMapping("/ajax_update_quality_control_item")
	@ResponseBody
	public RespondResult ajaxUpdateQualityControlItem(String visitGuid,MedicalRecordQualityControlItem qualityControlItem){
		RespondResult respondResult = null;
		
		try{
			MedicalRecordQualityControlItem qualityControlItemOfDataBase = medicalRecordService.updateQualityControlItem(visitGuid, qualityControlItem);
			if(qualityControlItemOfDataBase != null){
				respondResult = new RespondResult(true, RespondResult.successCode, "", qualityControlItemOfDataBase);
			}else{
				respondResult = new RespondResult(false, RespondResult.errorCode, "", qualityControlItemOfDataBase);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
	
	
	@PostMapping("/ajax_delete_quality_control_item")
	@ResponseBody
	public RespondResult ajaxDeleteQualityControlItem(String visitGuid,Integer qualityControlItemId){
		RespondResult respondResult = null;
		
		try{
			int count = medicalRecordService.deleteQualityControlItem(visitGuid, qualityControlItemId);
			
			if(count > 0){
				respondResult = new RespondResult(true, RespondResult.successCode, "", "");
			}else{
				respondResult = new RespondResult(false, RespondResult.errorCode, "", "");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
	
	@GetMapping("/ajax_quality_control_score_rate")
	@ResponseBody
	public RespondResult ajaxQualityControlScoreRate(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> qualityControlScoreRates = new ArrayList<Map<String,Object>>();
			if(!params.queryUnPigeonholeEmpty()){
				qualityControlScoreRates = medicalRecordService.getQualityControlScoreRate(params);
			}
			respondResult = new RespondResult(true, RespondResult.successCode, null, qualityControlScoreRates);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), params);
		}
		
		return respondResult;
	}
	
	
	@GetMapping("/ajax_query_trace")
	@ResponseBody
	public RespondResult ajaxQueryTrace(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			int count = 0;
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordService.getMedicalRecord(params);
				count= medicalRecordService.getMedicalRecordCount(params);
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
	
	@GetMapping("/ajax_trace_detail")
	@ResponseBody
	public RespondResult ajaxTraceDetail(String visitGuid){
		RespondResult respondResult = null;
		Map<String,Object> data = new HashMap<String, Object>();
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			
			medicalRecords = medicalRecordService.getMedicalRecordByVisitGuid(visitGuid);
			data.put("medicalRecords", medicalRecords);
			List<Map<String,Object>> medicalRecordTraces = new ArrayList<Map<String,Object>>();
			medicalRecordTraces = medicalRecordService.getMeditalRecordTraceByVisitGuid(visitGuid);
			data.put("medicalRecordTraces", medicalRecordTraces);
			respondResult = new RespondResult(true, RespondResult.successCode, null, data);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), null);
		}
		
		return respondResult;
	}
}
