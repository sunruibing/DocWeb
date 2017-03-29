package com.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.User;
import com.service.UserService;

import redis.clients.jedis.Jedis;

/**
 * @author sun
 *
 */
@WebServlet(name = "BindingPhone", urlPatterns = {"/BindingPhone"})
public class BindingPhone extends HttpServlet {
	private static final long serialVersionUID = -2024535499763819269L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		request.setCharacterEncoding("utf-8");
		String code = request.getParameter("code");
		String phone = request.getParameter("phone");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		if (code != null && jedis.get(phone).equals(code)) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			try {
				UserService userService = new UserService();
				User user = userService.queryUserById(id);
				String mobileNumber = user.getPhone();
				if (mobileNumber != null || "".equals(mobileNumber)) {
					int msg = userService.insertBinding(id, phone);
					if (msg == 1) {
						response.getWriter().print(0);
					} else {
						response.getWriter().print(2);
					}
				}
				response.getWriter().print(1);
			} catch (Exception e) {
				response.getWriter().print(2);
			}
		} else {
			response.getWriter().print(1);
		}
	}
}