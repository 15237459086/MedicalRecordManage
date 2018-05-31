<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
String baseInfoJson= (String)request.getAttribute("baseInfoJson");
String operateInfoJson= (String)request.getAttribute("operateInfoJson");
String respondResultJson= (String)request.getAttribute("respondResultJson");
%>
<link rel="stylesheet" href="${basePath}assets/css/basic.css"/>
<link rel="stylesheet" href="${basePath}assets/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" href="${basePath}assets/jquery-ui/jquery-ui.css"/>
<link rel="stylesheet" href="${basePath}assets/css/coding/edit_index_info.css"/>

<script type="text/javascript" src="${basePath}assets/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}assets/layer/layer.js"></script>
<script type="text/javascript" src="${basePath}assets/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${basePath}assets/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${basePath}assets/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="${basePath}assets/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${basePath}assets/jqueryValidate/localization/messages_zh.js"></script>
<script type="text/javascript" src="${basePath}assets/js/coding/edit_operate_info.js"></script>
<input type="hidden" id="basePath" value="${basePath }"/>
<form action="${basePath}medical_record_coding/edit_operate_info" method="post" id="operateInfoForm" style="width: 100%">
	<input type="hidden" name="visitGuid" value="${visitGuid }">
	<table style="width: 100%" cellspacing="0" cellpadding="0">
		<tbody>
			<tr>
				<td class="tdLabel_7">围手术期</td>
				<td colspan="3" class="tdLabel_8" style="border-right: 0px">
				<input type="text" name="periOperatePeriodStartDate" value="${operateInfo.periOperatePeriodStartDate}"
					onfocus="WdatePicker()" class="input_box" style="width:150px">--<input
					type="text" name="periOperatePeriodEndDate" value="${operateInfo.periOperatePeriodEndDate}"
					onfocus="WdatePicker()" class="input_box" style="width:150px">
				</td>
				<td colspan="3">
				<div style="float:right">
					<input type="button" value="删除" onclick="deleteOperateRecord()" class="btn">
					<input type="button" value="新增" onclick="addOperateRecord()" class="btn">
					<input type="submit" value="保存" class="btn"></div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="bg_danlanColor clearfix" style="color:#333">
		<ul class="margin0 clearfix" id="operate_record_ul">
		</ul>
	</div>
	<input type="hidden" id="operationCount" value="0"/>
	<div class="content" id="operation_records">
		
		
	</div>
