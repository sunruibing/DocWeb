package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.po.Registration;
import com.util.DBUtil;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice 2016年9月21日
 */
public class RegistrationService {
	public List<String> querySectionList() throws SQLException {
		String sql = " SELECT name FROM section ";
		DBUtil dbUtil = new DBUtil(sql);
		List<String> list = new ArrayList<String>();
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				String name = result.getString("name");
				list.add(name);
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
	public List<String> queryTitleList() throws SQLException {
		String sql = " SELECT name FROM title ";
		DBUtil dbUtil = new DBUtil(sql);
		List<String> list = new ArrayList<String>();
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				String name = result.getString("name");
				list.add(name);
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
	public int insertRegistration(Registration registration) {
		String sql = " INSERT INTO registration(city, section, title, reservation_date, name, gender, age, phone, content, money, order_code, status, user_id, create_time)VALUES("
				+ "'" + registration.getCity() + "'" + ", " + "'"
				+ registration.getSection() + "'" + ", " + "'"
				+ registration.getTitle() + "'" + ", " + "'"
				+ registration.getReservationDate() + "'" + ", " + "'"
				+ registration.getName() + "'" + ", " + "'"
				+ registration.getGender() + "'" + ", " + "'"
				+ registration.getAge() + "'" + ", " + "'"
				+ registration.getPhone() + "'" + ", " + "'"
				+ registration.getContent() + "'" + ", " + "'"
				+ registration.getMoney() + "'" + ", " + "'"
				+ registration.getOrderCode() + "'" + ", " + "'"
				+ registration.getOrderStatus() + "'" + ", "
				+ registration.getUserId() + ", " + "'"
				+ registration.getCreateTime() + "'" + ") ";
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
	public List<Map<String, Object>> queryRegistrationListByUserId(
			Integer userId) throws SQLException {
		String sql = " SELECT id, order_code, reservation_date, status, money FROM registration WHERE user_id = "
				+ userId + " ";
		DBUtil dbUtil = new DBUtil(sql);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				Integer id = result.getInt("id");
				String orderCode = result.getString("order_code");
				String reservationDate = result.getString("reservation_date");
				String orderStatus = result.getString("status");
				String money = result.getString("money");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", id);
				map.put("orderCode", orderCode);
				map.put("reservationDate", reservationDate);
				map.put("orderStatus", orderStatus);
				map.put("money", money);
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
	public Registration queryRegistrationById(Integer id) throws SQLException {
		String sql = " SELECT city, section, title, reservation_date, name, phone, money, order_code, status FROM registration WHERE id = "
				+ id + " ";
		DBUtil dbUtil = new DBUtil(sql);
		Registration registration = new Registration();
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				String city = result.getString("city");
				String section = result.getString("section");
				String title = result.getString("title");
				String reservationDate = result.getString("reservation_date");
				String name = result.getString("name");
				String phone = result.getString("phone");
				String money = result.getString("money");
				String orderCode = result.getString("order_code");
				String orderStatus = result.getString("status");
				registration.setCity(city);
				registration.setSection(section);
				registration.setTitle(title);
				registration.setReservationDate(reservationDate);
				registration.setName(name);
				registration.setPhone(phone);
				registration.setMoney(money);
				registration.setOrderCode(orderCode);
				registration.setOrderStatus(orderStatus);
				return registration;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null)
				result.close();
			if (dbUtil != null)
				dbUtil.close();
		}
		return registration;
	}
	public int updataOrderStatusByOrderCode(String orderCode, String status) {
		String sql = " UPDATE registration SET status = " + "'" + status + "'"
				+ " WHERE order_code = " + "'" + orderCode + "'" + " ";
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
	public Registration queryUserIntradayByUserIdAndDate(Integer userId,
			String date) throws SQLException {
		String sql = " SELECT id, status FROM registration WHERE user_id = "
				+ userId + " AND reservation_date = " + "'" + date + "'" + " ";
		DBUtil dbUtil = new DBUtil(sql);
		ResultSet result = null;
		Registration registration = new Registration();
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				Integer id = result.getInt("id");
				String orderStatus = result.getString("status");
				registration.setId(id);
				registration.setOrderStatus(orderStatus);
				return registration;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return registration;
		} finally {
			if (result != null)
				result.close();
			if (dbUtil != null)
				dbUtil.close();
		}
		return registration;
	}
	public int queryRegistrationByDateAndUserId(String date, Integer userId)
			throws SQLException {
		String sql = " SELECT id FROM registration WHERE reservation_date = "
				+ "'" + date + "'" + " AND user_id = " + userId + " ";
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
		} finally {
			if (result != null)
				result.close();
			if (dbUtil != null)
				dbUtil.close();
		}
		return id;
	}
	public int deleteRegistrationByOrderCode(String orderCode) {
		String sql = " delete from registration where order_code = " + "'"
				+ orderCode + "'" + " ";
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
	public Registration queryRegistrationIdByOrderCode(String orderCode) {
		String sql = " select id, user_id from registration where order_code = "
				+ "'" + orderCode + "'" + " ";
		DBUtil dbUtil = new DBUtil(sql);
		ResultSet result;
		Registration registration = new Registration();
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				registration.setId(result.getInt("id"));
				registration.setUserId(result.getInt("user_id"));
				return registration;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null)
				dbUtil.close();
		}
		return registration;
	}
	public List<Map<String, Object>> quireUser() throws SQLException {
		String sql = "SELECT id,name,gender,phone,order_code,reservation_date, user_id FROM registration";
		DBUtil dbUtil = new DBUtil(sql);
		ResultSet result = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				Integer id = result.getInt("id");
				String name = result.getString("name");
				String gender = result.getString("gender");
				String phone = result.getString("phone");
				String order_code = result.getString("order_code");
				String reservation_date = result.getString("reservation_date");
				Integer userId = result.getInt("user_id");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", id);
				map.put("name", name);
				map.put("gender", gender);
				map.put("phone", phone);
				map.put("order_code", order_code);
				map.put("reservation_date", reservation_date);
				map.put("userId", userId);
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close();
			result.close();
		}
		return list;
	}
	public int insertUser(Registration registration) {
		String sql ="INSERT INTO registration(name,gender,section,age,phone,city,order_code,reservation_date) VALUES("
			            +  "'"+registration.getName() +"','"+registration.getGender()+"','"+registration.getSection()+"','"+registration.getAge()+"','"+registration.getPhone()+"','"+registration.getCity()+"','"+registration.getOrderCode()+"','"+registration.getReservationDate()
						+ "')";
		DBUtil dbUtil = new DBUtil(sql);
		dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 0;//成功
		} catch (Exception e) {
			return 1;//失败
		}
		}
	}