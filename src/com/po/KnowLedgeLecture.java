package com.po;

public class KnowLedgeLecture {
	
	private Integer id;

	private String title;

	private String cover;

	private String video;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return "KnowLedgeLecture [id=" + id + ", title=" + title + ", cover=" + cover + ", video=" + video + "]";
	}
	
	
}
