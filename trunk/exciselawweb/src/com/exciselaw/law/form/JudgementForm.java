package com.exciselaw.law.form;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.exciselaw.law.domain.TsJudgement;

public class JudgementForm extends CommonForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private TsJudgement judgement;
	private String createdDate;
	private String judgementDate;
	private String pronouncementDate;
	private String[] deleteFileBoxes;
	private String[] msStatutesBoxes;
	
    private String filename;
    private CommonsMultipartFile[] fileData;
	
	public JudgementForm(){
	}
	
	
	public TsJudgement getJudgement() {
		return judgement;
	}
	

	public void setJudgement(TsJudgement judgement) {
		this.judgement = judgement;
	}
	

	public String getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	public String getJudgementDate() {
		return judgementDate;
	}


	public void setJudgementDate(String judgementDate) {
		this.judgementDate = judgementDate;
	}


	public String getPronouncementDate() {
		return pronouncementDate;
	}


	public void setPronouncementDate(String pronouncementDate) {
		this.pronouncementDate = pronouncementDate;
	}


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
}
