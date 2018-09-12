package com.kurumi.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kurumi.pojo.MedicalRecordImage;
import com.kurumi.pojo.ScanImage;

public interface MedicalRecordImageMapper {
    
    int deleteByPrimaryKey(Integer id);

    int insert(MedicalRecordImage record);

    int insertSelective(MedicalRecordImage record);
    
    MedicalRecordImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MedicalRecordImage record);

    int updateByPrimaryKey(MedicalRecordImage record);
    
    int imagePaginationByPrimaryKey(MedicalRecordImage record);
    
    int getCountByFileHashAndVisitGuid(@Param("fileHash") String fileHash,@Param("visitGuid") String visitGuid);
    
    
    /**
	 * 获取病案图片
	 * @return
	 */
	List<Map<String,Object>> getImageFilesByVisitGuid(@Param("visitGuid") String visitGuid);
	
	/**
	 * 获取病案图片
	 * @return
	 */
	List<Map<String,Object>> getImageFilesByVisitGuidAndPrinterTypeCode(@Param("visitGuid") String visitGuid,@Param("printerTypeCode") String printerTypeCode);
	
	/**
	 * 获取病案图片
	 * @return
	 */
	List<Map<String,Object>> getImageFileByFileHash(@Param("fileHash") String fileHash);
	
	MedicalRecordImage selectByVisitGuidAndFileHash(@Param("visitGuid") String visitGuid,@Param("fileHash") String fileHash);
	
	/**
	 * 获取病案标签编页数量
	 * @return
	 */
	List<Map<String,Object>> getPaginationCountByVisitGuid(@Param("visitGuid") String visitGuid);
	
	/**
	 * 获取未编页图片数量
	 * @param visitGuid
	 * @return
	 */
	int getUnPaginationImageCountByVisitGuid(@Param("visitGuid") String visitGuid);
    
	/**
	 * 获取客观数据病案图片
	 * @return
	 */
	List<Map<String,Object>> getObjectiveImageFilesByVisitGuid(@Param("visitGuid") String visitGuid,@Param("list") List<String> list);
}