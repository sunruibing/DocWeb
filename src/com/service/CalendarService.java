package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.DBUtil;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice 2016年10月6日
 */
public class CalendarService {

	public List<Map<String, Object>> queryCalendarListByDateAndUserId(
			String date, Integer userId) throws SQLException {

		String sql = " SELECT id, date, registration_id, remind_id, health_id FROM calendar WHERE date like "
				+ "'" + date + "%'" + " AND user_id = " + userId + " ";
		DBUtil dbUtil = new DBUtil(sql);

		ResultSet result = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			result = dbUtil.pst.executeQuery();

			while (result.next()) {

				Map<String, Object> map = new HashMap<String, Object>();

				map.put("id", result.getInt("id"));
				map.put("date", result.getString("date"));
				map.put("registrationId", result.getInt("registration_id"));
				map.put("remindId", result.getInt("remind_id"));
				map.put("healthId", result.getInt("health_id"));

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
	public Map<String, Object> queryCalendarByDateAndUserId(String date,
			Integer userId) throws SQLException {

		String sql = " select calendar.registration_id, calendar.remind_id, remind.time1, remind.time2, remind.time3, calendar.health_id, health.tag, health.content from calendar left join registration on calendar.registration_id = registration.id left join remind on calendar.remind_id = remind.id left join health on calendar.health_id = health.id where calendar.date = "
				+ "'" + date + "'" + " and calendar.user_id = " + userId + " ";
		DBUtil dbUtil = new DBUtil(sql);

		ResultSet result = null;

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			result = dbUtil.pst.executeQuery();

			while (result.next()) {

				map.put("registrationId", result.getInt("registration_id"));
				map.put("remindId", result.getInt("remind_id"));
				map.put("time1", result.getString("remind.time1"));
				map.put("time2", result.getString("remind.time2"));
				map.put("time3", result.getString("remind.time3"));
				map.put("health_id", result.getString("calendar.health_id"));
				map.put("tag", result.getString("health.tag"));
				map.put("content", result.getString("health.content"));

			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null)
				result.close();
			if (dbUtil != null)
				dbUtil.close();
		}
		return map;
	}
	public Integer queryCalendarIdByDateAndUserId(String date, Integer userId)
			throws SQLException {
		String sql = " SELECT id FROM calendar WHERE date = " + "'" + date + "'"
				+ " AND user_id = " + userId + " ";
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
	public int insertRegistrationId(String date, Integer registrationId,
			Integer userId) {
		String sql = " INSERT INTO calendar(date, registration_id, user_id) VALUES("
				+ "'" + date + "'" + ", " + registrationId + ", " + userId
				+ ") ";
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
	public int updateRegistrationId(String date, Integer registrationId,
			Integer userId) {
		String sql = " update calendar set registration_id = " + registrationId
				+ " where user_id = " + userId + " and date = " + "'" + date
				+ "'" + " ";
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
	public int insertHealthId(String date, Integer healthId, Integer userId) {
		String sql = " insert into calendar(date, health_id, user_id) values("
				+ "'" + date + "'" + ", " + healthId + ", " + userId + ") ";
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
	public int updateHealthId(String date, Integer healthId, Integer userId) {

		String sql = " update calendar set health_id = " + healthId
				+ " where user_id = " + userId + " and date = " + "'" + date
				+ "'" + " ";
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
	public int insertRemindId(String date, Integer remindId, Integer userId) {
		String sql = " insert into calendar(date, remind_id, user_id) values("
				+ "'" + date + "'" + ", " + remindId + ", " + userId + ") ";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;// success
		} catch (SQLException e) {
			e.printStackTrace();
			return 2;// error
		}
	}
	public int updateRemindId(String date, Integer remindId, Integer userId) {
		String sql = " update calendar set remind_id = " + remindId
				+ " where user_id = " + userId + " and date = " + "'" + date
				+ "'" + " ";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;// success
		} catch (SQLException e) {
			e.printStackTrace();
			return 2;// error
		}
	}
	public int updateRegistrationId(Integer userId, Integer registrationId) {
		String sql = " update calendar set registration_id = null where user_id = "
				+ userId + " and  registration_id = " + registrationId + " ";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;// success
		} catch (SQLException e) {
			e.printStackTrace();
			return 2;// error
		}
	}
}
