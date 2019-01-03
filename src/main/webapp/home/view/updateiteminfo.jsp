<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taosearch.util.Result"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script language="javascript" type="text/javascript" src="${ctx }/static/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
    var item_id = "${param.item_id}";
    var page = "${param.page}";
    var rows = "${param.rows}";
    var before_audit_status;
    $(function() {
        $('#activity_start_time_div').datetimepicker({
            todayBtn : true,
            showMeridian : true,
            timepicker:false,    //关闭时间选项
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
        $('input[name="item_jhlb"]').change(function() {
            if ($(this).val() == '002' || $(this).val() == '004' || $(this).val() == '005') {

                if ($(this).val() == '004'){
                    $("#item_jhlj_id").attr("placeholder","如：http://s.click.taobao.com/5evMsiw?pid=mm_0_0_0  （只有商家能提供）");
                } else {
                    $("#item_jhlj_id").attr("placeholder","");
                }
                if ($(this).val() == '005'){
                    $("#jh_td_id").html("活动ID:");
                }else {
                    $("#jh_td_id").html("计划链接:");
                }
                $("#item_jhlj_tr_id").show();
            } else {
                $("#item_jhlj_tr_id").hide();
            }
        });
        $('input[name="coupon_type_id"]').change(function() {
            if ($(this).val() == '1') {
                $("#tr_coupon_url_id").show();
                $("#tr_coupon_rest_num").show();
                $("#tr_item_yjbl_id").show();
            }else {
                $("#tr_coupon_url_id").hide();
                $("#tr_coupon_rest_num").hide();
                $("#tr_item_yjbl_id").hide();

            }
        });
        initItemType();
        getItemInfo();
        $("#item_update_form").html5Validate(function() {
            updateItemInfo();
        });
    });
    function updateItemInfo() {
        var data = new FormData($("#item_update_form")[0]);
        data.append("item_id", item_id);
        data.append("before_audit_status", before_audit_status);
        console.log(data);
        $.ajax({
            url : $ctx + '/order/updateItemInfo',
            type: 'POST',
            data: data,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success : function(data) {
                if (data.code == 1) {
                    $('#alert_dialog_success').html(data.message);
                    $('#modal_success').modal('show');
                    gobackpage();
                } else {
                    $('#alert_dialog_danger').html(data.message);
                    $('#modal_danger').modal('show');
                }
            }
        });

    }

    function getItemInfo1() {
        console.log("=========>开始逻辑")
        var item_url = $("#item_url_id").val();
        console.log(item_url);
        var data = {
            "item_url" : item_url,
            "status" : before_audit_status
        }
        $.ajax({
            url : $ctx + '/order/getItemInfo',
            data : data,
            type : 'post',
            dataType : 'json',
            success : function(data) {
                if (data.code == 1) {
                    $("#item_no_id").val(data.data.item_no);
                    $("#item_name_id").val(data.data.item_name);
                    $("#item_image_id").val(data.data.item_image);
                    $("#item_title_id").val(data.data.item_title);
                    $("#item_website_type_id").val(data.data.item_website_type);
                }else{
                    $('#alert_dialog_danger').html(data.message);
                    $('#modal_danger').modal('show');
                }
            }
        });
    }
    // 序列化表单
    function serializeObject(form) {// 将表单元素中的值序列化成对象
        var o = {};
        $.each(form.serializeArray(), function(index_) {
            if (o[this['name']]) {
                o[this['name']] = o[this['name']] + "," + this['value'];

            } else {
                o[this['name']] = this['value'];
            }
        });
        return o;
    }
    function getItemInfo() {
        console.log("==========================>进入详情页面商品id为" + item_id);
        var data = {
            item_id : item_id
        }
        $.ajax({
            url : $ctx + '/order/getItemInfoById',
            type : 'post',
            data : data,
            async : false,
            dataType : 'json',
            success : function(data) {
                data.data.item_type = getItemTypeValue(data.data.item_type);
                console.log(data);
                before_audit_status = data.data.state;
                $('#item_update_form').form('load', data.data);
                $("#item_no_id").val("");
                if (data.data.item_jhlb == '005'){
                    $("#jh_td_id").html("活动ID:");
                } else if (data.data.item_jhlb == '002' || data.data.item_jhlb == '004') {
                    $("#item_jhlj_tr_id").show();
                } else {
                    $("#item_jhlj_tr_id").hide();
                }
                $('#activity_start_time').val(
                    data.data.activity_start_time);
                $('#coupon_start_time').val(
                    getTimeDay(data.data.coupon_start_time));
                $('#coupon_end_time')
                    .val(getTimeDay(data.data.coupon_end_time));

                if ( data.data.coupon_type_id == '1') {
                    $("#tr_coupon_url_id").show();
                    $("#tr_coupon_rest_num").show();
                    $("#tr_item_yjbl_id").show();
                }else {
                    $("#tr_coupon_url_id").hide();
                    $("#tr_coupon_rest_num").hide();
                    $("#tr_item_yjbl_id").hide();
                }
            }
        });
    };
    $('input[name="coupon_type_id"]').change(function() {
        if ($(this).val() == '1') {
            $("#tr_coupon_url_id").show();
            $("#tr_coupon_rest_num").show();
            $("#tr_item_yjbl_id").show();
        }else {
            $("#tr_coupon_url_id").hide();
            $("#tr_coupon_rest_num").hide();
            $("#tr_item_yjbl_id").hide();

        }
    });
    function getTimeHHDay(str) {
        if (str == null || str == "") {
            return "";
        } else {
            return str.substring(0, 16);
        }
    }
    function initItemType() {
        $.ajax({
            url : $ctx + '/order/getItemTypes',
            type : 'get',
            async : false,
            dataType : 'json',
            success : function(datas) {
                $("#item_type_select").html("");
                $("#item_type_select").append(
                    '<option class="form-control" value="">所有分类</option>');
                for (var i = 0; i < datas.length; i++) {
                    $("#item_type_select").append(
                        '<option class="form-control" value="'
                        + datas[i].type_id + '">'
                        + datas[i].type_name + '</option>');
                }
            }
        });
    }
    function gobackpage() {
        p = {
            page : page,
            rows : rows
        }
        $('#showId').load($ctx + "/home/view/index2.jsp", p);
        $('#li_name').html("商品列表");
    }
    function getItemTypeValue(type) {
        var key = type;
        var value;
        switch (key) {
            case '女装':
                value = '001';
                break;
            case '男装':
                value = '002';
                break;
            case '内衣':
                value = '003';
                break;
            case '母婴':
                value = '004';
                break;
            case '化妆品':
                value = '005';
                break;
            case '居家':
                value = '006';
                break;
            case '鞋包配饰':
                value = '007';
                break;
            case '美食':
                value = '008';
                break;
            case '文体车品':
                value = '009';
                break;
            case '数码家电':
                value = '010';
                break;
            default:
                value = '';
        }
        return value;
    }
</script>
<div class="col-xs-12 col-sm-12 col-md-12 row">
	<div class="" id="book_accordion-element">
		<div class="col-xs-12 col-sm-12 col-md-12">
			<form class=" col-xs-12 col-sm-12 col-md-12" id="item_update_form" role="form" enctype="multipart/form-data" method="post">
				<input id="item_image_id" name="item_image" type="hidden"> <input id="item_title_id" name="item_title" type="hidden"> <input id="item_website_type_id" name="item_website_type" type="hidden">
				<table style="width: 90%; border-collapse: separate; border-spacing: 10px">
					<tr>
						<td style="width: 10%">商品链接：</td>
						<td><textarea id="item_url_id" name="item_url" class="form-control" style="resize: none; width: 99%; overflow-y: scroll;" required></textarea><span style="color: #cc0001">*</span></td>
					</tr>
					<tr>
						<td></td>
						<td><div class="form-inline">
							<a class="btn btn-primary form-control" onclick="getItemInfo1()" href="#">获取产品链接</a>
						</div></td>
					</tr>
					<tr>
						<td>商品ID：</td>
						<td class="form-inline"><input  id="item_no_id" name="item_no" style="width: 30%" class="form-control"><span style="color: #cc0001">&nbsp;&nbsp;*</span></td>
					</tr>
					<tr>
						<td>商品分类：</td>
						<td><select name="item_type" id="item_type_select" style="width: 150px;" class="form-control" >
							<option value="">所有分类</option>
						</select><span style="color: #cc0001">*</span></td>
					</tr>
					<tr>
						<td>活动类型：</td>
						<td><input type="radio" name="item_hdlx" value="001" checked="checked">普通活动&nbsp;&nbsp;<input  type="radio" name="item_hdlx" value="002">淘抢购&nbsp;&nbsp;<input type="radio" name="item_hdlx" value="003">聚划算</td>
					</tr>
					<tr>
						<td>活动开始时间：</td>
						<td><div id="activity_start_time_div1" class="input-append date form-group">
							<input type="text" name="activity_start_time" id="activity_start_time" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
						</div></td>
					</tr>
					<tr>
						<td>备注信息：</td>
						<td><textarea id="remark_id" name="remark" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>
					</tr>
					<tr>
						<td>商品主图：</td>
						<td><textarea id="item_main_image" name="item_main_image" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>
					</tr>
					<tr>
						<td>推广主图：</td>
						<td><textarea id="item_generalize_main_image" name="item_generalize_main_image" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>

					</tr>
					<tr>
						<td>商品名称：</td>
						<td><textarea  id="item_name_id" name="item_name" required data-max="20" data-min="12" class="form-control" style="resize: none; overflow-y: scroll;"></textarea><span style="color: #cc0001">12到20个字符*</span></td>
					</tr>
					<tr>
						<td>券后价格：</td>
						<td class="form-inline"><input placeholder="请输入券后单价，可保留两位小数" pattern="^[0-9]+(.[0-9]{1,2})?$" id="item_qhjg_id" required name="item_qhjg" style="width: 30%" class="form-control"><span style="color: #cc0001">&nbsp;&nbsp;*</span></td>
					</tr>
					<tr>
						<td>优惠卷类别：</td>
						<td><input type="radio" name="coupon_type_id" value="1" checked="checked">普通券&nbsp;&nbsp;
							<input type="radio" name="coupon_type_id" value="2">阿里妈妈推广卷&nbsp;&nbsp;</td>
					</tr>
					<tr id="tr_coupon_url_id">
						<td>优惠券地址：</td>
						<td><textarea id="coupon_url_id" name="coupon_url" class="form-control" style="resize: none; overflow-y: scroll;"></textarea><span style="color: #cc0001">*</span></td>
					</tr>

					<tr id="tr_coupon_rest_num">
						<td>优惠卷数量：</td>
						<td class="form-inline"><input id="coupon_rest_num" name="coupon_rest_num" style="width: 10%" class="form-control" pattern="^[0-9]*$"></td>
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
					<tr id="tr_item_yjbl_id">
						<td>佣金比例：</td>
						<td class="form-inline"><input  placeholder="请输入佣金比例，可保留两位小数" pattern="^[0-9]+(.[0-9]{1,2})?$" id="item_yjbl_id" required name="item_yjbl" style="width: 30%" class="form-control"> <span style="color: #cc0001">&nbsp;&nbsp;*</span></td>
					</tr>
					<tr >
						<td>计划类别：</td>
						<td><input type="radio" name="item_jhlb" value="001" checked="checked">通用<input  type="radio" name="item_jhlb" value="002">定向
							<input  type="radio" name="item_jhlb" value="003">鹊桥<input type="radio" name="item_jhlb" value="004">营销计划<input type="radio" name="item_jhlb" value="005">营销计划—团长</td>
					</tr>
					<tr id="item_jhlj_tr_id">
						<td id="jh_td_id">计划链接：</td>
						<td><textarea  id="item_jhlj_id" name="item_jhlj" class="form-control" style="resize: none; overflow-y: scroll;"></textarea><span style="color: #cc0001">*</span></td>
					</tr>

					<tr>
						<td>视频地址：</td>
						<td><textarea placeholder="格式：http://tbm.alicdn.com/8KCicAdNjHXuTZtqyoW/hqEzoXHLRuB%40%40hd.mp4"  id="item_image_backup_id" name="item_image_backup" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>
					</tr>
					<tr>
						<td>卖点填写：</td>
						<td><textarea id="item_md_id" required name="item_md" class="form-control" style="resize: none; overflow-y: scroll;"></textarea><span style="color: #cc0001">*</span></td>
					</tr>
					<tr>
						<td>联系电话：</td>
						<td>
							<input placeholder="请输入电话号码" id="phone_id" required name="phone" style="width: 15%"
								   class="form-control" pattern="^[0-9]*$">
						</td>
					</tr>
					<tr>
						<td>联系QQ：</td>
						<td class="form-inline">
							<input placeholder="请输入QQ号码" id="qq_id" required name="qq" style="width: 15%"
								   class="form-control" pattern="^[0-9]*$">
						</td>

					</tr>
					<tr>
						<td>收费类型：</td>
						<td class="form-inline">
							<div style="float:left; display:inline;width: 30%">
								<input type="radio" name="item_sflx" value="0" checked="checked">线下结算 单/元
								<input type="radio" name="item_sflx" value="1">团长结算 单/%
								<span style="color: #cc0001">&nbsp;&nbsp;*
								</span>
							</div>
							<%--<div style="float:left; display:inline;color: red">--%>
								<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
								<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>

								<%--阿里妈妈推广券<br>--%>
								<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
								<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;①计划类别必须为营销计划！--%>
							<%--</div>--%>

						</td>

					</tr>
					<tr>
						<td>收费单价：</td>
						<td class="form-inline">
							<div style="float:left; display:inline;width: 30%">

								<input placeholder="可保留两位小数" pattern="^[0-9]+(.[0-9]{1,2})?$" id="item_fwdj_id" required
									   name="item_fwdj"  class="form-control"><span
									style="color: #cc0001">&nbsp;&nbsp;&nbsp;*</span>


							</div>
							<%--<div style="float:left; display:inline;color: red">--%>
								<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
								<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
								<%--②请确认商家已将阿里妈妈券绑定在营销计划上！<br>--%>
								<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
								<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;③请确认营销计划已生效（包括预告商品）！--%>
							<%--</div>--%>
						</td>

					</tr>
					<tr>
						<td>买家实拍图：</td>
						<td>
                            <input  name="file" type="file" multiple="multiple"></td>
					</tr>
					<tr>
						<td>实拍图文案：</td>
						<td><textarea id="real_image_text_id" name="real_image_text" class="form-control"
									  style="resize: none; overflow-y: scroll;"></textarea></td>
					</tr>
					<tr>
						<td>朋友圈文案：</td>
						<td><textarea placeholder=" 增加朋友圈内容有助于单子在更多渠道展现，提高跑量。"
									  id="friend_text_id" name="friend_text" class="form-control"
									  style="resize: none; overflow-y: scroll;"></textarea></td>
					</tr>
					<tr>
						<td></td>
						<td><div class="form-inline">
							<button class="btn btn-primary form-control" type="submit">
								<i class="glyphicon glyphicon-saved"></i>再次提交
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