package com.excise.law.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


/**
 * The persistent class for the V_LAW database table.
 * 
 */
@Entity
@Table(name="V_LAW")
public class VLaw implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LAW_ID")
	private BigDecimal lawId;
	
	@Column(name="LAW_EXCISE_STATUS")
	private BigDecimal lawExciseStatus;

	@Column(name="LAW_TYPE_ID")
	private BigDecimal lawTypeId;

    @Lob()
	@Column(name="LAW_TITLE_THAI")
	private String lawTitleThai;
	
	@Column(name="LAW_TYPE_NAME")
	private String lawTypeName;

	@Column(name="LAW_ENFORCE_STATUS")
	private BigDecimal lawEnforceStatus;

	@Column(name="LAW_ENFORCE_STATUS_DESC")
	private String lawEnforceStatusDesc;
	
	@Column(name="STATUE_ID")
	private BigDecimal statueId;

	@Column(name="STATUE_NAME")
	private String statueName;

    public VLaw() {
    }

	public BigDecimal getLawId() {
		return lawId;
	}

	public void setLawId(BigDecimal lawId) {
		this.lawId = lawId;
	}

	public BigDecimal getLawExciseStatus() {
		return lawExciseStatus;
	}

	public void setLawExciseStatus(BigDecimal lawExciseStatus) {
		this.lawExciseStatus = lawExciseStatus;
	}

	public BigDecimal getLawTypeId() {
		return lawTypeId;
	}

	public void setLawTypeId(BigDecimal lawTypeId) {
		this.lawTypeId = lawTypeId;
	}

	public String getLawTitleThai() {
		return lawTitleThai.replaceAll("\\<.*?>","");
	}

	public void setLawTitleThai(String lawTitleThai) {	
		this.lawTitleThai = lawTitleThai;
	}

	public String getLawTypeName() {
		return lawTypeName;
	}

	public void setLawTypeName(String lawTypeName) {
		this.lawTypeName = lawTypeName;
	}

	public BigDecimal getLawEnforceStatus() {
		return lawEnforceStatus;
	}

	public void setLawEnforceStatus(BigDecimal lawEnforceStatus) {
		this.lawEnforceStatus = lawEnforceStatus;
	}

	public String getLawEnforceStatusDesc() {
		return lawEnforceStatusDesc;
	}

	public void setLawEnforceStatusDesc(String lawEnforceStatusDesc) {
		this.lawEnforceStatusDesc = lawEnforceStatusDesc;
	}

	public BigDecimal getStatueId() {
		return statueId;
	}

	public void setStatueId(BigDecimal statueId) {
		this.statueId = statueId;
	}

	public String getStatueName() {
		return statueName;
	}

	public void setStatueName(String statueName) {
		this.statueName = statueName;
	}
}