package com.po;
import com.po.Doctor;

/**
 * 医生对健康状况的评论
 * @author LiD
 *
 */
public class DocComment {
	
	private Integer id;			//主键
	private Integer healthId;	//health表主键,此表外键
	private Integer docId;		//doctor表主键,此表外键
	private String content;		//医生对健康状况的评论
	private Doctor	doctor;		//医生信息
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHealthId() {
		return healthId;
	}
	public void setHealthId(Integer healthId) {
		this.healthId = healthId;
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public String toString() {
		return "DocComment [id=" + id + ", healthId=" + healthId + ", docId=" + docId + ", content=" + content
				+ ", doctor=" + doctor + "]";
	}
	
}
