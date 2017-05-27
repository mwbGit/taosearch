var select_status;
var onSelectRow;
var caseInfoUrl = $ctx + '/order/queryItemlistForPage';
var DicCommObjItemUrl = $ctx
		+ '/caseInfoController/queryDicCommObjItemByTypeId';
var ImgUrl = $ctx + '/img/download';
var $case_infoGrid = $("#case_Info");
var $case_paginator = $("#case_paginator");
var gridDatas;
var rowForPage = 10;
var queryParamsData;
var page = 1;
var baseUrl = $ctx;
var item_state;
var ua = $ua;
var team_id = $team_id;
$(function() {
	if ($p_page != "") {
		page = $p_page
	}
	if ($p_rows != "") {
		rowForPage = $p_rows
	}
	initnavstateinfo();
	// 获取数据信息
	initGrid();
	initItemType();
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
	var gridDatas = getGridDatas(caseInfoUrl + '?statePage=000&page=' + page + '&rows='
			+ rowForPage);
	console.log(gridDatas);
	$case_infoGrid
			.datagrid(
					{
						columns : [ [
								{
									title : '<input type="checkbox" name="choose">',
									field : 'item_id',
									formatter : function(val, row, indexId) {
										console
												.log('==============>'
														+ indexId);
										var rownum = (gridDatas.page - 1)
												* gridDatas.rows + indexId + 1;
										return '<input type="checkbox" name="choose" value="'
												+ val + '">' + rownum;
									}
								},
								{
									title : '提交时间',
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
									title : '图片',
									field : 'item_image',
									formatter : function(val, row) {
										console.log(row);
										return '<a target="_blank" href="'
												+ row.item_url
												+ '"><img alt="商品主图" src="'
												+ val
												+ '" width="100" height="100"></a>';
									}
								},
								{
									title : '产品名称',
									field : 'item_name',
									formatter : function(val, row) {
										console
												.log("==============>"
														+ baseUrl);
										if (row.item_website_type == '1') {
											if (val == null || val == "") {
												return "";
											} else {
												return '<p>'
														+ val
														+ '</p>'
														+ '</br><img alt="" src="'
														+ baseUrl
														+ '/static/image/taobao.png" width="20" height="20">ID:'
														+ row.item_no;
											}
										} else if (row.item_website_type == '2') {
											if (val == null || val == "") {
												return "";
											} else {
												return val
														+ '</br><img alt="" src="'
														+ baseUrl
														+ '/static/image/tmall.ico" width="20" height="20">ID:'
														+ row.item_no;
											}
										}
									}
								},
								{
									title : '服务单价',
									field : 'item_fwdj',
									formatter : function(val) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '券后价格',
									field : 'item_qhjg',
									formatter : function(val, row) {
										if (val == null || val == "") {
											return "";
										} else {
											return val;
										}
									}
								},
								{
									title : '佣金信息',
									field : 'item_yjbl',
									formatter : function(val, row) {
										console.log(val);
										console.log(row.item_jhlb);
										if (row.item_jhlb == '002') {
											if (val == null || val == "") {
												return "";
											} else {
												return getItemjhlb(row.item_jhlb)
														+ '：'
														+ val
														+ '%</br><a target="_blank" href="'
														+ row.item_jhlj
														+ '">查看计划连接</a></br>'
														+ getItemhdlx(row.item_hdlx);
											}
										} else {
											if (val == null || val == "") {
												return "";
											} else {
												return getItemjhlb(row.item_jhlb)
														+ '：'
														+ val
														+ '%</br>'
														+ getItemhdlx(row.item_hdlx);
											}
										}

									}
								},
								{
									title : '优惠券信息',
									field : 'coupon_start_time',
									formatter : function(val, row) {
										if (val == null || val == "") {
											return "";
										} else {
											var str = '<p>开始时间：'
													+ getTimeDay(val)
													+ '</p></br>结束时间：'
													+ getTimeDay(row.coupon_end_time);
											if (row.state != '001'
													&& row.state != '002'
													&& row.state != '999'
													&& row.state != '112') {
												str += '</br>领取/剩余：'
														+ row.coupon_get_num
														+ '/'
														+ row.coupon_rest_num;
											}
											return str;
										}
									}
								},
								{
									title : '提交人',
									field : 'username',
									width : 6,
									formatter : function(val, row) {
										if (val == null || val == "") {
											return "";
										} else {
											return '<p>' + val + '</p>'
													+ '</br>QQ:' + row.qq;
										}
									}
								},
								{
									title : '状态',
									field : 'state',
									formatter : function(val, row) {
										if (row.remark == null
												|| row.remark == "") {
											return getItemState(val);
										} else {
											if (row.state == "112"
													|| row.state == "999"
													|| row.state == "667") {
												return getItemState(val) + ':'
														+ row.remark;
											} else {
												return getItemState(val);
											}
										}
									}
								},
								{
									title : '操作',
									field : 'state',
									formatter : function(val, row) {
										var str = "<a 10%href='#' onclick='skipInfo(\""
											+ row.item_id + "\")'>查看</a>";
										str += "</br><a href='#' onclick='skipupdateItemInfo(\"" + row.item_id + "\")'>再次提交</a>";


										return str;
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
	$case_paginator.bootstrapPaginator({
		currentPage : page,
		totalPages : gridDatas.totalpage,
		onPageClicked : function(e, originalEvent, type, pages) {
			// loadGrid(caseInfoUrl+"?rows="+rowForPage+"&page="+pages);
			$case_infoGrid.datagrid('loadData', {
				rows : getGridDatas(caseInfoUrl + "?statePage="+item_state+"&rows=" + rowForPage
						+ "&page=" + pages).list
			});
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
			$("#query_item_type_select").html("");
			$("#query_item_type_select").append(
					'<option class="form-control" value="">所有分类</option>');
			for (var i = 0; i < datas.length; i++) {
				$("#query_item_type_select").append(
						'<option class="form-control" value="'
								+ datas[i].type_id + '">' + datas[i].type_name
								+ '</option>');
			}
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
function claim(id) {
	$.ajax({
		url : $ctx + '/order/claim?id=' + id,
		type : 'get',
		async : false,
		dataType : 'json',
		success : function(datas) {
			alert("成功");
		}
	});
}
function skipInfo(id) {
	console.log(id);
	console.log(gridDatas.page);
	console.log(gridDatas.rows);
	p = {
		item_id : id,
		page : gridDatas.page,
		rows : gridDatas.rows
	}
	$('#showId').load($ctx + "/home/view/iteminfo.jsp", p);
	$('#li_name').html("页面详情");
}
function skipupdateItemInfo(id) {
	console.log(id);
	console.log(gridDatas.page);
	console.log(gridDatas.rows);
	p = {
			item_id : id,
			page : gridDatas.page,
			rows : gridDatas.rows
	}
	$('#showId').load($ctx + "/home/view/updateiteminfo.jsp", p);
	$('#li_name').html("商品再次提交");
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
function initnavstateinfo() {
	$.ajax({
		url : $ctx + '/order/getnavstateinfo',
		type : 'get',
		async : false,
		dataType : 'json',
		success : function(datas) {
			$("#nav_state_query").html("");
			for (var i = 0; i < datas.length; i++) {
				if (i == 0) {
					$("#nav_state_query").append(
							"<li><a href='#' onclick='ItemByStateChange(\""
									+ datas[i].state + "\",this)'>"
									+ getItemState(datas[i].state) + "("
									+ datas[i].num + ")</a></li>");
				} else {
					$("#nav_state_query").append(
							"<li><a href='#' onclick='ItemByStateChange(\""
									+ datas[i].state + "\",this)'>"
									+ getItemState(datas[i].state) + "("
									+ datas[i].num + ")</a></li>");
				}
			}
			$("#nav_state_query li:first").prop("class", 'active');
		}
	});
}
function ItemByStateChange(state, obj) {
	item_state = state;
	console.log(state);
	obj = $(obj).parent('li');
	console.log(obj);
	var lis = obj.siblings().removeClass('active');
	obj.addClass('active');
	var data = {
		state : state
	};
	$.ajax({
		url : caseInfoUrl + '?statePage='+item_state+'&page=' + page + '&rows=' + rowForPage,
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
						rows : getDatas(caseInfoUrl + "?statePage="+item_state+"&rows=" + rowForPage
								+ "&page=" + pages, queryParamsData).list
					});
				}
			})
		}
	});
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
	case '000':
		value = '全部商品';
		break;
	case '009':
		value = '即将结束';
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
				rows : getGridDatas(caseInfoUrl + "?statePage="+item_state+"&rows=" + rowForPage
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
	data.state = item_state;
	queryParamsData = data;
	console.info(111, data);
	$.ajax({
		url : caseInfoUrl + '?statePage='+item_state+'&page=' + page + '&rows=' + rowForPage,
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
						rows : getDatas(caseInfoUrl + "?statePage="+item_state+"&rows=" + rowForPage
								+ "&page=" + pages, queryParamsData).list
					});
				}
			})
		}
	})
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