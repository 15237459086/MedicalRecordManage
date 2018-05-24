package com.kurumi.mapper;

import com.kurumi.pojo.MedicalRecordBorrowApply;

public interface MedicalRecordBorrowApplyMapper {
    
    int deleteByPrimaryKey(Integer id);

    int insert(MedicalRecordBorrowApply record);

    int insertSelective(MedicalRecordBorrowApply record);
    
    MedicalRecordBorrowApply selectByPrimaryKey(Integer id);
   
    int updateByPrimaryKeySelective(MedicalRecordBorrowApply record);

    int updateByPrimaryKey(MedicalRecordBorrowApply record);
}