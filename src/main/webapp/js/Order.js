$(function () {
    var pageNum = 1;
    var status;//记录订单状态
    var butClass;//支付那个按钮的类 分别绑定时间
    var zhuangtai;//订单状态
    var orderNo;//记录订单号
    var isPay;//记录是否支付
    function checkLogin() {
        $.post("/LOLdaojucheng/user/hasLogin",function (result) {
            if (!result.success){
                $("#zhezhaoceng").css("visibility","hidden");
            }
        })
    }

    function fun(){
        window.setInterval(function (args) {
            if (orderNo!=null){
                $.post("/LOLdaojucheng/Order/detail.do",{"orderNo":orderNo},function (result) {
                    if (result.success){
                        if (result.data.status===20){
                            $("#erweimaDiv").css("visibility","hidden");
                        }
                    }
                })
            } },1000)
    }
    fun();
    checkLogin();
   function getOrderList() {
       $.post("/LOLdaojucheng/Order/list.do",{"pageNum":pageNum,"pageSize":8},function (result) {
           if (result.success){
               $(result.data.list).each(function () {
                   var class_name = this.orderNo;
                   if (this.status==10){
                       isPay = true;
                       status = "支付";
                       butClass = "pay";
                       zhuangtai = "未支付";
                   } else if (this.status==0){
                       status = "已取消";
                       zhuangtai = "已经取消";
                   } else  if (this.status==60) {
                       status = "交易关闭";
                       zhuangtai = "已经关闭";
                   }else if(this.status ==50) {
                       status = "交易成功";
                       zhuangtai = "交易成功";
                   }else if (this.status==20){
                       status ="已付款";
                       zhuangtai = "已经付款";
                   } else if (this.status == 40){
                       status = "已发货";
                       zhuangtai = "已经发货";
                   }
                   if (!isPay) {
                       $("#orderTable").append("<tr class='center'><td class=\"center\">"+this.orderNo+"</td>\n" +
                           "                            <td id='td1"+class_name+"' class=\"center\"></td>\n" +
                           "                            <td id='tda"+class_name+"'  class=\" center\"></td>\n" +
                           "                            <td class=\" center\">"+this.payment+"</td>\n" +
                           "                            <td class=\"center \">"+status+"</td>\n" +
                           "                            <td class=\"center \" ><button data-value='"+this.orderNo+"' class='"+butClass+" payButton'>"+status+"</button></td></tr>")
                       $(this.orderItemVoList).each(function () {
                           $("#td1"+class_name+"").append(""+this.productName+"<br/>");
                           $("#tda"+class_name+"").append(""+this.quantity+"<br/>")
                       })
                   }else {
                       $("#orderTable").append("<tr class='center'><td class=\"center\">"+this.orderNo+"</td>\n" +
                           "                            <td id='td1"+class_name+"' class=\"center\"></td>\n" +
                           "                            <td id='tda"+class_name+"'  class=\" center\"></td>\n" +
                           "                            <td class=\" center\">"+this.payment+"</td>\n" +
                           "                            <td class=\"center \">"+status+"</td>\n" +
                           "                            <td class=\"center \" ><button data-value='"+this.orderNo+"' class='"+butClass+" payButton'>"+status+"</button><br/>" +
                           "                            <a data-value='"+this.orderNo+"' class='quxiaodingdan'>取消订单</a></td></tr>")
                       $(this.orderItemVoList).each(function () {
                           $("#td1"+class_name+"").append(""+this.productName+"<br/>");
                           $("#tda"+class_name+"").append(""+this.quantity+"<br/>")
                       })
                   }

               });

           }
       })
   }
   //取消订单
    $("#orderTable").on("click",".quxiaodingdan",function () {
        var orderNo = $(this).attr("data-value");
       $.post("/LOLdaojucheng/Order/cancel.do",{"orderNo":orderNo},function (result) {
               window.location.reload();

       })
    });
   getOrderList();
   $("#orderTable").on("click",".pay",function () {
       orderNo = $(this).attr("data-value");
       $.post("/LOLdaojucheng/Order/pay",{"orderNo":orderNo},function (result) {
            if (result.success){
                //填二维码图片
                $("#bodyDiv").append("<img id=\"pictureDiv\" src='"+URL+"/images/qr-"+orderNo+".png' />");
                $("#erweimaDiv").css("visibility","visible");
            }
       })

   });
   //点击叉号隐藏二维码
    $("#close_erweima").click(function () {
        $("#erweimaDiv").css("visibility","hidden");
    })
});