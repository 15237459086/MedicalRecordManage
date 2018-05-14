<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/base_resource.jsp" %>
<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>
<script type="text/javascript" src="${basePath}assets/js/quality_control/quality_control_score_rate.js"></script>


<div class="loc">
	<h3>质控评分统计</h3>
	<ul class="loc_loc"><li> 当前位置：病案质控 > 质控评分统计</li>
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
		      <td class="tdLabel_4" rowspan="2">应质控数</td>
		      <td class="tdLabel_4" rowspan="2">已质控数</td>
		      <td class="tdLabel_4" rowspan="2">质控率</td>
		      <td class="tdLabel_4" colspan="2">甲级</td>
		      <td class="tdLabel_4" colspan="2">乙级</td>
		      <td class="tdLabel_4" colspan="2">丙级</td>
	      </tr>
	      <tr>
	      	  <td class="tdLabel_4">病案数</td>
		      <td class="tdLabel_4">比率</td>
		      <td class="tdLabel_4">病案数</td>
		      <td class="tdLabel_4">比率</td>
		      <td class="tdLabel_4">病案数</td>
		      <td class="tdLabel_4">比率</td>
	      </tr>
      </thead>
      <tbody>
      	 <tr hidden="hidden" id="template_tr">
      	  <td class="tdLabel_5"><span class="out_dept_name"></span></td>
      	  <td class="tdLabel_5"><span class="total_count"></span></td>
	      <td class="tdLabel_5"><span class="quality_control_count"></span></td>
	      <td class="tdLabel_5"><span class="quality_control_rate"></span></td>
	      <td class="tdLabel_5"><span class="quality_control_a_count"></span></td>
	       <td class="tdLabel_5"><span class="quality_control_a_rate"></span></td>
	      <td class="tdLabel_5"><span class="quality_control_b_count"></span></td>
	      <td class="tdLabel_5"><span class="quality_control_b_rate"></span></td>
	      <td class="tdLabel_5"><span class="quality_control_c_count"></span></td>
	      <td class="tdLabel_5"><span class="quality_control_c_rate"></span></td>
	      

	     </tr>
      	
      </tbody>
     </table>
