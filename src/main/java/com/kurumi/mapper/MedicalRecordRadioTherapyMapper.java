package com.kurumi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kurumi.pojo.MedicalRecordRadioTherapy;
public interface MedicalRecordRadioTherapyMapper {
    
    
    int deleteByPrimaryKey(String visitGuid);

    int insert(MedicalRecordRadioTherapy record);

    int insertSelective(MedicalRecordRadioTherapy record);

    MedicalRecordRadioTherapy selectByPrimaryKey(String visitGuid);

    int updateByPrimaryKeySelective(MedicalRecordRadioTherapy record);

    int updateByPrimaryKey(MedicalRecordRadioTherapy record);
    
    /**
	 * 检验病案是否唯一
	 * @param onlyId
	 * @param mrId
	 * @param visitNumber
	 * @return
	 */
	int checkMeditalRecordUniq(@Param("mrId") String mrId);
	
	
	List<String> getVisitGuidByMrId(@Param("mrId") String mrId);
	
}