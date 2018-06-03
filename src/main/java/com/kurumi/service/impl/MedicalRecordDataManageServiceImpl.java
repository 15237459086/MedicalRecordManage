package com.kurumi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurumi.mapper.MedicalRecordMapper;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.service.MedicalRecordDataManageService;
import com.kurumi.util.JsonUtil;
import com.kurumi.util.StringUtil;

@Service
public class MedicalRecordDataManageServiceImpl implements MedicalRecordDataManageService {

	@Autowired
	private MedicalRecordMapper medicalRecordMapper;
	
	@Override
	public List<Map<String,Object>> getMedicalRecordOfExport(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordOfExport(medicalRecordQuery);
	}

	@Override
	public List<Map<String, Object>> getMedicalRecordOfExcel(MedicalRecordQuery medicalRecordQuery) {
		List<Map<String,Object>> medicalRecords = medicalRecordMapper.getMedicalRecordOfExport(medicalRecordQuery);
		for (Map<String, Object> medicalRecord : medicalRecords) {
			String visitGuid = (String)medicalRecord.get("visit_guid");
			List<String> jsonDatas = medicalRecordMapper.getMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
			if(!jsonDatas.isEmpty()){
				Map<String, Object> jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
				medicalRecord.put("jsonMap", jsonMap);
			}
		}
		// TODO Auto-generated method stub
		return medicalRecords;
	}

}
