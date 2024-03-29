package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the V_ARTICLE_SECTION database table.
 * 
 */
@Entity
@Table(name="V_ARTICLE_SECTION")
public class VArticleSection implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ARTICLE_SECTION_ID")
	private BigDecimal articleSectionId;
	
	@Column(name="ARTICLE_GROUP_ID")
	private BigDecimal articleGroupId;
	
	@Column(name="ARTICLE_GROUP_NAME")
	private String articleGroupName;

	@Column(name="ARTICLE_SECTION_NAME")
	private String articleSectionName;

	@Column(name="ARTICLE_SECTION_TYPE_DESC")
	private String articleSectionTypeDesc;

	@Column(name="ARTICLE_SECTION_ORDER")
	private BigDecimal articleSectionOrder;

	@Column(name="ARTICLE_SECTION_STATUS")
	private String articleSectionStatus;

	@Column(name="ARTICLE_SECTION_STATUS_DESC")
	private String articleSectionStatusDesc;

	@Column(name="ARTICLE_SECTION_TYPE")
	private String articleSectionType;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="STATUE_ID")
	private BigDecimal statueId;

	@Column(name="STATUE_NAME")
	private String statueName;

    public VArticleSection() {
    }

	public BigDecimal getArticleSectionId() {
		return articleSectionId;
	}

	public void setArticleSectionId(BigDecimal articleSectionId) {
		this.articleSectionId = articleSectionId;
	}

	public BigDecimal getArticleGroupId() {
		return articleGroupId;
	}

	public void setArticleGroupId(BigDecimal articleGroupId) {
		this.articleGroupId = articleGroupId;
	}

	public String getArticleGroupName() {
		return articleGroupName;
	}

	public void setArticleGroupName(String articleGroupName) {
		this.articleGroupName = articleGroupName;
	}

	public String getArticleSectionName() {
		return articleSectionName;
	}

	public void setArticleSectionName(String articleSectionName) {
		this.articleSectionName = articleSectionName;
	}

	public String getArticleSectionTypeDesc() {
		return articleSectionTypeDesc;
	}

	public void setArticleSectionTypeName(String articleSectionTypeDesc) {
		this.articleSectionTypeDesc = articleSectionTypeDesc;
	}

	public BigDecimal getArticleSectionOrder() {
		return articleSectionOrder;
	}

	public void setArticleSectionOrder(BigDecimal articleSectionOrder) {
		this.articleSectionOrder = articleSectionOrder;
	}

	public String getArticleSectionStatus() {
		return articleSectionStatus;
	}

	public void setArticleSectionStatus(String articleSectionStatus) {
		this.articleSectionStatus = articleSectionStatus;
	}

	public String getArticleSectionStatusDesc() {
		return articleSectionStatusDesc;
	}

	public void setArticleSectionStatusDesc(String articleSectionStatusDesc) {
		this.articleSectionStatusDesc = articleSectionStatusDesc;
	}

	public String getArticleSectionType() {
		return articleSectionType;
	}

	public void setArticleSectionType(String articleSectionType) {
		this.articleSectionType = articleSectionType;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getStatueId() {
		return statueId;
	}

	public void setStatueId(BigDecimal statueId) {
		this.statueId = statueId;
	}

	public String getStatueName() {
		return statueName;
	}

	public void setStatueName(String statueName) {
		this.statueName = statueName;
	}
}