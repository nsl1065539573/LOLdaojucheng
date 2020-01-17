<%--
  Created by IntelliJ IDEA.
  User: nansongling
  Date: 2019/4/11
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>个人信息</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css"/>
    <script src="${pageContext.request.contextPath}/js/head.js" type="text/javascript" charset="utf-8"></script>
    <link href="${pageContext.request.contextPath}/css/down.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/userInfo.css" type="text/css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/js/userInfo.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
    <div class="container-fluid">
        <%@include file="head.jsp"%>
        <div class="row">
            <div class="d13 col-xs-1 col-sm-1 col-md-1 col-lg-1">
            </div>
            <div id="userInfoDiv" class="d13 col-xs-10 col-sm-10 col-md-10 col-lg-10">
                <div class="userDetail"><span class="span_1">用户名&nbsp;:</span><input id="username" class="yangshi_input"  readonly="readonly" type="text" value="username"/></div>
                <div class="userDetail"><span class="span_1">昵称&nbsp;&nbsp;:</span><input id="nicheng" class="yangshi_input"  readonly="readonly" type="text" value="您还没有设置昵称" /></div>
                <div class="userDetail"><span class="span_1">邮箱&nbsp;&nbsp;:</span><input id="email" class="yangshi_input"  readonly="readonly" type="text" value="邮箱" /></div>
                <div class="userDetail"><span class="span_1">手机&nbsp;&nbsp;:</span><input id="phone" class="yangshi_input"  readonly="readonly" type="text" value="手机" /></div>
                <div class="userDetail"><span class="span_1">密保问题:</span><input id="question" class="yangshi_input" readonly="readonly" type="text" value="nicheng" /></div>
                <div style="margin-top: 40px;width: 100%;text-align: center"><button id="changeUserInfo" class="button_yangshi">修改信息</button><button id="changePassword" class="button_yangshi">修改密码</button></div>
                <img id="img_timo" src="${pageContext.request.contextPath}/img/timo.gif">
                <img id="img_xiaopao" src="${pageContext.request.contextPath}/img/xiaopao.jpg">
            </div>
            <div class="d13 col-xs-1 col-sm-1 col-md-1 col-lg-1">
            </div>
            <div class="style_change" id="change_userInfo">
                <div class="change_head">
                    <span class="change_head_span">更改个人信息</span><div class="close_style" id="close_changeUserInfo"><span class="glyphicon glyphicon-remove"></span></div>
                </div>
                <div class="change_body">
                    <div class="changeUserDetail"><span class="span_1">昵称&nbsp;&nbsp;:</span><input id="changeNicheng" class="yangshi_input"  type="text" /></div>
                    <div class="changeUserDetail"><span class="span_1">邮箱&nbsp;&nbsp;:</span><input id="changeEmail" class="yangshi_input"   type="text" /></div>
                    <div class="changeUserDetail"><span class="span_1">手机&nbsp;&nbsp;:</span><input id="chanegPhone" class="yangshi_input"   type="text"  /></div>
                    <div class="changeUserDetail"><span class="span_1">密保问题:</span><input id="changeQuestion" class="yangshi_input"  type="text"  /></div>
                    <div class="changeUserDetail"><span class="span_1">密保答案:</span><input id="changeAnswer" class="yangshi_input"  type="text"  /></div>
                    <div style="width: 100%;text-align: center"><button id="change_sure" class="button_yangshi">确定</button></div>
                </div>
            </div>
            <div class="style_change" id="check_answer">
                <div class="change_head">
                    <span class="change_head_span">请校验密保答案</span><div class="close_style" id="close_checkAnswer"><span class="glyphicon glyphicon-remove"></span></div>
                </div>
                <div class="change_body">
                    <div class="changeUserDetail"><span class="span_1">密保问题:</span><input id="checkQuestion" class="yangshi_input"  type="text"  /></div>
                    <div class="changeUserDetail"><span class="span_1">密保答案:</span><input id="checkAnswer" class="yangshi_input"  type="text"  /></div>
                    <div style="width: 100%;text-align: center"><button id="change_sure1" class="button_yangshi">确定</button></div>
                </div>
            </div>
            <div class="style_change" id="update_password">
                <div class="change_head">
                    <span class="change_head_span">请更改密码</span><div class="close_style" id="close_update_password"><span class="glyphicon glyphicon-remove"></span></div>
                </div>
                <div class="change_body">
                    <div class="changeUserDetail"><span class="span_1">新密码:</span><input id="newPassword" class="yangshi_input"  type="password"  /></div>
                    <div class="changeUserDetail"><span class="span_1">确认密码:</span><input id="checkNewPassword" class="yangshi_input"  type="password"  /></div>
                    <div style="width: 100%;text-align: center"><button id="change_sure2" class="button_yangshi">确定</button></div>
                </div>
            </div>
        </div>
        <%@include file="down.jsp"%>
    </div>
</body>
</html>
