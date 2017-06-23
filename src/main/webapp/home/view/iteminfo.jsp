<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taosearch.util.Result"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ua" value="${sessionScope.userAuthorization.authorization}"></c:set>
<script type="text/javascript">
    var item_id = "${param.item_id}";
    var page = "${param.page}";
    var rows = "${param.rows}";
    var before_audit_status;
    var coupon_id;
    var $audit_logGrid = $("#audit_log_list");
    var ImgUrl = $ctx + '/img/download';
    var ua="${ua}";
    var p;
    $(function() {
        if(ua=="3"){
            $("#item_info_jhlj_tr_id").hide();
            $("#item_audit_form_001").hide();
            $("#item_audit_form_002").hide();
            $("#item_audit_form_003").hide();
            $("#item_audit_form_004").hide();
            $("#upload_item_paying_form").hide();
        }
        $("#upload_item_pay_again_form").hide();
        $("#upload_item_pay_form").hide();
        $("#upload_item_pay2_form").hide();
        $("#upload_item_pay3_form").hide();
        $("#upload_item_pay_again_item_id").val(item_id);
        $("#upload_item_pay_item_id").val(item_id);
        $("#upload_item_pay2_item_id").val(item_id);
        $("#upload_item_pay2_item_id1").val(item_id);
        getItemInfo();

        $('#activity_start_time_div').datetimepicker({
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
        $('#coupon_end_time_div1').datetimepicker({
            todayBtn : true,
            showMeridian : true,
            startView : 0,
            autoclose : true
        });

    });
    function showFrom(key) {

        switch (key) {
            case '001':
                $("#item_audit_form_001").show();
                break;
            case '002':
                $("#item_audit_form_002").show();
                break;
            case '003':
                $("#item_audit_form_003").show();
                break;
            case '009':
                $("#item_audit_form_003").show();
                break;
            case '004':
                $("#item_audit_form_004").show();
                break;
            case '006':
                $("#upload_item_paying_form").show();
                break;
        }
    }
    function upload_item_pay_form() {
        var options = {
            success : function(data) {
                if (data.code == 1) {
                    before_audit_status = data.data.state;
                    $('#alert_dialog_success').html(data.message);
                    $('#modal_success').modal('show');
                    gobackpage();
                } else {
                    $('#alert_dialog_danger').html(data.message);
                    $('#modal_danger').modal('show');
                }
            }
        };
        $("#upload_item_pay_form").ajaxForm(options);
    }
    function upload_item_pay_again_form() {
        var options = {
            success : function(data) {
                if (data.code == 1) {
                    before_audit_status = data.data.state;
                    $('#alert_dialog_success').html(data.message);
                    $('#modal_success').modal('show');
                    gobackpage();
                } else {
                    $('#alert_dialog_danger').html(r.message);
                    $('#modal_danger').modal('show');
                }
            }
        };
        $("#upload_item_pay_again_form").ajaxForm(options);
    }
    function upload_item_paying_form() {
        var options = {
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
        };
        p=options;
        $("#upload_item_pay2_form").ajaxForm(p);
    }
    function upload_item_paying_form1() {
        var options = {
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
        };
        p=options;
        $("#upload_item_pay3_form").ajaxForm(p);
    }
    function updateCoupon(form_id) {
        var data = serializeObject($("#" + form_id));
        data.coupon_id = coupon_id;
        console.log("==================>更新优惠券")
        console.log(data);
        $.ajax({
            url : $ctx + '/order/updateCoupon',
            type : 'post',
            data : data,
            async : false,
            dataType : 'json',
            success : function(r) {
                if (r.code == 1) {
                    $('#alert_dialog_success').html(r.message);
                    $('#modal_success').modal('show');
                    gobackpage();
                } else {
                    $('#alert_dialog_danger').html(r.message);
                    $('#modal_danger').modal('show');
                }
            }
        });
    }
    function submitAudit(form_id) {
        var data = serializeObject($("#" + form_id));
        data.item_id = item_id;
        data.before_audit_status = before_audit_status;
        if (before_audit_status == '003' || before_audit_status == '004') {
            data.coupon_id = coupon_id;
        }
        console.log("==================>提交审核")
        console.log(data);
        $.ajax({
            url : $ctx + '/order/saveauditLog',
            type : 'post',
            data : data,
            async : false,
            dataType : 'json',
            success : function(r) {
                if (r.code == 1) {
                    $('#alert_dialog_success').html(r.message);
                    $('#modal_success').modal('show');
                    gobackpage();
                } else {
                    $('#alert_dialog_danger').html(r.message);
                    $('#modal_danger').modal('show');
                }
            }
        });
    }
    function cancelAudit(form_id) {
        if (!window.confirm("确认取消审核？")){
            return false;
        }
        $.ajax({
            url : $ctx + '/order/cancelAudit?id=' + item_id,
            type : 'get',
            async : false,
            dataType : 'json',
            success : function() {
                gobackpage();
            }
        });
        gobackpage();
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
                console.log(data);
                before_audit_status = data.data.state;
                var key = data.data.state;
                if (ua != "2" && key == "667" && data.data.item_zfje_state == "0") {
                    $("#upload_item_pay_again_form").show();
                }else if (ua != "2" && key == "667" && data.data.item_zfje_state == "1") {
                    $("#upload_item_pay3_form").show();
                }
                else if (ua == "3") {
                    if (key == "005" && data.data.item_zfje_state == "0") {
                        $("#upload_item_pay_form").show();
                    } else if (key == "005" && data.data.item_zfje_state == "1") {
                        $("#upload_item_pay2_form").show();
                    }
                    else if (key == "667" && data.data.item_zfje_state == "1") {
                        $("#upload_item_pay3_form").show();
                    }

                    showFrom(data.data.state);
                    initGrid(data.data.logs);
                } else if (ua != "3" && key == "005" && data.data.item_zfje_state == "0") {
                    $("#upload_item_pay_form").show();
                } else if (ua != "2" && key == "667") {
                    $("#upload_item_pay_again_form").show();
                } else if (ua != "3" && key == "005" && data.data.item_zfje_state == "1") {
                    $("#upload_item_pay2_form").show();
                } else if (ua != "3" && key == "667" && data.data.item_zfje_state == "1") {
                    $("#upload_item_pay3_form").show();
                }
                coupon_id = data.data.coupon_id;
                switch(key){
                    case '001':
                    case '002':
                    case '003':
                    case '009':
                    case '004':
                    case '112':
                    case '999':
                        $('#item_info_form').form('load', data.data);
                        $('#itemInfo_hdlx_id').val(getItemhdlx(data.data.item_hdlx));
                        $('#itemInfo_jhlb_id').val(getItemjhlb(data.data.item_jhlb));
                        if (data.data.item_jhlb == '002' || data.data.item_jhlb == '004') {
                            $("#item_info_jhlj_tr_id").show();
                        } else {
                            $("#item_info_jhlj_tr_id").hide();
                        }
                        $('#itemInfo_activity_start_time_id').val(
                            getTimeHHDay(data.data.activity_start_time));
                        $('#itemInfo_coupon_start_time_id').val(
                            getTimeDay(data.data.coupon_start_time));
                        $('#itemInfo_coupon_end_time_id').val(
                            getTimeDay(data.data.coupon_end_time));
                        $("#item_info_form").show();
                        $("#item_pay_info_form").hide();
                        break;
                    case '005':
                    case '006':
                    case '007':
                    case '667':
                        initItemImage(data.data.attachments);
                        $("#pay_item_no_span").html(data.data.item_no);
                        $("#pay_item_createtime_span").html(getTimeDay(data.data.createtime));
                        $("#pay_coupon_start_span").html(getTimeDay(data.data.coupon_start_time));
                        $("#pay_coupon_end_span").html(getTimeDay(data.data.coupon_end_time));
                        $("#pay_coupon_get_span").html(data.data.coupon_get_num+"("+(data.data.coupon_get_num+data.data.coupon_rest_num)+")");
                        $("#pay_item_zfje_span").html(data.data.item_zfje+"(券后价格："+data.data.item_qhjg+")");
                        $("#pay_coupon_use_span").html((data.data.item_zfje/data.data.item_qhjg)+"(服务单价："+data.data.item_fwdj+")");
                        $("#pay_item_ysje_span").html(data.data.item_zfje/data.data.item_qhjg*data.data.item_fwdj);
                        $("#pay_item_ssje_span").html(data.data.item_ssje);
                        $("#pay_coupon_zhl_span").html((((data.data.item_zfje/data.data.item_qhjg)/data.data.coupon_get_num)*100).toFixed(2));
                        $("#item_info_form").hide();
                        $("#item_pay_info_form").show();
                        break;
                }
            }
        });
    }
    function initItemImage(images){
        for (var i = 0; i < images.length; i++) {
            $("#item_image_list_div").append('<a target="_blank" title="查看原图" href="'+ImgUrl+'?'+images[i].attachment_value+'"><img  width="100" height="100" alt="" src="'+ ImgUrl+'?'+images[i].attachment_value + '"></a>');
        }
    }
    function initGrid(gridDatas) {
        $audit_logGrid.datagrid({
            columns : [ [ {
                title : '审批人',
                field : 'auditor',
                formatter : function(val) {
                    if (val == null || val == "") {
                        return "";
                    } else {
                        return val;
                    }
                }
            }, {
                title : '审批时间',
                field : 'audit_time',
                formatter : function(val) {
                    if (val == null || val == "") {
                        return "";
                    } else {
                        return getTimeAll(val);
                    }
                }
            }, {
                title : '审批前状态',
                field : 'before_audit_status',
                formatter : function(val) {
                    if (val == null || val == "") {
                        return "";
                    } else {
                        return getItemState(val);
                    }
                }
            }, {
                title : '审批后状态',
                field : 'after_audit_status',
                formatter : function(val) {
                    if (val == null || val == "") {
                        return "";
                    } else {
                        return getItemState(val);
                    }
                }
            }, {
                title : '备注说明',
                field : 'audit_remarks',
                formatter : function(val) {
                    if (val == null || val == "") {
                        return "";
                    } else {
                        return val;
                    }
                }
            } ] ],
            singleSelect : true,// 允许自适应宽度
            selectChange : function(selected, rowIndex, rowData, $row) {
                console.info($row)
                select_status = selected;
                onSelectRow = rowData;
            }
            // edit:true
        }).datagrid("loadData", {
            rows : gridDatas
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
    function copyText1(obj) {
        var Url2 = obj.parentNode.parentNode.getElementsByTagName("td")[2]
            .getElementsByTagName("textarea")[0];
        Url2.select(); // 选择对象
        document.execCommand("Copy"); // 执行浏览器复制命令
    }
    function copyText2(obj) {
        var Url2 = obj.parentNode.parentNode.getElementsByTagName("td")[2]
            .getElementsByTagName("input")[0];
        Url2.select(); // 选择对象
        document.execCommand("Copy"); // 执行浏览器复制命令
    }
    function addFile(obj) {
        $(obj).next().append('<input  name="item_attachment" type="file" multiple="multiple">');
    }
    function getItemjhlb(str) {
        var key = str;
        var value;
        switch (key) {
            case '001':
                value = '通用';
                break;
            case '002':
                value = '定向';
                break;
            case '003':
                value = '鹊桥';
                break;
            case '004':
                value = '营销计划';
                break;
            default:
                value = '';
        }
        return value;
    }
    function getItemhdlx(str) {
        var key = str;
        var value;
        switch (key) {
            case '001':
                value = '普通活动';
                break;
            case '002':
                value = '淘抢购';
                break;
            case '003':
                value = '聚划算';
                break;
            default:
                value = '';
        }
        return value;
    }
    function getTimeAll(str) {
        if (str == null || str == "") {
            return "";
        } else {
            return str.substring(0, 19);
        }
    }
    function getTimeDay(str) {
        if (str == null || str == "") {
            return "";
        } else {
            return str.substring(0, 10);
        }
    }
    function getTimeHHDay(str) {
        if (str == null || str == "") {
            return "";
        } else {
            return str.substring(0, 16);
        }
    }
    function getItemState(str) {
        var key = str;
        var value;
        switch (key) {
            case '001':
                value = '待审核';
                break;
            case '002':
                value = '待二审';
                break;
            case '003':
                value = '推广中';
                break;
            case '009':
                value = '即将结束';
                break;
            case '004':
                value = '已结束';
                break;
            case '005':
                value = '待付款';
                break;
            case '006':
                value = '付款中';
                break;
            case '007':
                value = '已付款';
                break;
            case '112':
                value = '驳回';
                break;
            case '667':
                value = '拒绝付款';
                break;
            case '999':
                value = '拒绝';
                break;
            default:
                value = '';
        }
        return value;
    }
</script>
<%-- <script src="${ctx }/static/js/_tjcx.js"></script> --%>
<div class="col-xs-12 col-sm-12 col-md-12 row">
	<div class="" id="book_accordion-element">
		<div class="col-xs-12 col-sm-12 col-md-12">
			<a href="#" onclick="gobackpage()" class="btn">返回商品列表</a>
			<form class=" col-xs-12 col-sm-12 col-md-12" id="item_info_form" role="form">
				<input id="iteminfo_id_id" name="item_id" type="hidden">
				<hr />
				<table style="width: 90%; border-collapse: separate; border-spacing: 20px;">
					<tr>
						<td style="width: 10%"><input type="button" onClick="copyText1(this)" value="点击复制" /></td>
						<td style="width: 12%">商品链接：</td>
						<td class="td3"><textarea name="item_url" style="width: 100%; resize: none; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></textarea></td>
					</tr>
					<tr>
						<td></td>
						<td>商品ID：</td>
						<td><input name="item_no" style="width: 30%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></td>
					</tr>
					<tr>
						<td></td>
						<td>商品分类：</td>
						<td><input name="item_type" style="width: 30%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></td>
					</tr>
					<tr>
						<td></td>
						<td>活动类型：</td>
						<td><input id="itemInfo_hdlx_id" name="item_hdlx" style="width: 30%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></td>
					</tr>
					<tr>
						<td></td>
						<td>活动开始时间：</td>
						<td><input id="itemInfo_activity_start_time_id" name="activity_start_time" style="width: 30%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></td>
					</tr>
					<tr>
						<td><input type="button" onClick="copyText1(this)" value="点击复制" /></td>
						<td>备注信息：</td>
						<td><textarea name="remark" style="width: 100%; resize: none; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></textarea></td>
					</tr>
					<tr>
						<td><input type="button" onClick="copyText1(this)" value="点击复制" /></td>
						<td>商品主图：</td>
						<td><textarea name="item_main_image" style="width: 100%; resize: none; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></textarea></td>
					</tr>
					<tr>
						<td><input type="button" onClick="copyText1(this)" value="点击复制" /></td>
						<td>商品名称：</td>
						<td><textarea name="item_name" style="width: 100%; resize: none; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></textarea></td>
					</tr>
					<tr>
						<td><input type="button" onClick="copyText2(this)" value="点击复制" /></td>
						<td>券后价格：</td>
						<td><input name="item_qhjg" style="width: 30%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></td>
					</tr>
					<tr>
						<td><input type="button" onClick="copyText1(this)" value="点击复制" /></td>
						<td>优惠券地址：</td>
						<td><textarea name="coupon_url" style="width: 100%; resize: none; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></textarea></td>
					</tr>
					<tr>
						<td></td>
						<td>优惠券有效期：</td>
						<td><input id="itemInfo_coupon_start_time_id" name="coupon_start_time" style="width: 15%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly">- <input id="itemInfo_coupon_end_time_id" name="coupon_end_time" style="width: 15%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></td>
					</tr>
					<tr>
						<td><input type="button" onClick="copyText2(this)" value="点击复制" /></td>
						<td>佣金比例：</td>
						<td><input name="item_yjbl" style="width: 30%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></td>
					</tr>
					<tr>
						<td></td>
						<td>计划类别：</td>
						<td><input id="itemInfo_jhlb_id" name="item_jhlb" style="width: 30%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></td>
					</tr>
					<tr id="item_info_jhlj_tr_id">
						<td><input type="button" onClick="copyText1(this)" value="点击复制" /></td>
						<td>计划连接：</td>
						<td><textarea name="item_jhlj" style="width: 100%; resize: none; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></textarea>
					</tr>



					<tr>
						<td><input type="button" onClick="copyText1(this)" value="点击复制" /></td>
						<td>视频地址：</td>
						<td><textarea name="item_image_backup" style="width: 100%; resize: none; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></textarea></td>
					</tr>
					<tr>
						<td><input type="button" onClick="copyText1(this)" value="点击复制" /></td>
						<td>卖点填写：</td>
						<td><textarea name="item_md" style="width: 100%; resize: none; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></textarea></td>
					</tr>
					<tr>
						<td><input type="button" onClick="copyText2(this)" value="点击复制" /></td>
						<td>联系QQ：</td>
						<td><input name="qq" style="width: 30%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></td>
					</tr>
					<tr>
						<td><input type="button" onClick="copyText2(this)" value="点击复制" /></td>
						<td>收费单价：</td>
						<td><input name="item_fwdj" style="width: 30%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></td>
					</tr>
				</table>
				<hr />
			</form>
			<form class=" col-xs-12 col-sm-12 col-md-12" id="item_pay_info_form" role="form">
				<table style="border-collapse: separate; border-spacing: 20px;">
					<tr>
						<td>产品编码：</td>
						<td><span id="pay_item_no_span"></span></td>
					</tr>
					<tr>
						<td>提交时间：</td>
						<td><span id="pay_item_createtime_span"></span></td>
					</tr>
					<tr>
						<td>优惠开始时间：</td>
						<td><span id="pay_coupon_start_span"></span></td>
					</tr>
					<tr>
						<td>优惠结束时间：</td>
						<td><span id="pay_coupon_end_span"></span></td>
					</tr>
					<tr>
						<td>领取数量：</td>
						<td><span id="pay_coupon_get_span"></span></td>
					</tr>
					<tr>
						<td>支付金额：</td>
						<td><span id="pay_item_zfje_span"></span></td>
					</tr>
					<tr>
						<td>使用数量：</td>
						<td><span id="pay_coupon_use_span"></span></td>
					</tr>
					<tr>
						<td>应收款金额：</td>
						<td><span id="pay_item_ysje_span"></span></td>
					</tr>
					<tr>
						<td>实际收款金额：</td>
						<td><span id="pay_item_ssje_span"></span></td>
					</tr>
					<tr>
						<td>转化率：</td>
						<td><span id="pay_coupon_zhl_span"></span></td>
					</tr>
				</table>
				<div id="item_image_list_div"></div>
			</form>

		</div>
	</div>
	<a href="#" onclick="gobackpage()" class="btn">返回商品列表</a>
	<hr />
</div>
<div class="col-xs-12 col-sm-12 col-md-12">
	<form action="${ctx }/order/uploadpayInfo" enctype="multipart/form-data" method="post" id="upload_item_pay_form">
		<input name="item_id" id="upload_item_pay_item_id" type="hidden"> <input name="after_audit_status" value="005" type="hidden"> <input name="before_audit_status" value="005" type="hidden">
		<table style="border-collapse: separate; border-spacing: 20px;">
			<tr>
				<td>支付金额：</td>
				<td><input name="item_zfje" class="form-control"></td>
				<td style="width: 10%">附件：</td>
				<td><button type="button" onclick="addFile(this)">+</button>
					<li style="list-style-type: none;width: 200px"></li></td>
				<td>备注：</td>
				<td><textarea id="audit_remarks_id" name="audit_remarks" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>
				<td><button class="btn btn-primary form-control" onclick="upload_item_pay_form()">
					<i class="glyphicon glyphicon-open"></i>提交支付金额信息
				</button></td>
			</tr>
		</table>
	</form>
	<form action="${ctx }/order/uploadpayInfo" enctype="multipart/form-data" method="post" id="upload_item_pay_again_form">
		<input name="item_id" id="upload_item_pay_again_item_id" type="hidden"> <input name="after_audit_status" value="667" type="hidden"> <input name="before_audit_status" value="667" type="hidden">
		<table style="border-collapse: separate; border-spacing: 20px;">
			<tr>
				<td>支付金额：</td>
				<td><input style="width:100px" name="item_zfje" class="form-control"></td>
				<td style="width: 10%">附件：</td>
				<td><button type="button" onclick="addFile(this)">+</button>
					<li style="list-style-type: none;width: 200px"></li></td>
				<td>备注：</td>
				<td><textarea id="audit_remarks_id" name="audit_remarks" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>
				<td><button class="btn btn-primary form-control" onclick="upload_item_pay_again_form()">
					<i class="glyphicon glyphicon-open"></i>再次提交支付金额信息
				</button></td>
			</tr>
		</table>
	</form>
	<form action="${ctx }/order/uploadpayingInfo" enctype="multipart/form-data" method="post" id="upload_item_pay2_form">
		<input name="item_id" id="upload_item_pay2_item_id" type="hidden"><input name="after_audit_status" value="006" type="hidden"> <input name="before_audit_status" value="005" type="hidden">
		<table style="border-collapse: separate; border-spacing: 20px;">
			<tr>
				<td>实收金额：</td>
				<td><input style="width: 100px" name="item_ssje" class="form-control"></td>
				<td style="width: 10%">附件：</td>
				<td><button type="button" onclick="addFile(this)">+</button>
					<li style="list-style-type: none;width: 200px"></li></td>
				<td>备注：</td>

				<td><textarea id="audit_remarks_id" name="audit_remarks" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>
			</tr>
		</table>
		<div class="form-inline">
			<button class="btn btn-primary form-control" onclick="upload_item_paying_form()">
				<i class="glyphicon glyphicon-open"></i>提交实收金额
			</button>
		</div>
	</form>
	<form action="${ctx }/order/uploadpayingInfo" enctype="multipart/form-data" method="post" id="upload_item_pay3_form">
		<input name="item_id" id="upload_item_pay2_item_id1" type="hidden"><input name="after_audit_status" value="006" type="hidden"> <input name="before_audit_status" value="005" type="hidden">
		<table style="border-collapse: separate; border-spacing: 20px;">
			<tr>
				<td>实收金额：</td>
				<td><input style="width: 100px" name="item_ssje" class="form-control"></td>
				<td style="width: 10%">附件：</td>
				<td><button type="button" onclick="addFile(this)">+</button>
					<li style="list-style-type: none;width: 200px"></li></td>
				<td>备注：</td>
				<td><textarea id="audit_remarks_id" name="audit_remarks" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>
			</tr>
		</table>
		<div class="form-inline">
			<button class="btn btn-primary form-control" onclick="upload_item_paying_form1()">
				<i class="glyphicon glyphicon-open"></i>再次提交实收金额
			</button>
		</div>
	</form>
</div>
<c:if test="${ua=='3'}">
	<div class="col-xs-12 col-sm-12 col-md-12">
		<form class=" col-xs-12 col-sm-12 col-md-12" id="item_audit_form_001" role="form">
			<table style="border-collapse: separate; border-spacing: 20px;">
				<tr>
					<td>请审核：</td>
					<td><select name="after_audit_status" id="audit_state_select" style="width: 150px;" class="form-control">
						<option value="002">通过</option>
						<option value="112">驳回</option>
						<option value="999">拒绝</option>
					</select></td>
					<td>备注：</td>
					<td><textarea id="audit_remarks_id" name="audit_remarks" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>
					<td><button class="btn btn-primary form-control" type="button" onclick="submitAudit('item_audit_form_001')">
						<i class="glyphicon glyphicon-saved"></i>提交审核
					</button></td>
					<td><button class="btn btn-primary form-control" type="button" onclick="cancelAudit('item_audit_form_001')">
						<i class="glyphicon glyphicon-saved"></i>取消审核
					</button></td>
				</tr>
			</table>
		</form>
		<form class=" col-xs-12 col-sm-12 col-md-12" id="item_audit_form_002" role="form">
			<table style="border-collapse: separate; border-spacing: 20px;">
				<tr>
					<td>请审核：</td>
					<td><select name="after_audit_status" id="audit_state_select" style="width: 150px;" class="form-control">
						<option value="003">通过</option>
						<option value="999">拒绝</option>
					</select></td>
					<td>备注：</td>
					<td><textarea id="audit_remarks_id" name="audit_remarks" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>
					<td><button class="btn btn-primary form-control" type="button" onclick="submitAudit('item_audit_form_002')">
						<i class="glyphicon glyphicon-saved"></i>提交审核
					</button></td>
					<td><button class="btn btn-primary form-control" type="button" onclick="cancelAudit('item_audit_form_002')">
						<i class="glyphicon glyphicon-saved"></i>取消审核
					</button></td>
				</tr>
			</table>
		</form>
		<form class=" col-xs-12 col-sm-12 col-md-12" id="item_audit_form_003" role="form">
			<input name="after_audit_status" value="004" type="hidden">
			<table style="border-collapse: separate; border-spacing: 20px;">
				<tr>
					<td>优惠券结束日期：</td>
					<td><div id="coupon_end_time_div" class="input-append date form-group">
						<input id="coupon_end_time" name="coupon_end_time" type="text" class="add-on form-control" data-format="yyyy-MM-dd">
					</div></td>
					<td>领取数量：</td>
					<td><input name="coupon_get_num" class="form-control"></td>
					<td>剩余数量：</td>
					<td><input name="coupon_rest_num" class="form-control"></td>
					<td>备注：</td>
					<td><textarea id="audit_remarks_id" name="audit_remarks" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>
				</tr>
			</table>
			<div class="form-inline">
				<button class="btn btn-primary form-control" type="button" onclick="updateCoupon('item_audit_form_003')">
					<i class="glyphicon glyphicon-saved"></i>只更新优惠券
				</button>
				<button class="btn btn-primary form-control" type="button" onclick="submitAudit('item_audit_form_003')">
					<i class="glyphicon glyphicon-saved"></i>更新优惠券并结束
				</button>
			</div>
		</form>
		<form class=" col-xs-12 col-sm-12 col-md-12" id="item_audit_form_004" role="form">
			<input name="after_audit_status" value="005" type="hidden">
			<table style="border-collapse: separate; border-spacing: 20px;">
				<tr>
					<td>优惠券结束日期：</td>
					<td><div id="coupon_end_time_div1" class="input-append date form-group">
						<input id="coupon_end_time" name="coupon_end_time" type="text" class="add-on form-control" data-format="yyyy-MM-dd">
					</div></td>
					<td>领取数量：</td>
					<td><input name="coupon_get_num" class="form-control"></td>
					<td>剩余数量：</td>
					<td><input name="coupon_rest_num" class="form-control"></td>
					<td>备注：</td>
					<td><textarea id="audit_remarks_id" name="audit_remarks" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>
				</tr>
			</table>
			<div class="form-inline">
				<button class="btn btn-primary form-control" type="button" onclick="submitAudit('item_audit_form_004')">
					<i class="glyphicon glyphicon-saved"></i>更新优惠券进入待付款
				</button>
			</div>
		</form>
		<form class=" col-xs-12 col-sm-12 col-md-12" role="form" id="upload_item_paying_form">
			<table style="border-collapse: separate; border-spacing: 20px;">
				<tr>
					<td>审核：</td>
					<td><select name="after_audit_status" id="audit_state_select" style="width: 150px;" class="form-control">
						<option value="007">完成付款</option>
						<option value="667">拒绝付款</option>
					</select></td>
					<td>备注：</td>
					<td><textarea id="audit_remarks_id" name="audit_remarks" class="form-control" style="resize: none; overflow-y: scroll;"></textarea></td>
				</tr>
			</table>
			<div class="form-inline">
				<button class="btn btn-primary form-control" onclick="submitAudit('upload_item_paying_form')">
					<i class="glyphicon glyphicon-open"></i>提交审核
				</button>
				<button class="btn btn-primary form-control" type="button" onclick="cancelAudit('upload_item_paying_form')">
					<i class="glyphicon glyphicon-saved"></i>取消审核
				</button>
			</div>
		</form>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-12">
		<div class="book_theme_flag1">
			<label for="audit_log_list" class="form-control-static" id="case_Info_label"></label>
			<hr />
			<table id="audit_log_list" class="table table-striped table-advance table-hover table-condensed">
			</table>
		</div>
	</div>
	<hr />
</c:if>

