package com.exciselaw.law.form;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.exciselaw.law.domain.TsArticle;
import com.exciselaw.law.domain.TsExArticleCompleted;
import com.exciselaw.law.domain.TsExArticleHeader;
import com.exciselaw.law.domain.TsLaw;
import com.exciselaw.law.domain.TsTariff;

public class ExciseLawForm extends CommonForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private TsExArticleHeader tsExArticleHeader;
	private TsArticle tsArticle;
	private TsExArticleCompleted tsExArticleCompleted;
	private TsTariff tsTariff;
	private TsLaw tsLaw;
	
	private String statueId;
	private String primaryKey;
	private String articleGroup;
	private String gazetteDated;
	private String createdDate;
	
	private String articleOrder;
	
	private String[] deleteFileBoxes;
	private String[] statutesBoxes;
	private String[] dutyGroupGoodsBoxes;
	private String[] dutyGroupServicesBoxes;
	
    private String filename;
    private CommonsMultipartFile[] fileData;
	
	public ExciseLawForm(){
	}

	public TsExArticleHeader getTsExArticleHeader() {
		return tsExArticleHeader;
	}

	public void setTsExArticleHeader(TsExArticleHeader tsExArticleHeader) {
		this.tsExArticleHeader = tsExArticleHeader;
	}

	public TsArticle getTsArticle() {
		return tsArticle;
	}

	public void setTsArticle(TsArticle tsArticle) {
		this.tsArticle = tsArticle;
	}

	public TsExArticleCompleted getTsExArticleCompleted() {
		return tsExArticleCompleted;
	}

	public void setTsExArticleCompleted(TsExArticleCompleted tsExArticleCompleted) {
		this.tsExArticleCompleted = tsExArticleCompleted;
	}

	public TsTariff getTsTariff() {
		return tsTariff;
	}

	public void setTsTariff(TsTariff tsTariff) {
		this.tsTariff = tsTariff;
	}

	public TsLaw getTsLaw() {
		return tsLaw;
	}

	public void setTsLaw(TsLaw tsLaw) {
		this.tsLaw = tsLaw;
	}

	public String getStatueId() {
		return statueId;
	}

	public void setStatueId(String statueId) {
		this.statueId = statueId;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getArticleGroup() {
		return articleGroup;
	}

	public void setArticleGroup(String articleGroup) {
		this.articleGroup = articleGroup;
	}

	public String getGazetteDated() {
		return gazetteDated;
	}

	public void setGazetteDated(String gazetteDated) {
		this.gazetteDated = gazetteDated;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getArticleOrder() {
		return articleOrder;
	}

	public void setArticleOrder(String articleOrder) {
		this.articleOrder = articleOrder;
	}

	public String[] getDeleteFileBoxes() {
		return deleteFileBoxes;
	}

	public void setDeleteFileBoxes(String[] deleteFileBoxes) {
		this.deleteFileBoxes = deleteFileBoxes;
	}

	public String[] getStatutesBoxes() {
		return statutesBoxes;
	}

	public void setStatutesBoxes(String[] statutesBoxes) {
		this.statutesBoxes = statutesBoxes;
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
