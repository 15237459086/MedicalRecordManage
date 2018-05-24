package com.kurumi.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.kurumi.pojo.RespondResult;
import com.kurumi.query.MedicalRecordBorrowQuery;
import com.kurumi.service.MedicalRecordBorrowService;
import com.kurumi.util.StringUtil;
import com.kurumi.util.WaterMarkUtil;

@Controller
@RequestMapping("/medical_record_borrow")
public class MedicalRecordBorrowController {

	@Autowired
	private MyConfig myConfig;
	
	@Autowired
	private MedicalRecordBorrowService medicalRecordBorrowService;
	
	@GetMapping("/borrow_apply")
	public String borrowApply(Model model){
		return "borrow/borrow_apply";
	}
	
	@GetMapping("/borrow_reply")
	public String borrowReply(Model model){
		return "borrow/borrow_reply";
	}
	
	@GetMapping("/borrow_record")
	public String borrowRecord(Model model){
		return "borrow/borrow_record";
	}
	
	@GetMapping("/ajax_query_medical_record")
	@ResponseBody
	public RespondResult ajaxQueryMedicalRecord(MedicalRecordBorrowQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			int count = 0;
			if(!params.IsBorrowApplyEmpty()){
				Subject subject=SecurityUtils.getSubject();
				Session session = subject.getSession();
				Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
				String userCode =(String)currentUser.get("user_code");
				params.setBorrowerCode(userCode);
				medicalRecords = medicalRecordBorrowService.getMedicalRecordOfBorrow(params);
				count= medicalRecordBorrowService.getMedicalRecordCountOfBorrow(params);
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
	
	
	
	@GetMapping("/ajax_query_borrow_apply")
	@ResponseBody
	public RespondResult ajaxQueryBorrowApply(MedicalRecordBorrowQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			int count = 0;
			if(!params.IsBorrowApplyEmpty()){
				
				medicalRecords = medicalRecordBorrowService.getMedicalRecordOfBorrowApply(params);
				count= medicalRecordBorrowService.getMedicalRecordCountOfBorrowApply(params);
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
	@PostMapping("/ajax_borrow_reply")
	public RespondResult ajaxAddBorrowReply(Integer borrowReplyItemId,String replyCode,Model model){
		try{
			int result = medicalRecordBorrowService.borrowReply(borrowReplyItemId,replyCode);
			if(result > 0){
				return new RespondResult(true, RespondResult.successCode, "审批成功", "审批成功");
			}else{
				return new RespondResult(true, RespondResult.errorCode, "审批失败", "审批失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new RespondResult(false, RespondResult.errorCode, e.getMessage(), "审批失败");
		}
	}
	
	@ResponseBody
	@PostMapping("/ajax_add_borrow_apply")
	public RespondResult ajaxAddBorrowApply(String visitGuid, Model model){
		try{
			int result = medicalRecordBorrowService.addMedicalRecordBorrowApply(visitGuid);
			if(result > 0){
				return new RespondResult(true, RespondResult.successCode, "借阅申请成功", "借阅申请成功");
			}else{
				return new RespondResult(true, RespondResult.errorCode, "借阅申请失败", "借阅申请失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new RespondResult(false, RespondResult.errorCode, e.getMessage(), "借阅申请失败");
		}
	}
	
	
	@GetMapping("/ajax_query_borrow_record")
	@ResponseBody
	public RespondResult ajaxQueryBorrowRecord(MedicalRecordBorrowQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			int count = 0;
			if(!params.IsBorrowApplyEmpty()){
				Subject subject=SecurityUtils.getSubject();
				Session session = subject.getSession();
				Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
				String userCode =(String)currentUser.get("user_code");
				params.setBorrowerCode(userCode);
				medicalRecords = medicalRecordBorrowService.getBorrowRecord(params);
				count= medicalRecordBorrowService.getBorrowRecordCount(params);
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
	
	
	@RequestMapping("/show_pdf_to_borrow")
	public void showImagePagination(String visitGuid, HttpServletResponse response) {
		
		ByteArrayOutputStream baos = null;
		try {
			String pdfPath = myConfig.getPdfRecourcePath()+StringUtil.getLocalPath(visitGuid)+ visitGuid+"\\"+"show.pdf";
			
			baos = WaterMarkUtil.getOutputStreamOfWaterMarkByText(pdfPath, "仅可用于借阅");
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
}
