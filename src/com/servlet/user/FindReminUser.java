package com.servlet.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.service.UserService;

@WebServlet(name="FindReminUser",urlPatterns={"/FindReminUser"})
public class FindReminUser extends HttpServlet {

	/**
	 * 管理后台获取用户信息
	 */
	private static final long serialVersionUID = 836086660248823676L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	 
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	  
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		UserService userService = new UserService();
		
		try {
			List<Map<String, Object>> list = userService.queryUser();
			
			response.getWriter().print(new Gson().toJson(list));// success
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().print(1);//获取失败
		}
	}

}
