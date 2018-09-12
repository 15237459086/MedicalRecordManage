<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
String baseInfoJson= (String)request.getAttribute("baseInfoJson");
String intensiveCareInfoJson= (String)request.getAttribute("intensiveCareInfoJson");
%>
<link rel="stylesheet" href="${basePath}assets/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" href="${basePath}assets/jquery-ui/jquery-ui.css"/>
<link rel="stylesheet" href="${basePath}assets/css/basic.css"/>
<link rel="stylesheet" href="${basePath}assets/css/coding/edit_index_info.css"/>
<script type="text/javascript" src="${basePath}assets/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}assets/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${basePath}assets/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${basePath}assets/layer/layer.js"></script>
<script type="text/javascript" src="${basePath}assets/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath}assets/jqueryValidate/jquery.validate.js"></script>

<script type="text/javascript" src="${basePath}assets/jqueryValidate/localization/messages_zh.js"></script>
<script type="text/javascript" src="${basePath}assets/js/coding/edit_intensive_care_info.js"></script>
<form action="${basePath }medical_record_coding/edit_intensive_care_info" style="height:auto;width: 100%" method="post" id="intensiveCareInfoForm">
	<input type="hidden" id="visitGuid" name="visitGuid" value="${visitGuid }">
	<input type="hidden" id="intensiveCareRecordCount" value="0"/>
	<table style="width: 100%; border: 0">
		<tbody>
			<tr>
				<td colspan="6" class="dark">
					<div style="float:right">
						<input type="submit" value="保存" class="btn">&nbsp;
					</div></td>
			</tr>
			<tr>
				<td class="tdLabel_7">ICU天数</td>
				<td class="tdLabel_8"><input type="text" name="icuNurseDayNumber"
					class="input_box" style="width:150px"
					value="${intensiveCareInfo.icuNurseDayNumber}">
				</td>
				<td class="tdLabel_7">CCU天数</td>
				<td class="tdLabel_8"><input type="text" name="ccuNurseDayNumber" class="input_box"
					style="width:150px"
					value="${intensiveCareInfo.ccuNurseDayNumber}">
				</td>
			</tr>
		</tbody>
	</table>
	<table style="width: 100%; border: 0">
		<tbody>
			<tr>
				<td colspan="7" style="border:none;float: right;">
					<input type="button" value="删除" class="btn" onclick="deleteIntensiveCareRecord()" >
					<input type="button" value="新增" onclick="addIntensiveCareRecord()" class="btn">
					</td>
			</tr>
		</tbody>
	</table>
	<div class="bg_danlanColor">
		<ul class="bg_danlanColor margin0 clearfix" id="intensive_care_ul">

		</ul>
	</div>
	<div class="content" id="intensiveCareRecords" >
		
	</div>
</form>
<div hidden="hidden">
	<ul id="template_intensive_care_ul">
		<li data-index="0">记录1</li>
	</ul>
	
	<div class="layout" id="templateIntensiveCare">
		<table style="width: 100%; border: 0">
			<tbody>
				<tr>
					<td class="tdLabel_7" style="width: 20%">ICU类型</td>
					<td class="tdLabel_8" width="22%">
						<select name="intensiveCareRecords[0].icuTypeCode" class="ICUType">
						</select> 
						<input type="hidden" name="intensiveCareRecords[0].icuTypeName"/>
					</td>
					<td class="tdLabel_7" width="7%">进入时间</td>
					<td class="tdLabel_8" width="22%">
						<input type="text" name="intensiveCareRecords[0].inIcuDateTime"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="input_box">
					</td>
					<td class="tdLabel_7" width="7%">退出时间</td>
					<td class="tdLabel_8" width="22%">
						<input type="text" name="intensiveCareRecords[0].outIcuDateTime"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="input_box">
					</td>
				</tr>
				<tr>
					<td class="tdLabel_7">计划再次入住ICU</td>
					<td class="tdLabel_8">
						<input type="radio" class="radio_cl"
									name="intensiveCareRecords[0].reIcuPlanCode"
									value="1">是
						<input type="radio" class="radio_cl"
									name="intensiveCareRecords[0].reIcuPlanCode"
									value="0">否
						<input type="hidden" name="intensiveCareRecords[0].reIcuPlanName"/>
					</td>
					<td class="tdLabel_7">APACHEII评分</td>
					<td class="tdLabel_8"><input type="text"
						name="intensiveCareRecords[0].apacheScore"
						class="input_box" style="width: 150px">
					</td>
					<td class="tdLabel_7">患者死亡</td>
					<td class="tdLabel_8">
						<input type="radio" class="radio_cl" name="intensiveCareRecords[0].icuDeathCode"
						value="1">是
						<input type="radio" class="radio_cl" name="intensiveCareRecords[0].icuDeathCode"
						value="0">否
						<input type="hidden" name="intensiveCareRecords[0].icuDeathName"/>
					</td>
				</tr>
				<tr>
					<td class="tdLabel_7">计划再次入住原因</td>
					<td class="tdLabel_8" colspan="5"><textarea
							name="intensiveCareRecords[0].reIcuComment"
							style="width: 625px; height: 47px;"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<script>
	$(function(){
		var baseInfoJson= <%=baseInfoJson%>;
		console.log(baseInfoJson);
		var intensiveCareInfoJson= <%=intensiveCareInfoJson%>;
		//console.log(intensiveCareInfoJson);
		
		initPage(baseInfoJson,intensiveCareInfoJson);
		
	})
</script>