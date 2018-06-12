<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setAttribute("basePath", basePath);
	String baseInfoJson= (String)request.getAttribute("baseInfoJson");
	String jsonDatas= (String)request.getAttribute("jsonDatas");
%>
<link rel="stylesheet" href="${basePath}assets/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" href="${basePath}assets/css/pagination/style.css"/>
    <script type="text/javascript" src="${basePath}assets/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath}assets/bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${basePath}assets/css/basic.css"/>
    <link rel="stylesheet" href="${basePath}assets/css/list.css"/>
<script type="text/javascript" src="${basePath}assets/js/util.js"></script>
<script type="text/javascript" src="${basePath}assets/layer/layer.js"></script>
<link href="${basePath}assets/css/CatalogManage.css" rel="stylesheet" type="text/css">
<div class="file_manage">
	<input type="hidden" id="basePath" value="${basePath }"/>
	<input type="hidden" id="visitGuid" value="${visitGuid }"/>
    <div class="file_title">
    	
    	<h3 id="paginationTitle" hidden="hidden"></h3>
    	
        <input type="hidden" id="visitGuidHidden">
        
        <ul style="width:90px;">
            <li><a onclick="custom_close()" id="closeBtn2">关闭</a></li>
        </ul>
        <ul style="width:100px;">
            <li><a onclick="imagePaginationFinish()">完成编页</a></li>
        </ul>
    </div>
    <div class="fileContent clearfix">
        <div class="fileContent-left">
            <div class="file_header">
                <div >
                	编页进度 &nbsp;<span id="paginationRateSpan">0/0</span>
                </div>
                <ul hidden="hidden">
                	
               		
               		<li id="templatePageTypeLi" class="file_li">
	            	<a>
		            	
					</a>
				</li>
                </ul>
            </div>
        <!--<h4 style="height:35px;line-height: 35px;width:965px;margin:0 0 5px;background:#e3e5e4;padding-left:25px;">文件对照信息</h4>-->
            <div class="filePage">
            	<div class="grid" id="templateImageDiv" hidden="hidden">
					<div class="imgholder"><img src=""/></div>
					<p hidden="hidden"></p>
				</div>
                <div id="container">
				</div>
            </div>
        </div>
        <div class="file_menu">
        <h3>病案编目管理</h3>
        <ul class="clearfix" id="menulist">
        	<%-- <c:forEach var="mrPageType" items="${mrPageTypes}" varStatus="status">
            	<li class="file_li" id="m3_${mrPageType.id }" page-type-id="${mrPageType.id }" data-filename="${mrPageType.id }">
	            	<a>${mrPageType.name }
		            	<span id="mrpagetype${mrPageType.id}">
							<c:choose>
								<c:when test="${not empty mrPageTypesOfPaginationMap[mrPageType.id]}">
									${mrPageTypesOfPaginationMap[mrPageType.id].pagination_count}
								</c:when>
								<c:otherwise>0</c:otherwise>
							</c:choose>
						</span>
					</a>
				</li>
            </c:forEach> --%>
            
        </ul>
    </div>
    </div>
</div>
<script>
	/*提交查询*/
