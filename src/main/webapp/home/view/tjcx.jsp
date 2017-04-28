<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taosearch.util.Result"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	$(function() {
		$('#activity_start_time_div').datetimepicker({
			todayBtn : true,
			showMeridian : true,
			startView : 0,
			autoclose : true
		});

		$('#coupon_start_time_div').datetimepicker({
			todayBtn : true,
			showMeridian : true,
			startView : 0,
			autoclose : true
		});
		$('#coupon_end_time_div').datetimepicker({
			todayBtn : true,
			showMeridian : true,
			startView : 0,
			autoclose : true
		});
		$("#item_jhlj_tr_id").hide();

		$('input[name="item_jhlb"]').change(function() {
			if ($(this).val() == '002') {
				$("#item_jhlj_tr_id").show();
			} else {
				$("#item_jhlj_tr_id").hide();
			}
		});
	});
</script>
<script src="${ctx }/static/js/item.js"></script>
<div class="col-xs-12 col-sm-12 col-md-12 row">
	<div class="" id="book_accordion-element">
		<div class="col-xs-12 col-sm-12 col-md-12">
			<form class=" col-xs-12 col-sm-12 col-md-12" id="item_submit_form" role="form">
				<input id="item_image_id" name="item_image" type="hidden"> <input id="item_title_id" name="item_title" type="hidden"> <input id="item_website_type_id" name="item_website_type" type="hidden">
				<table style="width: 90%; border-collapse: separate; border-spacing: 10px">
					<tr>
						<td style="width: 10%">商品链接：</td>
						<td><textarea id="item_url_id"  name="item_url" class="form-control" style="resize: none; width:99%;overflow-y: scroll;" required></textarea><span style="color: #cc0001">*</span></td>
					</tr>
					<tr>
						<td></td>
						<td><div class="form-inline">
								<a class="btn btn-primary form-control" onclick="getItemInfo()" href="#">获取产品链接</a>
							</div></td>
					</tr>
					<tr>
						<td >商品ID：</td>
						<td class="form-inline"><input id="item_no_id" name="item_no" style="width: 30%" class="form-control"><span style="color: #cc0001">&nbsp;&nbsp;*</span></td>
					</tr>
					<tr>
						<td>商品分类：</td>
						<td><select name="item_type" id="item_type_select" style="width: 150px;" class="form-control">
								<option value="">所有分类</option>
						</select><span style="color: #cc0001">*</span></td>
					</tr>
					<tr>
						<td>活动类型：</td>
						<td><input type="radio" name="item_hdlx" value="001" checked="checked">普通活动&nbsp;&nbsp;<input type="radio" name="item_hdlx" value="002">淘抢购&nbsp;&nbsp;<input type="radio" name="item_hdlx" value="003">聚划算</td>
					</tr>
					<tr>
						<td>商品名称：</td>
						<td><textarea id="item_name_id" name="item_name" required data-max="20" data-min="12" class="form-control" style="resize: none; overflow-y: scroll;"></textarea><span style="color: #cc0001">12到20个字符*</span></td>
					</tr>
					<tr>
						<td>券后价格：</td>
						<td class="form-inline"><input placeholder="请输入券后单价，可保留两位小数" pattern="^[0-9]+(.[0-9]{1,2})?$" id="item_qhjg_id" required name="item_qhjg" style="width: 30%" class="form-control"><span style="color: #cc0001">&nbsp;&nbsp;*</span></td>
					</tr>
					<tr>
						<td>优惠券地址：</td>
						<td><textarea id="coupon_url_id" required name="coupon_url" class="form-control" style="resize: none; overflow-y: scroll;"></textarea><span style="color: #cc0001">*</span></td>
					</tr>
					<tr>
						<td></td>
						<td><div class="form-inline">
								<a class="btn btn-primary form-control" onclick="getCouponInfo()" href="#">抓取优惠券</a>
							</div></td>
					</tr>
					<tr>
						<td>佣金比例：</td>
						<td class="form-inline"><input placeholder="请输入佣金比例，可保留两位小数" pattern="^[0-9]+(.[0-9]{1,2})?$" id="item_yjbl_id" required name="item_yjbl" style="width: 30%" class="form-control"> <span style="color: #cc0001">&nbsp;&nbsp;*</span></td>
					</tr>
					<tr>
						<td>计划类别：</td>
						<td><input type="radio" name="item_jhlb" value="001" checked="checked">通用<input type="radio" name="item_jhlb" value="002">定向<input type="radio" name="item_jhlb" value="003">鹊桥</td>
					</tr>
					<tr id="item_jhlj_tr_id">
						<td>定向连接：</td>
						<td><textarea id="item_jhlj_id" name="item_jhlj" class="form-control" style="resize: none; overflow-y: scroll;"></textarea><span style="color: #cc0001">*</span></td>
					</tr>
					<tr>
						<td>活动开始时间：</td>
						<td><div id="activity_start_time_div" class="input-append date form-group">
								<input style="width: 30%" id="activity_start_time" name="activity_start_time" type="text" class="add-on form-control" data-format="yyyy-MM-dd">
							</div></td>
					</tr>
					<tr>
						<td>优惠券有效期：</td>
						<td><div class="form-inline">
								<div id="coupon_start_time_div" class="input-append date form-group">
									<input id="coupon_start_time" name="coupon_start_time" type="text" class="add-on form-control" data-format="yyyy-MM-dd">-
								</div>
								<div id="coupon_end_time_div" class="input-append date form-group">
									<input id="coupon_end_time" name="coupon_end_time" type="text" class="add-on form-control" data-format="yyyy-MM-dd">
								</div>
							</div></td>
					</tr>
					<tr>
						<td>备注信息：</td>
						<td><textarea id="remark_id" name="remark" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>
					</tr>
					<tr>
						<td>补充主图：</td>
						<td><textarea id="item_image_backup_id" name="item_image_backup" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>
					</tr>
					<tr>
						<td>卖点填写：</td>
						<td><textarea id="item_md_id" required name="item_md" class="form-control" style="resize: none; overflow-y: scroll;"></textarea><span style="color: #cc0001">*</span></td>
					</tr>
					<tr>
						<td>联系QQ：</td>
						<td class="form-inline"><input placeholder="请输入QQ号码" id="qq_id" required name="qq" style="width: 30%" class="form-control" pattern="^[0-9]*$"><span style="color: #cc0001">&nbsp;&nbsp;*</span></td>
					</tr>
					<tr>
						<td>收费单价：</td>
						<td class="form-inline"><input  placeholder="请输入收费单价，可保留两位小数" pattern="^[0-9]+(.[0-9]{1,2})?$" id="item_fwdj_id" required name="item_fwdj" style="width: 30%" class="form-control"><span style="color: #cc0001">&nbsp;&nbsp;*</span></td>
					</tr>
					<tr>
						<td></td>
						<td><div class="form-inline">
								<button class="btn btn-primary form-control" type="submit" >
									<i class="glyphicon glyphicon-saved"></i>提交
								</button>
								<button class="btn btn-primary form-control" type="reset">
									<i class="glyphicon glyphicon-repeat"></i>重置
								</button>
							</div></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<div class="col-xs-12 col-sm-12 col-md-12">
	<div class="book_theme_flag1">
		<label for="case_Info" class="form-control-static" id="case_Info_label"></label>
		<hr />
		<table id="case_Info" class="table table-striped table-advance table-hover table-condensed">
			<%-- 			<caption class="text-center">数据信息</caption> --%>
		</table>
	</div>

	<div id="case_paginator" style="align: center; float: left; cursor: pointer;"></div>




</div>