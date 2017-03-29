package com.servlet.doccomment;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.po.DocComment;
import com.service.DocCommentService;

/**
 * 根据健康Id获取医生的评论信息
 * @author LiD
 */
@WebServlet(name="GetComById",urlPatterns={"/getComment"})
public class GetComById extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1605401559983182069L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String healthId = req.getParameter("healthId");
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		DocCommentService service = new DocCommentService();
		Gson gson = new Gson();
		try {
			List<DocComment> comList = service.getContByHeaId(Integer.parseInt(healthId));
			String json = gson.toJson(comList);
			System.out.println(json);
			resp.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().write(gson.toJson(Boolean.FALSE.toString()));
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
