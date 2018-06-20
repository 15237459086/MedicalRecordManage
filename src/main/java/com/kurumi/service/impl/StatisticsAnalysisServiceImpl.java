package com.kurumi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurumi.mapper.StatisticsAnalysisMapper;
import com.kurumi.service.StatisticsAnalysisService;

@Service
public class StatisticsAnalysisServiceImpl implements StatisticsAnalysisService {

	@Autowired
	private StatisticsAnalysisMapper statisticsAnalysisMapper;
	
	@Override
	public int getMedicalRecordCount(Date outHospitalStartDate, Date outHospitalEndDate) {
		// TODO Auto-generated method stub
		return statisticsAnalysisMapper.getMedicalRecordCount(outHospitalStartDate, outHospitalEndDate);
	}

	@Override
	public List<Map<String, Object>> getMedicalPayTypeAnalysis(Date outHospitalStartDate, Date outHospitalEndDate) {
		// TODO Auto-generated method stub
		return statisticsAnalysisMapper.getMedicalPayTypeAnalysis(outHospitalStartDate, outHospitalEndDate);
	}

	@Override
	public List<Map<String, Object>> getInHospitalTypeAnalysis(Date outHospitalStartDate, Date outHospitalEndDate) {
		// TODO Auto-generated method stub
		return statisticsAnalysisMapper.getInHospitalTypeAnalysis(outHospitalStartDate, outHospitalEndDate);
	}

}
