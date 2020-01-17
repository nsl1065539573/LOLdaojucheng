$(function () {

    function trim(str) { //删除左右两端的空格
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }
    $.post("${pageContext.request.contextPath}/product/rebate",function (result) {
        $(result.data).each(function () {
            $("#dd015").append("<div class='div_2 col-md-3 col-lg-3 col-xs-3 col-sm-3'><div class='div_3'> <a href='#'><img src='"+this.mainImage+"' class='i5'></a>" +
                "<a href='#'><p class='p2'>"+this.name+this.subtitle+"</p></a> " +
                "<p class='p6'>原价： <span class='c1'>"+this.price+"Q币</span></p>" +
                "<p class='p6'>折后价：<span class='c1'>"+(this.price*(this.rebate/10)).toFixed(2)+"Q币</span></p>" +
                " <a class='a9' href='#'><button class='b02'>立即购买</button></a></div></div>")
        })
    },"json")
    $.post("../product/selectHot",function (result) {
        $(result.data).each(function () {
            $("#div_4").append("<a href='#'>" +
                "<div class='d043'>" +
                "<img class='i6' src='"+this.mainImage+"'>" +
                "<div class='d044'><span class='s5'>"+this.name+   +this.subtitle+"<br/>Q币价："+this.price+"<br/>微信价："+(this.price-5)+"</span></div>" +
                "</div>" +
                "</a>");
        })
    },"json")
    $.post("../product/hotproduct",function (result) {
        $(result.data).each(function () {
            $("#div_1").append("<div class='d041'><a href='#'><img src='"+this.mainImage+"' class='i5'></a>" +
                "<a href='#'><p class='p2'>"+this.name+this.subtitle+"</p></a> " +
                "<p class='p6'>Q币价： <span class='c1'>"+this.price+"Q币</span></p>" +
                "<p class='p6'>微信价：<span class='c1'>"+(this.price-5)+"元</span></p>" +
                " <a class='a9' href='#'><button class='b02'>立即购买</button></a>")
        })
    },"json");
    $("#b1").click(function () {
        var word = $("#i02").val();
        $.post("LOLdaojucheng/product/mohuchaxun",{"word":word},
            function (result) {
                alert(word);
            },"json")
    });
    $.ajax()
    $("#i02").keyup(function () {
        var word = $("#i02").val();
        $.post("../product/mohuchaxun",{"word":word},
            function (result) {
                if (trim(word)!="") (
                    $(result.data).each(function () {
                        $("#div_5").css("visibility","visible");
                        $("#div_5").html("<div>"+this.name+this.subtitle+"</div>")


                    }))
                else {
                    $("#div_5").css("visibility","hidden");
                }

            },"json")
    });
    $("#i02").blur(function () {
        $("#div_5").css("visibility","hidden");
    })

});