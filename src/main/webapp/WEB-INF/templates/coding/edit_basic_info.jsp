<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath); 
String baseInfoJson= (String)request.getAttribute("baseInfoJson");
String basicInfoJson= (String)request.getAttribute("basicInfoJson");
String respondResultJson= (String)request.getAttribute("respondResultJson");
%>
<link rel="stylesheet" href="${basePath}assets/bootstrap/css/bootstrap.css"/> 
<link rel="stylesheet" href="${basePath}assets/jquery-ui/jquery-ui.css"/>   
<link rel="stylesheet" href="${basePath}assets/css/coding/edit_index_info.css"/>
<link rel="stylesheet" href="${basePath}assets/css/basic.css"/>
<script type="text/javascript" src="${basePath}assets/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}assets/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${basePath}assets/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${basePath}assets/layer/layer.js"></script>
<script type="text/javascript" src="${basePath}assets/js/header.js"></script> 

<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>
<script type="text/javascript" src="${basePath}assets/My97DatePicker/WdatePicker.js"></script>
<script src="${basePath}assets/distpicker/js/distpicker.data.js"></script>
<script src="${basePath}assets/distpicker/js/distpicker.js"></script>
<script src="${basePath}assets/distpicker/js/main.js"></script>
<script type="text/javascript" src="${basePath}assets/jqueryValidate/jquery.validate.js"></script>

<script type="text/javascript" src="${basePath}assets/jqueryValidate/localization/messages_zh.js"></script>
<script type="text/javascript" src="${basePath}assets/js/coding/edit_basic_info.js"></script>
<input type="hidden" id="basePath" value="${basePath }">
<form action="${basePath}medical_record_coding/edit_basic_info"
	method="post" id="basicInfoForm">
