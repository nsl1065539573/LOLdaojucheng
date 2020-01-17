<%--
  Created by IntelliJ IDEA.
  User: nansongling
  Date: 2019/3/12
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品详情</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.cookie.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css" />
    <script src="${pageContext.request.contextPath}/js/head.js" type="text/javascript" charset="utf-8"></script>
    <link href="${pageContext.request.contextPath}/css/down.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/daohanglan.css" type="text/css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/js/details.js" type="text/javascript" charset="utf-8"></script>
    <link href="${pageContext.request.contextPath}/css/details.css" type="text/css" rel="stylesheet" />
</head>
<body style="background-color: #F3F4F6">
<div class="container-fluid">
<%@include file="head.jsp"%>
<%@include file="daohanglan.jsp"%>
    <div class="row">
        <div class="col-md-1 col-lg-1 col-xs-1 col-sm-1" style="background-color: #F3F4F6">
        </div>
        <div class="col-md-2 col-lg-2 col-xs-2 col-sm-2" id="details_div_id_04" style="background-color: white">
        </div>
        <div class="col-md-8 col-lg-8 col-xs-8 col-sm-8" id="details_div_id_01" style="background-color: white">
            <div class="row" id="details_div_id_row">

            </div>
            <div class="row" id="details_div_id_row_01">

            </div>
        </div>
        <div class="col-md-1 col-lg-1 col-xs-1 col-sm-1" style="background-color: #F3F4F6">
        </div>
    </div>
    <div class="row" style="height: 40px">

    </div>
<%@include file="down.jsp"%>
</div>
</body>
</html>
