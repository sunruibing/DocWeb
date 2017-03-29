/*package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.po.MyUser;
import com.util.DBUtil;
*//**
 * ҽ���˻�ȡ�û��б�
 * @author Administrator
 *
 *//*

public class MyUserService {

	 public List<Map<String,Object>> queryMyUser(Integer doctorId) throws SQLException{
		 
		 String sql = "SELECT myuser.id,userId,`user`.icon,`user`.`name`,`user`.phone FROM myuser,`user` WHERE userId = `user`.id AND myuser.doctorId = "+doctorId+"";
		 DBUtil dbUtil = new DBUtil(sql);
		 ResultSet  result = null;
		 List<Map<String,Object>>  list = new ArrayList<Map<String,Object>>();
		 try {
			result= dbUtil.pst.executeQuery();
			while(result.next()){
				Map<String,Object>  map = new HashMap<String,Object>();
				map.put("id",result.getInt("id"));
				map.put("userId",result.getInt("userId"));
				map.put("icon", result.getString("icon"));
				map.put("name", result.getString("name"));
				map.put("phone", result.getString("phone"));
				list.add(map);
			}
			return list;
		} catch (Exception e) {
          e.printStackTrace();
		}finally {
			dbUtil.close();
			result.close();
		}
		 return list;
		 
	 }
	 public int insertMyUuser(MyUser myUser){
		 String sql = "INSERT INTO myuser(userId,doctorId) VALUES("+myUser.getUserId()+","+myUser.getDoctorId()+")";
		 DBUtil dbUtil = new DBUtil(sql);
		 try {
			dbUtil.pst.executeUpdate();
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}finally {
			dbUtil.close();
		}
	 }
}
*/