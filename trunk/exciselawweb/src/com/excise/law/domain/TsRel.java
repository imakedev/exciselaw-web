package com.excise.law.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TS_REL database table.
 * 
 */
@Entity
@Table(name="TS_REL")
public class TsRel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REL_SEQ" ,sequenceName="REL_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REL_SEQ")
	@Column(name="TR_ID")
	private Long trId;

	@Column(name="TR_KEY")
	private String trKey;

	@Column(name="TR_TABLE_NAME")
	private String trTableName;

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
	//bi-directional many-to-one association to TsRelMap
    @ManyToOne
	@JoinColumn(name="RM_ID")
	private TsRelMap tsRelMap;

    public TsRel() {
    }

	public Long getTrId() {
		return this.trId;
	}

	public void setTrId(Long trId) {
		this.trId = trId;
	}

	public String getTrKey() {
		return this.trKey;
	}

	public void setTrKey(String trKey) {
		this.trKey = trKey;
	}

	public String getTrTableName() {
		return this.trTableName;
	}

	public void setTrTableName(String trTableName) {
		this.trTableName = trTableName;
	}

	public TsRelMap getTsRelMap() {
		return this.tsRelMap;
	}

	public void setTsRelMap(TsRelMap tsRelMap) {
		this.tsRelMap = tsRelMap;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}