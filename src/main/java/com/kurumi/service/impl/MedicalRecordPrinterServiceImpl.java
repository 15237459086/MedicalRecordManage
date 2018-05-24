package com.kurumi.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kurumi.mapper.MedicalRecordMapper;
import com.kurumi.mapper.MedicalRecordPrinterApplyItemMapper;
import com.kurumi.mapper.MedicalRecordPrinterApplyMapper;
import com.kurumi.pojo.MedicalRecord;
import com.kurumi.pojo.MedicalRecordPrinterApply;
import com.kurumi.pojo.MedicalRecordPrinterApplyItem;
import com.kurumi.query.MedicalRecordPrinterQuery;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.service.MedicalRecordPrinterService;

@Service
public class MedicalRecordPrinterServiceImpl implements MedicalRecordPrinterService {

	@Autowired
	private MedicalRecordMapper medicalRecordMapper;
	
	@Autowired
	private MedicalRecordPrinterApplyMapper printerApplyMapper;
	
	@Autowired
	private MedicalRecordPrinterApplyItemMapper printerApplyItemMapper;
	
	@Override
	public List<Map<String, Object>> getMedicalRecordOfPrinter(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordOfPrinter(medicalRecordQuery);
	}

	@Override
	public int getMedicalRecordCountOfPrinter(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordCountOfPrinter(medicalRecordQuery);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int addMedicalRecordPrinterApply(MedicalRecordPrinterApply printerApply,
			MedicalRecordPrinterApplyItem printerApplyItem) {
		// TODO Auto-generated method stub
		printerApply.setApplyTypeCode("Windows");
		printerApply.setApplyTypeName("窗口");
		Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
		Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
		String userCode = (String)currentUser.get("user_code");
		String userName = (String)currentUser.get("user_name");
		printerApply.setRegistrantCode(userCode);
		printerApply.setRegistrantName(userName);
		printerApplyMapper.insert(printerApply);
		MedicalRecord medicalRecord = medicalRecordMapper.selectByPrimaryKey(printerApplyItem.getVisitGuid());
		
		
		printerApplyItem.setMedicalRecordPrinterApplyId(printerApply.getId());
		printerApplyItem.setOnlyId(medicalRecord.getOnlyId());
		printerApplyItem.setMrId(medicalRecord.getMrId());
		printerApplyItem.setVisitNumber(medicalRecord.getVisitNumber());
		printerApplyItem.setPatientName(medicalRecord.getPatientName());
		printerApplyItem.setPatientCode(medicalRecord.getIdNumber());
		printerApplyItem.setOutHospitalDateTime(medicalRecord.getOutHospitalDateTime());
		printerApplyItem.setAuditorCode(userCode);
		printerApplyItem.setAuditorName(userName);
		printerApplyItem.setApprovalStatusCode("PERMIT");
		printerApplyItem.setApprovalStatusName("审核通过");
		printerApplyItemMapper.insert(printerApplyItem);
		return printerApplyItem.getId();
	}

	@Override
	public List<Map<String, Object>> getPrinterApplyItemById(Integer printerApplyItemId) {
		// TODO Auto-generated method stub
		return printerApplyItemMapper.getPrinterApplyItemById(printerApplyItemId);
	}

	@Override
	public List<Map<String, Object>> getPrinterApplyByPrinterQuery(MedicalRecordPrinterQuery record) {
		// TODO Auto-generated method stub
		return printerApplyMapper.getPrinterApplyByPrinterQuery(record);
	}

	@Override
	public int getPrinterApplyCountByPrinterQuery(MedicalRecordPrinterQuery record) {
		// TODO Auto-generated method stub
		return printerApplyMapper.getPrinterApplyCountByPrinterQuery(record);
	}

	@Override
	public List<Map<String, Object>> getPrinterApplyItemByApplyId(Integer printerApplyId) {
		// TODO Auto-generated method stub
		return printerApplyItemMapper.getPrinterApplyItemByApplyId(printerApplyId);
	}

	}
