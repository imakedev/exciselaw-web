package com.excise.law.form;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ConsultPublishForm extends CommonForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	private TsDiscussPublished tsDiscussPublished;
	private String createdDate;
	private String lawInvolved;
	private String publishedDetails;
	private String[] deleteFileBoxes;
	private String[] msStatutesBoxes;
	private String[] dutyGroupGoodsBoxes;
	private String[] dutyGroupServicesBoxes;
	
    private String filename;
    private CommonsMultipartFile[] fileData;

	
	public ConsultPublishForm(){
	}


//	public TsDiscussPublished getTsDiscussPublished() {
//		return tsDiscussPublished;
//	}
//
//
//	public void setTsDiscussPublished(TsDiscussPublished tsDiscussPublished) {
//		this.tsDiscussPublished = tsDiscussPublished;
//	}


	public String[] getDeleteFileBoxes() {
		return deleteFileBoxes;
	}


	public void setDeleteFileBoxes(String[] deleteFileBoxes) {
		this.deleteFileBoxes = deleteFileBoxes;
	}


	public String[] getMsStatutesBoxes() {
		return msStatutesBoxes;
	}


	public void setMsStatutesBoxes(String[] msStatutesBoxes) {
		this.msStatutesBoxes = msStatutesBoxes;
	}


	public String[] getDutyGroupGoodsBoxes() {
		return dutyGroupGoodsBoxes;
	}


	public void setDutyGroupGoodsBoxes(String[] dutyGroupGoodsBoxes) {
		this.dutyGroupGoodsBoxes = dutyGroupGoodsBoxes;
	}


	public String[] getDutyGroupServicesBoxes() {
		return dutyGroupServicesBoxes;
	}


	public void setDutyGroupServicesBoxes(String[] dutyGroupServicesBoxes) {
		this.dutyGroupServicesBoxes = dutyGroupServicesBoxes;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public CommonsMultipartFile[] getFileData() {
		return fileData;
	}


	public void setFileData(CommonsMultipartFile[] fileData) {
		this.fileData = fileData;
	}


	public String getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	public String getLawInvolved() {
		return lawInvolved;
	}


	public void setLawInvolved(String lawInvolved) {
		this.lawInvolved = lawInvolved;
	}


	public String getPublishedDetails() {
		return publishedDetails;
	}


	public void setPublishedDetails(String publishedDetails) {
		this.publishedDetails = publishedDetails;
	}
}