</form>
<div hidden="hidden">
	<ul id="template_operate_ui">
		<li data-index="0">记录1</li>
	</ul>
	
	<div class="padding0px" id="templateOperateRecord">
		<table width="100%" cellspacing="0" border="0" cellpadding="0" class="formTable2">
			<tbody>
				<tr>
					<td class="tdLabel_7">开始时间</td>
					<td colspan="2" class="tdLabel_8"><input type="text"
						name="operateRecords[0].operateStartDate"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						class="input_box" style="width: 250px"
						value="">
					</td>
					<td class="tdLabel_7">结束时间</td>
					<td colspan="2" class="tdLabel_8"><input type="text"
						name="operateRecords[0].operateEndDate"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						class="input_box" style="width: 250px"
						value="">
					</td>
				</tr>
				<tr>
					<td class="tdLabel_7">手术原始描述</td>
					<td colspan="2" class="tdLabel_8">
						<input type="text" readonly="readonly" class="input_box" name="operateRecords[0].operateOriginalDesc">
						<input type="hidden" name="operateRecords[0].operateOriginalCode">
					</td>
					<td class="tdLabel_7">手术及操作</td>
					<td colspan="2" class="tdLabel_8">
						<input type="text" class="input_box" name="operateRecords[0].operateDescShow">
						<input type="hidden" name="operateRecords[0].operateCode">
						<input type="hidden" name="operateRecords[0].operateName">
					</td>
					
				</tr>
				
				<tr>
					<td class="tdLabel_7">切口等级</td>
					<td class="tdLabel_8">
						<select name="operateRecords[0].incisionLevelCode">
						</select> 
						<input type="hidden" name="operateRecords[0].incisionLevelName"/>
					</td>
					<td class="tdLabel_7">愈合类别</td>
					<td class="tdLabel_8">
						<select name="operateRecords[0].cicatrizeTypeCode">
						</select> 
						<input type="hidden" name="operateRecords[0].cicatrizeTypeName"/>
					</td>
					<td class="tdLabel_7">手术级别</td>
					<td class="tdLabel_8">
						<select name="operateRecords[0].opsLevelCode">
						</select>
						<input type="hidden" name="operateRecords[0].opsLevelName" />
					</td>
				</tr>
				
				<tr>
					<td class="tdLabel_7">麻醉分级</td>
					<td class="tdLabel_8">
						<select name="operateRecords[0].anaesthesiaLevelCode">
						</select>
						<input type="hidden" name="operateRecords[0].anaesthesiaLevelName"/>
					</td>
					<td class="tdLabel_7" colspan="1">麻醉方式</td>
					<td class="tdLabel_8">
						<select name="operateRecords[0].anaesthesiaTypeCode">
						</select>
						<input type="hidden" name="operateRecords[0].anaesthesiaTypeName" />
						
					</td>
					<td class="tdLabel_7">麻醉医师</td>
					<td class="tdLabel_8">
						<input type="hidden" name="operateRecords[0].operateWorkers[0].professionTitleCode" value="mzys"/>
						<input type="hidden" name="operateRecords[0].operateWorkers[0].professionTitleName" value="麻醉医师"/>
						<input type="text" name="operateRecords[0].operateWorkers[0].medicalWorkerName"/>
						<input type="hidden" name="operateRecords[0].operateWorkers[0].medicalWorkerCode"/>
					</td>
				</tr>
				<tr>
					<td class="tdLabel_7">手术医师</td>
					<td class="tdLabel_8">
						<input type="hidden" name="operateRecords[0].operateWorkers[1].professionTitleCode" value="ssys"/>
						<input type="hidden" name="operateRecords[0].operateWorkers[1].professionTitleName" value="手术医师"/>
						<input type="text" name="operateRecords[0].operateWorkers[1].medicalWorkerName"/>
						<input type="hidden" name="operateRecords[0].operateWorkers[1].medicalWorkerCode"/>
					</td>
					<td class="tdLabel_7">Ⅰ助</td>
					<td class="tdLabel_8">
						<input type="hidden" name="operateRecords[0].operateWorkers[2].professionTitleCode" value="dyzs"/>
						<input type="hidden" name="operateRecords[0].operateWorkers[2].professionTitleName" value="第一助手"/>
						<input type="text" name="operateRecords[0].operateWorkers[2].medicalWorkerName"/>
						<input type="hidden" name="operateRecords[0].operateWorkers[2].medicalWorkerCode"/>
					</td>
					<td class="tdLabel_7">Ⅱ助</td>
					<td class="tdLabel_8">
						<input type="hidden" name="operateRecords[0].operateWorkers[3].professionTitleCode" value="dszs"/>
						<input type="hidden" name="operateRecords[0].operateWorkers[3].professionTitleName" value="第二助手"/>
						<input type="text" name="operateRecords[0].operateWorkers[3].medicalWorkerName"/>
						<input type="hidden" name="operateRecords[0].operateWorkers[3].medicalWorkerCode"/>
					</td>
				</tr>
				<tr>
					<td class="tdLabel_7">位序</td>
						
					<td class="tdLabel_8">
						<input type="text" name="operateRecords[0].operateSortIndex" />
					</td>
				</tr>
				
			</tbody>
								
		</table>
	</div>
</div>

<script>
	$(function(){
		var baseInfoJson= <%=baseInfoJson%>;
		var operateInfoJson= <%=operateInfoJson%>;
		
		console.log(baseInfoJson);
		console.log(operateInfoJson);
		initPage(baseInfoJson,operateInfoJson);
		var respondResultJson= <%=respondResultJson%>;
		/* console.log(respondResultJson); */
		if(respondResultJson){
			if(respondResultJson.success){
				if(respondResultJson.stateCode = "200"){
					layer.msg(respondResultJson.stateMessage);
				}
			}else{
				if(respondResultJson.stateCode = "500"){
					layer.msg(respondResultJson.stateMessage);
				}
			}
		}
	});
</script>