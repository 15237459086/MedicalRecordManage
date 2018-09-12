package com.kurumi.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfOutline;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFUtil {

	public static ByteArrayOutputStream getPDFStream(List<Map<String,Object>> imageFiles,String imageBasicPath) throws MalformedURLException, IOException, DocumentException{
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	Document document = new Document(PageSize.A4); 
    	document.setMargins(0, 0, 0, 0);
    	PdfWriter writer = PdfWriter.getInstance(document, bos);
		
		document.open();
		// Code 2
      
		Map<String, String> pageTypes = new HashMap<String, String>();
		List<String> pageTypeCodes = new ArrayList<String>();
		for (Map<String, Object> imageFile : imageFiles) {
			/*Image image = Image.getInstance(BarCodeUtil.generate("123456789"));*/
			String imagePath = imageBasicPath + GuidUtil.getLocalPath((String)imageFile.get("file_hash"))+"\\"
 					+(String)imageFile.get("file_name")+"."+(String)imageFile.get("file_type");
        	String pageTypeCode = (String)(String)imageFile.get("mr_page_type_code");
        	String pageTypeName = (String)(String)imageFile.get("page_type_name");
        	if(pageTypeCode == null){
        		pageTypeCode = "-01";
        		pageTypeName = "未编页";
        	}
        	
			Image image = Image.getInstance(imagePath);
	        // 获取操作的页面
	        // 根据域的大小缩放图片
	        //image.scaleToFit(signRect.getWidth(), signRect.getHeight());
	        // 添加图片
			float imageHeight=image.getScaledHeight();
			float imageWidth=image.getScaledWidth();
			int i=0;
			while(imageHeight>PageSize.A4.getHeight()||imageWidth>PageSize.A4.getWidth()){
				image.scalePercent(100-i);
				i++;
				imageHeight=image.getScaledHeight();
				imageWidth=image.getScaledWidth();
			}
			/*image.scalePercent(100);*/
			image.setAlignment(Image.ALIGN_CENTER);
			document.newPage();
			if(!pageTypes.containsKey(pageTypeCode)){
				document.add(new Chunk(pageTypeName).setLocalDestination(pageTypeCode));
				pageTypes.put(pageTypeCode, pageTypeName);
				pageTypeCodes.add(pageTypeCode);
			}
			
			document.add(image);
			
		}
		
		PdfContentByte cb = writer.getDirectContent();
		PdfOutline root = cb.getRootOutline();
		for (String pageTypeCode : pageTypeCodes) {
			 @SuppressWarnings("unused")
		     PdfOutline oline1 = new PdfOutline(root, PdfAction.gotoLocalPage(pageTypeCode, false),pageTypes.get(pageTypeCode));
		        
		}
		// Code 3  
       
          
        
	 	document.close();
	 	return bos;
    
	}
	
	
	public static synchronized void createPdf(List<Map<String,Object>> imageFiles,String imageBasicPath,String newPDFPath) throws DocumentException, IOException{
    	Document document = new Document();
    	document.setPageSize(PageSize.A4);
    	FileOutputStream out = null;
    	
		try {
			File newFile = new File(newPDFPath);
			if(!newFile.exists()){
				File parentFile = new File(newFile.getParent());
				if(!parentFile.exists()){
					parentFile.mkdirs();
				}
			}else{
				return;
			}
			out = new FileOutputStream(newPDFPath);
			PdfWriter writer = PdfWriter.getInstance(document, out);
			// 打开文档
			document.open();
			// 读取一个图片
			Map<String, String> pageTypes = new HashMap<String, String>();
			List<String> pageTypeCodes = new ArrayList<String>();
			for (Map<String, Object> sourceFile : imageFiles) {
				String fileType = (String)sourceFile.get("file_type");
				if(fileType.equalsIgnoreCase("PNG")|| fileType.equalsIgnoreCase("JPG")||fileType.equalsIgnoreCase("JPEG") || fileType.equalsIgnoreCase("TIF") || fileType.equalsIgnoreCase("TIFF")){
					
					String imagePath = imageBasicPath + GuidUtil.getLocalPath((String)sourceFile.get("file_hash"))+"\\"
		 					+(String)sourceFile.get("file_name")+"."+fileType;
		        	String pageTypeCode = (String)(String)sourceFile.get("mr_page_type_code");
		        	String pageTypeName = (String)(String)sourceFile.get("page_type_name");
		        	if(pageTypeCode == null){
		        		pageTypeCode = "-01";
		        		pageTypeName = "未编页";
		        	}
		        	
					Image image = Image.getInstance(imagePath);
			        // 获取操作的页面
			        // 根据域的大小缩放图片
			        //image.scaleToFit(signRect.getWidth(), signRect.getHeight());
			        // 添加图片
					float imageHeight=image.getScaledHeight();
					float imageWidth=image.getScaledWidth();
					int i=0;
					while(imageHeight>PageSize.A4.getHeight()||imageWidth>PageSize.A4.getWidth()){
						image.scalePercent(100-i);
						i++;
						imageHeight=image.getScaledHeight();
						imageWidth=image.getScaledWidth();
					}
					/*image.scalePercent(100);*/
					image.setAlignment(Image.TOP);
					image.setAbsolutePosition(0, 0);
					document.newPage();
					if(!pageTypes.containsKey(pageTypeCode)){
						document.add(new Chunk(pageTypeName,new Font(FontFamily.UNDEFINED, 18, 0, BaseColor.WHITE)).setLocalDestination(pageTypeCode));
						
						
						pageTypes.put(pageTypeCode, pageTypeName);
						pageTypeCodes.add(pageTypeCode);
					}
					document.add(image);
				}
				/*Image image = Image.getInstance(BarCodeUtil.generate("123456789"));*/
				
				
			}
			PdfContentByte cb = writer.getDirectContent();
			PdfOutline root = cb.getRootOutline();
			for (String pageTypeCode : pageTypeCodes) {
				 @SuppressWarnings("unused")
			     PdfOutline oline1 = new PdfOutline(root, PdfAction.gotoLocalPage(pageTypeCode, false),pageTypes.get(pageTypeCode));
			        
			}
        	
			
		} catch (DocumentException de) {
			de.printStackTrace();
			throw de;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		}finally{
			try {
				if(out!= null){
					out.flush();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try{
				if(document != null){
					document.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if(out!= null){
					out.flush();
				}
			} catch (Exception e) {
				// TODO: handle exception
				out.close();
			}
		}
    }
	
	
	public static synchronized void createPdf(List<Map<String,Object>> imageFiles,String imageBasicPath,String pageIndexPDFPath,String newPDFPath) throws DocumentException, IOException{
    	Document document = new Document();
    	document.setPageSize(PageSize.A4);
    	FileOutputStream out = null;
    	
		try {
			File newFile = new File(newPDFPath);
			if(!newFile.exists()){
				File parentFile = new File(newFile.getParent());
				if(!parentFile.exists()){
					parentFile.mkdirs();
				}
			}else{
				return;
			}
			out = new FileOutputStream(newPDFPath);
			PdfWriter writer = PdfWriter.getInstance(document, out);
			// 打开文档
			document.open();
			if(pageIndexPDFPath != null){
				File pageIndexPDFFile = new File(pageIndexPDFPath);
				if(pageIndexPDFFile.exists()){
					PdfReader pdfReader = new PdfReader(pageIndexPDFPath);
					// 打开文档
					document.open();
					PdfContentByte cb = writer.getDirectContent();  
					int pageOfCurrentReaderPDF = 0;
					while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {  
		    	        document.newPage();
		    	        pageOfCurrentReaderPDF++;  
		                PdfImportedPage page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
		                cb.addTemplate(page, 0, 0);
		                
		    	    }
				}
			}
			
			
			
			// 读取一个图片
			/*Map<String, String> pageTypes = new HashMap<String, String>();
			List<String> pageTypeCodes = new ArrayList<String>();*/
			for (Map<String, Object> sourceFile : imageFiles) {
				String fileType = (String)sourceFile.get("file_type");
				if(fileType.equalsIgnoreCase("PNG")|| fileType.equalsIgnoreCase("JPG")||fileType.equalsIgnoreCase("JPEG") || fileType.equalsIgnoreCase("TIF")|| fileType.equalsIgnoreCase("TIFF")){
					
					String imagePath = imageBasicPath + GuidUtil.getLocalPath((String)sourceFile.get("file_hash"))+"\\"
		 					+(String)sourceFile.get("file_name")+"."+fileType;
		        	/*String pageTypeCode = (String)(String)sourceFile.get("mr_page_type_code");
		        	String pageTypeName = (String)(String)sourceFile.get("page_type_name");
		        	if(pageTypeCode == null){
		        		pageTypeCode = "-01";
		        		pageTypeName = "未编页";
		        	}*/
		        	
					Image image = Image.getInstance(imagePath);
			        // 获取操作的页面
			        // 根据域的大小缩放图片
			        //image.scaleToFit(signRect.getWidth(), signRect.getHeight());
			        // 添加图片
					float imageHeight=image.getScaledHeight();
					float imageWidth=image.getScaledWidth();
					int i=0;
					while(imageHeight>PageSize.A4.getHeight()||imageWidth>PageSize.A4.getWidth()){
						image.scalePercent(100-i);
						i++;
						imageHeight=image.getScaledHeight();
						imageWidth=image.getScaledWidth();
					}
					/*image.scalePercent(100);*/
					image.setAlignment(Image.ALIGN_CENTER);
					document.newPage();
					/*if(!pageTypes.containsKey(pageTypeCode)){
						document.add(new Chunk(pageTypeName).setLocalDestination(pageTypeCode));
						pageTypes.put(pageTypeCode, pageTypeName);
						pageTypeCodes.add(pageTypeCode);
					}*/
					
					document.add(image);
				}
				/*Image image = Image.getInstance(BarCodeUtil.generate("123456789"));*/
				
				
			}
			/*PdfContentByte cb = writer.getDirectContent();
			PdfOutline root = cb.getRootOutline();
			for (String pageTypeCode : pageTypeCodes) {
				 @SuppressWarnings("unused")
			     PdfOutline oline1 = new PdfOutline(root, PdfAction.gotoLocalPage(pageTypeCode, false),pageTypes.get(pageTypeCode));
			        
			}*/
        	
			
		} catch (DocumentException de) {
			de.printStackTrace();
			throw de;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		}finally{
			try {
				if(out!= null){
					out.flush();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try{
				if(document != null){
					document.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if(out!= null){
					out.flush();
				}
			} catch (Exception e) {
				// TODO: handle exception
				out.close();
			}
		}
    }
	
	/**
	 * 依据模板生成pdf流
	 * @param data
	 * @param pageIndexTemplatePDFPath
	 * @param medicalWorkerMap
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static ByteArrayOutputStream getPDFStreamByTemplate(Map<String, Object> data,
			String pageIndexTemplatePDFPath, Map<String, Object> signatureMedicalWorks)
			throws IOException, DocumentException {

		// // 模板路径
		// String templatePath = "F:/Publish/medical_record_template.pdf";
		//
		PdfReader reader = null;
		ByteArrayOutputStream bos = null;
		PdfStamper stamper = null;
		try {

			Map<String, Object> basicInfo = (Map<String, Object>) data.get("basicInfo");
			reader = new PdfReader(pageIndexTemplatePDFPath);// 读取pdf模板
			bos = new ByteArrayOutputStream();
			stamper = new PdfStamper(reader, bos);
			AcroFields form = stamper.getAcroFields();

			// 使用中文字体
			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
			fontList.add(bf);

			form.setSubstitutionFonts(fontList);
			if (basicInfo != null) {
				String patientName = (String) basicInfo.get("patientName");
				if (patientName != null) {
					form.setField("patientName", patientName);
					
				}
				

				String sexCode = (String) basicInfo.get("sexCode");
				if (sexCode != null) {
					form.setField("sexCode", sexCode);
				}
				

				String sexName = (String) basicInfo.get("sexName");
				if (sexName != null) {
					form.setField("sexName", sexName);
				}
				
				String birthday = (String) basicInfo.get("birthday");
				if (birthday != null) {
					form.setField("birthday", birthday);
				}
				
				Object yearOfAge = (Object) basicInfo.get("yearOfAge");
				if (yearOfAge != null) {
					form.setField("yearOfAge", yearOfAge.toString());
				}
				
				Object monthOfAge = (Object) basicInfo.get("monthOfAge");
				if (monthOfAge != null) {
					form.setField("monthOfAge", monthOfAge.toString());
				}
				
				Object weekOfAge = (Object) basicInfo.get("weekOfAge");
				if (weekOfAge != null) {
					form.setField("weekOfAge", weekOfAge.toString());
				}
				
				Object dayOfAge = (Object) basicInfo.get("dayOfAge");
				if (dayOfAge != null) {
					form.setField("dayOfAge", dayOfAge.toString());
				}

				
				String ageUnitCode = (String) basicInfo.get("ageUnitCode");
				if (ageUnitCode != null) {
					form.setField("ageUnitCode", ageUnitCode);
				}
				
				String ageUnitName = (String) basicInfo.get("ageUnitName");
				if (ageUnitName != null) {
					form.setField("ageUnitName", ageUnitCode);
				}
				
				Object ageNumber = (Object) basicInfo.get("ageNumber");
				if (ageNumber != null) {
					form.setField("ageNumber", ageNumber.toString());
				}

				String marriageCode = (String) basicInfo.get("marriageCode");
				if (marriageCode != null) {
					form.setField("marriageCode", marriageCode);
				}
				
				String marriageName = (String) basicInfo.get("marriageName");
				if (marriageName != null) {
					form.setField("marriageName", marriageName);
				}
				
				String professionCode = (String) basicInfo.get("professionCode");
				if (professionCode != null) {
					form.setField("professionCode", professionCode);
				}
				
				String professionName = (String) basicInfo.get("professionName");
				if (professionName != null) {
					form.setField("professionName", professionName);
				}
				
				String birthAddress = (String) basicInfo.get("birthAddress");
				if (birthAddress != null) {
					form.setField("birthAddress", birthAddress);
				}
				

				String birthAddressPostCode = (String) basicInfo.get("birthAddressPostCode");
				if (birthAddressPostCode != null) {
					form.setField("birthAddressPostCode", birthAddressPostCode);
				}

				String telePhone = (String) basicInfo.get("telePhone");
				if (telePhone != null) {
					form.setField("telePhone", telePhone);
				}
				
				String nationalityCode = (String) basicInfo.get("nationalityCode");
				if (nationalityCode != null) {
					form.setField("nationalityCode", nationalityCode);
				}
				
				String nationalityName = (String) basicInfo.get("nationalityName");
				if (nationalityName != null) {
					form.setField("nationalityName", nationalityName);
				}

				
				String nationCode = (String) basicInfo.get("nationCode");
				if (nationCode != null) {
					form.setField("nationCode", nationCode);
				}
				
				String nationName = (String) basicInfo.get("nationName");
				if (nationName != null) {
					form.setField("nationName", nationName);
				}
				
				String nativePlace = (String) basicInfo.get("nativePlace");
				if (nativePlace != null) {
					form.setField("nativePlace", nativePlace);
				}
				
				String registeredAddress = (String) basicInfo.get("registeredAddress");
				if (registeredAddress != null) {
					form.setField("registeredAddress", registeredAddress);
				}
				
				String registeredAddressPostCode = (String) basicInfo.get("registeredAddressPostCode");
				if (registeredAddressPostCode != null) {
					form.setField("registeredAddressPostCode", registeredAddressPostCode);
				}
				
				String idNumber = (String) basicInfo.get("idNumber");
				if (idNumber != null) {
					form.setField("idNumber", idNumber);
				}
				
				String mrId = (String) basicInfo.get("mrId");
				if (mrId != null) {
					form.setField("mrId", mrId);
				}
				
				String onlyId = (String) basicInfo.get("onlyId");
				if (onlyId != null) {
					form.setField("onlyId", mrId);
				}
				
				Object visitNumber = (Object) basicInfo.get("visitNumber");
				if (visitNumber != null) {
					form.setField("visitNumber", visitNumber.toString());
				}
				
				String medicalPayTypeCode = (String) basicInfo.get("medicalPayTypeCode");
				if (medicalPayTypeCode != null) {
					form.setField("medicalPayTypeCode", medicalPayTypeCode);
				}
				
				String medicalPayTypeName = (String) basicInfo.get("medicalPayTypeName");
				if (medicalPayTypeName != null) {
					form.setField("medicalPayTypeName", medicalPayTypeName);
				}

				String medicalInsuranceNumber = (String) basicInfo.get("medicalInsuranceNumber");
				if (medicalInsuranceNumber != null) {
					form.setField("medicalInsuranceNumber", medicalInsuranceNumber);
				}

				String medicalHealthNumber = (String) basicInfo.get("medicalHealthNumber");
				if (medicalInsuranceNumber != null) {
					form.setField("medicalHealthNumber", medicalHealthNumber);
				}

				String workUnitName = (String) basicInfo.get("workUnitName");
				if (workUnitName != null) {
					form.setField("workUnitName", workUnitName);
				}
				
				String workUnitAddress = (String) basicInfo.get("workUnitAddress");
				if (workUnitAddress != null) {
					form.setField("workUnitAddress", workUnitAddress);
				}

				String workUnitPhone = (String) basicInfo.get("workUnitPhone");
				if (workUnitPhone != null) {
					form.setField("workUnitPhone", workUnitPhone);
				}

				String workUnitPostCode = (String) basicInfo.get("workUnitPostCode");
				if (workUnitPostCode != null) {
					form.setField("workUnitPostCode", workUnitPostCode);
				}

				

				String permanentAddress = (String) basicInfo.get("permanentAddress");
				if (permanentAddress != null) {
					form.setField("permanentAddress", permanentAddress);
				}

				String permanentAddressPhone = (String) basicInfo.get("permanentAddressPhone");
				if (permanentAddressPhone != null) {
					form.setField("permanentAddressPhone", permanentAddressPhone);
				}

				String permanentAddressPostCode = (String) basicInfo.get("permanentAddressPostCode");
				if (permanentAddressPostCode != null) {
					form.setField("permanentAddressPostCode", permanentAddressPostCode);
				}

				String linkManName = (String) basicInfo.get("linkManName");
				if (linkManName != null) {
					form.setField("linkManName", linkManName);
				}

				String linkManRelativeRelationName = (String) basicInfo.get("linkManRelativeRelationName");
				if (linkManRelativeRelationName != null) {
					form.setField("linkManRelativeRelationName", linkManRelativeRelationName);
				}

				String linkManAddress = (String) basicInfo.get("linkManAddress");
				if (linkManAddress != null) {
					form.setField("linkManAddress", linkManAddress);
				}

				String linkManPhone = (String) basicInfo.get("linkManPhone");
				if (linkManPhone != null) {
					form.setField("linkManPhone", linkManPhone);
				}
				
				String inHospitalTypeCode = (String) basicInfo.get("inHospitalTypeCode");
				if (inHospitalTypeCode != null) {
					form.setField("inHospitalTypeCode", inHospitalTypeCode);
				}
				

				String inHospitalTypeName = (String) basicInfo.get("inHospitalTypeName");
				if (inHospitalTypeName != null) {
					form.setField("inHospitalTypeName", inHospitalTypeName);
				}
				
				
				String inHospitalStateCode = (String) basicInfo.get("inHospitalStateCode");
				if (inHospitalStateCode != null) {
					form.setField("inHospitalStateCode", inHospitalStateCode);
				}
				
				String inHospitalStateName = (String) basicInfo.get("inHospitalStateName");
				if (inHospitalStateName != null) {
					form.setField("inHospitalStateName", inHospitalStateName);
				}

				String outpatientOfDoctorCode = (String) basicInfo.get("outpatientOfDoctorCode");
				if (outpatientOfDoctorCode != null) {
					form.setField("outpatientOfDoctorCode", outpatientOfDoctorCode);
				}
				
				
				String outpatientOfDoctorName = (String) basicInfo.get("outpatientOfDoctorName");
				if (outpatientOfDoctorName != null) {
					form.setField("outpatientOfDoctorName", outpatientOfDoctorName);
				}

				String inHospitalDateTime = (String) basicInfo.get("inHospitalDateTime");
				if (inHospitalDateTime != null) {
					form.setField("inHospitalDateTime", inHospitalDateTime);
				}

				String inDeptName = (String) basicInfo.get("inDeptName");
				if (inDeptName != null) {
					form.setField("inDeptName", inDeptName);
				}
				
				String inHospitalWardName = (String) basicInfo.get("inHospitalWardName");
				if (inHospitalWardName != null) {
					form.setField("inHospitalWardName", inHospitalWardName);
				}

				

				String outHospitalDateTime = (String) basicInfo.get("outHospitalDateTime");
				if (outHospitalDateTime != null) {
					form.setField("outHospitalDateTime", outHospitalDateTime);
				}

				String outDeptName = (String) basicInfo.get("outDeptName");
				if (outDeptName != null) {
					form.setField("outDeptName", outDeptName);
				}
				
				String outHospitalWardName = (String) basicInfo.get("outHospitalWardName");
				if (outHospitalWardName != null) {
					form.setField("outHospitalWardName", outHospitalWardName);
				}

				
				String outHospitalTypeCode = (String) basicInfo.get("outHospitalTypeCode");
				if (outHospitalTypeCode != null) {
					form.setField("outHospitalTypeCode", outHospitalTypeCode);
				}
				
				String outHospitalTypeName = (String) basicInfo.get("outHospitalTypeName");
				if (outHospitalTypeName != null) {
					form.setField("outHospitalTypeName", outHospitalTypeName);
				}
				
				
				String diagConfirmedDateTime = (String) basicInfo.get("diagConfirmedDateTime");
				if (diagConfirmedDateTime != null) {
					form.setField("diagConfirmedDateTime", diagConfirmedDateTime);
				}

				
				Object diagConfirmedDayNumber = (Object) basicInfo.get("diagConfirmedDayNumber");
				if (diagConfirmedDayNumber != null) {
					form.setField("diagConfirmedDayNumber", diagConfirmedDayNumber.toString());
				}

				Object inHospitalDayNumber = (Object) basicInfo.get("inHospitalDayNumber");
				if (inHospitalDayNumber != null) {
					form.setField("inHospitalDayNumber", inHospitalDayNumber.toString());
				}
				
				String shiftToUnitName = (String) basicInfo.get("shiftToUnitName");
				if (shiftToUnitName != null) {
					form.setField("shiftToUnitName", shiftToUnitName);
				}
				
				String rehospitalAimName = (String) basicInfo.get("rehospitalAimName");
				if (rehospitalAimName != null) {
					form.setField("rehospitalAimName", rehospitalAimName);
				}
				
				
				String dealthDateTime = (String) basicInfo.get("dealthDateTime");
				if (dealthDateTime != null) {
					form.setField("dealthDateTime", dealthDateTime);
				}
				
				String dealthReasonCode = (String) basicInfo.get("dealthReasonCode");
				if (dealthReasonCode != null) {
					form.setField("dealthReasonCode", dealthReasonCode);
				}
				
				String dealthReasonName = (String) basicInfo.get("dealthReasonName");
				if (dealthReasonName != null) {
					form.setField("dealthReasonName", dealthReasonName);
				}

				String autopsyCode = (String) basicInfo.get("autopsyCode");
				if (autopsyCode != null) {
					form.setField("autopsyCode", autopsyCode);
				}
				
				String autopsyName = (String) basicInfo.get("autopsyName");
				if (autopsyName != null) {
					form.setField("autopsyName", autopsyName);
				}
				
				String pathologyNumber = (String) basicInfo.get("pathologyNumber");
				if (pathologyNumber != null) {
					form.setField("pathologyNumber", pathologyNumber);
				}
				
				String drugAllergyDesc = (String) basicInfo.get("drugAllergyDesc");
				if (drugAllergyDesc != null) {
					form.setField("drugAllergyCode", "2");
					form.setField("drugAllergyDesc", drugAllergyDesc);
				}
				
				String changeDeptDesc = (String) basicInfo.get("changeDeptDesc");
				if (changeDeptDesc != null) {
					form.setField("changeDeptDesc", changeDeptDesc);
				}
				
				String infusionBloodDesc = (String) basicInfo.get("infusionBloodDesc");
				if (infusionBloodDesc != null) {
					form.setField("infusionBloodDesc", infusionBloodDesc);
				}
			}
			Map<String, Object> cureInfo = (Map<String, Object>) data.get("cureInfo");
			if (cureInfo != null) {
				
				String bloodTypeCode = (String) cureInfo.get("bloodTypeCode");
				if (bloodTypeCode != null) {
					form.setField("bloodTypeCode", bloodTypeCode);
				}
				
				String bloodTypeName = (String) cureInfo.get("bloodTypeName");
				if (bloodTypeName != null) {
					form.setField("bloodTypeName", bloodTypeName);
				}

				String rhBloodTypeCode = (String) cureInfo.get("rhBloodTypeCode");
				if (rhBloodTypeCode != null) {
					form.setField("rhBloodTypeCode", rhBloodTypeCode);
				}
				
				String rhBloodTypeName = (String) cureInfo.get("rhBloodTypeName");
				if (rhBloodTypeName != null) {
					form.setField("rhBloodTypeName", rhBloodTypeName);
				}
				
				Object rescueNumber = (Object) cureInfo.get("rescueNumber");
				if (rescueNumber != null) {
					form.setField("rescueNumber", rescueNumber.toString());
				}
				
				Object rescueSucceedNumber = (Object) cureInfo.get("rescueSucceedNumber");
				if (rescueSucceedNumber != null) {
					form.setField("rescueSucceedNumber", rescueSucceedNumber.toString());
				}
				
				Object inConsultationNumber = (Object) cureInfo.get("inConsultationNumber");
				if (inConsultationNumber != null) {
					form.setField("inConsultationNumber", inConsultationNumber.toString());
				}
				
				Object outConsultationNumber = (Object) cureInfo.get("outConsultationNumber");
				if (outConsultationNumber != null) {
					form.setField("outConsultationNumber", outConsultationNumber.toString());
				}
				
				Object infusionTimes = (Object) cureInfo.get("infusionTimes");
				if (infusionTimes != null) {
					form.setField("infusionTimes", infusionTimes.toString());
				}
				
				Object infusionReactTimes = (Object) cureInfo.get("infusionReactTimes");
				if (infusionReactTimes != null) {
					form.setField("infusionReactTimes", infusionReactTimes.toString());
				}
				
				
				String rehospitalAimOf31Code = (String) cureInfo.get("rehospitalAimOf31Code");
				if (rehospitalAimOf31Code != null) {
					form.setField("rehospitalAimOf31Code", rehospitalAimOf31Code);
				}
				
				String rehospitalAimOf31Name = (String) cureInfo.get("rehospitalAimOf31Name");
				if (rehospitalAimOf31Name != null) {
					form.setField("rehospitalAimOf31Name", rehospitalAimOf31Name);
				}

				String rehospitalAimOf31Description = (String) cureInfo.get("rehospitalAimOf31Description");
				if (rehospitalAimOf31Description != null) {
					form.setField("rehospitalAimOf31Description", rehospitalAimOf31Description);
				}
				

				// 恶性肿瘤入院确诊日期
				String malignantTumorConfirmedDateTime = (String) cureInfo.get("malignantTumorConfirmedDateTime");
				if (malignantTumorConfirmedDateTime != null) {
					form.setField("malignantTumorConfirmedDateTime", malignantTumorConfirmedDateTime);
				}

				String tumorGrade = (String) cureInfo.get("tumorGrade");
				if (tumorGrade != null) {
					form.setField("tumorGrade", tumorGrade);
				}

				String tumorStagingOfT = (String) cureInfo.get("tumorStagingOfT");
				if (tumorStagingOfT != null) {
					form.setField("tumorStagingOfT", tumorStagingOfT);
				}

				String tumorStagingOfN = (String) cureInfo.get("tumorStagingOfN");
				if (tumorStagingOfN != null) {
					form.setField("tumorStagingOfN", tumorStagingOfN);
				}

				String tumorStagingOfM = (String) cureInfo.get("tumorStagingOfM");
				if (tumorStagingOfM != null) {
					form.setField("tumorStagingOfM", tumorStagingOfM);
				}
				
				String malignantTumorFirstCureTypeCode = (String) cureInfo.get("malignantTumorFirstCureTypeCode");
				if (malignantTumorFirstCureTypeCode != null) {
					form.setField("malignantTumorFirstCureTypeCode", malignantTumorFirstCureTypeCode);
				}
				
				String malignantTumorFirstCureTypeName = (String) cureInfo.get("malignantTumorFirstCureTypeName");
				if (malignantTumorFirstCureTypeName != null) {
					form.setField("malignantTumorFirstCureTypeName", malignantTumorFirstCureTypeName);
				}

				String malignantTumorHighestDiagBasisCode = (String) cureInfo.get("malignantTumorHighestDiagBasisCode");
				if (malignantTumorHighestDiagBasisCode != null) {
					form.setField("malignantTumorHighestDiagBasisCode", malignantTumorHighestDiagBasisCode);
				}
				
				String malignantTumorHighestDiagBasisName = (String) cureInfo.get("malignantTumorHighestDiagBasisName");
				if (malignantTumorHighestDiagBasisName != null) {
					form.setField("malignantTumorHighestDiagBasisName", malignantTumorHighestDiagBasisName);
				}

				String qualityControlDateTime = (String) cureInfo.get("qualityControlDateTime");
				if (qualityControlDateTime != null) {
					form.setField("qualityControlDateTime", qualityControlDateTime.substring(0, 10));
				}
				
				String medicalRecordQualityCode = (String) cureInfo.get("medicalRecordQualityCode");
				if (medicalRecordQualityCode != null) {
					form.setField("medicalRecordQualityCode", medicalRecordQualityCode);
				}
				
				String medicalRecordQualityName = (String) cureInfo.get("medicalRecordQualityName");
				if (medicalRecordQualityName != null) {
					form.setField("medicalRecordQualityName", medicalRecordQualityName);
				}
				

				
				// 医务人员
				List<Map<String, Object>> cureWorkers = (List<Map<String, Object>>) cureInfo.get("cureWorkers");
				if (cureWorkers != null) {
					for (Map<String, Object> cureWorker : cureWorkers) {
						String professionTitleName = (String) cureWorker.get("professionTitleName");
						if (professionTitleName != null) {
							if (professionTitleName.contains("科主任")) {
								String medicalWorkerCode = (String) cureWorker.get("medicalWorkerCode");
								String medicalWorkerName = (String) cureWorker.get("medicalWorkerName");
								if(medicalWorkerCode != null && signatureMedicalWorks.containsKey(medicalWorkerCode)){
									int pageNo = form.getFieldPositions("directorDepartment").get(0).page;
              			            Rectangle signRect = form.getFieldPositions("directorDepartment").get(0).position;
              			            float x = signRect.getLeft();
              			            float y = signRect.getBottom();
              			            Map<String, Object> signatureMedicalWork = (Map<String, Object>)signatureMedicalWorks.get(medicalWorkerCode);
              			            String signaturePath = (String)signatureMedicalWork.get("signaturePath");
              			            Image image = Image.getInstance(signaturePath);
              			            // 获取操作的页面
              			            PdfContentByte under = stamper.getOverContent(pageNo);
              			            float scaler = ((float)signRect.getHeight()/image.getHeight())*100;
            			            
            			            image.scalePercent(scaler);
              			            // 添加图片
              			            image.setAbsolutePosition(x, y);
              			            under.addImage(image); 
								}
								else if (medicalWorkerName != null) {
									form.setField("directorDepartment", medicalWorkerName);
								}
								
							} else if (professionTitleName.contains("副主任")) {
								String medicalWorkerCode = (String) cureWorker.get("medicalWorkerCode");
								String medicalWorkerName = (String) cureWorker.get("medicalWorkerName");
								if(medicalWorkerCode != null && signatureMedicalWorks.containsKey(medicalWorkerCode)){
									int pageNo = form.getFieldPositions("director").get(0).page;
              			            Rectangle signRect = form.getFieldPositions("director").get(0).position;
              			            float x = signRect.getLeft();
              			            float y = signRect.getBottom();
              			            Map<String, Object> signatureMedicalWork = (Map<String, Object>)signatureMedicalWorks.get(medicalWorkerCode);
              			            String signaturePath = (String)signatureMedicalWork.get("signaturePath");
              			            Image image = Image.getInstance(signaturePath);
              			            // 获取操作的页面
              			            PdfContentByte under = stamper.getOverContent(pageNo);
              			            float scaler = ((float)signRect.getHeight()/image.getHeight())*100;
          			            
              			            image.scalePercent(scaler);
              			            // 添加图片
              			            image.setAbsolutePosition(x, y);
              			            under.addImage(image); 
								}
								else if (medicalWorkerName != null) {
									form.setField("director", medicalWorkerName);
								}
							} else if (professionTitleName.contains("主治")) {
								String medicalWorkerCode = (String) cureWorker.get("medicalWorkerCode");
								String medicalWorkerName = (String) cureWorker.get("medicalWorkerName");
								if(medicalWorkerCode != null && signatureMedicalWorks.containsKey(medicalWorkerCode)){
									int pageNo = form.getFieldPositions("zhuZhimedicalWorkerName").get(0).page;
              			            Rectangle signRect = form.getFieldPositions("zhuZhimedicalWorkerName").get(0).position;
              			            float x = signRect.getLeft();
              			            float y = signRect.getBottom();
              			            Map<String, Object> signatureMedicalWork = (Map<String, Object>)signatureMedicalWorks.get(medicalWorkerCode);
              			            String signaturePath = (String)signatureMedicalWork.get("signaturePath");
              			            Image image = Image.getInstance(signaturePath);
              			           
              			            // 获取操作的页面
              			            PdfContentByte under = stamper.getOverContent(pageNo);
              			            float scaler = ((float)signRect.getHeight()/image.getHeight())*100;
              			            
              			            image.scalePercent(scaler);
              			            // 添加图片
              			            image.setAbsolutePosition(x, y);
              			            under.addImage(image); 
								}
								else if (medicalWorkerName != null) {
									form.setField("zhuZhimedicalWorkerName", medicalWorkerName);
								}
							} else if (professionTitleName.contains("住院")) {
								String medicalWorkerCode = (String) cureWorker.get("medicalWorkerCode");
								String medicalWorkerName = (String) cureWorker.get("medicalWorkerName");
								if(medicalWorkerCode != null && signatureMedicalWorks.containsKey(medicalWorkerCode)){
									int pageNo = form.getFieldPositions("zhuYuanmedicalWorkerName").get(0).page;
              			            Rectangle signRect = form.getFieldPositions("zhuYuanmedicalWorkerName").get(0).position;
              			            float x = signRect.getLeft();
              			            float y = signRect.getBottom();
              			            Map<String, Object> signatureMedicalWork = (Map<String, Object>)signatureMedicalWorks.get(medicalWorkerCode);
              			            String signaturePath = (String)signatureMedicalWork.get("signaturePath");
              			            Image image = Image.getInstance(signaturePath);
              			            // 获取操作的页面
              			            PdfContentByte under = stamper.getOverContent(pageNo);
              			            float scaler = ((float)signRect.getHeight()/image.getHeight())*100;
          			            
              			            image.scalePercent(scaler);
              			            // 添加图片
              			            image.setAbsolutePosition(x, y);
              			            under.addImage(image); 
								}
								else if(medicalWorkerName != null) {
									form.setField("zhuYuanmedicalWorkerName", medicalWorkerName);
								}
							} else if (professionTitleName.contains("责任")) {
								String medicalWorkerName = (String) cureWorker.get("medicalWorkerName");
								if (medicalWorkerName != null) {
									form.setField("zrhs", medicalWorkerName);
								}
							} else if (professionTitleName.contains("进修")) {
								String medicalWorkerName = (String) cureWorker.get("medicalWorkerName");
								if (medicalWorkerName != null) {
									form.setField("refresherDoctors", medicalWorkerName);
								}
							} else if (professionTitleName.contains("实习")) {
								String medicalWorkerName = (String) cureWorker.get("medicalWorkerName");
								if (medicalWorkerName != null) {
									form.setField("intern", medicalWorkerName);
								}
							} else if (professionTitleName.contains("编码")) {
								String medicalWorkerName = (String) cureWorker.get("medicalWorkerName");
								if (medicalWorkerName != null) {
									form.setField("coders", medicalWorkerName);
								}
							} else if (professionTitleName.contains("质控医师")) {
								String medicalWorkerName = (String) cureWorker.get("medicalWorkerName");
								if (medicalWorkerName != null) {
									form.setField("qualityPhysician", medicalWorkerName);
								}
							} else if (professionTitleName.contains("质控护士")) {
								String medicalWorkerName = (String) cureWorker.get("medicalWorkerName");
								if (medicalWorkerName != null) {
									form.setField("qualityNurse", medicalWorkerName);
								}
							}
						}
					}
				}

			}
			Map<String, Object> diseaseDiagInfo = (Map<String, Object>) data.get("diseaseDiagInfo");
			Map<String, Object> operateInfo = (Map<String, Object>) data.get("operateInfo");
			if (diseaseDiagInfo != null) {
				Map<String, Object> outpatientDiagRecord = (Map<String, Object>) diseaseDiagInfo.get("outpatientDiagRecord");
				if(outpatientDiagRecord != null){
					String diseaseDiagCode = (String) outpatientDiagRecord.get("diseaseDiagCode");
					String diseaseDiagName = (String) outpatientDiagRecord.get("diseaseDiagName");
					if (diseaseDiagCode != null) {
						form.setField("outpatientDiagCode", diseaseDiagCode);
					}
					if (diseaseDiagName != null) {
						form.setField("outpatientDiagDesc", diseaseDiagName);
						
					}
				}
				
				Map<String, Object> mainDiagRecord = (Map<String, Object>) diseaseDiagInfo.get("mainDiagRecord");
				if(mainDiagRecord != null){
					String diseaseDiagCode = (String) mainDiagRecord.get("diseaseDiagCode");
					String diseaseDiagName = (String) mainDiagRecord.get("diseaseDiagName");
					if (diseaseDiagCode != null) {
						form.setField("mainDiagnosisCode", diseaseDiagCode);
					}
					if (diseaseDiagName != null) {
						form.setField("mainDiagnosisDesc", diseaseDiagName);
						
					}
				}
				
				Map<String, Object> damageAndVenenationDiagRecord = (Map<String, Object>) diseaseDiagInfo.get("damageAndVenenationDiagRecord");
				if(damageAndVenenationDiagRecord != null){
					String diseaseDiagCode = (String) damageAndVenenationDiagRecord.get("diseaseDiagCode");
					String diseaseDiagName = (String) damageAndVenenationDiagRecord.get("diseaseDiagName");
					if (diseaseDiagCode != null) {
						form.setField("injuryAndPoisoningCode", diseaseDiagCode);
					}
					if (diseaseDiagName != null) {
						form.setField("injuryAndPoisoning", diseaseDiagName);
						
					}
				}
				List<Map<String, Object>> diseaseDiagRecords = (List<Map<String, Object>>) diseaseDiagInfo
						.get("diseaseDiagRecords");
				if (diseaseDiagRecords != null) {
					int otherDiagIndex = 0;
					for (Map<String, Object> diseaseDiagRecord : diseaseDiagRecords) {
						String diagTypeName = (String) diseaseDiagRecord.get("diagTypeName");
						if (diagTypeName != null) {
							if (diagTypeName.contains("其他") || diagTypeName.contains("其它")) {
								// 其他诊断
								String diseaseDiagCode = (String) diseaseDiagRecord.get("diseaseDiagCode");
								String diseaseDiagName = (String) diseaseDiagRecord.get("diseaseDiagName");
								
								if (diseaseDiagName != null) {
									form.setField("otherDiagnosis" + otherDiagIndex, diseaseDiagName);
								}
								if (diseaseDiagCode != null) {
									form.setField("otherDiagnosisName" + otherDiagIndex, diseaseDiagCode);
								}
								otherDiagIndex++;
							}  else if (diagTypeName.contains("病理诊断")) {
								// 病理诊断
								String diseaseDiagCode = (String) diseaseDiagRecord.get("diseaseDiagCode");
								String diseaseDiagName = (String) diseaseDiagRecord.get("diseaseDiagName");
								if (diseaseDiagCode != null) {
									form.setField("pathologicDiagnosisCode", diseaseDiagCode);
								}
								if (diseaseDiagName != null) {
									form.setField("pathologicDiagnosis", diseaseDiagName);
									
								}
							}
						}
					}
				}
			}

			if (operateInfo != null) {
				// 手术信息
				List<Map<String, Object>> operateRecords = (List<Map<String, Object>>) operateInfo
						.get("operateRecords");
				if (operateRecords != null) {
					int operateRecordIndex = 0;
					for (Map<String, Object> operateRecord : operateRecords) {
						String operateCode = (String) operateRecord.get("operateCode");
						if (operateCode != null) {
							form.setField("operateCode" + operateRecordIndex, operateCode);

						}

						String operateStartDateStr = (String) operateRecord.get("operateStartDate");
						String operateStartDate = operateStartDateStr.substring(0, 10);
						if (operateStartDate != null) {
							form.setField("operateStartDate" + operateRecordIndex, operateStartDate);

						}

						String opsLevelCode = (String) operateRecord.get("opsLevelCode");
						if (opsLevelCode != null) {
							form.setField("opsLevelCode" + operateRecordIndex, opsLevelCode);

						}

						String operateName = (String) operateRecord.get("operateName");
						if (operateName != null) {
							form.setField("operateName" + operateRecordIndex, operateName);

						}

						String incisionLevelName = (String) operateRecord.get("incisionLevelName");
						String cicatrizeTypeName = (String) operateRecord.get("cicatrizeTypeName");
						if (incisionLevelName != null & cicatrizeTypeName != null) {
							form.setField("incisionLevelName" + operateRecordIndex,
									incisionLevelName + "/" + cicatrizeTypeName);

						}

						String anaesthesiaTypeName = (String) operateRecord.get("anaesthesiaTypeName");
						if (anaesthesiaTypeName != null) {
							form.setField("anaesthesiaTypeName" + operateRecordIndex, anaesthesiaTypeName);

						}

						List<Map<String, Object>> operateWorkers = (List<Map<String, Object>>) operateRecord
								.get("operateWorkers");
						if (operateWorkers != null) {
							for (Map<String, Object> operateWorker : operateWorkers) {

								String professionTitleName = (String) operateWorker.get("professionTitleName");
								if (professionTitleName != null) {
									if (professionTitleName.contains("手术")) {
										String medicalWorkerName = (String) operateWorker.get("medicalWorkerName");
										if (medicalWorkerName != null) {
											form.setField("operationDoctor" + operateRecordIndex, medicalWorkerName);
										}
									} else if (professionTitleName.contains("第一")) {
										String medicalWorkerName = (String) operateWorker.get("medicalWorkerName");
										if (medicalWorkerName != null) {
											form.setField("assistant" + operateRecordIndex, medicalWorkerName);
										}
									} else if (professionTitleName.contains("第二")) {
										String medicalWorkerName = (String) operateWorker.get("medicalWorkerName");
										if (medicalWorkerName != null) {
											form.setField("assistant1" + operateRecordIndex, medicalWorkerName);
										}
									} else if (professionTitleName.contains("麻醉")) {
										String medicalWorkerName = (String) operateWorker.get("medicalWorkerName");
										if (medicalWorkerName != null) {
											form.setField("anesthesiologist" + operateRecordIndex, medicalWorkerName);
										}
									}
								}

							}
						}

					}
				}
			}

			
			stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
			stamper.close();
			return bos;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}

	}
	
	/**
	 * 依据模板生成pdf
	 * @param data
	 * @param pageIndexTemplatePDFPath
	 * @param newPDFPath
	 * @param newPDFName
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static synchronized void createPDFByTemplate(Map<String, Object> data,String pageIndexTemplatePDFPath,String newPDFPath) throws IOException, DocumentException{  
        
        FileOutputStream out = null;  
        ByteArrayOutputStream bos = null;
       
        try {
        	File newFile = new File(newPDFPath);
			if(!newFile.exists()){
				File parentFile = new File(newFile.getParent());
				if(!parentFile.exists()){
					parentFile.mkdirs();
				}
			}
            out = new FileOutputStream(newPDFPath);// 输出流 
            bos = getPDFStreamByTemplate(data, pageIndexTemplatePDFPath, new HashMap<String, Object>()); 
           
             
  
            Document doc = new Document(PageSize.A4); 
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfReader pf =new PdfReader(bos.toByteArray());
            for(int index =1;index<=pf.getNumberOfPages();index++){
            	PdfImportedPage importPage = copy.getImportedPage(pf, index);
            	copy.addAnnotation(new PdfAnnotation(copy, 0, 0, 0, 0,new PdfString( "text"+index), new PdfString( "text"+index)));
                copy.addPage(importPage);
            }
            PdfContentByte cb = copy.getDirectContent();
			PdfOutline root = cb.getRootOutline();
			PdfOutline oline1 = new PdfOutline(root, PdfAction.gotoLocalPage("text1", false),"text1");
			PdfOutline oline2 = new PdfOutline(root, PdfAction.gotoLocalPage("text2", false),"text2");
            doc.close();
        }  catch (IOException e) {  
            e.printStackTrace();
            throw e;
        } catch (DocumentException e) {  
            e.printStackTrace();
            throw e;
        }finally{
        	if(bos != null){
        		try {
        			bos.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
        	}
        }
  
    }
	
	
	public static ByteArrayOutputStream getPDFStream(String pageIndexPDFPath,String imagePDFPath) throws DocumentException, IOException{
		Document document = new Document();
    	document.setPageSize(PageSize.A4);
		ByteArrayOutputStream bos = null;
		try {
			PdfReader pdfReader = new PdfReader(pageIndexPDFPath);
			bos = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bos);
			// 打开文档
			document.open();
			PdfContentByte cb = writer.getDirectContent();  
			int pageOfCurrentReaderPDF = 0;
			while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {  
    	        document.newPage();
    	        pageOfCurrentReaderPDF++;  
                PdfImportedPage page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
                cb.addTemplate(page, 0, 0);
                
    	    }
			PdfReader pdfReader1= new PdfReader(imagePDFPath);
			
			int pageOfCurrentReaderPDF1 = 0;
			while (pageOfCurrentReaderPDF1 < pdfReader1.getNumberOfPages()) {  
    	        document.newPage(); 
    	        
    	        pageOfCurrentReaderPDF1++;  
                PdfImportedPage page = writer.getImportedPage(pdfReader1, pageOfCurrentReaderPDF1);
                
                cb.addTemplate(page, 0, 0);
                
    	    }
		} catch (DocumentException de) {
			de.printStackTrace();
			throw de;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		}finally{

			try{
				if(document != null){
					document.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		return bos;
    }
}
