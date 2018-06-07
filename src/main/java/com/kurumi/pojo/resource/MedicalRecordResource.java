package com.kurumi.pojo.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicalRecordResource {

	private String newPDFPath;
	
	private String pageIndexPDFPath;
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	
	private String pageIndexTemplatePDFPath;
	
	private String imageBasicPath;
	
	private List<Map<String,Object>> imageRecources = new ArrayList<Map<String,Object>>();
	
	private String pdfBasicPath;
	
	private List<Map<String,Object>> pdfRecources = new ArrayList<Map<String,Object>>();
	
	private String currentVersion;

	public String getNewPDFPath() {
		return newPDFPath;
	}

	public void setNewPDFPath(String newPDFPath) {
		this.newPDFPath = newPDFPath;
	}

	public String getImageBasicPath() {
		return imageBasicPath;
	}

	public void setImageBasicPath(String imageBasicPath) {
		this.imageBasicPath = imageBasicPath;
	}

	public List<Map<String, Object>> getImageRecources() {
		return imageRecources;
	}

	public void setImageRecources(List<Map<String, Object>> imageRecources) {
		this.imageRecources = imageRecources;
	}

	public String getPdfBasicPath() {
		return pdfBasicPath;
	}

	public void setPdfBasicPath(String pdfBasicPath) {
		this.pdfBasicPath = pdfBasicPath;
	}

	public List<Map<String, Object>> getPdfRecources() {
		return pdfRecources;
	}

	public void setPdfRecources(List<Map<String, Object>> pdfRecources) {
		this.pdfRecources = pdfRecources;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getPageIndexTemplatePDFPath() {
		return pageIndexTemplatePDFPath;
	}

	public void setPageIndexTemplatePDFPath(String pageIndexTemplatePDFPath) {
		this.pageIndexTemplatePDFPath = pageIndexTemplatePDFPath;
	}

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getPageIndexPDFPath() {
		return pageIndexPDFPath;
	}

	public void setPageIndexPDFPath(String pageIndexPDFPath) {
		this.pageIndexPDFPath = pageIndexPDFPath;
	}
	
	
	
	

	

	
	
}
