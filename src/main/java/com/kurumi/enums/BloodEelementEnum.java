package com.kurumi.enums;

public enum BloodEelementEnum {

	/**
	 * "红细胞
	 */
	B0001("红细胞","B0001"),
	/**
	 * 白细胞
	 */
	B0002("白细胞","B0002"),
	/**
	 * 血小板
	 */
	B0003("血小板","B0003"),
	/**
	 * 血浆
	 */
	B0004("血浆","B0004"),
	/**
	 * 全血
	 */
	B0005("全血","B0005"),
	/**
	 * 全血
	 */
	B0006("自体血","B0006"),
	/**
	 * 其他
	 */
	B9999("其他","B9999"); 
	
	// 成员变量  
    private String enumDesc;  
    private String enumName;
    
	private BloodEelementEnum(String enumDesc, String enumName) {
		this.enumDesc = enumDesc;
		this.enumName = enumName;
	}

	public String getEnumDesc() {
		return enumDesc;
	}

	public void setEnumDesc(String enumDesc) {
		this.enumDesc = enumDesc;
	}

	public String getEnumName() {
		return enumName;
	}

	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}

	
    
}
