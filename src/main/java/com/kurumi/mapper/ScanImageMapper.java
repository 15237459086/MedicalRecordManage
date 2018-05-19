package com.kurumi.mapper;

import com.kurumi.pojo.ScanImage;

public interface ScanImageMapper {
   

    int deleteByPrimaryKey(String fileHash);

    int insert(ScanImage record);

    int insertSelective(ScanImage record);
    
    ScanImage selectByPrimaryKey(String fileHash);
    
    int updateByPrimaryKeySelective(ScanImage record);

    int updateByPrimaryKey(ScanImage record);
}