<%--
  Created by IntelliJ IDEA.
  User: nansongling
  Date: 2019/3/2
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="head_div_id_01" class="row">
    <div class="head_div_class_01 col-md-6 col-xs-12 col-sm-6 col-lg-6">
        <ul class="nav navbar-nav">
            <li class="active">
                <a class="head_a_class_01" href="#"><span class="glyphicon glyphicon-phone"></span>手机版</a>
            </li>
            <li>
                <a class="head_a_class_01" href="#">游戏币交易</a>
            </li>
            <li>
                <a class="head_a_class_01" href="#">Q币充值</a>
            </li>
            <li>
                <a class="head_a_class_01" href="#">心悦游戏玩家</a>
            </li>
        </ul>
    </div>
    <div class="head_div_class_01 col-md-6 col-xs-12 col-sm-6 col-lg-6">
        <ul class="nav navbar-nav">
            <li id="head_li_id_01" class="active">您还未登录请
                <a id="login" class="head_a_class_01" style="margin-left:0px !important;color:grey;" href="#">登陆</a>
            </li>
            <li>
                <a id="orderLi" class="head_a_class_01" href="#">我的订单</a>
            </li>
            <li>
                <a id="user_info_li" class="head_a_class_01" href="#">个人中心</a>
            </li>
            <li>
                <a class="head_a_class_01" href="#">官方论坛</a>
            </li>
            <li>
                <a class="head_a_class_01" href="#">帮助中心</a>
            </li>
            <li class="dropdown">
                <a class="head_a_class_01" href="#" class="dropdown-toggle" data-toggle="dropdown">
                    游戏导航 <b class="caret"></b>
                </a>
                <ul id="head_ul_id_01" class="dropdown-menu">
                    <li class="divider">
                    <li>
                        <a class="head_a_class_02" href="#"> 传奇霸业</a>
                    </li>
                    <li class="divider">
                    <li>
                        <a class="head_a_class_02" href="#"> 贪玩蓝月</a>
                    </li>
                    <li class="divider">
                    <li>
                        <a class="head_a_class_02" href="#"> 冒险岛</a>
                    </li>
                    <li class="divider">
                    <li>
                        <a class="head_a_class_02" href="#">洛克王国</a>
                    </li>
                    <li class="divider">
                    <li>
                        <a class="head_a_class_02" href="#"> 赛尔号</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<div id="head_div_id_02" class="row">
    <div class="head_div_class_01 col-md-4 col-lg-4 col-xs-12 col-sm-4">
         <span id="head_span_yishuzi">道聚城</span>
    </div>
    <div class="head_div_class_01 col-md-5 col-lg-5 col-xs-12 col-sm-5">
        <div id="dianji">
        <input id="head_input_id_01" type="text" placeholder="输入道具进行搜索">
        <button id="head_button_id_01"><span class="glyphicon glyphicon-search"></span></button>
        <div id="head_div_id_03"></div>
        </div>

    </div>
    <div class="head_div_class_01 col-md-3 col-lg-3 col-xs-12 col-sm-3">
        <img id="head_img_id_02" src="${pageContext.request.contextPath}/img/7.png">
    </div>

</div>
<!-- 遮罩层-->
<div id="zhezhaoceng">

</div>
<!--登录窗口-->
<div id="login_div">
    <div id="login_div_head">
        <span id="login_div_head_tishi">输入账号密码登录</span><a href="#"><span id="close_login_div" class="glyphicon glyphicon-remove"></span></a>
    </div>
    <div id="login_div_body">
        <input name="username" id="login_username" type="text" placeholder="请输入用户名" /><br/>
        <input name="password" id="login_password" type="password" placeholder="请输入密码" /><br/>
        <input id="check" type="text" placeholder="点击右侧获得验证码">
        <input id="check_code" type="text" readonly="readonly" ><br/>
        <button type="button" id="login_button">登录</button><br />
    </div>
</div>
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
            <img id="add_subimg" src="img/weikezi_cangqiongzhiguang_sub.jpg"/><br/>
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
<!--登录框-->

<!--第三栏首页导航栏-->
<div class="row" id="head_div_id_04">
    <div id="head_div_id_05" class="col-md-1 col-lg-1 col-xs-1 col-sm-1">
        <a class="head_a_class_03" href="#"><button id="head_button_id_02">首页</button></a>
    </div>
    <div class="col-md-10 col-lg-10 col-xs-10 col-sm-10">
        <ul id="head_ul_id_02">
            <a class="head_a_class_03" href="#">
                <li id="productListLi" class="head_li_class_01">商品列表</li>
            </a>
            <a class="head_a_class_03" href="#">
                <li class="head_li_class_01">活动专区</li>
            </a>
            <a class="head_a_class_03" href="#">
                <li class="head_li_class_01">限时折扣</li>
            </a>
            <a class="head_a_class_03" href="#">
                <li class="head_li_class_01">聚豆乐园</li>
            </a>
            <a class="head_a_class_03" href="#">
                <li class="head_li_class_01">周边商城</li>
            </a>
            <a class="head_a_class_03" href="#">
                <li id="userInfoLi" class="head_li_class_01">个人中心</li>
            </a>
        </ul>
    </div>
    <div class="col-md-1 col-lg-1 col-xs-1 col-sm-1">
        <a href='#'><button id="head_button_id_03"><span class="glyphicon glyphicon-shopping-cart"></span><span style="margin-left: 3%">购物车(<span id="number">0</span>)</span></button></a>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        
    })
</script>

