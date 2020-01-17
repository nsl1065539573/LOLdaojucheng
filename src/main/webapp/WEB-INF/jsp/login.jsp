<%--
  Created by IntelliJ IDEA.
  User: nansongling
  Date: 2019/2/26
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登陆界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.cookie.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/denglu.css"/>
    <script src="${pageContext.request.contextPath}/js/login.js" type="text/javascript" charset="utf-8"></script>
</head>
<body class = "b1">
<!--最大的那个栅格模型的盒子-->
<!--填充背景颜色-->
<div  id="d_2" class="container-fluid">
    <!--最上面那一栏功能栏-->
    <div class="row" id="d2">
        <div id="d3" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <img id="i1" src="${pageContext.request.contextPath}/img/logo3.jpg" />
        </div>
        <div id="d4" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <img src="${pageContext.request.contextPath}/img/logo4.png" id="i2"/>
        </div>
    </div>
    <!--这里放登陆的窗口-->
    <div class="row " id="d5">

        <div id="d7" class="col-lg-6 col-md-6 col-sm-6 col-xs-0">
            <img id="i3" src="${pageContext.request.contextPath}/img/beijing1.png"  />
        </div>
        <div id="d8" class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
            <div  id="d11">
                <p id="p5"></p>
            </div>
            <div id="d10">
                <form>
                    <p  class="p1">
                        Username：
                    </p>
                    <input name="username" type="text" id="in1" placeholder=" 请输入账号"/><br />
                    <p  class="p1">
                        Password：
                    </p>
                    <input name="password" type="password" id="in2" placeholder=" 请输入密码"/><br />
                    <p class="p1">
                        check:
                    </p>
                    <input id="check" type="text" placeholder="点击右侧获得验证码">
                    <input id="check_code" type="text" readonly="readonly" >
                    <button type="button" id="b2">登录</button><br />
                </form>
                <p id="p3">注册账号</p>
                <p id="p4">忘记密码？</p>
            </div>
            <div>

            </div>
        </div>
    </div>
    <!--这里放最下面的那几栏小字-->
    <%@include file="login_down.jsp"%>
</div>
</body>
</html>
