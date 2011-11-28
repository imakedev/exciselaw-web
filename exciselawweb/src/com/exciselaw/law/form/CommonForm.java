package com.exciselaw.law.form;

import java.io.Serializable;

public class CommonForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mode;
	private String action;
	private String isSusses;
	private String criteria ;
	private String add;
	private String edit;
	private String delete;
	private String view;
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getIsSusses() {
		return isSusses;
	}
	public void setIsSusses(String isSusses) {
		this.isSusses = isSusses;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getEdit() {
		return edit;
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
}
