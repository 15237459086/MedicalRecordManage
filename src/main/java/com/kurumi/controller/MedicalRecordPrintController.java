package com.kurumi.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurumi.config.MyConfig;
import com.kurumi.pojo.MedicalRecordPrinterApply;
import com.kurumi.pojo.MedicalRecordPrinterApplyItem;
import com.kurumi.pojo.RespondResult;
import com.kurumi.pojo.pdf.MedicalRecordResource;
import com.kurumi.query.MedicalRecordPrinterQuery;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.service.MedicalRecordPrinterService;
import com.kurumi.service.MedicalRecordScanService;
import com.kurumi.thread.MedicalRecordPDFThread;
import com.kurumi.util.StringUtil;
import com.kurumi.util.WaterMarkUtil;

@Controller
@RequestMapping("/medical_record_print")
public class MedicalRecordPrintController {

	@Autowired
	private MyConfig myConfig;
	
	
	@Autowired
	private MedicalRecordPrinterService medicalRecordPrinterService;
	
	@Autowired
	private MedicalRecordScanService medicalRecordScanService;
	
	@GetMapping("/print_apply")
	public String printApply(Model model){
		return "print/print_apply";
	}
	
	@GetMapping("/print_record")
	public String printRecord(Model model){
		return "print/print_record";
	}
	
	@GetMapping("/ajax_query_print")
	@ResponseBody
	public RespondResult ajaxQueryPrint(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			int count = 0;
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordPrinterService.getMedicalRecordOfPrinter(params);
				count= medicalRecordPrinterService.getMedicalRecordCountOfPrinter(params);
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
	
	
	@ResponseBody
	@PostMapping("/ajax_add_print_apply")
	public RespondResult ajaxAddPrintApply(MedicalRecordPrinterApply printerApply,MedicalRecordPrinterApplyItem printerApplyItem, Model model){
		try{
			int result = medicalRecordPrinterService.addMedicalRecordPrinterApply(printerApply, printerApplyItem);
			if(result > 0){
				String visitGuid = printerApplyItem.getVisitGuid();
				List<Map<String,Object>> sourceFiles = medicalRecordScanService.getImageFilesByVisitGuidAndPrinterTypeCode(visitGuid, printerApplyItem.getApplyPrinterTypeCode());
				MedicalRecordResource medicalRecordResource = new MedicalRecordResource();
				medicalRecordResource.getSourceFiles().addAll(sourceFiles);
				medicalRecordResource.getSourceBasicPaths().put("imageBasicPath", myConfig.getImageRecourcePath());
				String newPDFPath = myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(visitGuid)+ visitGuid+"\\"+"publisih\\"+printerApplyItem.getApplyPrinterTypeCode()+".pdf";
				medicalRecordResource.setNewPDFPath(newPDFPath);
				MedicalRecordPDFThread medicalRecordPDFThread = new MedicalRecordPDFThread(medicalRecordResource);
				medicalRecordPDFThread.start();
				return new RespondResult(true, RespondResult.successCode, "打印申请成功", printerApplyItem);
			}else{
				return new RespondResult(true, RespondResult.errorCode, "打印申请失败", "打印申请失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new RespondResult(false, RespondResult.errorCode, e.getMessage(), "打印申请失败");
		}
	}
	
	@GetMapping("/print_operate")
	public String printOperate(Integer printerApplyItemId, Model model){
		List<Map<String,Object>> printerApplyItems = medicalRecordPrinterService.getPrinterApplyItemById(printerApplyItemId);
		model.addAttribute("printerApplyItems", printerApplyItems);
		return "print/print_operate";
	}
	
	
	@RequestMapping("/show_pdf_to_publish")
	public void showPdfToPublish(String visitGuid,String applyPrinterTypeCode,HttpServletResponse response) {
		
		ByteArrayOutputStream baos = null;
		try {
			String pdfPath = myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(visitGuid)+ visitGuid+"\\"+"publisih\\"+applyPrinterTypeCode+".pdf";
			
			baos = WaterMarkUtil.getOutputStreamOfWaterMarkByText(pdfPath, "仅可用于打印");
			response.setContentLength(baos.size());
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline;FileName=printer.pdf");
			
			OutputStream outStream = response.getOutputStream();  
	        outStream.write(baos.toByteArray(), 0, baos.size());  
	        outStream.flush(); 
	        outStream.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@GetMapping("/ajax_query_print_apply")
	@ResponseBody
	public RespondResult ajaxQueryPrintApply(MedicalRecordPrinterQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> printerApplys = new ArrayList<Map<String,Object>>();
			int count = 0;
			if(!params.IsPrinterApplyEmpty()){
				printerApplys = medicalRecordPrinterService.getPrinterApplyByPrinterQuery(params);
				count= medicalRecordPrinterService.getPrinterApplyCountByPrinterQuery(params);
			}
			
			params.setTotalCounts(count);
			params.setQueryDatas(printerApplys);
			respondResult = new RespondResult(true, RespondResult.successCode, null, params);
		}catch (Exception e) {
			// TODO: handle exception
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), params);
		}
		
		return respondResult;
	}
	
	
	@GetMapping("/ajax_query_print_apply_item")
	@ResponseBody
	public RespondResult ajaxQueryPrintApplyItem(Integer printerApplyId){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> printerApplyItems = new ArrayList<Map<String,Object>>();
			printerApplyItems = medicalRecordPrinterService.getPrinterApplyItemByApplyId(printerApplyId);
			
			respondResult = new RespondResult(true, RespondResult.successCode, null, printerApplyItems);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), "");
		}
		
		return respondResult;
	}
}
