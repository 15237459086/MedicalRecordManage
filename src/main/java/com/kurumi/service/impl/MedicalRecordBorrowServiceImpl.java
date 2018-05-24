package com.kurumi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kurumi.config.MyConfig;
import com.kurumi.mapper.MedicalRecordBorrowApplyItemMapper;
import com.kurumi.mapper.MedicalRecordBorrowApplyMapper;
import com.kurumi.mapper.MedicalRecordMapper;
import com.kurumi.pojo.MedicalRecord;
import com.kurumi.pojo.MedicalRecordBorrowApply;
import com.kurumi.pojo.MedicalRecordBorrowApplyItem;
import com.kurumi.query.MedicalRecordBorrowQuery;
import com.kurumi.service.MedicalRecordBorrowService;
import com.kurumi.util.DateUtil;

@Service
public class MedicalRecordBorrowServiceImpl implements MedicalRecordBorrowService {

	@Autowired
	private MyConfig myConfig;
	
	@Autowired
	private MedicalRecordMapper medicalRecordMapper;
	
	@Autowired
	private MedicalRecordBorrowApplyMapper borrowApplyMapper;
	
	@Autowired
	private MedicalRecordBorrowApplyItemMapper borrowApplyItemMapper;
	
	@Override
	public List<Map<String, Object>> getMedicalRecordOfBorrow(MedicalRecordBorrowQuery borrowQuery) {
		// TODO Auto-generated method stub
		return borrowApplyItemMapper.getMedicalRecordOfBorrow(borrowQuery);
	}

	@Override
	public int getMedicalRecordCountOfBorrow(MedicalRecordBorrowQuery borrowQuery) {
		// TODO Auto-generated method stub
		return borrowApplyItemMapper.getMedicalRecordCountOfBorrow(borrowQuery);
	}

	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int addMedicalRecordBorrowApply(String visitGuid) {
		// TODO Auto-generated method stub
		MedicalRecordBorrowApply borrowApply = new MedicalRecordBorrowApply();
		Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
		Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
		String userCode =(String)currentUser.get("user_code");
		String userName =(String)currentUser.get("user_name");
		borrowApply.setBorrowerName(userName);
		borrowApply.setBorrowerCode(userCode);
		borrowApplyMapper.insert(borrowApply);
		MedicalRecord medicalRecord = medicalRecordMapper.selectByPrimaryKey(visitGuid);
		MedicalRecordBorrowApplyItem borrowApplyItem = new MedicalRecordBorrowApplyItem();
		borrowApplyItem.setMedicalRecordBorrowApplyId(borrowApply.getId());
		borrowApplyItem.setVisitGuid(visitGuid);
		borrowApplyItem.setOnlyId(medicalRecord.getOnlyId());
		borrowApplyItem.setMrId(medicalRecord.getMrId());
		borrowApplyItem.setVisitNumber(medicalRecord.getVisitNumber());
		borrowApplyItem.setPatientName(medicalRecord.getPatientName());
		borrowApplyItem.setPatientCode(medicalRecord.getIdNumber());
		borrowApplyItem.setOutDeptCode(medicalRecord.getOutDeptCode());
		borrowApplyItem.setOutDeptName(medicalRecord.getOutDeptName());
		borrowApplyItem.setOutHospitalDateTime(medicalRecord.getOutHospitalDateTime());
		borrowApplyItem.setReplyStatusCode(MedicalRecordBorrowApply.BORROW_APPLY_CODE);
		borrowApplyItem.setReplyStatusName(MedicalRecordBorrowApply.BORROW_APPLY_NAME);
		return borrowApplyItemMapper.insert(borrowApplyItem);
	}

	@Override
	public List<Map<String, Object>> getMedicalRecordOfBorrowApply(MedicalRecordBorrowQuery borrowQuery) {
		// TODO Auto-generated method stub
		return borrowApplyItemMapper.getMedicalRecordOfBorrowApply(borrowQuery);
	}

	@Override
	public int getMedicalRecordCountOfBorrowApply(MedicalRecordBorrowQuery borrowQuery) {
		// TODO Auto-generated method stub
		return borrowApplyItemMapper.getMedicalRecordCountOfBorrowApply(borrowQuery);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int borrowReply(Integer borrowReplyItemId,String replyCode) {
		// TODO Auto-generated method stub
		MedicalRecordBorrowApplyItem borrowApplyItem =borrowApplyItemMapper.selectByPrimaryKey(borrowReplyItemId);
		Date currentDate = new Date();
		borrowApplyItem.setReplyDateTime(currentDate);
		if(MedicalRecordBorrowApply.BORROW_PERMIT_CODE.equalsIgnoreCase(replyCode)){
			borrowApplyItem.setReplyStatusCode(MedicalRecordBorrowApply.BORROW_PERMIT_CODE);
			borrowApplyItem.setReplyStatusName(MedicalRecordBorrowApply.BORROW_PERMIT_NAME);
			borrowApplyItem.setValidityDateTime(DateUtil.addDay(currentDate, myConfig.getBorrowLimitDay()));
		}else{
			borrowApplyItem.setReplyStatusCode(MedicalRecordBorrowApply.BORROW_REJECT_CODE);
			borrowApplyItem.setReplyStatusName(MedicalRecordBorrowApply.BORROW_REJECT_NAME);
		}
		Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
		Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
		String userCode =(String)currentUser.get("user_code");
		String userName =(String)currentUser.get("user_name");
		borrowApplyItem.setReplyerCode(userCode);
		borrowApplyItem.setReplyerName(userName);
		
		return borrowApplyItemMapper.borrowReplyById(borrowApplyItem);
	}

	@Override
	public List<Map<String, Object>> getBorrowRecord(MedicalRecordBorrowQuery borrowQuery) {
		// TODO Auto-generated method stub
		return borrowApplyItemMapper.getBorrowRecord(borrowQuery);
	}

	@Override
	public int getBorrowRecordCount(MedicalRecordBorrowQuery borrowQuery) {
		// TODO Auto-generated method stub
		return borrowApplyItemMapper.getBorrowRecordCount(borrowQuery);
	}

}
