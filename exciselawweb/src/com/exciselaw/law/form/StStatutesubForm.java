package com.exciselaw.law.form;

import java.io.Serializable;

import com.exciselaw.law.domain.StStatutesub;

public class StStatutesubForm 	extends CommonForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private   StStatutesub stStatutesub;
	private String[] productBoxes; //listMsLegalitems
	private String[] serviceBoxes; //listMsLegalitems
	private String[] msStatuteBoxes; //listMsStatutes
	private String[] statusBoxes;
	private String[] displayBoxes;
	private String announceDate;
	private String updateTime;
	private Long productId;
	private String article;
	public StStatutesubForm(){
		stStatutesub=new StStatutesub();
	}
	public StStatutesub getStStatutesub() {
		return stStatutesub;
	}
	public void setStStatutesub(StStatutesub stStatutesub) {
		this.stStatutesub = stStatutesub;
	}
	public String[] getProductBoxes() {
		return productBoxes;
	}
	public void setProductBoxes(String[] productBoxes) {
		this.productBoxes = productBoxes;
	}
	public String[] getServiceBoxes() {
		return serviceBoxes;
	}
	public void setServiceBoxes(String[] serviceBoxes) {
		this.serviceBoxes = serviceBoxes;
	}
	public String[] getMsStatuteBoxes() {
		return msStatuteBoxes;
	}
	public void setMsStatuteBoxes(String[] msStatuteBoxes) {
		this.msStatuteBoxes = msStatuteBoxes;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String[] getStatusBoxes() {
		return statusBoxes;
	}
	public void setStatusBoxes(String[] statusBoxes) {
		this.statusBoxes = statusBoxes;
	}
	public String[] getDisplayBoxes() {
		return displayBoxes;
	}
	public void setDisplayBoxes(String[] displayBoxes) {
		this.displayBoxes = displayBoxes;
	}
	public String getAnnounceDate() {
		return announceDate;
	}
	public void setAnnounceDate(String announceDate) {
		this.announceDate = announceDate;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	 
	
}
