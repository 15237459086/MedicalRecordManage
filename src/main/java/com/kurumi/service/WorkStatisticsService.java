package com.kurumi.service;

import java.util.Map;

import com.kurumi.query.WorkStatisticsQuery;

public interface WorkStatisticsService {
	
	Map<String,Object> pigeonholedStatistics(WorkStatisticsQuery params);

	Map<String,Object> codingStatistics(WorkStatisticsQuery params);
	
}
