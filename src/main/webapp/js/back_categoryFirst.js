$(function () {
    //检查用户是否登录
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
   //查询所有分类并且展示出来
    function selectAll() {
        checkLogin();
        $.post("/LOLdaojucheng/category/selectAll",function (result) {
            $("#leibieTable").empty();
            $("#leibieTable").append("\n" +
                "                <tr>\n" +
                "                    <th>分类的编号</th>\n" +
                "                    <th>分类名称</th>\n" +
                "                </tr>");
            $(result.data).each(function () {
               $("#leibieTable").append(" <tr>" +
                   "                    <td>"+this.id+"</td>" +
                   "                    <td>"+this.name+"</td>" +
                   "                </tr>")
            });
        })
    }
    selectAll();
    //添加节点
    $("#addCategory").click(function () {
        checkLogin();
        var categoryName = $("#categoryName").val();
        if (categoryName==""||categoryName==null){
            alert("请输入类别名称");
        } else {
            $.post("/LOLdaojucheng/manage/category/add_category",{"categoryName":categoryName},function (result) {
                if (result.success){
                    alert("添加成功")
                    selectAll();
                }
            })

        }
    });
    $("#login_out").click(function () {
        $.post("/LOLdaojucheng/user/exit",function (result) {
            if (result.success){
                checkLogin();
            }
        })
    })
});