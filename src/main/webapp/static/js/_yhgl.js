var select_status;
var onSelectRow;
var userInfoUrl = $ctx + '/user/getUserListForPage';
var userOFFORONUrl = $ctx + '/user/updateUserState';
var resettingPwdUrl = $ctx + '/user/updateUserPassword';
var $user_infoGrid = $("#user_Info");
var $user_paginator = $("#user_paginator");
var gridDatas;
var rowForPage = 5;
var queryParamsData;
$(function() {
	// 获取数据信息
	initGrid();

	initUserTeam();
})
// 获取数据信息
function initGrid() {
	var gridDatas = getGridDatas(userInfoUrl + '?page=1&rows=' + rowForPage);
	$user_infoGrid
			.datagrid(
					{
						columns : [ [
								{
									title : '<input type="checkbox" name="choose">',
									field : 'user_id',
									formatter : function(val, row, indexId) {
										var rownum = (gridDatas.page - 1)
												* gridDatas.rows + indexId + 1;
										return '<input type="checkbox" name="choose" value="'
												+ val + '">' + rownum;
									}
								},
								{
									title : '用户名',
									field : 'username',
									formatter : function(val, row, indexId) {
										if (val == null || val == "") {
											return "";
										} else {
											return "<a href='#' onclick='showUserInfoModel(\""
													+ indexId
													+ "\")'>"
													+ val
													+ "</a>";
										}
									}
								},
								{
									title : '所在组',
									field : 'team',
									formatter : function(val, row, indexId) {
										if (val == null || val == "") {
											return "未分配";
										} else {
											return val;
										}
									}
								},
								{
									title : '手机号',
									field : 'tel',
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
									title : '管理员',
									field : 'admin',
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											if (val == '1') {
												return '是';
											} else if (val == '0') {
												return '否';
											}
										}
									}
								},
								{
									title : '操作',
									field : 'user_id',
									formatter : function(val, row, indexId) {
										return "<button class='btn btn-danger btn-xs' onclick='updateUserState(\""
												+ indexId
												+ "\")'><i class='glyphicon glyphicon-wrench'></i>&nbsp;&nbsp;删除</button>&nbsp;&nbsp;<button class='btn btn-primary btn-xs' onclick='resettingPWD(\""
												+ indexId
												+ "\")'><i class='glyphicon glyphicon-cog'></i>&nbsp;&nbsp;密码重置</button>&nbsp;&nbsp;<button class='btn btn-primary btn-xs' onclick='showUpdateUserPWD(\""
												+ indexId
												+ "\")'><i class='glyphicon glyphicon-cog'></i>&nbsp;&nbsp;修改密码</button>";
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
			// loadGrid(userInfoUrl+"?rows="+rowForPage+"&page="+pages);
			$user_infoGrid.datagrid('loadData', {
				rows : getGridDatas(userInfoUrl + "?rows=" + rowForPage
						+ "&page=" + pages).list
			});
		}
	});
}
function initUserTeam() {
	$.ajax({
		url : $ctx + '/user/getUserTeams',
		type : 'get',
		async : false,
		dataType : 'json',
		success : function(datas) {
			$("#showUserForteam_id").html("");
			$("#addUserForteam_id").html("");
			$("#showUserForteam_id").append(
					'<option class="form-control" value="">未分组</option>');
			$("#addUserForteam_id").append(
					'<option class="form-control" value="">未分组</option>');
			for (var i = 0; i < datas.length; i++) {
				$("#showUserForteam_id").append(
						'<option class="form-control" value="'
								+ datas[i].team_id + '">' + datas[i].team_name
								+ '</option>');
				$("#addUserForteam_id").append(
						'<option class="form-control" value="'
								+ datas[i].team_id + '">' + datas[i].team_name
								+ '</option>');
			}
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
function showUserInfoModel(indexId, flag) {
	$('#showUser_form').form('clear');
	$('#showUser_form').form('load', gridDatas.list[indexId]);// 为知识产权查看详情form表单赋值
	$("#showUserModal").modal('show');
}
function showAddUserModel() {
	$('#addUser_form').form('clear');
	$("#userName_err_span").html("");
	$("#userName_div").attr("class", "");
	$("#userName_span").attr("class", "");
	$("#addUserForadmin option:first").prop("selected", 'selected');
	$("#addUserButton").attr("disabled", true);
	$("#addUserModal").modal('show');
}

function showUpdateUserPWD(indexId) {
	$('#updateUserPWDform').form('clear');
	$("#newpwd_err_span").html("");
	$("#newpwd_div").attr("class", "");
	$("#newpwd_span").attr("class", "");
	$("#updatepwdButton").attr("disabled", true);
	$("#updateUserPWdForUserid").val(gridDatas.list[indexId].user_id);
	$("#updateUserPWDModal").modal('show');
}
/* 用户详情更改保存功能 */
function updateUser() {
	var data = serializeObject($("#showUser_form"));
	$.ajax({
		url : $ctx + '/user/updateUserInfo',
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
			$('#showUser_form').form('clear');
			$("#showUserModal").modal('hide');
			loadGrid(userInfoUrl + "?page=1&rows=" + rowForPage);

		}
	})
}
/* 新增用户提交 */
function saveUser() {
	var data = serializeObject($("#addUser_form"));
	$.ajax({
		url : $ctx + '/user/saveUserInfo',
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
			$('#addUser_form').form('clear');
			$("#addUserModal").modal('hide');
			loadGrid(userInfoUrl + "?page=1&rows=" + rowForPage);

		}
	})
}
/* 修改用户资料状态 1启用 0停用 */
function updateUserState(indexId) {
	var rowData = gridDatas.list[indexId];
	console.log(rowData);
	var data;
	if (rowData.state == '1') {
		data = {
			user_id : rowData.user_id,
			state : '0'
		};
	} else if (rowData.state == '0') {
		data = {
			user_id : rowData.user_id,
			state : '1'
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
			loadGrid(userInfoUrl + "?page=1&rows=" + rowForPage);

		}
	})

}
function skipTeamList() {
	$('#showId').load($ctx + "/home/view/xzgl.jsp");
	$('#li_name').html("小组管理");
}
/* 用户密码重置 */
function resettingPWD(indexId) {
	var rowData = gridDatas.list[indexId];
	$.ajax({
		url : resettingPwdUrl,
		data : {
			user_id : rowData.user_id
		},
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
			// loadGrid(userInfoUrl + "?page=1&rows=" + rowForPage);

		}
	})
}
function updteUserPwd() {
	var data = serializeObject($("#updateUserPWDform"));
	console.log(data)
	$.ajax({
		url : $ctx + '/user/updteUserPwd',
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
			$('#updateUserPWDform').form('clear');
			$("#updateUserPWDModal").modal('hide');
			// loadGrid(userInfoUrl + "?page=1&rows=" + rowForPage);

		}
	})
}
function checkUserName() {
	$("#userName_err_span").html("");
	console.log($("#addUserForusername").val());
	$.ajax({
		url : $ctx + '/user/checkUserName',
		data : {
			username : $("#addUserForusername").val()
		},
		type : 'post',
		async : false,
		success : function(r) {
			if (r.code == 1) {
				$("#userName_div").attr("class",
						"form-group has-success has-feedback");
				$("#userName_span").attr("class",
						"glyphicon glyphicon-ok form-control-feedback");
				$("#addUserButton").attr("disabled", false);
			} else {
				$("#userName_div").attr("class",
						"form-group has-error has-feedback");
				$("#userName_span").attr("class",
						"glyphicon glyphicon-remove form-control-feedback");
				$("#userName_err_span").html(r.message);
				$("#addUserButton").attr("disabled", true);
			}
		}
	})
}
function checkpwd() {
	$("#newpwd_err_span").html("");
	var newpwd1 = $("#newpwd1").val();
	var newpwd2 = $("#newpwd2").val();
	if (newpwd1 == "" || newpwd2 == "") {
		$("#newpwd_div").attr("class", "form-group has-error has-feedback");
		$("#newpwd_span").attr("class",
				"glyphicon glyphicon-remove form-control-feedback");
		$("#newpwd_err_span").html("请输入密码");
		$("#updatepwdButton").attr("disabled", true);
	} else {
		if (newpwd1 == newpwd2) {
			$("#newpwd_div").attr("class",
					"form-group has-success has-feedback");
			$("#newpwd_span").attr("class",
					"glyphicon glyphicon-ok form-control-feedback");
			$("#updatepwdButton").attr("disabled", false);
		} else {
			$("#newpwd_div").attr("class", "form-group has-error has-feedback");
			$("#newpwd_span").attr("class",
					"glyphicon glyphicon-remove form-control-feedback");
			$("#newpwd_err_span").html("两次密码要一致");
			$("#updatepwdButton").attr("disabled", true);
		}
	}
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
				rows : getGridDatas(userInfoUrl + "?rows=" + rowForPage
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
						rows : getDatas(userInfoUrl + "?rows=" + rowForPage
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