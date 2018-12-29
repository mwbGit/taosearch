var select_status;
var onSelectRow;
var querytjcxUrl = $ctx + '/caseInfoController/querytjcxdata';
var $case_infoGrid = $("#case_Info");
var $case_paginator = $("#case_paginator");
var gridDatas;
var rowForPage = 5;
var queryParamsData;
var imgData;
$(function() {
	initItemType();
	$("#item_submit_form").html5Validate(function() {
		submitItemInfo();
	}, {
		validate : function() {
			if ($("#item_type_select").val() == "") {
				$("#item_type_select").testRemind("请选择商品分类");
				return false;
			}
			var data = serializeObject($("#item_submit_form"));
			if (data.item_jhlb == '002' && data.item_jhlj == "") {
				$("#item_jhlj_id").testRemind("请输入定向连接");
				return false;
			}
			return true;
		}
	});
});
function getItemInfo() {
	console.log("=========>开始逻辑")
	var item_url = $("#item_url_id").val();
	console.log(item_url);
	var data = {
		"item_url" : item_url
	}
	$.ajax({
		url : $ctx + '/order/getItemInfo',
		data : data,
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if (data.code == 1) {
				$("#item_no_id").val(data.data.item_no);
				$("#item_name_id").text(data.data.item_name);
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
								+ datas[i].type_id + '">' + datas[i].type_name
								+ '</option>');
			}
		}
	});
}
function submitItemInfo() {
    var data = new FormData($("#item_submit_form")[0]);

    $.ajax({
		url : $ctx + '/order/submitItemInfo',
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
				$("#item_submit_form")[0].reset();
			} else {
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