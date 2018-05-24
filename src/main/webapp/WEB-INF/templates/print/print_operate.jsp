<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/base_resource.jsp" %>
<script type="text/javascript" src="${basePath}assets/js/iframe.js"></script>

<div class="loc">
	<h3>病案打印</h3>
	<ul class="loc_loc"><li> 当前位置：窗口服务 > 病案打印</li>
	</ul>
</div>
 <table style="width:934px; float:left; border-collapse:collapse;" id="query_show_table">
     <thead>
      <tr>
      	   <td class="tdLabel_4">患者姓名</td>
	      <td class="tdLabel_4">患者证件号</td>
	      <td class="tdLabel_4">病案号</td>
	      <td class="tdLabel_4">住院次数</td>
	      <td class="tdLabel_4">出院日期</td>
	      
	      <td class="tdLabel_4">申请人姓名</td>
      	  <td class="tdLabel_4">申请人证件号</td>
	      <td class="tdLabel_4">申请人电话</td>
	      <td class="tdLabel_4">打印类型</td>
	      <td class="tdLabel_4">打印份数</td>
	      <td class="tdLabel_4">操作</td>
      </tr>
     </thead>
     <tbody>
     	
     	<c:forEach var="printerApplyItem" items="${printerApplyItems }">
     		 <tr>
      	  <td class="tdLabel_5"><span class="patient_name">${printerApplyItem.patient_name}</span></td>
	      <td class="tdLabel_5"><span class="id_number">${printerApplyItem.patient_code}</span></td>
	      <td class="tdLabel_5"><span class="mr_id">${printerApplyItem.mr_id}</span></td>
	      <td class="tdLabel_5"><span class="visit_number">${printerApplyItem.visit_number}</span></td>
	      <td class="tdLabel_5"><span class="">${printerApplyItem.out_hospital_date_time}</span></td>
	      
	      <td class="tdLabel_5"><span class="claimer_name">${printerApplyItem.claimer_name}</span></td>
	      <td class="tdLabel_5"><span class="claimer_code">${printerApplyItem.claimer_code}</span></td>
	      
	      <td class="tdLabel_5"><span class="claimer_phone">${printerApplyItem.claimer_phone}</span></td>
	      <td class="tdLabel_5"><span class="apply_printer_type_name">${printerApplyItem.apply_printer_type_name}</span></td>
	      <td class="tdLabel_5"><span class="apply_copies">${printerApplyItem.apply_copies}</span></td>
	      
	      <td class="tdLabel_5"><a class="operate" target="_blank" href="${basePath }medical_record_print/show_pdf_to_publish?visitGuid=${printerApplyItem.visit_guid}&applyPrinterTypeCode=${printerApplyItem.apply_printer_type_code}" title="打印">打印&nbsp;</a></td>
	     </tr>
     	</c:forEach>
     </tbody>
  </table>
 
