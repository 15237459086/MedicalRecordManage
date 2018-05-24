<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/base_resource.jsp" %>
<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>
<script type="text/javascript" src="${basePath}assets/js/print/print_apply.js"></script>


<div class="loc">
	<h3>打印申请</h3>
	<ul class="loc_loc"><li> 当前位置：窗口服务 > 打印申请</li>
	</ul>
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
	      <td class="tdLabel_5"><a class="operate" onclick="applyFormShow(this)" title="打印申请">打印申请&nbsp;</a></td>
	     </tr>
      	
      </tbody>
     </table>
 <div id="pageList" class="pageList">
    <ul class="pagination clearfix" id="page_plus"></ul>
    <div class="pagination">
        <div>总共：<b id="totalPage">0</b> 条信息    当前页是第 <b id="currentPage">0/0</b>　页</div>
    </div>
</div>
<div id="applyLayerDiv" hidden="hidden">
	<div class="list_con_table" style="width: 910px; margin-left: 5px;">
		<div class="search_table">
		   <form>
		   <input type="hidden" name="visitGuid"/>
		    <ul>
		   	<li class="Label_1">住院号：&nbsp;</li>
		    <li class="Label_2"><input class="input_box" disabled="disabled" type="text" name="onlyId" /></li>
		    <li class="Label_1">病案号：&nbsp;</li>
		    <li class="Label_2"><input class="input_box" type="text" disabled="disabled" name="mrId"/></li>
		    <li class="Label_1">住院次数：&nbsp;</li>
		    <li class="Label_2"><input class="input_box" type="text" disabled="disabled" name="visitNumber" /></li>
		    <li class="Label_1">患者姓名：&nbsp;</li>
		    <li class="Label_2"><input class="input_box" type="text" disabled="disabled" name="patientName" /></li>
		    <li class="Label_1">患者证件号：&nbsp;</li>
		    <li class="Label_2"><input class="input_box" disabled="disabled" type="text" name="idNumber"/></li>
		    <li class="Label_1">出院日期：&nbsp;</li>
		    <li class="Label_2">
		    	<input class="input_box" disabled="disabled" type="text" name="outHospitalDateTime"/>
		    </li>
		    <li class="Label_1">申请人姓名：&nbsp;</li>
		    <li class="Label_2"><input class="input_box" type="text" name="claimerName"/></li>
		    <li class="Label_1">申请人证件号：&nbsp;</li>
		    <li class="Label_2"><input class="input_box" type="text" name="claimerCode"/></li>
		    
		    <li class="Label_1">与患者关系：&nbsp;</li>
		    <li class="Label_2">
		    	<select name="claimerRelativeRelationCode" class="input_box">
		    		
		    	</select>
		    	<input type="hidden" name="claimerRelativeRelationName"/>
		    </li>
		    <li class="Label_1">联系电话：&nbsp;</li>
		    <li class="Label_2"><input class="input_box" type="text" name="claimerPhone"/></li>
		    
		     <li class="Label_1">申请打印类型：&nbsp;</li>
		    <li class="Label_2">
		    	<select name="applyPrinterTypeCode" class="input_box">
		    		
		    	</select>
		    	<input type="hidden" name="applyPrinterTypeName"/>
		    </li>
		    <li class="Label_1">申请打印份数：&nbsp;</li>
		    <li class="Label_2"><input class="input_box" type="text" name="applyCopies" value="1"/></li>
		    <li class="Label_1">申请原因：&nbsp;</li>
		    <li class="Label_2" style="width: 88%;"><input class="input_box" type="text" name="applyRemark"/></li>
		    
		    <li class="Label_5">
		     <a id="addBtn" onclick="addBtnClick()">添加申请</a>
		    </li>
		    </ul>
		    </form>
		</div>
	</div>
	
</div>