<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/base_resource.jsp" %>
<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>
<script type="text/javascript" src="${basePath}assets/js/coding/coding_defect_page.js"></script>


<div class="loc">
	<h3>编码缺陷</h3>
	<ul class="loc_loc"><li> 当前位置：病案编码 > 编码缺陷</li>
	</ul>
</div>

<div class="list_con_table">
	 
   <div class="search_table">
   <input type="hidden" id="basePath" value="${basePath }"/>
   <form id="queryForm" style="width: 100%;">
   	<input type="hidden" name="currentPage" value="1"/>
    <ul>
    <li class="Label_1">出院日期：&nbsp;</li>
    <li class="Label_2" style="width: 25%;">
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
	      	  <td class="tdLabel_4">缺陷编号</td>
		      <td class="tdLabel_4" style="width: 300px;">缺陷描述</td>
		      <td class="tdLabel_4">缺陷数</td>
		      <td class="tdLabel_4">详情</td>
	      </tr>
      </thead>
      <tbody>
      	 <tr hidden="hidden" id="template_tr">
      	  <td class="tdLabel_5"><span class="coding_error_code"></span></td>
	      <td class="tdLabel_5"><span class="coding_error_name"></span></td>
	      <td class="tdLabel_5"><span class="count"></span></td>
	      <td class="tdLabel_5">
	      	<a class="operate" onclick="defectDetailShow(this)" title="详情">详情&nbsp;</a>
	      </td>
	     </tr>
      	
      </tbody>
</table>


<div id="template_layer_div" hidden="hidden" class="list_con_table">
	<div class="search_table" id="template_layer_record" style="width: 920px;">
    <ul>
   	<li class="Label_1">缺陷描述：&nbsp;</li>
    <li class="Label_2" style="width: 64%"><input class="input_box" type="text" readonly id="codingErrorName"/></li>
    
    <li class="Label_1">缺陷数：&nbsp;</li>
    <li class="Label_2" style="width: 12%;"><input class="input_box" type="text" readonly id="count"/></li>
    
    
    </ul>
    </div>
    <table style="width:100%; float:left; border-collapse:collapse;" id="template_layer_defect">
      <thead>
	      <tr>
		      <td class="tdLabel_4">病案号</td>
		      <td class="tdLabel_4">住院次数</td>
		      <td class="tdLabel_4">姓名</td>
		      <td class="tdLabel_4">身份证号</td>
		      <td class="tdLabel_4">出院科室</td>
		      <td class="tdLabel_4">出院日期</td>
		      <td class="tdLabel_4">编码者</td>
	      </tr>
      </thead>
      	
      <tbody>
      	 <tr hidden="hidden" id="template_defect_tr">
	      <td class="tdLabel_5"><span class="mr_id"></span></td>
	      <td class="tdLabel_5"><span class="visit_number"></span></td>
	      <td class="tdLabel_5"><span class="patient_name"></span></td>
	      <td class="tdLabel_5"><span class="id_number"></span></td>
	      <td class="tdLabel_5"><span class="out_dept_name"></span></td>
	      <td class="tdLabel_5"><span class="out_hospital_date"></span></td>
	      <td class="tdLabel_5"><span class="coder_name"></span></td>
	     </tr>
      </tbody>
   </table>
</div>
