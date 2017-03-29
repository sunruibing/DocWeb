package com.servlet.doctor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.po.Doctor;
import com.service.DoctorService;

@WebServlet(name="FindDoctorByPhone",urlPatterns={"/FindDoctorByPhone"})
public class FindDoctorByPhone extends HttpServlet {

	/**
	 * 医生端医生登陆
	 */
	private static final long serialVersionUID = 2433003159659874628L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		
		String phone = request.getParameter("phone");
		
		DoctorService doctorService = new DoctorService();
		
		try {
			
		Doctor doctor = doctorService.queryDoctorByPhone(phone);
		response.getWriter().print(new Gson().toJson(doctor));// success
		
		} catch (Exception e) {
			   response.getWriter().print("1");//登陆失败
		}
	}

}
