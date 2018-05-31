<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
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

<script type="text/javascript" src="${basePath}assets/jqueryValidate/localization/messages_zh.js"></script>
<script type="text/javascript" src="${basePath}assets/js/coding/edit_nurse_info.js"></script>
<form action="${basePath }medical_record_coding/edit_nurse_info" style="height:auto" method="post" id="nurseInfoForm">
	<input type="hidden" id="visitGuid" name="visitGuid" value="${visitGuid }">
	<table style="width:100%;" cellspacing="0" cellpadding="0">
		<tbody>
			<tr>
				<td colspan="6" class="dark">
					<div style="float:right">
						<input type="submit" value="保存" class="btn">&nbsp;
					</div>
                </td>
			</tr>
			<tr>
				<td class="tdLabel_7">病危天数</td>
				<td class="tdLabel_8">
					<input type="text" name="criticalDayNumber" value="${nurseInfo.criticalDayNumber}">
				</td>
				<td class="tdLabel_7">病重天数</td>
				<td class="tdLabel_8">
					<input type="text" name="sickDayNumber" value="${nurseInfo.sickDayNumber}">
				</td>

				<td class="tdLabel_7">特级护理天数</td>
				<td class="tdLabel_8">
					<input type="text" name="specialNurseDayNumber"
					value="${nurseInfo.specialNurseDayNumber}">
				</td>

			</tr>
			<tr>
				<td class="tdLabel_7">一级护理天数</td>
				<td class="tdLabel_8"><input type="text" name="firstLevelNurseDayNumber"
					value="${nurseInfo.firstLevelNurseDayNumber}">
				</td>
				<td class="tdLabel_7">二级护理天数</td>
				<td class="tdLabel_8">
					<input type="text" name="secondLevelNurseDayNumber"
					value="${nurseInfo.secondLevelNurseDayNumber}">
				</td>
				<td class="tdLabel_7">三级护理天数</td>
				<td class="tdLabel_8"><input type="text" name="threeLevelNurseDayNumber"
					value="${nurseInfo.threeLevelNurseDayNumber}">
				</td>
			</tr>
		    
		</tbody>
	</table>
</form>

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
		initPage();
		
	});
</script>