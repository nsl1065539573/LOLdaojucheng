$(function () {
    var categoryName;//品类名称
    var shangjia;//是否上架
    var onOrDown;//按钮显示上架或者下架
    var butClass;//按钮在不同状态的商品给不同的类，以给不同的事件
    var pageNum = 1;//记录页数
    var maxPage;//记录最大页数
    var mainPicture;//记录主图地址
    var subPicture;//记录副图地址
    var productId;//记录商品ID
    var status;//修改商品属性时记录商品的状态码

  function checkLogin() {
      $.post("/LOLdaojucheng/manage/user/checkLogin",function (result) {
          if (!result.success){

              $("#zhezhaoceng").css("visibility","visible");
              alert("请先进行登录");
              return false;
          }
      });
  }
  checkLogin();
  function appendProduct(){
      checkLogin();
      $.post("/LOLdaojucheng/manage/product/list.do",{"pageNum":pageNum,"pageSize":20},function (result) {

          if (result.success){
              //设置最大页数
              maxPage = result.data.pages;
              //添加页码
              $("#fenyeUL").empty();
              $("#list_table").empty();
              //如果只有一页，那么就直接添加一页
              if (result.data.pages==1){
                  $("#fenyeUL").append(" <li id='previous'><a>&laquo;</a></li>" +
                      "                    <li ><a href=\"#\">1</a></li>" +
                      "                    <li id='next'><a href=\"#\">&raquo;</a></li>");
              }else {
                  for (var i=1;i<=result.data.pages;i++){
                      if (i==1){
                          $("#fenyeUL").append(" <li id='previous'><a>&laquo;</a></li>" +
                              "                  <li id='first' ><a>1</a></li>");
                      } else if (i==result.data.pages){
                          $("#fenyeUL").append("<li id='last' data-value='"+i+"'><a>"+i+"</a></li>" +
                              "                 <li id='next'><a>&raquo;</a></li>")
                      } else {
                          $("#fenyeUL").append("<li class='yema' data-value='"+i+"'><a>"+i+"</a></li>");
                      }
                  }
              }
              $("#list_table").append("<tr>\n" +
                  "                    <th>商品编号</th>\n" +
                  "                    <th>商品名称</th>\n" +
                  "                    <th>商品描述</th>\n" +
                  "                    <th>商品数量</th>\n" +
                  "                    <th>商品类别</th>\n" +
                  "                    <th>是否下架</th>\n" +
                  "                    <th>操作</th>\n" +
                  "                </tr>");
              $(result.data.list).each(function () {
                  if(this.categoryId==1){
                      categoryName ="【英雄】";
                  }else if (this.categoryId==2){
                      categoryName = "【表情】"
                  }
                  else if (this.categoryId==3){
                      categoryName = "【皮肤】"
                  }else if (this.categoryId==4){
                      categoryName = "【道具】"
                  }else if (this.categoryId==5){
                      categoryName = "【守卫】"
                  }else if (this.categoryId==6){
                      categoryName = "【道具包】"
                  }else {
                      categoryName = "【手办】";
                  }
                  if(this.status==2){
                      shangjia = "已下架商品"
                      onOrDown = "上架";
                      butClass = "shangjia";
                  }else if (this.status==3){
                      shangjia = "已删除商品";
                      onOrDown = "添加";
                      butClass = "tianjia";
                  } else {
                      shangjia = "已上架商品";
                      onOrDown = "下架";
                      butClass = "xiajia";
                  }
                  $("#list_table").append("<tr>" +
                      "                    <td>"+this.id+"</td>" +
                      "                    <td class='change_details_td' data-value='"+this.id+"' title='双击查看并修改更多商品资料'>"+this.name+this.subtitle+"</td>" +
                      "                    <td data-value='"+this.id+"' class='changeDetail' title='双击更改商品描述'>"+this.detail+"</td>" +
                      "                    <td data-value='"+this.id+"' class='changeStock' title='双击更改数量'>"+this.stock+"</td>" +
                      "                    <td>"+categoryName+"</td>" +
                      "                    <td>"+shangjia+"</td>" +
                      "                    <td>" +
                      "                        <button data-value='"+this.id+"' type='button' class='btn btn-danger "+butClass+"'>"+onOrDown+"</button>" +
                      "                    </td>" +
                      "                </tr>");

              });
          }

      });
  }
  appendProduct();
  //更改商品其他信息
    $("#list_table").on("dblclick",".change_details_td",function () {
        checkLogin();
        $("#zhezhaoceng").css("visibility","visible");
        $("#productDetails").css("visibility","visible");
        productId = $(this).attr("data-value");
        $.post("/LOLdaojucheng/manage/product/detail",{"productId":productId},function (result) {
            if (result.success){
                $("option[data-value="+result.data.status+"]").attr("selected","selected");
                $("#zhekou").val(result.data.rebate);
                $("#jiage").val(result.data.price);
            }
        })
    });
    $("#change_zhekou").click(function () {
        checkLogin();
        status = $("#status_select").find("option:selected").attr("data-value");
        if (status!=5) {
            alert("请先将商品修改为打折状态");
            return false;
        }else {
            $("#zhekou").attr("readonly",false);
        }

    });
    $("#change_jiage").click(function () {
       $("#jiage").attr("readonly",false) ;
    });
    $("#change_detail_button").click(function () {
        checkLogin();
        status = $("#status_select").find("option:selected").attr("data-value");
        var rebate = $("#zhekou").val();
        var price = $("#jiage").val();
        if (status==5){

           if (!(/^\d+(\.\d+)?$/.test(rebate))){
               alert("请输入阿拉伯数字类型的折扣");
               return false;
           } else if (rebate<0||rebate==10||rebate==0||rebate>10){
               alert("请输入0至10之间的折扣，不得等于0或者10");
               return false;
           }
        } else if (!(/^\d+(\.\d+)?$/.test(price))){
            alert("请输入阿拉伯数字类型的价格");
            return false;
        }
        $.post("/LOLdaojucheng/manage/product/saveOrUpdateProduct",{"id":productId,"price":price,"status":status,"rebate":rebate},function (result) {
            if (result.success){
                alert("更新商品信息成功！")
                $("#zhezhaoceng").css("visibility","hidden");
                $("#productDetails").css("visibility","hidden");
                appendProduct();
            }
        })
    });
    //关闭更新商品窗口
    $("#close_product_details").click(function () {
        $("#zhezhaoceng").css("visibility","hidden");
        $("#productDetails").css("visibility","hidden");
    });
  //更改商品上下架信息
    //下架商品
    $("#list_table").on("click",".xiajia",function () {
        checkLogin();
        var id = $(this).attr("data-value");
        $.post("/LOLdaojucheng/manage/product/set_sale_status",{"productId":id,"status":2},function (result) {
            if (result.success){
                alert("成功将该商品下架");
                appendProduct();
            }
        })
    });
    //上架商品
    $("#list_table").on("click",".shangjia",function () {
        checkLogin();
        var id = $(this).attr("data-value");
        $.post("/LOLdaojucheng/manage/product/set_sale_status",{"productId":id,"status":1},function (result) {
            if (result.success){
                alert("成功将该商品上架");
                appendProduct();
            }
        })
    });
    //退出当前页面并且退出登录
    $("#login_out").click(function () {
       $.post("/LOLdaojucheng/user/exit",function (result) {
           if (result.success){
               alert("您已经退出登录，如需操作，请再次登录");
               checkLogin();
           }

       }) 
    });
   //更改商品数量
    $("#list_table").on("dblclick",".changeStock",function () {
        checkLogin();
        productId = $(this).attr("data-value");
       $("#zhezhaoceng").css("visibility","visible");
       $("#change_stock").css("visibility","visible");
    });
    $("#change_stock_button").click(function () {
        var stock = $("#change_stock_input").val();
        if (!(/^\d+(\.\d+)?$/.test(stock))){
            alert("输入的数量不是阿拉伯数字");
            return false;
        } else if (stock<0){
            alert("输入了负数的数量");
            return false;
        }
       $.post("/LOLdaojucheng/manage/product/updateStock",{"productId":productId,"stock":stock},function (result) {
            appendProduct();
            $("#zhezhaoceng").css("visibility","hidden");
           $("#change_stock").css("visibility","hidden");
       })
    });
    //更改商品描述
    $("#list_table").on("dblclick",".changeDetail",function () {
        checkLogin();
        productId = $(this).attr("data-value");
        $("#zhezhaoceng").css("visibility","visible");
        $("#change_details").css("visibility","visible");
    });
    $("#change_details_button").click(function () {
        checkLogin();
        var details = $("#change_details_input").val();
        if (details==""||details==null){
            alert("请输入商品详细信息");
            return false;
        }
        $.post("/LOLdaojucheng/manage/product/updateDetail",{"productId":productId,"detail":details},function (result) {
            if (result.success){
                appendProduct();
                $("#zhezhaoceng").css("visibility","hidden");
                $("#change_details").css("visibility","hidden");
            }
        })
    });
    //点击关闭更改数量的盒子
    $("#close_change_stock").click(function () {
        $("#zhezhaoceng").css("visibility","hidden");
        $("#change_stock").css("visibility","hidden");
    });
    $("#close_change_details").click(function () {
        $("#zhezhaoceng").css("visibility","hidden");
        $("#change_details").css("visibility","hidden");
    });
   //点击上一页的事件
    $("#fenyeUL").on("click","#previous",function () {
        checkLogin();
        if (pageNum==1){//如果页数是第一页，那就不让点击了
            $(this).addClass("disabled");
        }else {//执行上一页的操作
            $(this).removeClass("disabled");
            $.post("/LOLdaojucheng/manage/product/list.do",{"pageNum":pageNum-1,"pageSize":20},function (result) {
                if (result.success){
                    //清空所填入的数据 重新插入
                    $("#list_table").empty();
                    $("#list_table").append("<tr>\n" +
                        "                    <th>商品编号</th>\n" +
                        "                    <th>商品名称</th>\n" +
                        "                    <th>商品描述</th>\n" +
                        "                    <th>商品数量</th>\n" +
                        "                    <th>商品类别</th>\n" +
                        "                    <th>是否下架</th>\n" +
                        "                    <th>操作</th>\n" +
                        "                </tr>");
                    //遍历集合，获得数据并且填充到table里
                    $(result.data.list).each(function () {
                        if(this.categoryId==1){
                            categoryName ="【英雄】";
                        }else if (this.categoryId==2){
                            categoryName = "【表情】"
                        }
                        else if (this.categoryId==3){
                            categoryName = "【皮肤】"
                        }else if (this.categoryId==4){
                            categoryName = "【道具】"
                        }else if (this.categoryId==5){
                            categoryName = "【守卫】"
                        }else if (this.categoryId==6){
                            categoryName = "【道具包】"
                        }else {
                            categoryName = "【手办】";
                        }
                        if(this.status==2){
                            shangjia = "已下架商品"
                            onOrDown = "上架";
                            butClass = "shangjia";
                        }else if (this.status==3){
                            shangjia = "已删除商品";
                            onOrDown = "添加";
                            butClass = "tianjia";
                        } else {
                            shangjia = "已上架商品";
                            onOrDown = "下架";
                            butClass = "xiajia";
                        }
                        $("#list_table").append("<tr>" +
                            "                    <td>"+this.id+"</td>" +
                            "                    <td class='change_details_td' data-value='"+this.id+"' title='双击查看并修改更多商品资料'>"+this.name+this.subtitle+"</td>" +
                            "                    <td data-value='"+this.id+"' class='changeDetail' title='双击更改商品描述'>"+this.detail+"</td>" +
                            "                    <td data-value='"+this.id+"' class='changeStock' title='双击更改数量'>"+this.stock+"</td>" +
                            "                    <td>"+categoryName+"</td>" +
                            "                    <td>"+shangjia+"</td>" +
                            "                    <td>" +
                            "                        <button data-value='"+this.id+"' type='button' class='btn btn-danger "+butClass+"'>"+onOrDown+"</button>" +
                            "                    </td>" +
                            "                </tr>");
                    });
                }
            });
            pageNum = pageNum-1;
        }
    });
    //点击下一页执行的操作
    $("#fenyeUL").on("click","#next",function () {
        checkLogin();
        if (pageNum==maxPage){
            $(this).addClass("disabled");
        } else {
            $(this).removeClass("disabled");
            $.post("/LOLdaojucheng/manage/product/list.do",{"pageNum":pageNum+1,"pageSize":20},function (result) {
                if (result.success){
                    //清空所填入的数据 重新插入
                    $("#list_table").empty();
                    $("#list_table").append("<tr>\n" +
                        "                    <th>商品编号</th>\n" +
                        "                    <th>商品名称</th>\n" +
                        "                    <th>商品描述</th>\n" +
                        "                    <th>商品数量</th>\n" +
                        "                    <th>商品类别</th>\n" +
                        "                    <th>是否下架</th>\n" +
                        "                    <th>操作</th>\n" +
                        "                </tr>");
                    //遍历集合，获得数据并且填充到table里
                    $(result.data.list).each(function () {
                        if(this.categoryId==1){
                            categoryName ="【英雄】";
                        }else if (this.categoryId==2){
                            categoryName = "【表情】"
                        }
                        else if (this.categoryId==3){
                            categoryName = "【皮肤】"
                        }else if (this.categoryId==4){
                            categoryName = "【道具】"
                        }else if (this.categoryId==5){
                            categoryName = "【守卫】"
                        }else if (this.categoryId==6){
                            categoryName = "【道具包】"
                        }else {
                            categoryName = "【手办】";
                        }
                        if(this.status==2){
                            shangjia = "已下架商品"
                            onOrDown = "上架";
                            butClass = "shangjia";
                        }else if (this.status==3){
                            shangjia = "已删除商品";
                            onOrDown = "添加";
                            butClass = "tianjia";
                        } else {
                            shangjia = "已上架商品";
                            onOrDown = "下架";
                            butClass = "xiajia";
                        }
                        $("#list_table").append("<tr>" +
                            "                    <td>"+this.id+"</td>" +
                            "                    <td class='change_details_td' data-value='"+this.id+"' title='双击查看并修改更多商品资料'>"+this.name+this.subtitle+"</td>" +
                            "                    <td data-value='"+this.id+"' class='changeDetail' title='双击更改商品描述'>"+this.detail+"</td>" +
                            "                    <td data-value='"+this.id+"' class='changeStock' title='双击更改数量'>"+this.stock+"</td>" +
                            "                    <td>"+categoryName+"</td>" +
                            "                    <td>"+shangjia+"</td>" +
                            "                    <td>" +
                            "                        <button data-value='"+this.id+"' type='button' class='btn btn-danger "+butClass+"'>"+onOrDown+"</button>" +
                            "                    </td>" +
                            "                </tr>");
                    });
                }
            });
            pageNum = pageNum+1;
        }

    });
    //点击第一页执行的操作
    $("#fenyeUL").on("click","#first",function () {
        checkLogin();
        pageNum = 1;
        $.post("/LOLdaojucheng/manage/product/list.do",{"pageNum":1,"pageSize":20},function (result) {
            if (result.success){
                //清空所填入的数据 重新插入
                $("#list_table").empty();
                $("#list_table").append("<tr>\n" +
                    "                    <th>商品编号</th>\n" +
                    "                    <th>商品名称</th>\n" +
                    "                    <th>商品描述</th>\n" +
                    "                    <th>商品数量</th>\n" +
                    "                    <th>商品类别</th>\n" +
                    "                    <th>是否下架</th>\n" +
                    "                    <th>操作</th>\n" +
                    "                </tr>");
                //遍历集合，获得数据并且填充到table里
                $(result.data.list).each(function () {
                    if(this.categoryId==1){
                        categoryName ="【英雄】";
                    }else if (this.categoryId==2){
                        categoryName = "【表情】"
                    }
                    else if (this.categoryId==3){
                        categoryName = "【皮肤】"
                    }else if (this.categoryId==4){
                        categoryName = "【道具】"
                    }else if (this.categoryId==5){
                        categoryName = "【守卫】"
                    }else if (this.categoryId==6){
                        categoryName = "【道具包】"
                    }else {
                        categoryName = "【手办】";
                    }
                    if(this.status==2){
                        shangjia = "已下架商品"
                        onOrDown = "上架";
                        butClass = "shangjia";
                    }else if (this.status==3){
                        shangjia = "已删除商品";
                        onOrDown = "添加";
                        butClass = "tianjia";
                    } else {
                        shangjia = "已上架商品";
                        onOrDown = "下架";
                        butClass = "xiajia";
                    }
                    $("#list_table").append("<tr>" +
                        "                    <td>"+this.id+"</td>" +
                        "                    <td class='change_details_td' data-value='"+this.id+"' title='双击查看并修改更多商品资料'>"+this.name+this.subtitle+"</td>" +
                        "                    <td data-value='"+this.id+"' class='changeDetail' title='双击更改商品描述'>"+this.detail+"</td>" +
                        "                    <td data-value='"+this.id+"' class='changeStock' title='双击更改数量'>"+this.stock+"</td>" +
                        "                    <td>"+categoryName+"</td>" +
                        "                    <td>"+shangjia+"</td>" +
                        "                    <td>" +
                        "                        <button data-value='"+this.id+"' type='button' class='btn btn-danger "+butClass+"'>"+onOrDown+"</button>" +
                        "                    </td>" +
                        "                </tr>");
                });
            }
        });
    });
    //点击页码的事件
    $("#fenyeUL").on("click",".yema",function () {
        checkLogin();
        var pageNumber = $(this).attr("data-value");
        pageNum = pageNumber;
        $.post("/LOLdaojucheng/manage/product/list.do",{"pageNum":pageNumber,"pageSize":20},function (result) {
            if (result.success){
                //清空所填入的数据 重新插入
                $("#list_table").empty();
                $("#list_table").append("<tr>\n" +
                    "                    <th>商品编号</th>\n" +
                    "                    <th>商品名称</th>\n" +
                    "                    <th>商品描述</th>\n" +
                    "                    <th>商品数量</th>\n" +
                    "                    <th>商品类别</th>\n" +
                    "                    <th>是否下架</th>\n" +
                    "                    <th>操作</th>\n" +
                    "                </tr>");
                //遍历集合，获得数据并且填充到table里
                $(result.data.list).each(function () {
                    if(this.categoryId==1){
                        categoryName ="【英雄】";
                    }else if (this.categoryId==2){
                        categoryName = "【表情】"
                    }
                    else if (this.categoryId==3){
                        categoryName = "【皮肤】"
                    }else if (this.categoryId==4){
                        categoryName = "【道具】"
                    }else if (this.categoryId==5){
                        categoryName = "【守卫】"
                    }else if (this.categoryId==6){
                        categoryName = "【道具包】"
                    }else {
                        categoryName = "【手办】";
                    }
                    if(this.status==2){
                        shangjia = "已下架商品"
                        onOrDown = "上架";
                        butClass = "shangjia";
                    }else if (this.status==3){
                        shangjia = "已删除商品";
                        onOrDown = "添加";
                        butClass = "tianjia";
                    } else {
                        shangjia = "已上架商品";
                        onOrDown = "下架";
                        butClass = "xiajia";
                    }
                    $("#list_table").append("<tr>" +
                        "                    <td>"+this.id+"</td>" +
                        "                    <td class='change_details_td' data-value='"+this.id+"' title='双击查看并修改更多商品资料'>"+this.name+this.subtitle+"</td>" +
                        "                    <td data-value='"+this.id+"' class='changeDetail' title='双击更改商品描述'>"+this.detail+"</td>" +
                        "                    <td data-value='"+this.id+"' class='changeStock' title='双击更改数量'>"+this.stock+"</td>" +
                        "                    <td>"+categoryName+"</td>" +
                        "                    <td>"+shangjia+"</td>" +
                        "                    <td>" +
                        "                        <button data-value='"+this.id+"' type='button' class='btn btn-danger "+butClass+"'>"+onOrDown+"</button>" +
                        "                    </td>" +
                        "                </tr>");
                });
            }
        });
    });
    //点击最后一页的事件
    $("#fenyeUL").on("click","#last",function () {
        checkLogin();
        pageNum = $(this).attr("data-value");
        $.post("/LOLdaojucheng/manage/product/list.do",{"pageNum":pageNum,"pageSize":20},function (result) {
            if (result.success){
                //清空所填入的数据 重新插入
                $("#list_table").empty();
                $("#list_table").append("<tr>\n" +
                    "                    <th>商品编号</th>\n" +
                    "                    <th>商品名称</th>\n" +
                    "                    <th>商品描述</th>\n" +
                    "                    <th>商品数量</th>\n" +
                    "                    <th>商品类别</th>\n" +
                    "                    <th>是否下架</th>\n" +
                    "                    <th>操作</th>\n" +
                    "                </tr>");
                //遍历集合，获得数据并且填充到table里
                $(result.data.list).each(function () {
                    if(this.categoryId==1){
                        categoryName ="【英雄】";
                    }else if (this.categoryId==2){
                        categoryName = "【表情】"
                    }
                    else if (this.categoryId==3){
                        categoryName = "【皮肤】"
                    }else if (this.categoryId==4){
                        categoryName = "【道具】"
                    }else if (this.categoryId==5){
                        categoryName = "【守卫】"
                    }else if (this.categoryId==6){
                        categoryName = "【道具包】"
                    }else {
                        categoryName = "【手办】";
                    }
                    if(this.status==2){
                        shangjia = "已下架商品"
                        onOrDown = "上架";
                        butClass = "shangjia";
                    }else if (this.status==3){
                        shangjia = "已删除商品";
                        onOrDown = "添加";
                        butClass = "tianjia";
                    } else {
                        shangjia = "已上架商品";
                        onOrDown = "下架";
                        butClass = "xiajia";
                    }
                    $("#list_table").append("<tr>" +
                        "                    <td>"+this.id+"</td>" +
                        "                    <td class='change_details_td' data-value='"+this.id+"' title='双击查看并修改更多商品资料'>"+this.name+this.subtitle+"</td>" +
                        "                    <td data-value='"+this.id+"' class='changeDetail' title='双击更改商品描述'>"+this.detail+"</td>" +
                        "                    <td data-value='"+this.id+"' class='changeStock' title='双击更改数量'>"+this.stock+"</td>" +
                        "                    <td>"+categoryName+"</td>" +
                        "                    <td>"+shangjia+"</td>" +
                        "                    <td>" +
                        "                        <button data-value='"+this.id+"' type='button' class='btn btn-danger "+butClass+"'>"+onOrDown+"</button>" +
                        "                    </td>" +
                        "                </tr>");
                });
            }
        });
    });
    //获取所有商品类别
    $.post("/LOLdaojucheng/category/selectAll",function (result) {
        if (result.success){
            $(result.data).each(function () {
                $("#categoryIds").append("<option data-value='"+this.id+"'>-- "+this.name+" --</option>")
            })
        }
    });
    //添加商品
    $("#addProduct").click(function () {
        checkLogin();
        //获取到商品的状态码
        var status = $("#productStatus").find("option:selected").attr("data-value");
        var categoryId = $("#categoryIds").find("option:selected").attr("data-value");
        var gameName = $("#gameName").find("option:selected").attr("data-value");
        var rebate = $("#rebate").val();
        var price = $("#price").val();
        var name = $("#pifuming").val();
        var subTitle = $("#yingxiongming").val();
        var detail = $("#detail").val();
        var stock = $("#stock").val();
        if (status==0){
            alert("请选择商品状态");
            return false;
        }else if (price==""||price==null){
            alert("请输入商品价格");
            return false;
        }else if (!(/^\d+(\.\d+)?$/.test(price))){
            alert("请输入阿拉伯数字格式的价格");
            return false;
        }else if (price<=0){
            alert("您输入了0或者负数作为价格");
            return false;
        } else if (categoryId==0){
            alert("请选择商品品类");
            return false;
        } else  if (gameName!=1){
            alert("请选择游戏名称");
            return false;
        }else if (status==5&&rebate==null&&rebate>=10&&rebate<=0){
            alert("请正确输入折扣");
            return false;
        }else if (mainPicture==null||mainPicture==""){
            alert("请上传商品主图");
            return false;
        } else  if (subPicture==null||subPicture==""){
            alert("请上传商品副图");
            return false;
        } else  if (stock==""||stock==null){
            alert("请输入商品数量");
            return false;
        } else if (!(/^\d+(\.\d+)?$/.test(stock))){
            alert("请输入阿拉伯数字类型的数量");
            return false;
        } else  if (stock==0||stock<0){
            alert("您输入了小数或者0作为商品数量");
            return false;
        }else if (name==""||name==null){
            alert("请输入商品主名称");
            return false;
        } else if (subTitle==""||subTitle==null){
            alert("请输入商品副名称");
            return false;
        } else if (detail==""||detail==null){
            alert("请输入商品描述");
            return false;
        }
        $.post("/LOLdaojucheng/manage/product/saveOrUpdateProduct",
            {"categoryId":categoryId,
                "name":name,
                "subtitle":subTitle,
                "rebate":rebate,
                "mainImage":mainPicture,
                "price":price,
                "stock":stock,
                "status":status,
                "subImages":subPicture,
                "detail":detail},
            function (result) {
                alert(result.success);
        });
        
    });
    //上传图片
    $("#mainPicture").click(function () {
        checkLogin();
        $("#uploadMain").ajaxSubmit(function (result) {
            if (result.success)
            mainPicture = "img/"+result.data.uri;
        });
    });
    $("#subPicture").click(function () {
        checkLogin();
        $("#uploadSub").ajaxSubmit(function (result) {
            if (result.success)
            subPicture = "img/"+result.data.uri;
        })
    })
});