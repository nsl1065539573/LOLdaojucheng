$(function () {
    var in1 = $("#in1");
    var in2 = $("#in2");
    var in3 = $("#in3");
    var in4 = $("#in4");
    var in5 = $("#in5");
    var in6 = $("#in6");
    var in7 = $("#in7");
    var p = $("#p5");
    function trim(str) { //删除左右两端的空格
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }
    //单击按钮时执行
    $("#b2").click(function () {
        alert(1);
        p.text("");
        $.post("/LOLdaojucheng/user/register.do",
            {"username":in1.val(),
                "password":in2.val(),
                "email":in4.val(),
                "phone":in5.val(),
                "question":in6.val(),
                "answer":in7.val()
            },
            function (result) {
                if (result.success){
                    $("#d8").html("<div id='div_1'>" +
                        "           <p id='user_p_tishi' style=' font-size: 27px; width: 85%;color:darkgrey;margin-left: 7.5%;position: relative;top: 40px;'>注册成功，快去登录吧(*^ω^*)</p>" +
                        "           <button id='button_success' style='background-color: darkslateblue;color: white;height: 48px;width: 360px;margin-top: 60px;border: none;border-radius: 3px;margin-left: 7.5%;font-size: 18px'>去登录</button>" +
                        "           </div>");
                } else {
                    p.text(result.msg);
                }
            },
            "json"
        )
    });
    in1.blur(function () {
        if (trim(in1.val())==null||trim(in1.val())===""){
            p.text("请输入用户名");
            return false;
        }else if (trim(in1.val()).length<6||trim(in1.val()).length>18){
            p.text("用户名长度请在6-18位");
            return false;
        }else if (/[^\u4e00-\u9fa5\w]/.test(in1.val())) {
            p.text("用户名只能包含中文、英文、数字和下划线");
            return false;
        }
        p.text("");
    });
    in2.blur(function () {
        if (trim(in2.val())==null||trim(in2.val())===""){
            p.text("请输入密码");
            return false;
        }else if (trim(in2.val()).length<6||trim(in2.val()).length>18){
            p.text("请输入6-18位密码");
            return false;
        }
        p.text("");
    });
    in7.blur(function () {
        if (trim(in7.val())==null||trim(in7.val())===""){
            p.text("请输入密保答案");
            return false;
        }
        p.text("");
    });
    in6.blur(function () {
        if (trim(in6.val())==null||trim(in6.val())===""){
            p.text("请输入密保问题");
            return false;
        }
        p.text("");
    });
    in5.blur(function () {
        if (trim(in5.val())==null||trim(in5.val())===""){
            p.text("请输入手机号");
            return false;
        }else if (!(/^1[3|5|7|8][0-9]\d{4,8}$/.test(in5.val()))) {
            p.text("请输入正确格式的手机号码");
            return false;
        }
        p.text("");
    });
    in4.blur(function () {
        if (trim(in4.val())==null||trim(in4.val())===""){
            p.text("请输入邮箱");
            return false;

        }else if(in4 && !/^\w+@[a-z0-9]+(\.[a-z]+){1,3}$/.test(in4.val())){
            p.text("邮箱格式不正确");
            return false;
        }
        p.text("");
    });
    in3.blur(function () {
        if (trim(in3.val())==null||trim(in3.val())===""){
            p.text("请确认密码");
            return false;
        }else if (trim(in2.val())!==trim(in3.val())){
            p.text("密码不一致");
            return false;
        }
        p.text("");
    });


});


