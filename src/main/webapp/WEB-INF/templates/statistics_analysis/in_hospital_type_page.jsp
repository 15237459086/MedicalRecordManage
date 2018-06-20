<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/base_resource.jsp" %>
<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>
<script type="text/javascript" src="${basePath}assets/FusionChart/FusionCharts.js"></script>
<script type="text/javascript" src="${basePath}assets/js/statistics_analysis/in_hospital_type_page.js"></script>


<div class="loc">
	<h3>入院方式统计</h3>
	<ul class="loc_loc"><li> 当前位置：统计分析 > 入院方式统计</li>
	</ul>
</div>
<div class="list_con_table">
	 
   <div class="search_table">
   <input type="hidden" id="basePath" value="${basePath }"/>
   <form id="queryForm" style="width: 100%;">
   	<input type="hidden" name="currentPage" value="1"/>
    <ul>
    <li class="Label_1">出院日期：&nbsp;</li>
    <li class="Label_2" style="width:30%;">
    	<input class="input_box" name="outHospitalStartDate" type="text" style="width:46%" onFocus="WdatePicker()"/>-<input class="input_box" type="text" style="width:46%" name="outHospitalEndDate" onFocus="WdatePicker()" />
    </li>
   
    <li class="Label_5">
     <a id="queryBtn" onclick="queryBtnClick()">统计</a>
    </li>
    </ul>
    </form>
   </div>
  
    
</div>
 <div id="divPieChart"></div>
