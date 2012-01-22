package com.excise.law.form;

import java.io.Serializable;

import com.excise.law.domain.TsArticle;
import com.excise.law.domain.TsExArticleCompleted;

public class ExciseLawForm extends CommonForm implements Serializable{
	private static final long serialVersionUID = 1L;
	private TsArticle tsArticle;
	private TsExArticleCompleted tsExArticleCompleted;
	private String primaryKey;
	private String articleOrder;
	private String createdDate;
	public ExciseLawForm(){
		tsArticle=new TsArticle();
		tsExArticleCompleted =new TsExArticleCompleted();
	}
	public TsArticle getTsArticle() {
		return tsArticle;
	}
	public void setTsArticle(TsArticle tsArticle) {
		this.tsArticle = tsArticle;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getArticleOrder() {
		return articleOrder;
	}
	public void setArticleOrder(String articleOrder) {
		this.articleOrder = articleOrder;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public TsExArticleCompleted getTsExArticleCompleted() {
		return tsExArticleCompleted;
	}
	public void setTsExArticleCompleted(TsExArticleCompleted tsExArticleCompleted) {
		this.tsExArticleCompleted = tsExArticleCompleted;
	}
	
}
