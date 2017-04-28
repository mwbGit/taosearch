var select_status;
var onSelectRow;
var caseInfoUrl = $ctx + '/order/queryFinancialStatementsForPage';
var ImgUrl = $ctx + '/img/download';
var $case_infoGrid = $("#case_Info");
var $case_paginator = $("#case_paginator");
var gridDatas;
var rowForPage = 20;
var queryParamsData;
var page = 1;
var baseUrl = $ctx;
var item_state;
var ua = $ua;
var team_id = $team_id;
$(function() {
	// 获取数据信息
	initGrid();
	initUserTeam();
	controlQuerySelect(ua, team_id);
});
// 获取数据信息
/**
 * 序号， 提交时间 图片 商品名称 服务单价 券后价格 佣金信息 优惠券信息 提交人 状态 操作
 * 
 * @returns
 */
function initGrid() {
	var gridDatas = getGridDatas(caseInfoUrl + '?page=' + page + '&rows='
			+ rowForPage);
	console.log(gridDatas);
	$case_infoGrid.datagrid(
			{
				columns : [ [
						{
							title : '排行',
							field : 'username',
							formatter : function(val, row, indexId) {
								var rownum = (gridDatas.page - 1)
										* gridDatas.rows + indexId + 1;
								return rownum;
							}
						}, {
							title : '业务员',
							field : 'username',
							formatter : function(val) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '提报数量',
							field : 'totle',
							formatter : function(val) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '日均提报',
							field : 'daynum',
							formatter : function(val, row) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '待审核',
							field : 'dscnum',
							formatter : function(val, row) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '驳回',
							field : 'bhnum',
							formatter : function(val, row) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '拒绝率',
							field : 'jjl',
							formatter : function(val, row) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '拒绝',
							field : 'jjnum',
							formatter : function(val, row) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '待二审',
							field : 'desnum',
							formatter : function(val, row) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '推广中',
							field : 'tgznum',
							formatter : function(val, row) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '已结束',
							field : 'yjsnum',
							formatter : function(val, row) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '待付款',
							field : 'dfknum',
							formatter : function(val, row) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '付款中',
							field : 'fkznum',
							formatter : function(val, row) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '拒绝付款',
							field : 'jjfknum',
							formatter : function(val, row) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '已付款',
							field : 'yfknum',
							formatter : function(val, row) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '客单价',
							field : 'kdj',
							formatter : function(val, row) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '实收金额',
							field : 'ssje',
							formatter : function(val, row) {
								if (val == null || val == "") {
									return "";
								} else {
									return val;
								}
							}
						}, {
							title : '应收金额',
							field : 'ysje',
							formatter : function(val, row) {
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
		rows : gridDatas.list
	});
	$case_paginator.bootstrapPaginator({
		currentPage : page,
		totalPages : gridDatas.totalpage,
		onPageClicked : function(e, originalEvent, type, pages) {
			// loadGrid(caseInfoUrl+"?rows="+rowForPage+"&page="+pages);
			$case_infoGrid.datagrid('loadData', {
				rows : getGridDatas(caseInfoUrl + "?rows=" + rowForPage
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
			$("#query_team_select").html("");
			$("#query_team_select").append(
					'<option class="form-control" value="">选择业务员小组</option>');
			for (var i = 0; i < datas.length; i++) {
				$("#query_team_select").append(
						'<option class="form-control" value="'
								+ datas[i].team_id + '">' + datas[i].team_name
								+ '</option>');
			}
		}
	});
}
function controlQuerySelect(ua, team_id) {
	console.log("================================>查看权限");
	console.log(ua);
	var key = ua;
	console.log(key);
	switch (key) {
	case '1':
		$("#query_team_select").attr("disabled", true);
		$("#query_user_select").attr("disabled", true);
		break;
	case '2':
		$("#query_team_select").attr("disabled", true);
		initqueryUserSelect(team_id);
		break;
	case '3':
		break;
	}
}
function query_team_selectchange() {
	var x = $("#query_team_select")[0].options;
	var text = $(x[x.selectedIndex]).html();
	var value = $(x[x.selectedIndex]).val();
	console.log(text);
	console.log(value);
	initqueryUserSelect(value);
}
function initqueryUserSelect(value) {
	if (value != "") {
		var data = {
			team_id : value
		};
		$
				.ajax({
					url : $ctx + '/user/getTeamUsers',
					data : data,
					type : 'post',
					async : false,
					dataType : 'json',
					success : function(datas) {
						$("#query_user_select").html("");
						$("#query_user_select")
								.append(
										'<option class="form-control" value="">选择业务员</option>');
						for (var i = 0; i < datas.length; i++) {
							$("#query_user_select").append(
									'<option class="form-control" value="'
											+ datas[i].user_id + '">'
											+ datas[i].username + '</option>');
						}
					}
				});
	} else {
		$("#query_user_select").html("");
		$("#query_user_select").append(
				'<option class="form-control" value="">选择业务员</option>');
	}
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
function loadGrid(url) {
	gridDatas = getGridDatas(url);
	$case_infoGrid.datagrid("loadData", {
		rows : gridDatas.list
	});
	$case_paginator.bootstrapPaginator({
		currentPage : page,
		totalPages : gridDatas.totalpage,
		onPageClicked : function(e, originalEvent, type, pages) {
			$case_infoGrid.datagrid('loadData', {
				rows : getGridDatas(caseInfoUrl + "?rows=" + rowForPage
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
/**
 * 多条件查询接口
 */
function queryItem() {
	var data = serializeObject($("#query_splb_form"));
	queryParamsData = data;
	console.info(111, data);
	$.ajax({
		url : caseInfoUrl + '?page=' + page + '&rows=' + rowForPage,
		data : data,
		type : 'post',
		async : false,
		success : function(datas) {
			console.info(datas)
			$case_infoGrid.datagrid('loadData', {
				rows : datas.list
			});
			$case_paginator.bootstrapPaginator({
				currentPage : 1,
				totalPages : datas.totalpage,
				onPageClicked : function(e, originalEvent, type, pages) {
					$case_infoGrid.datagrid('loadData', {
						rows : getDatas(caseInfoUrl + "?rows=" + rowForPage
								+ "&page=" + pages, queryParamsData).list
					});
				}
			})
		}
	})
}
function downloadCase() {
	var team_id = $("#query_team_select").val();
	var user_id = $("#query_user_select").val();
	var create_start_time = $("#create_start_time").val();
	var create_end_time = $("#create_end_time").val();
	var update_start_time = $("#update_start_time").val();
	var update_end_time = $("#update_end_time").val();
	window.open($ctx + '/order/queryFinancialStatementsForExcel?team_id='
			+ team_id + '&user_id=' + user_id + '&create_start_time='
			+ create_start_time + '&create_end_time=' + create_end_time
			+ '&update_start_time=' + update_start_time + '&update_end_time='
			+ update_end_time);
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