package com.kurumi.mapper;

import java.util.List;
import java.util.Map;

import com.kurumi.pojo.MedicalRecordBorrowApplyItem;
import com.kurumi.query.MedicalRecordBorrowQuery;

public interface MedicalRecordBorrowApplyItemMapper {
    
    
    int deleteByPrimaryKey(Integer id);

    int insert(MedicalRecordBorrowApplyItem record);

    int insertSelective(MedicalRecordBorrowApplyItem record);

    MedicalRecordBorrowApplyItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MedicalRecordBorrowApplyItem record);

    int updateByPrimaryKey(MedicalRecordBorrowApplyItem record);
    
    
    /**
	 * 获取用于借阅的病案
	 * @return
	 */
	List<Map<String,Object>> getMedicalRecordOfBorrow(MedicalRecordBorrowQuery borrowQuery);
	
	/**
	 * 获取用于借阅的病案数量
	 * @return
	 */
	int getMedicalRecordCountOfBorrow(MedicalRecordBorrowQuery borrowQuery);
	
	
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
	
	
	int borrowReplyById(MedicalRecordBorrowApplyItem record);
	
	
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