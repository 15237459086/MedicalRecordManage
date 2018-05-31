package com.kurumi.thread;

import java.io.IOException;


import com.itextpdf.text.DocumentException;
import com.kurumi.pojo.resource.MedicalRecordResource;
import com.kurumi.util.PDFUtil;


public class PageIndexPDFThread extends Thread {
	
	private MedicalRecordResource medicalRecordResource;
	
	public PageIndexPDFThread(MedicalRecordResource medicalRecordResource){
		this.medicalRecordResource = medicalRecordResource;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(medicalRecordResource != null){
			try {
				PDFUtil.createPDFByTemplate(medicalRecordResource.getDataMap(),medicalRecordResource.getPageIndexTemplatePDFPath(),medicalRecordResource.getNewPDFPath());
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}
