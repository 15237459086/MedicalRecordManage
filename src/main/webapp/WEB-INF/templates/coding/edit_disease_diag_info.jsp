<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
String baseInfoJson= (String)request.getAttribute("baseInfoJson");
String diseaseDiagInfoJson= (String)request.getAttribute("diseaseDiagInfoJson");
String respondResultJson= (String)request.getAttribute("respondResultJson");
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
<script type="text/javascript" src="${basePath}assets/js/coding/edit_disease_diag_info.js"></script>
<form action="${basePath}medical_record_coding/edit_disease_diag_info"
	method="post" id="diseaseDiagInfoForm">
	<input type="hidden" name="visitGuid" value="${visitGuid }">
	<input type="hidden" value="0" id="diseaseDiagCount" />
	<input type="hidden" id="basePath" value="${basePath }">
	<div style="height:822px; overflow:scroll;">
		<table style="margin:0 auto;width: 99%" id="diseaseDiagTable">
		
			<thead>
				<tr>
					<td colspan="9">
						<div style="float:right">
							<input type="submit" value="保存" id="zongAdd" class="btn"> <input
								type="button" value="新增" onclick="addDiseaseDiagRecord()"
								class="btn">
						</div>
					</td>
				</tr>
				
				<tr>
					<td class="tdLabel_4" style="width: 12%" >诊断类型</td>
					<td class="tdLabel_4" style="width: 20%">原始描述</td>
					<td class="tdLabel_4" style="width: 24%">疾病编码</td>
					<td class="tdLabel_4" style="width: 15%">入院病情</td>
					<td class="tdLabel_4" style="width: 15%">治疗结果</td>
					<td class="tdLabel_4" style="width: 10%">治疗天数</td>
					<td class="tdLabel_4" style="width: 13%">位序</td>
					<td class="tdLabel_4" style="width: 7%">操作</td>
				</tr>
				<tr>
					<td class="tdLabel_8">
						<input type="text" readonly="readonly" name="outpatientDiagRecord.diagTypeName" value="门诊诊断"/>
						<input type="hidden" name="outpatientDiagRecord.diagTypeCode" value="1"/>
					</td>
					<td class="tdLabel_8">
						<input type="text" name="outpatientDiagRecord.diseaseDiagOriginalDesc" style="width: 100%" readonly="readonly" value="${diseaseDiagInfo.outpatientDiagRecord.diseaseDiagOriginalDesc }">
						<input type="hidden" name="outpatientDiagRecord.diseaseDiagOriginalCode" value="${diseaseDiagInfo.outpatientDiagRecord.diseaseDiagOriginalCode }">
					</td>
					<td class="tdLabel_8">
						<c:if test="${(not empty diseaseDiagInfo.outpatientDiagRecord.diseaseDiagName) and (not empty diseaseDiagInfo.outpatientDiagRecord.diseaseDiagCode)  }">
							<c:set scope="page" var="outpatientDiagShow" value="${diseaseDiagInfo.outpatientDiagRecord.diseaseDiagCode }[${diseaseDiagInfo.outpatientDiagRecord.diseaseDiagName }]"></c:set>
						</c:if>
						<input class="input_box" data-type="diseaseDiag" type="text" name="outpatientDiagRecord.diseaseDiagShow" value="${outpatientDiagShow }">
						<input type="hidden" name="outpatientDiagRecord.diseaseDiagName" value="${diseaseDiagInfo.outpatientDiagRecord.diseaseDiagName }">
						<input type="hidden" name="outpatientDiagRecord.diseaseDiagCode" value="${diseaseDiagInfo.outpatientDiagRecord.diseaseDiagCode }">
					</td>
					<td class="tdLabel_8" colspan="5">
					</td>
					
				</tr>
				<tr>
					<td class="tdLabel_8">
						<input type="text" readonly="readonly" name="mainDiagRecord.diagTypeName" value="主要诊断"/>
						<input type="hidden" name="mainDiagRecord.diagTypeCode" value="3"/>
					</td>
					<td class="tdLabel_8">
						<input type="text" name="mainDiagRecord.diseaseDiagOriginalDesc" style="width: 100%" readonly="readonly" value="${diseaseDiagInfo.mainDiagRecord.diseaseDiagOriginalDesc}">
						<input type="hidden" name="mainDiagRecord.diseaseDiagOriginalCode" value="${diseaseDiagInfo.mainDiagRecord.diseaseDiagOriginalCode}">
					</td>
					<td class="tdLabel_8">
						<c:if test="${(not empty diseaseDiagInfo.mainDiagRecord.diseaseDiagName) and (not empty diseaseDiagInfo.mainDiagRecord.diseaseDiagCode)  }">
							<c:set scope="page" var="mainDiagShow" value="${diseaseDiagInfo.mainDiagRecord.diseaseDiagCode }[${diseaseDiagInfo.mainDiagRecord.diseaseDiagName }]"></c:set>
						</c:if>
						<input class="input_box" data-type="diseaseDiag" type="text" name="mainDiagRecord.diseaseDiagShow" value="${mainDiagShow }">
						<input type="hidden" name="mainDiagRecord.diseaseDiagName" value="${diseaseDiagInfo.mainDiagRecord.diseaseDiagName}">
						<input type="hidden" name="mainDiagRecord.diseaseDiagCode" value="${diseaseDiagInfo.mainDiagRecord.diseaseDiagCode}">
					</td>
					<td class="tdLabel_8">
						<select name="mainDiagRecord.inHospitalDiseaseStateCode" style="width: 100%">
						</select>
						<input type="hidden" name="mainDiagRecord.inHospitalDiseaseStateName">
					</td>
					<td class="tdLabel_8">
						<select name="mainDiagRecord.treatResultCode" style="width: 100%">
						</select>
						<input type="hidden" name="mainDiagRecord.treatResultName">
					</td>
				</tr>
				</thead>
				<tbody id="div_TC">
				
				</tbody>
				
				<tfoot>
					<tr>
						<td class="tdLabel_8">
							<input type="text" readonly="readonly" name="damageAndVenenationDiagRecords[0].diagTypeName" value="损伤和中毒的外部原因"/>
							<input type="hidden" name="damageAndVenenationDiagRecords[0].diagTypeCode" value="7"/>
						</td>
						<td class="tdLabel_8">
							<c:choose>
								<c:when test="${(not empty diseaseDiagInfo.damageAndVenenationDiagRecords) and  fn:length(diseaseDiagInfo.damageAndVenenationDiagRecords) > 0}">
									<input type="text" name="damageAndVenenationDiagRecords[0].diseaseDiagOriginalDesc" style="width: 100%" readonly="readonly" value="${diseaseDiagInfo.damageAndVenenationDiagRecords[0].diseaseDiagOriginalDesc}">
									<input type="hidden" name="damageAndVenenationDiagRecords[0].diseaseDiagOriginalCode" value="${diseaseDiagInfo.damageAndVenenationDiagRecords[0].diseaseDiagOriginalCode}">
								</c:when>
								<c:otherwise>
									<input type="text" name="damageAndVenenationDiagRecords[0].diseaseDiagOriginalDesc" style="width: 100%" readonly="readonly">
									<input type="hidden" name="damageAndVenenationDiagRecords[0].diseaseDiagOriginalCode">
								</c:otherwise>
							</c:choose>
							
						</td>
						<td class="tdLabel_8">
							<c:choose>
								<c:when test="${(not empty diseaseDiagInfo.damageAndVenenationDiagRecords) and  fn:length(diseaseDiagInfo.damageAndVenenationDiagRecords) > 0}">
									<c:if test="${(not empty diseaseDiagInfo.damageAndVenenationDiagRecords[0].diseaseDiagName) and (not empty diseaseDiagInfo.damageAndVenenationDiagRecords[0].diseaseDiagCode)  }">
										<c:set scope="page" var="damageAndVenenation0DiagShow" value="${diseaseDiagInfo.damageAndVenenationDiagRecords[0].diseaseDiagCode }[${diseaseDiagInfo.damageAndVenenationDiagRecords[0].diseaseDiagName }]"></c:set>
									</c:if>
									<input class="input_box" data-type="diseaseDiag" type="text" name="damageAndVenenationDiagRecords[0].diseaseDiagShow" value="${damageAndVenenation0DiagShow}">
									<input type="hidden" name="damageAndVenenationDiagRecords[0].diseaseDiagName" value="${diseaseDiagInfo.damageAndVenenationDiagRecords[0].diseaseDiagName }">
									<input type="hidden" name="damageAndVenenationDiagRecords[0].diseaseDiagCode" value="${diseaseDiagInfo.damageAndVenenationDiagRecords[0].diseaseDiagCode}">
										
								</c:when>
								<c:otherwise>
									<input class="input_box" data-type="diseaseDiag" type="text" name="damageAndVenenationDiagRecords[0].diseaseDiagShow">
									<input type="hidden" name="damageAndVenenationDiagRecords[0].diseaseDiagName">
									<input type="hidden" name="damageAndVenenationDiagRecords[0].diseaseDiagCode">
									
								</c:otherwise>
							</c:choose>
							
						</td>
						<td class="tdLabel_8" colspan="5">
						</td>
					</tr>
					<tr>
						<td class="tdLabel_8">
							<input type="text" readonly="readonly" name="pathologyDiagRecords[0].diagTypeName" value="病理学诊断"/>
							<input type="hidden" name="pathologyDiagRecords[0].diagTypeCode" value="8"/>
						</td>
						<td class="tdLabel_8">
							<c:choose>
								<c:when test="${(not empty diseaseDiagInfo.pathologyDiagRecords) and  fn:length(diseaseDiagInfo.pathologyDiagRecords) > 0}">
									<input type="text" name="pathologyDiagRecords[0].diseaseDiagOriginalDesc" style="width: 100%" readonly="readonly" value="${diseaseDiagInfo.pathologyDiagRecords[0].diseaseDiagOriginalDesc }">
									<input type="hidden" name="pathologyDiagRecords[0].diseaseDiagOriginalCode" value="${diseaseDiagInfo.pathologyDiagRecords[0].diseaseDiagOriginalCode }">
								</c:when>
								<c:otherwise>
									<input type="text" name="pathologyDiagRecords[0].diseaseDiagOriginalDesc" style="width: 100%" readonly="readonly">
									<input type="hidden" name="pathologyDiagRecords[0].diseaseDiagOriginalCode">
								</c:otherwise>
							</c:choose>
							
						</td>
						<td class="tdLabel_8">
							<c:choose>
								<c:when test="${(not empty diseaseDiagInfo.pathologyDiagRecords) and  fn:length(diseaseDiagInfo.pathologyDiagRecords) > 0}">
									<c:if test="${(not empty diseaseDiagInfo.pathologyDiagRecords[0].diseaseDiagName) and (not empty diseaseDiagInfo.pathologyDiagRecords[0].diseaseDiagCode)  }">
										<c:set scope="page" var="pathologyDiag0Show" value="${diseaseDiagInfo.pathologyDiagRecords[0].diseaseDiagCode }[${diseaseDiagInfo.pathologyDiagRecords[0].diseaseDiagName }]"></c:set>
									</c:if>
									<input class="input_box" data-type="diseaseDiag" type="text" name="pathologyDiagRecords[0].diseaseDiagShow" value="${pathologyDiag0Show}">
									<input type="hidden" name="pathologyDiagRecords[0].diseaseDiagName" value="${diseaseDiagInfo.pathologyDiagRecords[0].diseaseDiagName }">
									<input type="hidden" name="pathologyDiagRecords[0].diseaseDiagCode" value="${diseaseDiagInfo.pathologyDiagRecords[0].diseaseDiagCode}">
								</c:when>
								<c:otherwise>
									<input class="input_box" data-type="diseaseDiag" type="text" name="pathologyDiagRecords[0].diseaseDiagShow">
									<input type="hidden" name="pathologyDiagRecords[0].diseaseDiagName">
									<input type="hidden" name="pathologyDiagRecords[0].diseaseDiagCode">
								</c:otherwise>
							</c:choose>
							
						</td>
						<td class="tdLabel_8" colspan="5">
							<c:choose>
								<c:when test="${(not empty diseaseDiagInfo.pathologyDiagRecords) and  fn:length(diseaseDiagInfo.pathologyDiagRecords) > 0}">
									病理号<input type="text" name="pathologyDiagRecords[0].pathologyNumber" value="${ diseaseDiagInfo.pathologyDiagRecords[0].pathologyNumber}">
								</c:when>
								<c:otherwise>
									病理号<input type="text" name="pathologyDiagRecords[0].pathologyNumber">
								</c:otherwise>
							</c:choose>
						
						</td>
					</tr>
					<tr>
						<td class="tdLabel_8" colspan="9"></td>
					</tr>
				</tfoot>
		</table>
	
	</div>
	
