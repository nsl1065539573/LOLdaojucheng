$(function () {//页面加载以后执行的
    var productID;
   var productId = $.cookie("productId");
  $.post("/LOLdaojucheng/product/detail_protal/"+productId+"",function (result) {
      var status = result.data.status;
      if (status==5){
          $("#details_div_id_row").empty();
          $("#details_div_id_row_01").empty();
        $("#details_div_id_row").append("<div id='details_div_id_05'><img id='details_img_id_01' src='/LOLdaojucheng/"+result.data.subImages+"' />" +
            "                           <div id='details_div_id_02'><span id='details_span_id_01'>"+result.data.name+"  "+result.data.subtitle+"</span><br/><br/>" +
            "                           <div id='details_div_id_03'><span class='details_span_class_01'>Q币价:</span><span id='details_span_id_04'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+result.data.price+"</span><br/>" +
            "                           <span class='details_span_class_01'>"+"  "+"微信价:</span><span id='details_span_id_02'>&nbsp;&nbsp;&nbsp;&nbsp;"+((result.data.price*0.95).toFixed(2))+"</span><br/>" +
            "                           <span class='details_span_class_01'>"+"  "+"折后价:</span><span id='details_span_id_03'>&nbsp;&nbsp;&nbsp;￥"+((result.data.price*(result.data.rebate/10)).toFixed(2))+"</span>" +
            "                           <br/><br/> <button id='details_button_id_01' data-rebate='"+true+"' value='"+result.data.id+"'>加入购物车</button></div></div>" +
            "                           " +
            "</div> ");
          $("#details_div_id_row_01").append("<div id='div_details'>" +
              "                               <div style='margin-top: 15px'><span id='details_span_id_05'>商品详情</span>" +
              "                               <div style='font-size: 14px;color: gainsboro;display: inline-block'>////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////</div>" +
              "                               </div>" +
              "                               <span id='details_span_id_06'>"+result.data.detail+"</span>" +
              "                                </div>")
      }else {
          $("#details_div_id_row").empty();
          $("#details_div_id_row_01").empty();
          $("#details_div_id_row").append("<div id='details_div_id_05'><img id='details_img_id_01' src='/LOLdaojucheng/"+result.data.subImages+"' />" +
              "                           <div id='details_div_id_02'><span id='details_span_id_01'>"+result.data.name+"  "+result.data.subtitle+"</span><br/><br/>" +
              "                           <div id='details_div_id_03'><span class='details_span_class_01'>Q币价:</span><span id='details_span_id_04'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+result.data.price+"</span><br/>" +
              "                           <span class='details_span_class_01'>"+"  "+"微信价:</span><span id='details_span_id_02'>&nbsp;&nbsp;&nbsp;&nbsp;"+((result.data.price*0.95).toFixed(2))+"</span><br/>" +
              "                           <br/><br/> <button id='details_button_id_01' value='"+result.data.id+"'>加入购物车</button></div></div>" +
              "                           " +
              "</div>" );
          $("#details_div_id_row_01").append("<div id='div_details'>" +
              "                               <div style='margin-top: 15px'><span id='details_span_id_05'>商品详情</span>" +
              "                               <div style='font-size: 14px;color: gainsboro;display: inline-block'>////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////</div>" +
              "                               </div>" +
              "                               <span id='details_span_id_06'>"+result.data.detail+"</span>" +
              "                                </div>")
      }
  },"json");
  $.post("/LOLdaojucheng/product/selectHot",function (result) {
      $(result.data).each(function () {
        $("#details_div_id_04").append("<a class='detail_post_a_class_01' value="+this.id+">" +
            "<div class='details_post_div_class_01'>" +
            "<img class='details_post_img_class_01' src='/LOLdaojucheng/"+this.subImages+"'>" +
            "<div class='details_post_div_class_02'><span class='details_post_span_class_01'>"+this.name+this.subtitle+"<br/>Q币价："+this.price+"<br/>微信价："+(this.price-5)+"</span></div>" +
            "</div>" +
            "</a>")
      });
      $("#details_div_id_04").append("<div style='height: 25px'></div>")
  });
  $("#details_div_id_01").on("click","#details_button_id_01",function () {
      var rebate=$(this).attr("data-rebate");
      var productid = $(this).attr("value");
      productID = productid;
      $("#zhezhaoceng").css("visibility","visible");
      $.post("/LOLdaojucheng/product/details",{"productId":productid},function (result) {
          $("#add_subimg").attr("src","/LOLdaojucheng/"+result.data.subImages+"");
          $("#add_name").text(result.data.name+"  "+result.data.subtitle);
          if (!rebate)
              $("#price").text((result.data.price).toFixed(2));
          else
              $("#price").text(((result.data.price)*(result.data.rebate/10)).toFixed(2));
      });
      $.post("/LOLdaojucheng/user/hasLogin",function (result) {
          if (result.success){
              $("#add_div").css("visibility","visible");
          } else {
              $("#login_div").css("visibility","visible");
          }
      },"json");
  });

    //添加购物车
    $("#add_button").click(function () {
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
    });

    /***
     * 点击热门排行查看商品详情
     */
    $("#details_div_id_04").on("click",".detail_post_a_class_01",function () {

        var val = $(this).attr("value");

        $.cookie("productId",val,{expires: 7, path: "/" });
        $.post("/LOLdaojucheng/product/detail_protal/"+val+"",function (result) {

            var status = result.data.status;
            if (status==5){
                 $("#details_div_id_row").empty();
                $("#details_div_id_row_01").empty();
                $("#details_div_id_row").append("<div id='details_div_id_05'><img id='details_img_id_01' src='/LOLdaojucheng/"+result.data.subImages+"' />" +
                    "                           <div id='details_div_id_02'><span id='details_span_id_01'>"+result.data.name+"  "+result.data.subtitle+"</span><br/><br/>" +
                    "                           <div id='details_div_id_03'><span class='details_span_class_01'>Q币价:</span><span id='details_span_id_04'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+result.data.price+"</span><br/>" +
                    "                           <span class='details_span_class_01'>"+"  "+"微信价:</span><span id='details_span_id_02'>&nbsp;&nbsp;&nbsp;&nbsp;"+((result.data.price*0.95).toFixed(2))+"</span><br/>" +
                    "                           <span class='details_span_class_01'>"+"  "+"折后价:</span><span id='details_span_id_03'>&nbsp;&nbsp;&nbsp;￥"+((result.data.price*(result.data.rebate/10)).toFixed(2))+"</span>" +
                    "                           <br/><br/> <button id='details_button_id_01' data-rebate='"+true+"' value='"+result.data.id+"'>加入购物车</button></div></div>" +
                    "                           " +
                    "</div> ");
                $("#details_div_id_row_01").append("<div id='div_details'>" +
                    "                               <div style='margin-top: 15px'><span id='details_span_id_05'>商品详情</span>" +
                    "                               <div style='font-size: 14px;color: gainsboro;display: inline-block'>////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////</div>" +
                    "                               </div>" +
                    "                               <span id='details_span_id_06'>"+result.data.detail+"</span>" +
                    "                                </div>")
            }else {
                $("#details_div_id_row").empty();
                $("#details_div_id_row_01").empty();

                $("#details_div_id_row").append("<div id='details_div_id_05'><img id='details_img_id_01' src='/LOLdaojucheng/"+result.data.subImages+"' />" +
                    "                           <div id='details_div_id_02'><span id='details_span_id_01'>"+result.data.name+"  "+result.data.subtitle+"</span><br/><br/>" +
                    "                           <div id='details_div_id_03'><span class='details_span_class_01'>Q币价:</span><span id='details_span_id_04'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+result.data.price+"</span><br/>" +
                    "                           <span class='details_span_class_01'>"+"  "+"微信价:</span><span id='details_span_id_02'>&nbsp;&nbsp;&nbsp;&nbsp;"+((result.data.price*0.95).toFixed(2))+"</span><br/>" +
                    "                           <br/><br/> <button id='details_button_id_01' value='"+result.data.id+"'>加入购物车</button></div></div>" +
                    "                           " +
                    "</div>" );
                $("#details_div_id_row_01").append("<div id='div_details'>" +
                    "                               <div style='margin-top: 15px'><span id='details_span_id_05'>商品详情</span>" +
                    "                               <div style='font-size: 14px;color: gainsboro;display: inline-block'>////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////</div>" +
                    "                               </div>" +
                    "                               <span id='details_span_id_06'>"+result.data.detail+"</span>" +
                    "                                </div>");

            }
        },"json");
    });
});