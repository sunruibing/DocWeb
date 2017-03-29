package com.service;

import java.sql.SQLException;

import com.po.Feedback;
import com.util.DBUtil;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice 2016年9月22日
 */
public class FeedbackService {
	public int insertFeedback(Feedback advise) throws SQLException {
		String sql = "INSERT  INTO feedback(content,time,user_id) VALUES(" + "'"
				+ advise.getContent() + "'" + "," + "'" + advise.getTime() + "'"
				+ "," + advise.getUserId() + ") ";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.pst.executeUpdate(sql);
			return 1;
		} catch (Exception e) {
			return 2;
		} finally {
			if (dbUtil != null)
				dbUtil.close();
		}
	}
}
