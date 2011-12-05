package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TM_ARTICLE_GROUP database table.
 * 
 */
@Entity
@Table(name="TM_ARTICLE_GROUP")
public class TmArticleGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ARTICLE_GROUP_SEQ", sequenceName="ARTICLE_GROUP_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARTICLE_GROUP_SEQ")
	@Column(name="ARTICLE_GROUP_ID")
	private long articleGroupId;

	@Column(name="ARTICLE_GROUP_NAME")
	private String articleGroupName;

	@Column(name="ARTICLE_GROUP_ORDER")
	private BigDecimal articleGroupOrder;

	@Column(name="ARTICLE_GROUP_STATUS")
	private String articleGroupStatus = "A";

	@Column(name="ARTICLE_GROUP_TYPE")
	private String articleGroupType;

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

    public TmArticleGroup() {
    }

	public long getArticleGroupId() {
		return this.articleGroupId;
	}

	public void setArticleGroupId(long articleGroupId) {
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