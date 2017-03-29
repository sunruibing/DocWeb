/*package com.servlet.myuser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.MyUser;
import com.service.MyUserService;
@WebServlet(name="AddMyUser",urlPatterns={"/addMyUser"})
public class AddMyUser extends HttpServlet {

	*//**
	 * ҽ��������û�
	 *//*
	private static final long serialVersionUID = -7261233476005968328L;
   
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("application/json");
		try {
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			Integer doctorId = Integer.parseInt(request.getParameter("doctorId"));
			MyUser my = new MyUser();
			my.setUserId(userId);
			my.setDoctorId(doctorId);
			MyUserService myUserService = new MyUserService();
			
			myUserService.insertMyUuser(my);
			response.getWriter().print(0);//�ɹ�
		} catch (Exception e) {
             e.printStackTrace();
             response.getWriter().print(1);//ʧ��
		}
	}
}
*/