package com.po;

/**
 * 问答
 * 
 * @author FFFF
 *
 */
public class Answers {
	private Integer id;
	private String content;
	private String time;
	private String userName;
	private String icon;
	private Integer diseaseId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(Integer diseaseId) {
		this.diseaseId = diseaseId;
	}
	@Override
	public String toString() {
		return "Answers [id=" + id + ", content=" + content + ", time=" + time
				+ ", userName=" + userName + ", icon=" + icon + ", diseaseId="
				+ diseaseId + "]";
	}

}
