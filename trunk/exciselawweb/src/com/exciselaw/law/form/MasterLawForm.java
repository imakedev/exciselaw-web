package com.exciselaw.law.form;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.exciselaw.law.domain.TmArticleGroup;
import com.exciselaw.law.domain.TmArticleSection;
import com.exciselaw.law.domain.TmLawType;
import com.exciselaw.law.domain.TmStatue;

public class MasterLawForm extends CommonForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private TmStatue tmStatue;
	private TmLawType tmLawType;
	private TmArticleGroup tmArticleGroup;
	private TmArticleSection tmArticleSection;
	private String articleGroupOrder;
	private String createdDate;
	private String[] deleteFileBoxes;
	private String[] msStatutesBoxes;
	private String[] dutyGroupGoodsBoxes;
	private String[] dutyGroupServicesBoxes;
	
    private String filename;
    private CommonsMultipartFile[] fileData;
	
	public MasterLawForm(){
	}

	public TmStatue getTmStatue() {
		return tmStatue;
	}

	public void setTmStatue(TmStatue tmStatue) {
		this.tmStatue = tmStatue;
	}

	public TmLawType getTmLawType() {
		return tmLawType;
	}

	public void setTmLawType(TmLawType tmLawType) {
		this.tmLawType = tmLawType;
	}

	public TmArticleGroup getTmArticleGroup() {
		return tmArticleGroup;
	}

	public void setTmArticleGroup(TmArticleGroup tmArticleGroup) {
		this.tmArticleGroup = tmArticleGroup;
	}

	public TmArticleSection getTmArticleSection() {
		return tmArticleSection;
	}

	public void setTmArticleSection(TmArticleSection tmArticleSection) {
		this.tmArticleSection = tmArticleSection;
	}

	public String getArticleGroupOrder() {
		return articleGroupOrder;
	}

	public void setArticleGroupOrder(String articleGroupOrder) {
		this.articleGroupOrder = articleGroupOrder;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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
}
