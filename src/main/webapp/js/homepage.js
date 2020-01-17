$(function () {
    var div_1 = $("#div_1");
    var div_4 = $("#div_4");
    var i02 = $("#i02");
    var userInfo;
    var productID;
    var dayuyi = false;


    //获得登录用户信息
    $.post("/LOLdaojucheng/user/get_user_info",function (result) {
        if (result.success){
            userInfo = result.data;
           $("#d037").html(" <img src="+pageUrl+"'img/9.png' id='i04'>" +
               "             <p id='hasLoginP' class='s3'>欢迎您！" +
               "             <a id='a1'>"+result.data.username+"</a><br /> 购买超值商品！" +
               "                    </p>")
        }
    },"json");


    $("#div_5").css("visibility","hidden");
    function trim(str) { //删除左右两端的空格
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }

    /***
     * 打折商品的遍历与展示
     */
    $.post("/LOLdaojucheng/product/rebate",function (result) {

        $(result.data).each(function () {

            $("#dd015").append("<div class='div_2 col-md-3 col-lg-3 col-xs-3 col-sm-3'><div class='div_3'> <a href='#' class='aaa3' value="+this.id+"><img src='"+pageUrl+this.mainImage+"' class='i5'></a>" +
                                "<a href='#' class='aaa2' value="+this.id+"><p class='p2'>"+this.name+this.subtitle+"</p></a> " +
                                "<p class='p6'>原价： <span class='c1'>"+this.price+"Q币</span></p>" +
                                "<p class='p6'>折后价：<span class='c1'>"+(this.price*(this.rebate/10)).toFixed(2)+"Q币</span></p>" +
                                "<button class='b02' value='"+this.id+"' >立即购买</button></div></div>")
        })
    },"json");
//----------------------------------------------
    //添加购物车
    $("#add_button").click(function () {
        $.post("/LOLdaojucheng/Cart/get_quanti",{"productId":productID},function (result) {
            if (result.success){
                $.post("/LOLdaojucheng/Cart/add.do",{"productId":productID,"count":1},function (result1) {
                    if (result1.success){
                        $("#queren_add").css("visibility","visible");
                        $("#add_div").css("visibility","hidden");
                    }
                });
            } else {
                $("#chaoguo1").css("visibility","visible");
            }
        });
    });
    //点击那几个立即购买的按钮的事件
    //点击添加打折商品
    $("#dd015").on("click",".b02",function () {
        productID = $(this).attr("value");
        var productId = $(this).attr("value");
        $("#zhezhaoceng").css("visibility","visible");
        $.post("/LOLdaojucheng/product/details",{"productId":productId},function (result) {
            $("#add_subimg").attr("src","/LOLdaojucheng/"+result.data.subImages+"");
            $("#add_name").text(result.data.name+"  "+result.data.subtitle);
            $("#price").text((result.data.price*(result.data.rebate/10)).toFixed(2));
        });
        $.post("/LOLdaojucheng/user/hasLogin",function (result) {
            if (result.success){
                $("#add_div").css("visibility","visible");
            } else {
                $("#login_div").css("visibility","visible");
            }
        },"json");
    });
    $("#div_1").on("click",".b02",function () {
        productID = $(this).attr("value");
        $("#zhezhaoceng").css("visibility","visible");
        $.post("/LOLdaojucheng/product/details",{"productId":productID},function (result) {
            $("#add_subimg").attr("src",result.data.subImages);
            $("#add_name").text(result.data.name+"  "+result.data.subtitle);
            $("#price").text(result.data.price);
        });
        $.post("/LOLdaojucheng/user/hasLogin",function (result) {
            if (result.success){
                $("#add_div").css("visibility","visible");
            } else {
                $("#login_div").css("visibility","visible");
            }
        },"json");
    });
    //-----------------------------------------------------




    //这个模块完了



    $.post("/LOLdaojucheng/product/selectHot",function (result) {
        $(result.data).each(function () {
            $("#div_4").append("<a class='aaa1' href='#' value="+this.id+">" +
                "<div class='d043'>" +
                "<img class='i6' src='"+pageUrl+this.subImages+"'>" +
                "<div class='d044'><span class='s5'>"+this.name+this.subtitle+"<br/>Q币价："+this.price+"<br/>微信价："+(this.price-5)+"</span></div>" +
                "</div>" +
                "</a>");
        })
    },"json");


    $("#div_4").on("click",".aaa1",function () {
        var value = $(this).attr("value");
        $.cookie("productId",value,{expires: 7, path: "/" });
        window.location.href="/LOLdaojucheng/protalView/details";
    });
    $.post("/LOLdaojucheng/product/shouban",function (result) {
        $(result.data).each(function () {
            $("#d028").append("<a class='shouban_a' >" +
                "                <div class='d050'>" +
                "                    <a  value="+this.id+" ><img class='i8' value='"+this.id+"' src='"+pageUrl+this.mainImage+"'><br />" +
                "                        <span class='s7' value="+this.id+" >"+this.name+"  "+this.subtitle+"</span>" +
                "                        <span class='s8' value='"+this.id+"'>￥"+this.price.toFixed(2)+"</span></a>" +
                "                </div></a>")
        })
    },"json");
    $("#d028").on("click",".i8,.s7,.s8",function () {
        var value = $(this).attr("value");
        $.cookie("productId",value,{expires: 7, path: "/" });
        window.location.href="/LOLdaojucheng/protalView/details";
    });

    $.post("/LOLdaojucheng/product/hotproduct",function (result) {
        $(result.data).each(function () {
            $("#div_1").append("<div class='d041'><a class='hotProduct' value="+this.id+" ><img value='"+this.id+"' src='"+pageUrl+this.mainImage+"' class='i5'></a>" +
                "<a><p class='p2' value="+this.id+" >"+this.name+this.subtitle+"</p></a> " +
                "<p class='p6'>Q币价： <span class='c1'>"+this.price+"Q币</span></p>" +
                "<p class='p6'>微信价：<span class='c1'>"+(this.price-5)+"元</span></p>" +
                " <button class='b02' value='"+this.id+"'>立即购买</button></div>")
        })
    },"json");
    $("#div_1").on("click",".i5,.p2",function () {
        var value = $(this).attr("value");
        $.cookie("productId",value,{expires: 7, path: "/" });
        window.location.href="/LOLdaojucheng/protalView/details";
    })

    
});
