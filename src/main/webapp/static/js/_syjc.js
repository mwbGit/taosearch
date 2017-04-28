var select_status;
var onSelectRow;
var caseInfoUrl = $ctx + '/caseInfoController/querySCaseInfoForPage';
var DicCommObjItemUrl = $ctx
		+ '/caseInfoController/queryDicCommObjItemByTypeId';
var ImgUrl = $ctx + '/img/download';
var $case_infoGrid = $("#case_Info");
var $case_paginator = $("#case_paginator");
var gridDatas;
var rowForPage = 5;
var queryParamsData;
$(function() {
	// 加载数据信息
	initGrid();
})
function initGrid() {
	var gridDatas = getGridDatas(caseInfoUrl + '?page=1&rows=' + rowForPage);
	$case_infoGrid
			.datagrid(
					{
						columns : [ [
								{
									title : '',
									field : 'case_id',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '',
									field : 'num',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '',
									field : 'contact_name',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '',
									field : 'contact_tel',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '',
									field : 'pos_y',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '',
									field : 'pos_x',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '',
									field : 'amount',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '',
									field : 'remark',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '状态',
									field : 'dic_item_id',
									formatter : function(val, row) {
										if (val == 1) {
											return "<i class='light light-success'></i><input type='hidden' name='case_status_id' value="
													+ val + " />";
										} else if (val == 0) {
											return "<i class='light light-danger'></i><input type='hidden' name='case_status_id' value="
													+ val + " />";
										} else {
											return "";
										}
									}
								},
								{
									title : '案卷号',
									field : 'case_code',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '检查对象',
									field : 'object_name',
									formatter : function(val, row, indexId) {
										if (val == null || val == "") {
											return "";
										} else {
											return "<a href='#' onclick='showInfoModel(\""
													+ indexId
													+ "\")'>"
													+ val
													+ "</a>";
										}
									}
								},
								{
									title : '经营位置',
									field : 'object_pos',
									hidden : true,
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '检查人员',
									field : 'rpt_user_name',
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '检查日期',
									field : 'rpt_time',
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '操作',
									field : 'case_status_id',
									formatter : function(val, row, indexId) {
										if (checkRevoke(row.rpt_time)) {
											return "<button class='btn btn-danger btn-xs' onclick='revokeData(\""
													+ indexId
													+ "\")'>撤销</button>";
										} else {
											return "";
										}
									}
								} ] ],
						singleSelect : true,
						selectChange : function(selected, rowIndex, rowData,
								$row) {
							console.info($row)
							select_status = selected;
							onSelectRow = rowData;
						}
					// edit:true
					}).datagrid("loadData", {
				rows : gridDatas.datas
			});
	$case_paginator.bootstrapPaginator({
		currentPage : 1,
		totalPages : gridDatas.totalpage,
		onPageClicked : function(e, originalEvent, type, pages) {
			// loadGrid(caseInfoUrl+"?rows="+rowForPage+"&page="+pages);
			$case_infoGrid.datagrid('loadData', {
				rows : getGridDatas(caseInfoUrl + "?rows=" + rowForPage
						+ "&page=" + pages).datas
			});
		}
	});
}
// 操作是否可以撤回 72小时 3天内
function checkRevoke(date) {
	console.log("参数时间是============>" + date);
	var arr = date.split("-");
	var today = new Date();
	console.log("今天的时间是==========>" + today);
	var myday = new Date();
	myday.setFullYear(arr[0], arr[1] - 1, arr[2]);
	console.log("转换后的时间是========>" + myday);
	myday.setDate(myday.getDate() + 3);
	console.log("最后时限时间是========>" + myday);
	if (today > myday) {
		console.log("不可以撤销上报记录")
		return false;
	} else {
		console.log("可以撤销上报记录")
		return true;
	}
}
// 撤销上报数据
function revokeData(indexId) {
	var case_id = gridDatas.datas[indexId].case_id;
	if (confirm("您确定要撤销这条数据么？")) {
		$.ajax({
			url : $ctx + '/caseInfoController/revokeSData',
			data : {
				'case_id' : case_id
			},
			type : 'post',
			async : false,
			success : function(r) {
				if (r.flag) {
					$('#alert_dialog_success').html(r.msg);
					$('#modal_success').modal('show');
				} else {
					$('#alert_dialog_danger').html(r.msg);
					$('#modal_danger').modal('show');
				}
				loadGrid(caseInfoUrl + "?page=1&rows=" + rowForPage);
			}
		})
	}
}
function showModel(indexId, flag) {
	console.info(gridDatas.datas)
	var rpt_attach = gridDatas.datas[indexId].rpt_attach;
	var str = gridDatas.datas[indexId].attach_uid;
	var strs = new Array(); // 定义一数组
	strs = str.split(","); // 字符分割
	rpt_attach = rpt_attach.split(",");
	$('#approvalCase_form').form('clear');
	$('#showCase_form').form('clear');
	$("#attachment").html("");
	$("#showCaseForattachment").html("");
	if (flag == 0) {
		$("#caseregister_result_id").attr('readonly', false);
		$("#caseregister_result_id").attr('disabled', false);
		$("#caseregister_desc").attr('readonly', false);
		$("#approvalButton").show();
	} else if (flag == -1) {
		$("#caseregister_result_id").attr('readonly', true);
		$("#caseregister_result_id").attr('disabled', true);
		$("#caseregister_desc").attr('readonly', true);
		$("#approvalButton").hide();
	} else {
		$("#caseregister_result_id").attr('readonly', true);
		$("#caseregister_result_id").attr('disabled', true);
		$("#caseregister_desc").attr('readonly', false);
		$("#approvalButton").show();
	}
	$('#approvalCase_form').form('load', gridDatas.datas[indexId]);
	$("#attachmentnumber").html(strs.length);
	for (i = 0; i < strs.length; i++) {
		// $("#attachment").append("<li><a href='"+strs[i]+"'
		// target='_blank'>"+rpt_attach[i]+"</a></li>");
		if (i == 0) {
			$("#attachment")
					.append(
							"<div class='item active'><a  href='"
									+ strs[i]
									+ "' target='_blank'><img style='width:130px;height:80px' src='"
									+ strs[i] + "'></a></div>");
		} else {
			$("#attachment")
					.append(
							"<div class='item'><a  href='"
									+ strs[i]
									+ "' target='_blank'><img style='width:130px;height:80px' src='"
									+ strs[i] + "'></a></div>");
		}
	}
	$("#approvalCaseModal").modal('show');
}
function showInfoModel(indexId, flag) {
	$('#showSYJCCase_form').form('clear');
	$("#showSYJCCaseForattachment").html("");
	$('#showSYJCCase_form').form('load', gridDatas.datas[indexId]);
	var rpt_attach = gridDatas.datas[indexId].pictures;
	$("#showSYJCCaseForattachmentnumber").html(rpt_attach.length);
	for (i = 0; i < rpt_attach.length; i++) {
		// $("#attachment").append("<li><a href='"+strs[i]+"'
		// target='_blank'>"+rpt_attach[i]+"</a></li>");
		if (i == 0) {
			$("#showSYJCCaseForattachment")
					.append(
							"<div class='item active'><a  href='"
									+ ImgUrl
									+ "?"
									+ rpt_attach[i].value
									+ "' target='_blank'><img style='width:130px;height:80px' src='"
									+ ImgUrl + "?" + rpt_attach[i].value
									+ "'></a></div>");
		} else {
			$("#showSYJCCaseForattachment")
					.append(
							"<div class='item'><a  href='"
									+ ImgUrl
									+ "?"
									+ rpt_attach[i].value
									+ "' target='_blank'><img style='width:130px;height:80px' src='"
									+ ImgUrl + "?" + rpt_attach[i].value
									+ "'></a></div>");
		}
	}
	$("#showSYJCCaseModal").modal('show');
}
function approvalCase() {
	var data = serializeObject($("#approvalCase_form"));
	$.ajax({
		url : $ctx + '/caseInfoController/approval',
		data : data,
		type : 'post',
		async : false,
		success : function(r) {
			if (r.flag) {
				$('#alert_dialog_success').html(r.msg);
				$('#modal_success').modal('show');
			} else {
				$('#alert_dialog_danger').html(r.msg);
				$('#modal_danger').modal('show');
			}
			$('#approvalCase_form').form('clear');
			$("#approvalCaseModal").modal('hide');
			loadGrid(caseInfoUrl + "?page=1&rows=" + rowForPage);

		}
	})
}
function updateCase() {
	var data = serializeObject($("#showCase_form"));
	$.ajax({
		url : $ctx + '/caseInfoController/updateCase',
		data : data,
		type : 'post',
		async : false,
		success : function(r) {
			if (r.flag) {
				$('#alert_dialog_success').html(r.msg);
				$('#modal_success').modal('show');
			} else {
				$('#alert_dialog_danger').html(r.msg);
				$('#modal_danger').modal('show');
			}
			$('#showCase_form').form('clear');
			$("#showCaseModal").modal('hide');
			loadGrid(caseInfoUrl + "?page=1&rows=" + rowForPage);

		}
	})
}
function loadGrid(url) {
	gridDatas = getGridDatas(url);
	$case_infoGrid.datagrid("loadData", {
		rows : gridDatas.datas
	});
	$case_paginator.bootstrapPaginator({
		currentPage : 1,
		totalPages : gridDatas.totalpage,
		onPageClicked : function(e, originalEvent, type, pages) {
			$case_infoGrid.datagrid('loadData', {
				rows : getGridDatas(caseInfoUrl + "?rows=" + rowForPage
						+ "&page=" + pages).datas
			});
		}
	})
}
function getGridDatas(url) {
	$.ajax({
		url : url,
		type : 'post',
		async : false,
		success : function(data) {
			gridDatas = data;
		}
	})
	return gridDatas;
}
function getDatas(url, data) {
	var datas;
	if (data == null) {
		$.ajax({
			url : url,
			type : 'post',
			async : false,
			success : function(data) {
				datas = data;
			}
		})
	} else {
		$.ajax({
			url : url,
			type : 'post',
			data : data,
			async : false,
			success : function(data) {
				datas = data;
			}
		})
	}

	return datas;
}
function initApprovalSelect() {
	var datas = getDatas(DicCommObjItemUrl + '?dic_type_id=2');
	for (var i = 0; i < datas.length; i++) {
		$("#caseregister_result_id").append(
				'<option value="' + datas[i].dic_item_id
						+ '" class="form-control">' + datas[i].dic_item_name);
		$("#showCaseForcaseregister_result_id").append(
				'<option value="' + datas[i].dic_item_id
						+ '" class="form-control">' + datas[i].dic_item_name);
	}
}
function initType(dic_type_id, parent_dic_item_id) {
	if (dic_type_id == 21 || dic_type_id == '21') {
		$("#showCaseForcase_btype_id").html('');
		var datas = getDatas($ctx
				+ '/caseInfoController/queryDicTreeItemById?dic_type_id='
				+ dic_type_id);
		for (var i = 0; i < datas.length; i++) {
			$("#showCaseForcase_btype_id").append(
					'<option value="' + datas[i].dic_item_id
							+ '" class="form-control">'
							+ datas[i].dic_item_name);
		}
	}
	if (dic_type_id == 22 || dic_type_id == '22') {
		var url;
		$("#showCaseForcase_stype_id").html('');
		var $case_btype_id = $("#showCaseForcase_btype_id").val();
		if (parent_dic_item_id == null || parent_dic_item_id == '') {
			url = $ctx
					+ '/caseInfoController/queryDicTreeItemById?dic_type_id='
					+ dic_type_id + '&parent_dic_item_id=' + $case_btype_id;
		} else {
			url = $ctx
					+ '/caseInfoController/queryDicTreeItemById?dic_type_id='
					+ dic_type_id + '&parent_dic_item_id=' + parent_dic_item_id;
		}
		var datas = getDatas(url);
		for (var i = 0; i < datas.length; i++) {
			$("#showCaseForcase_stype_id").append(
					'<option value="' + datas[i].dic_item_id
							+ '" class="form-control">'
							+ datas[i].dic_item_name);
		}
	}
}
function selectchange(id, desc) {
	var x = $("#" + id)[0].options;
	var text = $(x[x.selectedIndex]).html();
	if (text == "真") {
		$("#" + desc).val("");
		$("#" + desc).html("");
		$("#" + desc).attr('readonly', true);
	} else if (text == "假") {
		$("#" + desc).attr('readonly', false);
	}
}
function queryCase() {
	var data = serializeObject($("#query_form"));
	queryParamsData = data;
	console.info(111, data);
	$.ajax({
		url : $ctx + '/caseInfoController/queryCaseInfoForPage?page=1&rows='
				+ rowForPage,
		data : data,
		type : 'post',
		async : false,
		success : function(datas) {
			console.info(datas)
			$case_infoGrid.datagrid('loadData', {
				rows : datas.datas
			});
			$case_paginator.bootstrapPaginator({
				currentPage : 1,
				totalPages : datas.totalpage,
				onPageClicked : function(e, originalEvent, type, pages) {
					$case_infoGrid.datagrid('loadData', {
						rows : getDatas(caseInfoUrl + "?rows=" + rowForPage
								+ "&page=" + pages, queryParamsData).datas
					});
				}
			})
		}
	})
}
function qyery_selectchange() {
	var x = $("#query_case_status_id")[0].options;
	var text = $(x[x.selectedIndex]).html();
	if (text.indexOf("待鉴定") >= 0) {
		$("#query_caseregister_result_id").val('');
		$("#query_caseregister_result_id").attr('readonly', true);
		$("#query_caseregister_result_id").attr('disabled', true);
	} else if (text.indexOf("已鉴定") >= 0) {
		$("#query_caseregister_result_id").attr('readonly', false);
		$("#query_caseregister_result_id").attr('disabled', false);
	}
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