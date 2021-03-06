<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taosearch.util.Result"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ua" value="${sessionScope.userAuthorization.authorization}"></c:set>
<c:set var="team_id" value="${sessionScope.userAuthorization.team_id}"></c:set>
<script type="text/javascript">
    var $p_page = "${param.page}";
    var $p_rows = "${param.rows}";
    var $ua="${ua}";
    var $team_id="${team_id}";
    $(function() {
        console.log("=========page=====>" + ($p_page == ""));
        console.log("=========rows=====>" + ($p_rows == ""));
        $('#query_splb_start_time_div').datetimepicker({
            todayBtn : true,
            showMeridian : true,
            startView : 0,
            autoclose : true
        });

        $('#query_splb_end_time_div').datetimepicker({
            todayBtn : true,
            showMeridian : true,
            startView : 0,
            autoclose : true
        });
        $('#query_hd_start_time_div').datetimepicker({
            todayBtn : true,
            showMeridian : true,
            startView : 0,
            autoclose : true
        });

        $('#query_hd_end_time_div').datetimepicker({
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
<script src="${ctx }/static/js/_index2.js"></script>
<div class="col-xs-12 col-sm-12 col-md-12 row">
	<div class="" id="book_accordion-element">
		<div class="col-xs-12 col-sm-12 col-md-12">
			<form class=" col-xs-15 col-sm-15 col-md-15" id="query_splb_form1" role="form">
				<table>
					<tr><td>
						<input  style="width:300px;" id="taoke_url" name="item_no" class="form-control" placeholder="产品ID"></td>
						<td><button class="btn btn-primary form-control" type="button" onclick="getDataokeDatas()">
							<i class="glyphicon glyphicon-search"></i>搜索
						</button>	</td>
						<td id="td6"  style="display: none"><span style="color: red" id="span6">&nbsp&nbsp查询无结果</span></td>
						<td id="td1" style="display: none">&nbsp卷后价格：<span style="color: red" id="span1"></span></td>
						<td id="td2" style="display: none">&nbsp 佣金比例：<span id="span2" style="color: red"></span></td>
						<td id="td3" style="display: none">&nbsp 领取数量：<span id="span3" style="color: red"></span></td>
						<td id="td4" style="display: none"> &nbsp 剩余数量：<span id="span4" style="color: red"></span></td>
					</tr>
					<tr><td colspan="2" id="td8" style=" display: none">&nbsp 历史推广：<span id="span8" style="color: red"></span></td>
						<td colspan="5" id="td5" style=" display: none"> &nbsp优惠券结束时间：<span id="span5" style="color: red"></span></td>

					</tr>
					<tr><td colspan="2">&nbsp</td>
						<td id="td7" colspan="5" style=" display: none"> &nbsp标题：<span id="span7" style="color: red"></span></td>

					</tr>
				</table>
			</form>
			<nav class="navbar navbar-default" role="navigation">
				<div>
					<ul class="nav navbar-nav" id="nav_state_query">
					</ul>
				</div>
			</nav>
			<form class=" col-xs-15 col-sm-15 col-md-15" id="query_splb_form" role="form">
				<table>
					<tr>
						<td width="12%"><select name="item_type" id="query_item_type_select" class="form-control">
							<option value="">所有分类</option>
						</select></td>
						<td width="12%"> <select name="team_id" id="query_team_select" onchange="query_team_selectchange()" class="form-control">
							<option value="">选择小组</option>
						</select></td>
						<td width="12%"><select name="user_id" id="query_user_select"  class="form-control">
							<option value="">选择业务员</option>
						</select></td>
						<td width="8%">提交时间：</td>
						<td width="25%"><div class="form-inline">
							<div id="query_splb_start_time_div" class="input-append date form-group">
								<input style="width: 100px" id="query_start_time" name="query_start_time" type="text" class="add-on form-control" data-format="yyyy-MM-dd">-
							</div>
							<div id="query_splb_end_time_div" class="input-append date form-group">
								<input style="width: 100px" id="query_end_time" name="query_end_time" type="text" class="add-on form-control" data-format="yyyy-MM-dd">
							</div>

						</div></td>
						<td width="8%">活动时间：</td>
						<td width="25%"><div class="form-inline">
							<div id="query_hd_start_time_div" class="input-append date form-group">
								<input style="width: 100px" id="activity_begin_time" name="activity_begin_time" type="text" class="add-on form-control" data-format="yyyy-MM-dd">-
							</div>
							<div id="query_hd_end_time_div" class="input-append date form-group">
								<input style="width: 100px" id="activity_end_time" name="activity_end_time" type="text" class="add-on form-control" data-format="yyyy-MM-dd">
							</div>

						</div></td>
					<tr>
				</table>
				<table>
					<tr>
						<td>商品ID：</td>
						<td><input style="width:100px;" id="query_item_no" name="item_no" class="form-control"></td>
						<td>关键字：</td>
						<td><input style="width:100px;" id="query_key_item_name" name="item_name" class="form-control"></td>
						<td>QQ：</td>
						<td><input style="width:100px;" id="qqq" name="qq" class="form-control"></td>
						<td>优惠券领取数量：</td>
						<td><input style="width:100px;" id="coupon_get_num_min" name="coupon_get_num_min" class="form-control"></td>
						<td>-</td>
						<td><input style="width:100px;" id="coupon_get_num_max" name="coupon_get_num_max" class="form-control"></td>
						<td><button class="btn btn-primary form-control" type="button" onclick="queryItem()">
							<i class="glyphicon glyphicon-search"></i>搜索
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
		<table id="case_Info"  class="table table-bordered table-advance table-hover table-condensed" style="font-size: 12px;">
		</table>
		<button id="hPay" onclick="hPay()" class="btn btn-primary"  type="button" style="width: 8%;display: none" >合并付款</button>
	</div>

	<div id="case_paginator" style="align: center; float: left; cursor: pointer;"></div>
</div>
