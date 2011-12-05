package com.excise.law.form;

import java.io.Serializable;

public class SearchForm extends CommonForm implements Serializable{

	private static final long serialVersionUID = 1L;
	private String statueId;
	private String lawTypeId;
	private String titleName;
	private String masterWordId;
	
	public String getStatueId() {
		return statueId;
	}
	public void setStatueId(String statueId) {
		this.statueId = statueId;
	}
	public String getLawTypeId() {
		return lawTypeId;
	}
	public void setLawTypeId(String lawTypeId) {
		this.lawTypeId = lawTypeId;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getMasterWordId() {
		return masterWordId;
	}
	public void setMasterWordId(String masterWordId) {
		this.masterWordId = masterWordId;
	}
}
