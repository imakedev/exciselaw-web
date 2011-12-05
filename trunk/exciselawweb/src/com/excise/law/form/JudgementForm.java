package com.excise.law.form;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.excise.law.domain.TsJudgement;

public class JudgementForm extends CommonForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private TsJudgement judgement;
	private String year1;
	private String year2;
	private String judgementGroup1;
	private String judgementGroup2;
	private String judgementTypeId1;
	private String judgementTypeId2;
	private String judgementDate;
	private String pronouncementDate;
	
	public JudgementForm(){
	}

	public TsJudgement getJudgement() {
		return judgement;
	}

	public void setJudgement(TsJudgement judgement) {
		this.judgement = judgement;
	}

	public String getYear1() {
		return year1;
	}

	public void setYear1(String year1) {
		this.year1 = year1;
	}

	public String getYear2() {
		return year2;
	}

	public void setYear2(String year2) {
		this.year2 = year2;
	}

	public String getJudgementGroup1() {
		return judgementGroup1;
	}

	public void setJudgementGroup1(String judgementGroup1) {
		this.judgementGroup1 = judgementGroup1;
	}

	public String getJudgementGroup2() {
		return judgementGroup2;
	}

	public void setJudgementGroup2(String judgementGroup2) {
		this.judgementGroup2 = judgementGroup2;
	}

	public String getJudgementTypeId1() {
		return judgementTypeId1;
	}

	public void setJudgementTypeId1(String judgementTypeId1) {
		this.judgementTypeId1 = judgementTypeId1;
	}

	public String getJudgementTypeId2() {
		return judgementTypeId2;
	}

	public void setJudgementTypeId2(String judgementTypeId2) {
		this.judgementTypeId2 = judgementTypeId2;
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
}
