$(function(){
	$("input[name='email']").blur(function(){
		var email = $(this).val();

		if(obj.checkNull("email","Email不能为空！") && obj.checkEmail()){
			$.post("/index/checkemail",{"email":email},function(result){
				$("#email_msg").text(result);
				$("#email_msg").css("color","red");
			});
		}
	});
	
	$("input[name='password']").blur(function(){
		obj.checkPassword();
		obj.checkLength("password");
		
	});
	
	$("input[name='repassword']").blur(function(){
		obj.checkPassword();
		obj.checkLength("repassword");
	});

	$("form").submit(function (){
		var flag = true;
		flag = obj.checkNull("email","Email不为空!") && flag;
		flag = obj.checkNull("password","密码不为空!")&& flag;
		flag = obj.checkNull("repassword","确认密码不为空!")&& flag;
		flag = obj.checkEmail() && flag;
		flag = obj.checkPassword() && flag;

		var email = $("input[name = 'email']").val();
		var password = $("input[name = 'password']").val();
		if(flag){
			$.get("/index/doRegister",{
				"email":email,
				"password":password
			},function (result){
				if(result === "注册成功"){
					alert(result);
					window.location.href="/index/toLogin";
				}
				$("#form_msg").html(result);
				$("#form_msg").css("color","red");
			});
		}
		return false;
	});
});

var obj = {
	"setMsg":function(name,msg){
		$("#"+name+"_msg").text(msg);
		$("#"+name+"_msg").css("color","red");
	},
	//非空验证
	"checkNull":function(name,msg){
		var value = $("input[name='"+ name +"']").val();
		if(value.length == 0){
			this.setMsg(name,msg);
			return false;
		}else{
			this.setMsg(name,"");
		}
		return true;
	},
	//判断长度是否小于6
	"checkLength":function(name){
		var value = $("input[name='"+ name +"']").val();
		if(value.length == 0){
			this.setMsg(name,"密码不能为空！");
			return false;
		}
		else if(value.length < 6){
			this.setMsg(name,"密码小于6！");
			return false;
		}
		else{
			this.setMsg(name,"");
		}
		return true;
	},
	//E-mail格式验证
	"checkEmail":function(){
		var email = $("input[name='email']").val();
		var match=/^\w+@\w+(\.\w+)+$/;
		if(email.length > 0){
			if(!match.test(email)){
				this.setMsg("email","email格式不正确！");
				return false;
			}
		}
		return true;
	},
	//检验密码是否一致
	"checkPassword":function(){
		var password = $("input[name='password']").val();
		var repassword = $("input[name='repassword']").val();
		if(password.length > 0 && repassword.length > 0){
			if(password != repassword){
				this.setMsg("password","两次密码不一致！");
				this.setMsg("repassword","两次密码不一致！");
				return false;
			}else{
				this.setMsg("password","");
				this.setMsg("repassword","");
			}
		}
		return true;
	}
}
