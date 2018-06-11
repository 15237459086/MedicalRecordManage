<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setAttribute("basePath", basePath);
	String respondResultJson= (String)request.getAttribute("respondResultJson");
%>
<html>
<head>
  <base href="<%=basePath%>">
    
    <title>胡桃健康</title>
	<link rel="stylesheet" href="${basePath}assets/bootstrap/css/bootstrap.css"/>
	    
	<link rel="stylesheet" href="${basePath}assets/css/basic.css"/>
	
	<link rel="stylesheet" href="${basePath}assets/css/coding/edit_index_info.css"/>
	<script type="text/javascript" src="${basePath}assets/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath}assets/bootstrap/js/bootstrap.js"></script>
	
	<script type="text/javascript" src="${basePath}assets/layer/layer.js"></script>
	<script type="text/javascript" src="${basePath}assets/js/coding/edit_index_info.js"></script>
	<script type="text/javascript">
		function loadContent(url) {
			$("#mainContent").attr("src", url);
		}
		$(function(){
			var respondResultJson= <%=respondResultJson%>;
			console.log(respondResultJson);
			if(respondResultJson){
				if(!respondResultJson.success){
					if(respondResultJson.stateCode = "404"){
						layer.msg(respondResultJson.stateMessage);
						
					}
				}else{
					$("ul[role='tablist']").removeAttr("hidden");
					$("a[aria-controls='baseInfo']").click();
				}
			}
		});
	</script>
</head>
<body>
	<div id="div_TC">
		<div class="TC_title">
			<h3 id="title">标题</h3>
	        <ul style="width:100px;">
	            <li><a style="color: white;" onclick="codingFinish('${visitGuid}')">完成编页</a></li>
	        </ul>
		</div>
		<input type="hidden" id ="basePath" value="${basePath }">
		<div class="TC_menu">
			<h3>病案编码管理</h3>
			<ul role="tablist" hidden="hidden">
				<li class="TC_li active"><a onclick="loadContent('${basePath}medical_record_coding/edit_basic_info_form?visitGuid=${visitGuid}')" aria-controls="baseInfo" role="tab" data-toggle="tab">基本数据</a>
				</li>
				<li class="TC_li"><a onclick="loadContent('${basePath}medical_record_coding/edit_cure_info_form?visitGuid=${visitGuid}')" aria-controls="cureMentInfo" role="tab" data-toggle="tab">治疗信息</a>
				</li>
				
				<li class="TC_li"><a onclick="loadContent('${basePath}medical_record_coding/edit_disease_diag_info_form?visitGuid=${visitGuid}')" aria-controls="diagMentInfo" role="tab" data-toggle="tab">疾病诊断</a>
				</li>
				<li class="TC_li"><a onclick="loadContent('${basePath}medical_record_coding/edit_operate_info_form?visitGuid=${visitGuid}')" aria-controls="operateMentInfo" role="tab" data-toggle="tab">手术记录</a>
				</li>
				<%-- <li class="TC_li"><a onclick="loadContent('${basePath}medical_record/edit_home_page_infusion_blood_form?visitGuid=${visitGuid}')" aria-controls="infusionBloodMentInfo" role="tab" data-toggle="tab">输血记录</a>
				</li>
				<li class="TC_li"><a onclick="loadContent('${basePath}medical_record/edit_home_page_transfer_dept_form?visitGuid=${visitGuid}')" aria-controls="transferDeptMentInfo" role="tab" data-toggle="tab">转科记录</a>
				</li>
				<li class="TC_li"><a onclick="loadContent('${basePath}medical_record/edit_home_page_drug_allergy_form?visitGuid=${visitGuid}')" aria-controls="drugAllergyMentInfo" role="tab" data-toggle="tab">药物过敏</a>
				</li>
				<li class="TC_li"><a onclick="loadContent('${basePath}medical_record/edit_home_page_intensive_care_form?visitGuid=${visitGuid}')" aria-controls="intensiveCareMentInfo" role="tab" data-toggle="tab">重症监护</a>
				</li> --%>
				<li class="TC_li"><a onclick="loadContent('${basePath}medical_record_coding/edit_nurse_info_form?visitGuid=${visitGuid}')" aria-controls="nurseMentInfo" role="tab" data-toggle="tab">护理记录</a>
				</li>
				<%-- <li class="TC_li"><a onclick="loadContent('${basePath}medical_record/edit_home_page_pressure_sore_form?visitGuid=${visitGuid}')" aria-controls="pressureSoreMentInfo" role="tab" data-toggle="tab">压疮/床褥</a>
				</li> --%>
				<%-- <li class="TC_li"><a onclick="loadContent('${basePath}medical_record/edit_home_page_pregnancy_birth_form?visitGuid=${visitGuid}')" aria-controls="pregnancyBirthMentInfo" role="tab" data-toggle="tab">产科记录</a>
				</li> --%>
				<%-- <li class="TC_li"><a onclick="loadContent('${basePath}medical_record/edit_home_page_antibacterial_drug_form?visitGuid=${visitGuid}')" aria-controls="antibacterialAgentsMentInfo" role="tab" data-toggle="tab">抗菌药物</a>
				</li>
				<li class="TC_li"><a onclick="loadContent('${basePath}medical_record/edit_home_page_infection_form?visitGuid=${visitGuid}')" aria-controls="infectionMentInfo" role="tab" data-toggle="tab">医院感染</a>
				</li>
				<li class="TC_li"><a onclick="loadContent('${basePath}medical_record/edit_home_page_examination_reports_form?visitGuid=${visitGuid}')" aria-controls="examinationReportsMentInfo" role="tab" data-toggle="tab">检验报告单</a>
				</li>
				<li class="TC_li"><a onclick="loadContent('${basePath}medical_record/edit_home_page_doctor_advice_temporary_form?visitGuid=${visitGuid}')" aria-controls="doctorAdviceTemporaryMentInfo" role="tab" data-toggle="tab">临时医嘱</a>
				</li>
				<li class="TC_li"><a onclick="loadContent('${basePath}medical_record/edit_home_page_doctor_advice_standing_form?visitGuid=${visitGuid}')" aria-controls="doctorAdviceStandingMentInfo" role="tab" data-toggle="tab">长期医嘱</a>
				</li>--%>
				<li class="TC_li"><a onclick="loadContent('${basePath}medical_record_coding/edit_cost_info_form?visitGuid=${visitGuid}')" aria-controls="visitCostMentInfo" role="tab" data-toggle="tab">住院费用</a>
				</li> 
			</ul>
		</div>
		<div class="content_list">
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="baseInfo">
					<iframe id="mainContent" style="border: 0px;" width="100%" src="">	
					</iframe>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
