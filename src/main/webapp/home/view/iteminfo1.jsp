<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.taosearch.util.Result" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    var item_id1 = "${param.item_id1}";
    var item_id2 = "${param.item_id2}";
    var item_id3 = "${param.item_id3}";

    debugger
    if (item_id1 != '') {
        $("#upload_item_pay_form1").show();
        getItemInfo(item_id1, 1);
    }
    if (item_id2 != '') {
        $("#upload_item_pay_form2").show();
        getItemInfo(item_id2, 2);
    }
    if (item_id3 != '') {
        $("#upload_item_pay_form3").show();
        getItemInfo(item_id3, 3);
    }


    function getItemInfo(item_id, num) {
        var data = {
            item_id: item_id
        };
        $.ajax({
            url: $ctx + '/order/getItemInfoById',
            type: 'post',
            data: data,
            async: false,
            dataType: 'json',
            success: function (data) {
                var item = data.data;
                $("#title" + num).text(item.item_title);
                $("#item_no" + num).text(item.item_no);
                $("#item_createtime" + num).text(item.createtime);
                $("#coupon_start" + num).text(item.coupon_start_time);
                $("#coupon_end" + num).text(item.coupon_end_time);
                $("#coupon_get_num" + num).val(item.coupon_get_num);
                $("#item_zfje" + num).val(item.item_zfje);
                $("#coupon_use" + num).text(item.coupon_use_num);
                $("#item_fwdj" + num).text(item.item_fwdj);
                $("#item_qhjg" + num).text(item.item_qhjg);
                $("#coupon_rest_num" + num).text(item.coupon_rest_num + item.coupon_get_num);
                $("#item_id" + num).val(item.item_id);
            }
        });
    }

    function check(typeId) {
        var zfje = $("#item_zfje" + typeId).val();
        var qhjg = $("#item_ssje" + typeId).val();
        var get_num = $("#coupon_get_num" + typeId).val();
        if (zfje == null || zfje == '') {
            $('#alert_dialog_danger').html("支付金额不正确！");
            $('#modal_danger').modal('show');
            return false;
        }
        if (qhjg == null || qhjg == '') {
            $('#alert_dialog_danger').html("实收金额不正确！");
            $('#modal_danger').modal('show');
            return false;
        }
        if (get_num == null || get_num == '') {
            $('#alert_dialog_danger').html("优惠券领取数量不正确！");
            $('#modal_danger').modal('show');
            return false;
        }

        return true;
    }

    function commit() {

        debugger;
        if (item_id1 != '') {
            if (check(1)) {
                doUpload(1);
            } else {
                return
            }
        }
        if (item_id2 != '') {
            if (check(2)) {
                doUpload(2);
            } else {
                return
            }
        }
        if (item_id3 != '') {
            if (check(3)) {
                doUpload(3);
            } else {
                return
            }
        }

        gobackpage();
    }

    function doUpload(typeId) {

        $("#item_merge" + typeId).val(itemMerge());
        $("#item_merge" + typeId).val(itemMerge());
        $("#item_merge" + typeId).val(itemMerge());

        var formData = new FormData($("#upload_item_pay_form" + typeId)[0]);
        $.ajax({
            url: '${ctx }/order/uploadpayInfo',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.code != 1) {
                    $('#alert_dialog_danger').html(data.message);
                    $('#modal_danger').modal('show');
                }
            }
        });
    }

    function addFile(obj) {
        var formData = new FormData();
        var name = $("input").val();
        formData.append("file", $("#upload")[0].files[0]);
        formData.append("name", name);
    }
    function addFile(obj) {
        $(obj).next().append('<input  name="item_attachment" type="file" multiple="multiple">');
    }
    function sumShow() {
        $("#total").text(sum());
    }

    function itemMerge() {
        var itemMerge = '';
        var item_no1 = $("#item_no1").text();
        var item_no2 = $("#item_no2").text();
        var item_no3 = $("#item_no3").text();
        if (item_no1 != null && item_no1 != '') {
            itemMerge += item_no1;
        }
        if (item_no2 != null && item_no2 != '') {
            itemMerge += '; ' + item_no2;
        } else {
            return '';
        }

        if (item_no3 != null && item_no3 != '') {
            itemMerge += '; ' + item_no3;
        }
        itemMerge += ' 合并付款，实收金额为' + sum() + '元';

        return itemMerge;

    }

    function sum() {
        var qhjg1 = $("#item_ssje1").val();
        var qhjg2 = $("#item_ssje2").val();
        var qhjg3 = $("#item_ssje3").val();
        if (qhjg1 == '' || qhjg1 == null) {
            qhjg1 = 0;
        }
        if (qhjg2 == '' || qhjg2 == null) {
            qhjg2 = 0;
        }
        if (qhjg3 == '' || qhjg3 == null) {
            qhjg3 = 0;
        }

        var sum = parseInt(qhjg1) + parseInt(qhjg2) + parseInt(qhjg3);

        return sum;
    }

    function gobackpage() {
        p = {
            page: 1,
            rows: 10
        };
        $('#showId').load($ctx + "/home/view/index2.jsp", p);
        $('#li_name').html("商品列表");
    }

    function calculate(typeId) {
        var zfje = $("#item_zfje" + typeId).val();
        var qhjg = $("#item_qhjg" + typeId).text();
        var get_num = $("#coupon_get_num" + typeId).val();
        var sum = zfje / qhjg * $("#item_fwdj" + typeId).text();

        $("#item_ysje" + typeId).text(sum.toFixed(2));

        var coupon_zhl = zfje / qhjg / get_num * 100;
        $("#coupon_zhl" + typeId).text(coupon_zhl.toFixed(2));

        $("#coupon_use" + typeId).text((zfje / qhjg).toFixed(0));

    }
