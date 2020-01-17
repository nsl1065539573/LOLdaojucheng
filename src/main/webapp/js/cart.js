$(function () {
    var userId;//用户Id
    var leixing;//道具的类型 皮肤或者是其他的啥
    var youhui = "暂无优惠";//优惠信息
    var price;//遍历时商品的价格
    var hadShipping;//是否有地址
    var checkedId;
    var checkedShippingId;
    var shippingId;//记录地址Id

    /***
     * 查询用户的所有地址
     */
   function selectAllShipping(){
        $.post("/LOLdaojucheng/shipping/selectAllByUserId",function (result) {
            $(result.data).each(function () {
                if (this.checked==1){
                    checkedId = this.id;
                    checkedShippingId = this.id;
                    $("#details_list").append("<button value='"+this.id+"' style='border: #F74A4A 3px dashed !important;' value='"+this.id+"' class='shipping_details'>" +
                        "                    <p>"+this.receiverCity+"</p>" +
                        "                        <p>"+this.receiverDistrict+"</p>" +
                        "                        <p>"+this.receiverAddress+"</p>"+
                        "                </button>");
                }else {
                    $("#details_list").append("<button value='"+this.id+"' class='shipping_details'>" +
                        "                    <p>"+this.receiverCity+"</p>" +
                        "                        <p>"+this.receiverDistrict+"</p>" +
                        "                        <p>"+this.receiverAddress+"</p>"+
                        "                </button>");
                }
            });
        });
    }
    selectAllShipping();
    /***
     * 查询用户的收货地址信息
     */
    update();
    $("#bangdingjuese").click(function () {
        if (hadShipping==1){
            $("#shipping_list").css("visibility","visible");
            $("#zhezhaoceng").css("visibility","visible");
        }else if (hadShipping==2){
            $("#shipping_list").css("visibility","visible");
            $("#zhezhaoceng").css("visibility","visible");
        }
        else {
            $("#zhezhaoceng").css("visibility","visible");
            $("#shipping").css("visibility","visible");
        }
    });
    
    /***
     * 点击地址的事件
     */
    $("#details_list").on("click",".shipping_details",function () {
        checkedShippingId = $(this).attr("value");
        $(".shipping_details").css("border"," #DDDDDD 3px dashed ");
        $(this).css("border","3px #E43333 dashed");
        $("[value="+checkedId+"]").css("border","3px #F74A4A dashed");
    });
    /***
     * 点击选中被选中的地址
     */
    $("#revokeChecked").click(function () {
        $.post("/LOLdaojucheng/shipping/changeChecked",{"id":checkedShippingId},function (result) {
            if (result.success){
                alert(result.msg);
                //刷新这一部分，将新改的默认地址变色
                bianse();
                //更新购物车主页的地址信息
               update()
            }
        });
    });

    /***
     * 更改地址信息
     */
    $("#changeShippingInfo").click(function () {
        $("#shipping_list").css("visibility","hidden");
        $("#changeShippingInfoDiv").css("visibility","visible");
    });
    $("#updateShipping").click(function () {
        var gameName =$("#changeGameName").val();
        var netName = $("#changeNetName").val();
        var daquName = $("#changeDaquName").val();
        var jueseName = $("#changeJueseName").val();
        var phoneNum = $("#changePhoneNum").val();
        var nameName = $("#changeNameName").val();
        if (gameName==""||netName==""||daquName==""||jueseName==""||phoneNum==""||nameName==""){
            alert("需要输入完整字段");
            return false;
        }
        $.post("/LOLdaojucheng/shipping/updateShipping",{"id":checkedShippingId,"receiverProvince":gameName,"receiverMobile":phoneNum,"receiverName":nameName,"receiverCity":netName,"receiverDistrict":daquName,"receiverAddress":jueseName},function (result) {
            if (result.success){
                alert("更改地址信息成功");
                bianse();
                update();
                $("#changeShipping").css("visibility","hidden");
                $("#shipping_list").css("visibility","visible");
            } else {
                alert(result.msg);
            }
        });
    });

    /***
     * 删除地址信息
     */
    $("#deleteShipping").click(function () {
        $.post("/LOLdaojucheng/shipping/deleteAddress",{"id":checkedShippingId},function (result) {
            if (result.success){
                bianse();
                update();
            } else {
                alert(result.msg);
            }
        });
    });

    /***
     * 添加地址信息
     */
    $("#addShipping").click(function () {
        $("#shipping_list").css("visibility","hidden");
        $("#shipping").css("visibility","visible");
    });
    function update() {
        $.post("/LOLdaojucheng/shipping/selectAllByUserId",function (res) {
            $.post("/LOLdaojucheng/shipping/selectByUserId",function (result) {
                if (result.data!=null){
                    hadShipping = 1;
                    $("#bangding_span").text(result.data.receiverCity+"   "+result.data.receiverDistrict+"   "+result.data.receiverAddress);
                    $("#bangdingjuese").text("更改角色");
                }else if (result.data==null&&res.data!=null){
                    hadShipping = 2;
                    $("#bangding_span").text("您还没有绑定角色哦");
                    $("#bangdingjuese").text("管理角色");
                }else {

                }
            });
        });

    }
    function bianse(){
        $.post("/LOLdaojucheng/shipping/selectAllByUserId",function (result) {
            $("#details_list").html("");
            $(result.data).each(function () {
                if (this.checked==1){
                    checkedId = this.id;
                    $("#details_list").append("<button value='"+this.id+"' style='border: #F74A4A 3px dashed !important;' value='"+this.id+"' class='shipping_details'>" +
                        "                    <p>"+this.receiverCity+"</p>" +
                        "                        <p>"+this.receiverDistrict+"</p>" +
                        "                        <p>"+this.receiverAddress+"</p>"+
                        "                </button>");
                }else {
                    $("#details_list").append("<button value='"+this.id+"' class='shipping_details'>" +
                        "                    <p>"+this.receiverCity+"</p>" +
                        "                        <p>"+this.receiverDistrict+"</p>" +
                        "                        <p>"+this.receiverAddress+"</p>"+
                        "                </button>");
                }
            });
        });
    }
    /***
     *
     */
    //点击绑定按钮
    $("#bangding").click(function () {
        var gameName =$("#gameName").val();
        var netName = $("#netName").val();
        var daquName = $("#daquName").val();
        var jueseName = $("#jueseName").val();
        var phoneNum = $("#phoneNum").val();
        var nameName = $("#nameName").val();
        if (gameName==""||netName==""||daquName==""||jueseName==""||phoneNum==""||nameName==""){
            alert("需要输入完整字段");
            return false;
        }

        $.post("/LOLdaojucheng/shipping/add_shipping",{"receiverProvince":gameName,"receiverMobile":phoneNum,"checked":1,"receiverName":nameName,"receiverCity":netName,"receiverDistrict":daquName,"receiverAddress":jueseName},function (result) {
            if (result.success){
                $("#zhezhaoceng").css("visibility","hidden");
                $("#shipping").css("visibility","hidden");
                alert("添加地址成功");
                update();
                bianse();
            }
        });
    });
    //点击关闭按钮 关闭绑定角色窗口
    $("#close_shipping").click(function () {
        $("#zhezhaoceng").css("visibility","hidden");
        $("#shipping").css("visibility","hidden");
    });
    $("#close_shipping_list").click(function () {
        $("#zhezhaoceng").css("visibility","hidden");
        $("#shipping_list").css("visibility","hidden");
    });
    $("#close_changeShipping").click(function () {
        $("#zhezhaoceng").css("visibility","hidden");
        $("#changeShippingInfoDiv").css("visibility","hidden");
    });

    $.post("/LOLdaojucheng/user/get_user_info",function (result) {
        if (result.success)
            userId = result.data.id;
    });
    function cartList(){
        $.post("/LOLdaojucheng/Cart/list.do",function (result) {
            var ischeck;
            if (result.success) {
                $(result.data.cartProductVOList).each(function () {

                    if (this.productCategoryId == 1)
                        leixing = "英雄";
                    else if (this.productCategoryId == 2)
                        leixing = "表情";
                    else if (this.productCategoryId == 3)
                        leixing = "皮肤";
                    else if (this.productCategoryId == 4)
                        leixing = "道具";
                    else if (this.productCategoryId == 5)
                        leixing = "守卫眼皮肤";
                    else if (this.productCategoryId == 6)
                        leixing = "道具包";
                    else
                        leixing = "其他";
                    if (this.productStatus == 5) {
                        youhui = "限时折扣";
                        price = (this.productTotalPrice * (this.rebate / 10)).toFixed(2) * this.quantity;
                    } else
                        price = this.productTotalPrice * this.quantity;
                    if (this.productChecked == 0)
                        ischeck = false;
                    if (this.productChecked == 1)
                        ischeck = true;
                    $("#append_cart").append("<div data-productId='"+this.productId+"' class='d22'>" +
                        "                <input class='i4'  data-productId='" + this.productId + "' checked="+ischeck+" data-price='" + price + "'type='checkbox' class='checkbox' >&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "                <a href='#'><img src='/LOLdaojucheng/" + this.productSubImages + "' class='i3'></a>&nbsp;&nbsp;" +
                        "                <span class='p10'><a class='a11' href='#'>" + this.productName + "</a>&nbsp;&nbsp;<a class='a11' href='#'>" + this.productSubtitle + "</a></span>" +
                        "                <span class='p11'>" + leixing + "</span>" +
                        "                <span class='p17'>" + this.productPrice + "&nbsp;&nbsp;Q币</span>" +
                        "                <span class='p18'>永久</span>" +
                        "                <span class='p19'><button class='b5'>-</button><input class='i5'  type='text' value=" + this.quantity + "><button class='b6'>+</button></span>" +
                        "                <span class='p20'>" + youhui + "</span>" +
                        "                <span class='p21'>" + price + "&nbsp;&nbsp;Q币</span>" +
                        "                <span class='p22'><a class='a7' data-productId='"+this.productId+"' href='#'>删除</a></span>" +
                        "            </div>");

                    $("[data-productId="+this.productId+"]").prop("checked",ischeck);
                });


                if (result.data.allChecked) {
                    $("#allCheck").attr("checked", true);
                }
                var totalPrice = 0;//总价
                var price = 0;//用户存储单个条目复选框的价格
                //获取所有被勾选的条目复选框  循环遍历
                $(".i4").each(function () {
                    var isChecked = $(this).prop("checked");
                    if (isChecked) {
                        //把这个价钱存储到变量price中
                        price = $(this).attr("data-price");
                        //加到总价里
                        totalPrice += Number(price);
                    }
                })
                //遍历完成以后，总价已经计算出来了，显示到总价上
                $("#totalPrice").text(totalPrice);
            }
        });
    }
    cartList();
    //点击加减数量的事件
    //点击减号，根据道聚城的模式，弹框不能再减了
    $("#append_cart").on("click",".b5",function () {
        alert("不能再减了！");
    });
    //点击加号，不能再加了
    $("#append_cart").on("click",".b6",function () {
        alert("对不起，每件商品限购一件！");
    });

    //点击删除，购物车中将此商品移除
    $("#append_cart").on("click",".a7",function (result) {
       var productId = $(this).attr("data-productId");
       $.post("/LOLdaojucheng/Cart/delectProducts.do",{"productIds":productId},function (result) {
           if (result.success){
               window.location.reload();
           }
       });
    });
    //手动写入商品数量
    $("#append_cart").on("change",".i5",function () {
        var quantity = $(this).val();
        if (quantity>=2)
            alert("对不起，每次每件商品只能购买一件");
        $(this).val(1);
    })
    //点击全选以后的事件
    $("#allCheck").click(function () {
        var check = $("#allCheck").prop("checked");
        $(".i4").prop("checked",check);
        if (check){
           $.post("/LOLdaojucheng/Cart/select_all.do",function () {

           });
           showTotal();
        } else {
            $.post("/LOLdaojucheng/Cart/un_select_all.do", function (result) {

            });
            $("#totalPrice").text(0);
        }
    });
    /***
     * 点击条目框
     */
    $("#append_cart").on("click",".i4",function () {
        var subs = $(".i4");
        $("#allCheck").prop("checked",subs.length == subs.filter(":checked").length?true:false);
        
        if ($(this).prop("checked")){
            $.post("/LOLdaojucheng/Cart/select.do",{"productId":$(this).attr("data-productId")},function (result) {

            })
        }else {
            $.post("/LOLdaojucheng/Cart/un_select.do",{"productId":$(this).attr("data-productId")},function (result) {
                
            });
        }
        showTotal();
    }
    );

    /***
     * 计算总价的方法
     */
    function showTotal(){
        var totalPrice = 0;//总价
        var price=0;//用户存储单个条目复选框的价格
        //获取所有被勾选的条目复选框  循环遍历
        $(".i4").each(function () {
            var isChecked = $(this).prop("checked");
            if (isChecked){
                //把这个价钱存储到变量price中
                price = $(this).attr("data-price");
                //加到总价里
                totalPrice += Number(price);
            }
        });
        //遍历完成以后，总价已经计算出来了，显示到总价上
        $("#totalPrice").text(totalPrice);
    }

    /***
     * 点击结算按钮
     *
     */
    $("#jiesuan").click(function () {
        var totalPrice = $("#totalPrice").text();
        alert(totalPrice);
        $.post("/LOLdaojucheng/Order/createOrder",{"shippingId":checkedId,"totalPrice":totalPrice},function (result) {
            if (result.success){
                alert("添加订单成功");
            }
        })
    })
});