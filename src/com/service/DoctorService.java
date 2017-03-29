package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.po.Comment;
import com.po.Doctor;
import com.po.User;
import com.util.DBUtil;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice 2016年9月20日
 */
public class DoctorService {
	public List<Map<String, Object>> queryDoctorList() throws SQLException {
		String sql = " SELECT id, username, name, icon, title, section, bio, adept, chat_cost, hospital, city FROM doctor ";
		DBUtil dbUtil = new DBUtil(sql);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", result.getInt("id"));
				map.put("username", result.getString("username"));
				map.put("name", result.getString("name"));
				map.put("icon", result.getString("icon"));
				map.put("title", result.getString("title"));
				map.put("section", result.getString("section"));
				map.put("bio", result.getString("bio"));
				map.put("adept", result.getString("adept"));
				map.put("chatCost", result.getString("chat_cost"));
				map.put("hospital", result.getString("hospital"));
				map.put("city", result.getString("city"));
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
	public Doctor queryDoctorById(Integer doctorId) throws SQLException {
		String sql = " SELECT name, username, icon, title, section, hospital, chat_cost, call_cost, seniority, bio, adept, comment_count FROM doctor WHERE id = "
				+ doctorId + " ";
		DBUtil dbUtil = new DBUtil(sql);
		Doctor doctor = new Doctor();
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				String name = result.getString("name");
				String userName = result.getString("username");
				String icon = result.getString("icon");
				String title = result.getString("title");
				String section = result.getString("section");
				String hospital = result.getString("hospital");
				String chatCost = result.getString("chat_cost");
				String callCost = result.getString("call_cost");
				String seniority = result.getString("seniority");
				String bio = result.getString("bio");
				String adept = result.getString("adept");
				String commentCount = result.getString("comment_count");
				doctor.setName(name);
				doctor.setUsername(userName);
				doctor.setIcon(icon);
				doctor.setTitle(title);
				doctor.setSection(section);
				doctor.setHospital(hospital);
				doctor.setChatCost(chatCost);
				doctor.setCallCost(callCost);
				doctor.setSeniority(seniority);
				doctor.setBio(bio);
				doctor.setAdept(adept);
				doctor.setCommentCount(commentCount);
			}
			return doctor;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null)
				result.close();
			if (dbUtil != null)
				dbUtil.close();
		}
		return doctor;
	}
	public List<Map<String, Object>> queryThisDoctorAllCommentByDoctorId(
			Integer doctorId) throws SQLException {
		String sql = " SELECT comment.id, `user`.`name` AS user_name, `user`.icon AS user_icon, `comment`.content, comment.time FROM `comment`, doctor, `user` WHERE comment.doctor_id = doctor.id AND `comment`.user_id = `user`.id AND doctor.id = "
				+ doctorId + " ";
		DBUtil dbUtil = new DBUtil(sql);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				Integer id = result.getInt("id");
				String userName = result.getString("user_name");
				String userIcon = result.getString("user_icon");
				String content = result.getString("content");
				String time = result.getString("time");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", id);
				map.put("userName", userName);
				map.put("userIcon", userIcon);
				map.put("content", content);
				map.put("time", time);
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
	public List<Map<String, Object>> queryDoctorListByUserId(Integer userId)
			throws SQLException {
		String sql = " SELECT mydoctor.doctor_id, doctor.icon, doctor.username, doctor.name, doctor.title, doctor.section, doctor.chat_cost, doctor.adept FROM mydoctor,doctor WHERE mydoctor.doctor_id = doctor.id AND mydoctor.user_id = "
				+ userId + " ";
		DBUtil dbUtil = new DBUtil(sql);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("doctorId", result.getInt("doctor_id"));
				map.put("doctorIcon", result.getString("icon"));
				map.put("doctorUserName", result.getString("username"));
				map.put("doctorName", result.getString("name"));
				map.put("doctorTitle", result.getString("title"));
				map.put("doctorSection", result.getString("section"));
				map.put("chatCost", result.getString("chat_cost"));
				map.put("adept", result.getString("adept"));

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
	public int insertMyDoctor(Integer userId, Integer doctorId) {
		String sql = " insert into mydoctor(user_id, doctor_id)values(" + userId
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
	}
	public Integer queryMyDoctorByUserIdAndDoctorId(Integer userId,
			Integer doctorId) {
		String sql = " select id from mydoctor where user_id = " + userId
				+ " and doctor_id = " + doctorId + " ";
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
	public int deleteMyDoctor(Integer userId, Integer doctorId) {
		String sql = " delete from mydoctor where user_id = " + userId
				+ " and doctor_id = " + doctorId + " ";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;// success
		} catch (SQLException e) {
			e.printStackTrace();
			return 2;// error
		}
	}
	// Random Select Three Doctor
	public List<Map<String, Object>> queryDoctorRandom() throws SQLException {
		String sql = " select id, username, `name`, icon, title, section, bio, adept, chat_cost, hospital from doctor order by rand() limit 3 ";
		DBUtil dbUtil = new DBUtil(sql);
		ResultSet result = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", result.getInt("id"));
				map.put("username", result.getString("username"));
				map.put("name", result.getString("name"));
				map.put("icon", result.getString("icon"));
				map.put("title", result.getString("title"));
				map.put("section", result.getString("section"));
				map.put("bio", result.getString("bio"));
				map.put("adept", result.getString("adept"));
				map.put("chatCost", result.getString("chat_cost"));
				map.put("hospital", result.getString("hospital"));
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
	//用户给医生添加评论
	public int insertComment(Comment comment) throws SQLException{
		
		String sql="INSERT INTO comment(user_id,doctor_id,content,time) VALUES("+comment.getUserId()+","+comment.getDoctorId()+","+"'"+comment.getContent()+"'"+","+"'"+comment.getTime()+"'"+")";
		DBUtil dbUtil = new DBUtil(sql);
		dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 0;//成功
		} catch (Exception e) {
			return 1;//失败
		}
	}
	//医生端获取医生信息
	public Doctor queryDoctorByPhone(String phone) throws SQLException {
		String sql = "select id,username,password,name,icon,title,section from doctor where username = " + phone + "";
		DBUtil dbUtil = new DBUtil(sql);
		Doctor doctor = new Doctor();
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				Integer id = result.getInt("id");
				String username = result.getString("username");
				String password = result.getString("password");
				String name = result.getString("name");
				String icon = result.getString("icon");
				String title = result.getString("title");
				String section = result.getString("section");
				
				doctor.setId(id);
				doctor.setName(name);
				doctor.setUsername(username);
				doctor.setPassword(password);
				doctor.setIcon(icon);
				doctor.setTitle(title);
				doctor.setSection(section);
				return doctor;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return doctor;
		} finally {
			if (result != null)
				result.close();
			if (dbUtil != null)
				dbUtil.close();
		}
		return doctor;
	}
}
