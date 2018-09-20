<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
String baseInfoJson= (String)request.getAttribute("baseInfoJson");
String infusionBloodInfoJson= (String)request.getAttribute("infusionBloodInfoJson");
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
<script type="text/javascript" src="${basePath}assets/js/coding/edit_infusion_blood_info.js"></script>
<form action="${basePath }medical_record_coding/edit_infusion_blood_info" style="height:auto;width: 100%" method="post" id="infusionBloodInfoForm">
	<input type="hidden" id="visitGuid" name="visitGuid" value="${visitGuid }">
	<input type="hidden" id="infusionBloodRecordCount" value="0"/>
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
				<td class="tdLabel_7">输血次数</td>
				<td class="tdLabel_8">
					<input type="text" name="infusionBloodTimes" class="input_box" value="${infusionBloodInfo.infusionBloodTimes}">
				</td>
				<td class="tdLabel_7">输血反应次数</td>
				<td class="tdLabel_8">
					<input type="text" name="infusionBloodReactTimes" class="input_box" value="${infusionBloodInfo.infusionBloodReactTimes}">
				</td>
				<td class="tdLabel_8" colspan="2">
					
				</td>
			</tr>
		    
		</tbody>
	</table>
	<table style="width: 100%;" id="infusionBloodRecords" >
		<thead>
			<tr>
				<td colspan="12" class="tdLabel_7">

					<div style="float:right">
						<span class="red" id="tsxx" style="display:none"> </span> 
						<input type="button" value="新增" onclick="addInfusionBloodRecord()"class="btn">
					</div>
				</td>
			</tr>
			<tr>
				<td class="tdLabel_4" style="width: 18%">输血血液类型</td>
				<td class="tdLabel_4" style="width: 8%">输血反应</td>
				<td class="tdLabel_4" style="width: 18%">输血日期</td>
				<td class="tdLabel_4" style="width: 5%">红细胞</td>
				<td class="tdLabel_4" style="width: 5%">白细胞</td>
				<td class="tdLabel_4" style="width: 5%">血小板</td>
				<td class="tdLabel_4" style="width: 5%">血浆</td>
				<td class="tdLabel_4" style="width: 5%">全血</td>
				<td class="tdLabel_4" style="width: 5%">自体血</td>
				<td class="tdLabel_4" style="width: 5%">其他</td>
				<td class="tdLabel_4" style="width: 13%">操作人</td>
				<td class="tdLabel_4" style="width: 8%">操作</td>
			</tr>

		</thead>
		<tbody>
			
			
		</tbody>
	</table>
</form>

<div hidden="hidden">
	<table>
		<tr id="templateInfusionBloodTr">
			<td class="tdLabel_8">
				<select name="infusionBloodRecords[0].infusionBloodTypeCode">
				</select>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodTypeName"/>
			</td>
			<td class="tdLabel_8" style="text-align: center;">
				<input type="radio" name="infusionBloodRecords[0].isBleedingReactionCode" value="1" title="是"/>是
				<input type="radio" name="infusionBloodRecords[0].isBleedingReactionCode" value="0" title="否"/>否
				<input type="hidden" name="infusionBloodRecords[0].isBleedingReactionName"/>
			</td>
			<td class="tdLabel_8">
				<input type="text" name="infusionBloodRecords[0].bleedingDateTime" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
			</td>
			<td class="tdLabel_8">
				<input type="text" name="infusionBloodRecords[0].infusionBloodElements[0].bloodElementVolume" style="width:100%"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[0].bloodElementCode" value="B0001"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[0].bloodElementName" value="红细胞"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[0].bloodElementUnitCode" value="unit"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[0].bloodElementUnitName" value="单位"/>
			</td>
			<td class="tdLabel_8">
				<input type="text" name="infusionBloodRecords[0].infusionBloodElements[1].bloodElementVolume" style="width:100%"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[1].bloodElementCode" value="B0002"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[1].bloodElementName" value="白细胞"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[1].bloodElementUnitCode" value="bag"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[1].bloodElementUnitName" value="袋"/>
			</td>
			<td class="tdLabel_8">
				<input type="text" name="infusionBloodRecords[0].infusionBloodElements[2].bloodElementVolume" style="width:100%"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[2].bloodElementCode" value="B0003"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[2].bloodElementName" value="血小板"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[2].bloodElementUnitCode" value="ml"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[2].bloodElementUnitName" value="毫升"/>
			</td>
			<td class="tdLabel_8">
				<input type="text" name="infusionBloodRecords[0].infusionBloodElements[3].bloodElementVolume" style="width:100%"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[3].bloodElementCode" value="B0004"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[3].bloodElementName" value="血浆"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[3].bloodElementUnitCode" value="ml"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[3].bloodElementUnitName" value="毫升"/>
			</td>
			<td class="tdLabel_8">
				<input type="text" name="infusionBloodRecords[0].infusionBloodElements[4].bloodElementVolume" style="width:100%"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[4].bloodElementCode" value="B0005"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[4].bloodElementName" value="全血"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[4].bloodElementUnitCode" value="ml"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[4].bloodElementUnitName" value="毫升"/>
			</td>
			<td class="tdLabel_8">
				<input type="text" name="infusionBloodRecords[0].infusionBloodElements[5].bloodElementVolume" style="width:100%"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[5].bloodElementCode" value="B0006"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[5].bloodElementName" value="自体血"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[5].bloodElementUnitCode" value="ml"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[5].bloodElementUnitName" value="毫升"/>
			</td>
			<td class="tdLabel_8">
				<input type="text" name="infusionBloodRecords[0].infusionBloodElements[6].bloodElementVolume" style="width:100%"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[6].bloodElementCode" value="B9999"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[6].bloodElementName" value="其他"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[6].bloodElementUnitCode" value="ml"/>
				<input type="hidden" name="infusionBloodRecords[0].infusionBloodElements[6].bloodElementUnitName" value="毫升"/>
			</td>
			<td class="tdLabel_8">
				<input type="text" name="infusionBloodRecords[0].operatorName" style="width: 100%;"/>
				<input type="hidden" name="infusionBloodRecords[0].operatorCode"/>
			</td>
			<td class="tdLabel_8">
				<a class="btn" style="padding:4px;margin:0 auto; display: block; position: relative; left:-25%;" onclick="deleteInfusionBloodRecord(this)">删除</a>
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
		var infusionBloodInfoJson= <%=infusionBloodInfoJson%>;
		console.log(baseInfoJson);
		console.log(infusionBloodInfoJson);
		initPage(baseInfoJson,infusionBloodInfoJson);
		
	});
</script>