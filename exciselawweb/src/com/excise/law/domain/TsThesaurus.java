package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TS_THESAURUS database table.
 * 
 */
@Entity
@Table(name="TS_THESAURUS")
public class TsThesaurus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="THESAURUS_SEQ", sequenceName="THESAURUS_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="THESAURUS_SEQ")
	@Column(name="THESAURUS_ID")
	private long thesaurusId;

	@Column(name="MASTER_WORD_THESAURUS")
	private String masterWordThesaurus;
	
	@Column(name="MASTER_WORD_ID")
	private BigDecimal masterWordId;

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

    public TsThesaurus() {
    }

	public long getThesaurusId() {
		return thesaurusId;
	}

	public void setThesaurusId(long thesaurusId) {
		this.thesaurusId = thesaurusId;
	}

	public String getMasterWordThesaurus() {
		return masterWordThesaurus;
	}

	public void setMasterWordThesaurus(String masterWordThesaurus) {
		this.masterWordThesaurus = masterWordThesaurus;
	}

	public BigDecimal getMasterWordId() {
		return masterWordId;
	}

	public void setMasterWordId(BigDecimal masterWordId) {
		this.masterWordId = masterWordId;
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