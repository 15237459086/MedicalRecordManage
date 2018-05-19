package com.kurumi.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kurumi.config.MyConfig;
import com.kurumi.pojo.RespondResult;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.service.BaseInfoService;
import com.kurumi.service.MedicalRecordScanService;
import com.kurumi.service.MedicalRecordService;
import com.kurumi.util.GuidUtil;
import com.kurumi.util.JsonUtil;
import com.kurumi.util.PDFUtil;

@Controller
@RequestMapping("/medical_record_scan")
public class MedicalRecordScanController {

	@Autowired
	private MyConfig myConfig;
	
	@Autowired
	private BaseInfoService baseInfoService;
	
	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@Autowired
	private MedicalRecordScanService medicalRecordScanService;
	
	@GetMapping("/scan_upload")
	public String scanUpload(String errorCode,Model model){
		model.addAttribute("errorCode", errorCode);
		return "scan/scan_upload";
	}
	
	
	@GetMapping("/scan_image_pagination")
	public String scanImagePagination(String mrId,Model model){
		model.addAttribute("mrId", mrId);
		return "scan/scan_image_pagination";
	}
	
	@GetMapping("/ajax_query_scan")
	@ResponseBody
	public RespondResult ajaxQueryScan(MedicalRecordQuery params){
		RespondResult respondResult = null;
		
		try{
			List<Map<String,Object>> medicalRecords = new ArrayList<Map<String,Object>>();
			int count = 0;
			if(!params.queryUnEncodingEmpty()){
				medicalRecords = medicalRecordScanService.getMedicalRecordOfScan(params);
				count= medicalRecordScanService.getMedicalRecordCountOfScan(params);
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
	
	@PostMapping(value = "/images_upload")
	public String imagesUpload(@RequestParam("uploadImages")MultipartFile[] uploadImages,@RequestParam("visitGuid")String visitGuid,HttpServletRequest request) {
		try{
			if(uploadImages!=null && uploadImages.length>0){
				medicalRecordScanService.scanImagesUpload(visitGuid, uploadImages);
				List<Map<String,Object>> medicalRecords = medicalRecordService.getMedicalRecordByVisitGuid(visitGuid);
				String mrId = (String)medicalRecords.get(0).get("mr_id");
				return "redirect:/medical_record_scan/scan_image_pagination?mrId="+mrId;
			}else{
				return "redirect:/medical_record_scan/scan_upload?errorCode=1";
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "redirect:/medical_record_scan/scan_upload?errorCode=2";
		}
		
	}
	
	@GetMapping("/image_pagination")
	public String imagePagination(String visitGuid, Model model){
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		List<Map<String, Object>> mrPageTypes = baseInfoService.getMrPageTypes();
		baseInfo.put("mrPageTypes", mrPageTypes);
		String baseInfoJson = JsonUtil.objectToJson(baseInfo);
		model.addAttribute("baseInfoJson", baseInfoJson);
		Map<String, Object> datas = new HashMap<String,Object>();
		
		List<Map<String,Object>> medicalRecords = medicalRecordService.getMedicalRecordByVisitGuid(visitGuid);
		List<Map<String,Object>> pageTypeOfPaginations = medicalRecordScanService.getPaginationCountByVisitGuid(visitGuid);
		List<Map<String,Object>> scanFiles = medicalRecordScanService.getImageFilesByVisitGuid(visitGuid);
		datas.put("medicalRecord", medicalRecords.get(0));
		datas.put("scanFiles", scanFiles);
		datas.put("pageTypeOfPaginations", pageTypeOfPaginations);
		datas.put("visitGuid", visitGuid);
		String jsonDatas = JsonUtil.objectToJson(datas);
		model.addAttribute("jsonDatas", jsonDatas);
		return "scan/image_pagination";
	}
	
	
	@GetMapping("/ajax_image_stream")
	@ResponseBody
	public void ajaxImageStream(String fileHash,HttpServletResponse response){
		List<Map<String,Object>> imageFiles = medicalRecordScanService.getImageFileByFileHash(fileHash);
		Map<String,Object> imageFile = null;
		if(imageFiles.size() > 0){
			imageFile = imageFiles.get(0);
		}
		response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        String fileName = (String)imageFile.get("file_name");
        response.addHeader(
                "Content-Disposition",
                "attachment; filename=\""
                        + fileHash + "\"");
        String filePath = myConfig.getImageRecourcePath() + GuidUtil.getLocalPath(fileHash)+"\\"
        					+fileName+"."+imageFile.get("file_type");
        ServletOutputStream out;
        FileInputStream in = null;
		try {
			out = response.getOutputStream();
			in = new FileInputStream(filePath);
			 int len;
	            byte[] buffer = new byte[1024];
	            while ((len = in.read(buffer)) != -1) {
	                out.write(buffer, 0, len);
	            }
	            out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
	
	@ResponseBody
	@PostMapping("/ajax_image_pagination")
	public RespondResult ajaxImagePagination(String visitGuid,String fileHash,String newPageTypeCode, Model model){
		try{
			int result = medicalRecordScanService.imagePagination(visitGuid, fileHash, newPageTypeCode);
			if(result == 0){
				
				return new RespondResult(true, RespondResult.errorCode, "编页失败", "编页失败");
			}else{
				List<Map<String,Object>> datas = medicalRecordScanService.getPaginationCountByVisitGuid(visitGuid);
				return new RespondResult(true, RespondResult.successCode, "编页成功", datas);
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new RespondResult(false, RespondResult.errorCode, e.getMessage(), "编页失败");
		}
	}
	
	
	@ResponseBody
	@PostMapping("/ajax_image_pagination_finish")
	public RespondResult ajaxImagePaginationFinish(String visitGuid, Model model){
		try{
			int result = medicalRecordScanService.imagePaginationFinish(visitGuid);
			if(result == -2){
				return new RespondResult(true, RespondResult.lackCode, "编页完成失败", "基础资源缺失或存在未完成编页的图片");
			}else if(result == 1){
				return new RespondResult(true, RespondResult.successCode, "编页完成", "编页完成");
			}
			else{
				return new RespondResult(true, RespondResult.repeatCode, "编页完成失败", "编页已完成");
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new RespondResult(false, RespondResult.errorCode, e.getMessage(), "编页完成失败");
		}
	}
	
	
	@RequestMapping("/show_image_pagination")
	public void showImagePagination(String visitGuid, HttpServletResponse response) {
		
		ByteArrayOutputStream baos = null;
		try {
			List<Map<String,Object>> scanFiles = medicalRecordScanService.getImageFilesByVisitGuid(visitGuid);
			
			baos = PDFUtil.getPDFStream(scanFiles,myConfig.getImageRecourcePath());
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
	
	@GetMapping("/show_to_publish")
	public String showToPublish(Model model){
		return "publish/show_to_publish";
	}
	
}
