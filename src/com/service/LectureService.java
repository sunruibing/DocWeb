package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.po.KnowLedgeLecture;
import com.util.DBUtil;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice 2016年9月22日
 */
public class LectureService {
	public List<Map<String, Object>> queryLectureAll() throws SQLException {
		String sql = "SELECT id, title, cover, video  FROM lecture";
		DBUtil dbUtil = new DBUtil(sql);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", result.getInt("id"));
				map.put("title", result.getString("title"));
				map.put("cover", result.getString("cover"));
				map.put("video", result.getString("video"));
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
	public String queryLectureById(Integer lectureId) throws SQLException {
		String sql = "SELECT video FROM lecture WHERE id = " + lectureId + " ";
		DBUtil dbUtil = new DBUtil(sql);
		String video = "";
		ResultSet result = null;
		try {
			result = dbUtil.pst.executeQuery();
			while (result.next()) {
				video = result.getString("video");
				return video;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null)
				result.close();
			if (dbUtil != null)
				dbUtil.close();
		}
		return video;
	}
	public int insertLecture(KnowLedgeLecture knowLedgeLecture) {
		String sql = "insert into lecture(title,cover,video) VALUES(" + "'"
				+ knowLedgeLecture.getTitle() + "'" + "," + "'"
				+ knowLedgeLecture.getCover() + "'" + "," + "'"
				+ knowLedgeLecture.getVideo() + "'" + ")";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate();
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
	}
	public int deleteLectureById(Integer id) {
		String sql = " DELETE FROM lecture WHERE id = " + id + " ";
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
}
