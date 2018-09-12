package com.kurumi.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kurumi.pojo.MedicalRecord;
import com.kurumi.pojo.MedicalRecordQualityControlItem;
import com.kurumi.pojo.RespondResult;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.service.BaseInfoService;
import com.kurumi.service.MedicalRecordService;
import com.kurumi.util.DateUtil;
import com.kurumi.util.ExcelUtil;

@Controller
@RequestMapping("/medical_record")
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@Autowired
	private BaseInfoService bseInfoService;
	
	@SuppressWarnings("unused")
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
	@GetMapping("/coding_index")
	public String codingIndex(){
		return "coding_index.default";
	}
	@GetMapping("/windows_index")
	public String windowsIndex(){
		return "windows_index.default";
	}
	
	@GetMapping("/trace_index")
	public String traceIndex(){
		
		
		return "trace_index.default";
	}
	
	@GetMapping("/borrow_index")
	public String borrowIndex(){
		
		
		return "borrow_index.default";
	}
	
	@GetMapping("/work_statistics_index")
	public String workStatisticsIndex(){
		
		
		return "work_statistics_index.default";
	}
	
	@GetMapping("/data_manage_index")
	public String dataManageIndex(){
		return "data_manage_index.default";
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
	
	@GetMapping("/import_medical_record_form")
	public String importMedicalRecordForm(){
		return "pigeonholed/import_medical_record";
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
	
	@GetMapping("/pigeonholedBeyondToExcel")
	public void pigeonholedBeyondToExcel(MedicalRecordQuery params,String beyondDay,HttpServletResponse response) {
		String[] headers = { "姓名", "病案号", "住院号","住院次数", "出院科室", "出院日期","离院方式", "归档日期", "迟送天数" };
		OutputStream out = null;
		List<String> keys = new ArrayList<String>();
		keys.add("patient_name");
		keys.add("mr_id");
		keys.add("only_id");
		keys.add("visit_number");
		keys.add("out_dept_name");
		keys.add("out_hospital_date");
		keys.add("out_hospital_type_name");
		keys.add("trace_date");
		keys.add("beyond_number_day");
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			int count = 0;
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordService.getMedicalRecordOfPigeonholedBeyond(params);
				count= medicalRecordService.getMedicalRecordCount(params);
			}
			response.setContentType("octets/stream");
			String title= "数据导出";
			StringBuilder downLoadFileName = new StringBuilder();
			if(!medicalRecords.isEmpty()){
				float rate = ((float)medicalRecords.size()/count)*100;
				title = new StringBuilder().append(DateUtil.dateFormat(params.getOutHospitalStartDate())).append("到").append(DateUtil.dateFormat(params.getOutHospitalEndDate()))
						.append("的报表").append("---").append(params.getBeyondNumberOfDay()).append("日归档率为：").append(new  DecimalFormat("##0.00").format(rate)).append("%").toString();
				
				downLoadFileName = new StringBuilder(
						"attachment;filename=");
				downLoadFileName.append(title)
						.append(".xls");
			}else{
				downLoadFileName = new StringBuilder(
						"attachment;filename=");
				title = new StringBuilder().append(DateUtil.dateFormat(params.getOutHospitalStartDate())).append("到").append(DateUtil.dateFormat(params.getOutHospitalEndDate()))
						.append("的报表").append("---").append(params.getBeyondNumberOfDay()).append("日无迟送病案").toString();
				downLoadFileName.append(title)
						.append(".xls");
			}
			String encodeStr = new String(downLoadFileName.toString().getBytes(
					"GB2312"), "ISO_8859_1");
			response.addHeader("Content-Disposition", encodeStr);
			out = response.getOutputStream();
			ExcelUtil.exportExcel(out, title, headers, keys, medicalRecords);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.flush();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				try {
					out.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			
		}
		
	}
	
	@PostMapping(value = "/import_medical_record")
	public String importMedicalRecord(@RequestParam("uploadFiles")MultipartFile[] uploadFiles,HttpServletRequest request) {
		try{
			if(uploadFiles!=null && uploadFiles.length>0){
				MultipartFile uploadFile = uploadFiles[0];
				Workbook workbook = WorkbookFactory.create(uploadFile.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);
				List<MedicalRecord> medicalRecords = new ArrayList<MedicalRecord>();
				int rowIndex = 0;
				for (Row row : sheet) {
					rowIndex ++;
					if(rowIndex ==1){
						continue;
					}
					MedicalRecord medicalRecord = new MedicalRecord();
					int colIndex = 0;
					for (Cell cell : row) {
						colIndex ++;
						String value =null;
						/*CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());*/
						switch (cell.getCellType()) {
							case Cell.CELL_TYPE_STRING:// 字符串型
								value = cell.getRichStringCellValue().getString();
								System.out.print(value+",");
								break;
							case Cell.CELL_TYPE_NUMERIC:// 数值型
								if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) { // 如果是date类型则 ，获取该cell的date值
									value = cell.getDateCellValue().toString();
									System.out.print(value+",");
								} else {// 纯数字
									value = ""+cell.getNumericCellValue();
									System.out.print(value+",");
								}
								break;
							case Cell.CELL_TYPE_BOOLEAN:// 布尔
								value = ""+cell.getBooleanCellValue();
								System.out.print(value+",");
								break;
							case Cell.CELL_TYPE_FORMULA:// 公式型
								System.out.print(cell.getCellFormula()+",");
								break;
							case Cell.CELL_TYPE_BLANK:// 空值
								System.out.print(",");
								break;
							case Cell.CELL_TYPE_ERROR: // 故障
								System.out.print(",");
								break;
							default:
								System.out.print(",");
						}
						if(value != null && colIndex ==1){
							medicalRecord.setOnlyId(value);
						}else if(value != null && colIndex ==2){
							medicalRecord.setMrId(value);
						}else if(value != null && colIndex ==3){
							BigDecimal visitNumber = new BigDecimal(value);
							medicalRecord.setVisitNumber(visitNumber.intValue());
						}else if(value != null && colIndex ==4){
							medicalRecord.setPatientName(value);
						}else if(value != null && colIndex ==5){
							medicalRecord.setIdNumber(value);
						}else if(value != null && colIndex ==6){
							medicalRecord.setOutDeptCode(value);
						}else if(value != null && colIndex ==7){
							medicalRecord.setOutDeptName(value);
						}else if(value != null && colIndex ==8){
							Date outHospitalDateTime = DateUtil.dateParse(DateUtil.DATE_TIME_FORMATE, value);
							medicalRecord.setOutHospitalDateTime(outHospitalDateTime);
						}else if(value != null && colIndex ==9){
							medicalRecord.setOutHospitalTypeCode(value);
						}else if(value != null && colIndex ==10){
							medicalRecord.setOutHospitalTypeName(value);
						}
						
						
					}
					System.out.println();
					medicalRecords.add(medicalRecord);
				}
				
				int count = medicalRecordService.importMedicalRecord(medicalRecords);
				return "redirect:/medical_record/import_medical_record_form?resultCode="+count;
			}else{
				return "redirect:/medical_record/import_medical_record_form?resultCode=0";
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "redirect:/medical_record/import_medical_record_form?resultCode=-1";
		}
		
	}
	
	
}
