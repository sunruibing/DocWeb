$(document).ready(function(){
	//极光登录
	logged();
	/* 按钮发送消息 */
	$("#send").click(function() {
		var msg = $("#sendMsg").val();
		sendSingleMsg(msg);
	});
	$(".leftnav h2").click(function(){
		  $(this).next().slideToggle(200);	
		  $(this).toggleClass("on"); 
	  })
	  $(".leftnav ul li a").click(function(){
		    $("#a_leader_txt").text($(this).text());
	  		$(".leftnav ul li a").removeClass("on");
			$(this).addClass("on");
	  });
   /*关闭对话框*/	
    $('.close').click(function(){
    $('.chat').css('display','none')
    });
    //添加用药时间
    $('.hour1').timepick();
	$('.hour2').timepick();
	$('.hour3').timepick();
	
	//
	var phone = $.cookies.get("phone");
	/* 获取医生信息*/
	if(phone){
		$.ajax({
			url: serUrl+"/FindDoctorByPhone?phone="+phone,  
			type: "post",  
			dataType: "json",
			success:function(data){
	        		  $(".information").html($(".information").html() +  "<img src=" + data.icon +" />" + "<div class='section'>"+"<span>"+data.name + "</span>"+"<li>" +data.section + "</li>"+"</div>");
			        
	        		  $.cookies.set("id", data.id);
	        		  
	        		   var doctorId = $.cookies.get("id");
	        		   if(doctorId){
	        			   $.ajax({
	        				   url:serUrl+"/findMyUser?doctorId="+doctorId,
	        			       Type:"POST",
	        			       dataType:"JSON",
	        			    	success:function(data){
	        			    		$.each(data, function(Index, comt){
	        			    		//console.info(comt['name']);
	        			        	 $(".user").html($(".user").html() +"<ul><li class='userName'>"+comt['name']+"</li></ul>");
	        			    	})
	        			   }
	        			})
	        		  }
			}
		
		});
	}
	   $(".subMenu").click(function () {
	   var page = $(this).attr("data-src");
        if(page){
            $(".admin").load(page);
        }
	   });
	   $.ajax({
			url: serUrl+'/FindHealthAll',  
	        type: 'post',  
	        dataType: 'json',
	        success:function(data){
	        	  $.each(data, function(commentIndex, comment){
	                  
	        		 //console.log("姓名:"+ comment['name'],comment['icon'],comment['tag'].split(","),comment['content'],comment['create_date'],comment['doctex']);
	        		 $("#wwwzzjsnet_zzjs_1").html($("#wwwzzjsnet_zzjs_1").html() + "<ul>"+
					/* "<li>"+'<img src=' +comment["icon"] + '/>'+ */
					 "<a id='docname'>"+comment["name"]+"</a>"+
				     "<p>"+comment['create_date']+"</p></li></ul>"+
		            "<div class='illness'><lable>"+comment['tag'].split(",")+"</lable>"+
     	       		"</div><pre class='neirong'>"+comment['content']+"</pre>"+
		            "<div class='huifu'><span>陈医生:</span><font>"+comment['doctex']+"</font>"+
					"</div>");
	        	  });
	        }
		});
});










