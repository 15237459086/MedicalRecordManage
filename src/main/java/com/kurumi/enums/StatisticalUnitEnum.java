package com.kurumi.enums;

public enum StatisticalUnitEnum {

	/**
	 * 毫升
	 */
	ml("毫升","ml"),
	/**
	 * 单位
	 */
	unit("单位","unit"),
	/**
	 * 袋
	 */
	bag("袋","bag"),
	/**
	 * 袋
	 */
	g("克","g"),
	/**
	 * 袋
	 */
	kg("千克","kg"); 
	
	// 成员变量  
    private String unitNameDesc;  
    private String unitName;
    
	private StatisticalUnitEnum(String unitNameDesc, String unitName) {
		this.unitNameDesc = unitNameDesc;
		this.unitName = unitName;
	}

	public String getUnitNameDesc() {
		return unitNameDesc;
	}

	public void setUnitNameDesc(String unitNameDesc) {
		this.unitNameDesc = unitNameDesc;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	
}
