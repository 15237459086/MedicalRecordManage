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
	
	
	public static void exportExcelOfPageIndex(OutputStream out,List<Map<String, Object>> datas) {

		try {

			// 创建一个工作薄
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建一个sheet页
			HSSFSheet sheet = workbook.createSheet();
			
			HSSFRow row = null;
			HSSFCell cell = null;
			
			// 创建第一行，生成表格的表头行
			row = sheet.createRow(0);// 第一行
			String[] headers = 
				{ "患者姓名","患者性别代码","患者性别名称","出生日期",
				"年龄（年）", "年龄（月）","患者婚姻代码","患者婚姻名称",
				"职业代码","职业名称","出生地","出生地邮编","联系电话",
				"国籍代码","国籍名称","民族代码","民族名称","籍贯",
				"户口地址","户口邮编","证件类型代码","证件类型名称","证件号码",
				"病案号","住院次数"};
			for (int i = 0; i < headers.length; i++) {
				cell = row.createCell(i);// 从第一列开始
				cell.setCellValue(headers[i]);
			}
			int i = 0;
			for (Map<String, Object> data : datas) {
				Map<String, Object> jsonMap = (Map<String, Object>)data.get("jsonMap");
				row = sheet.createRow(1 + i);// 从第二行去写数据
				if(jsonMap != null){
					Map<String, Object> basicInfo = (Map<String, Object>)jsonMap.get("basicInfo");
					int colIndex = 0;
					//1
					cell = row.createCell(colIndex);
					Object patientName = basicInfo.get("patientName");
					setCellValue(patientName,cell);
					colIndex ++;
					//2
					cell = row.createCell(colIndex);
					Object sexCode = basicInfo.get("sexCode");
					setCellValue(sexCode,cell);
					colIndex ++;
					//3
					cell = row.createCell(colIndex);
					Object sexName = basicInfo.get("sexName");
					setCellValue(sexName,cell);
					colIndex ++;
					//4
					cell = row.createCell(colIndex);
					Object birthday = basicInfo.get("birthday");
					setCellValue(birthday,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object yearOfAge = basicInfo.get("yearOfAge");
					setCellValue(yearOfAge,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object monthOfAge = basicInfo.get("monthOfAge");
					setCellValue(monthOfAge,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object marriageCode = basicInfo.get("marriageCode");
					setCellValue(marriageCode,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object marriageName = basicInfo.get("marriageName");
					setCellValue(marriageName,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object professionCode = basicInfo.get("professionCode");
					setCellValue(professionCode,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object professionName = basicInfo.get("professionName");
					setCellValue(professionName,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object birthAddress = basicInfo.get("birthAddress");
					setCellValue(birthAddress,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object birthAddressPostCode = basicInfo.get("birthAddressPostCode");
					setCellValue(birthAddressPostCode,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object telePhone = basicInfo.get("telePhone");
					setCellValue(telePhone,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object nationalityCode = basicInfo.get("nationalityCode");
					setCellValue(nationalityCode,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object nationalityName = basicInfo.get("nationalityName");
					setCellValue(nationalityName,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object nationCode = basicInfo.get("nationCode");
					setCellValue(nationCode,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object nationName = basicInfo.get("nationName");
					setCellValue(nationName,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object nativePlace = basicInfo.get("nativePlace");
					setCellValue(nativePlace,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object registeredAddress = basicInfo.get("registeredAddress");
					setCellValue(registeredAddress,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object registeredAddressPostCode = basicInfo.get("registeredAddressPostCode");
					setCellValue(registeredAddressPostCode,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object documentTypeCode = basicInfo.get("documentTypeCode");
					setCellValue(documentTypeCode,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object documentTypeName = basicInfo.get("documentTypeName");
					setCellValue(documentTypeName,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object idNumber = basicInfo.get("idNumber");
					setCellValue(idNumber,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object mrId = basicInfo.get("mrId");
					setCellValue(mrId,cell);
					colIndex ++;
					cell = row.createCell(colIndex);
					Object visitNumber = basicInfo.get("visitNumber");
					setCellValue(visitNumber,cell);
					/*for(int colIndex = 0; colIndex < keys.size(); colIndex++){
						cell = row.createCell(colIndex);
						Object value = data.get(keys.get(colIndex));
						if(value instanceof String){
							cell.setCellValue((String)value);
						}else if(value instanceof Integer){
							cell.setCellValue((Integer)value);
						}else if(value instanceof Date){
							cell.setCellValue(DateUtil.dateFormat((Date)value));
						}else{
							cell.setCellValue(value.toString());
						}
						
					}*/
				}
				
				i++;
			}

			workbook.write(out);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void setCellValue(Object obj,HSSFCell cell){
		if(obj instanceof String){
			cell.setCellValue((String)obj);
		}else if(obj instanceof Integer){
			cell.setCellValue((Integer)obj);
		}else if(obj instanceof Date){
			cell.setCellValue(DateUtil.dateFormat((Date)obj));
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
