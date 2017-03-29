package com.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.User;
import com.google.gson.Gson;
import com.service.UserService;
import com.util.StringUtil;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = -8081147951073848386L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		try {
			if ((phone != null && password != null)
					&& (!phone.isEmpty() && !password.isEmpty())) {
				UserService userService = new UserService();
				User DBuser = userService.queryUserByPhone(phone);
				if (StringUtil.MD5Encode(password)
						.equals(DBuser.getPassword())) {
					DBuser.setPassword("");
					String gson = new Gson().toJson(DBuser);
					response.getWriter().print(gson);
				} else {
					response.getWriter().print(1);
				}
			} else {
				response.getWriter().print(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(2);
		}
	}
}
