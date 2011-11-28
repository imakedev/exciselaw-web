package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TS_JUDGEMENT_ATTACH database table.
 * 
 */
@Entity
@Table(name="TS_JUDGEMENT_ATTACH")
public class TsJudgementAttach implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="JUDGEMENT_ATTACH_SEQ", sequenceName="JUDGEMENT_ATTACH_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="JUDGEMENT_ATTACH_SEQ")
	@Column(name="JUDGEMENT_ATTACH_ID")
	private long judgementAttachId;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_PATH")
	private String filePath;

	@Column(name="JUDGEMENT_ID")
	private java.math.BigDecimal judgementId;

	private String status;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TsJudgementAttach() {
    }

	public long getJudgementAttachId() {
		return judgementAttachId;
	}

	public void setJudgementAttachId(long judgementAttachId) {
		this.judgementAttachId = judgementAttachId;
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

	public java.math.BigDecimal getJudgementId() {
		return this.judgementId;
	}

	public void setJudgementId(java.math.BigDecimal judgementId) {
		this.judgementId = judgementId;
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