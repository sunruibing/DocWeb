package com.po;


/**
 * 用户添加评论
 * @author Administrator
 *
 */
public class Comment {
  
	private Integer id;
	
	private String content;
	
	private Integer doctorId;
	
	private Integer userId;
	
	private String time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", doctorId="
				+ doctorId + ", userId=" + userId + ", time=" + time + "]";
	}
	
}
