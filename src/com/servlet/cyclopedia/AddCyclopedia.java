package com.servlet.cyclopedia;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.Cyclopedia;
import com.service.CyclopediaService;
import com.util.DateUtil;


@WebServlet(name="AddCyclopedia",urlPatterns={"/AddCyclopedia"})
public class AddCyclopedia extends HttpServlet {
	private static final long serialVersionUID = -7607821866418974601L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String icon = request.getParameter("icon");
		String cover = request.getParameter("cover");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Cyclopedia cyclopedia = new Cyclopedia();
		CyclopediaService  cyclopediaService  = new CyclopediaService();
		try {
			cyclopedia.setIcon(icon);
			cyclopedia.setCover(cover);
			cyclopedia.setTitle(title);
			cyclopedia.setContent(content);
			cyclopedia.setTime(DateUtil.DateToString(new Date()));
			cyclopediaService.insertCyclopedia(cyclopedia);
			response.getWriter().print(0);
		} catch (Exception e) {
			response.getWriter().print(1);
		}
	}
}
