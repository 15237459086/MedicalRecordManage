package com.kurumi.enums;

public enum ProfessionTitleEnum {

	/**
	 * 科主任
	 */
	A1001("A1001","科主任"),
	/**
	 * 护士长
	 */
	A1002("A1002","护士长"),
	/**
	 * 主任医师
	 */
	A2001("A2001","主任医师"),
	/**
	 * 副主任医师
	 */
	A2002("A2002","副主任医师"),
	/**
	 * 主治医师
	 */
	A2003("A2003","主治医师"),
	/**
	 * 主诊医师
	 */
	A2004("A2004","主诊医师"),
	/**
	 * 住院医师
	 */
	A2005("A2005","住院医师"),
	/**
	 * 进修医师
	 */
	A2006("A2006","进修医师"),
	/**
	 * 研究生实习医师
	 */
	A2007("A2007","研究生实习医师"),
	/**
	 * 实习医师
	 */
	A2008("A2008","实习医师"),
	/**
	 * 质控医师
	 */
	A2009("A2009","质控医师"),
	/**
	 * 责任医师
	 */
	A2010("A2010","责任医师"),
	/**
	 * 责任护士
	 */
	A3001("A3001","责任护士"),
	/**
	 * 实习护士
	 */
	A3002("A3002","实习护士"),
	/**
	 * 质控护士
	 */
	A3003("A3003","质控护士"),
	/**
	 * 编码员
	 */
	A4001("A4001","编码员"),
	/**
	 * 手术医师
	 */
	A5001("A5001","手术医师"),
	/**
	 * 麻醉医师
	 */
	A5002("A5002","麻醉医师"),
	/**
	 * 第一助手
	 */
	A5003("A4003","第一助手"),
	/**
	 * 第二助手
	 */
	A5004("A4004","第二助手");
	
	// 成员变量  
    private String professionTitleCode;  
    private String professionTitleName;
    
	private ProfessionTitleEnum(String professionTitleCode, String professionTitleName) {
		this.professionTitleCode = professionTitleCode;
		this.professionTitleName = professionTitleName;
	}

	public String getProfessionTitleCode() {
		return professionTitleCode;
	}

	public String getProfessionTitleName() {
		return professionTitleName;
	}

    
    
}
