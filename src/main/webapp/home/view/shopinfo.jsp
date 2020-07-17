<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taosearch.util.Result"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ua" value="${sessionScope.userAuthorization.authorization}"></c:set>
<script type="text/javascript">
    var item_id = "${param.id}";
    var ua="${ua}";
	var $audit_logGrid = $("#audit_log_list");
	var p;
    $(function() {
        getItemInfo();
    });

    function getItemInfo() {
        console.log("==========================>进入详情页面商品id为" + item_id);
        var data = {
            id : item_id
        }
        $.ajax({
            url : $ctx + '/shop/detail',
            type : 'post',
            data : data,
            async : false,
            dataType : 'json',
            success : function(data) {
				$("#shop_id").val(data.shop.id);
				$("#shop_name").val(data.shop.name);
				$("#shop_link").val(data.shop.link);
				$("#shop_address").val(data.shop.address);
				initGrid(data.shopUsers);
			}
        });
    }
	function gobackpage() {
		$('#showId').load($ctx + "/home/view/dpxx.jsp", p);
		$('#li_name').html("店铺列表");
	}
	function initGrid(gridDatas) {
		$audit_logGrid.datagrid({
			columns : [ [ {
				title : '身份',
				field : 'type',
				formatter : function(val) {
					if (val == null) {
						return "";
					} else if (val == 0) {
						return "店铺老板";
					} else {
						return "店铺运营" ;
					}
				}
			}, {
				title : '手机号',
				field : 'phone',
				formatter : function(val) {
					if (val == null || val == "") {
						return "";
					} else {
						return val;
					}
				}
			}, {
				title : 'QQ',
				field : 'qq',
				formatter : function(val) {
					if (val == null || val == "") {
						return "";
					} else {
						return val;
					}
				}
			}, {
				title : 'QQ名称',
				field : 'qq_name',
				formatter : function(val) {
					if (val == null || val == "") {
						return "";
					} else {
						return val;
					}
				}
			},  {
				title : '微信',
				field : 'wechat',
				formatter : function(val) {
					if (val == null || val == "") {
						return "";
					} else {
						return val;
					}
				}
			}, {
				title : '微信名称',
				field : 'wechat_name',
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

</script>
<%-- <script src="${ctx }/static/js/_tjcx.js"></script> --%>
<div class="col-xs-12 col-sm-12 col-md-12 row">
	<div class="" id="book_accordion-element">
		<div class="col-xs-12 col-sm-12 col-md-12">
			<a href="#" onclick="gobackpage()" class="btn">返回店铺列表</a>
			<form class=" col-xs-12 col-sm-12 col-md-12" id="item_info_form" role="form">
				<input id="iteminfo_id_id" name="item_id" type="hidden">
				<hr />
				<table style="width: 90%; border-collapse: separate; border-spacing: 20px;">
					<tr>
						<td></td>
						<td>店铺ID：</td>
						<td><input id="shop_id" style="width: 30%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></td>
					</tr>
					<tr>
						<td></td>
						<td>店铺名称：</td>
						<td><input id="shop_name" style="width: 30%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></td>
					</tr>
					<tr>
						<td></td>
						<td>店铺地址：</td>
						<td><input id="shop_address" style="width: 30%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></td>
					</tr>
					<tr>
						<td></td>
						<td>店铺链接：</td>
						<td><input id="shop_link" style="width: 30%; border-style: none; background-color: #fff; cursor: default;" readonly="readonly"></td>
					</tr>
				</table>
			</form>
			<div class="col-xs-12 col-sm-12 col-md-12">
				<div class="book_theme_flag1">
					<label for="audit_log_list" class="form-control-static" id="case_Info_label"></label>
					<hr />
					<table id="audit_log_list" class="table table-striped table-advance table-hover table-condensed">
					</table>
				</div>
				<hr />
			</div>
		</br>
		</div>
	</div>
	<a href="#" onclick="gobackpage()" class="btn">返回店铺列表</a>
	<hr />
</div>


