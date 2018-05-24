<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/base_resource.jsp" %>
<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>
<script type="text/javascript" src="${basePath}assets/js/borrow/borrow_reply.js"></script>


<div class="loc">
	<h3>借阅审批</h3>
	<ul class="loc_loc"><li> 当前位置：病案借阅 > 借阅审批</li>
	</ul>
</div>
<div class="list_con_table">
	 
   <div class="search_table">
   <input type="hidden" id="basePath" value="${basePath }"/>
   <form id="queryForm" style="width: 100%;">
   	<input type="hidden" name="currentPage" value="1"/>
    <ul>
    <li class="Label_1">借阅申请日期：&nbsp;</li>
    <li class="Label_2" style="width: 35%;">
    	<input class="input_box" name="applyStartDate" type="text" style="width:46%" onFocus="WdatePicker()"/>-<input class="input_box" type="text" style="width:46%" name="applyEndDate" onFocus="WdatePicker()" />
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
		      <td class="tdLabel_4">借阅人</td>
		       <td class="tdLabel_4">借阅日期</td>
		      <td class="tdLabel_4">状态</td>
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
	      <td class="tdLabel_5"><span class="out_hospital_date_format"></span></td>
	      <td class="tdLabel_5"><span class="borrower_name"></span></td>
	      <td class="tdLabel_5"><span class="borrower_date_format"></span></td>
	      <td class="tdLabel_5"><span class="borrow_status">借阅申请中</span></td>
	      <td class="tdLabel_5"><a class="operate" onclick="borrowReplySubmit(this,'PERMIT','批复')" title="批复">批复&nbsp;</a>&nbsp;/&nbsp; <a class="operate" onclick="borrowReplySubmit(this,'REJECT','拒批')" title="拒批">拒批&nbsp;</a></td>
	     </tr>
      	
      </tbody>
     </table>
 <div id="pageList" class="pageList">
    <ul class="pagination clearfix" id="page_plus"></ul>
    <div class="pagination">
        <div>总共：<b id="totalPage">0</b> 条信息    当前页是第 <b id="currentPage">0/0</b>　页</div>
    </div>
</div>
