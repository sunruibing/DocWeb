package com.servlet.answers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.po.Answers;
import com.service.AnswersService;


/**
 * @author FFFF
 */
@WebServlet(name="FindAnswersList", urlPatterns={"/FindAnswersList"})
public class FindAnswersList extends HttpServlet {
	private static final long serialVersionUID = -3347863058021854975L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		Integer diseaseId = Integer.parseInt(request.getParameter("id"));
		try {
			AnswersService answersService = new AnswersService();
			List<Answers> answersList = answersService.queryAnswersList(diseaseId);
			response.getWriter().print(new Gson().toJson(answersList));
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().print(2);
		}
	}
	@Override
	public void destroy() {
		super.destroy();
	}
}
