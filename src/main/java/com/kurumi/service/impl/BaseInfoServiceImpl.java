package com.kurumi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurumi.mapper.BaseInfoMapper;
import com.kurumi.service.BaseInfoService;

@Service
public class BaseInfoServiceImpl implements BaseInfoService {

	private String hospitalCode = "49557184-0";
	
	@Autowired
	private BaseInfoMapper baseInfoMapper;
	
	@Override
	public List<Map<String, Object>> getHospitalByCode(String hospitalCode) {
		// TODO Auto-generated method stub
		return baseInfoMapper.getHospitalByCode(hospitalCode);
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseDataOfBasicInfo() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		
		List<Map<String, Object>> medicalPayMentTypes = baseInfoMapper.getMedicalPaymentTypes();
		
		List<Map<String, Object>> sexs = baseInfoMapper.getSexs();
		
		List<Map<String, Object>> marriages = baseInfoMapper.getMarriages();
		
		baseInfo.put("medicalPayMentTypes", medicalPayMentTypes);
		baseInfo.put("sexs", sexs);
		baseInfo.put("marriages", marriages);
		
		List<Map<String, Object>> idDocumentTypes = baseInfoMapper.getIdDocumentTypes();
		baseInfo.put("idDocumentTypes", idDocumentTypes);
		
		List<Map<String, Object>> nationalitys = baseInfoMapper.getNationalitys();
		baseInfo.put("nationalitys", nationalitys);
		
		List<Map<String, Object>> nations = baseInfoMapper.getNations();
		baseInfo.put("nations", nations);
		
		List<Map<String, Object>> professions = baseInfoMapper.getProfessions();
		baseInfo.put("professions", professions);
		
		List<Map<String, Object>> relativeRelations = baseInfoMapper.getRelativeRelations();
		baseInfo.put("relativeRelations", relativeRelations);
		
		List<Map<String, Object>> inHospitalTypes = baseInfoMapper.getInHospitalTypes();
		baseInfo.put("inHospitalTypes", inHospitalTypes);
		
		List<Map<String, Object>> inHospitalStates = baseInfoMapper.getInHospitalStates();
		baseInfo.put("inHospitalStates", inHospitalStates);
		
		List<Map<String, Object>> outHospitalTypes = baseInfoMapper.getOutHospitalTypes();
		baseInfo.put("outHospitalTypes", outHospitalTypes);
		
		List<Map<String, Object>> rehospitalAims = baseInfoMapper.getRehospitalAims();
		baseInfo.put("rehospitalAims", rehospitalAims);
		
		List<Map<String, Object>> hospitalDealthReasons = baseInfoMapper.getHospitalDealthReasons();
		baseInfo.put("hospitalDealthReasons", hospitalDealthReasons);
		
		List<Map<String, Object>> hospitals = baseInfoMapper.getHospitalByCode(this.hospitalCode);
		List<Map<String, Object>> medicalDepts = new ArrayList<Map<String,Object>>();
		if(!hospitals.isEmpty()){
			medicalDepts = baseInfoMapper.getMedicalDeptByHospitalId(((Integer)hospitals.get(0).get("id")));
		}
		baseInfo.put("hospitals", hospitals);
		baseInfo.put("medicalDepts", medicalDepts);
		return baseInfo;
	}

	
	@Override
	public List<Map<String, Object>> getDiseaseByQueryName(String queryName) {
		// TODO Auto-generated method stub
		return baseInfoMapper.getDiseaseByQueryName("%"+queryName+"%");
	}

	@Override
	public List<Map<String, Object>> getMedicalWorkerByQueryName(
			String queryName) {
		// TODO Auto-generated method stub
		return baseInfoMapper.getMedicalWorkerByQueryName("%"+queryName+"%");
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseDataOfDiagInfo() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		List<Map<String, Object>> diagTypes = baseInfoMapper.getDiagTypes();
		baseInfo.put("diagTypes", diagTypes);
		
		List<Map<String, Object>> treatmentResults = baseInfoMapper.getTreatmentResults();
		baseInfo.put("treatmentResults", treatmentResults);
		return baseInfo;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseDataOfCureInfo() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		List<Map<String, Object>> bloodTypes = baseInfoMapper.getBloodTypes();
		baseInfo.put("bloodTypes", bloodTypes);
		
		List<Map<String, Object>> rhBloodTypes = baseInfoMapper.getRhBloodTypes();
		baseInfo.put("rhBloodTypes", rhBloodTypes);
		
		List<Map<String, Object>> diagAccordTypes = baseInfoMapper.getDiagAccordTypes();
		baseInfo.put("diagAccordTypes", diagAccordTypes);
		return baseInfo;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseDataOfOperateInfo() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		List<Map<String, Object>> incisionLevels = baseInfoMapper.getIncisionLevels();
		baseInfo.put("incisionLevels", incisionLevels);
		List<Map<String, Object>> cicatrizeTypes = baseInfoMapper.getCicatrizeTypes();
		baseInfo.put("cicatrizeTypes", cicatrizeTypes);
		List<Map<String, Object>> opsLevels = baseInfoMapper.getOpsLevels();
		baseInfo.put("opsLevels", opsLevels);
		
		
		List<Map<String, Object>> anaesthesiaWays = baseInfoMapper.getAnaesthesiaWays();
		baseInfo.put("anaesthesiaWays", anaesthesiaWays);
		
		
		List<Map<String, Object>> anaesthesiaLevels = baseInfoMapper.getAnaesthesiaLevels();
		baseInfo.put("anaesthesiaLevels", anaesthesiaLevels);
		return baseInfo;
	}

