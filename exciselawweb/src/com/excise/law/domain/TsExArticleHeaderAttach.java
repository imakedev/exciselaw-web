package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TS_EX_ARTICLE_HEADER_ATTACH database table.
 * 
 */
@Entity
@Table(name="TS_EX_ARTICLE_HEADER_ATTACH")
public class TsExArticleHeaderAttach implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ARTICLE_HEADER_ATTACH_SEQ", sequenceName="ARTICLE_HEADER_ATTACH_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARTICLE_HEADER_ATTACH_SEQ")
	@Column(name="ARTICLE_HEAD_ATTACH_ID")
	private long articleHeadAttachId;

	@Column(name="ARTICLE_HEADER_ID")
	private java.math.BigDecimal articleHeaderId;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_PATH")
	private String filePath;

	private String status;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TsExArticleHeaderAttach() {
    }

	public long getArticleHeadAttachId() {
		return this.articleHeadAttachId;
	}

	public void setArticleHeadAttachId(long articleHeadAttachId) {
		this.articleHeadAttachId = articleHeadAttachId;
	}

	public java.math.BigDecimal getArticleHeaderId() {
		return this.articleHeaderId;
	}

	public void setArticleHeaderId(java.math.BigDecimal articleHeaderId) {
		this.articleHeaderId = articleHeaderId;
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