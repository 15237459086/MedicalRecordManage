package com.kurumi.util;

import java.io.ByteArrayOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class WaterMarkUtil {

	// 水印透明度
    private static float alpha = 1f;
    // 水印横向位置
    private static int positionWidth = 150;
    // 水印纵向位置
    private static int positionHeight = 300;
	
	/**
	 * 获取水印PDF输出流
	 * @param inputPDFFile 输入PDF文件路径
	 * @param waterMarkText 水印文字
	 * @return ByteArrayOutputStream
	 * @throws Exception
	 */
	public static ByteArrayOutputStream getOutputStreamOfWaterMarkByText(String inputPDFFile,String waterMarkText) throws Exception {
		PdfStamper stamper = null;
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();
            PdfReader reader = new PdfReader(inputPDFFile);
            stamper = new PdfStamper(reader, bos);
            //这里的字体设置比较关键，这个设置是支持中文的写法
            BaseFont base = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 使用系统字体
            int total = reader.getNumberOfPages() + 1;
            PdfContentByte under = null;
            for (int i = 1; i < total; i++) {
                // 计算水印X,Y坐标
                float x = WaterMarkUtil.positionWidth;
                float y = WaterMarkUtil.positionHeight;
                // 获得PDF最顶层
                under = stamper.getOverContent(i);
                under.saveState();
                // set Transparency
                PdfGState gs = new PdfGState();
                // 设置透明度为0.2
                gs.setFillOpacity(WaterMarkUtil.alpha);
                under.setGState(gs);
                // 注意这里必须调用一次restoreState 否则设置无效
                under.restoreState();
                under.beginText();
                under.setFontAndSize(base, 14);
                under.setColorFill(BaseColor.DARK_GRAY);
                // 水印文字成45度角倾斜
                
                under.showTextAligned(Element.ALIGN_LEFT
                        , waterMarkText,x,
                        y, -45);
               /* for(int index = 0;index < 50;index ++){
                	
                	under.showTextAligned(Element.ALIGN_LEFT
                            , waterMarkText,300+(index*10),
                            1000-(index*10), -55);
                	
                	under.showTextAligned(Element.ALIGN_LEFT
                            , waterMarkText,100+(index*10),
                            1000-(index*10), -55);
                	
                	under.showTextAligned(Element.ALIGN_LEFT
                            , waterMarkText,100+(index*10),
                            600-(index*10), -55);
                }*/
                
                // 添加水印文字
                under.endText();
                under.setLineWidth(1f);
                under.stroke();
            }
            
            return bos;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally{
        	
        	if(stamper!= null){
        		try{
        			stamper.close();
        		}catch (Exception e) {
                    e.printStackTrace();
                }
        		
        	}
        }
		
		
    }
}
