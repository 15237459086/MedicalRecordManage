<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
String baseInfoJson= (String)request.getAttribute("baseInfoJson");
String cureInfoJson= (String)request.getAttribute("cureInfoJson");
String respondResultJson= (String)request.getAttribute("respondResultJson");
%>
<link rel="stylesheet" href="${basePath}assets/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" href="${basePath}assets/jquery-ui/jquery-ui.css"/>
<link rel="stylesheet" href="${basePath}assets/css/basic.css"/>
<link rel="stylesheet" href="${basePath}assets/css/coding/edit_index_info.css"/>
<script type="text/javascript" src="${basePath}assets/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}assets/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${basePath}assets/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${basePath}assets/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath}assets/layer/layer.js"></script>
<script type="text/javascript" src="${basePath}assets/jqueryValidate/jquery.validate.js"></script>

<script type="text/javascript" src="${basePath}assets/jqueryValidate/localization/messages_zh.js"></script>
<script type="text/javascript" src="${basePath}assets/js/coding/edit_cure_info.js"></script>
<input type="hidden" id="basePath" value="${basePath }"/>
<form action="${basePath}medical_record_coding/edit_cure_info" id="cureInfoForm" method="post">
	<input type="hidden" name="visitGuid" value="${visitGuid }">

	<table style="width: 99%;" cellspacing="0" cellpadding="0">
		<tbody>
			<tr>
				<td colspan="6"><input type="submit" value="保存" class="btn">
				</td>
			</tr>
			<tr>
				<td class="tdLabel_7 redColor">ABO血型</td>
				<td class="tdLabel_8">
					<select name="bloodTypeCode"></select>
					<input type="hidden" name="bloodTypeName"/>
				</td>
				<td class="tdLabel_7 redColor">RH血型</td>
				<td colspan="3" class="tdLabel_8">
					<select name="rhBloodTypeCode"></select>
					<input type="hidden" name="rhBloodTypeName"/>
				</td>
			</tr>
			<tr>
				<td class="tdLabel_7">新生儿出生体重(g)</td>
				<td class="tdLabel_8">
					<input type="text" name="babyBirthWeight" value="${cureInfo.babyBirthWeight }">
				</td>
				<td class="tdLabel_7">新生儿入院体重(g)</td>
				<td class="tdLabel_8">
					<input type="text" name="babyInHospitalWeight" value="${cureInfo.babyInHospitalWeight }">
				</td>
			</tr>
			<tr>
				<td class="tdLabel_7">抢救次数</td>
				<td class="tdLabel_8">
					<input type="text" name="rescueNumber" value="${cureInfo.rescueNumber }">
				</td>
				<td class="tdLabel_7">抢救成功次数</td>
				<td class="tdLabel_8">
					<input type="text" name="rescueSucceedNumber" value="${cureInfo.rescueSucceedNumber }">
				</td>
			</tr>
			<tr>
				<td class="tdLabel_7">院内会诊数</td>
				<td class="tdLabel_8">
					<input type="text" name="inConsultationNumber" value="${cureInfo.inConsultationNumber }">
				</td>

				<td class="tdLabel_7">外院会诊数</td>
				<td colspan="3" class="tdLabel_8">
					<input type="text" name="outConsultationNumber" value="${cureInfo.outConsultationNumber }">
				</td>
			</tr>
							
			<tr>
				<td class="tdLabel_7">输液次数</td>
				<td class="tdLabel_8">
					<input type="text" name="infusionTimes" value="${cureInfo.infusionTimes }" />

				</td>
				<td class="tdLabel_7">输液反应次数</td>
				<td class="tdLabel_8">
					<input type="text" name="infusionReactTimes" value="${cureInfo.infusionReactTimes }" />
				</td>
				<td class="tdLabel_7">随诊</td>
					<td class="tdLabel_8">
					<input type="radio"
						class="radio_cl" name="followUpClinicLimitCode" title="是 " value="1"/>
						是 
					<input type="radio" class="radio_cl" name="followUpClinicLimitCode" title="否" value="0"/>
						否 
					<input type="hidden" name="followUpClinicLimitName" value=""/>
						期限: 
					<input type="text" style="width: 60px;" name="followUpClinicDayNumber"
						value="${cureInfo.followUpClinicDayNumber }">
						天
					</td>
				
				</tr>
				<tr>
					<td class="tdLabel_7">31天再住院计划</td>
					<td class="tdLabel_8">
						<select name="rehospitalAimOf31Code" >
						</select>
						<input type="hidden" name="rehospitalAimOf31Name">
					</td>
					<td class="tdLabel_7">31天再住院目的</td>
					<td class="tdLabel_8" colspan="3">
						<input type="text" name="rehospitalAimOf31Description" style="width: 500px" value="${cureInfo.rehospitalAimOf31Description }">
					</td>
				</tr>
				
				<tr>
					<td class="tdLabel_7">入院前昏迷时间描述</td>
					<td class="tdLabel_8">
						<input type="text" name="beforeInHospitalComaTimeDesc"
					value="${cureInfo.beforeInHospitalComaTimeDesc }" />
					</td>
					<td class="tdLabel_7">入院后昏迷时间描述</td>
					<td class="tdLabel_8">
						<input type="text" name="afterInHospitalComaTimeDesc" value="${cureInfo.afterInHospitalComaTimeDesc }" />
	
					</td>
				</tr>
				
				<tr>
					<td class="tdLabel_7">肿瘤确诊日期</td>
					<td class="tdLabel_8">
						<input type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="malignantTumorConfirmedDateTime"
					value="${cureInfo.malignantTumorConfirmedDateTime }" />
					</td>
					<td class="tdLabel_7">肿瘤分级</td>
					<td class="tdLabel_8">
						<input type="text" name="tumorGrade" value="${cureInfo.tumorGrade }" />
	
					</td>
				</tr>
				<tr>
					<td class="tdLabel_7">肿瘤分期T</td>
					<td class="tdLabel_8">
						<input type="text" name="tumorStagingOfT" value="${cureInfo.tumorStagingOfT }" />
					</td>
					<td class="tdLabel_7">肿瘤分期N</td>
					<td class="tdLabel_8">
						<input type="text" name="tumorStagingOfN" value="${cureInfo.tumorStagingOfN }" />
					</td>
					<td class="tdLabel_7">肿瘤分期M</td>
					<td class="tdLabel_8">
						<input type="text" name="tumorStagingOfM" value="${cureInfo.tumorStagingOfM }" />
					</td>
				</tr>
				
				<tr>
					<td class="tdLabel_7">肿瘤治疗方式</td>
					<td class="tdLabel_8">
						<select name="malignantTumorFirstCureTypeCode">
						</select>
						<input type="hidden" name="malignantTumorFirstCureTypeName">
					</td>
					<td class="tdLabel_7">肿瘤诊断依据</td>
					<td class="tdLabel_8">
						<select name="malignantTumorHighestDiagBasisCode">
						</select>
						<input type="hidden" name="malignantTumorHighestDiagBasisName">
					</td>
				</tr>
				
				
					
				<tr>
					<td class="tdLabel_7">门诊和出院诊断</td>
					<td class="tdLabel_8">
						<input type="hidden" name="diagAccordRecords[0].diagCompareCode" value="A01"/>
						<input type="hidden" name="diagAccordRecords[0].diagCompareName" value="门诊诊断与出院诊断"/>
						<select name="diagAccordRecords[0].diagAccordCode" data-type="diagAccord">
						</select>
						<input type="hidden" name="diagAccordRecords[0].diagAccordName">
					</td>

					<td class="tdLabel_7">入院和出院诊断</td>
					<td class="tdLabel_8">
						<input type="hidden" name="diagAccordRecords[1].diagCompareCode" value="A02"/>
						<input type="hidden" name="diagAccordRecords[1].diagCompareName" value="入院诊断与出院诊断"/>
						<select name="diagAccordRecords[1].diagAccordCode" data-type="diagAccord">
						</select>
						<input type="hidden" name="diagAccordRecords[1].diagAccordName">
					</td>
					<td class="tdLabel_7">术前与术后诊断</td>
					<td class="tdLabel_8">
						<input type="hidden" name="diagAccordRecords[2].diagCompareCode" value="A02"/>
						<input type="hidden" name="diagAccordRecords[2].diagCompareName" value="术前诊断与术后诊断"/>
						<select name="diagAccordRecords[2].diagAccordCode" data-type="diagAccord">
						</select>
						<input type="hidden" name="diagAccordRecords[2].diagAccordName" >
					</td>
				</tr>
				<tr>
					<td class="tdLabel_7">临床与病理诊断</td>
					<td class="tdLabel_8">
						<input type="hidden" name="diagAccordRecords[3].diagCompareCode" value="A03"/>
						<input type="hidden" name="diagAccordRecords[3].diagCompareName" value="临床诊断与病理诊断"/>
						<select name="diagAccordRecords[3].diagAccordCode" data-type="diagAccord">
						</select>
						<input type="hidden" name="diagAccordRecords[3].diagAccordName">
					</td>

					<td class="tdLabel_7">放射和病理诊断</td>
					<td class="tdLabel_8">
						<input type="hidden" name="diagAccordRecords[4].diagCompareCode" value="A04"/>
						<input type="hidden" name="diagAccordRecords[4].diagCompareName" value="放射诊断与病理诊断"/>
						<select name="diagAccordRecords[4].diagAccordCode" data-type="diagAccord">
						</select>
						<input type="hidden" name="diagAccordRecords[4].diagAccordName">
					</td>
				</tr>
				<tr>
					<td class="tdLabel_7">HBsAg</td>
					<td class="tdLabel_8">
						<select name="hbsAgeResultCode" data-type="hivCheckResult">
						</select>
						<input type="hidden" name="hbsAgeResultName">
					</td>

					<td class="tdLabel_7">HCV-Ab</td>
					<td class="tdLabel_8">
						<select name="hcvAbResultCode" data-type="hivCheckResult">
						</select>
						<input type="hidden" name="hcvAbResultName" >
					</td>
					<td class="tdLabel_7">HIV-Ab</td>
					<td class="tdLabel_8">
						<select name="hivAbResultCode" data-type="hivCheckResult">
						</select>
						<input type="hidden" name="hivAbResultName">
					</td>
				</tr>
				<tr>
				<td class="tdLabel_7 redColor">科主任</td>
					<td class="tdLabel_8">
						<input type="hidden" name="cureWorkers[0].professionTitleCode" value="kzr"/>
						<input type="hidden" name="cureWorkers[0].professionTitleName" value="科主任"/>
						<input type="text" name="cureWorkers[0].medicalWorkerName" required="required"/>
						<input type="hidden" name="cureWorkers[0].medicalWorkerCode">
					</td>
					<td class="tdLabel_7 redColor">主任医师</td>
					<td class="tdLabel_8">
						<input type="hidden" name="cureWorkers[1].professionTitleCode" value="zrys"/>
						<input type="hidden" name="cureWorkers[1].professionTitleName" value="主任医师"/>
						<input type="text" name="cureWorkers[1].medicalWorkerName" required="required"/>
						<input type="hidden" name="cureWorkers[1].medicalWorkerCode">
					</td>
						
					<td class="tdLabel_7">副主任医师</td>
					<td class="tdLabel_8">
						<input type="hidden" name="cureWorkers[2].professionTitleCode" value="fzrys"/>
						<input type="hidden" name="cureWorkers[2].professionTitleName" value="副主任医师"/>
						<input type="text" name="cureWorkers[2].medicalWorkerName"/>
						<input type="hidden" name="cureWorkers[2].medicalWorkerCode">
					</td>
				</tr>

				<tr>
					<td class="tdLabel_7 redColor">主治医师</td>
					<td class="tdLabel_8">
						<input type="hidden" name="cureWorkers[3].professionTitleCode" value="zzys"/>
						<input type="hidden" name="cureWorkers[3].professionTitleName" value="主治医师"/>
						<input type="text" name="cureWorkers[3].medicalWorkerName" required="required"/>
						<input type="hidden" name="cureWorkers[3].medicalWorkerCode">
					</td>
					<td class="tdLabel_7 redColor">住院医师</td>
					<td class="tdLabel_8">
						<input type="hidden" name="cureWorkers[4].professionTitleCode" value="zyys"/>
						<input type="hidden" name="cureWorkers[4].professionTitleName" value="住院医师"/>
						<input type="text" name="cureWorkers[4].medicalWorkerName" required="required"/>
						<input type="hidden" name="cureWorkers[4].medicalWorkerCode">
					</td>
					<td class="tdLabel_7">进修医师</td>
					<td class="tdLabel_8">
						<input type="hidden" name="cureWorkers[5].professionTitleCode" value="jxys"/>
						<input type="hidden" name="cureWorkers[5].professionTitleName" value="进修医师"/>
						<input type="text" name="cureWorkers[5].medicalWorkerName"/>
						<input type="hidden" name="cureWorkers[5].medicalWorkerCode">
					</td>
				</tr>
				<tr>
					<td class="tdLabel_7">研究生实习医师</td>
					<td class="tdLabel_8">
						<input type="hidden" name="cureWorkers[6].professionTitleCode" value="yjssxys"/>
						<input type="hidden" name="cureWorkers[6].professionTitleName" value="研究生实习医师"/>
						<input type="text" name="cureWorkers[6].medicalWorkerName"/>
						<input type="hidden" name="cureWorkers[6].medicalWorkerCode">
					</td>
					<td class="tdLabel_7">实习医师</td>
					<td class="tdLabel_8">
						<input type="hidden" name="cureWorkers[7].professionTitleCode" value="sxys"/>
						<input type="hidden" name="cureWorkers[7].professionTitleName" value="实习医师"/>
						<input type="text" name="cureWorkers[7].medicalWorkerName"/>
						<input type="hidden" name="cureWorkers[7].medicalWorkerCode" value="">
					</td>
					<td class="tdLabel_7">实习护士</td>
					<td class="tdLabel_8">
						<input type="hidden" name="cureWorkers[8].professionTitleCode" value="sxhs"/>
						<input type="hidden" name="cureWorkers[8].professionTitleName" value="实习护士"/>
						<input type="text"  name="cureWorkers[8].medicalWorkerName"/>
						<input type="hidden" name="cureWorkers[8].medicalWorkerCode">
					</td>
					
				</tr>
				<tr>
					<td class="tdLabel_7">质控医师</td>
					<td class="tdLabel_8">
						<input type="hidden" name="cureWorkers[9].professionTitleCode" value="zkys"/>
						<input type="hidden" name="cureWorkers[9].professionTitleName" value="质控医师"/>
						<input type="text" name="cureWorkers[9].medicalWorkerName" readonly="readonly"/>
						<input type="hidden" name="cureWorkers[9].medicalWorkerCode">
					</td>
					<td class="tdLabel_7">质控护士</td>
					<td class="tdLabel_8">
						<input type="hidden" name="cureWorkers[10].professionTitleCode" value="zkhs"/>
						<input type="hidden" name="cureWorkers[10].professionTitleName" value="质控护士"/>
						<input type="text" name="cureWorkers[10].medicalWorkerName"/>
						<input type="hidden" name="cureWorkers[10].medicalWorkerCode">
					</td>
					<td class="tdLabel_7">编码员</td>
					<td class="tdLabel_8">
						<input type="hidden" name="cureWorkers[11].professionTitleCode" value="bmy"/>
						<input type="hidden" name="cureWorkers[11].professionTitleName" value="编码员"/>
						<input type="text" readonly="readonly" name="cureWorkers[11].medicalWorkerName"/>
						<input type="hidden" name="cureWorkers[11].medicalWorkerCode">
					</td>
				</tr>
				
				<tr>
					<td class="tdLabel_7">病案质量</td>
					<td class="tdLabel_8">
						
						<input type="text" name="medicalRecordQualityName" value="${cureInfo.medicalRecordQualityName }" readonly="readonly"/>
						<input type="hidden" name="medicalRecordQualityCode" value="${cureInfo.medicalRecordQualityCode }"/>
					</td>
					<td class="tdLabel_7">质控时间</td>
					<td class="tdLabel_8">
						
						<input type="text" name="qualityControlDateTime" value="${cureInfo.qualityControlDateTime }" readonly="readonly"/>
						
					</td>
					<td class="tdLabel_7">编目完成时间</td>
					<td class="tdLabel_8">
						
						<input type="text" name="finishCatalogDateTime" value="${cureInfo.finishCatalogDateTime }" readonly="readonly"/>
						
					</td>
				</tr>
		</tbody>
	</table>
</form>
<script>
	$(function(){
		var baseInfoJson= <%=baseInfoJson%>;
		var cureInfoJson= <%=cureInfoJson%>;
		var respondResultJson = <%=respondResultJson%>
		if(respondResultJson){
			if(respondResultJson.success){
				layer.msg("保存成功");
			}else{
				layer.msg("保存失败");
			}
		}
		initPage(baseInfoJson,cureInfoJson);
		/* 
		//校验数据正确性
		validateDate(); */
		
	});
</script>