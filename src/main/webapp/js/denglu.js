function aaa(){
			var i = document.getElementById("in1").value;
			var j = document.getElementById("in2").value;
			//var x = document.getElementById("a3").value;
			var y = document.getElementById("p5");
			if(i==""&&j==""){
				y.innerHTML="账号密码为空";
				y.setAttribute("style","opacity:1");
			}
			 else if(i==""||j==""){
				y.innerHTML="请输入正确的账号或密码";
				y.setAttribute("style","opacity:1");
			}
//			 else if(x!=j){
//				y.innerHTML="两次输入的密码不相同";
//				y.setAttribute("style","opacity:1");}	
			else{
				y.setAttribute("style","opacity:0");

			}
}