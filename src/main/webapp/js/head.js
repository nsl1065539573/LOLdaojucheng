$(function () {
    var i02 = $("#head_input_id_01");
    var div_5 = $("#head_div_id_03");
    var category;
    var hasLogin = false;
    var isTrue;
    var in1 = $("#login_username");
    var in2 = $("#login_password");
    var codeLength=4;
    var code ;
    var word;
    var productId;


    $("#check_code").click(function () {
        create_code(codeLength);
    });
    $("#check").keyup(function () {
        var code_len = $("#check").val().trim().length;
        if (code_len < codeLength) {
        } else if (code_len > codeLength) {
            isTrue=false;
        } else {
            if (verify_code()) {
                isTrue=true;
            } else {
                isTrue=false;
            }
        }
    });
    $("#close_login_div").click(function () {
        $("#login_div").css("visibility","hidden");
        $("#zhezhaoceng").css("visibility","hidden");
    });
    function create_code(codeLength){
        //生成验证码
        code = "";
        var checkCode = $("#check_code");
        var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L',
            'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');

        for(var i=0;i<codeLength;i++){
            var charIndex = Math.floor(Math.random()*36);
            code +=selectChar[charIndex];
        }
        if(checkCode){
            checkCode.addClass("code");
            checkCode.val(code);
        }
    }
    $("#login_button").click(function () {

        if (isTrue){

            if (trim(in1.val())==null||trim(in1.val())===""){
                alert("请输入用户名");
                in1.focus();
                return false;
            }else if (/[^\u4e00-\u9fa5\w]/.test(in1.val())) {
                alert("用户名只能包含中文、英文、数字和下划线")
                in1.focus();
                return false;
            }
            if (trim(in2.val())==null||trim(in2.val())===""){
                alert("请输入密码");
                in1.focus();
                return false;
            }
            $.post("/LOLdaojucheng/user/login",{"username":in1.val(),"password":in2.val()},function (result) {
                if (result.success){
                    $("#login_div").css("visibility: hidden");
                    location.reload();
                } else {
                    alert("登录失败");
                }
            },"json");
        } else {
            alert("验证码错误");
        }
    });
    function verify_code (){
        //验证验证码
        var user_input_code = $("#check").val().toLowerCase().trim();
        if(user_input_code.length <=0){
            return false;
        } else if(user_input_code != code.toLowerCase()){
            return false;
        }
        return true;
    }
    function trim(str) { //删除左右两端的空格
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }
    $.post("/LOLdaojucheng/user/get_user_info",function (result) {
        if (result.success){
            productId = result.data.id;
            hasLogin = true;
            $.post("/LOLdaojucheng/Cart/get_cart_product_count.do",{"userId":result.data.id},function (result) {
                $("#number").text(result.data);
            });
        }
    });
    $.post("/LOLdaojucheng/user/haveSession",function (result) {
        if (result.success){
            $("#head_li_id_01").html("<li id='ll' style='margin-left: -50px' class='active'>欢迎您！" +
                "            <a class='a1' id='userInfo' href='#' style='margin-left:0px !important;color:grey;'>"+result.data.username+"</a>" +
                "        </li>");
            hasLogin = true;
        }
    },"json");
    $("#head_li_id_01").on("click","#userInfo",function () {
       window.location.href="/LOLdaojucheng/protalView/userInfo";
    });

    $("#head_button_id_03").click(function () {
        if (hasLogin){
            window.location.href="/LOLdaojucheng/protalView/cart";
        } else {
            $("#zhezhaoceng").css("visibility","visible");
            $("#login_div").css("visibility","visible");
        }
    });

    $("#userInfoLi").click(function () {
        window.location.href="/LOLdaojucheng/protalView/userInfo";
    });
    /***
     * 在搜索框输入  下拉界面
     */
    $("#head_input_id_01").keyup(function () {
        div_5.html("");
        word = i02.val();
        $.post("/LOLdaojucheng/product/mohuchaxun",{"word":word},
            function (result) {
                if (result.data==null||result.data==""){
                    div_5.css("visibility","hidden");
                }
                if (trim(word)!="") {

                    $(result.data).each(function () {
                        if (this.categoryId==1){
                            category = "【英雄】";
                        } else if (this.categoryId===2){
                            category = "【表情】";
                        } else if (this.categoryId===3){
                            category = "【皮肤】";
                        }else if (this.categoryId===4){
                            category = "【道具】";
                        }else if (this.categoryId===5){
                            category = "【守卫眼皮肤】";
                        }else if (this.categoryId===6){
                            category = "【道具包】";
                        }else {
                            category = "【其他】";
                        }
                        div_5.css("visibility","visible");
                        div_5.append("<div class='div_sousuo' value='"+this.id+"'>"+category+this.name+this.subtitle+"</div>")
                    })}
                else {
                    div_5.html("");
                    div_5.css("visibility","hidden");
                }

            },"json")
    });

    /***
     * 点击回到主页的按钮
     */
    $("#head_button_id_02").click(function () {
        window.location.href="/LOLdaojucheng/protalView/homepage"
    });
    //----------------------------------------------------
    /***
     * 点击搜索  进行搜索，并展示商品列表
     */

    $("#head_button_id_01").click(function () {
        div_5.html("");
        word = i02.val();
        if (word==""){
            alert("必须要输入才能进行搜索");
        } else {
            $.cookie("search_word",word);
            window.location.href = "/LOLdaojucheng/protalView/sousuo_list";
        }


    });
    $("#head_div_id_03").on("click",".div_sousuo",function () {
        var productId = $(this).attr("value");
        $.cookie("productId",productId,{expires: 7, path: "/" });
        window.location.href="/LOLdaojucheng/protalView/details";

    });
    $("body,html").click(function () {
        div_5.html("");
        div_5.css("visibility","hidden");
    });
    $("#login").click(function () {
        $("#zhezhaoceng").css("visibility","visible");
        $("#login_div").css("visibility","visible");
    });

    //超过一个商品以后，点击那个确定按钮
    $("#queding_button").click(function () {
        $("#zhezhaoceng").css("visibility","hidden");
        $("#chaoguo1").css("visibility","hidden");
        $("#add_div").css("visibility","hidden");
    });




    //继续购物或者去购物车
    $("#return_buy").click(function () {
        $("#zhezhaoceng").css("visibility","hidden");
        $("#queren_add").css("visibility","hidden");
    });
    $("#go_cart").click(function () {
        window.location.href="/LOLdaojucheng/protalView/cart";
    });

    //-----------------------------------------------

    //那几个叉号的点击事件
    $("#close-button").click(function () {
        $("#add_div").css("visibility","hidden");
        $("#zhezhaoceng").css("visibility","hidden");
    });

    $("#closer").click(function () {
        $("#zhezhaoceng").css("visibility","hidden");
        $("#queren_add").css("visibility","hidden");
    });
    $("#closer_1").click(function () {
        $("#zhezhaoceng").css("visibility","hidden");
        $("#chaoguo1").css("visibility","hidden");
        $("#add_div").css("visibility","hidden");
    });
    //----------------------------------------------
    $("#productListLi").click(function () {
        $.cookie("daohanglanID",0);
        window.location.href="/LOLdaojucheng/protalView/product_list";
    });

    $("#user_info_li").click(function () {
        window.location.href="/LOLdaojucheng/protalView/userInfo";
    });
    $("#orderLi").click(function () {
        window.location.href="/LOLdaojucheng/protalView/Order";
    })

});