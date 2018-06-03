package com.kurumi.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurumi.pojo.RespondResult;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.service.MedicalRecordDataManageService;
import com.kurumi.util.DateUtil;
import com.kurumi.util.ExcelUtil;

@Controller
@RequestMapping("/data_manage")
public class MedicalRecordDataManageController {

	@Autowired
	private MedicalRecordDataManageService medicalRecordDataManageService;
	
	@GetMapping("/page_index_export")
	public String pageIndexExport(Model model){
		return "data_manage/page_index_export";
	}
	
	
	@GetMapping("/ajax_query_page_index")
	@ResponseBody
	public RespondResult ajaxQueryHomePage(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordDataManageService.getMedicalRecordOfExport(params);
			}
			respondResult = new RespondResult(true, RespondResult.successCode, null, medicalRecords);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
	
	
	@GetMapping("/page_index_to_excel")
	public void pageIndexToExcel(MedicalRecordQuery params,String beyondDay,HttpServletResponse response) {
		OutputStream out = null;
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordDataManageService.getMedicalRecordOfExcel(params);
			}
			response.setContentType("octets/stream");
			String title= "首页导出";
			StringBuilder downLoadFileName = new StringBuilder();
			downLoadFileName = new StringBuilder(
					"attachment;filename=");
			downLoadFileName.append(title).append(DateUtil.dateFormat(new Date()))
					.append(".xls");
			String encodeStr = new String(downLoadFileName.toString().getBytes(
					"GB2312"), "ISO_8859_1");
			response.addHeader("Content-Disposition", encodeStr);
			out = response.getOutputStream();
			ExcelUtil.exportExcelOfPageIndex(out,medicalRecords);
			
			
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
}
