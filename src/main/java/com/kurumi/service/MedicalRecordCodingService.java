package com.kurumi.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kurumi.pojo.coding.BasicInfo;
import com.kurumi.pojo.coding.CureInfo;
import com.kurumi.pojo.coding.DiseaseDiagInfo;
import com.kurumi.pojo.coding.NurseInfo;
import com.kurumi.pojo.coding.OperateInfo;

public interface MedicalRecordCodingService {

	int editBasicInfo(String visitGuid,BasicInfo basicInfo,Map<String, Object> jsonMap);
	
	int editCureInfo(String visitGuid,CureInfo cureInfo,Map<String, Object> jsonMap);
	
	int editDiseaseDiagInfo(String visitGuid,DiseaseDiagInfo diseaseDiagInfo,Map<String, Object> jsonMap);
	
	int editOperateInfo(String visitGuid,OperateInfo operateInfo,Map<String, Object> jsonMap);
	
	int editNurseInfo(String visitGuid,NurseInfo nurseInfo,Map<String, Object> jsonMap);
	
	
	/**
	 * 根visitGuid获取病案json数据
	 * @param visitGuid
	 * @return
	 */
	public List<String> getMedicalRecordJsonByVisitGuid(@Param("visitGuid")String visitGuid);
	
	
	String codingFinish(String visitGuid);
}