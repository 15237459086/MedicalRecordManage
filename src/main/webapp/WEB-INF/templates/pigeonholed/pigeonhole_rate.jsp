<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/base_resource.jsp" %>
<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>
<script type="text/javascript" src="${basePath}assets/js/pigeonholed/pigeonhole_rate.js"></script>


<div class="loc">
	<h3>归档统计</h3>
	<ul class="loc_loc"><li> 当前位置：病案归档 > 归档统计</li>
	</ul>
</div>
<div class="list_con_table">
	 
   <div class="search_table">
   <input type="hidden" id="basePath" value="${basePath }"/>
   <form id="queryForm" style="width: 100%">
   	<input type="hidden" name="currentPage" value="1"/>
    <ul>
   
    <li class="Label_1">出院科室：&nbsp;</li>
    <li class="Label_2">
    	<select name="outHospitalDeptCode" class="input_box">
    		
    	</select>
    </li>
    <li class="Label_1">出院日期：&nbsp;</li>
    <li class="Label_2">
    	<input class="input_box" name="outHospitalStartDate" type="text" style="width:46%" onFocus="WdatePicker()"/>-<input class="input_box" type="text" style="width:46%" name="outHospitalEndDate" onFocus="WdatePicker()" />
    </li>
    <li class="Label_5">
     <a id="queryBtn" onclick="queryBtnClick()">查询</a>
    </li>
    </ul>
    </form>
   </div>
  
    
</div>
 <table style="width:934px; float:left; border-collapse:collapse;" id="query_show_table">
      <thead>
	      <tr>
	      	  <td class="tdLabel_4" rowspan="2">科室名称</td>
		      <td class="tdLabel_4" rowspan="2">应归病案数</td>
		      <td class="tdLabel_4" rowspan="2">已归病案数</td>
		      <td class="tdLabel_4" rowspan="2">归档率</td>
		      <td class="tdLabel_4" colspan="2">2日</td>
		      <td class="tdLabel_4" colspan="2">3日</td>
		      <td class="tdLabel_4" colspan="2">7日</td>
	      </tr>
	      <tr>
	      	  <td class="tdLabel_4">归档数</td>
		      <td class="tdLabel_4">归档率</td>
		      <td class="tdLabel_4">归档数</td>
		      <td class="tdLabel_4">归档率</td>
		      <td class="tdLabel_4">归档数</td>
		      <td class="tdLabel_4">归档率</td>
	      </tr>
      </thead>
      <tbody>
      	 <tr hidden="hidden" id="template_tr">
      	  <td class="tdLabel_5"><span class="out_dept_name"></span></td>
	      <td class="tdLabel_5"><span class="medical_record_count"></span></td>
	      <td class="tdLabel_5"><span class="medical_record_pigeonhold_count"></span></td>
	      <td class="tdLabel_5"><span class="medical_record_pigeonhold_rate"></span></td>
	      <td class="tdLabel_5"><span class="two_dys_pigeonhole_count"></span></td>
	      <td class="tdLabel_5"><span class="two_dys_pigeonhole_rate"></span></td>
	      <td class="tdLabel_5"><span class="three_dys_pigeonhole_count"></span></td>
	      <td class="tdLabel_5"><span class="three_dys_pigeonhole_rate"></span></td>
	      <td class="tdLabel_5"><span class="seven_dys_pigeonhole_count"></span></td>
	      
	      <td class="tdLabel_5"><span class="seven_dys_pigeonhole_rate"></span></td>
	     </tr>
      	
      </tbody>
     </table>