</form>

<table hidden="hidden">
	<tr id="templateDiseaseDiagTr">
		<td class="tdLabel_8">
			<select name="diseaseDiagRecords[0].diagTypeCode" style="width: 100%">
			</select>
			<input type="hidden" name="diseaseDiagRecords[0].diagTypeName" />
		</td>
		<td class="tdLabel_8">
			<input type="text" name="diseaseDiagRecords[0].diseaseDiagOriginalDesc" style="width: 100%" readonly="readonly">
			<input type="hidden" name="diseaseDiagRecords[0].diseaseDiagOriginalCode">
		</td>
		<td class="tdLabel_8">
			<input class="input_box" data-type="diseaseDiag" type="text" name="diseaseDiagRecords[0].diseaseDiagShow">
			<input type="hidden" name="diseaseDiagRecords[0].diseaseDiagName">
			<input type="hidden" name="diseaseDiagRecords[0].diseaseDiagCode">
		</td>
		<td class="tdLabel_8">
			<select name="diseaseDiagRecords[0].inHospitalDiseaseStateCode" style="width: 100%">
			</select>
			<input type="hidden" name="diseaseDiagRecords[0].inHospitalDiseaseStateName">
		</td>
		<td class="tdLabel_8">
			<select name="diseaseDiagRecords[0].treatResultCode" style="width: 100%">
			</select>
			<input type="hidden" name="diseaseDiagRecords[0].treatResultName">
		</td>
		
		<td class="tdLabel_8">
			<input class="input_box" type="number" name="diseaseDiagRecords[0].treatDayNumber">
		</td>
		
		<td class="tdLabel_8">
			<input class="input_box" type="text" name="diseaseDiagRecords[0].diagSortIndex">
		</td>
		<td class="tdLabel_8">
			<a style="width: 50px;margin-right: 5px;" onclick="deleteDiseaseDiagRecord(this)" class="btn">删除</a>
		</td>
	</tr>
</table>
<script>
	$(function(){
		var baseInfoJson= <%=baseInfoJson%>;
		var diseaseDiagInfoJson= <%=diseaseDiagInfoJson%>;
		var respondResultJson = <%=respondResultJson%>
		if(respondResultJson){
			if(respondResultJson.success){
				layer.msg("保存成功");
			}else{
				layer.msg("保存失败");
			}
		}
		console.log(baseInfoJson);
		console.log(diseaseDiagInfoJson);
		initPage(baseInfoJson,diseaseDiagInfoJson);
		
	});
</script>