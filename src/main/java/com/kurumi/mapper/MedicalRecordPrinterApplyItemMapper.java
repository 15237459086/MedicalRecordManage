package com.kurumi.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kurumi.pojo.MedicalRecordPrinterApplyItem;

public interface MedicalRecordPrinterApplyItemMapper {
    
    int deleteByPrimaryKey(Integer id);

    int insert(MedicalRecordPrinterApplyItem record);

    int insertSelective(MedicalRecordPrinterApplyItem record);
    
    MedicalRecordPrinterApplyItem selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(MedicalRecordPrinterApplyItem record);

    int updateByPrimaryKey(MedicalRecordPrinterApplyItem record);
    
    List<Map<String,Object>> getPrinterApplyItemById(@Param("printerApplyItemId") Integer printerApplyItemId);
    
    List<Map<String,Object>> getPrinterApplyItemByApplyId(@Param("printerApplyId") Integer printerApplyId);
}