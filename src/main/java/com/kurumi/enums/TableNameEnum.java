package com.kurumi.enums;

public enum TableNameEnum {

	/**
	 * 布尔类型
	 */
	BooleanType("布尔类型","boolean_type"),
	/**
	 * 身体部位
	 */
	BodyPart("身体部位","body_part"); 
	
	// 成员变量  
    private String tableNameDesc;  
    private String tableName;
    
	private TableNameEnum(String tableNameDesc, String tableName) {
		this.tableNameDesc = tableNameDesc;
		this.tableName = tableName;
	}

	public String getTableNameDesc() {
		return tableNameDesc;
	}

	public void setTableNameDesc(String tableNameDesc) {
		this.tableNameDesc = tableNameDesc;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}  
    
}
