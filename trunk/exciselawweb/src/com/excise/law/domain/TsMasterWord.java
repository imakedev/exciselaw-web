package com.excise.law.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TS_MASTER_WORD database table.
 * 
 */
@Entity
@Table(name="TS_MASTER_WORD")
public class TsMasterWord implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="MASTER_WORD_SEQ", sequenceName="MASTER_WORD_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_WORD_SEQ")
	@Column(name="MASTER_WORD_ID")
	private long masterWordId;

	@Column(name="MASTER_WORD")
	private String masterWord;
	
	@Column(name="MASTER_WORD_DESCRIPTION")
	private String masterWordDescription;
	
	@Column(name="CREATED_BY")
	private String createdBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public TsMasterWord() {
    }
	
    public long getMasterWordId() {
		return masterWordId;
	}

	public void setMasterWordId(long masterWordId) {
		this.masterWordId = masterWordId;
	}

	public String getMasterWord() {
		return masterWord;
	}

	public void setMasterWord(String masterWord) {
		this.masterWord = masterWord;
	}

	public String getMasterWordDescription() {
		return masterWordDescription;
	}

	public void setMasterWordDescription(String masterWordDescription) {
		this.masterWordDescription = masterWordDescription;
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