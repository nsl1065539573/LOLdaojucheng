<%--
  Created by IntelliJ IDEA.
  User: nansongling
  Date: 2019/4/13
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>乐淘-商品管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/system.css">
    <script src="${pageContext.request.contextPath}/assets/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<body>
<div class="system">
    <div class="aside">
        <div class="profile">
            <div class="avatar img-circle">
                <img src="${pageContext.request.contextPath}/images/my_logo.jpg">
            </div>
            <h4>张学友</h4>
        </div>
        <div class="navs">
            <ul class="list-unstyled">
                <li>
                    <a href="/LOLdaojucheng/admin/user">
                        <i class="fa fa-user"></i>
                        用户管理
                    </a>
                </li>
                <li>
                    <a href="javascript:;">
                        <i class="fa fa-bars"></i>
                        分类管理
                        <i class="fa fa-angle-right"></i>
                    </a>
                    <ul class="list-unstyled">
                        <li>
                            <a href="/LOLdaojucheng/admin/categoryFirst">一级分类管理</a>
                        </li>
                        <%--<li class="active">--%>
                            <%--<a href="/LOLdaojucheng/admin/categorySecond">二级分类管理</a>--%>
                        <%--</li>--%>
                    </ul>
                </li>
                <li>
                    <a href="/LOLdaojucheng/admin/product">
                        <i class="fa fa-product-hunt"></i>
                        商品管理
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="main">
        <div class="header">
            <nav class="navbar navbar-custom">
                <div class="navbar-header">
                    <a href="javascript:;" class="navbar-brand">
                        <i class="fa fa-navicon"></i>
                    </a>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="javascript:;" class="login_out_bot">
                            <i class="fa fa-sign-out"></i> 退出
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="body">
            <button class="operation btn btn-primary" data-toggle="modal" data-target=".category-second">添加分类</button>
            <table class="table table-bordered">
                <tr>
                    <th>品牌编号</th>
                    <th>品牌名称</th>
                    <th>品牌logo</th>
                    <th>所属分类</th>
                </tr>
                <tr>
                    <td>7</td>
                    <td>阿萨德2</td>
                    <td>
                        <img src="${pageContext.request.contextPath}/images/brand6.png">
                    </td>
                    <td>女士馆</td>
                </tr>
            </table>
            <div class="btn-group page">
                <div class="btn btn-default" id="prevBtn">上一页</div>
                <div class="btn btn-default" id="nextBtn">下一页</div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade category-second">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
                <h4 class="modal-title" id="myLargeModalLabel">添加分类</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <select class="form-control">
                        <option>请选择商品分类</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="请输入商品名称">
                </div>
                <div class="form-group">
                    <input type="file" class="form-control">
                </div>
                <img src="images/brand6.png" alt="..." class="img-thumbnail">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>