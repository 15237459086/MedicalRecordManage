package com.kurumi.pojo.regionalism;

/**
 * 行政区域划分
 * @author lyh
 *
 */
public class Regionalism {

	//省（自治区，直辖市）编号
	private String provinceCode;
	
	//省（自治区，直辖市）名称
	private String provinceName;
	
	//市编号
	private String cityCode;
	
	//市名称
	private String cityName;
	
	//县（地级市，区）编号
	private String countyCode;
	
	//县（地级市，区）名称
	private String countyName;
	
	//乡（镇）编号
	private String villageCode;
	
	//乡（镇）名称
	private String villageName;
	
	//详细地址
	private String detailAddress;

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
	
	
}
