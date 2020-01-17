$(function () {
    var pageNum = 1;//记录页码
    var isUse;//是否启用
    var butClass;//记录按钮的类名
    var butValue;//记录按钮的值
    var maxPageNum;//最大页码
    //检查管理员是否登录
   function checkLogin() {
       $.post("/LOLdaojucheng/manage/user/checkLogin",function (result) {
           if (!result.success){
               $("#zhezhaoceng").css("visibility","visible");
               alert(result.msg);
               return false;
           }
       })
   }
   //添加表头
    function addbiaotou(){
        $("#userList").append("<tr>\n" +
            "                    <th>用户名</th>\n" +
            "                    <th>电话</th>\n" +
            "                    <th>状态</th>\n" +
            "                    <th>操作</th>\n" +
            "                </tr>");
    }
   checkLogin();
   //分页查询用户信息
    function appendUser() {
        checkLogin();
        $.post("/LOLdaojucheng/manage/user/userList",{"pageNum":pageNum,"pageSize":20},function (result) {
            $("#userList").empty();
            $("#pageNum").empty();
            //添加页码
            maxPageNum = result.data.pages;
            for (var i=1;i<=result.data.pages;i++){
                $("#pageNum").append("<li data-value='"+i+"' class='yema'><a>"+i+"</a></li>");
            }
            //添加用户
          addbiaotou();
            $(result.data.list).each(function () {
                if (this.role==1){
                    isUse = "已启用";
                    butClass = "zhengchang";
                    butValue = "禁用";
                }if (this.role==2){
                    isUse= "已禁用";
                    butClass = "jinyong";
                    butValue = "启用";
                }
                if (this.role!=0){
                    $("#userList").append("<tr>\n" +
                        "                    <td>"+this.username+"</td>\n" +
                        "                    <td>"+this.phone+"</td>\n" +
                        "                    <td>已启用</td>\n" +
                        "                    <td>\n" +
                        "                        <button data-value='"+this.id+"' type=\"button\" class=\"btn btn-danger "+butClass+"\">"+butValue+"</button>\n" +
                        "                    </td>\n" +
                        "                </tr>");
                }

            })
        })
    }
    appendUser();
    //退出登录
    $("#login_out").click(function () {
        $.post("/LOLdaojucheng/user/exit",function (result) {
            if (result.success){
                checkLogin();
            }
        })
    });
    //点击页码的事件
    $("#pageNum").on("click",".yema",function () {
        pageNum = $(this).attr("data-value");
        appendUser();
    });
    //点击上一页的事件
    $("#previous").click(function () {
       if (pageNum==1){
           $(this).addClass("disabled");
           return false;
       }else {
           $(this).removeClass("disabled");
           pageNum = pageNum-1;
           appendUser();
       }
    });
    //点击下一页的事件
    $("#next").click(function () {
        if (pageNum==maxPageNum){
            $(this).addClass("disabled");
            return false;
        } else {
            $(this).removeClass("disabled");
            pageNum = pageNum+1;
            appendUser();
        }
    })
    //点击禁用或者启用用户
    $("#userList").on("click",".zhengchang",function () {
        checkLogin();
        var id = $(this).attr("data-value");
        $.post("/LOLdaojucheng/manage/user/updateRole",{"userId":id,"role":2},function (result) {
            if (result.success){
                alert("禁用成功");
                appendUser();
            }
        });
    });
    $("#userList").on("click",".jinyong",function () {
        checkLogin();
        var id = $(this).attr("data-value");
        $.post("/LOLdaojucheng/manage/user/updateRole",{"userId":id,"role":1},function (result) {
            if (result.success){
                alert("启用成功");
                appendUser();
            }
        })
    })
});