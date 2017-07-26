<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taosearch.util.Result"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var item_id1 = "${param.item_id1}";
    var item_id2 = "${param.item_id2}";
    var item_id3 = "${param.item_id3}";

    if (item_id1 != '') {
        getItemInfo(item_id1,1);
    } else if (item_id2 != '') {
        alert(1);
    } else if (item_id3 != '') {
        alert(1);
    }


    function getItemInfo(item_id,num) {
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
                $("#coupon_rest_num" + num).val(item.coupon_rest_num);
                $("#item_id" + num).val(item.item_id);
            }
        });
    }

    function commit() {
        debugger;

        if (item_id1 != '') {
            $("#upload_item_pay_form").submit();

        } else if (item_id2 != '') {
            alert(1);
        } else if (item_id3 != '') {
            alert(1);
        }
    }

    function addFile(obj) {
        $(obj).next().append('<input  name="item_attachment" type="file" multiple="multiple">');
    }
    function gobackpage() {
        p = {
            page : 1,
            rows : 10
        }
        $('#showId').load($ctx + "/home/view/index2.jsp", p);
        $('#li_name').html("商品列表");
    }

</script>
<%-- <script src="${ctx }/static/js/_tjcx.js"></script> --%>
<div class="col-xs-12 col-sm-12 col-md-12 row">
	<div class="" id="book_accordion-element">
		<div class="col-xs-12 col-sm-12 col-md-12">
			<a href="#" onclick="gobackpage()" class="btn">返回商品列表</a>

			<form action="${ctx }/order/uploadpayInfo" enctype="multipart/form-data" method="post" id="upload_item_pay_form">

				<table style="border-collapse: separate; border-spacing: 20px;">
					<tr>
						<td><input type="hidden" id="coupon_rest_num1" name="coupon_rest_num"></td>
						<td><input type="hidden" id="item_id1" name="item_id"></td>
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
						<td><input type="text" id="coupon_get_num1"/>
							<button type="button">计算</button>
						</td>
					</tr>
					<tr>
						<td>支付金额：</td>
						<td><input type="text" id="item_zfje1" name="item_zfje"/></td>
					</tr>
					<tr>
						<td>使用数量：</td>
						<td><span id="coupon_use1"></span></td>
					</tr>
					<tr>
						<td>应收款金额：</td>
						<td><span id="item_ysje1"></span></td>
					</tr>
					<tr>
						<td>实际收款金额：</td>
						<td><input type="text" id="item_ssje1"/></td>
					</tr>
					<tr>
						<td>转化率：</td>
						<td><span id="coupon_zhl1"></span></td>
					</tr>
				</table>
				<table style="border-collapse: separate; border-spacing: 20px;">
					<tr>
						<td>优惠卷截图：</td>
						<td><input name="item_zfje" class="form-control"></td>
						<td style="width: 10%">附件：</td>
						<td>
							<button type="button" onclick="addFile(this)">+</button>
							<li style="list-style-type: none;width: 200px"></li>
						</td>
						<td>备注：</td>
						<td><textarea id="audit_remarks_id" name="audit_remarks" class="form-control"
									  style="resize: none; overflow-y: scroll;"></textarea></td>
						<td>
							<button class="btn btn-primary form-control" onclick="commit()">
								<i class="glyphicon glyphicon-open"></i>提交支付金额信息
							</button>
						</td>
					</tr>
				</table>
			</form>

		</div>
	</div>
	<a href="#" onclick="gobackpage()" class="btn">返回商品列表</a>
	<hr/>
</div>

