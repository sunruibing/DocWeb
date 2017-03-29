package com.po;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice 2016年9月22日
 */
public class Feedback {

	private Integer id;

	private String content;

	private String time;

	private Integer userId;

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Advise [id=" + id + ", content=" + content + ", time=" + time
				+ ", userId=" + userId + "]";
	}

}
