$(function () {
    var codeLength=4;
    var code ;
    var isTrue=false;
    var in1=$("#in1");
    var in2=$("#in2");
    var p = $("#p5");
    $("#check_code").click(function () {
        create_code(codeLength);
    });
    function trim(str) { //删除左右两端的空格
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }
    function create_code(codeLength){
        //生成验证码
        code = "";
        var checkCode = $("#check_code");
        var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L',
            'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');

        for(var i=0;i<codeLength;i++){
            var charIndex = Math.floor(Math.random()*36);
            code +=selectChar[charIndex];
        }
        if(checkCode){
            checkCode.addClass("code");
            checkCode.val(code);
        }
    }
    function verify_code (){
        //验证验证码
        var user_input_code = $("#check").val().toLowerCase().trim();
        if(user_input_code.length <=0){
            return false;
        } else if(user_input_code != code.toLowerCase()){
            return false;
        }
        return true;
    }

        $("#check").keyup(function () {
            var code_len = $("#check").val().trim().length;
            if (code_len < codeLength) {
                p.text("");
            } else if (code_len > codeLength) {
                p.text("验证码错误");
                isTrue=false;
            } else {
                if (verify_code()) {
                    p.text("");
                   isTrue=true;
                } else {
                   p.text("验证码错误");
                    isTrue=false;
                }
            }
        });
    $("#b2").click(function () {
        p.text("");
        if (isTrue){
            alert(1);
            if (trim(in1.val())==null||trim(in1.val())===""){
                p.text("请输入用户");
                in1.focus();
                return false;
            }else if (/[^\u4e00-\u9fa5\w]/.test(in1.val())) {
                p.text("用户名只能包含中文、英文、数字和下划线");
                in1.focus();
                return false;
            }
            if (trim(in2.val())==null||trim(in2.val())===""){
                p.text("请输入密码");
                in1.focus();
                return false;
            }
            $.post("/LOLdaojucheng/user/login",{"username":$("#in1").val(),"password":$("#in2").val()},function (result) {
                if (result.success){
                    $("#d8").html("<div id='div_1'>" +
                        "           <p id='user_p_tishi'>登录成功，是否前往主页？</p>" +
                        "           <button id='button_homepage'>去主页逛一逛</button><button id='button_exit'>算了不逛了</button>" +
                        "           </div>");
                } else {
                    $("#p5").text("用户名或密码错误！");
                }
            },"json");
        } else {
            $("#p5").text("验证码错误");
        }
    });
$("#d8").on("click","#button_homepage",function () {

    window.location.href="http://localhost:8888/LOLdaojucheng/";
});


});
