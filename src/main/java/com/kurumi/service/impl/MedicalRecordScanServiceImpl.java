package com.kurumi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurumi.mapper.MedicalRecordMapper;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.service.MedicalRecordScanService;

@Service
public class MedicalRecordScanServiceImpl implements MedicalRecordScanService {

	@Autowired
	private MedicalRecordMapper medicalRecordMapper;
	
	
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

}
