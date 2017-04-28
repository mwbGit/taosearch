<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taosearch.util.Result"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ua" value="${sessionScope.userAuthorization.authorization}"></c:set>
<c:set var="team_id" value="${sessionScope.userAuthorization.team_id}"></c:set>
<script type="text/javascript">
	var $ua = "${ua}";
	var $team_id = "${team_id}";
	$(function() {
		$('#query_create_start_time_div').datetimepicker({
			todayBtn : true,
			showMeridian : true,
			startView : 0,
			autoclose : true
		});

		$('#query_create_end_time_div').datetimepicker({
			todayBtn : true,
			showMeridian : true,
			startView : 0,
			autoclose : true
		});
		$('#query_update_start_time_div').datetimepicker({
			todayBtn : true,
			showMeridian : true,
			startView : 0,
			autoclose : true
		});

		$('#query_update_end_time_div').datetimepicker({
			todayBtn : true,
			showMeridian : true,
			startView : 0,
			autoclose : true
		});
	})
</script>
<style type="text/css">
.navbar {
	min-height: 30px
}
</style>
<script src="${ctx }/static/js/financialStatements.js"></script>
<div class="col-xs-12 col-sm-12 col-md-12 row">
	<div class="" id="book_accordion-element">
		<div class="col-xs-12 col-sm-12 col-md-12">
			<form class=" col-xs-15 col-sm-15 col-md-15" id="query_splb_form" role="form">
				<table>
					<tr>
						<td>选择小组：</td>
						<td><select name="team_id" id="query_team_select" onchange="query_team_selectchange()" style="width: 150px;" class="form-control">
								<option value="">选择业务员小组</option>
						</select></td>
						<td><select name="user_id" id="query_user_select" style="width: 150px;" class="form-control">
								<option value="">选择业务员</option>
						</select></td>
						<td>提交时间：</td>
						<td>
							<div class="form-inline">
								<div id="query_create_start_time_div" class="input-append date form-group">
									<input id="create_start_time" name="create_start_time" type="text" class="add-on form-control" data-format="yyyy-MM-dd">-
								</div>
							</div>
						</td>
						<td>
							<div class="form-inline">
								<div id="query_create_end_time_div" class="input-append date form-group">
									<input id="create_end_time" name="create_end_time" type="text" class="add-on form-control" data-format="yyyy-MM-dd">
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>付款时间：</td>
						<td>
							<div class="form-inline">
								<div id="query_update_start_time_div" class="input-append date form-group">
									<input id="update_start_time" name="update_start_time" type="text" class="add-on form-control" data-format="yyyy-MM-dd">-
								</div>
							</div>
						</td>
						<td>
							<div class="form-inline">
								<div id="query_update_end_time_div" class="input-append date form-group">
									<input id="update_end_time" name="update_end_time" type="text" class="add-on form-control" data-format="yyyy-MM-dd">
								</div>
							</div>
						</td>
						<td><button class="btn btn-primary form-control" type="button" onclick="queryItem()">
								<i class="glyphicon glyphicon-search"></i>搜索
							</button></td>
						<td><button class="btn btn-primary form-control" type="button" style="width: 40%"onclick="downloadCase()">
								<i class="glyphicon glyphicon-download-alt"></i>导出
							</button></td>
					</tr>
				</table>
				<hr />
			</form>
		</div>
	</div>
</div>
<!-- 	<legend></legend> -->
<div class="col-xs-15 col-sm-15 col-md-15">
	<div class="book_theme_flag1">
		<table id="case_Info" class="table table-bordered table-advance table-hover table-condensed" style="font-size: 12px;">
		</table>
	</div>
	<div id="case_paginator" style="align: center; float: left; cursor: pointer;"></div>
</div>
