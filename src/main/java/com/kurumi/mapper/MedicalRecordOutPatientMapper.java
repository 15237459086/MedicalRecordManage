package com.kurumi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kurumi.pojo.MedicalRecordOutPatient;

public interface MedicalRecordOutPatientMapper {
    
    
    int deleteByPrimaryKey(String visitGuid);

    int insert(MedicalRecordOutPatient record);

    int insertSelective(MedicalRecordOutPatient record);

    MedicalRecordOutPatient selectByPrimaryKey(String visitGuid);

    int updateByPrimaryKeySelective(MedicalRecordOutPatient record);

    int updateByPrimaryKey(MedicalRecordOutPatient record);
    
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