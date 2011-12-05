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
 * The persistent class for the REL_KEY_MAP database table.
 * 
 */
@Entity
@Table(name="TS_REL_KEY_MAP")
public class TsRelKeyMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REL_KEY_MAP_SEQ" ,sequenceName="REL_KEY_MAP_SEQ" ,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REL_KEY_MAP_SEQ")
	@Column(name="RKM_ID")
	private Long rkmId;

	@Column(name="RKM_KEY")
	private String rkmKey;

	@Column(name="RKM_NAME")
	private String rkmName;
	
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
    
	//bi-directional many-to-one association to RelTableMap
    @ManyToOne
	@JoinColumn(name="RTM_ID")
	private TsRelTableMap relTableMap;

    public TsRelKeyMap() {
    }

	public Long getRkmId() {
		return this.rkmId;
	}

	public void setRkmId(Long rkmId) {
		this.rkmId = rkmId;
	}

	public String getRkmKey() {
		return this.rkmKey;
	}

	public void setRkmKey(String rkmKey) {
		this.rkmKey = rkmKey;
	}

	public TsRelTableMap getRelTableMap() {
		return this.relTableMap;
	}

	public void setRelTableMap(TsRelTableMap relTableMap) {
		this.relTableMap = relTableMap;
	}

	public String getRkmName() {
		return rkmName;
	}

	public void setRkmName(String rkmName) {
		this.rkmName = rkmName;
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