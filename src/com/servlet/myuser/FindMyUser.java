/*package com.servlet.myuser;

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
import com.service.MyUserService;

@WebServlet(name="FindMyUser",urlPatterns={"/findMyUser"})
public class FindMyUser extends HttpServlet {

	*//**
	 * ҽ���˻�ȡ�ҵ��û���Ϣ
	 *//*
	private static final long serialVersionUID = 8275958590754996379L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Integer doctorId = Integer.parseInt(request.getParameter("doctorId"));
		
		MyUserService myUserService = new MyUserService();
		
		try {
		List<Map<String,Object>> myuserlist	=   myUserService.queryMyUser(doctorId);
		response.getWriter().print(new Gson().toJson(myuserlist));	
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().print(1);//error
		}
	}
}
*/