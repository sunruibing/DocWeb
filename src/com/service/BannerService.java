package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.DBUtil;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice
 *         2016年10月29日
 */
public class BannerService {

	public List<Map<String, Object>> queryBannerListByCategory1()
			throws SQLException {

		String sql = " select id, cover, site, cyclopedia_id from banner where category_code = 1 ";
		DBUtil dbUtil = new DBUtil(sql);

		ResultSet result = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			result = dbUtil.pst.executeQuery();

			while (result.next()) {

				Map<String, Object> map = new HashMap<String, Object>();

				map.put("id", result.getInt("id"));
				map.put("cover", result.getString("cover"));
				map.put("site", result.getString("site"));
				map.put("cyclopediaId", result.getInt("cyclopedia_id"));

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

	public List<Map<String, Object>> queryBannerListByCategory2()
			throws SQLException {

		String sql = " select id, cover, site, cyclopedia_id from banner where category_code = 2 ";
		DBUtil dbUtil = new DBUtil(sql);

		ResultSet result = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			result = dbUtil.pst.executeQuery();

			while (result.next()) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", result.getInt("id"));
				map.put("cover", result.getString("cover"));
				map.put("site", result.getString("site"));
				map.put("cyclopediaId", result.getInt("cyclopedia_id"));

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

}
