<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
String costInfoJson= (String)request.getAttribute("costInfoJson");
String respondResultJson = (String)request.getAttribute("respondResultJson");
%>
<link rel="stylesheet" href="${basePath}assets/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" href="${basePath}assets/css/basic.css"/>
<link rel="stylesheet" href="${basePath}assets/jquery-ui/jquery-ui.css"/>
<link rel="stylesheet" href="${basePath}assets/css/coding/edit_index_info.css"/>
<script type="text/javascript" src="${basePath}assets/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}assets/layer/layer.js"></script>
<script type="text/javascript" src="${basePath}assets/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${basePath}assets/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${basePath}assets/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath}assets/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${basePath}assets/jqueryValidate/localization/messages_zh.js"></script>
<script type="text/javascript" src="${basePath}assets/js/coding/edit_cost_info.js"></script>

<form action="${basePath}medical_record_coding/edit_cost_info" method="post" id="costInfoForm">
<input type="hidden" name="visitGuid" value="${visitGuid}">
<table style="width:100%;" cellpadding="0" cellspacing="0">
<tbody>
	<tr>
		<td colspan="6"><input type="submit" value="保存" class="btn" >
		</td>
	</tr>
	<tr>
		<td class="tdLabel_7" style="width:15%">总费用</td>
		<td class="tdLabel_8"><input type="text" name="totalCost"
			value="${costInfo.totalCost}">
		</td>
		<td class="tdLabel_7">自付金额</td>
		<td class="tdLabel_8"><input type="text"
			name="selfCost" value="${costInfo.selfCost}">
		</td>
	</tr>
	<tr>
		<td class="tdLabel_7">床位费</td>
		<td class="tdLabel_8">
			<input type="hidden" name="costRecords[0].medicalCostTypeCode" value="F00"/>
			<input type="hidden" name="costRecords[0].medicalCostTypeName" value="床位费"/>
			<input type="text" name="costRecords[0].costMoney" />
		</td>
		<td align="left" class="tdLabel_7">西药费</td>
		<td align="left" class="tdLabel_8">
			<input type="hidden" name="costRecords[1].medicalCostTypeCode" value="F01"/>
			<input type="hidden" name="costRecords[1].medicalCostTypeName" value="西药费"/>
			<input type="text" name="costRecords[1].costMoney" />
		</td>
		<td align="left" class="tdLabel_7">中药费</td>
		<td align="left" class="tdLabel_8">
			<input type="hidden" name="costRecords[2].medicalCostTypeCode" value="F02"/>
			<input type="hidden" name="costRecords[2].medicalCostTypeName" value="中药费"/>
			<input type="text" name="costRecords[2].costMoney" />
		</td>
	</tr>
	<tr>
		<td align="left" class="tdLabel_7">检验费</td>
		<td align="left" class="tdLabel_8">
			<input type="hidden" name="costRecords[3].medicalCostTypeCode" value="F03"/>
			<input type="hidden" name="costRecords[3].medicalCostTypeName" value="检验费"/>
			<input type="text" name="costRecords[3].costMoney" />
		</td>
		<td align="left" class="tdLabel_7">放射费</td>
		<td align="left" class="tdLabel_8">
			<input type="hidden" name="costRecords[4].medicalCostTypeCode" value="F04"/>
			<input type="hidden" name="costRecords[4].medicalCostTypeName" value="放射费"/>
			<input type="text" name="costRecords[4].costMoney" />
		</td>
		<td align="left" class="tdLabel_7">特检费</td>
		<td align="left" class="tdLabel_8">
			<input type="hidden" name="costRecords[5].medicalCostTypeCode" value="F05"/>
			<input type="hidden" name="costRecords[5].medicalCostTypeName" value="特检费"/>
			<input type="text" name="costRecords[5].costMoney" />
		</td>
	</tr>

	<tr>
		<td align="left" class="tdLabel_7">治疗费</td>
		<td align="left" class="tdLabel_8">
			<input type="hidden" name="costRecords[6].medicalCostTypeCode" value="F06"/>
			<input type="hidden" name="costRecords[6].medicalCostTypeName" value="治疗费"/>
			<input type="text" name="costRecords[6].costMoney" />
		</td>
		<td align="left" class="tdLabel_7">输血费</td>
		<td align="left" class="tdLabel_8">
			<input type="hidden" name="costRecords[7].medicalCostTypeCode" value="F07"/>
			<input type="hidden" name="costRecords[7].medicalCostTypeName" value="输血费"/>
			<input type="text" name="costRecords[7].costMoney" />
		</td>
		<td align="left" class="tdLabel_7">手术费</td>
		<td align="left" class="tdLabel_8">
			<input type="hidden" name="costRecords[8].medicalCostTypeCode" value="F08"/>
			<input type="hidden" name="costRecords[8].medicalCostTypeName" value="手术费"/>
			<input type="text" name="costRecords[8].costMoney" />
		</td>
	</tr>
	<tr>
		<td align="left" class="tdLabel_7">护理费</td>
		<td align="left" class="tdLabel_8">
			<input type="hidden" name="costRecords[9].medicalCostTypeCode" value="F09"/>
			<input type="hidden" name="costRecords[9].medicalCostTypeName" value="护理费"/>
			<input type="text" name="costRecords[9].costMoney" />
		</td>
		<td align="left" class="tdLabel_7">其他费</td>
		<td align="left" class="tdLabel_8">
			<input type="hidden" name="costRecords[10].medicalCostTypeCode" value="F10"/>
			<input type="hidden" name="costRecords[10].medicalCostTypeName" value="其他"/>
			<input type="text" name="costRecords[10].costMoney" />
		</td>
	</tr>
</tbody>
</table>
</form>
<script>
	$(function(){
		var costInfoJson = <%=costInfoJson%>
		var respondResultJson = <%=respondResultJson%>
		if(respondResultJson){
			if(respondResultJson.success){
				layer.msg("保存成功");
			}else{
				layer.msg("保存失败");
			}
		}
		//console.log(visitCostInfoJson);
		initPage(costInfoJson);
	});
</script>