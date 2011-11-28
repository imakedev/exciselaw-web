package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the V_ARTICLE_GROUP database table.
 * 
 */
@Entity
@Table(name="V_ARTICLE_GROUP")
public class VArticleGroup implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ARTICLE_GROUP_ID")
	private BigDecimal articleGroupId;

	@Column(name="ARTICLE_GROUP_NAME")
	private String articleGroupName;

	@Column(name="ARTICLE_GROUP_ORDER")
	private BigDecimal articleGroupOrder;

	@Column(name="ARTICLE_GROUP_STATUS")
	private String articleGroupStatus;

	@Column(name="ARTICLE_GROUP_TYPE")
	private String articleGroupType;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="STATUE_ID")
	private BigDecimal statueId;

	@Column(name="STATUE_NAME")
	private String statueName;

    public VArticleGroup() {
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

	public BigDecimal getArticleGroupOrder() {
		return this.articleGroupOrder;
	}

	public void setArticleGroupOrder(BigDecimal articleGroupOrder) {
		this.articleGroupOrder = articleGroupOrder;
	}

	public String getArticleGroupStatus() {
		return this.articleGroupStatus;
	}

	public void setArticleGroupStatus(String articleGroupStatus) {
		this.articleGroupStatus = articleGroupStatus;
	}

	public String getArticleGroupType() {
		return this.articleGroupType;
	}

	public void setArticleGroupType(String articleGroupType) {
		this.articleGroupType = articleGroupType;
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

	public String getStatueName() {
		return this.statueName;
	}

	public void setStatueName(String statueName) {
		this.statueName = statueName;
	}

}