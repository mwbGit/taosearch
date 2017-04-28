<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taosearch.util.Result"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
</script>
<script src="${ctx }/static/js/_yhgl.js"></script>
<!-- 	<div class="col-xs-12 col-sm-12 col-md-12 row"> -->
<!-- 		<div class="" id="book_accordion-element"> -->
<!-- 			<div class="col-xs-12 col-sm-12 col-md-12"> -->

<!-- 				<form class="form-inline col-xs-12 col-sm-12 col-md-12" id="query_form" role="form"> -->
<!-- 						<div class="form-group"> -->
<!-- 							<label for="query_case_status_id" class="form-control-static">状态：</label> -->
<!-- 							<select name="case_status_id" id="query_case_status_id" style="width:150px;" class="form-control" onchange="qyery_selectchange()"> -->
<!-- 								<option value="" class="form-control"> -->
<!-- 								<option value="10" class="form-control">待鉴定 -->
<!-- 								<option value="20" class="form-control">已鉴定 -->
<!-- 							</select> -->
<!-- 							<label for="query_caseregister_result_id" class="form-control-static">鉴别结果：</label> -->
<!-- 							<select name="caseregister_result_id" id="query_caseregister_result_id" style="width:150px;" class="form-control" disabled readonly> -->
<!-- 								<option value="" class="form-control"> -->
<!-- 								<option value="1" class="form-control">真 -->
<!-- 								<option value="0" class="form-control">假 -->
<!-- 							</select> -->
<!-- 							<button class="btn btn-primary form-control" type="button" -->
<!-- 								onclick="queryCase()"> -->
<!-- 								<i class="glyphicon glyphicon-search"></i>查询 -->
<!-- 							</button> -->
<!-- 						</div> -->
<!-- 	<hr /> -->
<!-- 				</form> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<div class="col-xs-12 col-sm-12 col-md-12">
			<button class="btn btn-success book_theme_flag2" type="button" data-toggle="modal"
				data-target="#addBooks" onclick="showAddUserModel()"> 
 				<i class="glyphicon glyphicon-plus"></i>新增用户 
			</button> 
			<div style="float:right"><a href="#" onclick="skipTeamList()"><i class="glyphicon glyphicon-hand-right"></i>小组管理</a></div>
	<!-- 			<div class="book_theme_flag1 btn-group"> -->
	<!-- 			<button class="btn btn-success" type="button" data-toggle="modal" -->
	<!-- 				data-target="#addBooks" onclick=""> -->
	<!-- 				<i class="glyphicon glyphicon-plus"></i>新增 -->
	<!-- 			</button> -->
	<!-- 			<button class="btn btn-danger" type="button" onclick="beforeDelete(1)" -->
	<!-- 				id="button_delete"> -->
	<!-- 				<i class="glyphicon glyphicon-trash"></i>刪除 -->
	<!-- 			</button> -->
	<!-- 			<button class="btn btn-info" type="button" data-toggle="modal" -->
	<!-- 				onclick="beforeEdit()"> -->
	<!-- 				<i class="glyphicon glyphicon-edit"></i>编辑 -->
	<!-- 			</button> -->
	<!-- 			</div> -->
	<div class="book_theme_flag1">
		<table id="user_Info"
			class="table table-striped table-advance table-hover table-condensed">
			<%-- 			<caption class="text-center">数据信息</caption> --%>
		</table>
	</div>
	<!-- 	<div class="row-fluid book_theme_flag2"> -->
	<!-- 		<ul class="blog-images" id="books_images" style="padding-left:0"> -->
	<!-- 		</ul> -->
	<!-- 	</div> -->
	<div id="user_paginator"
		style="align: center; float: left; cursor: pointer;"></div>
	<!-- 	<div id="afterpagination" style="float:left;margin-top:20px"><ul class="pagination "><li class="disabled" ><a id="book_totalpageAndTotals"></a></li></ul></div> -->
</div>
