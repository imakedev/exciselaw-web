package com.excise.law.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the REL_TABLE_MAP database table.
 * 
 */
@Entity
@Table(name="TS_REL_TABLE_MAP")
public class TsRelTableMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RTM_ID")
	private Long rtmId;

	@Column(name="RTM_NAME")
	private String rtmName;

	@Column(name="RTM_TABLE_NAME")
	private String rtmTableName;

	@Column(name="RTM_OBJECT_NAME")
	private String rtmObjectName;

	@Column(name="RTM_ATT_ID")
	private String rtmAttId;

	@Column(name="RTM_ATT_NAME")
	private String rtmAttName;
 
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
	//bi-directional many-to-one association to RelKeyMap
	/*@OneToMany(mappedBy="relTableMap")
	private Set<RelKeyMap> relKeyMaps;*/

    public TsRelTableMap() {
    }

	public Long getRtmId() {
		return this.rtmId;
	}

	public void setRtmId(Long rtmId) {
		this.rtmId = rtmId;
	}

	public String getRtmName() {
		return this.rtmName;
	}

	public void setRtmName(String rtmName) {
		this.rtmName = rtmName;
	}

	public String getRtmTableName() {
		return this.rtmTableName;
	}

	public void setRtmTableName(String rtmTableName) {
		this.rtmTableName = rtmTableName;
	}

	public String getRtmObjectName() {
		return rtmObjectName;
	}

	public void setRtmObjectName(String rtmObjectName) {
		this.rtmObjectName = rtmObjectName;
	}

	public String getRtmAttId() {
		return rtmAttId;
	}

	public void setRtmAttId(String rtmAttId) {
		this.rtmAttId = rtmAttId;
	}

	public String getRtmAttName() {
		return rtmAttName;
	}

	public void setRtmAttName(String rtmAttName) {
		this.rtmAttName = rtmAttName;
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