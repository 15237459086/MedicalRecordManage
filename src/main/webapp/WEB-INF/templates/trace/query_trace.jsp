<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/base_resource.jsp" %>
<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>
<script type="text/javascript" src="${basePath}assets/js/trace/query_trace.js"></script>
<div class="loc">
	<h3>示踪查询</h3>
	<ul class="loc_loc"><li> 当前位置：病案示踪 > 示踪查询</li>
	</ul>
</div>
<div id="template_layer_div" hidden="hidden"  class="list_con_table">
	<div class="search_table" id="template_layer_record">
    <ul>
   
    <li class="Label_1">病案号：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" readonly id="layerMrId"/></li>
    <li class="Label_1">住院号：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" readonly id="layerOnlyId"/></li>
    
    <li class="Label_1">姓名：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" readonly id="layerPatientName"/></li>
    
    <li class="Label_1">身份证：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" readonly id="layerIdNumber"/></li>
    
    <li class="Label_1">出院日期：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" readonly id="layerOutHospitalDate"/></li>
    
     <li class="Label_1">出院科室：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" disabled="disabled" readonly="readonly" id="layerOutDeptName"/></li>
    
    </ul>
    </div>
    <table style="width:100%; float:left; border-collapse:collapse;" id="template_layer_trace">
      <thead>
	      <tr>
	      	  <td class="tdLabel_4">操作方式</td>
		      <td class="tdLabel_4">操作日期</td>
		      <td class="tdLabel_4">操作者</td>
		      <td class="tdLabel_4">备注</td>
	      </tr>
      </thead>
      	
      <tbody>
      	 <tr hidden="hidden" id="template_layer_trace_tr">
      	  <td class="tdLabel_5"><span class="trace_type_name"></span></td>
	      <td class="tdLabel_5"><span class="create_date_time_format"></span></td>
	      <td class="tdLabel_5"><span class="create_user_name"></span></td>
	      <td class="tdLabel_5"><span class="remark"></span></td>
	     </tr>
      </tbody>
   </table>
</div>
<div class="list_con_table">
	 
   <div class="search_table">
   <input type="hidden" id="basePath" value="${basePath }"/>
   <form id="queryForm">
   	<input type="hidden" name="currentPage" value="1"/>
    <ul>
   
    <li class="Label_1">病案号：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" name="mrId"/></li>
    <li class="Label_1">姓名：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" name="patientName" /></li>
    <li class="Label_1">身份证号：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" name="idNumber"/></li>
    <li class="Label_1">出院日期：&nbsp;</li>
    <li class="Label_2">
    	<input class="input_box" name="outHospitalStartDate" type="text" style="width:46%" onFocus="WdatePicker()"/>-<input class="input_box" type="text" style="width:46%" name="outHospitalEndDate" onFocus="WdatePicker()" />
    </li>
    <li class="Label_1">出院科室：&nbsp;</li>
    <li class="Label_2">
    	<select name="outHospitalDeptCode" class="input_box">
    		
    	</select>
    </li>
    <li class="Label_1">住院号：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" name="onlyId"/></li>
    
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
	      	  <td class="tdLabel_4">住院号</td>
		      <td class="tdLabel_4">病案号</td>
		      <td class="tdLabel_4">姓名</td>
		      <td class="tdLabel_4">身份证号</td>
		      <td class="tdLabel_4">住院次数</td>
		      <td class="tdLabel_4">出院科室</td>
		      <td class="tdLabel_4">出院日期</td>
		      <td class="tdLabel_4">离院方式</td>
		      <td class="tdLabel_4">操作</td>
	      </tr>
      </thead>
      <tbody>
      	 <tr hidden="hidden" id="template_tr">
      	  <td class="tdLabel_5"><span class="only_id"></span></td>
	      <td class="tdLabel_5"><span class="mr_id"></span></td>
	      <td class="tdLabel_5"><span class="patient_name"></span></td>
	      <td class="tdLabel_5"><span class="id_number"></span></td>
	      <td class="tdLabel_5"><span class="visit_number"></span></td>
	      <td class="tdLabel_5"><span class="out_dept_name"></span></td>
	      <td class="tdLabel_5"><span class="out_hospital_date"></span></td>
	      <td class="tdLabel_5"><span class="out_hospital_type_name"></span></td>
	      <td class="tdLabel_5"><a onclick="traceDetailShow(this)" title="示踪详情">示踪详情&nbsp;</a></td>
	     </tr>
      	
      </tbody>
     </table>
 <div id="pageList" class="pageList">
    <ul class="pagination clearfix" id="page_plus"></ul>
    <div class="pagination">
        <div>总共：<b id="totalPage">0</b> 条信息    当前页是第 <b id="currentPage">0/0</b>　页</div>
    </div>
</div>