<input type="hidden" name="visitGuid" value="${visitGuid }">
<table style="width:99.5%;">
<tbody>
	<tr>
		<td colspan="6"><input type="submit" value="保存" class="btn" disabled="disabled">
				</td>
			</tr>
			<tr>
				<td class="tdLabel_7 redColor">住院号</td>
				<td class="tdLabel_8">
					<input type="text" name="onlyId" value="${basicInfo.onlyId }"/>
				</td>

				<td class="tdLabel_7 redColor">病案号</td>
				<td class="tdLabel_8">
					<input type="text" name="mrId" value="${basicInfo.mrId }" />
				</td>
				<td class="tdLabel_7 redColor">住院次数</td>
				<td class="tdLabel_8">
					<input type="text" name="visitNumber" value="${basicInfo.visitNumber }" />
				</td>
			</tr>
			<tr>
				

				<td class="tdLabel_7 redColor">付费方式</td>
				<td class="tdLabel_8">
					<select name="medicalPayTypeCode">
						
					</select>
					<input type="hidden" name="medicalPayTypeName"/>
				</td>
				<td class="tdLabel_7">社保账号</td>
				<td class="tdLabel_8">
					<input type="text" name="medicalInsuranceNumber" value="${basicInfo.medicalInsuranceNumber}"/>
				</td>
				<td class="tdLabel_7">健康卡号</td>
				<td class="tdLabel_8">
					<input type="text" name="medicalHealthNumber" value="${basicInfo.medicalHealthNumber}"/>
				</td>
			</tr>
			<tr>
				<td class="tdLabel_7 redColor">姓名</td>
				<td class="tdLabel_8">
					<input type="text" name="patientName" value="${basicInfo.patientName}" />
				</td>

				<td class="tdLabel_7">证件类型</td>
				<td class="tdLabel_8">
					<select name="documentTypeCode">
						
					</select>
					<input type="hidden" name="documentTypeName"/>
				</td>
				<td class="tdLabel_7">证件号</td>
				<td class="tdLabel_8">
					<input type="text" name="idNumber" value="${basicInfo.idNumber }"/>
				</td>
			</tr>
			<tr>
				<td class="tdLabel_7 redColor">性别</td>
				<td class="tdLabel_8">
					<select name="sexCode">
						
					</select>
					<input type="hidden" name="sexName" />
				</td>

				<td class="tdLabel_7 redColor">出生日期</td>
				<td class="tdLabel_8"><input type="text"
					onFocus="WdatePicker()" name="birthday" value="${basicInfo.birthday }" />
				</td>
				<td class="tdLabel_7 redColor">年龄</td>
				<td class="tdLabel_8">
					<input type="text" name="yearOfAge" value="${basicInfo.yearOfAge }"/>
				</td>
			</tr>
			<tr>
				<td class="tdLabel_7 redColor">国籍</td>
				<td class="tdLabel_8">
					<select name="nationalityCode">
						
					</select>
					<input type="hidden" name="nationalityName"/>
				</td>

				
				<td class="tdLabel_7 redColor">民族</td>
				<td class="tdLabel_8">
					<select name="nationCode">
						
					</select>
					<input type="hidden" name="nationName"/>
				</td>
				<td class="tdLabel_7 redColor">婚姻</td>
				<td class="tdLabel_8">
					<select name="marriageCode">
						
					</select>
					<input type="hidden" name="marriageName" />
				</td>
			</tr>
			<tr>
				<td class="tdLabel_7 redColor">职业</td>
				<td class="tdLabel_8">
					<select name="professionCode">
						
					</select>
					<input type="hidden" name="professionName" />
				</td>
				<td class="tdLabel_7">联系电话</td>
				<td class="tdLabel_8">
					<input type="text" name="telePhone" value="${basicInfo.telePhone }"/>
				</td>
			</tr>
			<tr>
				<td class="tdLabel_7">籍贯</td>
				<td class="tdLabel_8" colspan="5">
					<span class="redColor">地址</span>&nbsp;&nbsp;<input type="text" name="nativePlace"  style="width: 95%;" value="${basicInfo.nativePlace}"/>
				</td>
				
			</tr>
			<%-- <tr>
				<td class="tdLabel_7" rowspan="2">籍贯</td>
				<td class="tdLabel_8" colspan="5">
					<div id="nativePlaceRegionalismDiv">
						&nbsp;&nbsp;<label for="province10">省：</label>
						<select name="nativePlaceRegionalism.provinceName" id="province10" data-address></select>
						&nbsp;&nbsp;&nbsp;&nbsp;<label for="city10">市：</label>
						<select name="nativePlaceRegionalism.cityName" id="city10" data-address></select>
						&nbsp;&nbsp;&nbsp;&nbsp;<label for="district10">县：</label>
						<select name="nativePlaceRegionalism.countyName" id="district10" data-address></select>
					</div>
				</td>
				
			</tr>
			<tr>
				<td class="tdLabel_8" colspan="5">
					地址&nbsp;&nbsp;<input type="text" name="nativePlace"  style="width: 95%;" value="${basicInfo.nativePlace}"/>
				</td>
			</tr>
			 --%>
			<tr>
				<td class="tdLabel_7">出生地</td>
				<td class="tdLabel_8" colspan="3">
					地址&nbsp;&nbsp;<input type="text" name="birthAddress"  style="width: 90%;" value="${basicInfo.birthAddress}"/>
				</td>
				<td class="tdLabel_7">邮编</td>
				<td class="tdLabel_8" ><input type="text"
					name="birthAddressPostCode"
					value="${basicInfo.birthAddressPostCode}">
				</td>
			</tr>
			
			<tr>
				<td class="tdLabel_7">户口</td>
				<td class="tdLabel_8" colspan="3">
					地址&nbsp;&nbsp;<input type="text" name="registeredAddress"  style="width: 90%;" value="${basicInfo.registeredAddress}"/>
				</td>
				<td class="tdLabel_7">邮编</td>
				<td class="tdLabel_8" ><input type="text"
					name="registeredAddressPostCode"
					value="${basicInfo.registeredAddressPostCode}">
				</td>
			</tr>
			
			<tr>
				<td class="tdLabel_7" rowspan="2">现/常住地</td>
				<td class="tdLabel_8" colspan="5">地址 
					<input type="text" name="permanentAddress" style="width: 500px" value="${basicInfo.permanentAddress}">
				</td>
			</tr>
			<tr>
				<td class="tdLabel_8">电话 <input type="text"
					name="permanentAddressPhone" style="width:170px" value="${basicInfo.permanentAddressPhone}">
				</td>
				<td class="tdLabel_7">移动电话</td>
				<td class="tdLabel_8"><input type="text" name="permanentAddressMobilePhone"
					style="width:150px" value="${basicInfo.permanentAddressMobilePhone}">
				</td>
				<td class="tdLabel_7">邮编</td>
				<td class="tdLabel_8" ><input type="text"
					name="permanentAddressPostCode" style="width:150px"
					value="${basicInfo.permanentAddressPostCode}">
				</td>
			</tr>
			
			<tr>
				<td class="tdLabel_7" rowspan="2">工作单位</td>
				<td class="tdLabel_8" colspan="3">地址： <input
					type="text" name="workUnitAddress" style="width: 500px"
					value="${basicInfo.workUnitAddress }">
				</td>
				
				<td class="tdLabel_7">电话</td>
				<td class="tdLabel_8"><input type="text"
					name="workUnitPhone" value="${basicInfo.workUnitPhone }">
				</td>
			</tr>
			<tr>
				<td class="tdLabel_8" colspan="3">单位名：<input type="text" style="width: 490px"
					name="workUnitName" value="${basicInfo.workUnitName }">
				</td>
				<td class="tdLabel_7">邮编</td>
				<td class="tdLabel_8"><input type="text"
					name="workUnitPostCode" value="${basicInfo.workUnitPostCode}">
				</td>
			</tr>
			<tr>
				<td class="tdLabel_7" rowspan="2">联系人</td>
				<td class="tdLabel_8"><span class="redColor">姓名</span> 
				<input type="text"
					name="linkManName" style="width: 150px"
					value="${basicInfo.linkManName}">
				</td>
				<td class="tdLabel_7 redColor" >与病人关系</td>
				<td class="tdLabel_8">
					<select name="linkManRelativeRelationCode">
						
					</select>
					<input type="hidden" name="linkManRelativeRelationName" />
				</td>
				<td class="tdLabel_7 redColor">电话</td>
				<td class="tdLabel_8"><input type="text"
					name="linkManPhone" value="${basicInfo.linkManPhone}">
				</td>

			</tr>
			<tr>
				<td class="tdLabel_8" colspan="3">地址： <input
					type="text" name="linkManAddress" style="width: 500px"
					value="${basicInfo.linkManAddress}">
				</td>
				<td class="tdLabel_7">邮编</td>
				<td class="tdLabel_8"><input type="text"
					name="linkManAddressPostCode" style="width: 150px;"
					value="${basicInfo.linkManAddressPostCode}">
				</td>
			</tr>

			<tr>
				<td class="tdLabel_7">接诊日期</td>
				<td class="tdLabel_8">
					<input type="text"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="clinicalReceptionDateTime"
					value="${basicInfo.clinicalReceptionDateTime }" /></td>

				<td class="tdLabel_7">门诊医生</td>
				<td class="tdLabel_8"><input type="text" name="outpatientOfDoctorName"
					data-type="medicalWorker" value="${basicInfo.outpatientOfDoctorName }"/>
					<input type="hidden" name="outpatientOfDoctorCode" value="${basicInfo.outpatientOfDoctorCode }"/>
				</td>

				<td class="tdLabel_7">经办人</td>
				<td class="tdLabel_8"><input type="text" name="responsiblePersonName"
					data-type="medicalWorker" value="${basicInfo.responsiblePersonName }">
					<input type="hidden" name="responsiblePersonCode" value="${basicInfo.responsiblePersonCode }">
				</td>
			</tr>
			<tr>
				<td class="tdLabel_7 redColor">入院途径</td>
				<td class="tdLabel_8">
					<select name="inHospitalTypeCode" >
						
					</select>
					<input type="hidden" name="inHospitalTypeName" />
				</td>
				<td class="tdLabel_7">转入机构</td>
				<td class="tdLabel_8" colspan="3">
					<input type="text" style="width: 80%" name="shiftToUnitName" value="${basicInfo.shiftToUnitName}">
				</td>
			</tr>
			<tr>
				<td class="tdLabel_7">入院状况</td>
				<td class="tdLabel_8">
				<select name="inHospitalStateCode">
						
					</select>
					<input type="hidden" name="inHospitalStateName" />
				</td>
				<td class="tdLabel_7">颅脑损伤昏迷时间</td>
				<td class="tdLabel_8">
				 <input type="text" name="craniocerebralInjuryAndComaDateTime"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					value="${basicInfo.craniocerebralInjuryAndComaDateTime}"></td>
				<td class="tdLabel_7">诊断确诊时间</td>
				<td class="tdLabel_8">
				 <input type="text" name="diagConfirmedDateTime"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					value="${basicInfo.diagConfirmedDateTime}" style="width: 150px">
				</td>
			</tr>
			
			<tr>
				<td class="tdLabel_7 redColor">入院科室</td>
				<td class="tdLabel_8">
					<select name="inDeptCode" id="inDeptCode">
						
					</select>
					<input type="hidden" name="inDeptName"/>
				</td>
				<td class="tdLabel_7 redColor">入院时间</td>
				<td class="tdLabel_8">
					<input type="text" name="inHospitalDateTime"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					value="${basicInfo.inHospitalDateTime}"></td>

				<td class="tdLabel_7">诊疗科目</td>
				<td class="tdLabel_8">
				<input type="text" name="diagSubjectName"
					value="${basicInfo.diagSubjectName}">
				</td>
				<tr>
					<td class="tdLabel_7">转科描述</td>
					<td class="tdLabel_8" colspan="5">
						<input type="text" style="width: 95%;" name="changeDeptDesc" value="${basicInfo.changeDeptDesc}" />
					</td>
				</tr>
				<tr>
					<td class="tdLabel_7">过敏药物描述</td>
					<td class="tdLabel_8" colspan="5">
						<input type="text" style="width: 95%;" name="drugAllergyDesc" value="${basicInfo.drugAllergyDesc}" />
					</td>
				</tr>
				<tr>
					<td class="tdLabel_7">输血描述</td>
					<td class="tdLabel_8" colspan="5">
						<input type="text" style="width: 95%;" name="infusionBloodDesc" value="${basicInfo.infusionBloodDesc}" />
					</td>
				</tr>
				<tr>
				<td class="tdLabel_7">病理号</td>
				<td class="tdLabel_8">
					
					<input type="text" name="pathologyNumber" value="${basicInfo.pathologyNumber}" />
				</td>
				<td class="tdLabel_7">再住院计划</td>
				<td class="tdLabel_8">
					<select name="rehospitalAimCode">
						
					</select>
					<input type="hidden" name="rehospitalAimName"/> 间隔 
					<input type="text" style="width:30px"
					name="rehospitalIntervalDayNumber"
					value="${basicInfo.rehospitalIntervalDayNumber }"> 天</td>
				</tr>
				<tr>
				<td class="tdLabel_7 redColor">出院科室</td>
				<td class="tdLabel_8">
					<select name="outDeptCode" id="outDeptCode" title=''>
						
					</select>
					<input type="hidden" name="outDeptName"  value=""/>
				</td>
				<td class="tdLabel_7 redColor">出院时间</td>
				<td class="tdLabel_8">
					<input type="text" name="outHospitalDateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					value="${basicInfo.outHospitalDateTime}"></td>
				<td class="tdLabel_7 redColor">住院天数</td>
				<td class="tdLabel_8">
					<input type="text" name="inHospitalDayNumber" value="${basicInfo.inHospitalDayNumber}" />
				</td>
					
			</tr>

			<tr>
				<td class="tdLabel_7 redColor">离院方式</td>
				<td class="tdLabel_8">
					<select name="outHospitalTypeCode">
						
					</select>
					<input type="hidden" name="outHospitalTypeName" />
				</td>
				<td class="tdLabel_7">拟接收机构</td>
				<td class="tdLabel_8" colspan="3">
				<input type="text" style="width: 80%;" name="receiveUnitName" value="${basicInfo.receiveUnitName }">
				</td>

			</tr>
			<tr>
				<td class="tdLabel_7">死亡时间</td>
				<td class="tdLabel_8">
					<input type="text" name="dealthDateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					value="${basicInfo.dealthDateTime}"></td>
				<td class="tdLabel_7">死亡原因</td>
				<td class="tdLabel_8">
					<select name="dealthReasonCode">
						
					</select>
					<input type="hidden" name="dealthReasonName"/>
				</td>
				<td class="tdLabel_7">尸检</td>
				<td class="tdLabel_8" colspan="3">
					<input type="radio" name="autopsyCode" value="1" title="是">
                        			 是
                    <input type="radio" name="autopsyCode" value="0" title="否" >
                        			 否
                    <input type="hidden" name="autopsyName" value="">
              	</td>
			</tr>
		</tbody>
	</table>
</form>

<script>
	$(function(){
		var baseInfoJson= <%=baseInfoJson%>;
		var basicInfoJson = <%=basicInfoJson%>
		var respondResultJson = <%=respondResultJson%>
		if(respondResultJson){
			if(respondResultJson.success){
				layer.msg("保存成功");
			}else{
				layer.msg("保存失败");
			}
		}
		console.log(baseInfoJson);
		console.log(basicInfoJson);
		initPage(baseInfoJson,basicInfoJson);
		
	});
</script>