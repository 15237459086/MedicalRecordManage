<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/base_resource.jsp" %>
<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>
<link href="${basePath}assets/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
       
<script src="${basePath}assets/fileinput/js/fileinput.js" type="text/javascript"></script>
<script src="${basePath}assets/fileinput/js/fileinput_locale_zh.js" type="text/javascript"></script>
<script type="text/javascript" src="${basePath}assets/js/scan/scan_upload.js"></script>
<div class="loc">
	<h3>扫描件上传</h3>
	<ul class="loc_loc"><li> 当前位置：病案扫描> 扫描件上传</li>
	</ul>
</div>

<div id="query_div" class="list_con_table">
	 
   <div class="search_table">
   <input type="hidden" id="basePath" value="${basePath }"/>
   <input type="hidden" id="errorCode" value="${errorCode }"/>
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
		      <td class="tdLabel_4">是否质控</td>
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
	      <td class="tdLabel_5"><span class="quality_control_status_name"></span></td>
	      <td class="tdLabel_5"><a class="operate_pigeonhole" onclick="scanUploadFormShow(this)" title="扫描件上传">扫描件上传&nbsp;</a></td>
	     </tr>
      	
      </tbody>
     </table>
    <div id="pageList" class="pageList">
	    <ul class="pagination clearfix" id="page_plus"></ul>
	    <div class="pagination">
	        <div>总共：<b id="totalPage">0</b> 条信息    当前页是第 <b id="currentPage">0/0</b>　页</div>
	    </div>
	</div>
</div>
 
 

<div id="scan_upload_div" hidden="hidden" class="list_con_table" style="height: 500px;">
	 <table style="width:934px; float:left; border-collapse:collapse;" id="scan_upload_tb">
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
		      <td class="tdLabel_4">是否质控</td>
	      </tr>
      </thead>
      <tbody>
      	 <tr>
      	  <td class="tdLabel_5"><span class="only_id"></span></td>
	      <td class="tdLabel_5"><span class="mr_id"></span></td>
	      <td class="tdLabel_5"><span class="patient_name"></span></td>
	      <td class="tdLabel_5"><span class="id_number"></span></td>
	      <td class="tdLabel_5"><span class="visit_number"></span></td>
	      <td class="tdLabel_5"><span class="out_dept_name"></span></td>
	      <td class="tdLabel_5"><span class="out_hospital_date"></span></td>
	      <td class="tdLabel_5"><span class="out_hospital_type_name"></span></td>
	      <td class="tdLabel_5"><span class="quality_control_status_name"></span></td>
	     </tr>
      	
      </tbody>
     </table>
     <br/>
	 <div class="kv-main" style="margin-top: 70px;">
            <form action="${basePath}medical_record_scan/images_upload" method="post" enctype="multipart/form-data">
            	<input type="hidden" name="visitGuid" id="visitGuid"/>
            	<div style="overflow-y: scroll;max-height: 300px;">
                	<input id="file-0" class="file" style="width: 300px;" type="file" name="uploadImages" multiple data-min-file-count="1" accept="image/gif,image/jpeg,image/jpg,image/png">
                </div>
                <br>
                <button type="submit" class="btn btn-primary">提交</button>
                <button id="btn_reset" type="reset" class="btn btn-default">清空</button>
                <button type="button" class="btn btn-default" onclick="btnRetreat()">返回</button>
            </form>
        </div>
</div>
<script>
    $("#file-0").fileinput({
    	language: 'zh', //设置语言
        allowedFileExtensions : ['jpg', 'png','gif'],
        showUpload: false,
        showRemove:	false
    });
</script>