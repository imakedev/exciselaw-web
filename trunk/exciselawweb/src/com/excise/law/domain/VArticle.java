package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the V_ARTICLE database table.
 * 
 */
@Entity
@Table(name="V_ARTICLE")
public class VArticle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ARTICLE_ID")
	private BigDecimal articleId;
	
	@Column(name="ARTICLE_GROUP_ID")
	private BigDecimal articleGroupId;

	@Column(name="ARTICLE_GROUP_NAME")
	private String articleGroupName;

	@Column(name="ARTICLE_HEADER_ID")
	private BigDecimal articleHeaderId;

	@Column(name="ARTICLE_HEADER_NAME")
	private String articleHeaderName;

	@Column(name="ARTICLE_NUMBER")
	private String articleNumber;

	@Column(name="ARTICLE_ORDER")
	private BigDecimal articleOrder;

	@Column(name="ARTICLE_SECTION_ID")
	private BigDecimal articleSectionId;

	@Column(name="ARTICLE_SECTION_NAME")
	private String articleSectionName;

	@Column(name="EXCISE_ARTICLE_TYPE")
	private BigDecimal exciseArticleType;

	@Column(name="LAW_TYPE_ID")
	private BigDecimal lawTypeId;

	@Column(name="LAW_TYPE_NAME")
	private String lawTypeName;

	@Column(name="STATUE_ID")
	private BigDecimal statueId;

	@Column(name="STATUE_NAME")
	private String statueName;

    public VArticle() {
    }

	public BigDecimal getArticleGroupId() {
		return this.articleGroupId;
	}

	public void setArticleGroupId(BigDecimal articleGroupId) {
		this.articleGroupId = articleGroupId;
	}

	public String getArticleGroupName() {
		return this.articleGroupName;
	}

	public void setArticleGroupName(String articleGroupName) {
		this.articleGroupName = articleGroupName;
	}

	public BigDecimal getArticleHeaderId() {
		return this.articleHeaderId;
	}

	public void setArticleHeaderId(BigDecimal articleHeaderId) {
		this.articleHeaderId = articleHeaderId;
	}

	public String getArticleHeaderName() {
		return this.articleHeaderName;
	}

	public void setArticleHeaderName(String articleHeaderName) {
		this.articleHeaderName = articleHeaderName;
	}

	public BigDecimal getArticleId() {
		return this.articleId;
	}

	public void setArticleId(BigDecimal articleId) {
		this.articleId = articleId;
	}

	public String getArticleNumber() {
		return this.articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	public BigDecimal getArticleOrder() {
		return this.articleOrder;
	}

	public void setArticleOrder(BigDecimal articleOrder) {
		this.articleOrder = articleOrder;
	}

	public BigDecimal getArticleSectionId() {
		return this.articleSectionId;
	}

	public void setArticleSectionId(BigDecimal articleSectionId) {
		this.articleSectionId = articleSectionId;
	}

	public String getArticleSectionName() {
		return this.articleSectionName;
	}

	public void setArticleSectionName(String articleSectionName) {
		this.articleSectionName = articleSectionName;
	}

	public BigDecimal getExciseArticleType() {
		return this.exciseArticleType;
	}

	public void setExciseArticleType(BigDecimal exciseArticleType) {
		this.exciseArticleType = exciseArticleType;
	}

	public BigDecimal getLawTypeId() {
		return this.lawTypeId;
	}

	public void setLawTypeId(BigDecimal lawTypeId) {
		this.lawTypeId = lawTypeId;
	}

	public String getLawTypeName() {
		return this.lawTypeName;
	}

	public void setLawTypeName(String lawTypeName) {
		this.lawTypeName = lawTypeName;
	}

	public BigDecimal getStatueId() {
		return this.statueId;
	}

	public void setStatueId(BigDecimal statueId) {
		this.statueId = statueId;
	}

	public String getStatueName() {
		return this.statueName;
	}

	public void setStatueName(String statueName) {
		this.statueName = statueName;
	}

}