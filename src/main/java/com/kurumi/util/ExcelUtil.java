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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


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
			XSSFWorkbook workbook = new XSSFWorkbook();
			// 创建一个sheet页
			XSSFSheet sheet = workbook.createSheet();
			
			XSSFRow row = null;
			XSSFCell cell = null;
			
			// 创建第一行，生成表格的表头行
			row = sheet.createRow(0);// 第一行
			String[] headers = 
				{"P900","P6891","P686","P800",
				"P1","P2","P3","P4",
				"P5","P6","P7","P8",
				"P9","P101","P102","P103",
				"P11","P12","P13","P801",
				"P802","P803","P14","P15",
				"P16","P17","P171","P18",
				"P19","P20","P804","P21",
				"P22","P23","P231","P24",
				"P25","P26","P261","P27",
				"P28","P281","P29","P30",
				"P301","P31","P321","P322",
				"P805","P323","P324","P325",
				"P806","P326","P327","P328",
				"P807","P329","P3291","P3292",
				"P808","P3293","P3294","P3295",
				"P809","P3296","P3297","P3298",
				"P810","P3299","P3281","P3282",
				"P811","P3283","P3284","P3285",
				"P812","P3286","P3287","P3288",
				"P813","P3289","P3271","P3272",
				"P814","P3273","P3274","P3275",
				"P815","P3276","P689","P351",
				"P352","P816","P353","P354",
				"P817","P355","P356","P818",
				"P361","P362","P363","P364",
				"P365","P366","P371","P372",
				"P38","P39","P40","P411",
				"P412","P413","P414","P415",
				"P421","P422","P687","P688",
				"P431","P432","P433","P434",
				"P819","P435","P436","P437",
				"P438","P44","P45","P46",
				"P47","P490","P491","P820",
				"P492","P493","P494","P495",
				"P496","P497","P498","P4981",
				"P499","P4910","P4911","P4912",
				"P821","P4913","P4914","P4915",
				"P4916","P4917","P4918","P4919",
				"P4982","P4920","P4921","P4922",
				"P4923","P822","P4924","P4925",
				"P4526","P4527","P4528","P4529",
				"P4530","P4983","P4531","P4532",
				"P4533","P4534","P823","P4535",
				"P4536","P4537","P4538","P4539",
				"P4540","P4541","P4984","P4542",
				"P4543","P4544","P4545","P824",
				"P4546","P4547","P4548","P4549",
				"P4550","P4551","P4552","P4985",
				"P4553","P4554","P45002","P45003",
				"P825","p45004","p45005","p45006",
				"p45007","p45008","p45009","p45010",
				"p45011","p45012","p45013","p45014",
				"p45015","P826","p45016","p45017",
				"p45018","p45019","p45020","p45021",
				"p45022","p45023","p45024","p45025",
				"p45026","p45027","P827","p45028",
				"p45029","p45030","p45031","p45032",
				"p45033","p45034","p45035","p45036",
				"p45037","p45038","p45039","P828",
				"p45040","p45041","p45042","p45043",
				"p45044","p45045","p45046","p45047",
				"p45048","p45049","p45050","p45051",
				"P829","p45052","p45053","p45054",
				"p45055","p45056","p45057","p45058",
				"p45059","p45060","p45061","P561",
				"P562","P563","P564","P6911",
				"P6912","P6913","P6914","P6915",
				"P6916","P6917","P6918","P6919",
				"P6920","P6921","P6922","P6923",
				"P6924","P6925","P57","P58",
				"P581","P60","P611","P612",
				"P613","P59","P62","P63",
				"P64","P651","P652","P653",
				"P654","P655","P656","P66",
				"P681","P682","P683","P684",
				"P685","P67","P731","P732",
				"P733","P734","P72","P830",
				"P831","P741","P742","P743",
				"P782","P751","P752","P754",
				"P755","P756","P757","P758",
				"P759","P760","P761","P762",
				"P763","P764","P765","P767",
				"P768","P769","P770","P771",
				"P772","P773","P774","P775",
				"P776","P777","P778","P779",
				"P780","P781"};
			String[] headers1 = 
				{ "医疗机构代码","机构名称","医疗保险手册（卡）号","健康卡号",
				"医疗付款方式","住院次数","病案号","姓名",
				"性别","出生日期","年龄","婚姻状况",
				"职业","出生省份","出生地市","出生地县",
				"民族","国籍","身份证号","现住址",
				"住宅电话","现住址邮政编码","工作单位及地址","电话",
				"工作单位邮政编码","户口地址","户口所在地邮政编码","联系人姓名",
				"关系","联系人地址","入院途径","联系人电话",
				"入院日期","入院科别","入院病室","转科科别",
				"出院日期","出院科别","出院病室","实际住院天数",
				"门（急）诊诊断编码","门（急）诊诊断描述","入院时情况","入院诊断编码",
				"入院诊断描述","入院后确诊日期","主要诊断编码","主要诊断疾病描述",
				"主要诊断入院病情","主要诊断出院情况","其他诊断编码1","其他诊断疾病描述1",
				"其他诊断入院病情1","其他诊断出院情况1","其他诊断编码2","其他诊断疾病描述2",
				"其他诊断入院病情2","其他诊断出院情况2","其他诊断编码3","其他诊断疾病描述3",
				"其他诊断入院病情3","其他诊断出院情况3","其他诊断编码4","其他诊断疾病描述4",
				"其他诊断入院病情4","其他诊断出院情况4","其他诊断编码5","其他诊断疾病描述5",
				"其他诊断入院病情5","其他诊断出院情况5","其他诊断编码6","其他诊断疾病描述6",
				"其他诊断入院病情6","其他诊断出院情况6","其他诊断编码7","其他诊断疾病描述7",
				"其他诊断入院病情7","其他诊断出院情况7","其他诊断编码8","其他诊断疾病描述8",
				"其他诊断入院病情8","其他诊断出院情况8","其他诊断编码9","其他诊断疾病描述9",
				"其他诊断入院病情9","其他诊断出院情况9","其他诊断编码10","其他诊断疾病描述10",
				"其他诊断入院病情10","其他诊断出院情况10","医院感染总次数","病理诊断编码1",
				"病理诊断名称1","病理号1","病理诊断编码2","病理诊断名称2",
				"病理号2","病理诊断编码3","病理诊断名称3","病理号3",
				"损伤、中毒的外部因素编码1","损伤、中毒的外部因素名称1","损伤、中毒的外部因素编码2","损伤、中毒的外部因素名称2",
				"损伤、中毒的外部因素编码3","损伤、中毒的外部因素名称3","过敏源","过敏药物名称",
				"HBsAg","HCV-Ab","HIV-Ab","门诊与出院诊断符合情况",
				"入院与出院诊断符合情况","术前与术后诊断符合情况","临床与病理诊断符合情况","放射与病理诊断符合情况",
				"抢救次数","抢救成功次数","最高诊断依据","分化程度",
				"科主任","主(副主)任医师","主治医师","住院医师",
				"责任护士","进修医师","研究生实习医师","实习医师",
				"编码员","病案质量","质控医师","质控护师",
				"质控日期","手术操作编码1","手术操作日期1","手术级别1",
				"手术操作名称1","手术操作部位1","手术持续时间1","术者1",
				"Ⅰ助1","Ⅱ助1","麻醉方式1","麻醉分级1",
				"切口愈合等级1","麻醉医师1","手术操作编码2","手术操作日期2",
				"手术级别2","手术操作名称2","手术操作部位2","手术持续时间2",
				"术者2","Ⅰ助2","Ⅱ助2","麻醉方式2",
				"麻醉分级2","切口愈合等级2","麻醉医师2","手术操作编码3",
				"手术操作日期3","手术级别3","手术操作名称3","手术操作部位3",
				"手术持续时间3","术者3","Ⅰ助3","Ⅱ助3",
				"麻醉方式3","麻醉分级3","切口愈合等级3","麻醉医师3",
				"手术操作编码4","手术操作日期4","手术级别4","手术操作名称4",
				"手术操作部位4","手术持续时间4","术者4","Ⅰ助4",
				"Ⅱ助4","麻醉方式4","麻醉分级4","切口愈合等级4",
				"麻醉医师4","手术操作编码5","手术操作日期5","手术级别5",
				"手术操作名称5","手术操作部位5","手术持续时间5","术者5",
				"Ⅰ助5","Ⅱ助5","麻醉方式5","麻醉分级5",
				"切口愈合等级5","麻醉医师5","手术操作编码6","手术操作日期6",
				"手术级别6","手术操作名称6","手术操作部位6","手术持续时间6",
				"术者6","Ⅰ助6","Ⅱ助6","麻醉方式6",
				"麻醉分级6","切口愈合等级6","麻醉医师6","手术操作编码7",
				"手术操作日期7","手术级别7","手术操作名称7","手术操作部位7",
				"手术持续时间7","术者7","Ⅰ助7","Ⅱ助7",
				"麻醉方式7","麻醉分级7","切口愈合等级7","麻醉医师7",
				"手术操作编码8","手术操作日期8","手术级别8","手术操作名称8",
				"手术操作部位8","手术持续时间8","术者8","Ⅰ助8",
				"Ⅱ助8","麻醉方式8","麻醉分级8","切口愈合等级8",
				"麻醉医师8","手术操作编码9","手术操作日期9","手术级别9",
				"手术操作名称9","手术操作部位9","手术持续时间9","术者9",
				"Ⅰ助9","Ⅱ助9","麻醉方式9","麻醉分级9",
				"切口愈合等级9","麻醉医师9","手术操作编码10","手术操作日期10",
				"手术级别10","手术操作名称10","手术操作部位10","手术持续时间10",
				"术者10","Ⅰ助10","Ⅱ助10","麻醉方式10",
				"麻醉分级10","切口愈合等级10","麻醉医师10","特级护理天数",
				"一级护理天数","二级护理天数","三级护理天数","重症监护室名称1",
				"进入时间1","退出时间1","重症监护室名称2","进入时间2",
				"退出时间2","重症监护室名称3","进入时间3","退出时间3",
				"重症监护室名称4","进入时间4","退出时间4","重症监护室名称5",
				"进入时间5","退出时间5","死亡患者尸检","手术、治疗、检查、诊",
				"断为本院第一例","手术患者类型","随诊","随诊周数",
				"随诊月数","随诊年数","示教病例","ABO血型",
				"Rh血型","输血反应","红细胞","血小板",
				"血浆","全血","自体回收","其它",
				"（婴幼儿）年龄","新生儿出生体重1","新生儿出生体重2","新生儿出生体重3",
				"新生儿出生体重4","新生儿出生体重5","新生儿入院体重","入院前多少小时(颅脑损伤患者昏迷时间)",
				"入院前多少分钟(颅脑损伤患者昏迷时间)","入院后多少小时(颅脑损伤患者昏迷时间)","入院后多少分钟(颅脑损伤患者昏迷时间)","呼吸机使用时间",
				"是否有出院31天内再住院计划","出院31天再住院计划目的","转入医院名称","转入社区服务机构/乡镇卫生院名称",
				"住院总费用","住院总费用其中自付金额","一般医疗服务费","一般治疗操作费",
				"护理费","综合医疗服务类其他费用","病理诊断费","实验室诊断费",
				"影像学诊断费","临床诊断项目费","非手术治疗项目费","临床物理治疗费",
				"手术治疗费","麻醉费","手术费","康复费",
				"中医治疗费","西药费","抗菌药物费用","中成药费",
				"中草药费","血费","白蛋白类制品费","球蛋白类制品费",
				"凝血因子类制品费","细胞因子类制品费","检查用一次性医用材料费","治疗用一次性医用材料费",
				"手术用一次性医用材料费","其他费"};
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
					/*Object patientName = basicInfo.get("patientName");*/
					setCellValue("XXXX",cell);
					colIndex ++;
					//2
					cell = row.createCell(colIndex);
					/*Object sexCode = basicInfo.get("sexCode");*/
					setCellValue("XX医院",cell);
					colIndex ++;
					//3
					cell = row.createCell(colIndex);
					Object medicalInsuranceNumber = basicInfo.get("medicalInsuranceNumber");
					setCellValue(medicalInsuranceNumber,cell);
					colIndex ++;
					//4
					cell = row.createCell(colIndex);
					Object medicalHealthNumber = basicInfo.get("medicalHealthNumber");
					setCellValue(medicalHealthNumber,cell);
					colIndex ++;
					//5
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
	
	
	public static void exportExcelOfPageIndex1(OutputStream out,List<Map<String, Object>> datas) {

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
	
	private static void setCellValue(Object obj,XSSFCell cell){
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
