<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/base_resource.jsp" %>
<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>
<script type="text/javascript" src="${basePath}assets/js/print/print_record.js"></script>
<div class="loc">
	<h3>打印记录</h3>
	<ul class="loc_loc"><li> 当前位置：窗口服务 > 打印记录</li>
	</ul>
</div>
<div id="template_layer_div" hidden="hidden"  class="list_con_table">
	<div class="search_table" id="template_layer_apply" style="width: 900px;margin-left: 8px;">
    <ul>
   
    <li class="Label_1">申请人姓名：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" readonly id="layerClaimerName"/></li>
    <li class="Label_1">申请人证件号：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" readonly id="layerClaimerCode"/></li>
    
    <li class="Label_1">申请人电话：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" readonly id="layerClaimerPhone"/></li>
    
    <li class="Label_1">申请日期：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" readonly id="layerApplyDateTime"/></li>
    
    <li class="Label_1">与患者关系：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" readonly id="layerRelativeRelationName"/></li>
    
     <li class="Label_1">申请打印原因：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" disabled="disabled" id="layerApplyRemark"/></li>
    
    </ul>
     <table style="width:100%; float:left; border-collapse:collapse;" id="template_layer_item">
      <thead>
	      <tr>
	      	  <td class="tdLabel_4">住院号</td>
		      <td class="tdLabel_4">病案号</td>
		      <td class="tdLabel_4">住院次数</td>
		      <td class="tdLabel_4">患者姓名</td>
		      <td class="tdLabel_4">身份证号</td>
		      <td class="tdLabel_4">出院日期</td>
		      <td class="tdLabel_4">打印类型</td>
		      <td class="tdLabel_4">打印份数</td>
	      </tr>
      </thead>
      	
      <tbody>
      	 <tr hidden="hidden" id="template_layer_item_tr">
      	  <td class="tdLabel_5"><span class="only_id"></span></td>
	      <td class="tdLabel_5"><span class="mr_id"></span></td>
	      <td class="tdLabel_5"><span class="visit_number"></span></td>
	      <td class="tdLabel_5"><span class="patient_name"></span></td>
	      <td class="tdLabel_5"><span class="patient_code"></span></td>
	      <td class="tdLabel_5"><span class="out_hospital_date_format"></span></td>
	      <td class="tdLabel_5"><span class="printer_type_name"></span></td>
	      <td class="tdLabel_5"><span class="apply_copies"></span></td>
	     </tr>
      </tbody>
   </table>
    </div>
   
</div>
<div class="list_con_table">
	 
   <div class="search_table">
   <input type="hidden" id="basePath" value="${basePath }"/>
   <form id="queryForm">
   	<input type="hidden" name="currentPage" value="1"/>
    <ul>
    <li class="Label_1">申请人姓名：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" name="claimerName" /></li>
    <li class="Label_1">申请人证件号：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" name="claimerCode"/></li>
    
    <li class="Label_1">申请人电话：&nbsp;</li>
    <li class="Label_2">
    
    	<input class="input_box" type="text" name="claimerPhone"/>
    </li>
    <li class="Label_1">申请日期：&nbsp;</li>
    <li class="Label_2">
    	<input class="input_box" name="applyStartDateTime" type="text" style="width:46%" onFocus="WdatePicker()"/>-<input class="input_box" type="text" style="width:46%" name="applyEndDateTime" onFocus="WdatePicker()" />
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
	      	  <td class="tdLabel_4">申请人姓名</td>
		      <td class="tdLabel_4">申请人证件号</td>
		      <td class="tdLabel_4">申请人电话</td>
		      <td class="tdLabel_4">申请日期</td>
		      <td class="tdLabel_4">与患者关系</td>
		      <td class="tdLabel_4">申请打印原因</td>
		      <td class="tdLabel_4">操作</td>
	      </tr>
      </thead>
      <tbody>
      	 <tr hidden="hidden" id="template_tr">
      	  <td class="tdLabel_5"><span class="claimer_name"></span></td>
	      <td class="tdLabel_5"><span class="claimer_code"></span></td>
	      <td class="tdLabel_5"><span class="claimer_phone"></span></td>
	      <td class="tdLabel_5"><span class="apply_date_format"></span></td>
	      <td class="tdLabel_5"><span class="claimer_relative_relation_name"></span></td>
	      <td class="tdLabel_5"><span class="apply_remark"></span></td>
	      <td class="tdLabel_5"><a onclick="printerApplyItemShow(this)" title="打印申请项">打印项&nbsp;</a></td>
	     </tr>
      	
      </tbody>
     </table>
 <div id="pageList" class="pageList">
    <ul class="pagination clearfix" id="page_plus"></ul>
    <div class="pagination">
        <div>总共：<b id="totalPage">0</b> 条信息    当前页是第 <b id="currentPage">0/0</b>　页</div>
    </div>
</div>