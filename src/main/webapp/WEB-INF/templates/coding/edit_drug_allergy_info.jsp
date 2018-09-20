<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
String baseInfoJson= (String)request.getAttribute("baseInfoJson");
String drugAllergyInfoJson= (String)request.getAttribute("drugAllergyInfoJson");
String respondResultJson= (String)request.getAttribute("respondResultJson");
%>
<link rel="stylesheet" href="${basePath}assets/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" href="${basePath}assets/jquery-ui/jquery-ui.css"/>
<link rel="stylesheet" href="${basePath}assets/css/basic.css"/>
<link rel="stylesheet" href="${basePath}assets/css/coding/edit_index_info.css"/>
<script type="text/javascript" src="${basePath}assets/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}assets/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${basePath}assets/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${basePath}assets/layer/layer.js"></script>
<script type="text/javascript" src="${basePath}assets/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${basePath}assets/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath}assets/jqueryValidate/localization/messages_zh.js"></script>
<script type="text/javascript" src="${basePath}assets/js/coding/edit_drug_allergy_info.js"></script>
<form action="${basePath }medical_record_coding/edit_drug_allergy_info" style="height:auto;width: 100%" method="post" id="drugAllergyInfoForm">
	<input type="hidden" id="visitGuid" name="visitGuid" value="${visitGuid }">
	<input type="hidden" id="drugAllergyRecordCount" value="0"/>
	
	<table style="width: 100%;" id="drugAllergyRecords" >
		<thead>
			<tr>
				<td colspan="6" class="tdLabel_7">

					<div style="float:right">
						<span class="red" id="tsxx" style="display:none"> </span> 
						<input type="submit" value="保存" class="btn">&nbsp;
						<input type="button" value="新增" onclick="addDrugAllergyRecord()"class="btn">
					</div>
				</td>
			</tr>
			<tr>
				<td class="tdLabel_4">过敏源类型</td>
				<td class="tdLabel_4" colspan="4">过敏药物</td>
				<td class="tdLabel_4">操作</td>
			</tr>

		</thead>
		<tbody>
		</tbody>
	</table>
</form>

<div hidden="hidden">
	<table>
		<tr id="templateDrugAllergyTr">
			<td class="tdLabel_8">
				<select name="drugAllergyRecords[0].drugAllergyTypeCode">
				</select>
				<input type="hidden" name="drugAllergyRecords[0].drugAllergyTypeName"/>
			</td>
			<td class="tdLabel_8" colspan="4">
				<input type="text" class="input_box" name="drugAllergyRecords[0].drugAllergyName"/>
			</td>
			<td class="tdLabel_8">
				<a class="btn" style="padding:4px;margin:0 auto; display: block; position: relative; left:-25%;" onclick="deleteDrugAllergyRecord(this)">删除</a>
			</td>
		</tr>
	</table>
</div>

<script>
	$(function(){
		var respondResultJson = <%=respondResultJson%>
		if(respondResultJson){
			if(respondResultJson.success){
				layer.msg("保存成功");
			}else{
				layer.msg("保存失败");
			}
		}
		var baseInfoJson= <%=baseInfoJson%>;
		var drugAllergyInfoJson= <%=drugAllergyInfoJson%>;
		console.log(baseInfoJson);
		console.log(drugAllergyInfoJson);
		 initPage(baseInfoJson,drugAllergyInfoJson);
		
	});
</script>