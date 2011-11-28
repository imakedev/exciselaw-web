package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TS_PUBLISHED_ATTACH database table.
 * 
 */
@Entity
@Table(name="TS_PUBLISHED_ATTACH")
public class TsPublishedAttach implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="PUBLISHED_ATTACH_SEQ", sequenceName="PUBLISHED_ATTACH_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PUBLISHED_ATTACH_SEQ")
	@Column(name="PUBLISHED_ATTACH_ID")
	private long publishedAttachId;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="DISCUSS_PUBLISHED_ID")
	private java.math.BigDecimal discussPublishedId;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_PATH")
	private String filePath;

	private String status = "A";

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TsPublishedAttach() {
    }

	public long getPublishedAttachId() {
		return this.publishedAttachId;
	}

	public void setPublishedAttachId(long publishedAttachId) {
		this.publishedAttachId = publishedAttachId;
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

	public java.math.BigDecimal getDiscussPublishedId() {
		return this.discussPublishedId;
	}

	public void setDiscussPublishedId(java.math.BigDecimal discussPublishedId) {
		this.discussPublishedId = discussPublishedId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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