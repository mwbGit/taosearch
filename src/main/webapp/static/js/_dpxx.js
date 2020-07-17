var select_status;
var onSelectRow;
var caseInfoUrl = $ctx + '/shop/getShops';
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
    // 获取数据信息
    initGrid();
    controlQuerySelect(ua, team_id);
});
// 获取数据信息
/**
 * 序号， 提交时间 图片 商品名称 服务单价 券后价格 佣金信息 优惠券信息 提交人 状态 操作
 *
 * @returns
 */
function initGrid() {
    var gridDatas = getGridDatas(caseInfoUrl + '?hz=true&orderColumn=total_settlement&order=desc&page=' + page + '&rows='
        + rowForPage);
    console.log(gridDatas);
    $case_infoGrid
        .datagrid(
            {
                columns : [ [
                    {
                        title : '店铺ID',
                        field : 'id',
                        formatter : function(val) {
                            if (val == null || val == "") {
                                return "";
                            } else {
                                return val;
                            }
                        }
                    },
                    {
                        title : '负责人',
                        field : 'username',
                        formatter : function(val) {
                            if (val == null || val == "") {
                                return "";
                            } else {
                                return val;
                            }
                        }
                    },
                    {
                        title : '店铺名称',
                        field : 'name',
                        formatter : function(val, row) {
                            if (val == null || val == "") {
                                return "";
                            } else {
                                return "<a href='"+row.link+ "' target='_blank'> "+ val +"</a>";
                            }
                        }
                    },

                    {
                        title : '推广产品数',
                        field : 'item_num',
                        formatter : function(val, row) {
                            if (val == null || val == "") {
                                return "0";
                            } else {
                                return  val ;
                            }
                        }
                    },
                    {
                        title : '推广次数',
                        field : 'real_item_num',
                        formatter : function(val, row) {
                            if (val == null || val == "") {
                                return "0";
                            } else {
                                return  val ;
                            }
                        }
                    },

                    {
                        title : '产品客单价(均)',
                        field : 'price',
                        formatter : function(val, row) {
                            if (val == null || val == "") {
                                return "0";
                            } else {
                                return  val;
                            }
                        }
                    },
                    {
                        title : '单次推广结算(均)',
                        field : 'settlement',
                        formatter : function(val, row) {
                            if (val == null || val == "") {
                                return "0";
                            } else {
                                return  val;
                            }
                        }
                    },
                    {
                        title : '总结算',
                        field : 'total_settlement',
                        formatter : function(val, row) {
                            if (val == null || val == "") {
                                return "0";
                            } else {
                                return  val;
                            }
                        }
                    },
                    {
                        title : '操作',
                        field : 'id',
                        formatter : function(val, row) {
                            var str = "<a href='#' onclick='skipInfo(\""  + val +"\" )'>店铺详情</a>";
                            str += "</br><a href='#' onclick='tgxq(\"" + val + "\")'>推广详情</a>";
                            str += "</br><a href='#' onclick='addShop(\"" + val + "\")'>修改</a>";

                            return str;
                        }
                    }
                ] ],
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
            $case_infoGrid.datagrid('loadData', {
                rows : getGridDatas(caseInfoUrl + "?hz=true&rows=" + rowForPage +"&page=" + pages).list
            });
        }
    });
}
function skipInfo(id, state) {
    console.log(id);
    console.log(gridDatas.page);
    console.log(gridDatas.rows);
    p = {
        item_id: id,
        page: gridDatas.page,
        rows: gridDatas.rows
    }
    if (state == '005' || state == '667') {
        p = {
            item_id1: id,
            page: gridDatas.page,
            rows: gridDatas.rows
        }
        $('#showId').load($ctx + "/home/view/iteminfo1.jsp", p);
        $('#li_name').html("页面详情");
    }else {
        $('#showId').load($ctx + "/home/view/iteminfo.jsp", p);
        $('#li_name').html("页面详情");
    }
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
    p = {
        id : id,
    };
    $('#showId').load($ctx + "/home/view/shopinfo.jsp", p);
    $('#li_name').html("店铺详情");
}
function addShop(id) {
    p = {
        id : id
    };
    $('#showId').load($ctx + "/home/view/addShop.jsp", p);
    $('#li_name').html("店铺详情");
}
function tgxq(id) {
    p = {
        shop_id : id,
        show_search : 1
    };
    $('#showId').load($ctx + "/home/view/index3.jsp", p);
    $('#li_name').html("推广详情");
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
function getTimeDay(str) {
    if (str == null || str == "") {
        return "";
    } else {
        return str.substring(0, 10);
    }
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