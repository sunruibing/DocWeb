package com.po;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice 2016年9月21日
 */
public class Collect {

	private Integer id;

	private Integer userId;

	private Integer cyclopediaId;

	private String time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCyclopediaId() {
		return cyclopediaId;
	}

	public void setCyclopediaId(Integer cyclopediaId) {
		this.cyclopediaId = cyclopediaId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Clect [id=" + id + ", userId=" + userId + ", cyclopediaId="
				+ cyclopediaId + ", time=" + time + "]";
	}

}
