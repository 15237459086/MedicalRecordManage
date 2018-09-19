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
<script type="text/javascript" src="${basePath}assets/js/coding/edit_infection_info.js"></script>
<form action="${basePath }medical_record_coding/edit_infection_info" style="height:auto;width: 100%" method="post" id="infectionInfoForm">
	<input type="hidden" id="visitGuid" name="visitGuid" value="${visitGuid }">
	<table style="width:100%;">
		<tbody>
			<tr>
				<td colspan="6" class="dark">
					<div style="float:right">
						<input type="submit" value="保存" class="btn">&nbsp;
					</div>
                </td>
			</tr>
			<tr>
				<td class="tdLabel_7">感染总次数</td>
				<td class="tdLabel_8">
					<input type="text" name="infectionTotalTimes" value="${infectionInfo.infectionTotalTimes}">
				</td>
				<td class="tdLabel_8" colspan="4"></td>
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