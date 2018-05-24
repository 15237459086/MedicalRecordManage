package com.kurumi.service;

import java.util.List;
import java.util.Map;

import com.kurumi.query.MedicalRecordBorrowQuery;

public interface MedicalRecordBorrowService {

	/**
	 * 获取用于借阅的病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfBorrow(MedicalRecordBorrowQuery borrowQuery);
	
	/**
	 * 获取用于借阅的数量
	 * @return
	 */
	int getMedicalRecordCountOfBorrow(MedicalRecordBorrowQuery borrowQuery);
	
	
	int addMedicalRecordBorrowApply(String visitGuid);
	
	
	/**
	 * 获取借阅申请的病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfBorrowApply(MedicalRecordBorrowQuery borrowQuery);
	
	/**
	 * 获取借阅申请的病案数量
	 * @return
	 */
	int getMedicalRecordCountOfBorrowApply(MedicalRecordBorrowQuery borrowQuery);
	
	int borrowReply(Integer borrowReplyItemId,String replyCode);
	
	/**
	 * 获取借阅记录
	 * @return
	 */
	List<Map<String,Object>> getBorrowRecord(MedicalRecordBorrowQuery borrowQuery);
	
	/**
	 * 获取借阅记录数量
	 * @return
	 */
	int getBorrowRecordCount(MedicalRecordBorrowQuery borrowQuery);
	
}
