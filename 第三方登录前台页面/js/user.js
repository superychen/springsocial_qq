$.ajax({
	type:"get",
	url:"http://localhost:8080/cqyc/sysUser/isLogin",
	async:true,
	dataType:'json',
	xhrFields: {
		withCredentials: true
	},
	crossDomain: true,
	success:function(res){
		console.log(res.msg.username);
		if(res.code === "1000"){
			window.location.href="login.html"
		}else{
			$('#user_login_name').append(res.msg.username);
			$('#nav_child').append(' <dd><a href="index.html">后台管理</a></dd>')
		}
	},
	error:function(error){
		console.log(error);
	}
});

$('#loginout').click(function(){
	$.ajax({
		type:"get",
		url:"http://localhost:8080/cqyc/sysUser/loginOut",
		async:true,
		dataType:"json",
		xhrFields: {
			withCredentials: true
		},
		crossDomain: true,
		success:function(res){
			window.location.href="login.html";
		},
		error:function(error){
			console.log(error);
		}
	});
})
