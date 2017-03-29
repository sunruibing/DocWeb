package com.util;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice
 *         2016年12月16日
 */
@WebServlet(name = "Launch", urlPatterns = {"/launch"})
public class Launch extends HttpServlet {
	private static final long serialVersionUID = -625511740909593856L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		DBUtil dbUtil = new DBUtil("select id, img, date from launch");
		ResultSet result = null;
		String img;
		String date;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				img = result.getString("img");
				date = result.getString("date");
				resp.getWriter().print(
						"{\"img\":\"" + img + "\",\"date\":\"" + date + "\"}");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null)
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
					dbUtil.close();
				}
			if (dbUtil != null)
				dbUtil.close();
		}
	}
}
