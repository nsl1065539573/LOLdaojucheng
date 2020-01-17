<%--
  Created by IntelliJ IDEA.
  User: nansongling
  Date: 2019/4/13
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>道聚城-后台管理登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <script src="${pageContext.request.contextPath}/assets/jquery/jquery.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/back_login.js" charset="utf-8"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-6 position">
            <div class="form-top">
                <div class="form-top-left">
                    <h3>登录道聚城后台管理系统</h3>
                    <p>请输入您的用户名和密码</p>
                </div>
                <div class="form-top-right">
                    <i class="fa fa-key"></i>
                </div>
            </div>
            <div class="form-bottom">
                <%--<form role="form" class="login-form">--%>
                    <div class="form-group">
                        <input id="username" type="text" placeholder="用户名..." class="form-username form-control">
                    </div>
                    <div class="form-group">
                        <input id="password" type="password" placeholder="密码..." class="form-password form-control">
                    </div>
                    <button style="width: 100%" id="loginButtonBack" class="btn btn-primary">登录</button>
                <%--</form>--%>
            </div>
        </div>
    </div>
</div>
</body>
</html>
