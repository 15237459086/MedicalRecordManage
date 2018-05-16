package com.kurumi.util;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;


/**
 * ExcelUtil
 * @author lyh
 *
 */
public class ExcelUtil {

	public static void exportExcel(OutputStream out, String title,String[] headers,List<String> keys, List<Map<String, Object>> datas) {

		try {

			// 创建一个工作薄
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建一个sheet页
			HSSFSheet sheet = workbook.createSheet();
			// 设置sheet页的列宽
			sheet.setDefaultColumnWidth(20);

			// 设置标题样式
			HSSFCellStyle titleStyle = workbook.createCellStyle();
			// 设置字体居中显示
			titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 设置标题字体
			HSSFFont titleFont = workbook.createFont();
			titleFont.setFontHeightInPoints((short) 25);
			titleStyle.setFont(titleFont);

			// 产生标题
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);
			cell.setCellStyle(titleStyle);
			cell.setCellValue(title);
			// 合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));

			// 表头样式
			HSSFCellStyle headStyle = workbook.createCellStyle();
			headStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
			// 设置填充模式
			headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			// 设置字体居中
			headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			// 设置字体样式
			HSSFFont headFont = workbook.createFont();
			headFont.setColor(HSSFColor.LIGHT_ORANGE.index);
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置字体粗细
			headFont.setFontHeightInPoints((short) 16);
			headStyle.setFont(headFont);
			// 创建第二行，生成表格的表头行
			row = sheet.createRow(1);// 第二行
			for (int i = 0; i < headers.length; i++) {
				cell = row.createCell(i);// 从第一列开始
				cell.setCellStyle(headStyle);// 设置列的样式
				cell.setCellValue(headers[i]);
			}
			int i = 0;
			for (Map<String, Object> data : datas) {
				row = sheet.createRow(2 + i);// 从第三行去写数据
				
				for(int colIndex = 0; colIndex < keys.size(); colIndex++){
					cell = row.createCell(colIndex);
					Object value = data.get(keys.get(colIndex));
					if(value instanceof String){
						cell.setCellValue((String)value);
					}else if(value instanceof Integer){
						cell.setCellValue((Integer)value);
					}else if(value instanceof Date){
						cell.setCellValue(DateUtil.dateFormat((Date)value));
					}/*else{
						cell.setCellValue(value.toString());
					}*/
					
				}
				i++;
			}

			workbook.write(out);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 导出excel数据
	 * 
	 * @param filePath
	 * @return
	 */
	public static void exportExcel(OutputStream out, String title, String[] header, List<Map<String, Object>> mapList) {
		// 创建一个工作薄
		HSSFWorkbook workbook = null;
		try {

			// 创建一个工作薄
			workbook = new HSSFWorkbook();
			// 创建一个sheet页
			HSSFSheet sheet = workbook.createSheet();
			// 设置sheet页的列宽
			sheet.setDefaultColumnWidth(20);

			// 设置标题样式
			HSSFCellStyle titleStyle = workbook.createCellStyle();
			// 设置字体居中显示
			titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 设置标题字体
			HSSFFont titleFont = workbook.createFont();
			titleFont.setFontHeightInPoints((short) 25);
			titleStyle.setFont(titleFont);

			// 产生标题
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);
			cell.setCellStyle(titleStyle);
			cell.setCellValue(title);
			// 合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, header.length - 1));

			// 表头样式
			HSSFCellStyle headStyle = workbook.createCellStyle();
			headStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
			// 设置填充模式
			headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			// 设置字体居中
			headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			// 设置字体样式
			HSSFFont headFont = workbook.createFont();
			headFont.setColor(HSSFColor.LIGHT_ORANGE.index);
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置字体粗细
			headFont.setFontHeightInPoints((short) 16);
			headStyle.setFont(headFont);
			// 创建第二行，生成表格的表头行
			row = sheet.createRow(1);// 第二行
			for (int i = 0; i < header.length; i++) {
				cell = row.createCell(i);// 从第一列开始
				cell.setCellStyle(headStyle);// 设置列的样式
				cell.setCellValue(header[i]);
			}
			int i = 0;
			for (Map<String, Object> cellMap : mapList) {
				row = sheet.createRow(2 + i);// 从第三行去写数据
				int j = 0;
				for (Map.Entry<String, Object> entry : cellMap.entrySet()) {
					cell = row.createCell(j);
					Object obj = entry.getValue();
					if (obj instanceof Integer) {
						cell.setCellValue((Integer) entry.getValue());
					}
					if (obj instanceof Date) {
						cell.setCellValue(DateUtil.dateFormat((Date)entry.getValue()));
					}
					if (obj instanceof String) {
						cell.setCellValue((String) entry.getValue());
					}
					if (obj instanceof Double) {
						cell.setCellValue((Double) entry.getValue());
					}
					if (obj instanceof Float) {
						cell.setCellValue((Float) entry.getValue());
					}
					if (obj instanceof Long) {
						cell.setCellValue((Long) entry.getValue());
					}

					j++;
				}
				i++;
			}

			workbook.write(out);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
