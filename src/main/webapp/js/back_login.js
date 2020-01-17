$(function () {

   //点击登录按钮，进行管理员登录
   $("#loginButtonBack").click(function () {

       //验证是否输入了用户名和密码
       var username = $("#username");
       var password = $("#password");
       if (username.val()==""){
           alert("请输入用户名");
           return false;
       } else if (password.val()==""){
           alert("请输入密码");
           return false;
       }
       $.post("/LOLdaojucheng/manage/user/login",{"username":username.val(),"password":password.val()},function (result) {
           if (result.success){
               window.location.href="/LOLdaojucheng/admin/product";
           }
       });
   }) ;
});