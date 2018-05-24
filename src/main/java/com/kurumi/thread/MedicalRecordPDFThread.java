package com.kurumi.thread;

import java.io.IOException;


import com.itextpdf.text.DocumentException;
import com.kurumi.pojo.pdf.MedicalRecordResource;
import com.kurumi.util.PDFUtil;


public class MedicalRecordPDFThread extends Thread {

	
	
	private MedicalRecordResource medicalRecordResource;
	
	public MedicalRecordPDFThread(MedicalRecordResource medicalRecordResource){
		this.medicalRecordResource = medicalRecordResource;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(medicalRecordResource != null){
			try {
				PDFUtil.createPdf(medicalRecordResource.getSourceFiles(), medicalRecordResource.getSourceBasicPaths(), medicalRecordResource.getNewPDFPath());
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
