package com.servlet.Feedback;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.Feedback;
import com.service.FeedbackService;
import com.util.DateUtil;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice 2016年9月22日
 */
@WebServlet(name = "AddFeedback", urlPatterns = {"/AddFeedback"})
public class AddFeedback extends HttpServlet {
	private static final long serialVersionUID = -9059566419703552477L;
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
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		System.out.println(userId);
		String content = request.getParameter("content");
		System.out.println(content);
		if (2 <= content.length()) {
			try {
				Feedback feedback = new Feedback();
				feedback.setUserId(userId);
				feedback.setContent(content);
				feedback.setTime(DateUtil.DateToString(new Date()));
				FeedbackService adviseservice = new FeedbackService();
				int msg = adviseservice.insertFeedback(feedback);
				if (1 == msg) {
					response.getWriter().print(0);
				} else {
					response.getWriter().print(2);
				}
			} catch (Exception e) {
				response.getWriter().print(2);
			}
		} else {
			response.getWriter().print(1);
		}
	}
}
