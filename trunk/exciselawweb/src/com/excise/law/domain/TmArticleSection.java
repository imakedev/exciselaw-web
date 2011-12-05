package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TM_ARTICLE_SECTION database table.
 * 
 */
@Entity
@Table(name="TM_ARTICLE_SECTION")
public class TmArticleSection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ARTICLE_SECTION_SEQ", sequenceName="ARTICLE_SECTION_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARTICLE_SECTION_SEQ")
	@Column(name="ARTICLE_SECTION_ID")
	private long articleSectionId;

	@Column(name="ARTICLE_GROUP_ID")
	private BigDecimal articleGroupId;

	@Column(name="ARTICLE_SECTION_NAME")
	private String articleSectionName;

	@Column(name="ARTICLE_SECTION_ORDER")
	private BigDecimal articleSectionOrder;

	@Column(name="ARTICLE_SECTION_STATUS")
	private String articleSectionStatus = "A";

	@Column(name="ARTICLE_SECTION_TYPE")
	private String articleSectionType;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="STATUE_ID")
	private BigDecimal statueId;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TmArticleSection() {
    }

	public long getArticleSectionId() {
		return this.articleSectionId;
	}

	public void setArticleSectionId(long articleSectionId) {
		this.articleSectionId = articleSectionId;
	}

	public BigDecimal getArticleGroupId() {
		return this.articleGroupId;
	}

	public void setArticleGroupId(BigDecimal articleGroupId) {
		this.articleGroupId = articleGroupId;
	}

	public String getArticleSectionName() {
		return this.articleSectionName;
	}

	public void setArticleSectionName(String articleSectionName) {
		this.articleSectionName = articleSectionName;
	}

	public BigDecimal getArticleSectionOrder() {
		return this.articleSectionOrder;
	}

	public void setArticleSectionOrder(BigDecimal articleSectionOrder) {
		this.articleSectionOrder = articleSectionOrder;
	}

	public String getArticleSectionStatus() {
		return this.articleSectionStatus;
	}

	public void setArticleSectionStatus(String articleSectionStatus) {
		this.articleSectionStatus = articleSectionStatus;
	}

	public String getArticleSectionType() {
		return this.articleSectionType;
	}

	public void setArticleSectionType(String articleSectionType) {
		this.articleSectionType = articleSectionType;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getStatueId() {
		return this.statueId;
	}

	public void setStatueId(BigDecimal statueId) {
		this.statueId = statueId;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}