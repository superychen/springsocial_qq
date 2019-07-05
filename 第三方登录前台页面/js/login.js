$(function(){
layui.use('layer',function(){
 var flag = 0;
 var layer = layui.layer;  
 $('#yanzhen').pointsVerify({
      defaultNum : 4,    //默认的文字数量
      checkNum : 2,  //校对的文字数量
      vSpace : 5,    //间隔
      imgName : ['1.jpg', '2.jpg'],
      imgSize : {
         width: '400px',
         height: '200px',
      },
      barSize : {
         width : '400px',
         height : '40px',
      },
      ready : function() {
        return flag;
      },
      success : function() {
         //alert('验证成功，添加你自己的代码！');
         //......后续操作
         flag=1;
         return flag;
      },
      error : function() {
//          alert('验证失败！');
		layer.tips('请点击正确的验证顺序',$('#yanzhen'));
      }
   });
   $('#sub').click(function(){
	   	var loginName = $('#loginName').val();
		var Upassword = $('#Upassword').val();
		var allData = {
　　　　　　　　　　username:loginName,
　　　　　　　　　　password:Upassword
　　　　　　　　　};
   		if(flag==1){
   			$.ajax({
   				url:"http://www.merryyou.cn/authentication/login",
   				type:"post",
   				xhrFields: {
           			withCredentials: true
	       		},
				crossDomain: true,
				dataType:"json",
				data:JSON.stringify(allData),
				contentType:'application/json;charset=utf-8',
				success:function(res){
					window.location.href="voting_index.html"
					console.log(res);
				},
				error:function(error){
					console.log(error);
					$('#login_error').text("登录失败");
				}
   			})
   		}else{
   			if(loginName === null || loginName === ''){
   				layer.tips('登录名不能为空',$('#loginName'));
   			}else if(Upassword === null || Upassword === ''){
   				layer.tips('密码输入不能为空',$('#Upassword'));
   			}else{
   				layer.tips('请点击正确的验证顺序',$('#yanzhen'));
   			}
   		}
   })
})

})
