package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.po.Registration;
import com.po.User;
import com.util.DBUtil;
import com.util.StringUtil;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice 2016年9月23日
 */
public class UserService {

	public User queryUserByPhone(String phone) throws SQLException {
		String sql = "select id,role,phone,password,name,gender,age,icon from user where phone = " + phone + "";
		DBUtil dbUtil = new DBUtil(sql);
		User user = new User();
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				Integer id = result.getInt("id");
				Integer role = result.getInt("role");
				String DBphone = result.getString("phone");
				String password = result.getString("password");
				String name = result.getString("name");
				String gender = result.getString("gender");
				int age = result.getInt("age");
				String icon = result.getString("icon");
				user.setId(id);
				user.setRole(role);
				user.setPhone(DBphone);
				user.setPassword(password);
				user.setName(name);
				user.setGender(gender);
				user.setAge(age);
				user.setIcon(icon);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return user;
		} finally {
			if (result != null)
				result.close();
			if (dbUtil != null)
				dbUtil.close();
		}
		return user;
	}
	public User queryUserById(Integer id) throws SQLException {
		String sql = "select id,phone,password from user where id = " + id + " ";
		DBUtil dbUtil = new DBUtil(sql);
		User user = new User();
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				user.setId(result.getInt("id"));
				user.setPhone(result.getString("phone"));
				user.setPassword(result.getString("password"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null)
				result.close();
			if (dbUtil != null)
				dbUtil.close();
		}
		return user;
	}
	public int addUser(User user) {
		String password = StringUtil.MD5Encode(user.getPassword());
		String sql = "insert into user(phone,password)values(" + "'" + user.getPhone() + "'" + "," + "'" + password
				+ "'" + ")";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 2;
		} finally {
			if (dbUtil != null)
				dbUtil.close();
		}
	}
	public int updataPassword(String password, String phone) {
		String sql = "UPDATE user SET password = " + "'" + password + "'" + " WHERE phone = " + "'" + phone + "'" + " ";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;
		} catch (Exception e) {
			return 2;
		} finally {
			if (dbUtil != null)
				dbUtil.close();
		}
	}
	public int updataUserName(Integer id, String name) {
		String sql = "UPDATE user SET  name= " + "'" + name + "'" + " WHERE id = " + id + " ";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 2;
		} finally {
			if (dbUtil != null)
				dbUtil.close();
		}
	}
	public int updataUserGender(Integer id, String gender) {
		String sql = "UPDATE user SET gender = " + "'" + gender + "'" + "  WHERE id = " + id + " ";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 2;
		} finally {
			if (dbUtil != null)
				dbUtil.close();
		}
	}
	public int insertGenderAndName(User user) {
		String sql = "UPDATE user SET name = " + "'" + user.getName() + "'" + " ,gender = " + "'" + user.getGender()
				+ "'" + " WHERE id = " + user.getId() + " ";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 2;
		} finally {
			if (dbUtil != null)
				dbUtil.close();
		}
	}
	public String queryIcon(Integer id) throws SQLException {
		String sql = "SELECT icon FROM user WHERE id = " + id + " ";
		DBUtil dbUtil = new DBUtil(sql);
		ResultSet result = null;
		String icon = "";
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				icon = result.getString("icon");
				return icon;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result != null)
				result.close();
			if (dbUtil != null)
				dbUtil.close();
		}
		return icon;
	}
	public int updataIcon(Integer id, String icon) {
		String sql = "UPDATE user SET icon = " + "'" + icon + "'" + " WHERE id= " + id + " ";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		} finally {
			if (dbUtil != null)
				dbUtil.close();
		}
	}
	public int updataPassword(Integer id, String password) {
		String sql = "UPDATE user SET password = " + "'" + password + "'" + " WHERE id=" + id + "";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		} finally {
			if (dbUtil != null)
				dbUtil.close();
		}
	}
	public int insertBinding(Integer id, String phone) {
		String sql = "UPDATE user SET phone = " + "'" + phone + "'" + " WHERE id=" + id + "";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;
		} catch (Exception e) {
			return 2;
		} finally {
			if (dbUtil != null)
				dbUtil.close();
		}
	}
	
	//医生端查看挂号患者
	public List<Map<String, Object>> queryUser() throws SQLException {
		String sql = "SELECT id,user_id,city,section,reservation_date,name,gender,age,phone FROM registration order by id desc";
		DBUtil dbUtil = new DBUtil(sql);
		ResultSet result = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", result.getInt("id"));
				map.put("user_id", result.getInt("user_id"));
				map.put("city", result.getString("city"));
				map.put("name", result.getString("name"));
				map.put("section", result.getString("section"));
				map.put("reservation_date", result.getString("reservation_date"));
				map.put("gender", result.getString("gender"));
				map.put("age",result.getString("age"));
				map.put("phone", result.getString("phone"));
				list.add(map);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null)
				result.close();
			if (dbUtil != null)
				dbUtil.close();
		}
		return list;
	}
	//医生端获取用户列表
	public Integer queryMyUserByUserIdAndDoctorId(Integer userId,
			Integer doctorId) {
		String sql = " select id from myuser where userId = " + userId
				+ " and doctorId = " + doctorId + " ";
		DBUtil dbUtil = new DBUtil(sql);
		ResultSet result = null;
		Integer id = 0;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				id = result.getInt("id");
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	//医生端添加用户
	/*public int insertMyUser(Integer userId, Integer doctorId) {
		String sql = " insert into myuser(userId, doctorId)values(" + userId
				+ ", " + doctorId + ") ";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;// success
		} catch (SQLException e) {
			e.printStackTrace();
			return 2;// error
		} finally {
			if (dbUtil != null)
				dbUtil.close();
		}
	}*/
}