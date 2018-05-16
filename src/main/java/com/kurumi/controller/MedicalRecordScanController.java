package com.kurumi.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kurumi.config.MyConfig;
import com.kurumi.pojo.RespondResult;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.service.MedicalRecordScanService;
import com.kurumi.util.GuidUtil;

@Controller
@RequestMapping("/medical_record_scan")
public class MedicalRecordScanController {

	@Autowired
	private MyConfig myConfig;
	
	@Autowired
	private MedicalRecordScanService medicalRecordScanService;
	
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
	public String imagesUpload(@RequestParam("uploadImages")MultipartFile[] uploadImages,HttpServletRequest request) {
		try{
			if(uploadImages!=null && uploadImages.length>0){
				for (MultipartFile uploadImage : uploadImages) {
					//文件原名称
					String originalFilename=uploadImage.getOriginalFilename();
					//文件流Hash（100位）
					String hashStr = new StringBuffer(GuidUtil.get36SystemHash(new String(uploadImage.getBytes()))).reverse().toString();
					
		
					//获取文件后缀名
					String fileSuffixes = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
					long fileSize = uploadImage.getSize();
					String fileSizeHashStr = GuidUtil.get36SystemHash(fileSize);
					
					//文件Hash
					String fileHash = new StringBuffer(hashStr).append(fileSizeHashStr).toString();
					String localPath = GuidUtil.getLocalPath(hashStr);
					
					String newFileName = hashStr.substring(8)+fileSizeHashStr.substring(0, 5);
					
					
					//文件保存路径
					String filePath = myConfig.getImageRecourcePath() + localPath;
					File destFile = new File(filePath,newFileName+"."+fileSuffixes);
					if(!destFile.exists()){
						File destDir = new File(filePath);
						if(!destDir.exists()){
							destDir.mkdirs();
						}
						uploadImage.transferTo(destFile);
					}
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:/medical_record/pigeonholed";
	}
}
