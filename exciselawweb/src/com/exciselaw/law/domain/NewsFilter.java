package com.exciselaw.law.domain;

import java.io.Serializable;

public class NewsFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String newsDisplayStatus;
	
	private String newsTopic;	

	private String newsStartDate;

	private String newsEndDate;

	public NewsFilter() {
	}

	public String getNewsDisplayStatus() {
		return newsDisplayStatus;
	}

	public void setNewsDisplayStatus(String newsDisplayStatus) {
		this.newsDisplayStatus = newsDisplayStatus;
	}

	public String getNewsTopic() {
		return newsTopic;
	}

	public void setNewsTopic(String newsTopic) {
		this.newsTopic = newsTopic;
	}

	public String getNewsStartDate() {
		return newsStartDate;
	}

	public void setNewsStartDate(String newsStartDate) {
		this.newsStartDate = newsStartDate;
	}

	public String getNewsEndDate() {
		return newsEndDate;
	}

	public void setNewsEndDate(String newsEndDate) {
		this.newsEndDate = newsEndDate;
	}

}