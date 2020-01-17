<%--
  Created by IntelliJ IDEA.
  User: nansongling
  Date: 2019/4/24
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>我的订单</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.cookie.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/head.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/down.css" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Order.css" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/Order.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/daohanglan.css" type="text/css" />
</head>
<body>
<div class="container-fluid" class="noJianJu">
    <%@include file="head.jsp"%>
    <%@include file="daohanglan.jsp"%>
    <div id="erweimaDiv">
        <div id="divHead">
            <span id="span_1">扫码支付</span><div id="close_erweima"><span  class="glyphicon glyphicon-remove"></span></div>
        </div>
        <div id="bodyDiv">

        </div>
    </div>
    <div id="juliDiv" class="row noJianJu">
        <%--//留左边的空白--%>
        <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">

        </div>
        <%--中间订单的部分--%>
        <div id="orderMain" class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
            <div id="orderHead">
                我的订单<br/>
                <span id="span_bottom" class="line" >
                    <span class="line_1"></span>
                </span>
            </div>
            <div id="orderBody">
                <div id="divLogo">  <p id="orderLogo">所有订单</p></div>
                <div id="orderList">
                    <table id="orderTable" class="table table-bordered">
                        <tr>
                            <th class="center">订单编号</th>
                            <th class="center">订单物品</th>
                            <th class="center">数量</th>
                            <th class="center">实付款</th>
                            <th class="center">订单状态</th>
                            <th class="center">操作</th>
                        </tr>
                    </table>
                </div>
                <video src="blob:https://player.bilibili.com/96de1939-1df0-4988-8431-80146e68bd57"></video>
            </div>

        </div>
        <%--//右边的空白--%>
        <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">

        </div>
    </div>
    <%@include file="down.jsp"%>
</div>


</body>
<script>
    var URL = "${pageContext.request.contextPath}";
</script>
</html>
