package com.kurumi.pojo.coding;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 孕产记录
 * @author lyh
 *
 */
public class MotherhoodRecord {

	/**
	 * 孕周
	 */
	private BigDecimal gestationalWeek;
	
	/**
	 * 胎位Code
	 */
	private String positionOfFetusCode;
	
	/**
	 * 胎位Name
	 */
	private String positionOfFetusName;
	
	/**
	 * 分娩方式Code
	 */
	private String deliveryModeCode ;
	
	/**
	 * 分娩方式Name
	 */
	private String deliveryModeName;
	
	/**
	 * 新生儿性别Code
	 */
	private String babySexCode;
	
	/**
	 * 新生儿性别Name
	 */
	private String babySexName;
	
	/**
	 * 新生儿AGP评分
	 */
	private BigDecimal babyAGPScore;
	
	/**
	 * 新生儿出生日期
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date babyBirthDateTime;
	
	/**
	 * 新生儿死亡日期
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date babyDeathDateTime;
	
	/**
	 * 新生儿出生体重
	 */
	private BigDecimal weightOfbabyBirth;
	
	/**
	 * 新生儿出生身高
	 */
	private BigDecimal heightOfbabyBirth;
	
	/**
	 * 是否有产伤Code
	 */
	private String isBirthInjuryCode;
	
	/**
	 * 是否有产伤Name
	 */
	private String isBirthInjuryName;
	
	/**
	 * 是否听力筛查Code
	 */
	private String isHearingScreeningCode;
	
	/**
	 * 是否听力筛查Name
	 */
	private String isHearingScreeningName;
	
	/**
	 * 是否甲状腺筛查Code
	 */
	private String isThyroidScreeningCode;
	
	/**
	 * 是否甲状腺筛查Name
	 */
	private String isThyroidScreeningBName;
	
	/**
	 * 是否苯丙酮尿酸检查Code
	 */
	private String isAcetoneTestCode;
	
	/**
	 * 是否苯丙酮尿酸检查Name
	 */
	private String isAcetoneTestName;
	
	/**
	 * 是否唐氏综合症筛查Code
	 */
	private String isOscarScreeningCode;
	
	/**
	 * 是否苯丙酮尿酸检查Name
	 */
	/**
	 * 是否唐氏综合症筛查Name
	 */
	private String isOscarScreeningName;
	
	/**
	 * 是否卡介苗预防接种Code
	 */
	private String isBcgVaccinationCode;
	
	/**
	 * 是否卡介苗预防接种Name
	 */
	private String isBcgVaccinationName;
	
	/**
	 * 是否乙肝疫苗接种Code
	 */
	private String isHepatitisVaccinationCode;
	
	/**
	 * 是否乙肝疫苗接种Name
	 */
	private String isHepatitisVaccinationName;
	
	/**
	 * 是否视网膜筛查Code
	 */
	private String isRetinalScreeningCode;
	
	/**
	 * 是否视网膜筛查Name
	 */
	private String isRetinalScreeningName;
	
	/**
	 * 是否氢孕酮检测Code
	 */
	private String isHydroprogesteroneTestCode;
	
	/**
	 * 是否氢孕酮检测Name
	 */
	private String isHydroprogesteroneTestName;
	
	/**
	 * 是否葡萄糖磷酸检测Code
	 */
	private String isGlucosePhosphateTestCode;
	
	/**
	 * 是否葡萄糖磷酸检测Name
	 */
	private String isGlucosePhosphateTestName;
	
	/**
	 * 是否脱氢酶检查Code
	 */
	private String isDehydrogenaseTestCode;
	
	/**
	 * 是否脱氢酶检查Name
	 */
	private String isDehydrogenaseTestName;
	
	/**
	 * 是否串联质朴检测Code
	 */
	private String isGuestMassSpectrometryCode;
	
	/**
	 * 是否串联质朴检测Name
	 */
	private String isGuestMassSpectrometryName;
	
	/**
	 * 是否产后出血Code
	 */
	private String isPostpartumHemorrhageCode;
	
	/**
	 * 是否产后出血Name
	 */
	private String isPostpartumHemorrhageName;
	
	/**
	 * 新生儿疾病原始Code
	 */
	private String babyDiseaseOriginalCode;
	
	/**
	 * 新生儿疾病原始Name
	 */
	private String babyDiseaseOriginalName;
	
	/**
	 * 新生儿疾病Code
	 */
	private String babyDiseaseCode;
	
	/**
	 * 新生儿疾病Name
	 */
	private String babyDiseaseName;
	
	/**
	 * 接生者Code
	 */
	private String midwifeCode;
	
	/**
	 * 接生者Name
	 */
	private String midwifeName;

	public BigDecimal getGestationalWeek() {
		return gestationalWeek;
	}

	public void setGestationalWeek(BigDecimal gestationalWeek) {
		this.gestationalWeek = gestationalWeek;
	}

	public String getPositionOfFetusCode() {
		return positionOfFetusCode;
	}

	public void setPositionOfFetusCode(String positionOfFetusCode) {
		this.positionOfFetusCode = positionOfFetusCode;
	}

