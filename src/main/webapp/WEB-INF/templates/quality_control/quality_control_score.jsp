<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/base_resource.jsp" %>
<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>
<script type="text/javascript" src="${basePath}assets/js/quality_control/quality_control_score.js"></script>
<style>
	.win{z-index:10000;position:absolute;top:130px;left:60%;width:400px;height:450px;background:#fff;margin:-102px 0 0 -202px;display:none;}
	#cust_hidden {
		width:99%;
	    text-overflow: ellipsis;
	    overflow: hidden;
	    display: block;
	    white-space: nowrap;
	}
</style>

<div id="win" class="win">
	<div id ="updateDiv">
		<div class="list_con_table" style="width: 918px;">
		 
	   	<div class="search_table">
	   	<form action="" style="width: 100%;">
	   		<input type="hidden" name="visitGuid"/>
	   		<input type="hidden" name="id"/>
			<ul>
		    <li class="Label_1" style="height: 120px;padding-top: 40px;">评价项目：&nbsp;</li>
		    <li class="Label_2" style="width: 54.6%;height: 120px;">
		    	<select class="input_box" name="firstLevelCode">
		    		<option value="">-----  选择一级评价项目  -----</option>
		    	</select>
		    	<input type="hidden" name="firstLevelName" />
		    	<br/>
		    	<select class="input_box" name="secondLevelCode">
		    		<option value="">-----  选择二级评价项目  -----</option>
		    	</select>
		    	<input type="hidden" name="secondLevelName" />
		    	<br/>
		    	<select class="input_box" name="thirdLevelCode">
		    		<option value="">-----  选择三级评价项目  -----</option>
		    	</select>
		    	<input type="hidden" name="thirdLevelName" />
		    </li>
		    <li class="Label_1" style="height: 120px;">扣分：&nbsp;</li>
		    <li class="Label_2" style="height: 120px;padding-top: 40px;">
		    	<input class="input_box" type="text" name="deduction" style="width: 50px;"/>
		    	
		    </li>
		    <li class="Label_1">其他说明：&nbsp;</li>
		    <li class="Label_2" style="width: 88%">
		    	<input class="input_box" type="text" name="remark"/>
		    	
		    </li>
		    <li class="Label_5">
		     <a id="updateBtn" onclick="updateBtnClick()">修改评分</a>
		     
		    </li>
		    </ul>
		 </form>
		</div>
		</div>
	</div>
</div>
<div class="loc">
	<h3>质控评分</h3>
	<ul class="loc_loc"><li> 当前位置：病案质控 > 质控评分</li>
	</ul>
</div>
<div class="list_con_table">
	 
   <div class="search_table">
   <input type="hidden" id="basePath" value="${basePath }"/>
   
   <form action="" id="addForm" style="width: 100%">
   <input type="hidden" id="visitGuid" name="visitGuid" value="${visitGuid }"/>
    <ul>
   
    <li class="Label_1">病案号：&nbsp;</li>
    <li class="Label_2">
    	<label class="mr_id"></label>
    <li class="Label_1">姓名：&nbsp;</li>
    <li class="Label_2">
    	<label class="patient_name"></label>
    </li>
    <li class="Label_1">身份证号：&nbsp;</li>
    <li class="Label_2">
    	<label class="id_number"></label>
    </li>
    <li class="Label_1">住院号：&nbsp;</li>
    <li class="Label_2">
    	<label class="only_id"></label>
    </li>
    <li class="Label_1">出院日期：&nbsp;</li>
    <li class="Label_2">
    	<label class="out_hospital_date"></label>
    </li>
    <li class="Label_1">出院科室：&nbsp;</li>
    <li class="Label_2">
    	<label class="out_dept_name"></label>
    	
    </li>
    <li class="Label_1" style="height: 120px;padding-top: 40px;">评价项目：&nbsp;</li>
    <li class="Label_2" style="width: 54.6%;height: 120px;">
    	<select class="input_box" id="firstLevelCode" name="firstLevelCode">
    		<option value="">-----  选择一级评价项目  -----</option>
    	</select>
    	<input type="hidden" name="firstLevelName" />
    	<br/>
    	<select class="input_box" id="secondLevelCode" name="secondLevelCode">
    		<option value="">-----  选择二级评价项目  -----</option>
    	</select>
    	<input type="hidden" name="secondLevelName" />
    	<br/>
    	<select class="input_box"  id="thirdLevelCode" name="thirdLevelCode">
    		<option value="">-----  选择三级评价项目  -----</option>
    	</select>
    	<input type="hidden" name="thirdLevelName" />
    </li>
    <li class="Label_1" style="height: 120px;">扣分：&nbsp;</li>
    <li class="Label_2" style="height: 120px;padding-top: 40px;">
    	<input class="input_box" id="deduction" type="text" name="deduction" style="width: 50px;"/>
    	
    </li>
    <li class="Label_1">其他说明：&nbsp;</li>
    <li class="Label_2" style="width: 88%">
    	<input class="input_box" type="text" name="remark"/>
    	
    </li>
    <li class="Label_5">
     <a id="finishBtn" onclick="qualityControlFinishSubmit()">质控完成</a>
     <a id="addBtn" onclick="addBtnClick()">新增质控</a>
     
    </li>
    </ul>
    </form>
   </div>
  
    
</div>
 <table style="width:934px; float:left; border-collapse:collapse;table-layout: fixed;" id="data_show_table">
      <thead>
	      <tr>
	      	  <td class="tdLabel_4" style="width: 250px;">评价项目</td>
		      <td class="tdLabel_4">扣分</td>
		      <td class="tdLabel_4">其他说明</td>
		      <td class="tdLabel_4">质控人</td>
		      <td class="tdLabel_4">质控日期</td>
		      <td class="tdLabel_4">操作</td>
	      </tr>
      </thead>
      <tbody>
      	 <tr hidden="hidden" id="template_tr">
      	  <td class="tdLabel_5" style="width: 250px;"><span id="cust_hidden" class="project"></span></td>
	      <td class="tdLabel_5"><span class="deduction"></span></td>
	      <td class="tdLabel_5"><span class="remark"></span></td>
	      <td class="tdLabel_5"><span class="last_user_name"></span></td>
	      <td class="tdLabel_5"><span class="last_date_format"></span></td>
	      <td class="tdLabel_5">&nbsp;<a class="operate_pigeonhole" onclick="updateFormShow(this)" title="修改">修改</a>
	      &nbsp;&nbsp;<a class="operate_pigeonhole" onclick="deleteQualityControlSubmit(this)" title="删除">删除</a>&nbsp;
	      </td>
	     </tr>
      	
      </tbody>
     </table>
