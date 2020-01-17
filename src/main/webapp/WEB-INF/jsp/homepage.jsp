<%@ page import="com.LOLdaojucheng.common.Const" %><%--
  Created by IntelliJ IDEA.
  User: nansongling
  Date: 2019/2/28
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>道聚城</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.cookie.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/zhujiemian.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css" />
    <script src="${pageContext.request.contextPath}/js/homepage.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/head.js" type="text/javascript" charset="utf-8"></script>
    <link href="${pageContext.request.contextPath}/css/down.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/daohanglan.css" type="text/css" rel="stylesheet" />
</head>
<body style="background-color:#F3F4F6;padding: 0;">
<div id="d04" class="container-fluid" style="padding: 0px">
    <%@include file="head.jsp"  %>

    <!--商品导航栏-->

        <%@include file="daohanglan.jsp"%>
        <!--轮播图区域-->
        <div id="d010" class="row">
            <div style="height:302px;" id="d07" class="col-md-2 col-lg-2 col-xs-2 col-sm-2">
                <div id="d037">
                    <img src="${pageContext.request.contextPath}/img/9.png" id="i04">
                    <p id="hasLoginP" class="s3">您还未登录哦，
                        <a href="http://127.0.0.1:8020/英雄联盟道聚城/denglu.html?__hbt=1533797777089" id="a1">立即登录</a><br /> 购买超值商品！
                    </p>
                </div>
                <div id="d035">
                    <p id="p6">当前未绑定角色</p>
                    <p id="p5">
                        <a href="./protal/login">立即绑定></a>
                    </p>
                </div>
                <div id="d036">
                    <a href="#" id="a2"><span style="color:#969596;" class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: #969596;">我的优惠券</span></a><br />
                    <a href="#" id="a3"><span style="color: #969596;" class="	glyphicon glyphicon-magnet"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: #969596;">查询Q币Q点余额</span></a>
                </div>
                <div id="d038">
                    <p><span>公告</span><span id="s4"><a href="#">更多></a></span></p>
                    <p class="p1">
                        <a class="a8" href="#">LOL聚诚品免费抓娃娃</a>
                    </p>
                    <p class="p1">
                        <a class="a8" href="#">LOL道聚城新版商城上线啦</a>
                    </p>
                    <p style="color:black" class="p1">
                        <a class="a8" href="#">迎接新赛季优惠券限时领</a>
                    </p>
                    <p class="p1">
                        <a class="a8" href="#">冰雪节领券使用说明</a>
                    </p>
                </div>
            </div>
            <div id="myCarousel" class="d08 carousel slide col-md-7 col-lg-7 col-xs-7 col-sm-7">
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="d039 carousel-inner">
                    <div class=" d040 item active">
                        <a href="#"><img class="i3" src="${pageContext.request.contextPath}/img/14.png" alt="First slide"></a>
                    </div>
                    <div class=" d040 item">
                        <a href="#"><img class="i3" src="${pageContext.request.contextPath}/img/13.png" alt="Second slide"></a>
                    </div>
                    <div class="d040 item">
                        <a href="#"><img class="i3" src="${pageContext.request.contextPath}/img/11.png" alt="Third slide"></a>
                    </div>
                    <div class="d040 item">
                        <a href="#"><img class="i3" src="${pageContext.request.contextPath}/img/10.png" alt="fourth slide"></a>
                    </div>
                </div>
                <!-- 轮播（Carousel）导航 -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
            <div id="d09" class="col-md-2 col-lg-2 col-xs-2 col-sm-2">
                <a style="color: orange;" class="a4" href="#"><button class="b01"><span class="glyphicon glyphicon-fire"></span><span>活动中心</span></button></a><br />
                <a style="color: orange;" class="a4" href="#"><button class="b01"><span class="glyphicon glyphicon-tag"></span><span>聚豆商城</span> </button></a><br />
                <img style="height:140px;width:100%;border: 0px;" src="${pageContext.request.contextPath}/img/4.jpg">
            </div>
        </div>

    <!--<!--商品列表栏-->
    <div id="dd014" class="row d051">
        <div id="d011" class="col-md-6 col-lg-6 col-xs-6 col-sm-6">
            <p>限时抢购</p>
        </div>
        <div id="d012" class="col-md-6 col-lg-6 col-xs-6 col-sm-6">
            <a id="a4" href="#">
                <p id="p7">更多></p>
            </a>
        </div>
    </div>
    <!--打折商品栏
    -->

    <div id="chaoguo1">
        <div id="chaoguo1_head">
            <span id="wenxintishi1">温馨提示</span><span id="closer_1" class="glyphicon glyphicon-remove"></span>
        </div>
        <div id="chaoguo1_body">
            <p id="p_tishi">对不起，该道具每次购买不得超过一件</p><br/>
            <button id="queding_button">确定</button>
        </div>
    </div>
    <div id="add_div">
        <div id="head">
            <span style="font-size: 16px;font-weight: bold"> 加入购物车</span>><span id="close-button" class="glyphicon glyphicon-remove"></span>
        </div>
        <div id="add_body">

            <div id="add_body_left">
                <img id="add_subimg" src="${pageContext.request.contextPath}/img/weikezi_cangqiongzhiguang_sub.jpg"/><br/>
                <span id="add_name">星际迷航  拉克丝</span>
            </div>
            <div id="add_body_right">
                <span>价格：</span><span id="price" style="font: bold 24px Arial;color: #ff5900">59</span><span style="color: #FF5900">&nbsp;Q币</span><br/>
                <button id="add_button">确定</button>
            </div>
        </div>
    </div>
    <div id="queren_add">
        <div id="tishi">
            <span id="wenxintishi">温馨提示</span><span id="closer" class="glyphicon glyphicon-remove"></span>
        </div>
        <div id="queren_body">
            <div style="height: 30px;width: 100%;margin-top:10px "><span id="add_cart_span">道具已经成功加入购物车</span></div><br/>
            <div><button id="return_buy">继续购物</button><button id="go_cart">去购物车结算</button></div>
        </div>
    </div>
    <div id="dd015" class="row d051" >

    </div>
    <!--热门推荐新品上架栏-->
    <div id="d020" class="row">
        <div id="021" class="col-md-10 col-lg-10 col-xs-10 col-sm-10">
            <div class="d042"><span style="font-size:20px;color:#555555;margin-left: 60px;" id="s5">热门推荐  </span><span id="s6"><a herf="#" id="i05">更多></a></span>
                <hr />
            </div>
            <div id="div_1">
            </div>
        </div>
        <div id="d022" class="container col-md-2 col-lg-2 col-xs-2 col-sm-2">
            <div id="d023" class="row">
                <div class="d043">
                    <p style="margin-top:40px;font-size: 15px;color:red">热门排行</p>
            </div>
            <div id="div_4" style="margin-top:10px;">
            </div>
            </div>
            </div>

    </div>
    <!--周边商城-->
    <div id="d025" class="row d051">
        <div id="d026" style="margin-top:15px;" class="col-md-12 col-lg-12 col-xs-12 col-sm-12">
            <span style="font-size:20px;color:#555555;margin-left: 30px;margin-top: 50px;">周边商城</span>
            <hr />
        </div>
    </div>
    <div id="d027" class="row d051">
        <div id="d028" style="display: flex;" class="col-md-12 col-lg-12 col-xs-12 col-sm-12">
        </div>
    </div>
    <!--二维码那一栏-->
    <!--最后一栏！！！-->
    <%@include file="down.jsp"%>
</div>
</body>
<script>
    var pageUrl = "${pageContext.request.contextPath}/";
  $(function () {

      /***
       * 点击商品进入商品详情
       */
      $("#dd015").on("click",".aaa2,.aaa3",function () {
          var value = $(this).attr("value");

          $.cookie("productId",value,{expires: 7, path: "/" });
          alert($.cookie("productId"));
          window.location.href="/LOLdaojucheng/protalView/details";
      });


  });

</script>
</html>