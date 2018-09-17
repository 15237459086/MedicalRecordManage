package com.kurumi.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.digester.DocumentProperties.Encoding;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.json.JSONObject;
import org.json.XML;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class XMLUtil {

	public static Element getRootElement(String xmlPath) throws DocumentException{
		
        Element root = getRootElement(xmlPath,"GB2312");
        return root;
	}
	
	public static Element getRootElement(URL url) throws DocumentException{
		
        Element root = getRootElement(url,"GB2312");
        return root;
	}
	
	public static Element getRootElement(URL url,String charCode) throws DocumentException{
		SAXReader saxReader = new SAXReader();  
		saxReader.setEncoding(charCode);
        //2.获取XML文件：  
        Document doc = saxReader.read(url);
		//"F:\\test_data\\xml\\Ultimate.xml"
        //3.获取根节点：
        Element root = doc.getRootElement();
        return root;
	}
	
	public static Element getRootElement(String xmlPath,String charCode) throws DocumentException{
		SAXReader saxReader = new SAXReader();  
		saxReader.setEncoding(charCode);
        //2.获取XML文件：  
        Document doc = saxReader.read(new File(xmlPath));
		//"F:\\test_data\\xml\\Ultimate.xml"
        //3.获取根节点：
        Element root = doc.getRootElement();
        return root;
	}
	
	
	public static Element getRootElementByContent(String xmlContent) throws DocumentException{
		Document doc = DocumentHelper.parseText(xmlContent);
        //获取根节点：
        Element root = doc.getRootElement();
        return root;
	}
	
	public static boolean saveDocumentByContent(String xmlContent,String xmlPath) throws DocumentException, IOException{
		Document document = DocumentHelper.parseText(xmlContent);
		
    	OutputFormat outputFormat = new OutputFormat();
    	outputFormat.setEncoding("UTF-8");
    	XMLWriter writer = new XMLWriter(new FileWriter(new File(xmlPath)),outputFormat);
    	writer.write(document);
    	writer.close();
    	return true;
	}
	
	
	public static boolean saveDocumentByContent(String xmlContent,String xmlPath,String xmlName) throws DocumentException, IOException{
		Document document = DocumentHelper.parseText(xmlContent);
		
    	OutputFormat outputFormat = new OutputFormat();
    	outputFormat.setEncoding("UTF-8");
    	File file = new File(xmlPath,xmlName);
		if(!file.exists()){
			File destDir = new File(xmlPath);
			if(!destDir.exists()){
				destDir.mkdirs();
			}
			file.createNewFile();
		}
    	XMLWriter writer = new XMLWriter(new FileWriter(file),outputFormat);
    	writer.write(document);
    	writer.close();
    	return true;
	}
	
	public static JSONObject getJsonOjbectByXml(Element xmlElement){
		return XML.toJSONObject(xmlElement.asXML());
	}
	
	
}