</script>
<%-- <script src="${ctx }/static/js/_tjcx.js"></script> --%>
<div class="col-xs-12 col-sm-12 col-md-12 row">
	<div class="" id="book_accordion-element">
		<div class="col-xs-12 col-sm-12 col-md-12">
			<a href="#" onclick="gobackpage()" class="btn">返回商品列表</a>

			<form action="${ctx }/order/uploadpayInfo" enctype="multipart/form-data" method="post"
				  id="upload_item_pay_form1" style="display: none">

				<table style="border-collapse: separate; border-spacing: 20px;">
					<tr>
						<td><input type="hidden" id="item_id1" name="item_id"></td>
						<td><input type="hidden" id="item_merge1" name="item_merge"></td>
					</tr>
					<tr>
						<td style="width: 20%">商品标题：</td>
						<td><span id="title1"></span></td>
					</tr>
					<tr>
						<td>产品编码：</td>
						<td><span id="item_no1"></span></td>
					</tr>
					<tr>
						<td>提交时间：</td>
						<td><span id="item_createtime1"></span></td>
					</tr>
					<tr>
						<td>优惠开始时间：</td>
						<td><span id="coupon_start1"></span></td>
					</tr>
					<tr>
						<td>优惠结束时间：</td>
						<td><span id="coupon_end1"></span></td>
					</tr>
					<tr>
						<td>领取数量：</td>
						<td><input type="text" id="coupon_get_num1" style="width: 70px"/>
							(<span id="coupon_rest_num1"></span>)
						</td>
					</tr>
					<tr>
						<td>支付金额：</td>
						<td><input type="text" id="item_zfje1" name="item_zfje" style="width: 70px" />
							(券后价格:<span id="item_qhjg1"></span>)
							<button type="button" onclick="calculate(1)">计算</button>
						</td>
					</tr>
					<tr>
						<td>使用数量：</td>
						<td><span id="coupon_use1"></span>
							(服务单价:<span id="item_fwdj1"></span>)
						</td>
					</tr>
					<tr>
						<td>应收款金额：</td>
						<td><span id="item_ysje1"></span></td>
					</tr>
					<tr>
						<td>实际收款金额：</td>
						<td><input type="text" name="item_ssje" id="item_ssje1" style="width: 70px" value="0"/></td>
					</tr>
					<tr>
						<td>转化率：</td>
						<td><span id="coupon_zhl1"></span></td>
					</tr>
				</table>
				<table style="border-collapse: separate; border-spacing: 20px;">
					<tr>
						<td style="width: 10%">截图：</td>
						<td>
							<button type="button" onclick="addFile(this)">+</button>
							<li style="list-style-type: none;width: 200px"></li>
						</td>
						<td>备注：</td>
						<td><textarea id="audit_remarks_id" name="audit_remarks" class="form-control"
									  style="resize: none; overflow-y: scroll;"></textarea></td>
					</tr>
				</table>
			</form>
			<form action="${ctx }/order/uploadpayInfo" enctype="multipart/form-data" method="post"
				  id="upload_item_pay_form2" style="display: none">

				<table style="border-collapse: separate; border-spacing: 20px;">
					<tr>
						<td><input type="hidden" id="item_id2" name="item_id"></td>
						<td><input type="hidden" id="item_merge2" name="item_merge"></td>

					</tr>
					<tr>
						<td style="width: 20%">商品标题：</td>
						<td><span id="title2"></span></td>
					</tr>
					<tr>
						<td>产品编码：</td>
						<td><span id="item_no2"></span></td>
					</tr>
					<tr>
						<td>提交时间：</td>
						<td><span id="item_createtime2"></span></td>
					</tr>
					<tr>
						<td>优惠开始时间：</td>
						<td><span id="coupon_start2"></span></td>
					</tr>
					<tr>
						<td>优惠结束时间：</td>
						<td><span id="coupon_end2"></span></td>
					</tr>
					<tr>
						<td>领取数量：</td>
						<td><input type="text" id="coupon_get_num2" style="width: 70px"/>
							(<span id="coupon_rest_num2"></span>)
						</td>
					</tr>
					<tr>
						<td>支付金额：</td>
						<td><input type="text" id="item_zfje2" name="item_zfje" style="width: 70px" />
							(券后价格:<span id="item_qhjg2"></span>)
							<button type="button" onclick="calculate(2)">计算</button>
						</td>
					</tr>
					<tr>
						<td>使用数量：</td>
						<td><span id="coupon_use2"></span>
							(服务单价:<span id="item_fwdj2"></span>)
						</td>
					</tr>
					<tr>
						<td>应收款金额：</td>
						<td><span id="item_ysje2"></span></td>
					</tr>
					<tr>
						<td>实际收款金额：</td>
						<td><input type="text" name="item_ssje" id="item_ssje2" style="width: 70px" value="0"/></td>
					</tr>
					<tr>
						<td>转化率：</td>
						<td><span id="coupon_zhl2"></span></td>
					</tr>
				</table>
				<table style="border-collapse: separate; border-spacing: 20px;">
					<tr>
						<td style="width: 10%">截图：</td>
						<td>
							<button type="button" onclick="addFile(this)">+</button>
							<li style="list-style-type: none;width: 200px"></li>
						</td>
						<td>备注：</td>
						<td><textarea id="audit_remarks_id2" name="audit_remarks" class="form-control"
									  style="resize: none; overflow-y: scroll;"></textarea></td>
					</tr>
				</table>
			</form>
			<form action="${ctx }/order/uploadpayInfo" enctype="multipart/form-data" method="post"
				  id="upload_item_pay_form3" style="display: none">

				<table style="border-collapse: separate; border-spacing: 20px;">
					<tr>
						<td><input type="hidden" id="item_id3" name="item_id"></td>
						<td><input type="hidden" id="item_merge3" name="item_merge"></td>
					</tr>
					<tr>
						<td style="width: 20%">商品标题：</td>
						<td><span id="title3"></span></td>
					</tr>
					<tr>
						<td>产品编码：</td>
						<td><span id="item_no3"></span></td>
					</tr>
					<tr>
						<td>提交时间：</td>
						<td><span id="item_createtime3"></span></td>
					</tr>
					<tr>
						<td>优惠开始时间：</td>
						<td><span id="coupon_start3"></span></td>
					</tr>
					<tr>
						<td>优惠结束时间：</td>
						<td><span id="coupon_end3"></span></td>
					</tr>
					<tr>
						<td>领取数量：</td>
						<td><input type="text" id="coupon_get_num3" style="width: 70px"/>
							(<span id="coupon_rest_num3"></span>)
						</td>
					</tr>
					<tr>
						<td>支付金额：</td>
						<td><input type="text" id="item_zfje3" name="item_zfje" style="width: 70px"/>
							(券后价格:<span id="item_qhjg3"></span>)
							<button type="button" onclick="calculate(3)">计算</button>
						</td>
					</tr>
					<tr>
						<td>使用数量：</td>
						<td><span id="coupon_use3"></span>
							(服务单价:<span id="item_fwdj3"></span>)
						</td>
					</tr>
					<tr>
						<td>应收款金额：</td>
						<td><span id="item_ysje3"></span></td>
					</tr>
					<tr>
						<td>实际收款金额：</td>
						<td><input type="text" name="item_ssje" id="item_ssje3" style="width: 70px" value="0"/></td>
					</tr>
					<tr>
						<td>转化率：</td>
						<td><span id="coupon_zhl3"></span></td>
					</tr>
				</table>
				<table style="border-collapse: separate; border-spacing: 20px;">
					<tr>
						<td style="width: 10%">截图：</td>
						<td>
							<button type="button" onclick="addFile(this)">+</button>
							<li style="list-style-type: none;width: 200px"></li>
						</td>
						<td>备注：</td>
						<td><textarea id="audit_remarks_id3" name="audit_remarks" class="form-control"
									  style="resize: none; overflow-y: scroll;"></textarea></td>
					</tr>
				</table>
			</form>

			<table style="border-collapse: separate; border-spacing: 20px;">

				<tr>
					<td>
						<button type="button" onclick="sumShow()">
							实收总额计算
						</button>

					</td>
					<td><span id="total"></span>元</td>
					<td>

					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td>
					</td>
					<td>
						<button type="button" onclick="commit()" class="btn btn-primary">
							<i class="glyphicon glyphicon-open"></i>提交付款信息
						</button>

					</td>

				</tr>
			</table>
		</div>
	</div>
	<a href="#" onclick="gobackpage()" class="btn">返回商品列表</a>
	<hr/>
</div>

