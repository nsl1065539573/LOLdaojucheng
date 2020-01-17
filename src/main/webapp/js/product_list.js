$(function () {
    var id = $.cookie("daohanglanID");
    var total;//从数据库查询出的总条数
    var pageNumber = 1;//记录现在是第几页
    var bigPageNum;//最大页码
    var ajax_url="/LOLdaojucheng/product/list_protal";
    var hasId;
    var productID;
    if (id==0){
        hasId = false;
    } else
        hasId = true;
    var data;

    if (id==0){
        $("#daohanglan_button_id_01").addClass("daohanglanbianse");
    } else if (id==null){
        $.cookie("daohanglanID",0);
        $("#daohanglan_button_id_01").addClass("daohanglanbianse");
        id = 0;
    }else {

    }





    /***
     * 加载导航栏,并且给被选中的导航栏元素变色
     */

        $.post("/LOLdaojucheng/category/selectAll",
            function (result) {
                $(result.data).each(function () {
                    //如果是这个品类的话，就变色
                    if (this.id == id)
                        $("#daohanglan_ul_id_01").append("<a class='a3' value='" + this.id + "' ><li style='display: inline-block'  value=" + this.id + " class='daohanglanbianse l3'>" + this.name + "</li></a>");
                    //如果不是，就不变色
                    else
                        $("#daohanglan_ul_id_01").append("<a class='a3' value='" + this.id + "' ><li style='display: inline-block ' value=" + this.id + " class='l3'>" + this.name + "</li></a>");

                });
            }, "json");

    /***
     * 点击导航栏，执行的事件
     */
    $("#daohanglan_ul_id_01").on("click",".l3",function () {
        $.cookie("daohanglanID",$(this).attr("value"));
        var categoryId = $(this).attr("value");
        $("li").removeClass("daohanglanbianse");
        $("#daohanglan_button_id_01").removeClass("daohanglanbianse");
        $(this).addClass("daohanglanbianse");
        $.post(ajax_url,{"categoryId":categoryId,"pageNum":1,"pageSize":8},function (result) {
            $("#list_div_id_02").html("");
            $(result.data.list).each(function () {
                $("#list_div_id_02").append("<div class='details_div'><a ><img data-productid='"+this.id+"' src='/LOLdaojucheng/"+this.mainImage+"' class='productMainImage'></a>" +
                    "                <a><p data-productid='"+this.id+"' class='productName'>"+this.name+this.subtitle+"</p></a>" +
                    "                <p class='price_Q'>Q币价： <span class='price_Qb'>"+this.price+"Q币</span></p>" +
                    "                <p class='price_W'>微信价：<span class='price_Wx'>"+(this.price-5)+"元</span></p>" +
                    "                <button data-productId='"+this.id+"' class='buy_button'>立即购买</button>" +
                    "            </div>");
            });
            bigPageNum = Math.ceil(result.data.total/8);
            if (bigPageNum==1){
                $("#list_ul").html("");
                $("#list_ul").append(" <li id='previous' class='shubiao'><a>&laquo;</a></li>" +
                    "                <li id='firstPage' class='shubiao'><a>1</a></li>" +
                    "                <li id='next'  class='shubiao'><a>&raquo;</a></li>")
            }else {
                $("#list_ul").html("");
                for (var i = 1; i <= bigPageNum; i++) {
                    if (i == 1) {
                        $("#list_ul").append(" <li id='previous' class='shubiao'><a>&laquo;</a></li>" +
                            "                <li id='firstPage' class='shubiao'><a>1</a></li>")
                    } else if (i == result.data.pages) {
                        $("#list_ul").append("<li id='lastPage' value='" + i + "' class='shubiao'><a>" + i + "</a></li>" +
                            "                <li id='next'  class='shubiao'><a>&raquo;</a></li>")
                    } else {
                        $("#list_ul").append("<li value='" + i + "' ' class='list_li_click shubiao'><a>" + i + "</a></li>");
                    }
                }
            }
        });
    });
    /***
     * 点击全部分类执行的事件
     */
    $("#daohanglan_button_id_01").click(function () {
       $(this).addClass("daohanglanbianse");
       $("li").removeClass("daohanglanbianse");
       $.cookie("daohanglanID",0);
       id = 0;
       $.post(ajax_url,{"categoryId":id,"pageNum":1},function (result) {
           $("#list_div_id_02").html("");
           $(result.data.list).each(function () {
               $("#list_div_id_02").append("<div class='details_div'><a><img data-productid='"+this.id+"' src='/LOLdaojucheng/"+this.mainImage+"' class='productMainImage'></a>" +
                   "                <a><p data-productid='"+this.id+"' class='productName'>"+this.name+this.subtitle+"</p></a>" +
                   "                <p class='price_Q'>Q币价： <span class='price_Qb'>"+this.price+"Q币</span></p>" +
                   "                <p class='price_W'>微信价：<span class='price_Wx'>"+(this.price-5)+"元</span></p>" +
                   "                <button data-productId='"+this.id+"' class='buy_button'>立即购买</button>" +
                   "            </div>");
           });
           bigPageNum = result.data.pages;
           if (bigPageNum==1){
               $("#list_ul").html("");
               $("#list_ul").append(" <li id='previous' class='shubiao'><a>&laquo;</a></li>" +
                   "                <li id='firstPage' class='shubiao'><a>1</a></li>" +
                   "                <li id='next'  class='shubiao'><a>&raquo;</a></li>");

           } else {
               $("#list_ul").html("");
               for (var i = 1; i <= result.data.pages; i++) {
                   if (i == 1) {
                       $("#list_ul").append(" <li id='previous' class='shubiao'><a>&laquo;</a></li>" +
                           "                <li id='firstPage' class='shubiao'><a>1</a></li>")
                   } else if (i == result.data.pages) {
                       $("#list_ul").append("<li id='lastPage' value='" + i + "' class='shubiao'><a>" + i + "</a></li>" +
                           "                <li id='next'  class='shubiao'><a>&raquo;</a></li>")
                   } else {
                       $("#list_ul").append("<li value='" + i + "' ' class='list_li_click shubiao'><a>" + i + "</a></li>");
                   }
               }
           }
       });
    });
    $.post(ajax_url,{"categoryId":id,"pageNum":1},function (result) {
                    $(result.data.list).each(function () {
                        $("#list_div_id_02").append("<div class='details_div'><a><img data-productid='"+this.id+"' src='/LOLdaojucheng/"+this.mainImage+"' class='productMainImage'></a>" +
                            "                <a ><p data-productid='"+this.id+"' class='productName'>"+this.name+this.subtitle+"</p></a>" +
                            "                <p class='price_Q'>Q币价： <span class='price_Qb'>"+this.price+"Q币</span></p>" +
                            "                <p class='price_W'>微信价：<span class='price_Wx'>"+(this.price-5)+"元</span></p>" +
                            "                <button data-productId='"+this.id+"' class='buy_button'>立即购买</button>" +
                            "            </div>");
                    });
                    bigPageNum = result.data.pages;
                    if (bigPageNum==1){
                        $("#list_ul").html("");
                        $("#list_ul").append(" <li id='previous' class='shubiao'><a>&laquo;</a></li>" +
                            "                <li id='firstPage' class='shubiao'><a>1</a></li>" +
                            "                <li id='next'  class='shubiao'><a>&raquo;</a></li>");

                    } else {
                        $("#list_ul").html("");
                        for (var i = 1; i <= result.data.pages; i++) {
                            if (i == 1) {
                                $("#list_ul").append(" <li id='previous' class='shubiao'><a>&laquo;</a></li>" +
                                    "                <li id='firstPage' class='shubiao'><a>1</a></li>")
                            } else if (i == result.data.pages) {
                                $("#list_ul").append("<li id='lastPage' value='" + i + "' class='shubiao'><a>" + i + "</a></li>" +
                                    "                <li id='next'  class='shubiao'><a>&raquo;</a></li>")
                            } else {
                                $("#list_ul").append("<li value='" + i + "' ' class='list_li_click shubiao'><a>" + i + "</a></li>");
                            }
                        }
                    }
            });
    /***
     * 点击分页按钮进行的操作
     */
    $("#list_ul").on("click",".list_li_click",function () {
        var pageNum = $(this).attr("value");
        id = $.cookie("daohanglanID");
        pageNumber = pageNum;
            $.post(ajax_url,{"categoryId":id,"pageNum":pageNum},function (result) {
                $("#list_div_id_02").html("");
                $(result.data.list).each(function () {
                    $("#list_div_id_02").append("<div class='details_div'><a><img data-productid='"+this.id+"' src='/LOLdaojucheng/"+this.mainImage+"' class='productMainImage'></a>" +
                        "                <a><p data-productid='"+this.id+"' class='productName'>"+this.name+this.subtitle+"</p></a>" +
                        "                <p class='price_Q'>Q币价： <span class='price_Qb'>"+this.price+"Q币</span></p>" +
                        "                <p class='price_W'>微信价：<span class='price_Wx'>"+(this.price-5)+"元</span></p>" +
                        "                <button data-productId='"+this.id+"' class='buy_button'>立即购买</button>" +
                        "            </div>");
                });
            });

    });
    /***
     * 点击上一页执行的操作
     */
    $("#list_ul").on("click","#previous",function () {
        id = $.cookie("daohanglanID");
        if (pageNumber==1){
            event.preventDefault();
        }else {
            pageNumber = pageNumber-1;
            $.post(ajax_url,{"categoryId":id,"pageNum":pageNumber},function (result) {
                $("#list_div_id_02").html("");
                $(result.data.list).each(function () {
                    $("#list_div_id_02").append("<div class='details_div'><a><img data-productid='"+this.id+"' src='/LOLdaojucheng/" + this.mainImage + "' class='productMainImage'></a>" +
                        "                <a ><p data-productid='"+this.id+"' class='productName'>" + this.name + this.subtitle + "</p></a>" +
                        "                <p class='price_Q'>Q币价： <span class='price_Qb'>" + this.price + "Q币</span></p>" +
                        "                <p class='price_W'>微信价：<span class='price_Wx'>" + (this.price - 5) + "元</span></p>" +
                        "                <button data-productId='"+this.id+"' class='buy_button'>立即购买</button>" +
                        "            </div>");
                });
            });
        }
    });
    /***
     * 点击执行下一页操作
     */
    $("#list_ul").on("click","#next",function () {
        id = $.cookie("daohanglanID");
        if (pageNumber==bigPageNum){
            event.preventDefault();
        } else {
            pageNumber = pageNumber+1;

            $.post(ajax_url,{"categoryId":id,"pageNum":pageNumber},function (result) {
                $("#list_div_id_02").html("");
                $(result.data.list).each(function () {
                    $("#list_div_id_02").append("<div class='details_div'><a><img data-productid='"+this.id+"' src='/LOLdaojucheng/" + this.mainImage + "' class='productMainImage'></a>" +
                        "                <a><p data-productid='"+this.id+"' class='productName'>" + this.name + this.subtitle + "</p></a>" +
                        "                <p class='price_Q'>Q币价： <span class='price_Qb'>" + this.price + "Q币</span></p>" +
                        "                <p class='price_W'>微信价：<span class='price_Wx'>" + (this.price - 5) + "元</span></p>" +
                        "                <button data-productId='"+this.id+"' class='buy_button'>立即购买</button>" +
                        "            </div>");
                });
            });
        }
    });
    /***
     * 点击进入第一页
     */
    $("#list_ul").on("click","#firstPage",function () {
        id = $.cookie("daohanglanID");
        pageNumber = 1;
        $.post(ajax_url,{"categoryId":id,"pageNum":1},function (result) {
            $("#list_div_id_02").html("");
            $(result.data.list).each(function () {
                $("#list_div_id_02").append("<div class='details_div'><a ><img data-productid='"+this.id+"' src='/LOLdaojucheng/" + this.mainImage + "' class='productMainImage'></a>" +
                    "                <a ><p data-productid='"+this.id+"' class='productName'>" + this.name + this.subtitle + "</p></a>" +
                    "                <p class='price_Q'>Q币价： <span class='price_Qb'>" + this.price + "Q币</span></p>" +
                    "                <p class='price_W'>微信价：<span class='price_Wx'>" + (this.price - 5) + "元</span></p>" +
                    "                <button data-productId='"+this.id+"' class='buy_button'>立即购买</button>" +
                    "            </div>");
            });
        });
    });
    /***
     * 点击进入最后一页
     */
    $("#list_ul").on("click","#lastPage",function () {
        id = $.cookie("daohanglanID");
        var pageNum = $(this).attr("value");
        pageNumber = pageNum;
        $.post(ajax_url,{"categoryId":id,"pageNum":pageNum},function (result) {
            $("#list_div_id_02").html("");
            $(result.data.list).each(function () {
                $("#list_div_id_02").append("<div class='details_div'><a ><img src='/LOLdaojucheng/" + this.mainImage + "' data-productid='"+this.id+"' class='productMainImage'></a>" +
                    "                <a ><p data-productid='"+this.id+"' class='productName'>" + this.name + this.subtitle + "</p></a>" +
                    "                <p class='price_Q'>Q币价： <span class='price_Qb'>" + this.price + "Q币</span></p>" +
                    "                <p class='price_W'>微信价：<span class='price_Wx'>" + (this.price - 5) + "元</span></p>" +
                    "                <button data-productId='"+this.id+"' class='buy_button'>立即购买</button>" +
                    "            </div>");
            });
        });
    });
    //点击进入商品详情
    $("#list_div_id_02").on("click",".productName,.productMainImage",function () {
        var productId = $(this).attr("data-productid");
        $.cookie("productId",productId,{expires: 7, path: "/" });
        window.location.href="/LOLdaojucheng/protalView/details";
    })
    /***
     * 热门排行那一列
     */
   $.post("/LOLdaojucheng/product/selectHot",function (result) {
       $(result.data).each(function () {
           $("#list_div_id_01").append("<a class='hotDetails' value='"+this.id+"'  style='width: 100%'>" +
               "                <div style='width: 100%'>" +
               "               <img class='list_img_class_01' src='/LOLdaojucheng/"+this.subImages+"'>" +
               "                <div class='list_div_class_01'><span>"+this.name+this.subtitle+"<br/>Q币价："+this.price+"<br/>微信价："+(this.price-5)+"</span></div>" +
               "                </div>" +
               "                </a>");
       },"json");

       });

    /***
     * 点击进入商品详情
     */
    //热门排行那一列进入商品详情
    $("#list_div_id_01").on("click",".hotDetails",function () {
        var value  = $(this).attr("value");
        $.cookie("productId",value);
        window.location.href="/LOLdaojucheng/protalView/details";
    })
    //加入购物车
    $("#list_div_id_02").on("click",".buy_button",function () {
        $("#zhezhaoceng").css("visibility","visible");
        $("#add_div").css("visibility","visible");
        var productId = $(this).attr("data-productId");
        productID = productId;
        $.post("/LOLdaojucheng/product/details",{"productId":productId},function (result) {
            $("#add_subimg").attr("src","/LOLdaojucheng/"+result.data.subImages+"");
            $("#add_name").text(result.data.name+"  "+result.data.subtitle);
            $("#price").text((result.data.price).toFixed(2));
            $.post("/LOLdaojucheng/user/hasLogin",function (result) {
                if (result.success){
                    $("#add_div").css("visibility","visible");
                } else {
                    $("#login_div").css("visibility","visible");
                }
            },"json");
        })
    })
    //确定加入购物车
    $("#add_button").click(function () {
        alert(111);
        $.post("/LOLdaojucheng/Cart/get_quanti",{"productId":productID},function (result) {
            if (result.success){
                $.post("/LOLdaojucheng/Cart/add.do",{"productId":productID,"count":1},function (result1) {
                    if (result1.success){
                        $.post("/LOLdaojucheng/Cart/get_cart_product_count.do",function (result2) {
                            $("#number").html(result2.data);
                        });
                        $("#queren_add").css("visibility","visible");
                        $("#add_div").css("visibility","hidden");
                    }
                });
            } else {
                $("#chaoguo1").css("visibility","visible");
            }
        });
    })
});