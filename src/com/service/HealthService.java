package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.po.Health;
import com.util.DBUtil;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice 2016骞�10鏈�5鏃�
 */
public class HealthService {
	public int insertHealth(Health health) {
		String sql = " insert into health(create_date, tag, content, user_id) values("
				+ "'" + health.getCreateDate() + "'" + ", " + "'"
				+ health.getTag() + "'" + ", " + "'" + health.getContent() + "'"
				+ ", " + "'" + health.getUserId() + "'" + ") ";
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
	public Integer queryHealthByDateAndUserId(String date, Integer userId)
			throws SQLException {
		String sql = " select id from health where create_date = " + "'" + date
				+ "'" + " and user_id = " + userId + " ";
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
	public int deleteHealthById(Integer id) {
		String sql = " delete from health where id = " + id + " ";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 1;// success
		} catch (SQLException e) {
			e.printStackTrace();
			return 2;// error
		}
	}
	public List<Map<String, Object>> queryHealthAll() throws SQLException {
		 String sql ="select health.tag,health.content,health.create_date,`user`.icon,`user`.`name`,doccomment.content doctex FROM  health,`user`,doccomment WHERE user_id=`user`.id AND healthId=health.id ";
		//String sql ="select health.tag,health.content,health.create_date,`user`.icon,`user`.`name` from  health,`user` where user_id=`user`.id; ";
		DBUtil dbUtil = new DBUtil(sql);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("tag", result.getString("tag"));
				map.put("content",result.getString("content"));
				map.put("create_date", result.getString("create_date"));
				map.put("icon",result.getString("icon"));
                map.put("name",result.getString("name"));
                map.put("doctex",result.getString("doctex"));
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (result != null)
				result.close();
			if (dbUtil != null)
				dbUtil.close();
		}
		return list;
	}
}
