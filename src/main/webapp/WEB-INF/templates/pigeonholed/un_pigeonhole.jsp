<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/base_resource.jsp" %>
<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>
<script type="text/javascript" src="${basePath}assets/js/pigeonholed/un_pigeonhole.js"></script>

<style>
	.changeCss{color: red;}
	.changeinputCss{border: 1px red solid;}
	.overlay{z-index:100;position:absolute;top:-138px;left:0px;background-color:#000;opacity:0.5;filter:alpha(opacity=50);display:none;}
	.win{z-index:10000;position:absolute;top:130px;left:60%;width:400px;height:450px;background:#fff;margin:-102px 0 0 -202px;display:none;}
	h2{text-align:right;padding-right: 5px;margin-top: -3px;margin-bottom:10px;}
	h2 span{color:#000000;font-size:25;color:#000000;cursor:pointer;}
	.input_text{
		height: 30px;
		width: 240px;
		margin-bottom: 10px;
		padding-left:2px;
	}
	.input_td{
		padding-left: 30px;
		padding-right:4px;
		text-align:right;
		padding-bottom:10px;
	}
	.input_submit{
	    display:block;
		height: 38px;
		width: 240px;
		line-height:38px;
		background-color: #07a396;
		color: white;
		font-size: 18;
		font-family: 幼圆;
		border:none;
		text-align: center;
	}
</style>
<!-- 归档弹框 -->
<div id="win" class="win">

	<div id="new_table">
	<form action="" id="templateAddForm">
	<table style="margin-top: 10px;">
		<tr><td class="input_td">住院号:</td><td><input name="onlyId" class="input_text" type="text"></td></tr>
		
		<tr><td class="input_td">姓　　名:</td><td><input name='patientName' class="input_text" type="text"></td></tr>
		<tr><td class="input_td">身份证号:</td><td><input name='idNumber' class="input_text" type="text"></td></tr>
		<tr><td class="input_td">病 案 号:</td><td><input  name='mrId' class="input_text" type="text"></td></tr>
		<tr><td class="input_td">住院次数:</td><td><input  name='visitNumber' class="input_text" type="text"></td></tr>
		<tr><td class="input_td">出院科室:</td>
		<td>
			<select name="outDeptCode" class="input_text">
    		</select>
    		<input name='outDeptName' type="hidden">
		</td></tr>
		<tr><td class="input_td">出院日期:</td>
		<td><input name='outHospitalDateTime' class="input_text" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
		</tr>
		<tr><td class="input_td">离院方式:</td>
		<td>
			<select name="outHospitalTypeCode" class="input_text">
    		</select>
    		<input name='outHospitalTypeName' type="hidden">
		</td></tr>
		<tr><td></td><td><a class="input_submit" onclick="clickAddFormSubmitBtn()">新增病案</a></td></tr>
	</table>
	</form>
	</div>
	
	<div id="pigeonhole_table">
	
	<table>
		<tr>
			<td class="input_td">姓　　名:</td>
			<td><input disabled="disabled" name="patientName" class="input_text" type="text"></td></tr>
		<tr>
			<td class="input_td">身份证号:</td>
			<td><input disabled="disabled" name="idNumber" class="input_text" type="text"></td>
		</tr>
		<tr>
			<td class="input_td">病 案 号:</td>
			<td><input disabled="disabled" name="mrId" class="input_text" type="text"></td></tr>
		<tr>
			<td class="input_td">住院次数:</td>
			<td><input disabled="disabled" name='visitNumber' class="input_text" type="text"></td>
		</tr>
		<tr><td class="input_td">出院科室:</td>
		<td>
			<input disabled="disabled" name="outDeptName" class="input_text" type="text">
			
		</td></tr>
		<tr><td class="input_td">出院日期:</td>
		<td><input name='outHospitalDateTime'  disabled="disabled" class="input_text" type="text"></td>
		</tr>
		<tr><td class="input_td">离院方式:</td>
		<td>
			<input name=outHospitalTypeName  disabled="disabled" class="input_text" type="text" title=''>
		</td>
		</tr>
		
		<tr><td class="input_td  changeCss">归档日期:</td>
		<td><input name='pigeonholeDateTime' class="input_text" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
		</tr>
		<tr><td class="input_td changeCss">特殊标记:</td><td><input  class="input_text" name="treatmentSignId" style="height:20px;width:30px;margin-bottom:5px;"value="BM" type="checkbox"></tr>
		<tr><td></td><td>
		<input name="visitGuid" type="hidden"/> 
		<a class="input_submit" onclick="pigeonholeSubmit()">病案归档</a>
		</td></tr>
	</table>
	</div>
</div>
<div class="loc">
	<h3>未归病案</h3>
	<ul class="loc_loc"><li> 当前位置：病案归档 > 未归病案</li>
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
    <li class="Label_1">住院号：&nbsp;</li>
    <li class="Label_2"><input class="input_box" type="text" name="onlyId"/></li>
    <li class="Label_5">
     <!-- <a data-info='1231231' onclick='Pigeonhole(this)'>新增</a> -->
     <a onclick='addFormShow()'>新增</a>
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
	      <td class="tdLabel_5"><a class="operate_pigeonhole" onclick="pigeonholeFormShow(this)" title="归档">归档&nbsp;</a></td>
	     </tr>
      	
      </tbody>
     </table>
 <div id="pageList" class="pageList">
    <ul class="pagination clearfix" id="page_plus"></ul>
    <div class="pagination">
        <div>总共：<b id="totalPage">0</b> 条信息    当前页是第 <b id="currentPage">0/0</b>　页</div>
    </div>
</div>