package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TS_NEWS_ATTACH database table.
 * 
 */
@Entity
@Table(name="TS_NEWS_ATTACH")
public class TsNewsAttach implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NEWS_ATTACH_SEQ", sequenceName="NEWS_ATTACH_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NEWS_ATTACH_SEQ")
	@Column(name="ATTACH_ID")
	private long attachId;

	@Column(name="ATTACH_FILE_NAME")
	private String attachFileName;

	@Column(name="ATTACH_FILE_PATH")
	private String attachFilePath;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="NEWS_ID")
	private java.math.BigDecimal newsId;

    public TsNewsAttach() {
    }

	public long getAttachId() {
		return this.attachId;
	}

	public void setAttachId(long attachId) {
		this.attachId = attachId;
	}

	public String getAttachFileName() {
		return this.attachFileName;
	}

	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}

	public String getAttachFilePath() {
		return this.attachFilePath;
	}

	public void setAttachFilePath(String attachFilePath) {
		this.attachFilePath = attachFilePath;
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

	public java.math.BigDecimal getNewsId() {
		return this.newsId;
	}

	public void setNewsId(java.math.BigDecimal newsId) {
		this.newsId = newsId;
	}

}