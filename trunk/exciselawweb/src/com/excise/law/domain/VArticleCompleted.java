package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the V_ARTICLE database table.
 * 
 */
@Entity
@Table(name="V_ARTICLE_COMPLETED")
public class VArticleCompleted implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ARTICLE_COMPLETED_ID")
	private BigDecimal articleCompletedId;
	
	@Column(name="ARTICLE_GROUP_ID")
	private BigDecimal articleGroupId;

	@Column(name="ARTICLE_GROUP_NAME")
	private String articleGroupName;

	@Column(name="ARTICLE_COMPLETED_NUMBER")
	private String articleCompletedNumber;

	@Column(name="ARTICLE_COMPLETED_ORDER")
	private BigDecimal articleCompletedOrder;

	@Column(name="ARTICLE_SECTION_ID")
	private BigDecimal articleSectionId;

	@Column(name="ARTICLE_SECTION_NAME")
	private String articleSectionName;

	@Column(name="STATUE_ID")
	private BigDecimal statueId;

	@Column(name="STATUE_NAME")
	private String statueName;

    public VArticleCompleted() {
    }

	public BigDecimal getArticleCompletedId() {
		return articleCompletedId;
	}

	public void setArticleCompletedId(BigDecimal articleCompletedId) {
		this.articleCompletedId = articleCompletedId;
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

	public String getArticleCompletedNumber() {
		return articleCompletedNumber;
	}

	public void setArticleCompletedNumber(String articleCompletedNumber) {
		this.articleCompletedNumber = articleCompletedNumber;
	}

	public BigDecimal getArticleCompletedOrder() {
		return articleCompletedOrder;
	}

	public void setArticleCompletedOrder(BigDecimal articleCompletedOrder) {
		this.articleCompletedOrder = articleCompletedOrder;
	}

	public BigDecimal getArticleSectionId() {
		return articleSectionId;
	}

	public void setArticleSectionId(BigDecimal articleSectionId) {
		this.articleSectionId = articleSectionId;
	}

	public String getArticleSectionName() {
		return articleSectionName;
	}

	public void setArticleSectionName(String articleSectionName) {
		this.articleSectionName = articleSectionName;
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