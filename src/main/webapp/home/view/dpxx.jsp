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
<script src="${ctx }/static/js/_dpxx.js"></script>
<div class="col-xs-12 col-sm-12 col-md-12 row">
	<div class="" id="book_accordion-element">
		<div class="col-xs-12 col-sm-12 col-md-12">

			<form class=" col-xs-15 col-sm-15 col-md-15" id="query_splb_form" role="form">
				<table>
					<tr>
						<td>店铺名称：</td>
						<td><input style="width:100px;" id="query_key_item_name" name="name" class="form-control"></td>
						<td>手机号：</td>
						<td><input style="width:100px;" id="query_phone" name="phone" class="form-control"></td>
						<td>QQ号：</td>
						<td><input style="width:100px;" id="query_qq" name="qq" class="form-control"></td>
						<td>微信号：</td>
						<td><input style="width:100px;" id="query_wexin" name="wechat" class="form-control"></td>
                        <td>排序列：</td>
                        <td><select name="orderColumn" id="orderColumn" style="width: 100px;" class="form-control">
							<option value="total_settlement">总结算</option>
                            <option value="id">店铺ID</option>
                            <option value="item_num">推广产品数</option>
                            <option value="real_item_num">推广次数</option>
                            <option value="price">产品客单价(均)</option>
                            <option value="settlement">单次推广结算(均)</option>
							<option value=""></option>
						<td>排序：</td>
                        <td><select name="order" id="order" style="width: 100px;" class="form-control">
							<option value="desc">降序</option>
							<option value="asc">升序</option>
							<option value=""></option>
						</select>
                        </td>
                    </tr>
                    <tr>
                        <td>产品ID：</td>
                        <td><input style="width:100px;" id="query_item_no" name="item_no" class="form-control"></td>
						<td>产品均客单价：</td>
						<td><input style="width:100px;" id="price_min" name="price_min" class="form-control"></td>
						<td>---------</td>
						<td><input style="width:100px;" id="price_max" name="price_max" class="form-control"></td>
						<td>总结算金额：</td>
						<td><input style="width:100px;" id="shje_min" name="settlement_min" class="form-control"></td>
						<td>---------</td>
						<td><input style="width:100px;" id="shje_max" name="settlement_max" class="form-control"></td>

						<td><input type="hidden" name="hz" value="true">
							<button class="btn btn-primary form-control" type="button" onclick="queryItem()">
							<i class="glyphicon glyphicon-search"></i>搜索
							</button>

						</td>
                        <td><button class="btn btn-primary form-control" type="button" onclick="addShop(0)">
							添加店铺
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
	</div>
	<div id="case_paginator" style="align: center; float: left; cursor: pointer;"></div>
</div>
