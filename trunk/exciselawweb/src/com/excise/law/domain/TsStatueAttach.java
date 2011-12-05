package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TS_STATUE_ATTACH database table.
 * 
 */
@Entity
@Table(name="TS_STATUE_ATTACH")
public class TsStatueAttach implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STATUE_ATTACH_SEQ", sequenceName="STATUE_ATTACH_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STATUE_ATTACH_SEQ")
	@Column(name="STATUE_ATTACH_ID")
	private long statueAttachId;

	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_PATH")
	private String filePath;

	@Column(name="STATUE_ATTACH_STATUS")
	private String statueAttachStatus;

	@Column(name="STATUE_ID")
	private java.math.BigDecimal statueId;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TsStatueAttach() {
    }

	public long getStatueAttachId() {
		return this.statueAttachId;
	}

	public void setStatueAttachId(long statueAttachId) {
		this.statueAttachId = statueAttachId;
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

	public String getStatueAttachStatus() {
		return this.statueAttachStatus;
	}

	public void setStatueAttachStatus(String statueAttachStatus) {
		this.statueAttachStatus = statueAttachStatus;
	}

	public java.math.BigDecimal getStatueId() {
		return this.statueId;
	}

	public void setStatueId(java.math.BigDecimal statueId) {
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