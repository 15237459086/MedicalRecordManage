package com.kurumi.pojo.pdf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicalRecordResource {

	private String newPDFPath;
	
	private List<String> sourcePaths = new ArrayList<String>();
	
	private List<Map<String,Object>> sourceFiles =  new ArrayList<Map<String,Object>>();
	
	private Map<String, String> sourceBasicPaths = new HashMap<String, String>();
	
	public List<String> getSourcePaths() {
		return sourcePaths;
	}

	public void setSourcePaths(List<String> sourcePaths) {
		this.sourcePaths = sourcePaths;
	}

	public String getNewPDFPath() {
		return newPDFPath;
	}

	public void setNewPDFPath(String newPDFPath) {
		this.newPDFPath = newPDFPath;
	}

	public List<Map<String, Object>> getSourceFiles() {
		return sourceFiles;
	}

	public void setSourceFiles(List<Map<String, Object>> sourceFiles) {
		this.sourceFiles = sourceFiles;
	}

	public Map<String, String> getSourceBasicPaths() {
		return sourceBasicPaths;
	}

	public void setSourceBasicPaths(Map<String, String> sourceBasicPaths) {
		this.sourceBasicPaths = sourceBasicPaths;
	}

	

	
	
}
