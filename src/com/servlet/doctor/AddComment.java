package com.servlet.doctor;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.Comment;
import com.service.DoctorService;
import com.util.DateUtil;
@WebServlet(name="AddComment",urlPatterns={"/AddComment"})
public class AddComment extends HttpServlet {

	/**
	 * 添加用户评论陌模块
	 */
	private static final long serialVersionUID = 4525319145034757754L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    
    response.setContentType("application/json");
    response.setCharacterEncoding("Utf-8");
    
    Integer userId = Integer.parseInt(request.getParameter("userId"));//获取用户id
    
    Integer doctorId = Integer.parseInt(request.getParameter("doctorId"));//获取医生id

    String  content = request.getParameter("content"); 
    
    DoctorService doctorService = new DoctorService();
    
    try {
    	Comment comment = new Comment();
    	
    	comment.setContent(content);
    	comment.setDoctorId(doctorId);
    	comment.setUserId(userId);
    	comment.setTime(DateUtil.DateToString(new Date()));
    	doctorService.insertComment(comment);
    	
    	response.getWriter().print(0);//添加成功
	} catch (Exception e) {
		response.getWriter().print(1);//添加失败
	}
    }   
}
