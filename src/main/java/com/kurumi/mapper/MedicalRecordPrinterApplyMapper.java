package com.kurumi.mapper;

import java.util.List;
import java.util.Map;

import com.kurumi.pojo.MedicalRecordPrinterApply;
import com.kurumi.query.MedicalRecordPrinterQuery;

public interface MedicalRecordPrinterApplyMapper {
   
    
    int deleteByPrimaryKey(Integer id);

    int insert(MedicalRecordPrinterApply record);

    int insertSelective(MedicalRecordPrinterApply record);

    
    MedicalRecordPrinterApply selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(MedicalRecordPrinterApply record);

    int updateByPrimaryKey(MedicalRecordPrinterApply record);
    
    List<Map<String,Object>> getPrinterApplyByPrinterQuery(MedicalRecordPrinterQuery record);
    
    int getPrinterApplyCountByPrinterQuery(MedicalRecordPrinterQuery record);
}