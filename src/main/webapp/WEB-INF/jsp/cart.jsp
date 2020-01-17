<%--
  Created by IntelliJ IDEA.
  User: nansongling
  Date: 2019/3/26
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>购物车</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gouwuche.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css"/>
    <script src="${pageContext.request.contextPath}/js/head.js" type="text/javascript" charset="utf-8"></script>
    <link href="${pageContext.request.contextPath}/css/down.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/cart.js"  charset="utf-8"></script>
</head>
<body class="b01">
<div class="d1 container-fluid" style="padding: 0px">
    <%@include file="head.jsp"%>
    <!--您现在的位置那一栏小字-->
    <div class="row d12">
        <div class="d13 col-xs-1 col-sm-1 col-md-1 col-lg-1">

        </div>
        <div class="d14 col-xs-10 col-sm-10 col-md-10 col-lg-10">
            <p class="p1">您现在的位置><a  class="a6"  href="#">购物车</a></p>
        </div>
        <div class="d13 col-xs-1 col-sm-1 col-md-1 col-lg-1">

        </div>
    </div>
    <!--收货角色信息-->
    <div class="row d15">
        <div class="d13 col-xs-1 col-sm-1 col-md-1 col-lg-1">

        </div>
        <div class="d16 col-xs-10 col-sm-10 col-md-10 col-lg-10">
            <p class="p2">收货角色信息</p>
            <div class="d37"></div>
        </div>
        <div class="d13 col-xs-1 col-sm-1 col-md-1 col-lg-1">

        </div>
    </div>
    <!--当前绑定角色那一栏-->
    <div class="row d17">
        <div class="d13 col-xs-1 col-sm-1 col-md-1 col-lg-1">

        </div>
        <div class="d18 col-xs-10 col-sm-10 col-md-10 col-lg-10">
            <p class="p3">当前绑定角色：<span id="bangding_span" class="p4">当前未绑定角色</span>&nbsp&nbsp;&nbsp;&nbsp;<a href="#"><button id="bangdingjuese" class="b4">绑定角色</button></a></p>
            <p class="p5">商品信息</p>
            <div class="d38"></div>
        </div>
        <div class="d13 col-xs-1 col-sm-1 col-md-1 col-lg-1">

        </div>
    </div>
    <!--地址栏窗口-->
    <div id="shipping">
        <div id="shipping_head">
            <span id="span_1">绑定角色</span><div id="close_shipping"><span  class="glyphicon glyphicon-remove"></span></div>
        </div>
        <div id="shipping_body">
            <div class="input_div"><span class="fenlei_span">游戏：</span><input id="gameName" class="input_1" type="text" placeholder="请输入游戏名" /></div>
            <div class="input_div"><span class="fenlei_span">网络：</span><input id="netName" class="input_1" type="text" placeholder="电信或网通" /></div>
            <div class="input_div"><span class="fenlei_span">大区：</span><input id="daquName" class="input_1" type="text" placeholder="请输入大区" /></div>
            <div class="input_div"><span class="fenlei_span">角色：</span><input id="jueseName" class="input_1" type="text" placeholder="请输入角色名" /></div>
            <div class="input_div"><span class="fenlei_span">姓名：</span><input id="nameName" class="input_1" type="text" placeholder="请输入收件人姓名" /></div>
            <div class="input_div"><span class="fenlei_span">电话：</span><input id="phoneNum" class="input_1" type="text" placeholder="请输入收件手机号" /></div>
            <button id="bangding">绑定角色</button>
        </div>
    </div>
    <!--更改地址窗口-->
    <div id="changeShippingInfoDiv">
        <div id="changeShipping_head">
            <span id="span_3">更改地址信息</span><div id="close_changeShipping"><span class="glyphicon glyphicon-remove"></span></div>
        </div>
        <div id="changeShipping_body">
            <div class="input_div"><span class="fenlei_span">游戏：</span><input id="changeGameName" class="input_1" type="text" placeholder="请输入游戏名" /></div>
            <div class="input_div"><span class="fenlei_span">网络：</span><input id="changeNetName" class="input_1" type="text" placeholder="电信或网通" /></div>
            <div class="input_div"><span class="fenlei_span">大区：</span><input id="changeDaquName" class="input_1" type="text" placeholder="请输入大区" /></div>
            <div class="input_div"><span class="fenlei_span">角色：</span><input id="changeJueseName" class="input_1" type="text" placeholder="请输入角色名" /></div>
            <div class="input_div"><span class="fenlei_span">姓名：</span><input id="changeNameName" class="input_1" type="text" placeholder="请输入收件人姓名" /></div>
            <div class="input_div"><span class="fenlei_span">电话：</span><input id="changePhoneNum" class="input_1" type="text" placeholder="请输入收件手机号" /></div>
            <button id="updateShipping">更新角色</button>
        </div>
    </div>
    <!--当前用户的所有的收货地址-->
    <div id="shipping_list">
        <div id="shipping_list_head">
            <span id="span_2">更改角色</span><div id="close_shipping_list"><span class="glyphicon glyphicon-remove"></span></div>
        </div>
        <div id="shipping_list_body">
            <div id="details_list">
            </div>
            <div id="shipping_list_bottom">
                <button id="revokeChecked" class="button_list">选中</button><button id="changeShippingInfo" class="button_list">更改</button>
                <button id="deleteShipping" class="button_list">删除</button><button id="addShipping" class="button_list">新建</button>
            </div>
        </div>
    </div>
    <!--你所购买的商品再这里-->
    <div class="row d19">
        <div class="d13 col-xs-1 col-sm-1 col-md-1 col-lg-1">

        </div>
        <div id="append_cart" class="d20 col-xs-10 col-sm-10 col-md-10 col-lg-10">
            <div class="d21">
                <div class="d40"></div>
                <span class="p6"><input type="checkbox" style="display: inline-block" id="allCheck" class="checkbox" v-model="checked">全选</span>
                <span class="p7">商品名称</span>
                <span style="margin-left: 120px;" class="p9">类型</span>
                <span class="p8">单价</span>
                <span class="p12">期限</span>
                <span class="p13">数量</span>
                <span class="p14">优惠</span>
                <span class="p15">小计</span>
                <span class="p16">操作</span>
                <div class="d39"></div>
            </div>
            <%--<div class="d22">--%>
                <%--<input class="i4" type="checkbox" class="checkbox" v-model="checked">&nbsp;&nbsp;&nbsp;&nbsp;--%>
                <%--<a href="#"><img src="${pageContext.request.contextPath}/img/22.jpg" class="i3"></a>&nbsp;&nbsp;--%>
                <%--<span class="p10"><a class="a11" href="#">苍穹之光</a>&nbsp;&nbsp;<a class="a11" href="#">维克兹</a></span>--%>
                <%--<span class="p11">皮肤</span>--%>
                <%--<span class="p17">69&nbsp;&nbsp;Q币</span>--%>
                <%--<span class="p18">永久</span>--%>
                <%--<span class="p19"><button class="b5">-</button><input class="i5" type="text" value="1"><button class="b6">+</button></span>--%>
                <%--<span class="p20">限时折扣</span>--%>
                <%--<span class="p21">34.5&nbsp;&nbsp;Q币</span>--%>
                <%--<span class="p22"><a class="a7" href="#">删除</a></span>--%>
            <%--</div>--%>

            <%--<div class="d22">--%>
                <%--<input class="i4" type="checkbox" class="checkbox" v-model="checked">&nbsp;&nbsp;&nbsp;&nbsp;--%>
                <%--<a href="#"><img src="img/24.jpg" class="i3"></a>&nbsp;&nbsp;--%>
                <%--<span class="p10"><a class="a11" href="#">苍穹之光</a>&nbsp;&nbsp;<a class="a11" href="#">约里克</a></span>--%>
                <%--<span class="p11">皮肤</span>--%>
                <%--<span class="p17">69&nbsp;&nbsp;Q币</span>--%>
                <%--<span class="p18">永久</span>--%>
                <%--<span class="p19"><button class="b5">-</button><input class="i5" type="text" value="1"><button class="b6">+</button></span>--%>
                <%--<span class="p20">限时折扣</span>--%>
                <%--<span class="p21">34.5&nbsp;&nbsp;Q币</span>--%>
                <%--<span class="p22"><a class="a7" href="#">删除</a></span>--%>
            <%--</div>--%>
        </div>
        <div class="d13 col-xs-1 col-sm-1 col-md-1 col-lg-1">

        </div>
    </div>
    <!--您的优惠信息那一栏小字-->
    <div class="d23 row">
        <div class="d13 col-xs-1 col-sm-1 col-md-1 col-lg-1">

        </div>
        <div class="d24 col-xs-10 col-sm-10 col-md-10 col-lg-10">
            <p class="p1">您的优惠信息</p>
            <div class="d25">
                <span class="p23">优惠券总量：&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;<a href="#">如何获得优惠券>></a></span>
                <p class="p24"><span class="p25">实付款</span><span id="totalPrice" class="p26">0<span class="p25">Q币</span></span><span class="p27"><a class="a12" id="jiesuan" href="#"><button class="b7">去结算</button></a></span></p>
            </div>
        </div>
        <div class="d13 col-xs-1 col-sm-1 col-md-1 col-lg-1">

        </div>
    </div>
    <!--搜索栏-->
    <!--<div class="row d26">
        <div class="d13 col-xs-1 col-sm-1 col-md-1 col-lg-1">

        </div>
        <div class="d27 col-xs-10 col-sm-10 col-md-10 col-lg-10">
            <div class="d28">

            </div>
            <div class="d29">

            </div>
            <div class="d30">

            </div>
        </div>
        <div class="d13 col-xs-1 col-sm-1 col-md-1 col-lg-1">

        </div>
    </div>-->
    <!--温馨提示那四行-->
    <%--<div class="row d31">--%>
        <%--<div class="41 col-xs-1 col-sm-1 col-md-1 col-lg-1">--%>

        <%--</div>--%>
        <%--<div class="42 col-xs-10 col-sm-10 col-md-10 col-lg-10">--%>
            <%--<p class="30">温馨提示：</p>--%>
            <%--<div class="d44">--%>

            <%--</div>--%>
            <%--<p class="p31">"LOL优惠券使用规则：[道具包]分类道具全场无法使用，每张优惠券的信息，请仔细核对以下信息：订单满减的金额是否符合要求、优惠券限定可用的道具范围。"</p>--%>
            <%--<p class="p32">"购买成功后系统会自动发货，如发货失败24小时内会自动补发。"</p>--%>
            <%--<p class="p32">"请确认游戏中是否存在购物车中的英雄或皮肤，如果存在，请勿重复购买。"</p>--%>
            <%--<p class="p32">"请重新登录游戏查收物品。"</p>--%>
        <%--</div>--%>
        <%--<div class="43 col-xs-1 col-sm-1 col-md-1 col-lg-1">--%>

        <%--</div>--%>
    <%--</div>--%>
    <%--<!--下面那四栏-->--%>
    <%--<div class="row d36">--%>
        <%--<div class="d32 col-xs-3 col-sm-3 col-md-3 col-lg-3">--%>
            <%--<p class="p33">我是新手</p>--%>
            <%--<a href="#" class="a10"><p class="p34">什么是道聚城</p></a>--%>
            <%--<a href="#" class="a10"><p class="p34">如何购买</p></a>--%>
            <%--<a href="#" class="a10"><p class="p34">如何赠送</p></a>--%>
            <%--<a href="#" class="a10"><p class="p34">如何获取聚豆</p></a>--%>
        <%--</div>--%>
        <%--<div class="d33 col-xs-3 col-sm-3 col-md-3 col-lg-3">--%>
            <%--<p class="p33">个人中心</p>--%>
            <%--<a href="#" class="a10"><p class="p34">发货时间</p></a>--%>
            <%--<a href="#" class="a10"><p class="p34">如何领取</p></a>--%>
            <%--<a href="#" class="a10"><p class="p34">补发规则</p></a>--%>
            <%--<a href="#" class="a10"><p class="p34">VIP价定义</p></a>--%>
            <%--<a href="#" class="a10"><p class="p34">道具状态定义</p></a>--%>
        <%--</div>--%>
        <%--<div class="d34 col-xs-3 col-sm-3 col-md-3 col-lg-3">--%>
            <%--<p class="p33">支付方式</p>--%>
            <%--<a href="#" class="a10"><p class="p34">支付方式</p></a>--%>
            <%--<a href="#" class="a10"><p class="p34">购物点支付</p></a>--%>
            <%--<a href="#" class="a10"><p class="p34">如何充值</p></a>--%>

        <%--</div>--%>
        <%--<div class="d35 col-xs-3 col-sm-3 col-md-3 col-lg-3">--%>
            <%--<p class="p33">腾讯在线服务</p>--%>
            <%--<a href="#" class="a10"><p class="p34">腾讯官方在线客服</p></a>--%>
        <%--</div>--%>
    <%--</div>--%>
    <!--最后一栏！！-->
    <div class="row d36">

    </div>
    <%@include file="down.jsp"%>

</div>
</body>
</html>