	public String getPositionOfFetusName() {
		return positionOfFetusName;
	}

	public void setPositionOfFetusName(String positionOfFetusName) {
		this.positionOfFetusName = positionOfFetusName;
	}

	public String getDeliveryModeCode() {
		return deliveryModeCode;
	}

	public void setDeliveryModeCode(String deliveryModeCode) {
		this.deliveryModeCode = deliveryModeCode;
	}

	public String getDeliveryModeName() {
		return deliveryModeName;
	}

	public void setDeliveryModeName(String deliveryModeName) {
		this.deliveryModeName = deliveryModeName;
	}

	public String getBabySexCode() {
		return babySexCode;
	}

	public void setBabySexCode(String babySexCode) {
		this.babySexCode = babySexCode;
	}

	public String getBabySexName() {
		return babySexName;
	}

	public void setBabySexName(String babySexName) {
		this.babySexName = babySexName;
	}

	public BigDecimal getBabyAGPScore() {
		return babyAGPScore;
	}

	public void setBabyAGPScore(BigDecimal babyAGPScore) {
		this.babyAGPScore = babyAGPScore;
	}

	public Date getBabyBirthDateTime() {
		return babyBirthDateTime;
	}

	public void setBabyBirthDateTime(Date babyBirthDateTime) {
		this.babyBirthDateTime = babyBirthDateTime;
	}

	public Date getBabyDeathDateTime() {
		return babyDeathDateTime;
	}

	public void setBabyDeathDateTime(Date babyDeathDateTime) {
		this.babyDeathDateTime = babyDeathDateTime;
	}

	public BigDecimal getWeightOfbabyBirth() {
		return weightOfbabyBirth;
	}

	public void setWeightOfbabyBirth(BigDecimal weightOfbabyBirth) {
		this.weightOfbabyBirth = weightOfbabyBirth;
	}

	public BigDecimal getHeightOfbabyBirth() {
		return heightOfbabyBirth;
	}

	public void setHeightOfbabyBirth(BigDecimal heightOfbabyBirth) {
		this.heightOfbabyBirth = heightOfbabyBirth;
	}

	public String getIsBirthInjuryCode() {
		return isBirthInjuryCode;
	}

	public void setIsBirthInjuryCode(String isBirthInjuryCode) {
		this.isBirthInjuryCode = isBirthInjuryCode;
	}

	public String getIsBirthInjuryName() {
		return isBirthInjuryName;
	}

	public void setIsBirthInjuryName(String isBirthInjuryName) {
		this.isBirthInjuryName = isBirthInjuryName;
	}

	public String getIsHearingScreeningCode() {
		return isHearingScreeningCode;
	}

	public void setIsHearingScreeningCode(String isHearingScreeningCode) {
		this.isHearingScreeningCode = isHearingScreeningCode;
	}

	public String getIsHearingScreeningName() {
		return isHearingScreeningName;
	}

	public void setIsHearingScreeningName(String isHearingScreeningName) {
		this.isHearingScreeningName = isHearingScreeningName;
	}

	public String getIsThyroidScreeningCode() {
		return isThyroidScreeningCode;
	}

	public void setIsThyroidScreeningCode(String isThyroidScreeningCode) {
		this.isThyroidScreeningCode = isThyroidScreeningCode;
	}

	public String getIsThyroidScreeningBName() {
		return isThyroidScreeningBName;
	}

	public void setIsThyroidScreeningBName(String isThyroidScreeningBName) {
		this.isThyroidScreeningBName = isThyroidScreeningBName;
	}

	public String getIsOscarScreeningCode() {
		return isOscarScreeningCode;
	}

	public void setIsOscarScreeningCode(String isOscarScreeningCode) {
		this.isOscarScreeningCode = isOscarScreeningCode;
	}

	public String getIsOscarScreeningName() {
		return isOscarScreeningName;
	}

	public void setIsOscarScreeningName(String isOscarScreeningName) {
		this.isOscarScreeningName = isOscarScreeningName;
	}

	public String getIsBcgVaccinationCode() {
		return isBcgVaccinationCode;
	}

	public void setIsBcgVaccinationCode(String isBcgVaccinationCode) {
		this.isBcgVaccinationCode = isBcgVaccinationCode;
	}

	public String getIsBcgVaccinationName() {
		return isBcgVaccinationName;
	}

	public void setIsBcgVaccinationName(String isBcgVaccinationName) {
		this.isBcgVaccinationName = isBcgVaccinationName;
	}

	public String getIsHepatitisVaccinationCode() {
		return isHepatitisVaccinationCode;
	}

	public void setIsHepatitisVaccinationCode(String isHepatitisVaccinationCode) {
		this.isHepatitisVaccinationCode = isHepatitisVaccinationCode;
	}

	public String getIsHepatitisVaccinationName() {
		return isHepatitisVaccinationName;
	}

	public void setIsHepatitisVaccinationName(String isHepatitisVaccinationName) {
		this.isHepatitisVaccinationName = isHepatitisVaccinationName;
	}

	public String getIsRetinalScreeningCode() {
		return isRetinalScreeningCode;
	}

