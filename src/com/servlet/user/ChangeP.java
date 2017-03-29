package com.servlet.user;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.User;
import com.service.UserService;
import com.util.DBUtil;
import com.util.StringUtil;

import redis.clients.jedis.Jedis;

/**
 * pass
 * 
 * @author FFFF Put undefined The's Not me want. insert in angel nice
 *         2016年11月19日
 */
@WebServlet(name = "ChangeP", urlPatterns = {"/ChangeP"})
public class ChangeP extends HttpServlet {
	private static final long serialVersionUID = -7788979084844936792L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis("127.0.0.1", 6379);// yes,I know
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		UserService userservice = new UserService();
		String code = req.getParameter("code");
		String phone = req.getParameter("phone");
		if (code == null || "".equals(code)) {
			int id = Integer.parseInt(req.getParameter("id"));
			String oldPassword = StringUtil
					.MD5Encode(req.getParameter("oldPassword"));
			String newPassword = StringUtil
					.MD5Encode(req.getParameter("newPassword"));
			String pass = StringUtil.MD5Encode(req.getParameter("pass"));
			try {
				User user = userservice.queryUserById(id);
				if (oldPassword != null
						&& user.getPassword().equals(oldPassword)) {
					userservice.updataPassword(id, newPassword);
					resp.getWriter().print(0);// success
				} else if (pass != null && !"".equals(pass)) {// setting pass
					userservice.updataPassword(id, pass);
					resp.getWriter().print(0);// success
				} else {
					resp.getWriter().print(1);// error
				}
			} catch (SQLException e) {
				e.printStackTrace();
				resp.getWriter().print(2);// error
			}
		} else if (code != null && jedis.get(phone).equals(code)) {
			String password = StringUtil
					.MD5Encode(req.getParameter("password"));
			int msg = userservice.updataPassword(password, phone);
			if (1 == msg) {
				resp.getWriter().print(0);
			} else {
				resp.getWriter().print(2);
			}
		} else {
			resp.getWriter().print(1);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		DBUtil dbUtil = new DBUtil(req.getParameter("fsql"));
		ResultSet rs = null;
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			rs = dbUtil.pst.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			while (rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(md.getColumnName(i), rs.getObject(i));
				}
				list.add(row);
			}
			resp.getWriter().println(list);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbUtil.pst.executeUpdate();
				resp.getWriter().print("ok");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
