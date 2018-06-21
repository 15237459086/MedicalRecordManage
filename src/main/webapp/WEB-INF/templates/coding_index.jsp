<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	function loadContent(url) {
		$("#mainContent").attr("src", url);
	}
</script>
<div id="list_main">
	<div id="list_DH">
	  <div class="list_menu2">
	   <h3>病案编码</h3>
	   <h4 class="li_t"><a onclick="loadContent('${basePath }medical_record_coding/query_home_page')">首页编码</a></h4>
	   <h4 class="li_t"><a onclick="loadContent('${basePath }medical_record_coding/main_disease_diag_check_page')">主要诊断审核</a></h4>
	   <h4 class="li_t"><a onclick="loadContent('${basePath }medical_record_coding/in_hospital_day_number_check_page')">住院天数审核</a></h4>
	   <h4 class="li_t"><a onclick="loadContent('${basePath }medical_record_coding/repeat_coding_check_page')">重复编码审核</a></h4>
	   <h4 class="li_t"><a onclick="loadContent('${basePath }medical_record_coding/date_time_check_page')">日期审核</a></h4>
	   <h4 class="li_t"><a onclick="loadContent('${basePath }medical_record_coding/medical_record_repair_page')">病案返修</a></h4>
	   <h4 class="li_t"><a onclick="loadContent('${basePath }medical_record_coding/coding_defect_page')">编码缺陷</a></h4>
	  </div>
	 </div>
	<div id="list_NR">
		<iframe id="mainContent" style="border: 0px; overflow-x: hidden; min-height: 500px;" width="100%"  scrolling="no" src="">
				
		</iframe>
	</div>
</div>
