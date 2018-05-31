package com.kurumi.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kurumi.mapper.BaseInfoMapper;
import com.kurumi.mapper.MedicalRecordMapper;
import com.kurumi.pojo.MedicalRecord;
import com.kurumi.pojo.MedicalRecordQualityControl;
import com.kurumi.pojo.MedicalRecordQualityControlItem;
import com.kurumi.pojo.MedicalRecordTrace;
import com.kurumi.query.MedicalRecordQuery;
import com.kurumi.service.MedicalRecordService;
import com.kurumi.util.DateUtil;
import com.kurumi.util.JsonUtil;
import com.kurumi.util.StringUtil;


@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
	
	
	@Autowired
	private MedicalRecordMapper medicalRecordMapper;
	
	@Autowired
	private BaseInfoMapper baseInfoMapper;

	@Override
	public List<Map<String, Object>> getMedicalRecordOfUnPigeonhole(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordOfUnPigeonhole(medicalRecordQuery);
	}

	@Override
	public int getMedicalRecordCountOfUnPigeonhole(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordCountOfUnPigeonhole(medicalRecordQuery);
	}

	@Override
	public int checkMeditalRecordUniq(String onlyId, String mrId, Integer visitNumber) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.checkMeditalRecordUniq(onlyId, mrId, visitNumber);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int insertMeditalRecord(MedicalRecord medicalRecord) {
		// TODO Auto-generated method stub
		int count = 0;
		medicalRecord.setVisitGuid(StringUtil.getId());
		count = medicalRecordMapper.insert(medicalRecord);
		if(count < 0){
			return 0;
		}
		List<Map<String,Object>> treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_INIT);
		if(treatmentTraceTypes.size() == 0){
			return 0;
		}
		
		MedicalRecordTrace medicalRecordTrace = new MedicalRecordTrace();
		
		medicalRecordTrace.setVisitGuid(medicalRecord.getVisitGuid());
		Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
		Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
		medicalRecordTrace.setCreateUserId((String)currentUser.get("user_code"));
		medicalRecordTrace.setCreateUserName((String)currentUser.get("user_name"));
		medicalRecordTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
		medicalRecordTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
		count = medicalRecordMapper.insertMedicalRecordTrace(medicalRecordTrace);
		return count;
	}

	@Transactional(readOnly=true)
	@Override
	public MedicalRecord selectMedicalRecordByPrimaryKey(String visitGuid) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.selectByPrimaryKey(visitGuid);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int updateMedicalRecordByPrimaryKey(MedicalRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int pigeonholeMedicalRecord(String visitGuid, String pigeonholeDateTime, String treatmentSignId) {
		// TODO Auto-generated method stub
		
		MedicalRecord medicalRecord = medicalRecordMapper.selectByPrimaryKey(visitGuid);
		if(medicalRecord == null){
			return -1; //病案不存在
		}
		int count = medicalRecordMapper.getMeditalRecordTraceCount(visitGuid, MedicalRecordTrace.VISIT_PIGEONHOLE);
		if(count > 0){
			return 0; //病案已归档
		}
		List<Map<String,Object>> treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_PIGEONHOLE);
		if(treatmentTraceTypes.size() == 0){
			return -2; //归档失踪类型不存在
		}
		if(treatmentSignId == null || "1".equalsIgnoreCase(treatmentSignId)){
			
			
			medicalRecord.setTreatmentSignCode("BM");
			medicalRecord.setTreatmentSignName("保密");
			
			
		}else{
			medicalRecord.setTreatmentSignCode("PT");
			medicalRecord.setTreatmentSignName("普通");
			
		}
		count = medicalRecordMapper.updateByPrimaryKey(medicalRecord);
		if(count != 1){
			return -9; //归档失败
		}
		MedicalRecordTrace medicalRecordTrace = new MedicalRecordTrace();
		
		medicalRecordTrace.setVisitGuid(medicalRecord.getVisitGuid());
		Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
		Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
		medicalRecordTrace.setCreateUserId((String)currentUser.get("user_code"));
		medicalRecordTrace.setCreateUserName((String)currentUser.get("user_name"));
		medicalRecordTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
		medicalRecordTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
		Date pigeonholeDate = null;
		try {
			pigeonholeDate = DateUtil.dateParse(DateUtil.DATE_TIME_FORMATE, pigeonholeDateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3; // 日期错误
		}
		medicalRecordTrace.setTraceDateTime(pigeonholeDate);
		count = medicalRecordMapper.insertMedicalRecordTrace(medicalRecordTrace);
		if(count != 1){
			return -9; //归档失败
		}
		return 1;
	}

	@Override
	public List<Map<String, Object>> getMedicalRecordOfPigeonholed(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordOfPigeonholed(medicalRecordQuery);
	}

	@Override
	public int getMedicalRecordCountOfPigeonholed(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordCountOfPigeonholed(medicalRecordQuery);
	}

	@Override
	public List<Map<String, Object>> getMedicalRecordOfPigeonholedBeyond(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordOfPigeonholedBeyond(medicalRecordQuery);
	}

	@Override
	public int getMedicalRecordCountOfPigeonholedBeyond(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordCountOfPigeonholedBeyond(medicalRecordQuery);
	}

	@Override
	public List<Map<String, Object>> getMedicalRecordPigeonholedRate(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordPigeonholedRate(medicalRecordQuery);
	}

	@Override
	public List<Map<String, Object>> getMedicalRecordOfQualityControl(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordOfQualityControl(medicalRecordQuery);
	}

	@Override
	public int getMedicalRecordCountOfQualityControl(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordCountOfQualityControl(medicalRecordQuery);
	}

	@Override
	public List<Map<String, Object>> getMedicalRecordByVisitGuid(String visitGuid) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordByVisitGuid(visitGuid);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int addQualityControlItem(String visitGuid, MedicalRecordQualityControlItem qualityControlItem) {
		// TODO Auto-generated method stub
		MedicalRecordQualityControl qualityControl = new MedicalRecordQualityControl();
		qualityControl = medicalRecordMapper.selectQualityControlByPrimaryKey(visitGuid);
		int count = 0;
		if(qualityControl == null){
			qualityControl = new MedicalRecordQualityControl();
			qualityControl.setVisitGuid(visitGuid);
			Subject subject=SecurityUtils.getSubject();
			Session session = subject.getSession();
			Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
			qualityControl.setCreateUserId((String)currentUser.get("user_code"));
			qualityControl.setCreateUserName((String)currentUser.get("user_name"));
			BigDecimal score = qualityControl.getScore().subtract(qualityControlItem.getDeduction());
			if(score.doubleValue() < 0){
				score = BigDecimal.ZERO;
			}
			qualityControl.setScore(score);
			count = medicalRecordMapper.insertQualityControl(qualityControl);
		}else{
			BigDecimal score = qualityControl.getScore().subtract(qualityControlItem.getDeduction());
			if(score.doubleValue() < 0){
				score = BigDecimal.ZERO;
			}
			qualityControl.setScore(score);
			count = medicalRecordMapper.updateQualityControlByPrimaryKey(qualityControl);
		}
		
		if(count == 0){
			return count;
		}
		qualityControlItem.setMedicalRecordQualityControlId(qualityControl.getId());
		Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
		Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
		qualityControlItem.setLastUserId((String)currentUser.get("user_code"));
		qualityControlItem.setLastUserName((String)currentUser.get("user_name"));
		count = medicalRecordMapper.insertQualityControlItem(qualityControlItem);
		return count;
	}

	@Override
	public List<Map<String, Object>> getQualityControlItemByVisitGuid(String visitGuid) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getQualityControlItemByVisitGuid(visitGuid);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int finishQualityControl(String visitGuid) {
		// TODO Auto-generated method stub
		int count = medicalRecordMapper.getMeditalRecordTraceCount(visitGuid, MedicalRecordTrace.VISIT_QUALITY);
		if(count > 0){
			return 2; //此前质控已完成
		}
		
		List<Map<String,Object>> treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_QUALITY);
		if(treatmentTraceTypes.size() == 0){
			return -2; //质控示踪类型不存在
		}
		MedicalRecordTrace medicalRecordTrace = new MedicalRecordTrace();
		
		medicalRecordTrace.setVisitGuid(visitGuid);
		Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
		@SuppressWarnings("unchecked")
		Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
		String userCode = (String)currentUser.get("user_code");
		String userName = (String)currentUser.get("user_name");
		medicalRecordTrace.setCreateUserId(userCode);
		medicalRecordTrace.setCreateUserName(userName);
		medicalRecordTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
		medicalRecordTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
		List<String> jsonDatas = medicalRecordMapper.getMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if(!jsonDatas.isEmpty()){
			
			jsonMap = JsonUtil.jsonToPojo(jsonDatas.get(0), Map.class);
			if(jsonMap != null){
				Map<String, Object> cureInfo = (Map<String, Object>)jsonMap.get("cureInfo");
				cureInfo.put("qualityControlDateTime", DateUtil.dateFormat(DateUtil.DATE_TIME_FORMATE, new Date()));
				List<Map<String,Object>> cureWorkers = (List<Map<String,Object>>)cureInfo.get("cureWorkers");
				for (Map<String, Object> cureWorker : cureWorkers) {
					String professionTitleCode = StringUtil.meaningStr((String)cureWorker.get("professionTitleCode"));
					if(professionTitleCode != null && professionTitleCode.equalsIgnoreCase("ZKYS")){
						cureWorker.put("medicalWorkerCode", userCode);
						cureWorker.put("medicalWorkerName", userName);
						break;
					}
				}
				medicalRecordMapper.deleteMedicalRecordJsonByVisitGuid(StringUtil.handleJsonParam(visitGuid));
				String jsonMapJson = JsonUtil.objectToJson(jsonMap);
				medicalRecordMapper.insertMedicalRecordJson(jsonMapJson);
			}
		}
		count = medicalRecordMapper.insertMedicalRecordTrace(medicalRecordTrace);
		return count;
	}

	@Override
	public List<Map<String, Object>> getQualityControlItemByQualityControlId(Integer qualityControlId) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getQualityControlItemByQualityControlId(qualityControlId);
	}

	@Override
	public MedicalRecordQualityControlItem selectQualityControlItemByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.selectQualityControlItemByPrimaryKey(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public MedicalRecordQualityControlItem updateQualityControlItem(String visitGuid,
			MedicalRecordQualityControlItem qualityControlItem) {
		// TODO Auto-generated method stub
		MedicalRecordQualityControlItem qualityControlItemOfDataBase = medicalRecordMapper.selectQualityControlItemByPrimaryKey(qualityControlItem.getId());
		MedicalRecordQualityControl qualityControl = medicalRecordMapper.selectQualityControlByPrimaryKey(visitGuid);
		BigDecimal score = qualityControl.getScore().add(qualityControlItemOfDataBase.getDeduction()).subtract(qualityControlItem.getDeduction());
		if(score.doubleValue() < 0){
			score = BigDecimal.ZERO;
		}else if(score.doubleValue() >100){
			score = BigDecimal.valueOf(100);
		}
		qualityControl.setScore(score);
		int count = medicalRecordMapper.updateQualityControlByPrimaryKey(qualityControl);
		if(count == 0){
			return null;
		}
		qualityControlItemOfDataBase.setFirstLevelCode(qualityControlItem.getFirstLevelCode());
		qualityControlItemOfDataBase.setFirstLevelName(qualityControlItem.getFirstLevelName());
		qualityControlItemOfDataBase.setSecondLevelCode(qualityControlItem.getSecondLevelCode());
		qualityControlItemOfDataBase.setSecondLevelName(qualityControlItem.getSecondLevelName());
		
		qualityControlItemOfDataBase.setThirdLevelCode(qualityControlItem.getThirdLevelCode());
		qualityControlItemOfDataBase.setThirdLevelName(qualityControlItem.getThirdLevelName());
		qualityControlItemOfDataBase.setDeduction(qualityControlItem.getDeduction());
		qualityControlItemOfDataBase.setRemark(qualityControlItem.getRemark());
		count = medicalRecordMapper.updateQualityControlItemByPrimaryKey(qualityControlItemOfDataBase);
		if(count == 0){
			return null;
		}
		return qualityControlItemOfDataBase;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int deleteQualityControlItem(String visitGuid, Integer qualityControlItemId) {
		// TODO Auto-generated method stub
		MedicalRecordQualityControlItem qualityControlItem = medicalRecordMapper.selectQualityControlItemByPrimaryKey(qualityControlItemId);
		MedicalRecordQualityControl qualityControl = medicalRecordMapper.selectQualityControlByPrimaryKey(visitGuid);
		BigDecimal score = qualityControl.getScore().add(qualityControlItem.getDeduction());
		if(score.doubleValue() < 0){
			score = BigDecimal.ZERO;
		}else if(score.doubleValue() >100){
			score = BigDecimal.valueOf(100);
		}
		qualityControl.setScore(score);
		int count = medicalRecordMapper.updateQualityControlByPrimaryKey(qualityControl);
		if(count == 0){
			return -1;
		}
		count = medicalRecordMapper.deleteQualityControlItemByPrimaryKey(qualityControlItemId);
		if(count == 0){
			return -1;
		}
		return count;
	}

	@Override
	public List<Map<String, Object>> getQualityControlScoreRate(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getQualityControlScoreRate(medicalRecordQuery);
	}

	@Override
	public List<Map<String, Object>> getMedicalRecord(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecord(medicalRecordQuery);
	}

	@Override
	public int getMedicalRecordCount(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordCount(medicalRecordQuery);
	}

	@Override
	public List<Map<String, Object>> getMeditalRecordTraceByVisitGuid(String visitGuid) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMeditalRecordTraceByVisitGuid(visitGuid);
	}

	@Override
	public List<Map<String, Object>> getMedicalRecordOfHomePage(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordOfHomePage(medicalRecordQuery);
	}

	@Override
	public int getMedicalRecordCountOfHomePage(MedicalRecordQuery medicalRecordQuery) {
		// TODO Auto-generated method stub
		return medicalRecordMapper.getMedicalRecordCountOfHomePage(medicalRecordQuery);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int importMedicalRecord(List<MedicalRecord> medicalRecords) {
		// TODO Auto-generated method stub
		int total = 0;
		for (MedicalRecord medicalRecord : medicalRecords) {
			int count = 0;
			MedicalRecordQuery medicalRecordQuery = new MedicalRecordQuery();
			medicalRecordQuery.setOnlyId(medicalRecord.getOnlyId());
			if(medicalRecordMapper.getMedicalRecordCount(medicalRecordQuery) > 0){
				continue;
			}
			
			medicalRecord.setVisitGuid(StringUtil.getId());
			count = medicalRecordMapper.insert(medicalRecord);
			if(count < 0){
				continue;
			}
			List<Map<String,Object>> treatmentTraceTypes = baseInfoMapper.getTraceTypeByCode(MedicalRecordTrace.VISIT_INIT);
			if(treatmentTraceTypes.size() == 0){
				continue;
			}
			
			MedicalRecordTrace medicalRecordTrace = new MedicalRecordTrace();
			
			medicalRecordTrace.setVisitGuid(medicalRecord.getVisitGuid());
			Subject subject=SecurityUtils.getSubject();
			Session session = subject.getSession();
			Map<String, Object> currentUser = (Map<String, Object>)session.getAttribute("currentUser");
			medicalRecordTrace.setCreateUserId((String)currentUser.get("user_code"));
			medicalRecordTrace.setCreateUserName((String)currentUser.get("user_name"));
			medicalRecordTrace.setTraceTypeCode((String)treatmentTraceTypes.get(0).get("code"));
			medicalRecordTrace.setTraceTypeName((String)treatmentTraceTypes.get(0).get("name"));
			count = medicalRecordMapper.insertMedicalRecordTrace(medicalRecordTrace);
			total++;
		}
		return total;
	}
	
	
	

}
