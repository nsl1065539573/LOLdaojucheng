<%--
  Created by IntelliJ IDEA.
  User: nansongling
  Date: 2019/2/28
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>注册界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/zhuce.css" />
    <script src="${pageContext.request.contextPath}/js/registe.js" type="text/javascript" charset="utf-8"></script>

</head>
<body class="b1">
<!--最大的那个栅格模型的盒子-->
<!--填充背景颜色-->
<div id="d_2" class="container-fluid">
    <!--最上面那一栏功能栏-->
    <div class="row" id="d2">
        <div id="d3" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <img id="i1" src="${pageContext.request.contextPath}/img/logo3.jpg" />
        </div>
        <div id="d4" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <img src="${pageContext.request.contextPath}/img/logo4.png" id="i2" />
        </div>
    </div>

    <!--这里放登陆的窗口-->
    <div class="row " id="d5">

        <div id="d7" class="col-lg-6 col-md-6 col-sm-6 col-xs-0">
            <img id="i3" src="${pageContext.request.contextPath}/img/beijing2.png" />
        </div>
        <div id="d8" class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
            <div id="d11">
                <p id="p5"></p>
            </div>
            <div id="d10">
                <form>
                    <p id="p_1">
                        Username：
                    </p>
                    <input name="username" type="text" id="in1" placeholder=" 请输入账号" /><br />
                    <p id="p_2">
                        Password：
                    </p>
                    <input name="password" type="password" id="in2" placeholder=" 请输入密码" /><br />
                    <p id="p_3">
                        Notarize：
                    </p>
                    <input type="password" id="in3" placeholder=" 请确认密码" /><br />
                    <p id="p_4">
                        email:
                    </p>
                    <input type="text" name="email" id="in4" placeholder="请输入邮箱"><br />
                    <p id="p_5">
                        phone:
                    </p>
                    <input type="text" name="phone"  id="in5" placeholder="请输入电话"><br />
                    <p id="p_6">
                        question:
                    </p>
                    <input type="text" name="question" id="in6" placeholder="请输入密保问题"><br />
                    <p id="p_7">
                        answer:
                    </p>
                    <input type="text" name="answer" id="in7" placeholder="请输入密保答案"><br />
                    <button type="button" id="b2">注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册</button><br />
                </form>

                <p id="p3">已有账号？请登录</p>
                <p id="p4">忘记密码？</p>
            </div>
            <div>

            </div>
        </div>
    </div>
    <!--这里放最下面的那几栏小字-->
    <%@include file="regist_down.jsp"%>

</div>
</body>

</html>
