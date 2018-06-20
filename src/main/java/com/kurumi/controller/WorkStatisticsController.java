package com.kurumi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurumi.pojo.RespondResult;
import com.kurumi.query.WorkStatisticsQuery;
import com.kurumi.service.WorkStatisticsService;

@Controller
@RequestMapping("/work_statistics")
public class WorkStatisticsController {

	@Autowired
	private WorkStatisticsService workStatisticsService;
	
	@GetMapping("/pigeonholed_statistics_page")
	public String pigeonholedStatisticsPage(){
		return "work_statistics/pigeonholed_statistics";
	}
	
	@GetMapping("/coding_statistics_page")
	public String codingStatisticsPage(){
		return "work_statistics/coding_statistics";
	}
	
	
	@GetMapping("/pigeonholed_statistics")
	@ResponseBody
	public RespondResult pigeonholedStatistics(WorkStatisticsQuery params){
		RespondResult respondResult = null;
		
		try{
			Map<String, Object> datas = workStatisticsService.pigeonholedStatistics(params);
			respondResult = new RespondResult(true, RespondResult.successCode, null, datas);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), params);
		}
		
		return respondResult;
	}
	
	@GetMapping("/coding_statistics")
	@ResponseBody
	public RespondResult codingStatistics(WorkStatisticsQuery params){
		RespondResult respondResult = null;
		
		try{
			Map<String, Object> datas = workStatisticsService.codingStatistics(params);
			respondResult = new RespondResult(true, RespondResult.successCode, null, datas);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(), params);
		}
		
		return respondResult;
	}
}
