$(function () {
    var forgettoken;
    var username;
    /***
     * 获得登录用户详细信息
     */
    getUserInfo();
    function getUserInfo(){
        $.post("/LOLdaojucheng/user/get_user_info",function (result) {
            if (!result.success) {
                $("#zhezhaoceng").css("visibility","visible");
                alert("请先进行登录");
            }
            $("#username").attr("value",result.data.username);
            username = result.data.username;
            if (result.data.nicheng!=null){
                $("#nicheng").attr("value",result.data.nicheng);
                $("#changeNicheng").attr("value",result.data.nicheng);
            }
            $("#phone").attr("value",result.data.phone);
            $("#email").attr("value",result.data.email);
            $("#question").attr("value",result.data.question);
            $("#chanegPhone").attr("value",result.data.phone);
            $("#changeEmail").attr("value",result.data.email);
            $("#changeQuestion").attr("value",result.data.question);
            $("#checkQuestion").attr("value",result.data.question);
        });
    }
    $("#changeUserInfo").click(function () {
        $("#change_userInfo").css("visibility","visible");
        $("#zhezhaoceng").css("visibility","visible");
    });
    $("#changePassword").click(function () {
        $("#checkAnswer").val("");
        $("#newPassword").val("");
        $("#checkNewPassword").val("");
        $("#zhezhaoceng").css("visibility","visible");
        $("#check_answer").css("visibility","visible");
    });
    /***
     * 点击关闭按钮的事件
     */
    $("#close_changeUserInfo").click(function () {
        $("#change_userInfo").css("visibility","hidden");
        $("#zhezhaoceng").css("visibility","hidden");
    });
    $("#close_checkAnswer").click(function () {
        $("#zhezhaoceng").css("visibility","hidden");
        $("#check_answer").css("visibility","hidden");
    });
    $("#close_update_password").click(function () {
        $("#zhezhaoceng").css("visibility","hidden");
        $("#update_password").css("visibility","hidden");
    });

    /***
     * 验证密保问题和答案是否正确
     */
    $("#change_sure1").click(function () {

        if ($("#checkAnswer").val()==""){
            alert("请输入答案");
            return false;
        }
       $.post("/LOLdaojucheng/user/select_isAnswerTrue",{"username":username,"question":$("#checkQuestion").val(),"answer":$("#checkAnswer").val()},function (result) {
          if (result.success){
              forgettoken = result.msg;
              $("#check_answer").css("visibility","hidden");
              $("#update_password").css("visibility","visible");
          }

       });

    });
    /***
     * 点击确定按钮，更改密码
     */
    $("#change_sure2").click(function () {
        var newPassword = $("#newPassword");
        var checkPassword = $("#checkNewPassword");
        if (newPassword.val()==""){
            alert("请输入密码");
            return false;
        } else if (newPassword.val().length<6||newPassword.val().length>18){
            alert("请输入6-18位的密码");
            return false;
        } else if (newPassword.val()!=checkPassword.val()){
            alert("两次输入的密码不一致");
            return false;
        }
        $.post("/LOLdaojucheng/user/update_password",{"username":username,"password":newPassword.val(),"forgettoken":forgettoken},function (result) {
            if (result.success){
                alert("修改密码成功！");
                $("#zhezhaoceng").css("visibility","hidden");
                $("#update_password").css("visibility","hidden");
            }
        });
    });
    /***
     * 点击确定按钮，更改用户信息，并且将数据传到后台
     */
    $("#change_sure").click(function () {
        var nicheng = $("#changeNicheng");
        var phone = $("#chanegPhone");
        var email = $("#changeEmail");
        var question = $("#changeQuestion");
        var answer = $("#changeAnswer");
        if (phone.val()==""){
            alert("手机号不能为空");
            return false;
        } else if (email.val()==""){
            alert("邮箱不能为空");
            return false;
        } else if (question.val()==null){
            alert("密保问题不能为空");
            return false;
        } else if (nicheng.val()==""){
            alert("确定不设置昵称吗");
            return false;
        }else if (!(/^1[3|5|7|8][0-9]\d{4,8}$/.test(phone.val()))) {
            alert("请输入正确格式的手机号");
            return false;
        }else if (!/^\w+@[a-z0-9]+(\.[a-z]+){1,3}$/.test(email.val())){
            alert("请输入正确格式的邮箱");
            return false;
        }
        alert("111");
        $.post("/LOLdaojucheng/user/update_information",{"nicheng":nicheng.val(),"phone":phone.val(),"email":email.val(),"question":question.val(),"answer":answer.val()},function (result) {
            if (result.success){
                alert("修改信息成功！")
                $("#change_userInfo").css("visibility","hidden");
                $("#zhezhaoceng").css("visibility","hidden");
                getUserInfo();
            }
        });
    });


});