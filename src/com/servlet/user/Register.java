package com.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.po.User;
import com.service.UserService;
import com.util.RedisUtil;

/**
 * @author FFFF
 *
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {
	private static final long serialVersionUID = -2024535499763819269L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String userJson = request.getParameter("user");
		String code = request.getParameter("code");
		User user = new Gson().fromJson(userJson, User.class);
		String phone = user.getPhone();
		if (code != null && RedisUtil.get(phone).equals(code)) {
			try {
				UserService userService = new UserService();
				User DBuser = userService.queryUserByPhone(user.getPhone());
				if (DBuser.getPhone() == null || "".equals(DBuser.getPhone())) {
					user.setRole(1);
					int msg = userService.addUser(user);
					if (1 == msg) {
						response.getWriter().print(0);
					} else {
						response.getWriter().print(2);
					}
				} else {
					response.getWriter().print(1);
				}
			} catch (Exception e) {
				response.getWriter().print(2);
			}
		} else {
			response.getWriter().print(1);
		}
	}
}
