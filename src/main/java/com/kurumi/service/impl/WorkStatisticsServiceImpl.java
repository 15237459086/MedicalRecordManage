package com.kurumi.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurumi.mapper.WorkStatisticsMapper;
import com.kurumi.query.WorkStatisticsQuery;
import com.kurumi.service.WorkStatisticsService;

@Service
public class WorkStatisticsServiceImpl implements WorkStatisticsService {

	@Autowired
	private WorkStatisticsMapper workStatisticsMapper;
	
	@Override
	public Map<String, Object> pigeonholedStatistics(WorkStatisticsQuery params) {
		// TODO Auto-generated method stub
		Map<String, Object> datas = new HashMap<String, Object>();
		int medicalRecordCount = workStatisticsMapper.getMedicalRecordCountOfShouldPigeonholed(params.getStartDate(), params.getEndDate());
		datas.put("medicalRecordCount", medicalRecordCount);
		
		int imageCountOfShouldPigeonholed = workStatisticsMapper.getImageCountOfShouldPigeonholed(params.getStartDate(), params.getEndDate());
		datas.put("imageCountOfShouldPigeonholed", imageCountOfShouldPigeonholed);
		
		
		int imageCountOfPigeonholed = workStatisticsMapper.getImageCountOfPigeonholed(params.getStartDate(), params.getEndDate());
		datas.put("imageCountOfPigeonholed", imageCountOfPigeonholed);
		
		float pigeonholedRate = imageCountOfPigeonholed/(float)imageCountOfShouldPigeonholed*100;
		datas.put("pigeonholedRate",new DecimalFormat("##0.00").format(pigeonholedRate)+"%");
		
		List<Map<String,Object>> groupImageCountOfPigeonholers = workStatisticsMapper.getGroupImageCountOfPigeonholer(params.getStartDate(), params.getEndDate());
		for (Map<String, Object> imageCountOfPigeonholer : groupImageCountOfPigeonholers) {
			long imageCount = (long)imageCountOfPigeonholer.get("count");
			float imageCountRate = new BigDecimal(imageCount).intValue()/(float)imageCountOfShouldPigeonholed*100;
			imageCountOfPigeonholer.put("imageCountRate",new DecimalFormat("##0.00").format(imageCountRate)+"%");
		}
		datas.put("groupImageCountOfPigeonholers", groupImageCountOfPigeonholers);
		return datas;
	}

	@Override
	public Map<String, Object> codingStatistics(WorkStatisticsQuery params) {
		// TODO Auto-generated method stub
		Map<String, Object> datas = new HashMap<String, Object>();
		int medicalRecordCount = workStatisticsMapper.getMedicalRecordCountOfCodinged(params.getStartDate(), params.getEndDate());
		datas.put("medicalRecordCount", medicalRecordCount);
		List<Map<String,Object>> groupMedicalRecordCountOfCodingeds = workStatisticsMapper.getGroupMedicalRecordCountOfCodinged(params.getStartDate(), params.getEndDate());
		for (Map<String, Object> medicalRecordCountOfCodinged : groupMedicalRecordCountOfCodingeds) {
			long codingCount = (long)medicalRecordCountOfCodinged.get("count");
			float codingCountRate = new BigDecimal(codingCount).intValue()/(float)medicalRecordCount*100;
			medicalRecordCountOfCodinged.put("codingCountRate",new DecimalFormat("##0.00").format(codingCountRate)+"%");
		}
		datas.put("groupMedicalRecordCountOfCodingeds", groupMedicalRecordCountOfCodingeds);
		return datas;
	}

}
