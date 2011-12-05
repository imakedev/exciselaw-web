package com.excise.law.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the MVIEW_PERSONEL database table.
 * 
 */
@Entity
@Table(name="MVIEW_PERSONEL")
public class MviewPersonel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PER_ID")
	private long perId;
	
	@Column(name="LAST_REFRESH")
	private String lastRefresh;

	@Column(name="ORG_ID")
	private BigDecimal orgId;

	@Column(name="ORG_ID_1")
	private BigDecimal orgId1;

	@Column(name="ORG_ID_2")
	private BigDecimal orgId2;

	@Column(name="PER_CARDNO")
	private String perCardno;

	@Column(name="PER_NAME")
	private String perName;

	@Column(name="PER_SURNAME")
	private String perSurname;

	@Column(name="PL_CODE")
	private String plCode;

	@Column(name="PL_NAME")
	private String plName;

	@Column(name="PM_CODE")
	private String pmCode;

	@Column(name="PM_NAME")
	private String pmName;

	@Column(name="POS_ID")
	private BigDecimal posId;

	public MviewPersonel() {
    }

	public String getLastRefresh() {
		return this.lastRefresh;
	}

	public void setLastRefresh(String lastRefresh) {
		this.lastRefresh = lastRefresh;
	}

	public BigDecimal getOrgId() {
		return this.orgId;
	}

	public void setOrgId(BigDecimal orgId) {
		this.orgId = orgId;
	}

	public BigDecimal getOrgId1() {
		return this.orgId1;
	}

	public void setOrgId1(BigDecimal orgId1) {
		this.orgId1 = orgId1;
	}

	public BigDecimal getOrgId2() {
		return this.orgId2;
	}

	public void setOrgId2(BigDecimal orgId2) {
		this.orgId2 = orgId2;
	}

	public String getPerCardno() {
		return this.perCardno!=null?this.perCardno.trim():"";
	}

	public void setPerCardno(String perCardno) {
		this.perCardno = perCardno!=null?perCardno.trim():"";
	}

	public long getPerId() {
		return this.perId;
	}

	public void setPerId(long perId) {
		this.perId = perId;
	}

	public String getPerName() {
		return this.perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	public String getPerSurname() {
		return this.perSurname;
	}

	public void setPerSurname(String perSurname) {
		this.perSurname = perSurname;
	}

	public String getPlCode() {
		return this.plCode;
	}

	public void setPlCode(String plCode) {
		this.plCode = plCode;
	}

	public String getPlName() {
		return this.plName;
	}

	public void setPlName(String plName) {
		this.plName = plName;
	}

	public String getPmCode() {
		return this.pmCode;
	}

	public void setPmCode(String pmCode) {
		this.pmCode = pmCode;
	}

	public String getPmName() {
		return this.pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public BigDecimal getPosId() {
		return this.posId;
	}

	public void setPosId(BigDecimal posId) {
		this.posId = posId;
	}

	public String getUsername() {
		return (this.perName!=null?this.perName:"")+" "+(this.perSurname!=null?this.perSurname:"");
	}

}