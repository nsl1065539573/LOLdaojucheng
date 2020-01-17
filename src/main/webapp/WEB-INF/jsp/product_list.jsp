<%--
  Created by IntelliJ IDEA.
  User: nansongling
  Date: 2019/3/7
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品列表</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.cookie.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/head.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/down.css" type="text/css" />
    <link href="/LOLdaojucheng/css/product_list.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/product_list.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/daohanglan.css"/>
</head>
<body>
<div class="container-fluid" style="padding: 0px">
<%@include file="head.jsp"%>
    <div id="daohanglan_div_id_01" class="row">
        <div class="col-md-1 col-lg-1 col-xs-1 col-sm-1">
            <button id="daohanglan_button_id_01">全部分类</button>
        </div>
        <div id="daohanglan_div_id_02" class="col-md-11 col-lg-11 col-xs-11 col-sm-11">
            <ul id="daohanglan_ul_id_01">
            </ul>
        </div>
    </div>
<div class="row">
    <!-- 用这个盒子当左边空白-->
    <div class="col-md-1 col-lg-1 col-xs-1 col-sm-1"></div>
    <!-- 这个盒子做热门推荐-->
    <div class="col-md-2 col-lg-2 col-xs-2 col-sm-2" style="padding: 0px">
        <p style="margin-top:40px;font-size: 15px;color:red">热门排行</p>
        <div id="list_div_id_01">
        </div>
    </div>
    <div class="col-md-9 col-lg-9 col-xs-9 col-sm-9" >
        <div id="list_div_id_02">
        </div>
        <div style="width: 100%;text-align: center">
            <ul id="list_ul" class="pagination">

            </ul>
        </div>

        
    </div>
</div>
<%@include file="down.jsp"%>

</div>
</body>
</html>
