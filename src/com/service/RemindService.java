package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.po.Remind;
import com.util.DBUtil;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice 2016年10月5日
 */
public class RemindService {
	public int insertRemindByUserId(Remind remind) {
		String sql = " insert into remind(remind_date, time1, time2, time3, content1, content2, content3, user_id, timestamp) values("
				+ "'" + remind.getRemindDate() + "'" + ", " + "'"
				+ remind.getTime1() + "'" + ", " + "'" + remind.getTime2() + "'"
				+ ", " + "'" + remind.getTime3() + "'" + ", " + "'"
				+ remind.getContent1() + "'" + ", " + "'" + remind.getContent2()
				+ "'" + ", " + "'" + remind.getContent3() + "'" + ", "
				+ remind.getUserId() + ", " + "'" + remind.getTimestamp() + "'"
				+ "); ";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;// success
		} catch (SQLException e) {
			e.printStackTrace();
			return 2;// error
		}
	}
	public Integer queryRemindIdByDateAndUserId(String remindDate,
			Integer userId) throws SQLException {
		String sql = " select id from remind where remind_date = " + "'"
				+ remindDate + "'" + " and user_id = " + userId + " ";
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
	public Remind queryRemindById(Integer id) throws SQLException {
		String sql = " select remind_date, time1, time2, time3, content1, content2, content3, timestamp from remind where id = "
				+ id + " ";
		DBUtil dbUtil = new DBUtil(sql);
		ResultSet result = null;
		Remind remind = new Remind();
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				remind.setRemindDate(result.getString("remind_date"));
				remind.setTime1(result.getString("time1"));
				remind.setTime2(result.getString("time2"));
				remind.setTime3(result.getString("time3"));
				remind.setContent1(result.getString("content1"));
				remind.setContent2(result.getString("content2"));
				remind.setContent3(result.getString("content3"));
				remind.setTimestamp(result.getString("timestamp"));
			}
			return remind;
		} catch (SQLException e) {
			e.printStackTrace();
			return remind;
		} finally {
			if (result != null)
				result.close();
			if (dbUtil != null)
				dbUtil.close();
		}
	}
	public int deleteRemindListByTimeStamp(String timeStamp) {
		String sql = " delete from remind where timestamp = " + "'" + timeStamp
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
}