	public void setIsRetinalScreeningCode(String isRetinalScreeningCode) {
		this.isRetinalScreeningCode = isRetinalScreeningCode;
	}

	public String getIsRetinalScreeningName() {
		return isRetinalScreeningName;
	}

	public void setIsRetinalScreeningName(String isRetinalScreeningName) {
		this.isRetinalScreeningName = isRetinalScreeningName;
	}

	public String getIsHydroprogesteroneTestCode() {
		return isHydroprogesteroneTestCode;
	}

	public void setIsHydroprogesteroneTestCode(String isHydroprogesteroneTestCode) {
		this.isHydroprogesteroneTestCode = isHydroprogesteroneTestCode;
	}

	public String getIsHydroprogesteroneTestName() {
		return isHydroprogesteroneTestName;
	}

	public void setIsHydroprogesteroneTestName(String isHydroprogesteroneTestName) {
		this.isHydroprogesteroneTestName = isHydroprogesteroneTestName;
	}

	public String getIsGlucosePhosphateTestCode() {
		return isGlucosePhosphateTestCode;
	}

	public void setIsGlucosePhosphateTestCode(String isGlucosePhosphateTestCode) {
		this.isGlucosePhosphateTestCode = isGlucosePhosphateTestCode;
	}

	public String getIsGlucosePhosphateTestName() {
		return isGlucosePhosphateTestName;
	}

	public void setIsGlucosePhosphateTestName(String isGlucosePhosphateTestName) {
		this.isGlucosePhosphateTestName = isGlucosePhosphateTestName;
	}

	public String getIsDehydrogenaseTestCode() {
		return isDehydrogenaseTestCode;
	}

	public void setIsDehydrogenaseTestCode(String isDehydrogenaseTestCode) {
		this.isDehydrogenaseTestCode = isDehydrogenaseTestCode;
	}

	public String getIsDehydrogenaseTestName() {
		return isDehydrogenaseTestName;
	}

	public void setIsDehydrogenaseTestName(String isDehydrogenaseTestName) {
		this.isDehydrogenaseTestName = isDehydrogenaseTestName;
	}

	public String getIsGuestMassSpectrometryCode() {
		return isGuestMassSpectrometryCode;
	}

	public void setIsGuestMassSpectrometryCode(String isGuestMassSpectrometryCode) {
		this.isGuestMassSpectrometryCode = isGuestMassSpectrometryCode;
	}

	public String getIsGuestMassSpectrometryName() {
		return isGuestMassSpectrometryName;
	}

	public void setIsGuestMassSpectrometryName(String isGuestMassSpectrometryName) {
		this.isGuestMassSpectrometryName = isGuestMassSpectrometryName;
	}

	public String getIsPostpartumHemorrhageCode() {
		return isPostpartumHemorrhageCode;
	}

	public void setIsPostpartumHemorrhageCode(String isPostpartumHemorrhageCode) {
		this.isPostpartumHemorrhageCode = isPostpartumHemorrhageCode;
	}

	public String getIsPostpartumHemorrhageName() {
		return isPostpartumHemorrhageName;
	}

	public void setIsPostpartumHemorrhageName(String isPostpartumHemorrhageName) {
		this.isPostpartumHemorrhageName = isPostpartumHemorrhageName;
	}

	public String getBabyDiseaseOriginalCode() {
		return babyDiseaseOriginalCode;
	}

	public void setBabyDiseaseOriginalCode(String babyDiseaseOriginalCode) {
		this.babyDiseaseOriginalCode = babyDiseaseOriginalCode;
	}

	public String getBabyDiseaseOriginalName() {
		return babyDiseaseOriginalName;
	}

	public void setBabyDiseaseOriginalName(String babyDiseaseOriginalName) {
		this.babyDiseaseOriginalName = babyDiseaseOriginalName;
	}

	public String getBabyDiseaseCode() {
		return babyDiseaseCode;
	}

	public void setBabyDiseaseCode(String babyDiseaseCode) {
		this.babyDiseaseCode = babyDiseaseCode;
	}

	public String getBabyDiseaseName() {
		return babyDiseaseName;
	}

	public void setBabyDiseaseName(String babyDiseaseName) {
		this.babyDiseaseName = babyDiseaseName;
	}

	public String getMidwifeCode() {
		return midwifeCode;
	}

	public void setMidwifeCode(String midwifeCode) {
		this.midwifeCode = midwifeCode;
	}

	public String getMidwifeName() {
		return midwifeName;
	}

	public void setMidwifeName(String midwifeName) {
		this.midwifeName = midwifeName;
	}

	public String getIsAcetoneTestCode() {
		return isAcetoneTestCode;
	}

	public void setIsAcetoneTestCode(String isAcetoneTestCode) {
		this.isAcetoneTestCode = isAcetoneTestCode;
	}

	public String getIsAcetoneTestName() {
		return isAcetoneTestName;
	}

	public void setIsAcetoneTestName(String isAcetoneTestName) {
		this.isAcetoneTestName = isAcetoneTestName;
	}
	
	
	
}
