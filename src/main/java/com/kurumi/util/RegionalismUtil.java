package com.kurumi.util;

import java.net.URL;

import org.dom4j.DocumentException;
import org.dom4j.Element;

public class RegionalismUtil {

	private static Element regionalismElement;
	
	public static Element getRegionalismElement(URL url,String charCode) throws DocumentException{
		if(regionalismElement == null){
			regionalismElement = XMLUtil.getRootElement(url, charCode);
		}
		
		return regionalismElement;
	}
}
