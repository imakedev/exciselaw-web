package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TS_DISCUSS_PUBLISHED database table.
 * 
 */
@Entity
@Table(name="TS_DISCUSS_PUBLISHED")
public class TsDiscussPublished implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DISCUSS_PUBLISHED_SEQ", sequenceName="DISCUSS_PUBLISHED_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DISCUSS_PUBLISHED_SEQ")	
	@Column(name="PUBLISHED_ID")
	private long publishedId;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

    @Lob()
	@Column(name="LAW_INVOLVED")
	private String lawInvolved;

    @Lob()
	@Column(name="PUBLISHED_DETAILS")
	private String publishedDetails;

	@Column(name="PUBLISHED_DISPLAY")
	private BigDecimal publishedDisplay;

	@Column(name="PUBLISHED_NAME")
	private String publishedName;

	@Column(name="PUBLISHED_STATUS")
	private String publishedStatus = "A";

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TsDiscussPublished() {
    }

	public long getPublishedId() {
		return this.publishedId;
	}

	public void setPublishedId(long publishedId) {
		this.publishedId = publishedId;
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

	public String getLawInvolved() {
		return this.lawInvolved;
	}

	public void setLawInvolved(String lawInvolved) {
		this.lawInvolved = lawInvolved;
	}

	public String getPublishedDetails() {
		return this.publishedDetails;
	}

	public void setPublishedDetails(String publishedDetails) {
		this.publishedDetails = publishedDetails;
	}

	public BigDecimal getPublishedDisplay() {
		return this.publishedDisplay;
	}

	public void setPublishedDisplay(BigDecimal publishedDisplay) {
		this.publishedDisplay = publishedDisplay;
	}

	public String getPublishedName() {
		return this.publishedName;
	}

	public void setPublishedName(String publishedName) {
		this.publishedName = publishedName;
	}

	public String getPublishedStatus() {
		return this.publishedStatus;
	}

	public void setPublishedStatus(String publishedStatus) {
		this.publishedStatus = publishedStatus;
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