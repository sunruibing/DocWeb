/*package com.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.UserService;
@WebServlet(name="AddMyUser",urlPatterns={"/AddMyUser"})
public class AddMyUser extends HttpServlet {

	*//**
	 * 医生端添加用户列表
	 *//*
	private static final long serialVersionUID = -315008480194893282L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Integer doctorId = Integer.parseInt(request.getParameter("doctorId"));
		
		UserService userService = new UserService(); 
      	Integer  id= userService.queryMyUserByUserIdAndDoctorId(userId, doctorId);
		if (id != null && id==0 ) {
		int msg =  userService.insertMyUser(userId, doctorId);
		    if(msg ==1){
		    	response.getWriter().print(0);//success
		    }else{
		    	response.getWriter().print(1);//errror
		    }
		}else{
			response.getWriter().print(1);//添加失败
		}
	}
}
*/