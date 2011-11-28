package com.exciselaw.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the MVIEW_ORGANIZATION database table.
 * 
 */
@Entity
@Table(name="MVIEW_ORGANIZATION")
public class MviewOrganization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ORG_ID")
	private long orgId;
	
	@Column(name="LAST_REFRESH")
	private String lastRefresh;

	@Column(name="ORG_CODE")
	private String orgCode;

	@Column(name="ORG_ID_REF")
	private BigDecimal orgIdRef;

	@Column(name="ORG_NAME")
	private String orgName;

    public MviewOrganization() {
    }

	public String getLastRefresh() {
		return this.lastRefresh;
	}

	public void setLastRefresh(String lastRefresh) {
		this.lastRefresh = lastRefresh;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public long getOrgId() {
		return this.orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public BigDecimal getOrgIdRef() {
		return this.orgIdRef;
	}

	public void setOrgIdRef(BigDecimal orgIdRef) {
		this.orgIdRef = orgIdRef;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}