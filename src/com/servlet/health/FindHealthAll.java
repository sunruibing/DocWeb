package com.servlet.health;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.service.HealthService;
@WebServlet(name="FindHealthAll",urlPatterns={"/FindHealthAll"})
public class FindHealthAll extends HttpServlet {

	/**
	 * 医生端获取医生信息
	 */
	private static final long serialVersionUID = -6767146060751209419L;
   
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		HealthService healthService = new HealthService();
		
		try {
		List<Map<String,Object>> healthlist = healthService.queryHealthAll();
		response.getWriter().print(new Gson().toJson(healthlist));// success
		
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(1);
		}
	}
}
