<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/base_resource.jsp" %>
<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>
<script type="text/javascript" src="${basePath}assets/js/work_statistics/coding_statistics.js"></script>
<div class="loc">
	<h3>编码统计</h3>
	<ul class="loc_loc"><li> 当前位置：工作统计 > 编码统计</li>
	</ul>
</div>

<div class="list_con_table">
	 
   <div class="search_table">
   <input type="hidden" id="basePath" value="${basePath }"/>
   <form id="statisticsForm" style="width: 100%;">
    <ul>
    <li class="Label_1">日期：&nbsp;</li>
    <li class="Label_2" style="width: 40%;">
    	<input class="input_box" name="startDate" type="text" style="width:46%" onFocus="WdatePicker()"/>-<input class="input_box" type="text" style="width:46%" name="endDate" onFocus="WdatePicker()" />
    </li>
    
    <li class="Label_5">
     <a id="statisticsBtn" onclick="statisticsBtnClick()">统计</a>
    </li>
    </ul>
    </form>
   </div>
  
    
</div>
 <table style="width:934px; float:left; border-collapse:collapse;" id="query_show_table">
      <thead>
	      <tr>
	      	  <td class="tdLabel_4">编码病案数</td>
		      <td class="tdLabel_4">员工姓名</td>
		      <td class="tdLabel_4">编页总数</td>
		      <td class="tdLabel_4">完成比率</td>
	      </tr>
      </thead>
      <tbody>
      	 <tr hidden="hidden" id="template_tr">
      	   <td class="tdLabel_5" rowspan="2"><span class="medicalRecordCount"></span></td>
	      <td class="tdLabel_5"><span class="create_user_name"></span></td>
	      <td class="tdLabel_5"><span class="count"></span></td>
	      <td class="tdLabel_5"><span class="codingCountRate"></span></td>
	     </tr>
      	
      </tbody>
    </table>