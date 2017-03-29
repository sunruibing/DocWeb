package com.service;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.po.DocComment;
import com.po.Doctor;
import com.util.DataSourceUtils;

/**
 * 医生对健康状况的评论
 * @author LiD
 *
 */
public class DocCommentService {

	private QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
	
	public List<DocComment> getContByHeaId(Integer healthId) throws Exception {
		String sql="select * from doccomment where healthid=?";
		List<DocComment> list= runner.query(sql, new BeanListHandler<DocComment>(DocComment.class),healthId);
		for(DocComment doc:list){
			String sql1="select * from doctor where id=?";
			Doctor doctor = runner.query(sql1, new BeanHandler<Doctor>(Doctor.class),doc.getDocId());
			doc.setDoctor(doctor);
		}
		return list;
	}
}
