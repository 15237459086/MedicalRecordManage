package com.kurumi.thread;

import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.kurumi.pojo.resource.MedicalRecordResource;
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
				if(medicalRecordResource.getCurrentVersion().equalsIgnoreCase("role_version_1.1")){
					PDFUtil.createPdf(medicalRecordResource.getImageRecources(),medicalRecordResource.getImageBasicPath(), medicalRecordResource.getNewPDFPath());
				}else if(medicalRecordResource.getCurrentVersion().equalsIgnoreCase("role_version_2.1")){
					PDFUtil.createPdf(medicalRecordResource.getImageRecources(),medicalRecordResource.getImageBasicPath(),medicalRecordResource.getPageIndexPDFPath(),medicalRecordResource.getNewPDFPath());
				}
				
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
