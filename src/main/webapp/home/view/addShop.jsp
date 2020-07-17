<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ua" value="${sessionScope.userAuthorization.authorization}"></c:set>
<script type="text/javascript">
    var item_id = "${param.id}";
    var ua = "${ua}";
    var idex = 1;
    var p;
    $(function () {
        getItemInfo();
    });

    function getItemInfo() {
        console.log("==========================>进入详情页面商品id为" + item_id);
        var data = {
            id: item_id
        };
        if (item_id != null && item_id > 0) {
            $.ajax({
                url: $ctx + '/shop/detail',
                type: 'post',
                data: data,
                async: false,
                dataType: 'json',
                success: function (data) {
                    $("#shop_id").val(data.shop.id);
                    $("#shop_name").val(data.shop.name);
                    $("#shop_link").val(data.shop.link);
                    $("#shop_address").val(data.shop.address);
                    if (data.shopUsers != null) {
                        $.each(data.shopUsers, function (key, value) {
                            initUser(value.id, value.type, value.name, value.phone, value.wechat_name, value.wechat, value.qq, value.qq_name);
                        })
                    }
                }
            });
            if (ua != 3) {
                $('#butSave').hide();
                $('#shop_name').attr("readonly", "");
                $('#shop_address').attr("readonly", "");
                $('#shop_link').attr("readonly", "");
            }
        }
    }

    function gobackpage() {
        $('#showId').load($ctx + "/home/view/dpxx.jsp", p);
        $('#li_name').html("店铺列表");
    }

    function addShop() {
        var data = serializeObject($("#item_info_form"));
        if (data.name == '') {
            alert("请输入店铺名称")
            return
        }
        $.ajax({
            url: $ctx + '/shop/save',
            type: 'post',
            data: data,
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data.code == 1) {
                    $('#alert_dialog_success').html("保存成功");
                    $('#modal_success').modal('show');
                    item_id = data.data;
                    $('.shop_id').val(item_id);
                    debugger
                } else {
                    $('#alert_dialog_danger').html(data.message);
                    $('#modal_danger').modal('show');
                }

            }
        });
    }

    function addShopUser(idex) {
        var data = serializeObject($("#user_form" + idex + ""));
        if (data.name == '') {
            alert("请输入姓名")
            return
        }
        if (data.phone == '') {
            alert("请输入手机号")
            return
        }
        $.ajax({
            url: $ctx + '/shop/user/save',
            type: 'post',
            data: data,
            async: false,
            dataType: 'json',
            success: function (data) {
                $("#user_id" + idex + "").val(data.data);
                if (data.code == 1) {
                    $('#alert_dialog_success').html("保存成功");
                    $('#modal_success').modal('show');
                } else {
                    $('#alert_dialog_danger').html(data.message);
                    $('#modal_danger').modal('show');
                }
            }
        });
    }

    function deleteUser(idex) {
        var id = $("#user_id" + idex + "").val();
        if (id > 0) {
            if (window.confirm("确定删除?")) {
                $.ajax({
                    url: $ctx + '/shop/user/delete?id=' + id,
                    type: 'get',
                    async: false,
                    dataType: 'json',
                    success: function (r) {
                    }
                });
            } else {
                return;
            }
        }
        $("#user_form" + idex + "").hide();
    }

    function initUser(id, type, name, phone, wechat_name, wechat, qq, qq_name) {
        var read;
        if (id == 0 || ua == 3) {
            read = '';
        } else {
            read = ' readonly ';
        }
        var ba;
        if (type == 0) {
            ba = "老板"
        } else {
            ba = "运营"
        }
        idex++;
        var table = ' <form class=" col-xs-12 col-sm-12 col-md-12" id="user_form' + idex + '" role="form">\n' +
            '<input name="type" type="hidden" value="' + type + '">' +
            '<input name="id"  id="user_id' + idex + '" type="hidden" value="' + id + '">' +
            '<input class="shop_id" name="shop_id" type="hidden" value="' + item_id + '">' +
            '                    <table style="width: 90%; border-collapse: separate; border-spacing: 10px">\n' +
            '                        <tr>\n' +
            '                            <td>姓名：</td>\n' +
            '                            <td class="form-inline"><input ' + read + ' id="user_name" name="name" value="' + name + '" style="width: 50%"\n' +
            '                                                           class="form-control"><span\n' +
            '                                    style="color: #cc0001">&nbsp;&nbsp;*</span></td>\n' +
            '                            <td>联系电话：</td>\n' +
            '                            <td class="form-inline"><input ' + read + ' id="user_phone" name="phone" value="' + phone + '" style="width: 50%"\n' +
            '                                                           class="form-control"><span\n' +
            '                                    style="color: #cc0001">&nbsp;&nbsp;*</span></td>\n' +
            '                        </tr>\n' +
            '                        <tr>\n' +
            '                            <td>微信昵称：</td>\n' +
            '                            <td class="form-inline"><input  ' + read + ' id="user_wecaht_name" name="wechat_name"  value="' + wechat_name + '" style="width: 50%"\n' +
            '                                                           class="form-control"></td>\n' +
            '                            <td>微信号码：</td>\n' +
            '                            <td class="form-inline"><input  ' + read + ' id="user_wecaht" name="wechat"  value="' + wechat + '" style="width: 50%"\n' +
            '                                                           class="form-control"></td>\n' +
            '                        </tr>\n' +
            '                        <tr>\n' +
            '                            <td>QQ昵称：</td>\n' +
            '                            <td class="form-inline"><input  ' + read + ' id="qq" name="qq_name" value="' + qq_name + '" style="width: 50%"\n' +
            '                                                           class="form-control"></td>\n' +
            '                            <td>QQ号码：</td>\n' +
            '                            <td class="form-inline"><input  ' + read + ' id="qq_name" name="qq" value="' + qq + '" style="width: 50%"\n' +
            '                                                           class="form-control"></td>\n' +
            '                        </tr>'
        if (ua == 3 || id == 0) {
            table += '                        <tr>\n' +
                '                            <td></td>\n' +
                '                            <td colspan="2">\n' +
                '                                <div class="form-inline">\n' +
                '                                    <button class="btn btn-primary form-control" type="button" onclick="addShopUser(' + idex + ')">\n' +
                '                                        <i class="glyphicon glyphicon-saved"></i>保存' + ba + '信息' +
                '                                    </button>\n' +
                '                                    <button class="btn btn-primary form-control" type="button" onclick="deleteUser(' + idex + ')">删除\n' +
                '                                    </button>\n' +
                '                                </div>\n' +
                '                            </td>\n' +
                '                            <td></td>\n' +
                '                        </tr>\n' +
                '                    </table>\n' +
                '                    <hr/>\n' +
                '                </form>';
        }
        table += '<hr>';
        if (type == 0) {
            $('#dpfzr').append(table);
        } else {
            $('#yyfzr').append(table);
        }
    }

    function addUser(type) {
        initUser(0, type, "", "", "", "", "", "");
    }
