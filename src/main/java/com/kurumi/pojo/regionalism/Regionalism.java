package com.kurumi.pojo.regionalism;

import java.net.URL;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;

import com.kurumi.util.RegionalismUtil;
import com.kurumi.util.StringUtil;
import com.kurumi.util.XMLUtil;

/**
 * 行政区域划分
 * @author lyh
 *
 */
public class Regionalism {

	//省/自治区/直辖市/特别行政区编号
	private String provinceCode;
	
	//省/自治区/直辖市/特别行政区名称
	private String provinceName;
	
	//地级市/自治州/盟编号
	private String cityCode;
	
	//地级市/自治州/盟名称
	private String cityName;
	
	//市辖区/县/自治县/县级市/旗/自治旗/林区/特区编号
	private String countyCode;
	
	//市辖区/县/自治县/县级市/旗/自治旗/林区/特区名称
	private String countyName;
	
	//镇/乡/民族乡/街道/苏木/民族苏木/区公所编号
	private String townCode;
	
	//镇/乡/民族乡/街道/苏木/民族苏木/区公所名称
	private String townName;
	
	//行政村/社区/居/嘎査编号
	private String villageCode;
	
	//行政村/社区/居/嘎査名称
	private String villageName;
	
	//自然村村民小组/社区居民小组编号
	private String groupCode;
	
	//自然村村民小组/社区居民小组名称
	private String groupName;
	
	//额外地址描述
	private String extraAddressDesc;
	
	//省市县三级描述
	private String provinceCityCountyName;

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

	public String getTownCode() {
		return townCode;
	}

	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getExtraAddressDesc() {
		return extraAddressDesc;
	}

	public void setExtraAddressDesc(String extraAddressDesc) {
		this.extraAddressDesc = extraAddressDesc;
	}

	public String getProvinceCityCountyName() {
		return StringUtil.meaningStr(provinceCityCountyName);
	}

	public void setProvinceCityCountyName(String provinceCityCountyName) {
		this.provinceCityCountyName = provinceCityCountyName;
	}

	
	public void initProvinceCityCountyCode(){
		if(this.getProvinceCityCountyName() != null){
			String[] provinceCityCountys = this.getProvinceCityCountyName().split("-");
			if(provinceCityCountys.length == 3){
				ClassLoader classLoader = XMLUtil.class.getClassLoader();
				URL resource = classLoader.getResource("LocList.xml");
				try {
					Element regionalismElement = RegionalismUtil.getRegionalismElement(resource, "UTF-8");
					String provinceName = provinceCityCountys[0];
					String cityName = provinceCityCountys[1];
					String countyName = provinceCityCountys[2];
					String provinceCode = null;
					String cityCode = null;
					String countyCode = null;
					Node provinceNode = regionalismElement.selectSingleNode("//CountryRegion//State[contains('"+provinceName+"',@Name)]");
					if(provinceNode != null){
						Element provinceElement = (Element)provinceNode;
						provinceCode = provinceElement.attribute("Code").getValue();
						this.setProvinceCode(provinceCode);
						this.setProvinceName(provinceName);
					}
					if(cityName.contains("北京") || cityName.contains("天津") 
							|| cityName.contains("上海") || cityName.contains("重庆")){
						Node cityNode = regionalismElement.selectSingleNode("//CountryRegion//State[contains('"+cityName+"',@Name)]");
						if(cityNode != null){
							Element cityElement = (Element)cityNode;
							cityCode = cityElement.attribute("Code").getValue();
							this.setCityCode(provinceCode);
							this.setCityName(provinceName);
							Node countyNode = regionalismElement.selectSingleNode("//CountryRegion//State[@Code='"+cityCode+"']//City[contains('"+countyName+"',@Name)]");
							if(countyNode != null){
								Element countyElement = (Element)countyNode;
								countyCode = countyElement.attribute("Code").getValue();
								this.setCountyCode(countyCode);
								this.setCountyName(countyName);
							}
						}
					}else{
						Node cityNode = regionalismElement.selectSingleNode("//CountryRegion//State[@Code='"+provinceCode+"']//City[contains('"+cityName+"',@Name)]");
						if(cityNode != null){
							Element cityElement = (Element)cityNode;
							cityCode = cityElement.attribute("Code").getValue();
							this.setCityCode(cityCode);
							this.setCityName(cityName);
							
							Node countyNode = regionalismElement.selectSingleNode("//CountryRegion//State[@Code='"+provinceCode+"']//City[@Code='"+cityCode+"']//Region[contains('"+countyName+"',@Name)]");
							if(countyNode != null){
								Element countyElement = (Element)countyNode;
								countyCode = countyElement.attribute("Code").getValue();
								this.setCountyCode(countyCode);
								this.setCountyName(countyName);
							}
						}
					}
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
