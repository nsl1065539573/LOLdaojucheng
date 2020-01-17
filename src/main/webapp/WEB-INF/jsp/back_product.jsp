<%--
  Created by IntelliJ IDEA.
  User: nansongling
  Date: 2019/4/13
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>道聚城-商品管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/system.css">
    <script src="${pageContext.request.contextPath}/assets/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common.js"></script>
    <script src="${pageContext.request.contextPath}/js/back_product.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.fom.js" charset="utf-8"></script>
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
                        <%--<li>--%>
                            <%--<a href="/LOLdaojucheng/admin/categorySecond">二级分类管理</a>--%>
                        <%--</li>--%>
                    </ul>
                </li>
                <li class="active">
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
                        <a id="login_out"  class="login_out_bot">
                            <i class="fa fa-sign-out"></i> 退出
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="body">
            <button id="add_product" class="operation btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">添加商品</button>
            <table id="list_table" class="table table-bordered">

            </table>
            <div id="fenye" style="width: 100%;text-align: center">
                <ul id="fenyeUL" class="pagination">

                </ul>

            </div>
        </div>
    </div>
</div>

<div class="modal fade bs-example-modal-lg">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
                <h4 class="modal-title" id="myLargeModalLabel">添加商品</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <select id="gameName" class="form-control">
                        <option>-- 请选择游戏 --</option>
                        <option data-value="1">-- 英雄联盟 --</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="text" id="pifuming" class="form-control" placeholder="请输入商品名称">
                </div>
                <div class="form-group">
                    <input type="text" id="yingxiongming" class="form-control" placeholder="请输入商品副名称">
                </div>
                <div class="form-group">
                    <input type="text" id="detail" class="form-control" placeholder="请输入产品描述">
                </div>
                <div class="form-group">
                    <input type="text" id="stock" class="form-control" placeholder="请输入产品数量">
                </div>
                <div class="form-group">
                    <select id="categoryIds" class="form-control">
                        <option data-value="0">-- 请选择商品分类 --</option>
                    </select>
                </div>
                <div class="form-group">
                    <input id="price" type="text" class="form-control" placeholder="请输入商品原价">
                </div>
                <div class="form-group">
                    <select id="productStatus" class="form-control">
                        <option data-value="0">-- 请选择商品状态 --</option>
                        <option data-value="1">-- 正常 --</option>
                        <option data-value="5">-- 打折 --</option>
                        <option data-value="4">-- 热销 --</option>
                        <option data-value="6">-- 热门 --</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="text" id="rebate" class="form-control" placeholder="请输入商品折扣">
                </div>
                <div class="form-group">
                    <form action="/LOLdaojucheng/manage/product/upload" method="post" id="uploadMain" enctype="multipart/form-data">
                        <input style="display: inline-block;" type="file" name="upload_file"/>
                        <input type="button" value="提交主图" id="mainPicture">
                    </form>
                </div>
                <div class="form-group">
                    <form action="/LOLdaojucheng/manage/product/upload" method="post" id="uploadSub" enctype="multipart/form-data">
                        <input style="display: inline-block;" type="file" name="upload_file"/>
                        <input type="button" value="提交副图" id="subPicture">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="addProduct">添加</button>
            </div>
        </div>
    </div>
</div>
<div class="style_div" id="change_stock">
    <div class="style_head" id="change_stock_head">
        <span class="style_head_span">更改商品数量</span><div class="style_close" id="close_change_stock"><span  class="glyphicon glyphicon-remove"></span></div>
    </div>
    <div id="change_stock_body">
        <input class="style_input" id="change_stock_input" placeholder="请输入数量"><br/>
        <button id="change_stock_button" class="style_button">确定</button>
    </div>
</div>
<div class="style_div" id="change_details">
    <div class="style_head" id="change_details_head">
        <span class="style_head_span">更改商品描述</span><div class="style_close" id="close_change_details"><span  class="glyphicon glyphicon-remove"></span></div>
    </div>
    <div style="text-align: center" id="change_details_body">
        <textarea placeholder="请输入商品描述" id="change_details_input"></textarea><br/>
        <button id="change_details_button" class="style_button">确定</button>
    </div>
</div>
<div class="style_div" id="productDetails">
    <div class="style_head">
        <span class="style_head_span">更多商品信息</span><div class="style_close" id="close_product_details"><span  class="glyphicon glyphicon-remove"></span></div>
    </div>
    <div style="text-align: center">

        <select id="status_select" class="details_input">
            <option data-value="5">--打折--</option>
            <option data-value="4">--热销--</option>
            <option data-value="6">--热门--</option>
            <option data-value="1">--正常--</option>
        </select><br/>
        <input class="details_input" readonly="readonly" id="zhekou" type="text"><button id="change_zhekou" class="details_button">修改</button><br/>
        <input class="details_input" readonly="readonly" id="jiage" type="text"><button id="change_jiage" class="details_button">修改</button><br/>
        <button class="style_button" id="change_detail_button">确定修改</button>
    </div>
</div>
<div id="zhezhaoceng">

</div>
</body>
<style>
    .details_input{
        width: 200px;
        height: 29px;
        border-radius: 3px;
        padding-left: 10px;
        border: 1px darkgray solid;
        margin-top: 30px;
    }
    #zhezhaoceng{
        position: fixed;
        left: 0;
        right: 0;
        bottom: 0;
        top: 0;
        background-color: rgb(0 ,0 ,0);
        opacity: 0.3;
        z-index: 90;
        visibility: hidden;
    }
    .style_div{
        position: fixed;
        z-index:120 ;
        top: 20%;
        left: 20%;
        height: 60%;
        width: 60%;
        background-color: white;
        opacity: 95%;
        visibility: hidden;
    }
    #change_details_input{
        width: 200px;
        height: 150px;
        border-radius: 3px;
        padding-left: 10px;
        border: 1px darkgray solid;
        margin-top: 50px;
    }
    .style_head{
        height: 20%;
        width: 100%;
        background-color: #F5F5F5;
        border-bottom: 2px solid #DDDDDD;
    }
    .style_head_span{
        display: inline-block;
        margin-left: 30px;
        margin-top: 25px;
        font-size: 18px;
        font-weight: bold;
    }
    .style_close{
        display: inline-block;
        border: 1px solid #DDDDDD;
        height: 25px !important;
        width: 25px !important;
        text-align: center;
        line-height: 25px !important;
        font-size: 14px !important;
        color: #BDBDBD;
        border-radius: 50%;
        cursor: pointer;
        position: absolute;
        top: 20px;
        right: 20px;
    }
    .style_close:hover{
        background-color: red;
        color: white;
    }
    .style_input{
        height: 35px;
        width: 200px;
        border-radius: 3px;
        padding-left: 10px;
        border: 1px darkgray solid;
        margin-top: 50px;
    }
    .style_button{
        background-color: #36AB87;
        color: white;
        font-size: 15px;
        height: 35px;
        width: 140px;
        border: none;
        border-radius: 3px;
        margin-left: 25px;
        margin-top: 30px;
    }
    .details_button{
        background-color: #36AB87;
        color: white;
        height: 29px;
        width: 70px;
        border: none;
        border-radius: 2px;
        margin-left: 25px;
    }
    #change_stock_body{
        text-align: center;
    }
</style>
</html>