</script>
<%-- <script src="${ctx }/static/js/_tjcx.js"></script> --%>
<div class="col-xs-12 col-sm-12 col-md-12 row">
    <div class="" id="book_accordion-element">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <a href="#" onclick="gobackpage()" class="btn">返回店铺列表</a>
            <form class=" col-xs-12 col-sm-12 col-md-12" id="item_info_form" role="form">
                <input id="shop_id" name="id" type="hidden" value="0">
                <hr/>
                <h4>店铺信息</h4>
                <table style="width: 90%; border-collapse: separate; border-spacing: 10px">
                    <tr>
                        <td>店铺名称：</td>
                        <td class="form-inline"><input id="shop_name" name="name" style="width: 100%"
                                                       class="form-control" required><span
                                style="color: #cc0001">&nbsp;&nbsp;*</span>
                        </td>
                    </tr>

                    <tr>
                        <td>店铺地址：</td>
                        <td class="form-inline"><input id="shop_address" name="address" style="width: 100%"
                                                       class="form-control"></td>
                    </tr>
                    <tr>
                        <td>店铺链接：</td>
                        <td class="form-inline"><input id="shop_link" name="link" style="width: 100%"
                                                       class="form-control"></td>
                    </tr>
                    <tr id="butSave">
                        <td></td>
                        <td>
                            <div class="form-inline">
                                <button class="btn btn-primary form-control" type="button" onclick="addShop()">
                                    <i class="glyphicon glyphicon-saved"></i>保存店铺信息
                                </button>
                                <button class="btn btn-primary form-control" type="reset">
                                    <i class="glyphicon glyphicon-repeat"></i>重置
                                </button>
                            </div>
                        </td>
                        <td></td>
                    </tr>
                </table>
            </form>
            <h4>店铺负责人
                <button class="btn " type="button" style="width: 60px" onclick="addUser(0)"> 新增
                </button>
            </h4>
            <div id="dpfzr">

            </div>
            <h4>运营负责人
                <button class="btn " type="button" style="width: 60px" onclick="addUser(1)"> 新增</button>
            </h4>
            <div id="yyfzr">

            </div>
        </div>
    </div>
    <a href="#" onclick="gobackpage()" class="btn">返回店铺列表</a>
    <hr/>
</div>


