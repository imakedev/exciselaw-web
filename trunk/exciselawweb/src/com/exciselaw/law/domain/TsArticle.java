package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TS_ARTICLE database table.
 * 
 */
@Entity
@Table(name="TS_ARTICLE")
public class TsArticle implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="ARTICLE_SEQ", sequenceName="ARTICLE_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARTICLE_SEQ")
	@Column(name="ARTICLE_ID")
	private long articleId;

    @Lob()
	@Column(name="ARTICLE_DETAIL")
	private String articleDetail;

	@Column(name="ARTICLE_GROUP_ID")
	private BigDecimal articleGroupId;

	@Column(name="ARTICLE_HEADER_ID")
	private BigDecimal articleHeaderId;

	@Column(name="ARTICLE_KEYWORD")
	private String articleKeyword;

	@Column(name="ARTICLE_NUMBER")
	private String articleNumber;

	@Column(name="ARTICLE_ORDER")
	private BigDecimal articleOrder;

    @Lob()
	@Column(name="ARTICLE_REMARK")
	private String articleRemark;

	@Column(name="ARTICLE_SECTION_ID")
	private BigDecimal articleSectionId;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="EXCISE_ARTICLE_TYPE")
	private BigDecimal exciseArticleType;

	@Column(name="LAW_TYPE_ID")
	private BigDecimal lawTypeId;

	@Column(name="STATUE_ID")
	private BigDecimal statueId;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TsArticle() {
    }

	public long getArticleId() {
		return this.articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public String getArticleDetail() {
		return this.articleDetail;
	}

	public void setArticleDetail(String articleDetail) {
		this.articleDetail = articleDetail;
	}

	public BigDecimal getArticleGroupId() {
		return this.articleGroupId;
	}

	public void setArticleGroupId(BigDecimal articleGroupId) {
		this.articleGroupId = articleGroupId;
	}

	public BigDecimal getArticleHeaderId() {
		return this.articleHeaderId;
	}

	public void setArticleHeaderId(BigDecimal articleHeaderId) {
		this.articleHeaderId = articleHeaderId;
	}

	public String getArticleKeyword() {
		return this.articleKeyword;
	}

	public void setArticleKeyword(String articleKeyword) {
		this.articleKeyword = articleKeyword;
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

	public String getArticleRemark() {
		return this.articleRemark;
	}

	public void setArticleRemark(String articleRemark) {
		this.articleRemark = articleRemark;
	}

	public BigDecimal getArticleSectionId() {
		return this.articleSectionId;
	}

	public void setArticleSectionId(BigDecimal articleSectionId) {
		this.articleSectionId = articleSectionId;
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

	public BigDecimal getExciseArticleType() {
		return this.exciseArticleType;
	}

	public void setExciseArticleType(BigDecimal exciseArticleType) {
		this.exciseArticleType = exciseArticleType;
	}

	public BigDecimal getLawTypeId() {
		return lawTypeId;
	}

	public void setLawTypeId(BigDecimal lawTypeId) {
		this.lawTypeId = lawTypeId;
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