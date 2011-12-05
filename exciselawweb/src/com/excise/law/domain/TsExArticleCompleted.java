package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TS_EX_ARTICLE_COMPLETED database table.
 * 
 */
@Entity
@Table(name="TS_EX_ARTICLE_COMPLETED")
public class TsExArticleCompleted implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ARTICLE_COMPLETED_SEQ", sequenceName="ARTICLE_COMPLETED_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARTICLE_COMPLETED_SEQ")
	@Column(name="ARTICLE_COMPLETED_ID")
	private long articleCompletedId;

    @Lob()
	@Column(name="ARTICLE_COMPLETED_DETAIL")
	private String articleCompletedDetail;

	@Column(name="ARTICLE_COMPLETED_KEYWORD")
	private String articleCompletedKeyword;

	@Column(name="ARTICLE_COMPLETED_NUMBER")
	private String articleCompletedNumber;

	@Column(name="ARTICLE_COMPLETED_ORDER")
	private BigDecimal articleCompletedOrder;

    @Lob()
	@Column(name="ARTICLE_COMPLETED_REMARK")
	private String articleCompletedRemark;
    
	@Column(name="ARTICLE_HEADER_ID")
	private BigDecimal articleHeaderId;
    
	@Column(name="ARTICLE_GROUP_ID")
	private BigDecimal articleGroupId;

	@Column(name="ARTICLE_SECTION_ID")
	private BigDecimal articleSectionId;

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

    public TsExArticleCompleted() {
    }

	public long getArticleCompletedId() {
		return this.articleCompletedId;
	}

	public void setArticleCompletedId(long articleCompletedId) {
		this.articleCompletedId = articleCompletedId;
	}

	public String getArticleCompletedDetail() {
		return this.articleCompletedDetail;
	}

	public void setArticleCompletedDetail(String articleCompletedDetail) {
		this.articleCompletedDetail = articleCompletedDetail;
	}

	public String getArticleCompletedKeyword() {
		return this.articleCompletedKeyword;
	}

	public void setArticleCompletedKeyword(String articleCompletedKeyword) {
		this.articleCompletedKeyword = articleCompletedKeyword;
	}

	public String getArticleCompletedNumber() {
		return this.articleCompletedNumber;
	}

	public void setArticleCompletedNumber(String articleCompletedNumber) {
		this.articleCompletedNumber = articleCompletedNumber;
	}

	public BigDecimal getArticleCompletedOrder() {
		return this.articleCompletedOrder;
	}

	public void setArticleCompletedOrder(BigDecimal articleCompletedOrder) {
		this.articleCompletedOrder = articleCompletedOrder;
	}

	public String getArticleCompletedRemark() {
		return this.articleCompletedRemark;
	}

	public void setArticleCompletedRemark(String articleCompletedRemark) {
		this.articleCompletedRemark = articleCompletedRemark;
	}

	public BigDecimal getArticleHeaderId() {
		return articleHeaderId;
	}

	public void setArticleHeaderId(BigDecimal articleHeaderId) {
		this.articleHeaderId = articleHeaderId;
	}

	public BigDecimal getArticleGroupId() {
		return this.articleGroupId;
	}

	public void setArticleGroupId(BigDecimal articleGroupId) {
		this.articleGroupId = articleGroupId;
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