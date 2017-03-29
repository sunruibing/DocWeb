package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.po.User;
import com.util.DBUtil;

/**
 * @author Administrator
 *
 */
public class RoleService {
	public User queryRoleByPhone(String phone) throws SQLException {
		String sql = "select id,phone,password,name from user where phone = " + phone + "";
		DBUtil dbUtil = new DBUtil(sql);
		User user = new User();
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				Integer id = result.getInt("id");
				String DBphone = result.getString("phone");
				String password = result.getString("password");
				String name = result.getString("name");
				user.setId(id);
				user.setPhone(DBphone);
				user.setPassword(password);
				user.setName(name);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return user;
		} finally {
			if(result != null)result.close();
			if(dbUtil != null)dbUtil.close();
		}
		return user;
	}
}