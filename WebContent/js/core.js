//初始化
JIM.init();

var auth_platform;
$.ajax({
	url:serUrl+'/Singnature',
	type:'post',
	dataType:'json',
	success:function(data){
		auth_platform = data;
	}
	})

function resp(data) {
	console.log("resp : " + JSON.stringify(data));
	//getConversations(data);//调用获取会话列表函数
	
}

function ack(data) {
	console.log("ack : " + JSON.stringify(data));
}

function timeout(data) {
	console.log("timeout : " + JSON.stringify(data));
}

/*登录*/
function login() {

	//获取参数
	var phone = $.trim($("#phone").val());
	var password = $.trim($("#password").val());

	//调用IM登录函数
	JIM.login(phone, password, auth_platform, function(data) {
	    console.log("login success:" + JSON.stringify(data));

	    if("success" == data.message){
	    	$.cookies.set("phone", phone);
	    	$.cookies.set("password", password);
	    	location.href = "index.html";
	    }else{
	    	alert("登录失败");
	    }
	    
	}, ack, timeout);
};
/*保持登录*/
function logged(){
	
	var phone = $.cookies.get("phone");
	var password = $.cookies.get("password");
	
	JIM.login(phone, password, auth_platform, function(data) {
	    console.log("login success:" + JSON.stringify(data));

	    console.log("保持登录:" + data.message);

	   // JIM.getConversations(resp, ack, timeout);//输出获取会话列表函数
        
	    var notif; // 消息对象
		var i = 0;
	    // 聊天消息监听接收消息
	    JIM.onMsgReceive(function(data) {
	    	
	    //	var fromId = 
	    	$.cookies.set("phne",data.from_name);
	    	
	    	$("#nowChat").val(data.from_name);
	    	var chatContent = $("#ChatContent").find("#chat"+data.from_id);//初始化文本聊天记录框
			
	    	if(chatContent.length == 0){//不存在
				$("#ChatContent").append("<div id='chat"+data.from_id+"'></div>");
				var chatContent = $("#ChatContent").find("#chat"+data.from_id);//初始化文本聊天记录框	
			}
	    	
	    	if(data.msg_type == "text"){
	    		var msg = '<span class="chatter_msg_item chatter_msg_item_admin"><a href="" class="chatter_avatar"><img src="http://ogtsfrmaa.bkt.clouddn.com/620170227132100" /></a>'+data.msg_body.text+'</span>'; 
	    		
	    	}else{
	    		var msg ='<span class="chatter_msg_item chatter_msg_item_admin"><a href="" class="chatter_avatar"><img src="http://www.gbtags.com/gb/networks/avatars/1679dd15-8c8e-4c35-ae18-365c523562be.JPG" /></a><img src="'+data.msg_body.media_url+'" class="talkImg"/></span>';
	    	}
	    /*	//消息提示    	
	        if(window.Notification) { // 判断浏览器是否支持Notification特性，Chrome与Firefox支持，IE内核暂不支持
			   if(Notification.permission == 'granted') { // 判断浏览器是否允许此站点发送桌面消息；granted为允许
				  notif = new Notification(data.from_id, {
						body: data.msg_body.text,
						icon: 'http://ogtsfrmaa.bkt.clouddn.com/5320170313104639',
						tag: ++i // ID，如果ID重复则前者会被覆盖，如果ID不重复则一直叠加显示。
						});
						notif.onclick = function() { // 当Notification被单击时触发
							location.href = "index.html";
						}
					} else {
						Notification.requestPermission();
					}
				}*/
	    	    chatContent.append(msg);//追加聊天消息
	    	    //savemsg(msg);//保存聊天消息
	    	    
			var leftli = $(".user");
			
			var friendName ='<ul >';
				friendName+='<li class="userName">'+data.from_id+'</li>';
				friendName+='</ul>';
				var nameArrys = leftli.find(".userName");
				var flag = false;
				if(nameArrys.length > 0){
					$.each(nameArrys,function(i,obj){
						var userName = $(this).text();
						if(userName == data.from_id){
							flag = true; 
						}
					});
				}
				if(!flag){
					leftli.append(friendName);
					
					var li = leftli.find("li");
					li.unbind("click");
					li.on("click",function(){//用户列表单击
						var from_id = $.trim($(this).text());
						$('.chat').css('display','block').removeClass('mins');
				        $('.username').html($.trim($(this).html())); 	
				        $(".chat").show();
						var nowChat = $("#ChatContent").find("#chat"+from_id);
						if(nowChat.length == 0){//不存在
							$("#ChatContent").append("<div id='chat"+from_id+"'></div>");
						}else{
							//showMsg('ChatContent');//显示聊天记录
							$("#ChatContent").find("div").hide();
							nowChat.show();
						}
						$("#nowChat").val(from_id);
					});
				}
	      console.log('onMsgReceive : ' + JSON.stringify(data)); 
	    });
	    
	    // 事件监听
	    JIM.onEvent(function(data) {
	      console.log('onEvent : ' + JSON.stringify(data));
	    }); 
	}, ack, timeout);
}

/*获取用户个人信息*/
function getUserInfo() {
	var friend_name = $.cookies.get("username");
	console.log('getUserInfo...');
	JIM.getUserInfo(friend_name, resp, ack, timeout);
}

//发送事件
function sendSingleMsg(obj) { 
	
	var nowChat = $.trim($("#nowChat").val());
	var chatContent = $("#ChatContent").find("#chat"+nowChat);//初始化文本聊天记录框
	if (obj.length > 0) {                                       
		msg = obj;                                              
		JIM.sendSingleMsg(nowChat, msg, resp, ack,timeout); 
			
			var msg ='<span class="chatter_msg_item chatter_msg_item_user"><a href="" class="chatter_avatar"><img src="http://ogtsfrmaa.bkt.clouddn.com/5320170313104639"></a>'+obj+'</span>';
			                                
			chatContent.append(msg);//追加聊天消息
			
			//savemsg(msg);//保存聊天消息
			
		$("#fason").val("");                                    
	}else{                                                      
		alert("请输入消息内容");                                       
	}                                                           
}












