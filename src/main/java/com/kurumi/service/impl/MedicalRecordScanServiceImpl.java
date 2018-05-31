package com.kurumi.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kurumi.config.MyConfig;
import com.kurumi.mapper.BaseInfoMapper;
import com.kurumi.mapper.MedicalRecordImageMapper;
import com.kurumi.mapper.MedicalRecordMapper;
import com.kurumi.mapper.ScanImageMapper;
import com.kurumi.pojo.MedicalRecordImage;
import com.kurumi.pojo.MedicalRecordTrace;
import com.kurumi.pojo.ScanImage;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.service.MedicalRecordScanService;
import com.kurumi.util.GuidUtil;

@Service
public class MedicalRecordScanServiceImpl implements MedicalRecordScanService {

	@Autowired
	private MyConfig myConfig;
	
	@Autowired
	private BaseInfoMapper baseInfoMapper;
	
	@Autowired
	private MedicalRecordMapper medicalRecordMapper;
	
	@Autowired
	private ScanImageMapper scanImageMapper;
	
	@Autowired
	private MedicalRecordImageMapper medicalRecordImageMapper;
	
	
	@Override
	public List<Map<String, Object>> getMedicalRecordOfScan(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordOfScan(medicalRecordQuery);
	}

	@Override
	public int getMedicalRecordCountOfScan(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordCountOfScan(medicalRecordQuery);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@SuppressWarnings("unchecked")
	@Override
	public void scanImagesUpload(String visitGuid, MultipartFile[] uploadImages) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
		Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
		String userCode =(String)currentUser.get("user_code");
		String userName = (String)currentUser.get("user_name");
		List<ScanImage> scanImages = new ArrayList<ScanImage>();
		for (MultipartFile uploadImage : uploadImages) {
			ScanImage scanImage = new ScanImage();
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
			scanImage.setFileHash(fileHash);
			scanImage.setFileName(newFileName);
			scanImage.setFileType(fileSuffixes);
			scanImages.add(scanImage);
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
		for (ScanImage scanImage : scanImages) {
			if(scanImageMapper.selectByPrimaryKey(scanImage.getFileHash()) == null){
				scanImage.setCreateUserId(userCode);
				scanImage.setCreateUserName(userName);
				System.err.println(scanImage.getFileName().length());
				System.err.println(scanImage.getFileHash().length());
				scanImageMapper.insert(scanImage);
			}
			
			if(medicalRecordImageMapper.getCountByFileHashAndVisitGuid(scanImage.getFileHash(), visitGuid)==0){
				MedicalRecordImage medicalRecordImage = new MedicalRecordImage();
				medicalRecordImage.setFileHash(scanImage.getFileHash());
				medicalRecordImage.setVisitGuid(visitGuid);
				medicalRecordImage.setCreateUserId(userCode);
				medicalRecordImage.setCreateUserName(userName);
				medicalRecordImage.setLastUserId(userCode);
				medicalRecordImage.setLastUserName(userName);
				medicalRecordImageMapper.insert(medicalRecordImage);
			}
		}
		
	}

	@Override
	public List<Map<String, Object>> getImageFilesByVisitGuid(String visitGuid) {
		// TODO Auto-generated method stub
		return medicalRecordImageMapper.getImageFilesByVisitGuid(visitGuid);
	}

	@Override
	public List<Map<String, Object>> getImageFileByFileHash(String fileHash) {
		// TODO Auto-generated method stub
		return medicalRecordImageMapper.getImageFileByFileHash(fileHash);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int imagePagination(String visitGuid, String fileHash, String newPageTypeCode) {
		// TODO Auto-generated method stub
		MedicalRecordImage medicalRecordImage =medicalRecordImageMapper.selectByVisitGuidAndFileHash(visitGuid, fileHash);
		if(medicalRecordImage !=null){
			Subject subject=SecurityUtils.getSubject();
			Session session = subject.getSession();
			Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
			String userCode =(String)currentUser.get("user_code");
			String userName = (String)currentUser.get("user_name");
			medicalRecordImage.setMrPageTypeCode(newPageTypeCode);
			medicalRecordImage.setLastUserId(userCode);
			medicalRecordImage.setLastUserName(userName);
			medicalRecordImage.setLastDateTime(new Date());
			return medicalRecordImageMapper.updateByPrimaryKey(medicalRecordImage);
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> getPaginationCountByVisitGuid(String visitGuid) {
		// TODO Auto-generated method stub
		return medicalRecordImageMapper.getPaginationCountByVisitGuid(visitGuid);
	}

	@Override
	public int getUnPaginationImageCountByVisitGuid(String visitGuid) {
		// TODO Auto-generated method stub
		return medicalRecordImageMapper.getUnPaginationImageCountByVisitGuid(visitGuid);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int imagePaginationFinish(String visitGuid) {
		// TODO Auto-generated method stub
		int unPaginationImageCount = medicalRecordImageMapper.getUnPaginationImageCountByVisitGuid(visitGuid);
		if(unPaginationImageCount != 0){
			return -2;
		}
		List<Map<String,Object>> treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_PAGINATION);
		if(treatmentTraceTypes.size() == 0){
			return -2;
		}
		if(medicalRecordMapper.getMeditalRecordTraceCount(visitGuid, MedicalRecordTrace.VISIT_PAGINATION) > 0){
			return 2;
		}
		MedicalRecordTrace medicalRecordTrace = new MedicalRecordTrace();
		medicalRecordTrace.setVisitGuid(visitGuid);
		Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
		Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
		medicalRecordTrace.setCreateUserId((String)currentUser.get("user_code"));
		medicalRecordTrace.setCreateUserName((String)currentUser.get("user_name"));
		medicalRecordTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
		medicalRecordTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
		
		return medicalRecordMapper.insertMedicalRecordTrace(medicalRecordTrace);
	}

	@Override
	public List<Map<String, Object>> getImageFilesByVisitGuidAndPrinterTypeCode(String visitGuid,
			String printerTypeCode) {
		// TODO Auto-generated method stub
		return medicalRecordImageMapper.getImageFilesByVisitGuidAndPrinterTypeCode(visitGuid, printerTypeCode);
	}

}
