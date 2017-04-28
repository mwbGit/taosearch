var select_status;
var onSelectRow;
var teamListUrl = $ctx + '/user/getTeamListForPage';
var teamOFFORONUrl = $ctx + '/user/updateTeamState';
var $user_infoGrid = $("#user_Info");
var $user_paginator = $("#user_paginator");
var gridDatas;
var rowForPage = 5;
var queryParamsData;
$(function() {
	// 获取数据信息
	initGrid();
	$('#team_leader_select').selectpicker({
		liveSearch : true,
		maxOptions : 1
	});
	$('#add_team_leader_select').selectpicker({
		liveSearch : true,
		maxOptions : 1
	});
	getTeamLearderselectdata();
})
// 获取数据信息
function initGrid() {
	var gridDatas = getGridDatas(teamListUrl + '?page=1&rows=' + rowForPage);
	$user_infoGrid
			.datagrid(
					{
						columns : [ [
								{
									title : '<input type="checkbox" name="choose">',
									field : 'team_id',
									formatter : function(val, row, indexId) {
										var rownum = (gridDatas.page - 1)
												* gridDatas.rows + indexId + 1;
										return '<input type="checkbox" name="choose" value="'
												+ val + '">' + rownum;
									}
								},
								{
									title : '小组名称',
									field : 'team_name',
									formatter : function(val, row, indexId) {
										if (val == null || val == "") {
											return "";
										} else {
											return "<a href='#' onclick='showTeamInfoModel(\""
													+ indexId
													+ "\")'>"
													+ val
													+ "</a>";
										}
									}
								},
								{
									title : '组长',
									field : 'team_leader_name',
									formatter : function(val, row, indexId) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '备注',
									field : 'team_remark',
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '创建时间',
									field : 'createtime',
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return getTimeAll(val);
										}
									}
								},
								{
									title : '创建人',
									field : 'createuser',
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
									field : 'user_id',
									formatter : function(val, row, indexId) {
										return "<button class='btn btn-danger btn-xs' onclick='updateTeamState(\""
												+ indexId
												+ "\")'><i class='glyphicon glyphicon-wrench'></i>&nbsp;&nbsp;删除</button>";
									}
								} ] ],
						singleSelect : true,// 允许自适应宽度
						selectChange : function(selected, rowIndex, rowData,
								$row) {
							console.info($row)
							select_status = selected;
							onSelectRow = rowData;
						}
					// edit:true
					}).datagrid("loadData", {
				rows : gridDatas.list
			});
	$user_paginator.bootstrapPaginator({
		currentPage : 1,
		totalPages : gridDatas.totalpage,
		onPageClicked : function(e, originalEvent, type, pages) {
			// loadGrid(teamListUrl+"?rows="+rowForPage+"&page="+pages);
			$user_infoGrid.datagrid('loadData', {
				rows : getGridDatas(teamListUrl + "?rows=" + rowForPage
						+ "&page=" + pages).list
			});
		}
	});
}
function skipUserList() {
	$('#showId').load($ctx + "/home/view/yhgl.jsp");
	$('#li_name').html("用户管理");
}
function getTeamLearderselectdata() {
	$.ajax({
		url : $ctx + '/user/getTeamLearderselectdata',
		type : 'get',
		datatype : 'json',
		success : function(data) {
			$("#team_leader_select").html("");
			$("#add_team_leader_select").html("");
			var str = '';
			for (var i = 0; i < data.length; i++) {
				str += '<option value="' + data[i].user_id + '">'
						+ data[i].username + '</option>';
			}
			$('#team_leader_select').append(str);
			$('#add_team_leader_select').append(str);
			$('#team_leader_select').selectpicker('refresh');
			$('#add_team_leader_select').selectpicker('refresh');
			$('#team_leader_select').selectpicker('show');
			$('#add_team_leader_select').selectpicker('show');

			/*
			 * $('#tjcx_alone_select').selectpicker({ liveSearch : true,
			 * maxOptions : 1 });
			 */
		}
	});
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
function showTeamInfoModel(indexId, flag) {
	$('#showTeam_form').form('clear');
	$('#showTeam_form').form('load', gridDatas.list[indexId]);// 为知识产权查看详情form表单赋值
	$('#team_leader_select').selectpicker('val', gridDatas.list[indexId].team_leader);//默认选中
	$("#showTeamModal").modal('show');
}
function showAddTeamModel() {
	$("#team_name_err_span").html("");
	$("#team_name_div").attr("class", "");
	$("#team_name_span").attr("class", "");
	$("#addTeamButton").attr("disabled", true);
	$('#addTeam_form').form('clear');
	$("#addTeamModal").modal('show');
}
function updateTeam() {
	var data = serializeObject($("#showTeam_form"));
	$.ajax({
		url : $ctx + '/user/updateTeamInfo',
		data : data,
		type : 'post',
		async : false,
		success : function(r) {
			if (r.code == 1) {
				$('#alert_dialog_success').html(r.message);
				$('#modal_success').modal('show');
			} else {
				$('#alert_dialog_danger').html(r.message);
				$('#modal_danger').modal('show');
			}
			$('#showTeam_form').form('clear');
			$("#showTeamModal").modal('hide');
			loadGrid(teamListUrl + "?page=1&rows=" + rowForPage);

		}
	})
}
/* 新增用户提交 */
function saveTeam() {
	var data = serializeObject($("#addTeam_form"));
	$.ajax({
		url : $ctx + '/user/saveTeamInfo',
		data : data,
		type : 'post',
		async : false,
		success : function(r) {
			if (r.code == 1) {
				$('#alert_dialog_success').html(r.message);
				$('#modal_success').modal('show');
			} else {
				$('#alert_dialog_danger').html(r.message);
				$('#modal_danger').modal('show');
			}
			$('#addTeam_form').form('clear');
			$("#addTeamModal").modal('hide');
			loadGrid(teamListUrl + "?page=1&rows=" + rowForPage);

		}
	})
}
/* 修改用户资料状态 1启用 0停用 */
function updateTeamState(indexId) {
	var rowData = gridDatas.list[indexId];
	console.log(rowData);
	var data;
	if (rowData.team_state == '1') {
		data = {
			team_id : rowData.team_id,
			team_state : '0'
		};
	} else if (rowData.team_state == '0') {
		data = {
			team_id : rowData.team_id,
			team_state : '1'
		};
	}
	console.log(data)
	$.ajax({
		url : userOFFORONUrl,
		data : data,
		type : 'post',
		async : false,
		success : function(r) {
			if (r.code == 1) {
				$('#alert_dialog_success').html(r.message);
				$('#modal_success').modal('show');
			} else {
				$('#alert_dialog_danger').html(r.message);
				$('#modal_danger').modal('show');
			}
			loadGrid(teamListUrl + "?page=1&rows=" + rowForPage);

		}
	})

}
function checkTeamName() {
	$("#team_name_err_span").html("");
	console.log($("#addTeamForteam_name").val());
	$.ajax({
		url : $ctx + '/user/checkTeamName',
		data : {
			team_name : $("#addTeamForteam_name").val()
		},
		type : 'post',
		async : false,
		success : function(r) {
			if (r.code == 1) {
				$("#team_name_div").attr("class",
						"form-group has-success has-feedback");
				$("#team_name_span").attr("class",
						"glyphicon glyphicon-ok form-control-feedback");
				$("#addTeamButton").attr("disabled", false);
			} else {
				$("#team_name_div").attr("class",
						"form-group has-error has-feedback");
				$("#team_name_span").attr("class",
						"glyphicon glyphicon-remove form-control-feedback");
				$("#team_name_err_span").html(r.message);
				$("#addTeamButton").attr("disabled", true);
			}
		}
	})
}
function loadGrid(url) {
	gridDatas = getGridDatas(url);
	$user_infoGrid.datagrid("loadData", {
		rows : gridDatas.list
	});
	$user_paginator.bootstrapPaginator({
		currentPage : 1,
		totalPages : gridDatas.totalpage,
		onPageClicked : function(e, originalEvent, type, pages) {
			$user_infoGrid.datagrid('loadData', {
				rows : getGridDatas(teamListUrl + "?rows=" + rowForPage
						+ "&page=" + pages).list
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
/**
 * 多条件查询接口
 */
function queryCase() {
	var data = serializeObject($("#query_form"));
	queryParamsData = data;
	console.info(111, data);
	$.ajax({
		url : $ctx + '/caseInfoController/queryRCaseInfoForPage?page=1&rows='
				+ rowForPage,
		data : data,
		type : 'post',
		async : false,
		success : function(datas) {
			console.info(datas)
			$user_infoGrid.datagrid('loadData', {
				rows : datas.list
			});
			$user_paginator.bootstrapPaginator({
				currentPage : 1,
				totalPages : datas.totalpage,
				onPageClicked : function(e, originalEvent, type, pages) {
					$user_infoGrid.datagrid('loadData', {
						rows : getDatas(teamListUrl + "?rows=" + rowForPage
								+ "&page=" + pages, queryParamsData).list
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