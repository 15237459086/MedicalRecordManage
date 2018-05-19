package com.kurumi.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfOutline;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFUtil {

	public static ByteArrayOutputStream getPDFStream(List<Map<String,Object>> scanFiles,String imageBasicPath) throws MalformedURLException, IOException, DocumentException{
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	Document document = new Document(PageSize.A4); 
    	document.setMargins(0, 0, 0, 0);
    	PdfWriter writer = PdfWriter.getInstance(document, bos);
		
		document.open();
		// Code 2
      
		Map<String, String> pageTypes = new HashMap<String, String>();
		List<String> pageTypeCodes = new ArrayList<String>();
		for (Map<String, Object> scanFile : scanFiles) {
			/*Image image = Image.getInstance(BarCodeUtil.generate("123456789"));*/
			String imagePath = imageBasicPath + GuidUtil.getLocalPath((String)scanFile.get("file_hash"))+"\\"
 					+(String)scanFile.get("file_name")+"."+(String)scanFile.get("file_type");
        	String pageTypeCode = (String)(String)scanFile.get("mr_page_type_code");
        	String pageTypeName = (String)(String)scanFile.get("page_type_name");
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
	
}