function imagePaginationFinish(){
	var basePath = $("#basePath").val();
	var visitGuid = $("#visitGuid").val();
	layer.load(1);
	$.ajax({
		url: basePath + "medical_record_scan/ajax_image_pagination_finish",
		dataType: "json",
		type: "POST",
		data:{visitGuid:visitGuid},
		success: function( data ) {
			var success = data['success'];
			if(success){
				var resultShow = data['data'];
				layer.msg(resultShow);
			}else{
				layer.msg("操作错误，请重试！");
			}
			console.log(data);
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			layer.msg("网络或未知错误，请联系管理员");
		},
		complete:function(XMLHttpRequest, textStatus){
			layer.closeAll('loading');
		}
	});
	
};
	/* function sort_li(a, b){
        return Number($(b).attr('data-index')) < Number($(a).attr('data-index')) ? 1 : -1;    //升序排列
    } */
    function sort_li(a, b){
    	
      return Number($(b).attr('sort-code')) < Number($(a).attr('sort-code')) ? 1 : -1;    //升序排列 */
    }
    
    
	$(function(){
		var basePath = $("#basePath").val();
		var baseInfoJson= <%=baseInfoJson%>;
		console.log(baseInfoJson);
		var jsonDatas= <%=jsonDatas%>;
		console.log(jsonDatas);
		var scanFiles = jsonDatas['scanFiles'];
		var visitGuid = jsonDatas['visitGuid'];
		var medicalRecord = jsonDatas['medicalRecord'];
		$("#paginationTitle").html("姓名："+medicalRecord.patient_name +"&nbsp;&nbsp;&nbsp;&nbsp;证件号码："+medicalRecord.id_number
		+"&nbsp;&nbsp;&nbsp;&nbsp;病案号："+medicalRecord.mr_id+"&nbsp;&nbsp;&nbsp;&nbsp;住院次数："+medicalRecord.visit_number).removeAttr("hidden");
		$("#visitGuid").val(visitGuid);
		for(var index in scanFiles){
			var scanFile = scanFiles[index];
			var add_content=$("#templateImageDiv").clone();
			var mr_page_type_code = scanFile.mr_page_type_code;
			
			add_content.attr("file-hash", scanFile.file_hash);
			add_content.find("img").attr("src",basePath+"medical_record_scan/ajax_image_stream?fileHash="+scanFile.file_hash);
			add_content.removeAttr("id");
			add_content.removeAttr("hidden");
			if(mr_page_type_code){
				add_content.attr("page-type-code",mr_page_type_code).attr("sort-code",scanFile.sort_code).addClass("image_bined");
			}else{
				add_content.attr("sort-code",-1);
			}
			
			$("#container").append(add_content);
		}
		
		var paginationCount =  $("#container>div:not([page-type-code])").length;
		var imageDivs =$("#container>div");
		$("#paginationRateSpan").html((imageDivs.length-paginationCount)+"/"+imageDivs.length);
		imageDivs.sort(sort_li).appendTo('#container');
		var mrPageTypes = baseInfoJson['mrPageTypes'];
	   
		for(var index in mrPageTypes){
			var mrPageType = mrPageTypes[index];
			var add_content=$("#templatePageTypeLi").clone();
			
			add_content.attr("page-type-code",mrPageType.code).attr("sort-code",mrPageType.sort_code).find("a").html(mrPageType.name+"&nbsp;&nbsp;<span>0</span>");
			add_content.removeAttr("id");
			$("#menulist").append(add_content);
		}
		 var pageTypeOfPaginations = jsonDatas['pageTypeOfPaginations'];
		for(var index in pageTypeOfPaginations){
			var pageTypeOfPagination = pageTypeOfPaginations[index];
			$("#menulist li[page-type-code='"+pageTypeOfPagination.code+"'] a span").html(pageTypeOfPagination.count);
		}
		var filepage=$("#container>div");
   		//点击图片列表
	    $.each(filepage,function(i,item){
	        $(item).on("click",function(){
	           $(this).siblings(".grid").removeClass("image_checked").removeAttr("checked");
	           $(this).addClass("image_checked").attr("checked","checked");
	           var pageTypeCode= $(this).attr("page-type-code");
	           if(pageTypeCode){
	           		$("#menulist li[page-type-code='"+pageTypeCode+"']").css("background","#FEC99C").siblings().css("background","#F7F8F8");
	           }
	        })
	    })
	    //获取编目菜单列表
	    var menulist=$("#menulist li");
	    
	    //点击菜单绑定列表
	    $.each(menulist,function(i,item){
	        $(item).on("click",function(){
	          $(this).css("background","#FEC99C").siblings().css("background","#F7F8F8");//当前活动菜单加橘黄色背景，其他的还是默认灰色背景
	          
	          var checkedImage = $("#container div[checked='checked']");
	          var newPageTypeCode =$(this).attr("page-type-code");
	          var sortCode =$(this).attr("sort-code");
	          if(checkedImage.length != 0){
	          	var currentImage = checkedImage.get(0);
	         	var fileHash = currentImage.getAttribute("file-hash");
	         	var oldPageTypeCode = currentImage.getAttribute("page-type-code");
	         	$.ajax({
					type:"post",
					url:basePath + "medical_record_scan/ajax_image_pagination",
					data:{visitGuid:visitGuid,fileHash:fileHash, newPageTypeCode:newPageTypeCode},
					success:function(data){
						var success = data['success'];
						if(success){
							var datas = data['data'];
							var oldPageTypeCodeDeal = false;
							for(var index in datas){
								var pageType = datas[index];
								if(pageType.code == newPageTypeCode){
									$("#menulist li[page-type-code='"+newPageTypeCode+"'] a span").html(pageType.count);
								}else if(oldPageTypeCode && pageType.code == oldPageTypeCode){
									$("#menulist li[page-type-code='"+oldPageTypeCode+"'] a span").html(pageType.count);
									oldPageTypeCodeDeal = true;
								}
							}
							if( oldPageTypeCode && newPageTypeCode != oldPageTypeCode && !oldPageTypeCodeDeal){
								$("#menulist li[page-type-code='"+oldPageTypeCode+"'] a span").html("0");
							}
							var paginationCount =  $("#container>div:not([page-type-code])").length;
							$("#unPaginationSpan").html(paginationCount);
						}
						else{
							alert("编页失败")
						}
						console.log(datas)
					}
				});
				$(checkedImage).attr("page-type-code",newPageTypeCode);
				$(checkedImage).attr("sort-code",sortCode);
	         	$(checkedImage).addClass("image_bined").removeClass("image_checked").removeAttr("checked");
	         	var paginationCount =  $("#container>div:not([page-type-code])").length;
				var imageDivs =$("#container>div");
				$("#paginationRateSpan").html((imageDivs.length-paginationCount)+"/"+imageDivs.length);
	         
	         	
	         	$("#container>div").sort(sort_li).appendTo('#container');
	         	
	          }
	        });
	    });
   	
   });
    
   /*  $("#filepage li").sort(sort_li) // sort elements   JS的sort()方法
            .appendTo('#filepage'); */
	
    
</script>