	@Override
	public List<Map<String, Object>> getOperateByQueryName(String queryName) {
		// TODO Auto-generated method stub
		return baseInfoMapper.getOperateByQueryName("%"+queryName+"%");
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseInfoOfHomePageTransferDept() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		
		List<Map<String, Object>> hospitals = baseInfoMapper.getHospitalByCode(this.hospitalCode);
		List<Map<String, Object>> medicalDepts = new ArrayList<Map<String,Object>>();
		if(!hospitals.isEmpty()){
			medicalDepts = baseInfoMapper.getMedicalDeptByHospitalId(((Integer)hospitals.get(0).get("id")));
		}
		baseInfo.put("hospitals", hospitals);
		baseInfo.put("medicalDepts", medicalDepts);
		return baseInfo;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseInfoOfHomePageInfusionBlood() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		List<Map<String, Object>> bloodTypes = baseInfoMapper.getBloodTypes();
		baseInfo.put("bloodTypes", bloodTypes);
		return baseInfo;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseInfoOfHomePageIntensiveCare() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		List<Map<String, Object>> ICUTypes = baseInfoMapper.getICUTypes();
		baseInfo.put("ICUTypes", ICUTypes);
		return baseInfo;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseInfoOfHomePageAllergyDrug() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		
		List<Map<String, Object>> allergyDrugTypes = baseInfoMapper.getAllergyDrugTypes();
		baseInfo.put("allergyDrugTypes", allergyDrugTypes);
		return baseInfo;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseInfoOfHomePagePressureSore() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		List<Map<String, Object>> pressureSoreCradles = baseInfoMapper.getPressureSoreCradles();
		baseInfo.put("pressureSoreCradles", pressureSoreCradles);
		
		List<Map<String, Object>> pressureSorePhases = baseInfoMapper.getPressureSorePhases();
		baseInfo.put("pressureSorePhases", pressureSorePhases);
		
		List<Map<String, Object>> pressureSoreParts = baseInfoMapper.getPressureSoreParts();
		baseInfo.put("pressureSoreParts", pressureSoreParts);
		return baseInfo;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseInfoOfHomePageInfection() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		
		List<Map<String, Object>> pathogens = baseInfoMapper.getPathogens();
		baseInfo.put("pathogens", pathogens);
		
		List<Map<String, Object>> pathogenyCheckSpecimens = baseInfoMapper.getPathogenyCheckSpecimens();
		baseInfo.put("pathogenyCheckSpecimens", pathogenyCheckSpecimens);
		
		List<Map<String, Object>> predisposeFactors = baseInfoMapper.getPredisposeFactors();
		baseInfo.put("predisposeFactors", predisposeFactors);
		return baseInfo;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseInfoOfHomePageAntibacterialDrug() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		
		List<Map<String, Object>> pathogens = baseInfoMapper.getPathogens();
		baseInfo.put("pathogens", pathogens);
		List<Map<String, Object>> pathogenyCheckSpecimens = baseInfoMapper.getPathogenyCheckSpecimens();
		baseInfo.put("pathogenyCheckSpecimens", pathogenyCheckSpecimens);
		
		List<Map<String, Object>> antibacterialDrugPurposes = baseInfoMapper.getAntibacterialDrugPurposes();
		baseInfo.put("antibacterialDrugPurposes", antibacterialDrugPurposes);
		List<Map<String, Object>> antibacterialDrugClassificatorys = baseInfoMapper.getAntibacterialDrugClassificatorys();
		baseInfo.put("antibacterialDrugClassificatorys",antibacterialDrugClassificatorys);
		
		List<Map<String, Object>> antibacterialDrugRegimens = baseInfoMapper.getAntibacterialDrugRegimens();
		baseInfo.put("antibacterialDrugRegimens", antibacterialDrugRegimens);
		
		return baseInfo;
	}

	@Override
	public List<Map<String, Object>> getMedicalDepts() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> hospitals = baseInfoMapper.getHospitalByCode(this.hospitalCode);
		List<Map<String, Object>> medicalDepts = new ArrayList<Map<String,Object>>();
		if(!hospitals.isEmpty()){
			medicalDepts = baseInfoMapper.getMedicalDeptByHospitalId(((Integer)hospitals.get(0).get("id")));
		}
		return medicalDepts;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseInfoOfUnPigeonhole() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		List<Map<String, Object>> hospitals = baseInfoMapper.getHospitalByCode(this.hospitalCode);
		List<Map<String, Object>> medicalDepts = new ArrayList<Map<String,Object>>();
		if(!hospitals.isEmpty()){
			medicalDepts = baseInfoMapper.getMedicalDeptByHospitalId(((Integer)hospitals.get(0).get("id")));
		}
		baseInfo.put("hospitals", hospitals);
		baseInfo.put("medicalDepts", medicalDepts);
		List<Map<String, Object>> outHospitalTypes = baseInfoMapper.getOutHospitalTypes();
		baseInfo.put("outHospitalTypes", outHospitalTypes);
		
		return baseInfo;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseInfoOfQualityControl() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		List<Map<String, Object>> qualityControlPoints = baseInfoMapper.getQualityControlOfFirstLevel();
		baseInfo.put("qualityControlPoints", qualityControlPoints);
		return baseInfo;
	}

	@Override
	public List<Map<String, Object>> getQualityControlOfFirstLevel() {
		// TODO Auto-generated method stub
		return baseInfoMapper.getQualityControlOfFirstLevel();
	}

	@Override
	public List<Map<String, Object>> getQualityControlByUpOneLevelCode(String upOneLevelCode) {
		// TODO Auto-generated method stub
		return baseInfoMapper.getQualityControlByUpOneLevelCode(String.format("%s__", upOneLevelCode));
	}

	/**
	 * 获取标签类型
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getMrPageTypes() {
		// TODO Auto-generated method stub
		return baseInfoMapper.getMrPageTypes();
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseInfoOfPrint() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		List<Map<String, Object>> hospitals = baseInfoMapper.getHospitalByCode(this.hospitalCode);
		List<Map<String, Object>> medicalDepts = new ArrayList<Map<String,Object>>();
		if(!hospitals.isEmpty()){
			medicalDepts = baseInfoMapper.getMedicalDeptByHospitalId(((Integer)hospitals.get(0).get("id")));
		}
		
		baseInfo.put("hospitals", hospitals);
		baseInfo.put("medicalDepts", medicalDepts);
		List<Map<String, Object>> outHospitalTypes = baseInfoMapper.getOutHospitalTypes();
		baseInfo.put("outHospitalTypes", outHospitalTypes);
		List<Map<String, Object>> relativeRelations = baseInfoMapper.getRelativeRelations();
		baseInfo.put("relativeRelations", relativeRelations);
		List<Map<String, Object>> printerTypes = baseInfoMapper.getPrinterTypes();
		baseInfo.put("printerTypes", printerTypes);
		return baseInfo;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBaseInfoOfBorrow() {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> baseInfo = new HashMap<String, List<Map<String,Object>>>();
		List<Map<String, Object>> hospitals = baseInfoMapper.getHospitalByCode(this.hospitalCode);
		List<Map<String, Object>> medicalDepts = new ArrayList<Map<String,Object>>();
		if(!hospitals.isEmpty()){
			medicalDepts = baseInfoMapper.getMedicalDeptByHospitalId(((Integer)hospitals.get(0).get("id")));
		}
		
		baseInfo.put("hospitals", hospitals);
		baseInfo.put("medicalDepts", medicalDepts);
		List<Map<String, Object>> outHospitalTypes = baseInfoMapper.getOutHospitalTypes();
		baseInfo.put("outHospitalTypes", outHospitalTypes);
		List<Map<String, Object>> relativeRelations = baseInfoMapper.getRelativeRelations();
		baseInfo.put("relativeRelations", relativeRelations);
		List<Map<String, Object>> printerTypes = baseInfoMapper.getPrinterTypes();
		baseInfo.put("printerTypes", printerTypes);
		return baseInfo;
	}
}
